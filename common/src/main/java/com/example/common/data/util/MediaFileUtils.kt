package com.example.common.data.util

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Shared file / folder utilities used by both image-library and video-library.
 *
 * All suspend functions run on [Dispatchers.IO] internally.
 */
object MediaFileUtils {

    // ── Folder creation ──────────────────────────────────────────────────────

    /**
     * Creates a folder named [folderName] inside the device's DCIM directory.
     * Returns the absolute path of the created (or already-existing) folder,
     * or `null` if creation fails.
     */
    suspend fun createFolder(folderName: String): String? = withContext(Dispatchers.IO) {
        try {
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val folder = File(dcim, folderName)
            if (!folder.exists()) folder.mkdirs()
            if (folder.exists()) folder.absolutePath else null
        } catch (e: Exception) {
            null
        }
    }

    // ── DCIM folder listing ──────────────────────────────────────────────────

    /**
     * Returns the names of all sub-directories currently inside DCIM.
     * Used to validate new folder names before creation.
     */
    suspend fun getExistingDcimFolderNames(): Set<String> = withContext(Dispatchers.IO) {
        try {
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            dcim.listFiles()?.filter { it.isDirectory }?.map { it.name }?.toSet() ?: emptySet()
        } catch (e: Exception) {
            emptySet()
        }
    }

    // ── Media scanner ────────────────────────────────────────────────────────

    /**
     * Asks the system media scanner to index [file] so it appears in MediaStore.
     */
    fun scanFile(context: android.content.Context, file: File) {
        try {
            android.media.MediaScannerConnection.scanFile(
                context, arrayOf(file.absolutePath), null, null
            )
        } catch (_: Exception) { }
    }

    // ── Unique name generation ────────────────────────────────────────────────

    /**
     * Returns a unique display name for [displayName] inside [relativePath] by appending
     * an incrementing counter before the extension: `photo.jpg` → `photo(1).jpg` → …
     *
     * Queries [mediaCollectionUri] (either Images or Video) in MediaStore to check
     * for collisions, falling back to a timestamp suffix after 9999 iterations.
     */
    fun generateUniqueName(
        contentResolver: ContentResolver,
        mediaCollectionUri: Uri,
        relativePath: String,
        displayName: String
    ): String {
        val dotIndex = displayName.lastIndexOf('.')
        val stem = if (dotIndex >= 0) displayName.substring(0, dotIndex) else displayName
        val ext  = if (dotIndex >= 0) displayName.substring(dotIndex) else ""

        for (counter in 1..9999) {
            val candidate = "$stem($counter)$ext"
            if (findExistingFile(contentResolver, mediaCollectionUri, relativePath, candidate) == null)
                return candidate
        }
        return "$stem(${System.currentTimeMillis()})$ext"
    }

    // ── Existing file lookup ─────────────────────────────────────────────────

    /**
     * Returns the [Uri] of an existing file in MediaStore that matches [displayName]
     * inside [relativePath], or `null` if none is found.
     */
    fun findExistingFile(
        contentResolver: ContentResolver,
        mediaCollectionUri: Uri,
        relativePath: String,
        displayName: String
    ): Uri? {
        val normalizedPath = relativePath.trimEnd('/') + "/"
        val selection = "${MediaStore.MediaColumns.RELATIVE_PATH} = ? AND ${MediaStore.MediaColumns.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(normalizedPath, displayName)
        return try {
            contentResolver.query(
                mediaCollectionUri,
                arrayOf(MediaStore.MediaColumns._ID),
                selection,
                selectionArgs,
                null
            )?.use { cursor ->
                if (cursor.moveToFirst())
                    ContentUris.withAppendedId(mediaCollectionUri, cursor.getLong(0))
                else null
            }
        } catch (_: Exception) { null }
    }

    // ── Build shared MediaStore selection ────────────────────────────────────

    /**
     * Builds a MediaStore WHERE clause that:
     * - Excludes empty/cache paths
     * - Optionally filters by [bucketId]
     * - Optionally filters by all [searchQuery] terms (AND logic)
     *
     * [dataColumn] should be e.g. `MediaStore.Images.Media.DATA` or
     * `MediaStore.Video.Media.DATA` (suppressed deprecation is the caller's responsibility).
     * [bucketIdColumn] should be the matching `BUCKET_ID` column.
     * [displayNameColumn] should be the matching `DISPLAY_NAME` column.
     */
    fun buildSelection(
        dataColumn: String,
        bucketIdColumn: String,
        displayNameColumn: String,
        bucketId: Int?,
        searchQuery: String?
    ): String {
        val sb = StringBuilder()
        sb.append("length(trim($dataColumn)) > 0")
        sb.append(" AND $dataColumn NOT LIKE '/storage/sdcard0/cloudagent/cache%'")

        if (bucketId != null && bucketId != 0) {
            sb.append(" AND $bucketIdColumn = $bucketId")
        }

        if (!searchQuery.isNullOrBlank()) {
            val terms = searchQuery.trim().split("\\s+".toRegex())
            for (term in terms) {
                val escaped = term.replace("!", "!!").replace("%", "!%").replace("_", "!_")
                sb.append(" AND $displayNameColumn LIKE '%$escaped%' ESCAPE '!'")
            }
        }

        return sb.toString()
    }
}

