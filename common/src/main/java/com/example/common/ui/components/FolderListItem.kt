package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.data.model.FolderItem
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Common folder list item used by both image-library and video-library.
 *
 * The caller supplies [thumbnailContent] — a composable that renders the
 * 72×72 thumbnail (ImageThumbnail / VideoThumbnail / placeholder).
 *
 * The optional [contextMenuContent] slot lets each library overlay its own
 * context menu (video-library uses this; image-library passes nothing).
 *
 * Drag behaviour mirrors the video-library's existing FolderListItem:
 * - When [isDragging] the item is translated/scaled via [dragOffset] and
 *   a ghost placeholder is shown underneath.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FolderListItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    isDragging: Boolean = false,
    dragOffset: Offset = Offset.Zero,
    modifier: Modifier = Modifier,
    thumbnailContent: @Composable (thumbModifier: Modifier) -> Unit
) {
    val colors    = LocalLibraryColors.current
    val itemShape = RoundedCornerShape(12.dp)

    Box(modifier = modifier.then(if (isDragging) Modifier.zIndex(1f) else Modifier)) {

        // ── Actual content (translated + scaled when dragging) ──
        Surface(
            modifier = Modifier
                .then(
                    if (isDragging) Modifier.graphicsLayer {
                        translationX = dragOffset.x
                        translationY = dragOffset.y
                        scaleX = 1.05f
                        scaleY = 1.05f
                    } else Modifier
                )
                .fillMaxWidth()
                .then(
                    if (isDragging) Modifier.border(3.dp, Color(0xFF2196F3), itemShape)
                    else Modifier
                )
                .clip(itemShape)
                .combinedClickable(onClick = onClick, onLongClick = onLongClick),
            color = if (isSelected) colors.primary.copy(alpha = 0.1f) else Color.Transparent
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Selection checkbox
                if (isSelectionMode) {
                    CircularCheckIndicator(
                        isSelected    = isSelected,
                        selectedColor = colors.primary,
                        modifier      = Modifier.padding(end = 8.dp)
                    )
                }

                // Thumbnail 72×72 with 12dp radius
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFA3A3A3), Color(0xFFCDCDCD))
                            )
                        )
                ) {
                    thumbnailContent(Modifier.fillMaxSize())
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Folder name + item count
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text     = folder.name,
                        color    = colors.listFirstText,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text     = "${folder.itemCount}",
                        color    = colors.listSecondText,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
            }
        }

        // ── Ghost placeholder at layout position when dragging ──
        if (isDragging) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(itemShape)
                    .background(colors.listSecondText.copy(alpha = 0.08f))
                    .border(1.5.dp, colors.listSecondText.copy(alpha = 0.18f), itemShape)
            )
        }
    }
}

