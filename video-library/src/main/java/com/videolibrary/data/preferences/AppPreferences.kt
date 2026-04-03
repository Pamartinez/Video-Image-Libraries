package com.videolibrary.data.preferences

import android.content.Context
import com.example.common.data.model.ViewType
import com.example.common.data.preferences.SharedAppPreferences
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoSortOption

class AppPreferences(context: Context) : SharedAppPreferences(
    prefs                    = context.getSharedPreferences("video_library_prefs", Context.MODE_PRIVATE),
    defaultViewTypeId        = ViewType.LIST.id,
    defaultFolderViewTypeId  = ViewType.LIST.id,
    groupItemsOrderKeyPrefix = "group_mixed_order_"
) {
    companion object {
        private const val KEY_FOLDER_SORT             = "folder_tab_sort"
        private const val KEY_VIDEO_SORT              = "video_tab_sort"
        private const val KEY_TAB_TYPE                = "tab_type"
        private const val KEY_CUSTOM_FOLDER_ORDER     = "custom_folder_order"
        private const val KEY_FOLDER_VIDEO_SORT_OPTIONS = "folder_video_sort_options"
        private const val KEY_INSTANT_PLAYER          = "instant_player_enabled"
    }

    // ── Video-library specific ───────────────────────────────────────────────

    /** Folder-tab sort (Custom, Name, Item count). */
    var folderSortOption: FolderSortOption
        get() = FolderSortOption.fromId(prefs.getInt(KEY_FOLDER_SORT, FolderSortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_FOLDER_SORT, value.id).apply()

    /** Videos-tab sort (Custom, Name, Date created, Date modified, Duration). */
    var videoSortOption: VideoSortOption
        get() = VideoSortOption.fromId(prefs.getInt(KEY_VIDEO_SORT, VideoSortOption.CUSTOM_ORDER.id))
        set(value) = prefs.edit().putInt(KEY_VIDEO_SORT, value.id).apply()

    var selectedTab: Int
        get() = prefs.getInt(KEY_TAB_TYPE, 1)
        set(value) = prefs.edit().putInt(KEY_TAB_TYPE, value).apply()

    // Custom folder order: stored as "bucketId1,bucketId2,..."
    fun getCustomFolderOrder(): List<Int> {
        val raw = prefs.getString(KEY_CUSTOM_FOLDER_ORDER, "") ?: ""
        if (raw.isBlank()) return emptyList()
        return raw.split(",").mapNotNull { it.trim().toIntOrNull() }
    }

    fun saveCustomFolderOrder(order: List<Int>) {
        prefs.edit().putString(KEY_CUSTOM_FOLDER_ORDER, order.joinToString(",")).apply()
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
        prefs.edit().putString(KEY_FOLDER_VIDEO_SORT_OPTIONS,
            entries.joinToString(",") { e -> "${e.key}:${e.value}" }).apply()
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
        prefs.edit().putString(KEY_FOLDER_VIDEO_SORT_OPTIONS,
            options.entries.joinToString(",") { "${it.key}:${it.value}" }).apply()
    }

    /** When true, tapping a video starts playback immediately without entering the detail screen. */
    var instantPlayerEnabled: Boolean
        get()  = prefs.getBoolean(KEY_INSTANT_PLAYER, false)
        set(v) = prefs.edit().putBoolean(KEY_INSTANT_PLAYER, v).apply()
}
