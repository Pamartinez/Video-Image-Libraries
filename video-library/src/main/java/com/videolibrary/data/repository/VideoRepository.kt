package com.videolibrary.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.FolderItem
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.VideoSortOption
import com.videolibrary.data.util.FileLogger as Log
import java.io.File

class VideoRepository(private val context: Context) {

    private val contentResolver: ContentResolver get() = context.contentResolver
    private val videoUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

    // ── Get Videos ──────────────────────────────────────────────────────

    suspend fun getVideos(
        videoSortOption: VideoSortOption = VideoSortOption.CUSTOM_ORDER,
        bucketId: Int? = null,
        searchQuery: String? = null
    ): List<VideoItem> = withContext(Dispatchers.IO) {
        val videos = mutableListOf<VideoItem>()

        val selection = buildSelection(bucketId, searchQuery)
        val sortOrderStr = buildVideoSortOrder(videoSortOption)

        try {
            contentResolver.query(
                videoUri,
                VideoItem.PROJECTION,
                selection,
                null,
                sortOrderStr
            )?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val displayNameCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val durationCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
                val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                val dateAddedCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
                val mimeTypeCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
                val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
                val widthCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
                val heightCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idCol)
                    val displayName = cursor.getString(displayNameCol) ?: ""
                    val path = cursor.getString(dataCol) ?: ""
                    val duration = cursor.getLong(durationCol)
                    val size = cursor.getLong(sizeCol)
                    val dateAdded = cursor.getLong(dateAddedCol)
                    val dateModified = cursor.getLong(dateModifiedCol)
                    val bId = cursor.getInt(bucketIdCol)
                    val bName = cursor.getString(bucketNameCol) ?: ""
                    val mimeType = cursor.getString(mimeTypeCol) ?: "video/*"
                    val title = cursor.getString(titleCol) ?: displayName.substringBeforeLast('.', displayName)
                    val width = cursor.getInt(widthCol)
                    val height = cursor.getInt(heightCol)
                    val contentUri = ContentUris.withAppendedId(videoUri, id)

                    videos.add(
                        VideoItem(
                            id = id,
                            title = title,
                            displayName = displayName,
                            path = path,
                            duration = duration,
                            size = size,
                            dateAdded = dateAdded,
                            dateModified = dateModified,
                            bucketId = bId,
                            bucketName = bName,
                            mimeType = mimeType,
                            contentUri = contentUri,
                            width = width,
                            height = height
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("VideoRepository", "Failed to load videos", e)
        }

        videos
    }

    // ── Get Folders ─────────────────────────────────────────────────────

    suspend fun getFolders(
        folderSortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER
    ): List<FolderItem> = withContext(Dispatchers.IO) {
        val folderMap = mutableMapOf<Int, FolderItem>()

        @Suppress("DEPRECATION")
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.DATA
        )

        @Suppress("DEPRECATION")
        val selection = "length(trim(${MediaStore.Video.Media.DATA})) > 0"

        val sortOrderStr = "${MediaStore.Video.Media.DATE_MODIFIED} DESC"

        try {
            contentResolver.query(videoUri, projection, selection, null, sortOrderStr)?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idCol)
                    val bId = cursor.getInt(bucketIdCol)
                    val bName = cursor.getString(bucketNameCol) ?: ""
                    val dateModified = cursor.getLong(dateModifiedCol)
                    val dataPath = cursor.getString(dataCol) ?: ""
                    val folderPath = File(dataPath).parent ?: ""


                    val existing = folderMap[bId]
                    if (existing != null) {
                        folderMap[bId] = existing.copy(
                            itemCount = existing.itemCount + 1,
                            latestDateModified = maxOf(existing.latestDateModified, dateModified),
                            latestItemUri = if (dateModified > existing.latestDateModified) {
                                ContentUris.withAppendedId(videoUri, id)
                            } else {
                                existing.latestItemUri
                            }
                        )
                    } else {
                        folderMap[bId] = FolderItem(
                            bucketId = bId,
                            name = bName,
                            itemCount = 1,
                            latestItemUri = ContentUris.withAppendedId(videoUri, id),
                            latestDateModified = dateModified,
                            path = folderPath
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("VideoRepository", "Failed to load folders", e)
        }

        // Apply sorting
        val folders = folderMap.values.toList()
        when (folderSortOption) {
            FolderSortOption.CUSTOM_ORDER -> folders.sortedByDescending { it.latestDateModified }
            FolderSortOption.NAME_A_TO_Z -> folders.sortedBy { it.name.lowercase() }
            FolderSortOption.NAME_Z_TO_A -> folders.sortedByDescending { it.name.lowercase() }
            FolderSortOption.ITEMS_MOST_FIRST -> folders.sortedByDescending { it.itemCount }
            FolderSortOption.ITEMS_FEWEST_FIRST -> folders.sortedBy { it.itemCount }
        }
    }

    // ── Delete Videos ───────────────────────────────────────────────────

    suspend fun deleteVideos(videoIds: List<Long>): Boolean = withContext(Dispatchers.IO) {
        try {
            var deleted = 0
            for (id in videoIds) {
                val uri = ContentUris.withAppendedId(videoUri, id)
                deleted += contentResolver.delete(uri, null, null)
            }
            deleted > 0
        } catch (e: Exception) {
            Log.e("VideoRepository", "Delete operation failed", e)
            false
        }
    }

    // ── Rename Video ────────────────────────────────────────────────────

    suspend fun renameVideo(id: Long, newName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val cleanName = newName.trim()
            if (cleanName.isBlank()) return@withContext false

            val uri = ContentUris.withAppendedId(videoUri, id)
            val values = ContentValues().apply {
                put(MediaStore.Video.Media.DISPLAY_NAME, cleanName)
                put(MediaStore.Video.Media.TITLE, cleanName.substringBeforeLast('.', cleanName))
            }
            contentResolver.update(uri, values, null, null) > 0
        } catch (e: Exception) {
            Log.e("VideoRepository", "Rename failed", e)
            false
        }
    }

