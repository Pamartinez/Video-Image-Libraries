package com.example.common.data.util

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.common.data.db.TrashStore
import com.example.common.data.model.TrashEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

/**
 * Orchestrates the shared internal Trash: it moves the files between the shared trash folder and
 * their original locations, and keeps the shared [TrashStore] index in sync.
 *
 * Deleting NEVER shows a system "Move to trash?" / "Allow to delete?" dialog. [moveToTrash] copies
 * the bytes into the shared folder first, then removes the MediaStore original silently via
 * [MediaTrashHelper.deleteSilently] (which requires All-files access). It never calls
 * createTrashRequest / createDeleteRequest.
 *
 * Shared by gallery-transfer, image-library and video-library through the `common` module.
 */
object TrashManager {

    /** Default retention before an item auto-expires, matching Samsung Gallery (31 days). */
    const val DEFAULT_RETENTION_DAYS = 31

    /** A media item to be trashed. Callers build this from their own model. */
    data class TrashItem(
        val id: Long,
        val isVideo: Boolean,
        val path: String,
        val displayName: String,
        val size: Long,
        val mimeType: String,
        val width: Int = 0,
        val height: Int = 0,
        val dateModified: Long = 0L
    ) {
        fun uri(): Uri {
            val base = if (isVideo) MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            else MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            return ContentUris.withAppendedId(base, id)
        }
    }

    /** True when the app can operate the internal trash silently (All-files access held). */
    fun canOperateSilently(): Boolean = MediaTrashHelper.isExternalStorageManager()

    /**
     * Moves [items] into the shared trash: copies each file's bytes into the shared folder, then
     * removes the MediaStore original silently. Returns the number of items successfully trashed.
     */
    suspend fun moveToTrash(context: Context, items: List<TrashItem>): Int = withContext(Dispatchers.IO) {
        if (items.isEmpty() || !canOperateSilently()) return@withContext 0
        val dir = TrashStore.trashDir()
        val sourceApp = context.packageName
        val now = System.currentTimeMillis()
        var count = 0

        for (item in items) {
            val src = File(item.path)
            if (!src.exists()) continue

            val trashFileName = uniqueTrashName(dir, item.displayName)
            val dest = File(dir, trashFileName)

            val copied = runCatching { src.copyTo(dest, overwrite = true) }.isSuccess
            if (!copied) { dest.delete(); continue }

            // Remove the original silently (no dialog). Requires All-files access.
            val removed = MediaTrashHelper.deleteSilently(context, listOf(item.uri())) > 0
            if (!removed) { dest.delete(); continue }

            TrashStore.add(
                TrashEntry(
                    id = UUID.randomUUID().toString(),
                    trashFileName = trashFileName,
                    originalPath = item.path,
                    displayName = item.displayName,
                    isVideo = item.isVideo,
                    size = item.size,
                    mimeType = item.mimeType,
                    width = item.width,
                    height = item.height,
                    dateModified = item.dateModified,
                    deleteTimeMillis = now,
                    sourceApp = sourceApp
                )
            )
            count++
        }
        count
    }

    /**
     * Convenience overload that trashes items identified only by their MediaStore [uris] (e.g. the
     * delete-after-upload flow). Resolves each Uri's metadata from MediaStore, then delegates to
     * [moveToTrash]. Returns the number of items successfully trashed.
     */
    suspend fun moveUrisToTrash(context: Context, uris: List<Uri>): Int = withContext(Dispatchers.IO) {
        if (uris.isEmpty()) return@withContext 0
        val items = uris.mapNotNull { buildItemFromUri(context, it) }
        moveToTrash(context, items)
    }

    private fun buildItemFromUri(context: Context, uri: Uri): TrashItem? {
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.SIZE,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT,
            MediaStore.MediaColumns.DATE_MODIFIED
        )
        return runCatching {
            context.contentResolver.query(uri, projection, null, null, null)?.use { c ->
                if (!c.moveToFirst()) return null
                val path = c.getString(0) ?: return null
                val mime = c.getString(3) ?: ""
                TrashItem(
                    id = ContentUris.parseId(uri),
                    isVideo = mime.startsWith("video/"),
                    path = path,
                    displayName = c.getString(1) ?: File(path).name,
                    size = c.getLong(2),
                    mimeType = mime,
                    width = c.getInt(4),
                    height = c.getInt(5),
                    dateModified = c.getLong(6)
                )
            }
        }.getOrNull()
    }

    /**
     * Restores [entries] to their original paths (or a fallback folder if the original is gone),
     * rescans them into MediaStore and removes them from the index. Returns the number restored.
     */
    suspend fun restore(context: Context, entries: List<TrashEntry>): Int = withContext(Dispatchers.IO) {
        if (entries.isEmpty()) return@withContext 0
        val dir = TrashStore.trashDir()
        val restoredIds = mutableListOf<String>()

        for (entry in entries) {
            val trashFile = File(dir, entry.trashFileName)
            if (!trashFile.exists()) { restoredIds.add(entry.id); continue }

            var target = File(entry.originalPath)
            val parent = target.parentFile
            if (parent == null || (!parent.exists() && !parent.mkdirs())) {
                // Original folder is gone → restore into DCIM/Restored.
                val fallbackDir = File(
                    android.os.Environment.getExternalStoragePublicDirectory(
                        android.os.Environment.DIRECTORY_DCIM
                    ),
                    "Restored"
                )
                fallbackDir.mkdirs()
                target = File(fallbackDir, entry.displayName)
            }
            if (target.exists()) {
                target = File(target.parentFile, uniqueTrashName(target.parentFile!!, entry.displayName))
            }

            val moved = runCatching { trashFile.copyTo(target, overwrite = false) }.isSuccess
            if (moved) {
                trashFile.delete()
                MediaFileUtils.scanFile(context, target)
                restoredIds.add(entry.id)
            }
        }
        TrashStore.remove(restoredIds)
        restoredIds.size
    }

    /** Permanently deletes [entries]' files and removes them from the index. */
    suspend fun deletePermanently(entries: List<TrashEntry>): Int = withContext(Dispatchers.IO) {
        if (entries.isEmpty()) return@withContext 0
        val dir = TrashStore.trashDir()
        for (entry in entries) runCatching { File(dir, entry.trashFileName).delete() }
        TrashStore.remove(entries.map { it.id })
        entries.size
    }

    /** Removes every item from the trash (files + index). */
    suspend fun emptyAll(): Int = withContext(Dispatchers.IO) {
        val all = TrashStore.getAll()
        deletePermanently(all)
    }

    /** Deletes items whose age exceeds [retentionDays]. Returns the number removed. */
    suspend fun emptyExpired(retentionDays: Int = DEFAULT_RETENTION_DAYS): Int = withContext(Dispatchers.IO) {
        val cutoff = System.currentTimeMillis() - retentionDays * 24L * 60L * 60L * 1000L
        val expired = TrashStore.getAll().filter { it.deleteTimeMillis in 1 until cutoff }
        deletePermanently(expired)
    }

    // ── Helpers ────────────────────────────────────────────────────────────

    private fun uniqueTrashName(dir: File, displayName: String): String {
        if (!File(dir, displayName).exists()) return displayName
        val dot = displayName.lastIndexOf('.')
        val stem = if (dot >= 0) displayName.substring(0, dot) else displayName
        val ext = if (dot >= 0) displayName.substring(dot) else ""
        for (i in 1..9999) {
            val candidate = "$stem($i)$ext"
            if (!File(dir, candidate).exists()) return candidate
        }
        return "$stem-${System.currentTimeMillis()}$ext"
    }
}
