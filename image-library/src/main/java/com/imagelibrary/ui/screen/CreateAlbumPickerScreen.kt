package com.imagelibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.*
import com.imagelibrary.ui.theme.LocalImageColors
import androidx.compose.material3.Text

private const val MAX_SELECTION = 500

/**
 * Image-library entry point for the shared [CommonCreateAlbumPickerScreen].
 *
 * Injects:
 * - [ImageGridItem] for image selection grid
 * - [FolderGridItem] for folder browsing
 * - [GroupGridItem] for group browsing
 * - [ImageThumbnail] for selected items tray
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
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
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
    val isLargeGrid = viewType == ViewType.GRID_LARGE

    CommonCreateAlbumPickerScreen(
        albumName = albumName,
        allFolders = allFolders,
        allGroups = allGroups,
        browsedItems = browsedImages,
        currentBucketId = currentBucketId,
        selectedItemIds = selectedImageIds,
        getItemId = { it.id },
        isLargeGrid = isLargeGrid,
        maxSelectionCount = MAX_SELECTION,
        onFolderOpen = onFolderOpen,
        onFolderClose = onFolderClose,
        onToggleItem = onToggleImage,
        onDone = onDone,
        onBack = onBack,
        allItems = allImages,
        groupOrderedItems = groupOrderedItems,
        itemGridContent = { image, isSelected, onClick, onLongClick, mod ->
            ImageGridItem(
                image = image,
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
        selectedItemThumbnail = { image, onRemove ->
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
                        .clip(CircleShape)
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
        },
        screenBackgroundColor = colors.screenBackground,
        topBarColor = colors.screenBackground,
        primaryTextColor = colors.listFirstText,
        secondaryTextColor = colors.listSecondText,
        trayBackgroundColor = colors.actionBarBg,
        doneButtonActiveColor = colors.primary,
        doneButtonInactiveColor = Color(0xFF3A3A3A),
        modifier = modifier
    )
}
