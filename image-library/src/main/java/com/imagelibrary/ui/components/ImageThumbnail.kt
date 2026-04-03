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
 *
 * @param dateModified  MediaStore DATE_MODIFIED value (seconds since epoch).
 *   When non-zero it is appended to the Coil memory- and disk-cache keys so
 *   that an in-place edit (e.g. Samsung Gallery retouch) — which keeps the same
 *   content:// URI but bumps DATE_MODIFIED — causes Coil to discard the stale
 *   cached thumbnail and reload the updated image immediately.
 */
@Composable
fun ImageThumbnail(
    contentUri: Uri?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    iconSize: Dp = 40.dp,
    dateModified: Long = 0L
) {
    val context = LocalContext.current
    val colors = LocalImageColors.current

    if (contentUri == null) {
        ThumbnailPlaceholder(modifier, colors.cardBackground, iconSize, colors.listSecondText)
        return
    }

    // Build a cache key that includes dateModified so any edit to the file
    // (same URI, different mtime) bypasses the stale Coil cache entry.
    val cacheKey = if (dateModified > 0L) "${contentUri}_$dateModified" else contentUri.toString()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(contentUri)
            .memoryCacheKey(cacheKey)
            .diskCacheKey(cacheKey)
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
