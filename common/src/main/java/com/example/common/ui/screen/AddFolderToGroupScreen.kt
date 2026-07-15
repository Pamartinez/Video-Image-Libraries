package com.example.common.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.data.model.ViewType
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.FastScrollerForGrid
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
 * @param groupOrderedItems Pre-calculated ordered items for each group (groupId -> ordered list).
 *                       When present, these items are used directly instead of re-calculating from folders list.
 *                       This ensures the picker respects the group's sort order.
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
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
    headerTitle: (browseGroupName: String?, selectedCount: Int) -> String = { name, count ->
        when {
            name != null -> name  // Browsing inside a group - show group name
            count > 0 -> "$count selected"  // Items selected - show count
            else -> "Add albums"  // Default - show "Add albums"
        }
    },
    saveButtonLabel: String = "Add",
    onSave: (selectedFolderIds: Set<Int>, selectedGroupIds: Set<Long>) -> Unit,
    onCancel: () -> Unit,
    folderGridItem: @Composable (
        folder: FolderItem,
        isSelected: Boolean,
        isSelectable: Boolean,
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
    val lazyGridState = rememberLazyGridState()

    var selectedFolderIds by remember { mutableStateOf(emptySet<Int>()) }

    // Browse stack: list of (groupId, groupName) — empty = root view
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }
    val currentBrowseGroupId   = browseStack.lastOrNull()?.first
    val currentBrowseGroupName = browseStack.lastOrNull()?.second

    // Exclude the group being edited from the picker
    val filteredGroups = groups.filter { it.groupId != currentGroupId }

    // Build display items for the current browse level
    val displayItems: List<MixedItem> = if (currentBrowseGroupId != null) {
        // Check if we have pre-calculated items for this group
        val preCalculated = groupOrderedItems[currentBrowseGroupId]
        if (preCalculated != null) {
            // Use exact items from the group's sort order
            preCalculated.mapNotNull { item ->
                when (item) {
                    is FolderItem -> MixedItem.Folder(item)
                    is GroupItem -> MixedItem.Group(item)
                    else -> null
                }
            }
        } else {
            // Fallback: re-calculate from folders list (old behavior)
            val browsedGroup    = filteredGroups.find { it.groupId == currentBrowseGroupId }
            val memberBucketIds = browsedGroup?.memberBucketIds?.toSet() ?: emptySet()
            val memberFolders   = folders.filter { it.bucketId in memberBucketIds }
            val subGroups       = filteredGroups.filter { it.parentGroupId == currentBrowseGroupId }
            buildList {
                subGroups.forEach    { add(MixedItem.Group(it))  }
                memberFolders.forEach { add(MixedItem.Folder(it)) }
            }
        }
    } else {
        // Root level: check for pre-calculated root items (groupId = -1)
        val preCalculatedRoot = groupOrderedItems[-1L]
        if (preCalculatedRoot != null) {
            // Use exact items from root's sort order
            preCalculatedRoot.mapNotNull { item ->
                when (item) {
                    is FolderItem -> MixedItem.Folder(item)
                    is GroupItem -> MixedItem.Group(item)
                    else -> null
                }
            }
        } else {
            // Fallback: re-calculate root items (old behavior)
            val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
            val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
            val rootGroups       = filteredGroups.filter { it.parentGroupId == null }
            buildList {
                rootGroups.forEach        { add(MixedItem.Group(it))  }
                ungroupedFolders.forEach  { add(MixedItem.Folder(it)) }
            }
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

                // Top-bar save button removed - now using floating button at bottom
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
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns             = GridCells.Fixed(columnCount),
                        state               = lazyGridState,
                        modifier            = Modifier.fillMaxSize(),
                        contentPadding      = PaddingValues(spacing),
                        horizontalArrangement = Arrangement.spacedBy(spacing),
                        verticalArrangement   = Arrangement.spacedBy(spacing)
                    ) {
                        items(displayItems, key = { it.uniqueKey }) { item ->
                            when (item) {
                                is MixedItem.Folder -> {
                                    val folder     = item.folder
                                    // If browsing the current group being edited, folders are non-selectable (already in group)
                                    val isSelectable = currentBrowseGroupId != currentGroupId
                                    val isSelected = isSelectable && selectedFolderIds.contains(folder.bucketId)
                                    val toggle: () -> Unit = {
                                        if (isSelectable) {
                                            selectedFolderIds =
                                                if (isSelected) selectedFolderIds - folder.bucketId
                                                else            selectedFolderIds + folder.bucketId
                                        }
                                    }
                                    folderGridItem(
                                        folder,
                                        isSelected,
                                        isSelectable,
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
                    FastScrollerForGrid(
                        state = lazyGridState,
                        itemCount = displayItems.size,
                        sectionLabel = { index ->
                            when (val item = displayItems.getOrNull(index)) {
                                is MixedItem.Folder -> item.folder.name
                                is MixedItem.Group -> item.group.name
                                null -> ""
                            }
                        }
                    )
                }
            }
        }

        // ── Floating "Add" button (bottom-center) ────────────────────
        AnimatedVisibility(
            visible = canSave,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Surface(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp)
                    .clickable(onClick = { onSave(selectedFolderIds, emptySet()) }),
                shape = RoundedCornerShape(50.dp),
                color = Color(0xE6202020),
                shadowElevation = 24.dp,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = saveButtonLabel,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}





