package com.imagelibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.data.model.toMixedItems
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.theme.LocalImageColors
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.rememberDragDropGridState
import kotlin.math.roundToInt

/**
 * Typealias kept for source compatibility — all image-library code can still use
 * FolderListItem.Folder / FolderListItem.Group unchanged.
 */
typealias FolderListItem = MixedItem

/** Maps a raw [List<Any>] to a typed [List<MixedItem>]. */
fun List<Any>.toFolderListItems(): List<MixedItem> = toMixedItems()

@Composable
fun FoldersTab(
    mixedItems: List<Any> = emptyList(),
    // kept for callers that still pass them separately (group-creation mode uses these)
    folders: List<FolderItem>? = null,
    groups: List<GroupItem>? = null,
    viewType: ViewType = ViewType.GRID_LARGE,
    isSelectionMode: Boolean = false,
    selectedFolderIds: Set<Int> = emptySet(),
    selectedGroupIds: Set<Long> = emptySet(),
    isLoading: Boolean,
    showCheckboxes: Boolean = false,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit = {},
    onGroupLongClick: (GroupItem) -> Unit = {},
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    // ── Drag-and-drop ──
    sortOption: SortOption = SortOption.CUSTOM_ORDER,
    onReorderFolders: (Int, Int) -> Unit = { _, _ -> },
    onReorderDone: () -> Unit = {},
    // Called when drag starts after a long-press that entered selection mode
    // (host should call exitSelectionMode() to switch from checkboxes → drag-reorder)
    onExitSelectionForDrag: () -> Unit = {}
) {
    val colors = LocalImageColors.current

    // Use the pre-ordered unified list when provided; fall back to groups-first for
    // callers that still pass separate lists (e.g. group-creation mode).
    val resolvedItems: List<FolderListItem> = when {
        mixedItems.isNotEmpty() -> mixedItems.mapNotNull { item ->
            when (item) {
                is GroupItem  -> MixedItem.Group(item)
                is FolderItem -> MixedItem.Folder(item)
                else          -> null
            }
        }
        else -> buildList {
            groups?.forEach  { add(MixedItem.Group(it))  }
            folders?.forEach { add(MixedItem.Folder(it)) }
        }
    }

    // Drag modifier is active whenever sort=CUSTOM_ORDER and NOT in forced-checkbox mode
    // (showCheckboxes = group-creation mode).  isSelectionMode is intentionally excluded:
    // the gesture handler itself decides whether to reorder or keep selection mode.
    val canDrag = sortOption == SortOption.CUSTOM_ORDER && !showCheckboxes

    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
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
        // Index 0 is the "All albums" header (GridItemSpan full-width) — exclude it from
        // drag start and swap targets so draggedIndex can never point at the header.
        minDragIndex = 1
    )

    Box(modifier = modifier.fillMaxSize()) {

        TabContentScaffold(
            isLoading = isLoading,
            isEmpty = resolvedItems.isEmpty(),
            viewType = viewType,
            modifier = Modifier.fillMaxSize(),
            gridTransitionLabel = "folderGridColumnTransition",
            emptyContent = {
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
                                                androidx.compose.ui.graphics.Color.Transparent
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
                                        color = androidx.compose.ui.graphics.Color(0xFF1A1A2E),
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
                            text = "Photos and videos you take will appear here",
                            fontSize = 14.sp,
                            color = colors.listSecondText,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        ) { _, spacing, columnCount ->

            // ── Inner Box: grid + floating overlay share the same coordinate space ──
            // The overlay offset is measured in the LazyVerticalGrid's viewport coordinates.
            // Wrapping both in the same Box guarantees (0,0) is identical for the grid and
            // the overlay, matching the pattern used in video-library's MixedGridContent.
            Box(modifier = Modifier.fillMaxSize()) {

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
                    item(span = { GridItemSpan(maxLineSpan) }, key = "header_all_albums") {
                        Text(
                            text = "All albums",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.listFirstText,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
                        )
                    }

                    itemsIndexed(resolvedItems, key = { _, item -> item.uniqueKey }) { index, item ->
                        val itemIsDragging = canDrag && dragDropState.draggedIndex == index + 1
                        val anyDragActive  = canDrag && dragDropState.isDragging
                        val dimModifier    = if (anyDragActive && !itemIsDragging)
                            Modifier.graphicsLayer { alpha = 0.65f } else Modifier

                        when (item) {
                            is MixedItem.Folder -> FolderGridItem(
                                folder = item.folder,
                                isSelected = selectedFolderIds.contains(item.folder.bucketId),
                                isSelectionMode = isSelectionMode || showCheckboxes,
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
                                isSelectionMode = isSelectionMode || showCheckboxes,
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

                // ── Samsung-style floating overlay ─────────────────────────────────────
                // Rendered above the grid in the SAME Box — overlay coordinates are
                // identical to the LazyVerticalGrid viewport coordinates (both share this
                // Box's (0,0) origin), so no coordinate-space mismatch is possible.
                if (canDrag && dragDropState.isDragging) {
                    val overlayPos  = dragDropState.overlayPosition
                    val itemSizePx  = dragDropState.capturedItemSize
                    // draggedIndex is the grid layout index; subtract 1 to skip the header
                    val draggedItem = resolvedItems.getOrNull(dragDropState.draggedIndex - 1)

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
                                    isSelectionMode = isSelectionMode || showCheckboxes,
                                    viewType = viewType,
                                    onClick = {},
                                    onLongClick = null,
                                    isDragging = false
                                )
                                is MixedItem.Group -> GroupGridItem(
                                    group = draggedItem.group,
                                    isSelected = selectedGroupIds.contains(draggedItem.group.groupId),
                                    isSelectionMode = isSelectionMode || showCheckboxes,
                                    viewType = viewType,
                                    onClick = {},
                                    onLongClick = null,
                                    isDragging = false
                                )
                            }
                        }
                    }
                }

            } // end inner Box (grid + overlay)
        } // end TabContentScaffold grid slot

    } // end outer Box
}


