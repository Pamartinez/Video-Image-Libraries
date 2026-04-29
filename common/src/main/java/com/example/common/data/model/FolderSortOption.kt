package com.example.common.data.model

import com.example.common.ui.util.MixedItemSorting

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

    /**
     * Converts this FolderSortOption to the common MixedItemSorting.SortType.
     * Used by GroupDetailScreen to share sorting logic between libraries.
     */
    fun toSortType(): MixedItemSorting.SortType {
        return when (this) {
            CUSTOM_ORDER -> MixedItemSorting.SortType.CUSTOM_ORDER
            NAME_A_TO_Z -> MixedItemSorting.SortType.NAME_A_TO_Z
            NAME_Z_TO_A -> MixedItemSorting.SortType.NAME_Z_TO_A
            ITEMS_MOST_FIRST -> MixedItemSorting.SortType.ITEMS_MOST_FIRST
            ITEMS_FEWEST_FIRST -> MixedItemSorting.SortType.ITEMS_FEWEST_FIRST
        }
    }
}

