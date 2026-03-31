package com.videolibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.common.data.model.GroupItem
import com.videolibrary.data.model.ViewType
import com.example.common.ui.components.GroupGridItem as CommonGroupGridItem

/**
 * Video-library wrapper for the shared [CommonGroupGridItem].
 * Injects [VideoThumbnail] as the preview-cell slot and translates
 * [ViewType] into the [isSmallGrid] flag.
 */
@Composable
fun GroupGridItem(
    group: GroupItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    modifier: Modifier = Modifier,
    viewType: ViewType = ViewType.GRID_LARGE,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero
) {
    CommonGroupGridItem(
        group           = group,
        isSelected      = isSelected,
        isSelectionMode = isSelectionMode,
        modifier        = modifier,
        isSmallGrid     = viewType == ViewType.GRID_SMALL,
        isDragging      = isDragging,
        dragOffset      = dragOffset,
        onClick         = onClick,
        onLongClick     = onLongClick,
        previewCell     = { uri, cellModifier ->
            if (uri != null) {
                VideoThumbnail(
                    contentUri         = uri,
                    contentDescription = null,
                    contentScale       = ContentScale.Crop,
                    modifier           = cellModifier
                )
            } else {
                Box(modifier = cellModifier.background(Color(0xFF4A4A4A)))
            }
        }
    )
}
