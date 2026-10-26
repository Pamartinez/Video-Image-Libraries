package com.gallerytransferlibrary.upload

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.gallerytransferlibrary.data.model.BulkUploadCriteria
import com.gallerytransferlibrary.data.preferences.AppPreferences
import java.util.concurrent.TimeUnit

/**
 * Enqueues a [BulkUploadWorker] for a set of criteria. Used by both the Bulk upload screen and
 * Settings → "Back up old items now" — the only difference between them is which criteria they
 * build.
 */
object BulkUploadScheduler {

    const val UNIQUE_WORK = "gallery_transfer_bulk_upload"

    /**
     * Queues an upload of everything matching [criteria], minus [excludedKeys]. The current
     * destination and upload settings are snapshotted into the request here, at tap time.
     *
     * @param excludedKeys `MediaItem.uniqueKey`s the user unticked in the preview. Seeded into the
     *   run's ledger rather than passed as input data, which has a 10 KB cap a long exclusion list
     *   could blow.
     */
    fun enqueue(
        context: Context,
        criteria: BulkUploadCriteria,
        excludedKeys: Set<String> = emptySet()
    ) {
        val prefs = AppPreferences(context)
        val request = OneTimeWorkRequestBuilder<BulkUploadWorker>()
            .setInputData(
                BulkUploadWorker.inputDataOf(
                    criteria = criteria,
                    destRoot = prefs.dropboxDestPath,
                    overwrite = prefs.overwriteOnConflict,
                    deleteAfterUpload = prefs.deleteAfterUpload,
                    keepFolderStructure = prefs.keepFolderStructure
                )
            )
            // Any connection: this is an explicit user action, so silently doing nothing because
            // they're on cellular would be worse than spending the data. (The periodic
            // auto-upload honours the Wi-Fi-only preference instead — it runs unprompted.)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.SECONDS)
            .build()

        // The request's id exists as soon as it is built, so the ledger can be seeded before the
        // worker can possibly start reading it.
        if (excludedKeys.isNotEmpty()) prefs.seedBulkLedger(request.id.toString(), excludedKeys)

        WorkManager.getInstance(context.applicationContext)
            // APPEND_OR_REPLACE: two taps with different criteria are two real intents, so they run
            // one after the other. REPLACE would silently discard the first, KEEP the second.
            .enqueueUniqueWork(UNIQUE_WORK, ExistingWorkPolicy.APPEND_OR_REPLACE, request)
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelUniqueWork(UNIQUE_WORK)
    }
}
