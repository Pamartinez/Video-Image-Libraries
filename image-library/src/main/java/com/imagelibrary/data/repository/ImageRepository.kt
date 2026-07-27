package com.imagelibrary.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.IntentSender
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.FolderItem
import com.example.common.data.util.MediaFileUtils
import com.example.common.data.util.MediaTransferHelper
import com.example.common.data.util.MediaTrashHelper
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
        searchQuery: String? = null,
        allowMediaReordering: Boolean = false,
        customOrder: List<Long> = emptyList()
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
                val dateTakenCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
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
                    val dateTaken = cursor.getLong(dateTakenCol)
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
                            dateTaken = dateTaken,
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

        // Apply custom media order if enabled and in CUSTOM_ORDER sort mode
        return@withContext if (allowMediaReordering &&
            imageSortOption == ImageSortOption.CUSTOM_ORDER &&
            customOrder.isNotEmpty()
        ) {
            applyCustomMediaOrder(images, customOrder)
        } else {
            images
        }
    }

    /**
     * Applies custom media order to the list of images.
     * Images in customOrder are placed first in that order, followed by new items (not in customOrder)
     * prepended at position 0 (consistent with folder/group reordering behavior).
     *
     * @param images The images loaded from MediaStore
     * @param customOrder The persisted order of image IDs
     * @return Reordered list of images
     */
    private fun applyCustomMediaOrder(images: List<ImageItem>, customOrder: List<Long>): List<ImageItem> {
        val imageMap = images.associateBy { it.id }
        val result = mutableListOf<ImageItem>()
        val newItems = mutableListOf<ImageItem>()

        // First, add images in the custom order
        for (id in customOrder) {
            imageMap[id]?.let { result.add(it) }
        }

        // Then, collect new items (not in custom order)
        for (image in images) {
            if (!customOrder.contains(image.id)) {
                newItems.add(image)
            }
        }

        // Prepend new items at position 0 (newest first)
        return newItems + result
    }

    // ── Get Folders ─────────────────────────────────────────────────────

    /**
     * Get folders with album-specific sort options for preview generation.
     * Each album's preview is generated using THE FIRST IMAGE according to that album's own sort order.
     *
     * ⚠️ CRITICAL: This method MUST be used when independent sort is enabled to ensure
     * each album's preview reflects its own sort settings, not the global sort.
     */
    suspend fun getFoldersWithIndependentSort(
        sortOption: SortOption = SortOption.CUSTOM_ORDER,
        getFolderSortOption: (Int) -> ImageSortOption = { ImageSortOption.CUSTOM_ORDER },
        getCustomMediaOrder: (Int) -> List<Long> = { emptyList() }
    ): List<FolderItem> = withContext(Dispatchers.IO) {
        // Load ALL images from MediaStore
        val allImages = mutableMapOf<Int, MutableList<ImageItem>>()

        @Suppress("DEPRECATION")
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        @Suppress("DEPRECATION")
        val selection = "length(trim(${MediaStore.Images.Media.DATA})) > 0"

        try {
            contentResolver.query(imageUri, projection, selection, null, null)?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                val dateTakenCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val displayNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idCol)
                    val bId = cursor.getInt(bucketIdCol)
                    val bName = cursor.getString(bucketNameCol) ?: ""
                    val dateModified = cursor.getLong(dateModifiedCol)
                    val dateTaken = cursor.getLong(dateTakenCol)
                    val dataPath = cursor.getString(dataCol) ?: ""
                    val displayName = cursor.getString(displayNameCol) ?: ""

                    val image = ImageItem(
                        id = id,
                        title = "",
                        displayName = displayName,
                        path = dataPath,
                        size = 0,
                        dateModified = dateModified,
                        dateTaken = dateTaken,
                        bucketId = bId,
                        bucketName = bName,
                        mimeType = "image/*",
                        contentUri = ContentUris.withAppendedId(imageUri, id),
                        width = 0,
                        height = 0
                    )

                    allImages.getOrPut(bId) { mutableListOf() }.add(image)
                }
            }
        } catch (e: Exception) {
            Log.e("ImageRepository", "Failed to load images for folders", e)
        }

        // Build FolderItem for each bucket, generating preview from THE FIRST IMAGE using that album's sort
        val folderMap = mutableMapOf<Int, FolderItem>()
        for ((bucketId, images) in allImages) {
            if (images.isEmpty()) continue

            val bucketName = images.first().bucketName
            val folderPath = File(images.first().path).parent ?: ""

            // Get this album's specific sort option
            val albumSort = getFolderSortOption(bucketId)

            // Generate preview: Sort images according to this album's sort, then take the first one
            val customOrder = getCustomMediaOrder(bucketId)
            val previewImage = getFirstImageForAlbum(images, albumSort, customOrder)

            folderMap[bucketId] = FolderItem(
                bucketId = bucketId,
                name = bucketName,
                itemCount = images.size,
                latestItemUri = previewImage?.contentUri,
                latestDateModified = images.maxOfOrNull { it.dateModified } ?: 0L,
                path = folderPath
            )
        }

        // Apply folder-level sorting
        val folders = folderMap.values.toList()
        return@withContext when (sortOption) {
            SortOption.CUSTOM_ORDER -> folders  // raw order; ViewModel applies persisted custom order
            SortOption.NAME_A_TO_Z -> folders.sortedBy { it.name.lowercase() }
            SortOption.NAME_Z_TO_A -> folders.sortedByDescending { it.name.lowercase() }
            SortOption.ITEMS_MOST_FIRST -> folders.sortedByDescending { it.itemCount }
            SortOption.ITEMS_FEWEST_FIRST -> folders.sortedBy { it.itemCount }
        }
    }

    /**
     * Get the first image for an album according to the specified sort option.
     * This is used for album preview generation.
     */
    private fun getFirstImageForAlbum(
        images: List<ImageItem>,
        sortOption: ImageSortOption,
        customOrder: List<Long> = emptyList()
    ): ImageItem? {
        if (images.isEmpty()) return null

        return when (sortOption) {
            ImageSortOption.CUSTOM_ORDER -> {
                // If custom order exists, use the first ID from it
                if (customOrder.isNotEmpty()) {
                    val imageMap = images.associateBy { it.id }
                    customOrder.firstOrNull()?.let { imageMap[it] }
                        ?: images.maxWithOrNull(compareBy<ImageItem> { it.dateModified }.thenBy { it.id })
                } else {
                    images.maxWithOrNull(compareBy<ImageItem> { it.dateModified }.thenBy { it.id })
                }
            }
            ImageSortOption.NAME_A_TO_Z -> images.minByOrNull { it.displayName.lowercase() }
            ImageSortOption.NAME_Z_TO_A -> images.maxByOrNull { it.displayName.lowercase() }
            ImageSortOption.DATE_CREATED_ASC -> images.minByOrNull { it.id }
            ImageSortOption.DATE_CREATED_DESC -> images.maxByOrNull { it.id }
            ImageSortOption.DATE_MODIFIED_ASC -> images.minByOrNull { it.dateModified }
            ImageSortOption.DATE_MODIFIED_DESC -> images.maxByOrNull { it.dateModified }
        }
    }

    suspend fun getFolders(
        sortOption: SortOption = SortOption.CUSTOM_ORDER,
        imageSortOption: ImageSortOption = ImageSortOption.CUSTOM_ORDER
    ): List<FolderItem> = withContext(Dispatchers.IO) {
        val folderMap = mutableMapOf<Int, FolderItem>()

        @Suppress("DEPRECATION")
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        @Suppress("DEPRECATION")
        val selection = "length(trim(${MediaStore.Images.Media.DATA})) > 0"

        // Build sort order based on imageSortOption to select preview image correctly
        val (sortType, sortOrder) = imageSortOptionToTypeOrder(imageSortOption)
        val sortOrderStr = buildSortOrder(sortType, sortOrder)

        try {
            contentResolver.query(imageUri, projection, selection, null, sortOrderStr)?.use { cursor ->
                val idCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                @Suppress("DEPRECATION")
                val bucketIdCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                @Suppress("DEPRECATION")
                val bucketNameCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val dateModifiedCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
                val dateTakenCol    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                while (cursor.moveToNext()) {
                    val id          = cursor.getLong(idCol)
                    val bId         = cursor.getInt(bucketIdCol)
                    val bName       = cursor.getString(bucketNameCol) ?: ""
                    val dateModified = cursor.getLong(dateModifiedCol)
                    val dateTaken   = cursor.getLong(dateTakenCol)
                    val dataPath    = cursor.getString(dataCol) ?: ""
                    val folderPath  = File(dataPath).parent ?: ""

                    val existing = folderMap[bId]
                    if (existing != null) {
                        // Increment count but keep the first preview (already the top item based on sort)
                        folderMap[bId] = existing.copy(
                            itemCount          = existing.itemCount + 1,
                            latestDateModified = maxOf(existing.latestDateModified, dateModified)
                        )
                    } else {
                        // First item for this bucket becomes the preview (respects sort order)
                        folderMap[bId] = FolderItem(
                            bucketId           = bId,
                            name               = bName,
                            itemCount          = 1,
                            latestItemUri      = ContentUris.withAppendedId(imageUri, id),
                            latestDateModified = dateModified,
                            path               = folderPath
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ImageRepository", "Failed to load folders", e)
        }

        // Apply sorting
        val folders = folderMap.values.toList()
        return@withContext when (sortOption) {
            SortOption.CUSTOM_ORDER       -> folders  // raw order; ViewModel applies persisted custom order
            SortOption.NAME_A_TO_Z        -> folders.sortedBy { it.name.lowercase() }
            SortOption.NAME_Z_TO_A        -> folders.sortedByDescending { it.name.lowercase() }
            SortOption.ITEMS_MOST_FIRST   -> folders.sortedByDescending { it.itemCount }
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

    suspend fun trashImages(imageIds: List<Long>): IntentSender = withContext(Dispatchers.IO) {
        val uris = imageIds.map { ContentUris.withAppendedId(imageUri, it) }
        MediaTrashHelper.createTrashRequest(contentResolver, uris)
    }

    /** True when the app holds All-files access and can delete media without a system consent dialog. */
    fun canDeleteSilently(): Boolean = MediaTrashHelper.isExternalStorageManager()

    /** Move [imageIds] to the system (Samsung Gallery) trash silently via All-files access. Returns the number trashed. */
    suspend fun trashSilently(imageIds: List<Long>): Int = withContext(Dispatchers.IO) {
        val uris = imageIds.map { ContentUris.withAppendedId(imageUri, it) }
        MediaTrashHelper.trashSilently(context, uris)
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

    // ── Rename Album ────────────────────────────────────────────────────

    /**
     * Renames an album (folder) by renaming the physical directory on disk.
     * All images in the folder will automatically reflect the new bucket name after MediaStore rescans.
     *
     * @param bucketId    the bucket ID of the album to rename
     * @param newName     the new name for the album folder
     * @return true if rename was successful, false otherwise
     */
    suspend fun renameAlbum(bucketId: Int, newName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val cleanName = newName.trim()
            if (cleanName.isBlank()) return@withContext false

            // Get the folder path for this bucket
            @Suppress("DEPRECATION")
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            @Suppress("DEPRECATION")
            val selection = "${MediaStore.Images.Media.BUCKET_ID} = ?"
            val selectionArgs = arrayOf(bucketId.toString())

            var folderPath: String? = null
            contentResolver.query(imageUri, projection, selection, selectionArgs, null)?.use { cursor ->
                @Suppress("DEPRECATION")
                val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                if (cursor.moveToFirst()) {
                    val filePath = cursor.getString(dataCol)
                    folderPath = File(filePath).parent
                }
            }

            if (folderPath == null) {
                return@withContext false
            }

            val oldFolder = File(folderPath!!)
            if (!oldFolder.exists() || !oldFolder.isDirectory) {
                return@withContext false
            }

            val parentDir = oldFolder.parentFile ?: return@withContext false
            val newFolder = File(parentDir, cleanName)

            if (newFolder.exists()) {
                return@withContext false
            }

            // Attempt to rename the directory
            val renamed = oldFolder.renameTo(newFolder)
            if (renamed) {
                // Trigger MediaStore scan for the new folder to update BUCKET_DISPLAY_NAME
                MediaFileUtils.scanFile(context, newFolder)
            }

            renamed
        } catch (e: Exception) {
            Log.e("ImageRepository", "Rename album failed", e)
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
            // EXIF capture time — identical to Samsung Gallery's default (datetaken DESC, _id ASC).
            // For images with same DATE_TAKEN (burst photos), _ID ASC ensures chronological order.
            // Stable: editing a photo updates DATE_MODIFIED but never DATE_TAKEN.
            SortType.DATE_TAKEN -> "${MediaStore.Images.Media.DATE_TAKEN} $direction, ${MediaStore.Images.Media._ID} ASC"
        }
    }

    private fun imageSortOptionToTypeOrder(imageSortOption: ImageSortOption): Pair<SortType, SortOrder> {
        return when (imageSortOption) {
            // Testing: Try DATE_MODIFIED instead of DATE_TAKEN to match Samsung Gallery
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

