package com.imagelibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.ui.components.FolderListItem as CommonFolderListItem

/**
 * Image-library wrapper for the shared [CommonFolderListItem].
 * Injects [ImageThumbnail] as the thumbnail slot.
 */
@Composable
fun FolderListItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero,
    modifier: Modifier = Modifier
) {
    CommonFolderListItem(
        folder           = folder,
        isSelected       = isSelected,
        isSelectionMode  = isSelectionMode,
        onClick          = onClick,
        onLongClick      = onLongClick,
        isDragging       = isDragging,
        dragOffset       = dragOffset,
        modifier         = modifier,
        thumbnailContent = { thumbModifier ->
            if (folder.latestItemUri != null) {
                ImageThumbnail(
                    contentUri         = folder.latestItemUri,
                    contentDescription = folder.name,
                    contentScale       = ContentScale.Crop,
                    modifier           = thumbModifier,
                    iconSize           = 28.dp
                )
            }
        }
    )
}
