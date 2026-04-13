# Group Rename via More Menu - April 13, 2026

## Overview
Added "Rename" option to the BottomActionBar "More" menu when a single group is selected, allowing users to rename groups directly from selection mode.

## Implementation Summary

### 1. BottomActionBar Enhancement
**File: `common/src/main/java/com/example/common/ui/components/BottomActionBar.kt`** (UPDATED)
- Added `showRenameGroup: Boolean` parameter
- Added `onRenameGroup: () -> Unit` callback
- Updated `hasMoreItems` logic to include `showRenameGroup`
- Added "Rename" menu item in More dropdown when single group selected
- Uses same Edit icon (Icons.Default.Edit) as album rename

### 2. GroupNameDialog Enhancement
**File: `common/src/main/java/com/example/common/ui/components/CommonDialogs.kt`** (UPDATED)
- Added `allowDuplicates: Boolean = false` parameter
- Updated validation logic: `if (!allowDuplicates && existingNames.any { ... })`
- When `allowDuplicates = true`, skips duplicate name validation
- Allows groups to have the same name (user requirement)

### 3. SharedGroupDetailScreen
**File: `common/src/main/java/com/example/common/ui/screen/SharedGroupDetailScreen.kt`** (UPDATED)
- Added `showRenameGroup = totalSelected == 1 && hasGroupsSelected` to BottomActionBar
- Added `onRenameGroup = onRenameGroup` callback passthrough
- Shows "More" menu with "Rename" when single group selected inside a group view

### 4. UI Layer Updates

**File: `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`** (UPDATED)
- Main view BottomActionBar:
  - Added `showRenameGroup = totalSelected == 1 && hasGroupsSelected`
  - Added `onRenameGroup = { viewModel.showRenameGroupDialog() }`
- Group rename dialog:
  - Updated to `allowDuplicates = true` (groups can have duplicate names)
  - Removed `existingNames` parameter (not needed when duplicates allowed)

**File: `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`** (UPDATED)
- Main view BottomActionBar:
  - Added `showRenameGroup = state.selectedTab == 1 && selectedCount == 1 && hasGroupsSelected`
  - Added `onRenameGroup = { viewModel.showRenameGroupDialog() }`
- Group rename dialog:
  - Updated to `allowDuplicates = true`

## User Flow

### From Main View (Folders Tab):
1. User long-presses on a group to enter selection mode
2. With **exactly one group** selected, the BottomActionBar shows a "More" button (⋮)
3. Tapping "More" reveals only one option: **"Rename"**
4. Clicking "Rename" opens GroupNameDialog with:
   - Current group name pre-filled and selected
   - **No validation** against existing group names (duplicates allowed)
   - "Cancel" and "Rename" buttons
5. Upon confirming rename:
   - Group is renamed in the database
   - Selection mode exits
   - UI refreshes to show the new name
   - Auto-backup is triggered (if enabled)

### From Group Detail View:
- Same flow works when inside a group and selecting a sub-group
- BottomActionBar shows "More" → "Rename" for single sub-group selection
- Uses the existing `onRenameGroup` callback already wired in GroupDetailScreen

## Key Design Decisions

### Why Groups Allow Duplicate Names:
- **User requested**: "A group album is allow to use an existing group album name"
- Groups are organizational containers, not filesystem entities
- Multiple groups with the same name can coexist without conflict
- Users may want "Favorites" groups at different nesting levels
- No technical reason to enforce uniqueness (group IDs are unique internally)

### Why Albums Do NOT Allow Duplicate Names:
- Albums map to physical directories on the filesystem
- Filesystem prevents duplicate folder names in the same parent directory
- MediaStore BUCKET_DISPLAY_NAME must be unique per location
- Technical requirement, not a design choice

## Consistency with Coding Instructions

✅ **Behavioral Consistency Rule**: Both libraries implement identical group rename UI
✅ **UI Component Consistency Rule**: Uses shared BottomActionBar and GroupNameDialog from common module
✅ **Common-First Rule**: All components and logic in common module, reused by both libraries
✅ **Backup & Restore**: Triggers auto-backup after successful group rename (when enabled)

## Testing

### Build Status
- ✅ **image-library**: Built successfully, installed on device
- ✅ **video-library**: Built successfully, installed on device

### Files Modified
- `common/src/main/java/com/example/common/ui/components/BottomActionBar.kt`
- `common/src/main/java/com/example/common/ui/components/CommonDialogs.kt`
- `common/src/main/java/com/example/common/ui/screen/SharedGroupDetailScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`

## Comparison: Album Rename vs Group Rename

| Feature | Album Rename | Group Rename |
|---------|-------------|--------------|
| **Trigger** | Single folder selected | Single group selected |
| **Menu Location** | More → Rename | More → Rename |
| **Dialog** | AlbumRenameDialog | GroupNameDialog |
| **Validation** | Must be unique (MediaStore + filesystem) | No validation (duplicates allowed) |
| **Physical Change** | Renames directory on disk | Database only |
| **MediaStore Update** | Yes (triggers scan) | No |
| **Auto-backup** | Yes | Yes |
| **Exit Selection Mode** | Yes | No (stays in selection) |

## Implementation Complete

Both apps now support:
- ✅ Album rename via More menu (single folder selection)
- ✅ Group rename via More menu (single group selection)
- ✅ Filesystem validation for albums
- ✅ Duplicate names allowed for groups
- ✅ Identical behavior in both libraries

