package com.videolibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.data.model.FolderItem

@Composable
fun FolderContextMenu(
    expanded: Boolean,
    folder: FolderItem,
    onDismiss: () -> Unit,
    onDelete: (FolderItem) -> Unit = {},
    onSortBy: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    com.example.common.ui.components.FolderContextMenu(
        expanded  = expanded,
        folder    = folder,
        onDismiss = onDismiss,
        onDelete  = onDelete,
        onSortBy  = onSortBy,
        modifier  = modifier
    )
}
