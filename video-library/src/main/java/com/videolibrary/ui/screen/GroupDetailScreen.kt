package com.videolibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.CircularBackButton
import com.example.common.data.model.FolderItem
import com.videolibrary.data.model.FolderSortOption
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.videolibrary.data.model.ViewType
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.ScreenTopBar
import com.videolibrary.ui.components.*
import com.videolibrary.ui.theme.LocalVideoColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.rememberDragDropGridState
import kotlin.math.roundToInt

/**
 * Stateless screen displaying the contents of a single Group.
 * Shows sub-groups and member folders in a grid, with full selection and
 * drag-to-reorder support (when sortOption == CUSTOM_ORDER).
 */
@OptIn(ExperimentalMaterial3Api::class)
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
    // ── Drag-to-reorder ──────────────────────────────────────────────────
    /** ViewModel-ordered interleaved list (GroupItem / FolderItem). Non-empty = custom drag order. */
    orderedMixedItems: List<Any> = emptyList(),
    /** Called live on every swap; maps to viewModel.reorderGroupItem. */
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    /** Called once when the finger lifts; maps to viewModel.persistGroupOrder. */
    onReorderDone: () -> Unit = {},
    // ── Bottom action bar extras ─────────────────────────────────────────
    onShare: () -> Unit = {},
    onMove: () -> Unit = {},
    onRemoveFromGroup: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    var showMoreMenu   by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    var showCreateMenu by remember { mutableStateOf(false) }

    // ── Build typed mixed list ─────────────────────────────────────────────
    // When orderedMixedItems is non-empty the ViewModel's saved drag order is the
    // authoritative source; the raw groups+folders fallback is only used the very
    // first time a group is opened before any drag has occurred.
    val mixedItems: List<MixedFolderItem> = remember(subGroups, folders, sortOption, orderedMixedItems, groupsAlwaysOnTop) {
        val raw: List<MixedFolderItem> = if (orderedMixedItems.isNotEmpty()) {
            orderedMixedItems.toMixedFolderItems()
        } else {
            subGroups.map { MixedItem.Group(it) } +
            folders.map   { MixedItem.Folder(it) }
        }
        when (sortOption) {
            FolderSortOption.CUSTOM_ORDER       -> raw
            FolderSortOption.NAME_A_TO_Z        -> if (groupsAlwaysOnTop) {
                raw.filterIsInstance<MixedItem.Group>().sortedBy { it.sortKey.lowercase() } +
                raw.filterIsInstance<MixedItem.Folder>().sortedBy { it.sortKey.lowercase() }
            } else raw.sortedBy { it.sortKey.lowercase() }
            FolderSortOption.NAME_Z_TO_A        -> if (groupsAlwaysOnTop) {
                raw.filterIsInstance<MixedItem.Group>().sortedByDescending { it.sortKey.lowercase() } +
                raw.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.sortKey.lowercase() }
            } else raw.sortedByDescending { it.sortKey.lowercase() }
            FolderSortOption.ITEMS_MOST_FIRST   -> if (groupsAlwaysOnTop) {
                raw.filterIsInstance<MixedItem.Group>().sortedByDescending { it.itemCount } +
                raw.filterIsInstance<MixedItem.Folder>().sortedByDescending { it.itemCount }
            } else raw.sortedByDescending { it.itemCount }
            FolderSortOption.ITEMS_FEWEST_FIRST -> if (groupsAlwaysOnTop) {
                raw.filterIsInstance<MixedItem.Group>().sortedBy { it.itemCount } +
                raw.filterIsInstance<MixedItem.Folder>().sortedBy { it.itemCount }
            } else raw.sortedBy { it.itemCount }
        }
    }

    val totalSelected = selectedFolderIds.size + selectedGroupIds.size

    // ── Drag-to-reorder setup (grid view only; no header → no index offset) ──
    // canDrag intentionally does NOT include !isSelectionMode so the pointerInput
    // modifier is never torn down mid-gesture.
    val canDrag    = sortOption == FolderSortOption.CUSTOM_ORDER
    val gridState  = rememberLazyGridState()
    val dragDropState = rememberDragDropGridState(
        lazyGridState          = gridState,
        onMove                 = { from, to -> onReorderFolders(from, to) },
        onDragEnd              = onReorderDone,
        onLongPressWithoutDrag = { index ->
            mixedItems.getOrNull(index)?.let { item ->
                when (item) {
                    is MixedItem.Folder -> onFolderLongClick(item.folder)
                    is MixedItem.Group  -> onGroupLongClick(item.group)
                }
            }
        },
        isInSelectionMode = { isSelectionMode },
        onEnterDragMode   = {}
    )

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(Modifier.fillMaxSize()) {

            // ── Top bar ───────────────────────────────────────────────────
            ScreenTopBar {
                if (isSelectionMode) {
                    SelectionModeHeader(
                        selectedCount = totalSelected,
                        totalCount    = mixedItems.size,
                        onSelectAll   = onSelectAll,
                        onCancel      = onCancelSelection
                    )
                } else {
                    CircularBackButton(onClick = onBack)
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(groupName, fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold, color = colors.listFirstText)
                        val sub = buildString {
                            if (subGroups.isNotEmpty()) append("${subGroups.size} groups  ")
                            append("${folders.size} albums")
                        }
                        Text(sub, fontSize = 13.sp, color = colors.listSecondText)
                    }
                    ActionsPill {
                        IconButton(onClick = { showCreateMenu = true }, modifier = Modifier.size(40.dp)) {
                            Icon(Icons.Default.Add, contentDescription = "Create", tint = colors.iconColor, modifier = Modifier.size(22.dp))
                        }
                        ViewTypeToggleButton(viewType, onCycleViewType)
                        AppMoreMenuButton(
                            expanded  = showMoreMenu,
                            onExpand  = { showMoreMenu = true },
                            onDismiss = { showMoreMenu = false },
                            onSortBy   = { showSortDialog = true },
                            onViewAs   = onViewAs,
                            onSettings = onSettings,
                            onAbout    = onAbout
                        ) { dismiss ->
                            AppMenuItem("Add album(s)",  onDismiss = dismiss, onClick = onAddFolder,   textColor = colors.listFirstText)
                            AppMenuItem("Rename group",  onDismiss = dismiss, onClick = onRenameGroup, textColor = colors.listFirstText)
                            AppMenuItem("Hide album(s)", onDismiss = dismiss, onClick = onHideAlbums,  textColor = colors.listFirstText)
                            AppMenuDivider(color = colors.dividerColor)
                            AppMenuItem("Destroy group", onDismiss = dismiss, onClick = onDestroyGroup, textColor = Color(0xFFEF5350))
                        }
                    }
                }
            }

            // ── Content area (Box provides coordinate space for the drag overlay) ──
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                TabContentScaffold(
                    isLoading           = false,
                    isEmpty             = mixedItems.isEmpty(),
                    viewType            = viewType,
                    modifier            = Modifier.fillMaxSize(),
                    crossfadeLabel      = "groupDetailViewType",
                    gridTransitionLabel = "groupDetailGrid",
                    emptyContent = {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("No items in this group",
                                    fontSize = 16.sp, color = colors.listSecondText)
                                Spacer(Modifier.height(12.dp))
                                Button(onClick = onAddFolder) {
                                    Icon(Icons.Default.Add, null, modifier = Modifier.size(18.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text("Add albums")
                                }
                            }
                        }
                    },
                    listContent = {
                        LazyColumn(
                            modifier       = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 4.dp)
                        ) {
                            items(mixedItems, key = { it.uniqueKey }) { item ->
                                when (item) {
                                    is MixedItem.Folder -> FolderListItem(
                                        folder          = item.folder,
                                        isSelected      = selectedFolderIds.contains(item.folder.bucketId),
                                        isSelectionMode = isSelectionMode,
                                        onClick         = { onFolderClick(item.folder) },
                                        onLongClick     = { onFolderLongClick(item.folder) }
                                    )
                                    is MixedItem.Group -> GroupListItem(
                                        group           = item.group,
                                        isSelected      = selectedGroupIds.contains(item.group.groupId),
                                        isSelectionMode = isSelectionMode,
                                        onClick         = { onGroupClick(item.group) },
                                        onLongClick     = { onGroupLongClick(item.group) }
                                    )
                                }
                            }
                        }
                    },
                    gridContent = { _, spacing, columnCount ->
                        LazyVerticalGrid(
                            state                 = gridState,
                            columns               = GridCells.Fixed(columnCount),
                            // Samsung Gallery uses 10dp edge padding (album_grid_view_side_gap)
                            contentPadding        = PaddingValues(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(spacing),
                            verticalArrangement   = Arrangement.spacedBy(spacing),
                            modifier              = Modifier
                                .fillMaxSize()
                                .then(if (canDrag) Modifier.dragToReorderGrid(dragDropState) else Modifier),
                            userScrollEnabled     = !(canDrag && dragDropState.isDragging)
                        ) {
                                itemsIndexed(mixedItems, key = { _, item -> item.uniqueKey }) { index, item ->
                                    val itemIsDragging = canDrag && dragDropState.draggedIndex == index
                                    val anyDragActive  = canDrag && dragDropState.isDragging
                                    val dimModifier    = if (anyDragActive && !itemIsDragging)
                                        Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                                    when (item) {
                                        is MixedItem.Folder -> FolderGridItem(
                                            folder          = item.folder,
                                            isSelected      = selectedFolderIds.contains(item.folder.bucketId),
                                            isSelectionMode = isSelectionMode,
                                            viewType        = viewType,
                                            isDragging      = itemIsDragging,
                                            onClick = {
                                                if (!dragDropState.consumeNextClick()) onFolderClick(item.folder)
                                            },
                                            onLongClick = if (canDrag) null else ({ onFolderLongClick(item.folder) }),
                                            modifier    = Modifier
                                                .animateItem(placementSpec = spring(Spring.DampingRatioNoBouncy, 4000f))
                                                .then(dimModifier)
                                        )
                                        is MixedItem.Group -> GroupGridItem(
                                            group           = item.group,
                                            isSelected      = selectedGroupIds.contains(item.group.groupId),
                                            isSelectionMode = isSelectionMode,
                                            viewType        = viewType,
                                            isDragging      = itemIsDragging,
                                            onClick = {
                                                if (!dragDropState.consumeNextClick()) onGroupClick(item.group)
                                            },
                                            onLongClick = if (canDrag) null else ({ onGroupLongClick(item.group) }),
                                            modifier    = Modifier
                                                .animateItem(placementSpec = spring(Spring.DampingRatioNoBouncy, 4000f))
                                                .then(dimModifier)
                                        )
                                    }
                                }
                        } // end LazyVerticalGrid
                    } // end gridContent
                ) // end TabContentScaffold

                // ── Floating drag overlay ─────────────────────────────────────
                // Positioned absolutely within the Box(weight(1f)), always above the grid.
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
                                    scaleX          = 1.08f
                                    scaleY          = 1.08f
                                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                                    shadowElevation = 24f
                                }
                                .border(3.dp, Color(0xFF2196F3), overlayShape)
                        ) {
                            when (draggedItem) {
                                is MixedItem.Folder -> FolderGridItem(
                                    folder          = draggedItem.folder,
                                    isSelected      = false,
                                    isSelectionMode = false,
                                    viewType        = viewType,
                                    isDragging      = false,
                                    onClick         = {},
                                    onLongClick     = null
                                )
                                is MixedItem.Group -> GroupGridItem(
                                    group           = draggedItem.group,
                                    isSelected      = false,
                                    isSelectionMode = false,
                                    viewType        = viewType,
                                    isDragging      = false,
                                    onClick         = {},
                                    onLongClick     = null
                                )
                            }
                        }
                    }
                }
            } // end content Box
        } // end Column

        // ── Bottom action bar ─────────────────────────────────────────────
        BottomActionBar(
            visible          = isSelectionMode,
            onCopy           = {},
            onMove           = onMove,
            onDelete         = onDelete,
            onDetails        = {},
            showAllActions   = false,
            showDetails      = false,
            showGroup        = totalSelected >= 1,
            onGroup          = onGroup,
            showMove         = totalSelected >= 1,
            showShare        = true,
            onShare          = onShare,
            showOpenLocation = totalSelected == 1 && selectedGroupIds.isEmpty(),
            onOpenLocation   = onOpenLocation,
            modifier         = Modifier.align(Alignment.BottomCenter)
        )
    }

    if (showSortDialog) {
        FolderSortDialog(
            currentSortOption    = sortOption,
            onSortOptionSelected = { onSortOptionSelected(it); showSortDialog = false },
            onDismiss            = { showSortDialog = false }
        )
    }

    // ── "Choose what to create" bottom sheet (same as root screen) ──
    if (showCreateMenu) {
        ModalBottomSheet(
            onDismissRequest = { showCreateMenu = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = colors.menuBg,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            dragHandle = null
        ) {
            Column(modifier = Modifier.navigationBarsPadding().padding(bottom = 8.dp)) {
                Text(
                    text = "Choose what to create",
                    modifier = Modifier.padding(start = 24.dp, top = 28.dp, bottom = 12.dp, end = 24.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colors.listFirstText
                )
                // ── Album ──
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCreateMenu = false; onCreateAlbum() }
                        .padding(horizontal = 24.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(Color(0xFF3A3A3C), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Collections, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text("Album", color = colors.listFirstText, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(
                            "Create a new album and add videos manually.",
                            color = colors.listSecondText, fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
                // ── Group ──
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCreateMenu = false; onGroup() }
                        .padding(horizontal = 24.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(Color(0xFF3A3A3C), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Folder, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text("Group", color = colors.listFirstText, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(
                            "Create a group of related albums.",
                            color = colors.listSecondText, fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}


