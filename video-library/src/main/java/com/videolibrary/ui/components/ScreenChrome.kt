package com.videolibrary.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.data.model.ViewType
import com.example.common.ui.components.SelectionModeHeader as CommonSelectionModeHeader
import com.example.common.ui.components.ViewTypeToggleButton as CommonViewTypeToggleButton

/**
 * Delegates to the shared [CommonSelectionModeHeader] in common.
 * Kept here so existing import paths in video-library screens stay unchanged.
 */
@Composable
fun RowScope.SelectionModeHeader(
    selectedCount: Int,
    totalCount: Int,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit
) = CommonSelectionModeHeader(
    selectedCount = selectedCount,
    totalCount    = totalCount,
    onSelectAll   = onSelectAll,
    onCancel      = onCancel
)

/**
 * Delegates to the shared [CommonViewTypeToggleButton] in common.
 * Kept here so existing import paths in video-library screens stay unchanged.
 */
@Composable
fun ViewTypeToggleButton(
    viewType: ViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = CommonViewTypeToggleButton(viewType = viewType, onClick = onClick, modifier = modifier)
