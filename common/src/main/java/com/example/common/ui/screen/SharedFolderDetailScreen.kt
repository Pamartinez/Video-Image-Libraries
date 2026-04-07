package com.example.common.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.theme.LibraryColors

/**
 * Shared FolderDetailScreen used by both image-library and video-library.
 * Displays media items from a single folder with selection support.
 */
@Composable
fun <MediaItem, ViewTypeEnum> SharedFolderDetailScreen(
    folderName: String,
    items: List<MediaItem>,
    viewType: ViewTypeEnum,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    getItemId: (MediaItem) -> Long,
    onBack: () -> Unit,
    onItemClick: (MediaItem, Int) -> Unit,
    onItemLongClick: (MediaItem) -> Unit,
    onCycleViewType: () -> Unit,
    onCopy: () -> Unit,
    onMove: () -> Unit,
    onDelete: () -> Unit,
    onDetails: () -> Unit,
    onShare: () -> Unit,
    onOpenLocation: () -> Unit,
    onEdit: () -> Unit,
    onSelectAll: () -> Unit,
    onSortBy: () -> Unit,
    onViewAs: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    lazyGridState: LazyGridState,

    // Injected dependencies
    colors: LibraryColors,

    // Configuration
    isLargeGrid: (ViewTypeEnum) -> Boolean,
    getColumnCount: (ViewTypeEnum) -> Int,
    gridSpacing: Dp,
    emptyMessage: String,

    // Component slots
    selectionHeader: @Composable RowScope.(
        selectedCount: Int,
        totalCount: Int,
        allSelected: Boolean,
        onSelectAll: () -> Unit,
        onCancel: () -> Unit
    ) -> Unit,

    viewTypeToggle: @Composable (
        viewType: ViewTypeEnum,
        onClick: () -> Unit
    ) -> Unit,

    itemGridCell: @Composable (
        item: MediaItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        isLargeGrid: Boolean,
        onClick: () -> Unit,
        onLongClick: () -> Unit,
        modifier: Modifier
    ) -> Unit,

    modifier: Modifier = Modifier
) {
    var showMoreMenu by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header ──
            ScreenTopBar {
                if (isSelectionMode) {
                    val allSelected = items.isNotEmpty() && selectedIds.size == items.size
                    selectionHeader(selectedIds.size, items.size, allSelected, onSelectAll, onBack)
                } else {
                    CircularBackButton(onClick = onBack)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = folderName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.listFirstText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    ActionsPill {
                        viewTypeToggle(viewType, onCycleViewType)
                        AppMoreMenuButton(
                            expanded = showMoreMenu,
                            onExpand = { showMoreMenu = true },
                            onDismiss = { showMoreMenu = false },
                            onSortBy = onSortBy,
                            onViewAs = onViewAs,
                            onSettings = onSettings,
                            onAbout = onAbout
                        ) { dismiss ->
                            AppMenuItem("Select", onDismiss = dismiss, onClick = onEdit, textColor = colors.listFirstText)
                        }
                    }
                }
            }

            // ── Content grid ──
            val isLarge = isLargeGrid(viewType)
            val columnCount = getColumnCount(viewType)

            if (items.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = emptyMessage, fontSize = 16.sp, color = colors.listSecondText)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnCount),
                    state = lazyGridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(0.dp),
                    horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                    verticalArrangement = Arrangement.spacedBy(gridSpacing)
                ) {
                    items(items, key = { getItemId(it) }) { item ->
                        val index = items.indexOf(item)
                        itemGridCell(
                            item,
                            selectedIds.contains(getItemId(item)),
                            isSelectionMode,
                            isLarge,
                            {
                                if (isSelectionMode) onItemLongClick(item)
                                else onItemClick(item, index)
                            },
                            { onItemLongClick(item) },
                            Modifier.animateItem(
                                placementSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = 4000f
                                )
                            )
                        )
                    }
                }
            }
        }

        // ── Bottom action bar ──
        BottomActionBar(
            visible = isSelectionMode,
            onCopy = onCopy,
            onMove = onMove,
            onDelete = onDelete,
            onDetails = onDetails,
            showAllActions = true,
            showDetails = selectedIds.size == 1,
            showShare = selectedIds.isNotEmpty(),
            onShare = onShare,
            onOpenLocation = onOpenLocation,
            showOpenLocation = selectedIds.size == 1,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

