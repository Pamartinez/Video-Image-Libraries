package com.imagelibrary.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Image thumbnail using Coil for efficient image loading.
 * Falls back to a placeholder icon if the URI is null or loading fails.
 */
@Composable
fun ImageThumbnail(
    contentUri: Uri?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    iconSize: Dp = 40.dp
) {
    val context = LocalContext.current
    val colors = LocalImageColors.current

    if (contentUri == null) {
        ThumbnailPlaceholder(modifier, colors.cardBackground, iconSize, colors.listSecondText)
        return
    }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(contentUri)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Composable
private fun ThumbnailPlaceholder(
    modifier: Modifier,
    backgroundColor: androidx.compose.ui.graphics.Color,
    iconSize: Dp,
    iconTint: androidx.compose.ui.graphics.Color
) {
    Box(
        modifier = modifier.background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Image,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = iconTint.copy(alpha = 0.45f)
        )
    }
}
