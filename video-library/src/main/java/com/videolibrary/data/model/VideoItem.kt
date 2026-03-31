package com.videolibrary.data.model

import android.net.Uri
import android.provider.MediaStore

data class VideoItem(
    val id: Long,
    val title: String,
    val displayName: String,
    val path: String,
    val duration: Long,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val bucketId: Int,
    val bucketName: String,
    val mimeType: String,
    val contentUri: Uri,
    val width: Int = 0,
    val height: Int = 0,
    val isHdr: Boolean = false,
    val is360: Boolean = false,
    val isSlowMotion: Boolean = false,
    val isFastMotion: Boolean = false,
    val isHyperlapse: Boolean = false,
    val isDirectorsView: Boolean = false,
    val resumePosition: Long = 0L
) {
    companion object {
        val PROJECTION = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT
        )
    }
}

