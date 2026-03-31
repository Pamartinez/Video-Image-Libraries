package com.videolibrary.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.videolibrary.data.model.ViewType
import com.example.common.ui.components.ScreenTopBar
import com.videolibrary.ui.components.*
import com.videolibrary.ui.theme.LocalVideoColors

/**
 * Full-screen folder/group picker for adding items to an existing group.
 *
 * • Folders    → show checkbox; tap to toggle selection
 * • Groups     → no checkbox; tap to navigate inside (browse stack)
 * • Back button pops the browse stack, then calls [onCancel] at root level
 * • Save is enabled once at least one folder or group is selected
 */
@Composable
fun AddFolderToGroupScreen(
    allFolders: List<FolderItem>,
    allGroups: List<GroupItem>,
    currentGroupId: Long,
    viewType: ViewType = ViewType.GRID_LARGE,
    onSave: (folderBucketIds: Set<Int>, subGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit
) {
    val colors = LocalVideoColors.current

    // Selection state
    var selectedFolderIds by remember { mutableStateOf(emptySet<Int>()) }
    var selectedGroupIds   by remember { mutableStateOf(emptySet<Long>()) }

    // Browse stack: list of (groupId, groupName) — empty = root view
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId   = browseStack.lastOrNull()?.first
    val currentBrowseGroupName = browseStack.lastOrNull()?.second

    // Already-member bucket IDs for currentGroupId are excluded
    val memberIds = allGroups.find { it.groupId == currentGroupId }?.memberBucketIds?.toSet()
        ?: emptySet()

    // Compute what to show at the current browse level
    val visibleFolders: List<FolderItem>
    val visibleGroups: List<GroupItem>
    if (currentBrowseGroupId == null) {
        // Root: ungrouped folders + root groups (excluding the current group being edited)
        val groupedIds = allGroups.flatMap { it.memberBucketIds }.toSet()
        visibleFolders = allFolders.filter { it.bucketId !in groupedIds }
        visibleGroups  = allGroups.filter {
            it.parentGroupId == null && it.groupId != currentGroupId
        }
    } else {
        val browseGroup = allGroups.find { it.groupId == currentBrowseGroupId }
        visibleFolders  = browseGroup?.memberBucketIds?.mapNotNull { bid ->
            allFolders.find { it.bucketId == bid }
        } ?: emptyList()
        visibleGroups   = allGroups.filter { it.parentGroupId == currentBrowseGroupId }
    }

    val mixedItems: List<MixedFolderItem> =
        visibleGroups.map  { MixedItem.Group(it) } +
        visibleFolders.map { MixedItem.Folder(it) }

    val columnCount = if (viewType == ViewType.GRID_SMALL) 3 else 2
    val spacing     = if (viewType == ViewType.GRID_SMALL) 4.dp else 8.dp

    val canSave = selectedFolderIds.isNotEmpty() || selectedGroupIds.isNotEmpty()

    // Handle system back
    androidx.activity.compose.BackHandler {
        if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1)
        else onCancel()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.screenBackground)
    ) {
        // ── Top bar ───────────────────────────────────────────────────────
        ScreenTopBar {
            IconButton(
                onClick = {
                    if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1)
                    else onCancel()
                },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xD9323232))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back",
                    tint = Color.White, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = currentBrowseGroupName ?: "Add to group",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.listFirstText,
                modifier = Modifier.weight(1f)
            )
            // Save button
            TextButton(
                onClick = { onSave(selectedFolderIds, selectedGroupIds) },
                enabled = canSave
            ) {
                Text("Save",
                    color = if (canSave) colors.primary else colors.listSecondText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp)
            }
        }

        // ── Content grid ─────────────────────────────────────────────────
        if (mixedItems.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No items to add", fontSize = 16.sp, color = colors.listSecondText)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnCount),
                contentPadding = PaddingValues(spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement   = Arrangement.spacedBy(spacing),
                modifier = Modifier.fillMaxSize()
            ) {
                items(mixedItems, key = { it.uniqueKey }) { item ->
                    when (item) {
                        is MixedItem.Folder -> {
                            val folder = item.folder
                            FolderGridItem(
                                folder = folder,
                                isSelected = selectedFolderIds.contains(folder.bucketId),
                                isSelectionMode = true,
                                viewType = viewType,
                                onClick = {
                                    selectedFolderIds =
                                        if (folder.bucketId in selectedFolderIds)
                                            selectedFolderIds - folder.bucketId
                                        else
                                            selectedFolderIds + folder.bucketId
                                }
                            )
                        }
                        is MixedItem.Group -> {
                            val group = item.group
                            GroupGridItem(
                                group = group,
                                isSelected = false,
                                isSelectionMode = false,
                                viewType = viewType,
                                onClick = {
                                    // Navigate into group
                                    browseStack = browseStack + (group.groupId to group.name)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

