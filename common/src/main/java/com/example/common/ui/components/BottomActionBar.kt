package com.example.common.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DriveFileMove
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Floating pill-shaped context menu bar matching Blazor app.css:
 * .context-menu-bar {
 *   background-color: rgba(60, 60, 60, 0.45);
 *   backdrop-filter: blur(20px);
 *   border-radius: 50px;
 *   padding: 10px 24px;
 *   bottom: 24px;
 * }
 */
@Composable
fun BottomActionBar(
    visible: Boolean,
    onCopy: () -> Unit,
    onMove: () -> Unit,
    onDelete: () -> Unit,
    onDetails: () -> Unit,
    modifier: Modifier = Modifier,
    showAllActions: Boolean = true,
    showDetails: Boolean = true,
    onOpenLocation: () -> Unit = {},
    showOpenLocation: Boolean = false,
    onGroup: () -> Unit = {},
    showGroup: Boolean = false,
    showMove: Boolean = false,
    showShare: Boolean = false,
    onShare: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit  = slideOutVertically(targetOffsetY  = { it }) + fadeOut(),
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = 12.dp),
            shape          = RoundedCornerShape(50.dp),
            color          = Color(0xCC2A2A2A),
            shadowElevation = 24.dp,
            tonalElevation  = 0.dp
        ) {
            Row(
                // Samsung Gallery dimens:
                //   bottom_bar_layout_container_padding_horizontal = 10 dp
                //   bottom_menu_list_circle_item_horizontal_gap    =  4 dp
                // Items are self-contained at 58 dp height → no vertical padding needed.
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment     = Alignment.CenterVertically
            ) {
                if (showGroup) {
                    BottomBarItem(icon = Icons.Default.CreateNewFolder, label = "Group",   onClick = onGroup)
                }
                if (showAllActions) {
                    BottomBarItem(icon = Icons.Default.ContentCopy,             label = "Copy",    onClick = onCopy)
                }
                if (showAllActions || showMove) {
                    BottomBarItem(icon = Icons.AutoMirrored.Filled.DriveFileMove, label = "Move",  onClick = onMove)
                }
                if (showShare) {
                    BottomBarItem(icon = Icons.Default.Share,                   label = "Share",   onClick = onShare)
                }
                BottomBarItem(icon = Icons.Outlined.Delete, label = "Delete", onClick = onDelete)

                // "More" button with submenu for Details & Open location
                val hasMoreItems = (showAllActions && showDetails) || showOpenLocation
                if (hasMoreItems) {
                    var showMoreMenu by remember { mutableStateOf(false) }
                    Box {
                        BottomBarItem(
                            icon    = Icons.Default.MoreVert,
                            label   = "More",
                            onClick = { showMoreMenu = true }
                        )
                        DropdownMenu(
                            expanded         = showMoreMenu,
                            onDismissRequest = { showMoreMenu = false },
                            offset           = DpOffset(0.dp, (-20).dp),
                            shape            = RoundedCornerShape(16.dp),
                            containerColor   = Color(0xE6303030)
                        ) {
                            if (showAllActions && showDetails) {
                                AppMenuItem(
                                    text      = "Details",
                                    onDismiss = { showMoreMenu = false },
                                    onClick   = onDetails,
                                    textColor = Color.White,
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.Info,
                                            contentDescription = null,
                                            tint     = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                )
                            }
                            if ((showAllActions && showDetails) && showOpenLocation) {
                                AppMenuDivider(color = Color(0x40FFFFFF), thickness = 0.5.dp)
                            }
                            if (showOpenLocation) {
                                AppMenuItem(
                                    text      = "Open location",
                                    onDismiss = { showMoreMenu = false },
                                    onClick   = onOpenLocation,
                                    textColor = Color.White,
                                    leadingIcon = {
                                        Icon(
                                            Icons.Default.FolderOpen,
                                            contentDescription = null,
                                            tint     = Color.White,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Individual item in the floating context menu bar.
 * Fixed size (72 × 58 dp) so every bar has the same height regardless of
 * how many buttons it contains.
 *
 * Samsung Gallery reference dimens:
 *   bottom_bar_floating_height                      = 58 dp  (total item height)
 *   bottom_menu_list_dummy_circle_item_width        = 72 dp  (total item width)
 *   bottom_menu_list_circle_item_icon_size          = 24 dp
 *   bottom_menu_list_circle_item_text_margin_top    =  4 dp  (gap between icon and label)
 *   bottom_menu_list_view_holder_text_size          = 12 sp  (label text size)
 */
@Composable
fun BottomBarItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(width = 72.dp, height = 58.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint     = Color.White,
            modifier = Modifier.size(24.dp)  // bottom_menu_list_circle_item_icon_size
        )
        Spacer(modifier = Modifier.height(4.dp))  // bottom_menu_list_circle_item_text_margin_top
        Text(
            text       = label,
            fontSize   = 12.sp,              // bottom_menu_list_view_holder_text_size
            fontWeight = FontWeight.Medium,
            maxLines   = 1,
            color      = Color.White
        )
    }
}

