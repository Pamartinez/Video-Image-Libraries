package com.gallerytransferlibrary.upload

import android.content.Context
import android.net.Uri
import com.common.data.util.MediaTrashHelper
import com.gallerytransferlibrary.data.preferences.AppPreferences

/**
 * Delete-after-upload for background workers, which have no UI to prompt with.
 *
 * [MediaTrashHelper.trashSilently] needs All-files access. Without it the only route is a system
 * consent dialog, which requires an activity — so those URIs are parked in
 * [AppPreferences.pendingTrashUris] and the UI drains them the next time it starts. Before this
 * existed, a background upload with delete-after-upload simply never deleted anything for users
 * who hadn't granted that permission.
 */
internal object WorkerTrash {

    fun trashOrDefer(context: Context, prefs: AppPreferences, uris: List<Uri>) {
        if (uris.isEmpty()) return
        if (MediaTrashHelper.isExternalStorageManager()) {
            runCatching { MediaTrashHelper.trashSilently(context, uris) }
                .onFailure { prefs.addPendingTrashUris(uris.map(Uri::toString)) }
        } else {
            prefs.addPendingTrashUris(uris.map(Uri::toString))
        }
    }
}
