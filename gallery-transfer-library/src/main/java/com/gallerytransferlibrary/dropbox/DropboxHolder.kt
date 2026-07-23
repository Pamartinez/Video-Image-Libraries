package com.gallerytransferlibrary.dropbox

import android.content.Context
import com.example.common.data.dropbox.DropboxClient
import com.example.common.data.dropbox.DropboxClientFactory
import com.example.common.data.dropbox.DropboxConfig
import com.example.common.upload.UploadManager
import com.gallerytransferlibrary.BuildConfig
import com.gallerytransferlibrary.data.util.FileLogger

/**
 * Process-wide holder that lazily builds the reusable `common` Dropbox stack for this app,
 * injecting this module's [BuildConfig] app key / redirect URI and a namespaced token store.
 */
object DropboxHolder {

    @Volatile private var client: DropboxClient? = null
    @Volatile private var manager: UploadManager? = null

    fun config(): DropboxConfig = DropboxConfig(
        appKey = BuildConfig.DROPBOX_APP_KEY,
        redirectUri = BuildConfig.DROPBOX_REDIRECT_URI,
        tokenStoreName = "gallery_transfer"
    )

    fun client(context: Context): DropboxClient =
        client ?: synchronized(this) {
            client ?: DropboxClientFactory.create(context.applicationContext, config()).also { client = it }
        }

    fun uploadManager(context: Context): UploadManager {
        val c = client(context)
        return manager ?: synchronized(this) {
            manager ?: UploadManager(
                c.repository,
                c.authManager,
                logFailure = { message, throwable -> FileLogger.e("DropboxUpload", message, throwable) }
            ).also { manager = it }
        }
    }
}
