package com.example.common.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
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
        onLongClick: () -> Unit,
        modifier: Modifier
    ) -> Unit,

    modifier: Modifier = Modifier
) {
    var showMoreMenu by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header (only shown when floating mode is OFF) ──
            if (!floatingTopBarEnabled) {
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
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                        verticalArrangement = Arrangement.spacedBy(gridSpacing)
                    ) {
                    // ── HEADER AS FIRST ITEM (scrolls naturally with content) ──
                    if (!isSelectionMode && floatingTopBarEnabled) {
                        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding()
                                    .padding(horizontal = 6.dp, vertical = 12.dp)
                                    .heightIn(min = 56.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
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
                                        IconButton(onClick = { showMoreMenu = !showMoreMenu }, modifier = Modifier.size(40.dp)) {
                                            Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White, modifier = Modifier.size(22.dp))
                                        }
                                        DropdownMenu(
                                            expanded = showMoreMenu,
                                            onDismissRequest = { showMoreMenu = false },
                                            modifier = Modifier
                                                .background(colors.menuBg, RoundedCornerShape(16.dp))
                                                .widthIn(min = 200.dp)
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

                    // ── FOLDER ITEMS ──
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

                    // ── Floating overlay buttons (when header scrolled away) ──
                    if (floatingTopBarEnabled && !isSelectionMode && lazyGridState.firstVisibleItemIndex > 0) {
                    // Back button (top-left)
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                            .padding(start = 16.dp, top = 12.dp)
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

                    // Menu button (top-right)
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .statusBarsPadding()
                            .padding(end = 16.dp, top = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0x8C000000), RoundedCornerShape(24.dp))
                                .clickable { showMoreMenu = !showMoreMenu }
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
                            modifier = Modifier
                                .background(colors.menuBg, RoundedCornerShape(16.dp))
                                .widthIn(min = 200.dp)
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
