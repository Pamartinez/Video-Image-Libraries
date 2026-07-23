package com.example.common.data.dropbox

import android.content.ContentResolver
import android.net.Uri
import com.example.common.data.dropbox.api.DropboxApi
import com.example.common.data.dropbox.api.DropboxContentApi
import com.example.common.data.dropbox.model.CommitInfo
import com.example.common.data.dropbox.model.CreateFolderRequest
import com.example.common.data.dropbox.model.Entry
import com.example.common.data.dropbox.model.FileMetadata
import com.example.common.data.dropbox.model.ListFolderContinueRequest
import com.example.common.data.dropbox.model.ListFolderRequest
import com.example.common.data.dropbox.model.UploadArg
import com.example.common.data.dropbox.model.UploadCursor
import com.example.common.data.dropbox.model.UploadSessionAppendArg
import com.example.common.data.dropbox.model.UploadSessionFinishArg
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/** Outcome of an upload attempt. */
sealed class UploadResult {
    data class Success(val metadata: FileMetadata?) : UploadResult()
    /** The destination already has a file with this name (mode=add, autorename=false). */
    object Conflict : UploadResult()
    data class Error(val code: Int, val message: String?) : UploadResult()
}

/**
 * High-level Dropbox data operations: listing folders and uploading files (single-shot or chunked
 * session for large files). App-agnostic — lives in `common` so any app can reuse it.
 */
