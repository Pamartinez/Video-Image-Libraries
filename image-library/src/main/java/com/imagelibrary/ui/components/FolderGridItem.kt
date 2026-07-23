package com.imagelibrary.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import com.example.common.data.model.FolderItem
import com.example.common.ui.components.FolderThumbnailPlaceholder as CommonFolderThumbnailPlaceholder
import com.imagelibrary.data.model.ViewType
import com.example.common.ui.components.FolderGridItem as CommonFolderGridItem

/**
 * Image-library wrapper for the shared [CommonFolderGridItem].
 * Injects [ImageThumbnail] as the thumbnail slot and translates
 * [ViewType] into the [isSmallGrid] flag.
 */
@Composable
fun FolderGridItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    modifier: Modifier = Modifier,
    viewType: ViewType = ViewType.GRID_LARGE,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero
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
        dragOffset      = dragOffset,
        thumbnailContent = {
            if (folder.latestItemUri != null) {
                ImageThumbnail(
                    contentUri         = folder.latestItemUri,
                    contentDescription = folder.name,
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                )
            } else {
                CommonFolderThumbnailPlaceholder()
            }
        }
    )
}
