package com.videolibrary.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.VideoSortOption
import com.videolibrary.data.model.ViewType
import com.videolibrary.data.preferences.AppPreferences
import com.videolibrary.data.db.GroupStore
import com.videolibrary.data.repository.GroupRepository
import com.videolibrary.data.repository.VideoRepository
import com.videolibrary.data.util.FileLogger as Log
import java.util.concurrent.atomic.AtomicBoolean

data class VideoListUiState(
    val videos: List<VideoItem> = emptyList(),
    val folders: List<FolderItem> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTab: Int = 0,
    val viewType: ViewType = ViewType.LIST,
    val folderViewType: ViewType = ViewType.LIST,
    val sortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER,
    val videoSortOption: VideoSortOption = VideoSortOption.CUSTOM_ORDER,
    val instantPlayerEnabled: Boolean = true,
    val isSelectionMode: Boolean = false,
    val selectedVideoIds: Set<Long> = emptySet(),
    val selectedFolderIds: Set<Int> = emptySet(),
    val searchQuery: String = "",
    val isSearchActive: Boolean = false,
    val searchResults: List<VideoItem> = emptyList(),
    val currentFolderBucketId: Int? = null,
    val currentFolderName: String = "",
    val currentFolderSortOption: VideoSortOption = VideoSortOption.CUSTOM_ORDER,
    val folderVideos: List<VideoItem> = emptyList(),
    val showSortDialog: Boolean = false,
    val showViewAsDialog: Boolean = false,
    val showRenameDialog: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val showCreateFolderDialog: Boolean = false,
    val showCreateAlbumDialog: Boolean = false,
    val showMoveFolderPicker: Boolean = false,
    val showCopyFolderPicker: Boolean = false,
    val showAbout: Boolean = false,
    val showDetailsDialog: Boolean = false,
    val showSettings: Boolean = false,
    val autoBackupEnabled: Boolean = false,
    val independentSortEnabled: Boolean = true,
    /** When true, groups are pinned to the top of sorted lists; ungrouped albums follow. */
    val groupsAlwaysOnTop: Boolean = false,
    /** Sort option for the currently-open group (independent from the root sort). */
    val currentGroupSortOption: FolderSortOption = FolderSortOption.CUSTOM_ORDER,
    /** Whether the Hide Folders full-screen is shown. */
    val showHideFolders: Boolean = false,
    /** All folders (visible + hidden stubs) for the Hide Folders screen. */
    val allFoldersForHide: List<FolderItem> = emptyList(),
    /** Paths of currently hidden folders. */
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
    val hideScreenStartedInsideGroup: Boolean = false,
    val renameTarget: VideoItem? = null,
    val error: String? = null,
    val total: Int = 0,
    val scrollToTopTrigger: Int = 0,

    // ── Group data ────────────────────────────────────────────────────
    val rootGroups: List<GroupItem> = emptyList(),
    val ungroupedFolders: List<FolderItem> = emptyList(),
    /** Interleaved display order of GroupItems and FolderItems for the Folders tab. */
    val orderedMixedItems: List<Any> = emptyList(),
    /** Per-group custom sort orders, forwarded to FolderPickerScreen so the picker
     *  respects the same drag order as the group detail screen. */
    val allGroupCustomOrders: Map<Long, List<String>> = emptyMap(),

    // ── Group navigation (stack-based) ────────────────────────────────
    val currentGroupId: Long? = null,
    val currentGroupName: String = "",
    val currentGroupFolders: List<FolderItem> = emptyList(),
    val currentGroupSubGroups: List<GroupItem> = emptyList(),
    val groupStack: List<Pair<Long, String>> = emptyList(),
    /** Interleaved drag-ordered list of GroupItems and FolderItems for the current group's detail screen. */
    val currentGroupOrderedMixedItems: List<Any> = emptyList(),

    // ── Group selection ───────────────────────────────────────────────
    val moveToGroupGroupIds: Set<Long> = emptySet(),
    // -- Selection & Group Creation --
    val selectedGroupIds: Set<Long> = emptySet(),
    val isGroupCreationMode: Boolean = false,
    val pendingGroupCreationName: String = "",
    val groupCreationSelectedFolderIds: Set<Int> = emptySet(),
    val groupCreationSelectedGroupIds: Set<Long> = emptySet(),
    // -- Group Dialogs --
    val showGroupNameDialog: Boolean = false,
    val groupNameDialogForBottomBar: Boolean = false,
    val groupNameDialogForCreation: Boolean = false,
    val existingGroupNames: Set<String> = emptySet(),
    val suggestedGroupName: String = "Group 1",
    val showRenameGroupDialog: Boolean = false,
    val showDestroyGroupDialog: Boolean = false,
    val showAddFolderToGroup: Boolean = false,
    val showMoveToGroupPicker: Boolean = false,
    val moveToGroupFolderIds: Set<Int> = emptySet(),
    // -- Create Album Flow --
    val showCreateAlbumPicker: Boolean = false,
    val showCreateAlbumCopyMoveDialog: Boolean = false,
    val pendingAlbumName: String = "",
    val albumCreationSelectedVideoIds: Set<Long> = emptySet(),
    val albumCreationBrowsedVideos: List<VideoItem> = emptyList(),
    val albumCreationCurrentBucketId: Int? = null,
    val albumCreationCurrentBucketName: String = "",
    // -- Create Album name suggestions --
    val dcimFolderNames: Set<String> = emptySet(),
    // -- Details --
    val detailsTarget: VideoItem? = null,
    val folderDetailScrollToTopTrigger: Int = 0,
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

class VideoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VideoRepository(application)
    val preferences = AppPreferences(application)
    private val groupStore = GroupStore(application)
    private val groupRepository = GroupRepository(groupStore, repository)
    private val _uiState = MutableStateFlow(
        VideoListUiState(
            selectedTab          = preferences.selectedTab,
            viewType             = preferences.viewType,
            folderViewType       = preferences.folderViewType,
            sortOption           = preferences.folderSortOption,
            videoSortOption      = preferences.videoSortOption,
            instantPlayerEnabled = preferences.instantPlayerEnabled,
            autoBackupEnabled    = preferences.autoBackupEnabled,
            independentSortEnabled = preferences.independentSortEnabled,
            groupsAlwaysOnTop    = preferences.groupsAlwaysOnTop,
            hiddenFolderPaths    = preferences.hiddenFolderPaths
        )
    )
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
        viewModelScope.launch {
            val mediaStoreFolders = repository.getFolders(s.sortOption)
            val hiddenMeta      = preferences.getAllHiddenFolderMeta()
            val mediaStorePaths = mediaStoreFolders.map { it.path }.toSet()
            val ghosts = hiddenMeta
                .filter { (path, _) -> path !in mediaStorePaths }
                .map { (path, triple) ->
                    FolderItem(bucketId = triple.second, name = triple.first,
                        itemCount = triple.third, path = path)
                }
            val allFolders = mediaStoreFolders + ghosts

            val groupedBucketIds = groupRepository.getGroupedBucketIds()
            val ungrouped = allFolders.filter { it.bucketId !in groupedBucketIds }

            // Apply the same sort as the root view
            val (sortedGroups, sortedUngrouped) = sortHideScreenItems(
                groups            = s.rootGroups,
                folders           = ungrouped,
                sortOption        = s.sortOption,
                groupsAlwaysOnTop = s.groupsAlwaysOnTop,
                groupId           = null
            )
            _uiState.update {
                it.copy(
                    showHideFolders         = true,
                    allFoldersForHide       = allFolders,
                    rootGroupsForHide       = sortedGroups,
                    ungroupedFoldersForHide = sortedUngrouped,
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
        viewModelScope.launch {
            val mediaStoreFolders = repository.getFolders(s.sortOption)
            val hiddenMeta        = preferences.getAllHiddenFolderMeta()
            val mediaStorePaths   = mediaStoreFolders.map { it.path }.toSet()
            val ghosts = hiddenMeta
                .filter { (path, _) -> path !in mediaStorePaths }
                .map { (path, triple) ->
                    FolderItem(bucketId = triple.second, name = triple.first,
                               itemCount = triple.third, path = path)
                }
            val allFolders = mediaStoreFolders + ghosts

            val memberBucketIds = groupRepository.getFolderBucketIdsForGroup(groupId).toSet()
            val subGroups       = groupRepository.getChildGroups(groupId)
            val directFolders   = allFolders.filter { it.bucketId in memberBucketIds }

            // Apply the same sort as the group detail view
            val (sortedSubGroups, sortedFolders) = sortHideScreenItems(
                groups            = subGroups,
                folders           = directFolders,
                sortOption        = s.currentGroupSortOption,
                groupsAlwaysOnTop = s.groupsAlwaysOnTop,
                groupId           = groupId
            )
            _uiState.update {
                it.copy(
                    showHideFolders              = true,
                    allFoldersForHide            = allFolders,
                    hiddenFolderPaths            = preferences.hiddenFolderPaths,
                    rootGroupsForHide            = emptyList(),
                    ungroupedFoldersForHide      = emptyList(),
                    hideScreenGroupId            = groupId,
                    hideScreenGroupName          = groupName,
                    hideScreenGroupFolders       = sortedFolders,
                    hideScreenGroupSubGroups     = sortedSubGroups,
                    hideScreenStartedInsideGroup = true
                )
            }
        }
    }

    fun openGroupInHideScreen(group: GroupItem) {
        viewModelScope.launch {
            val groupFolders = _uiState.value.allFoldersForHide
                .filter { it.bucketId in group.memberBucketIds }
            val subGroups  = groupRepository.getChildGroups(group.groupId)
            val sortOption = preferences.getGroupSortOption(group.groupId)
            val (sortedSubGroups, sortedFolders) = sortHideScreenItems(
                groups            = subGroups,
                folders           = groupFolders,
                sortOption        = sortOption,
                groupsAlwaysOnTop = _uiState.value.groupsAlwaysOnTop,
                groupId           = group.groupId
            )
            _uiState.update {
                it.copy(
                    hideScreenGroupId        = group.groupId,
                    hideScreenGroupName      = group.name,
                    hideScreenGroupFolders   = sortedFolders,
                    hideScreenGroupSubGroups = sortedSubGroups
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
        val groupFolders = _uiState.value.allFoldersForHide
            .filter { it.bucketId in group.memberBucketIds }
        val paths = groupFolders.map { it.path }.filter { it.isNotBlank() }
        if (paths.isEmpty()) return
        val currentHidden = _uiState.value.hiddenFolderPaths
        val allAlreadyHidden = paths.all { it in currentHidden }
        viewModelScope.launch {
            if (allAlreadyHidden) {
                paths.forEach { path -> preferences.removeHiddenFolderMeta(path) }
                val newPaths = currentHidden - paths.toSet()
                preferences.hiddenFolderPaths = newPaths
                _uiState.update { it.copy(hiddenFolderPaths = newPaths) }
            } else {
                paths.forEach { path ->
                    val f = groupFolders.find { it.path == path }
                    if (f != null) preferences.saveHiddenFolderMeta(path, f.name, f.bucketId, f.itemCount)
                }
                val newPaths = currentHidden + paths.toSet()
                preferences.hiddenFolderPaths = newPaths
                _uiState.update { it.copy(hiddenFolderPaths = newPaths) }
            }
            silentRefresh()
            // Only back up hidden-album changes when auto-backup is enabled
            if (preferences.autoBackupEnabled) {
                withContext(Dispatchers.IO) {
                    com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
                }
            }
        }
    }

    fun toggleFolderHidden(folder: FolderItem) {
        val path = folder.path
        if (path.isBlank()) return
        val currentlyHidden = path in _uiState.value.hiddenFolderPaths
        viewModelScope.launch {
            if (currentlyHidden) {
                val newPaths = preferences.hiddenFolderPaths - path
                preferences.hiddenFolderPaths = newPaths
                preferences.removeHiddenFolderMeta(path)
                _uiState.update { s -> s.copy(hiddenFolderPaths = newPaths) }
            } else {
                val newPaths = preferences.hiddenFolderPaths + path
                preferences.hiddenFolderPaths = newPaths
                preferences.saveHiddenFolderMeta(path, folder.name, folder.bucketId, folder.itemCount)
                _uiState.update { s -> s.copy(hiddenFolderPaths = newPaths) }
            }
            silentRefresh()
            // Only back up hidden-album changes when auto-backup is enabled
            if (preferences.autoBackupEnabled) {
                withContext(Dispatchers.IO) {
                    com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
                }
            }
        }
    }

    /**
     * Set the sort option for the currently-open group.
     * Persists the choice per group so each group remembers its own sort independently.
     */
    fun setCurrentGroupSortOption(option: FolderSortOption) {
        val groupId = _uiState.value.currentGroupId ?: return
        preferences.saveGroupSortOption(groupId, option)
        _uiState.update { it.copy(currentGroupSortOption = option) }
        refreshCurrentGroup()
        scheduleAutoBackup()
    }
    val uiState: StateFlow<VideoListUiState> = _uiState.asStateFlow()

    // Copy/Move progress
    private val _copyMoveProgress = MutableStateFlow(CopyMoveProgress())
    val copyMoveProgress: StateFlow<CopyMoveProgress> = _copyMoveProgress.asStateFlow()
    private var copyMoveJob: Job? = null
    @Volatile private var copyMoveCancelled = false
    @Volatile private var bulkResolution: ConflictResolution? = null

    // Share intent — collected once at root screen level
    private val _shareIntent = MutableSharedFlow<Intent>(extraBufferCapacity = 1)
    val shareIntent: SharedFlow<Intent> = _shareIntent.asSharedFlow()

    // Auto-backup
    private companion object { const val AUTO_BACKUP_DEBOUNCE_MS = 5_000L }
    private var autoBackupJob: Job? = null
    @Volatile private var isRestoringBackup = false

    // Smooth-refresh: set true before any self-initiated MediaStore write;
    // reset to false automatically at the end of loadDataCore().
    private val isInternalChange = AtomicBoolean(false)

    // File conflict resolution
    private val _fileConflict = MutableStateFlow<FileConflict?>(null)
    val fileConflict: StateFlow<FileConflict?> = _fileConflict.asStateFlow()

    fun resolveConflict(resolution: ConflictResolution) {
        _fileConflict.value?.deferred?.complete(resolution)
        _fileConflict.value = null
    }

    // ── MediaStore ContentObserver (Samsung-style auto-refresh) ──────────
    // Watches MediaStore.Video for external changes (screenshots, external
    // deletes, moves). Rapid-fire onChange calls are debounced to one reload.
    // Skipped entirely when the app itself triggered the MediaStore write.
    private var mediaObserverJob: Job? = null

    private val mediaObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            // ① If the app itself triggered this MediaStore event, skip —
            //    silentRefresh() was already called explicitly after the op.
            if (isInternalChange.get()) return

            // ② Debounce: cancel any pending refresh and restart the 500 ms timer.
            mediaObserverJob?.cancel()
            mediaObserverJob = viewModelScope.launch {
                delay(500L)
                Log.d("VideoVM", "MediaStore changed — silent refresh")
                silentRefresh()
                refreshCurrentFolderIfOpen()
            }
        }
    }

    init {
        // Register observer for video MediaStore changes (notifyForDescendants
        // = true so we also catch per-row URI notifications)
        getApplication<Application>().contentResolver.registerContentObserver(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            true,
            mediaObserver
        )
        loadData()
    }

    override fun onCleared() {
        // Fire an immediate async backup before the scope is cancelled
        if (preferences.autoBackupEnabled) {
            viewModelScope.launch(Dispatchers.IO) {
                com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
            }
        }
        autoBackupJob?.cancel()
        getApplication<Application>().contentResolver.unregisterContentObserver(mediaObserver)
        mediaObserverJob?.cancel()
        super.onCleared()
    }

    // ── Public entry point — cold start only ──────────────────────────────

    fun loadData(scrollToTop: Boolean = false) {
        viewModelScope.launch {
            // Show the spinner only when there is genuinely no data yet (first launch).
            // Every subsequent call triggered by an operation or the observer is silent.
            val isColdStart = _uiState.value.folders.isEmpty() &&
                              _uiState.value.videos.isEmpty()
            if (isColdStart) _uiState.update { it.copy(isLoading = true) }
            loadDataCore(scrollToTop)
        }
    }

    // ── Shared loading body ───────────────────────────────────────────────

    /**
     * Queries repositories and updates state.
     * Does NOT touch isLoading (callers decide that).
     * Always resets isInternalChange at the end so the ContentObserver can
     * fire again after a self-initiated operation completes.
     */
    private suspend fun loadDataCore(scrollToTop: Boolean = false) {
        val s = _uiState.value
        val videos = repository.getVideos(s.videoSortOption)

        val hiddenPaths = preferences.hiddenFolderPaths
        // Fetch ALL folders from MediaStore first (hidden ones are still there — app-local).
        // The custom order is applied and saved across ALL folders so hidden folder bucket IDs
        // are retained in the saved order — their slot is preserved on un-hide (Bug 1 fix).
        var allFolders = repository.getFolders(s.sortOption)

        if (s.sortOption == FolderSortOption.CUSTOM_ORDER) {
            val savedOrder = preferences.getCustomFolderOrder()
            if (savedOrder.isNotEmpty()) {
                val orderMap = savedOrder.withIndex().associate { (index, id) -> id to index }
                allFolders = allFolders.sortedBy { orderMap[it.bucketId] ?: Int.MAX_VALUE }
            }
            // Save order for ALL folders (including hidden) so positions are never lost
            preferences.saveCustomFolderOrder(allFolders.map { it.bucketId })
        }

        // Visible-only list used for the main view and group detail
        val folders = allFolders.filter { it.path.isBlank() || it.path !in hiddenPaths }

        // Load groups and split folders into grouped / ungrouped
        val rootGroups = groupRepository.getRootGroups()
        val groupedIds = groupRepository.getGroupedBucketIds()

        // allUngroupedFolders (including hidden) feeds applyCustomMixedOrder so hidden
        // folder keys stay in customMixedOrder; ungroupedFolders is the display list.
        val allUngroupedFolders = allFolders.filter { it.bucketId !in groupedIds }
        val ungroupedFolders    = allUngroupedFolders.filter { it.path.isBlank() || it.path !in hiddenPaths }

        // Lookup: bucketId → path, used to determine group visibility
        val bucketPathMap = allFolders.associate { it.bucketId to it.path }
        // A group is visible only when at least one of its member folders is not hidden
        fun isGroupVisible(group: GroupItem) = group.memberBucketIds.any { id ->
            val p = bucketPathMap[id]; p.isNullOrBlank() || p !in hiddenPaths
        }

        val orderedMixed = if (s.sortOption == FolderSortOption.CUSTOM_ORDER) {
            // Compute & save order using ALL ungrouped so positions survive hide/un-hide;
            // then strip the hidden entries from the list that's actually rendered.
            val withHidden = applyCustomMixedOrder(rootGroups, allUngroupedFolders)
            withHidden.filter { item ->
                when (item) {
                    is FolderItem -> item.path.isBlank() || item.path !in hiddenPaths
                    is GroupItem  -> isGroupVisible(item)
                    else          -> true
                }
            }
        } else {
            val visibleGroups = rootGroups.filter { isGroupVisible(it) }
            sortMixedItems(visibleGroups + ungroupedFolders, s.sortOption, s.groupsAlwaysOnTop)
        }

        _uiState.update {
            it.copy(
                videos               = videos,
                folders              = folders,
                rootGroups           = rootGroups,
                ungroupedFolders     = ungroupedFolders,
                orderedMixedItems    = orderedMixed,
                allGroupCustomOrders = preferences.allCustomGroupItemsOrders(),
                isLoading            = false,
                scrollToTopTrigger   = if (scrollToTop) it.scrollToTopTrigger + 1 else it.scrollToTopTrigger
            )
        }
        // Allow the ContentObserver to fire again after our refresh is complete
        isInternalChange.set(false)

        // If a group is open, keep its folder list in sync with the freshly filtered `folders`
        val openGroupId = _uiState.value.currentGroupId
        if (openGroupId != null) {
            val gBucketIds    = groupRepository.getFolderBucketIdsForGroup(openGroupId).toSet()
            val gAllSubGroups = groupRepository.getChildGroups(openGroupId)
            val gFolders      = folders.filter { it.bucketId in gBucketIds }
            // Hide sub-groups whose every direct album is hidden (same rule as root)
            val gSubGroups    = gAllSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || isGroupVisible(sub)
            }
            val gSortOpt   = _uiState.value.currentGroupSortOption
            val gOrdered   = if (gSortOpt == FolderSortOption.CUSTOM_ORDER)
                applyCustomGroupMixedOrder(openGroupId, gSubGroups, gFolders)
            else
                sortMixedItems(gSubGroups + gFolders, gSortOpt, _uiState.value.groupsAlwaysOnTop)
            _uiState.update {
                it.copy(
                    currentGroupFolders           = gFolders,
                    currentGroupSubGroups         = gSubGroups,
                    currentGroupOrderedMixedItems = gOrdered
                )
            }
        }
    }

    /**
     * Reload all data in the background WITHOUT showing any loading indicator.
     * Use this for every post-operation refresh (delete, move, rename, etc.).
     */
    private fun silentRefresh(scrollToTop: Boolean = false) {
        viewModelScope.launch { loadDataCore(scrollToTop) }
    }

    /** Reload the current folder's videos in-place (no spinner, no list flicker). */
    private fun refreshFolderVideos() {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        viewModelScope.launch {
            val sortOption = getEffectiveFolderSortOption(bucketId)
            val videos = repository.getVideos(sortOption, bucketId = bucketId)
            _uiState.update { it.copy(folderVideos = videos) }
        }
    }

    /** Restores the saved interleaved drag order; appends brand-new items at the end. */
    private fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val saved     = preferences.customMixedOrder
        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (saved.isEmpty()) {
            val initial: List<Any> = groups + folders
            preferences.customMixedOrder = initial.map {
                if (it is GroupItem) "g_${it.groupId}" else "f_${(it as FolderItem).bucketId}"
            }
            return initial
        }

        val ordered   = saved.mapNotNull { groupMap[it] ?: folderMap[it] }
        val savedSet  = saved.toSet()
        // New groups and folders are prepended at the beginning (now matches expected behavior)
        val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
        val result: List<Any> = newGroups + newFolders + ordered

        preferences.customMixedOrder = result.map {
            if (it is GroupItem) "g_${it.groupId}" else "f_${(it as FolderItem).bucketId}"
        }
        return result
    }

    private fun sortMixedItems(
        items: List<Any>,
        option: FolderSortOption,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        fun name(i: Any)  = if (i is GroupItem) i.name  else (i as FolderItem).name
        fun count(i: Any) = if (i is GroupItem) i.totalItemCount else (i as FolderItem).itemCount
        fun sortList(list: List<Any>): List<Any> = when (option) {
            FolderSortOption.NAME_A_TO_Z        -> list.sortedBy           { name(it).lowercase()  }
            FolderSortOption.NAME_Z_TO_A        -> list.sortedByDescending { name(it).lowercase()  }
            FolderSortOption.ITEMS_MOST_FIRST   -> list.sortedByDescending { count(it)             }
            FolderSortOption.ITEMS_FEWEST_FIRST -> list.sortedBy           { count(it)             }
            FolderSortOption.CUSTOM_ORDER       -> list
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
     * Sorts [groups] and [folders] for the Hide Folders screen using [sortOption].
     * For CUSTOM_ORDER, restores the saved drag order (read-only — nothing is persisted).
     * Returns (sortedGroups, sortedFolders) as separate lists matching the screen's contract.
     *
     * @param groupId Non-null → use the group's saved mixed order; null → use root mixed order.
     */
    private fun sortHideScreenItems(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        sortOption: FolderSortOption,
        groupsAlwaysOnTop: Boolean,
        groupId: Long?
    ): Pair<List<GroupItem>, List<FolderItem>> {
        if (sortOption == FolderSortOption.CUSTOM_ORDER) {
            val savedOrder = if (groupId != null)
                preferences.getGroupMixedOrder(groupId)
            else
                preferences.customMixedOrder
            if (savedOrder.isEmpty()) return groups to folders
            val groupMap  = groups.associateBy  { "g_${it.groupId}"  }
            val folderMap = folders.associateBy { "f_${it.bucketId}" }
            val savedSet  = savedOrder.toSet()
            val newGroups  = groups.filter  { "g_${it.groupId}"  !in savedSet }
            val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
            val ordered    = savedOrder.mapNotNull { groupMap[it] ?: folderMap[it] }
            val result     = newGroups + newFolders + ordered
            return result.filterIsInstance<GroupItem>() to result.filterIsInstance<FolderItem>()
        }
        val sorted = sortMixedItems(groups + folders, sortOption, groupsAlwaysOnTop)
        return sorted.filterIsInstance<GroupItem>() to sorted.filterIsInstance<FolderItem>()
    }

    fun selectTab(tab: Int) { preferences.selectedTab = tab; _uiState.update { it.copy(selectedTab = tab) } }

    /**
     * Move a mixed item (folder or group) in the ordered list during drag.
     * [fromIndex] and [toIndex] are **0-based data indices** into [VideoListUiState.orderedMixedItems]
     * (the header offset is already subtracted by the caller in FoldersTab).
     * Called many times per second while dragging; does not write to disk.
     */
    fun reorderMixedItem(fromIndex: Int, toIndex: Int) {
        _uiState.update { state ->
            val mixed = state.orderedMixedItems.toMutableList()
            if (fromIndex < 0 || toIndex < 0 ||
                fromIndex >= mixed.size || toIndex >= mixed.size) return@update state
            val item = mixed.removeAt(fromIndex)
            mixed.add(toIndex, item)
            state.copy(orderedMixedItems = mixed)
        }
    }

    /** Persist the current mixed order to preferences (called when drag ends). */
    fun persistFolderOrder() {
        val state = _uiState.value
        preferences.customMixedOrder = state.orderedMixedItems.map {
            if (it is GroupItem) "g_${it.groupId}" else "f_${(it as FolderItem).bucketId}"
        }
        preferences.saveCustomFolderOrder(state.ungroupedFolders.map { it.bucketId })
        preferences.customGroupOrder = state.rootGroups.map { it.groupId }
        scheduleAutoBackup()
    }

    // ── Group navigation (stack-based) ────────────────────────────────

    fun openGroup(groupId: Long, name: String) {
        val s = _uiState.value
        val newStack = if (s.currentGroupId != null)
            s.groupStack + (s.currentGroupId to s.currentGroupName)
        else s.groupStack
        // Load the persisted sort for this group (defaults to CUSTOM_ORDER if not yet set)
        val groupSort = preferences.getGroupSortOption(groupId)
        _uiState.update {
            it.copy(
                currentGroupId      = groupId,
                currentGroupName    = name,
                groupStack          = newStack,
                isSelectionMode     = false,
                selectedFolderIds   = emptySet(),
                selectedGroupIds    = emptySet(),
                currentGroupSortOption = groupSort
            )
        }
        refreshCurrentGroup()
    }

    fun closeGroup() {
        val s = _uiState.value
        if (s.groupStack.isNotEmpty()) {
            val (prevId, prevName) = s.groupStack.last()
            val parentSort = preferences.getGroupSortOption(prevId)
            _uiState.update {
                it.copy(
                    currentGroupId     = prevId,
                    currentGroupName   = prevName,
                    groupStack         = s.groupStack.dropLast(1),
                    isSelectionMode    = false,
                    selectedFolderIds  = emptySet(),
                    selectedGroupIds   = emptySet(),
                    currentGroupSortOption = parentSort
                )
            }
            refreshCurrentGroup()
        } else {
            _uiState.update {
                it.copy(
                    currentGroupId        = null,
                    currentGroupName      = "",
                    currentGroupFolders   = emptyList(),
                    currentGroupSubGroups = emptyList(),
                    groupStack            = emptyList(),
                    isSelectionMode       = false,
                    selectedFolderIds     = emptySet(),
                    selectedGroupIds      = emptySet(),
                    currentGroupSortOption = FolderSortOption.CUSTOM_ORDER
                )
            }
        }
    }

    private fun refreshCurrentGroup() {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            val s         = _uiState.value
            val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
            val allSubGroups = groupRepository.getChildGroups(groupId)
            // Filter from the globally-sorted folders list so non-custom sorts display correctly
            val bucketIdSet = bucketIds.toSet()
            val folders = s.folders.filter { it.bucketId in bucketIdSet }
            // Hide sub-groups whose every direct album is hidden
            val visibleBucketSet = s.folders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            // Use the group's own independent sort option
            val groupSortOption = s.currentGroupSortOption
            val orderedMixed = if (groupSortOption == FolderSortOption.CUSTOM_ORDER) {
                applyCustomGroupMixedOrder(groupId, subGroups, folders)
            } else {
                sortMixedItems(subGroups + folders, groupSortOption, s.groupsAlwaysOnTop)
            }
            _uiState.update {
                it.copy(
                    currentGroupFolders           = folders,
                    currentGroupSubGroups         = subGroups,
                    currentGroupOrderedMixedItems = orderedMixed
                )
            }
        }
    }

    /**
     * Restores the saved drag order for items inside a specific group.
     * New items (sub-groups or folders) are prepended so they always appear at the top.
     * Deleted items are dropped.
     */
    private fun applyCustomGroupMixedOrder(
        groupId: Long,
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val saved     = preferences.getGroupMixedOrder(groupId)
        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (saved.isEmpty()) return groups + folders

        val ordered    = saved.mapNotNull { groupMap[it] ?: folderMap[it] }
        val savedSet   = saved.toSet()
        val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
        // New items are prepended so they always appear at the top
        return newGroups + newFolders + ordered
    }

    /**
     * Move a mixed item inside the current group during a drag.
     * [fromIndex] / [toIndex] are 0-based into [currentGroupOrderedMixedItems].
     * Called many times per second while dragging; does NOT write to disk.
     */
    fun reorderGroupItem(fromIndex: Int, toIndex: Int) {
        _uiState.update { state ->
            val mixed = state.currentGroupOrderedMixedItems.toMutableList()
            if (fromIndex < 0 || toIndex < 0 ||
                fromIndex >= mixed.size || toIndex >= mixed.size) return@update state
            val item = mixed.removeAt(fromIndex)
            mixed.add(toIndex, item)
            state.copy(currentGroupOrderedMixedItems = mixed)
        }
    }

    /** Persist the current group item order to preferences (called when drag ends). */
    fun persistGroupOrder() {
        val s = _uiState.value
        val groupId = s.currentGroupId ?: return
        val order = s.currentGroupOrderedMixedItems.map {
            if (it is GroupItem) "g_${it.groupId}" else "f_${(it as FolderItem).bucketId}"
        }
        preferences.saveGroupMixedOrder(groupId, order)
        scheduleAutoBackup()
    }

    // ── Group creation — Flow 1 (Group Creation Mode) ─────────────────

    fun enterGroupCreationMode() {
        _uiState.update {
            it.copy(
                isGroupCreationMode            = true,
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds  = emptySet()
            )
        }
    }

    fun exitGroupCreationMode() {
        _uiState.update {
            it.copy(
                isGroupCreationMode            = false,
                pendingGroupCreationName       = "",
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds  = emptySet(),
                showGroupNameDialog            = false,
                groupNameDialogForCreation     = false
            )
        }
    }

    /** Opens the name dialog upfront (before any grid selection). */
    fun showGroupNameForCreation() {
        viewModelScope.launch {
            val allNames = groupRepository.getAllGroups().map { it.name }.toSet()
            val suggested = generateUniqueGroupName(allNames)
            _uiState.update {
                it.copy(
                    showGroupNameDialog        = true,
                    groupNameDialogForCreation = true,
                    existingGroupNames         = allNames,
                    suggestedGroupName         = suggested
                )
            }
        }
    }

    /** Transitions from name dialog → checkbox selection mode. */
    fun enterGroupCreationModeWithName(name: String) {
        _uiState.update {
            it.copy(
                showGroupNameDialog            = false,
                groupNameDialogForCreation     = false,
                isGroupCreationMode            = true,
                pendingGroupCreationName       = name,
                groupCreationSelectedFolderIds = emptySet(),
                groupCreationSelectedGroupIds  = emptySet()
            )
        }
    }

    private fun generateUniqueGroupName(existingNames: Set<String>): String {
        val lower = existingNames.map { it.lowercase() }
        var counter = 1
        while (lower.contains("group $counter")) counter++
        return "Group $counter"
    }

    fun toggleGroupCreationFolderSelection(bucketId: Int) {
        _uiState.update {
            val s = if (bucketId in it.groupCreationSelectedFolderIds)
                it.groupCreationSelectedFolderIds - bucketId
            else
                it.groupCreationSelectedFolderIds + bucketId
            it.copy(groupCreationSelectedFolderIds = s)
        }
    }

    fun toggleGroupCreationGroupSelection(groupId: Long) {
        _uiState.update {
            val s = if (groupId in it.groupCreationSelectedGroupIds)
                it.groupCreationSelectedGroupIds - groupId
            else
                it.groupCreationSelectedGroupIds + groupId
            it.copy(groupCreationSelectedGroupIds = s)
        }
    }

    fun showGroupNameDialog() {
        val pending = _uiState.value.pendingGroupCreationName
        if (pending.isNotBlank()) {
            createGroupFromCreationMode(pending)
        } else {
            _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForBottomBar = false) }
        }
    }

    fun showGroupNameDialogForBottomBar() {
        _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForBottomBar = true) }
    }

    fun dismissGroupNameDialog() {
        _uiState.update {
            it.copy(showGroupNameDialog = false, groupNameDialogForCreation = false)
        }
    }

    fun createGroupFromCreationMode(name: String) {
        val s = _uiState.value
        viewModelScope.launch {
            groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.groupCreationSelectedFolderIds.toList(),
                subGroupIds     = s.groupCreationSelectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            exitGroupCreationMode()
            silentRefresh()
            if (s.currentGroupId != null) refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    // ── Group creation — Flow 2 (Selection mode → bottom bar) ─────────

    fun createGroupFromSelection(name: String) {
        val s = _uiState.value
        viewModelScope.launch {
            groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.selectedFolderIds.toList(),
                subGroupIds     = s.selectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            _uiState.update { it.copy(showGroupNameDialog = false) }
            exitSelectionMode()
            silentRefresh()
            if (s.currentGroupId != null) refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    // ── Group actions ─────────────────────────────────────────────────

    fun showRenameGroupDialog() = _uiState.update { it.copy(showRenameGroupDialog = true) }
    fun dismissRenameGroupDialog() = _uiState.update { it.copy(showRenameGroupDialog = false) }

    fun renameCurrentGroup(newName: String) {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            groupRepository.renameGroup(groupId, newName)
            _uiState.update { it.copy(currentGroupName = newName, showRenameGroupDialog = false) }
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
            _uiState.update { it.copy(showDestroyGroupDialog = false) }
            closeGroup()
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    fun showAddFolderToGroup() = _uiState.update { it.copy(showAddFolderToGroup = true) }
    fun dismissAddFolderToGroup() = _uiState.update { it.copy(showAddFolderToGroup = false) }

    fun addFoldersToCurrentGroup(folderBucketIds: Set<Int>, subGroupIds: Set<Long>) {
        val groupId = _uiState.value.currentGroupId ?: return
        viewModelScope.launch {
            if (folderBucketIds.isNotEmpty())
                groupRepository.addFoldersToGroup(groupId, folderBucketIds.toList())
            if (subGroupIds.isNotEmpty())
                groupRepository.addSubGroupsToGroup(groupId, subGroupIds.toList())
            _uiState.update { it.copy(showAddFolderToGroup = false) }
            silentRefresh()
            refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    /** Removes selected folders from the current group; destroys selected sub-groups. */
    fun removeSelectedFromGroup() {
        val s = _uiState.value
        viewModelScope.launch {
            groupRepository.moveItemsToGroup(
                folderBucketIds = s.selectedFolderIds.toList(),
                groupIds        = emptyList(),
                targetGroupId   = null
            )
            s.selectedGroupIds.forEach { groupRepository.destroyGroup(it) }
            exitSelectionMode()
            silentRefresh()
            refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    fun ungroupSelectedGroups() {
        val s = _uiState.value
        viewModelScope.launch {
            s.selectedGroupIds.forEach { groupRepository.destroyGroup(it) }
            exitSelectionMode()
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    fun toggleGroupSelection(groupId: Long) {
        _uiState.update {
            val newSet = if (groupId in it.selectedGroupIds)
                it.selectedGroupIds - groupId else it.selectedGroupIds + groupId
            it.copy(selectedGroupIds = newSet, isSelectionMode = true)
        }
    }

    fun showMoveToGroupPicker() {
        val s = _uiState.value
        _uiState.update {
            it.copy(
                showMoveToGroupPicker = true,
                moveToGroupFolderIds  = s.selectedFolderIds,
                moveToGroupGroupIds   = s.selectedGroupIds
            )
        }
    }

    fun dismissMoveToGroupPicker() = _uiState.update { it.copy(showMoveToGroupPicker = false) }

    fun moveSelectionToGroup(targetGroupId: Long?) {
        val s = _uiState.value
        viewModelScope.launch {
            groupRepository.moveItemsToGroup(
                folderBucketIds = s.moveToGroupFolderIds.toList(),
                groupIds        = s.moveToGroupGroupIds.toList(),
                targetGroupId   = targetGroupId
            )
            _uiState.update { it.copy(showMoveToGroupPicker = false) }
            exitSelectionMode()
            silentRefresh()
            if (s.currentGroupId != null) refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    fun createGroupAndMoveSelection(name: String) {
        val s = _uiState.value
        viewModelScope.launch {
            groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.selectedFolderIds.toList(),
                subGroupIds     = s.selectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            _uiState.update { it.copy(showGroupNameDialog = false) }
            exitSelectionMode()
            silentRefresh()
            if (s.currentGroupId != null) refreshCurrentGroup()
            scheduleAutoBackup()
        }
    }

    fun selectAllInGroup() {
        val s = _uiState.value
        _uiState.update {
            it.copy(
                selectedFolderIds = s.currentGroupFolders.map { f -> f.bucketId }.toSet(),
                selectedGroupIds  = s.currentGroupSubGroups.map { g -> g.groupId }.toSet()
            )
        }
    }

    fun selectAllFoldersAndGroups() {
        val s = _uiState.value
        _uiState.update {
            it.copy(
                selectedFolderIds = s.ungroupedFolders.map { f -> f.bucketId }.toSet(),
                selectedGroupIds  = s.rootGroups.map { g -> g.groupId }.toSet()
            )
        }
    }
    fun setViewType(v: ViewType) { preferences.viewType = v; _uiState.update { it.copy(viewType = v) }; scheduleAutoBackup() }
    fun cycleViewType() {
        val next = when (_uiState.value.viewType) {
            ViewType.LIST -> ViewType.GRID_LARGE
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            ViewType.GRID_SMALL -> ViewType.LIST
        }
        setViewType(next)
    }
    fun setFolderViewType(v: ViewType) { preferences.folderViewType = v; _uiState.update { it.copy(folderViewType = v) }; scheduleAutoBackup() }
    fun cycleFolderViewType() {
        val next = when (_uiState.value.folderViewType) {
            ViewType.LIST -> ViewType.GRID_LARGE
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            ViewType.GRID_SMALL -> ViewType.LIST
        }
        setFolderViewType(next)
    }
    /** Folder tab sort (Custom, Name, Item count). */
    fun setSortOption(s: FolderSortOption) { preferences.folderSortOption = s; _uiState.update { it.copy(sortOption = s) }; silentRefresh(scrollToTop = true); scheduleAutoBackup() }

    /** Videos tab sort (Custom, Name, Date created, Date modified). */
    fun setVideoSortOption(s: VideoSortOption) { preferences.videoSortOption = s; _uiState.update { it.copy(videoSortOption = s) }; silentRefresh(scrollToTop = true); scheduleAutoBackup() }

    /** Change the sort for the currently open folder (independent of the main tab sort). */
    fun setFolderSortOption(s: VideoSortOption) {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        if (preferences.independentSortEnabled) {
            preferences.saveFolderVideoSortOption(bucketId, s)
        } else {
            preferences.videoSortOption = s
        }
        _uiState.update {
            it.copy(
                currentFolderSortOption = s,
                folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
            )
        }
        viewModelScope.launch {
            val videos = repository.getVideos(videoSortOption = s, bucketId = bucketId)
            _uiState.update { it.copy(folderVideos = videos) }
        }
        scheduleAutoBackup()
    }

    // Selection mode
    fun enterSelectionMode() { _uiState.update { it.copy(isSelectionMode = true) } }
    fun exitSelectionMode() { _uiState.update { it.copy(isSelectionMode = false, selectedVideoIds = emptySet(), selectedFolderIds = emptySet(), selectedGroupIds = emptySet()) } }
    fun toggleVideoSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.selectedVideoIds.toMutableSet()
            if (sel.contains(id)) {
                sel.remove(id)
            } else {
                if (sel.size >= 1000) return@update s   // cap: silently ignore
                sel.add(id)
            }
            s.copy(selectedVideoIds = sel)
        }
    }
    fun toggleFolderSelection(bucketId: Int) {
        _uiState.update {
            val newSet = if (bucketId in it.selectedFolderIds) it.selectedFolderIds - bucketId else it.selectedFolderIds + bucketId
            it.copy(selectedFolderIds = newSet, isSelectionMode = true)
        }
    }
    fun selectAllVideos() { _uiState.update { it.copy(selectedVideoIds = it.videos.map { v -> v.id }.toSet()) } }
    fun deselectAllVideos() { _uiState.update { it.copy(selectedVideoIds = emptySet()) } }
    fun selectAllFolders() { _uiState.update { it.copy(selectedFolderIds = it.folders.map { f -> f.bucketId }.toSet()) } }
    fun deselectAllFolders() { _uiState.update { it.copy(selectedFolderIds = emptySet(), selectedGroupIds = emptySet()) } }

    // Search
    fun activateSearch() { _uiState.update { it.copy(isSearchActive = true) } }
    fun deactivateSearch() { _uiState.update { it.copy(isSearchActive = false, searchQuery = "", searchResults = emptyList()) } }
    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            val results = if (query.isBlank()) emptyList() else repository.getVideos(searchQuery = query)
            _uiState.update { it.copy(searchResults = results) }
        }
    }

    // Share
    fun shareSelectedVideos() {
        viewModelScope.launch {
            val s = _uiState.value
            val uris = s.folderVideos
                .filter { it.id in s.selectedVideoIds }
                .map { it.contentUri }
            if (uris.isEmpty()) return@launch
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                type = "video/*"
                putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(uris))
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            _shareIntent.emit(intent)
        }
    }

    fun shareSelectedFolders() {
        viewModelScope.launch {
            val s = _uiState.value
            val uris = ArrayList<android.net.Uri>()
            for (bucketId in s.selectedFolderIds) {
                repository.getVideos(bucketId = bucketId).mapTo(uris) { it.contentUri }
            }
            for (groupId in s.selectedGroupIds) {
                val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
                for (bucketId in bucketIds) {
                    repository.getVideos(bucketId = bucketId).mapTo(uris) { it.contentUri }
                }
            }
            if (uris.isEmpty()) return@launch
            val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                type = "video/*"
                putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            _shareIntent.emit(intent)
        }
    }

    // Folder navigation
    fun openFolder(bucketId: Int, name: String) {
        viewModelScope.launch {
            val folderSort = getEffectiveFolderSortOption(bucketId)
            val videos = repository.getVideos(videoSortOption = folderSort, bucketId = bucketId)
            _uiState.update {
                it.copy(
                    currentFolderBucketId = bucketId,
                    currentFolderName = name,
                    currentFolderSortOption = folderSort,
                    folderVideos = videos
                )
            }
        }
    }
    fun closeFolder() { _uiState.update { it.copy(currentFolderBucketId = null, currentFolderName = "", currentFolderSortOption = VideoSortOption.CUSTOM_ORDER, folderVideos = emptyList()) } }
    private fun refreshCurrentFolderIfOpen() {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        viewModelScope.launch {
            val s = _uiState.value
            val videos = repository.getVideos(videoSortOption = s.currentFolderSortOption, bucketId = bucketId)
            _uiState.update { it.copy(folderVideos = videos) }
        }
    }

    fun deleteSelectedVideos() {
        val idsToDelete = _uiState.value.selectedVideoIds

        // ① Optimistic removal — Compose's animateItem plays the exit animation immediately.
        _uiState.update { s ->
            s.copy(
                folderVideos     = s.folderVideos.filter { it.id !in idsToDelete },
                videos           = s.videos.filter       { it.id !in idsToDelete },
                isSelectionMode  = false,
                selectedVideoIds = emptySet()
            )
        }

        viewModelScope.launch {
            isInternalChange.set(true)        // ② suppress the ContentObserver callback
            repository.deleteVideos(idsToDelete.toList())
            silentRefresh()                   // ③ reconcile with MediaStore, no spinner
            refreshCurrentFolderIfOpen()
        }
    }

    fun deleteSelectedFolders() {
        val idsToDelete = _uiState.value.selectedFolderIds

        // ① Optimistic removal — remove from every list driving the UI right now.
        _uiState.update { s ->
            s.copy(
                orderedMixedItems = s.orderedMixedItems.filter { item ->
                    item !is FolderItem || item.bucketId !in idsToDelete
                },
                folders           = s.folders.filter          { it.bucketId !in idsToDelete },
                ungroupedFolders  = s.ungroupedFolders.filter { it.bucketId !in idsToDelete },
                isSelectionMode   = false,
                selectedFolderIds = emptySet()
            )
        }

        viewModelScope.launch {
            isInternalChange.set(true)        // ② suppress the ContentObserver callback
            for (folderId in idsToDelete) {
                val videos = repository.getVideos(bucketId = folderId)
                repository.deleteVideos(videos.map { it.id })
            }
            silentRefresh()                   // ③ reconcile with MediaStore, no spinner
        }
    }

    fun renameVideo(id: Long, name: String) {
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.renameVideo(id, name)
            silentRefresh()
            refreshCurrentFolderIfOpen()
        }
    }

    private fun getSelectedVideos(): List<VideoItem> {
        val state = _uiState.value
        return state.selectedVideoIds.mapNotNull { id ->
            state.folderVideos.find { it.id == id } ?: state.videos.find { it.id == id }
        }
    }

    /** Returns the parent folder path of the first selected video, or the folder path if a folder is selected. */
    fun getSelectedLocationPath(): String? {
        val state = _uiState.value
        if (state.selectedTab == 0) {
            // Videos tab — get the parent directory of the first selected video
            val video = state.selectedVideoIds.firstOrNull()?.let { id ->
                state.folderVideos.find { it.id == id } ?: state.videos.find { it.id == id }
            }
            return video?.path?.let { java.io.File(it).parent }
        } else {
            // Folders tab — get the folder path directly
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
        // If user already chose a bulk resolution, apply immediately without showing dialog
        bulkResolution?.let { return it }
        val deferred = CompletableDeferred<ConflictResolution>()
        _fileConflict.value = FileConflict(fileName, deferred)
        val resolution = deferred.await()
        if (resolution == ConflictResolution.SKIP_ALL || resolution == ConflictResolution.REPLACE_ALL) {
            bulkResolution = resolution
        }
        return resolution
    }

    fun moveSelectedVideos(dest: String) {
        val videos = getSelectedVideos()
        if (videos.isEmpty()) return
        _uiState.update { it.copy(showMoveFolderPicker = false) }
        exitSelectionMode()

        val folderName = destFolderName(dest)
        copyMoveCancelled = false
        bulkResolution = null
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Moving items to $folderName…", current = 0, total = videos.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            repository.moveVideos(
                videos, dest,
                onProgress = { current: Int, total: Int ->
                    _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total)
                },
                isCancelled = { copyMoveCancelled },
                onConflict = ::askConflictResolution
            )
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = videos.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderVideos()
        }
    }

    fun copySelectedVideos(dest: String) {
        val videos = getSelectedVideos()
        if (videos.isEmpty()) return
        _uiState.update { it.copy(showCopyFolderPicker = false) }
        exitSelectionMode()

        val folderName = destFolderName(dest)
        copyMoveCancelled = false
        bulkResolution = null
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Copying items to $folderName…", current = 0, total = videos.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            repository.copyVideos(
                videos, dest,
                onProgress = { current: Int, total: Int ->
                    _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total)
                },
                isCancelled = { copyMoveCancelled },
                onConflict = ::askConflictResolution
            )
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = videos.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderVideos()
        }
    }

    fun createFolder(name: String) {
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.createFolder(name)
            silentRefresh()
        }
    }

    fun createFolderAndMoveVideos(folderName: String) {
        val videos = getSelectedVideos()
        if (videos.isEmpty()) return
        _uiState.update { it.copy(showMoveFolderPicker = false) }
        exitSelectionMode()

        copyMoveCancelled = false
        bulkResolution = null
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Moving items to $folderName…", current = 0, total = videos.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                repository.moveVideos(
                    videos, path,
                    onProgress = { current: Int, total: Int -> _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total) },
                    isCancelled = { copyMoveCancelled },
                    onConflict = ::askConflictResolution
                )
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = videos.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderVideos()
        }
    }

    fun createFolderAndCopyVideos(folderName: String) {
        val videos = getSelectedVideos()
        if (videos.isEmpty()) return
        _uiState.update { it.copy(showCopyFolderPicker = false) }
        exitSelectionMode()

        copyMoveCancelled = false
        bulkResolution = null
        _copyMoveProgress.value = CopyMoveProgress(isActive = true, title = "Copying items to $folderName…", current = 0, total = videos.size)

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                repository.copyVideos(
                    videos, path,
                    onProgress = { current: Int, total: Int -> _copyMoveProgress.value = _copyMoveProgress.value.copy(current = current, total = total) },
                    isCancelled = { copyMoveCancelled },
                    onConflict = ::askConflictResolution
                )
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = videos.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            refreshFolderVideos()
        }
    }

    // Details dialog
    fun showVideoDetails(video: VideoItem) = _uiState.update { it.copy(showDetailsDialog = true, detailsTarget = video) }
    fun dismissVideoDetails() = _uiState.update { it.copy(showDetailsDialog = false, detailsTarget = null) }

    fun showDetailsForSelectedVideo() {
        val s = _uiState.value
        if (s.selectedVideoIds.isEmpty()) return
        val selectedId = s.selectedVideoIds.first()
        val video = s.folderVideos.find { it.id == selectedId }
            ?: s.videos.find { it.id == selectedId }
        video?.let { showVideoDetails(it) }
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
    fun showMoveFolderPicker() { Log.d("VideoVM", "showMoveFolderPicker: selectedIds=${_uiState.value.selectedVideoIds}"); _uiState.update { it.copy(showMoveFolderPicker = true) } }
    fun dismissMoveFolderPicker() = _uiState.update { it.copy(showMoveFolderPicker = false) }
    fun showCopyFolderPicker() { Log.d("VideoVM", "showCopyFolderPicker: selectedIds=${_uiState.value.selectedVideoIds}"); _uiState.update { it.copy(showCopyFolderPicker = true) } }
    fun dismissCopyFolderPicker() = _uiState.update { it.copy(showCopyFolderPicker = false) }
    fun showAbout() = _uiState.update { it.copy(showAbout = true) }
    fun dismissAbout() = _uiState.update { it.copy(showAbout = false) }

    // ── Settings ──────────────────────────────────────────────────────

    fun showSettings()    = _uiState.update { it.copy(showSettings = true) }
    fun dismissSettings() = _uiState.update { it.copy(showSettings = false) }

    // ── Instant Player toggle ─────────────────────────────────────────

    fun updateInstantPlayerEnabled(value: Boolean) {
        preferences.instantPlayerEnabled = value
        _uiState.update { it.copy(instantPlayerEnabled = value) }
        scheduleAutoBackup()
    }

    // ── Auto-backup ───────────────────────────────────────────────────

    fun updateAutoBackupEnabled(value: Boolean) {
        preferences.autoBackupEnabled = value
        _uiState.update { it.copy(autoBackupEnabled = value) }
    }

    /**
     * Cancels any pending backup and schedules a new one to fire after
     * AUTO_BACKUP_DEBOUNCE_MS on the IO dispatcher.
     * No-op when auto-backup is disabled or a restore is in progress.
     */
    fun scheduleAutoBackup() {
        if (!preferences.autoBackupEnabled || isRestoringBackup) return
        autoBackupJob?.cancel()
        autoBackupJob = viewModelScope.launch {
            delay(AUTO_BACKUP_DEBOUNCE_MS)
            withContext(Dispatchers.IO) {
                com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
            }
        }
    }

    /**
     * Cancel the debounce and immediately fire an async backup.
     * Called from MainActivity.onStop() to cover backgrounding and idle.
     */
    fun onAppBackground() {
        if (!preferences.autoBackupEnabled) return
        autoBackupJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
        }
    }

    /** Save backup JSON to Documents/VideoLibrary/backups/backup.json — runs on IO. */
    suspend fun saveBackupToFile(): Boolean = withContext(Dispatchers.IO) {
        com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
    }

    /**
     * Restore from Documents/VideoLibrary/backups/backup.json.
     * Suspends until [loadDataCore] finishes so the caller only receives `true`
     * once the list is fully settled — hidden albums and custom positions are
     * already in their final state before navigation occurs.
     */
    suspend fun restoreBackupFromFile(): Boolean {
        isRestoringBackup = true
        val ok = withContext(Dispatchers.IO) {
            com.videolibrary.data.util.BackupManager.restoreBackupFromFile(getApplication())
        }
        if (ok) {
            isRestoringBackup = false
            _uiState.update {
                it.copy(
                    viewType             = preferences.viewType,
                    folderViewType       = preferences.folderViewType,
                    sortOption           = preferences.folderSortOption,
                    videoSortOption      = preferences.videoSortOption,
                    instantPlayerEnabled = preferences.instantPlayerEnabled,
                    autoBackupEnabled    = preferences.autoBackupEnabled
                )
            }
            // Await full reload so the UI is settled before the caller navigates away
            loadDataCore()
        } else {
            isRestoringBackup = false
        }
        return ok
    }


    // ── Create Album flow ─────────────────────────────────────────────────────

    /** Step 1 – show the name dialog, pre-loaded with existing DCIM folder names. */
    fun showCreateAlbumDialog() {
        viewModelScope.launch {
            val dcimNames = repository.getExistingDcimFolderNames()
            _uiState.update { it.copy(showCreateAlbumDialog = true, dcimFolderNames = dcimNames) }
        }
    }

    fun dismissCreateAlbumDialog() =
        _uiState.update { it.copy(showCreateAlbumDialog = false) }

    /** Step 2 – open the full-screen picker after the user confirmed a name. */
    fun startCreateAlbumPicker(name: String) {
        _uiState.update {
            it.copy(
                showCreateAlbumDialog = false,
                showCreateAlbumPicker = true,
                pendingAlbumName = name,
                albumCreationSelectedVideoIds = emptySet(),
                albumCreationBrowsedVideos = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }
    }

    /** Called when the user taps a folder inside the picker. */
    fun loadAlbumCreationVideos(bucketId: Int, name: String) {
        _uiState.update {
            it.copy(albumCreationCurrentBucketId = bucketId, albumCreationCurrentBucketName = name)
        }
        viewModelScope.launch {
            val sortOption = getEffectiveFolderSortOption(bucketId)
            val videos = repository.getVideos(sortOption, bucketId = bucketId)
            _uiState.update { it.copy(albumCreationBrowsedVideos = videos) }
        }
    }

    /** Called when the user presses back from inside a folder in the picker. */
    fun closeAlbumCreationFolder() {
        _uiState.update {
            it.copy(
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = "",
                albumCreationBrowsedVideos = emptyList()
            )
        }
    }

    /** Toggle a video's selection in the picker (max 500 enforced). */
    fun toggleAlbumCreationVideoSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.albumCreationSelectedVideoIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else {
                if (sel.size >= 500) return@update s
                sel.add(id)
            }
            s.copy(albumCreationSelectedVideoIds = sel)
        }
    }

    /** Step 3 – show the copy/move choice dialog. */
    fun showCreateAlbumCopyMoveDialog() =
        _uiState.update { it.copy(showCreateAlbumCopyMoveDialog = true) }

    fun dismissCreateAlbumCopyMoveDialog() =
        _uiState.update { it.copy(showCreateAlbumCopyMoveDialog = false) }

    /** Cancel the entire Create Album flow. */
    fun cancelAlbumCreation() {
        _uiState.update {
            it.copy(
                showCreateAlbumPicker = false,
                showCreateAlbumCopyMoveDialog = false,
                pendingAlbumName = "",
                albumCreationSelectedVideoIds = emptySet(),
                albumCreationBrowsedVideos = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }
    }

    /** Step 4 – create the folder and copy or move the selected videos into it. */
    fun confirmAlbumCreation(copy: Boolean) {
        val s = _uiState.value
        val videoIds = s.albumCreationSelectedVideoIds
        val folderName = s.pendingAlbumName
        if (videoIds.isEmpty() || folderName.isBlank()) return

        val videos = s.videos.filter { it.id in videoIds }

        _uiState.update {
            it.copy(
                showCreateAlbumPicker = false,
                showCreateAlbumCopyMoveDialog = false,
                pendingAlbumName = "",
                albumCreationSelectedVideoIds = emptySet(),
                albumCreationBrowsedVideos = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }

        copyMoveCancelled = false
        bulkResolution = null
        val verb = if (copy) "Copying" else "Moving"
        _copyMoveProgress.value = CopyMoveProgress(
            isActive = true,
            title = "$verb items to \"$folderName\"…",
            current = 0,
            total = videos.size
        )

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                if (copy) {
                    repository.copyVideos(
                        videos, path,
                        onProgress = { cur, tot ->
                            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = cur, total = tot)
                        },
                        isCancelled = { copyMoveCancelled },
                        onConflict = { fileName -> askConflictResolution(fileName) }
                    )
                } else {
                    repository.moveVideos(
                        videos, path,
                        onProgress = { cur, tot ->
                            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = cur, total = tot)
                        },
                        isCancelled = { copyMoveCancelled },
                        onConflict = { fileName -> askConflictResolution(fileName) }
                    )
                }
            }
            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = videos.size)
            delay(400)
            _copyMoveProgress.value = CopyMoveProgress()
            silentRefresh()
            scheduleAutoBackup()
        }
    }

    fun playVideo(ctx: android.content.Context, video: VideoItem) {
        try { ctx.startActivity(Intent(Intent.ACTION_VIEW).apply { setDataAndType(video.contentUri, video.mimeType); addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) }) }
        catch (_: Exception) { Toast.makeText(ctx, "Unable to play video.", Toast.LENGTH_SHORT).show() }
    }

    /** Returns the correct sort option for a folder (album) based on independentSortEnabled. */
    private fun getEffectiveFolderSortOption(bucketId: Int): VideoSortOption {
        return if (preferences.independentSortEnabled) {
            preferences.getFolderVideoSortOption(bucketId)
        } else {
            preferences.videoSortOption
        }
    }
}
