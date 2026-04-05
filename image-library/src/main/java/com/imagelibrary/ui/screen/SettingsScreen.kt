package com.imagelibrary.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.common.ui.components.SettingsSection
import com.example.common.ui.components.SettingsToggleRow
import com.example.common.ui.screen.SharedSettingsScreen
import com.imagelibrary.ui.viewmodel.ImageListViewModel

@Composable
fun SettingsScreen(
    viewModel: ImageListViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    BackHandler { onBack() }

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
        backupPath                = "Documents/ImageLibrary/backups/",
        modifier                  = modifier,
        extraContent            = {
            // ── Carousel section (image-library only) ──
            SettingsSection(title = "Carousel") {
                SettingsToggleRow(
                    title           = "Show overlay on open",
                    subtitle        = "Display the back button, thumbnails and Share / Delete bar immediately when opening a photo",
                    checked         = state.carouselShowBarsOnOpen,
                    onCheckedChange = { viewModel.updateCarouselShowBarsOnOpen(it) }
                )
                SettingsToggleRow(
                    title           = "Hide bottom overlay",
                    subtitle        = "Never show the thumbnail filmstrip and action bar when viewing photos",
                    checked         = state.carouselAlwaysHideOverlay,
                    onCheckedChange = { viewModel.updateCarouselAlwaysHideOverlay(it) }
                )
            }
        }
    )
}
