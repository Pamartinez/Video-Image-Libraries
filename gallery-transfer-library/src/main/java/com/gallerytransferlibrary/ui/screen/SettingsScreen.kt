package com.gallerytransferlibrary.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.components.SettingsActionButton
import com.example.common.ui.components.SettingsSection
import com.example.common.ui.components.SettingsToggleRow
import com.example.common.ui.components.SortDialog
import com.example.common.ui.theme.LocalLibraryColors
import com.gallerytransferlibrary.data.model.AutoUploadFrequency

/**
 * Settings for Gallery Transfer. Styled to match the image-library / video-library Settings screens
 * (shared [SettingsSection] cards + [ScreenTopBar]). Dropbox connect / destination and the
 * background-upload conflict policy live here.
 */
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    connected: Boolean = false,
    accountLabel: String = "",
    destinationPath: String = "",
    overwriteOnConflict: Boolean = false,
    onOverwriteChange: (Boolean) -> Unit = {},
    deleteAfterUpload: Boolean = false,
    onDeleteAfterUploadChange: (Boolean) -> Unit = {},
    keepFolderStructure: Boolean = false,
    onKeepFolderStructureChange: (Boolean) -> Unit = {},
    autoUploadEnabled: Boolean = false,
    onAutoUploadEnabledChange: (Boolean) -> Unit = {},
    autoUploadOlderThanDays: Int = 30,
    onAutoUploadDaysChange: (Int) -> Unit = {},
    autoUploadFrequency: AutoUploadFrequency = AutoUploadFrequency.DAILY,
    onAutoUploadFrequencyChange: (AutoUploadFrequency) -> Unit = {},
    autoUploadWifiOnly: Boolean = true,
    onAutoUploadWifiOnlyChange: (Boolean) -> Unit = {},
    onBackupOldItems: (Int) -> Unit = {},
    onConnect: () -> Unit = {},
    onPickDestination: () -> Unit = {}
) {
    val colors = LocalLibraryColors.current

    var showDaysDialog by remember { mutableStateOf(false) }
    var showFrequencyDialog by remember { mutableStateOf(false) }
    var showBackupDaysDialog by remember { mutableStateOf(false) }

    BackHandler { onBack() }

    Column(modifier = Modifier.fillMaxSize().background(colors.screenBackground)) {

        // ── Header ──────────────────────────────────────────────────────
        ScreenTopBar {
            CircularBackButton(onClick = onBack)
            Spacer(Modifier.width(12.dp))
            Text(
                text = "Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.listFirstText
            )
        }

        // ── Scrollable content ──────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .navigationBarsPadding()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // ── Dropbox section ─────────────────────────────────────────
            SettingsSection(title = "Dropbox") {
                SettingsActionButton(
                    icon = Icons.Default.CloudUpload,
                    title = if (connected) "Connected" else "Connect to Dropbox",
                    subtitle = if (connected) accountLabel.ifBlank { "Tap to manage" }
                    else "Sign in to upload your media",
                    onClick = onConnect,
                    enabled = !connected
                )

                Spacer(Modifier.height(8.dp))

                SettingsActionButton(
                    icon = Icons.Default.Folder,
                    title = "Upload destination",
                    subtitle = destinationPath.ifBlank { "Dropbox root (/)" },
                    onClick = onPickDestination
                )
            }

            // ── Uploads section ─────────────────────────────────────────
            SettingsSection(title = "Uploads") {
                SettingsToggleRow(
                    title = "Keep folder structure",
                    subtitle = "Upload each item into a sub-folder named after the folder it lives in on this device, inside your chosen destination. If off, everything is uploaded directly into the destination folder.",
                    checked = keepFolderStructure,
                    onCheckedChange = onKeepFolderStructureChange
                )

                Spacer(Modifier.height(8.dp))

                SettingsToggleRow(
                    title = "Delete after upload",
                    subtitle = "Move each item to this device's trash once it has been uploaded successfully. Items stay recoverable in the Gallery trash.",
                    checked = deleteAfterUpload,
                    onCheckedChange = onDeleteAfterUploadChange
                )

                Spacer(Modifier.height(8.dp))

                SettingsToggleRow(
                    title = "Overwrite files with the same name",
                    subtitle = "For background uploads (app minimized or closed): when a file with the same name already exists, replace it. If off, keep both by uploading a renamed copy. While the app is open you'll still be asked each time.",
                    checked = overwriteOnConflict,
                    onCheckedChange = onOverwriteChange
                )
            }

            // ── Automatic upload section ────────────────────────────────
            SettingsSection(title = "Automatic upload") {
                SettingsToggleRow(
                    title = "Auto-upload old items",
                    subtitle = "Automatically upload media older than the age below to Dropbox in the background, even when the app is closed. Uses your destination, keep-folder-structure and delete-after-upload settings.",
                    checked = autoUploadEnabled,
                    onCheckedChange = onAutoUploadEnabledChange
                )

                Spacer(Modifier.height(8.dp))

                SettingsActionButton(
                    icon = Icons.Default.Schedule,
                    title = "Upload items older than",
                    subtitle = "$autoUploadOlderThanDays day${if (autoUploadOlderThanDays == 1) "" else "s"}",
                    onClick = { showDaysDialog = true }
                )

                Spacer(Modifier.height(8.dp))

                SettingsActionButton(
                    icon = Icons.Default.Schedule,
                    title = "How often to check",
                    subtitle = autoUploadFrequency.label,
                    onClick = { showFrequencyDialog = true }
                )

                Spacer(Modifier.height(8.dp))

                SettingsToggleRow(
                    title = "Wi‑Fi only",
                    subtitle = "Only auto-upload while connected to an un-metered (Wi‑Fi) network. If off, mobile data may be used.",
                    checked = autoUploadWifiOnly,
                    onCheckedChange = onAutoUploadWifiOnlyChange
                )

                if (connected) {
                    Spacer(Modifier.height(8.dp))

                    SettingsActionButton(
                        icon = Icons.Default.PlayArrow,
                        title = "Back up old items now",
                        subtitle = "Pick an age, then upload all items older than that right away. A progress bar shows and you can stop anytime.",
                        onClick = { showBackupDaysDialog = true }
                    )
                }
            }
        }
    }

    if (showDaysDialog) {
        DaysStepperDialog(
            currentDays = autoUploadOlderThanDays,
            onConfirm = {
                onAutoUploadDaysChange(it)
                showDaysDialog = false
            },
            onDismiss = { showDaysDialog = false }
        )
    }

    if (showFrequencyDialog) {
        SortDialog(
            options = AutoUploadFrequency.entries,
            labelFor = { it.label },
            currentOption = autoUploadFrequency,
            onOptionSelected = {
                onAutoUploadFrequencyChange(it)
                showFrequencyDialog = false
            },
            onDismiss = { showFrequencyDialog = false }
        )
    }

    if (showBackupDaysDialog) {
        DaysStepperDialog(
            currentDays = autoUploadOlderThanDays,
            confirmLabel = "Upload",
            onConfirm = {
                showBackupDaysDialog = false
                onBackupOldItems(it)
            },
            onDismiss = { showBackupDaysDialog = false }
        )
    }
}

/** Simple +/- stepper dialog to pick the "older than N days" threshold (minimum 1 day). */
@Composable
private fun DaysStepperDialog(
    currentDays: Int,
    confirmLabel: String = "OK",
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalLibraryColors.current
    var days by remember { mutableIntStateOf(currentDays.coerceAtLeast(1)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Upload items older than", color = colors.listFirstText) },
        text = {
            Row(
                modifier = Modifier.height(64.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = { if (days > 1) days-- }) { Text("−") }
                Spacer(Modifier.width(24.dp))
                Text(
                    text = "$days day${if (days == 1) "" else "s"}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.listFirstText
                )
                Spacer(Modifier.width(24.dp))
                OutlinedButton(onClick = { days++ }) { Text("+") }
            }
        },
        confirmButton = { TextButton(onClick = { onConfirm(days) }) { Text(confirmLabel) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
