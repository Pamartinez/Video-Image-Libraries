package com.imagelibrary.data.preferences

import android.content.Context
import com.example.common.data.model.ViewType
import com.example.common.data.preferences.SharedAppPreferences
import com.imagelibrary.data.model.ImageSortOption
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.SortOrder
import com.imagelibrary.data.model.SortType

class AppPreferences(context: Context) : SharedAppPreferences(
    prefs                   = context.getSharedPreferences("image_library_prefs", Context.MODE_PRIVATE),
    defaultViewTypeId       = ViewType.GRID_LARGE.id,
    defaultFolderViewTypeId = ViewType.GRID_LARGE.id,
    groupItemsOrderKeyPrefix = "custom_group_items_order_"
) {
    companion object {
        private const val KEY_SORT_OPTION              = "sort_option"
        private const val KEY_GROUP_SORT_OPTION        = "group_sort_option"
        private const val KEY_SORT_TYPE                = "sort_type"
        private const val KEY_SORT_ORDER               = "sort_order"
        private const val KEY_LATEST_UPDATE_TIME       = "latest_update_time"
        private const val KEY_LATEST_UPDATE_FOLDER     = "latest_update_folder"
        private const val KEY_FOLDER_NEW_MARK          = "folder_new_mark"
        private const val KEY_CUSTOM_ALBUM_ORDER       = "custom_album_order"
        private const val KEY_IMAGE_SORT_OPTION        = "image_sort_option"
        private const val KEY_FOLDER_IMAGE_SORT_OPTIONS = "folder_image_sort_options"
        private const val KEY_CAROUSEL_SHOW_BARS_ON_OPEN = "carousel_show_bars_on_open"
        private const val KEY_CAROUSEL_ALWAYS_HIDE_OVERLAY = "carousel_always_hide_overlay"
    }

    // ── Image-library specific ───────────────────────────────────────────────

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

    /** When true, carousel overlay bars are visible immediately when opening an image. */
    var carouselShowBarsOnOpen: Boolean
        get() = prefs.getBoolean(KEY_CAROUSEL_SHOW_BARS_ON_OPEN, false)
        set(value) = prefs.edit().putBoolean(KEY_CAROUSEL_SHOW_BARS_ON_OPEN, value).apply()

    /** When true, carousel overlay is always hidden and cannot be toggled. */
    var carouselAlwaysHideOverlay: Boolean
        get() = prefs.getBoolean(KEY_CAROUSEL_ALWAYS_HIDE_OVERLAY, false)
        set(value) = prefs.edit().putBoolean(KEY_CAROUSEL_ALWAYS_HIDE_OVERLAY, value).apply()

    // Per-album image sort (inside album): stored as "bucketId:sortOptionId,..."
    fun getFolderImageSortOption(bucketId: Int): ImageSortOption {
        val raw = prefs.getString(KEY_FOLDER_IMAGE_SORT_OPTIONS, "") ?: ""
        val id = raw.split(",")
            .mapNotNull { entry ->
                val parts = entry.split(":")
                if (parts.size == 2 && parts[0].toIntOrNull() == bucketId) parts[1].toIntOrNull()
                else null
            }
            .firstOrNull()
        return if (id != null) ImageSortOption.fromId(id) else ImageSortOption.CUSTOM_ORDER
    }

    fun saveFolderImageSortOption(bucketId: Int, sortOption: ImageSortOption) {
        val raw = prefs.getString(KEY_FOLDER_IMAGE_SORT_OPTIONS, "") ?: ""
        val map = raw.split(",")
            .filter { it.contains(":") }
            .associate {
                val parts = it.split(":")
                (parts[0].toIntOrNull() ?: 0) to (parts[1].toIntOrNull() ?: 0)
            }
            .toMutableMap()
        map[bucketId] = sortOption.id
        val entries = map.entries.toList().takeLast(200)
        prefs.edit().putString(KEY_FOLDER_IMAGE_SORT_OPTIONS,
            entries.joinToString(",") { e -> "${e.key}:${e.value}" }).apply()
    }

    /**
     * Returns all per-album image sort options as a Map of bucketId → sortOptionId.
     * Used by BackupManager to export all per-album sort settings.
     */
    fun getAllFolderImageSortOptions(): Map<Int, Int> {
        val raw = prefs.getString(KEY_FOLDER_IMAGE_SORT_OPTIONS, "") ?: ""
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
     * Restores all per-album image sort options from a Map of bucketId → sortOptionId.
     * Used by BackupManager to import per-album sort settings.
     */
    fun restoreAllFolderImageSortOptions(options: Map<Int, Int>) {
        val entries = options.entries.toList().takeLast(200)
        prefs.edit().putString(KEY_FOLDER_IMAGE_SORT_OPTIONS,
            entries.joinToString(",") { "${it.key}:${it.value}" }).apply()
    }
}
