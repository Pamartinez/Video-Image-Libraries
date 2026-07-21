package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val FAST_SCROLL_MIN_ITEMS = 5
private const val HIDE_DELAY_MS = 6000L  // 6 seconds before auto-hide

@Composable
fun FastScrollerForGrid(
    state: LazyGridState,
    itemCount: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    blockedByOtherGesture: Boolean = false,
    sectionLabel: (Int) -> String = { "" }
) {
    if (!enabled || blockedByOtherGesture || itemCount < FAST_SCROLL_MIN_ITEMS) return

    FastScrollerOverlay(
        modifier = modifier,
        itemCount = itemCount,
        currentIndex = state.firstVisibleItemIndex,
        onScrollTo = { state.scrollToItem(it) },
        sectionLabel = sectionLabel
    )
}

@Composable
fun FastScrollerForList(
    state: LazyListState,
    itemCount: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    blockedByOtherGesture: Boolean = false,
    sectionLabel: (Int) -> String = { "" }
) {
    if (!enabled || blockedByOtherGesture || itemCount < FAST_SCROLL_MIN_ITEMS) return

    FastScrollerOverlay(
        modifier = modifier,
        itemCount = itemCount,
        currentIndex = state.firstVisibleItemIndex,
        onScrollTo = { state.scrollToItem(it) },
        sectionLabel = sectionLabel
    )
}

@Composable
private fun FastScrollerOverlay(
    itemCount: Int,
    currentIndex: Int,
    onScrollTo: suspend (Int) -> Unit,
    sectionLabel: (Int) -> String,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val thumbHeightPx = with(density) { 40.dp.toPx() }
    val collapsedWidth = 4.dp
    val expandedWidth = 12.dp

    var heightPx by remember { mutableIntStateOf(0) }
    var dragging by remember { mutableStateOf(false) }
    var dragProgress by remember { mutableFloatStateOf(0f) }
    var targetIndex by remember { mutableIntStateOf(0) }
    var isVisible by remember { mutableStateOf(false) }
    var lastScrollTime by remember { mutableStateOf(0L) }
    var prevCurrentIndex by remember { mutableIntStateOf(0) }
    val scrollCoroutine = rememberCoroutineScope()

    val normalizedCurrent = if (itemCount <= 1) 0f else currentIndex.toFloat() / (itemCount - 1).toFloat()
    val progress = if (dragging) dragProgress else normalizedCurrent.coerceIn(0f, 1f)
    val trackHeight = (heightPx - thumbHeightPx).coerceAtLeast(1f)
    val thumbY = (progress * trackHeight).roundToInt()
    
    val thumbAlpha = if (isVisible) 1f else 0f

    // Show scroller when dragging or scrolling, hide after HIDE_DELAY_MS of no scrolling.
    // The effect restarts whenever `currentIndex` changes, cancelling the previous delay so
    // the bar only hides once scrolling has been idle for the full delay.
    LaunchedEffect(dragging, currentIndex) {
        if (dragging) {
            // Always visible while dragging
            isVisible = true
            lastScrollTime = System.currentTimeMillis()
        } else {
            if (currentIndex != prevCurrentIndex) {
                // Scrolling detected (currentIndex changed)
                isVisible = true
                lastScrollTime = System.currentTimeMillis()
                prevCurrentIndex = currentIndex
            }

            targetIndex = currentIndex.coerceIn(0, (itemCount - 1).coerceAtLeast(0))

            if (isVisible) {
                delay(HIDE_DELAY_MS)
                isVisible = false
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { heightPx = it.height },
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(24.dp)
                .pointerInput(itemCount, heightPx) {
                    detectVerticalDragGestures(
                        onDragStart = { offset ->
                            dragging = true
                            isVisible = true
                            lastScrollTime = System.currentTimeMillis()
                            val raw = (offset.y - thumbHeightPx / 2f) / trackHeight
                            dragProgress = raw.coerceIn(0f, 1f)
                            targetIndex = ((itemCount - 1) * dragProgress).roundToInt().coerceIn(0, itemCount - 1)
                            scrollCoroutine.launch { onScrollTo(targetIndex) }
                        },
                        onVerticalDrag = { _, dragAmount ->
                            val delta = if (trackHeight <= 0f) 0f else dragAmount / trackHeight
                            dragProgress = (dragProgress + delta).coerceIn(0f, 1f)
                            targetIndex = ((itemCount - 1) * dragProgress).roundToInt().coerceIn(0, itemCount - 1)
                            scrollCoroutine.launch { onScrollTo(targetIndex) }
                        },
                        onDragEnd = { dragging = false },
                        onDragCancel = { dragging = false }
                    )
                }
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset { IntOffset(0, thumbY) }
                    .width(if (dragging) expandedWidth else collapsedWidth)
                    .height(40.dp)
                    .graphicsLayer { alpha = thumbAlpha }
                    .background(
                        color = if (dragging) Color(0xFF0066FF) else Color(0x99999999),
                        shape = RoundedCornerShape(999.dp)
                    )
            )
        }

    }
}
