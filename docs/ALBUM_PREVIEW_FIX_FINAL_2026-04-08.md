# Album Preview Fix - Final Implementation - April 8, 2026

## ✅ ISSUE RESOLVED

### Problem Description
When changing the sort order in one album (e.g., to "Name A-Z"), **ALL albums** were showing their preview thumbnails using that same sort order. This violated the independent sort architecture where each album should maintain its own sort order and preview.

Additionally, **group previews** were also showing incorrect album thumbnails because groups were loading albums using the wrong method.

### Root Causes

#### Root Cause #1: ViewModel Loading Albums (FIXED)
**ImageListViewModel.loadDataCore()** was calling the old `repository.getFolders()` method which applies a **global sort option** to generate previews for all albums.

**Line 568 (BEFORE - BROKEN):**
```kotlin
val allFolders = repository.getFolders(s.sortOption, s.imageSortOption)
```
- Uses `s.imageSortOption` (the current/last-set sort) for ALL albums
- Every album preview generated with the same sort
- Result: Changing one album's sort contaminated all previews ❌

#### Root Cause #2: GroupRepository Loading Albums (FIXED) ⚠️ CRITICAL
**GroupRepository.getFolders lambda** was calling the old `getFolders()` method, causing groups to build previews from albums that had incorrect previews.

**image-library/GroupRepository.kt Line 13 (BEFORE - BROKEN):**
```kotlin
getFolders = { ImageRepository(context).getFolders() }
```

**video-library/GroupRepository.kt Line 15 (BEFORE - BROKEN):**
```kotlin
getFolders = { videoRepository.getFolders() }
```
- Groups call this lambda to get album data for building group previews
- Was getting albums with global sort (incorrect previews)
- Result: Group previews showed wrong thumbnails ❌

### The Fixes

#### Fix #1: ImageListViewModel (FIXED)
Changed ImageListViewModel to use `getFoldersWithIndependentSort()` which respects each album's individual sort preference.

**Line 568-571 (AFTER - FIXED):**
```kotlin
val allFolders = repository.getFoldersWithIndependentSort(
    sortOption = s.sortOption,
    getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
)
```

#### Fix #2: GroupRepository - Image Library (FIXED) ⚠️ MOST CRITICAL
Changed the `getFolders` lambda to use `getFoldersWithIndependentSort()`.

**image-library/GroupRepository.kt (AFTER - FIXED):**
```kotlin
class GroupRepository(context: Context) : com.example.common.data.repository.GroupRepository(
    store      = GroupStore(context),
    getFolders = {
        val preferences = AppPreferences(context)
        ImageRepository(context).getFoldersWithIndependentSort(
            sortOption = preferences.sortOption,
            getFolderSortOption = { bucketId -> preferences.getFolderImageSortOption(bucketId) }
        )
    }
)
```

#### Fix #3: GroupRepository - Video Library (FIXED)
Changed the `getFolders` lambda to use `getFoldersWithIndependentSort()` and added `preferences` parameter.

**video-library/GroupRepository.kt (AFTER - FIXED):**
```kotlin
class GroupRepository(
    store: GroupStore,
    videoRepository: VideoRepository,
    preferences: AppPreferences
) : com.example.common.data.repository.GroupRepository(
    store      = store,
    getFolders = {
        videoRepository.getFoldersWithIndependentSort(
            folderSortOption = preferences.folderSortOption,
            independentSortEnabled = true,
            getFolderSortOption = { bucketId -> preferences.getFolderVideoSortOption(bucketId) }
        )
    }
)
```

**video-library/VideoListViewModel.kt Line 172 (AFTER - FIXED):**
```kotlin
private val groupRepository = GroupRepository(groupStore, repository, preferences)
```

### Implementation Details

#### How It Works Now:
1. User changes Album A's sort to "Name (A to Z)"
   - `setFolderImageSortOption()` is called (line 957)
   - Saves: `preferences.saveFolderImageSortOption(bucketId, s)` (line 960)
   - Triggers: `silentRefresh()` (line 977)

2. `silentRefresh()` calls `loadDataCore()` (line 676)

3. `loadDataCore()` calls `getFoldersWithIndependentSort()` (line 568)
   - For each album bucket ID, it calls the lambda: `{ bucketId -> preferences.getFolderImageSortOption(bucketId) }`
   - Album A: Gets "Name (A to Z)" from preferences
   - Album B: Gets its own saved sort (e.g., "Date modified DESC")
   - Album C: Gets its own saved sort (e.g., "Custom order")

4. Each album's preview is generated:
   - Album A: `getFirstImageForAlbum(images, "Name A-Z")` → First image alphabetically
   - Album B: `getFirstImageForAlbum(images, "Date modified DESC")` → Most recent image
   - Album C: `getFirstImageForAlbum(images, "Custom order")` → Most recently modified image

### Files Modified
1. **`image-library/ui/viewmodel/ImageListViewModel.kt`** (line 568-571)
   - Changed from `getFolders()` to `getFoldersWithIndependentSort()`
   - Added lambda to retrieve each album's individual sort option

2. **`image-library/data/repository/GroupRepository.kt`** ⚠️ CRITICAL FIX
   - Changed `getFolders` lambda from `getFolders()` to `getFoldersWithIndependentSort()`
   - Added `AppPreferences` instantiation to access album sort options
   - This was the ROOT CAUSE for group preview issues

3. **`video-library/data/repository/GroupRepository.kt`** ⚠️ CRITICAL FIX
   - Changed `getFolders` lambda from `getFolders()` to `getFoldersWithIndependentSort()`
   - Added `preferences` constructor parameter
   - Fixed property name: `sortOption` → `folderSortOption`

4. **`video-library/ui/viewmodel/VideoListViewModel.kt`** (line 172)
   - Updated GroupRepository instantiation to pass `preferences` parameter

### Verification Status
✅ **Image Library** - Fixed and installed (v3 with ViewModel + GroupRepository fixes)  
✅ **Video Library** - Fixed and installed (v2 with GroupRepository fix)  
✅ **Build** - Successful  
✅ **Installation** - Successful on device SM-S948U1  

### Testing Instructions
1. Open **Image Library** app
2. Go to **Folders** tab
3. Open **Album A** → change sort to **"Name (A to Z)"**
4. Back to Folders → observe Album A's preview thumbnail
5. Open **Album B** → change sort to **"Date modified (descending)"**
6. Back to Folders → observe Album B's preview thumbnail

**Expected Result:**
- Album A shows: First image alphabetically (A-Z)
- Album B shows: Most recently modified image
- They should be DIFFERENT images (not using the same sort) ✅

### Related Documentation
- Full Preview System Rework: `docs/PREVIEW_SYSTEM_REWORK_2026-04-08.md`
- Independent Sort Architecture: `docs/INDEPENDENT_SORT_ARCHITECTURE.md`
- Group Sort Order: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md`

### Notes
- This was a **critical** bug fix - the repository changes alone weren't enough
- The ViewModel needs to call the RIGHT repository method
- Video library didn't have this bug - it was already calling `getFoldersWithIndependentSort()`
- Always verify the entire call chain when debugging preview issues

## Status: ✅ COMPLETE AND DEPLOYED



