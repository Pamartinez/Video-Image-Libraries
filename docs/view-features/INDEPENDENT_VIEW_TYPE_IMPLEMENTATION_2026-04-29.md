# Independent View Type Implementation

**Date:** April 29, 2026  
**Feature:** Independent album/group view type (grid size)  
**Branch:** `feature/independent-view-type`  
**Updated:** April 29, 2026 - Changed defaults and UI organization

## Overview

Implemented a new feature that allows each album and group to remember its own "View as" grid size (Large/Small), independent of the global view type setting. This mirrors the existing independent sort system.

**DEFAULTS CHANGED:**
- ✅ **Independent album/group sort**: Default = `true` (was `true`, unchanged)
- ✅ **Independent album/group view type**: Default = `true` (was `false`)
- ✅ **Groups always on top**: Default = `true` (was `false`)

**UI ORGANIZATION:**
- "Independent album/group view type" moved to its own **"View"** section
- "Sorting" section now contains only sort-related toggles

## What Was Implemented

### 1. Shared Preferences (Common Module)

**File:** `common/src/main/java/com/example/common/data/preferences/SharedAppPreferences.kt`

- Added `independentViewTypeEnabled: Boolean` property (default: `false`)
- Added per-group view type storage:
  - `getGroupViewType(groupId): ViewType`
  - `saveGroupViewType(groupId, viewType)`
  - `getAllGroupViewTypes(): Map<Long, Int>`
  - `saveAllGroupViewTypes(options: Map<Long, Int>)`

### 2. Library-Specific Preferences

**Files:**
- `video-library/src/main/java/com/videolibrary/data/preferences/AppPreferences.kt`
- `image-library/src/main/java/com/imagelibrary/data/preferences/AppPreferences.kt`

Added per-album view type storage methods for both libraries:
- `getFolderViewType(bucketId): ViewType` - Returns per-album view type or global `folderViewType`
- `saveFolderViewType(bucketId, viewType)` - Saves view type for specific album
- `getAllFolderViewTypes(): Map<Int, Int>` - Returns all per-album view types
- `saveAllFolderViewTypes(options)` / `restoreAllFolderViewTypes(options)` - Backup/restore support

### 3. ViewModels

