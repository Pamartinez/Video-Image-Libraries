# Media Drag-to-Reorder Implementation Summary

**Date:** June 26, 2026  
**Feature:** Drag-and-drop reordering of images/videos inside albums (folders)  
**Scope:** Both image-library and video-library  
**Branch:** `feature/media-drag-reorder`  
**Status:** Phase 6 Complete ✅ (Folders UI implemented, ready for testing)

---

## Overview

This feature allows users to manually reorder individual media items (images/videos) within albums by dragging and dropping them, similar to the existing drag-to-reorder functionality for albums and groups. The custom order is stored in app preferences and only applies when:

1. **The setting is enabled** (`allowMediaReordering = true`)
2. **Sort mode is CUSTOM_ORDER**
3. **User is inside a folder/album** (not the root view yet)

---

## Architecture Decision

**Approach: App-Only Custom Order** ✅

- Custom order stored in SharedPreferences, not MediaStore
- Safe, reversible, no risk of corrupting user's photo metadata
- Order only visible in this app (not shared with Samsung Gallery)
- See [MEDIA_REORDERING_ARCHITECTURE.md](./MEDIA_REORDERING_ARCHITECTURE.md) for detailed analysis

---

## Implementation Phases

### Phase 1: Storage Layer ✅ COMPLETE
**Files Modified:**
- `common/src/main/java/com/example/common/data/SharedAppPreferences.kt`
- `image-library/src/main/java/com/imagelibrary/data/AppPreferences.kt`
- `video-library/src/main/java/com/videolibrary/data/AppPreferences.kt`

**Key Methods:**
```kotlin
// Shared (common module)
var allowMediaReordering: Boolean
var customRootMediaOrder: List<Long>

// Library-specific
fun getFolderMediaCustomOrder(bucketId: Int): List<Long>
fun saveFolderMediaCustomOrder(bucketId: Int, mediaIds: List<Long>)
fun getAllFolderMediaCustomOrders(): Map<Int, List<Long>>
fun restoreAllFolderMediaCustomOrders(orders: Map<Int, List<Long>>)
```

**Storage Format:**
- Compact string format: `"bucketId:id1;id2;id3,bucketId2:id4;id5,..."`
- Maximum 50 albums stored to prevent SharedPreferences bloat
- Per-album orders stored separately for efficient lookup

---

### Phase 2: Repository Layer ✅ COMPLETE
**Files Modified:**
- `image-library/src/main/java/com/imagelibrary/data/repository/ImageRepository.kt`
- `video-library/src/main/java/com/videolibrary/data/repository/VideoRepository.kt`

**Key Changes:**
```kotlin
suspend fun getImages(
    imageSortOption: ImageSortOption,
    bucketId: Int?,
    allowMediaReordering: Boolean = false,
    customOrder: List<Long> = emptyList()
): List<ImageItem> {
    val images = loadFromMediaStore(...)
    
    // Apply custom order if enabled and available
    return if (allowMediaReordering && 
               imageSortOption == ImageSortOption.CUSTOM_ORDER && 
               customOrder.isNotEmpty()) {
        applyCustomMediaOrder(images, customOrder)
    } else {
        images
    }
}

private fun applyCustomMediaOrder(
    images: List<ImageItem>, 
    customOrder: List<Long>
): List<ImageItem> {
    val imageMap = images.associateBy { it.id }
    val result = mutableListOf<ImageItem>()
    val newItems = mutableListOf<ImageItem>()
    
    // First, add images in custom order
    for (id in customOrder) {
        imageMap[id]?.let { result.add(it) }
    }
    
    // Then, collect new items (not in custom order)
    for (image in images) {
        if (!customOrder.contains(image.id)) {
            newItems.add(image)
        }
    }
    
    // Prepend new items at position 0 (consistent with folder/group behavior)
    return newItems + result
}
```

