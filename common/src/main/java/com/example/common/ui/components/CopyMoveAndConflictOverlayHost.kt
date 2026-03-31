package com.example.common.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Shared overlay host for copy/move progress and file-conflict resolution dialogs.
 * Renders dialogs in a Box(fillMaxSize) so they always appear on top.
 */
@Composable
fun CopyMoveAndConflictOverlayHost(
    isProgressActive: Boolean,
    progressTitle: String,
    progressCurrent: Int,
    progressTotal: Int,
    onCancelProgress: () -> Unit,
    conflictFileName: String?,
    onReplaceConflict: () -> Unit,
    onRenameConflict: () -> Unit,
    onSkipConflict: () -> Unit,
    onSkipAllConflict: (() -> Unit)? = null,
    onReplaceAllConflict: (() -> Unit)? = null,
    renameActionLabel: String = "Rename"
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isProgressActive) {
            CopyMoveProgressDialog(
                title = progressTitle,
                current = progressCurrent,
                total = progressTotal,
                onCancel = onCancelProgress
            )
        }

        if (conflictFileName != null) {
            FileConflictDialog(
                fileName = conflictFileName,
                onReplace = onReplaceConflict,
                onRename = onRenameConflict,
                onSkip = onSkipConflict,
                onSkipAll = onSkipAllConflict,
                onReplaceAll = onReplaceAllConflict,
                renameActionLabel = renameActionLabel
            )
        }
    }
}



