package com.imagelibrary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.SortOption
import com.example.common.util.FormatUtils
import com.example.common.ui.components.ViewOptionRow
import com.example.common.ui.components.DetailsDialog
import com.imagelibrary.ui.theme.LocalImageColors
import androidx.compose.foundation.shape.RoundedCornerShape

private val PopupShape = RoundedCornerShape(28.dp)

/**
 * Sort dialog for folder/group sort options. Wraps the common generic [SortDialog].
 */
@Composable
fun SortDialog(
    currentSortOption: SortOption,
    onSortOptionSelected: (SortOption) -> Unit,
    onDismiss: () -> Unit
) {
    com.example.common.ui.components.SortDialog(
        options          = SortOption.entries.toList(),
        labelFor         = { it.label },
        currentOption    = currentSortOption,
        onOptionSelected = onSortOptionSelected,
        onDismiss        = onDismiss
    )
}

@Composable
fun ViewAsDialog(
    currentViewType: com.imagelibrary.data.model.ViewType,
    onViewTypeSelected: (com.imagelibrary.data.model.ViewType) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalImageColors.current
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = PopupShape,
        containerColor = colors.popupBg,
        titleContentColor = colors.listFirstText,
        textContentColor = colors.listFirstText,
        title = { Text("View as") },
        text = {
            Column {
                ViewOptionRow("Grid view", currentViewType == com.imagelibrary.data.model.ViewType.GRID_SMALL) {
                    onViewTypeSelected(com.imagelibrary.data.model.ViewType.GRID_SMALL)
                    onDismiss()
                }
                ViewOptionRow("Expand view", currentViewType == com.imagelibrary.data.model.ViewType.GRID_LARGE) {
                    onViewTypeSelected(com.imagelibrary.data.model.ViewType.GRID_LARGE)
                    onDismiss()
                }
            }
        },
        confirmButton = {}
    )
}

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




