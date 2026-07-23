package com.example.common.upload

import android.net.Uri

/**
 * One file queued for upload to Dropbox.
 *
 * @param relativePath optional sub-path (with trailing slash) preserved under the destination
 *   folder when uploading whole folders, e.g. "Camera/". Empty for flat uploads.
 */
data class UploadItem(
    val uri: Uri,
    val name: String,
    val size: Long,
    val relativePath: String = ""
) {
    /** Full destination path under [destRoot], e.g. "/Apps/Gallery/Camera/IMG_1.jpg". */
    fun destPath(destRoot: String): String {
        val root = destRoot.trimEnd('/')
        val sub = relativePath.trim('/')
        return buildString {
            append(root)
            append('/')
            if (sub.isNotEmpty()) {
                append(sub)
                append('/')
            }
            append(name)
        }
    }
}
