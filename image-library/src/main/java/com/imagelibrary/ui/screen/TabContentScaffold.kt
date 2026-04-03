package com.imagelibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.common.data.model.ViewType
import com.example.common.ui.screen.TabContentScaffold as CommonTabContentScaffold

/**
 * Image-library wrapper around [CommonTabContentScaffold].
 * Image library never uses LIST view, so [listContent] is omitted.
 */
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
    CommonTabContentScaffold(
        isLoading           = isLoading,
        isEmpty             = isEmpty,
        viewType            = viewType,
        modifier            = modifier,
        gridTransitionLabel = gridTransitionLabel,
        emptyContent        = emptyContent,
        listContent         = null,
        gridContent         = gridContent
    )
}
