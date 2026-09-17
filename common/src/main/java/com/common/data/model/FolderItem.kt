package com.common.data.model

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
    /**
     * dateModified of the specific cover video ([latestItemUri]). Unlike [latestDateModified]
     * (the album's max, used for folder sorting), this is the cover item's own timestamp — it must
     * match the thumbnail cache key so the pre-generated cover is found instead of re-extracted.
     */
    val coverDateModified: Long = 0L,
    val path: String = ""
)

