package com.gallerytransferlibrary.data.model

import android.net.Uri

/**
 * A single local media item (image or video) surfaced from MediaStore.
 * Shared by the folder grid, viewers and the Dropbox upload queue.
 */
data class MediaItem(
    val id: Long,
    val uri: Uri,
    val displayName: String,
    val path: String,
    val size: Long,
    val dateModified: Long,
    /**
     * Best-effort creation time, epoch SECONDS. Resolved as DATE_TAKEN → DATE_ADDED →
     * DATE_MODIFIED, because DATE_TAKEN is absent for screenshots, downloads and most videos.
     * Defaults to 0 so only the repository has to supply it.
     */
    val dateCreated: Long = 0L,
    val bucketId: Int,
    val bucketName: String,
    val mimeType: String,
    val isVideo: Boolean,
    val duration: Long = 0L,
    val width: Int = 0,
    val height: Int = 0
) {
    val uniqueKey: String get() = if (isVideo) "video_$id" else "image_$id"
}
