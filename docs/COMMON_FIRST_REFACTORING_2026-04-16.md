# Common-First Rule Refactoring: GroupMixedOrderUtil
**Date:** April 16, 2026  
**Rule Applied:** Common-First Rule (code identical in both libraries must go in `common`)  
**Impact:** Both `image-library` and `video-library`

---

## Problem Identified

During the performance optimization work, I added `applyCustomGroupMixedOrder` helper function to the **image-library** to match the **video-library's** existing implementation. However, this violated the **Common-First Rule**:

> **If code or functions are exactly the same, they MUST go in the `common` module. No exceptions.**

Both libraries had **identical** implementations of `applyCustomGroupMixedOrder`:

```kotlin
private fun applyCustomGroupMixedOrder(
    groupId: Long,
    groups: List<GroupItem>,
    folders: List<FolderItem>
): List<Any> {
    val saved     = preferences.getGroupMixedOrder(groupId)  // ✅ Same method in SharedAppPreferences
    val groupMap  = groups.associateBy  { "g_${it.groupId}" }
    val folderMap = folders.associateBy { "f_${it.bucketId}" }

    if (saved.isEmpty()) return groups + folders

    val ordered    = saved.mapNotNull { groupMap[it] ?: folderMap[it] }
    val savedSet   = saved.toSet()
    val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
    val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
    return newGroups + newFolders + ordered
}
```

### Why This Qualifies for Common Module

1. **100% Identical Logic**: Both implementations were byte-for-byte identical
2. **Shared Dependencies**: Both use `SharedAppPreferences.getGroupMixedOrder()`
3. **Common Data Types**: Uses only common types (`GroupItem`, `FolderItem`)
4. **No Library-Specific Code**: No image-specific or video-specific logic

---

## Solution: Extract to Common Module

### Created New Utility Class

**File:** `common/src/main/java/com/example/common/util/GroupMixedOrderUtil.kt`

```kotlin
package com.example.common.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.GroupItem
import com.example.common.data.preferences.SharedAppPreferences

/**
 * Utilities for handling custom ordering of mixed items (groups + folders) within groups.
 * Shared between image-library and video-library.
 */
object GroupMixedOrderUtil {

    /**
     * Apply custom order to a group's mixed items (sub-groups + folders).
     * New items not in the saved order are prepended at the top.
     *
     * @param groupId Group ID to get the custom order for
     * @param groups List of sub-groups in this group
     * @param folders List of folders in this group
     * @param preferences SharedAppPreferences instance to read the saved order
     * @return Ordered list of mixed items (groups + folders)
     */
    fun applyCustomGroupMixedOrder(
        groupId: Long,
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        preferences: SharedAppPreferences
    ): List<Any> {
        val saved     = preferences.getGroupMixedOrder(groupId)
        val groupMap  = groups.associateBy  { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }

        if (saved.isEmpty()) return groups + folders

        val ordered    = saved.mapNotNull { groupMap[it] ?: folderMap[it] }
        val savedSet   = saved.toSet()
        val newGroups: List<Any>  = groups.filter  { "g_${it.groupId}"  !in savedSet }
        val newFolders: List<Any> = folders.filter { "f_${it.bucketId}" !in savedSet }
        // New items are prepended so they always appear at the top
        return newGroups + newFolders + ordered
    }
}
```

### Key Design Decision: Preferences Parameter

Instead of accessing preferences directly, the function takes `SharedAppPreferences` as a parameter:
- ✅ **Testable**: Easy to mock preferences in unit tests
- ✅ **Flexible**: Works with any SharedAppPreferences subclass
- ✅ **Clear Dependencies**: Makes it obvious what the function needs
- ✅ **No Hidden State**: No global or static preference access

---

## Files Modified

### 1. Created Common Utility
- **`common/src/main/java/com/example/common/util/GroupMixedOrderUtil.kt`**
  - New file: Shared utility for both libraries

### 2. Image-Library ViewModel
- **`image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`**
  - **Removed**: Lines 750-771 (private `applyCustomGroupMixedOrder` function)
  - **Added**: Import for `com.example.common.util.GroupMixedOrderUtil`
  - **Updated**: Line 1718 - Changed call from `applyCustomGroupMixedOrder(groupId, subGroups, groupFolders)` to `GroupMixedOrderUtil.applyCustomGroupMixedOrder(groupId, subGroups, groupFolders, preferences)`

### 3. Video-Library ViewModel
- **`video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`**
  - **Removed**: Lines 901-918 (private `applyCustomGroupMixedOrder` function)
  - **Added**: Import for `com.example.common.util.GroupMixedOrderUtil`
  - **Updated**: 3 call sites (lines 656, 795, 883) - Changed from private function to common util
    - `applyCustomGroupMixedOrder(...)` → `GroupMixedOrderUtil.applyCustomGroupMixedOrder(..., preferences)`

---

## Benefits of This Refactoring

### 1. **Code Deduplication**
- **Before**: 2 identical implementations (image-library + video-library)
- **After**: 1 shared implementation in common module
- **Saved**: ~20 lines of duplicated code

### 2. **Single Source of Truth**
- Bug fixes now apply to both libraries automatically
- No risk of implementations diverging over time
- Easier to maintain and test

### 3. **Follows Architectural Rules**
- ✅ **Common-First Rule**: Identical code moved to common
- ✅ **DRY Principle**: Don't Repeat Yourself
- ✅ **Behavioral Consistency**: Guaranteed identical behavior

### 4. **Better Testability**
- Can test the utility function in isolation
- Both libraries benefit from the same test coverage
- Easier to write unit tests for the function

---

## Testing Performed

1. ✅ **Build Verification**: Both apps compiled successfully
2. ✅ **Installation**: Both apps installed on device (SM-S948U1 - 16)
3. ✅ **Functionality**: Group custom order works identically in both apps

---

## Common-First Rule Checklist

When adding new code, always ask:

1. ✅ **Can this logic run in both libraries without modification?**
   - Yes → It **MUST** go in `common`

2. ✅ **Can it be made shared with a small abstraction?**
   - Yes → Implement abstraction in `common`

3. ⚠️ **Does it only differ because of library-specific types?**
   - Extract type-independent parts to `common`

4. ❌ **Is the logic truly, fundamentally different?**
   - Only then may it live exclusively in library-specific files

### Double-Check Rule

After writing code in a library-specific file:

> *"Is there even one line here that is identical or near-identical in the other library?"*

**If YES → Move that line (or block) to `common` before finishing.**

---

## Related Patterns

This same pattern should be applied to other duplicate functions:

### Potential Candidates for Common Module

Look for:
- Identical helper functions in both ViewModels
- Identical UI component logic
- Identical data transformation logic
- Identical validation logic

### Future Refactoring Opportunities

- Review both ViewModels for other duplicate functions
- Check UI screens for duplicate helper functions
- Examine repositories for duplicate data processing

---

## Behavioral Consistency Verification

✅ **CRITICAL RULE COMPLIANCE**: This refactoring **enforces** behavioral consistency:
- Both libraries now use the **exact same code**
- No possibility of divergence
- Guaranteed identical group custom ordering behavior

---

## Deployment Status

- **Code**: ✅ Refactored to common module
- **Build**: ✅ Successful (both apps)
- **Installation**: ✅ Complete (both apps installed on device)
- **Testing**: ✅ Functionality verified identical

**Both apps now share the same `GroupMixedOrderUtil` implementation from the common module, following the Common-First Rule correctly.**

