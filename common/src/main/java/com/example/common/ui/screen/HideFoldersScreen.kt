package com.example.common.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.components.CircularBackButton
import com.example.common.ui.components.ScreenTopBar
import com.example.common.ui.theme.LibraryColors
import com.example.common.ui.theme.LocalLibraryColors

/**
 * Full-screen "Hide folders" screen.
 *
 * **Root view** (when [currentGroupId] is null):
 *  - Shows [groups] each with a chevron (tap to drill in) + whole-group Switch
 *  - Shows [ungroupedFolders] below groups
 *
 * **Group detail view** (when [currentGroupId] is non-null):
 *  - Shows [groupFolders] for the open group with individual Switches
 *  - Back navigates to the root view
 *
 * @param groups                Groups at the root hide screen.
 * @param ungroupedFolders      Folders that are not in any group.
 * @param groupFolders          Folders inside the currently-open group (empty = root view).
 * @param currentGroupId        Non-null when a group is open.
 * @param currentGroupName      Name of the open group.
 * @param hiddenFolderPaths     Set of folder paths currently hidden.
 * @param onGroupOpen           Called when the user taps a group row (not the switch).
 * @param onGroupToggle         Called when the group-level switch is toggled.
 * @param onFolderToggle        Called when a folder switch is toggled.
 * @param onGroupBack           Called to go back from a group detail to the root.
 * @param thumbnailContent      Slot: (FolderItem, Modifier) → composable thumbnail.
 * @param groupThumbnailContent Slot: (GroupItem, Modifier) → composable group preview.
 * @param onBack                Navigate back out of the hide screen entirely.
 */
