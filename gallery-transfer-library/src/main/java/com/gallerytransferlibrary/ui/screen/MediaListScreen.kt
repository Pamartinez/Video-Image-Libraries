package com.gallerytransferlibrary.ui.screen

import android.Manifest
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.border
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import kotlin.math.roundToInt
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.ViewType
import com.example.common.data.util.FileManagerHelper
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.CircularCheckIndicator
import com.example.common.ui.components.FolderGridItem
import com.example.common.ui.components.FolderThumbnailPlaceholder
import com.example.common.ui.components.CopyMoveAndConflictOverlayHost
import com.example.common.upload.ConflictResolution
import com.example.common.upload.UploadItem
import com.example.common.upload.UploadScheduler
import com.example.common.ui.components.SortDialog
import com.example.common.ui.components.ViewAsDialog
import com.example.common.ui.components.ViewTypeToggleButton
import com.example.common.ui.util.dragToReorderGrid
import com.example.common.ui.util.revealItem
import com.example.common.ui.util.rememberDragDropGridState
import com.example.common.ui.util.ZoomTransitionOverlay
import com.example.common.ui.util.rememberZoomTransitionState
import com.example.common.ui.util.zoomThumbnail
import com.example.common.ui.screen.AboutScreen
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.model.FilterSortOption
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaSortOption
import com.gallerytransferlibrary.data.model.SizeFilter
import com.gallerytransferlibrary.data.preferences.AppPreferences
import com.gallerytransferlibrary.data.util.FileLogger
import com.gallerytransferlibrary.dropbox.DropboxHolder
import com.gallerytransferlibrary.ui.components.MediaThumbnail
import com.gallerytransferlibrary.upload.AutoUploadScheduler
import com.gallerytransferlibrary.ui.theme.LocalGalleryColors
import com.gallerytransferlibrary.ui.viewmodel.MediaListUiState
import com.gallerytransferlibrary.ui.viewmodel.MediaListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

private enum class Overlay { NONE, SETTINGS, ABOUT, FILTER }

/** width/height aspect ratio for the zoom transition; 0f when the item's dimensions are unknown. */
private fun MediaItem.aspectRatioOrZero(): Float =
    if (width > 0 && height > 0) width.toFloat() / height.toFloat() else 0f

