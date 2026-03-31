package com.imagelibrary.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.ViewType
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.imagelibrary.ui.components.*
import com.imagelibrary.ui.theme.LocalImageColors

private const val MAX_SELECTION = 500

/**
 * Full-screen picker for creating a new album.
 *
 * • Root view: shows ungrouped albums + root groups (browse-only for groups).
 * • Inside a group: sub-groups + member albums.
 * • Inside an album: image grid with per-image selection enabled.
 * • A collapsible tray at the top previews all selected images across albums.
 * • "Done" triggers the Copy/Move dialog once ≥ 1 image is selected.
 */
@Composable
fun CreateAlbumPickerScreen(
    albumName: String,
    allFolders: List<FolderItem>,
    allGroups: List<GroupItem>,
    /** Images loaded for the currently opened album (empty when browsing albums/groups). */
    browsedImages: List<ImageItem>,
    /** Non-null when we are inside an album's image view. */
    currentBucketId: Int?,
    selectedImageIds: Set<Long>,
    viewType: ViewType = ViewType.GRID_LARGE,
    onFolderOpen: (FolderItem) -> Unit,
    onFolderClose: () -> Unit,
    onToggleImage: (Long) -> Unit,
    onDone: () -> Unit,
    onBack: () -> Unit,
    /** All images flattened — used to build the tray preview thumbnails. */
    allImages: List<ImageItem>,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current

    // Local group-browse navigation stack (separate from the album-image view)
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }

    // Tray collapse state
    var trayExpanded by remember { mutableStateOf(true) }

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

    val displayItems: List<FolderListItem> = remember(allFolders, allGroups, currentBrowseGroupId) {
        if (currentBrowseGroupId != null) {
            val browsedGroup = allGroups.find { it.groupId == currentBrowseGroupId }
            val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
            val memberFolders = memberBucketIds.mapNotNull { bid -> allFolders.find { it.bucketId == bid } }
            val subGroups = allGroups.filter { it.parentGroupId == currentBrowseGroupId }
            buildList {
                subGroups.forEach { add(MixedItem.Group(it)) }
                memberFolders.forEach { add(MixedItem.Folder(it)) }
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

    // ── Selected images for tray preview ────────────────────────────────────
    val selectedImages = remember(selectedImageIds, allImages) {
        allImages.filter { it.id in selectedImageIds }
    }

    val selectionCount = selectedImageIds.size

    // ── Grid layout helpers ──────────────────────────────────────────────────
    val isLargeGrid = viewType == ViewType.GRID_LARGE
    val columnCount = if (isLargeGrid) 2 else 3
    val gridSpacing = if (isLargeGrid) 8.dp else 4.dp

    // ── Header title ─────────────────────────────────────────────────────────
    val headerTitle = when {
        currentBucketId != null -> "$selectionCount / $MAX_SELECTION"
        browseStack.isNotEmpty() -> browseStack.last().second
        else -> "Select items"
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
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
                        color = colors.listFirstText,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = headerTitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.listFirstText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "New album: $albumName",
                            fontSize = 13.sp,
                            color = colors.listSecondText,
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
                    color = if (selectionCount > 0) colors.primary else Color(0xFF3A3A3A),
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

            // ── Selected images tray ─────────────────────────────────────────
            SelectedImagesTray(
                selectedImages = selectedImages,
                isExpanded = trayExpanded,
                onToggleExpand = { trayExpanded = !trayExpanded },
                onRemove = onToggleImage
            )

            // ── Content ──────────────────────────────────────────────────────
            if (currentBucketId != null) {
                // ── Album image grid with selection ──────────────────────────
                if (browsedImages.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No images in this album",
                            fontSize = 16.sp,
                            color = colors.listSecondText
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(2.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(browsedImages, key = { it.id }) { image ->
                            ImageGridItem(
                                image = image,
                                isSelected = selectedImageIds.contains(image.id),
                                isSelectionMode = true,
                                isLargeGrid = isLargeGrid,
                                onClick = { onToggleImage(image.id) },
                                onLongClick = { onToggleImage(image.id) },
                                modifier = Modifier.animateItem(
                                    placementSpec = spring(
                                        dampingRatio = Spring.DampingRatioNoBouncy,
                                        stiffness = 4000f
                                    )
                                )
                            )
                        }
                    }
                }
            } else {
                // ── Album / group browser ─────────────────────────────────────
                if (displayItems.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (currentBrowseGroupId != null) "No albums in this group"
                            else "No albums available",
                            fontSize = 16.sp,
                            color = colors.listSecondText
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(gridSpacing),
                        horizontalArrangement = Arrangement.spacedBy(gridSpacing),
                        verticalArrangement = Arrangement.spacedBy(gridSpacing)
                    ) {
                        items(displayItems, key = { it.uniqueKey }) { item ->
                            when (item) {
                                is MixedItem.Folder -> {
                                    FolderGridItem(
                                        folder = item.folder,
                                        isSelected = false,
                                        isSelectionMode = false,
                                        viewType = viewType,
                                        onClick = { onFolderOpen(item.folder) },
                                        onLongClick = { onFolderOpen(item.folder) },
                                        modifier = Modifier.animateItem(
                                            placementSpec = spring(
                                                dampingRatio = Spring.DampingRatioNoBouncy,
                                                stiffness = 4000f
                                            )
                                        )
                                    )
                                }
                                is MixedItem.Group -> {
                                    GroupGridItem(
                                        group = item.group,
                                        isSelected = false,
                                        isSelectionMode = false,
                                        viewType = viewType,
                                        onClick = {
                                            browseStack = browseStack + Pair(item.group.groupId, item.group.name)
                                        },
                                        onLongClick = {
                                            browseStack = browseStack + Pair(item.group.groupId, item.group.name)
                                        },
                                        modifier = Modifier.animateItem(
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
                }
            }
        }
    }
}

// ── Selected images tray ─────────────────────────────────────────────────────

@Composable
private fun SelectedImagesTray(
    selectedImages: List<ImageItem>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onRemove: (Long) -> Unit
) {
    val colors = LocalImageColors.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.actionBarBg)
    ) {
        // Expanded tray content
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            if (selectedImages.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items selected",
                        fontSize = 14.sp,
                        color = colors.listSecondText
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
                    items(selectedImages, key = { it.id }) { image ->
                        SelectedTrayItem(
                            image = image,
                            onRemove = { onRemove(image.id) }
                        )
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
                tint = colors.listSecondText,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun SelectedTrayItem(
    image: ImageItem,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(74.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        ImageThumbnail(
            contentUri = image.contentUri,
            contentDescription = image.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Red remove badge
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(20.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(Color(0xFFEF5350))
                .clickable(onClick = onRemove),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "−",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
