package com.imagelibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.ui.components.CircularCheckIndicator
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Grid view image item:
 * - 1:1 aspect ratio thumbnail, no rounded corners, no title
 */
@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ImageGridItem(
    image: ImageItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    isLargeGrid: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(colors.cardBackground)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        ImageThumbnail(
            contentUri = image.contentUri,
            contentDescription = image.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Selection checkbox (top-start)
        if (isSelectionMode) {
            CircularCheckIndicator(
                isSelected = isSelected,
                selectedColor = LocalImageColors.current.primary,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }

        // Selection overlay tint
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.primary.copy(alpha = 0.15f))
            )
        }
    }
}
