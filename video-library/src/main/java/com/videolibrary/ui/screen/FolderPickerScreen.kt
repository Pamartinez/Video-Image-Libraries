package com.videolibrary.ui.screen

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.screen.FolderPickerScreen as CommonFolderPickerScreen
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.VideoThumbnail

/**
 * Video-library entry point for the shared [CommonFolderPickerScreen].
 *
 * Injects:
 * - [VideoThumbnail] with brightness-aware frame selection as the folder thumbnail.
 * - Video-library [GroupGridItem] (backed by VideoThumbnail) as the group cell.
 */
@Composable
fun FolderPickerScreen(
    title: String,
    folders: List<FolderItem>,
    groups: List<GroupItem> = emptyList(),
    orderedMixedItems: List<Any> = emptyList(),
    groupCustomOrders: Map<Long, List<String>> = emptyMap(),
    groupSortOptions: Map<Long, Int> = emptyMap(),
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
    onFolderSelected: (String) -> Unit,
    onBack: () -> Unit,
    onCreateFolderAndSelect: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    CommonFolderPickerScreen(
        title                   = title,
        folders                 = folders,
        groups                  = groups,
        orderedMixedItems       = orderedMixedItems,
        groupCustomOrders       = groupCustomOrders,
        groupSortOptions        = groupSortOptions,
        groupOrderedItems       = groupOrderedItems,
        onFolderSelected        = onFolderSelected,
        onBack                  = onBack,
        onCreateFolderAndSelect = onCreateFolderAndSelect,
        modifier                = modifier,
        thumbnailContent        = { folder, mod ->
            VideoThumbnail(
                contentUri         = folder.latestItemUri,
                contentDescription = folder.name,
                contentScale       = ContentScale.Crop,
                modifier           = mod
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
            )
        },
        groupItemContent        = { group, onClick, onLongClick ->
            GroupGridItem(
                group           = group,
                isSelected      = false,
                isSelectionMode = false,
                onClick         = onClick,
                onLongClick     = onLongClick
            )
        }
    )
}
