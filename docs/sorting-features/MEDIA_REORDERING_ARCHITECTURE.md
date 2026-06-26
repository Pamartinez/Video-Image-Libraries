# Media Drag-to-Reorder Architecture & MediaStore Integration Analysis

**Date:** June 26, 2026  
**Feature:** Drag-and-drop reordering of images/videos inside albums  
**Scope:** Both image-library and video-library  
**Branch:** `feature/media-drag-reorder`

---

## Executive Summary

This document analyzes two approaches for implementing drag-to-reorder functionality for individual media files (images/videos) within albums:

1. **App-Only Custom Order** (Recommended ✅) - Store custom order in app preferences
2. **MediaStore Timestamp Manipulation** (Not Recommended ❌) - Modify `DATE_MODIFIED` to influence system sort

**Recommendation:** **App-Only Custom Order** is the safer, more maintainable approach.

---

## The Challenge

User wants to drag-and-drop individual images/videos to create a custom order within albums, similar to how albums/groups can currently be reordered. The key question:

> **Can we modify the MediaStore database so Samsung Gallery and other native apps see our custom order?**

---

## Approach 1: App-Only Custom Order (Recommended ✅)

### How It Works

Store custom order independently in **app preferences** (SharedPreferences), applying it after loading from MediaStore.

### Implementation

```kotlin
// In AppPreferences.kt
private const val KEY_FOLDER_MEDIA_CUSTOM_ORDERS = "folder_media_custom_orders"

fun getFolderMediaCustomOrder(bucketId: Int): List<Long> {
    // Parse "bucketId:id1;id2;id3,bucketId2:id4;id5,..."
    return parseOrderForBucket(bucketId)
}

fun saveFolderMediaCustomOrder(bucketId: Int, imageIds: List<Long>) {
    // Store as "bucketId:id1;id2;id3,bucketId2:id4;id5,..."
}
```

```kotlin
// In Repository
suspend fun getImages(
    imageSortOption: ImageSortOption,
    bucketId: Int?,
    allowMediaReordering: Boolean,
    getCustomOrder: (Int) -> List<Long>
): List<ImageItem> {
    // 1. Load from MediaStore (returns date-sorted)
    val images = loadFromMediaStore(imageSortOption, bucketId)
    
    // 2. If allowMediaReordering && CUSTOM_ORDER && custom order exists
    if (allowMediaReordering && 
        imageSortOption == ImageSortOption.CUSTOM_ORDER &&
        bucketId != null) {
        
        val savedOrder = getCustomOrder(bucketId)
        if (savedOrder.isNotEmpty()) {
            return applyCustomMediaOrder(images, savedOrder)
        }
    }
    
    return images
}

private fun applyCustomMediaOrder(
    images: List<ImageItem>,
    savedOrder: List<Long>
): List<ImageItem> {
    val imageMap = images.associateBy { it.id }
    val ordered = savedOrder.mapNotNull { imageMap[it] }
    val newItems = images.filterNot { it.id in savedOrder }
    return newItems + ordered // New items prepended at top
}
```

### Pros ✅

- **Safe**: No risk of corrupting MediaStore or user's photo metadata
- **App-specific**: Custom order only affects your app, doesn't interfere with other apps
- **Flexible**: Can implement complex sorting logic without system limitations
- **Reversible**: Disabling the feature restores default order instantly
- **Fast**: No MediaStore write operations (read-only)
- **No permissions needed**: Read-only MediaStore access
- **Backup-friendly**: Custom orders export/restore via app backup system

### Cons ⚠️

- **Not shared with other apps**: Samsung Gallery won't see your custom order
- **Storage overhead**: Must maintain order list in preferences
- **New item handling**: Need logic to handle newly-added photos

### Storage Format

**SharedPreferences format:**
```
"bucketId1:id1;id2;id3,bucketId2:id4;id5;id6,bucketId3:id7;id8"
```

Example: Album 123 with images in order [1001, 1002, 1003]:
```
"123:1001;1002;1003,456:2001;2002;2003"
```

---

## Approach 2: MediaStore Timestamp Manipulation (Not Recommended ❌)

### How It Would Work

Modify `DATE_MODIFIED` timestamps in MediaStore to create artificial ordering that other apps would respect when sorting by date.

### Cons (Why This Is A Bad Idea) ❌

