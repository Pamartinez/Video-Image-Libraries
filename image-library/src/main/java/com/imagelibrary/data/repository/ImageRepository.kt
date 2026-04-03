package com.imagelibrary.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.FolderItem
import com.example.common.data.util.MediaFileUtils
import com.example.common.data.util.MediaTransferHelper
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

    // ── Hide / Show Folder (app-local — no .nomedia / no MediaStore rescan) ──
    //
    // Visibility is managed entirely through AppPreferences.hiddenFolderPaths.
    // No filesystem writes are performed, so other apps (e.g. Samsung Gallery)
    // are never affected, and the change is instant with no async race conditions.

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
    ): Boolean = MediaTransferHelper.transfer(
        items              = images,
        mediaCollectionUri = imageUri,
        contentResolver    = contentResolver,
        getSourceUri       = { it.contentUri },
        getDisplayName     = { it.displayName },
        getMimeType        = { it.mimeType },
        getFilePath        = { uri -> getFilePath(uri) },
        context            = context,
        destFolderPath     = destFolderPath,
        deleteSource       = true,
        onProgress         = onProgress,
        isCancelled        = isCancelled,
        onConflict         = onConflict,
        logTag             = "ImageRepository"
    )

    // ── Copy Images ─────────────────────────────────────────────────────

    suspend fun copyImages(
        images: List<ImageItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = MediaTransferHelper.transfer(
        items              = images,
        mediaCollectionUri = imageUri,
        contentResolver    = contentResolver,
        getSourceUri       = { it.contentUri },
        getDisplayName     = { it.displayName },
        getMimeType        = { it.mimeType },
        getFilePath        = { uri -> getFilePath(uri) },
        context            = context,
        destFolderPath     = destFolderPath,
        deleteSource       = false,
        onProgress         = onProgress,
        isCancelled        = isCancelled,
        onConflict         = onConflict,
        logTag             = "ImageRepository"
    )

    // ── List DCIM Folder Names ───────────────────────────────────────────

    suspend fun getExistingDcimFolderNames(): Set<String> =
        MediaFileUtils.getExistingDcimFolderNames()

    // ── Create Folder ───────────────────────────────────────────────────

    suspend fun createFolder(folderName: String): String? =
        MediaFileUtils.createFolder(folderName)

    // ── Private Helpers ─────────────────────────────────────────────────

    private fun queryStringColumn(uri: Uri, column: String): String? {
        return try {
            contentResolver.query(uri, arrayOf(column), null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(0) else null
            }
        } catch (_: Exception) { null }
    }

    @Suppress("DEPRECATION")
    private fun getFilePath(uri: Uri): String? =
        queryStringColumn(uri, MediaStore.Images.Media.DATA)

    private fun buildSelection(bucketId: Int?, searchQuery: String?): String =
        MediaFileUtils.buildSelection(
            dataColumn        = MediaStore.Images.Media.DATA,
            bucketIdColumn    = MediaStore.Images.Media.BUCKET_ID,
            displayNameColumn = MediaStore.Images.Media.DISPLAY_NAME,
            bucketId          = bucketId,
            searchQuery       = searchQuery
        )

    private fun buildSortOrder(sortType: SortType, sortOrder: SortOrder): String {
        val direction = if (sortOrder == SortOrder.ASCENDING) "ASC" else "DESC"
        return when (sortType) {
            SortType.DATE      -> "${MediaStore.Images.Media.DATE_MODIFIED} $direction, ${MediaStore.Images.Media._ID} $direction"
            SortType.TITLE     -> "${MediaStore.Images.Media.DISPLAY_NAME} COLLATE NOCASE $direction"
            SortType.DATE_ADDED -> "${MediaStore.Images.Media.DATE_ADDED} $direction, ${MediaStore.Images.Media._ID} $direction"
        }
    }

    private fun imageSortOptionToTypeOrder(imageSortOption: ImageSortOption): Pair<SortType, SortOrder> {
        return when (imageSortOption) {
            ImageSortOption.CUSTOM_ORDER      -> SortType.DATE to SortOrder.DESCENDING
            ImageSortOption.NAME_A_TO_Z       -> SortType.TITLE to SortOrder.ASCENDING
            ImageSortOption.NAME_Z_TO_A       -> SortType.TITLE to SortOrder.DESCENDING
            ImageSortOption.DATE_CREATED_ASC  -> SortType.DATE_ADDED to SortOrder.ASCENDING
            ImageSortOption.DATE_CREATED_DESC -> SortType.DATE_ADDED to SortOrder.DESCENDING
            ImageSortOption.DATE_MODIFIED_ASC -> SortType.DATE to SortOrder.ASCENDING
            ImageSortOption.DATE_MODIFIED_DESC -> SortType.DATE to SortOrder.DESCENDING
        }
    }
}

