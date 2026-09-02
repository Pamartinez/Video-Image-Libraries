package com.videolibrary.data.cache

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.util.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Two-tier thumbnail cache manager with dynamic memory management.
 *
 * **Architecture (based on Samsung Gallery's CacheManager + BitmapCacheMgr):**
 * - **Tier 1:** Memory LRU cache (200MB, dynamic based on memory pressure)
 * - **Tier 2:** Disk cache (100MB, persistent across app restarts)
 * - **Write buffer:** Async disk writes prevent UI blocking
 * - **Dynamic sizing:** Responds to `onTrimMemory()` events
 *
 * **Samsung's proven patterns:**
 * - Memory cache tracks actual bitmap byte count (not item count)
 * - Automatic size adjustment on memory pressure
 * - Write buffer prevents disk I/O blocking
 * - Singleton instance for global access
 */
@Suppress("DEPRECATION")
class VideoThumbnailCache private constructor(
    context: Context
) : ComponentCallbacks2 {

    private val diskCache = VideoThumbnailDiskCache(context)
    private val cacheScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** Write buffer queue for async disk persistence */
    private val writeQueue = ConcurrentLinkedQueue<WriteRequest>()
    private var isProcessingWrites = false

    /** Current memory cache instance (can be resized dynamically) */
    @Volatile
    private var memoryCache: BitmapLruCache = createMemoryCache(MEMORY_CACHE_SIZE_NORMAL)

    companion object {
        /** Normal memory cache size: 200MB (Samsung's proven limit) */
        private const val MEMORY_CACHE_SIZE_NORMAL = 200 * 1024 // 200 MB in KB

        /** Reduced size on memory pressure: 100MB */
        private const val MEMORY_CACHE_SIZE_REDUCED = 100 * 1024 // 100 MB in KB

        /** Critical low memory size: 50MB */
        private const val MEMORY_CACHE_SIZE_CRITICAL = 50 * 1024 // 50 MB in KB

        @Volatile
        private var instance: VideoThumbnailCache? = null

        /**
         * Gets singleton instance of the cache.
         * Must be initialized with `init(context)` first.
         */
        fun getInstance(): VideoThumbnailCache {
            return instance ?: throw IllegalStateException(
                "VideoThumbnailCache not initialized. Call init(context) first."
            )
        }

        /**
         * Initializes the singleton cache instance.
         * Should be called from Application.onCreate() or ViewModel.init().
         */
        fun init(context: Context): VideoThumbnailCache {
            return instance ?: synchronized(this) {
                instance ?: VideoThumbnailCache(context.applicationContext).also {
                    instance = it
                    // Register for memory callbacks
                    context.applicationContext.registerComponentCallbacks(it)
                }
            }
        }
    }

    /**
     * Custom LRU cache that tracks bitmap byte count (Samsung's approach).
     */
    private class BitmapLruCache(maxSizeKB: Int) : LruCache<String, Bitmap>(maxSizeKB) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            // Return size in KB (matching Samsung's BitmapCacheMgr)
            return value.byteCount / 1024
        }

        override fun entryRemoved(evicted: Boolean, key: String, oldValue: Bitmap, newValue: Bitmap?) {
            // Bitmap will be GC'd automatically, no need to recycle (causes crashes if still in use)
        }
    }

    /**
     * Write request for async disk persistence.
     */
    private data class WriteRequest(
        val uri: Uri,
        val dateModified: Long,
        val bitmap: Bitmap
    )

    /**
     * Creates a new memory cache with the specified size.
     */
    private fun createMemoryCache(sizeKB: Int): BitmapLruCache {
        return BitmapLruCache(sizeKB)
    }

    /**
     * Loads a thumbnail from cache (memory → disk → null).
     *
     * **Fast path (synchronous):** Check memory cache first
     * **Slow path (async):** Load from disk and populate memory cache
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp
     * @return Cached bitmap, or null if not in any cache
     */
    suspend fun get(uri: Uri, dateModified: Long): Bitmap? {
        val key = getCacheKey(uri, dateModified)

        // Fast path: memory cache
        val memCached = memoryCache.get(key)
        if (memCached != null) {
            return memCached
        }

        // Slow path: disk cache
        val diskCached = diskCache.load(uri, dateModified)
        if (diskCached != null) {
            // Populate memory cache for next access
            memoryCache.put(key, diskCached)
            return diskCached
        }

        return null
    }

    /**
     * Checks if a thumbnail exists in any cache tier.
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp
     * @return true if thumbnail is cached (memory or disk)
     */
    fun exists(uri: Uri, dateModified: Long): Boolean {
        val key = getCacheKey(uri, dateModified)

        // Check memory first (fast)
        if (memoryCache.get(key) != null) {
            return true
        }

        // Check disk (still relatively fast - just file existence check)
        return diskCache.exists(uri, dateModified)
    }

    /**
     * Saves a thumbnail to cache (memory immediately, disk async).
     *
     * **Samsung's write pattern:**
     * - Memory cache updated immediately (synchronous)
     * - Disk write queued for async processing (non-blocking)
     *
     * @param uri Video content URI
     * @param dateModified Video modification timestamp
     * @param bitmap Thumbnail bitmap to cache
     */
    fun put(uri: Uri, dateModified: Long, bitmap: Bitmap) {
        val key = getCacheKey(uri, dateModified)

        // Immediate memory cache update
        memoryCache.put(key, bitmap)

        // Queue for async disk write
        writeQueue.offer(WriteRequest(uri, dateModified, bitmap))
        processWriteQueue()
    }

    /**
     * Processes the write queue asynchronously.
     * Uses Samsung's pattern: write buffer prevents blocking.
     */
    private fun processWriteQueue() {
        if (isProcessingWrites) return

        cacheScope.launch {
            isProcessingWrites = true

            try {
                while (true) {
                    val request = writeQueue.poll() ?: break

                    // Write to disk (may take 50-200ms per thumbnail)
                    diskCache.save(request.uri, request.dateModified, request.bitmap)
                }
            } finally {
                isProcessingWrites = false
            }
        }
    }

    /**
     * Removes a thumbnail from all cache tiers.
     */
    suspend fun remove(uri: Uri, dateModified: Long) {
        val key = getCacheKey(uri, dateModified)
        memoryCache.remove(key)
        diskCache.remove(uri, dateModified)
    }

    /**
     * Clears all caches (memory + disk).
     */
    suspend fun clear() {
        memoryCache.evictAll()
        diskCache.clear()
    }

    /**
     * Gets cache statistics for debugging.
     */
    fun getStats(): CacheStats {
        return CacheStats(
            memorySize = memoryCache.size(),
            memoryMaxSize = memoryCache.maxSize(),
            memoryCount = memoryCache.snapshot().size,
            diskSize = diskCache.getCacheSize(),
            diskCount = diskCache.getCacheCount()
        )
    }

    /**
     * Generates cache key from URI and modification date.
     */
    private fun getCacheKey(uri: Uri, dateModified: Long): String {
        // v9: first-frame extraction (embedded cover → frame@0 → short dark-nudge). Verified against
        // Samsung on-device; loadThumbnail/15s both gave mid-video frames. Bump invalidates v8.
        return "${uri}_${dateModified}_v9"
    }

    // ── Memory Management (ComponentCallbacks2) ──────────────────────────

    override fun onTrimMemory(level: Int) {
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL,
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> {
                // Critical: reduce to 50MB and clear half of memory cache
                resizeMemoryCache(MEMORY_CACHE_SIZE_CRITICAL)
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE -> {
                // Low: reduce to 100MB
                resizeMemoryCache(MEMORY_CACHE_SIZE_REDUCED)
            }

            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND,
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                // Background: trim to current max (remove oldest entries)
                memoryCache.trimToSize(memoryCache.maxSize() / 2)
            }
        }
    }

    /**
     * Resizes memory cache dynamically (Samsung's approach).
     * Migrates hot entries to new cache instance.
     */
    private fun resizeMemoryCache(newSizeKB: Int) {
        if (newSizeKB == memoryCache.maxSize()) return

        val oldCache = memoryCache
        val newCache = createMemoryCache(newSizeKB)

        // Migrate most recently used entries (up to 50% of new size)
        val snapshot = oldCache.snapshot()
        val migrateCount = (newSizeKB / oldCache.maxSize() * snapshot.size * 0.5).toInt()

        snapshot.entries.take(migrateCount).forEach { (key, value) ->
            newCache.put(key, value)
        }

        memoryCache = newCache
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        // No action needed
    }

    override fun onLowMemory() {
        // Called when system is running critically low on memory
        onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_COMPLETE)
    }

    /**
     * Cache statistics for debugging and settings display.
     */
    data class CacheStats(
        val memorySize: Int,        // Current memory usage in KB
        val memoryMaxSize: Int,     // Max memory size in KB
        val memoryCount: Int,       // Number of items in memory
        val diskSize: Long,         // Disk cache size in bytes
        val diskCount: Int          // Number of files on disk
    ) {
        val memorySizeMB: Int get() = memorySize / 1024
        val memoryMaxSizeMB: Int get() = memoryMaxSize / 1024
        val diskSizeMB: Long get() = diskSize / 1024 / 1024
    }
}


