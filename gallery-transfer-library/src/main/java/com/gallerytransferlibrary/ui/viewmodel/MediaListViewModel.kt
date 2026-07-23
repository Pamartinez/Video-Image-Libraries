package com.gallerytransferlibrary.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.data.model.FolderItem
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.ViewType
import com.gallerytransferlibrary.data.model.FilterSortOption
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.model.MediaSortOption
import com.gallerytransferlibrary.data.model.SizeFilter
import com.gallerytransferlibrary.data.preferences.AppPreferences
import com.gallerytransferlibrary.data.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MediaListUiState(
    val folders: List<FolderItem> = emptyList(),
    val currentBucketId: Int? = null,
    val currentBucketName: String = "",
    val media: List<MediaItem> = emptyList(),
    val isLoading: Boolean = false,
    val viewType: ViewType = ViewType.GRID_LARGE,
    val folderSort: FolderSortOption = FolderSortOption.NAME_A_TO_Z,
    val mediaSort: MediaSortOption = MediaSortOption.DATE_NEWEST,
    // Selection
    val selectionMode: Boolean = false,
    val selectedFolderIds: Set<Int> = emptySet(),
    val selectedMediaKeys: Set<String> = emptySet(),
    // Dialogs
    val showSortDialog: Boolean = false,
    val showViewAsDialog: Boolean = false,
    // Filter (flat item list)
    val filterActive: Boolean = false,
    val filterType: FilterType = FilterType.ALL,
    val filterSort: FilterSortOption = FilterSortOption.DATE_NEWEST,
    val filterSize: SizeFilter = SizeFilter.ALL,
    val filterNameQuery: String = "",
    val filterDateStart: Long? = null,
    val filterDateEnd: Long? = null,
    val allMedia: List<MediaItem> = emptyList(),
    val filterSelectionMode: Boolean = false,
    val filterSelectedKeys: Set<String> = emptySet(),
    val showFilterTypeDialog: Boolean = false,
    val showFilterSortDialog: Boolean = false,
    val showFilterSizeDialog: Boolean = false,
    val showFilterDateDialog: Boolean = false,
) {
    val inFolder: Boolean get() = currentBucketId != null
    val selectedCount: Int
        get() = if (inFolder) selectedMediaKeys.size else selectedFolderIds.size

    /** True when any non-default filter (type/size/name/date) is active. */
    val hasActiveFilters: Boolean
        get() = filterType != FilterType.ALL || filterSize != SizeFilter.ALL ||
            filterNameQuery.isNotBlank() || filterDateStart != null || filterDateEnd != null

    /** The flat list after applying the type/size/name/date filters and the chosen sort. */
    val filteredSortedMedia: List<MediaItem>
        get() {
            val query = filterNameQuery.trim().lowercase()
            // dateModified is epoch seconds; convert the (millis) bound to seconds for comparison.
            val endSec = filterDateEnd?.let { it / 1000 }
            val filtered = allMedia.filter { item ->
                val typeOk = when (filterType) {
                    FilterType.ALL -> true
                    FilterType.IMAGES -> !item.isVideo
                    FilterType.VIDEOS -> item.isVideo
                }
                val sizeOk = filterSize.matches(item.size)
                val nameOk = query.isEmpty() || item.displayName.lowercase().contains(query)
                // "Older than": keep items modified strictly before the start of the selected day.
                val dateOk = endSec == null || item.dateModified < endSec
                typeOk && sizeOk && nameOk && dateOk
            }
            return when (filterSort) {
                FilterSortOption.DATE_NEWEST -> filtered.sortedByDescending { it.dateModified }
                FilterSortOption.DATE_OLDEST -> filtered.sortedBy { it.dateModified }
                FilterSortOption.NAME_A_TO_Z -> filtered.sortedBy { it.displayName.lowercase() }
                FilterSortOption.NAME_Z_TO_A -> filtered.sortedByDescending { it.displayName.lowercase() }
                FilterSortOption.SIZE_LARGEST -> filtered.sortedByDescending { it.size }
                FilterSortOption.SIZE_SMALLEST -> filtered.sortedBy { it.size }
            }
        }
}

class MediaListViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = MediaRepository(app)
    private val prefs = AppPreferences(app)

    private val _uiState = MutableStateFlow(
        MediaListUiState(
            viewType = prefs.viewType,
            folderSort = prefs.folderSort,
            mediaSort = prefs.mediaSort,
            filterType = prefs.filterType,
            filterSort = prefs.filterSort,
            filterSize = prefs.filterSize
        )
    )
    val uiState: StateFlow<MediaListUiState> = _uiState.asStateFlow()

    init {
        loadFolders()
    }

    // ── Loading ─────────────────────────────────────────────────────────

    fun loadFolders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val folders = repository.getFolders(_uiState.value.folderSort)
            _uiState.update { it.copy(folders = folders, isLoading = false) }
        }
    }

    /** Reloads the current view (the open folder's media, or the folder grid) — e.g. after items
     *  are trashed following a delete-after-upload. */
    fun refreshCurrent() {
        viewModelScope.launch {
            val s = _uiState.value
            val bucketId = s.currentBucketId
            if (bucketId != null) {
                val media = repository.getMedia(bucketId, s.mediaSort)
                _uiState.update { it.copy(media = media) }
            }
            val folders = repository.getFolders(s.folderSort)
            _uiState.update { it.copy(folders = folders) }
            // The Filter view renders allMedia, which openFilter() loads separately — refresh it too
            // so trashed items disappear from the filtered grid without leaving/reopening Filter.
            if (s.filterActive || s.allMedia.isNotEmpty()) {
                val all = repository.getAllMedia()
                _uiState.update { it.copy(allMedia = all) }
            }
            // Drop selection keys for items that no longer exist so counts/highlights stay correct.
            // Filters themselves are unaffected (filteredSortedMedia is derived from state).
            _uiState.update { cur ->
                val mediaKeys = cur.media.mapTo(HashSet()) { it.uniqueKey }
                val filterKeys = cur.filteredSortedMedia.mapTo(HashSet()) { it.uniqueKey }
                val prunedMedia = cur.selectedMediaKeys.intersect(mediaKeys)
                val prunedFilter = cur.filterSelectedKeys.intersect(filterKeys)
                cur.copy(
                    selectedMediaKeys = prunedMedia,
                    selectionMode = cur.selectionMode && (prunedMedia.isNotEmpty() || cur.selectedFolderIds.isNotEmpty()),
                    filterSelectedKeys = prunedFilter,
                    filterSelectionMode = cur.filterSelectionMode && prunedFilter.isNotEmpty()
                )
            }
        }
    }

    fun openFolder(folder: FolderItem) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    currentBucketId = folder.bucketId,
                    currentBucketName = folder.name,
                    selectionMode = false,
                    selectedMediaKeys = emptySet()
                )
            }
            val media = repository.getMedia(folder.bucketId, _uiState.value.mediaSort)
            _uiState.update { it.copy(media = media, isLoading = false) }
        }
    }

    /** @return true if a navigation/selection state was consumed (caller should NOT exit app). */
    fun onBack(): Boolean {
        val s = _uiState.value
        if (s.selectionMode) { exitSelection(); return true }
        if (s.inFolder) {
            _uiState.update {
                it.copy(currentBucketId = null, currentBucketName = "", media = emptyList())
            }
            return true
        }
        return false
    }

    // ── View type & sorting ─────────────────────────────────────────────

    fun setViewType(viewType: ViewType) {
        prefs.viewType = viewType
        _uiState.update { it.copy(viewType = viewType, showViewAsDialog = false) }
    }

    fun setFolderSort(sort: FolderSortOption) {
        prefs.folderSort = sort
        _uiState.update { it.copy(folderSort = sort, showSortDialog = false) }
        loadFolders()
    }

    fun setMediaSort(sort: MediaSortOption) {
        prefs.mediaSort = sort
        _uiState.update { it.copy(mediaSort = sort, showSortDialog = false) }
        _uiState.value.currentBucketId?.let { bucketId ->
            viewModelScope.launch {
                val media = repository.getMedia(bucketId, sort)
                _uiState.update { it.copy(media = media) }
            }
        }
    }

    fun showSortDialog() = _uiState.update { it.copy(showSortDialog = true) }
    fun dismissSortDialog() = _uiState.update { it.copy(showSortDialog = false) }
    fun showViewAsDialog() = _uiState.update { it.copy(showViewAsDialog = true) }
    fun dismissViewAsDialog() = _uiState.update { it.copy(showViewAsDialog = false) }

    // ── Selection ───────────────────────────────────────────────────────

    fun toggleFolderSelection(bucketId: Int) {
        _uiState.update {
            val sel = it.selectedFolderIds.toMutableSet()
            if (!sel.add(bucketId)) sel.remove(bucketId)
            it.copy(selectedFolderIds = sel, selectionMode = sel.isNotEmpty())
        }
    }

    fun toggleMediaSelection(key: String) {
        _uiState.update {
            val sel = it.selectedMediaKeys.toMutableSet()
            if (!sel.add(key)) sel.remove(key)
            it.copy(selectedMediaKeys = sel, selectionMode = sel.isNotEmpty())
        }
    }

    fun startSelectionWithFolder(bucketId: Int) {
        _uiState.update { it.copy(selectionMode = true, selectedFolderIds = setOf(bucketId)) }
    }

    fun startSelectionWithMedia(key: String) {
        _uiState.update { it.copy(selectionMode = true, selectedMediaKeys = setOf(key)) }
    }

    fun selectAll() {
        _uiState.update {
            if (it.inFolder) it.copy(selectedMediaKeys = it.media.map { m -> m.uniqueKey }.toSet())
            else it.copy(selectedFolderIds = it.folders.map { f -> f.bucketId }.toSet())
        }
    }

    fun exitSelection() {
        _uiState.update {
            it.copy(selectionMode = false, selectedFolderIds = emptySet(), selectedMediaKeys = emptySet())
        }
    }

    // ── Filter (flat item list) ─────────────────────────────────────────

    fun openFilter() {
        _uiState.update { it.copy(filterActive = true) }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val all = repository.getAllMedia()
            _uiState.update { it.copy(allMedia = all, isLoading = false) }
        }
    }

    fun closeFilter() {
        _uiState.update {
            it.copy(
                filterActive = false,
                filterSelectionMode = false,
                filterSelectedKeys = emptySet(),
                showFilterTypeDialog = false,
                showFilterSortDialog = false,
                showFilterSizeDialog = false,
                showFilterDateDialog = false,
                // Name + date range are transient — reset them when leaving the Filter view.
                filterNameQuery = "",
                filterDateStart = null,
                filterDateEnd = null
            )
        }
    }

    fun setFilterType(type: FilterType) {
        prefs.filterType = type
        _uiState.update { it.copy(filterType = type, showFilterTypeDialog = false) }
    }

    fun setFilterSort(sort: FilterSortOption) {
        prefs.filterSort = sort
        _uiState.update { it.copy(filterSort = sort, showFilterSortDialog = false) }
    }

    fun setFilterSize(size: SizeFilter) {
        prefs.filterSize = size
        _uiState.update { it.copy(filterSize = size, showFilterSizeDialog = false) }
    }

    fun setFilterNameQuery(query: String) {
        _uiState.update { it.copy(filterNameQuery = query) }
    }

    fun setFilterOlderThan(dateMillis: Long?) {
        _uiState.update { it.copy(filterDateStart = null, filterDateEnd = dateMillis, showFilterDateDialog = false) }
    }

    fun clearFilterDate() {
        _uiState.update { it.copy(filterDateStart = null, filterDateEnd = null, showFilterDateDialog = false) }
    }

    fun clearAllFilters() {
        prefs.filterType = FilterType.ALL
        prefs.filterSize = SizeFilter.ALL
        _uiState.update {
            it.copy(
                filterType = FilterType.ALL,
                filterSize = SizeFilter.ALL,
                filterNameQuery = "",
                filterDateStart = null,
                filterDateEnd = null
            )
        }
    }

    fun showFilterTypeDialog() = _uiState.update { it.copy(showFilterTypeDialog = true) }
    fun dismissFilterTypeDialog() = _uiState.update { it.copy(showFilterTypeDialog = false) }
    fun showFilterSortDialog() = _uiState.update { it.copy(showFilterSortDialog = true) }
    fun dismissFilterSortDialog() = _uiState.update { it.copy(showFilterSortDialog = false) }
    fun showFilterSizeDialog() = _uiState.update { it.copy(showFilterSizeDialog = true) }
    fun dismissFilterSizeDialog() = _uiState.update { it.copy(showFilterSizeDialog = false) }
    fun showFilterDateDialog() = _uiState.update { it.copy(showFilterDateDialog = true) }
    fun dismissFilterDateDialog() = _uiState.update { it.copy(showFilterDateDialog = false) }

    fun toggleFilterSelection(key: String) {
        _uiState.update {
            val sel = it.filterSelectedKeys.toMutableSet()
            if (!sel.add(key)) sel.remove(key)
            it.copy(filterSelectedKeys = sel, filterSelectionMode = sel.isNotEmpty())
        }
    }

    fun startFilterSelectionWith(key: String) {
        _uiState.update { it.copy(filterSelectionMode = true, filterSelectedKeys = setOf(key)) }
    }

    fun selectAllFilter() {
        _uiState.update {
            it.copy(filterSelectedKeys = it.filteredSortedMedia.map { m -> m.uniqueKey }.toSet())
        }
    }

    fun exitFilterSelection() {
        _uiState.update { it.copy(filterSelectionMode = false, filterSelectedKeys = emptySet()) }
    }

    // ── Helpers for actions ─────────────────────────────────────────────

    /** The single selected media item, or null if not exactly one. Used for "Open location". */
    fun singleSelectedMedia(): MediaItem? {
        val s = _uiState.value
        if (!s.inFolder || s.selectedMediaKeys.size != 1) return null
        return s.media.firstOrNull { it.uniqueKey == s.selectedMediaKeys.first() }
    }

    /** Resolves the current selection into the concrete list of media items to upload. */
    suspend fun resolveSelectionForUpload(): List<MediaItem> {
        val s = _uiState.value
        return when {
            s.filterActive -> s.filteredSortedMedia.filter { it.uniqueKey in s.filterSelectedKeys }
            s.inFolder -> s.media.filter { it.uniqueKey in s.selectedMediaKeys }
            else -> repository.getMediaForBuckets(s.selectedFolderIds)
        }
    }

    /** All media items modified more than [days] days ago (for the manual "Back up old items" action). */
    suspend fun getMediaOlderThan(days: Int): List<MediaItem> {
        val cutoffSeconds = (System.currentTimeMillis() / 1000L) - days.coerceAtLeast(1) * 86_400L
        return repository.getAllMedia().filter { it.dateModified < cutoffSeconds }
    }
}
