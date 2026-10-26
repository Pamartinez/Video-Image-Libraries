package com.common.upload

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * Entry point the UI calls to start an upload. It stages the batch on the app's [UploadManager]
 * (so progress shows immediately) and enqueues a unique [UploadWorker] to do the work in the
 * background.
 *
 * Enqueued with APPEND_OR_REPLACE so two uploads started in quick succession run one after the
 * other. REPLACE used to cancel the first worker mid-flight, abandoning whatever was left of that
 * batch; the user had asked for both.
 */
object UploadScheduler {

    const val UNIQUE_WORK = "gallery_transfer_upload"

    fun enqueue(
        context: Context,
        items: List<UploadItem>,
        destRoot: String,
        overwrite: Boolean,
        deleteAfterUpload: Boolean = false
    ) {
        if (items.isEmpty()) return
        val manager = UploadServiceLocator.uploadManager(context) ?: return
        manager.prepareBatch(items, destRoot, overwrite, deleteAfterUpload)

        val request = OneTimeWorkRequestBuilder<UploadWorker>().build()
        WorkManager.getInstance(context.applicationContext)
            .enqueueUniqueWork(UNIQUE_WORK, ExistingWorkPolicy.APPEND_OR_REPLACE, request)
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelUniqueWork(UNIQUE_WORK)
    }
}
