package com.example.common.data.model

import android.net.Uri

/**
 * UI model for a folder / media bucket, shared by both image-library and video-library.
 *
 * [itemCount] is the number of media items (images or videos) in the folder.
 * [latestItemUri] is the URI of the most recently modified item, used as a thumbnail.
 */
data class FolderItem(
    val bucketId: Int,
    val name: String,
    val itemCount: Int,
    val latestItemUri: Uri? = null,
    val latestDateModified: Long = 0L,
    val path: String = ""
)

