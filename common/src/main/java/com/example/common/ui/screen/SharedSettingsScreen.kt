package com.example.common.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.components.SettingsActionButton
import com.example.common.ui.components.SettingsSection
import com.example.common.ui.components.SettingsToggleRow
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared Settings screen used by both image-library and video-library.
 *
 * @param onBack                   Navigate back.
 * @param autoBackupEnabled        Current value of the auto-backup toggle.
 * @param independentSortEnabled   Current value of the independent sort toggle.
 * @param groupsAlwaysOnTop        Current value of the groups-always-on-top toggle.
 * @param floatingTopBarEnabled    Current value of the floating top bar toggle.
 * @param onAutoBackupChange       Invoked when the auto-backup toggle changes.
 * @param onIndependentSortChange  Invoked when the independent sort toggle changes.
 * @param onGroupsAlwaysOnTopChange Invoked when the groups-always-on-top toggle changes.
 * @param onFloatingTopBarChange   Invoked when the floating top bar toggle changes.
 * @param onBackup                 Suspending action that saves the backup; returns true on success.
 * @param onRestore                Suspending action that restores from backup; returns true on success.
 * @param onRefreshAlbumPreviews   Action that refreshes album preview images.
 * @param backupPath               Human-readable folder path (e.g. "Documents/VideoLibrary/backups/").
 * @param onClearVideoThumbnails   Optional action to clear video thumbnail cache (video-library only).
 *                                 (e.g. the Carousel section in image-library).
 */
@Composable
fun SharedSettingsScreen(
    onBack: () -> Unit,
    autoBackupEnabled: Boolean,
    independentSortEnabled: Boolean,
    groupsAlwaysOnTop: Boolean,
    floatingTopBarEnabled: Boolean,
    onAutoBackupChange: (Boolean) -> Unit,
    onIndependentSortChange: (Boolean) -> Unit,
    onGroupsAlwaysOnTopChange: (Boolean) -> Unit,
    onFloatingTopBarChange: (Boolean) -> Unit,
    onBackup: suspend () -> Boolean,
    onRestore: suspend () -> Boolean,
    onRefreshAlbumPreviews: () -> Unit,
    backupPath: String,
    extraContent: @Composable ColumnScope.() -> Unit = {},
    onClearVideoThumbnails: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val ctx    = LocalContext.current
    val colors = LocalLibraryColors.current
    val scope  = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {

        // ── Header ──────────────────────────────────────────────────────
        ScreenTopBar {
            CircularBackButton(onClick = onBack)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text       = "Settings",
                fontSize   = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color      = colors.listFirstText
            )
        }

        // ── Scrollable content ────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // ── App-specific sections (e.g. Carousel for image-library) ──
            extraContent()

            // ── Interface section ─────────────────────────────────────
            SettingsSection(title = "Interface") {
                SettingsToggleRow(
                    title           = "Floating top bar",
                    subtitle        = "Samsung Gallery style: floating buttons over full-screen content when scrolling",
                    checked         = floatingTopBarEnabled,
                    onCheckedChange = onFloatingTopBarChange
                )
            }

            // ── Sorting section ───────────────────────────────────────
            SettingsSection(title = "Sorting") {
                SettingsToggleRow(
                    title           = "Independent album/group sort",
                    subtitle        = "Allow each album or group to have its own sort order. If off, all use the global sort.",
                    checked         = independentSortEnabled,
                    onCheckedChange = onIndependentSortChange
                )

                HorizontalDivider(
                    color    = colors.dividerColor,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                SettingsToggleRow(
                    title           = "Groups always on top",
                    subtitle        = "When sorting by name or count, groups are shown first (sorted among themselves), followed by ungrouped albums.",
                    checked         = groupsAlwaysOnTop,
                    onCheckedChange = onGroupsAlwaysOnTopChange
                )
            }

            // ── Data section ──────────────────────────────────────────
            SettingsSection(title = "Data") {
                SettingsToggleRow(
                    title           = "Auto-backup",
                    subtitle        = "Automatically save a backup after changes and when leaving the app",
                    checked         = autoBackupEnabled,
                    onCheckedChange = onAutoBackupChange
                )

                HorizontalDivider(
                    color    = colors.dividerColor,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                SettingsActionButton(
                    icon     = Icons.Default.Backup,
                    title    = "Backup Settings & Groups",
                    subtitle = "Save all settings and group data to\n${backupPath}backup.json"
                ) {
                    scope.launch {
                        val ok = onBackup()
                        Toast.makeText(
                            ctx,
                            if (ok) "Backup saved to $backupPath" else "Backup failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                Spacer(Modifier.height(8.dp))

                SettingsActionButton(
                    icon     = Icons.Default.Restore,
                    title    = "Restore from Backup",
                    subtitle = "Load settings and group data from\n${backupPath}backup.json"
                ) {
                    scope.launch {
                        val ok = onRestore()
                        Toast.makeText(
                            ctx,
                            if (ok) "Backup restored successfully"
                            else    "Restore failed — backup.json not found or invalid",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (ok) onBack()
                    }
                }

                Spacer(Modifier.height(8.dp))

                SettingsActionButton(
                    icon     = Icons.Default.Refresh,
                    title    = "Refresh Album Previews",
                    subtitle = "Update album cover images to reflect current sort order and content"
                ) {
                    onRefreshAlbumPreviews()
                    Toast.makeText(ctx, "Album previews refreshed", Toast.LENGTH_SHORT).show()
                }

                // ── Clear Video Thumbnails (video-library only) ──
                onClearVideoThumbnails?.let { clearAction ->
                    Spacer(Modifier.height(8.dp))

                    SettingsActionButton(
                        icon     = Icons.Default.Refresh,
                        title    = "Clear Video Thumbnails",
                        subtitle = "Remove all cached thumbnails and regenerate them from videos"
                    ) {
                        clearAction()
                        Toast.makeText(ctx, "Video thumbnails cleared - will regenerate", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}

