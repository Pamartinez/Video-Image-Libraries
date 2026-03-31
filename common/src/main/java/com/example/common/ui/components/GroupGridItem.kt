package com.example.common.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterVintage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.GroupItem
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Common group grid item used by both image-library and video-library.
 *
 * The caller supplies [previewCell] — a composable that renders a single
 * preview thumbnail for a given [Uri] (nullable = empty slot).
 * This allows each library to inject its own thumbnail composable
 * (ImageThumbnail / VideoThumbnail).
 *
 * Layout adapts to the number of preview images:
 * - 0 images : stacked cards with flower icon
 * - 1 image  : full cover
 * - 2 images : side by side (50/50 width, full height)
 * - 3 images : left half (full height) + right column (2 stacked)
 * - 4+ images: 2×2 grid
 */
@Composable
fun GroupGridItem(
    group: GroupItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    modifier: Modifier = Modifier,
    isSmallGrid: Boolean = false,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    previewCell: @Composable (uri: Uri?, cellModifier: Modifier) -> Unit
) {
    val colors        = LocalLibraryColors.current
    val nameFontSize  = if (isSmallGrid) 11.sp else 13.sp
    val countFontSize = if (isSmallGrid) 10.sp else 12.sp
    val shape         = RoundedCornerShape(12.dp)
    val gap           = 2.dp

    Box(modifier = modifier.then(if (isDragging) Modifier.zIndex(1f) else Modifier)) {

        // Ghost placeholder at original position while dragging
        if (isDragging) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .clip(shape)
                    .background(colors.listSecondText.copy(alpha = 0.08f))
                    .border(1.5.dp, colors.listSecondText.copy(alpha = 0.18f), shape)
            )
        }

        // Keep layout space but hide original slot when dragging (overlay floats above)
        Box(modifier = Modifier.graphicsLayer { if (isDragging) alpha = 0f }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isDragging) Modifier.graphicsLayer {
                            translationX  = dragOffset.x
                            translationY  = dragOffset.y
                            scaleX        = 1.08f
                            scaleY        = 1.08f
                            shadowElevation = 24f
                        } else Modifier
                    )
                    .clip(shape)
                    .then(
                        if (isDragging) Modifier.border(3.dp, Color(0xFF2196F3), shape)
                        else Modifier
                    )
                    .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            ) {
                // ── Mosaic thumbnail ──────────────────────────────────────
                val uris = group.previewUris
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f)
                ) {
                    when {
                        uris.isEmpty() -> GroupEmptyPlaceholder(Modifier.fillMaxSize())
                        uris.size == 1 -> previewCell(uris[0], Modifier.fillMaxSize())
                        uris.size == 2 -> {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(gap)
                            ) {
                                previewCell(uris[0], Modifier.weight(1f).fillMaxHeight())
                                previewCell(uris[1], Modifier.weight(1f).fillMaxHeight())
                            }
                        }
                        uris.size == 3 -> {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(gap)
                            ) {
                                previewCell(uris[0], Modifier.weight(1f).fillMaxHeight())
                                Column(
                                    modifier = Modifier.weight(1f).fillMaxHeight(),
                                    verticalArrangement = Arrangement.spacedBy(gap)
                                ) {
                                    previewCell(uris[1], Modifier.weight(1f).fillMaxWidth())
                                    previewCell(uris[2], Modifier.weight(1f).fillMaxWidth())
                                }
                            }
                        }
                        else -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(gap)
                            ) {
                                for (row in 0..1) {
                                    Row(
                                        modifier = Modifier.weight(1f).fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(gap)
                                    ) {
                                        for (col in 0..1) {
                                            previewCell(
                                                uris.getOrNull(row * 2 + col),
                                                Modifier.weight(1f).fillMaxHeight()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // ── Shared overlay: gradient + name + subtitle + selection ──
                GridItemOverlay(
                    name         = group.name,
                    subtitle     = "${group.totalItemCount}",
                    isSelected   = isSelected,
                    isSelectionMode = isSelectionMode,
                    nameFontSize = nameFontSize,
                    countFontSize = countFontSize
                )
            }
        }
    }
}

/** Stacked dark cards with a flower icon — shown when the group has no preview images. */
@Composable
private fun GroupEmptyPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF5C5C5C), Color(0xFF3A3A3A))
            )
        ),
        contentAlignment = Alignment.Center
    ) {
        // Back card (rotated right)
        Box(
            modifier = Modifier
                .fillMaxSize(0.62f)
                .offset(x = 12.dp, y = (-8).dp)
                .rotate(8f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF4A4A4A))
        )
        // Middle card
        Box(
            modifier = Modifier
                .fillMaxSize(0.62f)
                .offset(x = 4.dp, y = (-4).dp)
                .rotate(3f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF3E3E3E))
        )
        // Front card with flower icon
        Box(
            modifier = Modifier
                .fillMaxSize(0.62f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF2E2E2E)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector        = Icons.Filled.FilterVintage,
                contentDescription = null,
                modifier           = Modifier.size(36.dp),
                tint               = Color(0xFF5A5A5A)
            )
        }
    }
}

