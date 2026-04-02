package com.imagelibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.SortOption
import com.imagelibrary.data.model.ViewType
import com.example.common.util.FormatUtils
import com.example.common.ui.components.DetailsDialog
import com.example.common.ui.components.SortDialog

// ── Sort dialog (folder / group sort options) ────────────────────────────────

/**
 * Wraps the common generic [SortDialog] with image-library folder sort options.
 */
@Composable
fun SortDialog(
    currentSortOption: SortOption,
    onSortOptionSelected: (SortOption) -> Unit,
    onDismiss: () -> Unit
) {
    SortDialog(
        options          = SortOption.entries.toList(),
        labelFor         = { it.label },
        currentOption    = currentSortOption,
        onOptionSelected = onSortOptionSelected,
        onDismiss        = onDismiss
    )
}

// ── ViewAsDialog ─────────────────────────────────────────────────────────────

/**
 * Delegates to [com.example.common.ui.components.ViewAsDialog].
 * Image-library shows Grid / Expand options (no List).
 */
@Composable
fun ViewAsDialog(
    currentViewType: ViewType,
    onViewTypeSelected: (ViewType) -> Unit,
    onDismiss: () -> Unit
) {
    com.example.common.ui.components.ViewAsDialog(
        options           = listOf(ViewType.GRID_SMALL, ViewType.GRID_LARGE),
        labelFor          = { vt ->
            when (vt) {
                ViewType.GRID_SMALL -> "Grid view"
                ViewType.GRID_LARGE -> "Expand view"
                else                -> vt.name
            }
        },
        currentViewType    = currentViewType,
        onViewTypeSelected = onViewTypeSelected,
        onDismiss          = onDismiss
    )
}

// ── MoveToGroupPickerDialog ───────────────────────────────────────────────────

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

// ── ImageDetailsDialog ────────────────────────────────────────────────────────

@Composable
fun ImageDetailsDialog(
    image: ImageItem,
    onDismiss: () -> Unit
) {
    val ctx = LocalContext.current
    DetailsDialog(
        rows = buildList {
            add("Name"          to image.displayName)
            add("Size"          to FormatUtils.formatFileSize(ctx, image.size))
            if (image.width > 0 && image.height > 0)
                add("Resolution" to "${image.width} x ${image.height}")
            add("Format"        to image.mimeType)
            add("Date modified" to FormatUtils.formatDate(image.dateModified))
            add("Folder"        to image.bucketName)
        },
        path      = image.path,
        onDismiss = onDismiss
    )
}
