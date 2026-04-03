package com.videolibrary.data.repository

import android.net.Uri
import com.videolibrary.data.db.GroupStore
import com.example.common.data.model.GroupEntity
import com.example.common.data.model.GroupItem
import com.example.common.data.model.GroupMemberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Business logic for creating, reading, updating and deleting folder groups.
 * All public functions are safe to call from any coroutine scope; they run on
 * [Dispatchers.IO] internally.
 */
class GroupRepository(
    private val store: GroupStore,
    private val videoRepository: VideoRepository
) {

    // ── Create ─────────────────────────────────────────────────────────

    suspend fun createGroup(
        name: String,
        folderBucketIds: List<Int>,
        subGroupIds: List<Long> = emptyList(),
        parentGroupId: Long? = null
    ): Long = withContext(Dispatchers.IO) {
        val groupId = store.insertGroup(
            GroupEntity(name = name, parentGroupId = parentGroupId)
        )
        store.insertMembers(folderBucketIds.map { GroupMemberEntity(it, groupId) })
        subGroupIds.forEach { store.moveGroup(it, groupId) }
        groupId
    }

    // ── Read ───────────────────────────────────────────────────────────

    suspend fun getRootGroups(): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getRootGroups().map { buildGroupItem(it) }
    }

    suspend fun getChildGroups(parentGroupId: Long): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getChildGroups(parentGroupId).map { buildGroupItem(it) }
    }

    suspend fun getAllGroups(): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getAllGroups().map { buildGroupItem(it) }
    }

    /** Returns every folder bucketId that belongs to any group. */
    suspend fun getGroupedBucketIds(): Set<Int> = withContext(Dispatchers.IO) {
        store.getAllMembers().map { it.folderBucketId }.toSet()
    }

    suspend fun getFolderBucketIdsForGroup(groupId: Long): List<Int> = withContext(Dispatchers.IO) {
        store.getBucketIdsForGroup(groupId)
    }

    // ── Mutate ─────────────────────────────────────────────────────────

    suspend fun renameGroup(id: Long, name: String) = withContext(Dispatchers.IO) {
        store.renameGroup(id, name)
    }

    suspend fun addFoldersToGroup(groupId: Long, folderBucketIds: List<Int>) =
        withContext(Dispatchers.IO) {
            store.insertMembers(folderBucketIds.map { GroupMemberEntity(it, groupId) })
        }

    suspend fun addSubGroupsToGroup(groupId: Long, subGroupIds: List<Long>) =
        withContext(Dispatchers.IO) {
            subGroupIds.forEach { store.moveGroup(it, groupId) }
        }

    /**
     * Moves folders and/or sub-groups to [targetGroupId].
     * Passing null for [targetGroupId] ungrouped the items (places them at root level).
     */
    suspend fun moveItemsToGroup(
        folderBucketIds: List<Int>,
        groupIds: List<Long>,
        targetGroupId: Long?
    ) = withContext(Dispatchers.IO) {
        folderBucketIds.forEach { bid ->
            store.removeMemberByBucketId(bid)
            if (targetGroupId != null)
                store.insertMembers(listOf(GroupMemberEntity(bid, targetGroupId)))
        }
        groupIds.forEach { gId -> store.moveGroup(gId, targetGroupId) }
    }

    /**
     * Destroys a group:
     *  1. Promotes all child groups one level up (to this group's parent).
     *  2. Moves all member folders to the parent group; if at root, they become ungrouped.
     *  3. Deletes the group entity itself.
     */
    suspend fun destroyGroup(groupId: Long) = withContext(Dispatchers.IO) {
        val group = store.getGroupById(groupId) ?: return@withContext
        val parent = group.parentGroupId
        // Promote child groups to parent
        store.getChildGroups(groupId).forEach { store.moveGroup(it.groupId, parent) }
        // Move member folders to parent, or ungroup if already at root
        val memberBucketIds = store.getBucketIdsForGroup(groupId)
        store.deleteAllMembersOfGroup(groupId)
        if (parent != null) {
            store.insertMembers(memberBucketIds.map { GroupMemberEntity(it, parent) })
        }
        store.deleteGroup(groupId)
    }

    // ── Private helpers ────────────────────────────────────────────────

    private suspend fun buildGroupItem(entity: GroupEntity): GroupItem {
        val memberBucketIds = store.getBucketIdsForGroup(entity.groupId)
        val childGroups     = store.getChildGroups(entity.groupId)
        val allFolders      = videoRepository.getFolders()

        // Up to 4 preview URIs — take from member folders first, then child groups
        val previewUris = mutableListOf<Uri>()
        memberBucketIds.take(4).forEach { bid ->
            allFolders.find { it.bucketId == bid }?.latestItemUri?.let { previewUris.add(it) }
        }
        if (previewUris.size < 4) {
            for (child in childGroups) {
                if (previewUris.size >= 4) break
                store.getBucketIdsForGroup(child.groupId).forEach { bid ->
                    if (previewUris.size >= 4) return@forEach
                    allFolders.find { it.bucketId == bid }?.latestItemUri?.let { previewUris.add(it) }
                }
            }
        }

        return GroupItem(
            groupId        = entity.groupId,
            name           = entity.name,
            parentGroupId  = entity.parentGroupId,
            folderCount    = memberBucketIds.size,
            subGroupCount  = childGroups.size,
            totalItemCount = memberBucketIds.sumOf { bid ->
                allFolders.find { it.bucketId == bid }?.itemCount ?: 0
            },
            previewUris    = previewUris,
            memberBucketIds = memberBucketIds,
            createdAt      = entity.createdAt
        )
    }
}

