package com.imagelibrary.ui.screen

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.model.MixedItem
import com.imagelibrary.ui.components.GroupGridItem
import com.imagelibrary.ui.theme.LocalImageColors
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FolderPickerScreen(
    title: String,
    folders: List<FolderItem>,
    groups: List<GroupItem> = emptyList(),
    orderedMixedItems: List<Any> = emptyList(),
    onFolderSelected: (String) -> Unit,
    onBack: () -> Unit,
    onCreateFolderAndSelect: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val colors = LocalImageColors.current
    var showCreateDialog by remember { mutableStateOf(false) }

    // ── Selection state ──
    var isSelectionMode by remember { mutableStateOf(false) }
    var selectedIds by remember { mutableStateOf(setOf<Int>()) }
    var browseStack by remember { mutableStateOf(listOf<Pair<Long, String>>()) }

    val currentBrowseGroupId = browseStack.lastOrNull()?.first
    val displayItems: List<MixedItem> = remember(folders, groups, orderedMixedItems, currentBrowseGroupId) {
        if (currentBrowseGroupId != null) {
            val browsedGroup = groups.find { it.groupId == currentBrowseGroupId }
            val memberFolders = (browsedGroup?.memberBucketIds ?: emptyList())
                .mapNotNull { bid -> folders.find { it.bucketId == bid } }
            val subGroups = groups.filter { it.parentGroupId == currentBrowseGroupId }
            buildList {
                subGroups.forEach { add(MixedItem.Group(it)) }
                memberFolders.forEach { add(MixedItem.Folder(it)) }
            }
        } else {
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
                val groupedBucketIds = groups.flatMap { it.memberBucketIds }.toSet()
                val ungroupedFolders = folders.filter { it.bucketId !in groupedBucketIds }
                val rootGroups = groups.filter { it.parentGroupId == null }
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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
                                onClick = {
                                    if (isSelectionMode) toggleSelection(folder.bucketId)
                                    else onFolderSelected(folder.path)
                                },
                                onLongClick = { toggleSelection(folder.bucketId) }
                            )
                        }
                        is MixedItem.Group -> {
                            GroupGridItem(
                                group = item.group,
                                isSelected = false,
                                isSelectionMode = false,
                                onClick = {
                                    browseStack = browseStack + (item.group.groupId to item.group.name)
                                },
                                onLongClick = {
                                    browseStack = browseStack + (item.group.groupId to item.group.name)
                                }
                            )
                        }
                    }
                }
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
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Thumbnail – square, large rounded corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF2A2A2A), Color(0xFF3A3A3A))
                    )
                )
        ) {
            if (folder.latestItemUri != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(folder.latestItemUri)
                        
                        .crossfade(true)
                        .build(),
                    contentDescription = folder.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Selection checkmark overlay
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x55000000))
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Folder name
        Text(
            text = folder.name,
            color = Color.White,
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
