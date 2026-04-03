package com.example.common.data.preferences

import android.content.SharedPreferences
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.ViewType

/**
 * Base class containing ALL SharedPreferences properties that are identical in both
 * image-library and video-library.
 *
 * Each library's `AppPreferences` extends this class, passing only the values that
 * differ between the two (default view types and the per-group order key prefix).
 *
 * @param prefs                   The [SharedPreferences] instance for this library.
 * @param defaultViewTypeId       Default [ViewType.id] for [viewType] (image = GRID_LARGE, video = LIST).
 * @param defaultFolderViewTypeId Default [ViewType.id] for [folderViewType].
 * @param groupItemsOrderKeyPrefix Key prefix for per-group item orderings.
 *   image-library = "custom_group_items_order_"
 *   video-library = "group_mixed_order_"
 */
open class SharedAppPreferences(
    protected val prefs: SharedPreferences,
    private val defaultViewTypeId: Int = ViewType.GRID_LARGE.id,
    private val defaultFolderViewTypeId: Int = ViewType.GRID_LARGE.id,
    private val groupItemsOrderKeyPrefix: String = "custom_group_items_order_"
) {
    companion object {
        private const val KEY_VIEW_TYPE            = "viewas_files"
        private const val KEY_FOLDER_VIEW_TYPE     = "viewas_folder_detail"
        private const val KEY_INDEPENDENT_SORT     = "independent_sort_enabled"
        private const val KEY_GROUPS_ALWAYS_ON_TOP = "groups_always_on_top"
        private const val KEY_GROUP_SORT_PREFIX    = "group_sort_option_"
        private const val KEY_HIDDEN_FOLDER_PATHS  = "hidden_folder_paths"
        private const val KEY_HIDDEN_FOLDER_META   = "hidden_folder_meta"
        private const val KEY_AUTO_BACKUP          = "auto_backup_enabled"
        private const val KEY_CUSTOM_MIXED_ORDER   = "custom_mixed_order"
        private const val KEY_CUSTOM_GROUP_ORDER   = "custom_group_order"
    }

    // ── Sort / display toggles ───────────────────────────────────────────────

    /**
     * When true (default), each album/group can have its own sort order.
     * When false, all groups use the global sort.
     */
    var independentSortEnabled: Boolean
        get() = prefs.getBoolean(KEY_INDEPENDENT_SORT, true)
        set(value) = prefs.edit().putBoolean(KEY_INDEPENDENT_SORT, value).apply()

    /**
     * When true, groups are always shown at the top of the sorted list,
     * sorted among themselves, followed by ungrouped albums sorted separately.
     */
    var groupsAlwaysOnTop: Boolean
        get() = prefs.getBoolean(KEY_GROUPS_ALWAYS_ON_TOP, false)
        set(value) = prefs.edit().putBoolean(KEY_GROUPS_ALWAYS_ON_TOP, value).apply()

    // ── Per-group sort option ────────────────────────────────────────────────

    /** Returns the per-group sort option, defaulting to [FolderSortOption.CUSTOM_ORDER]. */
    fun getGroupSortOption(groupId: Long): FolderSortOption {
        val id = prefs.getInt("$KEY_GROUP_SORT_PREFIX$groupId", -1)
        return if (id == -1) FolderSortOption.CUSTOM_ORDER else FolderSortOption.fromId(id)
    }

    fun saveGroupSortOption(groupId: Long, option: FolderSortOption) {
        prefs.edit().putInt("$KEY_GROUP_SORT_PREFIX$groupId", option.id).apply()
    }

    // ── Hidden folders ───────────────────────────────────────────────────────

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
        prefs.edit().putString(
            KEY_HIDDEN_FOLDER_META,
            existing.entries.joinToString("|") { "${it.key}::${it.value}" }
        ).apply()
    }

    fun removeHiddenFolderMeta(path: String) {
        val existing = getRawHiddenFolderMeta().toMutableMap()
        existing.remove(path)
        prefs.edit().putString(
            KEY_HIDDEN_FOLDER_META,
            existing.entries.joinToString("|") { "${it.key}::${it.value}" }
        ).apply()
    }

    protected fun getRawHiddenFolderMeta(): Map<String, String> {
        val raw = prefs.getString(KEY_HIDDEN_FOLDER_META, null) ?: return emptyMap()
        return raw.split("|").filter { it.contains("::") }.mapNotNull { entry ->
            val idx = entry.indexOf("::")
            if (idx < 0) null else entry.substring(0, idx) to entry.substring(idx + 2)
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

    // ── View type ────────────────────────────────────────────────────────────

    var viewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_VIEW_TYPE, defaultViewTypeId))
        set(value) = prefs.edit().putInt(KEY_VIEW_TYPE, value.id).apply()

    var folderViewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_FOLDER_VIEW_TYPE, defaultFolderViewTypeId))
        set(value) = prefs.edit().putInt(KEY_FOLDER_VIEW_TYPE, value.id).apply()

    // ── Backup ───────────────────────────────────────────────────────────────

    /** When true, the app automatically backs up settings and group data after changes. */
    var autoBackupEnabled: Boolean
        get() = prefs.getBoolean(KEY_AUTO_BACKUP, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_BACKUP, value).apply()

    // ── Custom orders ────────────────────────────────────────────────────────

    /**
     * Unified interleaved display order of groups and folders.
     * Keys are "g_<groupId>" or "f_<bucketId>".
     */
    var customMixedOrder: List<String>
        get() {
            val raw = prefs.getString(KEY_CUSTOM_MIXED_ORDER, null) ?: return emptyList()
            return raw.split(",").filter { it.isNotEmpty() }
        }
        set(value) = prefs.edit()
            .putString(KEY_CUSTOM_MIXED_ORDER, value.joinToString(","))
            .apply()

    /** Persisted group order as a list of group IDs. Empty = not yet initialized. */
    var customGroupOrder: List<Long>
        get() = prefs.getString(KEY_CUSTOM_GROUP_ORDER, null)
            ?.split(",")?.mapNotNull { it.toLongOrNull() } ?: emptyList()
        set(value) = prefs.edit()
            .putString(KEY_CUSTOM_GROUP_ORDER, value.joinToString(",")).apply()

    // ── Per-group item orders ────────────────────────────────────────────────

    /**
     * Per-group ordered item list. Keys are "g_<groupId>" or "f_<bucketId>".
     * Each group stores its own display order independently.
     */
    fun customGroupItemsOrder(groupId: Long): List<String> {
        val raw = prefs.getString("$groupItemsOrderKeyPrefix$groupId", null) ?: return emptyList()
        return raw.split(",").filter { it.isNotEmpty() }
    }

    fun setCustomGroupItemsOrder(groupId: Long, order: List<String>) {
        prefs.edit().putString(
            "$groupItemsOrderKeyPrefix$groupId",
            order.joinToString(",")
        ).apply()
    }

    /**
     * Alias for [customGroupItemsOrder] — backward compatibility with video-library
     * code that calls [getGroupMixedOrder].
     */
    fun getGroupMixedOrder(groupId: Long): List<String> = customGroupItemsOrder(groupId)

    /**
     * Alias for [setCustomGroupItemsOrder] — backward compatibility with video-library
     * code that calls [saveGroupMixedOrder].
     */
    fun saveGroupMixedOrder(groupId: Long, order: List<String>) =
        setCustomGroupItemsOrder(groupId, order)

    /**
     * Returns every per-group custom order currently stored, keyed by group ID.
     * Used by BackupManager to export all group item orderings.
     */
    fun allCustomGroupItemsOrders(): Map<Long, List<String>> {
        return prefs.all
            .entries
            .filter { it.key.startsWith(groupItemsOrderKeyPrefix) }
            .mapNotNull { (key, value) ->
                val groupId = key.removePrefix(groupItemsOrderKeyPrefix).toLongOrNull()
                    ?: return@mapNotNull null
                val order = (value as? String)?.split(",")?.filter { it.isNotEmpty() }
                    ?: return@mapNotNull null
                groupId to order
            }
            .toMap()
    }
}

