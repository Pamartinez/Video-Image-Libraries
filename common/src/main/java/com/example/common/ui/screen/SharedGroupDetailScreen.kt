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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.theme.LibraryColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.rememberDragDropGridState
import kotlin.math.roundToInt

/**
 * Shared GroupDetailScreen used by both image-library and video-library.
 * Displays contents of a single Group with sub-groups and member folders.
 * Supports selection mode and drag-to-reorder.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <ViewTypeEnum, SortOptionEnum> SharedGroupDetailScreen(
    groupName: String,
    folders: List<FolderItem>,
    subGroups: List<GroupItem>,
    viewType: ViewTypeEnum,
    sortOption: SortOptionEnum,
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
    onHideAlbums: () -> Unit,
    onDestroyGroup: () -> Unit,
    onSortOptionSelected: (SortOptionEnum) -> Unit,
    onDelete: () -> Unit,
    onGroup: () -> Unit,
    onSelectAll: () -> Unit,
    onCancelSelection: () -> Unit,
    onCreateAlbum: () -> Unit,
    onViewAs: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onShare: () -> Unit,
    onMove: () -> Unit,
    onOpenLocation: () -> Unit,
    groupsAlwaysOnTop: Boolean,
    floatingTopBarEnabled: Boolean,
    orderedMixedItems: List<Any>,
    onReorderFolders: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    lazyGridState: LazyGridState,

    // Injected dependencies
    colors: LibraryColors,

    // Configuration
    albumCreationDescription: String,
    isLargeGrid: (ViewTypeEnum) -> Boolean,
    getColumnCount: (ViewTypeEnum) -> Int,
    getSpacing: (ViewTypeEnum) -> Dp,
    isCustomOrder: (SortOptionEnum) -> Boolean,
    sortMixedItems: (List<MixedItem>, SortOptionEnum, Boolean) -> List<MixedItem>,

    // Component slots
    folderGridItem: @Composable (
        folder: FolderItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        viewType: ViewTypeEnum,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        modifier: Modifier
    ) -> Unit,

    groupGridItem: @Composable (
        group: GroupItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        viewType: ViewTypeEnum,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        modifier: Modifier
    ) -> Unit,

    sortDialog: @Composable (
        currentSortOption: SortOptionEnum,
        onSortOptionSelected: (SortOptionEnum) -> Unit,
        onDismiss: () -> Unit
    ) -> Unit,

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

    modifier: Modifier = Modifier
) {
    var showMoreMenu by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }
    var showCreateMenu by remember { mutableStateOf(false) }

    // ── Calculate scroll state for visibility control ──
    val scrollOffset = if (floatingTopBarEnabled && !isSelectionMode) {
        lazyGridState.firstVisibleItemScrollOffset
    } else 0

    // Simple toggle: invisible when scrolling, visible when at top
    val isScrolled = scrollOffset > 0
    val showInline = !isScrolled
    val showFloating = isScrolled

    val totalSelected = selectedFolderIds.size + selectedGroupIds.size
    val totalItems = folders.size + subGroups.size

    // Build the display list
    val rawMixed: List<MixedItem> = if (orderedMixedItems.isNotEmpty()) {
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
            folders.forEach { add(MixedItem.Folder(it)) }
        }
    }

    val mixedItems: List<MixedItem> = sortMixedItems(rawMixed, sortOption, groupsAlwaysOnTop)

    // Drag-to-reorder setup
    val canDrag = isCustomOrder(sortOption)
    // Check if there's a header row in the grid (when floating mode is on and not in selection mode)
    val hasHeaderRow = floatingTopBarEnabled && !isSelectionMode
    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
        onMove = { from, to ->
            // Convert layout indices to data indices (account for optional header row)
            val dataFrom = if (hasHeaderRow) from - 1 else from
            val dataTo = if (hasHeaderRow) to - 1 else to
            if (dataFrom >= 0 && dataTo >= 0 && dataFrom < mixedItems.size && dataTo < mixedItems.size) {
                onReorderFolders(dataFrom, dataTo)
            }
        },
        onDragEnd = onReorderDone,
        onLongPressWithoutDrag = { layoutIndex ->
            // Convert layout index to data index (account for optional header row)
            val dataIndex = if (hasHeaderRow) layoutIndex - 1 else layoutIndex
            mixedItems.getOrNull(dataIndex)?.let { item ->
                when (item) {
                    is MixedItem.Folder -> onFolderLongClick(item.folder)
                    is MixedItem.Group  -> onGroupLongClick(item.group)
                }
            }
        },
        isInSelectionMode = { isSelectionMode },
        onEnterDragMode = {},
        minDragIndex = if (hasHeaderRow) 1 else 0
    )

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(Modifier.fillMaxSize()) {
            // ── Header (shown when floating mode is OFF OR in selection mode) ──
            if (!floatingTopBarEnabled || isSelectionMode) {
                ScreenTopBar {
                    if (isSelectionMode) {
                        val allSelected = totalItems > 0 && totalSelected == totalItems
                        selectionHeader(totalSelected, totalItems, allSelected, onSelectAll, onCancelSelection)
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
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
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
                            IconButton(onClick = { showCreateMenu = true }, modifier = Modifier.size(40.dp)) {
                                Icon(Icons.Default.Add, contentDescription = "Create", tint = colors.iconColor, modifier = Modifier.size(22.dp))
                            }
                            viewTypeToggle(viewType, onCycleViewType)
                            AppMoreMenuButton(
                                expanded = showMoreMenu,
                                onExpand = { showMoreMenu = true },
                                onDismiss = { showMoreMenu = false },
                                onSortBy = { showSortDialog = true },
                                onViewAs = onViewAs,
                                onSettings = onSettings,
                                onAbout = onAbout
                            ) { dismiss ->
                                AppMenuItem("Add album(s)", onDismiss = dismiss, onClick = onAddFolder, textColor = colors.listFirstText)
                                AppMenuItem("Rename group", onDismiss = dismiss, onClick = onRenameGroup, textColor = colors.listFirstText)
                                AppMenuItem("Hide album(s)", onDismiss = dismiss, onClick = onHideAlbums, textColor = colors.listFirstText)
                                AppMenuDivider(color = colors.dividerColor)
                                AppMenuItem("Destroy group", onDismiss = dismiss, onClick = onDestroyGroup, textColor = Color(0xFFEF5350))
                            }
                        }
                    }
                }
            }

            // ── Content grid ──
            val isLarge = isLargeGrid(viewType)
            val columnCount = getColumnCount(viewType)
            val spacing = getSpacing(viewType)

            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                if (mixedItems.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "No items in this group", fontSize = 16.sp, color = colors.listSecondText)
                            Spacer(Modifier.height(16.dp))
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
                        contentPadding = PaddingValues(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        verticalArrangement = Arrangement.spacedBy(spacing),
                        userScrollEnabled = !(canDrag && dragDropState.isDragging)
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

                                        // Title and subtitle
                                        Column(Modifier.weight(1f)) {
                                            Text(
                                                text = groupName,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.White,
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
                                                    color = Color(0xFFBBBBBB)
                                                )
                                            }
                                        }

                                        // ActionsPill with 3 buttons
                                        ActionsPill {
                                            IconButton(onClick = { showCreateMenu = true }, modifier = Modifier.size(48.dp)) {
                                                Icon(Icons.Default.Add, contentDescription = "Create", tint = Color.White, modifier = Modifier.size(24.dp))
                                            }
                                            viewTypeToggle(viewType, onCycleViewType)
                                            Box {
                                                IconButton(onClick = { showMoreMenu = !showMoreMenu }, modifier = Modifier.size(48.dp)) {
                                                    Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White, modifier = Modifier.size(24.dp))
                                                }
                                                DropdownMenu(
                                                    expanded = showMoreMenu,
                                                    onDismissRequest = { showMoreMenu = false },
                                                    modifier = Modifier
                                                        .background(colors.menuBg, RoundedCornerShape(16.dp))
                                                        .widthIn(min = 200.dp)
                                                ) {
                                                    AppMenuItem("Add album(s)", onDismiss = { showMoreMenu = false }, onClick = onAddFolder, textColor = colors.listFirstText)
                                                    AppMenuItem("Rename group", onDismiss = { showMoreMenu = false }, onClick = onRenameGroup, textColor = colors.listFirstText)
                                                    AppMenuItem("Hide album(s)", onDismiss = { showMoreMenu = false }, onClick = onHideAlbums, textColor = colors.listFirstText)
                                                    AppMenuDivider(color = colors.dividerColor)
                                                    AppMenuItem("Destroy group", onDismiss = { showMoreMenu = false }, onClick = onDestroyGroup, textColor = Color(0xFFEF5350))
                                                    AppMenuDivider(color = colors.dividerColor)
                                                    AppMenuItem("Sort", onDismiss = { showMoreMenu = false }, onClick = { showSortDialog = true }, textColor = colors.listFirstText)
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

                        // ── ALBUM AND GROUP ITEMS ──
                        itemsIndexed(mixedItems, key = { _, item -> item.uniqueKey }) { dataIndex, item ->
                            // Convert data index to layout index for comparison with dragDropState
                            val layoutIndex = if (hasHeaderRow) dataIndex + 1 else dataIndex
                            val itemIsDragging = canDrag && dragDropState.draggedIndex == layoutIndex
                            val anyDragActive = canDrag && dragDropState.isDragging
                            val dimModifier = if (anyDragActive && !itemIsDragging)
                                Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                            when (item) {
                                is MixedItem.Folder -> folderGridItem(
                                    item.folder,
                                    selectedFolderIds.contains(item.folder.bucketId),
                                    isSelectionMode,
                                    viewType,
                                    { if (!dragDropState.consumeNextClick()) onFolderClick(item.folder) },
                                    if (canDrag) null else ({ onFolderLongClick(item.folder) }),
                                    itemIsDragging,
                                    Modifier
                                        .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                        .then(dimModifier)
                                )
                                is MixedItem.Group -> groupGridItem(
                                    item.group,
                                    selectedGroupIds.contains(item.group.groupId),
                                    isSelectionMode,
                                    viewType,
                                    { if (!dragDropState.consumeNextClick()) onGroupClick(item.group) },
                                    if (canDrag) null else ({ onGroupLongClick(item.group) }),
                                    itemIsDragging,
                                    Modifier
                                        .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                        .then(dimModifier)
                                )
                            }
                        }
                    }

                    // ── Floating drag overlay ──
                    if (canDrag && dragDropState.isDragging) {
                        val overlayPos = dragDropState.overlayPosition
                        val itemSizePx = dragDropState.capturedItemSize
                        // Convert layout index to data index to get the correct item
                        val draggedDataIndex = if (hasHeaderRow) dragDropState.draggedIndex - 1 else dragDropState.draggedIndex
                        val draggedItem = mixedItems.getOrNull(draggedDataIndex)

                        if (draggedItem != null && itemSizePx != null) {
                            val density = LocalDensity.current
                            val itemWidthDp = with(density) { itemSizePx.width.toDp() }
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
                                    is MixedItem.Folder -> folderGridItem(
                                        draggedItem.folder,
                                        selectedFolderIds.contains(draggedItem.folder.bucketId),
                                        isSelectionMode,
                                        viewType,
                                        {},
                                        null,
                                        false,
                                        Modifier
                                    )
                                    is MixedItem.Group -> groupGridItem(
                                        draggedItem.group,
                                        selectedGroupIds.contains(draggedItem.group.groupId),
                                        isSelectionMode,
                                        viewType,
                                        {},
                                        null,
                                        false,
                                        Modifier
                                    )
                                }
                            }
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
                            AppMenuItem("Add album(s)", onDismiss = { showMoreMenu = false }, onClick = onAddFolder, textColor = colors.listFirstText)
                            AppMenuItem("Rename group", onDismiss = { showMoreMenu = false }, onClick = onRenameGroup, textColor = colors.listFirstText)
                            AppMenuItem("Hide album(s)", onDismiss = { showMoreMenu = false }, onClick = onHideAlbums, textColor = colors.listFirstText)
                            AppMenuDivider(color = colors.dividerColor)
                            AppMenuItem("Destroy group", onDismiss = { showMoreMenu = false }, onClick = onDestroyGroup, textColor = Color(0xFFEF5350))
                            AppMenuDivider(color = colors.dividerColor)
                            AppMenuItem("Sort", onDismiss = { showMoreMenu = false }, onClick = { showSortDialog = true }, textColor = colors.listFirstText)
                            AppMenuItem("View as", onDismiss = { showMoreMenu = false }, onClick = onViewAs, textColor = colors.listFirstText)
                            AppMenuItem("Settings", onDismiss = { showMoreMenu = false }, onClick = onSettings, textColor = colors.listFirstText)
                            AppMenuItem("About App", onDismiss = { showMoreMenu = false }, onClick = onAbout, textColor = colors.listFirstText)
                        }
                    }
                }
            }
        }

        // ── Bottom action bar ──
        BottomActionBar(
            visible = isSelectionMode,
            selectedCount = totalSelected,
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
        sortDialog(sortOption, onSortOptionSelected, { showSortDialog = false })
    }

    // ── "Choose what to create" bottom sheet ──
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
                            albumCreationDescription,
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
