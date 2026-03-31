package com.imagelibrary.data.repository

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
import com.imagelibrary.data.model.ImageSortOption
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.SortOrder
import com.imagelibrary.data.model.SortType
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.util.FileLogger as Log
import java.io.File

class ImageRepository(private val context: Context) {

    private val contentResolver: ContentResolver get() = context.contentResolver
    private val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    // ── Get Images ──────────────────────────────────────────────────────

    suspend fun getImages(
        imageSortOption: ImageSortOption = ImageSortOption.CUSTOM_ORDER,
        bucketId: Int? = null,
        searchQuery: String? = null
    ): List<ImageItem> = withContext(Dispatchers.IO) {
        val images = mutableListOf<ImageItem>()

        val (sortType, sortOrder) = imageSortOptionToTypeOrder(imageSortOption)
        val selection = buildSelection(bucketId, searchQuery)
        val sortOrderStr = buildSortOrder(sortType, sortOrder)

        try {
            contentResolver.query(
                imageUri,
                ImageItem.PROJECTION,
                selection,
                null,
                sortOrderStr
            )?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val sizeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val mimeTypeCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
                val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
                val widthCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idCol)
                    val displayName = cursor.getString(displayNameCol) ?: ""
                    val path = cursor.getString(dataCol) ?: ""
                    val size = cursor.getLong(sizeCol)
                    val dateModified = cursor.getLong(dateModifiedCol)
                    val bId = cursor.getInt(bucketIdCol)
                    val bName = cursor.getString(bucketNameCol) ?: ""
                    val mimeType = cursor.getString(mimeTypeCol) ?: "image/*"
                    val title = cursor.getString(titleCol) ?: displayName.substringBeforeLast('.', displayName)
                    val width = cursor.getInt(widthCol)
                    val height = cursor.getInt(heightCol)
                    val contentUri = ContentUris.withAppendedId(imageUri, id)

                    images.add(
                        ImageItem(
                            id = id,
                            title = title,
                            displayName = displayName,
                            path = path,
                            size = size,
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
            Log.e("ImageRepository", "Failed to load images", e)
        }

        images
    }

    // ── Get Folders ─────────────────────────────────────────────────────

    suspend fun getFolders(
        sortOption: SortOption = SortOption.CUSTOM_ORDER
    ): List<FolderItem> = withContext(Dispatchers.IO) {
        val folderMap = mutableMapOf<Int, FolderItem>()

        @Suppress("DEPRECATION")
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATA
        )

        @Suppress("DEPRECATION")
        val selection = "length(trim(${MediaStore.Images.Media.DATA})) > 0"

        val sortOrderStr = "${MediaStore.Images.Media.DATE_MODIFIED} DESC"

        try {
            contentResolver.query(imageUri, projection, selection, null, sortOrderStr)?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

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
                                ContentUris.withAppendedId(imageUri, id)
                            } else {
                                existing.latestItemUri
                            }
                        )
                    } else {
                        folderMap[bId] = FolderItem(
                            bucketId = bId,
                            name = bName,
                            itemCount = 1,
                            latestItemUri = ContentUris.withAppendedId(imageUri, id),
                            latestDateModified = dateModified,
                            path = folderPath
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ImageRepository", "Failed to load folders", e)
        }

        // Apply sorting
        val folders = folderMap.values.toList()
        when (sortOption) {
            SortOption.CUSTOM_ORDER -> folders  // raw order; ViewModel applies persisted custom order
            SortOption.NAME_A_TO_Z -> folders.sortedBy { it.name.lowercase() }
            SortOption.NAME_Z_TO_A -> folders.sortedByDescending { it.name.lowercase() }
            SortOption.ITEMS_MOST_FIRST -> folders.sortedByDescending { it.itemCount }
            SortOption.ITEMS_FEWEST_FIRST -> folders.sortedBy { it.itemCount }
        }
    }

    // ── Delete Images ───────────────────────────────────────────────────

    suspend fun deleteImages(imageIds: List<Long>): Boolean = withContext(Dispatchers.IO) {
        try {
            var deleted = 0
            for (id in imageIds) {
                val uri = ContentUris.withAppendedId(imageUri, id)
                deleted += contentResolver.delete(uri, null, null)
            }
            deleted > 0
        } catch (e: Exception) {
            Log.e("ImageRepository", "Delete operation failed", e)
            false
        }
    }

    // ── Rename Image ────────────────────────────────────────────────────

    suspend fun renameImage(id: Long, newName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val cleanName = newName.trim()
            if (cleanName.isBlank()) return@withContext false

            val uri = ContentUris.withAppendedId(imageUri, id)
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, cleanName)
                put(MediaStore.Images.Media.TITLE, cleanName.substringBeforeLast('.', cleanName))
            }
            contentResolver.update(uri, values, null, null) > 0
        } catch (e: Exception) {
            Log.e("ImageRepository", "Rename failed", e)
            false
        }
    }

    // ── Move Images ─────────────────────────────────────────────────────

    suspend fun moveImages(
        images: List<ImageItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = transferImages(images, destFolderPath, deleteSource = true, onProgress, isCancelled, onConflict)

    // ── Copy Images ─────────────────────────────────────────────────────

    suspend fun copyImages(
        images: List<ImageItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = transferImages(images, destFolderPath, deleteSource = false, onProgress, isCancelled, onConflict)

    // ── Shared transfer logic ───────────────────────────────────────────

    private suspend fun transferImages(
        images: List<ImageItem>,
        destFolderPath: String,
        deleteSource: Boolean,
        onProgress: (current: Int, total: Int) -> Unit,
        isCancelled: () -> Boolean,
        onConflict: suspend (fileName: String) -> ConflictResolution
    ): Boolean = withContext(Dispatchers.IO) {
        if (images.isEmpty()) return@withContext false
        val tag = if (deleteSource) "Move" else "Copy"

        try {
            @Suppress("DEPRECATION")
            val externalRoot = Environment.getExternalStorageDirectory().absolutePath
            val relativePath = destFolderPath.removePrefix(externalRoot).trimStart('/')

            var transferred = 0
            for ((index, image) in images.withIndex()) {
                if (isCancelled()) break
                onProgress(index, images.size)

                val sourceUri = image.contentUri
                val displayName = image.displayName
                val mimeType = image.mimeType

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
                            ConflictResolution.SKIP      -> continue
                            ConflictResolution.SKIP_ALL  -> continue
                            ConflictResolution.REPLACE   -> {
                                existingUri?.let { uri ->
                                    try { contentResolver.delete(uri, null, null) } catch (_: Exception) {}
                                }
                            }
                            ConflictResolution.REPLACE_ALL -> {
                                existingUri?.let { uri ->
                                    try { contentResolver.delete(uri, null, null) } catch (_: Exception) {}
                                }
                            }
                            ConflictResolution.RENAME -> {
                                finalName = generateUniqueName(relativePath, displayName)
                            }
                        }
                    }

                    val destValues = ContentValues().apply {
                        put(MediaStore.Images.Media.DISPLAY_NAME, finalName)
                        put(MediaStore.Images.Media.MIME_TYPE, mimeType)
                        put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
                        put(MediaStore.Images.Media.IS_PENDING, 1)
                    }
                    val destUri = contentResolver.insert(imageUri, destValues) ?: continue

                    val inputStream = contentResolver.openInputStream(sourceUri)
                    if (inputStream == null) { contentResolver.delete(destUri, null, null); continue }
                    val outputStream = contentResolver.openOutputStream(destUri)
                    if (outputStream == null) { inputStream.close(); contentResolver.delete(destUri, null, null); continue }

                    val copySuccess = try {
                        inputStream.use { inp -> outputStream.use { out -> inp.copyTo(out, bufferSize = 8192) } }
                        true
                    } catch (e: Exception) { Log.e("ImageRepository", "$tag stream copy failed for $displayName", e); false }

                    if (copySuccess) {
                        val updateValues = ContentValues().apply { put(MediaStore.Images.Media.IS_PENDING, 0) }
                        contentResolver.update(destUri, updateValues, null, null)
                        if (deleteSource) {
                            try { contentResolver.delete(sourceUri, null, null) } catch (_: Exception) {
                                val sourcePath = getFilePath(sourceUri)
                                if (sourcePath != null) { File(sourcePath).delete(); scanFile(File(sourcePath)) }
                            }
                        }
                        transferred++
                    } else { contentResolver.delete(destUri, null, null) }
                } catch (e: Exception) { Log.e("ImageRepository", "Failed to ${tag.lowercase()} $displayName", e) }
            }
            onProgress(images.size, images.size)
            transferred > 0
        } catch (e: Exception) { Log.e("ImageRepository", "$tag operation failed", e); false }
    }

    // ── List DCIM Folder Names ───────────────────────────────────────────

    suspend fun getExistingDcimFolderNames(): Set<String> = withContext(Dispatchers.IO) {
        try {
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            dcim.listFiles()
                ?.filter { it.isDirectory }
                ?.map { it.name }
                ?.toSet()
                ?: emptySet()
        } catch (e: Exception) {
            Log.e("ImageRepository", "Failed to list DCIM folders", e)
            emptySet()
        }
    }

    // ── Create Folder ───────────────────────────────────────────────────

    suspend fun createFolder(folderName: String): String? = withContext(Dispatchers.IO) {
        try {
            val folder = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                folderName
            )
            if (!folder.exists()) {
                if (folder.mkdirs()) folder.absolutePath else null
            } else {
                folder.absolutePath
            }
        } catch (e: Exception) {
            Log.e("ImageRepository", "Create folder failed", e)
            null
        }
    }

    // ── Private Helpers ─────────────────────────────────────────────────

    /**
     * Generates a unique file name for [displayName] inside [relativePath] by appending
     * an incrementing counter in parentheses before the extension:
     *   pablo.jpg → pablo(1).jpg → pablo(2).jpg → …
     * Keeps incrementing until no existing file with that name is found in MediaStore.
     * Falls back to a timestamp-based suffix after 9999 attempts to avoid infinite loops.
     */
    private fun generateUniqueName(relativePath: String, displayName: String): String {
        val dotIndex = displayName.lastIndexOf('.')
        val stem = if (dotIndex >= 0) displayName.substring(0, dotIndex) else displayName
        val ext  = if (dotIndex >= 0) displayName.substring(dotIndex) else ""   // includes the dot

        for (counter in 1..9999) {
            val candidate = "$stem($counter)$ext"
            if (findExistingFile(relativePath, candidate) == null) return candidate
        }
        // Extremely unlikely fallback: use current time millis to guarantee uniqueness
        return "$stem(${System.currentTimeMillis()})$ext"
    }

    private fun findExistingFile(relativePath: String, displayName: String): Uri? {
        val normalizedPath = relativePath.trimEnd('/') + "/"
        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} = ? AND ${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(normalizedPath, displayName)
        return try {
            contentResolver.query(imageUri, arrayOf(MediaStore.Images.Media._ID), selection, selectionArgs, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val id = cursor.getLong(0)
                    ContentUris.withAppendedId(imageUri, id)
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
    private fun getFilePath(uri: Uri): String? = queryStringColumn(uri, MediaStore.Images.Media.DATA)

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
        sb.append("length(trim(${MediaStore.Images.Media.DATA})) > 0")
        sb.append(" AND ${MediaStore.Images.Media.DATA} NOT LIKE '/storage/sdcard0/cloudagent/cache%'")

        if (bucketId != null && bucketId != 0) {
            @Suppress("DEPRECATION")
            sb.append(" AND ${MediaStore.Images.Media.BUCKET_ID} = $bucketId")
        }

        if (!searchQuery.isNullOrBlank()) {
            val terms = searchQuery.trim().split("\\s+".toRegex())
            for (term in terms) {
                val escaped = term.replace("!", "!!").replace("%", "!%").replace("_", "!_")
                sb.append(" AND ${MediaStore.Images.Media.DISPLAY_NAME} LIKE '%$escaped%' ESCAPE '!'")
            }
        }

        return sb.toString()
    }


    private fun buildSortOrder(sortType: SortType, sortOrder: SortOrder): String {
        val direction = if (sortOrder == SortOrder.ASCENDING) "ASC" else "DESC"
        return when (sortType) {
            SortType.DATE -> "${MediaStore.Images.Media.DATE_MODIFIED} $direction, ${MediaStore.Images.Media._ID} $direction"
            SortType.TITLE -> "${MediaStore.Images.Media.DISPLAY_NAME} COLLATE NOCASE $direction"
            SortType.DATE_ADDED -> "${MediaStore.Images.Media.DATE_ADDED} $direction, ${MediaStore.Images.Media._ID} $direction"
        }
    }

    private fun imageSortOptionToTypeOrder(imageSortOption: ImageSortOption): Pair<SortType, SortOrder> {
        return when (imageSortOption) {
            ImageSortOption.CUSTOM_ORDER -> SortType.DATE to SortOrder.DESCENDING
            ImageSortOption.NAME_A_TO_Z -> SortType.TITLE to SortOrder.ASCENDING
            ImageSortOption.NAME_Z_TO_A -> SortType.TITLE to SortOrder.DESCENDING
            ImageSortOption.DATE_CREATED_ASC -> SortType.DATE_ADDED to SortOrder.ASCENDING
            ImageSortOption.DATE_CREATED_DESC -> SortType.DATE_ADDED to SortOrder.DESCENDING
            ImageSortOption.DATE_MODIFIED_ASC -> SortType.DATE to SortOrder.ASCENDING
            ImageSortOption.DATE_MODIFIED_DESC -> SortType.DATE to SortOrder.DESCENDING
        }
    }
}

