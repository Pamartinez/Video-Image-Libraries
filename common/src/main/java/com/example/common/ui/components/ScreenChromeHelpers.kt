package com.example.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.LocalLibraryColors
import androidx.compose.ui.unit.sp
import com.example.common.data.model.ViewType

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

// ─────────────────────────────────────────────────────────────────────────────
// FolderContextMenu
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Shared context menu for a [com.example.common.data.model.FolderItem].
 * Used by both image-library and video-library.
 */
@Composable
fun FolderContextMenu(
    expanded: Boolean,
    folder: com.example.common.data.model.FolderItem?,
    onDismiss: () -> Unit,
    onDelete: (com.example.common.data.model.FolderItem) -> Unit,
    onSortBy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalLibraryColors.current
    DropdownMenu(
        expanded         = expanded && folder != null,
        onDismissRequest = onDismiss,
        shape            = RoundedCornerShape(16.dp),
        containerColor   = colors.menuBg,
        modifier         = modifier
    ) {
        AppMenuItem("Sort by", onDismiss = onDismiss, onClick = onSortBy,                          textColor = colors.listFirstText)
        AppMenuDivider(color = colors.dividerColor)
        AppMenuItem("Delete",  onDismiss = onDismiss, onClick = { folder?.let(onDelete) },         textColor = androidx.compose.ui.graphics.Color(0xFFEF5350))
    }
}


// ─────────────────────────────────────────────────────────────────────────────
// ViewTypeToggleButton
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Icon button that cycles through LIST → GRID_LARGE → GRID_SMALL.
 * Uses [LocalLibraryColors] so it adapts to both library themes.
 */
@Composable
fun ViewTypeToggleButton(
    viewType: ViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalLibraryColors.current
    IconButton(onClick = onClick, modifier = modifier.size(40.dp)) {
        Icon(
            imageVector = when (viewType) {
                ViewType.LIST       -> Icons.AutoMirrored.Filled.ViewList
                ViewType.GRID_SMALL -> Icons.Default.GridView
                ViewType.GRID_LARGE -> Icons.Default.Apps
            },
            contentDescription = "Change view",
            tint               = colors.iconColor,
            modifier           = Modifier.size(22.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SelectionModeHeader  (circle toggle + count pill  |  Cancel pill)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Selection-mode header row.
 *
 * Layout:
 *  ┌──────────────────┐          ┌──────────────┐
 *  │  ○  0            │  spacer  │    Cancel    │
 *  └──────────────────┘          └──────────────┘
 *
 * The left pill is the select-all toggle (circle icon + count).
 * The right pill dismisses selection mode.
 */
@Composable
fun RowScope.SelectionModeHeader(
    selectedCount: Int,
    totalCount: Int,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit
) {
    val allSelected = totalCount > 0 && selectedCount == totalCount
    val pillColor   = Color(0xCC2A2A2A)
    val pillShape   = RoundedCornerShape(14.dp)

    // ── Left pill: circle icon + count ───────────────────────────────────
    Surface(
        modifier = Modifier.clickable(onClick = onSelectAll),
        shape    = pillShape,
        color    = pillColor
    ) {
        Row(
            modifier              = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .then(
                        if (allSelected) Modifier.background(Color.White, CircleShape)
                        else Modifier.border(2.dp, Color.White, CircleShape)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (allSelected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Deselect all",
                        tint               = Color.Black,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }
            Text(
                text       = "$selectedCount",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
        }
    }

    Spacer(Modifier.weight(1f))

    // ── Right pill: Cancel ────────────────────────────────────────────────
    Surface(
        modifier = Modifier.clickable(onClick = onCancel),
        shape    = pillShape,
        color    = pillColor
    ) {
        Text(
            text       = "Cancel",
            fontSize   = 16.sp,
            fontWeight = FontWeight.Bold,
            color      = Color.White,
            modifier   = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SelectionHeader  (image-library style — same two-pill layout as SelectionModeHeader)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Selection-mode header used by image-library.
 * Same two dark rounded-rect pill layout as [SelectionModeHeader]:
 *  left pill = circle toggle + count,  right pill = Cancel.
 */
@Composable
fun RowScope.SelectionHeader(
    selectedCount: Int,
    allSelected: Boolean,
    onSelectAll: () -> Unit,
    onCancel: () -> Unit
) {
    val pillColor = Color(0xCC2A2A2A)
    val pillShape = RoundedCornerShape(14.dp)

    // ── Left pill: circle icon + count ───────────────────────────────────
    Surface(
        modifier = Modifier.clickable(onClick = onSelectAll),
        shape    = pillShape,
        color    = pillColor
    ) {
        Row(
            modifier              = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .then(
                        if (allSelected) Modifier.background(Color.White, CircleShape)
                        else Modifier.border(2.dp, Color.White, CircleShape)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (allSelected) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Deselect all",
                        tint               = Color.Black,
                        modifier           = Modifier.size(16.dp)
                    )
                }
            }
            Text(
                text       = "$selectedCount",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
        }
    }

    Spacer(Modifier.weight(1f))

    // ── Right pill: Cancel ────────────────────────────────────────────────
    Surface(
        modifier = Modifier.clickable(onClick = onCancel),
        shape    = pillShape,
        color    = pillColor
    ) {
        Text(
            text       = "Cancel",
            fontSize   = 16.sp,
            fontWeight = FontWeight.Bold,
            color      = Color.White,
            modifier   = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PillButton
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Small pill-shaped clickable button used in selection headers and bottom bars.
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
        shape    = RoundedCornerShape(50),
        color    = containerColor
    ) {
        Text(
            text       = text,
            fontSize   = 14.sp,
            fontWeight = FontWeight.Medium,
            color      = if (enabled) Color.White else Color.White.copy(alpha = 0.4f),
            modifier   = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
