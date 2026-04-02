package com.videolibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.videolibrary.data.model.FolderSortOption
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.example.common.util.FormatUtils
import com.example.common.ui.components.DetailsDialog
import com.example.common.ui.components.SortDialog

// ── Re-export shared dialogs so callers don't need to change imports ──────────

/**
 * Delegates to [com.example.common.ui.components.ViewAsDialog].
 * Video-library shows List / Grid / Expand options.
 */
@Composable
fun ViewAsDialog(
    currentViewType: ViewType,
    onViewTypeSelected: (ViewType) -> Unit,
    onDismiss: () -> Unit
) {
    com.example.common.ui.components.ViewAsDialog(
        options           = listOf(ViewType.LIST, ViewType.GRID_SMALL, ViewType.GRID_LARGE),
        labelFor          = { vt ->
            when (vt) {
                ViewType.LIST       -> "List view"
                ViewType.GRID_SMALL -> "Grid view"
                ViewType.GRID_LARGE -> "Expand view"
            }
        },
        currentViewType    = currentViewType,
        onViewTypeSelected = onViewTypeSelected,
        onDismiss          = onDismiss
    )
}

/**
 * Delegates to [com.example.common.ui.components.MoveToGroupPickerDialog].
 */
@Composable
fun MoveToGroupPickerDialog(
    groups: List<com.example.common.data.model.GroupItem>,
    onMove: (targetGroupId: Long?) -> Unit,
    onDismiss: () -> Unit
) {
    com.example.common.ui.components.MoveToGroupPickerDialog(
        groups    = groups,
        onMove    = onMove,
        onDismiss = onDismiss
    )
}

// ── VideoDetailsDialog ────────────────────────────────────────────────────────

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

// ── FolderSortDialog ──────────────────────────────────────────────────────────

/**
 * Sort dialog for the Folders tab and Group detail screen.
 * Wraps the common generic [SortDialog].
 */
@Composable
fun FolderSortDialog(
    currentSortOption: FolderSortOption,
    onSortOptionSelected: (FolderSortOption) -> Unit,
    onDismiss: () -> Unit
) {
    SortDialog(
        options          = FolderSortOption.entries.toList(),
        labelFor         = { it.label },
        currentOption    = currentSortOption,
        onOptionSelected = onSortOptionSelected,
        onDismiss        = onDismiss
    )
}
