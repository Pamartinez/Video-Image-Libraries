package com.gallerytransferlibrary.data.preferences

import android.content.Context
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.ViewType
import com.gallerytransferlibrary.data.model.AutoUploadFrequency
import com.gallerytransferlibrary.data.model.FilterSortOption
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaSortOption
import com.gallerytransferlibrary.data.model.SizeFilter

/**
 * Lightweight preference store for Gallery Transfer.
 * Holds view type, sort options, and the Dropbox destination folder path.
 */
class AppPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var viewType: ViewType
        get() = ViewType.fromId(prefs.getInt(KEY_VIEW_TYPE, ViewType.GRID_LARGE.id))
        set(value) = prefs.edit().putInt(KEY_VIEW_TYPE, value.id).apply()

    var folderSort: FolderSortOption
        get() = FolderSortOption.fromId(prefs.getInt(KEY_FOLDER_SORT, FolderSortOption.NAME_A_TO_Z.id))
        set(value) = prefs.edit().putInt(KEY_FOLDER_SORT, value.id).apply()

    var mediaSort: MediaSortOption
        get() = MediaSortOption.fromId(prefs.getInt(KEY_MEDIA_SORT, MediaSortOption.DATE_NEWEST.id))
        set(value) = prefs.edit().putInt(KEY_MEDIA_SORT, value.id).apply()

    /** Destination folder inside the user's Dropbox. Root = "" (uploads to /). */
    var dropboxDestPath: String
        get() = prefs.getString(KEY_DROPBOX_DEST, "") ?: ""
        set(value) = prefs.edit().putString(KEY_DROPBOX_DEST, value).apply()

    /**
     * How background uploads (app minimized/closed, no dialog possible) resolve a filename clash.
     * true = overwrite the existing file; false = keep both (upload with autorename). Default false.
     */
    var overwriteOnConflict: Boolean
        get() = prefs.getBoolean(KEY_OVERWRITE_CONFLICT, false)
        set(value) = prefs.edit().putBoolean(KEY_OVERWRITE_CONFLICT, value).apply()

    /**
     * When true, each item is deleted (moved to the system trash) from this device after it has been
     * successfully uploaded to Dropbox. Default false.
     */
    var deleteAfterUpload: Boolean
        get() = prefs.getBoolean(KEY_DELETE_AFTER_UPLOAD, true)
        set(value) = prefs.edit().putBoolean(KEY_DELETE_AFTER_UPLOAD, value).apply()

    /**
     * When true, uploads preserve the source folder: an item is placed in a sub-folder (named after
     * the folder it lives in on the device) under the chosen Dropbox destination. Default false.
     */
    var keepFolderStructure: Boolean
        get() = prefs.getBoolean(KEY_KEEP_FOLDER_STRUCTURE, true)
        set(value) = prefs.edit().putBoolean(KEY_KEEP_FOLDER_STRUCTURE, value).apply()

    /** Type filter for the flat Filter list. */
    var filterType: FilterType
        get() = FilterType.fromId(prefs.getInt(KEY_FILTER_TYPE, FilterType.ALL.id))
        set(value) = prefs.edit().putInt(KEY_FILTER_TYPE, value.id).apply()

    /** Sort order for the flat Filter list. */
    var filterSort: FilterSortOption
        get() = FilterSortOption.fromId(prefs.getInt(KEY_FILTER_SORT, FilterSortOption.DATE_NEWEST.id))
        set(value) = prefs.edit().putInt(KEY_FILTER_SORT, value.id).apply()

    /** Size bucket filter for the flat Filter list. */
    var filterSize: SizeFilter
        get() = SizeFilter.fromId(prefs.getInt(KEY_FILTER_SIZE, SizeFilter.ALL.id))
        set(value) = prefs.edit().putInt(KEY_FILTER_SIZE, value.id).apply()

    // ── Background auto-upload ──────────────────────────────────────────

    /** When true, a background worker auto-uploads media older than [autoUploadOlderThanDays]. */
    var autoUploadEnabled: Boolean
        get() = prefs.getBoolean(KEY_AUTO_UPLOAD_ENABLED, false)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_UPLOAD_ENABLED, value).apply()

    /** Age threshold (in days): items modified more than this many days ago are auto-uploaded. */
    var autoUploadOlderThanDays: Int
        get() = prefs.getInt(KEY_AUTO_UPLOAD_DAYS, 30)
        set(value) = prefs.edit().putInt(KEY_AUTO_UPLOAD_DAYS, value.coerceAtLeast(1)).apply()

    /** How often the background worker runs. */
    var autoUploadFrequency: AutoUploadFrequency
        get() = AutoUploadFrequency.fromId(prefs.getInt(KEY_AUTO_UPLOAD_FREQ, AutoUploadFrequency.DAILY.id))
        set(value) = prefs.edit().putInt(KEY_AUTO_UPLOAD_FREQ, value.id).apply()

    /** When true, the background worker uploads only on un-metered (Wi‑Fi) connections. */
    var autoUploadWifiOnly: Boolean
        get() = prefs.getBoolean(KEY_AUTO_UPLOAD_WIFI_ONLY, true)
        set(value) = prefs.edit().putBoolean(KEY_AUTO_UPLOAD_WIFI_ONLY, value).apply()

    /** Keys (MediaItem.uniqueKey) of items already auto-uploaded, so they are not uploaded again. */
    var autoUploadedKeys: Set<String>
        get() = prefs.getStringSet(KEY_AUTO_UPLOADED_KEYS, emptySet()) ?: emptySet()
        set(value) = prefs.edit().putStringSet(KEY_AUTO_UPLOADED_KEYS, value).apply()

    /** Adds [keys] to the persisted set of already-auto-uploaded items. */
    fun addAutoUploadedKeys(keys: Collection<String>) {
        if (keys.isEmpty()) return
        autoUploadedKeys = autoUploadedKeys + keys
    }

    companion object {
        private const val PREFS_NAME = "gallery_transfer_prefs"
        private const val KEY_VIEW_TYPE = "view_type"
        private const val KEY_FOLDER_SORT = "folder_sort"
        private const val KEY_MEDIA_SORT = "media_sort"
        private const val KEY_DROPBOX_DEST = "dropbox_dest_path"
        private const val KEY_OVERWRITE_CONFLICT = "overwrite_on_conflict"
        private const val KEY_DELETE_AFTER_UPLOAD = "delete_after_upload"
        private const val KEY_KEEP_FOLDER_STRUCTURE = "keep_folder_structure"
        private const val KEY_FILTER_TYPE = "filter_type"
        private const val KEY_FILTER_SORT = "filter_sort"
        private const val KEY_FILTER_SIZE = "filter_size"
        private const val KEY_AUTO_UPLOAD_ENABLED = "auto_upload_enabled"
        private const val KEY_AUTO_UPLOAD_DAYS = "auto_upload_days"
        private const val KEY_AUTO_UPLOAD_FREQ = "auto_upload_frequency"
        private const val KEY_AUTO_UPLOAD_WIFI_ONLY = "auto_upload_wifi_only"
        private const val KEY_AUTO_UPLOADED_KEYS = "auto_uploaded_keys"
    }
}
