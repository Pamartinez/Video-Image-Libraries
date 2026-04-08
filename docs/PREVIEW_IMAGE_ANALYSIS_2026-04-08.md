# Preview Image Generation Analysis - April 8, 2026

## Investigation
User reported potential issues with preview image generation rules for albums and groups in video-library.

## Findings
After thorough investigation, **both image-library and video-library already use identical logic** for preview generation. No changes were needed.

### Album (Folder) Preview Generation

**How it works:**
1. When querying videos/images from MediaStore, the query is **sorted according to the current sort option**
2. The **first item** returned from the sorted query becomes the folder's preview (stored in `latestItemUri`)
3. This ensures the preview always matches the top item when viewing the folder

**Implementation (identical in both libraries):**

**video-library** (`VideoRepository.kt`, lines ~126-162):
```kotlin
// Build sort order based on videoSortOption to select preview video correctly
val sortOrderStr = buildVideoSortOrder(videoSortOption)

contentResolver.query(videoUri, projection, selection, null, sortOrderStr)?.use { cursor ->
    while (cursor.moveToNext()) {
        val id = cursor.getLong(idCol)
        val bId = cursor.getInt(bucketIdCol)
        // ...
        val existing = folderMap[bId]
        if (existing != null) {
            // Increment count but keep the first preview (already the top item based on sort)
            folderMap[bId] = existing.copy(
                itemCount = existing.itemCount + 1,
                latestDateModified = maxOf(existing.latestDateModified, dateModified)
            )
        } else {
            // First item for this bucket becomes the preview (respects sort order)
            folderMap[bId] = FolderItem(
                bucketId = bId,
                name = bName,
                itemCount = 1,
                latestItemUri = ContentUris.withAppendedId(videoUri, id),
                // ...
            )
        }
    }
}
```

**image-library** (`ImageRepository.kt`, lines ~128-166):
```kotlin
// Build sort order based on imageSortOption to select preview image correctly
val (sortType, sortOrder) = imageSortOptionToTypeOrder(imageSortOption)
val sortOrderStr = buildSortOrder(sortType, sortOrder)

contentResolver.query(imageUri, projection, selection, null, sortOrderStr)?.use { cursor ->
    while (cursor.moveToNext()) {
        val id = cursor.getLong(idCol)
        val bId = cursor.getInt(bucketIdCol)
        // ...
        val existing = folderMap[bId]
        if (existing != null) {
            // Increment count but keep the first preview (already the top item based on sort)
            folderMap[bId] = existing.copy(
                itemCount = existing.itemCount + 1,
                latestDateModified = maxOf(existing.latestDateModified, dateModified)
            )
        } else {
            // First item for this bucket becomes the preview (respects sort order)
            folderMap[bId] = FolderItem(
                bucketId = bId,
                name = bName,
                itemCount = 1,
                latestItemUri = ContentUris.withAppendedId(imageUri, id),
                // ...
            )
        }
    }
}
```

### Group Preview Generation

**How it works:**
1. Groups display previews from **up to 4 folders** (not individual media items)
2. The folders are taken from the group's **ordered list** (respecting the group's sort order)
3. Each folder's preview (`latestItemUri`) is used, which already respects that folder's sort

**Implementation (shared in common module):**

**common** (`GroupRepository.kt`, lines ~181-188):
```kotlin
// Extract first 4 FOLDERS ONLY (skip groups) for preview
val previewUris = orderedItems
    .filterIsInstance<FolderItem>()
    .take(4)
    .mapNotNull { folder ->
        folder.latestItemUri
    }
```

Where `orderedItems` is built respecting the group's sort option:
```kotlin
private suspend fun buildOrderedGroupItems(
    groupId: Long,
    memberBucketIds: List<Int>,
    childGroups: List<GroupEntity>,
    allFolders: List<FolderItem>,
    groupSortOptions: Map<Long, Int>,
    groupCustomOrders: Map<Long, List<String>>
): List<Any>
```

This function applies the group's sort option (custom order, name, item count) and returns properly ordered items.

## Conclusion
✅ **Album previews** already respect the folder's current sort option  
✅ **Group previews** already respect the group's current sort order  
✅ **Both libraries** use identical logic (shared common code for groups)  
✅ **No changes needed** - preview generation is working correctly

## Note
The preview image generation is **intentionally designed** to:
- Show the "top" item based on current sort
- Update when sort changes (because folders are re-queried with new sort)
- Respect independent sort settings when enabled

This behavior is consistent with how gallery apps typically work - the preview represents what you'll see first when you open that folder/group.