    // ── Move Videos ─────────────────────────────────────────────────────

    suspend fun moveVideos(
        videos: List<VideoItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = transferVideos(videos, destFolderPath, deleteSource = true, onProgress, isCancelled, onConflict)

    // ── Copy Videos ─────────────────────────────────────────────────────

    suspend fun copyVideos(
        videos: List<VideoItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = transferVideos(videos, destFolderPath, deleteSource = false, onProgress, isCancelled, onConflict)

    // ── Shared transfer logic ───────────────────────────────────────────

    private suspend fun transferVideos(
        videos: List<VideoItem>,
        destFolderPath: String,
        deleteSource: Boolean,
        onProgress: (current: Int, total: Int) -> Unit,
        isCancelled: () -> Boolean,
        onConflict: suspend (fileName: String) -> ConflictResolution
    ): Boolean = withContext(Dispatchers.IO) {
        if (videos.isEmpty()) return@withContext false
        val tag = if (deleteSource) "Move" else "Copy"

        try {
            @Suppress("DEPRECATION")
            val externalRoot = Environment.getExternalStorageDirectory().absolutePath
            val relativePath = destFolderPath.removePrefix(externalRoot).trimStart('/')

            var transferred = 0
            for ((index, video) in videos.withIndex()) {
                if (isCancelled()) break
                onProgress(index, videos.size)

                val sourceUri = video.contentUri
                val displayName = video.displayName
                val mimeType = video.mimeType

                try {
                    // Check if a file with the same name already exists at destination
                    var finalName = displayName
                    val existingUri = findExistingFile(relativePath, displayName)
                    // For copy, treat any same-name match as conflict (including same source URI).
                    // For move, ignore self-match so same-folder move does not prompt unnecessarily.
                    val hasConflict = if (deleteSource) {
                        existingUri != null && existingUri != sourceUri
                    } else {
                        existingUri != null
                    }
                    if (hasConflict) {
                        when (onConflict(displayName)) {
                            ConflictResolution.SKIP,
                            ConflictResolution.SKIP_ALL    -> continue
                            ConflictResolution.REPLACE,
                            ConflictResolution.REPLACE_ALL -> {
                                existingUri?.let { uri ->
                                    try { contentResolver.delete(uri, null, null) } catch (_: Exception) {}
                                }
                            }
                            ConflictResolution.RENAME      -> finalName = generateUniqueName(relativePath, displayName)
                        }
                    }

                    val destValues = ContentValues().apply {
                        put(MediaStore.Video.Media.DISPLAY_NAME, finalName)
                        put(MediaStore.Video.Media.MIME_TYPE, mimeType)
                        put(MediaStore.Video.Media.RELATIVE_PATH, relativePath)
                        put(MediaStore.Video.Media.IS_PENDING, 1)
                    }
                    val destUri = contentResolver.insert(videoUri, destValues) ?: continue

                    val inputStream = contentResolver.openInputStream(sourceUri)
                    if (inputStream == null) { contentResolver.delete(destUri, null, null); continue }
                    val outputStream = contentResolver.openOutputStream(destUri)
                    if (outputStream == null) { inputStream.close(); contentResolver.delete(destUri, null, null); continue }

                    val copySuccess = try {
                        inputStream.use { inp -> outputStream.use { out -> inp.copyTo(out, bufferSize = 8192) } }
                        true
                    } catch (e: Exception) { Log.e("VideoRepository", "$tag stream copy failed for $displayName", e); false }

                    if (copySuccess) {
                        val updateValues = ContentValues().apply { put(MediaStore.Video.Media.IS_PENDING, 0) }
                        contentResolver.update(destUri, updateValues, null, null)
                        if (deleteSource) {
                            try { contentResolver.delete(sourceUri, null, null) } catch (_: Exception) {
                                val sourcePath = getFilePath(sourceUri)
                                if (sourcePath != null) { File(sourcePath).delete(); scanFile(File(sourcePath)) }
                            }
                        }
                        transferred++
                    } else { contentResolver.delete(destUri, null, null) }
                } catch (e: Exception) { Log.e("VideoRepository", "Failed to ${tag.lowercase()} $displayName", e) }
            }
            onProgress(videos.size, videos.size)
            transferred > 0
        } catch (e: Exception) { Log.e("VideoRepository", "$tag operation failed", e); false }
    }

    // -- Create a new folder inside DCIM ------------------------------------------------
    suspend fun createFolder(name: String): String? = withContext(Dispatchers.IO) {
        try {
            @Suppress("DEPRECATION")
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val folder = File(dcim, name)
            if (!folder.exists()) folder.mkdirs()
            folder.absolutePath
        } catch (e: Exception) {
            Log.e("VideoRepository", "Create folder failed", e)
            null
        }
    }

    // ── List existing DCIM sub-folder names ────────────────────────────

    suspend fun getExistingDcimFolderNames(): Set<String> = withContext(Dispatchers.IO) {
        try {
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            dcim.listFiles()?.filter { it.isDirectory }?.map { it.name }?.toSet() ?: emptySet()
        } catch (e: Exception) {
            Log.e("VideoRepository", "Failed to list DCIM folders", e)
            emptySet()
        }
    }


    // ── Private Helpers ─────────────────────────────────────────────────

    private fun generateUniqueName(relativePath: String, displayName: String): String {
        val dotIndex = displayName.lastIndexOf('.')
        val stem = if (dotIndex >= 0) displayName.substring(0, dotIndex) else displayName
        val ext  = if (dotIndex >= 0) displayName.substring(dotIndex) else ""
        for (counter in 1..9999) {
            val candidate = "$stem($counter)$ext"
            if (findExistingFile(relativePath, candidate) == null) return candidate
        }
        return "$stem(${System.currentTimeMillis()})$ext"
    }

    private fun findExistingFile(relativePath: String, displayName: String): Uri? {
        val normalizedPath = relativePath.trimEnd('/') + "/"
        val selection = "${MediaStore.Video.Media.RELATIVE_PATH} = ? AND ${MediaStore.Video.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(normalizedPath, displayName)
        return try {
            contentResolver.query(videoUri, arrayOf(MediaStore.Video.Media._ID), selection, selectionArgs, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val id = cursor.getLong(0)
                    ContentUris.withAppendedId(videoUri, id)
                } else null
            }
        } catch (_: Exception) { null }
    }

    private fun queryStringColumn(uri: Uri, column: String): String? {
        return try {
            contentResolver.query(uri, arrayOf(column), null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(0) else null
            }
        } catch (_: Exception) { null }
    }

    @Suppress("DEPRECATION")
    private fun getFilePath(uri: Uri): String? = queryStringColumn(uri, MediaStore.Video.Media.DATA)

    private fun scanFile(file: File) {
        try {
            android.media.MediaScannerConnection.scanFile(
                context, arrayOf(file.absolutePath), null, null
            )
        } catch (_: Exception) { }
    }

    private fun buildSelection(bucketId: Int?, searchQuery: String?): String {
        val sb = StringBuilder()
        @Suppress("DEPRECATION")
        sb.append("length(trim(${MediaStore.Video.Media.DATA})) > 0")
        sb.append(" AND ${MediaStore.Video.Media.DATA} NOT LIKE '/storage/sdcard0/cloudagent/cache%'")

        if (bucketId != null && bucketId != 0) {
            @Suppress("DEPRECATION")
            sb.append(" AND ${MediaStore.Video.Media.BUCKET_ID} = $bucketId")
        }

        if (!searchQuery.isNullOrBlank()) {
            val terms = searchQuery.trim().split("\\s+".toRegex())
            for (term in terms) {
                val escaped = term.replace("!", "!!").replace("%", "!%").replace("_", "!_")
                sb.append(" AND ${MediaStore.Video.Media.DISPLAY_NAME} LIKE '%$escaped%' ESCAPE '!'")
            }
        }

        return sb.toString()
    }


    private fun buildVideoSortOrder(option: VideoSortOption): String {
        return when (option) {
            VideoSortOption.CUSTOM_ORDER      -> "${MediaStore.Video.Media.DATE_MODIFIED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.NAME_A_TO_Z       -> "${MediaStore.Video.Media.DISPLAY_NAME} COLLATE NOCASE ASC"
            VideoSortOption.NAME_Z_TO_A       -> "${MediaStore.Video.Media.DISPLAY_NAME} COLLATE NOCASE DESC"
            VideoSortOption.DATE_CREATED_ASC   -> "${MediaStore.Video.Media.DATE_ADDED} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DATE_CREATED_DESC  -> "${MediaStore.Video.Media.DATE_ADDED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.DATE_MODIFIED_ASC  -> "${MediaStore.Video.Media.DATE_MODIFIED} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DATE_MODIFIED_DESC -> "${MediaStore.Video.Media.DATE_MODIFIED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.DURATION_ASC       -> "${MediaStore.Video.Media.DURATION} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DURATION_DESC      -> "${MediaStore.Video.Media.DURATION} DESC, ${MediaStore.Video.Media._ID} DESC"
        }
    }
}
