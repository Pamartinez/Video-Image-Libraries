package com.videolibrary.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
import com.example.common.data.model.FolderItem
import com.videolibrary.data.model.VideoItem
import com.videolibrary.ui.theme.LocalVideoColors

@Composable
fun VideoContextMenu(
    expanded: Boolean,
    video: VideoItem,
    onDismiss: () -> Unit,
    onShare: (VideoItem) -> Unit = {},
    onDelete: (VideoItem) -> Unit = {},
    onRename: (VideoItem) -> Unit = {},
    onDetails: (VideoItem) -> Unit = {},
    onSortBy: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = colors.menuBg,
        modifier = modifier
    ) {
        AppMenuItem("Share",   onDismiss = onDismiss, onClick = { onShare(video) },   textColor = colors.listFirstText)
        AppMenuItem("Rename",  onDismiss = onDismiss, onClick = { onRename(video) },  textColor = colors.listFirstText)
        AppMenuItem("Details", onDismiss = onDismiss, onClick = { onDetails(video) }, textColor = colors.listFirstText)
        AppMenuItem("Sort by", onDismiss = onDismiss, onClick = onSortBy,             textColor = colors.listFirstText)
        AppMenuDivider(color = colors.dividerColor)
        AppMenuItem("Delete",  onDismiss = onDismiss, onClick = { onDelete(video) },  textColor = Color(0xFFEF5350))
    }
}

@Composable
fun FolderContextMenu(
    expanded: Boolean,
    folder: FolderItem,
    onDismiss: () -> Unit,
    onDelete: (FolderItem) -> Unit = {},
    onSortBy: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = colors.menuBg,
        modifier = modifier
    ) {
        AppMenuItem("Sort by", onDismiss = onDismiss, onClick = onSortBy,               textColor = colors.listFirstText)
        AppMenuDivider(color = colors.dividerColor)
        AppMenuItem("Delete",  onDismiss = onDismiss, onClick = { onDelete(folder) },   textColor = Color(0xFFEF5350))
    }
}
