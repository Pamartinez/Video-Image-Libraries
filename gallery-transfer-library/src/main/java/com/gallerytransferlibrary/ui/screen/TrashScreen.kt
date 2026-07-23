package com.gallerytransferlibrary.ui.screen

import androidx.compose.runtime.Composable
import com.example.common.data.util.TrashManager
import com.example.common.ui.screen.SharedTrashScreen
import com.gallerytransferlibrary.ui.viewmodel.MediaListUiState

/**
 * Thin gallery-transfer wrapper over the shared internal-Trash browser. Behaviour and appearance
 * are identical across all three apps (see [com.example.common.ui.screen.SharedTrashScreen]).
 */
@Composable
fun TrashScreen(
    state: MediaListUiState,
    onBack: () -> Unit,
    onToggleSelect: (String) -> Unit,
    onLongPress: (String) -> Unit,
    onSelectAll: () -> Unit,
    onExitSelection: () -> Unit,
    onRestore: () -> Unit,
    onDeleteForever: () -> Unit,
    onEmptyAll: () -> Unit
) {
    SharedTrashScreen(
        entries = state.trashItems,
        selectedIds = state.trashSelectedIds,
        selectionMode = state.trashSelectionMode,
        isLoading = state.isTrashLoading,
        retentionDays = TrashManager.DEFAULT_RETENTION_DAYS,
        onBack = onBack,
        onToggleSelect = onToggleSelect,
        onLongPress = onLongPress,
        onSelectAll = onSelectAll,
        onExitSelection = onExitSelection,
        onRestore = onRestore,
        onDeleteForever = onDeleteForever,
        onEmptyAll = onEmptyAll
    )
}
