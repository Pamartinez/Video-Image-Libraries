package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.LocalLibraryColors

// ── Back button ──────────────────────────────────────────────────────────────

@Composable
fun CircularBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Color(0xD9323232))
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
    }
}

// ── Top bar ──────────────────────────────────────────────────────────────────

/**
 * Standard screen top bar: themed Surface with status-bar padding + content row.
 * Uses [LocalLibraryColors] so it adapts to both ImageLibraryTheme and VideoLibraryTheme.
 */
@Composable
fun ScreenTopBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val colors = LocalLibraryColors.current
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.actionBarBg
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .heightIn(min = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

// ── Actions pill ─────────────────────────────────────────────────────────────

@Composable
fun ActionsPill(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color(0x4D808080),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            content = content
        )
    }
}

// ── Menu helpers ─────────────────────────────────────────────────────────────

@Composable
fun AppMenuItem(
    text: String,
    onDismiss: () -> Unit,
    onClick: () -> Unit,
    textColor: Color,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    DropdownMenuItem(
        text = { Text(text, color = textColor) },
        onClick = { onDismiss(); onClick() },
        leadingIcon = leadingIcon
    )
}

@Composable
fun AppMenuDivider(
    color: Color,
    thickness: Dp = 1.dp,
    horizontalPadding: Dp = 16.dp
) {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        color = color,
        thickness = thickness
    )
}

// ── More-menu button ──────────────────────────────────────────────────────────

/**
 * Reusable MoreVert (⋮) icon-button + themed [DropdownMenu].
 *
 * The menu always ends with the four shared base items:
 *   Sort | View as | Settings | ─── | About App
 *
 * State is hoisted: the caller owns [expanded] / [onExpand] / [onDismiss].
 *
 * @param extraTopContent  Optional screen-specific items rendered *above* the base items.
 *                         Receives [onDismiss] so each item can close the menu after tapping.
 */
@Composable
fun AppMoreMenuButton(
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onSortBy: () -> Unit,
    onViewAs: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    modifier: Modifier = Modifier,
    extraTopContent: @Composable (onDismiss: () -> Unit) -> Unit = {}
) {
    val colors = LocalLibraryColors.current
    Box(modifier = modifier) {
        IconButton(
            onClick = onExpand,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More",
                tint = colors.iconColor,
                modifier = Modifier.size(22.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            shape = RoundedCornerShape(16.dp),
            containerColor = colors.menuBg
        ) {
            extraTopContent(onDismiss)
            AppMenuItem("Sort",      onDismiss = onDismiss, onClick = onSortBy,   textColor = colors.listFirstText)
            AppMenuItem("View as",   onDismiss = onDismiss, onClick = onViewAs,   textColor = colors.listFirstText)
            AppMenuItem("Settings",  onDismiss = onDismiss, onClick = onSettings, textColor = colors.listFirstText)
            AppMenuDivider(color = colors.dividerColor)
            AppMenuItem("About App", onDismiss = onDismiss, onClick = onAbout,    textColor = colors.listFirstText)
        }
    }
}
