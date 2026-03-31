package com.videolibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.CircularCheckIndicator
import com.videolibrary.data.model.VideoItem
import com.videolibrary.ui.theme.LocalVideoColors
import com.example.common.util.FormatUtils

/**
 * List view video item matching Blazor app.css:
 * - Thumbnail 100x75dp with 8dp radius
 * - Duration badge bottom-end
 * - Resume progress bar at bottom of thumbnail
 * - Title 16sp, file size 14sp secondary
 */
@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun VideoListItem(
    video: VideoItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    showContextMenu: Boolean = false,
    onDismissContextMenu: () -> Unit = {},
    onShareContext: (VideoItem) -> Unit = {},
    onDeleteContext: (VideoItem) -> Unit = {},
    onRenameContext: (VideoItem) -> Unit = {},
    onDetailsContext: (VideoItem) -> Unit = {},
    onSortByContext: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val colors = LocalVideoColors.current

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onClick = onClick, onLongClick = onLongClick),
            color = if (isSelected) colors.primary.copy(alpha = 0.1f)
            else Color.Transparent
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Selection checkbox (inline, left side)
                if (isSelectionMode) {
                    CircularCheckIndicator(
                        isSelected = isSelected,
                        selectedColor = LocalVideoColors.current.primary,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                // Thumbnail 100x75 with 8dp radius (matches .video-container.view-list .video-thumbnail-wrapper)
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(75.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    VideoThumbnail(
                        contentUri = video.contentUri,
                        contentDescription = video.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        iconSize = 28.dp
                    )

                    // Duration badge (bottom-end, matching .video-duration)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .background(colors.durationBg, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = FormatUtils.formatDuration(video.duration),
                            color = colors.durationText,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }

                    // Resume progress bar at bottom of thumbnail
                    if (video.resumePosition > 0 && video.duration > 0) {
                        val progress = (video.resumePosition.toFloat() / video.duration).coerceIn(0f, 1f)
                        if (progress < 0.95f) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(3.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(Color.White.copy(alpha = 0.3f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(progress)
                                        .background(colors.primary)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Video info: title + file size
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = video.title,
                        color = colors.listFirstText,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = FormatUtils.formatFileSize(ctx, video.size),
                        color = colors.listSecondText,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
            }
        }
        // Context menu
        VideoContextMenu(
            expanded = showContextMenu,
            video = video,
            onDismiss = onDismissContextMenu,
            onShare = onShareContext,
            onDelete = onDeleteContext,
            onRename = onRenameContext,
            onDetails = onDetailsContext,
            onSortBy = onSortByContext
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = if (isSelectionMode) 136.dp else 108.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}
