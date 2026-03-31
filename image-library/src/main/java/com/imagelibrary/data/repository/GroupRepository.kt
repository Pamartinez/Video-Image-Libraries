package com.imagelibrary.data.repository

import android.content.Context
import com.example.common.data.model.GroupEntity
import com.example.common.data.model.GroupItem
import com.example.common.data.model.GroupMemberEntity
import com.imagelibrary.data.db.GroupStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroupRepository(context: Context) {

    private val store = GroupStore(context)
    private val imageRepository = ImageRepository(context)

    // ── Create ──────────────────────────────────────────────────────

    /**
     * Create a group from selected folder bucket IDs and/or sub-group IDs.
     * @param name Group name
     * @param folderBucketIds Folder bucket IDs to add as members
     * @param subGroupIds Group IDs to nest inside the new group
     * @param parentGroupId If creating inside another group
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
        // Add folders as members
        val members = folderBucketIds.map { GroupMemberEntity(folderBucketId = it, groupId = groupId) }
        store.insertMembers(members)
        // Move sub-groups into the new group
        for (subGroupId in subGroupIds) {
            store.moveGroup(subGroupId, groupId)
        }
        groupId
    }

    // ── Read ────────────────────────────────────────────────────────

    /**
     * Get all root-level groups (parentGroupId == null) as GroupItems.
     */
    suspend fun getRootGroups(): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getRootGroups().map { buildGroupItem(it) }
    }

    /**
     * Get child groups of a parent group.
     */
    suspend fun getChildGroups(parentGroupId: Long): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getChildGroups(parentGroupId).map { buildGroupItem(it) }
    }

    /**
     * Get all groups (for "add folder" picker where we exclude current group).
     */
    suspend fun getAllGroups(): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getAllGroups().map { buildGroupItem(it) }
    }

    /**
     * Get the set of all folder bucket IDs that are assigned to any group.
     */
    suspend fun getGroupedBucketIds(): Set<Int> = withContext(Dispatchers.IO) {
        store.getAllMembers().map { it.folderBucketId }.toSet()
    }

    /**
     * Get folder bucket IDs that belong to a specific group.
     */
    suspend fun getFolderBucketIdsForGroup(groupId: Long): List<Int> = withContext(Dispatchers.IO) {
        store.getBucketIdsForGroup(groupId)
    }

    /**
     * Get group entity by ID.
     */
    suspend fun getGroupById(id: Long): GroupEntity? = withContext(Dispatchers.IO) {
        store.getGroupById(id)
    }

    // ── Update ──────────────────────────────────────────────────────

    suspend fun renameGroup(groupId: Long, newName: String) = withContext(Dispatchers.IO) {
        store.renameGroup(groupId, newName)
    }

    /**
     * Add folders to an existing group.
     */
    suspend fun addFoldersToGroup(groupId: Long, bucketIds: List<Int>) = withContext(Dispatchers.IO) {
        val members = bucketIds.map { GroupMemberEntity(folderBucketId = it, groupId = groupId) }
        store.insertMembers(members)
    }

    /**
     * Add sub-groups to an existing group.
     */
    suspend fun addSubGroupsToGroup(parentGroupId: Long, subGroupIds: List<Long>) = withContext(Dispatchers.IO) {
        for (id in subGroupIds) {
            store.moveGroup(id, parentGroupId)
        }
    }

    /**
     * Move selected folders and groups into a target group.
     * Folders are re-assigned to the target group (removed from previous group if any).
     * Groups are re-parented to the target group.
     * @param targetGroupId The group to move items into, or null for root level.
     */
    suspend fun moveItemsToGroup(
        folderBucketIds: List<Int>,
        groupIds: List<Long>,
        targetGroupId: Long?
    ) = withContext(Dispatchers.IO) {
        // Move folders
        for (bucketId in folderBucketIds) {
            // Remove existing membership first
            store.removeMemberByBucketId(bucketId)
            // If moving to a group (not root), add membership
            if (targetGroupId != null) {
                store.insertMembers(listOf(GroupMemberEntity(folderBucketId = bucketId, groupId = targetGroupId)))
            }
        }
        // Move groups
        for (gId in groupIds) {
            store.moveGroup(gId, targetGroupId)
        }
    }

    // ── Delete ──────────────────────────────────────────────────────

    /**
     * Remove a single folder from its group (back to ungrouped).
     */
    suspend fun removeFolderFromGroup(bucketId: Int) = withContext(Dispatchers.IO) {
        store.removeMemberByBucketId(bucketId)
    }

    /**
     * Destroy a group:
     * - Member folders become ungrouped (back to root)
     * - Child sub-groups move up one level (to this group's parent)
     */
    suspend fun destroyGroup(groupId: Long) = withContext(Dispatchers.IO) {
        val group = store.getGroupById(groupId) ?: return@withContext
        val parentId = group.parentGroupId

        // Move child groups up one level
        val childGroups = store.getChildGroups(groupId)
        for (child in childGroups) {
            store.moveGroup(child.groupId, parentId)
        }

        // Remove all folder members (they become ungrouped)
        store.deleteAllMembersOfGroup(groupId)

        // Delete the group itself
        store.deleteGroup(groupId)
    }

    // ── Helpers ──────────────────────────────────────────────────────

    private suspend fun buildGroupItem(entity: GroupEntity): GroupItem {
        val memberBucketIds = store.getBucketIdsForGroup(entity.groupId)
        val childGroups = store.getChildGroups(entity.groupId)

        // Collect preview URIs: one image from each of the first 4 member folders
        val previewUris = mutableListOf<android.net.Uri>()
        val allFolders = imageRepository.getFolders()
        for (bucketId in memberBucketIds.take(4)) {
            val folder = allFolders.find { it.bucketId == bucketId }
            folder?.latestItemUri?.let { previewUris.add(it) }
        }
        // If we have fewer than 4 from direct folders, try child group folders
        if (previewUris.size < 4) {
            for (child in childGroups) {
                if (previewUris.size >= 4) break
                val childBucketIds = store.getBucketIdsForGroup(child.groupId)
                for (bucketId in childBucketIds) {
                    if (previewUris.size >= 4) break
                    val folder = allFolders.find { it.bucketId == bucketId }
                    folder?.latestItemUri?.let { previewUris.add(it) }
                }
            }
        }

        val totalItemCount = memberBucketIds.sumOf { bucketId ->
            allFolders.find { it.bucketId == bucketId }?.itemCount ?: 0
        }

        return GroupItem(
            groupId        = entity.groupId,
            name           = entity.name,
            parentGroupId  = entity.parentGroupId,
            folderCount    = memberBucketIds.size,
            subGroupCount  = childGroups.size,
            totalItemCount = totalItemCount,
            previewUris    = previewUris,
            memberBucketIds = memberBucketIds,
            createdAt      = entity.createdAt
        )
    }
}
