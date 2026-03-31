package com.videolibrary.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.LruCache
import android.util.Size
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
import com.videolibrary.ui.theme.LocalVideoColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Reliable video thumbnail with **brightness-aware frame selection**.
 *
 * 1. Tries the system-cached `ContentResolver.loadThumbnail()` first.
 * 2. If the result is too dark (fade-from-black, black intro, etc.),
 *    uses `MediaMetadataRetriever` to seek forward 1 second at a time
 *    (up to [MAX_SEEK_SEC] seconds) until a frame with acceptable
 *    brightness is found.
 * 3. Falls back to a movie-icon placeholder if everything fails.
 *
 * Results are cached in an in-memory LRU cache so scrolling back to
 * an already-resolved thumbnail is instant.
 */
@Composable
fun VideoThumbnail(
    contentUri: Uri?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    iconSize: Dp = 40.dp
) {
    val context = LocalContext.current
    val colors = LocalVideoColors.current

    if (contentUri == null) {
        ThumbnailPlaceholder(modifier, colors.cardBackground, iconSize, colors.listSecondText)
        return
    }

    // Fast path: check memory cache synchronously
    var bitmap by remember(contentUri) {
        mutableStateOf(smartThumbnailCache.get(contentUri.toString()))
    }
    var loading by remember(contentUri) { mutableStateOf(bitmap == null) }

    // Async smart-thumbnail extraction
    LaunchedEffect(contentUri) {
        if (bitmap != null) return@LaunchedEffect          // cache hit
        loading = true
        val result = withContext(Dispatchers.IO) {
            loadSmartThumbnail(context, contentUri)
        }
        if (result != null) {
            smartThumbnailCache.put(contentUri.toString(), result)
        }
        bitmap = result
        loading = false
    }

    when {
        bitmap != null -> Image(
            bitmap = bitmap!!.asImageBitmap(),
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

/** LRU cache – ~24 MB (about 24 × 512×512 ARGB_8888 bitmaps). */
private val smartThumbnailCache = object : LruCache<String, Bitmap>(24 * 1024) {
    override fun sizeOf(key: String, value: Bitmap): Int = value.byteCount / 1024
}

/**
 * Loads a thumbnail for [uri], skipping dark frames.
 *
 * 1. Gets the system thumbnail via `ContentResolver.loadThumbnail()`.
 * 2. If bright enough → returns immediately.
 * 3. Otherwise opens a `MediaMetadataRetriever` and seeks 1 s, 2 s, …
 *    up to [MAX_SEEK_SEC] seconds, keeping the brightest frame found.
 * 4. Returns the best frame, or `null` on total failure.
 */
private fun loadSmartThumbnail(context: Context, uri: Uri): Bitmap? {
    // ── Step 1: system thumbnail ──
    val systemThumb = try {
        context.contentResolver.loadThumbnail(uri, Size(512, 512), null)
    } catch (_: Exception) {
        null
    }

    val systemBrightness = systemThumb?.let { averageBrightness(it) } ?: 0f
    if (systemBrightness >= BRIGHTNESS_THRESHOLD) {
        return systemThumb                               // fast path
    }

    // ── Step 2: seek through the video for a brighter frame ──
    var bestFrame: Bitmap? = systemThumb
    var bestBrightness = systemBrightness

    val retriever = MediaMetadataRetriever()
    try {
        retriever.setDataSource(context, uri)

        val durationMs = retriever.extractMetadata(
            MediaMetadataRetriever.METADATA_KEY_DURATION
        )?.toLongOrNull() ?: return bestFrame

        for (sec in 1..MAX_SEEK_SEC) {
            val timeUs = sec * 1_000_000L
            if (timeUs > durationMs * 1_000L) break       // past end of video

            val frame = retriever.getFrameAtTime(
                timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC
            ) ?: continue

            val brightness = averageBrightness(frame)

            if (brightness > bestBrightness) {
                // Keep the brighter frame, recycle the old one
                if (bestFrame !== systemThumb) bestFrame?.recycle()
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