**Behavior:**
- New items always appear at the top (position 0)
- Consistent with existing folder/group reordering logic
- Deleted items automatically removed (no longer in MediaStore)

---

### Phase 3: ViewModel Layer ✅ COMPLETE
**Files Modified:**
- `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`

**UiState Changes:**
```kotlin
data class ImageListUiState(
    // ...existing fields...
    val allowMediaReordering: Boolean = false
)
```

**Key Methods:**
```kotlin
// Settings toggle
fun updateAllowMediaReordering(value: Boolean) {
    preferences.allowMediaReordering = value
    _uiState.update { it.copy(allowMediaReordering = value) }
    scheduleAutoBackup()
}

// Folder (album) reordering
fun reorderFolderMedia(fromIndex: Int, toIndex: Int) {
    if (_uiState.value.currentFolderBucketId == null) return
    val currentImages = _uiState.value.folderImages.toMutableList()
    if (fromIndex !in currentImages.indices || toIndex !in currentImages.indices) return
    
    val item = currentImages.removeAt(fromIndex)
    currentImages.add(toIndex, item)
    _uiState.update { it.copy(folderImages = currentImages) }
}

fun onFolderMediaReorderDone() {
    val currentBucketId = _uiState.value.currentFolderBucketId ?: return
    val imageIds = _uiState.value.folderImages.map { it.id }
    preferences.saveFolderMediaCustomOrder(currentBucketId, imageIds)
    scheduleAutoBackup()
}

// Root view reordering (future phase)
fun reorderRootMedia(fromIndex: Int, toIndex: Int) {
    // Similar to folder reordering
}

fun onRootMediaReorderDone() {
    // Persist to customRootMediaOrder
}
```

**Data Flow:**
1. User drags item from `fromIndex` to `toIndex`
2. `reorderFolderMedia()` updates UI immediately (optimistic update)
3. `onFolderMediaReorderDone()` persists the new order to SharedPreferences
4. Auto-backup triggered if enabled

---

### Phase 4: Backup Integration ✅ COMPLETE
**Files Modified:**
- `common/src/main/java/com/example/common/data/util/BackupManager.kt`
- `image-library/src/main/java/com/imagelibrary/data/util/BackupManager.kt`
- `video-library/src/main/java/com/videolibrary/data/util/BackupManager.kt`

**SharedSettings Updates:**
```kotlin
data class SharedSettings(
    // ...existing fields...
    val allowMediaReordering: Boolean? = null,
    val customRootMediaOrder: List<Long>? = null
)
```

**Library-Specific Backup:**
```kotlin
// Write
put("folderMediaCustomOrders", JSONObject(
    prefs.getAllFolderMediaCustomOrders().mapKeys { it.key.toString() }
))

// Read
if (settings.has("folderMediaCustomOrders")) {
    val orders = settings.getJSONObject("folderMediaCustomOrders")
    val ordersMap = orders.keys().asSequence().associate { key ->
        key.toInt() to parseIdList(orders.getString(key))
    }
    prefs.restoreAllFolderMediaCustomOrders(ordersMap)
}
```

**Auto-Backup Triggers:**
- When user completes drag-and-drop gesture
- When toggle is changed in settings
- Standard app lifecycle events (background, onCleared)

---

### Phase 5: Settings UI ✅ COMPLETE
**Files Modified:**
- `common/src/main/java/com/example/common/ui/screen/SharedSettingsScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/SettingsScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/SettingsScreen.kt`

**UI Components:**
```kotlin
// In Interface section
SharedSettingsRow(
    title = "Drag to reorder media",
    subtitle = "Hold and drag images/videos in Custom sort",
    checked = allowMediaReordering,
    onCheckedChange = onToggleMediaReordering,
    showInfoIcon = true,
    onInfoClick = { showMediaReorderingInfo = true }
)

// Info dialog
if (showMediaReorderingInfo) {
    SharedInfoDialog(
        title = "Drag to Reorder Media",
        message = """
            When enabled and Custom sort is active:
            • Hold an image/video for 1 second to activate drag
            • Drag to reorder within the album
            • Order persists across app restarts
            • Only applies inside albums (not root view)
        """.trimIndent(),
        onDismiss = { showMediaReorderingInfo = false }
    )
}
```

