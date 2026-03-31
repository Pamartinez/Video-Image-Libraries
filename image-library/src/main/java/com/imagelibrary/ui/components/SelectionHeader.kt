package com.imagelibrary.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Selection mode header showing selected count, Select All toggle, and Cancel button.
 */
@Composable
fun SelectionHeader(
    selectedCount: Int,
    allSelected: Boolean,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onCancel) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Cancel selection",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "$selectedCount selected",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        PillButton(
            text = if (allSelected) "Deselect all" else "Select all",
            onClick = onSelectAll
        )
    }
}

/**
 * Small pill-shaped button used in headers.
 */
@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color(0xFF3A3A3A)
) {
    Surface(
        modifier = modifier.clickable(enabled = enabled, onClick = onClick),
        shape = RoundedCornerShape(50),
        color = containerColor
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.4f),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

