package com.videolibrary.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
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
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.VideoGridItem
import com.videolibrary.ui.components.VideoThumbnail
import com.videolibrary.ui.theme.LocalVideoColors

private const val MAX_ALBUM_SELECTION = 500

/**
 * Full-screen video picker for the Create Album flow.
 *
 * Navigation logic (handled locally so the ViewModel is not involved):
 *  - Root level  → displays [rootGroups] + [ungroupedFolders]
 *  - Inside group → displays that group's member [FolderItem]s
 *  - Inside folder → displays [browsedVideos] with multi-select
 *
 * Back-press priority:
 *  1. Folder is open        → [onFolderClose]
 *  2. Group is open         → close group (local state)
 *  3. Root level            → [onBack] (cancels the Create Album flow)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateAlbumPickerScreen(
    albumName: String,
    rootGroups: List<GroupItem>,
    ungroupedFolders: List<FolderItem>,
    allFolders: List<FolderItem>,
    browsedVideos: List<VideoItem>,
    currentBucketId: Int?,
    selectedVideoIds: Set<Long>,
    viewType: ViewType = ViewType.GRID_LARGE,
    onFolderOpen: (FolderItem) -> Unit,
    onFolderClose: () -> Unit,
    onToggleVideo: (Long) -> Unit,
    onDone: () -> Unit,
    onBack: () -> Unit,
    allVideos: List<VideoItem>,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current

    // Local group-browse state (never touches the ViewModel)
    var currentBrowseGroup by remember { mutableStateOf<GroupItem?>(null) }

    val isLargeGrid = viewType != ViewType.GRID_SMALL
    val columnCount = if (isLargeGrid) 2 else 3

    // ── Back handler ─────────────────────────────────────────────────────────
    BackHandler {
        when {
            currentBucketId != null -> onFolderClose()
            currentBrowseGroup != null -> currentBrowseGroup = null
            else -> onBack()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // ── Top bar ──────────────────────────────────────────────────────────
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color    = Color.Black
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(start = 4.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
                    .heightIn(min = 52.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back / up button
                IconButton(
                    onClick = {
                        when {
                            currentBucketId != null -> onFolderClose()
                            currentBrowseGroup != null -> currentBrowseGroup = null
                            else -> onBack()
                        }
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Title + subtitle
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                ) {
                    val title = when {
                        currentBucketId != null  -> "${selectedVideoIds.size} / $MAX_ALBUM_SELECTION"
                        currentBrowseGroup != null -> currentBrowseGroup!!.name
                        else                     -> "Select videos"
                    }
                    Text(
                        text       = title,
                        color      = Color.White,
                        fontSize   = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines   = 1,
                        overflow   = TextOverflow.Ellipsis
                    )
                    if (currentBucketId == null) {
                        Text(
                            text     = "New album: $albumName",
                            color    = Color.White.copy(alpha = 0.55f),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Done button
                if (selectedVideoIds.isNotEmpty()) {
                    TextButton(onClick = onDone) {
                        Text(
                            text       = "Done",
                            color      = Color.White,
                            fontSize   = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = Color.White.copy(alpha = 0.1f))

        // ── Selected-videos tray ─────────────────────────────────────────────
        SelectedVideosTray(
            selectedVideoIds = selectedVideoIds,
            allVideos        = allVideos,
            onRemove         = onToggleVideo
        )

        HorizontalDivider(color = Color.White.copy(alpha = 0.08f))

        // ── Main content ─────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (currentBucketId != null) {
                // Video selection grid
                if (browsedVideos.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No videos in this folder", color = Color.White.copy(alpha = 0.45f))
                    }
                } else {
                    LazyVerticalGrid(
                        columns             = GridCells.Fixed(columnCount),
                        contentPadding      = PaddingValues(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier            = Modifier.fillMaxSize()
                    ) {
                        items(browsedVideos, key = { it.id }) { video ->
                            VideoGridItem(
                                video            = video,
                                isSelected       = video.id in selectedVideoIds,
                                isSelectionMode  = true,
                                isLargeGrid      = isLargeGrid,
                                onClick          = { onToggleVideo(video.id) },
                                onLongClick      = { onToggleVideo(video.id) }
                            )
                        }
                    }
                }
            } else {
                // Folder / group browse grid
                val groupsToShow: List<GroupItem>
                val foldersToShow: List<FolderItem>

                if (currentBrowseGroup != null) {
                    groupsToShow  = emptyList()
                    foldersToShow = allFolders.filter { it.bucketId in currentBrowseGroup!!.memberBucketIds }
                } else {
                    groupsToShow  = rootGroups
                    foldersToShow = ungroupedFolders
                }

                if (groupsToShow.isEmpty() && foldersToShow.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No folders", color = Color.White.copy(alpha = 0.45f))
                    }
                } else {
                    LazyVerticalGrid(
                        columns             = GridCells.Fixed(columnCount),
                        contentPadding      = PaddingValues(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier            = Modifier.fillMaxSize()
                    ) {
                        items(groupsToShow, key = { "g_${it.groupId}" }) { group ->
                            GroupGridItem(
                                group           = group,
                                isSelected      = false,
                                isSelectionMode = false,
                                viewType        = viewType,
                                onClick         = { currentBrowseGroup = group }
                            )
                        }
                        items(foldersToShow, key = { "f_${it.bucketId}" }) { folder ->
                            FolderGridItem(
                                folder          = folder,
                                isSelected      = false,
                                isSelectionMode = false,
                                viewType        = viewType,
                                onClick         = { onFolderOpen(folder) }
                            )
                        }
                    }
                }
            }
        }
    }
}

// ── Selected-videos horizontal tray ──────────────────────────────────────────

@Composable
private fun SelectedVideosTray(
    selectedVideoIds: Set<Long>,
    allVideos: List<VideoItem>,
    onRemove: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxWidth()) {
        // Header row: count + expand/collapse toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text     = if (selectedVideoIds.isEmpty()) "No items selected"
                           else "${selectedVideoIds.size} selected",
                color    = if (selectedVideoIds.isEmpty()) colors.listSecondText else Color.White,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector        = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint               = colors.listSecondText,
                modifier           = Modifier.size(20.dp)
            )
        }

        // Thumbnail row (animates in/out)
        AnimatedVisibility(
            visible = expanded && selectedVideoIds.isNotEmpty(),
            enter   = expandVertically(),
            exit    = shrinkVertically()
        ) {
            LazyRow(
                contentPadding        = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier              = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
            ) {
                val ids = selectedVideoIds.toList()
                items(ids.size, key = { ids[it] }) { idx ->
                    val id    = ids[idx]
                    val video = allVideos.find { it.id == id }

                    Box(contentAlignment = Alignment.TopEnd) {
                        // Thumbnail
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF2A2A2A))
                        ) {
                            if (video != null) {
                                VideoThumbnail(
                                    contentUri         = video.contentUri,
                                    contentDescription = video.title,
                                    contentScale       = ContentScale.Crop,
                                    modifier           = Modifier.fillMaxSize()
                                )
                            }
                        }

                        // Remove badge (top-end)
                        Box(
                            modifier = Modifier
                                .offset(x = 4.dp, y = (-4).dp)
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEF5350))
                                .clickable { onRemove(id) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text       = "×",
                                color      = Color.White,
                                fontSize   = 13.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

