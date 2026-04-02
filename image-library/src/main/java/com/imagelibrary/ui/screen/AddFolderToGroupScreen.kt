package com.imagelibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.example.common.ui.screen.AddFolderToGroupScreen as CommonAddFolderToGroupScreen

/**
 * Image-library entry point for the add-to-group picker.
 * Delegates entirely to [CommonAddFolderToGroupScreen], injecting
 * [FolderGridItem] and [GroupGridItem] from this library so image
 * thumbnails are used throughout.
 */
@Composable
fun AddFolderToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    currentGroupId: Long,
    modifier: Modifier = Modifier,
    viewType: ViewType = ViewType.GRID_LARGE,
    onSave: (selectedFolderIds: Set<Int>, selectedGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit
) {
    CommonAddFolderToGroupScreen(
        folders        = folders,
        groups         = groups,
        currentGroupId = currentGroupId,
        modifier        = modifier,
        viewType       = viewType,
        onSave          = onSave,
        onCancel        = onCancel,
        folderGridItem  = { folder, isSelected, vt, onClick, onLongClick, mod ->
            FolderGridItem(
                folder          = folder,
                isSelected      = isSelected,
                isSelectionMode = true,
                viewType        = vt,
                onClick         = onClick,
                onLongClick     = onLongClick,
                modifier        = mod
            )
        },
        groupGridItem   = { group, vt, onClick, onLongClick, mod ->
            GroupGridItem(
                group           = group,
                isSelected      = false,
                isSelectionMode = false,
                viewType        = vt,
                onClick         = onClick,
                onLongClick     = onLongClick,
                modifier        = mod
            )
        }
    )
}
