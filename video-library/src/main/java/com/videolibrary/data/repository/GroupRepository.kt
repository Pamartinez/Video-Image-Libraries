package com.videolibrary.data.repository

import com.videolibrary.data.db.GroupStore

/**
 * Video-library GroupRepository.
 * Delegates all group logic to the shared [com.example.common.data.repository.GroupRepository].
 * The only video-specific detail — how to load [FolderItem]s — is supplied via lambda.
 */
class GroupRepository(
    store: GroupStore,
    videoRepository: VideoRepository
) : com.example.common.data.repository.GroupRepository(
    store      = store,
    getFolders = { videoRepository.getFolders() }
)
