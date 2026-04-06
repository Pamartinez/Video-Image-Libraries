package com.videolibrary.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.screen.CreateAlbumPickerScreen as CommonCreateAlbumPickerScreen
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.FolderGridItem
import com.videolibrary.ui.components.GroupGridItem
import com.videolibrary.ui.components.VideoGridItem
import com.videolibrary.ui.components.VideoThumbnail
import com.videolibrary.ui.theme.LocalVideoColors

private const val MAX_ALBUM_SELECTION = 500

/**
 * Video-library entry point for the shared [CommonCreateAlbumPickerScreen].
 *
 * Injects:
 * - [VideoGridItem] for video selection grid
 * - [FolderGridItem] for folder browsing
 * - [GroupGridItem] for group browsing
 * - [VideoThumbnail] for selected items tray
 */
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
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
    onFolderOpen: (FolderItem) -> Unit,
    onFolderClose: () -> Unit,
    onToggleVideo: (Long) -> Unit,
    onDone: () -> Unit,
    onBack: () -> Unit,
    allVideos: List<VideoItem>,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    val isLargeGrid = viewType != ViewType.GRID_SMALL

    CommonCreateAlbumPickerScreen(
        albumName = albumName,
        allFolders = allFolders,
        allGroups = rootGroups,  // Use rootGroups as the screen will filter them
        browsedItems = browsedVideos,
        currentBucketId = currentBucketId,
        selectedItemIds = selectedVideoIds,
        getItemId = { it.id },
        isLargeGrid = isLargeGrid,
        maxSelectionCount = MAX_ALBUM_SELECTION,
        onFolderOpen = onFolderOpen,
        onFolderClose = onFolderClose,
        onToggleItem = onToggleVideo,
        onDone = onDone,
        onBack = onBack,
        allItems = allVideos,
        groupOrderedItems = groupOrderedItems,
        itemGridContent = { video, isSelected, onClick, onLongClick, mod ->
            VideoGridItem(
                video = video,
                isSelected = isSelected,
                isSelectionMode = true,
                isLargeGrid = isLargeGrid,
                onClick = onClick,
                onLongClick = onLongClick,
                modifier = mod
            )
        },
        folderGridContent = { folder, onClick, onLongClick, mod ->
            FolderGridItem(
                folder = folder,
                isSelected = false,
                isSelectionMode = false,
                viewType = viewType,
                onClick = onClick,
                onLongClick = onLongClick,
                modifier = mod
            )
        },
        groupGridContent = { group, onClick, onLongClick, mod ->
            GroupGridItem(
                group = group,
                isSelected = false,
                isSelectionMode = false,
                viewType = viewType,
                onClick = onClick,
                onLongClick = onLongClick,
                modifier = mod
            )
        },
        selectedItemThumbnail = { video, onRemove ->
            Box(contentAlignment = Alignment.TopEnd) {
                // Thumbnail
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF2A2A2A))
                ) {
                    VideoThumbnail(
                        contentUri = video.contentUri,
                        contentDescription = video.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Remove badge (top-end)
                Box(
                    modifier = Modifier
                        .offset(x = 4.dp, y = (-4).dp)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEF5350))
                        .clickable(onClick = onRemove),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "×",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 13.sp
                    )
                }
            }
        },
        screenBackgroundColor = Color.Black,
        topBarColor = Color.Black,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White.copy(alpha = 0.55f),
        trayBackgroundColor = Color(0xFF1C1C1C),
        doneButtonActiveColor = Color(0xFF2979FF),
        doneButtonInactiveColor = Color(0xFF3A3A3A),
        modifier = modifier
    )
}

