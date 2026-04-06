package com.example.common.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem

/**
 * Shared visibility utilities for determining which groups/folders are visible
 * based on hidden folder paths.
 */
object VisibilityUtils {

    /**
     * Check if a folder is visible (not hidden).
     * @param folderPath The folder's path
     * @param hiddenPaths Set of hidden folder paths
     * @return true if the folder should be shown
     */
    fun isFolderVisible(folderPath: String, hiddenPaths: Set<String>): Boolean {
        return folderPath.isBlank() || folderPath !in hiddenPaths
    }

    /**
     * Check if a group is visible (has at least one non-hidden member folder).
     * @param group The GroupItem to check
     * @param bucketPathMap Map of bucketId to folder path
     * @param hiddenPaths Set of hidden folder paths
     * @return true if at least one member folder is visible
     */
    fun isGroupVisible(
        group: GroupItem,
        bucketPathMap: Map<Int, String>,
        hiddenPaths: Set<String>
    ): Boolean {
        return group.memberBucketIds.any { bucketId ->
            val path = bucketPathMap[bucketId]
            path.isNullOrBlank() || path !in hiddenPaths
        }
    }

    /**
     * Filter a list of mixed items (groups and folders) to only visible ones.
     */
    fun filterVisibleMixedItems(
        items: List<Any>,
        bucketPathMap: Map<Int, String>,
        hiddenPaths: Set<String>
    ): List<Any> {
        return items.filter { item ->
            when (item) {
                is FolderItem -> isFolderVisible(item.path, hiddenPaths)
                is GroupItem  -> isGroupVisible(item, bucketPathMap, hiddenPaths)
                else          -> true
            }
        }
    }
}

