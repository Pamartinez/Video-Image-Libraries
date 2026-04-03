package com.imagelibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ViewType
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.components.BottomActionBar
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.components.SelectionHeader
import com.imagelibrary.ui.components.SortDialog
import com.imagelibrary.ui.components.ViewTypeToggleButton
import com.imagelibrary.ui.theme.LocalImageColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.rememberDragDropGridState
import kotlin.math.roundToInt

/**
 * Screen displayed when the user opens a group.
 * 3-dot menu: Create album, Add folder, Rename group, Hide albums, Destroy group
 * Bottom bar (selection): Group | Share | Move | Open Location | Remove from group
 * Supports drag-to-reorder when sortOption == CUSTOM_ORDER.
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
    // Ordered interleaved list supplied by the ViewModel (preserves custom drag order).
    // When non-empty this takes precedence over the separate folders/subGroups lists.
    orderedMixedItems: List<Any> = emptyList(),
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    /** Hoisted from the caller so scroll position survives album-detail navigations. */
    lazyGridState: LazyGridState = rememberLazyGridState(),
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current
    var showMoreMenu by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    // Scroll-to-top on group navigation and sort changes is handled by the caller
    // (ImageListScreen) via LaunchedEffect(currentGroupId, sortOption).
    // Do NOT put a LaunchedEffect(sortOption) here — it would fire every time this
    // composable re-enters composition (e.g. when returning from FolderDetailScreen),
    // which would reset the scroll position the caller is trying to preserve.

    val totalSelected = selectedFolderIds.size + selectedGroupIds.size
    val totalItems = folders.size + subGroups.size

    // Build the display list.  Prefer the ViewModel-supplied orderedMixedItems (which
    // preserves the user's custom drag order) over rebuilding from the split lists.
    val rawMixed: List<FolderListItem> = if (orderedMixedItems.isNotEmpty()) {
        orderedMixedItems.mapNotNull { item ->
            when (item) {
                is GroupItem  -> MixedItem.Group(item)
                is FolderItem -> MixedItem.Folder(item)
                else          -> null
            }
        }
    } else {
        buildList {
            subGroups.forEach { add(MixedItem.Group(it)) }
            folders.forEach   { add(MixedItem.Folder(it)) }
        }
    }

    val mixedItems: List<FolderListItem> = when (sortOption) {
        SortOption.NAME_A_TO_Z        -> if (groupsAlwaysOnTop) {
            rawMixed.filterIsInstance<MixedItem.Group>().sortedBy { it.sortKey.lowercase() } +
            rawMixed.filterIsInstance<MixedItem.Folder>().sortedBy { it.sortKey.lowercase() }
        } else rawMixed.sortedBy { it.sortKey.lowercase() }
        SortOption.NAME_Z_TO_A        -> if (groupsAlwaysOnTop) {
            rawMixed.filterIsInstance<MixedItem.Group>().sortedByDescending { it.sortKey.lowercase() } +
            rawMixed.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.sortKey.lowercase() }
        } else rawMixed.sortedByDescending { it.sortKey.lowercase() }
        SortOption.ITEMS_MOST_FIRST   -> if (groupsAlwaysOnTop) {
            rawMixed.filterIsInstance<MixedItem.Group>().sortedByDescending { it.itemCount } +
            rawMixed.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.itemCount }
        } else rawMixed.sortedByDescending { it.itemCount }
        SortOption.ITEMS_FEWEST_FIRST -> if (groupsAlwaysOnTop) {
            rawMixed.filterIsInstance<MixedItem.Group>().sortedBy { it.itemCount } +
            rawMixed.filterIsInstance<MixedItem.Folder>().sortedBy { it.itemCount }
        } else rawMixed.sortedBy { it.itemCount }
        SortOption.CUSTOM_ORDER       -> rawMixed
    }

    // Drag is only active in CUSTOM_ORDER mode (same guard as FoldersTab).
    val canDrag = sortOption == SortOption.CUSTOM_ORDER

    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
        onMove = { from, to ->
            if (from >= 0 && to >= 0 && from < mixedItems.size && to < mixedItems.size) {
                onReorderFolders(from, to)
            }
        },
        onDragEnd = onReorderDone,
        onLongPressWithoutDrag = { index ->
            mixedItems.getOrNull(index)?.let { item ->
                when (item) {
                    is MixedItem.Folder -> onFolderLongClick(item.folder)
                    is MixedItem.Group  -> onGroupLongClick(item.group)
                }
            }
        },
        isInSelectionMode = { isSelectionMode },
        onEnterDragMode = {}
    )

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header ──
            ScreenTopBar {
                if (isSelectionMode) {
                    val allSelected = totalItems > 0 && totalSelected == totalItems
                    SelectionHeader(
                        selectedCount = totalSelected,
                        allSelected = allSelected,
                        onSelectAll = onSelectAll,
                        onCancel = onCancelSelection
                    )
                } else {
                    CircularBackButton(onClick = onBack)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = groupName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.listFirstText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        val groupCount = subGroups.size
                        val albumCount = folders.size
                        val subtitleParts = buildList {
                            if (groupCount > 0) add("$groupCount ${if (groupCount == 1) "group" else "groups"}")
                            if (albumCount > 0) add("$albumCount ${if (albumCount == 1) "album" else "albums"}")
                        }
                        if (subtitleParts.isNotEmpty()) {
                            Text(
                                text = subtitleParts.joinToString(" "),
                                fontSize = 13.sp,
                                color = colors.listSecondText
                            )
                        }
                    }
                    ActionsPill {
                        ViewTypeToggleButton(viewType = viewType, onClick = onCycleViewType)
                        AppMoreMenuButton(
                            expanded = showMoreMenu,
                            onExpand = { showMoreMenu = true },
                            onDismiss = { showMoreMenu = false },
                            onSortBy = { showSortDialog = true },
                            onViewAs = onViewAs,
                            onSettings = onSettings,
                            onAbout = onAbout
                        ) { dismiss ->
                            AppMenuItem("Create album",  onDismiss = dismiss, onClick = onCreateAlbum, textColor = colors.listFirstText)
                            AppMenuItem("Add folder",    onDismiss = dismiss, onClick = onAddFolder,   textColor = colors.listFirstText)
                            AppMenuItem("Rename group",  onDismiss = dismiss, onClick = onRenameGroup, textColor = colors.listFirstText)
                            AppMenuItem("Hide albums",   onDismiss = dismiss, onClick = onHideAlbums,  textColor = colors.listFirstText)
                            AppMenuDivider(color = colors.dividerColor)
                            AppMenuItem("Destroy group", onDismiss = dismiss,
                                onClick = onDestroyGroup, textColor = Color(0xFFEF5350))
                        }
                    }
                }
            }

            // ── Content grid (wrapped in Box so the drag overlay can float above the grid) ──
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            val columnCount = if (isLargeGrid) 2 else 3
            val spacing = if (isLargeGrid) 8.dp else 4.dp

            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                if (mixedItems.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "No items in this group", fontSize = 16.sp, color = colors.listSecondText)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onAddFolder,
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Add albums", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        state = lazyGridState,
                        modifier = Modifier
                            .fillMaxSize()
                            .then(if (canDrag) Modifier.dragToReorderGrid(dragDropState) else Modifier),
                        contentPadding = PaddingValues(spacing),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        verticalArrangement = Arrangement.spacedBy(spacing),
                        userScrollEnabled = !(canDrag && dragDropState.isDragging)
                    ) {
                        itemsIndexed(mixedItems, key = { _, item -> item.uniqueKey }) { index, item ->
                            val itemIsDragging = canDrag && dragDropState.draggedIndex == index
                            val anyDragActive  = canDrag && dragDropState.isDragging
                            val dimModifier    = if (anyDragActive && !itemIsDragging)
                                Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                            when (item) {
                                is MixedItem.Folder -> FolderGridItem(
                                    folder = item.folder,
                                    isSelected = selectedFolderIds.contains(item.folder.bucketId),
                                    isSelectionMode = isSelectionMode,
                                    viewType = viewType,
                                    onClick = {
                                        if (!dragDropState.consumeNextClick()) onFolderClick(item.folder)
                                    },
                                    onLongClick = if (canDrag) null else ({ onFolderLongClick(item.folder) }),
                                    isDragging = itemIsDragging,
                                    modifier = Modifier
                                        .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                        .then(dimModifier)
                                )
                                is MixedItem.Group -> GroupGridItem(
                                    group = item.group,
                                    isSelected = selectedGroupIds.contains(item.group.groupId),
                                    isSelectionMode = isSelectionMode,
                                    viewType = viewType,
                                    onClick = {
                                        if (!dragDropState.consumeNextClick()) onGroupClick(item.group)
                                    },
                                    onLongClick = if (canDrag) null else ({ onGroupLongClick(item.group) }),
                                    isDragging = itemIsDragging,
                                    modifier = Modifier
                                        .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                        .then(dimModifier)
                                )
                            }
                        }
                    }

                    // ── Samsung-style floating drag overlay ──────────────────────────────
                    if (canDrag && dragDropState.isDragging) {
                        val overlayPos  = dragDropState.overlayPosition
                        val itemSizePx  = dragDropState.capturedItemSize
                        val draggedItem = mixedItems.getOrNull(dragDropState.draggedIndex)

                        if (draggedItem != null && itemSizePx != null) {
                            val density      = LocalDensity.current
                            val itemWidthDp  = with(density) { itemSizePx.width.toDp() }
                            val itemHeightDp = with(density) { itemSizePx.height.toDp() }
                            val overlayShape = RoundedCornerShape(12.dp)

                            Box(
                                modifier = Modifier
                                    .offset { IntOffset(overlayPos.x.roundToInt(), overlayPos.y.roundToInt()) }
                                    .width(itemWidthDp)
                                    .height(itemHeightDp)
                                    .zIndex(10f)
                                    .graphicsLayer {
                                        scaleX = 1.08f
                                        scaleY = 1.08f
                                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                                        shadowElevation = 24f
                                    }
                                    .border(3.dp, Color(0xFF2196F3), overlayShape)
                            ) {
                                when (draggedItem) {
                                    is MixedItem.Folder -> FolderGridItem(
                                        folder = draggedItem.folder,
                                        isSelected = selectedFolderIds.contains(draggedItem.folder.bucketId),
                                        isSelectionMode = isSelectionMode,
                                        viewType = viewType,
                                        onClick = {},
                                        onLongClick = null,
                                        isDragging = false
                                    )
                                    is MixedItem.Group -> GroupGridItem(
                                        group = draggedItem.group,
                                        isSelected = selectedGroupIds.contains(draggedItem.group.groupId),
                                        isSelectionMode = isSelectionMode,
                                        viewType = viewType,
                                        onClick = {},
                                        onLongClick = null,
                                        isDragging = false
                                    )
                                }
                            }
                        }
                    }
                } // end non-empty branch
            } // end content Box
        } // end Column

        // ── Selection bottom bar ─────────────────────────────────────────────────────
        // Mirrors the root-screen bar: Group | Share | Move | Open Location | Remove
        BottomActionBar(
            visible = isSelectionMode,
            onCopy = {},
            onMove = onMove,
            onDelete = onDelete,
            onDetails = {},
            showAllActions = false,
            showDetails = false,
            showGroup = totalSelected >= 1,
            onGroup = onGroup,
            showMove = totalSelected >= 1,
            showShare = true,
            onShare = onShare,
            showOpenLocation = totalSelected == 1 && selectedGroupIds.isEmpty(),
            onOpenLocation = onOpenLocation,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    if (showSortDialog) {
        SortDialog(
            currentSortOption = sortOption,
            onSortOptionSelected = { onSortOptionSelected(it) },
            onDismiss = { showSortDialog = false }
        )
    }
}

