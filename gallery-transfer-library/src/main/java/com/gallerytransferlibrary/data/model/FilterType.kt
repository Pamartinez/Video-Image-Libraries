package com.gallerytransferlibrary.data.model

/** Media type filter for the flat "Filter" list. */
enum class FilterType(val id: Int, val label: String) {
    ALL(0, "All"),
    IMAGES(1, "Images"),
    VIDEOS(2, "Videos");

    companion object {
        fun fromId(id: Int): FilterType = entries.firstOrNull { it.id == id } ?: ALL
    }
}
