package com.example.common.data.db

import android.content.Context
import android.content.SharedPreferences
import com.example.common.data.model.GroupEntity
import com.example.common.data.model.GroupMemberEntity
import org.json.JSONArray
import org.json.JSONObject

/**
 * SharedPreferences + JSON persistence for groups and their folder memberships.
 * No Room/SQLite dependency: everything is stored in two JSON arrays.
 *
 * "groups_json"   → List<GroupEntity>
 * "members_json"  → List<GroupMemberEntity>
 */
class GroupStore(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("group_store", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_GROUPS  = "groups_json"
        private const val KEY_MEMBERS = "members_json"
        private const val KEY_NEXT_ID = "next_group_id"
    }

    // ── Auto-increment ID ──────────────────────────────────────────────

    private fun nextId(): Long {
        val id = prefs.getLong(KEY_NEXT_ID, 1L)
        prefs.edit().putLong(KEY_NEXT_ID, id + 1).apply()
        return id
    }

    // ── Groups ─────────────────────────────────────────────────────────

    private fun loadGroups(): MutableList<GroupEntity> {
        val arr = JSONArray(prefs.getString(KEY_GROUPS, "[]") ?: "[]")
        val list = mutableListOf<GroupEntity>()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            list.add(
                GroupEntity(
                    groupId       = o.getLong("groupId"),
                    name          = o.getString("name"),
                    parentGroupId = if (o.isNull("parentGroupId")) null else o.getLong("parentGroupId"),
                    createdAt     = o.getLong("createdAt")
                )
            )
        }
        return list
    }

    private fun saveGroups(groups: List<GroupEntity>) {
        val arr = JSONArray()
        for (g in groups) {
            arr.put(JSONObject().apply {
                put("groupId",       g.groupId)
                put("name",          g.name)
                put("parentGroupId", g.parentGroupId ?: JSONObject.NULL)
                put("createdAt",     g.createdAt)
            })
        }
        prefs.edit().putString(KEY_GROUPS, arr.toString()).apply()
    }

    fun insertGroup(group: GroupEntity): Long {
        val groups = loadGroups()
        val id = nextId()
        groups.add(group.copy(groupId = id))
        saveGroups(groups)
        return id
    }

    fun getGroupById(id: Long): GroupEntity? = loadGroups().find { it.groupId == id }

    fun getRootGroups(): List<GroupEntity> =
        loadGroups().filter { it.parentGroupId == null }.sortedBy { it.createdAt }

    fun getChildGroups(parentId: Long): List<GroupEntity> =
        loadGroups().filter { it.parentGroupId == parentId }.sortedBy { it.createdAt }

    fun getAllGroups(): List<GroupEntity> = loadGroups()

    fun renameGroup(id: Long, name: String) {
        val groups = loadGroups()
        val idx = groups.indexOfFirst { it.groupId == id }
        if (idx >= 0) {
            groups[idx] = groups[idx].copy(name = name)
            saveGroups(groups)
        }
    }

    fun moveGroup(id: Long, newParentId: Long?) {
        val groups = loadGroups()
        val idx = groups.indexOfFirst { it.groupId == id }
        if (idx >= 0) {
            groups[idx] = groups[idx].copy(parentGroupId = newParentId)
            saveGroups(groups)
        }
    }

    fun deleteGroup(groupId: Long) {
        val groups = loadGroups()
        groups.removeAll { it.groupId == groupId }
        saveGroups(groups)
    }

    // ── Members ────────────────────────────────────────────────────────

    private fun loadMembers(): MutableList<GroupMemberEntity> {
        val arr = JSONArray(prefs.getString(KEY_MEMBERS, "[]") ?: "[]")
        val list = mutableListOf<GroupMemberEntity>()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            list.add(
                GroupMemberEntity(
                    folderBucketId = o.getInt("folderBucketId"),
                    groupId        = o.getLong("groupId")
                )
            )
        }
        return list
    }

    private fun saveMembers(members: List<GroupMemberEntity>) {
        val arr = JSONArray()
        for (m in members) {
            arr.put(JSONObject().apply {
                put("folderBucketId", m.folderBucketId)
                put("groupId",        m.groupId)
            })
        }
        prefs.edit().putString(KEY_MEMBERS, arr.toString()).apply()
    }

    /** UPSERT: removes any existing row for each bucketId before inserting. */
    fun insertMembers(members: List<GroupMemberEntity>) {
        val all = loadMembers()
        for (m in members) {
            all.removeAll { it.folderBucketId == m.folderBucketId }
            all.add(m)
        }
        saveMembers(all)
    }

    fun removeMemberByBucketId(bucketId: Int) {
        val all = loadMembers()
        all.removeAll { it.folderBucketId == bucketId }
        saveMembers(all)
    }

    fun getBucketIdsForGroup(groupId: Long): List<Int> =
        loadMembers().filter { it.groupId == groupId }.map { it.folderBucketId }

    fun deleteAllMembersOfGroup(groupId: Long) {
        val all = loadMembers()
        all.removeAll { it.groupId == groupId }
        saveMembers(all)
    }

    fun getAllMembers(): List<GroupMemberEntity> = loadMembers()
}

