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
    val dateTaken: Long,
    val bucketId: Int,
    val bucketName: String,
    val mimeType: String,
    val contentUri: Uri,
    val width: Int = 0,
    val height: Int = 0,
    /** EXIF display rotation in degrees (0/90/180/270). MediaStore WIDTH/HEIGHT are raw, pre-rotation. */
    val orientation: Int = 0
) {
    /** Aspect ratio (width/height) as actually displayed, accounting for a 90°/270° EXIF rotation. */
    val displayAspectRatio: Float
        get() {
            if (width <= 0 || height <= 0) return 0f
            val rotated = orientation % 180 != 0
            val w = if (rotated) height else width
            val h = if (rotated) width else height
            return w.toFloat() / h.toFloat()
        }
    companion object {
        val PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.ORIENTATION
        )
    }
}
