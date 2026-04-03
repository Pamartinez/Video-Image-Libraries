package com.imagelibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.screen.FolderPickerScreen as CommonFolderPickerScreen
import com.imagelibrary.ui.components.GroupGridItem

/**
 * Image-library entry point for the shared [CommonFolderPickerScreen].
 *
 * Injects:
 * - [AsyncImage] (no VideoFrameDecoder) as the folder thumbnail.
 * - Image-library [GroupGridItem] (backed by ImageThumbnail) as the group cell.
 */
@Composable
fun FolderPickerScreen(
    title: String,
    folders: List<FolderItem>,
    groups: List<GroupItem> = emptyList(),
    orderedMixedItems: List<Any> = emptyList(),
    groupCustomOrders: Map<Long, List<String>> = emptyMap(),
    onFolderSelected: (String) -> Unit,
    onBack: () -> Unit,
    onCreateFolderAndSelect: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    CommonFolderPickerScreen(
        title                   = title,
        folders                 = folders,
        groups                  = groups,
        orderedMixedItems       = orderedMixedItems,
        groupCustomOrders       = groupCustomOrders,
        onFolderSelected        = onFolderSelected,
        onBack                  = onBack,
        onCreateFolderAndSelect = onCreateFolderAndSelect,
        modifier                = modifier,
        thumbnailContent        = { folder, mod ->
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(folder.latestItemUri)
                    .crossfade(true)
                    .build(),
                contentDescription = folder.name,
                contentScale       = ContentScale.Crop,
                modifier           = mod
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
