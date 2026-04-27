# Move Operation UI Standardization - Common Code Implementation
**Date:** April 27, 2026  
**Project:** Both libraries  
**Type:** Behavioral Consistency - Standardize to Full-Screen Interface  
**Priority:** Critical  

## Problem
The video-library and image-library had **completely different UI experiences** for the move-to-group operation:

**Image-library** (correct - preferred UX):
- Uses `MoveToGroupScreen` - **full-screen interface**
- Shows grid of all folders and groups
- Navigate inside groups to see structure
- Preview thumbnail and item count in bottom bar
- "Move here" button to complete action
- Option to create new group and move in one step
- Rich, contextual user experience

**Video-library** (wrong - simple but limited):
- Uses `MoveToGroupPickerDialog` - **modal dialog**
- Simple list of groups only
- No folder context or navigation
- No preview or visual feedback
- Limited functionality

### Why Full-Screen is Better
1. **Visual context**: Users can see the folder/group structure
2. **Navigation**: Can browse inside groups before moving
3. **Preview**: Bottom bar shows what's being moved
4. **Create + Move**: Can create a new group and move in one action
5. **Professional UX**: Matches modern gallery app patterns

## Solution: Use Common Code for Both Apps

Instead of duplicating code, we use the **common module** to ensure both apps have identical behavior.

### Architecture

```
common/ui/screen/MoveToGroupScreen.kt
├── All move logic and UI layout
├── Accepts injected content renderers
└── Single source of truth

video-library/ui/screen/MoveToGroupScreen.kt
├── Thin wrapper (74 lines)
├── Injects VideoThumbnail
├── Injects FolderGridItem
└── Injects GroupGridItem

image-library/ui/screen/MoveToGroupScreen.kt
├── Thin wrapper (74 lines)
├── Injects ImageThumbnail
├── Injects FolderGridItem
└── Injects GroupGridItem
```

## Implementation

### Step 1: Created Video-Library Wrapper

**New file:** `video-library/src/main/java/com/videolibrary/ui/screen/MoveToGroupScreen.kt`

This 74-line wrapper injects video-specific UI components into the common implementation:
- `VideoThumbnail` for preview
- `FolderGridItem` for folder display
- `GroupGridItem` for group display

### Step 2: Updated VideoListScreen.kt

Replaced two instances of `MoveToGroupPickerDialog` with `MoveToGroupScreen`:

**Location 1: Group Detail View (line ~515)**
```kotlin
// OLD (Modal Dialog)
MoveToGroupPickerDialog(
    groups    = state.rootGroups,
    onMove    = { viewModel.moveSelectionToGroup(it) },
    onDismiss = { viewModel.dismissMoveToGroupPicker() }
)

// NEW (Full-Screen Interface)
MoveToGroupScreen(
    folders = state.folders,
    groups = state.rootGroups,
    movingFolderIds = state.moveToGroupFolderIds,
    movingGroupIds = state.moveToGroupGroupIds,
    viewType = state.viewType,
    onMoveHere = { viewModel.moveSelectionToGroup(it) },
    onCreateGroupAndMove = { viewModel.createGroupAndMoveSelection(it) },
    onCancel = { viewModel.dismissMoveToGroupPicker() }
)
```

**Location 2: Main Folders Tab (line ~1150)**
Same change as above.

## User Experience Flow

### Before (Video-library only)
1. Select folders/albums
2. Click "Move" button
3. Simple modal dialog appears
4. Click destination group
5. Done

### After (Both apps now)
1. Select folders/albums  
2. Click "Move" button
3. **Full-screen interface appears** with:
   - Title: "Select a group"
   - Grid showing all groups with thumbnails
   - Folders shown grayed out (not clickable)
   - Groups shown and clickable to navigate inside
4. **Navigate inside groups** to see structure
5. Click "Move here" button at bottom
6. Or click "Create" to make new group and move
7. Bottom bar shows preview thumbnail and item count

**Result:** Much richer, more intuitive experience! ✅

## Benefits of This Approach

### 1. Code Reuse ✅
- Single implementation in `common/ui/screen/MoveToGroupScreen.kt`
- 275 lines of shared logic
- Only 74 lines per app for wrappers

### 2. Guaranteed Consistency ✅
- **Impossible** for apps to behave differently
- Same logic, same UI, same flow
- Bugs fixed once, both apps benefit

### 3. Type Safety ✅
- Each app injects its own components
- Compile-time verification
- No runtime casting or reflection

### 4. Maintainability ✅
- One place to update features
- One place to fix bugs
- Easy to understand and modify

