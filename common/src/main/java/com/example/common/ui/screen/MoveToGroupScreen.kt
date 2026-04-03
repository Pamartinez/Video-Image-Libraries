package com.example.common.ui.screen

import android.net.Uri
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.components.GroupNameDialog
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared full-screen picker for moving selected folders/groups into a group.
 *
 * - Folders are displayed grayed out (disabled, non-clickable).
 * - Groups are enabled and clickable to navigate inside.
 * - Bottom bar shows a preview thumbnail, item count, Cancel, and "Move here".
 * - Items being moved are excluded from the display.
 *
 * All library-specific rendering is injected:
 * @param thumbnailContent  Renders a thumbnail from a [Uri] (ImageThumbnail / VideoThumbnail).
 * @param folderItemContent Renders a [FolderItem] cell (disabled/greyed out by the caller wrapping it in alpha).
 * @param groupItemContent  Renders a [GroupItem] cell; receives [onClick]/[onLongClick] to navigate inside.
 */
@Composable
fun MoveToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    movingFolderIds: Set<Int>,
    movingGroupIds: Set<Long>,
    columnCount: Int = 3,
    gridSpacing: Float = 16f,
    onMoveHere: (targetGroupId: Long?) -> Unit,
    onCreateGroupAndMove: (name: String) -> Unit,
    onCancel: () -> Unit,
    onBackPress: () -> Unit = onCancel,
    thumbnailContent: @Composable (uri: Uri, modifier: Modifier) -> Unit,
    folderItemContent: @Composable (folder: FolderItem, modifier: Modifier) -> Unit,
    groupItemContent: @Composable (
        group: GroupItem,
        modifier: Modifier,
        onClick: () -> Unit,
        onLongClick: () -> Unit
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalLibraryColors.current
    val spacing = gridSpacing.dp

    // Navigation stack: list of (groupId, groupName)
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId = browseStack.lastOrNull()?.first

    // Create group dialog state
    var showCreateGroupDialog by remember { mutableStateOf(false) }

    val totalMovingItems = movingFolderIds.size + movingGroupIds.size
    val availableGroups  = groups.filter { it.groupId !in movingGroupIds }

    // Build display items based on browse level
    val displayItems: List<MixedItem> = if (currentBrowseGroupId != null) {
        val browsedGroup    = availableGroups.find { it.groupId == currentBrowseGroupId }
        val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
        val memberFolders   = memberBucketIds
            .filter { it !in movingFolderIds }
            .mapNotNull { bid -> folders.find { it.bucketId == bid } }
        val subGroups       = availableGroups.filter { it.parentGroupId == currentBrowseGroupId }
        buildList {
            subGroups.forEach     { add(MixedItem.Group(it)) }
            memberFolders.forEach { add(MixedItem.Folder(it)) }
        }
    } else {
        val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
        val ungroupedFolders = folders
            .filter { it.bucketId !in groupedBucketIds && it.bucketId !in movingFolderIds }
        val rootGroups       = availableGroups.filter { it.parentGroupId == null }
        buildList {
            rootGroups.forEach       { add(MixedItem.Group(it)) }
            ungroupedFolders.forEach { add(MixedItem.Folder(it)) }
        }
    }

    // Preview URI from the first moving folder / group
    val previewUri: Uri? = movingFolderIds.firstOrNull()?.let { bid ->
        folders.find { it.bucketId == bid }?.latestItemUri
    } ?: movingGroupIds.firstOrNull()?.let { gid ->
        groups.find { it.groupId == gid }?.previewUris?.firstOrNull()
    }

    // Handle system back: close dialog → pop browse stack → cancel
    androidx.activity.compose.BackHandler(enabled = true) {
        when {
            showCreateGroupDialog -> showCreateGroupDialog = false
            browseStack.isNotEmpty() -> browseStack = browseStack.dropLast(1)
            else -> onBackPress()
        }
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Header ──────────────────────────────────────────────
            Surface(modifier = Modifier.fillMaxWidth(), color = colors.actionBarBg) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .heightIn(min = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text       = if (currentBrowseGroupId != null) browseStack.last().second
                                     else "Select a group",
                        fontSize   = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color      = Color.White,
                        modifier   = Modifier.weight(1f)
                    )
                    Text(
                        text       = "Create",
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color      = Color.White,
                        modifier   = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { showCreateGroupDialog = true }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

            // ── Grid ─────────────────────────────────────────────────
            if (displayItems.isEmpty()) {
                Box(
                    modifier         = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text     = if (currentBrowseGroupId != null) "This group is empty" else "No items",
                        fontSize = 16.sp,
                        color    = colors.listSecondText
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns               = GridCells.Fixed(columnCount),
                    modifier              = Modifier.fillMaxWidth().weight(1f),
                    contentPadding        = PaddingValues(spacing),
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalArrangement   = Arrangement.spacedBy(spacing)
                ) {
                    items(displayItems, key = { it.uniqueKey }) { item ->
                        val animMod = Modifier.animateItem(
                            placementSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness    = 4000f
                            )
                        )
                        when (item) {
                            is MixedItem.Folder -> {
                                // Folders grayed out — non-clickable
                                Box(modifier = animMod.alpha(0.35f)) {
                                    folderItemContent(item.folder, Modifier)
                                }
                            }
                            is MixedItem.Group -> {
                                groupItemContent(
                                    item.group,
                                    animMod,
                                    { browseStack = browseStack + Pair(item.group.groupId, item.group.name) },
                                    { browseStack = browseStack + Pair(item.group.groupId, item.group.name) }
                                )
                            }
                        }
                    }
                }
            }
        }

        // ── Floating bottom bar ──────────────────────────────────────
        Surface(
            modifier        = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            shape           = RoundedCornerShape(50.dp),
            color           = Color(0xCC2A2A2A),
            shadowElevation = 24.dp,
            tonalElevation  = 0.dp
        ) {
            Row(
                modifier          = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (previewUri != null) {
                    thumbnailContent(previewUri, Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text       = "$totalMovingItems item${if (totalMovingItems != 1) "s" else ""}",
                    fontSize   = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color.White,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text       = "Cancel",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = Color.White,
                    modifier   = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onCancel() }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0x60FFFFFF)))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text       = "Move here",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = Color.White,
                    modifier   = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onMoveHere(currentBrowseGroupId) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }

    // ── Create group dialog ──────────────────────────────────────────
    if (showCreateGroupDialog) {
        val existingNames = groups.map { it.name }
        val lowerNames    = existingNames.map { it.lowercase() }
        var counter       = 1
        while (lowerNames.contains("group $counter".lowercase())) { counter++ }

        GroupNameDialog(
            title         = "Create group",
            initialName   = "Group $counter",
            existingNames = existingNames,
            onConfirm     = { name ->
                showCreateGroupDialog = false
                onCreateGroupAndMove(name)
            },
            onDismiss     = { showCreateGroupDialog = false }
        )
    }
}

