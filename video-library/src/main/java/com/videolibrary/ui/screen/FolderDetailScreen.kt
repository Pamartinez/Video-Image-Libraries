package com.videolibrary.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.CircularBackButton
import com.videolibrary.data.model.VideoItem
import com.videolibrary.data.model.ViewType
import com.example.common.ui.components.BottomActionBar
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.ScreenTopBar
import com.videolibrary.ui.components.*
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Folder detail screen matching Blazor header when _currentFolder != null:
 * - Back arrow in circular dark button
 * - Folder name title
 * - Pill-shaped action buttons (view toggle, search, menu)
 */
@Composable
fun FolderDetailScreen(
    folderName: String,
    videos: List<VideoItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    onBack: () -> Unit,
    onVideoClick: (VideoItem) -> Unit,
    onVideoLongClick: (VideoItem) -> Unit,
    onCycleViewType: () -> Unit = {},
    modifier: Modifier = Modifier,
    onCopy: () -> Unit = {},
    onMove: () -> Unit = {},
    onDelete: () -> Unit = {},
    onShare: () -> Unit = {},
    onDetails: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    onEdit: () -> Unit = {},
    onSelectAll: () -> Unit = {},
    onSortBy: () -> Unit = {},
    onViewAs: () -> Unit = {},
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    scrollToTopTrigger: Int = 0,
) {
    val colors = LocalVideoColors.current
    var showMoreMenu by remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()

    // Scroll to top whenever the sort option changes
    LaunchedEffect(scrollToTopTrigger) {
        if (scrollToTopTrigger > 0) {
            lazyListState.scrollToItem(0)
            lazyGridState.scrollToItem(0)
        }
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header ──
            ScreenTopBar {
                if (isSelectionMode) {
                    SelectionModeHeader(
                        selectedCount = selectedIds.size,
                        totalCount    = videos.size,
                        onSelectAll   = onSelectAll,
                        onCancel      = onBack
                    )
                } else {
                    CircularBackButton(onClick = onBack)

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text     = folderName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color    = colors.listFirstText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    ActionsPill {
                        ViewTypeToggleButton(viewType, onCycleViewType)

                        AppMoreMenuButton(
                                expanded  = showMoreMenu,
                                onExpand  = { showMoreMenu = true },
                                onDismiss = { showMoreMenu = false },
                                onSortBy   = onSortBy,
                                onViewAs   = onViewAs,
                                onSettings = onSettings,
                                onAbout    = onAbout
                            ) { dismiss ->
                            AppMenuItem("Select", onDismiss = dismiss, onClick = onEdit, textColor = colors.listFirstText)
                            }
                    }
                }
            }

            // ── Video content ──
            VideosTab(
                videos = videos,
                viewType = viewType,
                isSelectionMode = isSelectionMode,
                selectedIds = selectedIds,
                isLoading = false,
                onVideoClick = onVideoClick,
                onVideoLongClick = onVideoLongClick,
                lazyListState = lazyListState,
                lazyGridState = lazyGridState
            )
        }

        // Floating action bar
        BottomActionBar(
            visible = isSelectionMode,
            onCopy = onCopy,
            onMove = onMove,
            onDelete = onDelete,
            onDetails = onDetails,
            showAllActions = true,
            showDetails = selectedIds.size == 1,
            showShare = selectedIds.isNotEmpty(),
            onShare = onShare,
            onOpenLocation = onOpenLocation,
            showOpenLocation = selectedIds.size == 1,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
