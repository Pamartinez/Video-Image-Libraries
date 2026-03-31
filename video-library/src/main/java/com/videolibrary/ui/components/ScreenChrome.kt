package com.videolibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videolibrary.data.model.ViewType
import com.videolibrary.ui.theme.LocalVideoColors


/**
 * Selection-mode header row: circle select-all toggle + count + Cancel pill.
 */
@Composable
fun RowScope.SelectionModeHeader(
    selectedCount: Int,
    totalCount: Int,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit
) {
    val colors = LocalVideoColors.current
    val allSelected = totalCount > 0 && selectedCount == totalCount

    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .then(
                if (allSelected) Modifier.background(Color.White)
                else Modifier.border(2.dp, Color.White, CircleShape)
            )
            .clickable { onSelectAll() },
        contentAlignment = Alignment.Center
    ) {
        if (allSelected) {
            Icon(
                Icons.Default.Check,
                contentDescription = "Deselect all",
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
        }
    }

    Spacer(Modifier.width(12.dp))

    Text(
        text = "$selectedCount",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.weight(1f)
    )

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable { onCancel() },
        color = Color(0xFF3A3A3A),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            "Cancel",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}

/**
 * Icon button that cycles through LIST → GRID_LARGE → GRID_SMALL.
 */
@Composable
fun ViewTypeToggleButton(
    viewType: ViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalVideoColors.current
    IconButton(onClick = onClick, modifier = modifier.size(40.dp)) {
        Icon(
            imageVector = when (viewType) {
                ViewType.LIST       -> Icons.AutoMirrored.Filled.ViewList
                ViewType.GRID_SMALL -> Icons.Default.GridView
                ViewType.GRID_LARGE -> Icons.Default.Apps
            },
            contentDescription = "Change view",
            tint = colors.iconColor,
            modifier = Modifier.size(22.dp)
        )
    }
}

