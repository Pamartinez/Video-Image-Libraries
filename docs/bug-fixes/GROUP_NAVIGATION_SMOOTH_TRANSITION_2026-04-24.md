# Group Navigation Smooth Transition Fix
**Date:** April 24, 2026  
**Issue:** Janky/non-smooth transition when clicking nested groups  
**Scope:** Both `image-library` and `video-library`

## Problem

When navigating from one group to a nested group (clicking a group inside another group), the transition was not smooth. The scroll would jump immediately to the top before the new group's data had loaded, creating a jarring visual experience.

### Root Cause

The scroll-to-top was triggered **immediately** when `currentGroupId` changed:

```kotlin
// OLD CODE (video-library & image-library)
LaunchedEffect(state.currentGroupId) { 
    groupGridState.scrollToItem(0) 
}
```

This caused the following sequence:
1. User clicks nested group
2. `currentGroupId` changes immediately in state
3. LaunchedEffect triggers **immediately** and scrolls to position 0
4. **OLD group's items are still displayed** (data loads asynchronously in ViewModel coroutine)
5. Brief flash/jump as old items are visible at scroll position 0
6. New items finally load and replace them

## Solution

**Delay the scroll-to-top until AFTER the new group data has loaded**, by triggering on `currentGroupOrderedMixedItems` instead of `currentGroupId`.

We track both the last group ID and last sort option to differentiate between:
- **Group navigation**: Scroll after new data arrives
- **Sort change**: Scroll after items are re-sorted

### Implementation

**Video-Library:** `VideoListScreen.kt`  
**Image-Library:** `ImageListScreen.kt`

```kotlin
// NEW CODE (both libraries)
// Track the last group ID to detect navigation (not just re-composition with same ID).
val lastGroupIdForScroll = remember { mutableStateOf<Long?>(state.currentGroupId) }
val lastGroupSortForScroll = remember { mutableStateOf<com.example.common.data.model.FolderSortOption>(state.currentGroupSortOption) }
LaunchedEffect(state.currentGroupOrderedMixedItems) {
    // Scroll to top when navigating to a different group (after new data arrives)
    if (state.currentGroupId != lastGroupIdForScroll.value) {
        lastGroupIdForScroll.value = state.currentGroupId
        groupGridState.scrollToItem(0)
    }
    // Also scroll to top when sort option changes (after items refresh)
    else if (state.currentGroupSortOption != lastGroupSortForScroll.value) {
        lastGroupSortForScroll.value = state.currentGroupSortOption
        groupGridState.scrollToItem(0)
    }
}
```

## Benefits

1. **Smooth transitions**: Scroll happens only after data is loaded and ready to display
2. **No visual flash**: Users don't see old content briefly at the top before it changes
3. **Better UX**: Navigation feels instant and responsive
4. **Consistent behavior**: Both libraries now have identical smooth navigation

## Testing

**Test scenario:**
1. Create a root group
2. Create nested groups inside it (3+ levels deep)
3. Navigate from root → nested group → deeper nested group
4. Observe smooth transitions with no jumps or flashes

**Expected behavior:**
- ✅ Transition should be smooth and instant
- ✅ No brief flash of old content at the top
- ✅ Scroll position correctly at top of new group
- ✅ Works identically in both image-library and video-library

## Files Changed

- `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`

## Related

- Behavioral Consistency Rule: Both libraries must behave identically
- Group navigation improvements
- Scroll state management

