package com.imagelibrary.ui.screen

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ImageSortOption
import com.example.common.ui.components.SortDialog
import com.example.common.data.model.ConflictResolution
import com.imagelibrary.data.model.ImageItem
import com.example.common.data.util.FileManagerHelper
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.CopyMoveAndConflictOverlayHost
import com.example.common.ui.components.DeleteConfirmDialog
import com.example.common.ui.components.GroupNameDialog
import com.example.common.ui.components.CreateAlbumDialog
import com.example.common.ui.components.CopyMoveAlbumDialog
import com.example.common.ui.components.CreateFolderDialog
import com.example.common.ui.components.DestroyGroupDialog
import com.example.common.ui.components.RenameDialog
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.ScreenTopBar
import com.imagelibrary.ui.components.*
import com.imagelibrary.ui.theme.LocalImageColors
import com.imagelibrary.ui.viewmodel.ImageListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val progress by viewModel.copyMoveProgress.collectAsState()
    val conflict by viewModel.fileConflict.collectAsState()
    val ctx = LocalContext.current
    val colors = LocalImageColors.current
    var showMoreMenu by remember { mutableStateOf(false) }
    var showCreateMenu by remember { mutableStateOf(false) }
    var carouselDeleteTarget by remember { mutableStateOf<ImageItem?>(null) }

    val folderGridState = rememberLazyGridState()
    val imageGridState = remember(state.imageSortOption) { LazyGridState() }
    // Hoisted so GroupDetailScreen scroll survives album-detail navigations.
    // Scrolls to top when navigating to a different group; stays put when returning
    // from a folder (album) inside the same group.
    val groupGridState = rememberLazyGridState()

    LaunchedEffect(state.sortOption) { folderGridState.scrollToItem(0) }
    // Scroll group grid to top when the group changes OR when its independent sort changes.
    LaunchedEffect(state.currentGroupId, state.currentGroupSortOption) { groupGridState.scrollToItem(0) }
    LaunchedEffect(state.currentFolderBucketId) {
        if (state.currentFolderBucketId != null) imageGridState.scrollToItem(0)
    }

    val hasOverlay = state.showDeleteDialog || state.showSortDialog || state.showViewAsDialog ||
            state.showRenameDialog || state.showCreateFolderDialog || state.showDetailsDialog ||
            state.showMoveFolderPicker || state.showCopyFolderPicker ||
            state.showAbout || state.showSettings || state.showHideFolders || state.isSearchActive ||
            showMoreMenu || showCreateMenu ||
            state.showGroupNameDialog || state.showRenameGroupDialog || state.showDestroyGroupDialog

    // Collect share intents and launch system chooser
    LaunchedEffect(Unit) {
        // viewModel.shareIntent.collect { intent ->
        //     ctx.startActivity(Intent.createChooser(intent, "Share"))
        // }
    }

    BackHandler(
        enabled = hasOverlay || state.isSelectionMode || state.currentFolderBucketId != null ||
                state.carouselIndex >= 0 || progress.isActive || state.isGroupCreationMode ||
                state.currentGroupId != null || state.showAddFolderToGroup ||
                state.showMoveToGroupPicker
    ) {
        when {
            progress.isActive -> {}
            state.carouselIndex >= 0 -> viewModel.closeCarousel()
            showMoreMenu -> showMoreMenu = false
            showCreateMenu -> showCreateMenu = false
            state.showGroupNameDialog -> viewModel.dismissGroupNameDialog()
            state.showRenameGroupDialog -> viewModel.dismissRenameGroupDialog()
            state.showDestroyGroupDialog -> viewModel.dismissDestroyGroupDialog()
            state.showDeleteDialog -> viewModel.dismissDeleteDialog()
            state.showSortDialog -> viewModel.dismissSortDialog()
            state.showViewAsDialog -> viewModel.dismissViewAsDialog()
            state.showRenameDialog -> viewModel.dismissRenameDialog()
            state.showCreateFolderDialog -> viewModel.dismissCreateFolderDialog()
            state.showDetailsDialog -> viewModel.dismissImageDetails()
            state.showMoveFolderPicker -> viewModel.dismissMoveFolderPicker()
            state.showCopyFolderPicker -> viewModel.dismissCopyFolderPicker()
            state.showAddFolderToGroup -> viewModel.dismissAddFolderToGroup()
            state.showMoveToGroupPicker -> viewModel.dismissMoveToGroupPicker()
            state.isGroupCreationMode -> viewModel.exitGroupCreationMode()
            state.isSelectionMode -> viewModel.exitSelectionMode()
            state.currentFolderBucketId != null -> { viewModel.exitSelectionMode(); viewModel.closeFolder() }
            state.currentGroupId != null -> { viewModel.exitSelectionMode(); viewModel.closeGroup() }
            state.isSearchActive -> viewModel.deactivateSearch()
            state.showAbout -> viewModel.dismissAbout()
            state.showHideFolders && state.hideScreenGroupId != null -> {
                if (state.hideScreenStartedInsideGroup) viewModel.dismissHideFoldersScreen()
                else viewModel.closeGroupInHideScreen()
            }
            state.showHideFolders -> viewModel.dismissHideFoldersScreen()
            state.showSettings -> viewModel.dismissSettings()
        }
    }

    // ── Create Album picker (full-screen) ──
    if (state.showCreateAlbumPicker) {
        CreateAlbumPickerScreen(
            albumName = state.pendingAlbumName,
            allFolders = state.folders,
            allGroups = state.allGroups,
            browsedImages = state.albumCreationBrowsedImages,
            currentBucketId = state.albumCreationCurrentBucketId,
            selectedImageIds = state.albumCreationSelectedImageIds,
            viewType = state.viewType,
            onFolderOpen = { folder -> viewModel.loadAlbumCreationImages(folder.bucketId, folder.name) },
            onFolderClose = { viewModel.closeAlbumCreationFolder() },
            onToggleImage = { id -> viewModel.toggleAlbumCreationImageSelection(id) },
            onDone = { viewModel.showCreateAlbumCopyMoveDialog() },
            onBack = { viewModel.cancelAlbumCreation() },
            allImages = state.images
        )
        if (state.showCreateAlbumCopyMoveDialog) {
            CopyMoveAlbumDialog(
                itemCount = state.albumCreationSelectedImageIds.size,
                itemLabel = "image",
                onCopy = { viewModel.confirmAlbumCreation(copy = true) },
                onMove = { viewModel.confirmAlbumCreation(copy = false) },
                onCancel = { viewModel.dismissCreateAlbumCopyMoveDialog() }
            )
        }
        // Always host the progress/conflict overlays so they show when the operation starts
        CopyMoveAndConflictOverlayHost(
            isProgressActive = progress.isActive,
            progressTitle = progress.title,
            progressCurrent = progress.current,
            progressTotal = progress.total,
            onCancelProgress = { viewModel.cancelCopyMove() },
            conflictFileName = conflict?.fileName,
            onReplaceConflict = { viewModel.resolveConflict(ConflictResolution.REPLACE) },
            onRenameConflict = { viewModel.resolveConflict(ConflictResolution.RENAME) },
            onSkipConflict = { viewModel.resolveConflict(ConflictResolution.SKIP) },
            renameActionLabel = "Rename"
        )
        return
    }

    // ── Carousel ──
    if (state.carouselIndex >= 0 && state.folderImages.isNotEmpty()) {
        ImageCarouselScreen(
            images = state.folderImages,
            initialIndex = state.carouselIndex,
            onBack = { viewModel.closeCarousel() },
            initialBarsVisible = state.carouselShowBarsOnOpen,
            alwaysHideOverlay = state.carouselAlwaysHideOverlay,
            onMoreItems = listOf(
                "Settings" to { viewModel.showSettings() }
            ),
            onShare = { image ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = image.mimeType
                    putExtra(Intent.EXTRA_STREAM, image.contentUri)
                    clipData = android.content.ClipData.newRawUri("", image.contentUri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                ctx.startActivity(Intent.createChooser(intent, "Share"))
            },
            onDelete = { image -> carouselDeleteTarget = image },
            onCopy   = { image -> viewModel.carouselCopy(image.id) },
            onMove   = { image -> viewModel.carouselMove(image.id) },
            onDetails = { image -> viewModel.showImageDetails(image) },
            onOpenLocation = { image ->
                java.io.File(image.path).parent
                    ?.let { FileManagerHelper.openFolder(ctx, it) }
            }
        )
        if (carouselDeleteTarget != null) {
            DeleteConfirmDialog(
                count = 1,
                isFolder = false,
                itemName = "image",
                onConfirm = { carouselDeleteTarget?.let { viewModel.deleteCarouselImage(it.id) }; carouselDeleteTarget = null },
                onDismiss = { carouselDeleteTarget = null }
            )
        }
        return
    }

    // ── Settings / About ──
    if (state.showSettings) { SettingsScreen(viewModel = viewModel, onBack = { viewModel.dismissSettings() }); return }
    if (state.showAbout) { AboutScreen(onBack = { viewModel.dismissAbout() }); return }

    // ── Hide albums screen ─────────────────────────────────────────────────
    if (state.showHideFolders) {
        val groupHiddenState = state.rootGroupsForHide.associate { group ->
            val paths = state.allFoldersForHide
                .filter { it.bucketId in group.memberBucketIds }
                .map { it.path }
                .filter { it.isNotBlank() }
            group.groupId to (paths.isNotEmpty() && paths.all { it in state.hiddenFolderPaths })
        }
        val groupSubGroupHiddenState = state.hideScreenGroupSubGroups.associate { sub ->
            val paths = state.allFoldersForHide
                .filter { it.bucketId in sub.memberBucketIds }
                .map { it.path }
                .filter { it.isNotBlank() }
            sub.groupId to (paths.isNotEmpty() && paths.all { it in state.hiddenFolderPaths })
        }
        HideFoldersScreen(
            groups                   = state.rootGroupsForHide,
            ungroupedFolders         = state.ungroupedFoldersForHide,
            groupFolders             = state.hideScreenGroupFolders,
            currentGroupId           = state.hideScreenGroupId,
            currentGroupName         = state.hideScreenGroupName,
            hiddenFolderPaths        = state.hiddenFolderPaths,
            groupHiddenState         = groupHiddenState,
            groupSubGroups           = state.hideScreenGroupSubGroups,
            groupSubGroupHiddenState = groupSubGroupHiddenState,
            onGroupOpen              = { viewModel.openGroupInHideScreen(it) },
            onGroupToggle            = { viewModel.toggleGroupHidden(it) },
            onFolderToggle           = { viewModel.toggleFolderHidden(it) },
            onGroupBack              = {
                if (state.hideScreenStartedInsideGroup) viewModel.dismissHideFoldersScreen()
                else viewModel.closeGroupInHideScreen()
            },
            onBack                   = { viewModel.dismissHideFoldersScreen() }
        )
        return
    }

    // ── Folder detail ──
    if (state.currentFolderBucketId != null) {
        FolderDetailScreen(
            folderName = state.currentFolderName,
            images = state.folderImages,
            viewType = state.folderViewType,
            isSelectionMode = state.isSelectionMode,
            selectedIds = state.selectedImageIds,
            onBack = { viewModel.exitSelectionMode(); viewModel.closeFolder() },
            onImageClick = { _, index -> viewModel.openCarousel(index) },
            onImageLongClick = { image -> viewModel.enterSelectionMode(); viewModel.toggleImageSelection(image.id) },
            onCycleViewType = { viewModel.cycleFolderViewType() },
            onCopy = { viewModel.showCopyFolderPicker() },
            onMove = { viewModel.showMoveFolderPicker() },
            onDelete = { viewModel.showDeleteDialog() },
            // onShare = { viewModel.shareSelectedImages() },
            onDetails = {
                if (state.selectedImageIds.size == 1) {
                    state.folderImages.find { it.id == state.selectedImageIds.first() }?.let { viewModel.showImageDetails(it) }
                }
            },
            onOpenLocation = {
                viewModel.getSelectedLocationPath()?.let { FileManagerHelper.openFolder(ctx, it) }
            },
            onEdit = { viewModel.enterSelectionMode() },
            onSelectAll = {
                val allSelected = state.folderImages.isNotEmpty() && state.selectedImageIds.size == state.folderImages.size
                if (allSelected) viewModel.deselectAllImages() else viewModel.selectAllImages()
            },
            onSortBy = { viewModel.showSortDialog() },
            onViewAs = { viewModel.showViewAsDialog() },
            onSettings = { viewModel.showSettings() },
            onAbout = { viewModel.showAbout() },
            lazyGridState = imageGridState
        )
        if (state.showDeleteDialog) {
            DeleteConfirmDialog(
                count = state.selectedImageIds.size,
                isFolder = false,
                itemName = "image",
                onConfirm = { viewModel.deleteSelectedImages() },
                onDismiss = { viewModel.dismissDeleteDialog() }
            )
        }
        if (state.showDetailsDialog && state.detailsTarget != null) { ImageDetailsDialog(image = state.detailsTarget!!, onDismiss = { viewModel.dismissImageDetails() }) }
        if (state.showSortDialog) { SortDialog(options = ImageSortOption.entries, labelFor = { it.label }, currentOption = state.imageSortOption, onOptionSelected = { viewModel.setImageSortOption(it) }, onDismiss = { viewModel.dismissSortDialog() }) }
        if (state.showViewAsDialog) { ViewAsDialog(currentViewType = state.folderViewType, onViewTypeSelected = { viewModel.setFolderViewType(it) }, onDismiss = { viewModel.dismissViewAsDialog() }) }
        if (state.showMoveFolderPicker) { FolderPickerScreen(title = "Move to", folders = state.folders, groups = state.allGroups, orderedMixedItems = state.orderedMixedItems, groupCustomOrders = state.allGroupCustomOrders, onFolderSelected = { viewModel.moveSelectedImages(it) }, onBack = { viewModel.dismissMoveFolderPicker() }, onCreateFolderAndSelect = { viewModel.createFolderAndMoveImages(it) }) }
        if (state.showCopyFolderPicker) { FolderPickerScreen(title = "Copy to", folders = state.folders, groups = state.allGroups, orderedMixedItems = state.orderedMixedItems, groupCustomOrders = state.allGroupCustomOrders, onFolderSelected = { viewModel.copySelectedImages(it) }, onBack = { viewModel.dismissCopyFolderPicker() }, onCreateFolderAndSelect = { viewModel.createFolderAndCopyImages(it) }) }
        if (state.showCreateAlbumDialog) { CreateAlbumDialog(existingDcimNames = state.dcimFolderNames, onConfirm = { name -> viewModel.startCreateAlbumPicker(name) }, onDismiss = { viewModel.dismissCreateAlbumDialog() }) }
        CopyMoveAndConflictOverlayHost(
            isProgressActive = progress.isActive,
            progressTitle = progress.title,
            progressCurrent = progress.current,
            progressTotal = progress.total,
            onCancelProgress = { viewModel.cancelCopyMove() },
            conflictFileName = conflict?.fileName,
            onReplaceConflict = { viewModel.resolveConflict(ConflictResolution.REPLACE) },
            onRenameConflict = { viewModel.resolveConflict(ConflictResolution.RENAME) },
            onSkipConflict = { viewModel.resolveConflict(ConflictResolution.SKIP) },
            renameActionLabel = "Rename"
        )
        return
    }

    // ── Group detail ──
    if (state.currentGroupId != null) {
        // Full-screen pickers that overlay group detail (must be checked before GroupDetailScreen renders)
        if (state.showAddFolderToGroup) {
            AddFolderToGroupScreen(
                folders = state.ungroupedFolders,
                groups = state.rootGroups,
                currentGroupId = state.currentGroupId!!,
                viewType = state.viewType,
                onSave = { fIds, gIds -> viewModel.addFoldersToCurrentGroup(fIds, gIds) },
                onCancel = { viewModel.dismissAddFolderToGroup() }
            )
            return
        }
        if (state.showMoveToGroupPicker) {
            MoveToGroupScreen(
                folders = state.folders,
                groups = state.allGroups,
                movingFolderIds = state.moveToGroupFolderIds,
                movingGroupIds = state.moveToGroupGroupIds,
                viewType = state.viewType,
                onMoveHere = { viewModel.moveSelectionToGroup(it) },
                onCreateGroupAndMove = { viewModel.createGroupAndMoveSelection(it) },
                onCancel = { viewModel.dismissMoveToGroupPicker() }
            )
            return
        }

        GroupDetailScreen(
            groupName = state.currentGroupName,
            folders = state.currentGroupFolders,
            subGroups = state.currentGroupSubGroups,
            orderedMixedItems = state.currentGroupOrderedMixedItems,
            viewType = state.viewType,
            isSelectionMode = state.isSelectionMode,
            selectedFolderIds = state.selectedFolderIds,
            selectedGroupIds = state.selectedGroupIds,
            onBack = { viewModel.exitSelectionMode(); viewModel.closeGroup() },
            onFolderClick = { folder ->
                // Read directly from the live StateFlow value — avoids any stale Compose
                // lambda-capture of isSelectionMode that can occur when a prior long-press
                // left the state as "selection mode active" but the UI hadn't recomposed yet.
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
            onCycleViewType = { viewModel.cycleViewType() },
            onAddFolder = { viewModel.showAddFolderToGroup() },
            onRenameGroup = { viewModel.showRenameGroupDialog() },
            onHideAlbums  = { viewModel.showHideFoldersScreenForCurrentGroup() },
            onDestroyGroup = { viewModel.showDestroyGroupDialog() },
            onViewAs = { viewModel.showViewAsDialog() },
            onSettings = { viewModel.showSettings() },
            onAbout = { viewModel.showAbout() },
            onDelete = { viewModel.removeSelectedFromGroup() },
            onGroup = { viewModel.showGroupNameDialogForBottomBar() },
            onSelectAll = { viewModel.selectAllInGroup() },
            onCancelSelection = { viewModel.exitSelectionMode() },
            // onShare = { viewModel.shareSelectedFolders() },
            onMove = { viewModel.showMoveToGroupPicker() },
            onOpenLocation = {
                viewModel.getSelectedLocationPath()
                    ?.let { FileManagerHelper.openFolder(ctx, it) }
            },
            sortOption = state.currentGroupSortOption,
            onSortOptionSelected = { viewModel.setCurrentGroupSortOption(it) },
            groupsAlwaysOnTop = state.groupsAlwaysOnTop,
            onReorderFolders = { from, to -> viewModel.reorderGroupItem(from, to) },
            onReorderDone = { viewModel.persistGroupOrder() },
            lazyGridState = groupGridState
        )
        if (state.showRenameGroupDialog) { GroupNameDialog(title = "Rename group", confirmLabel = "Rename", initialName = state.currentGroupName, existingNames = state.allGroups.map { it.name }, onConfirm = { viewModel.renameCurrentGroup(it) }, onDismiss = { viewModel.dismissRenameGroupDialog() }) }
        if (state.showDestroyGroupDialog) { DestroyGroupDialog(groupName = state.currentGroupName, onConfirm = { viewModel.destroyCurrentGroup() }, onDismiss = { viewModel.dismissDestroyGroupDialog() }) }
        if (state.showGroupNameDialog) {
            val existingNames = state.allGroups.map { it.name }
            GroupNameDialog(
                title = "Create group",
                initialName = generateUniqueGroupName(existingNames),
                existingNames = existingNames,
                onConfirm = { name ->
                    if (state.groupNameDialogForBottomBar) viewModel.createGroupFromSelection(name)
                    else viewModel.createGroupFromCreationMode(name)
                },
                onDismiss = { viewModel.dismissGroupNameDialog() }
            )
        }
        return
    }

    // ── Search ──
    if (state.isSearchActive) {
        SearchScreen(query = state.searchQuery, results = state.searchResults, onQueryChange = { viewModel.setSearchQuery(it) }, onBack = { viewModel.deactivateSearch() }, onImageClick = {})
        return
    }

    // ── Full-screen pickers (early returns) ──
    // Each picker is wrapped in a Box so CopyMoveAndConflictOverlayHost is always rendered
    // on top — ensures progress and conflict dialogs appear no matter which picker is active.
    if (state.showMoveFolderPicker) {
        Box(modifier = Modifier.fillMaxSize()) {
            FolderPickerScreen(title = "Move to", folders = state.folders, groups = state.allGroups, orderedMixedItems = state.orderedMixedItems, groupCustomOrders = state.allGroupCustomOrders, onFolderSelected = { viewModel.moveSelectedImages(it) }, onBack = { viewModel.dismissMoveFolderPicker() }, onCreateFolderAndSelect = { viewModel.createFolderAndMoveImages(it) })
            CopyMoveAndConflictOverlayHost(isProgressActive = progress.isActive, progressTitle = progress.title, progressCurrent = progress.current, progressTotal = progress.total, onCancelProgress = { viewModel.cancelCopyMove() }, conflictFileName = conflict?.fileName, onReplaceConflict = { viewModel.resolveConflict(ConflictResolution.REPLACE) }, onRenameConflict = { viewModel.resolveConflict(ConflictResolution.RENAME) }, onSkipConflict = { viewModel.resolveConflict(ConflictResolution.SKIP) }, renameActionLabel = "Rename")
        }
        return
    }
    if (state.showCopyFolderPicker) {
        Box(modifier = Modifier.fillMaxSize()) {
            FolderPickerScreen(title = "Copy to", folders = state.folders, groups = state.allGroups, orderedMixedItems = state.orderedMixedItems, groupCustomOrders = state.allGroupCustomOrders, onFolderSelected = { viewModel.copySelectedImages(it) }, onBack = { viewModel.dismissCopyFolderPicker() }, onCreateFolderAndSelect = { viewModel.createFolderAndCopyImages(it) })
            CopyMoveAndConflictOverlayHost(isProgressActive = progress.isActive, progressTitle = progress.title, progressCurrent = progress.current, progressTotal = progress.total, onCancelProgress = { viewModel.cancelCopyMove() }, conflictFileName = conflict?.fileName, onReplaceConflict = { viewModel.resolveConflict(ConflictResolution.REPLACE) }, onRenameConflict = { viewModel.resolveConflict(ConflictResolution.RENAME) }, onSkipConflict = { viewModel.resolveConflict(ConflictResolution.SKIP) }, renameActionLabel = "Rename")
        }
        return
    }
    if (state.showMoveToGroupPicker) {
        MoveToGroupScreen(folders = state.folders, groups = state.allGroups, movingFolderIds = state.moveToGroupFolderIds, movingGroupIds = state.moveToGroupGroupIds, viewType = state.viewType, onMoveHere = { viewModel.moveSelectionToGroup(it) }, onCreateGroupAndMove = { viewModel.createGroupAndMoveSelection(it) }, onCancel = { viewModel.dismissMoveToGroupPicker() })
        return
    }

    // ══════════════════════════════════════════════════════════
    // ── Main Screen (Albums grid) ─────────────────────────────
    // ══════════════════════════════════════════════════════════
    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ──
            ScreenTopBar {
                when {
                    state.isGroupCreationMode -> {
                        // Save / Cancel bar
                        val totalSelected = state.groupCreationSelectedFolderIds.size +
                                state.groupCreationSelectedGroupIds.size
                        val headerText = if (state.pendingGroupCreationName.isNotBlank())
                            "Add to \"${state.pendingGroupCreationName}\" ($totalSelected)"
                        else
                            "Select items ($totalSelected)"
                        Text(
                            text = headerText,
                            fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                            color = Color.White, modifier = Modifier.weight(1f)
                        )
                        PillButton(
                            text = "Save",
                            onClick = { viewModel.showGroupNameDialog() },
                            enabled = totalSelected >= 2,
                            containerColor = if (totalSelected >= 2) colors.primary else Color(0xFF3A3A3A)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        PillButton(text = "Cancel", onClick = { viewModel.exitGroupCreationMode() })
                    }
                    state.isSelectionMode -> {
                        val count = state.selectedFolderIds.size + state.selectedGroupIds.size
                        val totalItems = state.ungroupedFolders.size + state.rootGroups.size
                        SelectionHeader(
                            selectedCount = count,
                            allSelected = totalItems > 0 && count == totalItems,
                            onSelectAll = { viewModel.selectAllFoldersAndGroups() },
                            onCancel = { viewModel.exitSelectionMode() }
                        )
                    }
                    else -> {
                        // Normal mode — action icons on the right
                        Spacer(modifier = Modifier.weight(1f))
                        ActionsPill {
                            // ── + Create ──
                            IconButton(onClick = { showCreateMenu = true }, modifier = Modifier.size(40.dp)) {
                                Icon(Icons.Default.Add, contentDescription = "Create", tint = colors.iconColor, modifier = Modifier.size(22.dp))
                            }
                            // ── View-type toggle ──
                            ViewTypeToggleButton(viewType = state.viewType, onClick = { viewModel.cycleViewType() })
                            // ── ⋮ More ──
                            AppMoreMenuButton(
                                expanded = showMoreMenu,
                                onExpand = { showMoreMenu = true },
                                onDismiss = { showMoreMenu = false },
                                onSortBy = { viewModel.showSortDialog() },
                                onViewAs = { viewModel.showViewAsDialog() },
                                onSettings = { viewModel.showSettings() },
                                onAbout = { viewModel.showAbout() },
                                extraTopContent = { dismiss ->
                                    com.example.common.ui.components.AppMenuItem(
                                        text      = "Hide albums",
                                        onDismiss = dismiss,
                                        onClick   = { viewModel.showHideFoldersScreen() },
                                        textColor = colors.listFirstText
                                    )
                                    com.example.common.ui.components.AppMenuDivider(
                                        color = colors.dividerColor
                                    )
                                }
                            )
                        }
                    }
                }
            }

            // ── Grid ──
            if (state.isGroupCreationMode) {
                FoldersTab(
                    folders = state.ungroupedFolders, groups = state.rootGroups,
                    viewType = state.viewType, isSelectionMode = false,
                    selectedFolderIds = state.groupCreationSelectedFolderIds,
                    selectedGroupIds = state.groupCreationSelectedGroupIds,
                    isLoading = state.isLoading, showCheckboxes = true,
                    onFolderClick = { viewModel.toggleGroupCreationFolderSelection(it.bucketId) },
                    onFolderLongClick = { viewModel.toggleGroupCreationFolderSelection(it.bucketId) },
                    onGroupClick = { viewModel.toggleGroupCreationGroupSelection(it.groupId) },
                    onGroupLongClick = { viewModel.toggleGroupCreationGroupSelection(it.groupId) },
                    lazyGridState = folderGridState, sortOption = state.sortOption
                )
            } else {
                FoldersTab(
                    mixedItems = state.orderedMixedItems,
                    viewType = state.viewType, isSelectionMode = state.isSelectionMode,
                    selectedFolderIds = state.selectedFolderIds,
                    selectedGroupIds = state.selectedGroupIds,
                    isLoading = state.isLoading,
                    onFolderClick = { folder ->
                        if (state.isSelectionMode) viewModel.toggleFolderSelection(folder.bucketId)
                        else viewModel.openFolder(folder.bucketId, folder.name)
                    },
                    onFolderLongClick = { folder ->
                        viewModel.enterSelectionMode()
                        viewModel.toggleFolderSelection(folder.bucketId)
                    },
                    onGroupClick = { group ->
                        if (state.isSelectionMode) viewModel.toggleGroupSelection(group.groupId)
                        else viewModel.openGroup(group.groupId, group.name)
                    },
                    onGroupLongClick = { group ->
                        viewModel.enterSelectionMode()
                        viewModel.toggleGroupSelection(group.groupId)
                    },
                    lazyGridState = folderGridState, sortOption = state.sortOption,
                    onReorderFolders = { from, to -> viewModel.reorderMixedItem(from, to) },
                    onReorderDone = { viewModel.persistFolderOrder() },
                    // Selection mode is intentionally preserved during drag-reorder.
                    onExitSelectionForDrag = {}
                )
            }
        }

        // ── Selection bottom bar: Group | Move | Share | Delete ──
        if (!state.isGroupCreationMode) {
            val totalSelected = state.selectedFolderIds.size + state.selectedGroupIds.size
            BottomActionBar(
                visible = state.isSelectionMode,
            onCopy = { viewModel.showCopyFolderPicker() },
            onMove = { viewModel.showMoveToGroupPicker() },
            onDelete = { viewModel.showDeleteDialog() },
                onDetails = { viewModel.showDetailsForSelectedImage() },
                showAllActions = false, showDetails = false,
                showGroup = totalSelected >= 1,
                onGroup = { viewModel.showGroupNameDialogForBottomBar() },
                showMove = totalSelected >= 1,
                showShare = true,
                // onShare = { viewModel.shareSelectedFolders() },
                showOpenLocation = totalSelected == 1 && state.selectedGroupIds.isEmpty(),
                onOpenLocation = {
                    viewModel.getSelectedLocationPath()
                        ?.let { FileManagerHelper.openFolder(ctx, it) }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

        // ── Overlay: Copy/Move Progress + File Conflict Dialog ───────────────────
        CopyMoveAndConflictOverlayHost(
            isProgressActive = progress.isActive,
            progressTitle = progress.title,
            progressCurrent = progress.current,
            progressTotal = progress.total,
            onCancelProgress = { viewModel.cancelCopyMove() },
            conflictFileName = conflict?.fileName,
            onReplaceConflict = { viewModel.resolveConflict(ConflictResolution.REPLACE) },
            onRenameConflict = { viewModel.resolveConflict(ConflictResolution.RENAME) },
            onSkipConflict = { viewModel.resolveConflict(ConflictResolution.SKIP) },
            renameActionLabel = "Rename"
        )

    }

    // ── Global dialogs ──
    if (state.showSortDialog) { SortDialog(options = SortOption.entries, labelFor = { it.label }, currentOption = state.sortOption, onOptionSelected = { viewModel.setSortOption(it) }, onDismiss = { viewModel.dismissSortDialog() }) }
    if (state.showViewAsDialog) { ViewAsDialog(currentViewType = state.viewType, onViewTypeSelected = { viewModel.setViewType(it) }, onDismiss = { viewModel.dismissViewAsDialog() }) }
    if (state.showRenameDialog && state.renameTarget != null) { RenameDialog(currentName = state.renameTarget!!.displayName, title = "Rename image", onRename = { viewModel.renameImage(state.renameTarget!!.id, it) }, onDismiss = { viewModel.dismissRenameDialog() }) }
    if (state.showDeleteDialog && state.currentFolderBucketId == null && state.currentGroupId == null) {
        val selFolders = state.ungroupedFolders.filter { it.bucketId in state.selectedFolderIds }
        val selGroups  = state.rootGroups.filter { it.groupId in state.selectedGroupIds }
        DeleteConfirmDialog(
            count          = state.selectedFolderIds.size,
            isFolder       = true,
            albumCount     = selFolders.size,
            groupCount     = selGroups.size,
            totalItemCount = selFolders.sumOf { it.itemCount } + selGroups.sumOf { it.totalItemCount },
            itemName       = "image",
            folderName     = "album",
            onConfirm      = { viewModel.deleteSelectedFolders() },
            onDismiss      = { viewModel.dismissDeleteDialog() }
        )
    }
    if (state.showCreateFolderDialog) { CreateFolderDialog(existingNames = state.folders.map { it.name }, onCreate = { viewModel.createFolder(it) }, onDismiss = { viewModel.dismissCreateFolderDialog() }) }
    if (state.showDetailsDialog && state.detailsTarget != null && state.currentFolderBucketId == null) { ImageDetailsDialog(image = state.detailsTarget!!, onDismiss = { viewModel.dismissImageDetails() }) }
    if (state.showGroupNameDialog && state.currentGroupId == null) {
        val existingNames = state.allGroups.map { it.name }
        GroupNameDialog(
            title = if (state.groupNameDialogForCreation) "New group" else "Create group",
            initialName = generateUniqueGroupName(existingNames),
            existingNames = existingNames,
            onConfirm = { name ->
                when {
                    state.groupNameDialogForCreation -> viewModel.enterGroupCreationModeWithName(name)
                    state.groupNameDialogForBottomBar -> viewModel.createGroupFromSelection(name)
                    else -> viewModel.createGroupFromCreationMode(name)
                }
            },
            onDismiss = { viewModel.dismissGroupNameDialog() }
        )
    }
    if (state.showCreateAlbumDialog && state.currentFolderBucketId == null) {
        CreateAlbumDialog(existingDcimNames = state.dcimFolderNames, onConfirm = { name -> viewModel.startCreateAlbumPicker(name) }, onDismiss = { viewModel.dismissCreateAlbumDialog() })
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
            Column(modifier = Modifier.navigationBarsPadding().padding(bottom = 8.dp)) {
                Text(
                    text = "Choose what to create",
                    modifier = Modifier.padding(start = 24.dp, top = 28.dp, bottom = 12.dp, end = 24.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colors.listFirstText
                )
                // ── Album ──
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
                        Icon(Icons.Default.Collections, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text("Album", color = colors.listFirstText, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(
                            "Create a new album and add pictures and videos manually.",
                            color = colors.listSecondText, fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
                // ── Group ──
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
                        Icon(Icons.Default.Folder, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
                    }
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Text("Group", color = colors.listFirstText, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text(
                            "Create a group of related albums.",
                            color = colors.listSecondText, fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

private fun generateUniqueGroupName(existingNames: List<String>): String {
    val lower = existingNames.map { it.lowercase() }
    var n = 1
    while (lower.contains("group $n")) n++
    return "Group $n"
}
