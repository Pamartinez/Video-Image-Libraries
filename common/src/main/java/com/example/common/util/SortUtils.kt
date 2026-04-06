package com.example.common.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem

/**
 * Shared sorting utilities for both image-library and video-library ViewModels.
 */
object SortUtils {

    /**
     * Sort a combined list of [GroupItem]s and [FolderItem]s together according to
     * the provided sort option ID, so that group-albums are treated exactly like regular albums.
     *
     * When [groupsAlwaysOnTop] is true the list is split into two segments:
     *   1. All groups, sorted by option among themselves.
     *   2. All folders, sorted by option among themselves.
     *
     * @param items Mixed list of GroupItem and FolderItem objects
     * @param sortOptionId The sort option ID:
     *   - 0 = CUSTOM_ORDER (should not reach here)
     *   - 1 = NAME_A_TO_Z
     *   - 2 = NAME_Z_TO_A
     *   - 3 = ITEMS_MOST_FIRST
     *   - 4 = ITEMS_FEWEST_FIRST
     * @param groupsAlwaysOnTop When true, groups are pinned to the top
     */
    fun sortMixedItems(
        items: List<Any>,
        sortOptionId: Int,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        fun itemName(item: Any) = when (item) {
            is GroupItem  -> item.name
            is FolderItem -> item.name
            else          -> ""
        }

        fun itemCount(item: Any) = when (item) {
            is GroupItem  -> item.totalItemCount
            is FolderItem -> item.itemCount
            else          -> 0
        }

        fun sortList(list: List<Any>): List<Any> = when (sortOptionId) {
            1 -> list.sortedBy { itemName(it).lowercase() }             // NAME_A_TO_Z
            2 -> list.sortedByDescending { itemName(it).lowercase() }   // NAME_Z_TO_A
            3 -> list.sortedByDescending { itemCount(it) }              // ITEMS_MOST_FIRST
            4 -> list.sortedBy { itemCount(it) }                        // ITEMS_FEWEST_FIRST
            else -> list                                                // CUSTOM_ORDER or unknown
        }

        return if (groupsAlwaysOnTop) {
            val groups  = items.filterIsInstance<GroupItem>()
            val folders = items.filterIsInstance<FolderItem>()
            sortList(groups) + sortList(folders)
        } else {
            sortList(items)
        }
    }

    /**
     * Build the unified display order of groups + folders using custom saved order.
     * Returns (sortedGroups, sortedFolders) as separate lists.
     *
     * @param groups List of GroupItem to sort
     * @param folders List of FolderItem to sort
     * @param savedOrder Saved custom order keys (e.g., ["g_123", "f_456", ...])
     * @param sortOptionId The sort option ID (0 = CUSTOM_ORDER)
     * @param groupsAlwaysOnTop When true and not CUSTOM_ORDER, groups are pinned to top
     */
    fun sortSeparateLists(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        savedOrder: List<String>,
        sortOptionId: Int,
        groupsAlwaysOnTop: Boolean = false
    ): Pair<List<GroupItem>, List<FolderItem>> {
        if (sortOptionId == 0) { // CUSTOM_ORDER
            if (savedOrder.isEmpty()) return groups to folders

            val groupMap  = groups.associateBy { "g_${it.groupId}" }
            val folderMap = folders.associateBy { "f_${it.bucketId}" }
            val savedSet  = savedOrder.toSet()

            val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
            val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
            val ordered    = savedOrder.mapNotNull { groupMap[it] ?: folderMap[it] }
            val result     = newGroups + newFolders + ordered

            return result.filterIsInstance<GroupItem>() to result.filterIsInstance<FolderItem>()
        }

        val sorted = sortMixedItems(groups + folders, sortOptionId, groupsAlwaysOnTop)
        return sorted.filterIsInstance<GroupItem>() to sorted.filterIsInstance<FolderItem>()
    }

    /**
     * Convert a mixed list of GroupItem/FolderItem to their string keys for persistence.
     */
    fun mixedItemsToKeys(items: List<Any>): List<String> {
        return items.mapNotNull { item ->
            when (item) {
                is GroupItem  -> "g_${item.groupId}"
                is FolderItem -> "f_${item.bucketId}"
                else          -> null
            }
        }
    }

    /**
     * Restore items from saved order keys.
     * Returns items in saved order, prepending new items at the beginning.
     */
    fun restoreMixedOrderFromKeys(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        savedOrder: List<String>
    ): List<Any> {
        if (savedOrder.isEmpty()) {
            return groups + folders
        }

        val groupMap  = groups.associateBy { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }
        val savedSet  = savedOrder.toSet()

        // New items not yet in the saved order — prepend at the beginning
        val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
        val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }

        // Items in their saved position (skip keys whose item was deleted)
        val ordered = savedOrder.mapNotNull { key -> groupMap[key] ?: folderMap[key] }

        return newGroups + newFolders + ordered
    }
}