### 5. Feature Parity ✅
- "Create group and move" works in both apps
- Navigation inside groups works in both apps
- Preview and visual feedback in both apps

## Files Modified

### Created
- ✅ `video-library/src/main/java/com/videolibrary/ui/screen/MoveToGroupScreen.kt` (74 lines)

### Modified
- ✅ `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`
  - Line ~515: Replaced dialog with full-screen (group view)
  - Line ~1150: Replaced dialog with full-screen (main view)

### Unchanged (Already Correct)
- ✅ `image-library/src/main/java/com/imagelibrary/ui/screen/MoveToGroupScreen.kt` (already uses full-screen)
- ✅ `common/src/main/java/com/example/common/ui/screen/MoveToGroupScreen.kt` (common implementation)

## Files Now Deprecated

The following component is no longer used and can be deleted in a future cleanup:
- `common/src/main/java/com/example/common/ui/components/CommonDialogs.kt` 
  - Function: `MoveToGroupPickerDialog` (lines 940-1010)
  - Last used: video-library (before this fix)
  - Status: Deprecated, safe to remove

## Testing Checklist

Please verify the following in **both apps**:

### Basic Move Operation:
- [ ] Select one or more folders/albums
- [ ] Click "Move" button
- [ ] **Verify:** Full-screen interface appears (not modal dialog)
- [ ] **Verify:** Shows "Select a group" at top with "Create" button
- [ ] **Verify:** Grid shows all groups with thumbnails
- [ ] **Verify:** Folders are grayed out and not clickable
- [ ] **Verify:** Groups are clickable

### Navigation:
- [ ] Click on a group → navigate inside
- [ ] **Verify:** Shows folders and sub-groups of that group
- [ ] **Verify:** Back button returns to previous level
- [ ] Navigate multiple levels deep → verify navigation stack works

### Bottom Bar:
- [ ] **Verify:** Shows preview thumbnail of moving items
- [ ] **Verify:** Shows count like "1 item" or "3 items"
- [ ] **Verify:** "Cancel" button dismisses without moving
- [ ] **Verify:** "Move here" button completes the move

### Create Group:
- [ ] Click "Create" button at top right
- [ ] Enter group name
- [ ] **Verify:** Creates group and moves items in one action
- [ ] **Verify:** Items end up in the new group

### Visual Consistency:
- [ ] UI looks identical in both apps
- [ ] Grid layout matches (2 columns for large, 3 for normal)
- [ ] Colors, fonts, spacing match
- [ ] Thumbnails render correctly (videos in video-library, images in image-library)

### Edge Cases:
- [ ] Move items when no groups exist → shows empty state
- [ ] Move into deeply nested group → navigation works
- [ ] Cancel at various points → no items moved
- [ ] Move items that are already in target group → works idempotently

## Build Status
✅ Video-library compiled successfully  
✅ Video-library installed on device (SM-S948U1 - 16)  
✅ No compilation errors  
✅ Image-library unchanged (already correct)

## Related Fixes
This is part of a series ensuring behavioral consistency:
1. ✅ `COPY_MOVE_BEHAVIORAL_CONSISTENCY_2026-04-27.md` - Fixed `bulkResolution` reset order
2. ✅ **This fix** - Standardized move UI to full-screen interface using common code

## Related Rules
- **BEHAVIORAL CONSISTENCY RULE** - All common operations must behave identically
- **UI COMPONENT CONSISTENCY RULE** - All UI components must be identical
- **Common-First Rule** - Shared logic must be in common module
- **Image-library as UX reference** - Image-library had the better UX in this case

## Performance Impact
✅ **Positive** - Richer UX without performance penalty
✅ **Positive** - Code reuse reduces APK size
✅ **Positive** - Easier to maintain and optimize

## Future Considerations

### Potential Enhancements (Apply to Common Module)
1. **Search functionality** - Add search bar to filter groups/folders
2. **Breadcrumb navigation** - Show current path at top
3. **Keyboard shortcuts** - Support keyboard navigation on tablets
4. **Accessibility** - Add TalkBack announcements and focus management
5. **Animation polish** - Smooth transitions when navigating levels

### Code Cleanup
1. **Delete MoveToGroupPickerDialog** - No longer used anywhere
2. **Consider moving ViewType** - Currently duplicated in both libraries
3. **Consolidate grid rendering** - FolderGridItem and GroupGridItem could share more code

---

**Both apps now provide the same rich, full-screen move experience using shared code from the common module! 🎉**

