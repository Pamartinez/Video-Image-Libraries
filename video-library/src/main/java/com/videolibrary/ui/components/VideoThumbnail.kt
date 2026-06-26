package com.videolibrary.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
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
import com.videolibrary.ui.theme.LocalVideoColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Reliable video thumbnail with **Samsung Gallery-style persistent caching**.
 *
 * **Architecture:**
 * 1. Check two-tier cache (memory → disk) for instant load
 * 2. If cache miss, extract thumbnail with brightness-aware frame selection
 * 3. Save to both memory and disk cache for future use
 * 4. Disk cache persists across app restarts (generate once, use forever)
 *
 * **Brightness-aware extraction:**
 * - Linearly seeks through video from 1 to [MAX_SEEK_SEC] seconds
 * - Selects brightest frame (avoids black frames)
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

/** Brightness threshold (0-255). Frames darker than this trigger seeking. */
private const val BRIGHTNESS_THRESHOLD = 28f

/** Maximum seconds to seek forward looking for a bright frame. */
private const val MAX_SEEK_SEC = 10

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
 * Extracts a thumbnail for [uri] using brightness-aware frame selection.
 *
 * **Algorithm:**
 * 1. Opens `MediaMetadataRetriever` and seeks through video 1s, 2s, 3s, … up to [MAX_SEEK_SEC]
 * 2. Measures brightness of each frame using ITU-R BT.601 luminance
 * 3. Keeps the brightest frame found (avoids black frames)
 * 4. Returns best frame, or null on failure
 *
 * **Note:** This is the extraction logic only. Caching is handled by VideoThumbnailCache.
 */
private fun extractThumbnail(context: Context, uri: Uri): Bitmap? {
    // ── Brightness-aware frame extraction ──
    var bestFrame: Bitmap? = null
    var bestBrightness = 0f

    val retriever = MediaMetadataRetriever()
    try {
        retriever.setDataSource(context, uri)

        val durationMs = retriever.extractMetadata(
            MediaMetadataRetriever.METADATA_KEY_DURATION
        )?.toLongOrNull() ?: return null

        for (sec in 1..MAX_SEEK_SEC) {
            val timeUs = sec * 1_000_000L
            if (timeUs > durationMs * 1_000L) break       // past end of video

            val frame = retriever.getFrameAtTime(
                timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC
            ) ?: continue

            val brightness = averageBrightness(frame)

            if (brightness > bestBrightness) {
                // Keep the brighter frame, recycle the old one
                bestFrame?.recycle()
                bestFrame = frame
                bestBrightness = brightness
            } else {
                frame.recycle()
            }

            if (bestBrightness >= BRIGHTNESS_THRESHOLD) break   // good enough
        }
    } catch (_: Exception) {
        // return whatever we have
    } finally {
        try {
            retriever.close()
        } catch (_: Exception) {
            try { @Suppress("DEPRECATION") retriever.release() } catch (_: Exception) {}
        }
    }

    return bestFrame
}

// ── Brightness measurement ─────────────────────────────────────────────

/**
 * Samples an 8×8 grid of pixels and returns the perceived brightness
 * (ITU-R BT.601 luminance, 0–255).  Very fast — only 64 pixel reads.
 */
private fun averageBrightness(bitmap: Bitmap): Float {
    val w = bitmap.width
    val h = bitmap.height
    if (w == 0 || h == 0) return 0f

    val stepX = (w / 8).coerceAtLeast(1)
    val stepY = (h / 8).coerceAtLeast(1)
    var sum = 0f
    var count = 0

    var y = 0
    while (y < h) {
        var x = 0
        while (x < w) {
            val pixel = bitmap.getPixel(x, y)
            val r = (pixel shr 16) and 0xFF
            val g = (pixel shr 8) and 0xFF
            val b = pixel and 0xFF
            sum += 0.299f * r + 0.587f * g + 0.114f * b
            count++
            x += stepX
        }
        y += stepY
    }

    return if (count > 0) sum / count else 0f
}


