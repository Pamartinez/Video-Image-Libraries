package com.imagelibrary.data.util

import android.content.Context
import com.imagelibrary.data.model.ImageSortOption
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.SortOrder
import com.imagelibrary.data.model.SortType
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.data.preferences.AppPreferences
import org.json.JSONArray
import org.json.JSONObject

/**
 * Image-Library backup singleton.
 *
 * All file I/O, group-data persistence, and shared-settings serialisation are
 * handled by [com.example.common.data.util.BackupManager].
 * Only the image-specific AppPreferences keys are implemented here.
 *
 * Backup file: Documents/ImageLibrary/backups/backup.json
 */
object BackupManager : com.example.common.data.util.BackupManager(
    libraryFolderName = "ImageLibrary",
    logger            = FileLogger
) {

    // ── Image-Library settings → JSON ────────────────────────────────

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
                groupSortOptions       = prefs.getAllGroupSortOptions(),
                groupViewTypes         = prefs.getAllGroupViewTypes(),
                independentSortEnabled = prefs.independentSortEnabled,
                independentViewTypeEnabled = prefs.independentViewTypeEnabled,
                groupsAlwaysOnTop      = prefs.groupsAlwaysOnTop,
                autoBackupEnabled      = prefs.autoBackupEnabled,
                floatingTopBarEnabled  = prefs.floatingTopBarEnabled,
                hiddenFolderPaths      = prefs.hiddenFolderPaths,
                hiddenFolderMeta       = prefs.getAllHiddenFolderMeta()
            )

            // Image-library specific keys
            put("sortOption",             prefs.sortOption.id)
            put("imageSortOption",        prefs.imageSortOption.id)
            put("sortType",               prefs.sortType.id)
            put("sortOrder",              prefs.sortOrder.id)
            put("carouselShowBarsOnOpen", prefs.carouselShowBarsOnOpen)
            put("carouselAlwaysHideOverlay", prefs.carouselAlwaysHideOverlay)
            put("customAlbumOrder",       JSONArray(prefs.customAlbumOrder))

            // Per-folder view types → JSONObject { "bucketId": viewTypeId }
            val folderViewTypesObj = JSONObject()
            prefs.getAllFolderViewTypes().forEach { (bucketId, viewTypeId) ->
                folderViewTypesObj.put(bucketId.toString(), viewTypeId)
            }
            put("folderViewTypes", folderViewTypesObj)
        }
    }

    // ── JSON → Image-Library settings ────────────────────────────────

    override fun readSettings(context: Context, settings: JSONObject) {
        val prefs = AppPreferences(context)

        // Shared keys
        val shared = readSharedSettings(settings)
        shared.viewType?.let               { prefs.viewType              = ViewType.fromId(it) }
        shared.folderViewType?.let         { prefs.folderViewType        = ViewType.fromId(it) }
        shared.customGroupOrder?.let       { prefs.customGroupOrder      = it }
        shared.customMixedOrder?.let       { prefs.customMixedOrder      = it }
        shared.customGroupItemsOrders?.forEach { (groupId, order) ->
            prefs.setCustomGroupItemsOrder(groupId, order)
        }
        shared.groupSortOptions?.forEach { (groupId, sortId) ->
            prefs.saveGroupSortOption(groupId, SortOption.fromId(sortId))
        }
        shared.groupViewTypes?.let { prefs.saveAllGroupViewTypes(it) }
        shared.independentSortEnabled?.let { prefs.independentSortEnabled = it }
        shared.independentViewTypeEnabled?.let { prefs.independentViewTypeEnabled = it }
        shared.groupsAlwaysOnTop?.let      { prefs.groupsAlwaysOnTop      = it }
        shared.autoBackupEnabled?.let      { prefs.autoBackupEnabled      = it }
        shared.floatingTopBarEnabled?.let  { prefs.floatingTopBarEnabled  = it }
        shared.hiddenFolderPaths?.let      { prefs.hiddenFolderPaths     = it }
        shared.hiddenFolderMeta?.forEach   { (path, triple) ->
            prefs.saveHiddenFolderMeta(path, triple.first, triple.second, triple.third)
        }

        // Image-library specific keys
        if (settings.has("sortOption"))
            prefs.sortOption = SortOption.fromId(settings.getInt("sortOption"))
        if (settings.has("imageSortOption"))
            prefs.imageSortOption = ImageSortOption.fromId(settings.getInt("imageSortOption"))
        if (settings.has("sortType"))
            prefs.sortType = SortType.fromId(settings.getInt("sortType"))
        if (settings.has("sortOrder"))
            prefs.sortOrder = SortOrder.fromId(settings.getInt("sortOrder"))
        if (settings.has("carouselShowBarsOnOpen"))
            prefs.carouselShowBarsOnOpen = settings.getBoolean("carouselShowBarsOnOpen")
        if (settings.has("carouselAlwaysHideOverlay"))
            prefs.carouselAlwaysHideOverlay = settings.getBoolean("carouselAlwaysHideOverlay")
        if (settings.has("customAlbumOrder")) {
            val arr = settings.getJSONArray("customAlbumOrder")
            prefs.customAlbumOrder = (0 until arr.length()).map { arr.getInt(it) }
        }

        // Per-album image sort options — JSONObject { "bucketId": sortOptionId }
        if (settings.has("folderImageSortOptions")) {
            val obj = settings.getJSONObject("folderImageSortOptions")
            val map = mutableMapOf<Int, Int>()
            for (key in obj.keys()) {
                val bucketId = key.toIntOrNull() ?: continue
                map[bucketId] = obj.getInt(key)
            }
            prefs.restoreAllFolderImageSortOptions(map)
        }

        // Per-folder view types — JSONObject { "bucketId": viewTypeId }
        if (settings.has("folderViewTypes")) {
            val obj = settings.getJSONObject("folderViewTypes")
            val map = mutableMapOf<Int, Int>()
            for (key in obj.keys()) {
                val bucketId = key.toIntOrNull() ?: continue
                map[bucketId] = obj.getInt(key)
            }
            prefs.restoreAllFolderViewTypes(map)
        }
    }
}
