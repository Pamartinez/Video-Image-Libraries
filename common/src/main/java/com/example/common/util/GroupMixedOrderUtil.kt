package com.example.common.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.preferences.SharedAppPreferences

/**
 * Utilities for handling custom ordering of mixed items (groups + folders) within groups.
 * Shared between image-library and video-library.
 */
object GroupMixedOrderUtil {

    /**
     * Apply custom order to a group's mixed items (sub-groups + folders).
     * New items not in the saved order are prepended at the top.
     *
     * @param groupId Group ID to get the custom order for
     * @param groups List of sub-groups in this group
     * @param folders List of folders in this group
     * @param preferences SharedAppPreferences instance to read the saved order
     * @return Ordered list of mixed items (groups + folders)
     */
    fun applyCustomGroupMixedOrder(
        groupId: Long,
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        preferences: SharedAppPreferences
    ): List<Any> =
        applyCustomGroupMixedOrder(preferences.getGroupMixedOrder(groupId), groups, folders)

    /**
     * Same as [applyCustomGroupMixedOrder] but takes the already-loaded saved order
     * directly (list of keys "g_123" / "f_456") instead of reading it from preferences.
     * Lets UI layers that already hold the saved order (e.g. pickers) reuse the exact
     * same ordering logic the ViewModel uses for the real group display.
     */
    fun applyCustomGroupMixedOrder(
        savedOrder: List<String>,
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (savedOrder.isEmpty()) return groups + folders

        val ordered    = savedOrder.mapNotNull { groupMap[it] ?: folderMap[it] }
        val savedSet   = savedOrder.toSet()
        val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
        // New items are prepended so they always appear at the top
        return newGroups + newFolders + ordered
    }
}

