package com.gallerytransferlibrary.data.preferences

import android.content.Context
import com.common.data.model.FolderSortOption
import com.common.data.model.ViewType
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
     * When true, users can long-press media items in Custom sort mode to drag-and-drop them into a
     * new order (mirrors the image-library / video-library "Drag to reorder media" setting).
     * Default true.
     */
    var allowMediaReordering: Boolean
        get() = prefs.getBoolean(KEY_ALLOW_MEDIA_REORDERING, true)
        set(value) = prefs.edit().putBoolean(KEY_ALLOW_MEDIA_REORDERING, value).apply()

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

    // ── Bulk upload run ledger ──────────────────────────────────────────
    //
    // Deliberately separate from [autoUploadedKeys]: that set is a permanent, global suppression
    // list, so writing bulk keys into it would silently exclude those items from auto-upload
    // forever AND make a deliberate re-run of the same bulk upload transfer nothing at all.
    //
    // Each ledger is keyed by the worker's WorkManager UUID, which survives retries and process
    // death but is regenerated on every new enqueue. That gives the semantics we want for free: a
    // retry resumes where it left off, a fresh tap starts clean.
    //
    // Stored per run, not in one shared slot: a run's ledger is seeded at ENQUEUE time with the
    // items the user unticked, so a second upload queued while the first is still pending would
    // otherwise overwrite the first one's exclusions and upload items the user had explicitly
    // deselected. [MAX_BULK_LEDGER_RUNS] bounds the growth; abandoned runs age out.

    /**
     * uniqueKeys the run [runId] must skip — because it already uploaded them, or because the user
     * unticked them in the preview before starting (see [seedBulkLedger]).
     */
    fun bulkLedgerKeys(runId: String): Set<String> =
        prefs.getStringSet(KEY_BULK_LEDGER_PREFIX + runId, emptySet()) ?: emptySet()

    /**
     * Pre-loads [runId]'s skip list with the items the user unticked in the preview, before the work
     * is enqueued.
     *
     * This is how a UI-side selection reaches a worker that otherwise only knows criteria and
     * re-queries MediaStore itself: the worker's existing "skip what's in the ledger" filter does
     * the rest, so there is no second exclusion mechanism to keep in sync. Survives process death
     * and retries for free, like the rest of the ledger. Also avoids WorkManager input data, whose
     * 10 KB cap a long exclusion list could exceed.
     */
    fun seedBulkLedger(runId: String, keys: Set<String>) {
        registerBulkLedgerRun(runId)
        prefs.edit().putStringSet(KEY_BULK_LEDGER_PREFIX + runId, keys).apply()
    }

    /** Claims the ledger for [runId] and returns the keys it must skip. */
    fun beginBulkLedger(runId: String): Set<String> {
        registerBulkLedgerRun(runId)
        return bulkLedgerKeys(runId)
    }

    /** Records [keys] as uploaded by [runId]. */
    fun addBulkLedgerKeys(runId: String, keys: Collection<String>) {
        if (keys.isEmpty()) return
        prefs.edit()
            .putStringSet(KEY_BULK_LEDGER_PREFIX + runId, bulkLedgerKeys(runId) + keys)
            .apply()
    }

    /** Releases [runId]'s ledger once it finishes. */
    fun clearBulkLedger(runId: String) {
        val remaining = bulkLedgerRunIds() - runId
        prefs.edit()
            .remove(KEY_BULK_LEDGER_PREFIX + runId)
            .putString(KEY_BULK_LEDGER_RUNS, remaining.joinToString(","))
            .apply()
    }

    private fun bulkLedgerRunIds(): List<String> =
        prefs.getString(KEY_BULK_LEDGER_RUNS, "")
            ?.split(',')
            ?.filter { it.isNotBlank() }
            ?: emptyList()

    /** Tracks [runId] as active (oldest first) and evicts ledgers beyond the cap. */
    private fun registerBulkLedgerRun(runId: String) {
        val known = bulkLedgerRunIds()
        if (known.lastOrNull() == runId) return
        val updated = (known - runId) + runId
        val evicted = updated.dropLast(MAX_BULK_LEDGER_RUNS)
        val editor = prefs.edit()
        evicted.forEach { editor.remove(KEY_BULK_LEDGER_PREFIX + it) }
        editor.putString(
            KEY_BULK_LEDGER_RUNS,
            updated.takeLast(MAX_BULK_LEDGER_RUNS).joinToString(",")
        )
        editor.apply()
    }

    // ── Deferred trashing ───────────────────────────────────────────────

    /**
     * URIs uploaded with delete-after-upload that still need to be trashed, for when the worker
     * could not do it itself. [MediaTrashHelper.trashSilently] requires All-files access; without it
     * the only route is a system consent dialog, which needs a UI. The UI drains this on start.
     */
    var pendingTrashUris: Set<String>
        get() = prefs.getStringSet(KEY_PENDING_TRASH_URIS, emptySet()) ?: emptySet()
        set(value) = prefs.edit().putStringSet(KEY_PENDING_TRASH_URIS, value).apply()

    fun addPendingTrashUris(uris: Collection<String>) {
        if (uris.isEmpty()) return
        pendingTrashUris = pendingTrashUris + uris
    }

    // ── Custom sort order (auto-saved on drag-to-reorder) ───────────────

    /** Root folder custom order as a list of bucketIds. Empty = not set yet. */
    var customFolderOrder: List<Int>
        get() = prefs.getString(KEY_CUSTOM_FOLDER_ORDER, "")
            ?.split(',')
            ?.mapNotNull { it.trim().toIntOrNull() }
            ?: emptyList()
        set(value) = prefs.edit()
            .putString(KEY_CUSTOM_FOLDER_ORDER, value.joinToString(",")).apply()

    /** Per-folder media custom order: bucketId -> ordered list of media ids. */
    fun getFolderMediaCustomOrder(bucketId: Int): List<Long> {
        val raw = prefs.getString(KEY_FOLDER_MEDIA_ORDERS, "") ?: ""
        if (raw.isEmpty()) return emptyList()
        for (entry in raw.split(',')) {
            val colon = entry.indexOf(':')
            if (colon <= 0) continue
            if (entry.substring(0, colon).toIntOrNull() != bucketId) continue
            val idsPart = entry.substring(colon + 1)
            if (idsPart.isEmpty()) return emptyList()
            return idsPart.split(';').mapNotNull { it.toLongOrNull() }
        }
        return emptyList()
    }

    /** Saves the media order for [bucketId]; keeps at most [MAX_MEDIA_ORDER_FOLDERS] folders. */
    fun saveFolderMediaCustomOrder(bucketId: Int, ids: List<Long>) {
        val raw = prefs.getString(KEY_FOLDER_MEDIA_ORDERS, "") ?: ""
        val entries = LinkedHashMap<Int, String>()
        if (raw.isNotEmpty()) {
            for (entry in raw.split(',')) {
                val colon = entry.indexOf(':')
                if (colon <= 0) continue
                val bId = entry.substring(0, colon).toIntOrNull() ?: continue
                entries[bId] = entry.substring(colon + 1)
            }
        }
        entries.remove(bucketId)
        entries[bucketId] = ids.joinToString(";")
        val trimmed = if (entries.size > MAX_MEDIA_ORDER_FOLDERS) {
            entries.entries.toList()
                .takeLast(MAX_MEDIA_ORDER_FOLDERS)
                .associate { it.key to it.value }
        } else entries
        val serialized = trimmed.entries.joinToString(",") { "${it.key}:${it.value}" }
        prefs.edit().putString(KEY_FOLDER_MEDIA_ORDERS, serialized).apply()
    }

    companion object {
        private const val PREFS_NAME = "gallery_transfer_prefs"
        private const val KEY_VIEW_TYPE = "view_type"
        private const val KEY_FOLDER_SORT = "folder_sort"
        private const val KEY_MEDIA_SORT = "media_sort"
        private const val KEY_DROPBOX_DEST = "dropbox_dest_path"
        private const val KEY_OVERWRITE_CONFLICT = "overwrite_on_conflict"
        private const val KEY_DELETE_AFTER_UPLOAD = "delete_after_upload"
        private const val KEY_ALLOW_MEDIA_REORDERING = "allow_media_reordering"
        private const val KEY_KEEP_FOLDER_STRUCTURE = "keep_folder_structure"
        private const val KEY_FILTER_TYPE = "filter_type"
        private const val KEY_FILTER_SORT = "filter_sort"
        private const val KEY_FILTER_SIZE = "filter_size"
        private const val KEY_AUTO_UPLOAD_ENABLED = "auto_upload_enabled"
        private const val KEY_AUTO_UPLOAD_DAYS = "auto_upload_days"
        private const val KEY_AUTO_UPLOAD_FREQ = "auto_upload_frequency"
        private const val KEY_AUTO_UPLOAD_WIFI_ONLY = "auto_upload_wifi_only"
        private const val KEY_AUTO_UPLOADED_KEYS = "auto_uploaded_keys"
        private const val KEY_BULK_LEDGER_RUNS = "bulk_ledger_runs"
        private const val KEY_BULK_LEDGER_PREFIX = "bulk_ledger_keys_"
        /** How many bulk-upload ledgers to retain; older ones are evicted. */
        private const val MAX_BULK_LEDGER_RUNS = 4
        private const val KEY_PENDING_TRASH_URIS = "pending_trash_uris"
        private const val KEY_CUSTOM_FOLDER_ORDER = "custom_folder_order"
        private const val KEY_FOLDER_MEDIA_ORDERS = "folder_media_custom_orders"
        private const val MAX_MEDIA_ORDER_FOLDERS = 500
    }
}
