package com.videolibrary.ui.screen

import androidx.compose.runtime.Composable
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.GroupGridItem
import com.example.common.ui.screen.AddFolderToGroupScreen as CommonAddFolderToGroupScreen

/**
 * Video-library entry point for the add-to-group picker.
 * Delegates entirely to [CommonAddFolderToGroupScreen], injecting
 * [FolderGridItem] and [GroupGridItem] from this library so video
 * thumbnails are used throughout.
 */
@Composable
fun AddFolderToGroupScreen(
    allFolders: List<FolderItem>,
    allGroups: List<GroupItem>,
    currentGroupId: Long,
    viewType: ViewType = ViewType.GRID_LARGE,
    onSave: (folderBucketIds: Set<Int>, subGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit
) {
    CommonAddFolderToGroupScreen(
        folders        = allFolders,
        groups         = allGroups,
        currentGroupId = currentGroupId,
        viewType       = viewType,
        onSave          = onSave,
        onCancel        = onCancel,
        folderGridItem  = { folder, isSelected, vt, onClick, _, mod ->
            FolderGridItem(
                folder          = folder,
                isSelected      = isSelected,
                isSelectionMode = true,
                viewType        = vt,
                onClick         = onClick,
                modifier        = mod
            )
        },
        groupGridItem   = { group, vt, onClick, _, mod ->
            GroupGridItem(
                group           = group,
                isSelected      = false,
                isSelectionMode = false,
                viewType        = vt,
                onClick         = onClick,
                modifier        = mod
            )
        }
    )
}
