package com.imagelibrary.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.ui.components.AppMenuDivider
import com.example.common.ui.components.AppMenuItem
import com.example.common.data.model.FolderItem
import com.imagelibrary.data.model.ImageItem

private val TopMenuColor = Color(0xE6000000)

@Composable
fun ImageContextMenu(
    expanded: Boolean,
    image: ImageItem?,
    onDismiss: () -> Unit,
    onDelete: (ImageItem) -> Unit,
    onRename: (ImageItem) -> Unit,
    onDetails: (ImageItem) -> Unit,
    onSortBy: () -> Unit
) {
    DropdownMenu(
        expanded = expanded && image != null,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(20.dp),
        containerColor = TopMenuColor
    ) {
        AppMenuItem("Delete",  onDismiss = onDismiss, onClick = { image?.let(onDelete) },  textColor = Color.White, leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White) })
        AppMenuItem("Rename",  onDismiss = onDismiss, onClick = { image?.let(onRename) },  textColor = Color.White, leadingIcon = { Icon(Icons.Default.Edit,   contentDescription = null, tint = Color.White) })
        AppMenuDivider(color = Color.White.copy(alpha = 0.15f), horizontalPadding = 0.dp)
        AppMenuItem("Details", onDismiss = onDismiss, onClick = { image?.let(onDetails) }, textColor = Color.White, leadingIcon = { Icon(Icons.Default.Info,   contentDescription = null, tint = Color.White) })
        AppMenuItem("Sort by", onDismiss = onDismiss, onClick = { onSortBy() },            textColor = Color.White, leadingIcon = { Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null, tint = Color.White) })
    }
}

@Composable
fun FolderContextMenu(
    expanded: Boolean,
    folder: FolderItem?,
    onDismiss: () -> Unit,
    onDelete: (FolderItem) -> Unit,
    onSortBy: () -> Unit
) {
    DropdownMenu(
        expanded = expanded && folder != null,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(20.dp),
        containerColor = TopMenuColor
    ) {
        AppMenuItem("Delete",  onDismiss = onDismiss, onClick = { folder?.let(onDelete) }, textColor = Color.White, leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White) })
        AppMenuDivider(color = Color.White.copy(alpha = 0.15f), horizontalPadding = 0.dp)
        AppMenuItem("Sort by", onDismiss = onDismiss, onClick = { onSortBy() },            textColor = Color.White, leadingIcon = { Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null, tint = Color.White) })
    }
}
