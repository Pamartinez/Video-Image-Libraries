package com.gallerytransferlibrary.upload

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.gallerytransferlibrary.data.preferences.AppPreferences
import java.util.concurrent.TimeUnit

/**
 * Registers (or cancels) the periodic [AutoUploadWorker] according to the user's auto-upload settings.
 * The unique periodic work survives app close and device reboot, so background auto-upload keeps
 * running until the feature is turned off.
 */
object AutoUploadScheduler {

    private const val UNIQUE_WORK = "gallery_transfer_auto_upload"

    /**
     * Reschedules the periodic worker from current preferences. Enqueues/updates when auto-upload is
     * enabled; cancels the unique work when it is disabled. Call from app start and after any change
     * to the auto-upload settings.
     */
    fun reschedule(context: Context) {
        val prefs = AppPreferences(context)
        val wm = WorkManager.getInstance(context.applicationContext)
        if (!prefs.autoUploadEnabled) {
            wm.cancelUniqueWork(UNIQUE_WORK)
            return
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(if (prefs.autoUploadWifiOnly) NetworkType.UNMETERED else NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequestBuilder<AutoUploadWorker>(
            prefs.autoUploadFrequency.minutes, TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        wm.enqueueUniquePeriodicWork(UNIQUE_WORK, ExistingPeriodicWorkPolicy.UPDATE, request)
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelUniqueWork(UNIQUE_WORK)
    }
}
