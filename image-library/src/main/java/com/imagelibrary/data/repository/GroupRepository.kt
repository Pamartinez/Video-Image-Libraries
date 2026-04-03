package com.imagelibrary.data.repository

import android.content.Context
import com.imagelibrary.data.db.GroupStore

/**
 * Image-library GroupRepository.
 * Delegates all group logic to the shared [com.example.common.data.repository.GroupRepository].
 * The only image-specific detail — how to load [FolderItem]s — is supplied via lambda.
 */
class GroupRepository(context: Context) : com.example.common.data.repository.GroupRepository(
    store      = GroupStore(context),
    getFolders = { ImageRepository(context).getFolders() }
)
