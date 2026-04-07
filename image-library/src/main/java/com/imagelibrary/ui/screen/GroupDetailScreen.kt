package com.imagelibrary.ui.screen

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.screen.SharedGroupDetailScreen
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.components.SelectionHeader
import com.imagelibrary.ui.components.SortDialog
import com.imagelibrary.ui.components.ViewTypeToggleButton
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Image-library GroupDetailScreen.
 * Delegates to [SharedGroupDetailScreen] with image-specific configuration.
 */
@Composable
fun GroupDetailScreen(
    groupName: String,
    folders: List<FolderItem>,
    subGroups: List<GroupItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    onBack: () -> Unit,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    onCycleViewType: () -> Unit = {},
    onCreateAlbum: () -> Unit = {},
    onAddFolder: () -> Unit = {},
    onRenameGroup: () -> Unit = {},
    onHideAlbums: () -> Unit = {},
    onDestroyGroup: () -> Unit = {},
    onViewAs: () -> Unit = {},
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    onDelete: () -> Unit = {},
    onGroup: () -> Unit = {},
    onSelectAll: () -> Unit = {},
    onCancelSelection: () -> Unit = {},
    onShare: () -> Unit = {},
    onMove: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    sortOption: SortOption = SortOption.CUSTOM_ORDER,
    onSortOptionSelected: (SortOption) -> Unit = {},
    groupsAlwaysOnTop: Boolean = false,
    orderedMixedItems: List<Any> = emptyList(),
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    lazyGridState: LazyGridState = rememberLazyGridState(),
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
        lazyGridState = lazyGridState,
        
        colors = LocalImageColors.current,
        
        albumCreationDescription = "Create a new album and add pictures and videos manually.",
        isLargeGrid = { it == ViewType.GRID_LARGE },
        getColumnCount = { if (it == ViewType.GRID_LARGE) 2 else 3 },
        getSpacing = { if (it == ViewType.GRID_LARGE) 18.dp else 12.dp },
        isCustomOrder = { it == SortOption.CUSTOM_ORDER },
        sortMixedItems = { items, sort, groupsTop ->
            when (sort) {
                SortOption.NAME_A_TO_Z -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedBy { it.sortKey.lowercase() } +
                    items.filterIsInstance<MixedItem.Folder>().sortedBy { it.sortKey.lowercase() }
                } else items.sortedBy { it.sortKey.lowercase() }
                SortOption.NAME_Z_TO_A -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.sortKey.lowercase() } +
                    items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.sortKey.lowercase() }
                } else items.sortedByDescending { it.sortKey.lowercase() }
                SortOption.ITEMS_MOST_FIRST -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedByDescending { it.itemCount } +
                    items.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.itemCount }
                } else items.sortedByDescending { it.itemCount }
                SortOption.ITEMS_FEWEST_FIRST -> if (groupsTop) {
                    items.filterIsInstance<MixedItem.Group>().sortedBy { it.itemCount } +
                    items.filterIsInstance<MixedItem.Folder>().sortedBy { it.itemCount }
                } else items.sortedBy { it.itemCount }
                SortOption.CUSTOM_ORDER -> items
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
            SortDialog(
                currentSortOption = current,
                onSortOptionSelected = onSelected,
                onDismiss = onDismiss
            )
        },
        
        selectionHeader = { count, total, allSel, onAll, onCancel ->
            SelectionHeader(
                selectedCount = count,
                allSelected = allSel,
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