@Composable
fun MediaListScreen(
    viewModel: MediaListViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val colors = LocalGalleryColors.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val prefs = remember { AppPreferences(context) }
    val client = remember { DropboxHolder.client(context) }
    val uploadManager = remember { DropboxHolder.uploadManager(context) }
    val uploadState by uploadManager.state.collectAsState()
    var connected by remember { mutableStateOf(client.authManager.isConnected) }
    var destPath by remember { mutableStateOf(prefs.dropboxDestPath) }
    var overwriteOnConflict by remember { mutableStateOf(prefs.overwriteOnConflict) }
    var deleteAfterUpload by remember { mutableStateOf(prefs.deleteAfterUpload) }
    var keepFolderStructure by remember { mutableStateOf(prefs.keepFolderStructure) }
    var autoUploadEnabled by remember { mutableStateOf(prefs.autoUploadEnabled) }
    var autoUploadOlderThanDays by remember { mutableStateOf(prefs.autoUploadOlderThanDays) }
    var autoUploadFrequency by remember { mutableStateOf(prefs.autoUploadFrequency) }
    var autoUploadWifiOnly by remember { mutableStateOf(prefs.autoUploadWifiOnly) }
    var manualSignIn by remember { mutableStateOf(false) }
    var showFolderPicker by remember { mutableStateOf(false) }

    // Ask for notification permission so the background upload's progress notification is visible.
    val notifPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* upload proceeds regardless of the result */ }

    // Let the UploadManager know when a UI is present: while it is, filename conflicts show the
    // interactive dialog; once the app is backgrounded, they auto-resolve via the settings policy.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> uploadManager.setUiActive(true)
                Lifecycle.Event.ON_STOP -> uploadManager.setUiActive(false)
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            uploadManager.setUiActive(false)
        }
    }

    LaunchedEffect(uploadState.completedMessage, uploadState.errorMessage) {
        uploadState.completedMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            uploadManager.clearCompletion()
        }
        uploadState.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            uploadManager.clearCompletion()
        }
    }

    // After a delete-after-upload batch, the manager surfaces the uploaded items' URIs. When the app
    // holds All-files access the items are moved to the system (Samsung Gallery) trash silently (no
    // dialog); otherwise the system asks for consent (one dialog) to move them to the system trash.
    // Either way we refresh.
    val trashLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) viewModel.refreshCurrent()
        uploadManager.clearUploadedUris()
    }
    LaunchedEffect(uploadState.uploadedUris) {
        val uris = uploadState.uploadedUris
        if (uris.isNotEmpty()) {
            if (com.example.common.data.util.MediaTrashHelper.isExternalStorageManager()) {
                withContext(Dispatchers.IO) {
                    com.example.common.data.util.MediaTrashHelper.trashSilently(context, uris)
                }
                viewModel.refreshCurrent()
                uploadManager.clearUploadedUris()
            } else {
                val pending = com.example.common.data.util.MediaTrashHelper.createTrashRequest(
                    context.contentResolver, uris
                )
                trashLauncher.launch(
                    androidx.activity.result.IntentSenderRequest.Builder(pending).build()
                )
            }
        }
    }

    var overlay by remember { mutableStateOf(Overlay.NONE) }
    var viewerIndex by remember { mutableStateOf<Int?>(null) }
    var filterViewerIndex by remember { mutableStateOf<Int?>(null) }
    // Current page within each viewer's image list (videos excluded), for Samsung-style
    // "track the viewed image on return to the grid".
    var viewerPage by remember { mutableStateOf(0) }
    var filterViewerPage by remember { mutableStateOf(0) }

    // Samsung Gallery–style shrink/grow shared-element transition between thumbnail and viewer.
    val zoomState = rememberZoomTransitionState()
    val cellCornerPx = with(LocalDensity.current) { 2.dp.toPx() }
    // Opens the viewer with a grow-from-thumbnail animation; falls back to plain open for the
    // rare case where the tapped cell isn't tracked.
    val openImageWithZoom: (String, MediaItem, () -> Unit) -> Unit = { key, item, setIndex ->
        scope.launch {
            zoomState.animateOpen(key, item, cellCornerPx, item.aspectRatioOrZero())
            setIndex()
            zoomState.finishAfterHandoff()
        }
    }

    // Shared upload trigger: resolves the current selection, exits it, then enqueues the upload
    // using the current Settings (destination, keep-structure, overwrite, delete-after-upload).
    val launchUpload: (exit: () -> Unit) -> Unit = { exit ->
        scope.launch {
            val items = viewModel.resolveSelectionForUpload()
            exit()
            val uploads = items.map {
                UploadItem(
                    uri = it.uri,
                    name = it.displayName,
                    size = it.size,
                    relativePath = if (keepFolderStructure) it.bucketName else ""
                )
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            UploadScheduler.enqueue(context, uploads, destPath, overwriteOnConflict, deleteAfterUpload)
        }
    }

    // Manual "Back up old items now": upload every item older than [days] via the same interactive
    // path, so the in-app progress bar + Stop button apply. Closes Settings first so progress is visible.
    val backupOldItems: (days: Int) -> Unit = { days ->
        scope.launch {
            val items = viewModel.getMediaOlderThan(days)
            overlay = Overlay.NONE
            if (items.isEmpty()) {
                Toast.makeText(context, "No items older than $days days", Toast.LENGTH_SHORT).show()
                return@launch
            }
            val uploads = items.map {
                UploadItem(
                    uri = it.uri,
                    name = it.displayName,
                    size = it.size,
                    relativePath = if (keepFolderStructure) it.bucketName else ""
                )
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            UploadScheduler.enqueue(context, uploads, destPath, overwriteOnConflict, deleteAfterUpload)
        }
    }

    BackHandler(enabled = overlay != Overlay.NONE || viewerIndex != null || filterViewerIndex != null || state.selectionMode || state.inFolder) {
        when {
            filterViewerIndex != null -> filterViewerIndex = null
            viewerIndex != null -> viewerIndex = null
            state.filterSelectionMode && overlay == Overlay.FILTER -> viewModel.exitFilterSelection()
            overlay == Overlay.FILTER -> { overlay = Overlay.NONE; viewModel.closeFilter() }
            overlay != Overlay.NONE -> overlay = Overlay.NONE
            else -> viewModel.onBack()
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = colors.screenBackground) {
        when (overlay) {
            Overlay.ABOUT -> AboutScreen(
                appName = "Gallery Transfer",
                logDirectory = FileLogger.logDirectory,
                onLogEvent = { tag, message -> FileLogger.d(tag, message) },
                onBack = { overlay = Overlay.NONE }
            )
            Overlay.SETTINGS -> SettingsScreen(
                onBack = { overlay = Overlay.NONE },
                connected = connected,
                accountLabel = client.authManager.tokenStore.accountName ?: "",
                destinationPath = destPath,
                overwriteOnConflict = overwriteOnConflict,
                onOverwriteChange = {
                    overwriteOnConflict = it
                    prefs.overwriteOnConflict = it
                },
                deleteAfterUpload = deleteAfterUpload,
                onDeleteAfterUploadChange = {
                    deleteAfterUpload = it
                    prefs.deleteAfterUpload = it
                },
                keepFolderStructure = keepFolderStructure,
                onKeepFolderStructureChange = {
                    keepFolderStructure = it
                    prefs.keepFolderStructure = it
                },
                allowMediaReordering = state.allowMediaReordering,
                onAllowMediaReorderingChange = { viewModel.updateAllowMediaReordering(it) },
                autoUploadEnabled = autoUploadEnabled,
                onAutoUploadEnabledChange = {
                    autoUploadEnabled = it
                    prefs.autoUploadEnabled = it
                    AutoUploadScheduler.reschedule(context)
                },
                autoUploadOlderThanDays = autoUploadOlderThanDays,
                onAutoUploadDaysChange = {
                    autoUploadOlderThanDays = it
                    prefs.autoUploadOlderThanDays = it
                },
                autoUploadFrequency = autoUploadFrequency,
                onAutoUploadFrequencyChange = {
                    autoUploadFrequency = it
                    prefs.autoUploadFrequency = it
                    AutoUploadScheduler.reschedule(context)
                },
                autoUploadWifiOnly = autoUploadWifiOnly,
                onAutoUploadWifiOnlyChange = {
                    autoUploadWifiOnly = it
                    prefs.autoUploadWifiOnly = it
                    AutoUploadScheduler.reschedule(context)
                },
                onBackupOldItems = backupOldItems,
                onConnect = { manualSignIn = true },
                onPickDestination = { if (connected) showFolderPicker = true else manualSignIn = true }
            )
            Overlay.FILTER -> Box(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize().statusBarsPadding()) {
                    FilterTopBar(
                        state = state,
                        onBack = { overlay = Overlay.NONE; viewModel.closeFilter() },
                        onExitSelection = { viewModel.exitFilterSelection() },
                        onSelectAll = { viewModel.selectAllFilter() },
                        onPickType = { viewModel.showFilterTypeDialog() },
                        onPickSort = { viewModel.showFilterSortDialog() }
                    )
                    if (!state.filterSelectionMode) {
                        FilterControls(
                            state = state,
                            onSearchChange = { viewModel.setFilterNameQuery(it) },
                            onPickType = { viewModel.showFilterTypeDialog() },
                            onPickSize = { viewModel.showFilterSizeDialog() },
                            onPickDate = { viewModel.showFilterDateDialog() },
                            onClearAll = { viewModel.clearAllFilters() }
                        )
                    }
                    if (state.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = colors.primary)
                        }
                    } else {
                        val filtered = state.filteredSortedMedia
                        if (filtered.isEmpty()) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "No items match this filter",
                                    color = colors.listSecondText,
                                    fontSize = 15.sp
                                )
                            }
                        } else {
                            val filterRevealIndex = if (filterViewerIndex != null) {
                                filtered.filter { !it.isVideo }.getOrNull(filterViewerPage)?.let { img ->
                                    filtered.indexOfFirst { it.uniqueKey == img.uniqueKey }
                                } ?: -1
                            } else -1
                            ItemGrid(
                                items = filtered,
                                selectedKeys = state.filterSelectedKeys,
                                selectionMode = state.filterSelectionMode,
                                viewType = state.viewType,
                                canDrag = false,
                                onItemClick = { index, item ->
                                    if (state.filterSelectionMode) viewModel.toggleFilterSelection(item.uniqueKey)
                                    else if (item.isVideo) playVideo(context, item)
                                    else openImageWithZoom(item.uniqueKey, item) { filterViewerIndex = index }
                                },
                                onItemLongClick = { item -> viewModel.startFilterSelectionWith(item.uniqueKey) },
                                onReorder = { _, _ -> },
                                onReorderDone = {},
                                revealIndex = filterRevealIndex,
                                zoomState = zoomState
                            )
                        }
                    }
                }

                BottomActionBar(
                    visible = state.filterSelectionMode,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    selectedCount = state.filterSelectedKeys.size,
                    showAllActions = false,
                    showMove = false,
                    showShare = false,
                    showDetails = false,
                    showDelete = true,
                    showUpload = true,
                    onUpload = { launchUpload { viewModel.exitFilterSelection() } },
                    showOpenLocation = false,
                    onCopy = {}, onMove = {}, onDelete = { viewModel.moveSelectedToTrash() }, onDetails = {}, onOpenLocation = {}
                )
            }
            Overlay.NONE -> Box(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize().statusBarsPadding()) {
                    TopBar(
                        state = state,
                        onBack = { viewModel.onBack() },
                        onExitSelection = { viewModel.exitSelection() },
                        onSelectAll = { viewModel.selectAll() },
                        onToggleView = {
                            viewModel.setViewType(
                                if (state.viewType == ViewType.GRID_LARGE) ViewType.GRID_SMALL
                                else ViewType.GRID_LARGE
                            )
                        },
                        onSort = { viewModel.showSortDialog() },
                        onViewAs = { viewModel.showViewAsDialog() },
                        onSettings = { overlay = Overlay.SETTINGS },
                        onAbout = { overlay = Overlay.ABOUT },
                        onFilter = { viewModel.openFilter(); overlay = Overlay.FILTER }
                    )

                    if (state.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = colors.primary)
                        }
                    } else if (state.inFolder) {
                        val mediaRevealIndex = if (viewerIndex != null) {
                            state.media.filter { !it.isVideo }.getOrNull(viewerPage)?.let { img ->
                                state.media.indexOfFirst { it.uniqueKey == img.uniqueKey }
                            } ?: -1
                        } else -1
                        MediaGrid(
                            state = state,
                            onItemClick = { index, item ->
                                if (state.selectionMode) viewModel.toggleMediaSelection(item.uniqueKey)
                                else if (item.isVideo) playVideo(context, item)
                                else openImageWithZoom(item.uniqueKey, item) { viewerIndex = index }
                            },
                            onItemLongClick = { item -> viewModel.startSelectionWithMedia(item.uniqueKey) },
                            onReorder = { from, to -> viewModel.reorderMedia(from, to) },
                            onReorderDone = { viewModel.persistMediaOrder() },
                            revealIndex = mediaRevealIndex,
                            zoomState = zoomState
                        )
                    } else {
                        FolderGrid(
                            state = state,
                            onFolderClick = { folder ->
                                if (state.selectionMode) viewModel.toggleFolderSelection(folder.bucketId)
                                else viewModel.openFolder(folder)
                            },
                            onFolderLongClick = { folder -> viewModel.startSelectionWithFolder(folder.bucketId) },
                            onReorder = { from, to -> viewModel.reorderFolder(from, to) },
                            onReorderDone = { viewModel.persistFolderOrder() }
                        )
                    }
                }

                // Selection action bar (Upload + Open location)
                BottomActionBar(
                    visible = state.selectionMode,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    selectedCount = state.selectedCount,
                    showAllActions = false,
                    showMove = false,
                    showShare = false,
                    showDetails = false,
                    showDelete = true,
                    showUpload = true,
                    onUpload = {
                        launchUpload { viewModel.exitSelection() }
                    },
                    showOpenLocation = state.inFolder && state.selectedCount == 1,
                    onOpenLocation = {
                        viewModel.singleSelectedMedia()?.let { m ->
                            FileManagerHelper.openFolder(context, m.path.substringBeforeLast('/'))
                        }
                        viewModel.exitSelection()
                    },
                    onCopy = {}, onMove = {}, onDelete = { viewModel.moveSelectedToTrash() }, onDetails = {}
                )
            }
        }
    }

    // ── Dialogs (rendered once, unconditionally on their flags) ──
    if (state.showSortDialog) {
        if (state.inFolder) {
            SortDialog(
                options = MediaSortOption.entries,
                labelFor = { it.label },
                currentOption = state.mediaSort,
                onOptionSelected = { viewModel.setMediaSort(it) },
                onDismiss = { viewModel.dismissSortDialog() }
            )
        } else {
            SortDialog(
                options = FolderSortOption.entries,
                labelFor = { it.label },
                currentOption = state.folderSort,
                onOptionSelected = { viewModel.setFolderSort(it) },
                onDismiss = { viewModel.dismissSortDialog() }
            )
        }
    }
    if (state.showViewAsDialog) {
        ViewAsDialog(
            options = listOf(ViewType.GRID_LARGE, ViewType.GRID_SMALL),
            labelFor = { if (it == ViewType.GRID_LARGE) "Large grid" else "Small grid" },
            currentViewType = state.viewType,
            onViewTypeSelected = { viewModel.setViewType(it) },
            onDismiss = { viewModel.dismissViewAsDialog() }
        )
    }
    if (state.showFilterTypeDialog) {
        SortDialog(
            options = FilterType.entries,
            labelFor = { it.label },
            currentOption = state.filterType,
            onOptionSelected = { viewModel.setFilterType(it) },
            onDismiss = { viewModel.dismissFilterTypeDialog() }
        )
    }
    if (state.showFilterSortDialog) {
        SortDialog(
            options = FilterSortOption.entries,
            labelFor = { it.label },
            currentOption = state.filterSort,
            onOptionSelected = { viewModel.setFilterSort(it) },
            onDismiss = { viewModel.dismissFilterSortDialog() }
        )
    }
    if (state.showFilterSizeDialog) {
        SortDialog(
            options = SizeFilter.entries,
            labelFor = { it.label },
            currentOption = state.filterSize,
            onOptionSelected = { viewModel.setFilterSize(it) },
            onDismiss = { viewModel.dismissFilterSizeDialog() }
        )
    }
    if (state.showFilterDateDialog) {
        OlderThanFilterDialog(
            initialDate = state.filterDateEnd,
            onApply = { date -> viewModel.setFilterOlderThan(date) },
            onClear = { viewModel.clearFilterDate() },
            onDismiss = { viewModel.dismissFilterDateDialog() }
        )
    }

    // ── Image viewer ──
    viewerIndex?.let { start ->
        val images = state.media.filter { !it.isVideo }
        val startInImages = state.media.getOrNull(start)?.let { sel ->
            images.indexOfFirst { it.uniqueKey == sel.uniqueKey }.coerceAtLeast(0)
        } ?: 0
        if (images.isNotEmpty()) {
            ImageViewer(
                images = images,
                startIndex = startInImages,
                onClose = {
                    val img = images.getOrNull(viewerPage)
                    viewerIndex = null
                    if (img != null) scope.launch { zoomState.animateClose(img.uniqueKey, img, cellCornerPx, img.aspectRatioOrZero()) }
                },
                onPageChanged = { viewerPage = it }
            )
        }
    }

    // ── Image viewer (from the Filter list) ──
    filterViewerIndex?.let { start ->
        val filtered = state.filteredSortedMedia
        val images = filtered.filter { !it.isVideo }
        val startInImages = filtered.getOrNull(start)?.let { sel ->
            images.indexOfFirst { it.uniqueKey == sel.uniqueKey }.coerceAtLeast(0)
        } ?: 0
        if (images.isNotEmpty()) {
            ImageViewer(
                images = images,
                startIndex = startInImages,
                onClose = {
                    val img = images.getOrNull(filterViewerPage)
                    filterViewerIndex = null
                    if (img != null) scope.launch { zoomState.animateClose(img.uniqueKey, img, cellCornerPx, img.aspectRatioOrZero()) }
                },
                onPageChanged = { filterViewerPage = it }
            )
        }
    }

    // ── Dropbox upload progress + conflict resolution overlay ──
    CopyMoveAndConflictOverlayHost(
        isProgressActive = uploadState.isUploading,
        progressTitle = "Uploading…",
        progressCurrent = uploadState.current,
        progressTotal = uploadState.total,
        onCancelProgress = {
            uploadManager.cancel()
            UploadScheduler.cancel(context)
        },
        conflictFileName = uploadState.conflictFileName,
        conflictApplyToAll = uploadState.applyToAll,
        onConflictApplyToAllToggle = { uploadManager.toggleApplyToAll() },
        onReplaceConflict = { uploadManager.resolveConflict(ConflictResolution.REPLACE) },
        onRenameConflict = { uploadManager.resolveConflict(ConflictResolution.KEEP_BOTH) },
        onSkipConflict = { uploadManager.resolveConflict(ConflictResolution.SKIP) },
        renameActionLabel = "Keep both"
    )

    // ── Dropbox folder picker (destination) ──
    if (showFolderPicker) {
        DropboxFolderPickerScreen(
            repository = client.repository,
            onSelect = { path ->
                prefs.dropboxDestPath = path
                destPath = path
                showFolderPicker = false
            },
            onBack = { showFolderPicker = false }
        )
    }

    // ── Dropbox sign-in modal (auth-on-demand or manual from Settings) ──
    if (uploadState.needsAuth || manualSignIn) {
        DropboxSignInModal(
            config = client.config,
            onResult = { code ->
                scope.launch {
                    val ok = if (code != null) client.authManager.exchangeCode(code) else false
                    connected = client.authManager.isConnected
                    val wasUploadAuth = uploadState.needsAuth
                    manualSignIn = false
                    if (wasUploadAuth) uploadManager.onAuthResult(ok)
                }
            }
        )
    }

    // ── Samsung-style shrink/grow overlay (top of z-order) ──
    ZoomTransitionOverlay(zoomState) { model ->
        val item = model as MediaItem
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(item.uri)
                .placeholderMemoryCacheKey(item.uri.toString())
                .crossfade(false)
                .build(),
            contentDescription = item.displayName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun TopBar(
    state: MediaListUiState,
    onBack: () -> Unit,
    onExitSelection: () -> Unit,
    onSelectAll: () -> Unit,
    onToggleView: () -> Unit,
    onSort: () -> Unit,
    onViewAs: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onFilter: () -> Unit
) {
    val colors = LocalGalleryColors.current
    var menuExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.selectionMode) {
            IconButton(onClick = onExitSelection) {
                Icon(Icons.Default.Close, "Close", tint = colors.iconColor)
            }
            Text(
                text = "${state.selectedCount} selected",
                color = colors.listFirstText,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onSelectAll) {
                Icon(Icons.Default.SelectAll, "Select all", tint = colors.iconColor)
            }
        } else {
            if (state.inFolder) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.iconColor)
                }
            } else {
                Spacer(Modifier.width(12.dp))
            }
            Text(
                text = if (state.inFolder) state.currentBucketName else "Gallery Transfer",
                color = colors.listFirstText,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            if (!state.inFolder) {
                IconButton(onClick = onFilter) {
                    Icon(Icons.Default.FilterList, "Filter", tint = colors.iconColor)
                }
            }
            ViewTypeToggleButton(
                viewType = state.viewType,
                onClick = onToggleView
            )
            AppMoreMenuButton(
                expanded = menuExpanded,
                onExpand = { menuExpanded = true },
                onDismiss = { menuExpanded = false },
                onSortBy = onSort,
                onViewAs = onViewAs,
                onSettings = onSettings,
                onAbout = onAbout
            )
        }
    }
}

