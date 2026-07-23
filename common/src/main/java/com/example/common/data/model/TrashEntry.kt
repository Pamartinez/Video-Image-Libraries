package com.example.common.data.model

/**
 * A single item currently held in the shared internal Trash.
 *
 * The file's bytes live inside the shared trash folder under [trashFileName]; [originalPath]
 * is the absolute path the file should be restored to. Shared by gallery-transfer,
 * image-library and video-library via a single JSON index (see [com.example.common.data.db.TrashStore]).
 */
data class TrashEntry(
    val id: String,
    val trashFileName: String,
    val originalPath: String,
    val displayName: String,
    val isVideo: Boolean,
    val size: Long,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val dateModified: Long,
    val deleteTimeMillis: Long,
    val sourceApp: String
)
