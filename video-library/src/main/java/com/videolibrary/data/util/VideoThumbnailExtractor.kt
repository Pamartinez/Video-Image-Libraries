package com.videolibrary.data.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.videolibrary.data.util.FileLogger as Log

/**
 * Video thumbnail frame selection.
 *
 * Verified on-device against Samsung Gallery (single-video album): Samsung shows the video's
 * FIRST frame. Two approaches were ruled out empirically:
 *  - `ContentResolver.loadThumbnail` returns a mid-video frame here — it does NOT match Samsung.
 *  - The decompiled "15s default" also lands mid-video — worse.
 *
 * So we extract the first frame directly: embedded cover art if present, else the frame at t=0
 * (OPTION_CLOSEST_SYNC). Only if that first frame is a black/dark intro do we nudge forward a few
 * seconds — matching Samsung's dark-frame avoidance, but staying near the start (never mid-video).
 */
object VideoThumbnailExtractor {

    private const val DARK_THRESHOLD = 32f     // mean luma below this = too dark / black
    private const val FLAT_STDDEV = 10f        // luma spread below this = solid/near-blank frame

    // Near-start candidates (µs). Start at 1s (a real decoded frame) rather than 0 — the frame at
    // t=0 is frequently a black fade-in. First non-black/non-flat candidate wins, so the common
    // case is a single decode at 1s. A few more only get tried when the intro is black.
    private val EARLY_CANDIDATES_US = longArrayOf(
        1_000_000L, 2_000_000L, 3_000_000L, 5_000_000L
    )

    fun extract(context: Context, uri: Uri): Bitmap? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(context, uri)
            firstFrame(retriever)
        } catch (e: Exception) {
            Log.w("VideoThumbnailExtractor", "Failed to extract thumbnail: ${e.message}")
            null
        } finally {
            try {
                retriever.close()
            } catch (_: Exception) {
                try {
                    @Suppress("DEPRECATION")
                    retriever.release()
                } catch (_: Exception) {
                }
            }
        }
    }

    private fun firstFrame(retriever: MediaMetadataRetriever): Bitmap? {
        // Embedded cover art wins when the file has one (Samsung uses it too) — but only if it is
        // itself a usable (non-black) image.
        embeddedThumbnail(retriever)?.let { if (!isDark(it) && !isFlat(it)) return it else it.recycle() }

        val durationUs = (retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            ?.toLongOrNull() ?: 0L) * 1_000L

        // Walk near-start candidates; return the FIRST one that is neither black nor flat, so we
        // stay close to the first frame but never show a black fade-in. Keep the brightest seen as
        // a fallback in case every candidate is poor.
        var best: Bitmap? = null
        var bestScore = -1f

        fun consider(timeUs: Long): Bitmap? {
            val f = getFrame(retriever, timeUs) ?: return null
            val (mean, stddev) = frameStats(f)
            if (mean >= DARK_THRESHOLD && stddev >= FLAT_STDDEV) return f  // acceptable → caller returns
            // Not acceptable: track the best-scoring (brightest + most detail) as a fallback.
            val score = mean + stddev
            if (score > bestScore) { best?.recycle(); best = f; bestScore = score } else f.recycle()
            return null
        }

        for (t in EARLY_CANDIDATES_US) {
            if (durationUs > 0 && t >= durationUs) continue
            consider(t)?.let { best?.recycle(); return it }
        }
        // Spread-out fallbacks if the whole intro was unusable.
        if (durationUs > 0) {
            for (p in floatArrayOf(0.25f, 0.5f, 0.75f)) {
                consider((durationUs * p).toLong())?.let { best?.recycle(); return it }
            }
        }
        return best ?: try {
            retriever.getFrameAtTime(0L, MediaMetadataRetriever.OPTION_CLOSEST)
        } catch (_: Exception) {
            null
        }
    }

    // OPTION_CLOSEST decodes the actual frame near the requested time, rather than snapping to the
    // nearest keyframe — which is often a black fade-in at t=0 with CLOSEST_SYNC.
    private fun getFrame(retriever: MediaMetadataRetriever, timeUs: Long): Bitmap? = try {
        retriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST)
    } catch (_: Exception) {
        null
    }

    private fun embeddedThumbnail(retriever: MediaMetadataRetriever): Bitmap? {
        val data = try {
            retriever.getEmbeddedPicture()
        } catch (_: Exception) {
            null
        } ?: return null
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    private fun isDark(bitmap: Bitmap): Boolean = frameStats(bitmap).first < DARK_THRESHOLD
    private fun isFlat(bitmap: Bitmap): Boolean = frameStats(bitmap).second < FLAT_STDDEV

    /** Returns (mean luma, luma std-dev) sampled on an 8×8 grid. */
    private fun frameStats(bitmap: Bitmap): Pair<Float, Float> {
        val w = bitmap.width
        val h = bitmap.height
        if (w == 0 || h == 0) return 0f to 0f
        val stepX = (w / 8).coerceAtLeast(1)
        val stepY = (h / 8).coerceAtLeast(1)
        var sum = 0f
        var sumSq = 0f
        var count = 0
        var y = 0
        while (y < h) {
            var x = 0
            while (x < w) {
                val p = bitmap.getPixel(x, y)
                val r = (p shr 16) and 0xFF
                val g = (p shr 8) and 0xFF
                val b = p and 0xFF
                val luma = 0.299f * r + 0.587f * g + 0.114f * b
                sum += luma
                sumSq += luma * luma
                count++
                x += stepX
            }
            y += stepY
        }
        if (count == 0) return 0f to 0f
        val mean = sum / count
        val variance = (sumSq / count - mean * mean).coerceAtLeast(0f)
        return mean to kotlin.math.sqrt(variance)
    }
}
