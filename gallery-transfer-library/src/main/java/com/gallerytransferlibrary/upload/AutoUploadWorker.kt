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
import com.example.common.data.util.MediaTrashHelper
import com.example.common.upload.UploadItem
import com.gallerytransferlibrary.data.preferences.AppPreferences
import com.gallerytransferlibrary.data.repository.MediaRepository
import com.gallerytransferlibrary.dropbox.DropboxHolder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Background worker that auto-uploads media older than the user-configured number of days to Dropbox.
 * Runs as a WorkManager foreground service so it keeps going while the app is closed.
 *
 * It reuses the app's singleton [com.example.common.upload.UploadManager] (via [DropboxHolder]) for
 * the actual transfer, so conflict resolution follows the user's background policy exactly as
 * interactive uploads do. Items already handled are recorded in [AppPreferences.autoUploadedKeys] so
 * they are never uploaded twice.
 */
class AutoUploadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo = buildForegroundInfo(0, 0)

    override suspend fun doWork(): Result = coroutineScope {
        val prefs = AppPreferences(applicationContext)
        if (!prefs.autoUploadEnabled) return@coroutineScope Result.success()

        // No valid Dropbox token → nothing we can do silently in the background; try again next run.
        val client = DropboxHolder.client(applicationContext)
        if (client.authManager.getValidAccessToken() == null) return@coroutineScope Result.success()

        // Select items older than the threshold that we haven't already auto-uploaded.
        val cutoffSeconds = (System.currentTimeMillis() / 1000L) - prefs.autoUploadOlderThanDays * 86_400L
        val alreadyUploaded = prefs.autoUploadedKeys
        val repository = MediaRepository(applicationContext)
        val candidates = repository.getAllMedia().filter {
            it.dateModified < cutoffSeconds && it.uniqueKey !in alreadyUploaded
        }
        if (candidates.isEmpty()) return@coroutineScope Result.success()

        setForeground(buildForegroundInfo(0, candidates.size))

        val keepStructure = prefs.keepFolderStructure
        val uploads = candidates.map {
            UploadItem(
                uri = it.uri,
                name = it.displayName,
                size = it.size,
                relativePath = if (keepStructure) it.bucketName else ""
            )
        }

        val manager = DropboxHolder.uploadManager(applicationContext)

        // Mirror upload progress into the notification.
        val notifier = launch {
            manager.state.collectLatest { s ->
                if (s.isUploading) {
                    runCatching { setForeground(buildForegroundInfo(s.current, s.total)) }
                }
            }
        }

        try {
            manager.prepareBatch(uploads, prefs.dropboxDestPath, prefs.overwriteOnConflict, prefs.deleteAfterUpload)
            manager.runPending()
        } finally {
            notifier.cancel()
        }

        // Record which items uploaded (for dedup) and, if requested, delete them silently.
        val uploadedUris = manager.state.value.lastUploadedUris.toSet()
        if (uploadedUris.isNotEmpty()) {
            val uploadedItems = candidates.filter { it.uri in uploadedUris }
            prefs.addAutoUploadedKeys(uploadedItems.map { it.uniqueKey })

            if (prefs.deleteAfterUpload && MediaTrashHelper.isExternalStorageManager()) {
                MediaTrashHelper.trashSilently(
                    applicationContext,
                    uploadedItems.map { it.uri }
                )
                manager.clearUploadedUris()
            }
        }

        Result.success()
    }

    private fun buildForegroundInfo(current: Int, total: Int): ForegroundInfo {
        createChannel()
        val text = if (total > 0) "Uploading $current of $total" else "Checking for old items…"
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Auto-upload to Dropbox")
            .setContentText(text)
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
                    NotificationChannel(CHANNEL_ID, "Automatic uploads", NotificationManager.IMPORTANCE_LOW)
                )
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "auto_uploads"
        private const val NOTIF_ID = 4211
    }
}
