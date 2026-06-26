package com.imagelibrary.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.content.IntentSender
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.common.data.model.ConflictResolution
import com.example.common.data.model.CopyMoveProgress
import com.example.common.data.model.FileConflict
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.util.MixedItemSorter
import com.example.common.util.FilePathUtils
import com.example.common.util.GroupMixedOrderUtil
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
    val showRenameAlbumDialog: Boolean = false,
    val renameAlbumTarget: FolderItem? = null,
    val showDeleteDialog: Boolean = false,
    val showCreateFolderDialog: Boolean = false,
    val showMoveFolderPicker: Boolean = false,
    val showCopyFolderPicker: Boolean = false,
    val showAbout: Boolean = false,
    val showSettings: Boolean = false,
    val carouselShowBarsOnOpen: Boolean = false,
    val carouselAlwaysHideOverlay: Boolean = false,
    val showDetailsDialog: Boolean = false,
    val detailsTarget: ImageItem? = null,
    val carouselIndex: Int = -1,
    val currentCarouselPage: Int = -1,
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
    val pendingGroupCreationParentId: Long? = null,
    val existingGroupNames: Set<String> = emptySet(),
    val suggestedGroupName: String = "Group 1",

    // ── Unified ordered mixed display list (groups + ungrouped folders interleaved) ──
    val orderedMixedItems: List<Any> = emptyList(),

    /** Per-group custom sort orders, forwarded to FolderPickerScreen so the picker
     *  respects the same drag order as the group detail screen. */
    val allGroupCustomOrders: Map<Long, List<String>> = emptyMap(),
    
    /** Per-group sort options (map of groupId to SortOption.id), forwarded to
     *  FolderPickerScreen so the picker respects each group's sort preference. */
    val allGroupSortOptions: Map<Long, Int> = emptyMap(),

    // ── Move-to-group picker ──
    val showMoveToGroupPicker: Boolean = false,
    val moveToGroupFolderIds: Set<Int> = emptySet(),
    val moveToGroupGroupIds: Set<Long> = emptySet(),

    val autoBackupEnabled: Boolean = false,
    /** Incremented by loadDataCore when a sort-change refresh completes, so the
     *  screen can scroll to top AFTER new items arrive (avoids animateItem fighting the scroll). */
    val scrollToTopTrigger: Int = 0,

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
    val independentViewTypeEnabled: Boolean = false,

    /** When true, groups are pinned to the top of sorted lists; ungrouped albums follow. */
    val groupsAlwaysOnTop: Boolean = false,

    /** When true, use Samsung Gallery-style floating top bar with full-screen content. */
    val floatingTopBarEnabled: Boolean = false,

    /** When true, users can drag-and-drop to reorder media items in Custom sort mode. */
    val allowMediaReordering: Boolean = false,

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
    val hideScreenStartedInsideGroup: Boolean = false,
    /** Pre-calculated hidden state for root groups (includes nested descendants). */
    val groupHiddenStateForHideScreen: Map<Long, Boolean> = emptyMap(),
    /** Pre-calculated hidden state for sub-groups in hide screen (includes nested descendants). */
    val groupSubGroupHiddenStateForHideScreen: Map<Long, Boolean> = emptyMap(),
    /** Increment this to trigger scroll-to-top in folder detail screen (album view). */
    val folderDetailScrollToTopTrigger: Int = 0
)

// CopyMoveProgress and FileConflict moved to common module

class ImageListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ImageRepository(application)
    private val groupRepository = GroupRepository(application)
    val preferences = AppPreferences(application)
    private val _uiState = MutableStateFlow(
        ImageListUiState(
            independentSortEnabled = preferences.independentSortEnabled,
            independentViewTypeEnabled = preferences.independentViewTypeEnabled,
            allowMediaReordering = preferences.allowMediaReordering
        )
    )
    val uiState: StateFlow<ImageListUiState> = _uiState.asStateFlow()

    fun updateIndependentSortEnabled(value: Boolean) {
        preferences.independentSortEnabled = value
        _uiState.update { it.copy(independentSortEnabled = value) }
        scheduleAutoBackup()
    }

    fun updateIndependentViewTypeEnabled(value: Boolean) {
        preferences.independentViewTypeEnabled = value
        _uiState.update { it.copy(independentViewTypeEnabled = value) }
        scheduleAutoBackup()
    }

    fun updateGroupsAlwaysOnTop(value: Boolean) {
        preferences.groupsAlwaysOnTop = value
        _uiState.update { it.copy(groupsAlwaysOnTop = value) }
        silentRefresh()
        scheduleAutoBackup()
    }

    fun updateAllowMediaReordering(value: Boolean) {
        preferences.allowMediaReordering = value
        _uiState.update { it.copy(allowMediaReordering = value) }
        scheduleAutoBackup()
    }

    // ── Media Reordering (Drag-and-Drop) ────────────────────────────────────────

    /**
     * Reorder media items within the currently-open folder (album).
     * Only active when allowMediaReordering is true and sort is CUSTOM_ORDER.
     */
    fun reorderFolderMedia(fromIndex: Int, toIndex: Int) {
        if (_uiState.value.currentFolderBucketId == null) return
        val currentImages = _uiState.value.folderImages.toMutableList()
        
        if (fromIndex !in currentImages.indices || toIndex !in currentImages.indices) return
        
        val item = currentImages.removeAt(fromIndex)
        currentImages.add(toIndex, item)
        
        _uiState.update { it.copy(folderImages = currentImages) }
    }

    /**
     * Persist the reordered folder media to preferences.
     * Called when drag-and-drop gesture completes.
     */
    fun onFolderMediaReorderDone() {
        val currentBucketId = _uiState.value.currentFolderBucketId ?: return
        val imageIds = _uiState.value.folderImages.map { it.id }
        preferences.saveFolderMediaCustomOrder(currentBucketId, imageIds)
        scheduleAutoBackup()
    }

    /**
     * Reorder media items in the root view (all images).
     * Only active when allowMediaReordering is true and sort is CUSTOM_ORDER.
     */
    fun reorderRootMedia(fromIndex: Int, toIndex: Int) {
        val currentImages = _uiState.value.images.toMutableList()

        if (fromIndex !in currentImages.indices || toIndex !in currentImages.indices) return

        val item = currentImages.removeAt(fromIndex)
        currentImages.add(toIndex, item)

        _uiState.update { it.copy(images = currentImages) }
    }

    /**
     * Persist the reordered root media to preferences.
     * Called when drag-and-drop gesture completes.
     */
    fun onRootMediaReorderDone() {
        val imageIds = _uiState.value.images.map { it.id }
        preferences.customRootMediaOrder = imageIds
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
                sortOption = s.sortOption,
                getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
            )
            val hiddenMeta      = preferences.getAllHiddenFolderMeta()
            val mediaStorePaths = mediaStoreFolders.map { it.path }.toSet()
            val ghosts = hiddenMeta
                .filter { (path, _) -> path !in mediaStorePaths }
                .map { (path, triple) ->
                    FolderItem(
                        bucketId  = triple.second, name = triple.first,
                        itemCount = triple.third,  path = path
                    )
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

    /**
     * Sorts [groups] and [folders] for the Hide Folders screen using [sortOption].
     * Delegates to MixedItemSorter.sortHideScreenItems in common module.
     */
    private fun sortHideScreenItems(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        sortOption: SortOption,
        groupsAlwaysOnTop: Boolean,
        groupId: Long?
    ): Pair<List<GroupItem>, List<FolderItem>> {
        val savedOrder = if (groupId != null)
            preferences.customGroupItemsOrder(groupId)
        else
            preferences.customMixedOrder
        return MixedItemSorter.sortHideScreenItems(groups, folders, sortOption, groupsAlwaysOnTop, savedOrder)
    }

    fun toggleGroupHidden(group: GroupItem) {
        viewModelScope.launch {
            val allFolders = _uiState.value.allFoldersForHide
            
            android.util.Log.d("HideDebug", "=== toggleGroupHidden START ===")
            android.util.Log.d("HideDebug", "Group: '${group.name}' (ID ${group.groupId})")
            android.util.Log.d("HideDebug", "Group.memberBucketIds from GroupItem = ${group.memberBucketIds}")
            
            android.util.Log.d("HideDebug", "allFoldersForHide (${allFolders.size} folders):")
            allFolders.forEach { folder ->
                android.util.Log.d("HideDebug", "  - ${folder.name} (bucketId=${folder.bucketId}, path='${folder.path}')")
            }
            
            // Get ALL descendant bucket IDs (including nested sub-groups)
            val allBucketIds = groupRepository.getAllDescendantBucketIds(group.groupId)
            android.util.Log.d("HideDebug", "allBucketIds result = $allBucketIds")
            
            val groupFolders = allFolders.filter { it.bucketId in allBucketIds }
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
                    com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
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
                    com.imagelibrary.data.util.BackupManager.saveBackupToFile(getApplication())
                }
            }
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
        
        // Refresh parent/root groups to update their preview thumbnails with new sort order
        viewModelScope.launch {
            silentRefresh()
        }
    }

    // Copy/Move progress
    private val _copyMoveProgress = MutableStateFlow(CopyMoveProgress())
    val copyMoveProgress: StateFlow<CopyMoveProgress> = _copyMoveProgress.asStateFlow()
    private var copyMoveJob: Job? = null
    @Volatile private var copyMoveCancelled = false
    @Volatile private var bulkResolution: ConflictResolution? = null

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
                // Preserve the current image order when an external app (e.g. Samsung Gallery)
                // triggers a MediaStore change — stops edited photos jumping to a new position.
                refreshFolderImages(preserveOrder = true)
            }
        }
    }

    // Share intent — collected once at root screen level
    private val _shareIntent = MutableSharedFlow<Intent>(extraBufferCapacity = 1)
    val shareIntent: SharedFlow<Intent> = _shareIntent.asSharedFlow()

    // Trash request — collected once at root screen level; UI launches the system dialog
    private val _trashRequest = MutableSharedFlow<IntentSender>(extraBufferCapacity = 1)
    val trashRequest: SharedFlow<IntentSender> = _trashRequest.asSharedFlow()

    private sealed class PendingTrash {
        data class SelectedImages(val ids: Set<Long>) : PendingTrash()
        data class SelectedFolders(val folderIds: Set<Int>) : PendingTrash()
        data class CarouselImage(val id: Long) : PendingTrash()
    }
    private var pendingTrash: PendingTrash? = null

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
                carouselAlwaysHideOverlay = preferences.carouselAlwaysHideOverlay,
                groupSortOption = preferences.groupSortOption,
                autoBackupEnabled = preferences.autoBackupEnabled,
                independentSortEnabled = preferences.independentSortEnabled,
                groupsAlwaysOnTop = preferences.groupsAlwaysOnTop,
                floatingTopBarEnabled = preferences.floatingTopBarEnabled,
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
    private suspend fun loadDataCore(scrollToTop: Boolean = false) {
        val s = _uiState.value
        val images = repository.getImages(
            imageSortOption = s.imageSortOption,
            allowMediaReordering = s.allowMediaReordering,
            customOrder = preferences.customRootMediaOrder
        )

        val hiddenPaths = preferences.hiddenFolderPaths
        // Fetch ALL folders from MediaStore (hidden ones are still there — app-local approach).
        // The mixed order is computed/saved across ALL so hidden folder keys are retained
        // in customMixedOrder — their slot is preserved when they're un-hidden (Bug 1 fix).
        // Use getFoldersWithIndependentSort to respect each album's sort option for preview generation.
        val allFolders = repository.getFoldersWithIndependentSort(
            sortOption = s.sortOption,
            getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
        )
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
        val groupedBucketIds = groupRepository.getGroupedBucketIds()
        // allUngroupedFolders (including hidden) feeds applyCustomMixedOrder so hidden
        // folder keys stay in customMixedOrder; ungroupedFolders is the display list.
        val allUngroupedFolders = allFolders.filter { it.bucketId !in groupedBucketIds }
        val ungroupedFolders    = allUngroupedFolders.filter { it.path.isBlank() || it.path !in hiddenPaths }

        // Lookup: bucketId → path, used to determine group visibility
        val bucketPathMap = allFolders.associate { it.bucketId to it.path }
        // A group is visible only when at least one of its member folders is not hidden
        fun isGroupVisible(group: GroupItem) = group.memberBucketIds.any { id ->
            val p = bucketPathMap[id]; p.isNullOrBlank() || p !in hiddenPaths
        }

        val orderedMixed = if (s.sortOption == SortOption.CUSTOM_ORDER) {
            // Compute/save order using ALL ungrouped so positions survive hide/un-hide;
            // then strip hidden entries from the list that's actually rendered.
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
                images = images,
                folders = folders,
                rootGroups = rootGroups,
                ungroupedFolders = ungroupedFolders,
                orderedMixedItems = orderedMixed,
                allGroups = allGroups,
                allGroupCustomOrders = groupCustomOrders,
                allGroupSortOptions = groupSortOptions,
                isLoading = false,
                scrollToTopTrigger = if (scrollToTop) it.scrollToTopTrigger + 1 else it.scrollToTopTrigger
            )
        }
        // Allow ContentObserver to fire again after our explicit refresh is done
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
            val gOrdered: List<Any> = if (gSortOpt == SortOption.CUSTOM_ORDER) {
                val savedOrder = preferences.customGroupItemsOrder(openGroupId)
                if (savedOrder.isEmpty()) {
                    gSubGroups + gFolders
                } else {
                    val byGroupKey  = gSubGroups.associateBy { "g_${it.groupId}"  }
                    val byFolderKey = gFolders.associateBy   { "f_${it.bucketId}" }
                    val savedSet    = savedOrder.toSet()
                    buildList {
                        gSubGroups.forEach { g -> if ("g_${g.groupId}"  !in savedSet) add(g) }
                        gFolders.forEach   { f -> if ("f_${f.bucketId}" !in savedSet) add(f) }
                        for (key in savedOrder) { val item = byGroupKey[key] ?: byFolderKey[key]; if (item != null) add(item) }
                    }
                }
            } else {
                sortMixedItems(gSubGroups + gFolders, gSortOpt, _uiState.value.groupsAlwaysOnTop)
            }
            _uiState.update {
                it.copy(
                    currentGroupFolders           = gFolders,
                    currentGroupSubGroups         = gSubGroups,
                    currentGroupOrderedMixedItems = gOrdered
                )
            }
        }
    }

    /** Reload all data in the background without showing any loading indicator.
     *  Set [scrollToTop] = true to increment [scrollToTopTrigger] after the refresh,
     *  signalling the screen to scroll to position 0 once new items have arrived. */
    private fun silentRefresh(scrollToTop: Boolean = false) {
        viewModelScope.launch { loadDataCore(scrollToTop) }
    }

    /** Force refresh album preview images by reloading folder data. */
    fun refreshAlbumPreviews() {
        viewModelScope.launch {
            silentRefresh()
        }
    }

    /** Reload folderImages in-place (no spinner, no list-clear flicker).
     *
     * @param preserveOrder When **true** and the active sort is [ImageSortOption.CUSTOM_ORDER],
     *   the existing list order is kept: images already present stay at their current position
     *   (even if their [ImageItem.id] was re-assigned by an external edit), and brand-new images
     *   are prepended at the top.  This prevents Samsung Gallery (or any other app) from
     *   disrupting the custom sort when it edits / recreates a file.
     *
     *   For all non-CUSTOM_ORDER sorts the flag is ignored and the list is re-sorted from
     *   MediaStore as normal (the user explicitly chose a sort criterion, so new content should
     *   be integrated into it).
     */
    private fun refreshFolderImages(preserveOrder: Boolean = false) {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        viewModelScope.launch {
            val imgs = repository.getImages(_uiState.value.imageSortOption, bucketId = bucketId)

            if (preserveOrder && _uiState.value.imageSortOption == ImageSortOption.CUSTOM_ORDER) {
                val existing      = _uiState.value.folderImages
                val existingPaths = existing.map { it.path }.toSet()
                // Match by file path — stable even when Samsung Gallery deletes+recreates
                // the file (which changes _ID but keeps the same path).
                val newByPath     = imgs.associateBy { it.path }
                val newById       = imgs.associateBy { it.id }

                // Preserve existing order; refresh item data from the new query.
                // Fall back to _ID match for items without a path.
                val preserved = existing.mapNotNull { old ->
                    if (old.path.isNotBlank()) newByPath[old.path] else newById[old.id]
                }
                // Genuinely new files (path not seen before) → prepend at the top
                val brandNew = imgs.filter { img ->
                    if (img.path.isNotBlank()) img.path !in existingPaths
                    else img.id !in existing.map { it.id }.toSet()
                }
                _uiState.update { it.copy(folderImages = brandNew + preserved) }
            } else {
                _uiState.update { it.copy(folderImages = imgs) }
            }
        }
    }

    /**
     * Build the unified display order of groups + ungrouped folders.
     * Delegates to MixedItemSorter.applyCustomMixedOrder in common module.
     */
    private fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>
    ): List<Any> {
        val savedOrder = preferences.customMixedOrder
        val (result, newOrder) = MixedItemSorter.applyCustomMixedOrder(groups, folders, savedOrder)
        preferences.customMixedOrder = newOrder
        return result
    }

    /**
     * Sort a combined list of [GroupItem]s and [FolderItem]s together.
     * Delegates to MixedItemSorter.sortMixedItems in common module.
     */
    private fun sortMixedItems(
        items: List<Any>,
        option: SortOption,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        return MixedItemSorter.sortMixedItems(items, option, groupsAlwaysOnTop)
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

    fun setViewType(v: ViewType) {
        val s = _uiState.value
        val groupId = s.currentGroupId

        if (s.independentViewTypeEnabled && groupId != null) {
            // Save to per-group storage when inside a group and independent mode is on
            preferences.saveGroupViewType(groupId, v)
        } else {
            // Save to global viewType for root view
            preferences.viewType = v
        }

        _uiState.update { it.copy(viewType = v) }
        scheduleAutoBackup()
    }

    fun cycleViewType() {
        val next = when (_uiState.value.viewType) {
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            else                -> ViewType.GRID_LARGE   // GRID_SMALL and LIST → GRID_LARGE
        }
        setViewType(next)
    }

    fun setFolderViewType(v: ViewType) {
        val s = _uiState.value
        val bucketId = s.currentFolderBucketId

        if (s.independentViewTypeEnabled && bucketId != null) {
            // Save to per-album storage when inside an album and independent mode is on
            preferences.saveFolderViewType(bucketId, v)
        } else {
            // Save to global folderViewType
            preferences.folderViewType = v
        }

        _uiState.update { it.copy(folderViewType = v) }
        scheduleAutoBackup()
    }

    fun cycleFolderViewType() {
        val next = when (_uiState.value.folderViewType) {
            ViewType.GRID_LARGE -> ViewType.GRID_SMALL
            else                -> ViewType.GRID_LARGE   // GRID_SMALL and LIST → GRID_LARGE
        }
        setFolderViewType(next)
    }
    fun setSortOption(s: SortOption) { preferences.sortOption = s; _uiState.update { it.copy(sortOption = s) }; silentRefresh(scrollToTop = true); scheduleAutoBackup() }

    /** Images tab sort (Custom, Name, Date created, Date modified) - for root/all images view. */
    fun setImageSortOption(s: ImageSortOption) {
        preferences.imageSortOption = s
        // Sort existing folder images in-memory immediately so that both
        // imageSortOption and folderImages change in the same recomposition frame.
        // This prevents LazyVerticalGrid's stable keys from re-scrolling when
        // the async data arrives later.
        val sorted = sortImagesInMemory(_uiState.value.folderImages, s)
        _uiState.update { 
            it.copy(
                imageSortOption = s, 
                folderImages = sorted,
                folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
            ) 
        }
        silentRefresh()
        refreshFolderImages()
        scheduleAutoBackup()
    }
    
    /**
     * Change the sort for the currently open folder (independent of the main tab sort).
     * 
     * ⚠️ CRITICAL: Independent sort is ALWAYS enabled.
     * ALWAYS saves album-specific sort (per bucketId).
     * DO NOT add back any checks or toggles - independent sort is mandatory!
     * See docs/INDEPENDENT_SORT_ARCHITECTURE.md for details.
     */
    fun setFolderImageSortOption(s: ImageSortOption) {
        val bucketId = _uiState.value.currentFolderBucketId ?: return
        // Always save album-specific sort (independent sort is now always enabled)
        preferences.saveFolderImageSortOption(bucketId, s)
        // Sort existing folder images in-memory immediately so that both
        // imageSortOption and folderImages change in the same recomposition frame.
        // This prevents LazyVerticalGrid's stable keys from re-scrolling when
        // the async data arrives later.
        val sorted = sortImagesInMemory(_uiState.value.folderImages, s)
        _uiState.update {
            it.copy(
                imageSortOption = s,
                folderImages = sorted,
                folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
            )
        }
        // Refresh the folder images from MediaStore to ensure consistency
        refreshFolderImages()
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
    
    fun setGroupSortOption(s: SortOption) { preferences.groupSortOption = s; _uiState.update { it.copy(groupSortOption = s) }; scheduleAutoBackup() }

    private fun sortImagesInMemory(images: List<ImageItem>, option: ImageSortOption): List<ImageItem> {
        return when (option) {
            // Testing: Try DATE_MODIFIED DESC, _ID ASC to match Samsung Gallery
            ImageSortOption.CUSTOM_ORDER -> images.sortedWith(compareByDescending<ImageItem> { it.dateModified }.thenBy { it.id })
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
            // Keep selection mode active even when deselecting the last item (Samsung Gallery behavior)
            s.copy(selectedImageIds = sel)
        }
    }

    fun toggleFolderSelection(id: Int) {
        _uiState.update { s ->
            val sel = s.selectedFolderIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else sel.add(id)
            // Keep selection mode active even when deselecting the last item (Samsung Gallery behavior)
            s.copy(selectedFolderIds = sel)
        }
    }

    fun toggleGroupSelection(id: Long) {
        _uiState.update { s ->
            val sel = s.selectedGroupIds.toMutableSet()
            if (sel.contains(id)) sel.remove(id) else sel.add(id)
            // Keep selection mode active even when deselecting the last item (Samsung Gallery behavior)
            s.copy(selectedGroupIds = sel)
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
        val s = _uiState.value
        // Load this album's specific sort option
        val albumSort = preferences.getFolderImageSortOption(bucketId)
        // Load folder-specific view type if independent mode is enabled
        val folderViewType = if (s.independentViewTypeEnabled) {
            preferences.getFolderViewType(bucketId)
        } else {
            preferences.folderViewType
        }

        _uiState.update {
            it.copy(
                currentFolderBucketId = bucketId,
                currentFolderName = name,
                imageSortOption = albumSort, // Set to album-specific sort
                folderViewType = folderViewType,
                isSelectionMode = false,
                selectedFolderIds = emptySet(),
                selectedImageIds = emptySet()
            )
        }
        viewModelScope.launch {
            val v = repository.getImages(
                imageSortOption = albumSort,
                bucketId = bucketId,
                allowMediaReordering = _uiState.value.allowMediaReordering,
                customOrder = preferences.getFolderMediaCustomOrder(bucketId)
            )
            _uiState.update { it.copy(folderImages = v) }
        }
    }

    /**
     * Closes the currently open album and returns to root view.
     *
     * ⚠️ CRITICAL: Restores root-level sort option.
     * DO NOT forget to restore imageSortOption to preferences.imageSortOption!
     * See docs/INDEPENDENT_SORT_ARCHITECTURE.md for details.
     */
    fun closeFolder() {
        // Restore global folderViewType when closing
        _uiState.update {
            it.copy(
                currentFolderBucketId = null,
                currentFolderName = "",
                folderImages = emptyList(),
                imageSortOption = preferences.imageSortOption, // Restore root-level sort
                folderViewType = preferences.folderViewType // Restore global view type
            )
        }
    }

    // Carousel
    fun openCarousel(index: Int) = _uiState.update { it.copy(carouselIndex = index) }
    fun closeCarousel() = _uiState.update { it.copy(carouselIndex = -1, currentCarouselPage = -1) }
    fun updateCarouselPage(page: Int) = _uiState.update { it.copy(currentCarouselPage = page) }

    /** Move a single image from the carousel overlay to the system trash. */
    fun deleteCarouselImage(imageId: Long) {
        viewModelScope.launch {
            try {
                val intentSender = repository.trashImages(listOf(imageId))
                pendingTrash = PendingTrash.CarouselImage(imageId)
                _trashRequest.emit(intentSender)
            } catch (e: Exception) {
                android.util.Log.e("ImageListViewModel", "Failed to create trash request", e)
            }
        }
    }

    private fun refreshCurrentFolderIfOpen() {
        refreshFolderImages()
    }

    fun deleteSelectedImages() {
        val idsToDelete = _uiState.value.selectedImageIds
        if (idsToDelete.isEmpty()) return
        _uiState.update { it.copy(isSelectionMode = false, selectedImageIds = emptySet()) }
        viewModelScope.launch {
            try {
                val intentSender = repository.trashImages(idsToDelete.toList())
                pendingTrash = PendingTrash.SelectedImages(idsToDelete)
                _trashRequest.emit(intentSender)
            } catch (e: Exception) {
                android.util.Log.e("ImageListViewModel", "Failed to create trash request", e)
            }
        }
    }

    fun deleteSelectedFolders() {
        val folderIds = _uiState.value.selectedFolderIds
        if (folderIds.isEmpty()) return
        _uiState.update { it.copy(isSelectionMode = false, selectedFolderIds = emptySet()) }
        viewModelScope.launch {
            try {
                val allImageIds = mutableListOf<Long>()
                for (folderId in folderIds) {
                    val images = repository.getImages(bucketId = folderId)
                    allImageIds.addAll(images.map { it.id })
                }
                if (allImageIds.isEmpty()) return@launch
                val intentSender = repository.trashImages(allImageIds)
                pendingTrash = PendingTrash.SelectedFolders(folderIds)
                _trashRequest.emit(intentSender)
            } catch (e: Exception) {
                android.util.Log.e("ImageListViewModel", "Failed to create trash request", e)
            }
        }
    }

    fun onTrashConfirmed() {
        val pending = pendingTrash ?: return
        pendingTrash = null
        when (pending) {
            is PendingTrash.CarouselImage -> {
                _uiState.update { s ->
                    s.copy(
                        folderImages = s.folderImages.filter { it.id != pending.id },
                        images = s.images.filter { it.id != pending.id }
                    )
                }
                closeCarousel()
                viewModelScope.launch {
                    isInternalChange.set(true)
                    silentRefresh()
                    refreshFolderImages()
                }
            }
            is PendingTrash.SelectedImages -> {
                val ids = pending.ids
                _uiState.update { s ->
                    s.copy(
                        folderImages = s.folderImages.filter { it.id !in ids },
                        images = s.images.filter { it.id !in ids }
                    )
                }
                viewModelScope.launch {
                    isInternalChange.set(true)
                    silentRefresh()
                    refreshFolderImages()
                }
            }
            is PendingTrash.SelectedFolders -> {
                val ids = pending.folderIds
                _uiState.update { s ->
                    s.copy(
                        orderedMixedItems = s.orderedMixedItems.filter { item ->
                            item !is FolderItem || item.bucketId !in ids
                        },
                        folders = s.folders.filter { it.bucketId !in ids },
                        ungroupedFolders = s.ungroupedFolders.filter { it.bucketId !in ids }
                    )
                }
                viewModelScope.launch {
                    isInternalChange.set(true)
                    silentRefresh()
                }
            }
        }
    }

    fun onTrashCancelled() {
        pendingTrash = null
    }

    fun renameImage(id: Long, name: String) {
        viewModelScope.launch {
            isInternalChange.set(true)
            repository.renameImage(id, name)
            silentRefresh()
            refreshFolderImages()
        }
    }

    // Share
    fun shareSelectedImages() {
        viewModelScope.launch {
            val s = _uiState.value
            val uris = s.folderImages
                .filter { it.id in s.selectedImageIds }
                .map { it.contentUri }
            if (uris.isEmpty()) return@launch

            // Use ACTION_SEND for single item, ACTION_SEND_MULTIPLE for multiple items
            val intent = if (uris.size == 1) {
                Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_STREAM, uris.first())
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            } else {
                Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "image/*"
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
                repository.getImages(bucketId = bucketId).mapTo(uris) { it.contentUri }
            }
            for (groupId in s.selectedGroupIds) {
                val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
                for (bucketId in bucketIds) {
                    repository.getImages(bucketId = bucketId).mapTo(uris) { it.contentUri }
                }
            }
            if (uris.isEmpty()) return@launch

            // Use ACTION_SEND for single item, ACTION_SEND_MULTIPLE for multiple items
            val intent = if (uris.size == 1) {
                Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_STREAM, uris.first())
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            } else {
                Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "image/*"
                    putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            }
            _shareIntent.emit(intent)
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

    fun moveSelectedImages(dest: String) {
        val images = getSelectedImages()
        if (images.isEmpty()) return
        _uiState.update { it.copy(showMoveFolderPicker = false) }
        exitSelectionMode()

        // Reset bulk resolution for new operation
        bulkResolution = null

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

        // Reset bulk resolution for new operation
        bulkResolution = null
        
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

    fun updateCarouselAlwaysHideOverlay(value: Boolean) {
        preferences.carouselAlwaysHideOverlay = value
        _uiState.update { it.copy(carouselAlwaysHideOverlay = value) }
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

    /**
     * Restore from Documents/ImageLibrary/backups/backup.json.
     * Suspends until [loadDataCore] finishes so the caller only receives `true`
     * once the list is fully settled — hidden albums and custom positions are
     * already in their final state before navigation occurs.
     */
    suspend fun restoreBackupFromFile(): Boolean {
        isRestoringBackup = true
        val ok = withContext(Dispatchers.IO) {
            com.imagelibrary.data.util.BackupManager.restoreBackupFromFile(getApplication())
        }
        if (ok) {
            isRestoringBackup = false

            // Refresh UI state with restored preferences
            _uiState.update {
                it.copy(
                    viewType                  = preferences.viewType,
                    folderViewType            = preferences.folderViewType,
                    sortOption                = preferences.sortOption,
                    imageSortOption           = preferences.imageSortOption,
                    carouselShowBarsOnOpen    = preferences.carouselShowBarsOnOpen,
                    carouselAlwaysHideOverlay = preferences.carouselAlwaysHideOverlay,
                    autoBackupEnabled         = preferences.autoBackupEnabled,
                    independentSortEnabled    = preferences.independentSortEnabled,
                    independentViewTypeEnabled = preferences.independentViewTypeEnabled,
                    groupsAlwaysOnTop         = preferences.groupsAlwaysOnTop,
                    floatingTopBarEnabled     = preferences.floatingTopBarEnabled
                )
            }

            // Await full reload so the UI is settled before the caller navigates away
            loadDataCore()

            // If inside a group, refresh to apply restored group sort option and view type
            val currentGroupId = _uiState.value.currentGroupId
            if (currentGroupId != null) {
                val restoredGroupSort = preferences.getGroupSortOption(currentGroupId)
                val restoredGroupViewType = if (preferences.independentViewTypeEnabled) {
                    preferences.getGroupViewType(currentGroupId)
                } else {
                    preferences.viewType
                }
                _uiState.update {
                    it.copy(
                        currentGroupSortOption = restoredGroupSort,
                        viewType = restoredGroupViewType
                    )
                }
                refreshCurrentGroup()
            }

            // If inside a folder, refresh to apply restored folder sort option and view type
            val currentFolderBucketId = _uiState.value.currentFolderBucketId
            if (currentFolderBucketId != null) {
                val restoredFolderSort = preferences.getFolderImageSortOption(currentFolderBucketId)
                val restoredFolderViewType = if (preferences.independentViewTypeEnabled) {
                    preferences.getFolderViewType(currentFolderBucketId)
                } else {
                    preferences.folderViewType
                }
                _uiState.update {
                    it.copy(
                        imageSortOption = restoredFolderSort,
                        folderViewType = restoredFolderViewType
                    )
                }
                // Reload folder images with restored sort
                viewModelScope.launch {
                    val v = repository.getImages(restoredFolderSort, bucketId = currentFolderBucketId)
                    _uiState.update { it.copy(folderImages = v) }
                }
            }
        } else {
            isRestoringBackup = false
        }
        return ok
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
                carouselAlwaysHideOverlay = preferences.carouselAlwaysHideOverlay,
                autoBackupEnabled = preferences.autoBackupEnabled,
                independentSortEnabled = preferences.independentSortEnabled,
                independentViewTypeEnabled = preferences.independentViewTypeEnabled,
                groupsAlwaysOnTop = preferences.groupsAlwaysOnTop,
                floatingTopBarEnabled = preferences.floatingTopBarEnabled
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
        viewModelScope.launch {
            val allNames = groupRepository.getAllGroups().map { it.name }.toSet()
            val suggested = com.example.common.ui.viewmodel.GroupCreationUtils.generateUniqueGroupName(allNames)
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

    /** Delegates to GroupCreationUtils.generateUniqueGroupName in common module. */
    private fun generateUniqueGroupName(existingNames: Set<String>): String {
        return com.example.common.ui.viewmodel.GroupCreationUtils.generateUniqueGroupName(existingNames)
    }

    /** Enters group creation mode with an already-chosen name (skips the name dialog at the end) */
    fun enterGroupCreationModeWithName(name: String) {
        // Preserve any pre-populated selections (from bottom bar flow)
        val s = _uiState.value
        _uiState.update {
            it.copy(
                showGroupNameDialog = false,
                groupNameDialogForCreation = false,
                isGroupCreationMode = true,
                isSelectionMode = false, // Exit regular selection mode
                pendingGroupCreationName = name,
                // Save the current group ID as the parent for the new nested group
                pendingGroupCreationParentId = s.currentGroupId,
                // Keep pre-populated folder selections, but clear group selections (groups not selectable)
                groupCreationSelectedFolderIds = s.groupCreationSelectedFolderIds,
                groupCreationSelectedGroupIds = emptySet(),
                // Clear regular selection state to prevent groups from appearing selected
                selectedFolderIds = emptySet(),
                selectedGroupIds = emptySet(),
                selectedImageIds = emptySet(),
                // Temporarily exit group view to show checkbox selection at root level
                currentGroupId = null,
                currentGroupName = "",
                currentGroupFolders = emptyList(),
                currentGroupSubGroups = emptyList(),
                currentGroupOrderedMixedItems = emptyList()
            )
        }
    }

    fun createGroupFromCreationMode(name: String) {
        val s = _uiState.value
        val folderIds = s.groupCreationSelectedFolderIds.toList()
        val groupIds = emptyList<Long>() // Groups are not selectable during creation
        val parentGroupId = s.pendingGroupCreationParentId

        viewModelScope.launch {
            val newGroupId = groupRepository.createGroup(
                name = name,
                folderBucketIds = folderIds,
                subGroupIds = groupIds,
                parentGroupId = parentGroupId
            )
            // Prepend the new group at position 0 — always, regardless of current sort option.
            // If sort isn't CUSTOM_ORDER yet, snapshot the current visible order first.
            if (parentGroupId == null) {
                prependToRootOrder("g_$newGroupId")
            } else {
                prependToGroupOrder("g_$newGroupId", parentGroupId, s)
            }
            exitGroupCreationMode()
            silentRefresh()
            // If we created a nested group, navigate back into the parent group
            if (parentGroupId != null) {
                val parentGroup = groupRepository.getGroupById(parentGroupId)
                parentGroup?.let { group ->
                    openGroup(group.groupId, group.name)
                }
            }
            scheduleAutoBackup()
        }
    }

    /** Called from bottom bar flow (long-press selection → Group button → name dialog) */
    fun showGroupNameDialogForBottomBar() {
        // Pre-populate creation selections with current selection
        val s = _uiState.value
        _uiState.update {
            it.copy(
                showGroupNameDialog = true,
                groupNameDialogForCreation = true,
                // Pre-populate with already-selected items
                groupCreationSelectedFolderIds = s.selectedFolderIds,
                groupCreationSelectedGroupIds = s.selectedGroupIds
            )
        }
        // Exit selection mode since we're entering creation mode
        exitSelectionMode()
    }

    fun createGroupFromSelection(name: String) {
        val s = _uiState.value
        val folderIds = s.selectedFolderIds.toList()
        val groupIds = s.selectedGroupIds.toList()
        val parentGroupId = s.currentGroupId

        viewModelScope.launch {
            val newGroupId = groupRepository.createGroup(
                name = name,
                folderBucketIds = folderIds,
                subGroupIds = groupIds,
                parentGroupId = parentGroupId
            )
            // Prepend the new group at position 0 — always, regardless of current sort option.
            if (parentGroupId == null) {
                prependToRootOrder("g_$newGroupId")
            } else {
                prependToGroupOrder("g_$newGroupId", parentGroupId, s)
            }
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

        // Load group data FIRST, then update state with everything together to avoid empty state flash
        viewModelScope.launch {
            val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
            // Reload sort options from preferences to get the latest changes
            val allGroups = groupRepository.getAllGroups()
            val groupSortOptions = allGroups.associate { it.groupId to preferences.getGroupSortOption(it.groupId).id }
            val groupCustomOrders = allGroups.associate { it.groupId to preferences.customGroupItemsOrder(it.groupId) }
            val allSubGroups = groupRepository.getChildGroups(
                parentGroupId = groupId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            // Filter from the globally-sorted folders list so non-custom sorts display correctly
            val bucketIdSet = bucketIds.toSet()
            val groupFolders = s.folders.filter { it.bucketId in bucketIdSet }
            // Hide sub-groups whose every direct album is hidden
            val visibleBucketSet = s.folders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            // Use the group's own independent sort option
            val groupSortOption = groupSort
            val orderedMixed = if (groupSortOption == SortOption.CUSTOM_ORDER) {
                GroupMixedOrderUtil.applyCustomGroupMixedOrder(groupId, subGroups, groupFolders, preferences)
            } else {
                sortMixedItems(subGroups + groupFolders, groupSortOption, s.groupsAlwaysOnTop)
            }

            // Load group-specific view type if independent mode is enabled
            val groupViewType = if (s.independentViewTypeEnabled) {
                preferences.getGroupViewType(groupId)
            } else {
                preferences.viewType
            }

            // Update state with group ID and data together — no empty state flash
            _uiState.update {
                it.copy(
                    currentGroupId                = groupId,
                    currentGroupName              = name,
                    groupStack                    = newStack,
                    currentGroupSortOption        = groupSort,
                    currentGroupFolders           = groupFolders,
                    currentGroupSubGroups         = subGroups,
                    currentGroupOrderedMixedItems = orderedMixed,
                    viewType                      = groupViewType
                )
            }
        }
    }

    fun closeGroup() {
        val s = _uiState.value
        if (s.groupStack.isNotEmpty()) {
            // Pop from stack
            val (prevId, prevName) = s.groupStack.last()
            val parentSort = preferences.getGroupSortOption(prevId)
            val parentViewType = if (s.independentViewTypeEnabled) {
                preferences.getGroupViewType(prevId)
            } else {
                preferences.viewType
            }
            _uiState.update {
                it.copy(
                    currentGroupId = prevId,
                    currentGroupName = prevName,
                    groupStack = s.groupStack.dropLast(1),
                    currentGroupFolders = emptyList(),
                    currentGroupSubGroups = emptyList(),
                    currentGroupSortOption = parentSort,
                    viewType = parentViewType
                )
            }
            refreshCurrentGroup()
        } else {
            // Returning to root - restore root view type
            val rootViewType = preferences.viewType
            _uiState.update {
                it.copy(
                    currentGroupId = null,
                    currentGroupName = "",
                    currentGroupFolders = emptyList(),
                    currentGroupSubGroups = emptyList(),
                    groupStack = emptyList(),
                    currentGroupSortOption = SortOption.CUSTOM_ORDER,
                    viewType = rootViewType
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
                sortOption = s.sortOption,
                getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
            )
            // Filter from the globally-sorted list so non-custom sorts display correctly
            val bucketIdSet  = bucketIds.toSet()
            val groupFolders = allFolders.filter { it.bucketId in bucketIdSet }
            // Reload sort options from preferences to get the latest changes
            val allGroups = groupRepository.getAllGroups()
            val groupSortOptions = allGroups.associate { it.groupId to preferences.getGroupSortOption(it.groupId).id }
            val groupCustomOrders = allGroups.associate { it.groupId to preferences.customGroupItemsOrder(it.groupId) }
            val allSubGroups = groupRepository.getChildGroups(
                parentGroupId = groupId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            // Hide sub-groups whose every direct album is hidden
            val visibleBucketSet = allFolders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            // Use the group's own sort option (independent of the root sort)
            val groupSortOption = s.currentGroupSortOption

            val orderedMixed: List<Any> = if ( groupSortOption == SortOption.CUSTOM_ORDER) {
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
            // Prepend the new group at position 0 — always, regardless of current sort option.
            prependToRootOrder("g_$newGroupId")
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

        // Capture the current group context before clearing UI state
        val parentGroupId = s.currentGroupId

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
            refreshFolderImages()

            // Prepend the new album at position 0 BEFORE calling loadDataCore().
            // If loadDataCore() were called first it would launch a concurrent loadDataCore
            // coroutine that still uses the old sort option; that coroutine could complete
            // after the second refresh and overwrite the correct position.
            if (parentGroupId != null) {
                val newBucketId = findFolderBucketIdByName(folderName)
                if (newBucketId != null) {
                    groupRepository.addFoldersToGroup(parentGroupId, listOf(newBucketId))
                    prependToGroupOrder("f_$newBucketId", parentGroupId, s)
                    loadDataCore()
                    refreshCurrentGroup()
                } else {
                    loadDataCore() // fallback: bucket not found, still refresh
                }
            } else {
                val newBucketId = findFolderBucketIdByName(folderName)
                if (newBucketId != null) {
                    prependToRootOrder("f_$newBucketId")
                }
                loadDataCore() // single refresh after prepend (or as fallback)
                // Safety net: if the new album landed somewhere other than position 0,
                // forcefully move it to the top (covers any remaining timing edge-cases).
                if (newBucketId != null) {
                    val key = "f_$newBucketId"
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

    /**
     * Find the bucketId of a newly created folder by its name.
     * Queries the repository directly (not UI state) with retries so that MediaStore
     * has time to index the new folder before we give up.
     */
    private suspend fun findFolderBucketIdByName(folderName: String): Int? {
        repeat(6) { attempt ->
            val folders = repository.getFolders()
            val found = folders.find { it.name.equals(folderName, ignoreCase = true) }?.bucketId
            if (found != null) return found
            delay(500) // wait for MediaStore to index the new folder, then retry
        }
        return null
    }

    /**
     * Ensures the root-level sort is CUSTOM_ORDER (snapshotting the current visible order
     * if needed), then prepends [newKey] so the new item always appears at position 0.
     *
     * Uses the CURRENT live UIState (not a stale snapshot) so the order is always accurate
     * even when called after a long-running async operation.
     *
     * IMPORTANT: Also snapshots when sort is already CUSTOM_ORDER but no order has been saved
     * yet (never manually reordered). Without this snapshot, all existing items would be
     * treated as "new" by applyCustomMixedOrder and placed BEFORE the new item, pushing it
     * to the end of the list.
     */
    private fun prependToRootOrder(newKey: String) {
        val current = _uiState.value
        if (current.sortOption != SortOption.CUSTOM_ORDER) {
            // Snapshot the current visible order and switch sort to CUSTOM_ORDER
            val snapshot = current.orderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> null
                }
            }
            preferences.sortOption = SortOption.CUSTOM_ORDER
            preferences.customMixedOrder = snapshot
            _uiState.update { it.copy(sortOption = SortOption.CUSTOM_ORDER) }
        } else if (preferences.customMixedOrder.isEmpty()) {
            // Sort is already CUSTOM_ORDER but the root has never been explicitly ordered.
            // Snapshot current items so they are in savedSet; the new item will then be
            // prepended at position 0 instead of appearing at the end.
            val snapshot = current.orderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> null
                }
            }
            preferences.customMixedOrder = snapshot
        }
        val existing = preferences.customMixedOrder
        if (newKey !in existing) {
            preferences.customMixedOrder = listOf(newKey) + existing
        }
    }

    /**
     * Ensures the given group's sort is CUSTOM_ORDER (snapshotting the current visible order
     * if needed), then prepends [newKey] so the new item always appears at position 0.
     *
     * IMPORTANT: Also snapshots when sort is already CUSTOM_ORDER but no order has been saved
     * yet (group was never manually reordered). Without this snapshot, all existing items would
     * be treated as "new" by loadDataCore and placed BEFORE the new item, pushing it to the end.
     */
    private fun prependToGroupOrder(newKey: String, groupId: Long, s: ImageListUiState) {
        if (preferences.getGroupSortOption(groupId) != SortOption.CUSTOM_ORDER) {
            // Snapshot the current group order and switch sort to CUSTOM_ORDER
            val snapshot = s.currentGroupOrderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> null
                }
            }
            preferences.saveGroupSortOption(groupId, SortOption.CUSTOM_ORDER)
            preferences.saveGroupMixedOrder(groupId, snapshot)
            _uiState.update { it.copy(currentGroupSortOption = SortOption.CUSTOM_ORDER) }
        } else if (preferences.getGroupMixedOrder(groupId).isEmpty()) {
            // Sort is already CUSTOM_ORDER but the group has never been explicitly ordered.
            // Snapshot current items so they are in savedSet; the new item will then be
            // prepended at position 0 instead of appearing at the end.
            val snapshot = s.currentGroupOrderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem  -> "g_${item.groupId}"
                    is FolderItem -> "f_${item.bucketId}"
                    else          -> null
                }
            }
            preferences.saveGroupMixedOrder(groupId, snapshot)
        }
        val existing = preferences.getGroupMixedOrder(groupId)
        if (newKey !in existing) {
            preferences.saveGroupMixedOrder(groupId, listOf(newKey) + existing)
        }
    }

}
