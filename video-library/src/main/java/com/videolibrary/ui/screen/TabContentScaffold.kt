package com.videolibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.common.data.model.ViewType
import com.example.common.ui.screen.TabContentScaffold as CommonTabContentScaffold

/**
 * Video-library wrapper around [CommonTabContentScaffold].
 * Video library supports LIST view via the optional [listContent] parameter.
 */
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
    CommonTabContentScaffold(
        isLoading           = isLoading,
        isEmpty             = isEmpty,
        viewType            = viewType,
        modifier            = modifier,
        crossfadeLabel      = crossfadeLabel,
        gridTransitionLabel = gridTransitionLabel,
        emptyContent        = emptyContent,
        listContent         = listContent,
        gridContent         = gridContent
    )
}
