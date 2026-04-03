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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.CircularCheckIndicator
import com.videolibrary.data.model.VideoItem
import com.videolibrary.ui.theme.LocalVideoColors
import com.example.common.util.FormatUtils

/**
 * Grid view video item matching Blazor app.css:
 * - 1:1 aspect ratio thumbnail with 12dp radius
 * - Duration badge bottom-end on thumbnail
 * - Resume progress bar at thumbnail bottom
 * - Title BELOW thumbnail (not overlaid), 13sp for 2-col, 11sp for 3-col
 * - Title has screen background color
 */
@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun VideoGridItem(
    video: VideoItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    isLargeGrid: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    // isLargeGrid == GRID_LARGE == 2 columns (SmallGrid in Blazor)
    // !isLargeGrid == GRID_SMALL == 3 columns (LargeGrid in Blazor)
    val titleFontSize = if (isLargeGrid) 13.sp else 11.sp
    val titlePadding = if (isLargeGrid) PaddingValues(8.dp) else PaddingValues(horizontal = 4.dp, vertical = 4.dp)
    val durationFontSize = if (isLargeGrid) 12.sp else 10.sp

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .combinedClickable(onClick = onClick, onLongClick = onLongClick)
        ) {
            // Thumbnail area — 1:1 aspect ratio
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.cardBackground)
            ) {
                VideoThumbnail(
                    contentUri = video.contentUri,
                    contentDescription = video.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Duration badge (bottom-end)
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
                        fontSize = durationFontSize,
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

                // Video type badges (HDR, 360, etc.)
                if (video.isHdr || video.is360 || video.isSlowMotion) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (video.isHdr) VideoBadge("HDR")
                        if (video.is360) VideoBadge("360°")
                        if (video.isSlowMotion) VideoBadge("SLO-MO")
                        if (video.isFastMotion) VideoBadge("FAST")
                        if (video.isHyperlapse) VideoBadge("HYPER")
                    }
                }

                // Selection checkbox (top-start)
                if (isSelectionMode) {
                    CircularCheckIndicator(
                        isSelected = isSelected,
                        selectedColor = LocalVideoColors.current.primary,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    )
                }

                // Selection overlay tint
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors.primary.copy(alpha = 0.15f))
                    )
                }
            }

            // Title BELOW thumbnail (matching .video-info .video-title in grid views)
            Text(
                text = video.title,
                color = colors.listFirstText,
                fontSize = titleFontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.screenBackground)
                    .padding(titlePadding)
            )
        }
    }
}

@Composable
fun VideoBadge(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 1.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 8.sp, maxLines = 1)
    }
}
