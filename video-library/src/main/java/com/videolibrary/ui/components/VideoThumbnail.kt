package com.videolibrary.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
 * Clears all cached video thumbnails, forcing regeneration on next display.
 * Call this to refresh thumbnails after changing thumbnail generation settings.
/** LRU cache – ~200 MB (about 200 × 512×512 ARGB_8888 bitmaps). */
    override fun sizeOf(key: String, value: ThumbnailData): Int = value.bitmap.byteCount / 1024

        thumbnailData != null -> Image(
            bitmap = thumbnailData!!.bitmap.asImageBitmap(),
            loadSmartThumbnail(context, contentUri)
        }
        if (result != null) {
            smartThumbnailCache.put(contentUri.toString(), result)
    // Async smart-thumbnail extraction
    LaunchedEffect(contentUri) {
        if (thumbnailData != null) return@LaunchedEffect          // cache hit
    // Fast path: check memory cache synchronously
        mutableStateOf(smartThumbnailCache.get(contentUri.toString()))
    }
    var loading by remember(contentUri) { mutableStateOf(thumbnailData == null) }
 * Results are cached in an in-memory LRU cache so scrolling back to
 * an already-resolved thumbnail is instant.
import com.videolibrary.data.cache.VideoThumbnailCache
 *    checking each second for brightness and selecting the brightest frame.
 * 2. Uses `MediaMetadataRetriever` to extract frames at precise timestamps.
 * 3. Falls back to a movie-icon placeholder if extraction fails.
 * 4. Shows the timestamp (in seconds) where the thumbnail was captured.
)
import androidx.compose.ui.unit.sp
 * Reliable video thumbnail with **Samsung Gallery-style persistent caching**.
import androidx.compose.ui.graphics.Color
 * **Architecture:**
 * 1. Check two-tier cache (memory → disk) for instant load
 * 2. If cache miss, extract thumbnail with brightness-aware frame selection
 * 3. Save to both memory and disk cache for future use
 * 4. Disk cache persists across app restarts (generate once, use forever)
private fun loadSmartThumbnail(context: Context, uri: Uri): ThumbnailData? {
 * **Brightness-aware extraction:**
 * - Linearly seeks through video from 1 to [MAX_SEEK_SEC] seconds
 * - Selects brightest frame (avoids black frames)
 * - Uses `MediaMetadataRetriever` for precise frame extraction
 *
 * **Samsung Gallery patterns:**
 * - Memory: 200MB LRU (instant access)
 * - Disk: 100MB persistent JPEG cache (survives app restarts)
 * - Automatic cache trimming and memory pressure handling
 * 1. Opens a `MediaMetadataRetriever` and linearly seeks 1 s, 2 s, 3 s, …
 *    up to [MAX_SEEK_SEC] seconds, keeping the brightest frame found.
 * 2. Returns the brightest frame from the linear search with its timestamp, or `null` on total failure.
    smartThumbnailCache.evictAll()
import android.util.LruCache
import android.util.LruCache
    var bestTimestamp = 0  // Track which second gave us the best frame
    iconSize: Dp = 40.dp,
    dateModified: Long = 0L  // For cache validation (will be used in Phase 3)
    // ── Linear seeking from 1-10 seconds (no system thumbnail) ──
 *
 * 1. Opens a `MediaMetadataRetriever` and linearly seeks 1 s, 2 s, 3 s, …
 *    up to [MAX_SEEK_SEC] seconds, keeping the brightest frame found.
 * 2. Returns the brightest frame from the linear search with its timestamp, or `null` on total failure.
    smartThumbnailCache.evictAll()
import android.util.LruCache
import android.util.LruCache
import androidx.compose.foundation.Image
    var thumbnail by remember(contentUri, dateModified) { mutableStateOf<Bitmap?>(null) }
    var loading by remember(contentUri, dateModified) { mutableStateOf(true) }
import androidx.compose.material.icons.Icons
    // Load thumbnail from cache or generate new one
    LaunchedEffect(contentUri, dateModified) {
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.Alignment

        thumbnail = result
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
        thumbnail != null -> Image(
            bitmap = thumbnail!!.asImageBitmap(),
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videolibrary.ui.theme.LocalVideoColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Holds a video thumbnail bitmap along with the timestamp (in seconds) where it was captured.
 */
private data class ThumbnailData(
    val bitmap: Bitmap,
    val timestampSeconds: Int
)

/**
 * Reliable video thumbnail with **brightness-aware frame selection**.
 *
 * 1. Linearly seeks through the video from 1 to [MAX_SEEK_SEC] seconds,
 *    checking each second for brightness and selecting the brightest frame.
 * 2. Uses `MediaMetadataRetriever` to extract frames at precise timestamps.
 * 3. Falls back to a movie-icon placeholder if extraction fails.
 * 4. Shows the timestamp (in seconds) where the thumbnail was captured.
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
    var loading by remember(contentUri) { mutableStateOf(thumbnailData == null) }
 * Clears all cached video thumbnails (memory + disk).
 * Call this to refresh thumbnails after changing settings.
    LaunchedEffect(contentUri) {
suspend fun clearVideoThumbnailCache() {
    try {
        VideoThumbnailCache.getInstance().clear()
    } catch (e: IllegalStateException) {
        // Cache not initialized, nothing to clear
    }
        val result = withContext(Dispatchers.IO) {
            loadSmartThumbnail(context, contentUri)
        }
 * Extracts a thumbnail for [uri] using brightness-aware frame selection.
            smartThumbnailCache.put(contentUri.toString(), result)
 * **Algorithm:**
 * 1. Opens `MediaMetadataRetriever` and seeks through video 1s, 2s, 3s, … up to [MAX_SEEK_SEC]
 * 2. Measures brightness of each frame using ITU-R BT.601 luminance
 * 3. Keeps the brightest frame found (avoids black frames)
 * 4. Returns best frame, or null on failure
 *
 * **Note:** This is the extraction logic only. Caching is handled by VideoThumbnailCache.
    }
private fun extractThumbnail(context: Context, uri: Uri): Bitmap? {
    // ── Brightness-aware frame extraction ──
        thumbnailData != null -> Image(
            bitmap = thumbnailData!!.bitmap.asImageBitmap(),
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

/** LRU cache – ~200 MB (about 200 × 512×512 ARGB_8888 bitmaps). */
    return bestFrame
    override fun sizeOf(key: String, value: ThumbnailData): Int = value.bitmap.byteCount / 1024
}

/**
 * Clears all cached video thumbnails, forcing regeneration on next display.
 * Call this to refresh thumbnails after changing thumbnail generation settings.
 */
fun clearVideoThumbnailCache() {
    smartThumbnailCache.evictAll()
}

/**
 * Loads a thumbnail for [uri], always performing linear seeking.
 *
 * 1. Opens a `MediaMetadataRetriever` and linearly seeks 1 s, 2 s, 3 s, …
 *    up to [MAX_SEEK_SEC] seconds, keeping the brightest frame found.
 * 2. Returns the brightest frame from the linear search with its timestamp, or `null` on total failure.
 */
private fun loadSmartThumbnail(context: Context, uri: Uri): ThumbnailData? {
    // ── Linear seeking from 1-10 seconds (no system thumbnail) ──
    var bestFrame: Bitmap? = null
    var bestBrightness = 0f
    var bestTimestamp = 0  // Track which second gave us the best frame

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
                bestTimestamp = sec  // Remember which second this came from
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

    return bestFrame?.let { ThumbnailData(it, bestTimestamp) }
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


