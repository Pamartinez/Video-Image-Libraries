# Independent View Type Implementation
**Date:** April 22, 2026  
**Scope:** Both `image-library` and `video-library`  
**Feature:** Per-album and per-group independent view types

---

## Overview

Implemented independent view type settings for each album and group, allowing users to have different view preferences (GRID_LARGE, GRID_SMALL, LIST) for different contexts, just like independent sort options.

This matches Samsung Gallery behavior where:
- **Each album** can have its own view type (grid vs list, grid size)
- **Each group** can have its own view type
- **Root level** has its own global view type
- View type is remembered when you navigate back to an album or group

---

## User Experience

### Before Implementation
- **One global folder view type** for ALL albums
- Changing view in one album changes it everywhere
- **One global group view type** for ALL groups
- No way to have different views for different contexts

### After Implementation
- **Each album remembers its own view type**
  - Pictures album → GRID_LARGE
  - Screenshots album → GRID_SMALL
  - Camera album → LIST
- **Each group remembers its own view type**
  - Travel group → GRID_LARGE
  - Work group → LIST
- **Root level** keeps its own view type separate from albums/groups
- Seamless: open album → sees its saved view → change view → saved automatically

---

## Implementation Details

### Architecture Pattern
Follows the **exact same pattern** as independent sort options:
- Per-folder settings: `"bucketId:viewTypeId,..."`
- Per-group settings: `"groupId:viewTypeId,..."`
- Falls back to global setting if no specific setting exists
- Limit to 200 most recent entries to prevent unbounded growth

### 1. AppPreferences Storage (image-library)
**File:** `image-library/src/main/java/com/imagelibrary/data/preferences/AppPreferences.kt`

Added methods:
- `getFolderViewType(bucketId: Int): ViewType` - Get album-specific view type
- `saveFolderViewType(bucketId: Int, viewType: ViewType)` - Save album-specific view type
- `getGroupViewType(groupId: Long): ViewType` - Get group-specific view type
- `saveGroupViewType(groupId: Long, viewType: ViewType)` - Save group-specific view type

Storage keys:
- `KEY_FOLDER_VIEW_TYPES = "folder_view_types"` - Album view types
- `KEY_GROUP_VIEW_TYPES = "group_view_types"` - Group view types

### 2. AppPreferences Storage (video-library)
**File:** `video-library/src/main/java/com/videolibrary/data/preferences/AppPreferences.kt`

**Identical implementation** as image-library:
- Same method signatures
- Same storage keys
- Same fallback logic
- Ensures **behavioral consistency** across both apps

### 3. ViewModel Integration (image-library)
**File:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

**openFolder()** - Load album-specific view type:
```kotlin
fun openFolder(bucketId: Int, name: String) {
    val albumSort = preferences.getFolderImageSortOption(bucketId)
    val albumViewType = preferences.getFolderViewType(bucketId)  // NEW
    _uiState.update {
        it.copy(
            currentFolderBucketId = bucketId,
            currentFolderName = name,
            imageSortOption = albumSort,
            folderViewType = albumViewType,  // Set album-specific view
            // ...
        )
    }
}
```

**closeFolder()** - Restore root view type:
```kotlin
fun closeFolder() {
    _uiState.update {
        it.copy(
            // ...
            folderViewType = preferences.folderViewType,  // Restore root view
        )
    }
}
```

**setFolderViewType()** - Save per-album or global:
```kotlin
fun setFolderViewType(v: ViewType) {
    val bucketId = _uiState.value.currentFolderBucketId
    if (bucketId != null) {
        preferences.saveFolderViewType(bucketId, v)  // Save per-album
    } else {
        preferences.folderViewType = v  // Save global
    }
    _uiState.update { it.copy(folderViewType = v) }
}
```

**openGroup()** - Load group-specific view type:
```kotlin
fun openGroup(groupId: Long, name: String) {
    val groupSort = preferences.getGroupSortOption(groupId)
    val groupViewType = preferences.getGroupViewType(groupId)  // NEW
    // ...
    _uiState.update {
        it.copy(
            currentGroupId = groupId,
            viewType = groupViewType,  // Set group-specific view
            // ...
        )
    }
}
```

**closeGroup()** - Restore parent or root view type:
```kotlin
fun closeGroup() {
    if (groupStack.isNotEmpty()) {
        val parentViewType = preferences.getGroupViewType(prevId)
        _uiState.update {
            it.copy(
                viewType = parentViewType,  // Restore parent group view
                // ...
            )
        }
    } else {
        _uiState.update {
            it.copy(
                viewType = preferences.viewType,  // Restore root view
                // ...
            )
        }
    }
}
```

**setViewType()** - Save per-group or global:
```kotlin
fun setViewType(v: ViewType) {
    val groupId = _uiState.value.currentGroupId
    if (groupId != null) {
        preferences.saveGroupViewType(groupId, v)  // Save per-group
    } else {
        preferences.viewType = v  // Save global
    }
    _uiState.update { it.copy(viewType = v) }
}
```

