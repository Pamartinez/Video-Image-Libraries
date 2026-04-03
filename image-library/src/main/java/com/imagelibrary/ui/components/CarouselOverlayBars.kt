package com.imagelibrary.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imagelibrary.data.model.ImageItem

/**
 * Samsung Gallery–style translucent top bar.
 *
 * Layout (matches One UI viewer):
 *   [← back]   [currentPage / totalPages]   [⋮ overflow]
 *
 * Reference Samsung Gallery dimens:
 *   action_bar_menu_item_min_size = 48 dp
 */
@Composable
fun CarouselTopBar(
    visible: Boolean,
    onBack: () -> Unit,
    currentPage: Int = 0,
    totalPages: Int = 0,
    onMoreItems: List<Pair<String, () -> Unit>> = emptyList(),
    modifier: Modifier = Modifier
) {
    var showOverflow by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit  = slideOutVertically(targetOffsetY  = { -it }) + fadeOut(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(listOf(Color(0xCC000000), Color.Transparent))
                )
                .statusBarsPadding()
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            // Back button — left
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0x44000000))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Page counter — centre
            if (totalPages > 0) {
                Text(
                    text = "${currentPage + 1} / $totalPages",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Overflow menu — right
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                IconButton(
                    onClick = { showOverflow = true },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0x44000000))
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
                if (onMoreItems.isNotEmpty()) {
                    DropdownMenu(
                        expanded = showOverflow,
                        onDismissRequest = { showOverflow = false }
                    ) {
                        onMoreItems.forEach { (label, action) ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    showOverflow = false
                                    action()
                                }
                            )
                        }
                    }
                } else {
                    // No items — dismiss immediately on recompose
                    if (showOverflow) showOverflow = false
                }
            }
        }
    }
}

/**
 * Samsung Gallery–style horizontal thumbnail filmstrip.
 *
 * Exact dimens from Samsung Gallery resources (filmstrip3 / viewer):
 *   film_strip3_view_height         = 49 dp  (outer container height)
 *   film_strip_focused_height       = 44 dp  (focused item height)
 *   film_strip_item_height          = 36 dp  (non-focused item height)
 *   film_strip_focused_image_width  = 37 dp  (focused item width)
 *   film_strip_image_width          = 31 dp  (non-focused item width)
 *   film_strip_item_corner_radius   = 2 dp
 *   film_strip_item_gap             = 4 dp
 *
 * Behaviour matching Samsung FilmStripLayoutManager + FoldAnimation + ItemSetHeightAnimation:
 *   1. Selected thumbnail is always centred in the strip (via contentPadding).
 *   2. Both width and height animate smoothly when focus changes (FoldAnimation equivalent).
 *   3. Items are vertically centred inside the 49 dp container (Alignment.CenterVertically).
 */
@Composable
fun CarouselThumbnailStrip(
    visible: Boolean,
    images: List<ImageItem>,
    currentPage: Int,
    thumbnailListState: LazyListState,
    onThumbnailClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Samsung FilmStripLayoutManager.getMidPoint() = viewWidth / 2.
    // We replicate this by setting contentPadding so that scrollToItem(n) always
    // positions item n at (screenWidth/2 - focusedWidth/2) → perfectly centred.
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val focusedWidth  = 37.dp  // film_strip_focused_image_width
    val centerPadding = (screenWidthDp - focusedWidth) / 2

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit  = slideOutVertically(targetOffsetY  = { it }) + fadeOut(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Scrim so the filmstrip is readable over any image
                .background(
                    Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC000000)))
                )
        ) {
            LazyRow(
                state = thumbnailListState,
                // centrePadding ensures animateScrollToItem(n) always centres item n
                contentPadding = PaddingValues(horizontal = centerPadding),
                horizontalArrangement = Arrangement.spacedBy(4.dp), // film_strip_item_gap
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(49.dp) // film_strip3_view_height
            ) {
                itemsIndexed(images, key = { _, img -> img.id }) { index, img ->
                    val isSelected = index == currentPage

                    // Animated width: 31 dp → 37 dp (FoldAnimation equivalent)
                    val itemWidth by animateDpAsState(
                        targetValue    = if (isSelected) 37.dp else 31.dp,
                        animationSpec  = tween(durationMillis = 150, easing = FastOutSlowInEasing),
                        label          = "film_strip_width"
                    )
                    // Animated height: 36 dp → 44 dp (ItemSetHeightAnimation equivalent)
                    val itemHeight by animateDpAsState(
                        targetValue    = if (isSelected) 44.dp else 36.dp,
                        animationSpec  = tween(durationMillis = 150, easing = FastOutSlowInEasing),
                        label          = "film_strip_height"
                    )

                    Box(
                        modifier = Modifier
                            .width(itemWidth)
                            .height(itemHeight)
                            .clip(RoundedCornerShape(2.dp)) // film_strip_item_corner_radius
                            .then(
                                if (isSelected)
                                    Modifier.border(2.dp, Color.White, RoundedCornerShape(2.dp))
                                else
                                    Modifier
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onThumbnailClick(index) }
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(img.contentUri)
                                // Include dateModified in cache key so Samsung Gallery
                                // edits (same URI, new mtime) reload the updated thumbnail.
                                .run {
                                    val key = if (img.dateModified > 0L)
                                        "${img.contentUri}_${img.dateModified}"
                                    else img.contentUri.toString()
                                    memoryCacheKey(key).diskCacheKey(key)
                                }
                                .crossfade(false)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

