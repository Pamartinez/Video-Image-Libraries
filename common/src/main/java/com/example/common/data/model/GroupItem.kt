package com.example.common.data.model

import android.net.Uri

/**
 * UI model for a group, displayed alongside FolderItems in the folder grid.
 * Shared by both image-library and video-library.
 *
 * [previewUris] contains up to 4 media URIs (one from each of the first 4 folders).
 * [totalItemCount] is the sum of media items across all member folders.
 */
data class GroupItem(
    val groupId: Long,
    val name: String,
    val parentGroupId: Long? = null,
    val folderCount: Int = 0,
    val subGroupCount: Int = 0,
    val totalItemCount: Int = 0,
    val previewUris: List<Uri> = emptyList(),
    val memberBucketIds: List<Int> = emptyList(),
    val createdAt: Long = 0L
)

