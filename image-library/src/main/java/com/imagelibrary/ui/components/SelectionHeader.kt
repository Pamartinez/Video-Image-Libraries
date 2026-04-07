package com.imagelibrary.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.common.ui.components.PillButton as CommonPillButton
import com.example.common.ui.components.SelectionHeader as CommonSelectionHeader

/**
 * Delegates to the shared [CommonSelectionHeader] in common.
 * Kept here so existing import paths in image-library screens stay unchanged.
 * Now uses standardized signature (totalCount instead of allSelected).
 */
@Composable
fun RowScope.SelectionHeader(
    selectedCount: Int,
    totalCount: Int,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit
) = CommonSelectionHeader(
    selectedCount = selectedCount,
    totalCount    = totalCount,
    onSelectAll   = onSelectAll,
    onCancel      = onCancel
)

/**
 * Delegates to the shared [CommonPillButton] in common.
 * Kept here so existing import paths in image-library screens stay unchanged.
 */
@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color(0xFF3A3A3A)
) = CommonPillButton(
    text           = text,
    onClick        = onClick,
    modifier       = modifier,
    enabled        = enabled,
    containerColor = containerColor
)
