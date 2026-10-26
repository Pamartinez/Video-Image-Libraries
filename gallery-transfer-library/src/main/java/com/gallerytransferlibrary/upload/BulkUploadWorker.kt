package com.gallerytransferlibrary.upload

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.common.upload.UploadItem
import com.common.upload.UploadManager
import com.gallerytransferlibrary.data.model.BulkUploadCriteria
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.preferences.AppPreferences
import com.gallerytransferlibrary.data.repository.MediaRepository
import com.gallerytransferlibrary.dropbox.DropboxHolder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Uploads every media item matching a set of criteria, and keeps going when the app is closed.
 *
 * **Why criteria and not an item list.** The old manual-backup path built the list in the UI and
 * handed it to the worker through an in-memory field on the [UploadManager] singleton. That field
 * dies with the process, so swiping the app away (which kills the process on many OEM builds) left
 * WorkManager to restart a worker that found nothing to do and reported success — the upload
 * vanished silently. Here the criteria travel in WorkManager's input [androidx.work.Data], which is
 * persisted in its own database across process death and reboot, and the worker queries MediaStore
 * itself. Same approach that makes [AutoUploadWorker] resilient.
 *
 * **Why the cutoff is frozen at enqueue.** [Keys.CUTOFF_SECONDS] is an absolute timestamp computed
 * when the user tapped Upload, not a relative "N days" re-evaluated per run. A retry hours later
 * would otherwise silently widen the set — unacceptable for an action that showed the user a count
 * and may delete their files. ([AutoUploadWorker] correctly does the opposite: periodic work
 * expresses a standing intent, so its cutoff rolls.)
 *
 * **Why the settings are snapshotted too.** `deleteAfterUpload` defaults to true and is
 * destructive. Reading it at run time would mean toggling a setting between tap and run could trash
 * files the user never agreed to lose.
 */
class BulkUploadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    object Keys {
        const val MEDIA_TYPE = "media_type"
        const val MIN_BYTES = "min_bytes"
        const val CUTOFF_SECONDS = "cutoff_seconds"
        const val DEST_ROOT = "dest_root"
        const val OVERWRITE = "overwrite"
        const val DELETE_AFTER = "delete_after"
        const val KEEP_STRUCTURE = "keep_structure"
        const val SUMMARY = "summary"
    }

    override suspend fun getForegroundInfo(): ForegroundInfo = buildForegroundInfo(0, 0)

    override suspend fun doWork(): Result = coroutineScope {
        val prefs = AppPreferences(applicationContext)
        val minBytes = inputData.getLong(Keys.MIN_BYTES, 0L)
        val cutoffSeconds = inputData.getLong(Keys.CUTOFF_SECONDS, 0L)
        val mediaType = FilterType.fromId(inputData.getInt(Keys.MEDIA_TYPE, FilterType.ALL.id))
        val destRoot = inputData.getString(Keys.DEST_ROOT) ?: ""
        val overwrite = inputData.getBoolean(Keys.OVERWRITE, false)
        val deleteAfter = inputData.getBoolean(Keys.DELETE_AFTER, false)
        val keepStructure = inputData.getBoolean(Keys.KEEP_STRUCTURE, true)
        val summary = inputData.getString(Keys.SUMMARY) ?: "Uploading old items"

        // A cutoff of 0 would match nothing; treat a malformed request as a no-op rather than
        // uploading the whole device.
        if (cutoffSeconds <= 0L) return@coroutineScope Result.success()

        // No valid token and no UI to sign in with — retry later rather than failing the batch.
        val client = DropboxHolder.client(applicationContext)
        if (client.authManager.getValidAccessToken() == null) {
            return@coroutineScope if (runAttemptCount < MAX_ATTEMPTS) Result.retry() else Result.success()
        }

        val runId = id.toString()
        val alreadyUploaded = prefs.beginBulkLedger(runId)

        val typeMatches: (MediaItem) -> Boolean = when (mediaType) {
            FilterType.ALL -> { _ -> true }
            FilterType.IMAGES -> { item -> !item.isVideo }
            FilterType.VIDEOS -> { item -> item.isVideo }
        }
        val candidates = MediaRepository(applicationContext).getAllMedia().filter {
            typeMatches(it) && it.size > minBytes && it.dateCreated < cutoffSeconds &&
                it.uniqueKey !in alreadyUploaded
        }
        if (candidates.isEmpty()) {
            prefs.clearBulkLedger(runId)
            return@coroutineScope Result.success()
        }

        val manager = DropboxHolder.uploadManager(applicationContext)
        val total = candidates.size
        var done = 0
        setForeground(buildForegroundInfo(0, total, summary))

        // Mirror the manager's per-batch progress into our own whole-run notification.
        val notifier = launch {
            manager.state.collectLatest { s ->
                if (s.isUploading) {
                    runCatching { buildForegroundInfo(done + s.current, total, summary) }
                        .onSuccess { runCatching { setForeground(it) } }
                }
            }
        }

        try {
            // Chunked so the ledger is flushed as we go. A ledger written only at the end would lose
            // 900 items to a kill at 900/1000. 50 rather than a handful because the manager caches
            // destination listings per batch, so tiny chunks re-list the same Dropbox folder.
            for (chunk in candidates.chunked(CHUNK_SIZE)) {
                if (isStopped) return@coroutineScope retryOrSucceed()

                // runBatch serializes against any other upload, so say so rather than showing a
                // progress bar that appears frozen while we wait our turn.
                if (manager.isBatchRunning) {
                    runCatching { setForeground(buildForegroundInfo(done, total, "Waiting for another upload…")) }
                }
                val result = manager.runBatch(
                    UploadManager.Batch(
                        items = chunk.map {
                            UploadItem(
                                uri = it.uri,
                                name = it.displayName,
                                size = it.size,
                                relativePath = if (keepStructure) it.bucketName else ""
                            )
                        },
                        destRoot = destRoot,
                        overwrite = overwrite,
                        deleteAfterUpload = deleteAfter,
                        // This worker trashes from the returned result below, so the UI must not
                        // also act on the same URIs.
                        delegateTrashToUi = false
                    )
                )

                val uploadedUris = result.uploadedUris.toSet()
                val uploadedItems = chunk.filter { it.uri in uploadedUris }
                prefs.addBulkLedgerKeys(runId, uploadedItems.map { it.uniqueKey })
                // Per chunk, not at the very end: an interrupted run then still frees the space for
                // what it did upload, and those files are safely in Dropbox already.
                if (deleteAfter) {
                    WorkerTrash.trashOrDefer(applicationContext, prefs, uploadedItems.map { it.uri })
                }

                done += chunk.size
                runCatching { setForeground(buildForegroundInfo(done, total, summary)) }
            }
        } finally {
            notifier.cancel()
        }

        prefs.clearBulkLedger(runId)
        Result.success()
    }

    private fun retryOrSucceed(): Result =
        if (runAttemptCount < MAX_ATTEMPTS) Result.retry() else Result.success()

    private fun buildForegroundInfo(
        current: Int,
        total: Int,
        summary: String = "Uploading old items"
    ): ForegroundInfo {
        createChannel()
        val text = if (total > 0) "Uploading $current of $total" else "Finding matching items…"
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Bulk upload to Dropbox")
            .setContentText(text)
            .setSubText(summary)
            .setSmallIcon(android.R.drawable.stat_sys_upload)
            .setOngoing(true)
            .setProgress(total.coerceAtLeast(1), current, total == 0)
            .build()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(NOTIF_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(NOTIF_ID, notification)
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = applicationContext.getSystemService(NotificationManager::class.java)
            if (nm.getNotificationChannel(CHANNEL_ID) == null) {
                nm.createNotificationChannel(
                    NotificationChannel(CHANNEL_ID, "Bulk uploads", NotificationManager.IMPORTANCE_LOW)
                )
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "bulk_uploads"
        /** Distinct from UploadWorker (4210) and AutoUploadWorker (4211) — a shared id would make
         *  two concurrently running foreground workers fight over one notification. */
        private const val NOTIF_ID = 4212
        private const val CHUNK_SIZE = 50
        private const val MAX_ATTEMPTS = 5

        /** Builds the input data for [criteria], freezing the cutoff and the relevant settings. */
        fun inputDataOf(
            criteria: BulkUploadCriteria,
            destRoot: String,
            overwrite: Boolean,
            deleteAfterUpload: Boolean,
            keepFolderStructure: Boolean,
            nowSec: Long = System.currentTimeMillis() / 1000L
        ): androidx.work.Data = androidx.work.Data.Builder()
            .putInt(Keys.MEDIA_TYPE, criteria.mediaType.id)
            .putLong(Keys.MIN_BYTES, criteria.minBytes)
            .putLong(Keys.CUTOFF_SECONDS, criteria.cutoffSeconds(nowSec))
            .putString(Keys.DEST_ROOT, destRoot)
            .putBoolean(Keys.OVERWRITE, overwrite)
            .putBoolean(Keys.DELETE_AFTER, deleteAfterUpload)
            .putBoolean(Keys.KEEP_STRUCTURE, keepFolderStructure)
            .putString(Keys.SUMMARY, criteria.summaryLabel())
            .build()
    }
}
