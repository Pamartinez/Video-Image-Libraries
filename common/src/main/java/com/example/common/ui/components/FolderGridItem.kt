package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Common folder grid item used by both image-library and video-library.
 *
 * The caller supplies:
 *  - [thumbnailContent] — the actual thumbnail composable (ImageThumbnail /
 *    VideoThumbnail / a placeholder Box). It is rendered inside the clipped
 *    card Box; [GridItemOverlay] is stacked on top automatically.
 *  - [contextMenuContent] — optional DropdownMenu / context menu composable
 *    rendered just outside the clipped card (video-library provides this).
 *
 * Drag behaviour:
 *  - When [isDragging] is true a ghost placeholder is shown at the original
 *    grid slot and the real item becomes invisible (`alpha = 0`).
 *  - If [dragOffset] is non-zero the real item is additionally translated /
 *    scaled / shadow-elevated, matching the image-library's live-drag overlay.
 *    The video-library keeps [dragOffset] at [Offset.Zero] and renders its own
 *    external drag overlay instead.
 */
@Composable
fun FolderGridItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    modifier: Modifier = Modifier,
    isSmallGrid: Boolean = false,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    thumbnailContent: @Composable () -> Unit,
    contextMenuContent: @Composable () -> Unit = {}
) {
    val colors         = LocalLibraryColors.current
    val shape          = RoundedCornerShape(12.dp)
    val nameFontSize   = if (isSmallGrid) 11.sp else 13.sp
    val countFontSize  = if (isSmallGrid) 10.sp else 12.sp
    val hasDragTranslation = isDragging && dragOffset != Offset.Zero

    Box(modifier = modifier.then(if (isDragging) Modifier.zIndex(1f) else Modifier)) {

        // ── Ghost placeholder at the layout slot while dragging ──────────────
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

        // ── Real content — hidden while dragging (the ghost stays in place) ──
        Box(modifier = Modifier.graphicsLayer { if (isDragging) alpha = 0f }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (hasDragTranslation) Modifier.graphicsLayer {
                            translationX    = dragOffset.x
                            translationY    = dragOffset.y
                            scaleX          = 1.08f
                            scaleY          = 1.08f
                            shadowElevation = 24f
                        } else Modifier
                    )
                    .clip(shape)
                    .then(
                        if (hasDragTranslation)
                            Modifier.border(3.dp, Color(0xFF2196F3), shape)
                        else Modifier
                    )
                    .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            ) {
                thumbnailContent()

                GridItemOverlay(
                    name       = folder.name,
                    subtitle   = "${folder.itemCount}",
                    isSelected = isSelected,
                    isSelectionMode = isSelectionMode,
                    nameFontSize    = nameFontSize,
                    countFontSize   = countFontSize
                )
            }

            contextMenuContent()
        }
    }
}
