package com.example.common.data.util

import android.content.Context
import android.os.Environment
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Abstract base class for backup / restore of app settings and group data.
 *
 * Backup JSON schema (version 2):
 * ```
 * {
 *   "backupVersion": 2,
 *   "library":       "ImageLibrary" | "VideoLibrary",
 *   "timestamp":     Long,
 *   "settings": {
 *     // Shared (both libraries — written/read via writeSharedSettings / readSharedSettings):
 *     "viewType":               Int,
 *     "folderViewType":         Int,
 *     "customGroupOrder":       [Long, ...],
 *     "customMixedOrder":       [String, ...],
 *     "customGroupItemsOrders": { "<groupId>": [String, ...], ... },
 *     "independentSortEnabled": Boolean,
 *     "hiddenFolderPaths":      [String, ...],
 *     "hiddenFolderMeta":       { "<path>": { "name": String, "bucketId": Int, "itemCount": Int }, ... },
 *
 *     // Image-library specific:
 *     "sortOption":             Int,
 *     "imageSortOption":        Int,
 *     "sortType":               Int,
 *     "sortOrder":              Int,
 *     "carouselShowBarsOnOpen": Boolean,
 *     "customAlbumOrder":       [Int, ...],
 *
 *     // Video-library specific:
 *     "folderSortOption":       Int,
 *     "videoSortOption":        Int,
 *     "selectedTab":            Int,
 *     "instantPlayerEnabled":   Boolean,
 *     "customFolderOrder":      [Int, ...],
 *     "folderVideoSortOptions": { "<bucketId>": Int, ... }
 *   },
 *   "groupData": { "groups": [...], "members": [...], "nextGroupId": Long }
 * }
 * ```
 *
 * Each library creates a singleton `object` that extends this class and implements:
 *   - [writeSettings]    — serialise library-specific prefs (call [writeSharedSettings] inside)
 *   - [readSettings]     — deserialise and apply prefs (call [readSharedSettings] inside)
 *   - [migrateSettings]  — optional: coerce old backup versions (default: no-op)
 */
