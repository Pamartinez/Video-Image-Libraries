package com.videolibrary.data.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.videolibrary.data.cache.VideoThumbnailCache
import com.videolibrary.data.preferences.AppPreferences
import com.videolibrary.data.repository.VideoRepository
import com.videolibrary.data.util.VideoThumbnailExtractor
import com.videolibrary.data.util.FileLogger as Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

/**
 * Background worker that pre-generates video previews for EVERY non-hidden folder, so previews no
 * longer require the user to open each folder first.
 *
 * Runs as a WorkManager foreground worker (dataSync), so it keeps generating even after the app is
 * closed and resumes on the next opportunity. Thumbnails are written to the persistent disk cache,
 * so a completed pass survives restarts. Skips videos that are already cached, so re-runs are cheap.
 */
class PreviewGenerationWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val prefs = AppPreferences(applicationContext)
        if (!prefs.backgroundPreviewGenerationEnabled) return@withContext Result.success()

        val cache = VideoThumbnailCache.init(applicationContext)
        val repo = VideoRepository(applicationContext)

        val targets = try {
            val hidden = prefs.hiddenFolderPaths
            fun visible(path: String) = hidden.none { it.isNotEmpty() && path.startsWith(it) }

            // Every (non-hidden) video, in the SAME order the Videos tab displays them, so the
            // backfill generates what the user browses first — instead of arbitrary MediaStore order.
            val allVideos = repo.getVideos(videoSortOption = prefs.videoSortOption).filter { visible(it.path) }
            val byUri = allVideos.associateBy { it.contentUri }

            // COVERS FIRST: the exact preview video each album displays, using the same sort prefs
            // as the UI — so every album gets a cover up front without the user opening/scrolling to
            // it. Then backfill the remaining videos.
            val coverPairs = repo.getFoldersWithIndependentSort(
                folderSortOption = prefs.folderSortOption,
                independentSortEnabled = prefs.independentSortEnabled,
                getFolderSortOption = { bucketId -> prefs.getFolderVideoSortOption(bucketId) },
                getCustomMediaOrder = { bucketId -> prefs.getFolderMediaCustomOrder(bucketId) }
            ).mapNotNull { folder -> folder.latestItemUri?.let { byUri[it] } }
                .map { it.contentUri to it.dateModified }

            val restPairs = allVideos.map { it.contentUri to it.dateModified }

            // Covers first, then the rest; de-duplicated preserving order; skip already-cached.
            LinkedHashSet<Pair<Uri, Long>>(coverPairs.size + restPairs.size)
                .apply { addAll(coverPairs); addAll(restPairs) }
                .filter { (uri, dm) -> !cache.exists(uri, dm) }
        } catch (e: Exception) {
            Log.w(TAG, "Failed to enumerate videos: ${e.message}")
            return@withContext Result.retry()
        }

        if (targets.isEmpty()) return@withContext Result.success()

        // Best-effort foreground promotion; if the OS refuses, keep going as a plain background job.
        runCatching { setForeground(createForegroundInfo(0, targets.size)) }

        val total = targets.size
        val done = AtomicInteger(0)
        val gate = Semaphore(MAX_CONCURRENCY)

        coroutineScope {
            targets.map { (uri, dm) ->
                async {
                    gate.withPermit {
                        if (isStopped) return@withPermit
                        try {
                            if (!cache.exists(uri, dm)) {
                                VideoThumbnailExtractor.extract(applicationContext, uri)
                                    ?.let { cache.put(uri, dm, it) }
                            }
                        } catch (e: Exception) {
                            Log.w(TAG, "Preview generation failed for $uri: ${e.message}")
                        }
                        val d = done.incrementAndGet()
                        if (d % PROGRESS_STEP == 0) {
                            runCatching { setForeground(createForegroundInfo(d, total)) }
                        }
                    }
                }
            }.awaitAll()
        }

        Result.success()
    }

    private fun createForegroundInfo(done: Int, total: Int): ForegroundInfo {
        createChannel()
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Preparing video previews")
            .setContentText(if (total > 0) "$done / $total" else "Working…")
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setOngoing(true)
            .setProgress(total, done, total == 0)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(NOTIFICATION_ID, notification)
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mgr = applicationContext.getSystemService(NotificationManager::class.java)
            if (mgr.getNotificationChannel(CHANNEL_ID) == null) {
                mgr.createNotificationChannel(
                    NotificationChannel(CHANNEL_ID, "Preview generation", NotificationManager.IMPORTANCE_LOW)
                )
            }
        }
    }

    companion object {
        private const val TAG = "PreviewGenerationWorker"
        private const val UNIQUE_NAME = "video_preview_generation"
        private const val CHANNEL_ID = "video_preview_generation"
        private const val NOTIFICATION_ID = 74110
        private const val MAX_CONCURRENCY = 6
        private const val PROGRESS_STEP = 5

        /**
         * Enqueue the pass. Auto-start on launch should pass [replace] = false (KEEP) so a running
         * pass isn't restarted; the Settings "Generate now" action passes [replace] = true.
         */
        fun enqueue(context: Context, replace: Boolean = false) {
            val request = OneTimeWorkRequestBuilder<PreviewGenerationWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork(
                UNIQUE_NAME,
                if (replace) ExistingWorkPolicy.REPLACE else ExistingWorkPolicy.KEEP,
                request
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(UNIQUE_NAME)
        }
    }
}
