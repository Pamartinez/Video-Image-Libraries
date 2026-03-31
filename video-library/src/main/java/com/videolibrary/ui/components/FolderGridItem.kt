package com.videolibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.common.data.model.FolderItem
import com.videolibrary.data.model.ViewType
import com.example.common.ui.components.FolderGridItem as CommonFolderGridItem

/**
 * Video-library wrapper for the shared [CommonFolderGridItem].
 * Injects [VideoThumbnail] as the thumbnail slot, translates
 * [ViewType] into [isSmallGrid], and wires the [FolderContextMenu].
 */
@Composable
fun FolderGridItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    viewType: ViewType = ViewType.GRID_LARGE,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    isDragging: Boolean = false,
    showContextMenu: Boolean = false,
    onDismissContextMenu: () -> Unit = {},
    onDeleteContext: (FolderItem) -> Unit = {},
    onSortByContext: () -> Unit = {}
) {
    CommonFolderGridItem(
        folder          = folder,
        isSelected      = isSelected,
        isSelectionMode = isSelectionMode,
        isSmallGrid     = viewType == ViewType.GRID_SMALL,
        modifier        = modifier,
        onClick         = onClick,
        onLongClick     = onLongClick,
        isDragging      = isDragging,
        thumbnailContent = {
            if (folder.latestItemUri != null) {
                VideoThumbnail(
                    contentUri         = folder.latestItemUri,
                    contentDescription = folder.name,
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFA3A3A3), Color(0xFFCDCDCD))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {}
            }
        },
        contextMenuContent = {
            FolderContextMenu(
                expanded  = showContextMenu,
                folder    = folder,
                onDismiss = onDismissContextMenu,
                onDelete  = onDeleteContext,
                onSortBy  = onSortByContext
            )
        }
    )
}
