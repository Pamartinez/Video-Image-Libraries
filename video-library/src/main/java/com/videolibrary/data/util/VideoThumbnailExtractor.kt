package com.videolibrary.data.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.videolibrary.data.util.FileLogger as Log

/**
 * Shared video thumbnail extraction for the video-library.
 *
 * Samsung Gallery patterns mirrored here:
 * - Prefer embedded video artwork when present
 * - Sample the frame at a fixed 1 second in (skips black intros) instead of frame 0
 * - Retry nearby frames when the first candidate is too dark
 */
object VideoThumbnailExtractor {

    private const val BRIGHTNESS_THRESHOLD = 28f
    private const val TARGET_FRAME_TIME_US = 1_000_000L
    private const val SEEK_STEP_US = 1_000_000L
    private const val MAX_NEARBY_SAMPLES = 5

    // A near-solid frame (white/gray/black) has almost no luminance spread.
    // Require a minimum standard deviation so we skip flat, single-color frames.
    private const val MIN_DETAIL_STDDEV = 12f
    // Reject frames that are almost fully white (blank/overexposed).
    private const val MAX_BRIGHTNESS = 250f

    fun extract(context: Context, uri: Uri): Bitmap? {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(context, uri)
            extractFromRetriever(retriever)
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

    private fun extractFromRetriever(retriever: MediaMetadataRetriever): Bitmap? {
        embeddedThumbnail(retriever)?.let { return it }

        val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            ?.toLongOrNull()
            ?: return null

        val baseTimeUs = resolveFrameTimeUs(durationMs)
        return sampleBrightFrame(retriever, durationMs, baseTimeUs)
    }

    private fun embeddedThumbnail(retriever: MediaMetadataRetriever): Bitmap? {
        val data = try {
            retriever.getEmbeddedPicture()
        } catch (_: Exception) {
            null
        } ?: return null

        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    private fun resolveFrameTimeUs(durationMs: Long): Long {
        if (durationMs <= 0L) return 0L
        val durationUs = durationMs * 1_000L
        return TARGET_FRAME_TIME_US.coerceIn(0L, durationUs)
    }

    private fun sampleBrightFrame(
        retriever: MediaMetadataRetriever,
        durationMs: Long,
        startTimeUs: Long
    ): Bitmap? {
        val durationUs = durationMs * 1_000L
        val candidates = buildList {
            // Preferred: fixed 1s in, then a few nearby frames to skip short black/solid intros.
            add(startTimeUs)
            for (offset in 1 until MAX_NEARBY_SAMPLES) {
                add(startTimeUs + offset * SEEK_STEP_US)
            }
            // Fallbacks spread across the video to escape long solid/title-card intros.
            add((durationUs * 0.25f).toLong())
            add((durationUs * 0.5f).toLong())
            add((durationUs * 0.75f).toLong())
        }.filter { it in 0L..durationUs }.distinct()

        // Track the most-detailed frame only as a fallback for when every candidate
        // fails the quality gates. We deliberately DO NOT return the max-detail frame
        // outright, otherwise a highly detailed mid-video frame (e.g. 25% in) would beat
        // a perfectly good early frame and the thumbnail would jump deep into the video.
        var bestFrame: Bitmap? = null
        var bestScore = -1f

        for (timeUs in candidates) {
            // OPTION_CLOSEST (not CLOSEST_SYNC) decodes the actual frame at the requested time
            // rather than snapping to the nearest keyframe. With sparse keyframes, CLOSEST_SYNC
            // made 1s/2s/3s all resolve to the black keyframe at 0s, so the thumbnail drifted to
            // the 25/50/75% fallbacks (5-10s in). CLOSEST keeps it near the requested ~1s.
            val frame = try {
                retriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST)
            } catch (_: Exception) {
                null
            } ?: continue

            val stats = calculateFrameStats(frame)

            // Skip flat frames (solid white/gray/black) and near-white blanks.
            val isFlat = stats.stdDev < MIN_DETAIL_STDDEV
            val tooDark = stats.mean < BRIGHTNESS_THRESHOLD
            val tooWhite = stats.mean > MAX_BRIGHTNESS
            val isAcceptable = !isFlat && !tooDark && !tooWhite

            // Return the FIRST acceptable frame in seek order. Because candidates are
            // ordered earliest-first (1s, 2s, 3s… before the 25/50/75% fallbacks), this
            // keeps the thumbnail near the start of the video instead of drifting to
            // whichever frame happens to have the most detail.
            if (isAcceptable) {
                bestFrame?.recycle()
                return frame
            }

            // Not acceptable: keep it only if it is the best low-quality option so far.
            if (stats.stdDev > bestScore) {
                bestFrame?.recycle()
                bestFrame = frame
                bestScore = stats.stdDev
            } else {
                frame.recycle()
            }
        }

        if (bestFrame != null) {
            return bestFrame
        }

        // Final fallback if the sampled frames failed.
        return try {
            retriever.getFrameAtTime(0L, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } catch (_: Exception) {
            null
        }
    }

    private data class FrameStats(val mean: Float, val stdDev: Float)

    private fun calculateFrameStats(bitmap: Bitmap): FrameStats {
        val w = bitmap.width
        val h = bitmap.height
        if (w == 0 || h == 0) return FrameStats(0f, 0f)

        val stepX = (w / 8).coerceAtLeast(1)
        val stepY = (h / 8).coerceAtLeast(1)
        var sum = 0f
        var sumSq = 0f
        var count = 0

        var y = 0
        while (y < h) {
            var x = 0
            while (x < w) {
                val pixel = bitmap.getPixel(x, y)
                val r = (pixel shr 16) and 0xFF
                val g = (pixel shr 8) and 0xFF
                val b = pixel and 0xFF
                val luma = 0.299f * r + 0.587f * g + 0.114f * b
                sum += luma
                sumSq += luma * luma
                count++
                x += stepX
            }
            y += stepY
        }

        if (count == 0) return FrameStats(0f, 0f)
        val mean = sum / count
        val variance = (sumSq / count - mean * mean).coerceAtLeast(0f)
        return FrameStats(mean, kotlin.math.sqrt(variance))
    }
}
