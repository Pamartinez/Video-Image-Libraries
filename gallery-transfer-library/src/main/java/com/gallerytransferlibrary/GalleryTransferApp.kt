package com.gallerytransferlibrary

import android.app.Application
import com.example.common.upload.UploadServiceLocator
import com.gallerytransferlibrary.dropbox.DropboxHolder
import com.gallerytransferlibrary.upload.AutoUploadScheduler

/**
 * Wires the shared `common` upload stack to this app's singleton [UploadManager] so the background
 * [com.example.common.upload.UploadWorker] can resolve it.
 */
class GalleryTransferApp : Application() {
    override fun onCreate() {
        super.onCreate()
        UploadServiceLocator.register { DropboxHolder.uploadManager(it) }
        AutoUploadScheduler.reschedule(this)
    }
}
