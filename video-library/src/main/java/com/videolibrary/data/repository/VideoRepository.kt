package com.videolibrary.data.repository

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

    // ── Hide / Show Folder (app-local — no .nomedia / no MediaStore rescan) ──
    //
    // Visibility is managed entirely through AppPreferences.hiddenFolderPaths.
    // No filesystem writes are performed, so other apps (e.g. Samsung Gallery)
    // are never affected, and the change is instant with no async race conditions.

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
    ): Boolean = MediaTransferHelper.transfer(
        items              = videos,
        mediaCollectionUri = videoUri,
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
        logTag             = "VideoRepository"
    )

    // ── Copy Videos ─────────────────────────────────────────────────────

    suspend fun copyVideos(
        videos: List<VideoItem>,
        destFolderPath: String,
        onProgress: (current: Int, total: Int) -> Unit = { _, _ -> },
        isCancelled: () -> Boolean = { false },
        onConflict: suspend (fileName: String) -> ConflictResolution = { ConflictResolution.RENAME }
    ): Boolean = MediaTransferHelper.transfer(
        items              = videos,
        mediaCollectionUri = videoUri,
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
        logTag             = "VideoRepository"
    )

    // ── Create Folder ────────────────────────────────────────────────────

    suspend fun createFolder(name: String): String? =
        MediaFileUtils.createFolder(name)

    // ── List existing DCIM sub-folder names ──────────────────────────────

    suspend fun getExistingDcimFolderNames(): Set<String> =
        MediaFileUtils.getExistingDcimFolderNames()

    // ── Private Helpers ──────────────────────────────────────────────────

    private fun queryStringColumn(uri: Uri, column: String): String? {
        return try {
            contentResolver.query(uri, arrayOf(column), null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) cursor.getString(0) else null
            }
        } catch (_: Exception) { null }
    }

    @Suppress("DEPRECATION")
    private fun getFilePath(uri: Uri): String? =
        queryStringColumn(uri, MediaStore.Video.Media.DATA)

    private fun buildSelection(bucketId: Int?, searchQuery: String?): String =
        MediaFileUtils.buildSelection(
            dataColumn        = MediaStore.Video.Media.DATA,
            bucketIdColumn    = MediaStore.Video.Media.BUCKET_ID,
            displayNameColumn = MediaStore.Video.Media.DISPLAY_NAME,
            bucketId          = bucketId,
            searchQuery       = searchQuery
        )

    private fun buildVideoSortOrder(option: VideoSortOption): String {
        return when (option) {
            VideoSortOption.CUSTOM_ORDER      -> "${MediaStore.Video.Media.DATE_MODIFIED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.NAME_A_TO_Z       -> "${MediaStore.Video.Media.DISPLAY_NAME} COLLATE NOCASE ASC"
            VideoSortOption.NAME_Z_TO_A       -> "${MediaStore.Video.Media.DISPLAY_NAME} COLLATE NOCASE DESC"
            VideoSortOption.DATE_CREATED_ASC  -> "${MediaStore.Video.Media.DATE_ADDED} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DATE_CREATED_DESC -> "${MediaStore.Video.Media.DATE_ADDED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.DATE_MODIFIED_ASC -> "${MediaStore.Video.Media.DATE_MODIFIED} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DATE_MODIFIED_DESC -> "${MediaStore.Video.Media.DATE_MODIFIED} DESC, ${MediaStore.Video.Media._ID} DESC"
            VideoSortOption.DURATION_ASC      -> "${MediaStore.Video.Media.DURATION} ASC, ${MediaStore.Video.Media._ID} ASC"
            VideoSortOption.DURATION_DESC     -> "${MediaStore.Video.Media.DURATION} DESC, ${MediaStore.Video.Media._ID} DESC"
        }
    }
}
