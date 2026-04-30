package com.videolibrary.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.common.ui.screen.SharedSettingsScreen
import com.videolibrary.ui.components.clearVideoThumbnailCache
import com.videolibrary.ui.viewmodel.VideoListViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: VideoListViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    SharedSettingsScreen(
        onBack                     = onBack,
        autoBackupEnabled          = state.autoBackupEnabled,
        independentSortEnabled     = state.independentSortEnabled,
        independentViewTypeEnabled = state.independentViewTypeEnabled,
        groupsAlwaysOnTop          = state.groupsAlwaysOnTop,
        floatingTopBarEnabled      = state.floatingTopBarEnabled,
        onAutoBackupChange         = { viewModel.updateAutoBackupEnabled(it) },
        onIndependentSortChange    = { viewModel.updateIndependentSortEnabled(it) },
        onIndependentViewTypeChange = { viewModel.updateIndependentViewTypeEnabled(it) },
        onGroupsAlwaysOnTopChange  = { viewModel.updateGroupsAlwaysOnTop(it) },
        onFloatingTopBarChange     = { viewModel.updateFloatingTopBarEnabled(it) },
        onBackup                   = { viewModel.saveBackupToFile() },
        onRestore                  = { viewModel.restoreBackupFromFile() },
        onRefreshAlbumPreviews     = { viewModel.refreshAlbumPreviews() },
        onClearVideoThumbnails     = { scope.launch { clearVideoThumbnailCache() } },
        backupPath                 = "Documents/VideoLibrary/backups/",
        modifier                   = modifier
    )
}
