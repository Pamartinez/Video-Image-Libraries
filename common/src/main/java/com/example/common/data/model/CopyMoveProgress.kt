package com.example.common.data.model

/**
 * Progress state for copy/move operations.
 * Used by both image-library and video-library to track file transfer progress.
 *
 * @property isActive Whether a copy/move operation is currently in progress
 * @property title Progress dialog title (e.g., "Copying items to Camera...")
 * @property current Number of items processed so far
 * @property total Total number of items to process
 */
data class CopyMoveProgress(
    val isActive: Boolean = false,
    val title: String = "",
    val current: Int = 0,
    val total: Int = 0
)

