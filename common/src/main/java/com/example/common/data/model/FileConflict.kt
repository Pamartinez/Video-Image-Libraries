package com.example.common.data.model

import kotlinx.coroutines.CompletableDeferred

/**
 * Represents a file name conflict during copy/move operations.
 * Used by both image-library and video-library to handle conflicts interactively.
 *
 * @property fileName The name of the conflicting file
 * @property deferred Deferred result that completes when the user makes a choice
 * @property applyToAll Whether the user's choice should apply to all subsequent conflicts
 */
data class FileConflict(
    val fileName: String,
    val deferred: CompletableDeferred<ConflictResolution>,
    var applyToAll: Boolean = false
)