@Composable
private fun FilterTopBar(
    state: MediaListUiState,
    onBack: () -> Unit,
    onExitSelection: () -> Unit,
    onSelectAll: () -> Unit,
    onPickType: () -> Unit,
    onPickSort: () -> Unit
) {
    val colors = LocalGalleryColors.current
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.filterSelectionMode) {
            IconButton(onClick = onExitSelection) {
                Icon(Icons.Default.Close, "Close", tint = colors.iconColor)
            }
            Text(
                text = "${state.filterSelectedKeys.size} selected",
                color = colors.listFirstText,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onSelectAll) {
                Icon(Icons.Default.SelectAll, "Select all", tint = colors.iconColor)
            }
        } else {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = colors.iconColor)
            }
            Text(
                text = "Filter",
                color = colors.listFirstText,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onPickType) {
                Text(state.filterType.label, color = colors.primary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            IconButton(onClick = onPickSort) {
                Icon(Icons.AutoMirrored.Filled.Sort, "Sort", tint = colors.iconColor)
            }
        }
    }
}

@Composable
private fun FilterControls(
    state: MediaListUiState,
    onSearchChange: (String) -> Unit,
    onPickType: () -> Unit,
    onPickSize: () -> Unit,
    onPickDate: () -> Unit,
    onClearAll: () -> Unit
) {
    val colors = LocalGalleryColors.current
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = state.filterNameQuery,
            onValueChange = onSearchChange,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
            placeholder = { Text("Search by name") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = colors.iconColor) },
            trailingIcon = {
                if (state.filterNameQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear search", tint = colors.iconColor)
                    }
                }
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistChip(
                onClick = onPickType,
                label = { Text("Type: ${state.filterType.label}") }
            )
            AssistChip(
                onClick = onPickSize,
                label = { Text("Size: ${state.filterSize.label}") }
            )
            AssistChip(
                onClick = onPickDate,
                label = { Text(filterDateChipLabel(state.filterDateEnd)) }
            )
            if (state.hasActiveFilters) {
                AssistChip(
                    onClick = onClearAll,
                    label = { Text("Clear") },
                    leadingIcon = { Icon(Icons.Default.Close, contentDescription = null) }
                )
            }
        }
    }
}

