package com.example.common.data.model

/**
 * Sealed wrapper used in the Folders-tab grid/list to hold either a
 * [FolderItem] or a [GroupItem] in a single ordered collection.
 *
 * Replaces the library-specific `FolderListItem` (image-library) and
 * `MixedFolderItem` (video-library) sealed classes.
 */
sealed class MixedItem {
    data class Folder(val folder: FolderItem) : MixedItem()
    data class Group(val group: GroupItem) : MixedItem()

    /** Name used for non-custom sort comparisons. */
    val sortKey: String
        get() = when (this) {
            is Folder -> folder.name
            is Group  -> group.name
        }

    /** Stable key for LazyVerticalGrid `key =` parameter (prevents re-animation). */
    val uniqueKey: Any
        get() = when (this) {
            is Folder -> "folder_${folder.bucketId}"
            is Group  -> "group_${group.groupId}"
        }

    /**
     * Total item count used for sort-by-count ordering.
     * Folder → [FolderItem.itemCount], Group → [GroupItem.totalItemCount].
     */
    val itemCount: Int
        get() = when (this) {
            is Folder -> folder.itemCount
            is Group  -> group.totalItemCount
        }
}

/** Maps a [List<Any>] from the ViewModel to a typed [List<MixedItem>]. */
fun List<Any>.toMixedItems(): List<MixedItem> = mapNotNull { item ->
    when (item) {
        is GroupItem  -> MixedItem.Group(item)
        is FolderItem -> MixedItem.Folder(item)
        else          -> null
    }
}