**Files:**
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`
- `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`

#### Added State Property
- `independentViewTypeEnabled: Boolean` to UI state

#### Updated Methods

**View Type Setters** (`setViewType`, `setFolderViewType`):
- Save to per-group storage when inside a group and independent mode enabled
- Save to per-album storage when inside an album and independent mode enabled
- Otherwise save to global `viewType`/`folderViewType`

**Group Navigation** (`openGroup`, `closeGroup`):
- Load group-specific view type when opening a group (if independent mode enabled)
- Restore parent group's or root view type when closing a group

**Album Navigation** (`openFolder`, `closeFolder`):
- Load album-specific view type when opening an album (if independent mode enabled)
- Restore global `folderViewType` when closing an album

**Backup Restore** (`restoreBackupFromFile`):
- Restore `independentViewTypeEnabled` setting
- Restore context-specific view types for currently open group/album

### 4. Backup System

**Files:**
- `common/src/main/java/com/example/common/data/util/BackupManager.kt`
- `video-library/src/main/java/com/videolibrary/data/util/BackupManager.kt`
- `image-library/src/main/java/com/imagelibrary/data/util/BackupManager.kt`

#### Common BackupManager
- Updated `SharedSettings` data class with:
  - `groupViewTypes: Map<Long, Int>?`
  - `independentViewTypeEnabled: Boolean?`
- Updated `writeSharedSettings()` to serialize per-group view types
- Updated `readSharedSettings()` to deserialize per-group view types

#### Library-Specific BackupManagers
- Added per-folder view types to JSON backup (key: `"folderViewTypes"`)
- Added restore logic for per-folder view types
- Both libraries backup/restore:
  - `independentViewTypeEnabled` setting
  - Per-group view types (via shared backup)
  - Per-album view types (library-specific)

### 5. Settings UI

**Files:**
- `common/src/main/java/com/example/common/ui/screen/SharedSettingsScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/SettingsScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/SettingsScreen.kt`

Added toggle in Settings > Sorting section:
- **Title:** "Independent album/group view type"
- **Subtitle:** "Allow each album or group to remember its own grid size (large/small). If off, all use the global view type."
- Positioned between "Independent album/group sort" and "Groups always on top"

## Behavior

### When `independentViewTypeEnabled` is OFF (default):
- All albums and groups use the global `viewType` / `folderViewType`
- Changing view type in any context updates the global setting
- Existing behavior preserved for backward compatibility

### When `independentViewTypeEnabled` is ON:
- **Root view:** Uses global `viewType` setting
- **Inside a group:** Uses that group's saved view type (or defaults to current `viewType` on first open)
- **Inside an album:** Uses that album's saved view type (or defaults to current `folderViewType` on first open)
- Changing view type saves to the current context (group/album-specific storage)
- Each context remembers its view type independently

### Navigation:
- Opening a group/album loads its specific view type
- Closing a group/album restores the parent context's view type
- Seamless transitions maintain the correct view type for each context

## Storage Schema

### SharedPreferences Keys:
- `independent_view_type_enabled` (Boolean) - Global toggle
- `group_view_type_{groupId}` (Int) - Per-group view type ID
- `folder_view_types` (String) - Per-album view types as comma-separated "bucketId:viewTypeId" pairs

### Backup JSON Schema:
```json
{
  "settings": {
    "independentViewTypeEnabled": Boolean,
    "groupViewTypes": { "groupId": viewTypeId, ... },
    "folderViewTypes": { "bucketId": viewTypeId, ... }
  }
}
```

## Testing Checklist

- [ ] Toggle "Independent album/group view type" in Settings
- [ ] Change view type in root view (should affect root only when independent mode is on)
- [ ] Open a group, change view type (should save to that group)
- [ ] Open a different group (should have different view type if previously set)
- [ ] Open an album, change view type (should save to that album)
- [ ] Close album/group (should restore previous context's view type)
- [ ] Backup settings, change view types, restore (should restore all view types)
- [ ] Toggle independent mode off (should use global view types everywhere)
- [ ] Toggle independent mode back on (should restore per-context view types)
- [ ] Test in BOTH image-library and video-library

## Consistency with Independent Sort

This implementation exactly mirrors the existing `independentSortEnabled` system:
- Same toggle pattern in Settings
- Same preference storage structure (per-group and per-album maps)
- Same context-aware save/load logic
- Same backup/restore architecture
- Both features work independently and can be enabled/disabled separately

## Files Modified

### Common Module (3 files)
1. `common/src/main/java/com/example/common/data/preferences/SharedAppPreferences.kt`
2. `common/src/main/java/com/example/common/data/util/BackupManager.kt`
3. `common/src/main/java/com/example/common/ui/screen/SharedSettingsScreen.kt`

### Video Library (4 files)
1. `video-library/src/main/java/com/videolibrary/data/preferences/AppPreferences.kt`
2. `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`
3. `video-library/src/main/java/com/videolibrary/ui/screen/SettingsScreen.kt`
4. `video-library/src/main/java/com/videolibrary/data/util/BackupManager.kt`

### Image Library (4 files)
1. `image-library/src/main/java/com/imagelibrary/data/preferences/AppPreferences.kt`
2. `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
3. `image-library/src/main/java/com/imagelibrary/ui/screen/SettingsScreen.kt`
4. `image-library/src/main/java/com/imagelibrary/data/util/BackupManager.kt`

**Total:** 11 files modified

## Next Steps

1. Build and install both apps on device
2. Test all functionality according to checklist
3. Verify behavioral consistency between apps
4. Test backup/restore with the new settings
5. Commit changes with descriptive message
