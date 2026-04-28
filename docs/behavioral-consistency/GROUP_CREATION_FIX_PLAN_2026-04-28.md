# Group Creation Fix - Implementation Plan

**Date**: April 28, 2026  
**Status**: 📋 Planning Phase

---

## Problem Analysis

### Current State (After Rollback)

**There are TWO completely different group creation workflows:**

#### Workflow 1: + Button → Group (Root view only)
1. User clicks + → Group
2. Calls `showGroupNameForCreation()`
3. Sets `groupNameDialogForCreation = true`
4. Dialog appears with name input
5. User enters name, clicks "Create"
6. Dialog `onConfirm` checks `groupNameDialogForBottomBar` flag
7. **If false**, calls `createGroupFromCreationMode(name)`
8. ❌ **PROBLEM**: This tries to create immediately using `groupCreationSelectedFolderIds` (which is empty!)
9. **No checkbox selection mode ever appears**

#### Workflow 2: Bottom Bar "Group" Button (Selection mode)
1. User long-presses items to enter selection mode
2. User clicks bottom bar "Group" button
3. Calls `showGroupNameDialogForBottomBar()`
4. Sets `groupNameDialogForBottomBar = true`
5. Dialog appears with name input
6. User enters name, clicks "Create"
7. Dialog `onConfirm` checks `groupNameDialogForBottomBar` flag
8. **If true**, calls `createGroupFromSelection(name)`
9. ✅ Creates group immediately from `selectedFolderIds`
10. **No checkbox selection adjustment phase**

### The Real Problem

**Neither workflow actually uses checkbox selection mode!**

- Workflow 1 (+ button): Supposed to enter creation mode with checkboxes, but instead tries to create immediately with empty selection
- Workflow 2 (bottom bar): Creates immediately from current selection, no adjustment allowed

**The user wants:**
- BOTH workflows to show name dialog → enter checkbox selection mode → allow selection/adjustment → save creates group

---

## Root Cause

The current code has:
1. `enterGroupCreationModeWithName(name)` - Sets `isGroupCreationMode = true` but starts with empty selections
2. `createGroupFromCreationMode(name)` - Creates group from `groupCreationSelectedFolderIds`
3. `createGroupFromSelection(name)` - Creates group from `selectedFolderIds` immediately

**The dialog routing (lines 584-585) is wrong:**
```kotlin
onConfirm = { name ->
    if (state.groupNameDialogForBottomBar) viewModel.createGroupFromSelection(name)
    else viewModel.createGroupFromCreationMode(name)
}
```

This creates the group immediately instead of entering checkbox selection mode!

---

## Solution Design

### Unified Workflow (Both Entry Points)

**All group creation should follow this flow:**

1. **Show name dialog**
2. **User enters name and clicks "Create"**
3. **Enter checkbox selection mode** with:
   - + button flow: Start with empty selection
   - Bottom bar flow: Start with pre-populated selection (current `selectedFolderIds`)
4. **User adjusts selection** (add/remove items)
5. **User clicks "Save" button** (in creation mode header)
6. **Group is created** with final `groupCreationSelectedFolderIds`

### Changes Required

#### 1. Fix `showGroupNameDialogForBottomBar()` (Both ViewModels)

**Current:**
```kotlin
fun showGroupNameDialogForBottomBar() {
    _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForBottomBar = true) }
}
```

**New:**
```kotlin
fun showGroupNameDialogForBottomBar() {
    // Pre-populate creation selections with current selection
    val s = _uiState.value
    _uiState.update {
        it.copy(
            showGroupNameDialog = true,
            groupNameDialogForCreation = true,
            // Pre-populate with already-selected items
            groupCreationSelectedFolderIds = s.selectedFolderIds,
            groupCreationSelectedGroupIds = s.selectedGroupIds
        )
    }
    // Exit selection mode since we're entering creation mode
    exitSelectionMode()
}
```

#### 2. Fix Dialog Routing (Both Screens)

**Current:**
```kotlin
onConfirm = { name ->
    if (state.groupNameDialogForBottomBar) viewModel.createGroupFromSelection(name)
    else viewModel.createGroupFromCreationMode(name)
}
```

**New:**
```kotlin
onConfirm = { name ->
    // ALWAYS enter checkbox selection mode (never create immediately)
    viewModel.enterGroupCreationModeWithName(name)
}
```

#### 3. Update `enterGroupCreationModeWithName()` (Both ViewModels)

**Current:**
```kotlin
fun enterGroupCreationModeWithName(name: String) {
    _uiState.update {
        it.copy(
            showGroupNameDialog = false,
            groupNameDialogForCreation = false,
            isGroupCreationMode = true,
            pendingGroupCreationName = name,
            groupCreationSelectedFolderIds = emptySet(),  // ❌ Always empty
            groupCreationSelectedGroupIds = emptySet()     // ❌ Always empty
        )
    }
}
```

