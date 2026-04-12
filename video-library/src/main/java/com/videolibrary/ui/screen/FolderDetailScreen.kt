package com.videolibrary.ui.screen

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.ui.screen.SharedFolderDetailScreen
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.SelectionModeHeader
import com.videolibrary.ui.components.VideoGridItem
import com.videolibrary.ui.components.ViewTypeToggleButton
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Video-library FolderDetailScreen.
 * Delegates to [SharedFolderDetailScreen] with video-specific configuration.
 */
@Composable
fun FolderDetailScreen(
    folderName: String,
    videos: List<VideoItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    floatingTopBarEnabled: Boolean = false,
    onBack: () -> Unit,
    onVideoClick: (VideoItem) -> Unit,
    onVideoLongClick: (VideoItem) -> Unit,
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
    scrollToTopTrigger: Int = 0,
) {
    val gridState = rememberLazyGridState()

    // Scroll to top when sort changes in the album
    LaunchedEffect(scrollToTopTrigger) {
        if (scrollToTopTrigger > 0) {
            gridState.scrollToItem(0)
        }
    }

    SharedFolderDetailScreen(
        folderName = folderName,
        items = videos,
        viewType = viewType,
        isSelectionMode = isSelectionMode,
        selectedIds = selectedIds,
        floatingTopBarEnabled = floatingTopBarEnabled,
        getItemId = { it.id },
        onBack = onBack,
        onItemClick = { video, _ -> onVideoClick(video) },  // Video doesn't use index
        onItemLongClick = onVideoLongClick,
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
        lazyGridState = gridState,

        colors = LocalVideoColors.current,

        isLargeGrid = { it == ViewType.GRID_LARGE },
        getColumnCount = { if (it == ViewType.GRID_LARGE) 2 else 3 },
        gridSpacing = 2.dp,
        emptyMessage = "No videos",

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

        itemGridCell = { video, isSelected, isSelMode, isLarge, onClick, onLongClick, mod ->
            VideoGridItem(
                video = video,
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

