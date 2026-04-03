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
        private const val KEY_CAROUSEL_SHOW_BARS_ON_OPEN = "carousel_show_bars_on_open"
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
}
