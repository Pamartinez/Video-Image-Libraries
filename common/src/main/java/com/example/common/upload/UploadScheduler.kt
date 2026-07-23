package com.example.common.upload

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * Entry point the UI calls to start an upload. It stages the batch on the app's [UploadManager]
 * (so progress shows immediately) and enqueues a single unique [UploadWorker] to do the work in the
 * background. Enqueuing as unique work (REPLACE) prevents two overlapping uploads of the same queue.
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
            .enqueueUniqueWork(UNIQUE_WORK, ExistingWorkPolicy.REPLACE, request)
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelUniqueWork(UNIQUE_WORK)
    }
}
