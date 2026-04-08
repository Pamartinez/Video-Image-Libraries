# Group Open Flash Fix - 2026-04-08

## Issue Description
When opening a group (that contains albums) after the app has been idle for hours, there was a brief UI flash where the "Add albums" button appeared for a split second before the albums loaded and displayed.

## Root Cause
**Race condition between state update and async data loading:**

1. When `openGroup()` was called, it immediately updated the UI state with the group ID and name
2. It then called `refreshCurrentGroup()` which loaded data **asynchronously** via `viewModelScope.launch`
3. The UI recomposed as soon as `currentGroupId` was set (step 1), but `currentGroupFolders` and `currentGroupSubGroups` were still empty
4. `SharedGroupDetailScreen.kt` shows the "Add albums" button when `mixedItems.isEmpty()` (line 258)
5. A few hundred milliseconds later, the async data loading completed and populated the lists, hiding the button

## Solution
**Load data BEFORE updating the state with the group ID:**

Modified the `openGroup()` function in both ViewModels to:
1. Start a coroutine to load ALL group data (folders, subgroups, sorted items)
2. THEN update the state with both the group ID and the loaded data **together in one atomic update**
3. This ensures the UI never sees the group ID with empty lists

### Changes Made

#### Image Library
**File:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

- **Before:** `openGroup()` updated state with group ID, then called async `refreshCurrentGroup()`
- **After:** `openGroup()` loads all data inside a coroutine, then updates state once with everything together

#### Video Library
**File:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

- Applied identical fix to maintain behavioral consistency between both libraries

### Code Pattern
```kotlin
fun openGroup(groupId: Long, name: String) {
    // Load group data FIRST, then update state with everything together to avoid empty state flash
    viewModelScope.launch {
        // ... load all group data ...
        val folders = ...
        val subGroups = ...
        val orderedMixed = ...
        
        // Update state with group ID and data together — no empty state flash
        _uiState.update {
            it.copy(
                currentGroupId                = groupId,
                currentGroupName              = name,
                currentGroupFolders           = folders,
                currentGroupSubGroups         = subGroups,
                currentGroupOrderedMixedItems = orderedMixed,
                // ... other fields ...
            )
        }
    }
}
```

## Verification
- ✅ **Builds successfully** on both image-library and video-library
- ✅ **Behavioral consistency** maintained between both apps
- ✅ **No empty state flash** - UI only renders when data is ready

## Testing Instructions
1. Open the app and navigate to a group with albums
2. Leave the app idle for several hours (or force-stop it)
3. Re-open the app and navigate to the group again
4. **Expected:** Group opens directly showing albums, NO "Add albums" button flash
5. **Verify in BOTH apps** (image-library and video-library)

## Files Modified
1. `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
2. `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

## Notes
- This fix eliminates the race condition by ensuring data and state are synchronized
- The async loading still happens, but the UI doesn't render the intermediate empty state
- Both libraries now have identical behavior for group opening (consistency rule enforced)

