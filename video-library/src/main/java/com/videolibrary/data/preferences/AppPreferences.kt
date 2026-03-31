package com.videolibrary.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoSortOption
import com.videolibrary.data.model.ViewType

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("video_library_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_VIEW_TYPE = "viewas_files"
        private const val KEY_FOLDER_VIEW_TYPE = "viewas_folder_detail"
        private const val KEY_FOLDER_SORT = "folder_tab_sort"
        private const val KEY_VIDEO_SORT = "video_tab_sort"
        private const val KEY_TAB_TYPE = "tab_type"
        private const val KEY_CUSTOM_FOLDER_ORDER = "custom_folder_order"
        private const val KEY_FOLDER_VIDEO_SORT_OPTIONS = "folder_video_sort_options"
        // Group ordering
        private const val KEY_CUSTOM_MIXED_ORDER  = "custom_mixed_order"
        private const val KEY_CUSTOM_GROUP_ORDER  = "custom_group_order"
        // Per-group item order (inside a group's detail screen)
        private const val KEY_GROUP_MIXED_ORDER_PREFIX = "group_mixed_order_"
        // Backup
        private const val KEY_AUTO_BACKUP = "auto_backup_enabled"
        // Instant Player
        private const val KEY_INSTANT_PLAYER = "instant_player_enabled"
        private const val KEY_INDEPENDENT_SORT_ENABLED = "independent_sort_enabled"
    }
    /**
     * When true (default), each album/group can have its own sort order. If false, all use the global sort.
     */
    var independentSortEnabled: Boolean
        get() = prefs.getBoolean(KEY_INDEPENDENT_SORT_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_INDEPENDENT_SORT_ENABLED, value).apply()

    var viewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_VIEW_TYPE, ViewType.LIST.id))
        set(value) = prefs.edit().putInt(KEY_VIEW_TYPE, value.id).apply()

    var folderViewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_FOLDER_VIEW_TYPE, ViewType.LIST.id))
        set(value) = prefs.edit().putInt(KEY_FOLDER_VIEW_TYPE, value.id).apply()

    /** Folder-tab sort (Custom, Name, Item count). */
    var folderSortOption: FolderSortOption
        get() = FolderSortOption.fromId(prefs.getInt(KEY_FOLDER_SORT, FolderSortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_FOLDER_SORT, value.id).apply()

    /** Videos-tab sort (Custom, Name, Date created, Date modified). */
    var videoSortOption: VideoSortOption
        get() = VideoSortOption.fromId(prefs.getInt(KEY_VIDEO_SORT, VideoSortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_VIDEO_SORT, value.id).apply()


    var selectedTab: Int
        get() = prefs.getInt(KEY_TAB_TYPE, 1)
        set(value) = prefs.edit().putInt(KEY_TAB_TYPE, value).apply()



    // Custom folder order: stored as "bucketId1,bucketId2,bucketId3,..."
    fun getCustomFolderOrder(): List<Int> {
        val raw = prefs.getString(KEY_CUSTOM_FOLDER_ORDER, "") ?: ""
        if (raw.isBlank()) return emptyList()
        return raw.split(",").mapNotNull { it.trim().toIntOrNull() }
    }

    fun saveCustomFolderOrder(order: List<Int>) {
        val serialized = order.joinToString(",")
        prefs.edit().putString(KEY_CUSTOM_FOLDER_ORDER, serialized).apply()
    }

    // Per-folder video sort (inside album): stored as "bucketId:sortOptionId,..."
    fun getFolderVideoSortOption(bucketId: Int): VideoSortOption {
        val raw = prefs.getString(KEY_FOLDER_VIDEO_SORT_OPTIONS, "") ?: ""
        val id = raw.split(",")
            .mapNotNull { entry ->
                val parts = entry.split(":")
                if (parts.size == 2 && parts[0].toIntOrNull() == bucketId) parts[1].toIntOrNull()
                else null
            }
            .firstOrNull()
        return if (id != null) VideoSortOption.fromId(id) else VideoSortOption.CUSTOM_ORDER
    }

    fun saveFolderVideoSortOption(bucketId: Int, sortOption: VideoSortOption) {
        val raw = prefs.getString(KEY_FOLDER_VIDEO_SORT_OPTIONS, "") ?: ""
        val map = raw.split(",")
            .filter { it.contains(":") }
            .associate {
                val parts = it.split(":")
                (parts[0].toIntOrNull() ?: 0) to (parts[1].toIntOrNull() ?: 0)
            }
            .toMutableMap()
        map[bucketId] = sortOption.id
        val entries = map.entries.toList().takeLast(200)
        val serialized = entries.joinToString(",") { e -> "${e.key}:${e.value}" }
        prefs.edit().putString(KEY_FOLDER_VIDEO_SORT_OPTIONS, serialized).apply()
    }

    /**
     * Unified interleaved display order of groups and folders on the Folders tab.
     * Keys are "g_<groupId>" or "f_<bucketId>".
     * Example: "g_1,f_1003,g_2,f_1007"
     */
    var customMixedOrder: List<String>
        get() {
            val raw = prefs.getString(KEY_CUSTOM_MIXED_ORDER, null) ?: return emptyList()
            return raw.split(",").filter { it.isNotEmpty() }
        }
        set(value) = prefs.edit()
            .putString(KEY_CUSTOM_MIXED_ORDER, value.joinToString(","))
            .apply()

    var customGroupOrder: List<Long>
        get() = prefs.getString(KEY_CUSTOM_GROUP_ORDER, null)
            ?.split(",")?.mapNotNull { it.toLongOrNull() } ?: emptyList()
        set(value) = prefs.edit()
            .putString(KEY_CUSTOM_GROUP_ORDER, value.joinToString(",")).apply()

    /**
     * Per-group interleaved display order for a specific group's detail screen.
     * Keys are "g_<groupId>" for sub-groups or "f_<bucketId>" for folders.
     */
    fun getGroupMixedOrder(groupId: Long): List<String> {
        val raw = prefs.getString("$KEY_GROUP_MIXED_ORDER_PREFIX$groupId", null) ?: return emptyList()
        return raw.split(",").filter { it.isNotEmpty() }
    }

    fun saveGroupMixedOrder(groupId: Long, order: List<String>) {
        prefs.edit()
            .putString("$KEY_GROUP_MIXED_ORDER_PREFIX$groupId", order.joinToString(","))
            .apply()
    }

    /**
     * Returns every per-group custom order currently stored, keyed by group ID.
     * Used by BackupManager to export all group item orderings.
     */
    fun allCustomGroupItemsOrders(): Map<Long, List<String>> {
        return prefs.all
            .entries
            .filter { it.key.startsWith(KEY_GROUP_MIXED_ORDER_PREFIX) }
            .mapNotNull { (key, value) ->
                val groupId = key.removePrefix(KEY_GROUP_MIXED_ORDER_PREFIX).toLongOrNull()
                    ?: return@mapNotNull null
                val order = (value as? String)?.split(",")?.filter { it.isNotEmpty() }
                    ?: return@mapNotNull null
                groupId to order
            }
            .toMap()
    }

    /**
     * Returns all per-folder video sort options as a Map of bucketId → sortOptionId.
     * Used by BackupManager to export all per-folder sort settings.
     */
    fun getAllFolderVideoSortOptions(): Map<Int, Int> {
        val raw = prefs.getString(KEY_FOLDER_VIDEO_SORT_OPTIONS, "") ?: ""
        if (raw.isBlank()) return emptyMap()
        return raw.split(",")
            .filter { it.contains(":") }
            .mapNotNull { entry ->
                val parts = entry.split(":")
                if (parts.size == 2) {
                    val bucketId = parts[0].trim().toIntOrNull() ?: return@mapNotNull null
                    val sortId   = parts[1].trim().toIntOrNull() ?: return@mapNotNull null
                    bucketId to sortId
                } else null
            }
            .toMap()
    }

    /**
     * Restores all per-folder video sort options from a Map of bucketId → sortOptionId.
     * Used by BackupManager to import all per-folder sort settings.
     */
    fun saveAllFolderVideoSortOptions(options: Map<Int, Int>) {
        val serialized = options.entries.joinToString(",") { "${it.key}:${it.value}" }
        prefs.edit().putString(KEY_FOLDER_VIDEO_SORT_OPTIONS, serialized).apply()
    }

    /** When true, tapping a video starts playback immediately without entering the detail screen. */
    var instantPlayerEnabled: Boolean
        get()  = prefs.getBoolean(KEY_INSTANT_PLAYER, false)
        set(v) = prefs.edit().putBoolean(KEY_INSTANT_PLAYER, v).apply()

    /** When true, the app automatically backs up settings and group data after changes. */
    var autoBackupEnabled: Boolean
        get()  = prefs.getBoolean(KEY_AUTO_BACKUP, false)
        set(v) = prefs.edit().putBoolean(KEY_AUTO_BACKUP, v).apply()
}
