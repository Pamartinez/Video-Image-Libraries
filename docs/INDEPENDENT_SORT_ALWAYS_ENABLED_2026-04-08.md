# Independent Sort Always Enabled

**Date:** April 8, 2026  
**Scope:** Both `image-library` and `video-library`

## Overview

Removed the optional "independent sort" setting and made sorting ALWAYS independent at every level:
- **Each album** has its own sort order (saved per-album)
- **Each group** has its own sort order (already implemented)
- **Root level** has its own sort order

This means users can now:
- Set "Name A-Z" in one album, "Date Modified" in another, and "Custom Order" in a third
- Set a different sort for each group
- Have a different sort at the root folders view
- All sort orders are independent and persistent

## Changes Made

### Video Library

#### 1. ViewModel Changes
**File:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

- **`setFolderSortOption()`** (lines ~1298-1304):
  - Removed `if (preferences.independentSortEnabled)` check
  - Now ALWAYS saves album-specific sort: `preferences.saveFolderVideoSortOption(bucketId, s)`
  
- **`getEffectiveFolderSortOption()`** (lines ~1962-1964):
  - Removed `if (preferences.independentSortEnabled)` check
  - Now ALWAYS returns album-specific sort: `preferences.getFolderVideoSortOption(bucketId)`

- **All `repository.getFoldersWithIndependentSort()` calls**:
  - Changed `independentSortEnabled = s.independentSortEnabled` to `independentSortEnabled = true`
  - Locations: `showHideFoldersScreen()`, `showHideFoldersScreenInsideGroup()`, `silentRefresh()`, `refreshCurrentGroup()`

### Image Library

#### 1. AppPreferences Changes
**File:** `image-library/src/main/java/com/imagelibrary/data/preferences/AppPreferences.kt`

Added per-album sort storage (matching video-library):
- Added constant: `KEY_FOLDER_IMAGE_SORT_OPTIONS`
- Added method: `getFolderImageSortOption(bucketId: Int): ImageSortOption`
- Added method: `saveFolderImageSortOption(bucketId: Int, sortOption: ImageSortOption)`
- Added method: `getAllFolderImageSortOptions(): Map<Int, Int>` (for backup)
- Added method: `restoreAllFolderImageSortOptions(options: Map<Int, Int>)` (for restore)

#### 2. ViewModel Changes
**File:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

- **`setImageSortOption()`** (lines ~922-935):
  - Added check: if inside an album (`currentFolderBucketId != null`), save album-specific sort
  - If at root level, save global sort
  ```kotlin
  if (bucketId != null) {
      preferences.saveFolderImageSortOption(bucketId, s)
  } else {
      preferences.imageSortOption = s
  }
  ```

- **`openFolder()`** (lines ~1009-1024):
  - Now loads album-specific sort when opening: `val albumSort = preferences.getFolderImageSortOption(bucketId)`
  - Sets UI state to use album's sort: `imageSortOption = albumSort`
  - Fetches images with album's sort: `repository.getImages(albumSort, bucketId)`

- **`closeFolder()`** (lines ~1026-1033):
  - Now restores root-level sort when closing album: `imageSortOption = preferences.imageSortOption`

## How It Works

### When Opening an Album
1. User taps on an album
2. `openFolder()` loads that album's specific sort option from preferences
3. UI updates to show the album with its own sort
4. User sees images sorted according to that album's preferences

### When Changing Sort Inside an Album
1. User changes sort (e.g., to "Name A-Z")
2. `setImageSortOption()` saves the sort specifically for this album
3. Images are re-sorted in-memory immediately
4. Repository fetches fresh data
5. If inside a group, group view is refreshed to update album preview

### When Closing an Album
1. User backs out of album
2. `closeFolder()` restores the root-level sort option
3. UI returns to root view with root's sort order

### Storage Format
Per-album sorts are stored as: `"bucketId1:sortId1,bucketId2:sortId2,...""`
- Example: `"123:1,456:3,789:0"` means album 123 uses sort ID 1, album 456 uses sort ID 3, etc.
- Limited to last 200 albums to prevent unlimited growth

## User Experience

### Before (Optional Independent Sort)
- User had to enable "Independent Sort" in settings
- If disabled, changing sort in one album affected ALL albums
- Confusing and unexpected behavior

### After (Always Independent Sort)
- Every album automatically has its own sort
- Changing sort in one album ONLY affects that album
- Intuitive and predictable behavior
- No settings toggle needed

## Testing

To verify the changes:

1. **Test Per-Album Sort**:
   - Open Album A, set sort to "Name A-Z"
   - Open Album B, set sort to "Date Modified"
   - Go back to Album A - should still be "Name A-Z" ✅
   - Go back to Album B - should still be "Date Modified" ✅

2. **Test Root Sort Independence**:
   - Set root folders view to "Items Most First"
   - Open an album, change to "Custom Order"
   - Go back to root - should still be "Items Most First" ✅

3. **Test Group Sort Independence**:
   - Open a group, set group sort to "Name Z-A"
   - Open an album in that group, set to "Date Created"
   - Go back to group - should still be "Name Z-A" ✅
   - Open different album - should have its own sort ✅

4. **Test Album Preview Updates**:
   - Inside a group with multiple albums
   - Open an album, change its sort
   - Go back to group
   - Album preview should reflect new sort (shows first image according to new sort order) ✅

## Removed Settings

The following settings/state are now obsolete (always true):
- `independentSortEnabled` preference (video-library)
- `independentSortEnabled` UI state (both libraries)
- "Independent Sort" toggle in settings (both libraries)

**Note:** The preferences/state still exist for backward compatibility but are now ignored - the behavior is always as if `independentSortEnabled = true`.

## Behavioral Consistency ✅

This change maintains behavioral consistency between both libraries:
- ✅ Image library: Each album has independent sort
- ✅ Video library: Each album has independent sort
- ✅ Both: Groups have independent sort
- ✅ Both: Root level has independent sort
- ✅ Identical behavior and user experience

## Related Fixes

This change builds upon the earlier fix from today:
- **ALBUM_PREVIEW_IN_GROUP_SORT_FIX_2026-04-08.md**: Ensures album previews in groups update when album sort changes
- That fix is now even more important since every album can have a different sort

