package com.example.common.data.model

/**
 * Shared view-type enum used by both image-library and video-library.
 * LIST is only shown in video-library; image-library uses GRID_SMALL and GRID_LARGE.
 */
enum class ViewType(val id: Int) {
    GRID_SMALL(1),
    GRID_LARGE(2),
    LIST(3);

    companion object {
        fun fromId(id: Int, default: ViewType = GRID_LARGE): ViewType =
            entries.firstOrNull { it.id == id } ?: default
    }
}

