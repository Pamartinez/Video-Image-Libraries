# Quick Wins: Code Consolidation Checklist
**Date:** April 8, 2026  
**Priority:** HIGH - Following "Common-First Rule" violations

## 🎯 Quick Summary

**Current state:**
- ImageListViewModel: 2164 lines
- VideoListViewModel: 1977 lines
- Total duplication: ~85-90% (3700+ lines)

**After consolidation:**
- BaseMediaListViewModel: ~1800 lines (shared)
- ImageListViewModel: ~300 lines (image-specific)
- VideoListViewModel: ~300 lines (video-specific)
- **Code reduction: 47%** (4141 → 2400 lines)

---

## ✅ Phase 1: Data Classes (1-2 hours)

### 1.1 CopyMoveProgress (DUPLICATE)
**Current:** Defined in both ViewModels (100% identical)
**Action:** 
```bash
Create: common/src/main/java/com/example/common/data/model/CopyMoveProgress.kt
Update: Both ViewModels to use common.data.model.CopyMoveProgress
```

**File:**
```kotlin
package com.example.common.data.model

data class CopyMoveProgress(
    val isActive: Boolean = false,
    val title: String = "",
    val current: Int = 0,
    val total: Int = 0
)
```

---

### 1.2 FileConflict (DUPLICATE)
**Current:** Defined in both ViewModels (100% identical)
**Action:**
```bash
Create: common/src/main/java/com/example/common/data/model/FileConflict.kt
Update: Both ViewModels to use common.data.model.FileConflict
```

**File:**
```kotlin
package com.example.common.data.model

import kotlinx.coroutines.CompletableDeferred

data class FileConflict(
    val fileName: String,
    val deferred: CompletableDeferred<ConflictResolution>,
    var applyToAll: Boolean = false
)
```

---

## ✅ Phase 2: Helper Functions (2-3 hours)

### 2.1 MixedItemSorter Utilities (DUPLICATE)
**Current:** sortMixedItems(), sortHideScreenItems(), applyCustomMixedOrder() duplicated in both ViewModels
**Action:**
```bash
Create: common/src/main/java/com/example/common/data/util/MixedItemSorter.kt
```

**File:**
```kotlin
package com.example.common.data.util

import com.example.common.data.model.FolderItem
import com.example.common.data.model.FolderSortOption
import com.example.common.data.model.GroupItem

object MixedItemSorter {
    
    /**
     * Sort combined list of GroupItem + FolderItem by sort option.
     * When groupsAlwaysOnTop is true, groups are sorted separately and placed first.
     */
    fun sortMixedItems(
        items: List<Any>,
        option: FolderSortOption,
        groupsAlwaysOnTop: Boolean = false
    ): List<Any> {
        fun itemName(item: Any) = when (item) {
            is GroupItem  -> item.name
            is FolderItem -> item.name
            else          -> ""
        }
        fun itemCount(item: Any) = when (item) {
            is GroupItem  -> item.totalItemCount
            is FolderItem -> item.itemCount
            else          -> 0
        }
        fun sortList(list: List<Any>): List<Any> = when (option) {
            FolderSortOption.NAME_A_TO_Z        -> list.sortedBy { itemName(it).lowercase() }
            FolderSortOption.NAME_Z_TO_A        -> list.sortedByDescending { itemName(it).lowercase() }
            FolderSortOption.ITEMS_MOST_FIRST   -> list.sortedByDescending { itemCount(it) }
            FolderSortOption.ITEMS_FEWEST_FIRST -> list.sortedBy { itemCount(it) }
            FolderSortOption.CUSTOM_ORDER       -> list
        }
        return if (groupsAlwaysOnTop) {
            val groups  = items.filterIsInstance<GroupItem>()
            val folders = items.filterIsInstance<FolderItem>()
            sortList(groups) + sortList(folders)
        } else {
            sortList(items)
        }
    }
    
    /**
     * Apply custom mixed order from preferences, handling new/deleted items.
     */
    fun applyCustomMixedOrder(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        savedOrder: List<String>
    ): List<Any> {
        val groupMap  = groups.associateBy { "g_${it.groupId}" }
        val folderMap = folders.associateBy { "f_${it.bucketId}" }
        
        if (savedOrder.isEmpty()) {
            return groups + folders
        }
        
        val savedSet   = savedOrder.toSet()
        val ordered    = savedOrder.mapNotNull { key -> groupMap[key] ?: folderMap[key] }
        val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
        val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
        
        return newGroups + newFolders + ordered
    }
    
    /**
     * Sort items for Hide Folders screen.
     */
    fun sortHideScreenItems(
        groups: List<GroupItem>,
        folders: List<FolderItem>,
        sortOption: FolderSortOption,
        groupsAlwaysOnTop: Boolean,
        customOrder: List<String>
    ): Pair<List<GroupItem>, List<FolderItem>> {
        if (sortOption == FolderSortOption.CUSTOM_ORDER && customOrder.isNotEmpty()) {
            val groupMap  = groups.associateBy { "g_${it.groupId}" }
            val folderMap = folders.associateBy { "f_${it.bucketId}" }
            val savedSet  = customOrder.toSet()
            val newGroups  = groups.filter { "g_${it.groupId}" !in savedSet }
            val newFolders = folders.filter { "f_${it.bucketId}" !in savedSet }
            val ordered    = customOrder.mapNotNull { groupMap[it] ?: folderMap[it] }
            val result     = newGroups + newFolders + ordered
            return result.filterIsInstance<GroupItem>() to result.filterIsInstance<FolderItem>()
        }
        val sorted = sortMixedItems(groups + folders, sortOption, groupsAlwaysOnTop)
        return sorted.filterIsInstance<GroupItem>() to sorted.filterIsInstance<FolderItem>()
    }
}
```

