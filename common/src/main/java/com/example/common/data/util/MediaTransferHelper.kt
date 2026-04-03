package com.example.common.data.util

import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.example.common.data.model.ConflictResolution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Shared copy / move logic for media files in MediaStore.
 *
 * Both image-library and video-library have identical transfer loops that differ
 * only in which [Uri] collection they target (`MediaStore.Images.Media.*` vs
 * `MediaStore.Video.Media.*`).  This helper encapsulates the shared loop so it
 * is maintained in one place.
 *
 * Usage in a repository:
 * ```kotlin
 * suspend fun moveImages(images: List<ImageItem>, destFolderPath: String, …) =
 *     MediaTransferHelper.transfer(
 *         items            = images,
 *         mediaCollectionUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
 *         contentResolver  = contentResolver,
 *         getSourceUri     = { it.contentUri },
 *         getDisplayName   = { it.displayName },
 *         getMimeType      = { it.mimeType },
 *         getFilePath      = { getFilePath(it) },
 *         destFolderPath   = destFolderPath,
 *         deleteSource     = true,
 *         …
 *     )
 * ```
 */
object MediaTransferHelper {

    /**
     * Transfers [items] to [destFolderPath] inside [mediaCollectionUri].
     *
     * @param items              list of media items to transfer
     * @param mediaCollectionUri e.g. `MediaStore.Images.Media.EXTERNAL_CONTENT_URI`
     * @param contentResolver    caller's [ContentResolver]
     * @param getSourceUri       extracts the content [Uri] from an item
     * @param getDisplayName     extracts the display file name from an item
     * @param getMimeType        extracts the MIME type string from an item
     * @param getFilePath        returns the legacy file path for an item's URI (used as
     *                           delete fallback); may return `null`
     * @param context            needed for [MediaFileUtils.scanFile] on delete fallback
     * @param destFolderPath     absolute destination folder path
     * @param deleteSource       `true` = move, `false` = copy
     * @param onProgress         called with (completedIndex, total) after each item
     * @param isCancelled        polling cancellation flag; loop breaks when `true`
     * @param onConflict         suspending callback invoked when a same-name file exists
     *                           at the destination; returns the chosen [ConflictResolution]
     * @param logTag             tag for FileLogger messages
     */
    suspend fun <T> transfer(
        items: List<T>,
        mediaCollectionUri: Uri,
        contentResolver: ContentResolver,
        getSourceUri: (T) -> Uri,
        getDisplayName: (T) -> String,
        getMimeType: (T) -> String,
        getFilePath: (Uri) -> String?,
        context: android.content.Context,
        destFolderPath: String,
        deleteSource: Boolean,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME },
        logTag: String = "MediaTransferHelper"
    ): Boolean = withContext(Dispatchers.IO) {
        if (items.isEmpty()) return@withContext false
        val operationName = if (deleteSource) "Move" else "Copy"

        try {
            @Suppress("DEPRECATION")
            val externalRoot = Environment.getExternalStorageDirectory().absolutePath
            val relativePath = destFolderPath.removePrefix(externalRoot).trimStart('/')

            var transferred = 0

            for ((index, item) in items.withIndex()) {
                if (isCancelled()) break
                onProgress(index, items.size)

                val sourceUri   = getSourceUri(item)
                val displayName = getDisplayName(item)
                val mimeType    = getMimeType(item)

                try {
                    var finalName = displayName
                    val existingUri = MediaFileUtils.findExistingFile(
                        contentResolver, mediaCollectionUri, relativePath, displayName
                    )

                    // Always show the conflict dialog when a file with the same name
                    // already exists at the destination — even if it is the source file
                    // itself (e.g. moving / copying to the same folder).
                    val hasConflict = existingUri != null

                    if (hasConflict) {
                        when (onConflict(displayName)) {
                            ConflictResolution.SKIP,
                            ConflictResolution.SKIP_ALL    -> continue

                            ConflictResolution.REPLACE,
                            ConflictResolution.REPLACE_ALL -> {
                                if (existingUri == sourceUri) {
                                    // Same file — replacing it with itself is a no-op.
                                    // For move: already at destination; nothing to do.
                                    // For copy: would copy onto itself; meaningless.
                                    onProgress(index + 1, items.size)
                                    continue
                                }
                                // Different file — remove it so we can write the new one.
                                try { contentResolver.delete(existingUri, null, null) } catch (_: Exception) {}
                            }

                            ConflictResolution.RENAME      -> {
                                finalName = MediaFileUtils.generateUniqueName(
                                    contentResolver, mediaCollectionUri, relativePath, displayName
                                )
                            }
                        }
                    }

                    // ── Insert pending entry ──────────────────────────────
                    val destValues = ContentValues().apply {
                        put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, finalName)
                        put(android.provider.MediaStore.MediaColumns.MIME_TYPE, mimeType)
                        put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
                        put(android.provider.MediaStore.MediaColumns.IS_PENDING, 1)
                    }
                    val destUri = contentResolver.insert(mediaCollectionUri, destValues) ?: continue

                    // ── Stream copy ───────────────────────────────────────
                    val inputStream  = contentResolver.openInputStream(sourceUri)
                    if (inputStream == null) { contentResolver.delete(destUri, null, null); continue }
                    val outputStream = contentResolver.openOutputStream(destUri)
                    if (outputStream == null) {
                        inputStream.close()
                        contentResolver.delete(destUri, null, null)
                        continue
                    }

                    val copySuccess = try {
                        inputStream.use { inp -> outputStream.use { out -> inp.copyTo(out, bufferSize = 8192) } }
                        true
                    } catch (copyEx: Exception) {
                        Log.e(logTag, "$operationName stream copy failed for $displayName", copyEx)
                        false
                    }

                    if (copySuccess) {
                        // Mark complete
                        val updateValues = ContentValues().apply {
                            put(android.provider.MediaStore.MediaColumns.IS_PENDING, 0)
                        }
                        contentResolver.update(destUri, updateValues, null, null)

                        // Delete source for move operations
                        if (deleteSource) {
                            try {
                                contentResolver.delete(sourceUri, null, null)
                            } catch (_: Exception) {
                                val sourcePath = getFilePath(sourceUri)
                                if (sourcePath != null) {
                                    val sourceFile = File(sourcePath)
                                    sourceFile.delete()
                                    MediaFileUtils.scanFile(context, sourceFile)
                                }
                            }
                        }
                        transferred++
                    } else {
                        contentResolver.delete(destUri, null, null)
                    }
                } catch (itemEx: Exception) {
                    Log.e(logTag, "Failed to ${operationName.lowercase()} $displayName", itemEx)
                }
            }

            onProgress(items.size, items.size)
            transferred > 0
        } catch (opEx: Exception) {
            Log.e(logTag, "$operationName operation failed", opEx)
            false
        }
    }
}