class DropboxRepository(
    private val contentResolver: ContentResolver,
    private val api: DropboxApi,
    private val contentApi: DropboxContentApi,
    moshi: Moshi
) {
    private val uploadArgAdapter = moshi.adapter(UploadArg::class.java)
    private val appendArgAdapter = moshi.adapter(UploadSessionAppendArg::class.java)
    private val finishArgAdapter = moshi.adapter(UploadSessionFinishArg::class.java)
    private val emptyBody: RequestBody =
        ByteArray(0).toRequestBody("application/octet-stream".toMediaType())

    /** Lists the folders (not files) directly under [path]. Root is "". */
    suspend fun listFolders(path: String): List<Entry> {
        val normalized = if (path == "/" || path.isBlank()) "" else path
        val folders = mutableListOf<Entry>()
        var response = api.listFolder(ListFolderRequest(path = normalized))
        var body = response.body()
        if (!response.isSuccessful || body == null) {
            throw DropboxException(response.code(), response.errorBody()?.string())
        }
        folders += body.entries.filter { it.isFolder }
        while (body?.hasMore == true && body.cursor != null) {
            response = api.listFolderContinue(ListFolderContinueRequest(body.cursor!!))
            body = response.body()
            if (!response.isSuccessful || body == null) {
                throw DropboxException(response.code(), response.errorBody()?.string())
            }
            folders += body.entries.filter { it.isFolder }
        }
        return folders.sortedBy { it.name?.lowercase() }
    }

    /**
     * Lists the file (non-folder) names directly under [path], lowercased, for up-front conflict
     * checks. Root is "". A missing destination folder yields an empty set (nothing to conflict with).
     */
    suspend fun listFileNames(path: String): Set<String> {
        val normalized = if (path == "/" || path.isBlank()) "" else path
        val names = mutableSetOf<String>()
        val response = try {
            api.listFolder(ListFolderRequest(path = normalized))
        } catch (e: Exception) {
            return emptySet()
        }
        var body = response.body()
        if (!response.isSuccessful || body == null) return emptySet()
        body.entries.filter { !it.isFolder }.forEach { e -> e.name?.let { names += it.lowercase() } }
        while (body?.hasMore == true && body.cursor != null) {
            val cont = api.listFolderContinue(ListFolderContinueRequest(body.cursor!!))
            body = cont.body()
            if (!cont.isSuccessful || body == null) break
            body.entries.filter { !it.isFolder }.forEach { e -> e.name?.let { names += it.lowercase() } }
        }
        return names
    }

    /**
     * Creates a new folder named [name] under [parentPath] (root is ""). Returns the created
     * folder's [Entry]. [autorename]=false so a name clash surfaces as a [DropboxException].
     */
    suspend fun createFolder(parentPath: String, name: String, autorename: Boolean = false): Entry {
        val normalizedParent = if (parentPath == "/" || parentPath.isBlank()) "" else parentPath.trimEnd('/')
        val fullPath = "$normalizedParent/${name.trim()}"
        val response = api.createFolder(CreateFolderRequest(path = fullPath, autorename = autorename))
        val body = response.body()
        if (!response.isSuccessful || body?.metadata == null) {
            throw DropboxException(response.code(), response.errorBody()?.string())
        }
        return body.metadata
    }

    /**
     * Uploads [uri] to [destPath] (full Dropbox path incl. filename). Chooses single-shot vs session
     * upload based on [size]. [mode]/[autorename] map from the user's conflict choice.
     */
    suspend fun uploadFile(
        uri: Uri,
        destPath: String,
        size: Long,
        mode: String,
        autorename: Boolean,
        onProgress: (bytesWritten: Long, total: Long) -> Unit
    ): UploadResult {
        return if (size in 0 until DropboxConfig.UPLOAD_SINGLE_LIMIT) {
            uploadSingle(uri, destPath, size, mode, autorename, onProgress)
        } else {
            uploadSession(uri, destPath, size, mode, autorename, onProgress)
        }
    }

    private suspend fun uploadSingle(
        uri: Uri,
        destPath: String,
        size: Long,
        mode: String,
        autorename: Boolean,
        onProgress: (Long, Long) -> Unit
    ): UploadResult {
        val arg = UploadArg(path = destPath, mode = mode, autorename = autorename)
        val header = asciiSafe(uploadArgAdapter.toJson(arg))
        val body = ProgressRequestBody(
            streamProvider = { openStream(uri) },
            totalBytes = size,
            onProgress = onProgress
        )
        val response = contentApi.upload(header, body)
        return mapUploadResponse(response.isSuccessful, response.code(), response.errorBody()?.string(), response.body())
    }

    private suspend fun uploadSession(
        uri: Uri,
        destPath: String,
        size: Long,
        mode: String,
        autorename: Boolean,
        onProgress: (Long, Long) -> Unit
    ): UploadResult {
        val chunkSize = DropboxConfig.UPLOAD_CHUNK_SIZE
        openStream(uri).use { input ->
            // Start session with the first chunk.
            val startResp = contentApi.uploadSessionStart(
                asciiSafe("""{"close":false}"""),
                readChunk(input, chunkSize).toRequestBody("application/octet-stream".toMediaType())
            )
            val start = startResp.body()
            if (!startResp.isSuccessful || start == null) {
                return mapUploadResponse(false, startResp.code(), startResp.errorBody()?.string(), null)
            }
            val sessionId = start.sessionId
            var offset = minOf(chunkSize, size)
            onProgress(offset, size)

            while (offset < size) {
                val chunk = readChunk(input, chunkSize)
                if (chunk.isEmpty()) break
                val appendArg = UploadSessionAppendArg(UploadCursor(sessionId, offset), close = false)
                val appendResp = contentApi.uploadSessionAppend(
                    asciiSafe(appendArgAdapter.toJson(appendArg)),
                    chunk.toRequestBody("application/octet-stream".toMediaType())
                )
                if (!appendResp.isSuccessful) {
                    return mapUploadResponse(false, appendResp.code(), appendResp.errorBody()?.string(), null)
                }
                offset += chunk.size
                onProgress(offset, size)
            }

            val finishArg = UploadSessionFinishArg(
                cursor = UploadCursor(sessionId, size),
                commit = CommitInfo(path = destPath, mode = mode, autorename = autorename)
            )
            val finishResp = contentApi.uploadSessionFinish(asciiSafe(finishArgAdapter.toJson(finishArg)), emptyBody)
            return mapUploadResponse(finishResp.isSuccessful, finishResp.code(), finishResp.errorBody()?.string(), finishResp.body())
        }
    }

    private fun mapUploadResponse(
        successful: Boolean,
        code: Int,
        errorBody: String?,
        metadata: FileMetadata?
    ): UploadResult {
        if (successful) return UploadResult.Success(metadata)
        // Dropbox returns 409 with "path/conflict/..." when a name clash occurs.
        if (code == 409 && errorBody?.contains("conflict") == true) return UploadResult.Conflict
        return UploadResult.Error(code, errorBody)
    }

    private fun openStream(uri: Uri) =
        contentResolver.openInputStream(uri) ?: throw DropboxException(-1, "Cannot open stream for $uri")

    private fun readChunk(input: java.io.InputStream, size: Long): ByteArray {
        val out = java.io.ByteArrayOutputStream()
        val buffer = ByteArray(16 * 1024)
        var remaining = size
        while (remaining > 0) {
            val toRead = minOf(buffer.size.toLong(), remaining).toInt()
            val read = input.read(buffer, 0, toRead)
            if (read == -1) break
            out.write(buffer, 0, read)
            remaining -= read
        }
        return out.toByteArray()
    }

    /** Dropbox-API-Arg values must be ASCII; escape any non-ASCII char as \uXXXX. */
    private fun asciiSafe(json: String): String {
        val sb = StringBuilder(json.length)
        for (ch in json) {
            if (ch.code in 0x20..0x7E) {
                sb.append(ch)
            } else {
                sb.append("\\u").append(ch.code.toString(16).padStart(4, '0'))
            }
        }
        return sb.toString()
    }
}

class DropboxException(val code: Int, message: String?) : Exception(message)