**Update both ViewModels:** Remove duplicated functions, import from common

---

### 2.2 FilePathUtils (DUPLICATE)
**Current:** destFolderName() duplicated in both ViewModels
**Action:**
```bash
Create: common/src/main/java/com/example/common/util/FilePathUtils.kt
```

**File:**
```kotlin
package com.example.common.util

object FilePathUtils {
    /**
     * Extract folder name from full path.
     * Example: "/storage/emulated/0/DCIM/Camera/" -> "Camera"
     */
    fun destFolderName(path: String): String {
        return path.trimEnd('/').substringAfterLast('/')
    }
    
    /**
     * Generate unique group name by appending (2), (3), etc.
     * Example: "My Group" -> "My Group (2)" if "My Group" exists
     */
    fun generateUniqueGroupName(baseName: String, existingNames: Set<String>): String {
        var name = baseName
        var counter = 2
        while (name in existingNames) {
            name = "$baseName ($counter)"
            counter++
        }
        return name
    }
}
```

**Update both ViewModels:** Remove duplicated functions, import from common

---

## ✅ Phase 3: Base ViewModel (1 week)

### 3.1 BaseMediaListViewModel Structure

**Create:** `common/src/main/java/com/example/common/ui/viewmodel/BaseMediaListViewModel.kt`

**Strategy:**
1. Generic parameters: `<MediaItem, MediaSortOption>`
2. Abstract methods for media-specific operations:
   - `abstract suspend fun getMediaItems(sortOption: MediaSortOption, bucketId: Int?, searchQuery: String?): List<MediaItem>`
   - `abstract suspend fun deleteMediaItems(ids: List<Long>)`
   - `abstract suspend fun renameMediaItem(id: Long, name: String)`
   - `abstract fun sortMediaItemsInMemory(items: List<MediaItem>, option: MediaSortOption): List<MediaItem>`
   - `abstract fun createMediaUri(id: Long): Uri`
3. All other functions implemented in base class
4. Share state flows and private fields

**Benefits:**
- ~1800 lines of shared logic
- Guaranteed behavioral consistency
- Single source of truth
- Easier testing (test base once)

---

## ✅ Phase 4: Base Repository (3-4 days)

