package com.example.common.data.util

import android.content.ContentResolver
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
     * Permanently deletes [uris] without launching a consent dialog. Requires All-files access
     * (see [isExternalStorageManager]), which lets the app delete media owned by any app. For rows
     * that MediaStore won't remove directly, falls back to a direct file delete followed by a media
     * rescan so the stale entry disappears. Returns the number of items deleted.
     *
     * Unlike moving to the system trash, this works for media owned by other apps (e.g. camera
     * photos), but the items are removed permanently and are NOT recoverable from the system trash.
     */
    fun deleteSilently(context: Context, uris: List<Uri>): Int {
        if (uris.isEmpty() || !isExternalStorageManager()) return 0
        val resolver = context.contentResolver
        var count = 0
        for (uri in uris) {
            val path = pathFor(resolver, uri)
            val removed = runCatching { resolver.delete(uri, null, null) }.getOrDefault(0) > 0
            if (removed) {
                count++
            } else if (path != null) {
                val file = java.io.File(path)
                val gone = runCatching { file.delete() }.getOrDefault(false) || !file.exists()
                if (gone) {
                    MediaFileUtils.scanFile(context, file)
                    count++
                }
            }
        }
        return count
    }

    /** Look up the absolute filesystem path for [uri] via MediaStore's DATA column, or null. */
    private fun pathFor(resolver: ContentResolver, uri: Uri): String? =
        runCatching {
            resolver.query(uri, arrayOf(MediaStore.MediaColumns.DATA), null, null, null)?.use { c ->
                if (c.moveToFirst()) c.getString(0) else null
            }
        }.getOrNull()
}
