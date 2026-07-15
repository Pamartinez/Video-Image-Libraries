package com.videolibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.FastScrollerForGrid
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.components.VideoGridItem
import com.videolibrary.ui.theme.LocalVideoColors

@Composable
fun VideosTab(
    videos: List<VideoItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    isLoading: Boolean,
    onVideoClick: (VideoItem) -> Unit,
    onVideoLongClick: (VideoItem) -> Unit,
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    val colors = LocalVideoColors.current

    if (isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            androidx.compose.material3.CircularProgressIndicator()
        }
    } else if (videos.isEmpty()) {
        // Matching Blazor .empty-state CSS
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = colors.listSecondText
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No items",
                    fontSize = 16.sp,
                    color = colors.listFirstText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Any videos you download or record will be shown here.",
                    fontSize = 14.sp,
                    color = colors.listSecondText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    } else {
        // Grid view only
        val isLargeGrid = viewType == ViewType.GRID_LARGE
        val spacing = if (isLargeGrid) 18.dp else 12.dp
        val columnCount = if (isLargeGrid) 2 else 3

        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(columnCount),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement = Arrangement.spacedBy(spacing)
            ) {
                items(videos, key = { it.id }) { video ->
                    VideoGridItem(
                        video = video,
                        isSelected = selectedIds.contains(video.id),
                        isSelectionMode = isSelectionMode,
                        isLargeGrid = isLargeGrid,
                        onClick = { onVideoClick(video) },
                        onLongClick = { onVideoLongClick(video) },
                        modifier = Modifier.animateItem(
                            placementSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = 4000f
                            )
                        )
                    )
                }
            }
            FastScrollerForGrid(
                state = lazyGridState,
                itemCount = videos.size,
                sectionLabel = { index ->
                    val clamped = index.coerceIn(0, (videos.size - 1).coerceAtLeast(0))
                    "${clamped + 1}/${videos.size}"
                }
            )
        }
    }
}
