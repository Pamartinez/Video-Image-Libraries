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
    ): List<Any> {
        val saved     = preferences.getGroupMixedOrder(groupId)
        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (saved.isEmpty()) return groups + folders

        val ordered    = saved.mapNotNull { groupMap[it] ?: folderMap[it] }
        val savedSet   = saved.toSet()
        val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
        // New items are prepended so they always appear at the top
        return newGroups + newFolders + ordered
    }
}

