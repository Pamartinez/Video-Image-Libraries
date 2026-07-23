package com.example.common.data.db

import android.os.Environment
import com.example.common.data.model.TrashEntry
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Shared, cross-app persistence for the internal Trash.
 *
 * Unlike per-app SharedPreferences, the trash index is a single JSON file that lives INSIDE the
 * shared trash folder on external storage, so gallery-transfer, image-library and video-library
 * (all of which hold All-files access) see the exact same trashed items.
 *
 * Layout:
 *   /storage/emulated/0/.VideoImageLibrariesTrash/
 *       .nomedia          → hides trashed media from other galleries
 *       index.json        → JSONArray<TrashEntry>
 *       <trashFileName>…   → the trashed files' bytes
 *
 * All reads/writes are guarded by a process-wide lock and use atomic temp-file replacement.
 */
object TrashStore {

    const val TRASH_DIR_NAME = ".VideoImageLibrariesTrash"
    private const val INDEX_FILE = "index.json"
    private const val TEMP_INDEX_FILE = "index.json.tmp"

    private val lock = Any()

    /** The shared trash folder, created on demand with a `.nomedia` marker. */
    fun trashDir(): File {
        val dir = File(Environment.getExternalStorageDirectory(), TRASH_DIR_NAME)
        if (!dir.exists()) dir.mkdirs()
        val noMedia = File(dir, ".nomedia")
        if (!noMedia.exists()) runCatching { noMedia.createNewFile() }
        return dir
    }

    private fun indexFile(): File = File(trashDir(), INDEX_FILE)

    // ── Read ───────────────────────────────────────────────────────────────

    fun getAll(): List<TrashEntry> = synchronized(lock) {
        val file = indexFile()
        if (!file.exists()) return emptyList()
        return runCatching {
            val arr = JSONArray(file.readText().ifBlank { "[]" })
            val list = ArrayList<TrashEntry>(arr.length())
            for (i in 0 until arr.length()) list.add(fromJson(arr.getJSONObject(i)))
            list
        }.getOrDefault(emptyList())
    }

    // ── Write ──────────────────────────────────────────────────────────────

    fun add(entry: TrashEntry) = synchronized(lock) {
        val list = getAllUnlocked().toMutableList()
        list.removeAll { it.id == entry.id }
        list.add(entry)
        saveUnlocked(list)
    }

    fun remove(ids: Collection<String>) = synchronized(lock) {
        val idSet = ids.toSet()
        val list = getAllUnlocked().filterNot { it.id in idSet }
        saveUnlocked(list)
    }

    fun clear() = synchronized(lock) { saveUnlocked(emptyList()) }

    // ── Internals ────────────────────────────────────────────────────────────

    private fun getAllUnlocked(): List<TrashEntry> {
        val file = indexFile()
        if (!file.exists()) return emptyList()
        return runCatching {
            val arr = JSONArray(file.readText().ifBlank { "[]" })
            val list = ArrayList<TrashEntry>(arr.length())
            for (i in 0 until arr.length()) list.add(fromJson(arr.getJSONObject(i)))
            list
        }.getOrDefault(emptyList())
    }

    private fun saveUnlocked(entries: List<TrashEntry>) {
        val arr = JSONArray()
        for (e in entries) arr.put(toJson(e))
        val dir = trashDir()
        val tmp = File(dir, TEMP_INDEX_FILE)
        runCatching {
            tmp.writeText(arr.toString())
            val target = File(dir, INDEX_FILE)
            if (target.exists()) target.delete()
            if (!tmp.renameTo(target)) {
                target.writeText(arr.toString())
                tmp.delete()
            }
        }
    }

    private fun toJson(e: TrashEntry): JSONObject = JSONObject().apply {
        put("id", e.id)
        put("trashFileName", e.trashFileName)
        put("originalPath", e.originalPath)
        put("displayName", e.displayName)
        put("isVideo", e.isVideo)
        put("size", e.size)
        put("mimeType", e.mimeType)
        put("width", e.width)
        put("height", e.height)
        put("dateModified", e.dateModified)
        put("deleteTimeMillis", e.deleteTimeMillis)
        put("sourceApp", e.sourceApp)
    }

    private fun fromJson(o: JSONObject): TrashEntry = TrashEntry(
        id = o.getString("id"),
        trashFileName = o.getString("trashFileName"),
        originalPath = o.getString("originalPath"),
        displayName = o.getString("displayName"),
        isVideo = o.optBoolean("isVideo", false),
        size = o.optLong("size", 0L),
        mimeType = o.optString("mimeType", ""),
        width = o.optInt("width", 0),
        height = o.optInt("height", 0),
        dateModified = o.optLong("dateModified", 0L),
        deleteTimeMillis = o.optLong("deleteTimeMillis", 0L),
        sourceApp = o.optString("sourceApp", "")
    )
}
