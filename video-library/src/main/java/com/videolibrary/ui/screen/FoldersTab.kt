package com.videolibrary.ui.screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.screen.SharedFoldersTab
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.FolderListItem
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.GroupListItem
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Video-library FoldersTab.
 * Delegates to [SharedFoldersTab] with video-specific configuration (supports LIST view).
 */
@Composable
fun FoldersTab(
    folders: List<FolderItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Int>,
    isLoading: Boolean,
    sortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER,
    orderedMixedItems: List<Any> = emptyList(),
    selectedGroupIds: Set<Long> = emptySet(),
    isGroupCreationMode: Boolean = false,
    groupCreationSelectedFolderIds: Set<Int> = emptySet(),
    groupCreationSelectedGroupIds: Set<Long> = emptySet(),
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit = {},
    onGroupLongClick: (GroupItem) -> Unit = {},
    onReorderFolders: (fromIndex: Int, toIndex: Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    onExitSelectionForDrag: () -> Unit = {},
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    // Build ordered items list
    val orderedItems: List<Any> = if (orderedMixedItems.isNotEmpty())
        orderedMixedItems
    else
        folders.map { it as Any }

    val isLargeGrid = viewType == ViewType.GRID_LARGE

    SharedFoldersTab(
        orderedMixedItems = orderedItems,
        isLoading = isLoading,
        viewType = viewType,
        sortOption = sortOption,
        isSelectionMode = isSelectionMode,
        selectedFolderIds = selectedIds,
        selectedGroupIds = selectedGroupIds,
        isGroupCreationMode = isGroupCreationMode,
        groupCreationSelectedFolderIds = groupCreationSelectedFolderIds,
        groupCreationSelectedGroupIds = groupCreationSelectedGroupIds,
        onFolderClick = onFolderClick,
        onFolderLongClick = onFolderLongClick,
        onGroupClick = onGroupClick,
        onGroupLongClick = onGroupLongClick,
        onReorderFolders = onReorderFolders,
        onReorderDone = onReorderDone,
        onExitSelectionForDrag = onExitSelectionForDrag,
        lazyListState = lazyListState,
        lazyGridState = lazyGridState,

        colors = LocalVideoColors.current,

        isCustomOrder = { it == FolderSortOption.CUSTOM_ORDER },
        supportsListView = true,
        showHeaderRow = true,
        emptyStateMessage = "No video folders found on this device.",
        gridSpacing = if (isLargeGrid) 18.dp else 12.dp,
        gridColumnCount = if (isLargeGrid) 2 else 3,

        folderGridItem = { folder, isSelected, isSelMode, vt, onClick, onLongClick, isDragging, mod ->
            FolderGridItem(
                folder = folder,
                isSelected = isSelected,
                isSelectionMode = isSelMode,
                viewType = vt,
                onClick = onClick,
                onLongClick = onLongClick,
                isDragging = isDragging,
                modifier = mod
            )
        },

        groupGridItem = { group, isSelected, isSelMode, vt, onClick, onLongClick, isDragging, mod ->
            GroupGridItem(
                group = group,
                isSelected = isSelected,
                isSelectionMode = isSelMode,
                viewType = vt,
                onClick = onClick,
                onLongClick = onLongClick,
                isDragging = isDragging,
                modifier = mod
            )
        },

        folderListItem = { folder, isSelected, isSelMode, onClick, onLongClick, isDragging, dragOffset ->
            FolderListItem(
                folder = folder,
                isSelected = isSelected,
                isSelectionMode = isSelMode,
                onClick = onClick,
                onLongClick = onLongClick,
                isDragging = isDragging,
                dragOffset = dragOffset
            )
        },

        groupListItem = { group, isSelected, isSelMode, onClick, onLongClick ->
            GroupListItem(
                group = group,
                isSelected = isSelected,
                isSelectionMode = isSelMode,
                onClick = onClick,
                onLongClick = onLongClick
            )
        },

        modifier = modifier
    )
}

