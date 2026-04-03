package com.example.common.ui.util

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ─────────────────────────────────────────────────────────────────────────────
// DragDropListState  (LazyColumn version)
// ─────────────────────────────────────────────────────────────────────────────
class DragDropListState(
    internal val listState: LazyListState,
    private var onMove: (Int, Int) -> Unit,
    private var onDragEnd: () -> Unit,
    private var onLongPressItem: ((Int) -> Unit)? = null,
    private var isInSelectionMode: () -> Boolean = { false },
    private var onEnterDragMode: (() -> Unit)? = null,
    /** List items with index < minDragIndex are excluded from drag start and swap targets (e.g. header rows). */
    private val minDragIndex: Int = 0
) {
    var draggedIndex by mutableIntStateOf(-1)
        private set
    var draggedOffset by mutableStateOf(Offset.Zero)
        private set

    private var fingerYInList  = 0f
    private var lastSwapMs     = 0L
    private val swapCooldownMs = 120L
    private var wasDragged     = false

    var autoScrollSpeed   = 0f
    var suppressNextClick = false
        internal set

    /** Reads and clears [suppressNextClick]. Returns `true` if a click was suppressed. */
    fun consumeNextClick(): Boolean {
        if (!suppressNextClick) return false
        suppressNextClick = false
        return true
    }

    val isDragActive: Boolean get() = draggedIndex >= 0 && wasDragged
    val isDragging:   Boolean get() = draggedIndex >= 0

    fun updateCallbacks(
        onMove: (Int, Int) -> Unit,
        onDragEnd: () -> Unit,
        onLongPressItem: ((Int) -> Unit)?,
        isInSelectionMode: () -> Boolean,
        @Suppress("UNUSED_PARAMETER") onEnterDragMode: (() -> Unit)?
    ) {
        this.onMove            = onMove
        this.onDragEnd         = onDragEnd
        this.onLongPressItem   = onLongPressItem
        this.isInSelectionMode = isInSelectionMode
    }

    fun onDragStart(offset: Offset) {
        listState.layoutInfo.visibleItemsInfo
            .firstOrNull { info -> offset.y.toInt() in info.offset..(info.offset + info.size) }
            ?.let { info ->
                if (info.index < minDragIndex) return@let  // skip header / non-draggable rows
                val wasAlreadyInSelection = isInSelectionMode()
                draggedIndex      = info.index
                draggedOffset     = Offset.Zero
                fingerYInList     = offset.y
                wasDragged        = false
                suppressNextClick = true
                if (!wasAlreadyInSelection) onLongPressItem?.invoke(info.index)
            }
    }

    fun onDrag(change: Offset) {
        if (draggedIndex < 0) return
        wasDragged    = true
        draggedOffset += change
        fingerYInList += change.y
        checkSwap()
    }

    fun onScrolled(consumed: Float) {
        if (!isDragActive) return
        draggedOffset = draggedOffset.copy(y = draggedOffset.y + consumed)
    }

    private fun checkSwap() {
        val nowMs = System.currentTimeMillis()
        if (nowMs - lastSwapMs < swapCooldownMs) return
        val fy = fingerYInList.toInt()
        val target = listState.layoutInfo.visibleItemsInfo.firstOrNull { info ->
            if (info.index == draggedIndex) return@firstOrNull false
            if (info.index < minDragIndex) return@firstOrNull false  // skip header / non-draggable rows
            val tCenter    = info.offset + info.size / 2
            val inBounds   = fy in info.offset..(info.offset + info.size)
            val thresh     = info.size / 4
            val pastCentre = kotlin.math.abs(fy - tCenter) < (info.size / 2 - thresh)
            inBounds && pastCentre
        }
        if (target != null) {
            lastSwapMs    = nowMs
            onMove(draggedIndex, target.index)
            draggedOffset = Offset.Zero
            draggedIndex  = target.index
        }
    }

    fun onDragEnd()    { if (wasDragged) onDragEnd.invoke(); reset() }
    fun onDragCancel() { reset() }

    private fun reset() {
        autoScrollSpeed   = 0f
        if (wasDragged) suppressNextClick = false
        draggedIndex      = -1
        draggedOffset     = Offset.Zero
        fingerYInList     = 0f
        wasDragged        = false
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DragDropGridState  (LazyVerticalGrid version)
// ─────────────────────────────────────────────────────────────────────────────
class DragDropGridState(
    internal val gridState: LazyGridState,
    private var onMove: (Int, Int) -> Unit,
    private var onDragEnd: () -> Unit,
    /** Called when the long-press gesture ends WITHOUT any drag movement (plain long-press). */
    private var onLongPressWithoutDrag: ((Int) -> Unit)? = null,
    private var isInSelectionMode: () -> Boolean = { false },
    @Suppress("UNUSED_PARAMETER")
    private var onEnterDragMode: (() -> Unit)? = null,
    /** Grid items with index < minDragIndex are excluded from drag start and swap targets (e.g. header rows). */
    private val minDragIndex: Int = 0
) {
    var draggedIndex by mutableIntStateOf(-1)
        private set

    var fingerPosInGrid by mutableStateOf(Offset.Zero)
        private set

    private var touchOffsetInItem: Offset = Offset.Zero

    val overlayPosition: Offset get() = fingerPosInGrid - touchOffsetInItem

    var capturedItemSize: IntSize? by mutableStateOf(null)
        private set

    /** Alias kept for backward compatibility. */
    val draggedItemSize: IntSize? get() = capturedItemSize

    private var lastSwapMs     = 0L
    private val swapCooldownMs = 120L
    private var wasDragged     = false

    var autoScrollSpeed   = 0f
    val isDragging:   Boolean get() = draggedIndex >= 0
    val isDragActive: Boolean get() = draggedIndex >= 0 && wasDragged

    var suppressNextClick = false
        internal set

    /** Reads and clears [suppressNextClick]. Returns `true` if a click was suppressed. */
    fun consumeNextClick(): Boolean {
        if (!suppressNextClick) return false
        suppressNextClick = false
        return true
    }

    /** Layout index of the item that was long-pressed; cleared in reset(). */
    private var pendingLongPressIndex = -1
    private var wasAlreadyInSelectionOnStart = false

    fun updateCallbacks(
        onMove: (Int, Int) -> Unit,
        onDragEnd: () -> Unit,
        onLongPressWithoutDrag: ((Int) -> Unit)?,
        isInSelectionMode: () -> Boolean,
        @Suppress("UNUSED_PARAMETER") onEnterDragMode: (() -> Unit)?
    ) {
        this.onMove                = onMove
        this.onDragEnd             = onDragEnd
        this.onLongPressWithoutDrag = onLongPressWithoutDrag
        this.isInSelectionMode     = isInSelectionMode
    }

    fun onDragStart(offset: Offset) {
        gridState.layoutInfo.visibleItemsInfo
            .firstOrNull { info ->
                offset.x.toInt() in info.offset.x..(info.offset.x + info.size.width) &&
                offset.y.toInt() in info.offset.y..(info.offset.y + info.size.height)
            }
            ?.let { info ->
                if (info.index < minDragIndex) return@let  // skip header / non-draggable rows
                wasAlreadyInSelectionOnStart = isInSelectionMode()
                draggedIndex          = info.index
                pendingLongPressIndex = info.index
                fingerPosInGrid       = offset
                touchOffsetInItem     = offset - Offset(info.offset.x.toFloat(), info.offset.y.toFloat())
                capturedItemSize      = info.size
                lastSwapMs            = 0L
                wasDragged            = false
                suppressNextClick     = true
                // Do NOT enter selection mode here — wait to see if the user actually drags.
                // Selection mode is entered in onDragEnd() only if the user did not drag.
            }
    }

    fun onDrag(change: Offset) {
        if (draggedIndex < 0) return
        wasDragged      = true
        fingerPosInGrid += change
        checkSwap()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onScrolled(consumed: Float) { /* intentionally empty */ }

    fun onDragEnd() {
        if (wasDragged) {
            onDragEnd.invoke()
        } else if (!wasAlreadyInSelectionOnStart && pendingLongPressIndex >= minDragIndex) {
            // Plain long-press (no movement): fire the selection callback now.
            onLongPressWithoutDrag?.invoke(pendingLongPressIndex)
        }
        reset()
    }
    fun onDragCancel() { reset() }

    private fun checkSwap() {
        val nowMs = System.currentTimeMillis()
        if (nowMs - lastSwapMs < swapCooldownMs) return
        val itemSize = capturedItemSize ?: return
        val op = overlayPosition
        val draggedCenterX = op.x + itemSize.width  / 2f
        val draggedCenterY = op.y + itemSize.height / 2f

        val target = gridState.layoutInfo.visibleItemsInfo.firstOrNull { info ->
            if (info.index == draggedIndex) return@firstOrNull false
            if (info.index < minDragIndex) return@firstOrNull false  // skip header / non-draggable rows
            val tCx = info.offset.x + info.size.width  / 2f
            val tCy = info.offset.y + info.size.height / 2f
            val inBoundsX   = draggedCenterX.toInt() in info.offset.x..(info.offset.x + info.size.width)
            val inBoundsY   = draggedCenterY.toInt() in info.offset.y..(info.offset.y + info.size.height)
            val threshX     = info.size.width  * 0.25f
            val threshY     = info.size.height * 0.25f
            val pastCentreX = kotlin.math.abs(draggedCenterX - tCx) < (info.size.width  / 2f - threshX)
            val pastCentreY = kotlin.math.abs(draggedCenterY - tCy) < (info.size.height / 2f - threshY)
            inBoundsX && inBoundsY && (pastCentreX || pastCentreY)
        }
        if (target != null) {
            lastSwapMs   = nowMs
            onMove(draggedIndex, target.index)
            draggedIndex = target.index
        }
    }

    private fun reset() {
        autoScrollSpeed              = 0f
        if (wasDragged) suppressNextClick = false
        draggedIndex                 = -1
        pendingLongPressIndex        = -1
        wasAlreadyInSelectionOnStart = false
        fingerPosInGrid              = Offset.Zero
        touchOffsetInItem            = Offset.Zero
        capturedItemSize             = null
        lastSwapMs                   = 0L
        wasDragged                   = false
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Composable factories
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState,
    onMove: (Int, Int) -> Unit,
    onDragEnd: () -> Unit,
    onLongPressItem: ((Int) -> Unit)? = null,
    isInSelectionMode: () -> Boolean = { false },
    onEnterDragMode: (() -> Unit)? = null,
    /** Items with list index < minDragIndex are excluded from drag start and swap targets. */
    minDragIndex: Int = 0
): DragDropListState {
    val state = remember(lazyListState) {
        DragDropListState(lazyListState, onMove, onDragEnd, onLongPressItem, isInSelectionMode, onEnterDragMode, minDragIndex)
    }
    SideEffect { state.updateCallbacks(onMove, onDragEnd, onLongPressItem, isInSelectionMode, onEnterDragMode) }
    return state
}

@Composable
fun rememberDragDropGridState(
    lazyGridState: LazyGridState,
    onMove: (Int, Int) -> Unit,
    onDragEnd: () -> Unit,
    /** Fired when the user long-presses but releases without dragging (plain long-press → select). */
    onLongPressWithoutDrag: ((Int) -> Unit)? = null,
    isInSelectionMode: () -> Boolean = { false },
    onEnterDragMode: (() -> Unit)? = null,
    /** Items with grid index < minDragIndex are excluded from drag start and swap targets. */
    minDragIndex: Int = 0
): DragDropGridState {
    val state = remember(lazyGridState) {
        DragDropGridState(lazyGridState, onMove, onDragEnd, onLongPressWithoutDrag, isInSelectionMode, onEnterDragMode, minDragIndex)
    }
    SideEffect { state.updateCallbacks(onMove, onDragEnd, onLongPressWithoutDrag, isInSelectionMode, onEnterDragMode) }
    return state
}

// ─────────────────────────────────────────────────────────────────────────────
// Modifiers
// ─────────────────────────────────────────────────────────────────────────────
fun Modifier.dragToReorderList(state: DragDropListState): Modifier =
    this.pointerInput(state) {
        coroutineScope {
            launch {
                while (true) {
                    val speed = state.autoScrollSpeed
                    if (speed != 0f && state.isDragActive) {
                        val consumed = state.listState.dispatchRawDelta(speed)
                        if (consumed != 0f) state.onScrolled(consumed)
                    }
                    delay(16L)
                }
            }
            detectDragGesturesAfterLongPress(
                onDragStart = { offset -> state.onDragStart(offset) },
                onDrag = { change, dragAmount ->
                    change.consume()
                    state.onDrag(dragAmount)
                    if (state.isDragActive) {
                        val vh   = size.height.toFloat()
                        val zone = 120.dp.toPx()
                        val y    = change.position.y
                        state.autoScrollSpeed = when {
                            y < zone -> {
                                val t    = (zone - y) / zone
                                val ramp = t * kotlin.math.sqrt(t)
                                -(ramp * 24f).coerceIn(1f, 24f)
                            }
                            y > vh - zone -> {
                                val t    = (y - (vh - zone)) / zone
                                val ramp = t * kotlin.math.sqrt(t)
                                (ramp * 24f).coerceIn(1f, 24f)
                            }
                            else -> 0f
                        }
                    }
                },
                onDragEnd    = { state.autoScrollSpeed = 0f; state.onDragEnd() },
                onDragCancel = { state.autoScrollSpeed = 0f; state.onDragCancel() }
            )
        }
    }

fun Modifier.dragToReorderGrid(state: DragDropGridState): Modifier =
    this.pointerInput(state) {
        coroutineScope {
            launch {
                while (true) {
                    val speed = state.autoScrollSpeed
                    if (speed != 0f && state.isDragActive) {
                        val consumed = state.gridState.dispatchRawDelta(speed)
                        if (consumed != 0f) state.onScrolled(consumed)
                    }
                    delay(16L)
                }
            }
            detectDragGesturesAfterLongPress(
                onDragStart = { offset -> state.onDragStart(offset) },
                onDrag = { change, dragAmount ->
                    change.consume()
                    state.onDrag(dragAmount)
                    if (state.isDragActive) {
                        val vh   = size.height.toFloat()
                        val zone = 120.dp.toPx()
                        val y    = change.position.y
                        state.autoScrollSpeed = when {
                            y < zone -> {
                                val t    = (zone - y) / zone
                                val ramp = t * kotlin.math.sqrt(t)
                                -(ramp * 24f).coerceIn(1f, 24f)
                            }
                            y > vh - zone -> {
                                val t    = (y - (vh - zone)) / zone
                                val ramp = t * kotlin.math.sqrt(t)
                                (ramp * 24f).coerceIn(1f, 24f)
                            }
                            else -> 0f
                        }
                    }
                },
                onDragEnd    = { state.autoScrollSpeed = 0f; state.onDragEnd() },
                onDragCancel = { state.autoScrollSpeed = 0f; state.onDragCancel() }
            )
        }
    }

