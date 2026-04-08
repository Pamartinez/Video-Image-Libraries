# Independent Sort Architecture - CRITICAL SYSTEM

**⚠️ WARNING: This document describes a CRITICAL feature. Breaking this will cause major UX issues.**

**Last Updated:** April 8, 2026  
**Status:** MANDATORY - Independent sort is ALWAYS enabled

---

## 🚨 CRITICAL RULE

**INDEPENDENT SORT IS ALWAYS ENABLED AND CANNOT BE OPTIONAL**

- Each album MUST have its own independent sort order
- Each group MUST have its own independent sort order  
- Root level MUST have its own independent sort order
- These are NEVER synchronized or shared

**DO NOT:**
- ❌ Add back any "independent sort enabled" toggle
- ❌ Make album sort changes affect other albums
- ❌ Make group sort changes affect other groups
- ❌ Share sort state between albums/groups/root

---

## System Architecture

### Three Independent Sort Levels

```
Root Level
├── Sort Option: preferences.imageSortOption / videoSortOption
├── Applies to: Folders tab main view
└── Saved in: SharedPreferences (global)

Album Level (Per-Album)
├── Sort Option: preferences.getFolderImageSortOption(bucketId) / getFolderVideoSortOption(bucketId)
├── Applies to: Inside each specific album
└── Saved in: SharedPreferences (per bucketId)

Group Level (Per-Group)
├── Sort Option: preferences.getGroupSortOption(groupId)
├── Applies to: Inside each specific group
└── Saved in: SharedPreferences (per groupId)
```

### Data Flow

#### Opening an Album
```kotlin
User taps album
    ↓
openFolder(bucketId, name)
    ↓
Load album-specific sort: getFolderImageSortOption(bucketId)
    ↓
Update UI state with album's sort
    ↓
Fetch images using album's sort
    ↓
Display images in album's sort order
```

#### Changing Sort in Album
```kotlin
User changes sort
    ↓
setImageSortOption(newSort)
    ↓
Check: Are we inside an album? (currentFolderBucketId != null)
    ↓
YES: Save to album-specific → saveFolderImageSortOption(bucketId, newSort)
NO:  Save to root-level → preferences.imageSortOption = newSort
    ↓
Re-sort images in memory immediately
    ↓
Fetch fresh data from repository
    ↓
If inside group: refreshCurrentGroup() to update album preview
```

#### Closing an Album
```kotlin
User backs out
    ↓
closeFolder()
    ↓
Restore root-level sort: imageSortOption = preferences.imageSortOption
    ↓
Clear album state
    ↓
Return to root view with root's sort
```

---

## Implementation Details

### Image Library

#### AppPreferences.kt
```kotlin
// CRITICAL: Per-album sort storage
private const val KEY_FOLDER_IMAGE_SORT_OPTIONS = "folder_image_sort_options"

// Retrieves album-specific sort option
fun getFolderImageSortOption(bucketId: Int): ImageSortOption

// Saves album-specific sort option
fun saveFolderImageSortOption(bucketId: Int, sortOption: ImageSortOption)
```

**Storage Format:** `"bucketId1:sortId1,bucketId2:sortId2,..."`
- Example: `"123:1,456:3"` = album 123 uses sort 1, album 456 uses sort 3

#### ImageListViewModel.kt

**CRITICAL FUNCTION #1: setImageSortOption()**
```kotlin
fun setImageSortOption(s: ImageSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId
    if (bucketId != null) {
        // ⚠️ CRITICAL: Save album-specific sort
        preferences.saveFolderImageSortOption(bucketId, s)
    } else {
        // ⚠️ CRITICAL: Save root-level sort
        preferences.imageSortOption = s
    }
    // ... rest of function
}
```

**CRITICAL FUNCTION #2: openFolder()**
```kotlin
fun openFolder(bucketId: Int, name: String) {
    // ⚠️ CRITICAL: Load album-specific sort
    val albumSort = preferences.getFolderImageSortOption(bucketId)
    _uiState.update {
        it.copy(
            currentFolderBucketId = bucketId,
            imageSortOption = albumSort, // ⚠️ CRITICAL: Set to album's sort
            // ...
        )
    }
    // Fetch images with album's sort
    repository.getImages(albumSort, bucketId = bucketId)
}
```

**CRITICAL FUNCTION #3: closeFolder()**
```kotlin
fun closeFolder() {
    _uiState.update {
        it.copy(
            currentFolderBucketId = null,
            imageSortOption = preferences.imageSortOption // ⚠️ CRITICAL: Restore root sort
        )
    }
}
```

### Video Library

#### AppPreferences.kt
```kotlin
// CRITICAL: Per-album sort storage
private const val KEY_FOLDER_VIDEO_SORT_OPTIONS = "folder_video_sort_options"

// Retrieves album-specific sort option
fun getFolderVideoSortOption(bucketId: Int): VideoSortOption

// Saves album-specific sort option  
fun saveFolderVideoSortOption(bucketId: Int, sortOption: VideoSortOption)
```

#### VideoListViewModel.kt

