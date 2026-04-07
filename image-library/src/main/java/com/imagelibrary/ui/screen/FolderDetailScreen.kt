package com.imagelibrary.ui.screen

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.ui.screen.SharedFolderDetailScreen
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.ImageGridItem
import com.imagelibrary.ui.components.SelectionHeader
import com.imagelibrary.ui.components.ViewTypeToggleButton
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Image-library FolderDetailScreen.
 * Delegates to [SharedFolderDetailScreen] with image-specific configuration.
 */
@Composable
fun FolderDetailScreen(
    folderName: String,
    images: List<ImageItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    onBack: () -> Unit,
    onImageClick: (ImageItem, Int) -> Unit,
    onImageLongClick: (ImageItem) -> Unit,
    onCycleViewType: () -> Unit = {},
    modifier: Modifier = Modifier,
    onCopy: () -> Unit = {},
    onMove: () -> Unit = {},
    onDelete: () -> Unit = {},
    onShare: () -> Unit = {},
    onDetails: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    onEdit: () -> Unit = {},
    onSelectAll: () -> Unit = {},
    onSortBy: () -> Unit = {},
    onViewAs: () -> Unit = {},
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    SharedFolderDetailScreen(
        folderName = folderName,
        items = images,
        viewType = viewType,
        isSelectionMode = isSelectionMode,
        selectedIds = selectedIds,
        getItemId = { it.id },
        onBack = onBack,
        onItemClick = onImageClick,
        onItemLongClick = onImageLongClick,
        onCycleViewType = onCycleViewType,
        onCopy = onCopy,
        onMove = onMove,
        onDelete = onDelete,
        onDetails = onDetails,
        onShare = onShare,
        onOpenLocation = onOpenLocation,
        onEdit = onEdit,
        onSelectAll = onSelectAll,
        onSortBy = onSortBy,
        onViewAs = onViewAs,
        onSettings = onSettings,
        onAbout = onAbout,
        lazyGridState = lazyGridState,

        colors = LocalImageColors.current,

        isLargeGrid = { it == ViewType.GRID_LARGE },
        getColumnCount = { if (it == ViewType.GRID_LARGE) 2 else 3 },
        gridSpacing = 2.dp,
        emptyMessage = "No images",

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

        itemGridCell = { image, isSelected, isSelMode, isLarge, onClick, onLongClick, mod ->
            ImageGridItem(
                image = image,
                isSelected = isSelected,
                isSelectionMode = isSelMode,
                isLargeGrid = isLarge,
                onClick = onClick,
                onLongClick = onLongClick,
                modifier = mod
            )
        },

        modifier = modifier
    )
}

