package com.example.common.ui.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize

/**
 * Wraps any composable content with Samsung Gallery–style pinch-to-zoom.
 *
 *  • **Pinch**      → smooth zoom between 1× and [maxScale] with simultaneous pan
 *  • **Double-tap** → toggle between 1× and [doubleTapScale]
 *  • **Pan**        → drag to pan while zoomed in (offset clamped to image bounds)
 *  • **Single-tap** → forwarded to [onSingleTap] (fires after double-tap timeout)
 *  • **Pager-safe** → single-finger drag at scale = 1 is NOT consumed,
 *                     so a parent `HorizontalPager` can still handle page-swiping.
 *
 * Usage with HorizontalPager:
 * ```
 * var isZoomed by remember { mutableStateOf(false) }
 * HorizontalPager(userScrollEnabled = !isZoomed) { page ->
 *     ZoomableImageContainer(onScaleChanged = { isZoomed = it > 1f }) {
 *         AsyncImage(...)
 *     }
 * }
 * ```
 */
@Composable
fun ZoomableImageContainer(
    modifier: Modifier = Modifier,
    maxScale: Float = 5f,
    doubleTapScale: Float = 2.5f,
    onSingleTap: (() -> Unit)? = null,
    onScaleChanged: (Float) -> Unit = {},
    content: @Composable () -> Unit,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    /** Clamps [raw] offset so the zoomed image never moves outside its bounds. */
    fun clampOffset(s: Float, raw: Offset): Offset {
        val maxX = ((s - 1f) * containerSize.width / 2f).coerceAtLeast(0f)
        val maxY = ((s - 1f) * containerSize.height / 2f).coerceAtLeast(0f)
        return Offset(raw.x.coerceIn(-maxX, maxX), raw.y.coerceIn(-maxY, maxY))
    }

    Box(
        modifier = modifier
            .onSizeChanged { containerSize = it }
            // ── Single-tap (toggle UI) + Double-tap (toggle zoom) ──────
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onSingleTap?.invoke() },
                    onDoubleTap = {
                        val newScale = if (scale > 1f) 1f else doubleTapScale
                        offset = if (newScale <= 1f) Offset.Zero
                                 else clampOffset(newScale, offset)
                        scale = newScale
                        onScaleChanged(scale)
                    },
                )
            }
            // ── Pinch-to-zoom + drag-to-pan (pager-friendly) ──────────
            .pointerInput(Unit) {
                awaitEachGesture {
                    // Start tracking even if the down was already consumed
                    // (e.g. by detectTapGestures running in parallel)
                    awaitFirstDown(requireUnconsumed = false)

                    var prevFocal = Offset.Zero
                    var prevDist = 0f

                    // Process every pointer event until all fingers lift
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Main)
                        val active = event.changes.filter { it.pressed }
                        if (active.isEmpty()) break

                        if (active.size >= 2) {
                            // ── Multi-touch: pinch-zoom + simultaneous pan ──
                            val focal = active.fold(Offset.Zero) { acc, c ->
                                acc + c.position
                            } / active.size.toFloat()
                            val dist = (active[0].position - active[1].position).getDistance()

                            if (prevFocal != Offset.Zero) {
                                val zoomDelta = if (prevDist > 0f) dist / prevDist else 1f
                                val panDelta = focal - prevFocal
                                val newScale = (scale * zoomDelta).coerceIn(1f, maxScale)
                                offset = if (newScale <= 1f) Offset.Zero
                                         else clampOffset(newScale, offset + panDelta)
                                scale = newScale
                                onScaleChanged(scale)
                            }

                            prevFocal = focal
                            prevDist = dist
                            // Consume so the parent HorizontalPager cannot scroll
                            active.forEach { it.consume() }

                        } else {
                            // ── Single-touch ────────────────────────────────
                            prevFocal = Offset.Zero
                            prevDist = 0f
                            val change = active.first()

                            if (scale > 1f) {
                                // Pan while zoomed — consume to keep pager still
                                val drag = change.position - change.previousPosition
                                offset = clampOffset(scale, offset + drag)
                                change.consume()
                                onScaleChanged(scale)
                            }
                            // scale == 1 → do NOT consume so the pager can swipe pages
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                },
        ) {
            content()
        }
    }
}


