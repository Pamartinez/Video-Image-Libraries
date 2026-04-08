# Album Preview in Group Sort Fix

**Date:** April 8, 2026  
**Scope:** Both `image-library` and `video-library`

## Issue

When a user is inside a group and views one of the first 4 albums (displayed in the group's preview), changing that album's sort order would correctly update the album's own preview image, but the group view would not refresh to show the updated preview. The album preview in the group remained showing the old first image according to the previous sort order.

## Root Cause

When the user changes an album's sort order via `setImageSortOption()` (image-library) or `setFolderSortOption()` (video-library), the functions were calling:
- `silentRefresh()` - to refresh the main Folders tab
- `refreshFolderImages()` / `refreshFolderVideos()` - to refresh the album's own content

However, they were NOT calling `refreshCurrentGroup()` when the user was inside a group (`currentGroupId != null`). This meant the group view's cached state of albums was not updated with the new preview images.

## Solution

Added a check in both libraries after changing the album sort:

```kotlin
// If we're inside a group, refresh the group view to update album preview
if (_uiState.value.currentGroupId != null) {
    refreshCurrentGroup()
}
```

This ensures that when an album's sort changes while viewing it from inside a group, the group view is refreshed to fetch the updated album previews (which now show the first image according to the new sort order).

## Implementation Details

### Image Library

**File:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

**Part 1 - Call refreshCurrentGroup():**  
**Function:** `setImageSortOption()`  
**Lines:** 938-941

**Part 2 - Force fresh folder fetch:**  
**Function:** `refreshCurrentGroup()`  
**Line:** 1724 - Changed from using cached `s.folders` to always calling `repository.getFolders()`

### Video Library

**File:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

**Part 1 - Call refreshCurrentGroup():**  
**Function:** `setFolderSortOption()`  
**Lines:** 1317-1320

**Part 2 - Force fresh folder fetch:**  
**Function:** `refreshCurrentGroup()`  
**Lines:** 900-910 - Changed from using cached `s.folders` to always calling `repository.getFoldersWithIndependentSort()`

## How `refreshCurrentGroup()` Works

The `refreshCurrentGroup()` function:
1. Fetches the current group's album list from the repository
2. The repository queries MediaStore with the current sort options
3. MediaStore returns albums with their first images (previews) calculated according to the current sort
4. The UI state is updated with the refreshed album list, which now has the correct preview images

## Testing

To verify the fix:
1. Create or open a group with multiple albums
2. Open one of the first 4 albums (visible in the group preview)
3. Change the album's sort order (e.g., from Custom Order to Name A-Z)
4. Go back to the group view
5. ✅ The album's preview in the group should now show the first image according to the new sort order

## Behavioral Consistency

This fix was applied to **BOTH** libraries simultaneously to maintain behavioral consistency:
- ✅ Image library: Updates group preview when album sort changes
- ✅ Video library: Updates group preview when album sort changes
- ✅ Identical behavior and user experience in both apps


