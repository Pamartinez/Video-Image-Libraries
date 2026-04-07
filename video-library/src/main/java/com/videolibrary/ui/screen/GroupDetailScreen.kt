package com.videolibrary.ui.screen

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.screen.SharedGroupDetailScreen
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.FolderSortDialog
import com.videolibrary.ui.components.SelectionModeHeader
import com.videolibrary.ui.components.ViewTypeToggleButton
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Video-library GroupDetailScreen.
 * Delegates to [SharedGroupDetailScreen] with video-specific configuration.
 */
@Composable
fun GroupDetailScreen(
    groupName: String,
    folders: List<FolderItem>,
    subGroups: List<GroupItem>,
    viewType: ViewType,
    sortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER,
    groupsAlwaysOnTop: Boolean = false,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    onBack: () -> Unit,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    onCycleViewType: () -> Unit,
    onAddFolder: () -> Unit,
    onRenameGroup: () -> Unit,
    onHideAlbums: () -> Unit = {},
    onDestroyGroup: () -> Unit,
    onSortOptionSelected: (FolderSortOption) -> Unit,
    onDelete: () -> Unit,
    onGroup: () -> Unit,
    onSelectAll: () -> Unit,
    onCancelSelection: () -> Unit,
    onCreateAlbum: () -> Unit = {},
    onViewAs: () -> Unit = {},
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    orderedMixedItems: List<Any> = emptyList(),
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    onShare: () -> Unit = {},
    onMove: () -> Unit = {},
    onRemoveFromGroup: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    SharedGroupDetailScreen(
        groupName = groupName,
        folders = folders,
        subGroups = subGroups,
        viewType = viewType,
        sortOption = sortOption,
        isSelectionMode = isSelectionMode,
        selectedFolderIds = selectedFolderIds,
        selectedGroupIds = selectedGroupIds,
        onBack = onBack,
        onFolderClick = onFolderClick,
        onFolderLongClick = onFolderLongClick,
        onGroupClick = onGroupClick,
        onGroupLongClick = onGroupLongClick,
        onCycleViewType = onCycleViewType,
        onAddFolder = onAddFolder,
        onRenameGroup = onRenameGroup,
        onHideAlbums = onHideAlbums,
        onDestroyGroup = onDestroyGroup,
        onSortOptionSelected = onSortOptionSelected,
        onDelete = onDelete,
        onGroup = onGroup,
        onSelectAll = onSelectAll,
        onCancelSelection = onCancelSelection,
        onCreateAlbum = onCreateAlbum,
        onViewAs = onViewAs,
        onSettings = onSettings,
        onAbout = onAbout,
        onShare = onShare,
        onMove = onMove,
        onOpenLocation = onOpenLocation,
        groupsAlwaysOnTop = groupsAlwaysOnTop,
        orderedMixedItems = orderedMixedItems,
        onReorderFolders = onReorderFolders,
        onReorderDone = onReorderDone,
        lazyGridState = rememberLazyGridState(),
        
        colors = LocalVideoColors.current,
        
        albumCreationDescription = "Create a new album and add videos manually.",
        isLargeGrid = { it == ViewType.GRID_LARGE },
        getColumnCount = { if (it == ViewType.GRID_LARGE) 2 else 3 },
        getSpacing = { if (it == ViewType.GRID_LARGE) 18.dp else 12.dp },
        isCustomOrder = { it == FolderSortOption.CUSTOM_ORDER },
        sortMixedItems = { items, sort, groupsTop ->
            when (sort) {
                FolderSortOption.NAME_A_TO_Z -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedBy { it.sortKey.lowercase() } +
                    items.filterIsInstance<MixedItem.Folder>().sortedBy { it.sortKey.lowercase() }
                } else items.sortedBy { it.sortKey.lowercase() }
                FolderSortOption.NAME_Z_TO_A -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.sortKey.lowercase() } +
                    items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.sortKey.lowercase() }
                } else items.sortedByDescending { it.sortKey.lowercase() }
                FolderSortOption.ITEMS_MOST_FIRST -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.itemCount } +
                    items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.itemCount }
                } else items.sortedByDescending { it.itemCount }
                FolderSortOption.ITEMS_FEWEST_FIRST -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedBy { it.itemCount } +
                    items.filterIsInstance<MixedItem.Folder>().sortedBy { it.itemCount }
                } else items.sortedBy { it.itemCount }
                FolderSortOption.CUSTOM_ORDER -> items
            }
        },
        
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
        
        sortDialog = { current, onSelected, onDismiss ->
            FolderSortDialog(
                currentSortOption = current,
                onSortOptionSelected = onSelected,
                onDismiss = onDismiss
            )
        },
        
        selectionHeader = { count, total, allSel, onAll, onCancel ->
            SelectionModeHeader(
                selectedCount = count,
                totalCount = total,
                onSelectAll = onAll,
                onCancel = onCancel
            )
        },
        
        viewTypeToggle = { vt, onClick ->
            ViewTypeToggleButton(viewType = vt, onClick = onClick)
        },
        
        modifier = modifier
    )
}



