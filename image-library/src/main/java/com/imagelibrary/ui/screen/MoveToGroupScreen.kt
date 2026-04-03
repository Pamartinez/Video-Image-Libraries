package com.imagelibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.ViewType
import com.example.common.ui.screen.MoveToGroupScreen as CommonMoveToGroupScreen
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.components.ImageThumbnail

/**
 * Image-library wrapper around the shared [CommonMoveToGroupScreen].
 * Injects [ImageThumbnail], [FolderGridItem], and [GroupGridItem] as renderers.
 */
@Composable
fun MoveToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    movingFolderIds: Set<Int>,
    movingGroupIds: Set<Long>,
    viewType: ViewType,
    onMoveHere: (targetGroupId: Long?) -> Unit,
    onCreateGroupAndMove: (name: String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isLargeGrid = viewType == ViewType.GRID_LARGE
    CommonMoveToGroupScreen(
        folders              = folders,
        groups               = groups,
        movingFolderIds      = movingFolderIds,
        movingGroupIds       = movingGroupIds,
        columnCount          = if (isLargeGrid) 2 else 3,
        gridSpacing          = if (isLargeGrid) 24f else 16f,
        onMoveHere           = onMoveHere,
        onCreateGroupAndMove = onCreateGroupAndMove,
        onCancel             = onCancel,
        thumbnailContent     = { uri, mod ->
            ImageThumbnail(
                contentUri         = uri,
                contentDescription = "Preview",
                contentScale       = ContentScale.Crop,
                modifier           = mod
            )
        },
        folderItemContent    = { folder, mod ->
            FolderGridItem(
                folder          = folder,
                isSelected      = false,
                isSelectionMode = false,
                viewType        = viewType,
                onClick         = {},
                onLongClick     = {},
                modifier        = mod
            )
        },
        groupItemContent     = { group, mod, onClick, onLongClick ->
            GroupGridItem(
                group           = group,
                isSelected      = false,
                isSelectionMode = false,
                viewType        = viewType,
                onClick         = onClick,
                onLongClick     = onLongClick,
                modifier        = mod
            )
        },
        modifier = modifier
    )
}
