package com.example.common.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.FastScrollerForGrid
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.theme.LibraryColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.rememberDragDropGridState
import kotlin.math.roundToInt

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

    // Drag-and-drop support
    allowMediaReordering: Boolean,
    onReorderItem: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    isCustomSortMode: Boolean,

    // Injected dependencies
    colors: LibraryColors,
    floatingTopBarEnabled: Boolean,

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
        onLongClick: (() -> Unit)?,
        modifier: Modifier
    ) -> Unit,

    modifier: Modifier = Modifier
) {
    var showMoreMenu by remember { mutableStateOf(false) }

    // ── Drag-and-drop state ──
    val hasHeaderRow = floatingTopBarEnabled && !isSelectionMode
    val canDrag = allowMediaReordering && isCustomSortMode && !isSelectionMode

    // Critical debug logging
    android.util.Log.e("DragReorder", "═══ SharedFolderDetailScreen Render ═══")
    android.util.Log.e("DragReorder", "allowMediaReordering = $allowMediaReordering")
    android.util.Log.e("DragReorder", "isCustomSortMode = $isCustomSortMode")
    android.util.Log.e("DragReorder", "isSelectionMode = $isSelectionMode")
    android.util.Log.e("DragReorder", "canDrag = $canDrag")
    android.util.Log.e("DragReorder", "items.size = ${items.size}")
    android.util.Log.e("DragReorder", "═══════════════════════════════════════")

    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
        onMove = { from, to ->
            // Convert layout indices to data indices (account for optional header row)
            val dataFrom = if (hasHeaderRow) from - 1 else from
            val dataTo = if (hasHeaderRow) to - 1 else to
            if (dataFrom >= 0 && dataTo >= 0 && dataFrom < items.size && dataTo < items.size) {
                onReorderItem(dataFrom, dataTo)
            }
        },
        onDragEnd = onReorderDone,
        // CRITICAL FIX: Only pass onLongPressWithoutDrag when drag is DISABLED
        // If we pass it when canDrag=true, it enters selection mode which blocks dragging!
        onLongPressWithoutDrag = if (canDrag) null else { layoutIndex ->
            // Convert layout index to data index (account for optional header row)
            val dataIndex = if (hasHeaderRow) layoutIndex - 1 else layoutIndex
            items.getOrNull(dataIndex)?.let { item ->
                onItemLongClick(item)
            }
        },
        isInSelectionMode = { isSelectionMode },
        onEnterDragMode = {},
        minDragIndex = if (hasHeaderRow) 1 else 0
    )

    // ── Calculate scroll state for visibility control ──
    val scrollOffset = if (floatingTopBarEnabled && !isSelectionMode) {
        lazyGridState.firstVisibleItemScrollOffset
    } else 0

    // Simple toggle: invisible when scrolling, visible when at top
    val isScrolled = scrollOffset > 0
    val showInline = !isScrolled
    val showFloating = isScrolled

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header (shown when floating mode is OFF OR in selection mode) ──
            if (!floatingTopBarEnabled || isSelectionMode) {
                ScreenTopBar {
                    if (isSelectionMode) {
                        val allSelected = items.isNotEmpty() && selectedIds.size == items.size
                        selectionHeader(selectedIds.size, items.size, allSelected, onSelectAll, onBack)
                    } else {
                        // Regular back button (no circular background - ScreenTopBar already has dark bg)
                        IconButton(onClick = onBack, modifier = Modifier.size(44.dp)) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = colors.iconColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }
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
            }

            // ── Content grid ──
            val isLarge = isLargeGrid(viewType)
            val columnCount = getColumnCount(viewType)

            if (items.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = emptyMessage, fontSize = 16.sp, color = colors.listSecondText)
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        state = lazyGridState,
                        modifier = Modifier
                            .fillMaxSize()
                            .then(if (canDrag) Modifier.dragToReorderGrid(dragDropState) else Modifier),
                        contentPadding = PaddingValues(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                        verticalArrangement = Arrangement.spacedBy(gridSpacing),
                        userScrollEnabled = !dragDropState.isDragging
                    ) {
                    // ── HEADER AS FIRST ITEM (scrolls naturally with content) ──
                    if (!isSelectionMode && floatingTopBarEnabled) {
                        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding()
                                    .padding(horizontal = 16.dp, vertical = 12.dp)
                                    .heightIn(min = 56.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Only show inline header content when not scrolled
                                if (showInline) {
                                    // Circular back button
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(Color(0x8C000000), RoundedCornerShape(24.dp))
                                            .clickable(onClick = onBack),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back",
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }

                                    Spacer(Modifier.width(12.dp))

                                    // Title
                                    Text(
                                        text = folderName,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f)
                                    )

                                    // ActionsPill with view type + menu
                                    ActionsPill {
                                        viewTypeToggle(viewType, onCycleViewType)
                                        Box {
                                            IconButton(
                                                onClick = { showMoreMenu = !showMoreMenu },
                                                modifier = Modifier.size(48.dp)
                                            ) {
                                                Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White, modifier = Modifier.size(24.dp))
                                            }
                                            DropdownMenu(
                                                expanded = showMoreMenu,
                                                onDismissRequest = { showMoreMenu = false },
                                                shape = RoundedCornerShape(16.dp),
                                                containerColor = colors.menuBg
                                            ) {
                                                AppMenuItem("Sort", onDismiss = { showMoreMenu = false }, onClick = onSortBy, textColor = colors.listFirstText)
                                                AppMenuItem("View as", onDismiss = { showMoreMenu = false }, onClick = onViewAs, textColor = colors.listFirstText)
                                                AppMenuItem("Settings", onDismiss = { showMoreMenu = false }, onClick = onSettings, textColor = colors.listFirstText)
                                                AppMenuItem("About App", onDismiss = { showMoreMenu = false }, onClick = onAbout, textColor = colors.listFirstText)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // ── FOLDER ITEMS ──
                    items(items, key = { getItemId(it) }) { item ->
                        val index = items.indexOf(item)
                        val layoutIndex = if (hasHeaderRow) index + 1 else index
                        val itemIsDragging = canDrag && dragDropState.draggedIndex == layoutIndex
                        val anyDragActive = canDrag && dragDropState.isDragging
                        val dimModifier = if (anyDragActive && !itemIsDragging)
                            Modifier.graphicsLayer { alpha = 0.65f }
                        else if (itemIsDragging)
                            Modifier.graphicsLayer { alpha = 0f }
                        else
                            Modifier

                        itemGridCell(
                            item,
                            selectedIds.contains(getItemId(item)),
                            isSelectionMode,
                            isLarge,
                            {
                                if (dragDropState.consumeNextClick()) {
                                    // Suppress click after drag ends
                                } else if (isSelectionMode) {
                                    onItemLongClick(item)
                                } else {
                                    onItemClick(item, index)
                                }
                            },
                            if (canDrag) {
                                // Drag handler will manage long-press; grid item should not handle it
                                null
                            } else {
                                { onItemLongClick(item) }
                            },
                            Modifier
                                .animateItem(
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = 4000f
                                    )
                                )
                                .then(dimModifier)
                        )
                    }
                }

                    // ── Fast scroller overlay ──
                    FastScrollerForGrid(
                        state = lazyGridState,
                        itemCount = items.size + if (hasHeaderRow) 1 else 0,
                        blockedByOtherGesture = canDrag && dragDropState.isDragging,
                        sectionLabel = { index ->
                            when {
                                hasHeaderRow && index == 0 -> folderName
                                else -> {
                                    val dataIndex = if (hasHeaderRow) index - 1 else index
                                    val clamped = dataIndex.coerceIn(0, (items.size - 1).coerceAtLeast(0))
                                    "${clamped + 1}/${items.size}"
                                }
                            }
                        }
                    )

                    // ── Floating drag overlay ──
                    if (canDrag && dragDropState.isDragging) {
                        val overlayPos = dragDropState.overlayPosition
                        val itemSizePx = dragDropState.capturedItemSize
                        val draggedIndex = if (hasHeaderRow) dragDropState.draggedIndex - 1 else dragDropState.draggedIndex
                        val draggedItem = items.getOrNull(draggedIndex)

                        if (draggedItem != null && itemSizePx != null) {
                            val density = androidx.compose.ui.platform.LocalDensity.current
                            val itemWidthDp = with(density) { itemSizePx.width.toDp() }
                            val itemHeightDp = with(density) { itemSizePx.height.toDp() }

                            Box(
                                modifier = Modifier
                                    .offset { androidx.compose.ui.unit.IntOffset(overlayPos.x.roundToInt(), overlayPos.y.roundToInt()) }
                                    .width(itemWidthDp)
                                    .height(itemHeightDp)
                                    .zIndex(10f)
                                    .graphicsLayer {
                                        scaleX = 1.08f
                                        scaleY = 1.08f
                                        transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.5f, 0.5f)
                                        shadowElevation = 24f
                                    }
                                    .border(3.dp, Color(0xFF2196F3), RoundedCornerShape(12.dp))
                            ) {
                                itemGridCell(
                                    draggedItem,
                                    selectedIds.contains(getItemId(draggedItem)),
                                    isSelectionMode,
                                    isLarge,
                                    {},
                                    null,
                                    Modifier
                                )
                            }
                        }
                    }

                    // ── Floating overlay buttons (shown when scrolled) ──
        if (floatingTopBarEnabled && !isSelectionMode && showFloating) {
                    // Back button (top-left) - aligned with inline header position
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                            .padding(start = 16.dp, top = 16.dp)
                            .size(48.dp)
                            .background(Color(0x8C000000), RoundedCornerShape(24.dp))
                            .clickable(onClick = onBack)
                            .zIndex(20f),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Menu button (top-right) - aligned with inline header position (compensating for ActionsPill padding)
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .statusBarsPadding()
                            .padding(end = 24.dp, top = 16.dp)  // 24dp = 16dp Row padding + 8dp ActionsPill padding
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0x8C000000), RoundedCornerShape(24.dp))
                                .clickable(onClick = { showMoreMenu = !showMoreMenu })
                                .zIndex(20f),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showMoreMenu,
                            onDismissRequest = { showMoreMenu = false },
                            shape = RoundedCornerShape(16.dp),
                            containerColor = colors.menuBg
                        ) {
                            AppMenuItem("Sort", onDismiss = { showMoreMenu = false }, onClick = onSortBy, textColor = colors.listFirstText)
                            AppMenuItem("View as", onDismiss = { showMoreMenu = false }, onClick = onViewAs, textColor = colors.listFirstText)
                            AppMenuItem("Settings", onDismiss = { showMoreMenu = false }, onClick = onSettings, textColor = colors.listFirstText)
                            AppMenuItem("About App", onDismiss = { showMoreMenu = false }, onClick = onAbout, textColor = colors.listFirstText)
                        }
                    }
                }
                }
            }
        }

        // ── Bottom action bar ──
        BottomActionBar(
            visible = isSelectionMode,
            selectedCount = selectedIds.size,
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
