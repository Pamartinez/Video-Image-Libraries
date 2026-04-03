package com.imagelibrary.ui.viewmodel

import android.app.Application
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.imagelibrary.data.model.ImageSortOption
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.repository.GroupRepository
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.data.preferences.AppPreferences
import com.imagelibrary.data.repository.ImageRepository
import java.util.concurrent.atomic.AtomicBoolean

data class ImageListUiState(
    val images: List<ImageItem> = emptyList(),
    val folders: List<FolderItem> = emptyList(),
    val isLoading: Boolean = true,
    val viewType: ViewType = ViewType.GRID_LARGE,
    val imageSortOption: ImageSortOption = ImageSortOption.CUSTOM_ORDER,
    val groupSortOption: SortOption = SortOption.CUSTOM_ORDER,
    val folderViewType: ViewType = ViewType.GRID_LARGE,
    val sortOption: SortOption = SortOption.CUSTOM_ORDER,
    val isSelectionMode: Boolean = false,
    val selectedImageIds: Set<Long> = emptySet(),
    val selectedFolderIds: Set<Int> = emptySet(),
    val selectedGroupIds: Set<Long> = emptySet(),
    val searchQuery: String = "",
    val isSearchActive: Boolean = false,
    val searchResults: List<ImageItem> = emptyList(),
    val currentFolderBucketId: Int? = null,
    val currentFolderName: String = "",
    val folderImages: List<ImageItem> = emptyList(),
    val showSortDialog: Boolean = false,
    val showViewAsDialog: Boolean = false,
    val showRenameDialog: Boolean = false,
    val renameTarget: ImageItem? = null,
    val showDeleteDialog: Boolean = false,
    val showCreateFolderDialog: Boolean = false,
    val showMoveFolderPicker: Boolean = false,
    val showCopyFolderPicker: Boolean = false,
    val showAbout: Boolean = false,
    val showSettings: Boolean = false,
    val carouselShowBarsOnOpen: Boolean = false,
    val showDetailsDialog: Boolean = false,
    val detailsTarget: ImageItem? = null,
    val carouselIndex: Int = -1,
    val error: String? = null,

    // ── Group state ──
    val rootGroups: List<GroupItem> = emptyList(),
    val ungroupedFolders: List<FolderItem> = emptyList(),
    val currentGroupId: Long? = null,
    val currentGroupName: String = "",
    val currentGroupFolders: List<FolderItem> = emptyList(),
    val currentGroupSubGroups: List<GroupItem> = emptyList(),
    // Ordered interleaved list of items inside the currently-open group (mirrors orderedMixedItems for root)
    val currentGroupOrderedMixedItems: List<Any> = emptyList(),
    val groupStack: List<Pair<Long, String>> = emptyList(),
    val isGroupCreationMode: Boolean = false,
    val groupCreationSelectedFolderIds: Set<Int> = emptySet(),
    val groupCreationSelectedGroupIds: Set<Long> = emptySet(),
    val showGroupNameDialog: Boolean = false,
    val showRenameGroupDialog: Boolean = false,
    val showDestroyGroupDialog: Boolean = false,
    val showAddFolderToGroup: Boolean = false,
    val allGroups: List<GroupItem> = emptyList(),
    val groupNameDialogForBottomBar: Boolean = false,
    val groupNameDialogForCreation: Boolean = false,
    val pendingGroupCreationName: String = "",

    // ── Unified ordered mixed display list (groups + ungrouped folders interleaved) ──
    val orderedMixedItems: List<Any> = emptyList(),

    /** Per-group custom sort orders, forwarded to FolderPickerScreen so the picker
     *  respects the same drag order as the group detail screen. */
    val allGroupCustomOrders: Map<Long, List<String>> = emptyMap(),

    // ── Move-to-group picker ──
    val showMoveToGroupPicker: Boolean = false,
    val moveToGroupFolderIds: Set<Int> = emptySet(),
    val moveToGroupGroupIds: Set<Long> = emptySet(),

    val autoBackupEnabled: Boolean = false,

    // ── Create Album flow ──
    val showCreateAlbumDialog: Boolean = false,
    val showCreateAlbumPicker: Boolean = false,
    val showCreateAlbumCopyMoveDialog: Boolean = false,
    val pendingAlbumName: String = "",
    val albumCreationSelectedImageIds: Set<Long> = emptySet(),
    val albumCreationBrowsedImages: List<ImageItem> = emptyList(),
    val albumCreationCurrentBucketId: Int? = null,
    val albumCreationCurrentBucketName: String = "",
    val dcimFolderNames: Set<String> = emptySet(),
    val independentSortEnabled: Boolean = true,

    /** When true, groups are pinned to the top of sorted lists; ungrouped albums follow. */
    val groupsAlwaysOnTop: Boolean = false,

    /** Sort option for the currently-open group (independent from the root sort). */
    val currentGroupSortOption: SortOption = SortOption.CUSTOM_ORDER,
    val showHideFolders: Boolean = false,
    val allFoldersForHide: List<FolderItem> = emptyList(),
    val hiddenFolderPaths: Set<String> = emptySet(),
    /** Groups shown at the root of the hide screen. */
    val rootGroupsForHide: List<GroupItem> = emptyList(),
    /** Ungrouped folders shown at the root of the hide screen. */
    val ungroupedFoldersForHide: List<FolderItem> = emptyList(),
    /** Non-null when the user has drilled into a group inside the hide screen. */
    val hideScreenGroupId: Long? = null,
    val hideScreenGroupName: String = "",
    val hideScreenGroupFolders: List<FolderItem> = emptyList(),
    /** Sub-groups inside the currently-open hide-screen group (mirrors root structure). */
    val hideScreenGroupSubGroups: List<GroupItem> = emptyList(),
    /** True when hide screen was opened from inside a group — back exits entirely. */
    val hideScreenStartedInsideGroup: Boolean = false
)

data class CopyMoveProgress(
    val isActive: Boolean = false,
    val title: String = "",
    val current: Int = 0,
    val total: Int = 0
)

data class FileConflict(
    val fileName: String,
    val deferred: CompletableDeferred<ConflictResolution>
)

class ImageListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ImageRepository(application)
    private val groupRepository = GroupRepository(application)
    val preferences = AppPreferences(application)
    private val _uiState = MutableStateFlow(
        ImageListUiState(
            independentSortEnabled = preferences.independentSortEnabled
        )
    )
    val uiState: StateFlow<ImageListUiState> = _uiState.asStateFlow()

    fun updateIndependentSortEnabled(value: Boolean) {
        preferences.independentSortEnabled = value
        _uiState.update { it.copy(independentSortEnabled = value) }
        scheduleAutoBackup()
    }

    fun updateGroupsAlwaysOnTop(value: Boolean) {
        preferences.groupsAlwaysOnTop = value
        _uiState.update { it.copy(groupsAlwaysOnTop = value) }
        silentRefresh()
        scheduleAutoBackup()
    }

    // ── Hide Folders ───────────────────────────────────────────────────────

    fun showHideFoldersScreen() {
        val s = _uiState.value
        val hiddenMeta = preferences.getAllHiddenFolderMeta()
        val visiblePaths = s.folders.map { it.path }.toSet()
        // Stubs for hidden folders that are no longer in MediaStore
        val hiddenStubs = hiddenMeta
            .filter { (path, _) -> path !in visiblePaths }
            .map { (path, triple) ->
                FolderItem(
                    bucketId  = triple.second, name = triple.first,
                    itemCount = triple.third,  path = path
                )
            }
        val allFolders = (s.folders + hiddenStubs)
        viewModelScope.launch {
            // getGroupedBucketIds() returns ALL bucket IDs across every group at every
            // nesting level — root groups AND sub-groups — so sub-group albums are
            // correctly excluded from the "ungrouped" list.
            val groupedBucketIds = groupRepository.getGroupedBucketIds()
            val ungrouped = allFolders.filter { it.bucketId !in groupedBucketIds }
                .sortedBy { it.name.lowercase() }
            _uiState.update {
                it.copy(
                    showHideFolders         = true,
                    allFoldersForHide       = allFolders,
                    rootGroupsForHide       = s.rootGroups.sortedBy { g -> g.name.lowercase() },
                    ungroupedFoldersForHide = ungrouped,
                    hiddenFolderPaths       = preferences.hiddenFolderPaths,
                    hideScreenGroupId       = null,
                    hideScreenGroupName     = "",
                    hideScreenGroupFolders  = emptyList()
                )
            }
        }
    }

    fun dismissHideFoldersScreen() {
        _uiState.update {
            it.copy(
                showHideFolders              = false,
                hideScreenGroupId            = null,
                hideScreenGroupName          = "",
                hideScreenGroupFolders       = emptyList(),
                hideScreenGroupSubGroups     = emptyList(),
                hideScreenStartedInsideGroup = false
            )
        }
    }

    /**
     * Opens the hide screen pre-scoped to the currently-open group.
     * Shows only that group's sub-groups + direct member albums — no root-level
     * groups or ungrouped albums are shown.
     */
    fun showHideFoldersScreenForCurrentGroup() {
        val s         = _uiState.value
        val groupId   = s.currentGroupId   ?: return
        val groupName = s.currentGroupName
        val hiddenMeta   = preferences.getAllHiddenFolderMeta()
        val visiblePaths = s.folders.map { it.path }.toSet()
        val hiddenStubs  = hiddenMeta
            .filter { (path, _) -> path !in visiblePaths }
            .map { (path, triple) ->
                FolderItem(bucketId = triple.second, name = triple.first,
                           itemCount = triple.third, path = path)
            }
        val allFolders = s.folders + hiddenStubs
        viewModelScope.launch {
            val memberBucketIds = groupRepository.getFolderBucketIdsForGroup(groupId).toSet()
            val subGroups       = groupRepository.getChildGroups(groupId)
            val directFolders   = allFolders
                .filter { it.bucketId in memberBucketIds }
                .sortedBy { it.name.lowercase() }
            _uiState.update {
                it.copy(
                    showHideFolders              = true,
                    allFoldersForHide            = allFolders,
                    hiddenFolderPaths            = preferences.hiddenFolderPaths,
                    // Root level intentionally empty — caller is already inside a group
                    rootGroupsForHide            = emptyList(),
                    ungroupedFoldersForHide      = emptyList(),
                    hideScreenGroupId            = groupId,
                    hideScreenGroupName          = groupName,
                    hideScreenGroupFolders       = directFolders,
                    hideScreenGroupSubGroups     = subGroups,
                    hideScreenStartedInsideGroup = true
                )
            }
        }
    }

    fun openGroupInHideScreen(group: GroupItem) {
        viewModelScope.launch {
            val groupFolders = _uiState.value.allFoldersForHide
                .filter { it.bucketId in group.memberBucketIds }
                .sortedBy { it.name.lowercase() }
            val subGroups = groupRepository.getChildGroups(group.groupId)
            _uiState.update {
                it.copy(
                    hideScreenGroupId        = group.groupId,
                    hideScreenGroupName      = group.name,
                    hideScreenGroupFolders   = groupFolders,
                    hideScreenGroupSubGroups = subGroups
                )
            }
        }
    }

    fun closeGroupInHideScreen() {
        _uiState.update {
            it.copy(
                hideScreenGroupId        = null,
                hideScreenGroupName      = "",
                hideScreenGroupFolders   = emptyList(),
                hideScreenGroupSubGroups = emptyList()
            )
        }
    }

    fun toggleGroupHidden(group: GroupItem) {
        val allFolders = _uiState.value.allFoldersForHide
        val groupFolders = allFolders.filter { it.bucketId in group.memberBucketIds }
        val paths = groupFolders.map { it.path }.filter { it.isNotBlank() }
        if (paths.isEmpty()) return
        val currentHidden = _uiState.value.hiddenFolderPaths
        val allAlreadyHidden = paths.all { it in currentHidden }
        viewModelScope.launch {
            if (allAlreadyHidden) {
                paths.forEach { path -> repository.showFolder(path); preferences.removeHiddenFolderMeta(path) }
                val newPaths = currentHidden - paths.toSet()
                preferences.hiddenFolderPaths = newPaths
                _uiState.update { it.copy(hiddenFolderPaths = newPaths) }
            } else {
                paths.forEach { path ->
                    repository.hideFolder(path)
                    val f = groupFolders.find { it.path == path }
                    if (f != null) preferences.saveHiddenFolderMeta(path, f.name, f.bucketId, f.itemCount)
                }
                val newPaths = currentHidden + paths.toSet()
                preferences.hiddenFolderPaths = newPaths
                _uiState.update { it.copy(hiddenFolderPaths = newPaths) }
            }
            paths.forEach { repository.rescanFolder(it) }
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    fun toggleFolderHidden(folder: FolderItem) {
        val path = folder.path
        if (path.isBlank()) return
        val currentlyHidden = path in _uiState.value.hiddenFolderPaths
        viewModelScope.launch {
            if (currentlyHidden) {
                repository.showFolder(path)
                val newPaths = preferences.hiddenFolderPaths - path
                preferences.hiddenFolderPaths = newPaths
                preferences.removeHiddenFolderMeta(path)
                _uiState.update { s -> s.copy(hiddenFolderPaths = newPaths) }
            } else {
                repository.hideFolder(path)
                val newPaths = preferences.hiddenFolderPaths + path
                preferences.hiddenFolderPaths = newPaths
                preferences.saveHiddenFolderMeta(path, folder.name, folder.bucketId, folder.itemCount)
                _uiState.update { s -> s.copy(hiddenFolderPaths = newPaths) }
            }
            repository.rescanFolder(path)
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    /**
     * Set the sort option for the currently-open group.
     * Persists the choice per group so each group remembers its own sort independently.
     */
    fun setCurrentGroupSortOption(option: SortOption) {
        val groupId = _uiState.value.currentGroupId ?: return
        preferences.saveGroupSortOption(groupId, option)
        _uiState.update { it.copy(currentGroupSortOption = option) }
        refreshCurrentGroup()
        scheduleAutoBackup()
    }

    // Copy/Move progress
    private val _copyMoveProgress = MutableStateFlow(CopyMoveProgress())
    val copyMoveProgress: StateFlow<CopyMoveProgress> = _copyMoveProgress.asStateFlow()
    private var copyMoveJob: Job? = null
    @Volatile private var copyMoveCancelled = false

    // MediaStore auto-refresh
    private var mediaObserverJob: Job? = null
    /** True while the app itself is performing a MediaStore write — suppresses the
     *  ContentObserver so we don't do a redundant second refresh. */
    private val isInternalChange = AtomicBoolean(false)
    private val mediaObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            // Skip: we triggered this change ourselves and will call silentRefresh() explicitly
            if (isInternalChange.get()) return
            mediaObserverJob?.cancel()
            mediaObserverJob = viewModelScope.launch {
                delay(500L)
                silentRefresh()
                refreshFolderImages()
            }
        }
    }

    // File conflict resolution
    private val _fileConflict = MutableStateFlow<FileConflict?>(null)
    val fileConflict: StateFlow<FileConflict?> = _fileConflict.asStateFlow()

    // Auto-backup debounce
    private companion object {
        const val AUTO_BACKUP_DEBOUNCE_MS = 5_000L
    }
    private var autoBackupJob: Job? = null
    @Volatile private var isRestoringBackup = false

    init {
        _uiState.update {
            it.copy(
                viewType = preferences.viewType,
                folderViewType = preferences.folderViewType,
                sortOption = preferences.sortOption,
                imageSortOption = preferences.imageSortOption,
                carouselShowBarsOnOpen = preferences.carouselShowBarsOnOpen,
                groupSortOption = preferences.groupSortOption,
                autoBackupEnabled = preferences.autoBackupEnabled,
                independentSortEnabled = preferences.independentSortEnabled,
                groupsAlwaysOnTop = preferences.groupsAlwaysOnTop,
                hiddenFolderPaths = preferences.hiddenFolderPaths
            )
        }
        loadData()
        getApplication<Application>().contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, mediaObserver
        )
    }

    override fun onCleared() {
        // Fire an immediate async backup before the scope is cancelled
        if (preferences.autoBackupEnabled) {
            viewModelScope.launch(Dispatchers.IO) {
                com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
            }
        }
        autoBackupJob?.cancel()
        mediaObserverJob?.cancel()
        getApplication<Application>().contentResolver.unregisterContentObserver(mediaObserver)
        super.onCleared()
    }

    fun resolveConflict(resolution: ConflictResolution) {
        _fileConflict.value?.deferred?.complete(resolution)
        _fileConflict.value = null
    }

    fun loadData() {
        viewModelScope.launch {
            // Show spinner only on cold start (no data yet); background refreshes are silent
            val isColdStart = _uiState.value.folders.isEmpty() && _uiState.value.images.isEmpty()
            if (isColdStart) {
                _uiState.update { it.copy(isLoading = true) }
            }
            loadDataCore()
        }
    }

    /** Shared data-loading body. Updates state without touching isLoading. */
    private suspend fun loadDataCore() {
        val s = _uiState.value
        val images = repository.getImages(s.imageSortOption)
        val folders = repository.getFolders(s.sortOption)

        val rootGroups = groupRepository.getRootGroups()
        val groupedBucketIds = groupRepository.getGroupedBucketIds()
        val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
        val allGroups = groupRepository.getAllGroups()

        val orderedMixed = if (s.sortOption == SortOption.CUSTOM_ORDER) {
            applyCustomMixedOrder(rootGroups, ungroupedFolders)
        } else {
            sortMixedItems(rootGroups + ungroupedFolders, s.sortOption, s.groupsAlwaysOnTop)
        }

        _uiState.update {
            it.copy(
                images = images,
                folders = folders,
                rootGroups = rootGroups,
                ungroupedFolders = ungroupedFolders,
                orderedMixedItems = orderedMixed,
                allGroups = allGroups,
                allGroupCustomOrders = preferences.allCustomGroupItemsOrders(),
                isLoading = false
            )
        }
        // Allow ContentObserver to fire again after our explicit refresh is done
        isInternalChange.set(false)
    }

    /** Reload all data in the background without showing any loading indicator. */
    private fun silentRefresh() {
        viewModelScope.launch { loadDataCore() }
    }

    /** Reload folderImages in-place (no spinner, no list-clear flicker). */
    private fun refreshFolderImages() {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        viewModelScope.launch {
            val imgs = repository.getImages(_uiState.value.imageSortOption, bucketId = bucketId)
            _uiState.update { it.copy(folderImages = imgs) }
        }
    }

    /**
     * Build the unified display order of groups + ungrouped folders.
     *
     * - First run: groups first then folders (no saved order yet); saves this as the baseline.
     * - Subsequent runs: restore items in their saved positions, append new items at the end,
     *   and prune any items that no longer exist (deleted groups / folders).
     */
    private fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val savedOrder = preferences.customMixedOrder

        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (savedOrder.isEmpty()) {
            // First time — groups first then folders; persist as baseline
            val initial: List<Any> = groups + folders
            preferences.customMixedOrder = initial.map { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else -> ""
                }
            }.filter { it.isNotEmpty() }
            return initial
        }

        val savedSet   = savedOrder.toSet()
        // Items in their saved position (skip keys whose item was deleted)
        val ordered    = savedOrder.mapNotNull { key -> groupMap[key] ?: folderMap[key] }
        // New items not yet in the saved order — prepend at the beginning
        val newGroups  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
        val result: List<Any> = newGroups + newFolders + ordered

        // Persist the pruned + extended order so new items survive next launch
        preferences.customMixedOrder = result.map { item ->
            when (item) {
                is GroupItem  -> "g_${item.groupId}"
                is FolderItem -> "f_${item.bucketId}"
                else -> ""
            }
        }.filter { it.isNotEmpty() }

        return result
    }

    /**
     * Sort a combined list of [GroupItem]s and [FolderItem]s together according to
     * [option], so that group-albums are treated exactly like regular albums.
     *
     * When [groupsAlwaysOnTop] is true the list is split into two segments:
     *   1. All groups, sorted by [option] among themselves.
     *   2. All folders, sorted by [option] among themselves.
     *
     * Key mapping:
     *   name         → GroupItem.name          / FolderItem.name
     *   item count   → GroupItem.totalItemCount / FolderItem.itemCount
     */
    private fun sortMixedItems(
        items: List<Any>,
        option: SortOption,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        fun itemName(item: Any) = when (item) {
            is GroupItem  -> item.name
            is FolderItem -> item.name
            else          -> ""
        }
        fun itemCount(item: Any) = when (item) {
            is GroupItem  -> item.totalItemCount
            is FolderItem -> item.itemCount
            else          -> 0
        }
        fun sortList(list: List<Any>): List<Any> = when (option) {
            SortOption.NAME_A_TO_Z        -> list.sortedBy             { itemName(it).lowercase() }
            SortOption.NAME_Z_TO_A        -> list.sortedByDescending   { itemName(it).lowercase() }
            SortOption.ITEMS_MOST_FIRST   -> list.sortedByDescending   { itemCount(it) }
            SortOption.ITEMS_FEWEST_FIRST -> list.sortedBy             { itemCount(it) }
            SortOption.CUSTOM_ORDER       -> list // should not reach here
        }
        return if (groupsAlwaysOnTop) {
            val groups  = items.filterIsInstance<GroupItem>()
            val folders = items.filterIsInstance<FolderItem>()
            sortList(groups) + sortList(folders)
        } else {
            sortList(items)
        }
    }

    /**
     * Reorder an item in the unified mixed list (groups + ungrouped folders).
     * Operates directly on [orderedMixedItems] so groups can be placed at any
     * position — including between or after folders — without being forced back
     * into a "groups-first block" by filterIsInstance splitting.
     * Called in real-time during drag.
     */
    fun reorderMixedItem(fromIndex: Int, toIndex: Int) {
        _uiState.update { state ->
            val mixed = state.orderedMixedItems.toMutableList()

            if (fromIndex < 0 || toIndex < 0 || fromIndex >= mixed.size || toIndex >= mixed.size) {
                return@update state
            }

            val item = mixed.removeAt(fromIndex)
            mixed.add(toIndex, item)

            // Derive the split lists from the new unified order (for features that still
            // need separate group / folder lists, e.g. group-detail, selection counts).
            val newGroups  = mixed.filterIsInstance<GroupItem>()
            val newFolders = mixed.filterIsInstance<FolderItem>()

            state.copy(
                orderedMixedItems  = mixed,
                rootGroups         = newGroups,
                ungroupedFolders   = newFolders
            )
        }
    }

    /**
     * Persist the current display order to SharedPreferences.
     * Called when drag ends.
     */
    fun persistFolderOrder() {
        val state = _uiState.value
        // Persist the unified interleaved order (used on next loadData)
        preferences.customMixedOrder = state.orderedMixedItems.map { item ->
            when (item) {
                is GroupItem  -> "g_${item.groupId}"
                is FolderItem -> "f_${item.bucketId}"
                else -> ""
            }
        }.filter { it.isNotEmpty() }
        // Keep the individual orders in sync for backup compatibility
        preferences.customAlbumOrder = state.ungroupedFolders.map { it.bucketId }
        preferences.customGroupOrder  = state.rootGroups.map     { it.groupId  }
        scheduleAutoBackup()
    }

    /**
     * Reorder an item inside the currently-open group (called in real-time during drag).
     * Mirrors reorderMixedItem but operates on currentGroupOrderedMixedItems.
     */
    fun reorderGroupItem(fromIndex: Int, toIndex: Int) {
        _uiState.update { state ->
            val mixed = state.currentGroupOrderedMixedItems.toMutableList()
            if (fromIndex < 0 || toIndex < 0 || fromIndex >= mixed.size || toIndex >= mixed.size) {
                return@update state
            }
            val item = mixed.removeAt(fromIndex)
            mixed.add(toIndex, item)
            state.copy(
                currentGroupOrderedMixedItems = mixed,
                currentGroupFolders           = mixed.filterIsInstance<FolderItem>(),
                currentGroupSubGroups         = mixed.filterIsInstance<GroupItem>()
            )
        }
    }

    /**
     * Persist the custom order for the currently-open group to SharedPreferences.
     * Called when drag ends.
     */
    fun persistGroupOrder() {
        val state   = _uiState.value
        val groupId = state.currentGroupId ?: return
        preferences.setCustomGroupItemsOrder(
            groupId,
            state.currentGroupOrderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> null
                }
            }
        )
        scheduleAutoBackup()
    }

    fun setViewType(v: ViewType) { preferences.viewType = v; _uiState.update { it.copy(viewType = v) }; scheduleAutoBackup() }
    fun cycleViewType() {
        val next = when (_uiState.value.viewType) {
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            else                -> ViewType.GRID_LARGE   // GRID_SMALL and LIST → GRID_LARGE
        }
        setViewType(next)
    }
    fun setFolderViewType(v: ViewType) { preferences.folderViewType = v; _uiState.update { it.copy(folderViewType = v) }; scheduleAutoBackup() }
    fun cycleFolderViewType() {
        val next = when (_uiState.value.folderViewType) {
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            else                -> ViewType.GRID_LARGE   // GRID_SMALL and LIST → GRID_LARGE
        }
        setFolderViewType(next)
    }
    fun setSortOption(s: SortOption) { preferences.sortOption = s; _uiState.update { it.copy(sortOption = s) }; silentRefresh(); scheduleAutoBackup() }
    fun setImageSortOption(s: ImageSortOption) {
        preferences.imageSortOption = s
        // Sort existing folder images in-memory immediately so that both
        // imageSortOption and folderImages change in the same recomposition frame.
        // This prevents LazyVerticalGrid's stable keys from re-scrolling when
        // the async data arrives later.
        val sorted = sortImagesInMemory(_uiState.value.folderImages, s)
        _uiState.update { it.copy(imageSortOption = s, folderImages = sorted) }
        silentRefresh()
        refreshFolderImages()
        scheduleAutoBackup()
    }
    fun setGroupSortOption(s: SortOption) { preferences.groupSortOption = s; _uiState.update { it.copy(groupSortOption = s) }; scheduleAutoBackup() }

    private fun sortImagesInMemory(images: List<ImageItem>, option: ImageSortOption): List<ImageItem> {
        return when (option) {
            ImageSortOption.CUSTOM_ORDER -> images.sortedByDescending { it.dateModified }
            ImageSortOption.NAME_A_TO_Z -> images.sortedBy { it.displayName.lowercase() }
            ImageSortOption.NAME_Z_TO_A -> images.sortedByDescending { it.displayName.lowercase() }
            ImageSortOption.DATE_CREATED_ASC -> images.sortedBy { it.id }
            ImageSortOption.DATE_CREATED_DESC -> images.sortedByDescending { it.id }
            ImageSortOption.DATE_MODIFIED_ASC -> images.sortedBy { it.dateModified }
            ImageSortOption.DATE_MODIFIED_DESC -> images.sortedByDescending { it.dateModified }
        }
    }
    fun enterSelectionMode() = _uiState.update { it.copy(isSelectionMode = true) }
    fun exitSelectionMode() = _uiState.update { it.copy(isSelectionMode = false, selectedImageIds = emptySet(), selectedFolderIds = emptySet(), selectedGroupIds = emptySet()) }

    fun toggleImageSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.selectedImageIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else { if (sel.size >= 1000) return@update s; sel.add(id) }
            s.copy(selectedImageIds = sel, isSelectionMode = sel.isNotEmpty() || s.selectedFolderIds.isNotEmpty())
        }
    }

    fun toggleFolderSelection(id: Int) {
        _uiState.update { s ->
            val sel = s.selectedFolderIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else sel.add(id)
            s.copy(selectedFolderIds = sel, isSelectionMode = sel.isNotEmpty() || s.selectedImageIds.isNotEmpty() || s.selectedGroupIds.isNotEmpty())
        }
    }

    fun toggleGroupSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.selectedGroupIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else sel.add(id)
            s.copy(selectedGroupIds = sel, isSelectionMode = sel.isNotEmpty() || s.selectedFolderIds.isNotEmpty() || s.selectedImageIds.isNotEmpty())
        }
    }

    fun selectAllImages() = _uiState.update { s -> s.copy(selectedImageIds = s.folderImages.map { it.id }.toSet()) }
    fun deselectAllImages() = _uiState.update { it.copy(selectedImageIds = emptySet()) }
    fun selectAllFolders() = _uiState.update { s -> s.copy(selectedFolderIds = s.folders.map { it.bucketId }.toSet()) }
    fun deselectAllFolders() = _uiState.update { it.copy(selectedFolderIds = emptySet()) }
    fun activateSearch() = _uiState.update { it.copy(isSearchActive = true) }
    fun deactivateSearch() = _uiState.update { it.copy(isSearchActive = false, searchQuery = "", searchResults = emptyList()) }

    fun setSearchQuery(q: String) {
        _uiState.update { it.copy(searchQuery = q) }
        if (q.isNotBlank()) {
            viewModelScope.launch {
                val r = repository.getImages(_uiState.value.imageSortOption, searchQuery = q)
                _uiState.update { it.copy(searchResults = r) }
            }
        } else _uiState.update { it.copy(searchResults = emptyList()) }
    }

    fun openFolder(bucketId: Int, name: String) {
        _uiState.update {
            it.copy(
                currentFolderBucketId = bucketId,
                currentFolderName = name,
                isSelectionMode = false,
                selectedFolderIds = emptySet(),
                selectedImageIds = emptySet()
            )
        }
        viewModelScope.launch {
            val v = repository.getImages(_uiState.value.imageSortOption, bucketId = bucketId)
            _uiState.update { it.copy(folderImages = v) }
        }
    }

    fun closeFolder() = _uiState.update { it.copy(currentFolderBucketId = null, currentFolderName = "", folderImages = emptyList()) }

    // Carousel
    fun openCarousel(index: Int) = _uiState.update { it.copy(carouselIndex = index) }
    fun closeCarousel() = _uiState.update { it.copy(carouselIndex = -1) }

    /** Delete a single image directly from the carousel overlay (no selection mode required). */
    fun deleteCarouselImage(imageId: Long) {
        // Optimistic: remove from visible lists immediately so the grid updates without a flash
        _uiState.update { s ->
            s.copy(
                folderImages = s.folderImages.filter { it.id != imageId },
                images = s.images.filter { it.id != imageId }
            )
        }
        closeCarousel()
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.deleteImages(listOf(imageId))
            silentRefresh()
            refreshFolderImages()
        }
    }

    private fun refreshCurrentFolderIfOpen() {
        refreshFolderImages()
    }

    fun deleteSelectedImages() {
        val idsToDelete = _uiState.value.selectedImageIds
        // Optimistic: remove images from lists immediately — Compose animates the gaps out
        _uiState.update { s ->
            s.copy(
                folderImages = s.folderImages.filter { it.id !in idsToDelete },
                images = s.images.filter { it.id !in idsToDelete },
                isSelectionMode = false,
                selectedImageIds = emptySet()
            )
        }
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.deleteImages(idsToDelete.toList())
            silentRefresh()
            refreshFolderImages()
        }
    }

    fun deleteSelectedFolders() {
        val idsToDelete = _uiState.value.selectedFolderIds
        // Optimistic: remove folders from all visible lists immediately
        _uiState.update { s ->
            s.copy(
                orderedMixedItems = s.orderedMixedItems.filter { item ->
                    item !is FolderItem || item.bucketId !in idsToDelete
                },
                folders = s.folders.filter { it.bucketId !in idsToDelete },
                ungroupedFolders = s.ungroupedFolders.filter { it.bucketId !in idsToDelete },
                isSelectionMode = false,
                selectedFolderIds = emptySet()
            )
        }
        viewModelScope.launch {
            isInternalChange.set(true)
            for (folderId in idsToDelete) {
                val images = repository.getImages(bucketId = folderId)
                repository.deleteImages(images.map { it.id })
            }
            silentRefresh()
        }
    }

    fun renameImage(id: Long, name: String) {
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.renameImage(id, name)
            silentRefresh()
            refreshFolderImages()
        }
    }

    private fun getSelectedImages(): List<ImageItem> {
        val state = _uiState.value
        return state.selectedImageIds.mapNotNull { id ->
            state.folderImages.find { it.id == id } ?: state.images.find { it.id == id }
        }
    }

    fun getSelectedLocationPath(): String? {
        val state = _uiState.value
        if (state.currentFolderBucketId != null) {
            val image = state.selectedImageIds.firstOrNull()?.let { id ->
                state.folderImages.find { it.id == id }
            }
            return image?.path?.let { java.io.File(it).parent }
        } else {
            val folder = state.selectedFolderIds.firstOrNull()?.let { id ->
                state.folders.find { it.bucketId == id }
            }
            return folder?.path
        }
    }

    private fun destFolderName(path: String): String {
        return path.trimEnd('/').substringAfterLast('/')
    }

    fun cancelCopyMove() {
        copyMoveCancelled = true
        _fileConflict.value?.deferred?.complete(ConflictResolution.SKIP)
        _fileConflict.value = null
        copyMoveJob?.cancel()
    }

    private suspend fun askConflictResolution(fileName: String): ConflictResolution {
        val deferred = CompletableDeferred<ConflictResolution>()
        _fileConflict.value = FileConflict(fileName, deferred)
        return deferred.await()
    }

    fun moveSelectedImages(dest: String) {
        val images = getSelectedImages()
        if (images.isEmpty()) return
        _uiState.update { it.copy(showMoveFolderPicker = false) }
        exitSelectionMode()

        val folderName = destFolderName(dest)
        copyMoveCancelled = false
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Moving items to $folderName…", current = 0, total = images.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            repository.moveImages(
                images, dest,
                onProgress = { current, total ->
                    _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total)
                },
                isCancelled = { copyMoveCancelled },
                onConflict = { fileName -> askConflictResolution(fileName) }
            )
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = images.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderImages()
        }
    }

    fun copySelectedImages(dest: String) {
        val images = getSelectedImages()
        if (images.isEmpty()) return
        _uiState.update { it.copy(showCopyFolderPicker = false) }
        exitSelectionMode()

        val folderName = destFolderName(dest)
        copyMoveCancelled = false
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Copying items to $folderName…", current = 0, total = images.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            repository.copyImages(
                images, dest,
                onProgress = { current, total ->
                    _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total)
                },
                isCancelled = { copyMoveCancelled },
                onConflict = { fileName -> askConflictResolution(fileName) }
            )
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = images.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderImages()
        }
    }

    fun createFolder(name: String) {
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.createFolder(name)
            silentRefresh()
        }
    }

    fun createFolderAndMoveImages(folderName: String) {
        val images = getSelectedImages()
        if (images.isEmpty()) return
        _uiState.update { it.copy(showMoveFolderPicker = false) }
        exitSelectionMode()

        copyMoveCancelled = false
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Moving items to $folderName…", current = 0, total = images.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                repository.moveImages(
                    images, path,
                    onProgress = { current, total -> _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total) },
                    isCancelled = { copyMoveCancelled },
                    onConflict = { fileName -> askConflictResolution(fileName) }
                )
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = images.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderImages()
        }
    }

    fun createFolderAndCopyImages(folderName: String) {
        val images = getSelectedImages()
        if (images.isEmpty()) return
        _uiState.update { it.copy(showCopyFolderPicker = false) }
        exitSelectionMode()

        copyMoveCancelled = false
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Copying items to $folderName…", current = 0, total = images.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                repository.copyImages(
                    images, path,
                    onProgress = { current, total -> _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total) },
                    isCancelled = { copyMoveCancelled },
                    onConflict = { fileName -> askConflictResolution(fileName) }
                )
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = images.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderImages()
        }
    }

    // Details dialog
    fun showImageDetails(image: ImageItem) = _uiState.update { it.copy(showDetailsDialog = true, detailsTarget = image) }
    fun dismissImageDetails() = _uiState.update { it.copy(showDetailsDialog = false, detailsTarget = null) }

    fun showDetailsForSelectedImage() {
        val s = _uiState.value
        if (s.selectedImageIds.isEmpty()) return
        val selectedId = s.selectedImageIds.first()
        val image = s.folderImages.find { it.id == selectedId }
            ?: s.images.find { it.id == selectedId }
        image?.let { showImageDetails(it) }
    }

    fun showSortDialog() = _uiState.update { it.copy(showSortDialog = true) }
    fun dismissSortDialog() = _uiState.update { it.copy(showSortDialog = false) }
    fun showViewAsDialog() = _uiState.update { it.copy(showViewAsDialog = true) }
    fun dismissViewAsDialog() = _uiState.update { it.copy(showViewAsDialog = false) }
    fun dismissRenameDialog() = _uiState.update { it.copy(showRenameDialog = false, renameTarget = null) }
    fun showDeleteDialog() = _uiState.update { it.copy(showDeleteDialog = true) }
    fun dismissDeleteDialog() = _uiState.update { it.copy(showDeleteDialog = false) }
    fun showCreateFolderDialog() = _uiState.update { it.copy(showCreateFolderDialog = true) }
    fun dismissCreateFolderDialog() = _uiState.update { it.copy(showCreateFolderDialog = false) }
    fun showMoveFolderPicker() { _uiState.update { it.copy(showMoveFolderPicker = true) } }
    fun dismissMoveFolderPicker() = _uiState.update { it.copy(showMoveFolderPicker = false) }
    fun showCopyFolderPicker() { _uiState.update { it.copy(showCopyFolderPicker = true) } }
    fun dismissCopyFolderPicker() = _uiState.update { it.copy(showCopyFolderPicker = false) }

    /** Select a single image (carousel context) then open the copy folder picker. */
    fun carouselCopy(imageId: Long) {
        // Close the carousel FIRST so the FolderPickerScreen early-return can render
        // (the carousel early-return has higher priority and would block the picker)
        _uiState.update { it.copy(selectedImageIds = setOf(imageId), carouselIndex = -1) }
        showCopyFolderPicker()
    }

    /** Select a single image (carousel context) then open the move folder picker. */
    fun carouselMove(imageId: Long) {
        // Close the carousel FIRST so the FolderPickerScreen early-return can render
        _uiState.update { it.copy(selectedImageIds = setOf(imageId), carouselIndex = -1) }
        showMoveFolderPicker()
    }
    fun showAbout() = _uiState.update { it.copy(showAbout = true) }
    fun dismissAbout() = _uiState.update { it.copy(showAbout = false) }

    fun showSettings() = _uiState.update { it.copy(showSettings = true) }
    fun dismissSettings() = _uiState.update { it.copy(showSettings = false) }

    fun updateCarouselShowBarsOnOpen(value: Boolean) {
        preferences.carouselShowBarsOnOpen = value
        _uiState.update { it.copy(carouselShowBarsOnOpen = value) }
    }

    fun updateAutoBackupEnabled(value: Boolean) {
        preferences.autoBackupEnabled = value
        _uiState.update { it.copy(autoBackupEnabled = value) }
    }

    /**
     * Debounced auto-backup — cancels any pending backup and schedules a new one
     * to fire after [AUTO_BACKUP_DEBOUNCE_MS] on the IO dispatcher.
     * No-op when auto-backup is disabled.
     */
    fun scheduleAutoBackup() {
        if (!preferences.autoBackupEnabled) return
        autoBackupJob?.cancel()
        autoBackupJob = viewModelScope.launch {
            delay(AUTO_BACKUP_DEBOUNCE_MS)
            withContext(Dispatchers.IO) {
                com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
            }
        }
    }

    /**
     * Cancel the debounce and immediately fire an async backup.
     * Called from [MainActivity.onStop] to cover backgrounding and idle.
     */
    fun onAppBackground() {
        if (!preferences.autoBackupEnabled) return
        autoBackupJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
        }
    }

    fun createBackupJson(): String =
        com.imagelibrary.data.util.BackupManager.createBackup(getApplication())

    /** Save backup JSON to Documents/ImageLibrary/backups/backup.json — runs on IO, never blocks the UI. */
    suspend fun saveBackupToFile(): Boolean = withContext(Dispatchers.IO) {
        com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
    }

    /** Restore from Documents/ImageLibrary/backups/backup.json — runs on IO, never blocks the UI. */
    suspend fun restoreBackupFromFile(): Boolean = withContext(Dispatchers.IO) {
        val ok = com.imagelibrary.data.util.BackupManager.restoreBackupFromFile(getApplication())
        if (ok) refreshStateAfterRestore()
        ok
    }

    fun restoreBackupJson(json: String): Boolean {
        val ok = com.imagelibrary.data.util.BackupManager.restoreBackup(getApplication(), json)
        if (ok) refreshStateAfterRestore()
        return ok
    }

    private fun refreshStateAfterRestore() {
        isRestoringBackup = false
        _uiState.update {
            it.copy(
                viewType = preferences.viewType,
                folderViewType = preferences.folderViewType,
                sortOption = preferences.sortOption,
                imageSortOption = preferences.imageSortOption,
                carouselShowBarsOnOpen = preferences.carouselShowBarsOnOpen,
                autoBackupEnabled = preferences.autoBackupEnabled,
                groupsAlwaysOnTop = preferences.groupsAlwaysOnTop
            )
        }
        loadData()
    }

    // ═══════════════════════════════════════════════════════════════
    // ── Group Feature ─────────────────────────────────────────────
    // ═══════════════════════════════════════════════════════════════

    // ── Group creation mode (from 3-dot menu "Group") ──

    fun enterGroupCreationMode() {
        _uiState.update {
            it.copy(
                isGroupCreationMode = true,
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds = emptySet()
            )
        }
    }

    fun exitGroupCreationMode() {
        _uiState.update {
            it.copy(
                isGroupCreationMode = false,
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds = emptySet(),
                pendingGroupCreationName = ""
            )
        }
    }

    fun toggleGroupCreationFolderSelection(bucketId: Int) {
        _uiState.update { s ->
            val sel = s.groupCreationSelectedFolderIds.toMutableSet()
            if (sel.contains(bucketId)) sel.remove(bucketId) else sel.add(bucketId)
            s.copy(groupCreationSelectedFolderIds = sel)
        }
    }

    fun toggleGroupCreationGroupSelection(groupId: Long) {
        _uiState.update { s ->
            val sel = s.groupCreationSelectedGroupIds.toMutableSet()
            if (sel.contains(groupId)) sel.remove(groupId) else sel.add(groupId)
            s.copy(groupCreationSelectedGroupIds = sel)
        }
    }

    /** Called when "Save" is pressed in group-creation mode */
    fun showGroupNameDialog() {
        val pending = _uiState.value.pendingGroupCreationName
        if (pending.isNotBlank()) {
            // Name was set upfront via the + menu — create immediately
            createGroupFromCreationMode(pending)
        } else {
            _uiState.update { it.copy(showGroupNameDialog = true) }
        }
    }
    fun dismissGroupNameDialog() = _uiState.update {
        it.copy(showGroupNameDialog = false, groupNameDialogForBottomBar = false, groupNameDialogForCreation = false)
    }

    /** Show the name dialog FIRST, then enter creation mode (called from + → Group) */
    fun showGroupNameForCreation() {
        _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForCreation = true) }
    }

    /** Enters group creation mode with an already-chosen name (skips the name dialog at the end) */
    fun enterGroupCreationModeWithName(name: String) {
        _uiState.update {
            it.copy(
                showGroupNameDialog = false,
                groupNameDialogForCreation = false,
                isGroupCreationMode = true,
                pendingGroupCreationName = name,
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds = emptySet()
            )
        }
    }

    fun createGroupFromCreationMode(name: String) {
        val s = _uiState.value
        val folderIds = s.groupCreationSelectedFolderIds.toList()
        val groupIds = s.groupCreationSelectedGroupIds.toList()
        val parentGroupId = s.currentGroupId

        viewModelScope.launch {
            groupRepository.createGroup(
                name = name,
                folderBucketIds = folderIds,
                subGroupIds = groupIds,
                parentGroupId = parentGroupId
            )
            exitGroupCreationMode()
            silentRefresh()
            if (s.currentGroupId != null) {
                refreshCurrentGroup()
            }
            scheduleAutoBackup()
        }
    }

    /** Called from bottom bar flow (long-press selection → Group button → name dialog) */
    fun showGroupNameDialogForBottomBar() {
        _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForBottomBar = true) }
    }

    fun createGroupFromSelection(name: String) {
        val s = _uiState.value
        val folderIds = s.selectedFolderIds.toList()
        val groupIds = s.selectedGroupIds.toList()
        val parentGroupId = s.currentGroupId

        viewModelScope.launch {
            groupRepository.createGroup(
                name = name,
                folderBucketIds = folderIds,
                subGroupIds = groupIds,
                parentGroupId = parentGroupId
            )
            exitSelectionMode()
            silentRefresh()
            if (s.currentGroupId != null) {
                refreshCurrentGroup()
            }
            scheduleAutoBackup()
        }
    }

    // ── Open/close group ──

    fun openGroup(groupId: Long, name: String) {
        // Push current group to stack if we're inside one
        val s = _uiState.value
        val newStack = if (s.currentGroupId != null) {
            s.groupStack + Pair(s.currentGroupId, s.currentGroupName)
        } else {
            s.groupStack
        }
        // Load the persisted sort for this group (defaults to CUSTOM_ORDER if not yet set)
        val groupSort = preferences.getGroupSortOption(groupId)
        _uiState.update {
            it.copy(
                currentGroupId = groupId,
                currentGroupName = name,
                groupStack = newStack,
                currentGroupSortOption = groupSort
            )
        }
        refreshCurrentGroup()
    }

    fun closeGroup() {
        val s = _uiState.value
        if (s.groupStack.isNotEmpty()) {
            // Pop from stack
            val (prevId, prevName) = s.groupStack.last()
            val parentSort = preferences.getGroupSortOption(prevId)
            _uiState.update {
                it.copy(
                    currentGroupId = prevId,
                    currentGroupName = prevName,
                    groupStack = s.groupStack.dropLast(1),
                    currentGroupFolders = emptyList(),
                    currentGroupSubGroups = emptyList(),
                    currentGroupSortOption = parentSort
                )
            }
            refreshCurrentGroup()
        } else {
            _uiState.update {
                it.copy(
                    currentGroupId = null,
                    currentGroupName = "",
                    currentGroupFolders = emptyList(),
                    currentGroupSubGroups = emptyList(),
                    groupStack = emptyList(),
                    currentGroupSortOption = SortOption.CUSTOM_ORDER
                )
            }
        }
    }

    private fun refreshCurrentGroup() {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            val s         = _uiState.value
            val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
            val allFolders = s.folders.ifEmpty { repository.getFolders() }
            // Filter from the globally-sorted list so non-custom sorts display correctly
            val bucketIdSet  = bucketIds.toSet()
            val groupFolders = allFolders.filter { it.bucketId in bucketIdSet }
            val subGroups    = groupRepository.getChildGroups(groupId)
            // Use the group's own sort option (independent of the root sort)
            val groupSortOption = s.currentGroupSortOption

            val orderedMixed: List<Any> = if (groupSortOption == SortOption.CUSTOM_ORDER) {
                val savedOrder = preferences.customGroupItemsOrder(groupId)
                if (savedOrder.isEmpty()) {
                    buildList {
                        subGroups.forEach    { add(it) }
                        groupFolders.forEach { add(it) }
                    }
                } else {
                    val byGroupKey  = subGroups.associateBy    { "g_${it.groupId}"  }
                    val byFolderKey = groupFolders.associateBy { "f_${it.bucketId}" }
                    val savedSet    = savedOrder.toSet()
                    buildList {
                        // New items prepended at the top
                        subGroups.forEach    { g -> if ("g_${g.groupId}"  !in savedSet) add(g) }
                        groupFolders.forEach { f -> if ("f_${f.bucketId}" !in savedSet) add(f) }
                        // Restore saved order, skipping deleted items
                        for (key in savedOrder) {
                            val item = byGroupKey[key] ?: byFolderKey[key]
                            if (item != null) add(item)
                        }
                    }
                }
            } else {
                // Non-custom sort: sort all items by the group's own sort option
                sortMixedItems(subGroups + groupFolders, groupSortOption, s.groupsAlwaysOnTop)
            }

            _uiState.update {
                it.copy(
                    currentGroupFolders           = groupFolders,
                    currentGroupSubGroups         = subGroups,
                    currentGroupOrderedMixedItems = orderedMixed
                )
            }
        }
    }

    // ── Group actions (inside group detail) ──

    fun showRenameGroupDialog() = _uiState.update { it.copy(showRenameGroupDialog = true) }
    fun dismissRenameGroupDialog() = _uiState.update { it.copy(showRenameGroupDialog = false) }

    fun renameCurrentGroup(newName: String) {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            groupRepository.renameGroup(groupId, newName)
            _uiState.update { it.copy(currentGroupName = newName) }
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    fun showDestroyGroupDialog() = _uiState.update { it.copy(showDestroyGroupDialog = true) }
    fun dismissDestroyGroupDialog() = _uiState.update { it.copy(showDestroyGroupDialog = false) }

    fun destroyCurrentGroup() {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            groupRepository.destroyGroup(groupId)
            closeGroup()
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    // ── Add folder to group ──

    fun showAddFolderToGroup() = _uiState.update { it.copy(showAddFolderToGroup = true) }
    fun dismissAddFolderToGroup() = _uiState.update { it.copy(showAddFolderToGroup = false) }

    fun addFoldersToCurrentGroup(folderBucketIds: Set<Int>, subGroupIds: Set<Long>) {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            if (folderBucketIds.isNotEmpty()) {
                groupRepository.addFoldersToGroup(groupId, folderBucketIds.toList())
            }
            if (subGroupIds.isNotEmpty()) {
                groupRepository.addSubGroupsToGroup(groupId, subGroupIds.toList())
            }
            dismissAddFolderToGroup()
            silentRefresh()
            refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    // ── Remove selected items from group (delete in group context just removes from group) ──

    fun removeSelectedFromGroup() {
        val s = _uiState.value
        val groupId = s.currentGroupId ?: return
        viewModelScope.launch {
            for (bucketId in s.selectedFolderIds) {
                groupRepository.removeFolderFromGroup(bucketId)
            }
            for (gId in s.selectedGroupIds) {
                groupRepository.destroyGroup(gId)
            }
            exitSelectionMode()
            silentRefresh()
            refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    // ── Ungroup selected groups (from root folder view) ──

    fun ungroupSelectedGroups() {
        val s = _uiState.value
        if (s.selectedGroupIds.isEmpty()) return
        viewModelScope.launch {
            for (gId in s.selectedGroupIds) {
                groupRepository.destroyGroup(gId)
            }
            exitSelectionMode()
            silentRefresh()
            if (s.currentGroupId != null) {
                refreshCurrentGroup()
            }
            scheduleAutoBackup()
        }
    }

    // ── Select all inside group ──

    fun selectAllInGroup() {
        val s = _uiState.value
        val allFolderIds = s.currentGroupFolders.map { it.bucketId }.toSet()
        val allGroupIds = s.currentGroupSubGroups.map { it.groupId }.toSet()
        val allSelected = s.selectedFolderIds == allFolderIds && s.selectedGroupIds == allGroupIds
        if (allSelected) {
            _uiState.update { it.copy(selectedFolderIds = emptySet(), selectedGroupIds = emptySet()) }
        } else {
            _uiState.update { it.copy(selectedFolderIds = allFolderIds, selectedGroupIds = allGroupIds) }
        }
    }

    // ── Select all folders + groups on main screen ──

    fun selectAllFoldersAndGroups() {
        val s = _uiState.value
        val allFolderIds = s.ungroupedFolders.map { it.bucketId }.toSet()
        val allGroupIds = s.rootGroups.map { it.groupId }.toSet()
        val allSelected = s.selectedFolderIds == allFolderIds && s.selectedGroupIds == allGroupIds
        if (allSelected) {
            deselectAllFolders()
            _uiState.update { it.copy(selectedGroupIds = emptySet()) }
        } else {
            _uiState.update { it.copy(selectedFolderIds = allFolderIds, selectedGroupIds = allGroupIds) }
        }
    }

    // ── Move to group picker ──

    fun showMoveToGroupPicker() {
        val s = _uiState.value
        _uiState.update {
            it.copy(
                showMoveToGroupPicker = true,
                moveToGroupFolderIds = s.selectedFolderIds,
                moveToGroupGroupIds = s.selectedGroupIds
            )
        }
        exitSelectionMode()
    }

    fun dismissMoveToGroupPicker() {
        _uiState.update {
            it.copy(
                showMoveToGroupPicker = false,
                moveToGroupFolderIds = emptySet(),
                moveToGroupGroupIds = emptySet()
            )
        }
    }

    fun moveSelectionToGroup(targetGroupId: Long?) {
        val s = _uiState.value
        val folderIds = s.moveToGroupFolderIds.toList()
        val groupIds = s.moveToGroupGroupIds.toList()
        viewModelScope.launch {
            groupRepository.moveItemsToGroup(folderIds, groupIds, targetGroupId)
            dismissMoveToGroupPicker()
            silentRefresh()
            if (s.currentGroupId != null) {
                refreshCurrentGroup()
            }
            scheduleAutoBackup()
        }
    }

    fun createGroupAndMoveSelection(name: String) {
        val s = _uiState.value
        val folderIds = s.moveToGroupFolderIds.toList()
        val groupIds = s.moveToGroupGroupIds.toList()
        viewModelScope.launch {
            val newGroupId = groupRepository.createGroup(
                name = name,
                folderBucketIds = emptyList(),
                subGroupIds = emptyList(),
                parentGroupId = null
            )
            groupRepository.moveItemsToGroup(folderIds, groupIds, newGroupId)
            dismissMoveToGroupPicker()
            silentRefresh()
            if (s.currentGroupId != null) {
                refreshCurrentGroup()
            }
            scheduleAutoBackup()
        }
    }

    // ── Create Album flow ─────────────────────────────────────────────────────

    fun showCreateAlbumDialog() {
        viewModelScope.launch {
            val dcimNames = repository.getExistingDcimFolderNames()
            _uiState.update { it.copy(showCreateAlbumDialog = true, dcimFolderNames = dcimNames) }
        }
    }

    fun dismissCreateAlbumDialog() =
        _uiState.update { it.copy(showCreateAlbumDialog = false) }

    fun startCreateAlbumPicker(name: String) {
        _uiState.update {
            it.copy(
                showCreateAlbumDialog = false,
                showCreateAlbumPicker = true,
                pendingAlbumName = name,
                albumCreationSelectedImageIds = emptySet(),
                albumCreationBrowsedImages = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }
    }

    fun loadAlbumCreationImages(bucketId: Int, name: String) {
        _uiState.update {
            it.copy(albumCreationCurrentBucketId = bucketId, albumCreationCurrentBucketName = name)
        }
        viewModelScope.launch {
            val images = repository.getImages(_uiState.value.imageSortOption, bucketId = bucketId)
            _uiState.update { it.copy(albumCreationBrowsedImages = images) }
        }
    }

    fun closeAlbumCreationFolder() {
        _uiState.update {
            it.copy(
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = "",
                albumCreationBrowsedImages = emptyList()
            )
        }
    }

    fun toggleAlbumCreationImageSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.albumCreationSelectedImageIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else {
                if (sel.size >= 500) return@update s
                sel.add(id)
            }
            s.copy(albumCreationSelectedImageIds = sel)
        }
    }

    fun showCreateAlbumCopyMoveDialog() =
        _uiState.update { it.copy(showCreateAlbumCopyMoveDialog = true) }

    fun dismissCreateAlbumCopyMoveDialog() =
        _uiState.update { it.copy(showCreateAlbumCopyMoveDialog = false) }

    fun cancelAlbumCreation() {
        _uiState.update {
            it.copy(
                showCreateAlbumPicker = false,
                showCreateAlbumCopyMoveDialog = false,
                pendingAlbumName = "",
                albumCreationSelectedImageIds = emptySet(),
                albumCreationBrowsedImages = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }
    }

    fun confirmAlbumCreation(copy: Boolean) {
        val s = _uiState.value
        val imageIds = s.albumCreationSelectedImageIds
        val folderName = s.pendingAlbumName
        if (imageIds.isEmpty() || folderName.isBlank()) return

        val images = s.images.filter { it.id in imageIds }

        _uiState.update {
            it.copy(
                showCreateAlbumPicker = false,
                showCreateAlbumCopyMoveDialog = false,
                pendingAlbumName = "",
                albumCreationSelectedImageIds = emptySet(),
                albumCreationBrowsedImages = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }

        copyMoveCancelled = false
        val verb = if (copy) "Copying" else "Moving"
        _copyMoveProgress.value = CopyMoveProgress(
            isActive = true,
            title = "$verb items to \"$folderName\"...",
            current = 0,
            total = images.size
        )

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                if (copy) {
                    repository.copyImages(
                        images,
                        path,
                        onProgress = { cur, tot ->
                            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = cur, total = tot)
                        },
                        isCancelled = { copyMoveCancelled },
                        onConflict = { fileName -> askConflictResolution(fileName) }
                    )
                } else {
                    repository.moveImages(
                        images,
                        path,
                        onProgress = { cur, tot ->
                            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = cur, total = tot)
                        },
                        isCancelled = { copyMoveCancelled },
                        onConflict = { fileName -> askConflictResolution(fileName) }
                    )
                }
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = images.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderImages()
            scheduleAutoBackup()
        }
    }

}