**Location:** Interface section, between "Drag to reorder albums" and existing toggles

---

### Phase 6: Drag-and-Drop UI (Folders) ✅ COMPLETE
**Files Modified:**
- `common/src/main/java/com/example/common/ui/screen/SharedFolderDetailScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/FolderDetailScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/FolderDetailScreen.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`

**SharedFolderDetailScreen Parameters:**
```kotlin
@Composable
fun SharedFolderDetailScreen(
    // ...existing params...
    allowMediaReordering: Boolean,
    onReorderItem: (Int, Int) -> Unit,
    onReorderDone: () -> Unit,
    isCustomSortMode: Boolean,
    // ...
) {
    val hasHeaderRow = floatingTopBarEnabled && !isSelectionMode
    val canDrag = allowMediaReordering && isCustomSortMode && !isSelectionMode
    
    val dragDropState = rememberDragDropGridState(
        lazyGridState = lazyGridState,
        onMove = { from, to ->
            // Convert layout indices to data indices (account for header row)
            val dataFrom = if (hasHeaderRow) from - 1 else from
            val dataTo = if (hasHeaderRow) to - 1 else to
            if (dataFrom >= 0 && dataTo >= 0 && 
                dataFrom < items.size && dataTo < items.size) {
                onReorderItem(dataFrom, dataTo)
            }
        },
        onDragEnd = onReorderDone,
        onLongPressWithoutDrag = { layoutIndex ->
            val dataIndex = if (hasHeaderRow) layoutIndex - 1 else layoutIndex
            items.getOrNull(dataIndex)?.let { item ->
                onItemLongClick(item)
            }
        },
        isInSelectionMode = { isSelectionMode },
        onEnterDragMode = {},
        minDragIndex = if (hasHeaderRow) 1 else 0
    )
    
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .then(if (canDrag) Modifier.dragToReorderGrid(dragDropState) else Modifier),
        userScrollEnabled = !dragDropState.isDragging,
        // ...
    )
}
```

**FolderDetailScreen Wiring (image-library):**
```kotlin
FolderDetailScreen(
    folderName = state.currentFolderName,
    images = state.folderImages,
    viewType = state.folderViewType,
    isSelectionMode = state.isSelectionMode,
    selectedIds = state.selectedImageIds,
    floatingTopBarEnabled = state.floatingTopBarEnabled,
    allowMediaReordering = state.allowMediaReordering,
    isCustomSortMode = state.imageSortOption == ImageSortOption.CUSTOM_ORDER,
    // ...
    onReorderItem = { fromIndex, toIndex -> 
        viewModel.reorderFolderMedia(fromIndex, toIndex) 
    },
    onReorderDone = { viewModel.onFolderMediaReorderDone() },
    // ...
)
```

**Behavior:**
- Drag only enabled when: `allowMediaReordering && isCustomSortMode && !isSelectionMode`
- Long-press (1 second) activates drag mode
- Scroll disabled during drag
- Header row accounted for in index conversions (floating top bar mode)
- Animations use spring physics for smooth transitions

---

## Testing Checklist

### Image-Library Testing
- [ ] **Enable toggle** in Settings → Interface → "Drag to reorder media"
- [ ] **Open an album** and change sort to "Custom order"
- [ ] **Long-press an image** for ~1 second
- [ ] **Drag to reorder** - verify smooth animation
- [ ] **Release** - verify order persists
- [ ] **Close and reopen album** - verify order restored
- [ ] **Add new images** - verify they appear at the top
- [ ] **Test with floating top bar ON** - verify header row doesn't interfere
- [ ] **Test with floating top bar OFF** - verify regular header works
- [ ] **Enter selection mode** - verify drag is disabled
- [ ] **Switch to different sort** (e.g., Date) - verify drag disabled
- [ ] **Disable toggle** - verify drag disabled
- [ ] **Backup and restore** - verify custom orders preserved

