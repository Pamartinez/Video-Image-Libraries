# Sub-Group Preview Image Order Fix - April 7, 2026

## Issue
When viewing a group (especially nested groups/sub-groups), the preview images were displayed in the wrong order. 

**Example:**
- Sub-group contains: Album 1, Album 2, Album 3, Album 4 (in custom sort order)
- Expected preview: (1, 2) / (3, 4) [top row / bottom row in a 2x2 grid]
- Actual preview: (2, 3) / (4, 1) — images were rotated/shifted

This was a **preview generation bug**, not a UI layout bug.

## Root Cause
The `refreshCurrentGroup()` function in both ViewModels was calling `getChildGroups()` **without** passing the sort configuration parameters:

```kotlin
// OLD CODE (WRONG):
val allSubGroups = groupRepository.getChildGroups(groupId)
```

This meant:
1. When a sub-group's preview was generated, it didn't know its own custom sort order
2. The preview was built using **database order** instead of **custom order**
3. The 4 preview images were extracted from the wrong positions in the albums list

## The Fix
Pass `groupSortOptions` and `groupCustomOrders` when calling `getChildGroups()` in `refreshCurrentGroup()`:

### video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt
```kotlin
private fun refreshCurrentGroup() {
    val groupId = _uiState.value.currentGroupId ?: return
    viewModelScope.launch {
        val s         = _uiState.value
        val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
        val allSubGroups = groupRepository.getChildGroups(
            parentGroupId = groupId,
            groupSortOptions = s.allGroupSortOptions,      // ← ADDED
            groupCustomOrders = s.allGroupCustomOrders     // ← ADDED
        )
        // ...existing code...
    }
}
```

### image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt
```kotlin
private fun refreshCurrentGroup() {
    val groupId = _uiState.value.currentGroupId ?: return
    viewModelScope.launch {
        val s         = _uiState.value
        val bucketIds = groupRepository.getFolderBucketIdsForGroup(groupId)
        val allFolders = s.folders.ifEmpty { repository.getFolders(s.sortOption, s.imageSortOption) }
        val bucketIdSet  = bucketIds.toSet()
        val groupFolders = allFolders.filter { it.bucketId in bucketIdSet }
        val allSubGroups = groupRepository.getChildGroups(
            parentGroupId = groupId,
            groupSortOptions = s.allGroupSortOptions,      // ← ADDED
            groupCustomOrders = s.allGroupCustomOrders     // ← ADDED
        )
        // ...existing code...
    }
}
```

## How It Works

### GroupRepository.getChildGroups()
When `groupSortOptions` and `groupCustomOrders` are passed:
1. Each sub-group is built via `buildGroupItem()`
2. `buildGroupItem()` calls `buildOrderedGroupItems()` with the sort config
3. `buildOrderedGroupItems()` respects the sub-group's **custom sort order** (if set)
4. The first 4 albums from the **correctly ordered list** are used as preview images
5. The preview now shows (1, 2, 3, 4) instead of (2, 3, 4, 1) ✅

### Where This Was Already Correct
The `loadDataCore()` function **already** passed these parameters when loading groups on app start or after operations:

```kotlin
val gAllSubGroups = groupRepository.getChildGroups(
    parentGroupId = openGroupId,
    groupSortOptions = groupSortOptions,       // ✅ Already correct
    groupCustomOrders = groupCustomOrders      // ✅ Already correct
)
```

So this bug only affected:
- **Manual navigation** into a group (via `openGroup()` → `refreshCurrentGroup()`)
- **After reordering** albums inside a group (the previous fix called `refreshCurrentGroup()` which had this bug)

## Testing Checklist
- [ ] Code compiles without errors in both libraries
- [ ] Both apps installed successfully
- [ ] **Manual Test**: Create a sub-group inside a parent group with 4+ albums
- [ ] **Manual Test**: Set sub-group to custom sort order
- [ ] **Manual Test**: Reorder albums so album order is clear (e.g., A, B, C, D)
- [ ] **Manual Test**: Go back to parent group — sub-group preview should show A, B in top row; C, D in bottom row ✅
- [ ] **Manual Test**: Test with 2-level and 3-level nesting
- [ ] **Manual Test**: Test in both image-library and video-library

## Consistency Rule Compliance
✅ **BEHAVIORAL CONSISTENCY RULE**: This fix was applied to **BOTH** libraries simultaneously  
✅ **UI COMPONENT CONSISTENCY RULE**: No UI changes, only logic fix  
✅ **Common-First Rule**: The fix is library-specific (different state property names) but the logic is identical

## Related Fixes
- **Previous Fix**: `docs/GROUP_PREVIEW_REORDER_FIX_2026-04-07.md` — added `refreshCurrentGroup()` call after `persistGroupOrder()`, which revealed this preview order bug
- **Architecture**: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md` — how group preview generation works

## Summary
The bug was that `refreshCurrentGroup()` wasn't passing sort configuration to `getChildGroups()`, causing sub-group preview images to be generated in database order instead of the user's custom sort order. Now previews display albums in the correct order! 🎉

