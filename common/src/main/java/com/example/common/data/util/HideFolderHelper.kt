package com.example.common.data.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.repository.GroupRepository

/**
 * Shared helper functions for hide/show folder logic.
 * Consolidates identical code from ImageListViewModel and VideoListViewModel.
 */
object HideFolderHelper {

    /**
     * Determine if a group is fully hidden (all descendant albums are hidden).
     * Uses the group's memberBucketIds which should already include all descendants
     * if built with getAllDescendantBucketIds().
     *
     * @param groupMemberBucketIds All bucket IDs belonging to the group (including nested)
     * @param allFolders All available folders
     * @param hiddenFolderPaths Currently hidden folder paths
     * @return true if all folders with non-blank paths are hidden
     */
    fun isGroupFullyHidden(
        groupMemberBucketIds: Set<Int>,
        allFolders: List<FolderItem>,
        hiddenFolderPaths: Set<String>
    ): Boolean {
        val paths = allFolders
            .filter { it.bucketId in groupMemberBucketIds }
            .map { it.path }
            .filter { it.isNotBlank() }
        return paths.isNotEmpty() && paths.all { it in hiddenFolderPaths }
    }

    /**
     * Get all folder paths that belong to a group (including nested sub-groups).
     * Filters out blank paths.
     *
     * @param groupId The group ID
     * @param groupRepository Repository to fetch descendant bucket IDs
     * @param allFolders All available folders
     * @return List of non-blank folder paths
     */
    suspend fun getAllGroupFolderPaths(
        groupId: Long,
        groupRepository: GroupRepository,
        allFolders: List<FolderItem>
    ): List<String> {
        val allBucketIds = groupRepository.getAllDescendantBucketIds(groupId)
        return allFolders
            .filter { it.bucketId in allBucketIds }
            .map { it.path }
            .filter { it.isNotBlank() }
    }

    /**
     * Get folder items that belong to a group (including nested sub-groups).
     *
     * @param groupId The group ID
     * @param groupRepository Repository to fetch descendant bucket IDs
     * @param allFolders All available folders
     * @return List of FolderItems with non-blank paths
     */
    suspend fun getAllGroupFolders(
        groupId: Long,
        groupRepository: GroupRepository,
        allFolders: List<FolderItem>
    ): List<FolderItem> {
        val allBucketIds = groupRepository.getAllDescendantBucketIds(groupId)
        return allFolders.filter { it.bucketId in allBucketIds && it.path.isNotBlank() }
    }
}

