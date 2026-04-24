# Video Library Sorting Fix - April 14, 2026

## Problem

The video library had a critical bug where album-specific sort options were never being applied. All albums were using the global Videos tab sort option, even when users selected different sort options (like "Duration") for specific albums.

### Symptoms:
- Sort by duration appeared broken inside albums
- All other per-album sort options (Name, Date created, etc.) didn't work
- Album preview thumbnails showed the wrong video (based on global sort instead of album-specific sort)
- Album sorts kept reverting to the global Videos tab sort

## Root Cause

**File:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`  
**Lines:** 2092-2095

The `getEffectiveFolderSortOption()` method had a TODO comment and was returning the wrong value:

```kotlin
// BEFORE (BROKEN):
private fun getEffectiveFolderSortOption(bucketId: Int): VideoSortOption {
    // TODO: Implement per-album sort options in AppPreferences
    return preferences.videoSortOption  // ❌ Always returns global sort
}
```

This method is called by:
1. `openFolder()` - When opening an album (line 1423)
2. `getFoldersWithIndependentSort()` in VideoRepository - For album preview generation (line 565)
3. `refreshFolderVideos()` - When refreshing album content (line 687)

## The Fix

**Changed lines 2092-2095 to:**

```kotlin
// AFTER (FIXED):
private fun getEffectiveFolderSortOption(bucketId: Int): VideoSortOption {
    // Load this album's specific sort option (independent sort is always enabled)
    return preferences.getFolderVideoSortOption(bucketId)
}
```

## Why This Was Missed

The AppPreferences class **already had** the correct methods implemented:
- `getFolderVideoSortOption(bucketId)` - lines 52-62
- `saveFolderVideoSortOption(bucketId, sortOption)` - lines 64-77

The save method was being called correctly (line 1282 in `setFolderSortOption`), but the load method was never being used.

This is a case where the infrastructure was complete, but one connection point was left as a TODO.

## Impact

This single-line fix resolves:
- ✅ Album-specific sort options now work correctly
- ✅ Duration sorting works properly inside albums
- ✅ All sort options (Name, Date created, Date modified) work per-album
- ✅ Album preview thumbnails show the correct first video based on that album's sort
- ✅ Sort preferences persist correctly per album
- ✅ Video library now matches image library behavior (which was already correct)

## Testing

After the fix:
1. Open any album in video-library
2. Change sort to "Duration (ascending)" or "Duration (descending)"
3. Verify videos are sorted by duration
4. Close and reopen the album - sort is preserved
5. Check album thumbnail on Folders tab - shows correct video based on album's sort
6. Verify other albums can have different sort options independently

## Technical Details

### Duration Sorting Implementation
Duration sorting was always correctly implemented at the SQL level:

```kotlin
// VideoRepository.kt lines 524-525
VideoSortOption.DURATION_ASC  -> "${MediaStore.Video.Media.DURATION} ASC, ${MediaStore.Video.Media._ID} ASC"
VideoSortOption.DURATION_DESC -> "${MediaStore.Video.Media.DURATION} DESC, ${MediaStore.Video.Media._ID} DESC"
```

The issue was that the correct sort option was never being passed to the query because `getEffectiveFolderSortOption` always returned the global sort.

### Related Code Locations

**VideoListViewModel.kt:**
- Line 1282: `preferences.saveFolderVideoSortOption(bucketId, s)` - Saving works ✅
- Line 1423: `val folderSort = getEffectiveFolderSortOption(bucketId)` - Now loads correctly ✅
- Line 2092-2095: `getEffectiveFolderSortOption()` - **FIXED** ✅

**AppPreferences.kt:**
- Lines 52-62: `getFolderVideoSortOption()` - Implementation exists ✅
- Lines 64-77: `saveFolderVideoSortOption()` - Implementation exists ✅

## Files Modified
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt` (1 method, 2 lines changed)

## Build Info
- Build: Successful
- Installed: video-library-debug.apk
- Device: SM-S948U1 - 16
- Date: April 14, 2026

