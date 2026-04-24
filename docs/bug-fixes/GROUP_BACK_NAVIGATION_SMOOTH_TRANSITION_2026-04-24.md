# Group Back Navigation Smooth Transition Fix
**Date:** April 24, 2026  
**Issue:** Janky/non-smooth transition when pressing back button from nested groups  
**Scope:** Both `image-library` and `video-library`

## Problem

When navigating BACK from a nested group (using back button or app back), the transition was not smooth. There was a brief flash of empty content before the parent group's data appeared, creating a jarring visual experience.

### Root Cause

The `closeGroup()` function was updating the state with **empty lists first**, then loading data asynchronously via `refreshCurrentGroup()`:

```kotlin
// OLD CODE (both libraries)
fun closeGroup() {
    val s = _uiState.value
    if (s.groupStack.isNotEmpty()) {
        val (prevId, prevName) = s.groupStack.last()
        val parentSort = preferences.getGroupSortOption(prevId)
        
        // ❌ Update state with empty lists FIRST
        _uiState.update {
            it.copy(
                currentGroupId = prevId,
                currentGroupName = prevName,
                groupStack = s.groupStack.dropLast(1),
                currentGroupFolders = emptyList(),      // ← Empty!
                currentGroupSubGroups = emptyList(),    // ← Empty!
                currentGroupSortOption = parentSort
            )
        }
        
        // Then load data asynchronously
        refreshCurrentGroup()  // ← Data loads AFTER state update
    }
}
```

This caused the following sequence:
1. User presses back button
2. `closeGroup()` called
3. State updated with **empty lists** for folders/subgroups
4. UI re-renders showing **empty content** (brief flash)
5. `refreshCurrentGroup()` starts loading data asynchronously
6. Data finally loads and replaces the empty state
7. **Result:** Visible flash/jump during transition

## Solution

**Load data FIRST, then update state with the loaded data** — mirroring the approach used in `openGroup()`.

### Implementation

**Files Changed:**
- `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

```kotlin
// NEW CODE (both libraries)
fun closeGroup() {
    val s = _uiState.value
    if (s.groupStack.isNotEmpty()) {
        val (prevId, prevName) = s.groupStack.last()
        val parentSort = preferences.getGroupSortOption(prevId)
        
        // ✅ Load data FIRST before updating state
        viewModelScope.launch {
            val bucketIds = groupRepository.getFolderBucketIdsForGroup(prevId)
            val allFolders = repository.getFoldersWithIndependentSort(
                sortOption = s.sortOption,
                getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
            )
            val bucketIdSet = bucketIds.toSet()
            val groupFolders = allFolders.filter { it.bucketId in bucketIdSet }
            
            val allGroups = groupRepository.getAllGroups()
            val groupSortOptions = allGroups.associate { it.groupId to preferences.getGroupSortOption(it.groupId).id }
            val groupCustomOrders = allGroups.associate { it.groupId to preferences.customGroupItemsOrder(it.groupId) }
            val allSubGroups = groupRepository.getChildGroups(
                parentGroupId = prevId,
                groupSortOptions = groupSortOptions,
                groupCustomOrders = groupCustomOrders
            )
            
            val visibleBucketSet = allFolders.map { it.bucketId }.toSet()
            val subGroups = allSubGroups.filter { sub ->
                sub.memberBucketIds.isEmpty() || sub.memberBucketIds.any { it in visibleBucketSet }
            }
            
            val groupSortOption = parentSort
            val orderedMixed = if (groupSortOption == SortOption.CUSTOM_ORDER) {
                GroupMixedOrderUtil.applyCustomGroupMixedOrder(prevId, subGroups, groupFolders, preferences)
            } else {
                sortMixedItems(subGroups + groupFolders, groupSortOption, s.groupsAlwaysOnTop)
            }

            // ✅ Update state with loaded data — no empty state flash
            _uiState.update {
                it.copy(
                    currentGroupId = prevId,
                    currentGroupName = prevName,
                    groupStack = s.groupStack.dropLast(1),
                    currentGroupFolders = groupFolders,               // ← Pre-loaded!
                    currentGroupSubGroups = subGroups,                // ← Pre-loaded!
                    currentGroupOrderedMixedItems = orderedMixed,     // ← Pre-loaded!
                    currentGroupSortOption = parentSort
                )
            }
        }
    } else {
        // Going back to root — OK to use empty lists
        _uiState.update {
            it.copy(
                currentGroupId = null,
                currentGroupName = "",
                currentGroupFolders = emptyList(),
                currentGroupSubGroups = emptyList(),
                currentGroupOrderedMixedItems = emptyList(),
                groupStack = emptyList(),
                currentGroupSortOption = SortOption.CUSTOM_ORDER
            )
        }
    }
}
```

### Key Changes

1. **Data loading moved INSIDE `closeGroup()`**: No longer calls `refreshCurrentGroup()` separately
2. **State update happens AFTER data loads**: Uses `viewModelScope.launch` to load data first
3. **Consistent with `openGroup()`**: Both forward and backward navigation now use the same pattern
4. **Applied to BOTH libraries**: Behavioral consistency maintained

## Benefits

1. **Smooth back navigation**: No flash of empty content during transitions
2. **Instant feel**: Data is ready when the UI shows it
3. **Better UX**: Navigation feels responsive and polished
4. **Consistent behavior**: Both forward (`openGroup`) and backward (`closeGroup`) navigation work identically
5. **Both libraries identical**: Image-library and video-library have matching smooth transitions

## Testing

**Test scenario:**
1. Create a root group with nested groups (3+ levels deep)
2. Navigate forward: Root → Group A → Group B → Group C
3. Press back button repeatedly to navigate back through the stack
4. Observe smooth transitions with no flashes or jumps

**Expected behavior:**
- ✅ Back navigation should be smooth and instant
- ✅ No brief flash of empty content
- ✅ Data appears immediately when transitioning
- ✅ Works identically in both image-library and video-library
- ✅ Works for both device back button and app back button

## Related Fixes

This builds upon the previous group navigation fix:
- **Previous fix** (April 24, 2026): Fixed forward navigation (clicking nested groups) by delaying scroll-to-top until data loads
- **This fix** (April 24, 2026): Fixed backward navigation (pressing back) by loading data before state update

Together, these two fixes ensure **all group navigation** (forward and backward) is smooth and responsive.

## Files Changed

- `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt` (closeGroup function)
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt` (closeGroup function)

