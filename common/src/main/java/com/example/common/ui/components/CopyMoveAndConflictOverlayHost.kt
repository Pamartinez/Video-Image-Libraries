package com.example.common.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Shared overlay host for copy/move progress and file-conflict resolution dialogs.
 * Renders dialogs in a Box(fillMaxSize) so they always appear on top.
 *
 * Uses Samsung Gallery style conflict dialog with "Apply to all items" checkbox.
 */
@Composable
fun CopyMoveAndConflictOverlayHost(
    isProgressActive: Boolean,
    progressTitle: String,
    progressCurrent: Int,
    progressTotal: Int,
    onCancelProgress: () -> Unit,
    conflictFileName: String?,
    conflictApplyToAll: Boolean,
    onConflictApplyToAllToggle: () -> Unit,
    onReplaceConflict: () -> Unit,
    onRenameConflict: () -> Unit,
    onSkipConflict: () -> Unit,
    renameActionLabel: String = "Rename"
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Show progress dialog only if no conflict is active
        // (Samsung Gallery behavior: conflict dialog takes priority, progress resumes after resolution)
        if (isProgressActive && conflictFileName == null) {
            CopyMoveProgressDialog(
                title = progressTitle,
                current = progressCurrent,
                total = progressTotal,
                onCancel = onCancelProgress
            )
        }

        // Conflict dialog takes priority over progress dialog
        if (conflictFileName != null) {
            FileConflictDialog(
                fileName = conflictFileName,
                applyToAll = conflictApplyToAll,
                onApplyToAllToggle = onConflictApplyToAllToggle,
                onReplace = onReplaceConflict,
                onRename = onRenameConflict,
                onSkip = onSkipConflict,
                onCancel = onCancelProgress,
                renameActionLabel = renameActionLabel
            )
        }
    }
}
