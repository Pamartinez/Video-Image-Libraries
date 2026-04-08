package com.example.common.data.util

import android.net.Uri
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupEntity
import com.example.common.data.model.GroupItem

/**
 * Shared preview generation logic for albums and groups.
 *
 * Architecture:
 * - Each album generates its preview from its FIRST media item according to its OWN sort order
 * - Each group generates its preview from the FIRST 4 albums (up to 4 preview URIs) according to the group's sort order
 * - Child groups recursively use the same logic
 *
 * This ensures previews are always consistent with the current sort order and independent for each container.
 */
object PreviewGenerator {

    /**
     * Generate preview URIs for a group by taking the first 4 folders after sorting.
     *
     * @param groupId The group's ID
     * @param memberBucketIds List of folder bucket IDs in this group
     * @param childGroups List of child group entities
     * @param allFolders All available folders (with their previews already generated per-album)
     * @param groupSortOption The sort option for this specific group (0=CUSTOM_ORDER, 1=NAME_A_TO_Z, etc.)
     * @param groupCustomOrder The custom order for this group (if sortOption=CUSTOM_ORDER)
     * @param getChildGroupPreviews Callback to get preview URIs for child groups (recursive)
     * @return List of up to 4 preview URIs
     */
    fun generateGroupPreview(
        groupId: Long,
        memberBucketIds: List<Int>,
        childGroups: List<GroupEntity>,
        allFolders: List<FolderItem>,
        groupSortOption: Int = 0,
        groupCustomOrder: List<String> = emptyList(),
        getChildGroupPreviews: (Long) -> List<Uri> = { emptyList() }
    ): List<Uri> {
        // Get member folders
        val memberFolders = memberBucketIds.mapNotNull { bid ->
            allFolders.find { it.bucketId == bid }
        }

        // Convert child entities to lightweight items for sorting
        val subGroupItems = childGroups.map { child ->
            GroupItem(
                groupId = child.groupId,
                name = child.name,
                parentGroupId = child.parentGroupId,
                previewUris = getChildGroupPreviews(child.groupId)
            )
        }

        // Build ordered list of items (groups and folders) according to this group's sort
        val orderedItems = buildOrderedGroupItems(
            groupSortOption = groupSortOption,
            groupCustomOrder = groupCustomOrder,
            subGroups = subGroupItems,
            memberFolders = memberFolders
        )

        // Extract first 4 FOLDERS ONLY (skip groups) for preview
        return orderedItems
            .filterIsInstance<FolderItem>()
            .take(4)
            .mapNotNull { it.latestItemUri }
    }

    /**
     * Build ordered list of items (folders and sub-groups) according to the specified sort option.
     *
     * @param groupSortOption Sort option ID (0=CUSTOM_ORDER, 1=NAME_A_TO_Z, 2=NAME_Z_TO_A, 3=ITEMS_MOST_FIRST, 4=ITEMS_FEWEST_FIRST)
     * @param groupCustomOrder Custom order keys (e.g., ["g_123", "f_456"])
     * @param subGroups List of child groups
     * @param memberFolders List of member folders
     * @return Ordered list of Any (GroupItem or FolderItem)
     */
    private fun buildOrderedGroupItems(
        groupSortOption: Int,
        groupCustomOrder: List<String>,
        subGroups: List<GroupItem>,
        memberFolders: List<FolderItem>
    ): List<Any> {
        return when (groupSortOption) {
            0 -> { // CUSTOM_ORDER
                if (groupCustomOrder.isEmpty()) {
                    // No saved order, return in default order
                    subGroups + memberFolders
                } else {
                    // Build map of items by their keys
                    val byGroupKey = subGroups.associateBy { "g_${it.groupId}" }
                    val byFolderKey = memberFolders.associateBy { "f_${it.bucketId}" }
                    val savedSet = groupCustomOrder.toSet()

                    // New items first, then saved order
                    buildList {
                        subGroups.forEach { if ("g_${it.groupId}" !in savedSet) add(it) }
                        memberFolders.forEach { if ("f_${it.bucketId}" !in savedSet) add(it) }
                        groupCustomOrder.forEach { key ->
                            (byGroupKey[key] ?: byFolderKey[key])?.let { add(it) }
                        }
                    }
                }
            }
            1 -> { // NAME_A_TO_Z
                (subGroups + memberFolders).sortedBy { item ->
                    when (item) {
                        is GroupItem -> item.name.lowercase()
                        is FolderItem -> item.name.lowercase()
                        else -> ""
                    }
                }
            }
            2 -> { // NAME_Z_TO_A
                (subGroups + memberFolders).sortedByDescending { item ->
                    when (item) {
                        is GroupItem -> item.name.lowercase()
                        is FolderItem -> item.name.lowercase()
                        else -> ""
                    }
                }
            }
            3 -> { // ITEMS_MOST_FIRST
                (subGroups + memberFolders).sortedByDescending { item ->
                    when (item) {
                        is GroupItem -> item.totalItemCount
                        is FolderItem -> item.itemCount
                        else -> 0
                    }
                }
            }
            4 -> { // ITEMS_FEWEST_FIRST
                (subGroups + memberFolders).sortedBy { item ->
                    when (item) {
                        is GroupItem -> item.totalItemCount
                        is FolderItem -> item.itemCount
                        else -> 0
                    }
                }
            }
            else -> subGroups + memberFolders
        }
    }
}

