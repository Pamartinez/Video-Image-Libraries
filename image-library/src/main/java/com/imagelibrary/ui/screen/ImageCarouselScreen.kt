package com.imagelibrary.ui.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.request.ImageRequest
import com.example.common.ui.components.BottomActionBar
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.ui.components.CarouselThumbnailStrip
import com.imagelibrary.ui.components.CarouselTopBar
import me.saket.telephoto.zoomable.coil.ZoomableAsyncImage
import me.saket.telephoto.zoomable.rememberZoomableImageState
import me.saket.telephoto.zoomable.rememberZoomableState
import kotlinx.coroutines.launch

/**
 * Samsung Gallery–style full-screen image carousel.
 *
 * - HorizontalPager for swiping between images
 * - Tap to toggle overlay (immersive mode + UI bars)
 * - Top bar  : back button + page counter (e.g. "3 / 15") + overflow menu
 * - Middle   : thumbnail filmstrip (synced to current page, Samsung Gallery specs)
 * - Bottom   : pill action bar with Share, Edit, Delete and More
 * - Pinch-to-zoom / double-tap zoom via ZoomableImageContainer
 * - Back press exits the carousel
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarouselScreen(
    images: List<ImageItem>,
    initialIndex: Int,
    onBack: () -> Unit,
    onShare: (ImageItem) -> Unit = {},
    onDelete: (ImageItem) -> Unit = {},
    onCopy: (ImageItem) -> Unit = {},
    onMove: (ImageItem) -> Unit = {},
    onDetails: (ImageItem) -> Unit = {},
    onOpenLocation: (ImageItem) -> Unit = {},
    initialBarsVisible: Boolean = false,
    alwaysHideBottomOverlay: Boolean = false,
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    onPageChanged: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    val view = LocalView.current
    val scope = rememberCoroutineScope()

    // Separate visibility states for top bar and bottom components
    var topBarVisible by remember { mutableStateOf(initialBarsVisible) }
    // Bottom overlay is hidden if alwaysHideBottomOverlay is true
    var bottomBarVisible by remember { mutableStateOf(initialBarsVisible && !alwaysHideBottomOverlay) }

    // Insets controller — hide system bars on entry, restore on leave
    val insetsController = remember(view) {
        val window = (context as Activity).window
        WindowCompat.getInsetsController(window, view).apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    DisposableEffect(Unit) {
        if (topBarVisible) {
            insetsController.show(WindowInsetsCompat.Type.systemBars())
        } else {
            insetsController.hide(WindowInsetsCompat.Type.systemBars())
        }
        onDispose { insetsController.show(WindowInsetsCompat.Type.systemBars()) }
    }

    BackHandler { onBack() }

    val pagerState = rememberPagerState(
        initialPage = initialIndex.coerceIn(0, (images.size - 1).coerceAtLeast(0))
    ) { images.size }

    val thumbnailListState = rememberLazyListState()

    // Report the active page IMMEDIATELY on every change, decoupled from the thumbnail-strip
    // animation. Previously onPageChanged ran only AFTER a suspending animateScrollToItem, so a
    // quick swipe-then-back cancelled this effect before the page was reported — the grid then
    // returned to the originally-opened image instead of the last-viewed one (the tracking bug).
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page -> onPageChanged(page) }
    }
    // Keep thumbnail strip centred on the current page (may suspend; must not gate onPageChanged).
    LaunchedEffect(pagerState.currentPage) {
        thumbnailListState.animateScrollToItem(pagerState.currentPage)
    }

    val currentImage = images.getOrNull(pagerState.currentPage)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // ── Full-screen pager ───────────────────────────────────────────
        // Telephoto's ZoomableAsyncImage integrates with HorizontalPager: at min zoom
        // it hands horizontal swipes to the pager; while zoomed it consumes them for
        // panning and only releases at the pan boundary. So userScrollEnabled stays on.
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { images.getOrNull(it)?.id ?: it }
        ) { page ->
            val image = images.getOrNull(page) ?: return@HorizontalPager

            val zoomableState = rememberZoomableState()
            val imageState = rememberZoomableImageState(zoomableState)
            val isCurrentPage = page == pagerState.currentPage

            // Reset zoom when this page is scrolled away so it re-opens un-zoomed.
            LaunchedEffect(isCurrentPage) {
                if (!isCurrentPage) zoomableState.resetZoom(androidx.compose.animation.core.snap())
            }

            ZoomableAsyncImage(
                model = ImageRequest.Builder(context)
                    // Cache key includes dateModified so Samsung Gallery edits
                    // (same URI, bumped mtime) bypass the stale Coil cache entry.
                    .data(image.contentUri)
                    .run {
                        val key = if (image.dateModified > 0L)
                            "${image.contentUri}_${image.dateModified}"
                        else image.contentUri.toString()
                        memoryCacheKey(key).diskCacheKey(key)
                    }
                    .crossfade(true)
                    .build(),
                contentDescription = image.title,
                state = imageState,
                // Single-tap: toggle overlay bars (immersive mode)
                onClick = {
                    topBarVisible = !topBarVisible
                    if (!alwaysHideBottomOverlay) {
                        bottomBarVisible = !bottomBarVisible
                    }
                    if (topBarVisible) insetsController.show(WindowInsetsCompat.Type.systemBars())
                    else insetsController.hide(WindowInsetsCompat.Type.systemBars())
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // ── Top bar: back button + page counter + settings ──────────────
        CarouselTopBar(
            visible = topBarVisible,
            onBack = onBack,
            currentPage = pagerState.currentPage,
            totalPages = images.size,
            onSettings = onSettings,
            onAbout = onAbout,
            modifier = Modifier.align(Alignment.TopStart)
        )

        // ── Bottom: thumbnail filmstrip + action bar ────────────────────
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarouselThumbnailStrip(
                visible = bottomBarVisible,
                images = images,
                currentPage = pagerState.currentPage,
                thumbnailListState = thumbnailListState,
                onThumbnailClick = { index ->
                    scope.launch { pagerState.animateScrollToPage(index) }
                }
            )
            BottomActionBar(
                visible = bottomBarVisible,
                selectedCount = 1,
                onCopy    = { currentImage?.let(onCopy) },
                onMove    = { currentImage?.let(onMove) },
                onShare   = { currentImage?.let(onShare) },
                onDelete  = { currentImage?.let(onDelete) },
                onDetails = { currentImage?.let(onDetails) },
                onOpenLocation = { currentImage?.let(onOpenLocation) },
                showAllActions   = true,
                showDetails      = true,
                showShare        = true,
                showOpenLocation = true,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