1. **Data Corruption Risk**: Overwrites real file metadata
2. **Unpredictable Behavior**: MediaStore may reject or override changes
3. **Permission Issues**: Requires write permissions
4. **Performance Impact**: Expensive N database updates
5. **Conflict with Real Edits**: Cannot distinguish real edits from reordering
6. **Samsung Gallery Won't Use It**: Samsung uses private `album_order` field
7. **Fragile**: Media rescans can undo changes
8. **User Confusion**: Wrong modification dates in file properties

---

## Samsung Gallery's Custom Order Mechanism

From decompiled Samsung Gallery code (`AlbumHelper.java`):

```java
ContentValues contentValues = new ContentValues();
contentValues.put("album_order", Long.valueOf(j2));  // Samsung's custom order field
contentValues.put("__bucketID", Integer.valueOf(newFolderId));
```

**Key Finding**: Samsung Gallery uses `album_order` field in its **private database**, not standard MediaStore fields.

### What This Means

- Samsung Gallery's custom order is stored in a **proprietary schema**
- Third-party apps **cannot access or modify** this database
- The `album_order` field is **not part of Android's MediaStore API**
- Modifying `DATE_MODIFIED` **will not affect Samsung Gallery's custom order**

---

## Recommendation: App-Only Approach

### Why App-Only Is The Right Choice

1. **Samsung Gallery Integration Is Impossible**
   - Samsung uses private database fields (`album_order`)
   - No public API to modify Samsung Gallery's order
   - Even if we modify `DATE_MODIFIED`, Samsung Gallery ignores it

2. **MediaStore Manipulation Is Harmful**
   - Corrupts real file metadata
   - Unpredictable across Android versions
   - Performance impact
   - Permission headaches

3. **App-Only Is Industry Standard**
   - Most gallery apps (Google Photos, etc.) store custom order locally
   - This is how folder/album reordering already works in our app
   - Consistent with existing architecture

### Feature Parity

Our current implementation **already uses app-only storage** for:
- ✅ Folder/album custom order (`customAlbumOrder`, `customMixedOrder`)
- ✅ Group custom order (`customGroupOrder`, per-group orders)
- ✅ Per-album sort options (`folderImageSortOptions`)

**Media reordering follows the same pattern** for consistency.

---

## Implementation Status

### Phase 1: Storage Layer ✅ COMPLETE

**Files Modified:**
- `common/src/main/java/com/example/common/data/preferences/SharedAppPreferences.kt`
  - Added `allowMediaReordering: Boolean` toggle
  - Added `customRootMediaOrder: List<Long>` for root view
  
- `image-library/src/main/java/com/imagelibrary/data/preferences/AppPreferences.kt`
  - Added `getFolderMediaCustomOrder(bucketId): List<Long>`
  - Added `saveFolderMediaCustomOrder(bucketId, imageIds)`
  - Added `getAllFolderMediaCustomOrders(): Map<Int, List<Long>>` for backup
  - Added `restoreAllFolderMediaCustomOrders(orders)` for restore
  
- `video-library/src/main/java/com/videolibrary/data/preferences/AppPreferences.kt`
  - Added `getFolderMediaCustomOrder(bucketId): List<Long>`
  - Added `saveFolderMediaCustomOrder(bucketId, videoIds)`
  - Added `getAllFolderMediaCustomOrders(): Map<Int, List<Long>>` for backup
  - Added `restoreAllFolderMediaCustomOrders(orders)` for restore

**Storage Format:** `"bucketId:id1;id2;id3,bucketId2:id4;id5"`  
**Max Albums Stored:** 50 (prevents excessive SharedPreferences size)

---

## Next Steps

**Remaining Phases:**
1. Phase 2: Repository Layer (apply custom order after MediaStore load)
2. Phase 3: ViewModel Layer (reorder methods + state management)
3. Phase 4: Backup Integration
4. Phase 5: Settings UI
5. Phase 6: Drag-and-Drop UI (Folders)
6. Phase 7: Drag-and-Drop UI (Root Views)
7. Phase 8: Testing & Polish
8. Phase 9: Documentation
9. Phase 10: Code Review & Merge

**Estimated Remaining Time:** 7-10 hours

---

## Related Documentation

- `INDEPENDENT_SORT_ARCHITECTURE.md` - Per-album sort system
- `GROUP_SORT_ORDER_ARCHITECTURE.md` - Group reordering patterns
- `../backup-restore/` - Backup system integration
- `MEDIA_REORDERING_CHECKLIST.md` - Implementation checklist

---

**Decision:** App-Only Custom Order (Approach 1) ✅  
**Status:** Phase 1 Complete - Storage Layer Implemented  
**Branch:** `feature/media-drag-reorder`

