package com.imagelibrary.ui.screen

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.data.model.toMixedItems
import com.example.common.ui.screen.SharedFoldersTab
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Typealias kept for source compatibility
 */
typealias FolderListItem = MixedItem

/** Maps a raw [List<Any>] to a typed [List<MixedItem>]. */
fun List<Any>.toFolderListItems(): List<MixedItem> = toMixedItems()

/**
 * Image-library FoldersTab.
 * Delegates to [SharedFoldersTab] with image-specific configuration.
 */
@Composable
fun FoldersTab(
    mixedItems: List<Any> = emptyList(),
    folders: List<FolderItem>? = null,
    groups: List<GroupItem>? = null,
    viewType: ViewType = ViewType.GRID_LARGE,
    isSelectionMode: Boolean = false,
    selectedFolderIds: Set<Int> = emptySet(),
    selectedGroupIds: Set<Long> = emptySet(),
    isLoading: Boolean,
    showCheckboxes: Boolean = false,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit = {},
    onGroupLongClick: (GroupItem) -> Unit = {},
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    sortOption: SortOption = SortOption.CUSTOM_ORDER,
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    onExitSelectionForDrag: () -> Unit = {}
) {
    // Build ordered items list
    val orderedItems: List<Any> = when {
        mixedItems.isNotEmpty() -> mixedItems
        else -> buildList {
            groups?.forEach { add(it) }
            folders?.forEach { add(it) }
        }
    }

    val isLargeGrid = viewType == ViewType.GRID_LARGE

    SharedFoldersTab(
        orderedMixedItems = orderedItems,
        isLoading = isLoading,
        viewType = viewType,
        sortOption = sortOption,
        isSelectionMode = isSelectionMode,
        selectedFolderIds = selectedFolderIds,
        selectedGroupIds = selectedGroupIds,
        isGroupCreationMode = showCheckboxes,
        groupCreationSelectedFolderIds = selectedFolderIds,
        groupCreationSelectedGroupIds = selectedGroupIds,
        onFolderClick = onFolderClick,
        onFolderLongClick = onFolderLongClick,
        onGroupClick = onGroupClick,
        onGroupLongClick = onGroupLongClick,
        onReorderFolders = onReorderFolders,
        onReorderDone = onReorderDone,
        onExitSelectionForDrag = onExitSelectionForDrag,
        lazyListState = rememberLazyListState(),
        lazyGridState = lazyGridState,

        colors = LocalImageColors.current,

        isCustomOrder = { it == SortOption.CUSTOM_ORDER },
        supportsListView = false,
        showHeaderRow = true,
        emptyStateMessage = "Photos and videos you take will appear here",
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

        folderListItem = { _, _, _, _, _, _, _ -> }, // Not used in image-library
        groupListItem = { _, _, _, _, _ -> }, // Not used in image-library

        modifier = modifier
    )
}




