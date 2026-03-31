package com.imagelibrary.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * View-type toggle icon button (GRID_LARGE ↔ GRID_SMALL).
 */
@Composable
fun ViewTypeToggleButton(
    viewType: ViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current
    IconButton(
        onClick = onClick,
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            imageVector = when (viewType) {
                ViewType.GRID_LARGE -> Icons.Default.GridView
                ViewType.GRID_SMALL -> Icons.Default.Apps
            },
            contentDescription = "Change view",
            tint = colors.iconColor,
            modifier = Modifier.size(22.dp)
        )
    }
}
