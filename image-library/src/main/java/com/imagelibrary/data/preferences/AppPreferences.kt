package com.imagelibrary.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.imagelibrary.data.model.ImageSortOption
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.SortOrder
import com.imagelibrary.data.model.SortType
import com.imagelibrary.data.model.ViewType

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("image_library_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_VIEW_TYPE = "viewas_files"
        private const val KEY_FOLDER_VIEW_TYPE = "viewas_folder_detail"
        private const val KEY_SORT_OPTION = "sort_option"
        private const val KEY_GROUP_SORT_OPTION = "group_sort_option"
        private const val KEY_SORT_TYPE = "sort_type"
        private const val KEY_SORT_ORDER = "sort_order"
        private const val KEY_LATEST_UPDATE_TIME = "latest_update_time"
        private const val KEY_LATEST_UPDATE_FOLDER = "latest_update_folder"
        private const val KEY_FOLDER_NEW_MARK = "folder_new_mark"
        private const val KEY_CUSTOM_ALBUM_ORDER = "custom_album_order"
        private const val KEY_CUSTOM_GROUP_ORDER = "custom_group_order"
        private const val KEY_CUSTOM_MIXED_ORDER = "custom_mixed_order"
        private const val KEY_CUSTOM_GROUP_ITEMS_ORDER_PREFIX = "custom_group_items_order_"
        private const val KEY_IMAGE_SORT_OPTION = "image_sort_option"
        private const val KEY_CAROUSEL_SHOW_BARS_ON_OPEN = "carousel_show_bars_on_open"
        private const val KEY_AUTO_BACKUP_ENABLED = "auto_backup_enabled"
        private const val KEY_INDEPENDENT_SORT_ENABLED = "independent_sort_enabled"
        private const val KEY_GROUPS_ALWAYS_ON_TOP = "groups_always_on_top"
        private const val KEY_GROUP_SORT_OPTION_PREFIX = "group_sort_option_"
        private const val KEY_HIDDEN_FOLDER_PATHS = "hidden_folder_paths"
        private const val KEY_HIDDEN_FOLDER_META = "hidden_folder_meta"
    }
    /**
     * When true (default), each album/group can have its own sort order. If false, all use the global sort.
     */
    var independentSortEnabled: Boolean
        get() = prefs.getBoolean(KEY_INDEPENDENT_SORT_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_INDEPENDENT_SORT_ENABLED, value).apply()

    /**
     * When true, groups are always shown at the top of the sorted list,
     * sorted among themselves, followed by ungrouped albums sorted separately.
     */
    var groupsAlwaysOnTop: Boolean
        get() = prefs.getBoolean(KEY_GROUPS_ALWAYS_ON_TOP, false)
        set(value) = prefs.edit().putBoolean(KEY_GROUPS_ALWAYS_ON_TOP, value).apply()

    /**
     * Per-group sort option. Returns [SortOption.CUSTOM_ORDER] if not yet set.
     */
    fun getGroupSortOption(groupId: Long): SortOption {
        val id = prefs.getInt("$KEY_GROUP_SORT_OPTION_PREFIX$groupId", -1)
        return if (id == -1) SortOption.CUSTOM_ORDER else SortOption.fromId(id)
    }

    fun saveGroupSortOption(groupId: Long, option: SortOption) {
        prefs.edit().putInt("$KEY_GROUP_SORT_OPTION_PREFIX$groupId", option.id).apply()
    }

    // ── Hidden folders ──────────────────────────────────────────────────────

    /** Set of folder paths currently marked as hidden (have a .nomedia file). */
    var hiddenFolderPaths: Set<String>
        get() {
            val raw = prefs.getString(KEY_HIDDEN_FOLDER_PATHS, null) ?: return emptySet()
            return raw.split("|").filter { it.isNotEmpty() }.toSet()
        }
        set(value) = prefs.edit()
            .putString(KEY_HIDDEN_FOLDER_PATHS, value.joinToString("|"))
            .apply()

    fun saveHiddenFolderMeta(path: String, name: String, bucketId: Int, itemCount: Int) {
        val existing = getRawHiddenFolderMeta().toMutableMap()
        existing[path] = "$name::$bucketId::$itemCount"
        prefs.edit().putString(KEY_HIDDEN_FOLDER_META,
            existing.entries.joinToString("|") { "${it.key}::${it.value}" }).apply()
    }

    fun removeHiddenFolderMeta(path: String) {
        val existing = getRawHiddenFolderMeta().toMutableMap()
        existing.remove(path)
        prefs.edit().putString(KEY_HIDDEN_FOLDER_META,
            existing.entries.joinToString("|") { "${it.key}::${it.value}" }).apply()
    }

    /** Internal: returns path → "name::bucketId::itemCount" raw strings. */
    private fun getRawHiddenFolderMeta(): Map<String, String> {
        val raw = prefs.getString(KEY_HIDDEN_FOLDER_META, null) ?: return emptyMap()
        return raw.split("|").filter { it.contains("::") }.mapNotNull { entry ->
            val idx = entry.indexOf("::")
            if (idx < 0) null
            else entry.substring(0, idx) to entry.substring(idx + 2)
        }.toMap()
    }

    fun getAllHiddenFolderMeta(): Map<String, Triple<String, Int, Int>> {
        val result = mutableMapOf<String, Triple<String, Int, Int>>()
        getRawHiddenFolderMeta().forEach { (path, value) ->
            val parts = value.split("::")
            if (parts.size >= 3) {
                val name     = parts[0]
                val bucketId = parts[1].toIntOrNull() ?: return@forEach
                val count    = parts[2].toIntOrNull() ?: 0
                result[path] = Triple(name, bucketId, count)
            }
        }
        return result
    }

    var viewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_VIEW_TYPE, ViewType.GRID_LARGE.id))
        set(value) = prefs.edit().putInt(KEY_VIEW_TYPE, value.id).apply()

    var folderViewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_FOLDER_VIEW_TYPE, ViewType.GRID_LARGE.id))
        set(value) = prefs.edit().putInt(KEY_FOLDER_VIEW_TYPE, value.id).apply()

    var sortOption: SortOption
        get() = SortOption.fromId(prefs.getInt(KEY_SORT_OPTION, SortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_SORT_OPTION, value.id).apply()
    var imageSortOption: ImageSortOption
        get() = ImageSortOption.fromId(prefs.getInt(KEY_IMAGE_SORT_OPTION, ImageSortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_IMAGE_SORT_OPTION, value.id).apply()
    var groupSortOption: SortOption
        get() = SortOption.fromId(prefs.getInt(KEY_GROUP_SORT_OPTION, SortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_GROUP_SORT_OPTION, value.id).apply()

    var sortType: SortType
        get() = SortType.fromId(prefs.getInt(KEY_SORT_TYPE, SortType.DATE.id))
        set(value) = prefs.edit().putInt(KEY_SORT_TYPE, value.id).apply()

    var sortOrder: SortOrder
        get() = SortOrder.fromId(prefs.getInt(KEY_SORT_ORDER, SortOrder.DESCENDING.id))
        set(value) = prefs.edit().putInt(KEY_SORT_ORDER, value.id).apply()

    var latestUpdateTime: Long
        get() = prefs.getLong(KEY_LATEST_UPDATE_TIME, -1L)
        set(value) = prefs.edit().putLong(KEY_LATEST_UPDATE_TIME, value).apply()

    var latestUpdateFolder: Int
        get() = prefs.getInt(KEY_LATEST_UPDATE_FOLDER, 0)
        set(value) = prefs.edit().putInt(KEY_LATEST_UPDATE_FOLDER, value).apply()

    var folderNewMark: Boolean
        get() = prefs.getBoolean(KEY_FOLDER_NEW_MARK, false)
        set(value) = prefs.edit().putBoolean(KEY_FOLDER_NEW_MARK, value).apply()

    /** Persisted album order as a list of bucket IDs. Empty = not yet initialized. */
    var customAlbumOrder: List<Int>
        get() {
            val raw = prefs.getString(KEY_CUSTOM_ALBUM_ORDER, null) ?: return emptyList()
            return raw.split(",").mapNotNull { it.toIntOrNull() }
        }
        set(value) = prefs.edit().putString(
            KEY_CUSTOM_ALBUM_ORDER,
            value.joinToString(",")
        ).apply()

    /** Persisted group order as a list of group IDs. Empty = not yet initialized. */
    var customGroupOrder: List<Long>
        get() {
            val raw = prefs.getString(KEY_CUSTOM_GROUP_ORDER, null) ?: return emptyList()
            return raw.split(",").mapNotNull { it.toLongOrNull() }
        }
        set(value) = prefs.edit().putString(
            KEY_CUSTOM_GROUP_ORDER,
            value.joinToString(",")
        ).apply()

    /**
     * Unified interleaved display order of groups and folders.
     * Keys are "g_<groupId>" or "f_<bucketId>".
     */
    var customMixedOrder: List<String>
        get() {
            val raw = prefs.getString(KEY_CUSTOM_MIXED_ORDER, null) ?: return emptyList()
            return raw.split(",").filter { it.isNotEmpty() }
        }
        set(value) = prefs.edit().putString(
            KEY_CUSTOM_MIXED_ORDER,
            value.joinToString(",")
        ).apply()

    /**
     * Per-group ordered item list. Keys are "g_<groupId>" or "f_<bucketId>".
     * Each group stores its own display order independently.
     */
    fun customGroupItemsOrder(groupId: Long): List<String> {
        val raw = prefs.getString("$KEY_CUSTOM_GROUP_ITEMS_ORDER_PREFIX$groupId", null)
            ?: return emptyList()
        return raw.split(",").filter { it.isNotEmpty() }
    }

    fun setCustomGroupItemsOrder(groupId: Long, order: List<String>) {
        prefs.edit().putString(
            "$KEY_CUSTOM_GROUP_ITEMS_ORDER_PREFIX$groupId",
            order.joinToString(",")
        ).apply()
    }

    /**
     * Returns every per-group custom order currently stored, keyed by group ID.
     * Used by BackupManager to export all group item orderings.
     */
    fun allCustomGroupItemsOrders(): Map<Long, List<String>> {
        return prefs.all
            .entries
            .filter { it.key.startsWith(KEY_CUSTOM_GROUP_ITEMS_ORDER_PREFIX) }
            .mapNotNull { (key, value) ->
                val groupId = key.removePrefix(KEY_CUSTOM_GROUP_ITEMS_ORDER_PREFIX).toLongOrNull()
                    ?: return@mapNotNull null
                val order = (value as? String)?.split(",")?.filter { it.isNotEmpty() }
                    ?: return@mapNotNull null
                groupId to order
            }
            .toMap()
    }

    /** When true, carousel overlay bars are visible immediately when opening an image. */
    var carouselShowBarsOnOpen: Boolean
        get() = prefs.getBoolean(KEY_CAROUSEL_SHOW_BARS_ON_OPEN, false)
        set(value) = prefs.edit().putBoolean(KEY_CAROUSEL_SHOW_BARS_ON_OPEN, value).apply()

    /** When true, the app automatically backs up settings and group data after changes. */
    var autoBackupEnabled: Boolean
        get() = prefs.getBoolean(KEY_AUTO_BACKUP_ENABLED, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_BACKUP_ENABLED, value).apply()
}
