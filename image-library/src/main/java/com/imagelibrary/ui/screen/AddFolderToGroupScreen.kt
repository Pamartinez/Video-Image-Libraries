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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.imagelibrary.data.model.ViewType
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.imagelibrary.ui.components.FolderGridItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.theme.LocalImageColors

/**
 * Full-screen picker to select folders (albums) to add to a group.
 * - Only folders can be selected (checkboxes on folders only).
 * - Groups have NO checkbox; clicking a group navigates inside it.
 * - Inside a group: its member folders are shown with checkboxes,
 *   sub-groups are shown without checkboxes and can be navigated into.
 */
@Composable
fun AddFolderToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    currentGroupId: Long,
    viewType: ViewType,
    onSave: (selectedFolderIds: Set<Int>, selectedGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current
    var selectedFolderIds by remember { mutableStateOf(emptySet<Int>()) }

    // Navigation stack: list of (groupId, groupName)
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId = browseStack.lastOrNull()?.first

    // Filter out the current group we're adding TO
    val filteredGroups = groups.filter { it.groupId != currentGroupId }

    // Build display items based on browse level
    val displayItems = if (currentBrowseGroupId != null) {
        // Inside a browsed group — show its member folders + sub-groups
        val browsedGroup = filteredGroups.find { it.groupId == currentBrowseGroupId }
        val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
        val memberFolders = memberBucketIds.mapNotNull { bid -> folders.find { it.bucketId == bid } }
        val subGroups = filteredGroups.filter { it.parentGroupId == currentBrowseGroupId }
        buildList<FolderListItem> {
                subGroups.forEach { add(MixedItem.Group(it)) }
                memberFolders.forEach { add(MixedItem.Folder(it)) }
            }
    } else {
        // Root level — show root groups + ungrouped folders
        // Use ALL groups (not just filteredGroups) so folders already in any group
        // are hidden here and only visible when the user navigates into that group.
        val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
        val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
        val rootGroups = filteredGroups.filter { it.parentGroupId == null }
        buildList<FolderListItem> {
            rootGroups.forEach { add(MixedItem.Group(it)) }
            ungroupedFolders.forEach { add(MixedItem.Folder(it)) }
        }
    }

    val totalSelected = selectedFolderIds.size
    val headerTitle = if (currentBrowseGroupId != null) {
        val name = browseStack.last().second
        "$name ($totalSelected selected)"
    } else {
        "Add albums ($totalSelected)"
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ── Header ──
            ScreenTopBar {
                CircularBackButton(onClick = {
                    if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1)
                    else onCancel()
                })

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = headerTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.listFirstText,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable(enabled = totalSelected > 0) {
                            onSave(selectedFolderIds, emptySet())
                        },
                    color = if (totalSelected > 0) colors.primary else Color(0xFF3A3A3A),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Done",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }

            // ── Grid ──
            val isLargeGrid = viewType == ViewType.GRID_LARGE
            val columnCount = if (isLargeGrid) 2 else 3
            val spacing = if (isLargeGrid) 24.dp else 16.dp

            if (displayItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (currentBrowseGroupId != null) "No albums in this group" else "No albums available",
                        fontSize = 16.sp,
                        color = colors.listSecondText
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnCount),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(spacing),
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    items(displayItems, key = { it.uniqueKey }) { item ->
                        when (item) {
                            is MixedItem.Folder -> {
                                // Folders are selectable with checkboxes
                                FolderGridItem(
                                    folder = item.folder,
                                    isSelected = selectedFolderIds.contains(item.folder.bucketId),
                                    isSelectionMode = true,
                                    viewType = viewType,
                                    onClick = {
                                        selectedFolderIds = if (selectedFolderIds.contains(item.folder.bucketId)) {
                                            selectedFolderIds - item.folder.bucketId
                                        } else {
                                            selectedFolderIds + item.folder.bucketId
                                        }
                                    },
                                    onLongClick = {
                                        selectedFolderIds = if (selectedFolderIds.contains(item.folder.bucketId)) {
                                            selectedFolderIds - item.folder.bucketId
                                        } else {
                                            selectedFolderIds + item.folder.bucketId
                                        }
                                    },
                                    modifier = Modifier.animateItem(
                                        placementSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness = 4000f
                                        )
                                    )
                                )
                            }
                            is MixedItem.Group -> {
                                // Groups: NO checkbox, click navigates inside
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
    }
}
