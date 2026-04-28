package com.example.common.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.theme.LibraryColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.dragToReorderList
import com.example.common.ui.util.rememberDragDropGridState
import com.example.common.ui.util.rememberDragDropListState
import kotlin.math.roundToInt

/**
 * Shared FoldersTab used by both image-library and video-library.
 * Displays folders/groups in grid or list view with drag-to-reorder support.
 */
@Composable
fun <ViewTypeEnum, SortOptionEnum> SharedFoldersTab(
    orderedMixedItems: List<Any>,
    isLoading: Boolean,
    viewType: ViewTypeEnum,
    sortOption: SortOptionEnum,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    isGroupCreationMode: Boolean,
    groupCreationSelectedFolderIds: Set<Int>,
    groupCreationSelectedGroupIds: Set<Long>,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    onReorderFolders: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    onExitSelectionForDrag: () -> Unit,
    lazyListState: LazyListState,
    lazyGridState: LazyGridState,

    // Injected dependencies
    colors: LibraryColors,

    // Configuration
    isCustomOrder: (SortOptionEnum) -> Boolean,
    supportsListView: Boolean,
    showHeaderRow: Boolean,
    emptyStateMessage: String,
    gridSpacing: Dp,
    gridColumnCount: Int,

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
        folder: GroupItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        viewType: ViewTypeEnum,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        modifier: Modifier
    ) -> Unit,

    folderListItem: @Composable (
        folder: FolderItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        dragOffset: Offset
    ) -> Unit,

    groupListItem: @Composable (
        group: GroupItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?
    ) -> Unit,

    modifier: Modifier = Modifier
) {
    val resolvedItems: List<MixedItem> = orderedMixedItems.mapNotNull { item ->
        when (item) {
            is GroupItem  -> MixedItem.Group(item)
            is FolderItem -> MixedItem.Folder(item)
            else          -> null
        }
    }

    val canDrag = isCustomOrder(sortOption) && !isGroupCreationMode

    // Empty state content
    val emptyContent: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 64.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(120.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                    colors = listOf(
                                        colors.primary.copy(alpha = 0.08f),
                                        Color.Transparent
                                    )
                                ),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(90.dp)
                            .background(
                                color = Color(0xFF1A1A2E),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Folder,
                            contentDescription = null,
                            modifier = Modifier.size(44.dp),
                            tint = colors.primary.copy(alpha = 0.7f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "No folders", fontSize = 16.sp, color = colors.listFirstText)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = emptyStateMessage,
                    fontSize = 14.sp,
                    color = colors.listSecondText,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    // List view content (video-library only)
    val listContent: @Composable () -> Unit = if (supportsListView) {
        {
            val dragDropListState = rememberDragDropListState(
                lazyListState = lazyListState,
                onMove = { from, to ->
                    val dataFrom = from - 1
                    val dataTo = to - 1
                    if (dataFrom >= 0 && dataTo >= 0 && dataFrom < resolvedItems.size && dataTo < resolvedItems.size) {
                        onReorderFolders(dataFrom, dataTo)
                    }
                },
                onDragEnd = onReorderDone,
                onLongPressItem = { layoutIndex ->
                    val dataIndex = layoutIndex - 1
                    resolvedItems.getOrNull(dataIndex)?.let { item ->
                        when (item) {
                            is MixedItem.Folder -> onFolderLongClick(item.folder)
                            is MixedItem.Group -> onGroupLongClick(item.group)
                        }
                    }
                },
                isInSelectionMode = { isSelectionMode },
                onEnterDragMode = onExitSelectionForDrag,
                minDragIndex = 1
            )

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (canDrag) Modifier.dragToReorderList(dragDropListState) else Modifier),
                contentPadding = PaddingValues(vertical = 0.dp),
                userScrollEnabled = !(canDrag && dragDropListState.isDragging)
            ) {
                item(key = "header_all_albums") {
                    Text(
                        text = "All albums",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.listFirstText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                itemsIndexed(resolvedItems, key = { _, item -> item.uniqueKey }) { index, item ->
                    val isDragging = canDrag && dragDropListState.draggedIndex == index + 1
                    val anyDragActive = canDrag && dragDropListState.isDragging
                    val dimModifier = if (anyDragActive && !isDragging)
                        Modifier.graphicsLayer { alpha = 0.65f }
                    else Modifier

                    when (item) {
                        is MixedItem.Folder -> {
                            val effectiveSelected = if (isGroupCreationMode)
                                groupCreationSelectedFolderIds.contains(item.folder.bucketId)
                            else selectedFolderIds.contains(item.folder.bucketId)

                            folderListItem(
                                item.folder,
                                effectiveSelected,
                                isSelectionMode || isGroupCreationMode,
                                { if (!dragDropListState.consumeNextClick()) onFolderClick(item.folder) },
                                if (canDrag) null else ({ onFolderLongClick(item.folder) }),
                                isDragging,
                                if (isDragging) dragDropListState.draggedOffset else Offset.Zero
                            )
                        }
                        is MixedItem.Group -> {
                            val effectiveSelected = if (isGroupCreationMode)
                                groupCreationSelectedGroupIds.contains(item.group.groupId)
                            else selectedGroupIds.contains(item.group.groupId)

                            groupListItem(
                                item.group,
                                effectiveSelected,
                                isSelectionMode, // NOT group creation mode - groups shouldn't show checkboxes during creation
                                { if (!dragDropListState.consumeNextClick()) onGroupClick(item.group) },
                                if (canDrag) null else ({ onGroupLongClick(item.group) })
                            )
                        }
                    }
                }
            }
        }
    } else {
        {}
    }

    // Grid view content
    val gridContent: @Composable (Boolean, Dp, Int) -> Unit = { _, spacing, columnCount ->
        val dragDropGridState = rememberDragDropGridState(
            lazyGridState = lazyGridState,
            onMove = { from, to ->
                val dataFrom = if (showHeaderRow) from - 1 else from
                val dataTo = if (showHeaderRow) to - 1 else to
                if (dataFrom >= 0 && dataTo >= 0 && dataFrom < resolvedItems.size && dataTo < resolvedItems.size) {
                    onReorderFolders(dataFrom, dataTo)
                }
            },
            onDragEnd = onReorderDone,
            onLongPressWithoutDrag = { layoutIndex ->
                val dataIndex = if (showHeaderRow) layoutIndex - 1 else layoutIndex
                resolvedItems.getOrNull(dataIndex)?.let { item ->
                    when (item) {
                        is MixedItem.Folder -> onFolderLongClick(item.folder)
                        is MixedItem.Group -> onGroupLongClick(item.group)
                    }
                }
            },
            isInSelectionMode = { isSelectionMode },
            onEnterDragMode = onExitSelectionForDrag,
            minDragIndex = if (showHeaderRow) 1 else 0
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnCount),
                state = lazyGridState,
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (canDrag) Modifier.dragToReorderGrid(dragDropGridState) else Modifier),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement = Arrangement.spacedBy(spacing),
                userScrollEnabled = !(canDrag && dragDropGridState.isDragging)
            ) {
                if (showHeaderRow) {
                    item(span = { GridItemSpan(maxLineSpan) }, key = "header_all_albums") {
                        Text(
                            text = "All albums",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.listFirstText,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                        )
                    }
                }

                itemsIndexed(resolvedItems, key = { _, item -> item.uniqueKey }) { index, item ->
                    val layoutIndex = if (showHeaderRow) index + 1 else index
                    val itemIsDragging = canDrag && dragDropGridState.draggedIndex == layoutIndex
                    val anyDragActive = canDrag && dragDropGridState.isDragging
                    val dimModifier = if (anyDragActive && !itemIsDragging)
                        Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                    when (item) {
                        is MixedItem.Folder -> {
                            val effectiveSelected = if (isGroupCreationMode)
                                groupCreationSelectedFolderIds.contains(item.folder.bucketId)
                            else selectedFolderIds.contains(item.folder.bucketId)

                            folderGridItem(
                                item.folder,
                                effectiveSelected,
                                isSelectionMode || isGroupCreationMode,
                                viewType,
                                { if (!dragDropGridState.consumeNextClick()) onFolderClick(item.folder) },
                                if (canDrag) null else ({ onFolderLongClick(item.folder) }),
                                itemIsDragging,
                                Modifier
                                    .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                    .then(dimModifier)
                            )
                        }
                        is MixedItem.Group -> {
                            val effectiveSelected = if (isGroupCreationMode)
                                groupCreationSelectedGroupIds.contains(item.group.groupId)
                            else selectedGroupIds.contains(item.group.groupId)

                            groupGridItem(
                                item.group,
                                effectiveSelected,
                                isSelectionMode, // NOT group creation mode - groups shouldn't show checkboxes during creation
                                viewType,
                                { if (!dragDropGridState.consumeNextClick()) onGroupClick(item.group) },
                                if (canDrag) null else ({ onGroupLongClick(item.group) }),
                                itemIsDragging,
                                Modifier
                                    .animateItem(placementSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = 4000f))
                                    .then(dimModifier)
                            )
                        }
                    }
                }
            }

            // ── Floating drag overlay ──
            if (canDrag && dragDropGridState.isDragging) {
                val overlayPos = dragDropGridState.overlayPosition
                val itemSizePx = dragDropGridState.capturedItemSize
                val draggedIndex = if (showHeaderRow) dragDropGridState.draggedIndex - 1 else dragDropGridState.draggedIndex
                val draggedItem = resolvedItems.getOrNull(draggedIndex)

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
                                selectedFolderIds.contains(draggedItem.folder.bucketId) || groupCreationSelectedFolderIds.contains(draggedItem.folder.bucketId),
                                isSelectionMode || isGroupCreationMode,
                                viewType,
                                {},
                                null,
                                false,
                                Modifier
                            )
                            is MixedItem.Group -> groupGridItem(
                                draggedItem.group,
                                selectedGroupIds.contains(draggedItem.group.groupId) || groupCreationSelectedGroupIds.contains(draggedItem.group.groupId),
                                isSelectionMode || isGroupCreationMode,
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
    }

    // Use TabContentScaffold or direct rendering based on library
    if (supportsListView) {
        // Video-library: use TabContentScaffold with LIST+GRID
        com.example.common.ui.screen.TabContentScaffold(
            isLoading = isLoading,
            isEmpty = resolvedItems.isEmpty(),
            viewType = viewType as com.example.common.data.model.ViewType,  // Safe cast for video-library
            modifier = modifier,
            crossfadeLabel = "folderViewTypeTransition",
            gridTransitionLabel = "folderGridColumnTransition",
            emptyContent = emptyContent,
            listContent = listContent,
            gridContent = gridContent
        )
    } else {
        // Image-library: grid-only, wrap in custom scaffold
        Box(modifier = modifier.fillMaxSize()) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Loading...", color = colors.listSecondText)
                }
            } else if (resolvedItems.isEmpty()) {
                emptyContent()
            } else {
                gridContent(false, gridSpacing, gridColumnCount)
            }
        }
    }
}
