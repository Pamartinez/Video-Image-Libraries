package com.videolibrary.ui.screen
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.videolibrary.data.model.FolderSortOption
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.FolderListItem
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.GroupListItem
import com.videolibrary.ui.components.MixedFolderItem
import com.videolibrary.ui.components.toMixedFolderItems
import com.videolibrary.ui.theme.LocalVideoColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.dragToReorderList
import com.example.common.ui.util.rememberDragDropGridState
import com.example.common.ui.util.rememberDragDropListState
import kotlin.math.roundToInt

@Composable
fun FoldersTab(
    folders: List<FolderItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Int>,
    isLoading: Boolean,
    sortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER,
    orderedMixedItems: List<Any> = emptyList(),
    selectedGroupIds: Set<Long> = emptySet(),
    isGroupCreationMode: Boolean = false,
    groupCreationSelectedFolderIds: Set<Int> = emptySet(),
    groupCreationSelectedGroupIds: Set<Long> = emptySet(),
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit = {},
    onGroupLongClick: (GroupItem) -> Unit = {},
    onReorderFolders: (fromIndex: Int, toIndex: Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    // Called on first drag-move after a long-press that entered selection mode.
    // Exits selection mode so drag-reorder can proceed.
    onExitSelectionForDrag: () -> Unit = {},
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    val colors = LocalVideoColors.current
    // canDrag intentionally does NOT include !isSelectionMode.
    // DragDropGridState manages the gesture internally; keeping this condition
    // stable prevents the pointerInput modifier from being torn down mid-gesture.
    val canDrag = sortOption == FolderSortOption.CUSTOM_ORDER && !isGroupCreationMode
    val resolvedItems: List<MixedFolderItem> =
        if (orderedMixedItems.isNotEmpty()) orderedMixedItems.toMixedFolderItems()
        else folders.map { MixedItem.Folder(it) }
    TabContentScaffold(
        isLoading = isLoading,
        isEmpty = resolvedItems.isEmpty(),
        viewType = viewType,
        modifier = modifier,
        crossfadeLabel = "folderViewTypeTransition",
        gridTransitionLabel = "folderGridColumnTransition",
        emptyContent = {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Folder,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = colors.listSecondText
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "No folders", fontSize = 16.sp, color = colors.listFirstText)
                    Text(
                        text = "No video folders found on this device.",
                        fontSize = 14.sp,
                        color = colors.listSecondText,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        listContent = {
            MixedListContent(
                items = resolvedItems,
                lazyListState = lazyListState,
                isSelectionMode = isSelectionMode,
                selectedFolderIds = selectedIds,
                selectedGroupIds = selectedGroupIds,
                isGroupCreationMode = isGroupCreationMode,
                groupCreationSelectedFolderIds = groupCreationSelectedFolderIds,
                groupCreationSelectedGroupIds = groupCreationSelectedGroupIds,
                canDrag = canDrag,
                onFolderClick = onFolderClick,
                onFolderLongClick = onFolderLongClick,
                onGroupClick = onGroupClick,
                onGroupLongClick = onGroupLongClick,
                onReorder = onReorderFolders,
                onReorderDone = onReorderDone,
                onExitSelectionForDrag = onExitSelectionForDrag
            )
        },
        gridContent = { _, spacing, columnCount ->
            MixedGridContent(
                items = resolvedItems,
                lazyGridState = lazyGridState,
                columnCount = columnCount,
                spacing = spacing,
                isSelectionMode = isSelectionMode,
                selectedFolderIds = selectedIds,
                selectedGroupIds = selectedGroupIds,
                isGroupCreationMode = isGroupCreationMode,
                groupCreationSelectedFolderIds = groupCreationSelectedFolderIds,
                groupCreationSelectedGroupIds = groupCreationSelectedGroupIds,
                viewType = viewType,
                canDrag = canDrag,
                onFolderClick = onFolderClick,
                onFolderLongClick = onFolderLongClick,
                onGroupClick = onGroupClick,
                onGroupLongClick = onGroupLongClick,
                onReorder = onReorderFolders,
                onReorderDone = onReorderDone,
                onExitSelectionForDrag = onExitSelectionForDrag
            )
        }
    )
}
// ─────────────────────────────────────────────────────────────────────────────
// MixedListContent
// ─────────────────────────────────────────────────────────────────────────────
@Composable
private fun MixedListContent(
    items: List<MixedFolderItem>,
    lazyListState: LazyListState,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    isGroupCreationMode: Boolean,
    groupCreationSelectedFolderIds: Set<Int>,
    groupCreationSelectedGroupIds: Set<Long>,
    canDrag: Boolean,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    onReorder: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    onExitSelectionForDrag: () -> Unit
) {
    // Layout index 0 = header; data items start at layout index 1.
    val dragDropState = rememberDragDropListState(
        lazyListState = lazyListState,
        onMove = { from, to ->
            val dataFrom = from - 1
            val dataTo   = to   - 1
            if (dataFrom >= 0 && dataTo >= 0 &&
                dataFrom < items.size && dataTo < items.size) {
                onReorder(dataFrom, dataTo)
            }
        },
        onDragEnd = onReorderDone,
        onLongPressItem = { layoutIndex ->
            val dataIndex = layoutIndex - 1
            items.getOrNull(dataIndex)?.let { item ->
                when (item) {
                    is MixedItem.Folder -> onFolderLongClick(item.folder)
                    is MixedItem.Group  -> onGroupLongClick(item.group)
                }
            }
        },
        isInSelectionMode  = { isSelectionMode },
        onEnterDragMode    = onExitSelectionForDrag
    )
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .then(if (canDrag) Modifier.dragToReorderList(dragDropState) else Modifier),
        contentPadding = PaddingValues(vertical = 4.dp),
        userScrollEnabled = !(canDrag && dragDropState.isDragging)
    ) {
        item(key = "header_all_albums") {
            Text(
                text = "All albums",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = LocalVideoColors.current.listFirstText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        itemsIndexed(items, key = { _, item -> item.uniqueKey }) { index, item ->
            // data index == index; layout index == index + 1 (header at 0)
            val isDragging    = canDrag && dragDropState.draggedIndex == index + 1
            // isDragging (not isDragActive) so visual feedback fires immediately on
            // long-press — draggedOffset is still Offset.Zero at that moment, so the
            // card appears exactly on top of the item before the finger has moved.
            val anyDragActive = canDrag && dragDropState.isDragging
            val dimModifier = if (anyDragActive && !isDragging)
                Modifier.graphicsLayer { alpha = 0.65f }
            else
                Modifier
            when (item) {
                is MixedItem.Folder -> {
                    val folder = item.folder
                    val effectiveSelected = when {
                        isGroupCreationMode -> groupCreationSelectedFolderIds.contains(folder.bucketId)
                        else                -> selectedFolderIds.contains(folder.bucketId)
                    }
                    FolderListItem(
                        folder = folder,
                        isSelected = effectiveSelected,
                        isSelectionMode = isSelectionMode || isGroupCreationMode,
                        onClick = {
                            if (!dragDropState.consumeNextClick()) onFolderClick(folder)
                        },
                        onLongClick = if (canDrag) null else ({ onFolderLongClick(folder) }),
                        isDragging = isDragging,
                        dragOffset = if (isDragging) dragDropState.draggedOffset else Offset.Zero
                    )
                }
                is MixedItem.Group -> {
                    val group = item.group
                    val effectiveSelected = when {
                        isGroupCreationMode -> groupCreationSelectedGroupIds.contains(group.groupId)
                        else                -> selectedGroupIds.contains(group.groupId)
                    }
                    GroupListItem(
                        group = group,
                        isSelected = effectiveSelected,
                        isSelectionMode = isSelectionMode || isGroupCreationMode,
                        onClick = {
                            if (!dragDropState.consumeNextClick()) onGroupClick(group)
                        },
                        onLongClick = if (canDrag) null else ({ onGroupLongClick(group) })
                    )
                }
            }
        }
    }
}
// ─────────────────────────────────────────────────────────────────────────────
// MixedGridContent
// ─────────────────────────────────────────────────────────────────────────────
@Composable
private fun MixedGridContent(
    items: List<MixedFolderItem>,
    lazyGridState: LazyGridState,
    columnCount: Int,
    spacing: Dp,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    isGroupCreationMode: Boolean,
    groupCreationSelectedFolderIds: Set<Int>,
    groupCreationSelectedGroupIds: Set<Long>,
    viewType: ViewType,
    canDrag: Boolean,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    onReorder: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    onExitSelectionForDrag: () -> Unit
) {
    // Layout index 0 = "All albums" header span; data items start at layout index 1.
    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
        onMove = { from, to ->
            val dataFrom = from - 1
            val dataTo   = to   - 1
            if (dataFrom >= 0 && dataTo >= 0 &&
                dataFrom < items.size && dataTo < items.size) {
                onReorder(dataFrom, dataTo)
            }
        },
        onDragEnd = onReorderDone,
        onLongPressItem = { layoutIndex ->
            val dataIndex = layoutIndex - 1
            items.getOrNull(dataIndex)?.let { item ->
                when (item) {
                    is MixedItem.Folder -> onFolderLongClick(item.folder)
                    is MixedItem.Group  -> onGroupLongClick(item.group)
                }
            }
        },
        isInSelectionMode  = { isSelectionMode },
        onEnterDragMode    = onExitSelectionForDrag
    )

    // Wrap grid + overlay in a Box so the overlay can float above the grid.
    Box(modifier = Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(columnCount),
            modifier = Modifier
                .fillMaxSize()
                .then(if (canDrag) Modifier.dragToReorderGrid(dragDropState) else Modifier),
            contentPadding = PaddingValues(spacing),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalArrangement = Arrangement.spacedBy(spacing),
            userScrollEnabled = !(canDrag && dragDropState.isDragging)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }, key = "header_all_albums") {
                Text(
                    text = "All albums",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalVideoColors.current.listFirstText,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                )
            }
            itemsIndexed(items, key = { _, item -> item.uniqueKey }) { index, item ->
                // data index == index; layout index == index + 1 (header at 0)
                val isDragging    = canDrag && dragDropState.draggedIndex == index + 1
                val anyDragActive = canDrag && dragDropState.isDragging
                val dimModifier   = if (anyDragActive && !isDragging)
                    Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                when (item) {
                    is MixedItem.Folder -> {
                        val folder = item.folder
                        val effectiveSelected = when {
                            isGroupCreationMode -> groupCreationSelectedFolderIds.contains(folder.bucketId)
                            else                -> selectedFolderIds.contains(folder.bucketId)
                        }
                        FolderGridItem(
                            folder          = folder,
                            isSelected      = effectiveSelected,
                            isSelectionMode = isSelectionMode || isGroupCreationMode,
                            viewType        = viewType,
                            onClick = {
                                if (!dragDropState.consumeNextClick()) onFolderClick(folder)
                            },
                            onLongClick = if (canDrag) null else ({ onFolderLongClick(folder) }),
                            isDragging  = isDragging,
                            modifier    = Modifier
                                .animateItem(
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = 4000f
                                    )
                                )
                                .then(dimModifier)
                        )
                    }
                    is MixedItem.Group -> {
                        val group = item.group
                        val effectiveSelected = when {
                            isGroupCreationMode -> groupCreationSelectedGroupIds.contains(group.groupId)
                            else                -> selectedGroupIds.contains(group.groupId)
                        }
                        GroupGridItem(
                            group           = group,
                            isSelected      = effectiveSelected,
                            isSelectionMode = isSelectionMode || isGroupCreationMode,
                            viewType        = viewType,
                            onClick = {
                                if (!dragDropState.consumeNextClick()) onGroupClick(group)
                            },
                            onLongClick = if (canDrag) null else ({ onGroupLongClick(group) }),
                            isDragging  = isDragging,
                            modifier    = Modifier
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
            }
        }

        // ── Floating overlay — always above the grid ─────────────────────────
        // Shown as soon as isDragging becomes true (on long-press, before any movement).
        // overlayPosition = fingerPosInGrid − touchOffsetInItem starts at itemTopLeft,
        // so the overlay appears exactly on top of the pressed item with no visual jump.
        if (canDrag && dragDropState.isDragging) {
            val overlayPos  = dragDropState.overlayPosition
            val itemSizePx  = dragDropState.capturedItemSize
            // draggedIndex is the GRID layout index; subtract 1 for the header item
            val draggedItem = items.getOrNull(dragDropState.draggedIndex - 1)

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
                    // Render the item normally (isDragging=false → fully visible, no ghost)
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
                        is MixedItem.Group  -> GroupGridItem(
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
    }
}
