# Album Selection in Group - Wrong Album Selected Fix

**Date**: April 12, 2026  
**Status**: ✅ FIXED  
**Applies to**: Both `image-library` and `video-library`

## Problem Description

When long-pressing to select an album inside a group, the **wrong album** was being selected. The album that got selected was not the one the user actually held/tapped.

### User Report
> "inside of a group album when I select and album for a razon the seelct album is not the original been hold for a few sec"

## Root Cause

The issue was in `SharedGroupDetailScreen.kt` - there were **THREE separate bugs** all related to the optional header row:

1. **Selection callback bug** - Wrong item selected in `onLongPressWithoutDrag`
2. **Visual feedback bug** - Wrong item showing drag/selection highlight
3. **Drag overlay bug** - Wrong item shown in the floating drag preview

All three bugs stemmed from the same issue: mixing layout indices with data indices without conversion.

### The Bugs

When the floating top bar is enabled (`floatingTopBarEnabled = true`) AND the user is NOT in selection mode, the screen adds a **header row** to the LazyVerticalGrid (line 316):

```kotlin
if (!isSelectionMode && floatingTopBarEnabled) {
    item(span = { GridItemSpan(maxLineSpan) }) {
        // Header content...
    }
}
```

This header becomes **grid item index 0**. The actual album/group items start at **layout index 1**, but they're at **data index 0** in the `mixedItems` list.

#### Bug #1: Wrong Item Selected

The `onLongPressWithoutDrag` callback was using the layout index directly:

```kotlin
// WRONG - before fix
onLongPressWithoutDrag = { index ->
    mixedItems.getOrNull(index)?.let { item ->  // ❌ index is layout index, not data index
        when (item) {
            is MixedItem.Folder -> onFolderLongClick(item.folder)
            is MixedItem.Group  -> onGroupLongClick(item.group)
        }
    }
}
```

#### Bug #2: Wrong Visual Feedback

The `itemsIndexed` loop was comparing layout indices from `dragDropState.draggedIndex` with data indices from the iteration:

```kotlin
// WRONG - before fix
itemsIndexed(mixedItems, key = { _, item -> item.uniqueKey }) { index, item ->
    val itemIsDragging = canDrag && dragDropState.draggedIndex == index  // ❌ comparing layout index to data index
    // ...
}
```

This caused the **wrong album to show the selection/drag highlight** - when you long-pressed the first album (layout index 1), the second album (data index 1) would show as selected.

#### Bug #3: Wrong Drag Overlay

The floating drag overlay was using layout index to fetch from data list:

```kotlin
// WRONG - before fix
val draggedItem = mixedItems.getOrNull(dragDropState.draggedIndex)  // ❌ using layout index as data index
```

### Why It Selected the Wrong Album

1. User long-presses **first album** in the group
2. With header row present, that album is at:
   - **Layout index 1** (position in the grid: 0=header, 1=first album)
   - **Data index 0** (position in mixedItems array)
3. `DragDropGridState.onDragStart` captures **layout index = 1**
4. `onLongPressWithoutDrag` receives **layout index = 1**
5. Code calls `mixedItems.getOrNull(1)` - this is the **SECOND album** (data index 1)!
6. **Wrong album gets selected**
7. Visual highlight also appears on the wrong album because `itemIsDragging` compares `draggedIndex=1` with `dataIndex=1` for the second album

## The Fix

The fix converts between layout indices and data indices in **three places**:

### Fix #1: Selection Callback

```kotlin
val hasHeaderRow = floatingTopBarEnabled && !isSelectionMode
val dragDropState = rememberDragDropGridState(
    // ...
    onLongPressWithoutDrag = { layoutIndex ->
        // Convert layout index to data index (account for optional header row)
        val dataIndex = if (hasHeaderRow) layoutIndex - 1 else layoutIndex
        mixedItems.getOrNull(dataIndex)?.let { item ->
            when (item) {
                is MixedItem.Folder -> onFolderLongClick(item.folder)
                is MixedItem.Group  -> onGroupLongClick(item.group)
            }
        }
    },
    minDragIndex = if (hasHeaderRow) 1 else 0  // Also prevent dragging the header
)
```

### Fix #2: Visual Feedback

```kotlin
itemsIndexed(mixedItems, key = { _, item -> item.uniqueKey }) { dataIndex, item ->
    // Convert data index to layout index for comparison with dragDropState
    val layoutIndex = if (hasHeaderRow) dataIndex + 1 else dataIndex
    val itemIsDragging = canDrag && dragDropState.draggedIndex == layoutIndex
    // ...
}
```

### Fix #3: Drag Overlay

```kotlin
// Convert layout index to data index to get the correct item
val draggedDataIndex = if (hasHeaderRow) dragDropState.draggedIndex - 1 else dragDropState.draggedIndex
val draggedItem = mixedItems.getOrNull(draggedDataIndex)
```

### Fix #4: Move Callback

Also fixed the `onMove` callback to convert indices (matching SharedFoldersTab):

```kotlin
onMove = { from, to ->
    // Convert layout indices to data indices (account for optional header row)
    val dataFrom = if (hasHeaderRow) from - 1 else from
    val dataTo = if (hasHeaderRow) to - 1 else to
    if (dataFrom >= 0 && dataTo >= 0 && dataFrom < mixedItems.size && dataTo < mixedItems.size) {
        onReorderFolders(dataFrom, dataTo)
    }
},
```

### Key Changes

1. **Calculate `hasHeaderRow`**: Determine if a header is present based on floating mode and selection state
2. **Convert indices in callbacks**: Subtract 1 when converting layout→data, add 1 when converting data→layout
3. **Fix visual feedback**: Compare layout indices in `itemIsDragging` check
4. **Fix drag overlay**: Use data index to fetch the dragged item
5. **Set `minDragIndex`**: Prevent dragging the header row itself

## Pattern Consistency

This fix makes `SharedGroupDetailScreen` consistent with `SharedFoldersTab`, which already had the same index adjustment:

```kotlin
// SharedFoldersTab.kt (already correct)
onLongPressWithoutDrag = { layoutIndex ->
    val dataIndex = if (showHeaderRow) layoutIndex - 1 else layoutIndex
    resolvedItems.getOrNull(dataIndex)?.let { item ->
        // ...
    }
}
```

## Files Changed

- `common/src/main/java/com/example/common/ui/screen/SharedGroupDetailScreen.kt`

## Testing

✅ **Tested in both apps**:
1. Open a group that contains multiple albums
2. With floating top bar enabled, scroll down (header becomes inline)
3. Long-press an album to select it
4. Verify the **correct album** is selected (the one you actually held)
5. Try with different albums at different positions
6. Test with floating mode OFF (no header) - should still work correctly

## Related Issues

This bug only occurred when:
- ✅ Inside a group view
- ✅ Floating top bar was enabled
- ✅ User was NOT already in selection mode (so header was visible)
- ✅ Long-press to select an album

The bug did NOT occur when:
- ❌ On the root folders screen (different implementation)
- ❌ Floating top bar was disabled (no header offset)
- ❌ Already in selection mode (header hidden, no offset)

## Prevention

To prevent similar issues in the future:

1. **Always check for header rows** when using layout indices from drag-drop callbacks
2. **Convert layout index to data index** before accessing item arrays
3. **Set minDragIndex** to skip non-draggable header items
4. **Test both with and without** floating headers/floating mode enabled


