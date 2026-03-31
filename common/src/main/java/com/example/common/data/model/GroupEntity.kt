package com.example.common.data.model

/**
 * Represents a virtual group persisted in SharedPreferences JSON.
 */
data class GroupEntity(
    val groupId: Long = 0,
    val name: String,
    val parentGroupId: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

