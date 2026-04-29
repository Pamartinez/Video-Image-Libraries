package com.example.common.ui.util

import com.example.common.data.model.MixedItem

/**
 * Shared sorting utilities for mixed items (folders + groups).
 * Used by both image-library and video-library GroupDetailScreen.
 */
object MixedItemSorting {

    /**
     * Sort options enum values that match both libraries.
     * Maps to SortOption (image-library) and FolderSortOption (video-library).
     */
    enum class SortType {
        NAME_A_TO_Z,
        NAME_Z_TO_A,
        ITEMS_MOST_FIRST,
        ITEMS_FEWEST_FIRST,
        CUSTOM_ORDER
    }

    /**
     * Sorts mixed items based on sort type and groupsAlwaysOnTop setting.
     * This logic is identical between image-library and video-library.
     */
    fun sortMixedItems(
        items: List<MixedItem>,
        sortType: SortType,
        groupsAlwaysOnTop: Boolean
    ): List<MixedItem> {
        return when (sortType) {
            SortType.NAME_A_TO_Z -> if (groupsAlwaysOnTop) {
                items.filterIsInstance<MixedItem.Group>().sortedBy { it.sortKey.lowercase() } +
                items.filterIsInstance<MixedItem.Folder>().sortedBy { it.sortKey.lowercase() }
            } else items.sortedBy { it.sortKey.lowercase() }

            SortType.NAME_Z_TO_A -> if (groupsAlwaysOnTop) {
                items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.sortKey.lowercase() } +
                items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.sortKey.lowercase() }
            } else items.sortedByDescending { it.sortKey.lowercase() }

            SortType.ITEMS_MOST_FIRST -> if (groupsAlwaysOnTop) {
                items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.itemCount } +
                items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.itemCount }
            } else items.sortedByDescending { it.itemCount }

            SortType.ITEMS_FEWEST_FIRST -> if (groupsAlwaysOnTop) {
                items.filterIsInstance<MixedItem.Group>().sortedBy { it.itemCount } +
                items.filterIsInstance<MixedItem.Folder>().sortedBy { it.itemCount }
            } else items.sortedBy { it.itemCount }

            SortType.CUSTOM_ORDER -> items
        }
    }

    /**
     * Extension function to convert library-specific sort option to common SortType.
     * Each library should define this extension on their SortOption enum.
     */
    interface SortOptionConverter {
        fun toSortType(): SortType
    }
}


