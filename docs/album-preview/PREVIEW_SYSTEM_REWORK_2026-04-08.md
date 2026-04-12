# Preview System Rework - April 8, 2026

## Overview
Completely reworked the preview generation system to ensure each album and group generates its preview using its **own specific sort order**, not the global/root sort order.

## Problem Statement
Previously, when you changed the sort order in one album (e.g., to A-Z), ALL albums would update their previews using that same A-Z sort, causing incorrect thumbnails across the entire library. This violated the principle that each album should maintain its own independent sort order and preview.

## Solution Architecture

### 1. Album Preview Generation
**Each album generates its preview from THE FIRST media item according to its OWN sort order.**

#### Implementation:
- **File**: `ImageRepository.kt` and `VideoRepository.kt`
- **Method**: `getFoldersWithIndependentSort()`
- **New Helper**: `getFirstImageForAlbum()` / `getFirstVideoForAlbum()`

**How it works:**
1. Load all media items from MediaStore (no specific sort)
2. Group items by bucket ID (album)
3. For each album:
   - Get the album's specific sort option from preferences
   - Use `getFirstImageForAlbum()` to efficiently find the first item according to that album's sort
   - Set that item's URI as the album's `latestItemUri` (preview thumbnail)

**Key improvement:**
Instead of sorting the entire list (expensive), we use optimized methods:
- `minByOrNull()` for ascending sorts (A-Z, oldest first, etc.)
- `maxByOrNull()` for descending sorts (Z-A, newest first, etc.)
- `maxWithOrNull(comparator)` for multi-field sorts (date + ID)

### 2. Group Preview Generation
**Each group generates its preview from the FIRST 4 ALBUMS after applying the group's own sort order.**

#### Implementation:
- **File**: `common/data/util/PreviewGenerator.kt` (new shared utility)
- **Method**: `generateGroupPreview()`
- **Used by**: `GroupRepository.buildGroupItem()`

**How it works:**
1. Get the group's specific sort option and custom order
2. Build ordered list of items (sub-groups + member folders) according to that group's sort
3. Extract the first 4 FOLDERS (skip sub-groups) from the sorted list
4. Use their `latestItemUri` as the group's preview URIs (4-thumbnail grid)
5. For child groups, recursively apply the same logic using each child's own sort

**Supported sort options:**
- `CUSTOM_ORDER` (0): User-defined drag-to-reorder
- `NAME_A_TO_Z` (1): Alphabetical ascending
- `NAME_Z_TO_A` (2): Alphabetical descending
- `ITEMS_MOST_FIRST` (3): By item count descending
- `ITEMS_FEWEST_FIRST` (4): By item count ascending

### 3. Shared Preview Logic
**Created `PreviewGenerator` utility in the `common` module to ensure consistent preview generation across both libraries.**

#### Benefits:
- ✅ Single source of truth for group preview logic
- ✅ Identical behavior in `image-library` and `video-library`
- ✅ Reusable for any future preview needs
- ✅ Recursively handles nested groups correctly

## Files Modified

### Common Module
1. **`common/data/util/PreviewGenerator.kt`** (NEW)
   - Shared preview generation logic
   - `generateGroupPreview()` method
   - `buildOrderedGroupItems()` helper

2. **`common/data/repository/GroupRepository.kt`**
   - Updated `buildGroupItem()` to use `PreviewGenerator`
   - Removed inline preview generation logic (now delegated)
   - Added recursive child group preview support

### Image Library
3. **`image-library/data/repository/ImageRepository.kt`**
   - Updated `getFoldersWithIndependentSort()` documentation
   - Replaced `sortImages()` with `getFirstImageForAlbum()` (more efficient)
   - Fixed `when` expressions to properly return sorted lists

4. **`image-library/ui/viewmodel/ImageListViewModel.kt`** ⚠️ CRITICAL FIX
   - Changed `loadDataCore()` to call `getFoldersWithIndependentSort()` instead of `getFolders()`
   - This was the root cause - it was using the global sort for ALL album previews
   - Now passes `getFolderSortOption` lambda to respect each album's independent sort

### Video Library
5. **`video-library/data/repository/VideoRepository.kt`**
   - Updated `getFoldersWithIndependentSort()` documentation
   - Replaced `sortVideos()` with `getFirstVideoForAlbum()` (more efficient)
   - Fixed `when` expressions to properly return sorted lists

**Note:** Video library ViewModel was already correctly using `getFoldersWithIndependentSort()` ✅

## Technical Details

### Album Preview Efficiency
**Before (inefficient):**
```kotlin
val sortedImages = sortImages(images, albumSort)  // Sorts entire list
val previewImage = sortedImages.firstOrNull()     // Takes first item
```

**After (optimized):**
```kotlin
val previewImage = getFirstImageForAlbum(images, albumSort)  // Directly finds first item

private fun getFirstImageForAlbum(images: List<ImageItem>, sortOption: ImageSortOption): ImageItem? {
    return when (sortOption) {
        ImageSortOption.NAME_A_TO_Z -> images.minByOrNull { it.displayName.lowercase() }  // O(n)
        ImageSortOption.NAME_Z_TO_A -> images.maxByOrNull { it.displayName.lowercase() }  // O(n)
        // ... other sort options
    }
}
```

**Performance gain:** O(n log n) → O(n) per album

### Group Preview Architecture
```
GroupRepository.buildGroupItem()
    ↓
PreviewGenerator.generateGroupPreview()
    ↓
1. Get group's sort option & custom order
2. Build ordered item list (groups + folders)
3. Filter to folders only
4. Take first 4 folders
5. Extract their preview URIs
    ↓
Return List<Uri> (up to 4 thumbnails)
```

## Testing Checklist
- [x] Build succeeds without errors
- [ ] Album previews update correctly when changing album sort
- [ ] Group previews respect group sort order
- [ ] Nested group previews work correctly
- [ ] Both `image-library` and `video-library` behave identically
- [ ] Performance is acceptable (no lag when switching sorts)

## Breaking Changes
None - this is a pure implementation improvement. The API remains the same.

## Next Steps
1. Test in both apps with various sort combinations
2. Verify album previews update correctly when sort changes
3. Verify group previews show correct thumbnails in correct order
4. Test nested groups with different sort options
5. Performance test with large libraries (1000+ items)

## References
- Independent Sort Architecture: `docs/INDEPENDENT_SORT_ARCHITECTURE.md`
- Group Sort Order: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md`
- Copilot Instructions: `.github/copilot-instructions.md` (Common-First Rule, Preview Rules)



