package com.example.common.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.FastScrollerForGrid
import com.example.common.ui.components.ScreenTopBar

/**
 * Shared Create Album picker screen used by both image-library and video-library.
 *
 * Composable slots allow each library to inject its own rendering logic:
 * - [itemGridContent] — renders a media item (ImageGridItem or VideoGridItem) in selection mode
 * - [folderGridContent] — renders a folder grid item
 * - [groupGridContent] — renders a group grid item
 * - [selectedItemThumbnail] — renders a thumbnail in the selected items tray
 *
 * Type parameter T represents the media item type (ImageItem or VideoItem).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> CreateAlbumPickerScreen(
    albumName: String,
    allFolders: List<FolderItem>,
    allGroups: List<GroupItem>,
    /** Items loaded for the currently opened folder (empty when browsing folders/groups). */
    browsedItems: List<T>,
    /** Non-null when we are inside a folder's item view. */
    currentBucketId: Int?,
    selectedItemIds: Set<Long>,
    /** Get unique ID from a media item. */
    getItemId: (T) -> Long,
    /** Large grid = 2 columns, small grid = 3 columns. */
    isLargeGrid: Boolean = true,
    maxSelectionCount: Int = 500,
    onFolderOpen: (FolderItem) -> Unit,
    onFolderClose: () -> Unit,
    onToggleItem: (Long) -> Unit,
    onDone: () -> Unit,
    onBack: () -> Unit,
    /** All items flattened — used to build the tray preview thumbnails. */
    allItems: List<T>,
    /**
     * Pre-calculated ordered items for each group (groupId -> ordered list).
     * When present, these items are used directly instead of re-calculating from memberBucketIds.
     * This ensures the picker respects the group's sort order (Custom, A-Z, etc.).
     */
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
    /** Renders a media item in the selection grid. */
    itemGridContent: @Composable (item: T, isSelected: Boolean, onClick: () -> Unit, onLongClick: () -> Unit, modifier: Modifier) -> Unit,
    /** Renders a folder grid item. */
    folderGridContent: @Composable (folder: FolderItem, onClick: () -> Unit, onLongClick: () -> Unit, modifier: Modifier) -> Unit,
    /** Renders a group grid item. */
    groupGridContent: @Composable (group: GroupItem, onClick: () -> Unit, onLongClick: () -> Unit, modifier: Modifier) -> Unit,
    /** Renders a selected item thumbnail in the tray. */
    selectedItemThumbnail: @Composable (item: T, onRemove: () -> Unit) -> Unit,
    /** Screen background color. */
    screenBackgroundColor: Color = Color.Black,
    /** Top bar background color. */
    topBarColor: Color = Color.Black,
    /** Primary text color. */
    primaryTextColor: Color = Color.White,
    /** Secondary text color. */
    secondaryTextColor: Color = Color.White.copy(alpha = 0.55f),
    /** Tray background color. */
    trayBackgroundColor: Color = Color(0xFF1C1C1C),
    /** Done button active color. */
    doneButtonActiveColor: Color = Color(0xFF2979FF),
    /** Done button inactive color. */
    doneButtonInactiveColor: Color = Color(0xFF3A3A3A),
    modifier: Modifier = Modifier
) {
    // Local group-browse navigation stack
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }

    // Tray collapse state
    var trayExpanded by remember { mutableStateOf(true) }

    val columnCount = if (isLargeGrid) 2 else 3
    val gridSpacing = if (isLargeGrid) 18.dp else 12.dp
    val lazyGridState = rememberLazyGridState()

    // Reset scroll position when entering/exiting folders
    LaunchedEffect(currentBucketId, browseStack) {
        if (currentBucketId != null || browseStack.isNotEmpty()) {
            lazyGridState.scrollToItem(0)
        }
    }

    // Handle Android back button
    BackHandler {
        when {
            currentBucketId != null -> onFolderClose()
            browseStack.isNotEmpty() -> browseStack = browseStack.dropLast(1)
            else -> onBack()
        }
    }

    // ── Build the display item list ──────────────────────────────────────────
    val currentBrowseGroupId = browseStack.lastOrNull()?.first

    val displayItems: List<MixedItem> = remember(allFolders, allGroups, currentBrowseGroupId, groupOrderedItems) {
        if (currentBrowseGroupId != null) {
            // Check if we have pre-calculated items for this group
            val preCalculated = groupOrderedItems[currentBrowseGroupId]
            if (preCalculated != null) {
                // Use exact items from the group's sort order
                preCalculated.mapNotNull { item ->
                    when (item) {
                        is FolderItem -> MixedItem.Folder(item)
                        is GroupItem -> MixedItem.Group(item)
                        else -> null
                    }
                }
            } else {
                // Fallback: re-calculate (old behavior)
                val browsedGroup = allGroups.find { it.groupId == currentBrowseGroupId }
                val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
                val memberFolders = memberBucketIds.mapNotNull { bid -> allFolders.find { it.bucketId == bid } }
                val subGroups = allGroups.filter { it.parentGroupId == currentBrowseGroupId }
                buildList {
                    subGroups.forEach { add(MixedItem.Group(it)) }
                    memberFolders.forEach { add(MixedItem.Folder(it)) }
                }
            }
        } else {
            val groupedBucketIds = allGroups.flatMap { it.memberBucketIds }.toSet()
            val ungroupedFolders = allFolders.filter { it.bucketId !in groupedBucketIds }
            val rootGroups = allGroups.filter { it.parentGroupId == null }
            buildList {
                rootGroups.forEach { add(MixedItem.Group(it)) }
                ungroupedFolders.forEach { add(MixedItem.Folder(it)) }
            }
        }
    }

    // ── Selected items for tray preview ────────────────────────────────────
    val selectedItems = remember(selectedItemIds, allItems) {
        allItems.filter { getItemId(it) in selectedItemIds }
    }

    val selectionCount = selectedItemIds.size

    // ── Header title ─────────────────────────────────────────────────────────
    val headerTitle = when {
        currentBucketId != null -> "$selectionCount / $maxSelectionCount"
        browseStack.isNotEmpty() -> browseStack.last().second
        else -> "Select items"
    }

    Box(modifier = modifier.fillMaxSize().background(screenBackgroundColor)) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ──────────────────────────────────────────────────────
            ScreenTopBar {
                CircularBackButton(onClick = {
                    when {
                        currentBucketId != null -> onFolderClose()
                        browseStack.isNotEmpty() -> browseStack = browseStack.dropLast(1)
                        else -> onBack()
                    }
                })

                Spacer(modifier = Modifier.width(12.dp))

                if (currentBucketId != null) {
                    Text(
                        text = headerTitle,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = primaryTextColor,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = headerTitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryTextColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "New album: $albumName",
                            fontSize = 13.sp,
                            color = secondaryTextColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Done button
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .then(
                            if (selectionCount > 0) Modifier.clickable(onClick = onDone)
                            else Modifier
                        ),
                    color = if (selectionCount > 0) doneButtonActiveColor else doneButtonInactiveColor,
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Done",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (selectionCount > 0) Color.White else Color.White.copy(alpha = 0.4f),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }

            // ── Selected items tray ─────────────────────────────────────────
            SelectedItemsTray(
                selectedItems = selectedItems,
                isExpanded = trayExpanded,
                onToggleExpand = { trayExpanded = !trayExpanded },
                onRemove = onToggleItem,
                getItemId = getItemId,
                itemThumbnail = selectedItemThumbnail,
                backgroundColor = trayBackgroundColor,
                secondaryTextColor = secondaryTextColor
            )

            // ── Content ──────────────────────────────────────────────────────
            if (currentBucketId != null) {
                // ── Item grid with selection ──────────────────────────────────
                if (browsedItems.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No items in this folder",
                            fontSize = 16.sp,
                            color = secondaryTextColor
                        )
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(columnCount),
                            state = lazyGridState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            items(browsedItems, key = { getItemId(it) }) { item ->
                                val itemId = getItemId(item)
                                itemGridContent(
                                    item,
                                    itemId in selectedItemIds,
                                    { onToggleItem(itemId) },
                                    { onToggleItem(itemId) },
                                    Modifier.animateItem(
                                        placementSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness = 4000f
                                        )
                                    )
                                )
                            }
                        }
                        FastScrollerForGrid(
                            state = lazyGridState,
                            itemCount = browsedItems.size,
                            sectionLabel = { index ->
                                val clamped = index.coerceIn(0, (browsedItems.size - 1).coerceAtLeast(0))
                                "${clamped + 1}/${browsedItems.size}"
                            }
                        )
                    }
                }
            } else {
                // ── Folder / group browser ─────────────────────────────────────
                if (displayItems.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (currentBrowseGroupId != null) "No folders in this group"
                            else "No folders available",
                            fontSize = 16.sp,
                            color = secondaryTextColor
                        )
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(columnCount),
                            state = lazyGridState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                            verticalArrangement = Arrangement.spacedBy(gridSpacing)
                        ) {
                            items(displayItems, key = { it.uniqueKey }) { item ->
                                when (item) {
                                    is MixedItem.Folder -> {
                                        folderGridContent(
                                            item.folder,
                                            { onFolderOpen(item.folder) },
                                            { onFolderOpen(item.folder) },
                                            Modifier.animateItem(
                                                placementSpec = spring(
                                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                                    stiffness = 4000f
                                                )
                                            )
                                        )
                                    }
                                    is MixedItem.Group -> {
                                        groupGridContent(
                                            item.group,
                                            { browseStack = browseStack + (item.group.groupId to item.group.name) },
                                            { browseStack = browseStack + (item.group.groupId to item.group.name) },
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
                        FastScrollerForGrid(
                            state = lazyGridState,
                            itemCount = displayItems.size,
                            sectionLabel = { index ->
                                when (val item = displayItems.getOrNull(index)) {
                                    is MixedItem.Folder -> item.folder.name
                                    is MixedItem.Group -> item.group.name
                                    null -> ""
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// ── Selected items tray ─────────────────────────────────────────────────────

@Composable
private fun <T> SelectedItemsTray(
    selectedItems: List<T>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onRemove: (Long) -> Unit,
    getItemId: (T) -> Long,
    itemThumbnail: @Composable (item: T, onRemove: () -> Unit) -> Unit,
    backgroundColor: Color,
    secondaryTextColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        // Expanded tray content
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            if (selectedItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items selected",
                        fontSize = 14.sp,
                        color = secondaryTextColor
                    )
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(selectedItems, key = { getItemId(it) }) { item ->
                        itemThumbnail(item) { onRemove(getItemId(item)) }
                    }
                }
            }
        }

        // Chevron toggle row
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggleExpand)
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = secondaryTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}






