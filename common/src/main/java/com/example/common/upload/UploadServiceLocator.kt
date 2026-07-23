package com.example.common.upload

import android.content.Context

/**
 * Bridges the `common` [UploadWorker] to each app's singleton [UploadManager] without `common`
 * depending on any app module. Every app registers its provider in `Application.onCreate`, e.g.
 *
 * ```
 * UploadServiceLocator.register { DropboxHolder.uploadManager(it) }
 * ```
 */
object UploadServiceLocator {

    @Volatile
    private var provider: ((Context) -> UploadManager)? = null

    fun register(managerProvider: (Context) -> UploadManager) {
        provider = managerProvider
    }

    /** The app's [UploadManager], or null if no app has registered a provider yet. */
    fun uploadManager(context: Context): UploadManager? =
        provider?.invoke(context.applicationContext)
}
