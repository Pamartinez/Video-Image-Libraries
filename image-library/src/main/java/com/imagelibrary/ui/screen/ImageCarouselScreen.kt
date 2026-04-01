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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.ZoomableImageContainer
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.ui.components.CarouselThumbnailStrip
import com.imagelibrary.ui.components.CarouselTopBar
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
    initialBarsVisible: Boolean = false
) {
    val context = LocalContext.current
    val view = LocalView.current
    val scope = rememberCoroutineScope()

    var barsVisible by remember { mutableStateOf(initialBarsVisible) }

    // Track whether the current page image is zoomed in; disables pager swiping while true
    var isCurrentPageZoomed by remember { mutableStateOf(false) }

    // Insets controller — hide system bars on entry, restore on leave
    val insetsController = remember(view) {
        val window = (context as Activity).window
        WindowCompat.getInsetsController(window, view).apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    DisposableEffect(Unit) {
        if (initialBarsVisible) {
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

    // Keep thumbnail strip centred on the current page and reset zoom state on navigation
    LaunchedEffect(pagerState.currentPage) {
        isCurrentPageZoomed = false
        thumbnailListState.animateScrollToItem(pagerState.currentPage)
    }

    val currentImage = images.getOrNull(pagerState.currentPage)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // ── Full-screen pager ───────────────────────────────────────────
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = !isCurrentPageZoomed,
            key = { images.getOrNull(it)?.id ?: it }
        ) { page ->
            val image = images.getOrNull(page) ?: return@HorizontalPager

            ZoomableImageContainer(
                modifier = Modifier.fillMaxSize(),
                // Single-tap: toggle overlay bars (immersive mode)
                onSingleTap = {
                    barsVisible = !barsVisible
                    if (barsVisible) insetsController.show(WindowInsetsCompat.Type.systemBars())
                    else insetsController.hide(WindowInsetsCompat.Type.systemBars())
                },
                // Notify parent so it can lock/unlock the pager
                onScaleChanged = { newScale ->
                    isCurrentPageZoomed = newScale > 1f
                }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(image.contentUri)
                        .crossfade(true)
                        .build(),
                    contentDescription = image.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // ── Top bar: back button + page counter + overflow ──────────────
        CarouselTopBar(
            visible = barsVisible,
            onBack = onBack,
            currentPage = pagerState.currentPage,
            totalPages = images.size,
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
                visible = barsVisible,
                images = images,
                currentPage = pagerState.currentPage,
                thumbnailListState = thumbnailListState,
                onThumbnailClick = { index ->
                    scope.launch { pagerState.animateScrollToPage(index) }
                }
            )
            BottomActionBar(
                visible = barsVisible,
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