abstract class BackupManager(
    private val libraryFolderName: String,
    private val logger: FileLogger
) {

    companion object {
        /** Increment when the JSON schema changes in a breaking way. */
        const val BACKUP_VERSION   = 2
        const val BACKUP_FILE_NAME = "backup.json"
    }

    // ── Shared-settings snapshot ──────────────────────────────────────

    /**
     * Typed snapshot of the shared setting keys that are identical in both libraries.
     * `null` means the key was absent in the backup (safe to skip when restoring).
     */
    data class SharedSettings(
        val viewType:               Int?,
        val folderViewType:         Int?,
        val customGroupOrder:       List<Long>?,
        val customMixedOrder:       List<String>?,
        val customGroupItemsOrders: Map<Long, List<String>>?,
        // Shared across both libraries
        val independentSortEnabled: Boolean?,
        val hiddenFolderPaths:      Set<String>?,
        val hiddenFolderMeta:       Map<String, Triple<String, Int, Int>>?
    )

    /** `<External Storage>/Documents/<libraryFolderName>/backups/` */
    val backupDirectory: File
        get() = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "$libraryFolderName/backups"
        )

    // ── File I/O ──────────────────────────────────────────────────────

    fun saveBackupToFile(context: Context): Boolean {
        return try {
            val dir = backupDirectory
            if (!dir.exists()) dir.mkdirs()
            val file = File(dir, BACKUP_FILE_NAME)
            if (file.exists()) file.setWritable(true)
            file.writeText(createBackup(context))
            file.setReadOnly()
            true
        } catch (e: Exception) {
            logger.e("BackupManager", "Failed to save backup to file", e)
            false
        }
    }

    fun restoreBackupFromFile(context: Context): Boolean {
        return try {
            val file = File(backupDirectory, BACKUP_FILE_NAME)
            if (!file.exists()) return false
            restoreBackup(context, file.readText())
        } catch (e: Exception) {
            logger.e("BackupManager", "Failed to restore backup from file", e)
            false
        }
    }

    // ── Serialisation ─────────────────────────────────────────────────

    /** Returns the full backup as a compact JSON string. */
    fun createBackup(context: Context): String {
        val root = JSONObject()
        root.put("backupVersion", BACKUP_VERSION)
        root.put("library",       libraryFolderName)
        root.put("timestamp",     System.currentTimeMillis())
        root.put("settings",      writeSettings(context))
        root.put("groupData",     buildGroupData(context))
        return root.toString()
    }

    // ── Deserialisation ───────────────────────────────────────────────

    fun restoreBackup(context: Context, jsonString: String): Boolean {
        return try {
            val root    = JSONObject(jsonString)
            val version = root.optInt("backupVersion", 0)
            if (version < 1) return false

            if (root.has("settings")) {
                val migrated = migrateSettings(version, root.getJSONObject("settings"))
                readSettings(context, migrated)
            }
            if (root.has("groupData")) applyGroupData(context, root.getJSONObject("groupData"))
            true
        } catch (e: Exception) {
            logger.e("BackupManager", "Restore failed", e)
            false
        }
    }

    // ── Group Data ────────────────────────────────────────────────────

    private fun buildGroupData(context: Context): JSONObject {
        val sp = context.getSharedPreferences("group_store", Context.MODE_PRIVATE)
        return JSONObject().apply {
            put("groups",      JSONArray(sp.getString("groups_json",  "[]") ?: "[]"))
            put("members",     JSONArray(sp.getString("members_json", "[]") ?: "[]"))
            put("nextGroupId", sp.getLong("next_group_id", 1L))
        }
    }

    private fun applyGroupData(context: Context, groupData: JSONObject) {
        context.getSharedPreferences("group_store", Context.MODE_PRIVATE).edit().apply {
            if (groupData.has("groups"))
                putString("groups_json",  groupData.getJSONArray("groups").toString())
            if (groupData.has("members"))
                putString("members_json", groupData.getJSONArray("members").toString())
            if (groupData.has("nextGroupId"))
                putLong("next_group_id",  groupData.getLong("nextGroupId"))
            apply()
        }
    }

    // ── Shared-settings helpers ───────────────────────────────────────

    /**
     * Writes all shared setting keys into [settings] using the standardised
     * v2 format (JSONArray for ordered lists, JSONObject for maps).
     * Call this inside your [writeSettings] implementation.
     */
    protected fun writeSharedSettings(
        settings:               JSONObject,
        viewType:               Int,
        folderViewType:         Int,
        customGroupOrder:       List<Long>,
        customMixedOrder:       List<String>,
        customGroupItemsOrders: Map<Long, List<String>>,
        independentSortEnabled: Boolean,
        hiddenFolderPaths:      Set<String>,
        hiddenFolderMeta:       Map<String, Triple<String, Int, Int>>
    ) {
        settings.put("viewType",       viewType)
        settings.put("folderViewType", folderViewType)
        settings.put("customGroupOrder", JSONArray(customGroupOrder))
        settings.put("customMixedOrder", JSONArray(customMixedOrder))
        val groupItemsObj = JSONObject()
        customGroupItemsOrders.forEach { (id, order) ->
            groupItemsObj.put(id.toString(), JSONArray(order))
        }
        settings.put("customGroupItemsOrders", groupItemsObj)

        // Shared across both libraries
        settings.put("independentSortEnabled", independentSortEnabled)
        settings.put("hiddenFolderPaths", JSONArray(hiddenFolderPaths.toList()))
        val metaObj = JSONObject()
        hiddenFolderMeta.forEach { (path, triple) ->
            metaObj.put(path, JSONObject().apply {
                put("name",      triple.first)
                put("bucketId",  triple.second)
                put("itemCount", triple.third)
            })
        }
        settings.put("hiddenFolderMeta", metaObj)
    }

    /**
     * Reads all shared setting keys from [settings] and returns a
     * [SharedSettings] snapshot. Missing keys are returned as `null`.
     * Call this inside your [readSettings] implementation.
     */
    protected fun readSharedSettings(settings: JSONObject): SharedSettings {
        val viewType = if (settings.has("viewType")) settings.getInt("viewType") else null
        val folderViewType = if (settings.has("folderViewType")) settings.getInt("folderViewType") else null

        val customGroupOrder: List<Long>? = if (settings.has("customGroupOrder")) {
            val arr = settings.getJSONArray("customGroupOrder")
            (0 until arr.length()).map { arr.getLong(it) }
        } else null

        val customMixedOrder: List<String>? = if (settings.has("customMixedOrder")) {
            val arr = settings.getJSONArray("customMixedOrder")
            (0 until arr.length()).map { arr.getString(it) }
        } else null

        val customGroupItemsOrders: Map<Long, List<String>>? = if (settings.has("customGroupItemsOrders")) {
            val obj = settings.getJSONObject("customGroupItemsOrders")
            val map = mutableMapOf<Long, List<String>>()
            for (key in obj.keys()) {
                val groupId = key.toLongOrNull() ?: continue
                val arr     = obj.getJSONArray(key)
                map[groupId] = (0 until arr.length()).map { arr.getString(it) }
            }
            map
        } else null

        // Shared across both libraries
        val independentSortEnabled: Boolean? =
            if (settings.has("independentSortEnabled")) settings.getBoolean("independentSortEnabled") else null

        val hiddenFolderPaths: Set<String>? = if (settings.has("hiddenFolderPaths")) {
            val arr = settings.getJSONArray("hiddenFolderPaths")
            (0 until arr.length()).map { arr.getString(it) }.toSet()
        } else null

        val hiddenFolderMeta: Map<String, Triple<String, Int, Int>>? = if (settings.has("hiddenFolderMeta")) {
            val obj = settings.getJSONObject("hiddenFolderMeta")
            val map = mutableMapOf<String, Triple<String, Int, Int>>()
            for (path in obj.keys()) {
                val meta      = obj.getJSONObject(path)
                val name      = meta.optString("name",      "")
                val bucketId  = meta.optInt   ("bucketId",  0)
                val itemCount = meta.optInt   ("itemCount", 0)
                if (name.isNotBlank()) map[path] = Triple(name, bucketId, itemCount)
            }
            map
        } else null

        return SharedSettings(
            viewType, folderViewType, customGroupOrder, customMixedOrder, customGroupItemsOrders,
            independentSortEnabled, hiddenFolderPaths, hiddenFolderMeta
        )
    }

    // ── Library-specific hooks ────────────────────────────────────────

    /** Serialise library-specific AppPreferences into a [JSONObject]. */
    protected abstract fun writeSettings(context: Context): JSONObject

    /** Restore library-specific AppPreferences from the supplied [settings] object. */
    protected abstract fun readSettings(context: Context, settings: JSONObject)

    /**
     * Override to coerce an older backup version into the current schema before
     * [readSettings] is called. Default implementation: returns [settings] unchanged.
     *
     * @param fromVersion the `backupVersion` value found in the file being restored
     * @param settings    the raw `settings` object from the backup file
     */
    protected open fun migrateSettings(fromVersion: Int, settings: JSONObject): JSONObject = settings
}


