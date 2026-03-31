package com.imagelibrary.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.imagelibrary.data.model.ViewType
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.example.common.ui.components.GroupNameDialog
import com.imagelibrary.ui.components.ImageThumbnail
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Full-screen picker for moving selected folders/groups into a group.
 *
 * - Folders are displayed grayed out (disabled, non-clickable).
 * - Groups are enabled and clickable to navigate inside.
 * - Bottom bar shows a preview thumbnail, item count, Cancel, and "Move here".
 * - The items being moved are excluded from the display.
 */
@Composable
fun MoveToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    movingFolderIds: Set<Int>,
    movingGroupIds: Set<Long>,
    viewType: ViewType,
    onMoveHere: (targetGroupId: Long?) -> Unit,
    onCreateGroupAndMove: (name: String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current

    // Navigation stack: list of (groupId, groupName)
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId = browseStack.lastOrNull()?.first

    // Create group dialog state
    var showCreateGroupDialog by remember { mutableStateOf(false) }

    val totalMovingItems = movingFolderIds.size + movingGroupIds.size

    // Exclude the items being moved from the display
    val availableGroups = groups.filter { it.groupId !in movingGroupIds }

    // Build display items based on browse level
    val displayItems = if (currentBrowseGroupId != null) {
        // Inside a group — show its member folders + sub-groups
        val browsedGroup = availableGroups.find { it.groupId == currentBrowseGroupId }
        val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
        val memberFolders = memberBucketIds
            .filter { it !in movingFolderIds }
            .mapNotNull { bid -> folders.find { it.bucketId == bid } }
        val subGroups = availableGroups.filter { it.parentGroupId == currentBrowseGroupId }
        buildList<FolderListItem> {
            subGroups.forEach { add(MixedItem.Group(it)) }
            memberFolders.forEach { add(MixedItem.Folder(it)) }
        }
    } else {
        // Root level — show root groups + ungrouped folders
        val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
        val ungroupedFolders = folders
            .filter { it.bucketId !in groupedBucketIds && it.bucketId !in movingFolderIds }
        val rootGroups = availableGroups.filter { it.parentGroupId == null }
        buildList<FolderListItem> {
            rootGroups.forEach { add(MixedItem.Group(it)) }
            ungroupedFolders.forEach { add(MixedItem.Folder(it)) }
        }
    }

    // Find a preview URI from the first moving folder
    val previewUri = movingFolderIds.firstOrNull()?.let { bid ->
        folders.find { it.bucketId == bid }?.latestItemUri
    } ?: movingGroupIds.firstOrNull()?.let { gid ->
        groups.find { it.groupId == gid }?.previewUris?.firstOrNull()
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header: "Select a group" + "Create" ──
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.actionBarBg
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .heightIn(min = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentBrowseGroupId != null) browseStack.last().second else "Select a group",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Create",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { showCreateGroupDialog = true }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

            // ── Grid ──
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            val columnCount = if (isLargeGrid) 2 else 3
            val spacing = if (isLargeGrid) 24.dp else 16.dp

            if (displayItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (currentBrowseGroupId != null) "This group is empty" else "No items",
                        fontSize = 16.sp,
                        color = colors.listSecondText
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnCount),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(spacing),
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    items(displayItems, key = { it.uniqueKey }) { item ->
                        when (item) {
                            is MixedItem.Folder -> {
                                // Folders are grayed out and non-clickable
                                Box(modifier = Modifier.alpha(0.35f)) {
                                    FolderGridItem(
                                        folder = item.folder,
                                        isSelected = false,
                                        isSelectionMode = false,
                                        viewType = viewType,
                                        onClick = { /* disabled */ },
                                        onLongClick = { /* disabled */ },
                                        modifier = Modifier.animateItem(
                                            placementSpec = spring(
                                                dampingRatio = Spring.DampingRatioNoBouncy,
                                                stiffness = 4000f
                                            )
                                        )
                                    )
                                }
                            }
                            is MixedItem.Group -> {
                                // Groups are enabled — click navigates inside
                                GroupGridItem(
                                    group = item.group,
                                    isSelected = false,
                                    isSelectionMode = false,
                                    viewType = viewType,
                                    onClick = {
                                        browseStack = browseStack + Pair(item.group.groupId, item.group.name)
                                    },
                                    onLongClick = {
                                        browseStack = browseStack + Pair(item.group.groupId, item.group.name)
                                    },
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
            }
        }

        // ── Floating bottom bar: [preview + count] | Cancel | Move here ──
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            color = Color(0xCC2A2A2A),
            shadowElevation = 24.dp,
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Preview thumbnail
                if (previewUri != null) {
                    ImageThumbnail(
                        contentUri = previewUri,
                        contentDescription = "Preview",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                // Item count
                Text(
                    text = "$totalMovingItems item${if (totalMovingItems != 1) "s" else ""}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f))

                // Cancel
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onCancel() }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Divider
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(Color(0x60FFFFFF))
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Move here
                Text(
                    text = "Move here",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onMoveHere(currentBrowseGroupId) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }

    // ── Create group dialog ──
    if (showCreateGroupDialog) {
        val existingNames = groups.map { it.name }
        val lowerNames = existingNames.map { it.lowercase() }
        var counter = 1
        while (lowerNames.contains("group $counter".lowercase())) { counter++ }
        val defaultName = "Group $counter"

        GroupNameDialog(
            title = "Create group",
            initialName = defaultName,
            existingNames = existingNames,
            onConfirm = { name ->
                showCreateGroupDialog = false
                onCreateGroupAndMove(name)
            },
            onDismiss = { showCreateGroupDialog = false }
        )
    }

    // Handle back press inside the picker
    androidx.activity.compose.BackHandler(enabled = true) {
        if (showCreateGroupDialog) {
            showCreateGroupDialog = false
        } else if (browseStack.isNotEmpty()) {
            browseStack = browseStack.dropLast(1)
        } else {
            onCancel()
        }
    }
}
