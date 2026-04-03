package com.example.common.ui.screen

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
import com.example.common.data.model.ViewType
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared scaffold that handles loading/empty/grid/list states for a media tab.
 *
 * - Shows a spinner only on cold start (no data yet).
 * - Shows [emptyContent] when there are no items.
 * - Shows [listContent] when [viewType] is [ViewType.LIST] (if provided).
 * - Otherwise renders [gridContent] with animated column/spacing values.
 *
 * @param crossfadeLabel     Animation label for the LIST ↔ GRID crossfade.
 * @param gridTransitionLabel Animation label for grid spacing animation.
 * @param listContent        Optional list view — pass `null` for libraries that don't use LIST view.
 * @param gridContent        Grid view composable receiving `(isLargeGrid, spacing, columnCount)`.
 */
@Composable
fun TabContentScaffold(
    isLoading: Boolean,
    isEmpty: Boolean,
    viewType: ViewType,
    modifier: Modifier = Modifier,
    crossfadeLabel: String = "viewTypeCrossfade",
    gridTransitionLabel: String = "gridSpacing",
    emptyContent: @Composable () -> Unit,
    listContent: (@Composable () -> Unit)? = null,
    gridContent: @Composable (isLargeGrid: Boolean, spacing: Dp, columnCount: Int) -> Unit
) {
    val colors = LocalLibraryColors.current

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

    val isListView = listContent != null && viewType == ViewType.LIST

    Crossfade(
        targetState   = isListView,
        animationSpec = tween(300),
        modifier      = modifier,
        label         = crossfadeLabel
    ) { showList ->
        if (showList && listContent != null) {
            listContent()
        } else {
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            // GRID_LARGE = 2 columns, GRID_SMALL = 3 columns
            val columnCount = if (isLargeGrid) 2 else 3
            val animatedSpacing by animateDpAsState(
                targetValue  = 8.dp,
                animationSpec = tween(300),
                label        = gridTransitionLabel
            )
            gridContent(isLargeGrid, animatedSpacing, columnCount)
        }
    }
}