**New:**
```kotlin
fun enterGroupCreationModeWithName(name: String) {
    // Preserve any pre-populated selections (from bottom bar flow)
    val s = _uiState.value
    _uiState.update {
        it.copy(
            showGroupNameDialog = false,
            groupNameDialogForCreation = false,
            isGroupCreationMode = true,
            pendingGroupCreationName = name,
            // Keep pre-populated selections if they exist, otherwise start empty
            groupCreationSelectedFolderIds = s.groupCreationSelectedFolderIds,
            groupCreationSelectedGroupIds = s.groupCreationSelectedGroupIds
        )
    }
}
```

#### 4. Add "Save" Button Handler (Already Exists)

The creation mode UI already has a "Save" button that should call:
```kotlin
onClick = { viewModel.createGroupFromCreationMode(state.pendingGroupCreationName) }
```

This creates the group using `groupCreationSelectedFolderIds` and exits creation mode.

---

## Files to Modify

### Image Library

**File 1:** `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
- Line 1760: Fix `showGroupNameDialogForBottomBar()`
- Line 1717: Fix `enterGroupCreationModeWithName()`

**File 2:** `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`
- Line 583-586: Fix dialog `onConfirm` routing

### Video Library

**File 3:** `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`
- Similar changes to image library

**File 4:** `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`
- Similar changes to image library

---

## Implementation Steps

1. ✅ Rollback all previous changes (DONE)
2. 📝 Create this implementation plan (DONE)
3. ⏳ **Ask user to review and approve plan**
4. ⏳ Implement changes in ImageListViewModel
5. ⏳ Implement changes in ImageListScreen
6. ⏳ Implement identical changes in VideoListViewModel
7. ⏳ Implement identical changes in VideoListScreen
8. ⏳ Verify no compilation errors
9. ⏳ Build and install both apps
10. ⏳ Test all three workflows in both apps

---

## Testing Plan

### Test 1: + Button → Group (Root View)
**Expected behavior:**
1. Click + → Group
2. Dialog shows with suggested name
3. Enter name, click "Create"
4. ✅ **Checkbox selection mode activates** (starts empty)
5. Select 2+ items
6. Click "Save"
7. ✅ Group created with selected items

### Test 2: Bottom Bar → Group (Root View)
**Expected behavior:**
1. Long-press select 2+ items
2. Click bottom bar "Group"
3. Dialog shows with suggested name
4. Enter name, click "Create"
5. ✅ **Checkbox selection mode activates** (pre-selected items)
6. Adjust selection (add/remove)
7. Click "Save"
8. ✅ Group created with adjusted selection

### Test 3: Bottom Bar → Group (Inside Group)
**Expected behavior:**
1. Navigate inside a group
2. Long-press select 2+ items
3. Click bottom bar "Group"
4. Dialog shows with suggested name
5. Enter name, click "Create"
6. ✅ **Checkbox selection mode activates** (pre-selected items)
7. Adjust selection (add/remove)
8. Click "Save"
9. ✅ Nested group created with adjusted selection

---

## Why This Will Work

1. **Unified flow**: Both entry points now use the same workflow
2. **Pre-population**: Bottom bar flow pre-fills selections before entering creation mode
3. **Preservation**: `enterGroupCreationModeWithName()` keeps existing selections instead of clearing
4. **Simplicity**: Dialog always calls the same method (`enterGroupCreationModeWithName`)
5. **Consistency**: Identical implementation in both libraries

---

## What Can Go Wrong

### Risk 1: Selection State Confusion
**Problem**: If user cancels the dialog, what happens to pre-populated `groupCreationSelectedFolderIds`?  
**Mitigation**: `dismissGroupNameDialog()` already clears all flags, so state resets properly

### Risk 2: Empty Selection in + Button Flow
**Problem**: User enters creation mode but doesn't select anything before clicking "Save"  
**Mitigation**: "Save" button should be disabled when `groupCreationSelectedFolderIds.size < 2` (check if this exists)

### Risk 3: Context-Specific Bugs
**Problem**: Works in root but not in groups (or vice versa)  
**Mitigation**: Test all three scenarios thoroughly, verify `currentGroupId` is properly passed to `createGroupFromCreationMode()`

---

## Success Criteria

✅ All three workflows show identical behavior  
✅ Checkbox selection mode appears in all contexts  
✅ Pre-population works for bottom bar flow  
✅ Empty start works for + button flow  
✅ Groups are created with correct selected items  
✅ Both apps behave identically  
✅ No regressions in existing functionality

---

## Next Step

**USER REVIEW REQUIRED** 

Please review this plan and confirm:
1. Does this approach make sense?
2. Is the unified workflow what you want?
3. Are there any edge cases we're missing?
4. Should we proceed with implementation?

