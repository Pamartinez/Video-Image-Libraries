package com.imagelibrary.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.theme.LocalImageColors

@Composable
internal fun TabContentScaffold(
    isLoading: Boolean,
    isEmpty: Boolean,
    viewType: ViewType,
    modifier: Modifier = Modifier,
    gridTransitionLabel: String,
    emptyContent: @Composable () -> Unit,
    gridContent: @Composable (isLargeGrid: Boolean, spacing: Dp, columnCount: Int) -> Unit
) {
    val colors = LocalImageColors.current

    // Only show a spinner on cold start (no data at all yet).
    // Background refreshes (delete, move, ContentObserver) are silent — the existing
    // grid stays visible so the screen never goes dark mid-operation.
    if (isLoading && isEmpty) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = colors.primary)
        }
        return
    }

    if (isEmpty) {
        emptyContent()
        return
    }

    val isLargeGrid = viewType == ViewType.GRID_LARGE
    // GRID_LARGE = 2 columns (Blazor SmallGrid: gap 24dp, padding 16dp)
    // GRID_SMALL = 3 columns (Blazor LargeGrid: gap 16dp, padding 12dp)
    val columnCount = if (isLargeGrid) 2 else 3
    val targetSpacing = if (isLargeGrid) 8.dp else 8.dp
    val animatedSpacing by animateDpAsState(
        targetValue = targetSpacing,
        animationSpec = tween(300),
        label = gridTransitionLabel
    )

    gridContent(isLargeGrid, animatedSpacing, columnCount)
}
