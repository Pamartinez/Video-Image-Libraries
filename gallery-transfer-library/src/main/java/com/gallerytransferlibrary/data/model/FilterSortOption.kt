package com.gallerytransferlibrary.data.model

/** Sort options for the flat "Filter" list (adds size on top of date/name). */
enum class FilterSortOption(val id: Int, val label: String) {
    DATE_NEWEST(0, "Date (newest first)"),
    DATE_OLDEST(1, "Date (oldest first)"),
    NAME_A_TO_Z(2, "Name (A to Z)"),
    NAME_Z_TO_A(3, "Name (Z to A)"),
    SIZE_LARGEST(4, "Size (largest first)"),
    SIZE_SMALLEST(5, "Size (smallest first)");

    companion object {
        fun fromId(id: Int): FilterSortOption = entries.firstOrNull { it.id == id } ?: DATE_NEWEST
    }
}