**CRITICAL FUNCTION #1: setFolderSortOption()**
```kotlin
fun setFolderSortOption(s: VideoSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId ?: return
    // ⚠️ CRITICAL: ALWAYS save album-specific sort (independent sort always enabled)
    preferences.saveFolderVideoSortOption(bucketId, s)
    // ... rest of function
}
```

**CRITICAL FUNCTION #2: getEffectiveFolderSortOption()**
```kotlin
private fun getEffectiveFolderSortOption(bucketId: Int): VideoSortOption {
    // ⚠️ CRITICAL: ALWAYS return album-specific sort
    return preferences.getFolderVideoSortOption(bucketId)
}
```

**CRITICAL: All repository calls**
```kotlin
// ⚠️ CRITICAL: ALWAYS pass independentSortEnabled = true
repository.getFoldersWithIndependentSort(
    folderSortOption = s.sortOption,
    independentSortEnabled = true, // ⚠️ MUST be true!
    getFolderSortOption = { bucketId -> getEffectiveFolderSortOption(bucketId) }
)
```

---

## Common Mistakes to Avoid

### ❌ WRONG: Sharing sort between albums
```kotlin
// DON'T DO THIS!
fun setImageSortOption(s: ImageSortOption) {
    preferences.imageSortOption = s  // This affects ALL albums!
}
```

### ✅ CORRECT: Album-specific sort
```kotlin
fun setImageSortOption(s: ImageSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId
    if (bucketId != null) {
        preferences.saveFolderImageSortOption(bucketId, s)  // Album-specific!
    } else {
        preferences.imageSortOption = s  // Root-level only
    }
}
```

### ❌ WRONG: Not loading album sort on open
```kotlin
// DON'T DO THIS!
fun openFolder(bucketId: Int, name: String) {
    // Missing: Load album's specific sort
    repository.getImages(_uiState.value.imageSortOption, bucketId)  // Uses wrong sort!
}
```

### ✅ CORRECT: Load album-specific sort
```kotlin
fun openFolder(bucketId: Int, name: String) {
    val albumSort = preferences.getFolderImageSortOption(bucketId)  // Load album's sort
    _uiState.update { it.copy(imageSortOption = albumSort) }
    repository.getImages(albumSort, bucketId)  // Use album's sort
}
```

### ❌ WRONG: Not restoring root sort on close
```kotlin
// DON'T DO THIS!
fun closeFolder() {
    _uiState.update { it.copy(currentFolderBucketId = null) }
    // Missing: Restore root sort!
}
```

### ✅ CORRECT: Restore root sort
```kotlin
fun closeFolder() {
    _uiState.update {
        it.copy(
            currentFolderBucketId = null,
            imageSortOption = preferences.imageSortOption  // Restore root sort
        )
    }
}
```

---

## Testing Checklist

After any changes to sort logic, ALWAYS test:

- [ ] Open Album A, set "Name A-Z", close, reopen → Still "Name A-Z"
- [ ] Open Album A (Name A-Z), open Album B, set "Date Modified", return to Album A → Still "Name A-Z"
- [ ] Set root to "Items Most", open album, change to "Custom", return to root → Root still "Items Most"
- [ ] Inside group, open album, change sort, return to group → Album preview updates
- [ ] Open album, change sort, kill app, restart, reopen album → Sort persisted
- [ ] Change sort in 10 different albums → Each remembers its own sort

---

## Debugging

### Check if album sort is being saved
```kotlin
Log.d("Sort", "Saving album $bucketId sort: ${sortOption.name}")
preferences.saveFolderImageSortOption(bucketId, sortOption)
```

### Check if album sort is being loaded
```kotlin
val albumSort = preferences.getFolderImageSortOption(bucketId)
Log.d("Sort", "Album $bucketId loaded sort: ${albumSort.name}")
```

### Check SharedPreferences directly
```kotlin
val raw = prefs.getString("folder_image_sort_options", "")
Log.d("Sort", "Raw album sorts: $raw")
// Should see: "123:1,456:3,..."
```

---

## History

### April 8, 2026 - Independent Sort Made Mandatory
- Removed optional `independentSortEnabled` setting
- Made per-album sort ALWAYS enabled in both libraries
- Image library: Added per-album sort infrastructure (was missing)
- Video library: Removed checks, always use per-album sort

### Previous Issues (Before April 8, 2026)
- Independent sort was optional, could be disabled
- When disabled, changing sort in one album affected all albums
- Caused confusion and user complaints
- Broke multiple times when refactoring

**Why It Kept Breaking:**
- Optional feature meant more code paths
- Easy to accidentally use global sort instead of per-album sort
- Not obvious which sort should be used in each context
- Tests didn't catch all edge cases

**Solution:**
- Made it mandatory (no toggle)
- Simpler code = less breakage
- Clear rules: always use per-album sort when inside album
- This document to prevent future breakage

---

## Related Documentation

- **ALBUM_PREVIEW_IN_GROUP_SORT_FIX_2026-04-08.md** - Album preview refresh when sort changes
- **INDEPENDENT_SORT_ALWAYS_ENABLED_2026-04-08.md** - Implementation details

---

## Contact

If you're modifying sort logic and have questions, refer to this document first.
If something is unclear, update this document - don't let it become outdated!

**Remember:** Independent sort is NOT optional. Breaking it affects every user.

