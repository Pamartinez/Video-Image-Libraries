# List View Removal from Video Library - 2026-04-29

## Summary
Removed LIST view support from video-library, leaving only GRID_SMALL and GRID_LARGE view types. The video library now matches the image library's behavior of supporting only grid views.

## Changes Made

### 1. **ViewType Enum** (`common/src/main/java/com/example/common/data/model/ViewType.kt`)
- No changes needed - LIST enum value remains for backward compatibility
- Video library simply doesn't use it anymore

### 2. **AppPreferences** (`video-library/src/main/java/com/videolibrary/data/preferences/AppPreferences.kt`)
- ✅ Changed default `viewType` from `ViewType.LIST` to `ViewType.GRID_LARGE`
- ✅ Changed default `folderViewType` from `ViewType.LIST` to `ViewType.GRID_LARGE`

### 3. **VideoListViewModel** (`video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`)
- ✅ Changed default state `viewType` from `ViewType.LIST` to `ViewType.GRID_LARGE`
- ✅ Changed default state `folderViewType` from `ViewType.LIST` to `ViewType.GRID_LARGE`
- ✅ Updated `cycleViewType()` to toggle between `GRID_LARGE` ↔ `GRID_SMALL` only
- ✅ Updated `cycleFolderViewType()` to toggle between `GRID_LARGE` ↔ `GRID_SMALL` only
- ✅ Added fallback case for legacy LIST settings (converts to GRID_LARGE)

### 4. **ViewAsDialog** (`video-library/src/main/java/com/videolibrary/ui/components/Dialogs.kt`)
- ✅ Removed `ViewType.LIST` from options list
- ✅ Updated comment to reflect "Grid / Expand options" (was "List / Grid / Expand")
- ✅ Kept LIST case in when expression for completeness (marked as "Not used")

### 5. **VideosTab** (`video-library/src/main/java/com/videolibrary/ui/screen/VideosTab.kt`)
- ✅ Removed list-related imports: `LazyColumn`, `LazyListState`, `rememberLazyListState`, `items`
- ✅ Removed `VideoListItem` import
- ✅ Removed `lazyListState` parameter
- ✅ Removed `TabContentScaffold` wrapper (was handling list/grid switching)
- ✅ Implemented direct grid rendering with loading/empty states
- ✅ Simplified to show only grid view

### 6. **FoldersTab** (`video-library/src/main/java/com/videolibrary/ui/screen/FoldersTab.kt`)
- ✅ Removed `LazyListState` import
- ✅ Removed `FolderListItem` and `GroupListItem` imports
- ✅ Removed `lazyListState` parameter from function signature
- ✅ Set `supportsListView = false` in SharedFoldersTab call
- ✅ Provided empty lambda implementations for `folderListItem` and `groupListItem`
- ✅ Added dummy `rememberLazyListState()` call (required by SharedFoldersTab signature)
- ✅ Updated comment to "grid views only"

### 7. **VideoListScreen** (`video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`)
- ✅ Removed `folderListState` and `videoListState` declarations
- ✅ Removed `lazyListState` parameters from VideosTab and FoldersTab calls
- ✅ Removed scroll-to-top logic for list states (only grid states remain)

### 8. **Files Deleted**
- ✅ `video-library/src/main/java/com/videolibrary/ui/components/VideoListItem.kt`
- ✅ `video-library/src/main/java/com/videolibrary/ui/components/GroupListItem.kt`
- ✅ `video-library/src/main/java/com/videolibrary/ui/components/FolderListItem.kt`
- ✅ `video-library/src/main/java/com/videolibrary/ui/screen/TabContentScaffold.kt`

## User-Facing Changes

### Before
- **Videos Tab**: List / Grid / Expand views available
- **Folders Tab**: List / Grid / Expand views available
- View type toggle: LIST → GRID_LARGE → GRID_SMALL → LIST (cycle)
- Default view: LIST

### After
- **Videos Tab**: Grid / Expand views only
- **Folders Tab**: Grid / Expand views only
- View type toggle: GRID_LARGE ↔ GRID_SMALL (2-way toggle)
- Default view: GRID_LARGE (Expand view)

## Backward Compatibility
- Users with saved LIST view preference will automatically fall back to GRID_LARGE
- Cycle methods include fallback cases: `ViewType.LIST -> ViewType.GRID_LARGE`
- No migration needed - app handles legacy settings gracefully

## Build & Install Status
✅ **Build**: Successful
✅ **Install**: Successful (installed on SM-S948U1 device)

## Consistency with Image Library
The video library now matches the image library's grid-only approach:
- Both libraries support only GRID_SMALL and GRID_LARGE
- Both use the same SharedFoldersTab implementation with `supportsListView = false`
- Both provide empty lambda implementations for unused list item composables
- View type behavior is now consistent across both apps

## Testing Recommendations
1. ✅ Verify app builds and installs successfully
2. ⚠️ Test view type switching (should toggle between Grid and Expand only)
3. ⚠️ Test folders tab navigation and display
4. ⚠️ Test videos tab navigation and display
5. ⚠️ Verify users with legacy LIST settings see GRID_LARGE instead
6. ⚠️ Check View As dialog shows only Grid and Expand options
7. ⚠️ Test backup/restore with view type settings

## Notes
- The LIST enum value remains in ViewType for potential future use or backward compatibility
- Common components (SharedFoldersTab) still support list view for flexibility
- This change simplifies the video library codebase and reduces maintenance burden

