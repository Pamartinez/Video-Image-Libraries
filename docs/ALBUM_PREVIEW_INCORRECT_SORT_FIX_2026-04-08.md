# Album Preview Incorrect Sort Fix

**Date:** April 8, 2026  
**Scope:** `image-library`  
**Issue:** Album previews not respecting per-album sort

---

## Problem

After implementing independent per-album sort, some albums were showing incorrect preview images. The preview image shown for each album on the folders tab didn't match the album's actual sort order.

### Example
- Album A has sort set to "Name A-Z"  
- Album preview showed an image from the middle (using default sort)
- Should show the first image alphabetically

### Root Cause

The `ImageRepository.getFolders()` method used a **single global sort option** for generating ALL album previews:

```kotlin
suspend fun getFolders(
    sortOption: SortOption = SortOption.CUSTOM_ORDER,
    imageSortOption: ImageSortOption = ImageSortOption.CUSTOM_ORDER  // ❌ One sort for all!
): List<FolderItem>
```

This meant:
1. All albums used the same sort for preview generation
2. Individual album sort preferences were ignored
3. Changing sort in Album A didn't update its preview
4. Preview didn't match what you see when opening the album

---

## Solution

Added `getFoldersWithIndependentSort()` method (similar to video-library) that:
1. Loads ALL images from MediaStore
2. Groups them by bucketId (album)
3. **For each album**, gets its specific sort option
4. Sorts that album's images according to ITS sort
5. Picks the first image as the preview
6. Returns folders with correct per-album previews

### New Method

**File:** `image-library/src/main/java/com/imagelibrary/data/repository/ImageRepository.kt`

```kotlin
suspend fun getFoldersWithIndependentSort(
    sortOption: SortOption = SortOption.CUSTOM_ORDER,
    getFolderSortOption: (Int) -> ImageSortOption = { ImageSortOption.CUSTOM_ORDER }
): List<FolderItem>
```

**Key features:**
- Takes a **lambda** that returns each album's sort option by bucketId
- Processes all albums independently
- Each album gets its own sorted preview

### Updated Calls

Changed all `repository.getFolders()` calls to `repository.getFoldersWithIndependentSort()`:

1. **`silentRefresh()`** - Main data refresh
2. **`showHideFoldersScreen()`** - Hide folders screen  
3. **`showHideFoldersScreenInsideGroup()`** - Hide screen in group
4. **`refreshCurrentGroup()`** - Group view refresh

Each call now passes the album sort getter:
```kotlin
repository.getFoldersWithIndependentSort(
    sortOption = s.sortOption,
    getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
)
```

---

## How It Works

### Old Behavior (Broken)
```
MediaStore Query
    ↓
Sort ALL images by global sort (e.g., "Date Modified")
    ↓
For each album: take first image as preview
    ↓
Result: All previews based on same sort ❌
```

### New Behavior (Fixed)
```
MediaStore Query  
    ↓
Group images by bucketId (album)
    ↓
For each album:
  ├─ Get album's specific sort (e.g., "Name A-Z")
  ├─ Sort that album's images
  └─ Take first image as preview
    ↓
Result: Each preview matches its album's sort ✅
```

---

## Implementation Details

### Helper Method Added

**File:** `ImageRepository.kt`

```kotlin
private fun sortImages(images: List<ImageItem>, option: ImageSortOption): List<ImageItem> {
    return when (option) {
        ImageSortOption.CUSTOM_ORDER -> images.sortedWith(...)
        ImageSortOption.NAME_A_TO_Z -> images.sortedBy { it.displayName.lowercase() }
        ImageSortOption.NAME_Z_TO_A -> images.sortedByDescending { it.displayName.lowercase() }
        ImageSortOption.DATE_CREATED_ASC -> images.sortedBy { it.id }
        ImageSortOption.DATE_CREATED_DESC -> images.sortedByDescending { it.id }
        ImageSortOption.DATE_MODIFIED_ASC -> images.sortedBy { it.dateModified }
        ImageSortOption.DATE_MODIFIED_DESC -> images.sortedByDescending { it.dateModified }
    }
}
```

This helper sorts a list of images according to any ImageSortOption.

### Performance Note

The new method loads ALL images at once (no sort parameter in query), then sorts in memory. This is necessary because each album needs a different sort.

**Performance impact:** Minimal
- MediaStore query is still fast (no sort applied)
- Grouping and sorting in memory is efficient
- Only done when folders list needs refresh

---

## Testing

To verify the fix:

1. **Set different sorts in different albums**:
   - Album A: "Name A-Z"
   - Album B: "Date Modified DESC"
   - Album C: "Custom Order"

2. **Check previews on folders tab**:
   - Album A preview should be first alphabetically ✅
   - Album B preview should be most recently modified ✅
   - Album C preview should follow custom order ✅

3. **Change sort and verify preview updates**:
   - Open Album A
   - Change sort to "Date Modified DESC"
   - Back to folders tab
   - Album A preview should now be most recent ✅

4. **Verify in groups**:
   - Inside a group with albums
   - Albums should show correct previews ✅

---

## Related Fixes

This builds upon other independent sort work:

1. **INDEPENDENT_SORT_ALWAYS_ENABLED_2026-04-08.md**
   - Made per-album sort always enabled
   - This fix ensures previews match that behavior

2. **ALBUM_PREVIEW_IN_GROUP_SORT_FIX_2026-04-08.md**
   - Fixed group preview refresh when album sort changes
   - Combined with this fix for complete preview correctness

3. **INDEPENDENT_SORT_ARCHITECTURE.md**
   - Architecture documentation
   - Explains why per-album previews are critical

---

## Video Library

Video library already had `getFoldersWithIndependentSort()` implemented correctly. This fix brings image-library to parity with video-library.

Both libraries now:
- ✅ Have per-album independent sort
- ✅ Generate previews respecting per-album sort
- ✅ Update previews when album sort changes
- ✅ Show consistent behavior

---

## Summary

✅ **Added** `getFoldersWithIndependentSort()` method to ImageRepository  
✅ **Updated** all folder fetch calls to use new method  
✅ **Added** `sortImages()` helper for in-memory sorting  
✅ **Fixed** album previews to respect per-album sort  
✅ **Matched** video-library behavior for consistency  

Album previews now correctly reflect each album's individual sort order! 🎯

