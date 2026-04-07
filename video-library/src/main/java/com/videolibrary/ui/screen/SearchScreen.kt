package com.videolibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.ui.screen.SharedSearchScreen
import com.videolibrary.data.model.VideoItem
import com.videolibrary.ui.components.VideoGridItem

/**
 * Video-library search screen.
 * Delegates to [SharedSearchScreen] with video-specific configuration.
 */
@Composable
fun SearchScreen(
    query: String,
    results: List<VideoItem>,
    onQueryChange: (String) -> Unit,
    onBack: () -> Unit,
    onVideoClick: (VideoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    SharedSearchScreen(
        query         = query,
        results       = results,
        itemKey       = { it.id },
        onQueryChange = onQueryChange,
        onBack        = onBack,
        onItemClick   = onVideoClick,
        renderItem    = { video, mod ->
            VideoGridItem(
                video           = video,
                isSelected      = false,
                isSelectionMode = false,
                isLargeGrid     = false,
                onClick         = { onVideoClick(video) },
                onLongClick     = {},
                modifier        = mod
            )
        },
        placeholder = "Search videos...",
        emptyPrompt = "Search my videos",
        modifier    = modifier,
        gridPadding = 4.dp
    )
}