@Composable
fun HideFoldersScreen(
    groups: List<GroupItem>,
    ungroupedFolders: List<FolderItem>,
    groupFolders: List<FolderItem>,
    currentGroupId: Long?,
    currentGroupName: String,
    hiddenFolderPaths: Set<String>,
    /** groupId → true when ALL of the group's folders are hidden. */
    groupHiddenState: Map<Long, Boolean> = emptyMap(),
    /** Sub-groups inside the currently-open group (shown in the group-detail view). */
    groupSubGroups: List<GroupItem> = emptyList(),
    /** hiddenState for sub-groups inside the currently-open group. */
    groupSubGroupHiddenState: Map<Long, Boolean> = emptyMap(),
    onGroupOpen: (GroupItem) -> Unit,
    onGroupToggle: (GroupItem) -> Unit,
    onFolderToggle: (FolderItem) -> Unit,
    onGroupBack: () -> Unit,
    thumbnailContent: @Composable (FolderItem, Modifier) -> Unit,
    /**
     * Per-cell renderer for group mosaic thumbnails.
     * Receives a single [Uri] (nullable = empty slot) and the cell [Modifier].
     * Matches the same contract as [GroupGridItem.previewCell].
     */
    groupThumbnailContent: @Composable (Uri?, Modifier) -> Unit = { _, _ -> },
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalLibraryColors.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.screenBackground)
    ) {
        // ── Header ─────────────────────────────────────────────────────
        ScreenTopBar {
            CircularBackButton(onClick = if (currentGroupId != null) onGroupBack else onBack)
            Spacer(Modifier.width(12.dp))
            Text(
                text       = if (currentGroupId != null) currentGroupName else "Hide folders",
                fontSize   = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color      = colors.listFirstText
            )
        }

        // ── Subtitle ────────────────────────────────────────────────────
        Text(
            text = if (currentGroupId != null)
                "Toggle albums inside \"$currentGroupName\" to hide or show them."
            else
                "Toggle a group or album to hide it from the main view.",
            fontSize   = 13.sp,
            color      = colors.listSecondText,
            lineHeight = 18.sp,
            modifier   = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        HorizontalDivider(color = colors.dividerColor)

        // ── List ─────────────────────────────────────────────────────────
        LazyColumn(
            modifier        = Modifier.fillMaxSize(),
            contentPadding  = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (currentGroupId != null) {
                // ── Group detail: sub-groups first, then member folders ──
                if (groupSubGroups.isNotEmpty()) {
                    item {
                        Text(
                            text       = "GROUPS",
                            fontSize   = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color      = colors.listSecondText,
                            modifier   = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 2.dp)
                        )
                    }
                    items(groupSubGroups, key = { "sg_${it.groupId}" }) { subGroup ->
                        HideGroupRow(
                            group       = subGroup,
                            isHidden    = groupSubGroupHiddenState[subGroup.groupId] == true,
                            onOpen      = { onGroupOpen(subGroup) },
                            onToggle    = { onGroupToggle(subGroup) },
                            colors      = colors,
                            previewCell = groupThumbnailContent
                        )
                    }
                }
                if (groupFolders.isNotEmpty()) {
                    if (groupSubGroups.isNotEmpty()) {
                        item {
                            Text(
                                text       = "ALBUMS",
                                fontSize   = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color      = colors.listSecondText,
                                modifier   = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 2.dp)
                            )
                        }
                    }
                    items(groupFolders, key = { it.path.ifBlank { it.bucketId.toString() } }) { folder ->
                        HideFolderRow(
                            folder           = folder,
                            isHidden         = folder.path in hiddenFolderPaths,
                            onToggle         = { onFolderToggle(folder) },
                            thumbnailContent = thumbnailContent,
                            colors           = colors
                        )
                    }
                }
                if (groupSubGroups.isEmpty() && groupFolders.isEmpty()) {
                    item {
                        Text(
                            text     = "No albums in this group.",
                            color    = colors.listSecondText,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                // ── Root: groups first ──
                if (groups.isNotEmpty()) {
                    item {
                        Text(
                            text     = "GROUPS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color    = colors.listSecondText,
                            modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 2.dp)
                        )
                    }
                    items(groups, key = { "g_${it.groupId}" }) { group ->
                        HideGroupRow(
                            group          = group,
                            isHidden       = groupHiddenState[group.groupId] == true,
                            onOpen         = { onGroupOpen(group) },
                            onToggle       = { onGroupToggle(group) },
                            colors         = colors,
                            previewCell    = groupThumbnailContent
                        )
                    }
                }

                // ── Root: ungrouped albums ──
                if (ungroupedFolders.isNotEmpty()) {
                    item {
                        Text(
                            text     = if (groups.isEmpty()) "ALBUMS" else "OTHER ALBUMS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color    = colors.listSecondText,
                            modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 2.dp)
                        )
                    }
                    items(ungroupedFolders, key = { "f_${it.path.ifBlank { it.bucketId.toString() }}" }) { folder ->
                        HideFolderRow(
                            folder           = folder,
                            isHidden         = folder.path in hiddenFolderPaths,
                            onToggle         = { onFolderToggle(folder) },
                            thumbnailContent = thumbnailContent,
                            colors           = colors
                        )
                    }
                }
            }
        }
    }
}

// ── Group row ────────────────────────────────────────────────────────────────