### 4. ViewModel Integration (video-library)
**File:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

**Identical implementation** as image-library:
- Same logic in `openFolder()`, `closeFolder()`, `setFolderViewType()`
- Same logic in `openGroup()`, `closeGroup()`, `setViewType()`
- Ensures **behavioral consistency** across both apps

---

## View Type Contexts

### Root Level (Folders Tab)
- **State:** `viewType` (groups + ungrouped albums)
- **Global setting:** `preferences.viewType`
- **Toggle with:** "View as" menu or floating button
- **Persisted:** Yes, as global default

### Inside Group
- **State:** `viewType` (sub-groups + member albums)
- **Per-group setting:** `preferences.getGroupViewType(groupId)`
- **Falls back to:** Global `viewType` if not set
- **Toggle with:** "View as" menu or floating button
- **Persisted:** Yes, per group

### Inside Album/Folder
- **State:** `folderViewType` (images/videos grid)
- **Per-album setting:** `preferences.getFolderViewType(bucketId)`
- **Falls back to:** Global `folderViewType` if not set
- **Toggle with:** "View as" menu or floating button
- **Persisted:** Yes, per album

---

## Behavioral Consistency

Both `image-library` and `video-library` implement this feature **identically**:

✅ Same storage mechanism (comma-separated key:value pairs)  
✅ Same method signatures in AppPreferences  
✅ Same ViewModel logic for load/save/restore  
✅ Same fallback behavior (per-item → global)  
✅ Same UX flow (change view → auto-saves)  
✅ Same backup integration (ready for BackupManager)  

The only differences are the available view types:
- **image-library**: GRID_LARGE ⟷ GRID_SMALL
- **video-library**: LIST ⟷ GRID_LARGE ⟷ GRID_SMALL

---

## Edge Cases Handled

1. **First time opening album/group** - Uses global view type as default
2. **Deleting and recreating album** - Old view type preserved if bucketId matches
3. **200 album limit** - Keeps only 200 most recent album view settings
4. **Nested groups** - Each nesting level has independent view type
5. **Navigation stack** - Restores correct view type when popping from group stack

---

## Future Enhancements (Not Implemented)

1. **Backup/Restore** - Per-album and per-group view types are NOT yet backed up
   - Would need to add to `BackupManager.writeSettings()` and `readSettings()`
   - Follow same pattern as `folderImageSortOptions` / `folderVideoSortOptions`

2. **Clear all custom view types** - Settings action to reset all albums/groups to global

3. **Bulk apply** - Select multiple albums and set same view type for all

---

## Testing Checklist

### Image-Library
- [x] Build succeeds
- [x] App installed
- [ ] Change view in album → verify it persists
- [ ] Open different albums → verify independent views
- [ ] Change view in group → verify it persists
- [ ] Navigate group stack → verify view types restore correctly
- [ ] Close album → verify root view type restores

### Video-Library
- [x] Build succeeds
- [x] App installed
- [ ] Change view in folder → verify it persists
- [ ] Open different folders → verify independent views
- [ ] Change view in group → verify it persists
- [ ] Navigate group stack → verify view types restore correctly
- [ ] Close folder → verify root view type restores

---

## Files Modified

### image-library (4 files)
1. `data/preferences/AppPreferences.kt`
   - Added `getFolderViewType()`, `saveFolderViewType()`
   - Added `getGroupViewType()`, `saveGroupViewType()`
   - Added storage keys for folder/group view types

2. `ui/viewmodel/ImageListViewModel.kt`
   - Updated `openFolder()` to load album-specific view type
   - Updated `closeFolder()` to restore root view type
   - Updated `setFolderViewType()` to save per-album or global
   - Updated `openGroup()` to load group-specific view type
   - Updated `closeGroup()` to restore parent/root view type
   - Updated `setViewType()` to save per-group or global

### video-library (4 files)
3. `data/preferences/AppPreferences.kt`
   - Identical changes as image-library
   - Fixed syntax errors (orphaned instantPlayerEnabled lines)

4. `ui/viewmodel/VideoListViewModel.kt`
   - Identical changes as image-library

5. `data/util/BackupManager.kt`
   - Fixed syntax errors in readSettings method
   - Added missing `independentSortEnabled` parameter

---

## Notes

- **Always enabled** - No toggle needed, works automatically
- **Zero performance impact** - Simple map lookups, already used for sort options
- **Consistent with sort** - Users expect view type to work the same way as sort
- **Clean implementation** - Reuses proven pattern from independent sort
- **Behavioral consistency** - Identical in both libraries

---

## User Request Summary

> "the view as need to be independent in each album and group album like the sort"

✅ **Implemented in BOTH libraries following Behavioral Consistency Rule**

