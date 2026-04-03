package com.imagelibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.common.data.model.ViewType
import com.example.common.ui.components.ViewTypeToggleButton as CommonViewTypeToggleButton

/**
 * Delegates to the shared [CommonViewTypeToggleButton] in common.
 * Kept here so existing import paths in image-library screens stay unchanged.
 */
@Composable
fun ViewTypeToggleButton(
    viewType: ViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = CommonViewTypeToggleButton(viewType = viewType, onClick = onClick, modifier = modifier)
