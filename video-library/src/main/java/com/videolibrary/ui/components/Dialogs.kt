package com.videolibrary.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.FolderSortOption
import com.example.common.util.FormatUtils
import com.example.common.ui.components.ViewOptionRow
import com.example.common.ui.components.DetailsDialog
import com.videolibrary.ui.theme.LocalVideoColors
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items

private val PopupShape = RoundedCornerShape(28.dp)



@Composable
fun ViewAsDialog(
    currentViewType: com.videolibrary.data.model.ViewType,
    onViewTypeSelected: (com.videolibrary.data.model.ViewType) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalVideoColors.current
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = PopupShape,
        containerColor = colors.popupBg,
        titleContentColor = colors.listFirstText,
        textContentColor = colors.listFirstText,
        title = { Text("View as") },
        text = {
            Column {
                ViewOptionRow("List view", currentViewType == com.videolibrary.data.model.ViewType.LIST) {
                    onViewTypeSelected(com.videolibrary.data.model.ViewType.LIST)
                    onDismiss()
                }
                ViewOptionRow("Grid view", currentViewType == com.videolibrary.data.model.ViewType.GRID_SMALL) {
                    onViewTypeSelected(com.videolibrary.data.model.ViewType.GRID_SMALL)
                    onDismiss()
                }
                ViewOptionRow("Expand view", currentViewType == com.videolibrary.data.model.ViewType.GRID_LARGE) {
                    onViewTypeSelected(com.videolibrary.data.model.ViewType.GRID_LARGE)
                    onDismiss()
                }
            }
        },
        confirmButton = {}
    )
}




@Composable
fun VideoDetailsDialog(
    video: VideoItem,
    onDismiss: () -> Unit
) {
    val ctx = LocalContext.current
    DetailsDialog(
        rows = buildList {
            add("Name"          to video.displayName)
            add("Duration"      to FormatUtils.formatDuration(video.duration))
            add("Size"          to FormatUtils.formatFileSize(ctx, video.size))
            if (video.width > 0 && video.height > 0)
                add("Resolution" to "${video.width} x ${video.height}")
            add("Format"        to video.mimeType)
            add("Date modified" to FormatUtils.formatDate(video.dateModified))
            add("Folder"        to video.bucketName)
        },
        path      = video.path,
        onDismiss = onDismiss
    )
}

// ── Group dialogs ────────────────────────────────────────────────────────────



/**
 * Full-screen dialog / bottom-sheet style picker that lets the user move
 * selected folders and groups into an existing group, or ungroup them (None).
 */
@Composable
fun MoveToGroupPickerDialog(
    groups: List<com.example.common.data.model.GroupItem>,
    onMove: (targetGroupId: Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalVideoColors.current
    Dialog(
        onDismissRequest = onDismiss,
        properties       = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier      = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.7f),
            shape         = RoundedCornerShape(24.dp),
            color         = colors.popupBg,
            tonalElevation = 8.dp
        ) {
            Column(Modifier.fillMaxSize()) {
                Text(
                    "Move to group",
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color      = colors.listFirstText,
                    modifier   = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 8.dp)
                )
                HorizontalDivider(color = colors.dividerColor)
                androidx.compose.foundation.lazy.LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    item {
                        MoveTargetRow(
                            name    = "None (ungroup)",
                            onClick = { onMove(null); onDismiss() },
                            colors  = colors
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color    = colors.dividerColor
                        )
                    }
                    items(groups) { g ->
                        MoveTargetRow(
                            name    = g.name,
                            onClick = { onMove(g.groupId); onDismiss() },
                            colors  = colors
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color    = colors.dividerColor
                        )
                    }
                }
                HorizontalDivider(color = colors.dividerColor)
                TextButton(
                    onClick  = onDismiss,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 8.dp, top = 4.dp)
                ) {
                    Text("Cancel", color = colors.primary, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun MoveTargetRow(
    name: String,
    onClick: () -> Unit,
    colors: com.videolibrary.ui.theme.VideoLibraryColors
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 14.dp)
    ) {
        Text(name, fontSize = 16.sp, color = colors.listFirstText)
    }
}

/**
 * Sort dialog for the Folders tab and Group detail screen.
 * Wraps the common generic [com.example.common.ui.components.SortDialog].
 */
@Composable
fun FolderSortDialog(
    currentSortOption: FolderSortOption,
    onSortOptionSelected: (FolderSortOption) -> Unit,
    onDismiss: () -> Unit
) {
    com.example.common.ui.components.SortDialog(
        options          = FolderSortOption.entries.toList(),
        labelFor         = { it.label },
        currentOption    = currentSortOption,
        onOptionSelected = onSortOptionSelected,
        onDismiss        = onDismiss
    )
}



