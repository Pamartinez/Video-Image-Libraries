# Image Library "View As" Dialog Missing in Groups - April 14, 2026

## Problem

In the image library, when inside a group (GroupDetailScreen), clicking the "View as" option in the 3-dot menu did nothing. The ViewAsDialog was never shown, so users couldn't change the view type (List/Grid Small/Grid Large) while viewing a group.

### Symptoms:
- "View as" menu item appears in group detail screen
- Clicking "View as" does nothing - no dialog appears
- ViewType cannot be changed while inside a group
- Works fine in the main Folders tab and inside albums

## Root Cause

**File:** `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`

The GroupDetailScreen was calling `viewModel.showViewAsDialog()` when the user tapped "View as" (line 566), but the **ViewAsDialog was never rendered** after the GroupDetailScreen component.

The dialog existed for:
- ✅ Main screen (line 844)
- ✅ Album detail screen (line 345)
- ❌ Group detail screen (MISSING)

## The Fix

**Added ViewAsDialog after GroupDetailScreen section:**

```kotlin
// BEFORE (lines 602-610):
if (state.showCreateAlbumDialog) {
    CreateAlbumDialog(
        existingDcimNames = state.dcimFolderNames,
        onConfirm = { name -> viewModel.startCreateAlbumPicker(name) },
        onDismiss = { viewModel.dismissCreateAlbumDialog() }
    )
}
return

// AFTER (lines 602-617):
if (state.showCreateAlbumDialog) {
    CreateAlbumDialog(
        existingDcimNames = state.dcimFolderNames,
        onConfirm = { name -> viewModel.startCreateAlbumPicker(name) },
        onDismiss = { viewModel.dismissCreateAlbumDialog() }
    )
}
if (state.showViewAsDialog) {
    ViewAsDialog(
        currentViewType = state.viewType,
        onViewTypeSelected = { viewModel.setViewType(it) },
        onDismiss = { viewModel.dismissViewAsDialog() }
    )
}
return
```

**Location:** Lines 609-615 (new code)

## Why This Was Missed

The GroupDetailScreen had the `onViewAs` callback properly wired (line 566), and the ViewModel had the correct `showViewAsDialog()` method, but the Composable that renders the dialog was simply never added to this section of the screen hierarchy.

This is likely a copy-paste oversight - the dialog exists in two other contexts (main screen and album detail) but was forgotten when the GroupDetailScreen section was created.

## Comparison with Video Library

The **video library was already correct** - it has the ViewAsDialog properly rendered after GroupDetailScreen (lines 385-391 in VideoListScreen.kt). This fix brings the image library into alignment with the video library.

## Impact

This fix resolves:
- ✅ "View as" now works inside groups
- ✅ Users can change between List/Grid Small/Grid Large view while in a group
- ✅ ViewType changes persist correctly
- ✅ Image library now matches video library behavior

## Testing

After the fix:
1. Open image-library app
2. Navigate to Folders tab
3. Open any group (or create one)
4. Tap 3-dot menu → "View as"
5. ViewAsDialog appears with List/Grid Small/Grid Large options
6. Select an option - view type changes
7. Close and reopen the group - view type is preserved

## Technical Details

### ViewAsDialog Locations

**ImageListScreen.kt:**
- Line 345: Inside album detail (FolderDetailScreen section) ✅
- Line 609-615: Inside group detail (GroupDetailScreen section) ✅ **FIXED**
- Line 844: Main screen (root level) ✅

The dialog uses `state.viewType` (not `state.folderViewType`) for groups, which is correct because groups show mixed items (folders + sub-groups) and use the main viewType preference.

### Related Code

**GroupDetailScreen.kt:**
- Line 45: `onViewAs: () -> Unit = {}` - Parameter definition ✅
- Line 92: `onViewAs = onViewAs` - Passed to SharedGroupDetailScreen ✅

**ImageListScreen.kt:**
- Line 566: `onViewAs = { viewModel.showViewAsDialog() }` - Callback wired ✅
- Line 609-615: ViewAsDialog rendered ✅ **FIXED**

## Files Modified
- `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt` (7 lines added)

## Build Info
- Build: Successful
- Installed: image-library-debug.apk
- Device: SM-S948U1 - 16
- Date: April 14, 2026

