package com.example.common.util

import com.example.common.data.model.FolderItem

/**
 * Shared utilities for handling "ghost" folders - folders that are hidden in app preferences
 * but no longer exist in MediaStore.
 */
object GhostFolderUtils {

    /**
     * Create ghost FolderItem objects from hidden folder metadata for folders
     * that no longer exist in MediaStore.
     *
     * @param hiddenMeta Map of path -> Triple(name, bucketId, itemCount)
     * @param mediaStorePaths Set of paths that currently exist in MediaStore
     * @return List of ghost FolderItem objects
     */
    fun createGhostFolders(
        hiddenMeta: Map<String, Triple<String, Int, Int>>,
        mediaStorePaths: Set<String>
    ): List<FolderItem> {
        return hiddenMeta
            .filter { (path, _) -> path !in mediaStorePaths }
            .map { (path, triple) ->
                FolderItem(
                    bucketId  = triple.second,
                    name      = triple.first,
                    itemCount = triple.third,
                    path      = path
                )
            }
    }

    /**
     * Merge MediaStore folders with ghost folders to create a complete folder list.
     */
    fun mergeWithGhostFolders(
        mediaStoreFolders: List<FolderItem>,
        hiddenMeta: Map<String, Triple<String, Int, Int>>
    ): List<FolderItem> {
        val mediaStorePaths = mediaStoreFolders.map { it.path }.toSet()
        val ghostFolders = createGhostFolders(hiddenMeta, mediaStorePaths)
        return mediaStoreFolders + ghostFolders
    }
}

