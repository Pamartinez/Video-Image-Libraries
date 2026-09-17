package com.videolibrary.ui.components

import com.common.data.model.FolderItem
import com.common.data.model.GroupItem
import com.common.data.model.MixedItem
import com.common.data.model.toMixedItems

/**
 * Typealias kept for source compatibility — all video-library code can still use
 * MixedFolderItem.Folder / MixedFolderItem.Group unchanged.
 */
typealias MixedFolderItem = MixedItem

/** Maps a raw [List<Any>] from the ViewModel to a typed [List<MixedItem>]. */
fun List<Any>.toMixedFolderItems(): List<MixedItem> = toMixedItems()
