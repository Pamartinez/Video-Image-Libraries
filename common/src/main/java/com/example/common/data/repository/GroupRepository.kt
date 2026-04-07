package com.example.common.data.repository

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
    suspend fun getRootGroups(
        groupSortOptions: Map<Long, Int> = emptyMap(),
        groupCustomOrders: Map<Long, List<String>> = emptyMap()
    ): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getRootGroups().map { buildGroupItem(it, groupSortOptions, groupCustomOrders) }
    }

    /** Get child groups of a parent group. */
    suspend fun getChildGroups(
        parentGroupId: Long,
        groupSortOptions: Map<Long, Int> = emptyMap(),
        groupCustomOrders: Map<Long, List<String>> = emptyMap()
    ): List<GroupItem> = withContext(Dispatchers.IO) {
        store.getChildGroups(parentGroupId).map { buildGroupItem(it, groupSortOptions, groupCustomOrders) }
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

    private suspend fun buildGroupItem(
        entity: GroupEntity,
        groupSortOptions: Map<Long, Int> = emptyMap(),
        groupCustomOrders: Map<Long, List<String>> = emptyMap()
    ): GroupItem {
        val memberBucketIds = store.getBucketIdsForGroup(entity.groupId)
        val childGroups     = store.getChildGroups(entity.groupId)
        val allFolders      = getFolders()

        // Build ordered list of items (folders and sub-groups) respecting the group's sort
        val orderedItems = buildOrderedGroupItems(
            entity.groupId,
            memberBucketIds,
            childGroups,
            allFolders,
            groupSortOptions,
            groupCustomOrders
        )

        // Extract first 4 FOLDERS ONLY (skip groups) for preview
        val previewUris = orderedItems
            .filterIsInstance<FolderItem>()
            .take(4)
            .mapNotNull { folder ->
                android.util.Log.d("GroupRepository", "buildGroupItem(${entity.groupId}): Preview folder bucketId=${folder.bucketId}, name=${folder.name}")
                folder.latestItemUri
            }
        android.util.Log.d("GroupRepository", "buildGroupItem(${entity.groupId}): sortOption=${groupSortOptions[entity.groupId]}, hasCustomOrder=${groupCustomOrders[entity.groupId]?.isNotEmpty()}, previewCount=${previewUris.size}")

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

    private suspend fun buildOrderedGroupItems(
        groupId: Long,
        memberBucketIds: List<Int>,
        childGroups: List<GroupEntity>,
        allFolders: List<FolderItem>,
        groupSortOptions: Map<Long, Int>,
        groupCustomOrders: Map<Long, List<String>>
    ): List<Any> {
        // Get member folders
        val memberFolders = memberBucketIds.mapNotNull { bid ->
            allFolders.find { it.bucketId == bid }
        }
        
        // Convert child entities to GroupItems (lightweight, just for ordering)
        val subGroups = childGroups.map { child ->
            GroupItem(
                groupId = child.groupId,
                name = child.name,
                parentGroupId = child.parentGroupId
            )
        }

        // Get sort option for this group (default to 0 = CUSTOM_ORDER)
        val sortOptionId = groupSortOptions[groupId] ?: 0

        // Apply sort based on option
        return when (sortOptionId) {
            0 -> { // CUSTOM_ORDER
                val savedOrder = groupCustomOrders[groupId] ?: emptyList()
                if (savedOrder.isEmpty()) {
                    // No saved order, return in database order
                    subGroups + memberFolders
                } else {
                    // Build map of items by their keys
                    val byGroupKey  = subGroups.associateBy { "g_${it.groupId}" }
                    val byFolderKey = memberFolders.associateBy { "f_${it.bucketId}" }
                    val savedSet = savedOrder.toSet()
                    
                    // New items first, then saved order
                    buildList {
                        subGroups.forEach { if ("g_${it.groupId}" !in savedSet) add(it) }
                        memberFolders.forEach { if ("f_${it.bucketId}" !in savedSet) add(it) }
                        savedOrder.forEach { key ->
                            (byGroupKey[key] ?: byFolderKey[key])?.let { add(it) }
                        }
                    }
                }
            }
            1 -> { // NAME_A_TO_Z
                (subGroups + memberFolders).sortedBy { item ->
                    when (item) {
                        is GroupItem -> item.name.lowercase()
                        is FolderItem -> item.name.lowercase()
                        else -> ""
                    }
                }
            }
            2 -> { // NAME_Z_TO_A
                (subGroups + memberFolders).sortedByDescending { item ->
                    when (item) {
                        is GroupItem -> item.name.lowercase()
                        is FolderItem -> item.name.lowercase()
                        else -> ""
                    }
                }
            }
            3 -> { // ITEMS_MOST_FIRST
                (subGroups + memberFolders).sortedByDescending { item ->
                    when (item) {
                        is GroupItem -> item.totalItemCount
                        is FolderItem -> item.itemCount
                        else -> 0
                    }
                }
            }
            4 -> { // ITEMS_FEWEST_FIRST
                (subGroups + memberFolders).sortedBy { item ->
                    when (item) {
                        is GroupItem -> item.totalItemCount
                        is FolderItem -> item.itemCount
                        else -> 0
                    }
                }
            }
            else -> subGroups + memberFolders
        }
    }
}




