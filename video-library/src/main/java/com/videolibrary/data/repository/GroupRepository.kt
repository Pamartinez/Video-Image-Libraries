package com.videolibrary.data.repository

import com.videolibrary.data.db.GroupStore
import com.videolibrary.data.preferences.AppPreferences

/**
 * Video-library GroupRepository.
 * Delegates all group logic to the shared [com.example.common.data.repository.GroupRepository].
 * The only video-specific detail — how to load [FolderItem]s — is supplied via lambda.
 *
 * ⚠️ CRITICAL: Uses getFoldersWithIndependentSort to ensure group previews show
 * album thumbnails that reflect each album's own sort order.
 */
class GroupRepository(
    store: GroupStore,
    videoRepository: VideoRepository,
    preferences: AppPreferences
) : com.example.common.data.repository.GroupRepository(
    store      = store,
    getFolders = {
        videoRepository.getFoldersWithIndependentSort(
            folderSortOption = preferences.folderSortOption,
            independentSortEnabled = true,
            getFolderSortOption = { bucketId -> preferences.getFolderVideoSortOption(bucketId) }
        )
    }
)
