package com.videolibrary.data.util

import android.content.Context
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoSortOption
import com.videolibrary.data.model.ViewType
import com.videolibrary.data.preferences.AppPreferences
import org.json.JSONArray
import org.json.JSONObject

/**
 * Video-Library backup singleton.
 *
 * All file I/O, group-data persistence, and shared-settings serialisation are
 * handled by [com.example.common.data.util.BackupManager].
 * Only the video-specific AppPreferences keys are implemented here.
 *
 * Backup file: Documents/VideoLibrary/backups/backup.json
 */
object BackupManager : com.example.common.data.util.BackupManager(
    libraryFolderName = "VideoLibrary",
    logger            = FileLogger
) {

    // ── Video-Library settings → JSON ────────────────────────────────

    override fun writeSettings(context: Context): JSONObject {
        val prefs = AppPreferences(context)
        return JSONObject().apply {
            // Shared keys (identical format in both libraries)
            writeSharedSettings(
                settings               = this,
                viewType               = prefs.viewType.id,
                folderViewType         = prefs.folderViewType.id,
                customGroupOrder       = prefs.customGroupOrder,
                customMixedOrder       = prefs.customMixedOrder,
                customGroupItemsOrders = prefs.allCustomGroupItemsOrders(),
                independentSortEnabled = prefs.independentSortEnabled,
                hiddenFolderPaths      = prefs.hiddenFolderPaths,
                hiddenFolderMeta       = prefs.getAllHiddenFolderMeta()
            )

            // Video-library specific keys
            put("folderSortOption",     prefs.folderSortOption.id)
            put("videoSortOption",      prefs.videoSortOption.id)
            put("selectedTab",          prefs.selectedTab)
            put("instantPlayerEnabled", prefs.instantPlayerEnabled)

            // Custom folder order → JSONArray of ints
            put("customFolderOrder", JSONArray(prefs.getCustomFolderOrder()))

            // Per-folder video sort options → JSONObject { "bucketId": sortOptionId }
            val folderVideoSortsObj = JSONObject()
            prefs.getAllFolderVideoSortOptions().forEach { (bucketId, sortId) ->
                folderVideoSortsObj.put(bucketId.toString(), sortId)
            }
            put("folderVideoSortOptions", folderVideoSortsObj)
        }
    }

    // ── JSON → Video-Library settings ────────────────────────────────

    override fun readSettings(context: Context, settings: JSONObject) {
        val prefs = AppPreferences(context)

        // Shared keys
        val shared = readSharedSettings(settings)
        shared.viewType?.let               { prefs.viewType              = ViewType.fromId(it) }
        shared.folderViewType?.let         { prefs.folderViewType        = ViewType.fromId(it) }
        shared.customGroupOrder?.let       { prefs.customGroupOrder      = it }
        shared.customMixedOrder?.let       { prefs.customMixedOrder      = it }
        shared.customGroupItemsOrders?.forEach { (groupId, order) ->
            prefs.saveGroupMixedOrder(groupId, order)
        }
        shared.independentSortEnabled?.let { prefs.independentSortEnabled = it }
        shared.hiddenFolderPaths?.let      { prefs.hiddenFolderPaths     = it }
        shared.hiddenFolderMeta?.forEach   { (path, triple) ->
            prefs.saveHiddenFolderMeta(path, triple.first, triple.second, triple.third)
        }

        // Video-library specific keys
        if (settings.has("folderSortOption"))
            prefs.folderSortOption = FolderSortOption.fromId(settings.getInt("folderSortOption"))
        if (settings.has("videoSortOption"))
            prefs.videoSortOption = VideoSortOption.fromId(settings.getInt("videoSortOption"))
        if (settings.has("selectedTab"))
            prefs.selectedTab = settings.getInt("selectedTab")
        if (settings.has("instantPlayerEnabled"))
            prefs.instantPlayerEnabled = settings.getBoolean("instantPlayerEnabled")

        // Custom folder order — JSONArray of ints
        if (settings.has("customFolderOrder")) {
            val arr = settings.getJSONArray("customFolderOrder")
            prefs.saveCustomFolderOrder((0 until arr.length()).map { arr.getInt(it) })
        }

        // Per-folder video sort options — JSONObject { "bucketId": sortOptionId }
        if (settings.has("folderVideoSortOptions")) {
            val obj = settings.getJSONObject("folderVideoSortOptions")
            val map = mutableMapOf<Int, Int>()
            for (key in obj.keys()) {
                val bucketId = key.toIntOrNull() ?: continue
                map[bucketId] = obj.getInt(key)
            }
            prefs.saveAllFolderVideoSortOptions(map)
        }
    }

    // ── v1 → v2 migration ────────────────────────────────────────────

    /**
     * v1 stored order lists as raw comma-separated strings; v2 uses JSONArrays / JSONObjects.
     * This method coerces each affected key before [readSettings] is called.
     */
    override fun migrateSettings(fromVersion: Int, settings: JSONObject): JSONObject {
        if (fromVersion >= 2) return settings

        migrateToIntArray(settings,    "customFolderOrder")
        migrateToLongArray(settings,   "customGroupOrder")
        migrateToStringArray(settings, "customMixedOrder")

        // "bucketId:sortOptionId,..." → JSONObject { "bucketId": sortOptionId }
        val rawSorts = settings.opt("folderVideoSortOptions")
        if (rawSorts is String) {
            val obj = JSONObject()
            rawSorts.split(",").filter { it.contains(":") }.forEach { entry ->
                val parts = entry.split(":")
                if (parts.size == 2) {
                    val bucketId = parts[0].trim().toIntOrNull() ?: return@forEach
                    val sortId   = parts[1].trim().toIntOrNull() ?: return@forEach
                    obj.put(bucketId.toString(), sortId)
                }
            }
            settings.put("folderVideoSortOptions", obj)
        }

        return settings
    }

    // ── Migration helpers ─────────────────────────────────────────────
    // migrateToIntArray / migrateToLongArray / migrateToStringArray are
    // inherited as protected methods from com.example.common.data.util.BackupManager.
}