private fun filterDateChipLabel(olderThan: Long?): String {
    if (olderThan == null) return "Date"
    val fmt = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return "Older than: ${fmt.format(olderThan)}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OlderThanFilterDialog(
    initialDate: Long?,
    onApply: (Long?) -> Unit,
    onClear: () -> Unit,
    onDismiss: () -> Unit
) {
    val pickerState = rememberDatePickerState(initialSelectedDateMillis = initialDate)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onApply(pickerState.selectedDateMillis) }) { Text("Apply") }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onClear) { Text("Clear") }
                TextButton(onClick = onDismiss) { Text("Cancel") }
            }
        }
    ) {
        DatePicker(
            state = pickerState,
            title = {
                Text(
                    text = "Show items older than",
                    modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 16.dp)
                )
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FolderGrid(
    state: MediaListUiState,
    onFolderClick: (com.example.common.data.model.FolderItem) -> Unit,
    onFolderLongClick: (com.example.common.data.model.FolderItem) -> Unit,
    onReorder: (Int, Int) -> Unit,
    onReorderDone: () -> Unit
) {
    val columns = if (state.viewType == ViewType.GRID_SMALL) 3 else 2
    val gridSpacing = if (state.viewType == ViewType.GRID_LARGE) 18.dp else 12.dp
    val context = LocalContext.current
    val loader = remember(context) {
        ImageLoader.Builder(context).components { add(VideoFrameDecoder.Factory()) }.crossfade(true).build()
    }
    val canDrag = state.folderSort == FolderSortOption.CUSTOM_ORDER
    val gridState = rememberLazyGridState()
    val dragState = rememberDragDropGridState(
        lazyGridState = gridState,
        onMove = { from, to ->
            if (from in state.folders.indices && to in state.folders.indices) onReorder(from, to)
        },
        onDragEnd = onReorderDone,
        onLongPressWithoutDrag = { index ->
            state.folders.getOrNull(index)?.let { onFolderLongClick(it) }
        },
        isInSelectionMode = { state.selectionMode }
    )

    val folderThumb: @Composable (com.example.common.data.model.FolderItem) -> Unit = { folder ->
        if (folder.latestItemUri != null) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(folder.latestItemUri).crossfade(true).build(),
                imageLoader = loader,
                contentDescription = folder.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(0.75f).background(Color(0xFF1A1A1A))
            )
        } else {
            FolderThumbnailPlaceholder()
        }
    }

    Box(Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            state = gridState,
            modifier = Modifier
                .fillMaxSize()
                .then(if (canDrag) Modifier.dragToReorderGrid(dragState) else Modifier),
            contentPadding = PaddingValues(
                start = gridSpacing,
                end = gridSpacing,
                top = gridSpacing,
                bottom = WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding() + gridSpacing + 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
            verticalArrangement = Arrangement.spacedBy(gridSpacing),
            userScrollEnabled = !(canDrag && dragState.isDragging)
        ) {
            itemsIndexed(state.folders, key = { _, it -> it.bucketId }) { index, folder ->
                val itemIsDragging = canDrag && dragState.draggedIndex == index
                val anyDragActive = canDrag && dragState.isDragging
                val dimModifier = if (anyDragActive && !itemIsDragging)
                    Modifier.graphicsLayer { alpha = 0.65f } else Modifier
                FolderGridItem(
                    folder = folder,
                    isSelected = folder.bucketId in state.selectedFolderIds,
                    isSelectionMode = state.selectionMode,
                    isSmallGrid = state.viewType == ViewType.GRID_SMALL,
                    isDragging = itemIsDragging,
                    onClick = { if (!dragState.consumeNextClick()) onFolderClick(folder) },
                    onLongClick = if (canDrag) null else { { onFolderLongClick(folder) } },
                    modifier = dimModifier,
                    thumbnailContent = { folderThumb(folder) }
                )
            }
        }

        // ── Floating drag overlay ──
        if (canDrag && dragState.isDragging) {
            val overlayPos = dragState.overlayPosition
            val itemSizePx = dragState.capturedItemSize
            val draggedFolder = state.folders.getOrNull(dragState.draggedIndex)
            if (draggedFolder != null && itemSizePx != null) {
                val density = LocalDensity.current
                val itemWidthDp = with(density) { itemSizePx.width.toDp() }
                val itemHeightDp = with(density) { itemSizePx.height.toDp() }
                Box(
                    modifier = Modifier
                        .offset { IntOffset(overlayPos.x.roundToInt(), overlayPos.y.roundToInt()) }
                        .width(itemWidthDp)
                        .height(itemHeightDp)
                        .zIndex(10f)
                        .graphicsLayer {
                            scaleX = 1.08f
                            scaleY = 1.08f
                            transformOrigin = TransformOrigin(0.5f, 0.5f)
                            shadowElevation = 24f
                        }
                        .border(3.dp, Color(0xFF2196F3), RoundedCornerShape(12.dp))
                ) {
                    FolderGridItem(
                        folder = draggedFolder,
                        isSelected = draggedFolder.bucketId in state.selectedFolderIds,
                        isSelectionMode = state.selectionMode,
                        isSmallGrid = state.viewType == ViewType.GRID_SMALL,
                        isDragging = false,
                        onClick = {},
                        onLongClick = null,
                        thumbnailContent = { folderThumb(draggedFolder) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MediaGrid(
    state: MediaListUiState,
    onItemClick: (Int, MediaItem) -> Unit,
    onItemLongClick: (MediaItem) -> Unit,
    onReorder: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    revealIndex: Int = -1,
    zoomState: com.example.common.ui.util.ZoomTransitionState? = null
) {
    ItemGrid(
        items = state.media,
        selectedKeys = state.selectedMediaKeys,
        selectionMode = state.selectionMode,
        viewType = state.viewType,
        canDrag = state.mediaSort == MediaSortOption.CUSTOM_ORDER && state.allowMediaReordering,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick,
        onReorder = onReorder,
        onReorderDone = onReorderDone,
        revealIndex = revealIndex,
        zoomState = zoomState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemGrid(
    items: List<MediaItem>,
    selectedKeys: Set<String>,
    selectionMode: Boolean,
    viewType: ViewType,
    canDrag: Boolean,
    onItemClick: (Int, MediaItem) -> Unit,
    onItemLongClick: (MediaItem) -> Unit,
    onReorder: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    revealIndex: Int = -1,
    zoomState: com.example.common.ui.util.ZoomTransitionState? = null
) {
    val colors = LocalGalleryColors.current
    val columns = if (viewType == ViewType.GRID_SMALL) 5 else 3
    val gridState = rememberLazyGridState()
    // Track the viewed image on return (Samsung Gallery behavior): minimal nearest-edge
    // scroll, no-op if already visible. No header row in this grid.
    LaunchedEffect(revealIndex) {
        if (revealIndex >= 0) gridState.revealItem(revealIndex, hasHeaderRow = false)
    }
    val dragState = rememberDragDropGridState(
        lazyGridState = gridState,
        onMove = { from, to -> if (from in items.indices && to in items.indices) onReorder(from, to) },
        onDragEnd = onReorderDone,
        onLongPressWithoutDrag = { index -> items.getOrNull(index)?.let { onItemLongClick(it) } },
        isInSelectionMode = { selectionMode }
    )
    Box(Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            state = gridState,
            modifier = Modifier
                .fillMaxSize()
                .then(if (canDrag) Modifier.dragToReorderGrid(dragState) else Modifier),
            contentPadding = PaddingValues(
                start = 2.dp,
                end = 2.dp,
                top = 2.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding() + 2.dp + 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            userScrollEnabled = !(canDrag && dragState.isDragging)
        ) {
            itemsIndexed(items, key = { _, item -> item.uniqueKey }) { index, item ->
                val selected = item.uniqueKey in selectedKeys
                val itemIsDragging = canDrag && dragState.draggedIndex == index
                val anyDragActive = canDrag && dragState.isDragging
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(2.dp))
                        .then(if (zoomState != null) Modifier.zoomThumbnail(item.uniqueKey, zoomState) else Modifier)
                        .graphicsLayer {
                            alpha = when {
                                itemIsDragging -> 0f
                                anyDragActive -> 0.65f
                                else -> 1f
                            }
                        }
                        .combinedClickable(
                            onClick = { if (!dragState.consumeNextClick()) onItemClick(index, item) },
                            onLongClick = if (canDrag) null else { { onItemLongClick(item) } }
                        )
                ) {
                    MediaThumbnail(item = item, modifier = Modifier.fillMaxSize())
                    if (selectionMode) {
                        Box(
                            Modifier.fillMaxSize().background(
                                if (selected) Color(0x662979FF) else Color.Transparent
                            )
                        )
                        CircularCheckIndicator(
                            isSelected = selected,
                            selectedColor = colors.primary,
                            modifier = Modifier.align(Alignment.TopEnd).padding(4.dp)
                        )
                    }
                }
            }
        }

        // ── Floating drag overlay ──
        if (canDrag && dragState.isDragging) {
            val overlayPos = dragState.overlayPosition
            val itemSizePx = dragState.capturedItemSize
            val draggedItem = items.getOrNull(dragState.draggedIndex)
            if (draggedItem != null && itemSizePx != null) {
                val density = LocalDensity.current
                val sizeDp = with(density) { itemSizePx.width.toDp() }
                Box(
                    modifier = Modifier
                        .offset { IntOffset(overlayPos.x.roundToInt(), overlayPos.y.roundToInt()) }
                        .size(sizeDp)
                        .zIndex(10f)
                        .graphicsLayer {
                            scaleX = 1.08f
                            scaleY = 1.08f
                            transformOrigin = TransformOrigin(0.5f, 0.5f)
                            shadowElevation = 24f
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .border(3.dp, Color(0xFF2196F3), RoundedCornerShape(4.dp))
                ) {
                    MediaThumbnail(item = draggedItem, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

private fun playVideo(context: android.content.Context, item: MediaItem) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(item.uri, if (item.mimeType.isNotBlank()) item.mimeType else "video/*")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        // No external player available — silently ignore.
    }
}
