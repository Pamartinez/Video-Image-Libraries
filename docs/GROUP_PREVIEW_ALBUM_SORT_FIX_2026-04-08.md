# Group Preview Not Refreshing When Album Sort Changes - Fix

**Date:** April 8, 2026  
**Scope:** `image-library`  
**Issue:** Group preview doesn't update when changing sort in one of the first 4 albums

---

## Problem

When inside a group, if you:
1. Open one of the first 4 albums (which provide the group's preview images)
2. Change the album's sort order
3. Go back to the group view

**Expected:** Group preview should update to show the new first image according to the new sort  
**Actual:** Group preview remains unchanged, showing the old first image

### Why This Matters

Groups display preview images from their first 4 albums. If an album's sort changes, its "first" image changes, so the group preview should update too. Without this, the preview becomes misleading.

---

## Root Cause Analysis

The issue was in **how per-album sort was being saved and how groups were refreshed**.

### Problem 1: No Dedicated Per-Album Sort Method

**Image-library** had only ONE method for changing image sort:
```kotlin
fun setImageSortOption(s: ImageSortOption) {
    preferences.imageSortOption = s  // ❌ Saves to GLOBAL sort only
    // ... refresh logic ...
    silentRefresh()
    refreshFolderImages()
}
```

This method was being used for BOTH:
- Global/root level sort (correct usage)
- Per-album sort (WRONG - doesn't save per-album!)

**Video-library** (which worked correctly) had TWO separate methods:
- `setVideoSortOption()` - for global sort
- `setFolderSortOption()` - for per-album sort

### Problem 2: No Group Refresh After Album Sort Change

Even if per-album sort was saved, the method didn't call `refreshCurrentGroup()` when inside a group. This meant:
- Album preview updated correctly ✅
- Group preview stayed stale ❌

---

## Solution

Created a dedicated `setFolderImageSortOption()` method that mirrors video-library's implementation:

### 1. New Method in ImageListViewModel

**File:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

```kotlin
/**
 * Change the sort for the currently open folder (independent of the main tab sort).
 * 
 * ⚠️ CRITICAL: Independent sort is ALWAYS enabled.
 * ALWAYS saves album-specific sort (per bucketId).
 * DO NOT add back any checks or toggles - independent sort is mandatory!
 * See docs/INDEPENDENT_SORT_ARCHITECTURE.md for details.
 */
fun setFolderImageSortOption(s: ImageSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId ?: return
    // Always save album-specific sort (independent sort is now always enabled)
    preferences.saveFolderImageSortOption(bucketId, s)
    // Sort existing folder images in-memory immediately so that both
    // imageSortOption and folderImages change in the same recomposition frame.
    // This prevents LazyVerticalGrid's stable keys from re-scrolling when
    // the async data arrives later.
    val sorted = sortImagesInMemory(_uiState.value.folderImages, s)
    _uiState.update {
        it.copy(
            imageSortOption = s,
            folderImages = sorted,
            folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
        )
    }
    // Refresh the folder images from MediaStore to ensure consistency
    refreshFolderImages()
    // Refresh album preview images on the Folders tab to reflect the new sort
    viewModelScope.launch {
        silentRefresh()
    }
    // If we're inside a group, refresh the group view to update album preview
    if (_uiState.value.currentGroupId != null) {
        refreshCurrentGroup()  // ← THIS IS THE KEY FIX!
    }
    scheduleAutoBackup()
}
```

**Key features:**
1. ✅ **Saves per-album sort** using `preferences.saveFolderImageSortOption(bucketId, s)`
2. ✅ **Refreshes all folders** to update album previews everywhere
3. ✅ **Refreshes current group** if inside one (updates group preview)
4. ✅ **Matches video-library** behavior exactly

### 2. Updated UI to Call Correct Method

**File:** `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`

```kotlin
if (state.showSortDialog) {
    SortDialog(
        options = ImageSortOption.entries,
        labelFor = { it.label },
        currentOption = state.imageSortOption,
        onOptionSelected = { option ->
            // If inside an album, save per-album sort; otherwise save global sort
            if (state.currentFolderBucketId != null) {
                viewModel.setFolderImageSortOption(option)  // ← Per-album sort
            } else {
                viewModel.setImageSortOption(option)  // ← Global sort
            }
        },
        onDismiss = { viewModel.dismissSortDialog() }
    )
}
```

**Logic:**
- Inside an album (`currentFolderBucketId != null`)? → Call `setFolderImageSortOption`
- On root/all images view? → Call `setImageSortOption`

---

## How It Works

### Before (Broken):
```
User changes album sort
    ↓
setImageSortOption() called
    ↓
Saves to preferences.imageSortOption (GLOBAL sort) ❌
    ↓
Refreshes folder images ✅
    ↓
Refreshes all folders ✅
    ↓
Does NOT refresh group ❌
    ↓
Result:
- Album preview updates ✅
- Group preview stays stale ❌
- Per-album sort NOT saved ❌
```

### After (Fixed):
```
User changes album sort inside an album
    ↓
setFolderImageSortOption() called
    ↓
Saves to preferences.saveFolderImageSortOption(bucketId, sort) ✅
    ↓
Refreshes folder images ✅
    ↓
Refreshes all folders (updates album previews) ✅
    ↓
Checks if inside a group → YES ✅
    ↓
Calls refreshCurrentGroup() ✅
    ↓
Result:
- Album preview updates ✅
- Group preview updates ✅
- Per-album sort saved ✅
```

---

## Testing

To verify the fix:

### Test Case 1: Basic Group Preview Refresh
1. Create a group with 4+ albums
2. Enter the group
3. Open the first album
4. Change sort (e.g., from "Date Modified DESC" to "Name A-Z")
5. Go back to group view
6. **Expected:** Group preview should show the new first image alphabetically ✅

### Test Case 2: Multiple Album Sorts
1. Inside a group with albums A, B, C, D
2. Open album A, change sort to "Name A-Z"
3. Back to group → Preview should update ✅
4. Open album B, change sort to "Date Modified DESC"
5. Back to group → Preview should update ✅
6. Each album maintains its own sort ✅

### Test Case 3: Per-Album Sort Persistence
1. Change sort in album A to "Name Z-A"
2. Close album, reopen it
3. **Expected:** Album A should still be sorted "Name Z-A" ✅
4. Open album B
5. **Expected:** Album B has its own independent sort ✅

### Test Case 4: Root vs Album Sort
1. On root view, change sort to "Date Modified DESC"
2. Open album A (has its own sort "Name A-Z")
3. **Expected:** Album A shows "Name A-Z" sort, not root sort ✅
4. Close album A, back to root
5. **Expected:** Root view shows "Date Modified DESC" ✅

---

## Related Work

This fix builds upon:

1. **INDEPENDENT_SORT_ALWAYS_ENABLED_2026-04-08.md**
   - Made per-album sort always enabled
   - This fix ensures it's properly saved and group previews update

2. **ALBUM_PREVIEW_INCORRECT_SORT_FIX_2026-04-08.md**
   - Fixed album previews to respect per-album sort
   - Combined with this fix for complete preview correctness

3. **INDEPENDENT_SORT_ARCHITECTURE.md**
   - Architecture documentation for independent sort system
   - Explains the separation of concerns

---

## Consistency with Video-Library

✅ **Image-library now matches video-library exactly:**

**Video-library has:**
- `setVideoSortOption()` - global video sort
- `setFolderSortOption()` - per-album video sort (with group refresh)

**Image-library now has:**
- `setImageSortOption()` - global image sort
- `setFolderImageSortOption()` - per-album image sort (with group refresh)

Both now:
- ✅ Save per-album sort correctly
- ✅ Refresh group previews when inside a group
- ✅ Call `refreshCurrentGroup()` after album sort changes
- ✅ Have identical behavior for sort changes

---

## Summary

✅ **Added** `setFolderImageSortOption()` method to ImageListViewModel  
✅ **Updated** sort dialog to call correct method (per-album vs global)  
✅ **Fixed** group preview refresh when album sort changes  
✅ **Fixed** per-album sort being saved correctly  
✅ **Matched** video-library implementation exactly  

Group previews now update correctly when changing album sort! 🎯

