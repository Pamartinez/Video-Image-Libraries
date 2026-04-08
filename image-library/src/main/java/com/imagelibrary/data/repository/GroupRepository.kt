package com.imagelibrary.data.repository

import android.content.Context
import com.imagelibrary.data.db.GroupStore
import com.imagelibrary.data.preferences.AppPreferences

/**
 * Image-library GroupRepository.
 * Delegates all group logic to the shared [com.example.common.data.repository.GroupRepository].
 * The only image-specific detail — how to load [FolderItem]s — is supplied via lambda.
 *
 * ⚠️ CRITICAL: Uses getFoldersWithIndependentSort to ensure group previews show
 * album thumbnails that reflect each album's own sort order.
 */
class GroupRepository(context: Context) : com.example.common.data.repository.GroupRepository(
    store      = GroupStore(context),
    getFolders = {
        val preferences = AppPreferences(context)
        ImageRepository(context).getFoldersWithIndependentSort(
            sortOption = preferences.sortOption,
            getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
        )
    }
)
