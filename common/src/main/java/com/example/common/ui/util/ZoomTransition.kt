package com.example.common.ui.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.util.lerp

/**
 * Samsung Gallery–style "shrink / grow" shared-element transition between a grid thumbnail and the
 * full-screen viewer.
 *
 * This is a faithful port of Samsung's `SimpleShrinkHandler` / `QuickViewBackShrinkHandler`
 * (decompiled from gallery.apk): the full image is scaled + translated so its rect matches the exact
 * on-screen rect of the grid thumbnail, while a scrim fades and the corner radius morphs. Samsung
 * itself does this with an overlay `ImageView` animated by scale/translation — so we mirror that with
 * a single overlay layer rather than fighting Compose's navigation graph.
 *
 * Timings copied 1:1 from Samsung's back-shrink:
 *  - duration = 300 ms (StatusCodes.INPUT_MISSING)
 *  - interpolator = PathInterpolator(0.22, 0.25, 0.0, 1.0)  (emphasized decelerate)
 */
val SamsungZoomEasing = CubicBezierEasing(0.22f, 0.25f, 0.0f, 1.0f)
const val SamsungZoomDurationMs = 300

@Stable
class ZoomTransitionState {
    /**
     * Latest on-screen (window-space) bounds of each tracked cell, keyed by the item's stable key.
     * Intentionally a plain map (not snapshot state): it is written on every layout pass and only
     * read imperatively at open/close time, so it must not trigger recomposition.
     */
    private val bounds = HashMap<Any, Rect>()

    /** 0f = collapsed onto the thumbnail, 1f = full-screen. */
    internal val progress = Animatable(1f)

    internal var activeKey by mutableStateOf<Any?>(null)
        private set
    internal var sourceRect by mutableStateOf<Rect?>(null)
        private set
    internal var cornerRadiusPx by mutableStateOf(0f)
        private set
    internal var model by mutableStateOf<Any?>(null)
        private set

    /**
     * Aspect ratio (width / height) of the image being animated. Samsung animates the image between
     * the grid cell and its actual on-screen *display rect* (the letter-boxed Fit bounds), not the
     * whole screen — this ratio lets us reconstruct that display rect. 0f = unknown (falls back to
     * the full container).
     */
    internal var aspectRatio by mutableStateOf(0f)
        private set

    /** The cell currently being animated is hidden so it never shows underneath the overlay. */
    var hiddenKey by mutableStateOf<Any?>(null)
        private set

    val isActive: Boolean get() = activeKey != null

    fun reportBounds(key: Any, rect: Rect) { bounds[key] = rect }
    fun removeBounds(key: Any) { bounds.remove(key) }
    fun isHidden(key: Any): Boolean = hiddenKey == key

    /** Grow from the thumbnail cell (or screen center if the cell isn't tracked) to full-screen. */
    suspend fun animateOpen(key: Any, model: Any, cornerRadiusPx: Float, aspectRatio: Float = 0f) {
        this.model = model
        this.cornerRadiusPx = cornerRadiusPx
        this.aspectRatio = aspectRatio
        this.sourceRect = bounds[key]
        this.hiddenKey = key
        this.activeKey = key
        progress.snapTo(0f)
        progress.animateTo(1f, tween(SamsungZoomDurationMs, easing = SamsungZoomEasing))
    }

    /** Shrink into an already-laid-out cell (used when the grid stays composed under the viewer). */
    suspend fun animateClose(key: Any, model: Any, cornerRadiusPx: Float, aspectRatio: Float = 0f) {
        this.model = model
        this.cornerRadiusPx = cornerRadiusPx
        this.aspectRatio = aspectRatio
        this.hiddenKey = key
        this.activeKey = key
        progress.snapTo(1f)
        this.sourceRect = awaitBounds(key, 500)
        progress.animateTo(0f, tween(SamsungZoomDurationMs, easing = SamsungZoomEasing))
        finish()
    }

    /**
     * Show the full-screen overlay immediately, before the destination grid has re-laid out
     * (used by the image-library carousel, which replaces the grid in composition). Call
     * [finishClose] once navigation is back on the grid.
     */
    fun beginClose(key: Any, model: Any, cornerRadiusPx: Float, aspectRatio: Float = 0f) {
        this.model = model
        this.cornerRadiusPx = cornerRadiusPx
        this.aspectRatio = aspectRatio
        this.sourceRect = null
        this.hiddenKey = key
        this.activeKey = key
    }

    /** Wait for the destination cell to lay out, then shrink the overlay into it. */
    suspend fun finishClose(key: Any) {
        progress.snapTo(1f)
        this.sourceRect = awaitBounds(key, 700)
        progress.animateTo(0f, tween(SamsungZoomDurationMs, easing = SamsungZoomEasing))
        finish()
    }

    /** Clear the overlay once the destination viewer is on screen (after [animateOpen]). */
    fun finish() {
        activeKey = null
        model = null
        sourceRect = null
        hiddenKey = null
        aspectRatio = 0f
    }

    private suspend fun awaitBounds(key: Any, timeoutMs: Long): Rect? {
        val start = withFrameNanos { it }
        var now = start
        while (bounds[key] == null && (now - start) / 1_000_000L < timeoutMs) {
            now = withFrameNanos { it }
        }
        return bounds[key]
    }
}

