package com.example.common.ui.util

import androidx.compose.foundation.lazy.grid.LazyGridState

/**
 * Samsung Gallery–style "track the viewed image on return to the grid".
 *
 * Replicates the behavior of Samsung's `SimpleAutoScroller`, which scrolls the grid with
 * `RecyclerView.scrollToPosition(position)`: it brings the target item into view with the
 * **minimal** amount of movement (aligning to the nearest edge) and does **nothing** if the
 * item is already fully visible. It never force-pins the item to the top and never centers it.
 *
 * @param dataIndex index of the item in the data list (the carousel/viewer page).
 * @param hasHeaderRow whether the grid renders a leading header cell (floating top bar).
 *   When true the layout index is offset by +1, matching how `SharedFolderDetailScreen`
 *   maps data indices to layout indices.
 */
suspend fun LazyGridState.revealItem(dataIndex: Int, hasHeaderRow: Boolean) {
    if (dataIndex < 0) return
    val layoutIndex = if (hasHeaderRow) dataIndex + 1 else dataIndex

    val layoutInfo = this.layoutInfo
    val viewportStart = layoutInfo.viewportStartOffset
    val viewportEnd = layoutInfo.viewportEndOffset
    val target = layoutInfo.visibleItemsInfo.firstOrNull { it.index == layoutIndex }

    if (target != null) {
        val itemStart = target.offset.y
        val itemEnd = itemStart + target.size.height
        val viewportHeight = viewportEnd - viewportStart
        when {
            // Already fully visible → do nothing (scrollToPosition leaves it in place).
            itemStart >= viewportStart && itemEnd <= viewportEnd -> return
            // Peeking off the top → align its top edge to the viewport top.
            itemStart < viewportStart -> scrollToItem(layoutIndex)
            // Peeking off the bottom → align its bottom edge to the viewport bottom
            // (minimal upward scroll). A negative scrollOffset places the item's top
            // at (viewportHeight - itemHeight), i.e. bottom-aligned.
            else -> scrollToItem(layoutIndex, target.size.height - viewportHeight)
        }
        return
    }

    // Not currently laid out (scrolled far away in either direction): GridLayoutManager's
    // scrollToPosition anchors it to the START, so align it to the top of the viewport.
    scrollToItem(layoutIndex)
}
