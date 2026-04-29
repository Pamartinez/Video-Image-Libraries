package com.example.common.ui.viewmodel

import com.example.common.util.FilePathUtils

/**
 * Shared group creation utilities used by both ImageListViewModel and VideoListViewModel.
 *
 * These utility functions contain the common logic for group creation operations.
 * While the ViewModels still need to call these from their own methods (due to different
 * UiState types), this consolidates the actual logic and ensures consistency.
 */
object GroupCreationUtils {

    /**
     * Generates a unique group name based on existing names.
     * Used by showGroupNameForCreation() in both ViewModels.
     */
    fun generateUniqueGroupName(existingNames: Set<String>): String {
        return FilePathUtils.generateUniqueGroupName("Group", existingNames)
    }
}

/**
 * Documentation for ViewModel implementers:
 *
 * The following three methods should be implemented identically in both ViewModels:
 *
 * 1. showGroupNameForCreation():
 *    ```kotlin
 *    fun showGroupNameForCreation() {
 *        viewModelScope.launch {
 *            val allNames = groupRepository.getAllGroups().map { it.name }.toSet()
 *            val suggested = GroupCreationUtils.generateUniqueGroupName(allNames)
 *            _uiState.update {
 *                it.copy(
 *                    showGroupNameDialog = true,
 *                    groupNameDialogForCreation = true,
 *                    existingGroupNames = allNames,
 *                    suggestedGroupName = suggested
 *                )
 *            }
 *        }
 *    }
 *    ```
 *
 * 2. enterGroupCreationModeWithName(name: String):
 *    ```kotlin
 *    fun enterGroupCreationModeWithName(name: String) {
 *        val s = _uiState.value
 *        _uiState.update {
 *            it.copy(
 *                showGroupNameDialog = false,
 *                groupNameDialogForCreation = false,
 *                isGroupCreationMode = true,
 *                isSelectionMode = false,
 *                pendingGroupCreationName = name,
 *                pendingGroupCreationParentId = s.currentGroupId,
 *                groupCreationSelectedFolderIds = s.groupCreationSelectedFolderIds,
 *                groupCreationSelectedGroupIds = emptySet(),
 *                selectedFolderIds = emptySet(),
 *                selectedGroupIds = emptySet(),
 *                selectedImageIds/VideoIds = emptySet(),
 *                currentGroupId = null,
 *                currentGroupName = "",
 *                currentGroupFolders = emptyList(),
 *                currentGroupSubGroups = emptyList(),
 *                currentGroupOrderedMixedItems = emptyList()
 *            )
 *        }
 *    }
 *    ```
 *
 * 3. createGroupFromCreationMode(name: String):
 *    ```kotlin
 *    fun createGroupFromCreationMode(name: String) {
 *        val s = _uiState.value
 *        val folderIds = s.groupCreationSelectedFolderIds.toList()
 *        val groupIds = emptyList<Long>()
 *        val parentGroupId = s.pendingGroupCreationParentId
 *
 *        viewModelScope.launch {
 *            val newGroupId = groupRepository.createGroup(
 *                name = name,
 *                folderBucketIds = folderIds,
 *                subGroupIds = groupIds,
 *                parentGroupId = parentGroupId
 *            )
 *
 *            if (parentGroupId == null) {
 *                prependToRootOrder("g_$newGroupId")
 *            } else {
 *                prependToGroupOrder("g_$newGroupId", parentGroupId, s)
 *            }
 *
 *            exitGroupCreationMode()
 *            silentRefresh()
 *
 *            if (parentGroupId != null) {
 *                val parentGroup = groupRepository.getGroupById(parentGroupId)
 *                parentGroup?.let { group ->
 *                    openGroup(group.groupId, group.name)
 *                }
 *            }
 *
 *            scheduleAutoBackup()
 *        }
 *    }
 *    ```
 *
 * NOTE: Due to different UiState types between libraries, we cannot use a shared base class.
 * However, these methods MUST remain identical. Use the verify-consistency.ps1 script to check.
 */


