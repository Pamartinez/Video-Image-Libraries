package com.example.common.data.model

/**
 * Shared folder sort-option enum used by both image-library and video-library.
 * image-library previously called this "SortOption"; a typealias is provided there.
 */
enum class FolderSortOption(val id: Int, val label: String) {
    CUSTOM_ORDER(0, "Custom order"),
    NAME_A_TO_Z(1, "Name (A to Z)"),
    NAME_Z_TO_A(2, "Name (Z to A)"),
    ITEMS_MOST_FIRST(3, "Items (most to fewest)"),
    ITEMS_FEWEST_FIRST(4, "Items (fewest to most)");

    companion object {
        fun fromId(id: Int): FolderSortOption =
            entries.firstOrNull { it.id == id } ?: CUSTOM_ORDER
    }
}

