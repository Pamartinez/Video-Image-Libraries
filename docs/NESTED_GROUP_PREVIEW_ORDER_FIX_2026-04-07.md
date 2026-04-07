# Nested Group Preview Image Order Fix — April 7, 2026

## Problem
When viewing a group inside another group (nested sub-groups), the preview thumbnail images were displayed in the wrong order.

### Example:
- Parent Group contains: Sub-Group A
- Sub-Group A contains: Album 1, Album 2, Album 3, Album 4 (in custom sort order)
- **Expected preview for Sub-Group A**: (Album 1, Album 2) / (Album 3, Album 4)
- **Actual preview**: (Album 2, Album 3) / (Album 4, Album 1)

### Key Observations:
- This only affected **preview generation** — not the actual display when opening the group
- The issue only occurred when **viewing** the parent group (not when sorting, adding, or deleting)
- The preview was using database order instead of the sub-group's custom sort order

## Root Cause

In `GroupRepository.buildOrderedGroupItems()`, when building the list of items to display in a group, child sub-groups were created using a **lightweight constructor** that only included basic info (groupId, name, parentGroupId).

The bug was in lines 215-222:

```kotlin
// BEFORE (WRONG): Lightweight constructor without preview generation
val subGroups = childGroups.map { child ->
    GroupItem(
        groupId = child.groupId,
        name = child.name,
        parentGroupId = child.parentGroupId  // ❌ No preview URIs!
    )
}
```

The problem: When you view a parent group, the UI displays its contents including sub-groups. Each sub-group shows a preview thumbnail (2x2 grid of 4 images). But because the sub-groups were created without calling `buildGroupItem()`, their `previewUris` field was empty/null, so the preview generation had to fall back to some default behavior that didn't respect the sub-group's sort order.

So even though Sub-Group A has custom sort order `[Album 1, Album 2, Album 3, Album 4]`, its preview was generated without considering this order, showing albums in database insertion order: `[Album 2, Album 3, Album 4, Album 1]`.

## Solution

The fix is to **build full GroupItem objects for sub-groups** instead of using the lightweight constructor. This ensures each sub-group's preview is generated correctly with `buildGroupItem()`, which respects its sort configuration.

### Updated Code:

```kotlin
// AFTER (CORRECT): Build full GroupItems with preview generation
val subGroups = childGroups.map { child ->
    buildGroupItem(child, groupSortOptions, groupCustomOrders)
}
```

### Why This Works:

1. **`buildGroupItem()`** is the function that:
   - Calls `buildOrderedGroupItems()` to get the correctly sorted list of items
   - Extracts the first 4 folders from that sorted list
   - Generates the `previewUris` list with thumbnails in the correct order

2. By calling `buildGroupItem()` recursively for each sub-group, we ensure:
   - Each sub-group's preview respects its own sort configuration
   - The preview URIs are populated correctly
   - The 2x2 grid thumbnail displays albums in the right order

3. This is a recursive solution that works for **any nesting depth**:
   - Parent Group → calls `buildGroupItem()` for Sub-Group A
   - Sub-Group A → calls `buildGroupItem()` for its own sub-groups (if any)
   - And so on...

## Testing

### Before Fix:
1. Create Parent Group
2. Create Sub-Group A inside Parent Group
3. Add Album 1, Album 2, Album 3, Album 4 to Sub-Group A
4. Set Sub-Group A to custom sort order
5. View Parent Group
6. **Bug**: Sub-Group A preview shows (Album 2, Album 3) / (Album 4, Album 1) ❌

### After Fix:
1. Same setup as above
2. View Parent Group
3. **Fixed**: Sub-Group A preview shows (Album 1, Album 2) / (Album 3, Album 4) ✅

## Files Modified

### common/src/main/java/com/example/common/data/repository/GroupRepository.kt

**Function**: `buildOrderedGroupItems()` (lines 215-218)

**Change**: When building the list of items for a group, sub-groups are now created using `buildGroupItem()` instead of the lightweight constructor. This ensures each sub-group's preview is generated with the correct sort order.

**Before**:
```kotlin
val subGroups = childGroups.map { child ->
    GroupItem(
        groupId = child.groupId,
        name = child.name,
        parentGroupId = child.parentGroupId
    )
}
```

**After**:
```kotlin
val subGroups = childGroups.map { child ->
    buildGroupItem(child, groupSortOptions, groupCustomOrders)
}
```

## Compliance with Coding Instructions

✅ **QUALITY FIRST RULE**: Thoroughly analyzed the root cause before implementing  
✅ **BEHAVIORAL CONSISTENCY RULE**: Fix is in shared `common` module, automatically applies to both libraries  
✅ **Common-First Rule**: Fix is in the shared `GroupRepository` where it belongs  
✅ **Documentation Files Rule**: Created in `docs/` folder ✓

## Related Documentation

- **Previous Fix**: `docs/SUB_GROUP_PREVIEW_ORDER_FIX_2026-04-07.md` — Fixed preview order when calling `refreshCurrentGroup()`
- **Architecture**: `docs/GROUP_SORT_ORDER_ARCHITECTURE.md` — How group sort order works
- **Related Fix**: `docs/GROUP_PREVIEW_REORDER_FIX_2026-04-07.md` — Preview refresh after reordering

## Summary

The bug was that when building the list of items to display in a group, child sub-groups were created using a lightweight constructor that didn't include preview URIs. This meant sub-group previews were not generated with the correct sort order.

The fix changes `buildOrderedGroupItems()` to call `buildGroupItem()` recursively for each child group, ensuring:
- Each sub-group's preview is generated based on its own sort configuration
- Preview URIs are populated in the correct order
- The 2x2 thumbnail grid shows albums in the order defined by the sub-group's sort settings

**Result**: Nested group previews now display albums in the correct sorted order! 🎉





