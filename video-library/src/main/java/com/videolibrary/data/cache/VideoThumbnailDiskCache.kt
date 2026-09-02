package com.videolibrary.data.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.common.util.Crc64
import com.videolibrary.data.util.FileLogger as Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Samsung Gallery-style disk cache for video thumbnails.
 *
 * **Architecture (based on Samsung's NoIndexDiskCacheHelper):**
 * - 100MB max cache size (matching Samsung's 104,857,600 bytes)
 * - CRC64-based file naming for collision-free keys
 * - Automatic LRU trimming to 80% when cache exceeds limit
 * - Touch-on-access for accurate LRU tracking
 * - JPEG compression at 85% quality (~50-100KB per thumbnail)
 *
 * **File structure:**
 * ```
 * /data/data/com.videolibrary/cache/video_thumbnails/
 *   ├── A1B2C3D4E5F67890.jpg (CRC64 hash)
 *   ├── F0E9D8C7B6A54321.jpg
 *   └── .nomedia
 * ```
 */
class VideoThumbnailDiskCache(context: Context) {

    private val cacheDir: File = File(context.cacheDir, "video_thumbnails")
    private val trimMutex = Mutex()

    companion object {
        /** Maximum cache size: 100MB (matches Samsung Gallery) */
        private const val MAX_CACHE_SIZE_BYTES = 100 * 1024 * 1024L // 100 MB

        /** Trim threshold: reduce to 80% when full (Samsung's strategy) */
        private const val TRIM_TO_PERCENT = 0.80

        /** JPEG compression quality (85% - good balance of size/quality) */
        private const val JPEG_QUALITY = 85
    }

    init {
        // Create cache directory and .nomedia file
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
            try {
                File(cacheDir, ".nomedia").createNewFile()
            } catch (e: IOException) {
                Log.w("VideoThumbnailDiskCache", "Failed to create .nomedia: ${e.message}")
            }
        }
    }

    /**
     * Generates cache key for a video URI with modification timestamp.
     *
     * **Format:** CRC64(uri + "_" + dateModified)
     * Including dateModified ensures cache invalidation when video is edited.
     */
    private fun getCacheKey(uri: Uri, dateModified: Long): String {
        // NOTE: this version tag is the real thumbnail-invalidation switch. Bump it whenever the
        // extraction algorithm changes (it is independent of VideoThumbnailCache's own key). v4:
        // first-frame extraction. Previously stuck at v3, which masked all extractor changes.
        val keyString = "${uri}_${dateModified}_v6"
        return Crc64.hashToHex(keyString)
    }

    /**
     * Gets the cache file for a given URI and modification date.
     */
    private fun getCacheFile(uri: Uri, dateModified: Long): File {
        val key = getCacheKey(uri, dateModified)
        return File(cacheDir, "$key.jpg")
    }

    /**
     * Checks if a thumbnail exists in disk cache.
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp (for cache validation)
     * @return true if cached thumbnail exists and is valid
     */
    fun exists(uri: Uri, dateModified: Long): Boolean {
        return getCacheFile(uri, dateModified).exists()
    }

    /**
     * Loads a thumbnail from disk cache.
     *
     * **Samsung's pattern:**
     * - Touch file on access (update lastModified for LRU)
     * - Return null if file doesn't exist or is corrupted
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp
     * @return Bitmap thumbnail, or null if not cached or failed to load
     */
    suspend fun load(uri: Uri, dateModified: Long): Bitmap? = withContext(Dispatchers.IO) {
        val file = getCacheFile(uri, dateModified)

        if (!file.exists()) {
            return@withContext null
        }

        try {
            // Touch file for LRU tracking (Samsung's approach)
            touchFile(file)

            // Decode JPEG
            BitmapFactory.decodeFile(file.absolutePath)
        } catch (e: Exception) {
            Log.w("VideoThumbnailDiskCache", "Failed to load ${file.name}: ${e.message}")
            // Delete corrupted file
            file.delete()
            null
        }
    }

    /**
     * Saves a thumbnail to disk cache with JPEG compression.
     *
     * **Samsung's write pattern:**
     * - Compress to JPEG at 85% quality
     * - Write atomically (temp file + rename)
     * - Check cache size and trim if needed
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp
     * @param bitmap Thumbnail bitmap to save
     * @return true if saved successfully
     */
    suspend fun save(uri: Uri, dateModified: Long, bitmap: Bitmap): Boolean = withContext(Dispatchers.IO) {
        val file = getCacheFile(uri, dateModified)
        val tempFile = File(cacheDir, "${file.name}.tmp")

        try {
            // Write to temp file first (atomic write)
            FileOutputStream(tempFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, out)
                out.flush()
            }

            // Rename temp to final (atomic)
            if (tempFile.renameTo(file)) {
                // Check cache size and trim if needed
                checkAndTrimCache()
                true
            } else {
                tempFile.delete()
                false
            }
        } catch (e: Exception) {
            Log.w("VideoThumbnailDiskCache", "Failed to save ${file.name}: ${e.message}")
            tempFile.delete()
            false
        }
    }

    /**
     * Updates the last modified timestamp of a file (touch for LRU tracking).
     * Samsung's approach for maintaining accurate LRU order.
     */
    private fun touchFile(file: File) {
        try {
            file.setLastModified(System.currentTimeMillis())
        } catch (_: Exception) {
            // Ignore touch failures
        }
    }

    /**
     * Checks cache size and trims to 80% if exceeding 100MB limit.
     *
     * **Samsung's trimming strategy (from NoIndexDiskCacheHelper line 323-361):**
     * 1. Calculate total cache size
     * 2. If > max, sort files by lastModified (oldest first)
     * 3. Delete oldest files until size <= 80% of max
     * 4. Return stats (size before, size after, bytes deleted)
     */
    private suspend fun checkAndTrimCache() {
        trimMutex.withLock {
            val files = cacheDir.listFiles()?.filter { it.extension == "jpg" } ?: return

            if (files.isEmpty()) return

            // Calculate total size
            val totalSize = files.sumOf { it.length() }

            if (totalSize <= MAX_CACHE_SIZE_BYTES) {
                return // Under limit, no trim needed
            }

            // Sort by lastModified (oldest first) - Samsung's LRU strategy
            val sortedFiles = files.sortedBy { it.lastModified() }

            // Calculate target size (80% of max)
            val targetSize = (MAX_CACHE_SIZE_BYTES * TRIM_TO_PERCENT).toLong()

            var currentSize = totalSize

            // Delete oldest files until under target
            for (file in sortedFiles) {
                if (currentSize <= targetSize) {
                    break
                }

                val fileSize = file.length()
                if (file.delete()) {
                    currentSize -= fileSize
                }
            }
        }
    }

    /**
     * Gets current cache size in bytes.
     */
    fun getCacheSize(): Long {
        return cacheDir.listFiles()?.filter { it.extension == "jpg" }?.sumOf { it.length() } ?: 0L
    }

    /**
     * Gets count of cached thumbnails.
     */
    fun getCacheCount(): Int {
        return cacheDir.listFiles()?.count { it.extension == "jpg" } ?: 0
    }

    /**
     * Clears entire cache (for settings "Clear Cache" option).
     */
    suspend fun clear() = withContext(Dispatchers.IO) {
        trimMutex.withLock {
            cacheDir.listFiles()?.forEach { file ->
                if (file.name != ".nomedia") {
                    file.delete()
                }
            }
        }
    }

    /**
     * Removes a specific thumbnail from cache.
     */
    suspend fun remove(uri: Uri, dateModified: Long) = withContext(Dispatchers.IO) {
        val file = getCacheFile(uri, dateModified)
        if (file.exists()) {
            file.delete()
        }
    }
}

