package com.gallerytransferlibrary.data.model

/** Sort options for media items inside a folder. */
enum class MediaSortOption(val id: Int, val label: String) {
    DATE_NEWEST(0, "Date (newest first)"),
    DATE_OLDEST(1, "Date (oldest first)"),
    NAME_A_TO_Z(2, "Name (A to Z)"),
    NAME_Z_TO_A(3, "Name (Z to A)");

    companion object {
        fun fromId(id: Int): MediaSortOption = entries.firstOrNull { it.id == id } ?: DATE_NEWEST
    }
}
