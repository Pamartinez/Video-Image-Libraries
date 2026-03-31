package com.imagelibrary.data.model

import android.net.Uri
import android.provider.MediaStore

data class ImageItem(
    val id: Long,
    val title: String,
    val displayName: String,
    val path: String,
    val size: Long,
    val dateModified: Long,
    val bucketId: Int,
    val bucketName: String,
    val mimeType: String,
    val contentUri: Uri,
    val width: Int = 0,
    val height: Int = 0
) {
    companion object {
        val PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )
    }
}
