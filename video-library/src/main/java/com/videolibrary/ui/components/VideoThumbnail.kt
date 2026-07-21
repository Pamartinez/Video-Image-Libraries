package com.videolibrary.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.videolibrary.data.cache.VideoThumbnailCache
import com.videolibrary.data.util.VideoThumbnailExtractor
import com.videolibrary.ui.theme.LocalVideoColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Reliable video thumbnail with **Samsung Gallery-style persistent caching**.
 *
 * **Architecture:**
 * 1. Check two-tier cache (memory → disk) for instant load
 * 2. If cache miss, extract thumbnail with Samsung-style embedded art / frame selection
 * 3. Save to both memory and disk cache for future use
 * 4. Disk cache persists across app restarts (generate once, use forever)
 *
 * **Frame selection:**
 * - Prefers embedded artwork, then samples the frame at a fixed 1 second in
 *   (skipping black intros), scanning nearby frames if that one is too dark
 * - Uses `MediaMetadataRetriever` for precise frame extraction
 *
 * **Samsung Gallery patterns:**
 * - Memory: 200MB LRU (instant access)
 * - Disk: 100MB persistent JPEG cache (survives app restarts)
 * - Automatic cache trimming and memory pressure handling
 */
@Composable
fun VideoThumbnail(
    contentUri: Uri?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    iconSize: Dp = 40.dp,
    dateModified: Long = 0L  // For cache validation (will be used in Phase 3)
) {
    val context = LocalContext.current
    val colors = LocalVideoColors.current

    if (contentUri == null) {
        ThumbnailPlaceholder(modifier, colors.cardBackground, iconSize, colors.listSecondText)
        return
    }

    var thumbnail by remember(contentUri, dateModified) { mutableStateOf<Bitmap?>(null) }
    var loading by remember(contentUri, dateModified) { mutableStateOf(true) }

    // Load thumbnail from cache or generate new one
    LaunchedEffect(contentUri, dateModified) {
        loading = true

        val result = withContext(Dispatchers.IO) {
            try {
                // Try to get cache instance (may not be initialized yet in this phase)
                val cache = try {
                    VideoThumbnailCache.getInstance()
                } catch (e: IllegalStateException) {
                    // Cache not initialized yet, fall back to direct extraction
                    null
                }

                // Check cache first (memory + disk)
                val cached = cache?.get(contentUri, dateModified)
                if (cached != null) {
                    return@withContext cached
                }

                // Cache miss - extract new thumbnail
                val extracted = extractThumbnail(context, contentUri)

                // Save to cache for future use
                if (extracted != null && cache != null) {
                    cache.put(contentUri, dateModified, extracted)
                }

                extracted
            } catch (e: Exception) {
                null
            }
        }

        thumbnail = result
        loading = false
    }

    when {
        thumbnail != null -> Image(
            bitmap = thumbnail!!.asImageBitmap(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
        loading -> Box(modifier = modifier.background(colors.cardBackground))
        else -> ThumbnailPlaceholder(
            modifier, colors.cardBackground, iconSize, colors.listSecondText
        )
    }
}

// ── Placeholder ────────────────────────────────────────────────────────

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
            imageVector = Icons.Default.Movie,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = iconTint.copy(alpha = 0.45f)
        )
    }
}

// ── Smart thumbnail loading ────────────────────────────────────────────

/**
 * Clears all cached video thumbnails (memory + disk).
 * Call this to refresh thumbnails after changing settings.
 */
suspend fun clearVideoThumbnailCache() {
    try {
        VideoThumbnailCache.getInstance().clear()
    } catch (e: IllegalStateException) {
        // Cache not initialized, nothing to clear
    }
}

/**
 * Extracts a thumbnail for [uri] using Samsung-style embedded art and frame sampling.
 *
 * **Note:** This is the extraction logic only. Caching is handled by VideoThumbnailCache.
 */
private fun extractThumbnail(context: Context, uri: Uri): Bitmap? {
    return VideoThumbnailExtractor.extract(context, uri)
}