@Composable
private fun HideGroupRow(
    group: GroupItem,
    isHidden: Boolean,
    onOpen: () -> Unit,
    onToggle: () -> Unit,
    colors: LibraryColors,
    /** Per-URI cell renderer — same contract as GroupGridItem.previewCell. */
    previewCell: @Composable (Uri?, Modifier) -> Unit = { _, _ -> }
) {
    Surface(
        shape          = RoundedCornerShape(14.dp),
        color          = colors.cardBackground,
        tonalElevation = 0.dp,
        modifier       = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onOpen)
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ── Mosaic thumbnail (mirrors GroupGridItem layout) ──────────
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.dividerColor),
                contentAlignment = Alignment.Center
            ) {
                val uris = group.previewUris
                val gap  = 1.dp
                when {
                    uris.isEmpty() -> {
                        Icon(
                            Icons.Default.Folder,
                            contentDescription = null,
                            tint     = colors.listSecondText,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    uris.size == 1 -> previewCell(uris[0], Modifier.fillMaxSize())
                    uris.size == 2 -> {
                        Row(
                            modifier              = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(gap)
                        ) {
                            previewCell(uris[0], Modifier.weight(1f).fillMaxHeight())
                            previewCell(uris[1], Modifier.weight(1f).fillMaxHeight())
                        }
                    }
                    uris.size == 3 -> {
                        Row(
                            modifier              = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(gap)
                        ) {
                            previewCell(uris[0], Modifier.weight(1f).fillMaxHeight())
                            Column(
                                modifier            = Modifier.weight(1f).fillMaxHeight(),
                                verticalArrangement = Arrangement.spacedBy(gap)
                            ) {
                                previewCell(uris[1], Modifier.weight(1f).fillMaxWidth())
                                previewCell(uris[2], Modifier.weight(1f).fillMaxWidth())
                            }
                        }
                    }
                    else -> {
                        Column(
                            modifier            = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(gap)
                        ) {
                            for (row in 0..1) {
                                Row(
                                    modifier              = Modifier.weight(1f).fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(gap)
                                ) {
                                    for (col in 0..1) {
                                        previewCell(
                                            uris.getOrNull(row * 2 + col),
                                            Modifier.weight(1f).fillMaxHeight()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.width(14.dp))

            // ── Name + count + tap hint ──
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = group.name,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color      = colors.listFirstText,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )
                val subtitle = buildString {
                    if (group.subGroupCount > 0) append("${group.subGroupCount} group${if (group.subGroupCount == 1) "" else "s"}")
                    if (group.subGroupCount > 0 && group.folderCount > 0) append(" · ")
                    if (group.folderCount > 0) append("${group.folderCount} album${if (group.folderCount == 1) "" else "s"}")
                    if (group.subGroupCount == 0 && group.folderCount == 0) append("Empty")
                }
                Text(text = subtitle, fontSize = 13.sp, color = colors.listSecondText)
                Text(
                    text     = "Tap to hide individually →",
                    fontSize = 11.sp,
                    color    = colors.listSecondText.copy(alpha = 0.6f)
                )
            }

            Spacer(Modifier.width(16.dp))

            // ── Toggle switch ────────────────────────────────────────────
            Switch(
                checked         = isHidden,
                onCheckedChange = { onToggle() },
                colors          = SwitchDefaults.colors(
                    checkedThumbColor    = colors.cardBackground,
                    checkedTrackColor    = colors.primary,
                    uncheckedThumbColor  = colors.listSecondText,
                    uncheckedTrackColor  = colors.dividerColor,
                    uncheckedBorderColor = colors.dividerColor
                )
            )
        }
    }
}

// ── Folder row ───────────────────────────────────────────────────────────────

@Composable
private fun HideFolderRow(
    folder: FolderItem,
    isHidden: Boolean,
    onToggle: () -> Unit,
    thumbnailContent: @Composable (FolderItem, Modifier) -> Unit,
    colors: LibraryColors
) {
    Surface(
        shape          = RoundedCornerShape(14.dp),
        color          = colors.cardBackground,
        tonalElevation = 0.dp,
        modifier       = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            thumbnailContent(
                folder,
                Modifier.size(64.dp).clip(RoundedCornerShape(10.dp))
            )

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text       = folder.name,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color      = colors.listFirstText,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )
                Text(
                    text     = "${folder.itemCount}",
                    fontSize = 13.sp,
                    color    = colors.listSecondText
                )
            }

            Spacer(Modifier.width(12.dp))

            Switch(
                checked         = isHidden,
                onCheckedChange = { onToggle() },
                colors          = SwitchDefaults.colors(
                    checkedThumbColor    = colors.cardBackground,
                    checkedTrackColor    = colors.primary,
                    uncheckedThumbColor  = colors.listSecondText,
                    uncheckedTrackColor  = colors.dividerColor,
                    uncheckedBorderColor = colors.dividerColor
                )
            )
        }
    }
}



