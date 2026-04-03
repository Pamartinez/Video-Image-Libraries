package com.example.common.data.repository

import android.net.Uri
import com.example.common.data.db.GroupStore
import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupEntity
import com.example.common.data.model.GroupItem
import com.example.common.data.model.GroupMemberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Shared business logic for creating, reading, updating and deleting folder groups.
 *
 * The only library-specific concern is how to load the list of [FolderItem]s
 * (images vs. videos come from different MediaStore tables). This is injected via
 * the [getFolders] lambda, keeping this class fully library-agnostic.
 *
 * Each library subclasses or constructs this directly:
 * ```
 * // image-library
 * class GroupRepository(context: Context) : CommonGroupRepository(
 *     store      = GroupStore(context),
 *     getFolders = { ImageRepository(context).getFolders() }
 * )
 * // video-library
 * class GroupRepository(store: GroupStore, repo: VideoRepository) : CommonGroupRepository(
 *     store      = store,
 *     getFolders = { repo.getFolders() }
 * )
 * ```
 */
open class GroupRepository(
    private val store: GroupStore,
    private val getFolders: suspend () -> List<FolderItem>
) {

    // ── Create ──────────────────────────────────────────────────────────────

    /**
     * Create a group from selected folder bucket IDs and/or sub-group IDs.
     * @param name            Group name
     * @param folderBucketIds Folder bucket IDs to add as members
     * @param subGroupIds     Group IDs to nest inside the new group
     * @param parentGroupId   If creating inside another group
     * @return the new group's ID
     */
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

    // ── Read ─────────────────────────────────────────────────────────────────

    /** Get all root-level groups (parentGroupId == null) as [GroupItem]s. */
    suspend fun getRootGroups(): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getRootGroups().map { buildGroupItem(it) }
    }

    /** Get child groups of a parent group. */
    suspend fun getChildGroups(parentGroupId: Long): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getChildGroups(parentGroupId).map { buildGroupItem(it) }
    }

    /** Get all groups (e.g. for "add folder" picker). */
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

    /** Get raw group entity by ID (useful for checking parentGroupId). */
    suspend fun getGroupById(id: Long): GroupEntity? = withContext(Dispatchers.IO) {
        store.getGroupById(id)
    }

    // ── Update ───────────────────────────────────────────────────────────────

    suspend fun renameGroup(groupId: Long, newName: String) = withContext(Dispatchers.IO) {
        store.renameGroup(groupId, newName)
    }

    /** Add folders to an existing group. */
    suspend fun addFoldersToGroup(groupId: Long, bucketIds: List<Int>) = withContext(Dispatchers.IO) {
        store.insertMembers(bucketIds.map { GroupMemberEntity(it, groupId) })
    }

    /** Add sub-groups to an existing group. */
    suspend fun addSubGroupsToGroup(parentGroupId: Long, subGroupIds: List<Long>) =
        withContext(Dispatchers.IO) {
            subGroupIds.forEach { store.moveGroup(it, parentGroupId) }
        }

    /**
     * Move selected folders and groups into a target group.
     * Pass null for [targetGroupId] to move items to the root level (ungroup).
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

    // ── Delete ───────────────────────────────────────────────────────────────

    /** Remove a single folder from its group (back to ungrouped). */
    suspend fun removeFolderFromGroup(bucketId: Int) = withContext(Dispatchers.IO) {
        store.removeMemberByBucketId(bucketId)
    }

    /**
     * Destroy a group:
     * - Child sub-groups move up one level (to this group's parent).
     * - Member folders move to the parent group; if at root, they become ungrouped.
     * - The group entity itself is deleted.
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

    // ── Private helpers ──────────────────────────────────────────────────────

    private suspend fun buildGroupItem(entity: GroupEntity): GroupItem {
        val memberBucketIds = store.getBucketIdsForGroup(entity.groupId)
        val childGroups     = store.getChildGroups(entity.groupId)
        val allFolders      = getFolders()

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
            groupId         = entity.groupId,
            name            = entity.name,
            parentGroupId   = entity.parentGroupId,
            folderCount     = memberBucketIds.size,
            subGroupCount   = childGroups.size,
            totalItemCount  = memberBucketIds.sumOf { bid ->
                allFolders.find { it.bucketId == bid }?.itemCount ?: 0
            },
            previewUris     = previewUris,
            memberBucketIds = memberBucketIds,
            createdAt       = entity.createdAt
        )
    }
}

