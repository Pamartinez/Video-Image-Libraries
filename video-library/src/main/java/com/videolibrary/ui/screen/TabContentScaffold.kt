package com.videolibrary.ui.screen

import androidx.compose.animation.Crossfade
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
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.theme.LocalVideoColors

@Composable
internal fun TabContentScaffold(
    isLoading: Boolean,
    isEmpty: Boolean,
    viewType: ViewType,
    modifier: Modifier = Modifier,
    crossfadeLabel: String,
    gridTransitionLabel: String,
    emptyContent: @Composable () -> Unit,
    listContent: @Composable () -> Unit,
    gridContent: @Composable (isLargeGrid: Boolean, spacing: Dp, columnCount: Int) -> Unit
) {
    val colors = LocalVideoColors.current

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

    val isListView = viewType == ViewType.LIST

    Crossfade(
        targetState = isListView,
        animationSpec = tween(300),
        modifier = modifier,
        label = crossfadeLabel
    ) { showList ->
        if (showList) {
            listContent()
        } else {
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            // GRID_LARGE = 2 columns, GRID_SMALL = 3 columns
            val columnCount = if (isLargeGrid) 2 else 3
            val targetSpacing = if (isLargeGrid) 8.dp else 8.dp
            val animatedSpacing by animateDpAsState(
                targetValue = targetSpacing,
                animationSpec = tween(300),
                label = gridTransitionLabel
            )

            gridContent(isLargeGrid, animatedSpacing, columnCount)
        }
    }
}
