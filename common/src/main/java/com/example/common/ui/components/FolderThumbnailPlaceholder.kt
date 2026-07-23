package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Shared placeholder shown in a folder card when the folder has no previewable item
 * (i.e. [com.example.common.data.model.FolderItem.latestItemUri] is null).
 *
 * Used by image-library, video-library and gallery-transfer-library so the empty-folder
 * preview looks identical across all apps. The media-specific thumbnail (ImageThumbnail /
 * VideoThumbnail / Coil loader) is still supplied by each app in the non-null branch.
 */
@Composable
fun FolderThumbnailPlaceholder(
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(Color(0xFF2A2A3A), Color(0xFF1A1A2E)),
    showFolderIcon: Boolean = true,
    iconTint: Color = Color.White.copy(alpha = 0.25f)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .background(brush = Brush.linearGradient(colors = gradientColors)),
        contentAlignment = Alignment.Center
    ) {
        if (showFolderIcon) {
            Icon(
                imageVector = Icons.Filled.Folder,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = iconTint
            )
        }
    }
}
