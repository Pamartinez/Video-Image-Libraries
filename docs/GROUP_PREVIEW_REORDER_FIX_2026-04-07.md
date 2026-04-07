# Group Preview Image Regeneration Fix - April 7, 2026

## Issue
When reordering albums within a group using custom sort mode, if an album is moved to one of the first 4 positions (which determines the group's preview images), the preview image for that group is not regenerated.

**UPDATE**: This also affected **nested groups** (sub-groups inside groups) - when reordering albums inside a sub-group, the sub-group's preview image wasn't updating in the parent group's view.

## Root Cause
The `persistGroupOrder()` function in both `ImageListViewModel` and `VideoListViewModel` was saving the new custom order to preferences but **not** triggering a refresh of the root group list. 

For nested groups, the issue was compounded: while `silentRefresh()` reloads root groups, it doesn't refresh the **current group** view, so sub-group preview images weren't being regenerated based on the new order.

Group preview images are generated in `GroupRepository.buildGroupItem()` based on the first 4 **folders** (albums) in the group's sorted order. When the order changes but the groups aren't reloaded, the preview stays stale.

## Solution
Added TWO refresh calls to `persistGroupOrder()` in both libraries:

1. **`silentRefresh()`** - Reloads root groups to regenerate top-level group preview images
2. **`refreshCurrentGroup()`** - Refreshes the current group view to regenerate sub-group preview images (for nested groups)

This ensures that:
1. The new custom order is saved to preferences
2. Auto-backup is triggered (if enabled)
3. **Root groups are reloaded** with `loadDataCore()`
4. **Current group is refreshed** to update sub-group previews
5. **Group preview images are regenerated** based on the updated order at ALL levels

### Files Modified

#### video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt
```kotlin
fun persistGroupOrder() {
    val s = _uiState.value
    val groupId = s.currentGroupId ?: return
    val order = s.currentGroupOrderedMixedItems.map {
        if (it is GroupItem) "g_${it.groupId}" else "f_${(it as FolderItem).bucketId}"
    }
    preferences.saveGroupMixedOrder(groupId, order)
    scheduleAutoBackup()
    // Refresh root groups to regenerate preview images based on the new order
    silentRefresh()
    // Also refresh current group to update sub-group previews (for nested groups)
    refreshCurrentGroup()  // ← ADDED
}
```

#### image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt
```kotlin
fun persistGroupOrder() {
    val state   = _uiState.value
    val groupId = state.currentGroupId ?: return
    preferences.setCustomGroupItemsOrder(
        groupId,
        state.currentGroupOrderedMixedItems.mapNotNull { item ->
            when (item) {
                is GroupItem  -> "g_${item.groupId}"
                is FolderItem -> "f_${item.bucketId}"
                else          -> null
            }
        }
    )
    scheduleAutoBackup()
    // Refresh root groups to regenerate preview images based on the new order
    silentRefresh()
    // Also refresh current group to update sub-group previews (for nested groups)
    refreshCurrentGroup()  // ← ADDED
}
```

## How Group Previews Work

### Preview Generation (in GroupRepository.buildGroupItem())
1. Load the group's member bucket IDs and child groups
2. Build an ordered list of items using `buildOrderedGroupItems()`, which respects:
   - Custom order (if sort mode is CUSTOM_ORDER)
   - Name sort (A-Z or Z-A)
   - Item count sort (most/fewest first)
3. Extract the first 4 **folders** (skip groups) from the ordered list
4. Use their `latestItemUri` as preview images
5. If fewer than 4 folder previews are available, recursively get previews from child groups

### When Previews Are Regenerated
- On app launch (`loadDataCore()`)
- When root groups are refreshed (`silentRefresh()`)
- **When current group is refreshed** (`refreshCurrentGroup()`) - updates sub-group previews
- **NOW: When group item order is persisted** (`persistGroupOrder()`)

## How the Fix Works for Nested Groups

### Scenario: Three-level nesting
```
Root
  └─ Parent Group (preview should update when Sub-Group albums are reordered)
      └─ Sub-Group (you're here, reordering albums)
          └─ Album 1, 2, 3, 4, 5...
```

### Execution Flow After Reordering:
1. **`persistGroupOrder()`** saves the new order for Sub-Group
2. **`silentRefresh()`** reloads ALL root groups
   - Parent Group is rebuilt via `buildGroupItem()`
   - Sub-Group is rebuilt as part of Parent Group's children
   - Sub-Group's preview is regenerated based on the new custom order
3. **`refreshCurrentGroup()`** refreshes the current Parent Group view
   - Calls `getChildGroups()` which rebuilds Sub-Group **again** with updated previews
   - Updates UI state with the fresh sub-group data
4. When you go back to Parent Group, Sub-Group's preview shows the reordered albums! ✅

## Testing Checklist
- [x] Code compiles without errors in both libraries
- [x] Both apps installed successfully
- [ ] **Manual Test**: Open a nested sub-group with custom sort enabled
- [ ] **Manual Test**: Move an album from position 5+ to position 1 in the sub-group
- [ ] **Manual Test**: Go back to parent group - sub-group preview should update
- [ ] **Manual Test**: Test with 2-level and 3-level nesting
- [ ] **Manual Test**: Test in both image-library and video-library

## Consistency Rule Compliance
✅ **BEHAVIORAL CONSISTENCY RULE**: This fix was applied to **BOTH** libraries simultaneously  
✅ **UI COMPONENT CONSISTENCY RULE**: No UI changes, only logic fix  
✅ **Common-First Rule**: The fix is library-specific (different function names in preferences) but the logic is identical

## Related Architecture
- **Group Sort Order**: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md`
- **Previous Group Fix**: `docs/GROUP_PREVIEW_SORT_FIX_2026-04-06.md`