### 4.1 BaseMediaRepository Structure

**Create:** `common/src/main/java/com/example/common/data/repository/BaseMediaRepository.kt`

**Strategy:**
1. Generic parameters: `<MediaItem, MediaSortOption>`
2. Abstract properties:
   - `abstract val mediaUri: Uri`
   - `abstract val mimeTypePrefix: String` ("image/*" or "video/*")
3. Abstract methods:
   - `abstract fun buildProjection(): Array<String>`
   - `abstract fun buildMediaItem(cursor: Cursor, ...): MediaItem`
   - `abstract fun buildSortOrder(sortOption: MediaSortOption): String`
4. Shared implementation:
   - All CRUD operations
   - Folder loading logic
   - Copy/move operations (delegate to MediaTransferHelper)
   - Delete operations

**Benefits:**
- ~400 lines of shared logic
- Consistent MediaStore query patterns
- Easier to add new media types in future

---

## 📊 Testing Checklist

After each phase, verify in **BOTH** apps:

### Phase 1 (Data Classes):
- [ ] Copy operation shows progress dialog
- [ ] File conflict dialog appears on name clash
- [ ] "Apply to all" checkbox works

### Phase 2 (Helper Functions):
- [ ] Custom order drag-and-drop works
- [ ] Group/folder sorting respects all sort options
- [ ] Hide folders screen shows correct order
- [ ] New groups/folders appear in correct position

### Phase 3 (Base ViewModel):
- [ ] All folder operations work (open, close, create, delete, rename)
- [ ] All group operations work (create, open, close, rename, destroy, add, remove)
- [ ] Copy/move operations work with progress and conflicts
- [ ] Selection mode works (select, deselect, select all)
- [ ] Hide folders screen works
- [ ] Backup/restore works
- [ ] Auto-backup triggers correctly
- [ ] Create album flow works

### Phase 4 (Base Repository):
- [ ] Media items load correctly
- [ ] Folders load with correct previews
- [ ] Sort order is respected
- [ ] Search works
- [ ] CRUD operations work (create, read, update, delete)
- [ ] Copy/move operations work
- [ ] Independent sort for albums works

---

## 🚨 Gotchas to Watch For

1. **Media-specific fields:** ImageItem has `dateTaken`, VideoItem has `duration` - handle in abstract methods
2. **Sort options:** ImageSortOption vs VideoSortOption have different values - parameterize
3. **MIME types:** "image/*" vs "video/*" - abstract property
4. **MediaStore URIs:** Images.Media vs Video.Media - abstract property
5. **Carousel vs Player:** Image has carousel, Video has instant player - keep in subclasses
6. **Settings:** carouselShowBarsOnOpen (Image) vs instantPlayerEnabled (Video) - keep in subclasses

---

## 💡 Tips

1. **Start small:** Extract data classes first, verify both apps work
2. **Test in both apps:** After EVERY change, test in BOTH apps
3. **Use git branches:** Create feature branch for each phase
4. **Keep backups:** Commit working code before major refactors
5. **Follow naming:** Use `BaseXXX` for abstract base classes
6. **Document generics:** Add KDoc explaining type parameters
7. **Use sealed interfaces:** Consider `sealed interface MediaItem` if needed
8. **Incremental migration:** Don't try to move everything at once

---

## ✅ Done When...

- [ ] Both ViewModels extend BaseMediaListViewModel
- [ ] Both Repositories extend BaseMediaRepository
- [ ] All duplicated data classes moved to common
- [ ] All duplicated helper functions moved to common
- [ ] All tests passing in both apps
- [ ] Both apps behave identically for all common operations
- [ ] Code reduction: 47% in ViewModels, 46% in Repositories
- [ ] Documentation updated with new architecture

**Total estimated time:** 2-3 weeks (following incremental approach)

**Total estimated impact:** 
- ~4500 lines of duplicate code eliminated
- Guaranteed behavioral consistency
- Easier maintenance and feature development
- Reduced test surface area

