package com.videolibrary.ui.screen

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.common.data.util.FileManagerHelper
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.CopyMoveAndConflictOverlayHost
import com.example.common.ui.components.ScreenTopBar
import com.videolibrary.data.model.ViewType
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoSortOption
import com.example.common.ui.components.SortDialog
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.DeleteConfirmDialog
import com.example.common.ui.components.GroupNameDialog
import com.example.common.ui.components.CreateAlbumDialog
import com.example.common.ui.components.CopyMoveAlbumDialog
import com.example.common.ui.components.CreateFolderDialog
import com.example.common.ui.components.DestroyGroupDialog
import com.example.common.ui.components.RenameDialog
import com.videolibrary.ui.components.*
import com.videolibrary.ui.theme.LocalVideoColors
import com.videolibrary.ui.viewmodel.VideoListViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun VideoListScreen(
    viewModel: VideoListViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val progress by viewModel.copyMoveProgress.collectAsState()
    val conflict by viewModel.fileConflict.collectAsState()
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val folderListState = rememberLazyListState()
    val folderGridState = rememberLazyGridState()
    val videoListState = rememberLazyListState()
    val videoGridState = rememberLazyGridState()
    val colors = LocalVideoColors.current
    var showMoreMenu by remember { mutableStateOf(false) }
    var showCreateMenu by remember { mutableStateOf(false) }

    LaunchedEffect(state.scrollToTopTrigger) {
        if (state.scrollToTopTrigger > 0) {
            folderListState.scrollToItem(0)
            folderGridState.scrollToItem(0)
            videoListState.scrollToItem(0)
            videoGridState.scrollToItem(0)
        }
    }

    // Collect share intents emitted by the ViewModel and launch the system chooser
    LaunchedEffect(Unit) {
        viewModel.shareIntent.collect { intent ->
            ctx.startActivity(Intent.createChooser(intent, null))
        }
    }

    val hasOverlay = state.showDeleteDialog || state.showSortDialog || state.showViewAsDialog ||
            state.showRenameDialog || state.showCreateFolderDialog || state.showDetailsDialog ||
            state.showMoveFolderPicker || state.showCopyFolderPicker ||
            state.showGroupNameDialog || state.showRenameGroupDialog ||
            state.showAbout || state.showSettings || state.isSearchActive ||
            state.showMoveToGroupPicker || showMoreMenu || showCreateMenu

    BackHandler(
        enabled = hasOverlay || state.isGroupCreationMode || state.isSelectionMode ||
                state.currentGroupId != null || state.currentFolderBucketId != null || progress.isActive
    ) {
        when {
            progress.isActive            -> { /* consume back press */ }
            showCreateMenu               -> showCreateMenu = false
            showMoreMenu                 -> showMoreMenu = false
            state.showSettings           -> viewModel.dismissSettings()
            state.showGroupNameDialog    -> viewModel.dismissGroupNameDialog()
            state.showRenameGroupDialog  -> viewModel.dismissRenameGroupDialog()
            state.showDestroyGroupDialog -> viewModel.dismissDestroyGroupDialog()
            state.showMoveToGroupPicker  -> viewModel.dismissMoveToGroupPicker()
            state.showDeleteDialog       -> viewModel.dismissDeleteDialog()
            state.showCreateAlbumDialog  -> viewModel.dismissCreateAlbumDialog()
            state.showViewAsDialog       -> viewModel.dismissViewAsDialog()
            state.showRenameDialog       -> viewModel.dismissRenameDialog()
            state.showCreateFolderDialog -> viewModel.dismissCreateFolderDialog()
            state.showDetailsDialog      -> viewModel.dismissVideoDetails()
            state.showMoveFolderPicker   -> viewModel.dismissMoveFolderPicker()
            state.isSelectionMode        -> viewModel.exitSelectionMode()
            state.currentGroupId != null -> viewModel.closeGroup()
            state.currentFolderBucketId != null -> {
                viewModel.exitSelectionMode()
                viewModel.closeFolder()
            }
            state.isSearchActive -> viewModel.deactivateSearch()
        }
    }

    // ── Create Album picker (full-screen) ────────────────────────────────────
    if (state.showCreateAlbumPicker) {
        CreateAlbumPickerScreen(
            albumName        = state.pendingAlbumName,
            rootGroups       = state.rootGroups,
            ungroupedFolders = state.ungroupedFolders,
            allFolders       = state.folders,
            browsedVideos    = state.albumCreationBrowsedVideos,
            currentBucketId  = state.albumCreationCurrentBucketId,
            selectedVideoIds = state.albumCreationSelectedVideoIds,
            allVideos        = state.videos,
            onFolderOpen     = { folder -> viewModel.loadAlbumCreationVideos(folder.bucketId, folder.name) },
            onFolderClose    = { viewModel.closeAlbumCreationFolder() },
            onToggleVideo    = { viewModel.toggleAlbumCreationVideoSelection(it) },
            onDone           = { viewModel.showCreateAlbumCopyMoveDialog() },
            onBack           = { viewModel.cancelAlbumCreation() }
        )
        if (state.showCreateAlbumCopyMoveDialog) {
            CopyMoveAlbumDialog(
                itemCount = state.albumCreationSelectedVideoIds.size,
                itemLabel = "video",
                onCopy    = { viewModel.confirmAlbumCreation(copy = true) },
                onMove    = { viewModel.confirmAlbumCreation(copy = false) },
                onCancel  = { viewModel.dismissCreateAlbumCopyMoveDialog() }
            )
        }
        return
    }

    // ── Group detail screen ──────────────────────────────────────────────────
    if (state.currentGroupId != null) {
        GroupDetailScreen(
            groupName         = state.currentGroupName,
            folders           = state.currentGroupFolders,
            subGroups         = state.currentGroupSubGroups,
            viewType          = state.folderViewType,
            sortOption        = state.sortOption,
            isSelectionMode   = state.isSelectionMode,
            selectedFolderIds = state.selectedFolderIds,
            selectedGroupIds  = state.selectedGroupIds,
            onBack = { viewModel.closeGroup() },
            onFolderClick = { folder ->
                // Read the LIVE StateFlow value — not the lambda-captured 'state' snapshot —
                // to prevent a stale isSelectionMode=true from routing a tap to
                // toggleFolderSelection when the user intends to open the folder.
                if (viewModel.uiState.value.isSelectionMode)
                    viewModel.toggleFolderSelection(folder.bucketId)
                else
                    viewModel.openFolder(folder.bucketId, folder.name)
            },
            onFolderLongClick = { folder ->
                viewModel.enterSelectionMode()
                viewModel.toggleFolderSelection(folder.bucketId)
            },
            onGroupClick = { group ->
                if (viewModel.uiState.value.isSelectionMode)
                    viewModel.toggleGroupSelection(group.groupId)
                else
                    viewModel.openGroup(group.groupId, group.name)
            },
            onGroupLongClick = { group ->
                viewModel.enterSelectionMode()
                viewModel.toggleGroupSelection(group.groupId)
            },
            onCycleViewType      = { viewModel.cycleFolderViewType() },
            onAddFolder          = { viewModel.showAddFolderToGroup() },
            onRenameGroup        = { viewModel.showRenameGroupDialog() },
            onDestroyGroup       = { viewModel.showDestroyGroupDialog() },
            onSortOptionSelected = { viewModel.setSortOption(it) },
            onDelete             = { viewModel.showDeleteDialog() },
            onGroup              = { viewModel.showGroupNameDialogForBottomBar() },
            onSelectAll          = { viewModel.selectAllInGroup() },
            onCancelSelection    = { viewModel.exitSelectionMode() },
            onCreateAlbum        = { viewModel.showCreateAlbumDialog() },
            onViewAs   = { viewModel.showViewAsDialog() },
            onSettings = { viewModel.showSettings() },
            onAbout    = { viewModel.showAbout() },
            // ── Drag-to-reorder ──────────────────────────────────────────
            orderedMixedItems = state.currentGroupOrderedMixedItems,
            onReorderFolders  = { from, to -> viewModel.reorderGroupItem(from, to) },
            onReorderDone     = { viewModel.persistGroupOrder() },
            // ── Bottom bar extras ────────────────────────────────────────
            onShare = {
                val paths = state.currentGroupFolders
                    .filter { it.bucketId in state.selectedFolderIds }
                    .map { it.path }
                if (paths.isNotEmpty()) {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, paths.joinToString("\n"))
                    }
                    ctx.startActivity(Intent.createChooser(shareIntent, null))
                }
            },
            onMove = { viewModel.showMoveToGroupPicker() },
            onRemoveFromGroup = { viewModel.removeSelectedFromGroup() },
            onOpenLocation = {
                viewModel.getSelectedLocationPath()?.let { path ->
                    FileManagerHelper.openFolder(ctx, path)
                }
            }
        )

        if (state.showRenameGroupDialog) {
            GroupNameDialog(
                initialName  = state.currentGroupName,
                title        = "Rename group",
                confirmLabel = "Rename",
                onConfirm    = { viewModel.renameCurrentGroup(it) },
                onDismiss    = { viewModel.dismissRenameGroupDialog() }
            )
        }
        if (state.showDestroyGroupDialog) {
            DestroyGroupDialog(
                groupName = state.currentGroupName,
                onConfirm = { viewModel.destroyCurrentGroup() },
                onDismiss = { viewModel.dismissDestroyGroupDialog() }
            )
        }
        if (state.showGroupNameDialog) {
            val isCreation = state.groupNameDialogForCreation
            GroupNameDialog(
                initialName  = if (isCreation) state.suggestedGroupName else "Group 1",
                title        = if (isCreation) "New group" else "Create group",
                confirmLabel = "Create",
                existingNames = if (isCreation) state.existingGroupNames else emptySet(),
                onConfirm = { name ->
                    when {
                        isCreation                        -> viewModel.enterGroupCreationModeWithName(name)
                        state.groupNameDialogForBottomBar -> viewModel.createGroupFromSelection(name)
                        else                              -> viewModel.createGroupFromCreationMode(name)
                    }
                },
                onDismiss = { viewModel.dismissGroupNameDialog() }
            )
        }
        if (state.showDeleteDialog) {
            val selFolders = state.currentGroupFolders.filter { it.bucketId in state.selectedFolderIds }
            val selGroups  = state.currentGroupSubGroups.filter { it.groupId in state.selectedGroupIds }
            DeleteConfirmDialog(
                count           = state.selectedFolderIds.size + state.selectedGroupIds.size,
                isFolder        = true,
                albumCount      = selFolders.size,
                groupCount      = selGroups.size,
                totalItemCount  = selFolders.sumOf { it.itemCount } + selGroups.sumOf { it.totalItemCount },
                itemName        = "video",
                folderName      = "folder",
                onConfirm       = { viewModel.deleteSelectedFolders() },
                onDismiss       = { viewModel.dismissDeleteDialog() }
            )
        }
        if (state.showViewAsDialog) {
            ViewAsDialog(
                currentViewType    = state.folderViewType,
                onViewTypeSelected = { viewModel.setFolderViewType(it) },
                onDismiss          = { viewModel.dismissViewAsDialog() }
            )
        }
        if (state.showAddFolderToGroup) {
            AddFolderToGroupScreen(
                allFolders     = state.folders,
                allGroups      = state.rootGroups + state.currentGroupSubGroups,
                currentGroupId = state.currentGroupId!!,
                viewType       = state.folderViewType,
                onSave         = { folderIds, groupIds -> viewModel.addFoldersToCurrentGroup(folderIds, groupIds) },
                onCancel       = { viewModel.dismissAddFolderToGroup() }
            )
        }
        if (state.showMoveToGroupPicker) {
            MoveToGroupPickerDialog(
                groups    = state.rootGroups,
                onMove    = { viewModel.moveSelectionToGroup(it) },
                onDismiss = { viewModel.dismissMoveToGroupPicker() }
            )
        }
        return
    }

    // ── Folder detail screen ─────────────────────────────────────────────────
    if (state.currentFolderBucketId != null) {
        FolderDetailScreen(
            folderName      = state.currentFolderName,
            videos          = state.folderVideos,
            viewType        = state.folderViewType,
            isSelectionMode = state.isSelectionMode,
            selectedIds     = state.selectedVideoIds,
            onBack = {
                viewModel.exitSelectionMode()
                viewModel.closeFolder()
            },
            onVideoClick = { video ->
                if (state.isSelectionMode) viewModel.toggleVideoSelection(video.id)
                else viewModel.playVideo(ctx, video)
            },
            onVideoLongClick = { video ->
                viewModel.enterSelectionMode()
                viewModel.toggleVideoSelection(video.id)
            },
            onCycleViewType = { viewModel.cycleFolderViewType() },
            onCopy          = { viewModel.showCopyFolderPicker() },
            onMove          = { viewModel.showMoveFolderPicker() },
            onDelete        = { viewModel.showDeleteDialog() },
            onShare         = { viewModel.shareSelectedVideos() },
            onDetails = {
                if (state.selectedVideoIds.size == 1) {
                    val video = state.folderVideos.find { it.id == state.selectedVideoIds.first() }
                    video?.let { viewModel.showVideoDetails(it) }
                }
            },
            onOpenLocation = {
                viewModel.getSelectedLocationPath()?.let { path ->
                    FileManagerHelper.openFolder(ctx, path)
                }
            },
            onEdit = { viewModel.enterSelectionMode() },
            onSelectAll = {
                val allSelected = state.folderVideos.isNotEmpty() &&
                        state.selectedVideoIds.size == state.folderVideos.size
                if (allSelected) viewModel.deselectAllVideos()
                else state.folderVideos.forEach { viewModel.toggleVideoSelection(it.id) }
            },
            onSortBy           = { viewModel.showSortDialog() },
            onViewAs           = { viewModel.showViewAsDialog() },
            onSettings         = { viewModel.showSettings() },
            onAbout            = { viewModel.showAbout() },
            scrollToTopTrigger = state.folderDetailScrollToTopTrigger
        )

        if (state.showViewAsDialog) {
            ViewAsDialog(
                currentViewType    = state.folderViewType,
                onViewTypeSelected = { viewModel.setFolderViewType(it) },
                onDismiss          = { viewModel.dismissViewAsDialog() }
            )
        }
        if (state.showDeleteDialog) {
            DeleteConfirmDialog(
                count     = state.selectedVideoIds.size,
                isFolder  = false,
                itemName  = "video",
                onConfirm = { viewModel.deleteSelectedVideos() },
                onDismiss = { viewModel.dismissDeleteDialog() }
            )
        }
        if (state.showDetailsDialog && state.detailsTarget != null) {
            VideoDetailsDialog(
                video     = state.detailsTarget!!,
                onDismiss = { viewModel.dismissVideoDetails() }
            )
        }
        if (state.showSortDialog) {
            SortDialog(
                options           = VideoSortOption.entries,
                labelFor          = { it.label },
                currentOption     = state.currentFolderSortOption,
                onOptionSelected  = { viewModel.setFolderSortOption(it) },
                onDismiss         = { viewModel.dismissSortDialog() }
            )
        }
        if (state.showMoveFolderPicker) {
            FolderPickerScreen(
                title                   = "Move to",
                folders                 = state.folders,
                groups                  = state.rootGroups + state.currentGroupSubGroups,
                orderedMixedItems       = state.orderedMixedItems,
                onFolderSelected        = { viewModel.moveSelectedVideos(it) },
                onBack                  = { viewModel.dismissMoveFolderPicker() },
                onCreateFolderAndSelect = { viewModel.createFolderAndMoveVideos(it) }
            )
        }
        if (state.showCopyFolderPicker) {
            FolderPickerScreen(
                title                   = "Copy to",
                folders                 = state.folders,
                groups                  = state.rootGroups + state.currentGroupSubGroups,
                orderedMixedItems       = state.orderedMixedItems,
                onFolderSelected        = { viewModel.copySelectedVideos(it) },
                onBack                  = { viewModel.dismissCopyFolderPicker() },
                onCreateFolderAndSelect = { viewModel.createFolderAndCopyVideos(it) }
            )
        }
        CopyMoveAndConflictOverlayHost(
            isProgressActive = progress.isActive,
            progressTitle = progress.title,
            progressCurrent = progress.current,
            progressTotal = progress.total,
            onCancelProgress = { viewModel.cancelCopyMove() },
            conflictFileName = conflict?.fileName,
            onReplaceConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.REPLACE) },
            onRenameConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.RENAME) },
            onSkipConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.SKIP) },
            onSkipAllConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.SKIP_ALL) },
            onReplaceAllConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.REPLACE_ALL) },
            renameActionLabel = "Keep Both"
        )
        return
    }

    // ── Search screen ────────────────────────────────────────────────────────
    if (state.isSearchActive) {
        SearchScreen(
            query         = state.searchQuery,
            results       = state.searchResults,
            onQueryChange = { viewModel.setSearchQuery(it) },
            onBack        = { viewModel.deactivateSearch() },
            onVideoClick  = { viewModel.playVideo(ctx, it) }
        )
        return
    }

    // ── About screen ─────────────────────────────────────────────────────────
    if (state.showAbout) {
        AboutScreen(onBack = { viewModel.dismissAbout() })
        return
    }

    // ── Settings screen ───────────────────────────────────────────────────────
    if (state.showSettings) {
        SettingsScreen(viewModel = viewModel, onBack = { viewModel.dismissSettings() })
        return
    }

    // ── Move / Copy folder picker (full-screen) ──────────────────────────────
    if (state.showMoveFolderPicker) {
        FolderPickerScreen(
            title                   = "Move to",
            folders                 = state.folders,
            groups                  = state.rootGroups + state.currentGroupSubGroups,
            orderedMixedItems       = state.orderedMixedItems,
            onFolderSelected        = { viewModel.moveSelectedVideos(it) },
            onBack                  = { viewModel.dismissMoveFolderPicker() },
            onCreateFolderAndSelect = { viewModel.createFolderAndMoveVideos(it) }
        )
        return
    }
    if (state.showCopyFolderPicker) {
        FolderPickerScreen(
            title                   = "Copy to",
            folders                 = state.folders,
            groups                  = state.rootGroups + state.currentGroupSubGroups,
            orderedMixedItems       = state.orderedMixedItems,
            onFolderSelected        = { viewModel.copySelectedVideos(it) },
            onBack                  = { viewModel.dismissCopyFolderPicker() },
            onCreateFolderAndSelect = { viewModel.createFolderAndCopyVideos(it) }
        )
        return
    }

    // ── Main Screen ──────────────────────────────────────────────────────────
    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top action bar ──
            ScreenTopBar {
                if (state.isSelectionMode) {
                    // ── Selection mode header ──
                    val count = if (state.selectedTab == 0) state.selectedVideoIds.size
                        else state.selectedFolderIds.size + state.selectedGroupIds.size
                    val totalCount = if (state.selectedTab == 0) state.videos.size
                        else state.orderedMixedItems.size
                    SelectionModeHeader(
                        selectedCount = count,
                        totalCount    = totalCount,
                        onSelectAll   = {
                            val allSelected = totalCount > 0 && count == totalCount
                            when {
                                allSelected && state.selectedTab == 0 -> viewModel.deselectAllVideos()
                                allSelected                            -> viewModel.deselectAllFolders()
                                state.selectedTab == 0                 -> viewModel.selectAllVideos()
                                else                                   -> viewModel.selectAllFoldersAndGroups()
                            }
                        },
                        onCancel = { viewModel.exitSelectionMode() }
                    )
                } else if (state.isGroupCreationMode) {
                    // ── Group creation mode header ──
                    val gcCount = state.groupCreationSelectedFolderIds.size +
                            state.groupCreationSelectedGroupIds.size
                    val gcLabel = if (state.pendingGroupCreationName.isNotBlank())
                        "Add to \"${state.pendingGroupCreationName}\" ($gcCount)"
                    else
                        "Select items ($gcCount)"
                    Text(
                        gcLabel,
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                        color = colors.listFirstText, modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    val canSave = gcCount >= 2
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable(enabled = canSave) { if (canSave) viewModel.showGroupNameDialog() },
                        color = if (canSave) colors.primary else Color(0xFF3A3A3A),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            "Save",
                            fontSize = 16.sp, fontWeight = FontWeight.Medium,
                            color = if (canSave) Color.White else Color.White.copy(alpha = 0.35f),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { viewModel.exitGroupCreationMode() },
                        color = Color(0xFF3A3A3A), shape = RoundedCornerShape(50)
                    ) {
                        Text("Cancel", fontSize = 16.sp, fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp))
                    }
                } else {
                    // ── Normal mode header ──
                    Text(
                        text = "VIDEO",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.listFirstText,
                        modifier = Modifier.weight(1f)
                    )

                    ActionsPill {
                        // + Create button
                        IconButton(
                            onClick = { showCreateMenu = true },
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Create",
                                tint = colors.iconColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        // View type toggle (tab-aware)
                        val currentViewType = if (state.selectedTab == 1) state.folderViewType else state.viewType
                        ViewTypeToggleButton(
                            viewType = currentViewType,
                            onClick  = {
                                if (state.selectedTab == 1) viewModel.cycleFolderViewType()
                                else viewModel.cycleViewType()
                            }
                        )

                        // More options
                        AppMoreMenuButton(
                            expanded  = showMoreMenu,
                            onExpand  = { showMoreMenu = true },
                            onDismiss = { showMoreMenu = false },
                            onSortBy   = { viewModel.showSortDialog() },
                            onViewAs   = { viewModel.showViewAsDialog() },
                            onSettings = { viewModel.showSettings() },
                            onAbout    = { viewModel.showAbout() }
                        )
                    }
                }
            }

            // ── Tab Row ──
            // pagerState is hoisted outside selection-mode branches so the tab
            // row is ALWAYS visible → consistent total header height in every mode.
            val pagerState = rememberPagerState(initialPage = state.selectedTab) { 2 }
            val tabTitles = listOf("VIDEOS", "FOLDERS")

            LaunchedEffect(pagerState.currentPage) {
                viewModel.selectTab(pagerState.currentPage)
            }

            Surface(color = colors.actionBarBg) {
                SecondaryTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    contentColor = colors.tabTextSelected,
                    indicator = {
                        TabRowDefaults.SecondaryIndicator(
                            color = colors.tabSelected,
                            height = 2.dp,
                            modifier = Modifier.tabIndicatorOffset(pagerState.currentPage)
                        )
                    },
                    divider = {
                        HorizontalDivider(thickness = 1.dp, color = colors.dividerColor)
                    }
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            // Disable tab switching during selection / group-creation mode
                            onClick = {
                                if (!state.isSelectionMode && !state.isGroupCreationMode) {
                                    scope.launch { pagerState.animateScrollToPage(index) }
                                }
                            },
                            text = {
                                Text(
                                    text = title,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.5.sp,
                                    color = if (pagerState.currentPage == index)
                                        colors.tabTextSelected else colors.tabTextDefault
                                )
                            }
                        )
                    }
                }
            }

            // ── Pager Content ──
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                // Disable swipe in selection / group-creation mode
                userScrollEnabled = !state.isSelectionMode && !state.isGroupCreationMode
            ) { page ->
                when (page) {
                    0 -> VideosTab(
                        videos = state.videos,
                        viewType = state.viewType,
                        isSelectionMode = state.isSelectionMode,
                        selectedIds = state.selectedVideoIds,
                        isLoading = state.isLoading,
                        onVideoClick = { video ->
                            if (state.isSelectionMode) viewModel.toggleVideoSelection(video.id)
                            else viewModel.playVideo(ctx, video)
                        },
                        onVideoLongClick = { video ->
                            viewModel.enterSelectionMode()
                            viewModel.toggleVideoSelection(video.id)
                        },
                        lazyListState = videoListState,
                        lazyGridState = videoGridState
                    )

                    1 -> FoldersTab(
                        folders = state.folders,
                        viewType = state.folderViewType,
                        isSelectionMode = state.isSelectionMode,
                        selectedIds = state.selectedFolderIds,
                        isLoading = state.isLoading,
                        sortOption = state.sortOption,
                        orderedMixedItems = state.orderedMixedItems,
                        selectedGroupIds = state.selectedGroupIds,
                        isGroupCreationMode = state.isGroupCreationMode,
                        groupCreationSelectedFolderIds = state.groupCreationSelectedFolderIds,
                        groupCreationSelectedGroupIds  = state.groupCreationSelectedGroupIds,
                        onFolderClick = { folder ->
                            when {
                                state.isGroupCreationMode ->
                                    viewModel.toggleGroupCreationFolderSelection(folder.bucketId)
                                state.isSelectionMode ->
                                    viewModel.toggleFolderSelection(folder.bucketId)
                                else -> viewModel.openFolder(folder.bucketId, folder.name)
                            }
                        },
                        onFolderLongClick = { folder ->
                            if (state.isGroupCreationMode) {
                                viewModel.toggleGroupCreationFolderSelection(folder.bucketId)
                            } else {
                                viewModel.enterSelectionMode()
                                viewModel.toggleFolderSelection(folder.bucketId)
                            }
                        },
                        onGroupClick = { group ->
                            when {
                                state.isGroupCreationMode ->
                                    viewModel.toggleGroupCreationGroupSelection(group.groupId)
                                state.isSelectionMode ->
                                    viewModel.toggleGroupSelection(group.groupId)
                                else -> viewModel.openGroup(group.groupId, group.name)
                            }
                        },
                        onGroupLongClick = { group ->
                            if (state.isGroupCreationMode) {
                                viewModel.toggleGroupCreationGroupSelection(group.groupId)
                            } else {
                                viewModel.enterSelectionMode()
                                viewModel.toggleGroupSelection(group.groupId)
                            }
                        },
                        onReorderFolders = { from, to -> viewModel.reorderMixedItem(from, to) },
                        onReorderDone = { viewModel.persistFolderOrder() },
                        onExitSelectionForDrag = {},
                        lazyListState = folderListState,
                        lazyGridState = folderGridState
                    )
                }
            }
        }


        // ── Floating Bottom Action Bar ──
        val selectedCount = if (state.selectedTab == 0) state.selectedVideoIds.size
            else state.selectedFolderIds.size + state.selectedGroupIds.size
        val showGroupBtn = state.selectedTab == 1 && selectedCount >= 1
        BottomActionBar(
            visible = state.isSelectionMode,
            onCopy = { viewModel.showCopyFolderPicker() },
            onMove = {
                if (state.selectedTab == 1) viewModel.showMoveToGroupPicker()
                else viewModel.showMoveFolderPicker()
            },
            onDelete = { viewModel.showDeleteDialog() },
            onDetails = { viewModel.showDetailsForSelectedVideo() },
            showAllActions = state.selectedTab == 0,
            showDetails = state.selectedTab == 0 && selectedCount == 1,
            showMove = state.selectedTab == 1 && selectedCount >= 1,
            showShare = state.selectedTab == 1 && selectedCount >= 1,
            onShare = { viewModel.shareSelectedFolders() },
            onOpenLocation = {
                viewModel.getSelectedLocationPath()?.let { path ->
                    FileManagerHelper.openFolder(ctx, path)
                }
            },
            showOpenLocation = selectedCount == 1,
            showGroup = showGroupBtn,
            onGroup   = { viewModel.showGroupNameDialogForBottomBar() },
            modifier  = Modifier.align(Alignment.BottomCenter)
        )

        // ── Overlay: Copy/Move Progress + File Conflict Dialog ───────────────────
        CopyMoveAndConflictOverlayHost(
            isProgressActive = progress.isActive,
            progressTitle = progress.title,
            progressCurrent = progress.current,
            progressTotal = progress.total,
            onCancelProgress = { viewModel.cancelCopyMove() },
            conflictFileName = conflict?.fileName,
            onReplaceConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.REPLACE) },
            onRenameConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.RENAME) },
            onSkipConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.SKIP) },
            onSkipAllConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.SKIP_ALL) },
            onReplaceAllConflict = { viewModel.resolveConflict(com.example.common.data.model.ConflictResolution.REPLACE_ALL) },
            renameActionLabel = "Keep Both"
        )
    }

    // ── Dialogs ──
    if (state.showSortDialog) {
        if (state.selectedTab == 1) {
            SortDialog(
                options          = FolderSortOption.entries,
                labelFor         = { it.label },
                currentOption    = state.sortOption,
                onOptionSelected = { viewModel.setSortOption(it) },
                onDismiss        = { viewModel.dismissSortDialog() }
            )
        } else {
            SortDialog(
                options          = VideoSortOption.entries,
                labelFor         = { it.label },
                currentOption    = state.videoSortOption,
                onOptionSelected = { viewModel.setVideoSortOption(it) },
                onDismiss        = { viewModel.dismissSortDialog() }
            )
        }
    }
    if (state.showViewAsDialog) {
        ViewAsDialog(
            currentViewType    = state.viewType,
            onViewTypeSelected = { viewModel.setViewType(it) },
            onDismiss          = { viewModel.dismissViewAsDialog() }
        )
    }
    if (state.showRenameDialog && state.renameTarget != null) {
        RenameDialog(
            currentName = state.renameTarget!!.displayName,
            title       = "Rename video",
            onRename    = { viewModel.renameVideo(state.renameTarget!!.id, it) },
            onDismiss   = { viewModel.dismissRenameDialog() }
        )
    }
    if (state.showDeleteDialog) {
        val selFolders = state.ungroupedFolders.filter { it.bucketId in state.selectedFolderIds }
        val selGroups  = state.rootGroups.filter { it.groupId in state.selectedGroupIds }
        val count = if (state.selectedTab == 0) state.selectedVideoIds.size
                    else state.selectedFolderIds.size + state.selectedGroupIds.size
        DeleteConfirmDialog(
            count          = count,
            isFolder       = state.selectedTab == 1,
            albumCount     = if (state.selectedTab == 1) selFolders.size else 0,
            groupCount     = if (state.selectedTab == 1) selGroups.size else 0,
            totalItemCount = if (state.selectedTab == 1) selFolders.sumOf { it.itemCount } + selGroups.sumOf { it.totalItemCount } else 0,
            itemName       = "video",
            folderName     = "folder",
            onConfirm = {
                if (state.selectedTab == 0) viewModel.deleteSelectedVideos()
                else viewModel.deleteSelectedFolders()
            },
            onDismiss = { viewModel.dismissDeleteDialog() }
        )
    }
    if (state.showCreateFolderDialog) {
        CreateFolderDialog(
            existingNames = state.folders.map { it.name },
            onCreate      = { viewModel.createFolder(it) },
            onDismiss     = { viewModel.dismissCreateFolderDialog() }
        )
    }
    if (state.showDetailsDialog && state.detailsTarget != null) {
        VideoDetailsDialog(
            video     = state.detailsTarget!!,
            onDismiss = { viewModel.dismissVideoDetails() }
        )
    }
    if (state.showGroupNameDialog) {
        val isCreation = state.groupNameDialogForCreation
        GroupNameDialog(
            initialName  = if (isCreation) state.suggestedGroupName else "Group 1",
            title        = if (isCreation) "New group" else "Create group",
            confirmLabel = "Create",
            existingNames = if (isCreation) state.existingGroupNames else emptySet(),
            onConfirm = { name ->
                when {
                    isCreation                        -> viewModel.enterGroupCreationModeWithName(name)
                    state.groupNameDialogForBottomBar -> viewModel.createGroupFromSelection(name)
                    else                              -> viewModel.createGroupFromCreationMode(name)
                }
            },
            onDismiss = { viewModel.dismissGroupNameDialog() }
        )
    }
    if (state.showMoveToGroupPicker) {
        MoveToGroupPickerDialog(
            groups    = state.rootGroups,
            onMove    = { viewModel.moveSelectionToGroup(it) },
            onDismiss = { viewModel.dismissMoveToGroupPicker() }
        )
    }

    // ── "Choose what to create" bottom sheet ──
    if (showCreateMenu) {
        ModalBottomSheet(
            onDismissRequest = { showCreateMenu = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = colors.menuBg,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            dragHandle = null
        ) {
            Text(
                text = "Choose what to create",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colors.listFirstText,
                modifier = Modifier.padding(start = 24.dp, top = 28.dp, end = 24.dp, bottom = 12.dp)
            )

            // ── Album row ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showCreateMenu = false; viewModel.showCreateAlbumDialog() }
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color(0xFF3A3A3C), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Collections,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        "Album",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colors.listFirstText
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Create a new album and add pictures and videos manually.",
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = colors.listSecondText
                    )
                }
            }

            // ── Group row ──
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showCreateMenu = false; viewModel.showGroupNameForCreation() }
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color(0xFF3A3A3C), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Folder,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        "Group",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colors.listFirstText
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Create a group of related albums.",
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = colors.listSecondText
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}
