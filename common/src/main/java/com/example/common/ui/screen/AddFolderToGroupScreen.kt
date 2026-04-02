package com.example.common.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.common.data.model.ViewType
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Shared full-screen picker for adding folders (and optionally sub-groups) to an existing group.
 *
 * Behaviour:
 * - Folders     → show checkbox; tap to toggle selection.
 * - Groups      → no checkbox; tap navigates inside (browse stack).
 * - Back button → pops the browse stack, then calls [onCancel] at root level.
 * - Save        → enabled once at least one folder is selected; calls [onSave].
 *
 * Library-specific concerns are injected via composable lambda slots:
 * - [folderGridItem] — renders a [FolderItem] cell (image vs video thumbnail).
 * - [groupGridItem]  — renders a [GroupItem] cell (image vs video preview mosaic).
 *
 * @param folders        Full flat list of all folders.
 * @param groups         Full list of all groups.
 * @param currentGroupId The group being edited — excluded from the picker.
 * @param viewType       Grid density (GRID_SMALL = 3 col, GRID_LARGE = 2 col).
 * @param headerTitle    Lambda that builds the top-bar title from the current
 *                       browse-group name (null = root) and selected count.
 * @param saveButtonLabel Label text for the save/done action button.
 * @param onSave         Called with the chosen folder bucket-IDs and sub-group IDs.
 * @param onCancel       Called when the user navigates back past the root level.
 * @param folderGridItem Composable slot — (folder, isSelected, viewType, onClick, onLongClick, modifier).
 * @param groupGridItem  Composable slot — (group, viewType, onClick, onLongClick, modifier).
 */
@Composable
fun AddFolderToGroupScreen(
    folders: List<FolderItem>,
    groups: List<GroupItem>,
    currentGroupId: Long,
    modifier: Modifier = Modifier,
    viewType: ViewType = ViewType.GRID_LARGE,
    headerTitle: (browseGroupName: String?, selectedCount: Int) -> String = { name, count ->
        if (name != null) "$name ($count selected)" else "Add to group"
    },
    saveButtonLabel: String = "Add",
    onSave: (selectedFolderIds: Set<Int>, selectedGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit,
    folderGridItem: @Composable (
        folder: FolderItem,
        isSelected: Boolean,
        viewType: ViewType,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        modifier: Modifier
    ) -> Unit,
    groupGridItem: @Composable (
        group: GroupItem,
        viewType: ViewType,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        modifier: Modifier
    ) -> Unit
) {
    val colors = LocalLibraryColors.current

    var selectedFolderIds by remember { mutableStateOf(emptySet<Int>()) }

    // Browse stack: list of (groupId, groupName) — empty = root view
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId   = browseStack.lastOrNull()?.first
    val currentBrowseGroupName = browseStack.lastOrNull()?.second

    // Exclude the group being edited from the picker
    val filteredGroups = groups.filter { it.groupId != currentGroupId }

    // Build display items for the current browse level
    val displayItems: List<MixedItem> = if (currentBrowseGroupId != null) {
        // Inside a browsed group: show its member folders + sub-groups
        val browsedGroup     = filteredGroups.find { it.groupId == currentBrowseGroupId }
        val memberFolders    = browsedGroup?.memberBucketIds
            ?.mapNotNull { bid -> folders.find { it.bucketId == bid } }
            ?: emptyList()
        val subGroups        = filteredGroups.filter { it.parentGroupId == currentBrowseGroupId }
        buildList {
            subGroups.forEach    { add(MixedItem.Group(it))  }
            memberFolders.forEach { add(MixedItem.Folder(it)) }
        }
    } else {
        // Root level: root groups + folders not belonging to any group
        val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
        val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
        val rootGroups       = filteredGroups.filter { it.parentGroupId == null }
        buildList {
            rootGroups.forEach        { add(MixedItem.Group(it))  }
            ungroupedFolders.forEach  { add(MixedItem.Folder(it)) }
        }
    }

    val totalSelected = selectedFolderIds.size
    val title         = headerTitle(currentBrowseGroupName, totalSelected)
    val canSave       = totalSelected > 0

    val columnCount = if (viewType == ViewType.GRID_SMALL) 3 else 2
    val spacing     = if (viewType == ViewType.GRID_SMALL) 16.dp else 24.dp

    // Handle system back
    BackHandler {
        if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1)
        else onCancel()
    }

    Box(modifier = modifier.fillMaxSize().background(colors.screenBackground)) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ───────────────────────────────────────────────────
            ScreenTopBar {
                CircularBackButton(onClick = {
                    if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1)
                    else onCancel()
                })

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text       = title,
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = colors.listFirstText,
                    modifier   = Modifier.weight(1f)
                )

                // Save / Done pill button
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable(enabled = canSave) {
                            onSave(selectedFolderIds, emptySet())
                        },
                    color = if (canSave) colors.primary else Color(0xFF3A3A3A),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text       = saveButtonLabel,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color      = Color.White,
                        modifier   = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }

            // ── Content grid ─────────────────────────────────────────────
            if (displayItems.isEmpty()) {
                Box(
                    modifier          = Modifier.fillMaxSize(),
                    contentAlignment  = Alignment.Center
                ) {
                    Text(
                        text     = if (currentBrowseGroupId != null)
                            "No items in this group"
                        else
                            "No items available",
                        fontSize = 16.sp,
                        color    = colors.listSecondText
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns             = GridCells.Fixed(columnCount),
                    modifier            = Modifier.fillMaxSize(),
                    contentPadding      = PaddingValues(spacing),
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalArrangement   = Arrangement.spacedBy(spacing)
                ) {
                    items(displayItems, key = { it.uniqueKey }) { item ->
                        when (item) {
                            is MixedItem.Folder -> {
                                val folder     = item.folder
                                val isSelected = selectedFolderIds.contains(folder.bucketId)
                                val toggle: () -> Unit = {
                                    selectedFolderIds =
                                        if (isSelected) selectedFolderIds - folder.bucketId
                                        else            selectedFolderIds + folder.bucketId
                                }
                                folderGridItem(
                                    folder,
                                    isSelected,
                                    viewType,
                                    toggle,
                                    toggle,
                                    Modifier.animateItem(
                                        placementSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness    = 4000f
                                        )
                                    )
                                )
                            }
                            is MixedItem.Group -> {
                                val group = item.group
                                val navigateIn: () -> Unit = {
                                    browseStack = browseStack + (group.groupId to group.name)
                                }
                                groupGridItem(
                                    group,
                                    viewType,
                                    navigateIn,
                                    navigateIn,
                                    Modifier.animateItem(
                                        placementSpec = spring(
                                            dampingRatio = Spring.DampingRatioNoBouncy,
                                            stiffness    = 4000f
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






