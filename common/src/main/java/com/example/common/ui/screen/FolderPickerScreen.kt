package com.example.common.ui.screen

import android.os.Environment
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.example.common.ui.components.GridItemOverlay
import com.example.common.ui.components.FastScrollerForGrid
import com.example.common.ui.theme.LocalLibraryColors
import java.io.File

/**
 * Shared folder-picker screen used by both image-library and video-library.
 *
 * Two composable slots allow each library to inject its own rendering logic
 * without duplicating the screen's layout or state management:
 *
 * - [thumbnailContent] — renders the preview thumbnail for a [FolderItem].
 *   Image-library passes a plain [AsyncImage]; video-library adds VideoFrameDecoder.
 *
 * - [groupItemContent] — renders a [GroupItem] cell in the grid.
 *   Each library passes its own GroupGridItem wrapper (which injects the correct
 *   thumbnail composable internally).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FolderPickerScreen(
    title: String,
    folders: List<FolderItem>,
    groups: List<GroupItem> = emptyList(),
    orderedMixedItems: List<Any> = emptyList(),
    /**
     * Per-group custom sort orders (keyed by groupId). Keys are "g_<groupId>" or
     * "f_<bucketId>", matching the format stored in AppPreferences.
     * When non-empty the picker respects the group's saved drag order instead of
     * falling back to alphabetical.
     */
    groupCustomOrders: Map<Long, List<String>> = emptyMap(),
    /**
     * Per-group sort options (keyed by groupId). Holds each group's sort preference
     * (e.g., FolderSortOption enum ID). When browsing into a group, the picker respects
     * this setting instead of hardcoding alphabetical sort.
     */
    groupSortOptions: Map<Long, Int> = emptyMap(),
    /**
     * Pre-calculated ordered items for specific groups (keyed by groupId).
     * When provided for a group, the picker uses these items directly instead of
     * re-calculating from folders/groups, ensuring the same sort order as GroupDetailScreen.
     */
    groupOrderedItems: Map<Long, List<Any>> = emptyMap(),
    onFolderSelected: (String) -> Unit,
    onBack: () -> Unit,
    onCreateFolderAndSelect: ((String) -> Unit)? = null,
    /** Renders the preview thumbnail inside a folder cell. */
    thumbnailContent: @Composable (folder: FolderItem, modifier: Modifier) -> Unit,
    /** Renders a group cell; the caller is responsible for invoking onClick / onLongClick. */
    groupItemContent: @Composable (
        group: GroupItem,
        onClick: () -> Unit,
        onLongClick: () -> Unit
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalLibraryColors.current
    var showCreateDialog by remember { mutableStateOf(false) }
    val lazyGridState = rememberLazyGridState()

    // ── Selection state ──
    var isSelectionMode by remember { mutableStateOf(false) }
    var selectedIds by remember { mutableStateOf(setOf<Int>()) }
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }

    // Reset scroll position when entering/exiting a group so each level opens at the top.
    LaunchedEffect(browseStack) { lazyGridState.scrollToItem(0) }

    val currentBrowseGroupId = browseStack.lastOrNull()?.first
    val displayItems: List<MixedItem> = remember(folders, groups, orderedMixedItems, currentBrowseGroupId, groupCustomOrders, groupSortOptions, groupOrderedItems) {
        if (currentBrowseGroupId != null) {
            // If we have pre-calculated ordered items for this group, use them directly
            val preCalculated = groupOrderedItems[currentBrowseGroupId]
            if (preCalculated != null) {
                preCalculated.mapNotNull { item ->
                    when (item) {
                        is FolderItem -> MixedItem.Folder(item)
                        is GroupItem -> MixedItem.Group(item)
                        else -> null
                    }
                }
            } else {
                // Fallback: calculate from memberBucketIds (old behavior)
                val browsedGroup = groups.find { it.groupId == currentBrowseGroupId }

                val memberFolders = (browsedGroup?.memberBucketIds ?: emptyList())
                    .mapNotNull { bid -> folders.find { it.bucketId == bid } }

                val subGroups = groups.filter { it.parentGroupId == currentBrowseGroupId }

                // Get the group's sort option (from groupSortOptions map)
                val groupSortId = groupSortOptions[currentBrowseGroupId] ?: 0 // 0 = CUSTOM_ORDER

                val customOrder = groupCustomOrders[currentBrowseGroupId]

                when (groupSortId) {
                0 -> {  // CUSTOM_ORDER
                    val customOrder = groupCustomOrders[currentBrowseGroupId]
                    if (customOrder != null && customOrder.isNotEmpty()) {
                        val subGroupMap = subGroups.associateBy { "g_${it.groupId}" }
                        val folderMap   = memberFolders.associateBy { "f_${it.bucketId}" }
                        val savedSet    = customOrder.toSet()
                        buildList {
                            // New items not yet in the saved order go first (in their original order, not alphabetical)
                            subGroups.filter   { "g_${it.groupId}"  !in savedSet }.forEach { add(MixedItem.Group(it)) }
                            memberFolders.filter { "f_${it.bucketId}" !in savedSet }.forEach { add(MixedItem.Folder(it)) }
                            // Then items in the persisted drag order
                            for (key in customOrder) {
                                subGroupMap[key]?.let { add(MixedItem.Group(it)) }
                                    ?: folderMap[key]?.let { add(MixedItem.Folder(it)) }
                            }
                        }
                    } else {
                        // No custom order saved yet — use default order (sub-groups first, then folders)
                        // This matches the behavior of GroupDetailScreen when Custom sort is selected
                        // but the user hasn't dragged to reorder anything yet.
                        buildList {
                            subGroups.forEach    { add(MixedItem.Group(it))  }
                            memberFolders.forEach { add(MixedItem.Folder(it)) }
                        }
                    }
                }
                1 -> {  // NAME_A_TO_Z
                    buildList {
                        subGroups.sortedBy    { it.name.lowercase() }.forEach { add(MixedItem.Group(it))  }
                        memberFolders.sortedBy { it.name.lowercase() }.forEach { add(MixedItem.Folder(it)) }
                    }
                }
                2 -> {  // NAME_Z_TO_A
                    buildList {
                        subGroups.sortedByDescending    { it.name.lowercase() }.forEach { add(MixedItem.Group(it))  }
                        memberFolders.sortedByDescending { it.name.lowercase() }.forEach { add(MixedItem.Folder(it)) }
                    }
                }
                3 -> {  // ITEMS_MOST_FIRST
                    buildList {
                        subGroups.sortedByDescending    { it.totalItemCount }.forEach { add(MixedItem.Group(it))  }
                        memberFolders.sortedByDescending { it.itemCount }.forEach { add(MixedItem.Folder(it)) }
                    }
                }
                4 -> {  // ITEMS_FEWEST_FIRST
                    buildList {
                        subGroups.sortedBy    { it.totalItemCount }.forEach { add(MixedItem.Group(it))  }
                        memberFolders.sortedBy { it.itemCount }.forEach { add(MixedItem.Folder(it)) }
                    }
                }
                else -> {  // Fallback to alphabetical
                    buildList {
                        subGroups.sortedBy    { it.name.lowercase() }.forEach { add(MixedItem.Group(it))  }
                        memberFolders.sortedBy { it.name.lowercase() }.forEach { add(MixedItem.Folder(it)) }
                    }
                }
                }
            }
        } else {
            // Root level: honour the caller's orderedMixedItems (reflects the user's
            // current sort option — custom drag order, A-Z, item count, etc.).
            val fromOrder = orderedMixedItems.mapNotNull { item ->
                when (item) {
                    is GroupItem -> if (item.parentGroupId == null) MixedItem.Group(item) else null
                    is FolderItem -> MixedItem.Folder(item)
                    else -> null
                }
            }
            if (fromOrder.isNotEmpty()) {
                fromOrder
            } else {
                // Fallback (orderedMixedItems not yet populated): sort alphabetically.
                val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
                val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
                    .sortedBy { it.name.lowercase() }
                val rootGroups = groups.filter { it.parentGroupId == null }
                    .sortedBy { it.name.lowercase() }
                buildList {
                    rootGroups.forEach { add(MixedItem.Group(it)) }
                    ungroupedFolders.forEach { add(MixedItem.Folder(it)) }
                }
            }
        }
    }
    val visibleFolders = remember(displayItems) { displayItems.mapNotNull { (it as? MixedItem.Folder)?.folder } }

    BackHandler {
        if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1) else onBack()
    }

    fun toggleSelection(id: Int) {
        val newSet = selectedIds.toMutableSet()
        if (newSet.contains(id)) newSet.remove(id) else newSet.add(id)
        selectedIds = newSet
        isSelectionMode = newSet.isNotEmpty()
    }

    fun selectAll() {
        if (selectedIds.size == visibleFolders.size) {
            selectedIds = emptySet()
            isSelectionMode = false
        } else {
            selectedIds = visibleFolders.map { it.bucketId }.toSet()
            isSelectionMode = true
        }
    }

    fun cancelSelection() {
        selectedIds = emptySet()
        isSelectionMode = false
    }

    // Inline create-folder dialog
    if (showCreateDialog) {
        var folderName by remember { mutableStateOf("") }
        var error by remember { mutableStateOf<String?>(null) }

        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            shape = RoundedCornerShape(28.dp),
            containerColor = colors.popupBg,
            titleContentColor = colors.listFirstText,
            textContentColor = colors.listFirstText,
            title = { Text("Create new folder") },
            text = {
                Column {
                    OutlinedTextField(
                        value = folderName,
                        onValueChange = {
                            folderName = it
                            val dcimDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            val folderExists = File(dcimDir, it).exists()
                            val nameInUse = folders.any { f -> f.name.equals(it, ignoreCase = true) }
                            error = when {
                                nameInUse -> "Folder name already in use."
                                folderExists -> "A folder with this name already exists."
                                else -> null
                            }
                        },
                        singleLine = true,
                        label = { Text("Folder name") },
                        isError = error != null,
                        supportingText = error?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showCreateDialog = false
                        if (onCreateFolderAndSelect != null) {
                            onCreateFolderAndSelect(folderName)
                        } else {
                            val newPath = File(
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                                folderName
                            ).absolutePath
                            File(newPath).mkdirs()
                            onFolderSelected(newPath)
                        }
                    },
                    enabled = folderName.isNotBlank() && error == null
                ) { Text("Create") }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) { Text("Cancel") }
            }
        )
    }

    Column(modifier = modifier.fillMaxSize().background(Color.Black)) {

        // ── Header: back chevron + "Create" ──
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .heightIn(min = 56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back chevron
                IconButton(onClick = {
                    if (browseStack.isNotEmpty()) browseStack = browseStack.dropLast(1) else onBack()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = browseStack.lastOrNull()?.second ?: title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                // Create button
                TextButton(onClick = { showCreateDialog = true }) {
                    Text(
                        text = "Create",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // ── Folder grid ──
        if (displayItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📁", style = MaterialTheme.typography.displayLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No folders available",
                        fontSize = 16.sp,
                        color = Color(0xFF888888)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    state = lazyGridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(displayItems, key = { it.uniqueKey }) { item ->
                        when (item) {
                            is MixedItem.Folder -> {
                                val folder = item.folder
                                val isSelected = selectedIds.contains(folder.bucketId)
                                FolderPickerGridItem(
                                    folder = folder,
                                    isSelected = isSelected,
                                    isSelectionMode = isSelectionMode,
                                    thumbnailContent = thumbnailContent,
                                    onClick = {
                                        if (isSelectionMode) toggleSelection(folder.bucketId)
                                        else onFolderSelected(folder.path)
                                    },
                                    onLongClick = { toggleSelection(folder.bucketId) }
                                )
                            }
                            is MixedItem.Group -> {
                                groupItemContent(
                                    item.group,
                                    { browseStack = browseStack + (item.group.groupId to item.group.name) },
                                    { browseStack = browseStack + (item.group.groupId to item.group.name) }
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

        // ── Bottom selection bar ──
        if (isSelectionMode) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Circle checkbox (select all)
                    val allSelected = selectedIds.size == visibleFolders.size && visibleFolders.isNotEmpty()
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .then(
                                if (allSelected)
                                    Modifier.background(Color.White)
                                else
                                    Modifier.border(2.dp, Color.White, CircleShape)
                            )
                            .clickable { selectAll() },
                        contentAlignment = Alignment.Center
                    ) {
                        if (allSelected) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Deselect all",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Selected count
                    Text(
                        text = "${selectedIds.size}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Cancel pill button
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { cancelSelection() },
                        color = Color(0xFF3A3A3A),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FolderPickerGridItem(
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    thumbnailContent: @Composable (FolderItem, Modifier) -> Unit,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF2A2A2A), Color(0xFF3A3A3A))
                )
            )
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
    ) {
        if (folder.latestItemUri != null) {
            thumbnailContent(folder, Modifier.fillMaxWidth().aspectRatio(0.75f))
        } else {
            Box(modifier = Modifier.fillMaxWidth().aspectRatio(0.75f))
        }
        GridItemOverlay(
            name            = folder.name,
            subtitle        = "${folder.itemCount}",
            isSelected      = isSelected,
            isSelectionMode = isSelectionMode
        )
    }
}




