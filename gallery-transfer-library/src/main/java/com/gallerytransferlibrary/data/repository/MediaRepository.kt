package com.gallerytransferlibrary.data.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.common.data.model.FolderItem
import com.example.common.data.model.FolderSortOption
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.model.MediaSortOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Reads images + videos from MediaStore and aggregates them into folders (buckets).
 * Intentionally minimal — no groups/albums/hidden folders (out of scope for this app).
 */
class MediaRepository(private val context: Context) {

    private val resolver: ContentResolver get() = context.contentResolver

    // ── Folders (buckets) ───────────────────────────────────────────────

    suspend fun getFolders(sort: FolderSortOption = FolderSortOption.NAME_A_TO_Z): List<FolderItem> =
        withContext(Dispatchers.IO) {
            val all = queryAll(bucketId = null)
            val grouped = all.groupBy { it.bucketId }
            val folders = grouped.map { (bucketId, items) ->
                val latest = items.maxByOrNull { it.dateModified }
                FolderItem(
                    bucketId = bucketId,
                    name = items.firstOrNull()?.bucketName?.ifBlank { "Unknown" } ?: "Unknown",
                    itemCount = items.size,
                    latestItemUri = latest?.uri,
                    latestDateModified = latest?.dateModified ?: 0L,
                    path = items.firstOrNull()?.path?.substringBeforeLast('/') ?: ""
                )
            }
            sortFolders(folders, sort)
        }

    private fun sortFolders(folders: List<FolderItem>, sort: FolderSortOption): List<FolderItem> =
        when (sort) {
            FolderSortOption.NAME_A_TO_Z -> folders.sortedBy { it.name.lowercase() }
            FolderSortOption.NAME_Z_TO_A -> folders.sortedByDescending { it.name.lowercase() }
            FolderSortOption.ITEMS_MOST_FIRST -> folders.sortedByDescending { it.itemCount }
            FolderSortOption.ITEMS_FEWEST_FIRST -> folders.sortedBy { it.itemCount }
            FolderSortOption.CUSTOM_ORDER -> folders.sortedByDescending { it.latestDateModified }
        }

    // ── Media inside a bucket ───────────────────────────────────────────

    suspend fun getMedia(
        bucketId: Int,
        sort: MediaSortOption = MediaSortOption.DATE_NEWEST
    ): List<MediaItem> = withContext(Dispatchers.IO) {
        sortMedia(queryAll(bucketId = bucketId), sort)
    }

    /** Expands a set of folders into all their contained media (for folder uploads). */
    suspend fun getMediaForBuckets(bucketIds: Collection<Int>): List<MediaItem> =
        withContext(Dispatchers.IO) {
            if (bucketIds.isEmpty()) emptyList()
            else queryAll(bucketId = null).filter { it.bucketId in bucketIds }
        }

    /** All media items across every folder, flat (for the Filter list). */
    suspend fun getAllMedia(): List<MediaItem> = withContext(Dispatchers.IO) {
        queryAll(bucketId = null)
    }

    private fun sortMedia(items: List<MediaItem>, sort: MediaSortOption): List<MediaItem> =
        when (sort) {
            MediaSortOption.DATE_NEWEST -> items.sortedByDescending { it.dateModified }
            MediaSortOption.DATE_OLDEST -> items.sortedBy { it.dateModified }
            MediaSortOption.NAME_A_TO_Z -> items.sortedBy { it.displayName.lowercase() }
            MediaSortOption.NAME_Z_TO_A -> items.sortedByDescending { it.displayName.lowercase() }
        }

    // ── MediaStore queries ──────────────────────────────────────────────

    private fun queryAll(bucketId: Int?): List<MediaItem> {
        val out = ArrayList<MediaItem>()
        out += queryOne(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, isVideo = false, bucketId)
        out += queryOne(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, isVideo = true, bucketId)
        return out
    }

    private fun queryOne(baseUri: Uri, isVideo: Boolean, bucketId: Int?): List<MediaItem> {
        val items = ArrayList<MediaItem>()
        val projection = arrayListOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.SIZE,
            MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.BUCKET_ID,
            MediaStore.MediaColumns.BUCKET_DISPLAY_NAME,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT
        )
        if (isVideo) projection.add(MediaStore.Video.Media.DURATION)

        val selection = bucketId?.let { "${MediaStore.MediaColumns.BUCKET_ID} = ?" }
        val args = bucketId?.let { arrayOf(it.toString()) }

        try {
            resolver.query(baseUri, projection.toTypedArray(), selection, args, null)?.use { c ->
                val idCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
                val nameCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                @Suppress("DEPRECATION")
                val dataCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                val sizeCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE)
                val dateCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED)
                val bucketIdCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_ID)
                val bucketNameCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.BUCKET_DISPLAY_NAME)
                val mimeCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE)
                val widthCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.WIDTH)
                val heightCol = c.getColumnIndexOrThrow(MediaStore.MediaColumns.HEIGHT)
                val durationCol = if (isVideo) c.getColumnIndex(MediaStore.Video.Media.DURATION) else -1

                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    items.add(
                        MediaItem(
                            id = id,
                            uri = ContentUris.withAppendedId(baseUri, id),
                            displayName = c.getString(nameCol) ?: "",
                            path = c.getString(dataCol) ?: "",
                            size = c.getLong(sizeCol),
                            dateModified = c.getLong(dateCol),
                            bucketId = c.getInt(bucketIdCol),
                            bucketName = c.getString(bucketNameCol) ?: "",
                            mimeType = c.getString(mimeCol) ?: if (isVideo) "video/*" else "image/*",
                            isVideo = isVideo,
                            duration = if (durationCol >= 0) c.getLong(durationCol) else 0L,
                            width = c.getInt(widthCol),
                            height = c.getInt(heightCol)
                        )
                    )
                }
            }
        } catch (e: Exception) {
            // Cursor/column failures on some OEM MediaStores — skip this source.
        }
        return items
    }
}
