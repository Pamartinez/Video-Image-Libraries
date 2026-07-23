package com.example.common.upload

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Runs a staged upload batch as a WorkManager foreground service so it keeps going even when the app
 * is minimized or closed. It delegates the actual work to the app's singleton [UploadManager]
 * (resolved via [UploadServiceLocator]) and mirrors that manager's [UploadManager.state] into an
 * ongoing progress notification.
 *
 * Conflict handling: while the app UI is visible the interactive dialog is used; once it isn't, the
 * manager auto-resolves clashes with the user's configured background policy (overwrite / keep both).
 */
class UploadWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo = buildForegroundInfo(0, 0)

    override suspend fun doWork(): Result = coroutineScope {
        val manager = UploadServiceLocator.uploadManager(applicationContext)
            ?: return@coroutineScope Result.failure()

        setForeground(getForegroundInfo())

        // Keep the notification in step with upload progress.
        val notifier = launch {
            manager.state.collectLatest { s ->
                if (s.isUploading) {
                    runCatching { setForeground(buildForegroundInfo(s.current, s.total)) }
                }
            }
        }

        try {
            manager.runPending()
        } finally {
            notifier.cancel()
        }
        Result.success()
    }

    private fun buildForegroundInfo(current: Int, total: Int): ForegroundInfo {
        createChannel()
        val text = if (total > 0) "Uploading $current of $total" else "Preparing…"
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Uploading to Dropbox")
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
                    NotificationChannel(CHANNEL_ID, "Uploads", NotificationManager.IMPORTANCE_LOW)
                )
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "uploads"
        private const val NOTIF_ID = 4210
    }
}
