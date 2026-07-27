package com.example.common.data.util

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.IntentSender
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore

/**
 * Shared helper for moving media items to the system (gallery) trash.
 *
 * Android requires user consent to trash media an app does not own, so this returns the
 * [IntentSender] the caller launches (one system "Move to trash?" dialog for the whole batch).
 * When the app holds All-files access (MANAGE_EXTERNAL_STORAGE), that request completes without a
 * dialog. Centralised here so the image, video and gallery-transfer apps all trash items identically.
 */
object MediaTrashHelper {

    /** Build a trash request for [uris]. Launch the returned [IntentSender] to show the system dialog. */
    fun createTrashRequest(resolver: ContentResolver, uris: List<Uri>): IntentSender =
        MediaStore.createTrashRequest(resolver, uris, true).intentSender

    /** True when the app holds All-files access (MANAGE_EXTERNAL_STORAGE), letting it delete silently. */
    fun isExternalStorageManager(): Boolean =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()

    /**
     * Moves [uris] to the system (Samsung Gallery) trash silently — no consent dialog — by setting
     * MediaStore's IS_TRASHED flag. The items stay recoverable from the Gallery trash. Requires
     * All-files access (see [isExternalStorageManager]), which lets the app trash media owned by any
     * app. Returns the number of items successfully trashed.
     */
    fun trashSilently(context: Context, uris: List<Uri>): Int {
        if (uris.isEmpty() || !isExternalStorageManager()) return 0
        val resolver = context.contentResolver
        val values = ContentValues().apply { put(MediaStore.MediaColumns.IS_TRASHED, 1) }
        var count = 0
        for (uri in uris) {
            val trashed = runCatching { resolver.update(uri, values, null, null) }.getOrDefault(0) > 0
            if (trashed) count++
        }
        return count
    }
}
