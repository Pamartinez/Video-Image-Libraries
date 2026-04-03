package com.videolibrary.ui.screen

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.ui.screen.HideFoldersScreen as CommonHideFoldersScreen
import com.videolibrary.ui.components.VideoThumbnail

/**
 * Video-library entry point for the Hide Folders screen.
 * Delegates to [CommonHideFoldersScreen], injecting [VideoThumbnail].
 */
@Composable
fun HideFoldersScreen(
    groups: List<GroupItem>,
    ungroupedFolders: List<FolderItem>,
    groupFolders: List<FolderItem>,
    currentGroupId: Long?,
    currentGroupName: String,
    hiddenFolderPaths: Set<String>,
    groupHiddenState: Map<Long, Boolean>,
    groupSubGroups: List<GroupItem> = emptyList(),
    groupSubGroupHiddenState: Map<Long, Boolean> = emptyMap(),
    onGroupOpen: (GroupItem) -> Unit,
    onGroupToggle: (GroupItem) -> Unit,
    onFolderToggle: (FolderItem) -> Unit,
    onGroupBack: () -> Unit,
    onBack: () -> Unit
) {
    CommonHideFoldersScreen(
        groups                   = groups,
        ungroupedFolders         = ungroupedFolders,
        groupFolders             = groupFolders,
        currentGroupId           = currentGroupId,
        currentGroupName         = currentGroupName,
        hiddenFolderPaths        = hiddenFolderPaths,
        groupHiddenState         = groupHiddenState,
        groupSubGroups           = groupSubGroups,
        groupSubGroupHiddenState = groupSubGroupHiddenState,
        onGroupOpen              = onGroupOpen,
        onGroupToggle            = onGroupToggle,
        onFolderToggle           = onFolderToggle,
        onGroupBack              = onGroupBack,
        onBack                   = onBack,
        thumbnailContent  = { folder, modifier ->
            VideoThumbnail(
                contentUri         = folder.latestItemUri,
                contentDescription = folder.name,
                contentScale       = ContentScale.Crop,
                modifier           = modifier,
                iconSize           = 28.dp
            )
        },
        // Per-URI cell renderer for the group mosaic thumbnail
        groupThumbnailContent = { uri: Uri?, modifier ->
            VideoThumbnail(
                contentUri         = uri,
                contentDescription = null,
                contentScale       = ContentScale.Crop,
                modifier           = modifier,
                iconSize           = 28.dp
            )
        }
    )
}