### Video-Library Testing
- [ ] Same tests as image-library (replace "images" with "videos")

### Consistency Testing
- [ ] Verify both apps have identical UX
- [ ] Verify settings UI matches exactly
- [ ] Verify drag behavior is identical
- [ ] Verify info dialogs have matching text

---

## Known Limitations

1. **Root view not yet implemented** - Phase 7 will add drag support to Images/Videos tabs
2. **Max 50 albums** - Storage limitation to prevent SharedPreferences bloat
3. **App-only order** - Custom order not visible in Samsung Gallery or other apps
4. **Custom sort required** - Feature only works in Custom sort mode
5. **Album context only** - Currently only works inside albums, not in root view

---

## Future Work (Phase 7+)

### Phase 7: Drag-and-Drop UI (Root Views)
- Add drag support to Images tab (image-library)
- Add drag support to Videos tab (video-library)
- Use `customRootMediaOrder` for persistence
- Same conditional logic as folders

### Phase 8: Testing & Polish
- Comprehensive testing in both apps
- Performance testing with large albums (1000+ items)
- Edge case testing (rapid reordering, interruptions)
- UX polish (haptic feedback, visual indicators)

### Phase 9: Documentation
- User-facing help documentation
- Update CHANGELOG.md
- Create demo video/screenshots

### Phase 10: Code Review & Merge
- Run full test suite
- Verify consistency script passes
- Create PR with `/pr-description` skill
- Merge to main

---

## Files Changed Summary

### Common Module (10 files)
- `SharedAppPreferences.kt` - Storage layer
- `BackupManager.kt` - Backup integration
- `SharedSettingsScreen.kt` - Settings UI
- `SharedFolderDetailScreen.kt` - Drag-and-drop UI
- (6 other utility/component files)

### Image-Library (5 files)
- `AppPreferences.kt` - Library-specific preferences
- `ImageRepository.kt` - Custom order application
- `ImageListViewModel.kt` - Reorder methods
- `FolderDetailScreen.kt` - Screen wrapper
- `ImageListScreen.kt` - Screen wiring
- `BackupManager.kt` - Backup integration

### Video-Library (5 files)
- `AppPreferences.kt` - Library-specific preferences
- `VideoRepository.kt` - Custom order application
- `VideoListViewModel.kt` - Reorder methods
- `FolderDetailScreen.kt` - Screen wrapper
- `VideoListScreen.kt` - Screen wiring
- `BackupManager.kt` - Backup integration

**Total: ~20 files modified**

---

## Related Documentation

- [MEDIA_REORDERING_ARCHITECTURE.md](./MEDIA_REORDERING_ARCHITECTURE.md) - Architecture analysis and approach comparison
- [MEDIA_REORDERING_CHECKLIST.md](./MEDIA_REORDERING_CHECKLIST.md) - Detailed implementation checklist with phase tracking

---

## Build & Install

```bash
# Build and install both apps
./gradlew :image-library:installDebug :video-library:installDebug

# Verify builds succeed
./gradlew :image-library:build -warnaserror
./gradlew :video-library:build -warnaserror

# Run consistency verification
./scripts/verify-consistency.ps1
```

---

## Next Steps

1. ✅ **Test folder drag-and-drop** - Enable setting, open album, test reordering
2. ⏳ **Implement root view drag-and-drop** (Phase 7) - Images/Videos tabs
3. ⏳ **Comprehensive testing** (Phase 8) - All scenarios in both apps
4. ⏳ **Documentation** (Phase 9) - User-facing help
5. ⏳ **Code review & merge** (Phase 10) - Create PR

**Status:** Ready for testing! 🎉