@Composable
fun rememberZoomTransitionState(): ZoomTransitionState = remember { ZoomTransitionState() }

/**
 * Attach to a grid thumbnail cell: reports its window-space bounds for the transition and hides it
 * (alpha 0) while it is the animation's source/destination so no duplicate shows under the overlay.
 */
fun Modifier.zoomThumbnail(key: Any, state: ZoomTransitionState): Modifier = composed {
    DisposableEffect(key, state) { onDispose { state.removeBounds(key) } }
    graphicsLayer { alpha = if (state.isHidden(key)) 0f else 1f }
        .onGloballyPositioned { state.reportBounds(key, it.boundsInWindow()) }
}

/**
 * Full-screen overlay that renders the growing/shrinking image plus the fading scrim. Place it at
 * the very top of the z-order (after all other content). [content] supplies the image for the given
 * model — use the same source you use in the viewer so the end of the grow matches perfectly.
 *
 * Faithful to Samsung's `SimpleShrinkHandler`: the image is drawn center-cropped ("cover") into a
 * rect that is interpolated between the grid cell and the image's on-screen **display rect** (the
 * letter-boxed Fit bounds). Because the display rect matches the image's own aspect ratio, the
 * cover-crop at that end is an exact no-op (so it lines up perfectly with the Fit viewer — no pop),
 * while at the cell end it becomes a square center-crop that matches the grid thumbnail.
 */
@Composable
fun ZoomTransitionOverlay(
    state: ZoomTransitionState,
    scrimColor: Color = Color.Black,
    content: @Composable (model: Any) -> Unit
) {
    val model = state.model
    if (!state.isActive || model == null) return

    var origin by remember { mutableStateOf(Offset.Zero) }
    var containerSize by remember { mutableStateOf(Size.Zero) }

    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val b = it.boundsInWindow()
                origin = b.topLeft
                containerSize = b.size
            }
    ) {
        val cw = containerSize.width
        val ch = containerSize.height
        if (cw <= 0f || ch <= 0f) return@Box

        // The image's on-screen display rect (letter-boxed Fit bounds within the container). This is
        // the "full" anchor Samsung animates from/to — not the whole screen.
        val aspect = state.aspectRatio
        val dispW: Float
        val dispH: Float
        if (aspect > 0f) {
            val containerAspect = cw / ch
            if (aspect >= containerAspect) { dispW = cw; dispH = cw / aspect }
            else { dispH = ch; dispW = ch * aspect }
        } else { dispW = cw; dispH = ch }
        val dispLeftLocal = (cw - dispW) / 2f      // container-local top-left of the display rect
        val dispTopLocal = (ch - dispH) / 2f
        val density = LocalDensity.current
        val dispWDp = with(density) { dispW.toDp() }
        val dispHDp = with(density) { dispH.toDp() }

        // Scrim — progress read inside drawBehind so it never triggers recomposition.
        Box(
            Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(scrimColor.copy(alpha = state.progress.value.coerceIn(0f, 1f)))
                }
        )

        // Content box is laid out ONCE at the display-rect size (this never changes during the
        // animation), so the grow/shrink is a pure GPU transform via graphicsLayer — zero per-frame
        // relayout, which is what keeps it perfectly smooth. All state reads are deferred into the
        // graphicsLayer lambdas (draw phase), so there's no per-frame recomposition either.
        //
        // OUTER layer scales the display-rect box (non-uniformly) into the interpolated rect and
        // clips to it; INNER layer counter-corrects so the center-cropped image keeps a single
        // uniform "cover" scale and never distorts.
        Box(
            Modifier
                .size(dispWDp, dispHDp)
                .graphicsLayer {
                    val p = state.progress.value.coerceIn(0f, 1f)
                    val src = state.sourceRect
                        ?: Rect(origin.x + cw / 2f, origin.y + ch / 2f, origin.x + cw / 2f, origin.y + ch / 2f)
                    val curW = lerp(src.width, dispW, p)
                    val curH = lerp(src.height, dispH, p)
                    val curLLocal = lerp(src.left - origin.x, dispLeftLocal, p)
                    val curTLocal = lerp(src.top - origin.y, dispTopLocal, p)
                    transformOrigin = TransformOrigin(0f, 0f)
                    scaleX = if (dispW > 0f) curW / dispW else 1f
                    scaleY = if (dispH > 0f) curH / dispH else 1f
                    translationX = curLLocal
                    translationY = curTLocal
                    clip = true
                    shape = RoundedCornerShape(state.cornerRadiusPx)
                }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val p = state.progress.value.coerceIn(0f, 1f)
                        val src = state.sourceRect
                            ?: Rect(origin.x + cw / 2f, origin.y + ch / 2f, origin.x + cw / 2f, origin.y + ch / 2f)
                        val sx = if (dispW > 0f) lerp(src.width, dispW, p) / dispW else 1f
                        val sy = if (dispH > 0f) lerp(src.height, dispH, p) / dispH else 1f
                        val coverScale =
                            if (dispW > 0f && dispH > 0f) maxOf(src.width / dispW, src.height / dispH)
                            else 1f
                        val scale = lerp(coverScale, 1f, p)
                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                        scaleX = if (sx > 0.0001f) scale / sx else 1f
                        scaleY = if (sy > 0.0001f) scale / sy else 1f
                    }
            ) {
                content(model)
            }
        }
    }
}

