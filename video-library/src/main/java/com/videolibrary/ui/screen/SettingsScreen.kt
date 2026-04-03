package com.videolibrary.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.common.ui.screen.SharedSettingsScreen
import com.videolibrary.ui.viewmodel.VideoListViewModel

@Composable
fun SettingsScreen(
    viewModel: VideoListViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    SharedSettingsScreen(
        onBack                    = onBack,
        autoBackupEnabled         = state.autoBackupEnabled,
        independentSortEnabled    = state.independentSortEnabled,
        groupsAlwaysOnTop         = state.groupsAlwaysOnTop,
        onAutoBackupChange        = { viewModel.updateAutoBackupEnabled(it) },
        onIndependentSortChange   = { viewModel.updateIndependentSortEnabled(it) },
        onGroupsAlwaysOnTopChange = { viewModel.updateGroupsAlwaysOnTop(it) },
        onBackup                  = { viewModel.saveBackupToFile() },
        onRestore                 = { viewModel.restoreBackupFromFile() },
        backupPath                = "Documents/VideoLibrary/backups/",
        modifier                  = modifier
    )
}
