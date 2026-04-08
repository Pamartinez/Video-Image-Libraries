package com.example.common.data.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.GroupItem

/**
 * Shared utility for sorting mixed lists of GroupItem and FolderItem.
 * Used by both image-library and video-library to maintain consistent sort behavior.
 *
 * Key features:
 * - Unified sorting of groups + folders together
 * - Support for "groups always on top" mode
 * - Custom order preservation with new item handling
 * - Hide screen sorting with ghost folder support
 */
object MixedItemSorter {

    /**
     * Sort a combined list of GroupItem + FolderItem by the given sort option.
     *
     * When [groupsAlwaysOnTop] is true, the list is split into two segments:
     *   1. All groups, sorted by [option] among themselves
     *   2. All folders, sorted by [option] among themselves
     *
     * Key mapping:
     *   - name       → GroupItem.name / FolderItem.name
     *   - item count → GroupItem.totalItemCount / FolderItem.itemCount
     *
     * @param items Mixed list of GroupItem and FolderItem objects
     * @param option Sort option to apply
     * @param groupsAlwaysOnTop When true, groups are sorted separately and placed first
     * @return Sorted list maintaining the same mixed type
     */
    fun sortMixedItems(
        items: List<Any>,
        option: FolderSortOption,
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

        fun sortList(list: List<Any>): List<Any> = when (option) {
            FolderSortOption.NAME_A_TO_Z        -> list.sortedBy { itemName(it).lowercase() }
            FolderSortOption.NAME_Z_TO_A        -> list.sortedByDescending { itemName(it).lowercase() }
            FolderSortOption.ITEMS_MOST_FIRST   -> list.sortedByDescending { itemCount(it) }
            FolderSortOption.ITEMS_FEWEST_FIRST -> list.sortedBy { itemCount(it) }
            FolderSortOption.CUSTOM_ORDER       -> list // Should not reach here
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
     * Build the unified display order of groups + ungrouped folders, preserving
     * the saved custom order from preferences.
     *
     * Behavior:
     * - First run (empty savedOrder): groups first, then folders; saves this as baseline
     * - Subsequent runs: restore items in saved positions, prepend new items at the top,
     *   prune deleted items
     *
     * @param groups List of GroupItem objects
     * @param folders List of FolderItem objects
     * @param savedOrder Previously saved order as list of keys ("g_123", "f_456")
     * @return Ordered mixed list and the new order to persist
     */
    fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        savedOrder: List<String>
    ): Pair<List<Any>, List<String>> {
        val groupMap  = groups.associateBy { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (savedOrder.isEmpty()) {
            // First time — groups first, then folders; persist as baseline
            val initial: List<Any> = groups + folders
            val newOrder = initial.map { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> ""
                }
            }.filter { it.isNotEmpty() }
            return initial to newOrder
        }

        val savedSet = savedOrder.toSet()
        // Items in their saved position (skip keys whose item was deleted)
        val ordered = savedOrder.mapNotNull { key -> groupMap[key] ?: folderMap[key] }
        // New items not yet in the saved order — prepend at the beginning
        val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
        val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
        val result: List<Any> = newGroups + newFolders + ordered

        // Persist the pruned + extended order
        val newOrder = result.map { item ->
            when (item) {
                is GroupItem  -> "g_${item.groupId}"
                is FolderItem -> "f_${item.bucketId}"
                else          -> ""
            }
        }.filter { it.isNotEmpty() }

        return result to newOrder
    }

    /**
     * Sort groups and folders for the Hide Folders screen using the given sort option.
     *
     * For CUSTOM_ORDER, restores the saved drag order (read-only — nothing is persisted here).
     * Returns (sortedGroups, sortedFolders) as separate lists.
     *
     * @param groups List of GroupItem objects
     * @param folders List of FolderItem objects (may include ghost folders)
     * @param sortOption Sort option to apply
     * @param groupsAlwaysOnTop When true, groups are sorted separately and placed first
     * @param customOrder Saved custom order for this context (root or group-specific)
     * @return Pair of (sorted groups, sorted folders)
     */
    fun sortHideScreenItems(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        sortOption: FolderSortOption,
        groupsAlwaysOnTop: Boolean,
        customOrder: List<String>
    ): Pair<List<GroupItem>, List<FolderItem>> {
        if (sortOption == FolderSortOption.CUSTOM_ORDER && customOrder.isNotEmpty()) {
            val groupMap  = groups.associateBy { "g_${it.groupId}" }
            val folderMap = folders.associateBy { "f_${it.bucketId}" }
            val savedSet  = customOrder.toSet()
            val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
            val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
            val ordered = customOrder.mapNotNull { groupMap[it] ?: folderMap[it] }
            val result = newGroups + newFolders + ordered
            return result.filterIsInstance<GroupItem>() to result.filterIsInstance<FolderItem>()
        }

        val sorted = sortMixedItems(groups + folders, sortOption, groupsAlwaysOnTop)
        return sorted.filterIsInstance<GroupItem>() to sorted.filterIsInstance<FolderItem>()
    }
}

