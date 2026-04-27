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
import com.example.common.data.model.CopyMoveProgress
import com.example.common.data.model.FileConflict
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.util.MixedItemSorter
import com.example.common.util.FilePathUtils
import com.example.common.util.GroupMixedOrderUtil
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
    /** When true, use Samsung Gallery-style floating top bar with full-screen content. */
    val floatingTopBarEnabled: Boolean = false,
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
    /** Pre-calculated hidden state for root groups (includes nested descendants). */
    val groupHiddenStateForHideScreen: Map<Long, Boolean> = emptyMap(),
    /** Pre-calculated hidden state for sub-groups in hide screen (includes nested descendants). */
    val groupSubGroupHiddenStateForHideScreen: Map<Long, Boolean> = emptyMap(),
    val renameTarget: VideoItem? = null,
    val showRenameAlbumDialog: Boolean = false,
    val renameAlbumTarget: FolderItem? = null,
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

    /** Per-group sort options (map of groupId to FolderSortOption.id), forwarded to
     *  FolderPickerScreen so the picker respects each group's sort preference. */
    val allGroupSortOptions: Map<Long, Int> = emptyMap(),

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

// CopyMoveProgress and FileConflict moved to common module

class VideoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VideoRepository(application)
    val preferences = AppPreferences(application)
    private val groupStore = GroupStore(application)
    private val groupRepository = GroupRepository(groupStore, repository, preferences)
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
            floatingTopBarEnabled = preferences.floatingTopBarEnabled,
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

    fun updateFloatingTopBarEnabled(value: Boolean) {
        preferences.floatingTopBarEnabled = value
        _uiState.update { it.copy(floatingTopBarEnabled = value) }
        scheduleAutoBackup()
    }

    // ── Hide Folders ───────────────────────────────────────────────────────

    fun showHideFoldersScreen() {
        val s = _uiState.value
        viewModelScope.launch {
            val mediaStoreFolders = repository.getFoldersWithIndependentSort(
                folderSortOption = s.sortOption,
                independentSortEnabled = s.independentSortEnabled,
                getFolderSortOption = { bucketId -> getEffectiveFolderSortOption(bucketId) }
            )
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

            // Calculate hidden state for each group (including nested descendants)
            val groupHiddenState = sortedGroups.associate { group ->
                val allBucketIds = groupRepository.getAllDescendantBucketIds(group.groupId)
                val paths = allFolders
                    .filter { it.bucketId in allBucketIds }
                    .map { it.path }
                    .filter { it.isNotBlank() }
                group.groupId to (paths.isNotEmpty() && paths.all { it in preferences.hiddenFolderPaths })
            }

            _uiState.update {
                it.copy(
                    showHideFolders         = true,
                    allFoldersForHide       = allFolders,
                    rootGroupsForHide       = sortedGroups,
                    ungroupedFoldersForHide = sortedUngrouped,
                    hiddenFolderPaths       = preferences.hiddenFolderPaths,
                    groupHiddenStateForHideScreen = groupHiddenState,
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
            val mediaStoreFolders = repository.getFoldersWithIndependentSort(
                folderSortOption = s.sortOption,
                independentSortEnabled = s.independentSortEnabled,
                getFolderSortOption = { bucketId -> getEffectiveFolderSortOption(bucketId) }
            )
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
            val subGroups       = groupRepository.getChildGroups(
                parentGroupId = groupId,
                groupSortOptions = s.allGroupSortOptions,
                groupCustomOrders = s.allGroupCustomOrders
            )
            val directFolders   = allFolders.filter { it.bucketId in memberBucketIds }

            // Apply the same sort as the group detail view
            val (sortedSubGroups, sortedFolders) = sortHideScreenItems(
                groups            = subGroups,
                folders           = directFolders,
                sortOption        = s.currentGroupSortOption,
                groupsAlwaysOnTop = s.groupsAlwaysOnTop,
                groupId           = groupId
            )

            // Calculate hidden state for sub-groups (including nested descendants)
            val groupSubGroupHiddenState = sortedSubGroups.associate { sub ->
                val allBucketIds = groupRepository.getAllDescendantBucketIds(sub.groupId)
                val paths = allFolders
                    .filter { it.bucketId in allBucketIds }
                    .map { it.path }
                    .filter { it.isNotBlank() }
                sub.groupId to (paths.isNotEmpty() && paths.all { it in preferences.hiddenFolderPaths })
            }

            _uiState.update {
                it.copy(
                    showHideFolders              = true,
                    allFoldersForHide            = allFolders,
                    hiddenFolderPaths            = preferences.hiddenFolderPaths,
                    rootGroupsForHide            = emptyList(),
                    ungroupedFoldersForHide      = emptyList(),
                    groupSubGroupHiddenStateForHideScreen = groupSubGroupHiddenState,
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
            val s = _uiState.value
            val groupFolders = _uiState.value.allFoldersForHide
                .filter { it.bucketId in group.memberBucketIds }
            val subGroups  = groupRepository.getChildGroups(
                parentGroupId = group.groupId,
                groupSortOptions = s.allGroupSortOptions,
                groupCustomOrders = s.allGroupCustomOrders
            )
            val sortOption = preferences.getGroupSortOption(group.groupId)
            val (sortedSubGroups, sortedFolders) = sortHideScreenItems(
                groups            = subGroups,
                folders           = groupFolders,
                sortOption        = sortOption,
                groupsAlwaysOnTop = _uiState.value.groupsAlwaysOnTop,
                groupId           = group.groupId
            )

            // Calculate hidden state for sub-groups (including nested descendants)
            val groupSubGroupHiddenState = sortedSubGroups.associate { sub ->
                val allBucketIds = groupRepository.getAllDescendantBucketIds(sub.groupId)
                val paths = s.allFoldersForHide
                    .filter { it.bucketId in allBucketIds }
                    .map { it.path }
                    .filter { it.isNotBlank() }
                sub.groupId to (paths.isNotEmpty() && paths.all { it in s.hiddenFolderPaths })
            }

            _uiState.update {
                it.copy(
                    hideScreenGroupId        = group.groupId,
                    hideScreenGroupName      = group.name,
                    hideScreenGroupFolders   = sortedFolders,
                    hideScreenGroupSubGroups = sortedSubGroups,
                    groupSubGroupHiddenStateForHideScreen = groupSubGroupHiddenState
                )
            }
        }
    }

    fun closeGroupInHideScreen() {
        viewModelScope.launch {
            val s = _uiState.value
            // Recalculate hidden state for root groups when returning to root level
            val groupHiddenState = s.rootGroupsForHide.associate { group ->
                val allBucketIds = groupRepository.getAllDescendantBucketIds(group.groupId)
                val paths = s.allFoldersForHide
                    .filter { it.bucketId in allBucketIds }
                    .map { it.path }
                    .filter { it.isNotBlank() }
                group.groupId to (paths.isNotEmpty() && paths.all { it in s.hiddenFolderPaths })
            }
            _uiState.update {
                it.copy(
                    hideScreenGroupId        = null,
                    hideScreenGroupName      = "",
                    hideScreenGroupFolders   = emptyList(),
                    hideScreenGroupSubGroups = emptyList(),
                    groupHiddenStateForHideScreen = groupHiddenState
                )
            }
        }
    }

    fun toggleGroupHidden(group: GroupItem) {
        viewModelScope.launch {
            android.util.Log.d("HideDebug", "=== toggleGroupHidden START ===")
            android.util.Log.d("HideDebug", "Group: '${group.name}' (ID ${group.groupId})")
            android.util.Log.d("HideDebug", "Group.memberBucketIds from GroupItem = ${group.memberBucketIds}")
            
            android.util.Log.d("HideDebug", "allFoldersForHide (${_uiState.value.allFoldersForHide.size} folders):")
            _uiState.value.allFoldersForHide.forEach { folder ->
                android.util.Log.d("HideDebug", "  - ${folder.name} (bucketId=${folder.bucketId}, path='${folder.path}')")
            }
            
            // Get ALL descendant bucket IDs (including nested sub-groups)
            val allBucketIds = groupRepository.getAllDescendantBucketIds(group.groupId)
            android.util.Log.d("HideDebug", "allBucketIds result = $allBucketIds")
            
            val groupFolders = _uiState.value.allFoldersForHide
                .filter { it.bucketId in allBucketIds }
            android.util.Log.d("HideDebug", "Filtered groupFolders (${groupFolders.size} folders):")
            groupFolders.forEach { folder ->
                android.util.Log.d("HideDebug", "  - ${folder.name} (bucketId=${folder.bucketId}, path='${folder.path}')")
            }
            
            val paths = groupFolders.map { it.path }.filter { it.isNotBlank() }
            android.util.Log.d("HideDebug", "Paths to hide (${paths.size}): $paths")
            
            if (paths.isEmpty()) {
                android.util.Log.d("HideDebug", "No paths found, returning")
                return@launch
            }
            val currentHidden = _uiState.value.hiddenFolderPaths
            val allAlreadyHidden = paths.all { it in currentHidden }
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
            
            // Recalculate group hidden states
            val s = _uiState.value
            if (s.hideScreenGroupId == null) {
                // At root level - recalculate root groups
                val groupHiddenState = s.rootGroupsForHide.associate { g ->
                    val allBucketIds = groupRepository.getAllDescendantBucketIds(g.groupId)
                    val p = s.allFoldersForHide.filter { it.bucketId in allBucketIds }.map { it.path }.filter { it.isNotBlank() }
                    g.groupId to (p.isNotEmpty() && p.all { it in preferences.hiddenFolderPaths })
                }
                _uiState.update { it.copy(groupHiddenStateForHideScreen = groupHiddenState) }
            } else {
                // Inside a group - recalculate sub-groups
                val groupSubGroupHiddenState = s.hideScreenGroupSubGroups.associate { g ->
                    val allBucketIds = groupRepository.getAllDescendantBucketIds(g.groupId)
                    val p = s.allFoldersForHide.filter { it.bucketId in allBucketIds }.map { it.path }.filter { it.isNotBlank() }
                    g.groupId to (p.isNotEmpty() && p.all { it in preferences.hiddenFolderPaths })
                }
                _uiState.update { it.copy(groupSubGroupHiddenStateForHideScreen = groupSubGroupHiddenState) }
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

        // Refresh parent/root groups to update their preview thumbnails with new sort order
        viewModelScope.launch {
            silentRefresh()
        }
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

    fun toggleConflictApplyToAll() {
        _fileConflict.value?.let { conflict ->
            conflict.applyToAll = !conflict.applyToAll
            _fileConflict.value = conflict.copy(applyToAll = conflict.applyToAll)
        }
    }

    fun resolveConflict(resolution: ConflictResolution) {
        val conflict = _fileConflict.value
        if (conflict != null) {
            // If "Apply to all" is checked, set bulk resolution for SKIP or REPLACE
            if (conflict.applyToAll) {
                when (resolution) {
                    ConflictResolution.SKIP -> bulkResolution = ConflictResolution.SKIP_ALL
                    ConflictResolution.REPLACE -> bulkResolution = ConflictResolution.REPLACE_ALL
                    ConflictResolution.RENAME -> { /* RENAME is always individual */ }
                    else -> { }
                }
            }
            conflict.deferred.complete(resolution)
            _fileConflict.value = null
        }
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
        // Use getFoldersWithIndependentSort to respect each album's sort option for preview generation.
        var allFolders = repository.getFoldersWithIndependentSort(
            folderSortOption = s.sortOption,
            independentSortEnabled = true, // Always use per-album sort
            getFolderSortOption = { bucketId -> getEffectiveFolderSortOption(bucketId) }
        )

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

        // Load all groups first to get their sort preferences
        val allGroups = groupRepository.getAllGroups()
        val groupSortOptions = allGroups.associate { group ->
            group.groupId to preferences.getGroupSortOption(group.groupId).id
        }
        val groupCustomOrders = preferences.allCustomGroupItemsOrders()

        // Get root groups with sort data for proper preview generation
        val rootGroups = groupRepository.getRootGroups(
            groupSortOptions = groupSortOptions,
            groupCustomOrders = groupCustomOrders
        )
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
                allGroupCustomOrders = groupCustomOrders,
                allGroupSortOptions  = groupSortOptions,
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
            val gAllSubGroups = groupRepository.getChildGroups(
                parentGroupId = openGroupId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            val gFolders      = folders.filter { it.bucketId in gBucketIds }
            // Hide sub-groups whose every direct album is hidden (same rule as root)
            val gSubGroups    = gAllSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || isGroupVisible(sub)
            }
            val gSortOpt   = _uiState.value.currentGroupSortOption
            val gOrdered   = if (gSortOpt == FolderSortOption.CUSTOM_ORDER)
                GroupMixedOrderUtil.applyCustomGroupMixedOrder(openGroupId, gSubGroups, gFolders, preferences)
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

    /** Force refresh album preview images by reloading folder data. */
    fun refreshAlbumPreviews() {
        viewModelScope.launch {
            silentRefresh()
        }
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

    /** Delegates to MixedItemSorter.applyCustomMixedOrder in common module. */
    private fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val savedOrder = preferences.customMixedOrder
        val (result, newOrder) = MixedItemSorter.applyCustomMixedOrder(groups, folders, savedOrder)
        preferences.customMixedOrder = newOrder
        return result
    }

    /** Delegates to MixedItemSorter.sortMixedItems in common module. */
    private fun sortMixedItems(
        items: List<Any>,
        option: FolderSortOption,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        return MixedItemSorter.sortMixedItems(items, option, groupsAlwaysOnTop)
    }

    /**
     * Sorts [groups] and [folders] for the Hide Folders screen.
     * Delegates to MixedItemSorter.sortHideScreenItems in common module.
     */
    private fun sortHideScreenItems(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        sortOption: FolderSortOption,
        groupsAlwaysOnTop: Boolean,
        groupId: Long?
    ): Pair<List<GroupItem>, List<FolderItem>> {
        val savedOrder = if (groupId != null)
            preferences.getGroupMixedOrder(groupId)
        else
            preferences.customMixedOrder
        return MixedItemSorter.sortHideScreenItems(groups, folders, sortOption, groupsAlwaysOnTop, savedOrder)
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

        // Load group data FIRST, then update state with everything together to avoid empty state flash
        viewModelScope.launch {
            val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
            // Reload sort options from preferences to get the latest changes
            val allGroups = groupRepository.getAllGroups()
            val groupSortOptions = allGroups.associate { it.groupId to preferences.getGroupSortOption(it.groupId).id }
            val groupCustomOrders = allGroups.associate { it.groupId to preferences.getGroupMixedOrder(it.groupId) }
            val allSubGroups = groupRepository.getChildGroups(
                parentGroupId = groupId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            // Filter from the globally-sorted folders list so non-custom sorts display correctly
            val bucketIdSet = bucketIds.toSet()
            val folders = s.folders.filter { it.bucketId in bucketIdSet }
            // Hide sub-groups whose every direct album is hidden
            val visibleBucketSet = s.folders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            // Use the group's own independent sort option
            val groupSortOption = groupSort
            val orderedMixed = if (groupSortOption == FolderSortOption.CUSTOM_ORDER) {
                GroupMixedOrderUtil.applyCustomGroupMixedOrder(groupId, subGroups, folders, preferences)
            } else {
                sortMixedItems(subGroups + folders, groupSortOption, s.groupsAlwaysOnTop)
            }

            // Update state with group ID and data together — no empty state flash
            _uiState.update {
                it.copy(
                    currentGroupId                = groupId,
                    currentGroupName              = name,
                    groupStack                    = newStack,
                    isSelectionMode               = false,
                    selectedFolderIds             = emptySet(),
                    selectedGroupIds              = emptySet(),
                    currentGroupSortOption        = groupSort,
                    currentGroupFolders           = folders,
                    currentGroupSubGroups         = subGroups,
                    currentGroupOrderedMixedItems = orderedMixed
                )
            }
        }
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
            // Always fetch fresh folder data to ensure previews reflect current sort order
            val allFolders = repository.getFoldersWithIndependentSort(
                folderSortOption = s.sortOption,
                independentSortEnabled = true, // Always use per-album sort
                getFolderSortOption = { bucketId -> getEffectiveFolderSortOption(bucketId) }
            )
            // Reload sort options from preferences to get the latest changes
            val allGroups = groupRepository.getAllGroups()
            val groupSortOptions = allGroups.associate { it.groupId to preferences.getGroupSortOption(it.groupId).id }
            val groupCustomOrders = allGroups.associate { it.groupId to preferences.getGroupMixedOrder(it.groupId) }
            val allSubGroups = groupRepository.getChildGroups(
                parentGroupId = groupId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            // Filter from the globally-sorted folders list so non-custom sorts display correctly
            val bucketIdSet = bucketIds.toSet()
            val folders = allFolders.filter { it.bucketId in bucketIdSet }
            // Hide sub-groups whose every direct album is hidden
            val visibleBucketSet = allFolders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            // Use the group's own independent sort option
            val groupSortOption = s.currentGroupSortOption
            val orderedMixed = if (groupSortOption == FolderSortOption.CUSTOM_ORDER) {
                GroupMixedOrderUtil.applyCustomGroupMixedOrder(groupId, subGroups, folders, preferences)
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

    /** Delegates to FilePathUtils.generateUniqueGroupName in common module. */
    private fun generateUniqueGroupName(existingNames: Set<String>): String {
        return FilePathUtils.generateUniqueGroupName("Group", existingNames)
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
            val newGroupId = groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.groupCreationSelectedFolderIds.toList(),
                subGroupIds     = s.groupCreationSelectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            // Prepend the new group at position 0 — always, regardless of current sort option.
            // If sort isn't CUSTOM_ORDER yet, snapshot the current visible order first.
            if (s.currentGroupId == null) {
                prependToRootOrder("g_$newGroupId")
            } else {
                prependToGroupOrder("g_$newGroupId", s.currentGroupId!!, s)
            }
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
            val newGroupId = groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.selectedFolderIds.toList(),
                subGroupIds     = s.selectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            // Prepend the new group at position 0 — always, regardless of current sort option.
            if (s.currentGroupId == null) {
                prependToRootOrder("g_$newGroupId")
            } else {
                prependToGroupOrder("g_$newGroupId", s.currentGroupId!!, s)
            }
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
            // Don't force selection mode - it's controlled by enterSelectionMode/exitSelectionMode
            it.copy(selectedGroupIds = newSet)
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
            val newGroupId = groupRepository.createGroup(
                name            = name,
                folderBucketIds = s.selectedFolderIds.toList(),
                subGroupIds     = s.selectedGroupIds.toList(),
                parentGroupId   = s.currentGroupId
            )
            // Prepend the new group at position 0 — always, regardless of current sort option.
            if (s.currentGroupId == null) {
                prependToRootOrder("g_$newGroupId")
            } else {
                prependToGroupOrder("g_$newGroupId", s.currentGroupId!!, s)
            }
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

    /**
     * Change the sort for the currently open folder (independent of the main tab sort).
     * 
     * ⚠️ CRITICAL: Independent sort is ALWAYS enabled.
     * ALWAYS saves album-specific sort (per bucketId).
     * DO NOT add back any checks or toggles - independent sort is mandatory!
     * See docs/INDEPENDENT_SORT_ARCHITECTURE.md for details.
     */
    fun setFolderSortOption(s: VideoSortOption) {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        // Always save album-specific sort (independent sort is now always enabled)
        preferences.saveFolderVideoSortOption(bucketId, s)
        // Sort existing folder videos in-memory immediately so that both
        // currentFolderSortOption and folderVideos change in the same recomposition frame.
        // This prevents LazyVerticalGrid's stable keys from re-scrolling when
        // the async data arrives later.
        val sorted = sortVideosInMemory(_uiState.value.folderVideos, s)
        _uiState.update {
            it.copy(
                currentFolderSortOption = s,
                folderVideos = sorted,
                folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
            )
        }
        // Refresh the folder videos from MediaStore to ensure consistency
        refreshFolderVideos()
        // Refresh album preview images on the Folders tab to reflect the new sort
        viewModelScope.launch {
            silentRefresh()
        }
        // If we're inside a group, refresh the group view to update album preview
        if (_uiState.value.currentGroupId != null) {
            refreshCurrentGroup()
        }
        scheduleAutoBackup()
    }

    private fun sortVideosInMemory(videos: List<VideoItem>, option: VideoSortOption): List<VideoItem> {
        return when (option) {
            VideoSortOption.CUSTOM_ORDER -> videos.sortedWith(compareByDescending<VideoItem> { it.dateModified }.thenBy { it.id })
            VideoSortOption.NAME_A_TO_Z -> videos.sortedBy { it.displayName.lowercase() }
            VideoSortOption.NAME_Z_TO_A -> videos.sortedByDescending { it.displayName.lowercase() }
            VideoSortOption.DURATION_ASC -> videos.sortedBy { it.duration }
            VideoSortOption.DURATION_DESC -> videos.sortedByDescending { it.duration }
            VideoSortOption.DATE_CREATED_ASC -> videos.sortedBy { it.id }
            VideoSortOption.DATE_CREATED_DESC -> videos.sortedByDescending { it.id }
            VideoSortOption.DATE_MODIFIED_ASC -> videos.sortedBy { it.dateModified }
            VideoSortOption.DATE_MODIFIED_DESC -> videos.sortedByDescending { it.dateModified }
        }
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
            // Don't force selection mode - it's controlled by enterSelectionMode/exitSelectionMode
            it.copy(selectedFolderIds = newSet)
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

            // Use ACTION_SEND for single item, ACTION_SEND_MULTIPLE for multiple items
            val intent = if (uris.size == 1) {
                Intent(Intent.ACTION_SEND).apply {
                    type = "video/*"
                    putExtra(Intent.EXTRA_STREAM, uris.first())
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            } else {
                Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "video/*"
                    putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(uris))
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
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
            
            // Use ACTION_SEND for single item, ACTION_SEND_MULTIPLE for multiple items
            val intent = if (uris.size == 1) {
                Intent(Intent.ACTION_SEND).apply {
                    type = "video/*"
                    putExtra(Intent.EXTRA_STREAM, uris.first())
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            } else {
                Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "video/*"
                    putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
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
        return FilePathUtils.destFolderName(path)
    }

    fun cancelCopyMove() {
        copyMoveCancelled = true
        _copyMoveProgress.value = CopyMoveProgress(isActive = false, title = "", current = 0, total = 0)
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

    fun showRenameAlbumDialog() {
        val s = _uiState.value
        if (s.selectedFolderIds.size == 1) {
            val bucketId = s.selectedFolderIds.first()
            val folder = s.folders.find { it.bucketId == bucketId }
            folder?.let {
                _uiState.update { state ->
                    state.copy(showRenameAlbumDialog = true, renameAlbumTarget = folder)
                }
                // Also load physical filesystem folder names for validation
                viewModelScope.launch(Dispatchers.IO) {
                    val physicalNames = getPhysicalFolderNames(folder.path)
                    _uiState.update { state ->
                        // Combine MediaStore DCIM names with physical folder names for complete validation
                        val allExistingNames = state.dcimFolderNames + physicalNames
                        state.copy(dcimFolderNames = allExistingNames)
                    }
                }
            }
        }
    }

    /**
     * Returns the names of all directories in the same parent folder as the given path.
     * Used to validate album renames against physical filesystem, not just MediaStore.
     */
    private fun getPhysicalFolderNames(folderPath: String): Set<String> {
        if (folderPath.isBlank()) return emptySet()
        return try {
            val folder = java.io.File(folderPath)
            val parentDir = folder.parentFile ?: return emptySet()
            parentDir.listFiles()
                ?.filter { it.isDirectory }
                ?.map { it.name }
                ?.toSet()
                ?: emptySet()
        } catch (e: Exception) {
            com.videolibrary.data.util.FileLogger.e("VideoListViewModel", "Failed to get physical folder names", e)
            emptySet()
        }
    }

    fun dismissRenameAlbumDialog() = _uiState.update {
        it.copy(showRenameAlbumDialog = false, renameAlbumTarget = null)
    }

    fun renameSelectedAlbum(newName: String) {
        val target = _uiState.value.renameAlbumTarget ?: return
        viewModelScope.launch {
            isInternalChange.set(true)
            val success = repository.renameAlbum(target.bucketId, newName)
            if (success) {
                exitSelectionMode()
                silentRefresh()
                if (_uiState.value.currentGroupId != null) {
                    refreshCurrentGroup()
                }
                scheduleAutoBackup()
            }
        }
    }

    // ── Album Creation ───────────────────────────────────────────────────────

    fun startCreateAlbumPicker(albumName: String) {
        _uiState.update {
            it.copy(
                showCreateAlbumDialog = false,
                showCreateAlbumPicker = true,
                pendingAlbumName = albumName
            )
        }
    }

    fun loadAlbumCreationVideos(bucketId: Int, name: String) {
        _uiState.update {
            it.copy(albumCreationCurrentBucketId = bucketId, albumCreationCurrentBucketName = name)
        }
        viewModelScope.launch {
            val videos = repository.getVideos(_uiState.value.videoSortOption, bucketId = bucketId)
            _uiState.update { it.copy(albumCreationBrowsedVideos = videos) }
        }
    }

    fun closeAlbumCreationFolder() {
        _uiState.update {
            it.copy(
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = "",
                albumCreationBrowsedVideos = emptyList()
            )
        }
    }

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
                albumCreationSelectedVideoIds = emptySet(),
                albumCreationBrowsedVideos = emptyList(),
                albumCreationCurrentBucketId = null,
                albumCreationCurrentBucketName = ""
            )
        }
    }

    fun confirmAlbumCreation(copy: Boolean) {
        val s = _uiState.value
        val videoIds = s.albumCreationSelectedVideoIds
        val folderName = s.pendingAlbumName
        if (videoIds.isEmpty() || folderName.isBlank()) return

        val videos = s.videos.filter { it.id in videoIds }
        val parentGroupId = s.currentGroupId

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
        val verb = if (copy) "Copying" else "Moving"
        _copyMoveProgress.value = CopyMoveProgress(
            isActive = true,
            title = "$verb items to \"$folderName\"...",
            current = 0,
            total = videos.size
        )

        copyMoveJob = viewModelScope.launch {
            isInternalChange.set(true)
            val path = repository.createFolder(folderName)
            if (path != null) {
                if (copy) {
                    repository.copyVideos(
                        videos,
                        path,
                        onProgress = { cur, tot ->
                            _copyMoveProgress.value = _copyMoveProgress.value.copy(current = cur, total = tot)
                        },
                        isCancelled = { copyMoveCancelled },
                        onConflict = { fileName -> askConflictResolution(fileName) }
                    )
                } else {
                    repository.moveVideos(
                        videos,
                        path,
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
            refreshFolderVideos()

            if (parentGroupId != null) {
                val newBucketId = findFolderBucketIdByName(folderName)
                if (newBucketId != null) {
                    groupRepository.addFoldersToGroup(parentGroupId, listOf(newBucketId))
                    prependToGroupOrder("folder_$newBucketId", parentGroupId, s)
                    loadDataCore()
                    refreshCurrentGroup()
                } else {
                    loadDataCore()
                }
            } else {
                val newBucketId = findFolderBucketIdByName(folderName)
                if (newBucketId != null) {
                    prependToRootOrder("folder_$newBucketId")
                }
                loadDataCore()
                if (newBucketId != null) {
                    val key = "folder_$newBucketId"
                    val idx = _uiState.value.orderedMixedItems.indexOfFirst {
                        it is FolderItem && it.bucketId == newBucketId
                    }
                    if (idx > 0) {
                        val reordered = listOf(key) +
                                preferences.customMixedOrder.filter { it != key }
                        preferences.customMixedOrder = reordered
                        _uiState.update { state ->
                            val mixed = state.orderedMixedItems.toMutableList()
                            val item = mixed.removeAt(idx)
                            mixed.add(0, item)
                            state.copy(orderedMixedItems = mixed)
                        }
                    }
                }
            }
            scheduleAutoBackup()
        }
    }

    private suspend fun findFolderBucketIdByName(name: String): Int? {
        return repository.getFolders().find { it.name == name }?.bucketId
    }

    // ── Play Video ───────────────────────────────────────────────────────────

    fun playVideo(context: android.content.Context, video: VideoItem) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(video.contentUri, video.mimeType)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }



    fun showDeleteDialog() = _uiState.update { it.copy(showDeleteDialog = true) }
    fun dismissDeleteDialog() = _uiState.update { it.copy(showDeleteDialog = false) }
    fun showCreateFolderDialog() = _uiState.update { it.copy(showCreateFolderDialog = true) }
    fun dismissCreateFolderDialog() = _uiState.update { it.copy(showCreateFolderDialog = false) }
    fun showMoveFolderPicker() { _uiState.update { it.copy(showMoveFolderPicker = true) } }
    fun dismissMoveFolderPicker() = _uiState.update { it.copy(showMoveFolderPicker = false) }
    fun showCopyFolderPicker() { _uiState.update { it.copy(showCopyFolderPicker = true) } }
    fun dismissCopyFolderPicker() = _uiState.update { it.copy(showCopyFolderPicker = false) }
    fun showAbout() = _uiState.update { it.copy(showAbout = true) }
    fun dismissAbout() = _uiState.update { it.copy(showAbout = false) }
    fun showSettings() = _uiState.update { it.copy(showSettings = true) }
    fun dismissSettings() = _uiState.update { it.copy(showSettings = false) }

    fun showCreateAlbumDialog() = _uiState.update { it.copy(showCreateAlbumDialog = true) }
    fun dismissCreateAlbumDialog() = _uiState.update { it.copy(showCreateAlbumDialog = false) }

    // ── Auto-backup ──────────────────────────────────────────────────────────

    fun updateAutoBackupEnabled(value: Boolean) {
        preferences.autoBackupEnabled = value
        _uiState.update { it.copy(autoBackupEnabled = value) }
    }

    fun scheduleAutoBackup() {
        if (!preferences.autoBackupEnabled) return
        autoBackupJob?.cancel()
        autoBackupJob = viewModelScope.launch {
            delay(AUTO_BACKUP_DEBOUNCE_MS)
            withContext(Dispatchers.IO) {
                com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
            }
        }
    }

    fun onAppBackground() {
        if (!preferences.autoBackupEnabled) return
        autoBackupJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
        }
    }

    fun createBackupJson(): String =
        com.videolibrary.data.util.BackupManager.createBackup(getApplication())

    suspend fun saveBackupToFile(): Boolean = withContext(Dispatchers.IO) {
        com.videolibrary.data.util.BackupManager.saveBackupToFile(getApplication())
    }

    suspend fun restoreBackupFromFile(): Boolean {
        isRestoringBackup = true
        val ok = withContext(Dispatchers.IO) {
            com.videolibrary.data.util.BackupManager.restoreBackupFromFile(getApplication())
        }
        if (ok) {
            isRestoringBackup = false

            // Refresh UI state with restored preferences
            _uiState.update {
                it.copy(
                    viewType = preferences.viewType,
                    folderViewType = preferences.folderViewType,
                    sortOption = preferences.folderSortOption,
                    videoSortOption = preferences.videoSortOption,
                    instantPlayerEnabled = preferences.instantPlayerEnabled,
                    autoBackupEnabled = preferences.autoBackupEnabled,
                    independentSortEnabled = preferences.independentSortEnabled,
                    groupsAlwaysOnTop = preferences.groupsAlwaysOnTop,
                    floatingTopBarEnabled = preferences.floatingTopBarEnabled
                )
            }

            // Reload all data to reflect restored preferences
            loadDataCore()

            // If inside a group, refresh to apply restored group sort option
            val currentGroupId = _uiState.value.currentGroupId
            if (currentGroupId != null) {
                val restoredGroupSort = preferences.getGroupSortOption(currentGroupId)
                _uiState.update { it.copy(currentGroupSortOption = restoredGroupSort) }
                refreshCurrentGroup()
            }

            // If inside a folder, refresh to apply restored folder video sort option
            val currentFolderBucketId = _uiState.value.currentFolderBucketId
            if (currentFolderBucketId != null) {
                val restoredFolderSort = preferences.getFolderVideoSortOption(currentFolderBucketId)
                _uiState.update { it.copy(currentFolderSortOption = restoredFolderSort) }
                refreshCurrentFolderIfOpen()
            }
        } else {
            isRestoringBackup = false
        }
        return ok
    }

    fun restoreBackupJson(json: String): Boolean {
        val ok = com.videolibrary.data.util.BackupManager.restoreBackup(getApplication(), json)
        if (ok) refreshStateAfterRestore()
        return ok
    }

    private fun refreshStateAfterRestore() {
        isRestoringBackup = false
        _uiState.update {
            it.copy(
                viewType = preferences.viewType,
                folderViewType = preferences.folderViewType,
                sortOption = preferences.folderSortOption,
                videoSortOption = preferences.videoSortOption,
                instantPlayerEnabled = preferences.instantPlayerEnabled,
                autoBackupEnabled = preferences.autoBackupEnabled,
                independentSortEnabled = preferences.independentSortEnabled,
                groupsAlwaysOnTop = preferences.groupsAlwaysOnTop,
                floatingTopBarEnabled = preferences.floatingTopBarEnabled
            )
        }
        loadData()
    }

    // ── Helper methods for custom order ──────────────────────────────────────

    private fun prependToRootOrder(newKey: String) {
        val current = _uiState.value
        if (current.sortOption != FolderSortOption.CUSTOM_ORDER) {
            val snapshot = current.orderedMixedItems.mapNotNull { item ->
                when (item) {
                    is com.example.common.data.model.GroupItem -> "group_${item.groupId}"
                    is com.example.common.data.model.FolderItem -> "folder_${item.bucketId}"
                    else -> null
                }
            }
            preferences.folderSortOption = FolderSortOption.CUSTOM_ORDER
            preferences.customMixedOrder = snapshot
            _uiState.update { it.copy(sortOption = FolderSortOption.CUSTOM_ORDER) }
        } else if (preferences.customMixedOrder.isEmpty()) {
            val snapshot = current.orderedMixedItems.mapNotNull { item ->
                when (item) {
                    is com.example.common.data.model.GroupItem -> "group_${item.groupId}"
                    is com.example.common.data.model.FolderItem -> "folder_${item.bucketId}"
                    else -> null
                }
            }
            preferences.customMixedOrder = snapshot
        }
        val existing = preferences.customMixedOrder
        if (newKey !in existing) {
            preferences.customMixedOrder = listOf(newKey) + existing
        }
    }

    private fun prependToGroupOrder(newKey: String, groupId: Long, s: VideoListUiState) {
        if (preferences.getGroupSortOption(groupId) != FolderSortOption.CUSTOM_ORDER) {
            val snapshot = s.currentGroupOrderedMixedItems.mapNotNull { item ->
                when (item) {
                    is com.example.common.data.model.GroupItem -> "group_${item.groupId}"
                    is com.example.common.data.model.FolderItem -> "folder_${item.bucketId}"
                    else -> null
                }
            }
            preferences.saveGroupSortOption(groupId, FolderSortOption.CUSTOM_ORDER)
            preferences.saveGroupMixedOrder(groupId, snapshot)
            _uiState.update { it.copy(currentGroupSortOption = com.example.common.data.model.FolderSortOption.CUSTOM_ORDER) }
        } else if (preferences.getGroupMixedOrder(groupId).isEmpty()) {
            val snapshot = s.currentGroupOrderedMixedItems.mapNotNull { item ->
                when (item) {
                    is com.example.common.data.model.GroupItem -> "group_${item.groupId}"
                    is com.example.common.data.model.FolderItem -> "folder_${item.bucketId}"
                    else -> null
                }
            }
            preferences.saveGroupMixedOrder(groupId, snapshot)
        }
        val existing = preferences.getGroupMixedOrder(groupId)
        if (newKey !in existing) {
            preferences.saveGroupMixedOrder(groupId, listOf(newKey) + existing)
        }
    }

    private fun getEffectiveFolderSortOption(bucketId: Int): VideoSortOption {
        // Load this album's specific sort option (independent sort is always enabled)
        return preferences.getFolderVideoSortOption(bucketId)
    }
}

