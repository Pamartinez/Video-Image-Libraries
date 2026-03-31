package com.imagelibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imagelibrary.data.model.ImageItem
import com.imagelibrary.data.model.ViewType
import com.example.common.ui.components.ActionsPill
import com.example.common.ui.components.AppMenuItem
import com.example.common.ui.components.AppMoreMenuButton
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.components.BottomActionBar
import com.imagelibrary.ui.components.ImageGridItem
import com.imagelibrary.ui.components.SelectionHeader
import com.imagelibrary.ui.components.ViewTypeToggleButton
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Folder detail screen:
 * - Back arrow + folder name + action buttons
 * - Grid of images (GRID_LARGE or GRID_SMALL with animation)
 * - Click image → opens carousel
 * - Long press → selection mode
 */
@Composable
fun FolderDetailScreen(
    folderName: String,
    images: List<ImageItem>,
    viewType: ViewType,
    isSelectionMode: Boolean,
    selectedIds: Set<Long>,
    onBack: () -> Unit,
    onImageClick: (ImageItem, Int) -> Unit,
    onImageLongClick: (ImageItem) -> Unit,
    onCycleViewType: () -> Unit = {},
    modifier: Modifier = Modifier,
    onCopy: () -> Unit = {},
    onMove: () -> Unit = {},
    onDelete: () -> Unit = {},
    onDetails: () -> Unit = {},
    onShare: () -> Unit = {},
    onOpenLocation: () -> Unit = {},
    onEdit: () -> Unit = {},
    onSelectAll: () -> Unit = {},
    onSortBy: () -> Unit = {},
    onViewAs: () -> Unit = {},
    onSettings: () -> Unit = {},
    onAbout: () -> Unit = {},
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    val colors = LocalImageColors.current
    var showMoreMenu by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header ──
            ScreenTopBar {
                if (isSelectionMode) {
                    val allSelected = images.isNotEmpty() && selectedIds.size == images.size
                    SelectionHeader(
                        selectedCount = selectedIds.size,
                        allSelected = allSelected,
                        onSelectAll = onSelectAll,
                        onCancel = onBack
                    )
                } else {
                    CircularBackButton(onClick = onBack)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = folderName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.listFirstText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    ActionsPill {
                        ViewTypeToggleButton(viewType = viewType, onClick = onCycleViewType)
                        AppMoreMenuButton(
                            expanded = showMoreMenu,
                            onExpand = { showMoreMenu = true },
                            onDismiss = { showMoreMenu = false },
                            onSortBy = onSortBy,
                            onViewAs = onViewAs,
                            onSettings = onSettings,
                            onAbout = onAbout
                        ) { dismiss ->
                            AppMenuItem("Select", onDismiss = dismiss, onClick = onEdit, textColor = colors.listFirstText)
                        }
                    }
                }
            }

            // ── Image grid content ──
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            val columnCount = if (isLargeGrid) 2 else 3
            val spacing = 2.dp

            if (images.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No images", fontSize = 16.sp, color = colors.listSecondText)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnCount),
                    state = lazyGridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(0.dp),
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    items(images, key = { it.id }) { image ->
                        val index = images.indexOf(image)
                        ImageGridItem(
                            image = image,
                            isSelected = selectedIds.contains(image.id),
                            isSelectionMode = isSelectionMode,
                            isLargeGrid = isLargeGrid,
                            onClick = {
                                if (isSelectionMode) onImageLongClick(image)
                                else onImageClick(image, index)
                            },
                            onLongClick = { onImageLongClick(image) },
                            modifier = Modifier.animateItem(
                                placementSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = 4000f
                                )
                            )
                        )
                    }
                }
            }
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
