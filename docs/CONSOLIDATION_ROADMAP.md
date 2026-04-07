# Common Module Consolidation Roadmap

## 🎯 Vision
Maximize code reuse between image-library and video-library by moving all shared logic to the common module while preserving library-specific customization.

---

## ✅ Phase 1: Quick Wins (COMPLETED)

### SearchScreen.kt ✅
- **Status:** Completed April 7, 2026
- **Result:** Video-library now uses SharedSearchScreen
- **Impact:** 140 lines eliminated, identical behavior guaranteed

---

## 🚀 Phase 2: Major Consolidations (READY TO START)

### 1. GroupDetailScreen.kt ⭐⭐⭐⭐⭐

**Estimated Effort:** 4-6 hours  
**Impact:** ~900 lines consolidated  
**Priority:** CRITICAL (most complex shared screen)

#### Implementation Plan:

**Step 1: Create SharedGroupDetailScreen.kt in common** (2-3 hours)
```kotlin
package com.example.common.ui.screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <ViewTypeEnum, SortOptionEnum> SharedGroupDetailScreen(
    groupName: String,
    folders: List<FolderItem>,
    subGroups: List<GroupItem>,
    viewType: ViewTypeEnum,
    sortOption: SortOptionEnum,
    isSelectionMode: Boolean,
    selectedFolderIds: Set<Int>,
    selectedGroupIds: Set<Long>,
    onBack: () -> Unit,
    onFolderClick: (FolderItem) -> Unit,
    onFolderLongClick: (FolderItem) -> Unit,
    onGroupClick: (GroupItem) -> Unit,
    onGroupLongClick: (GroupItem) -> Unit,
    
    // Injected dependencies
    colors: LibraryColors,
    
    // Component slots
    folderGridItem: @Composable (
        folder: FolderItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        viewType: ViewTypeEnum,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        modifier: Modifier
    ) -> Unit,
    
    groupGridItem: @Composable (
        group: GroupItem,
        isSelected: Boolean,
        isSelectionMode: Boolean,
        viewType: ViewTypeEnum,
        onClick: () -> Unit,
        onLongClick: (() -> Unit)?,
        isDragging: Boolean,
        modifier: Modifier
    ) -> Unit,
    
    sortDialog: @Composable (
        currentSortOption: SortOptionEnum,
        onSortOptionSelected: (SortOptionEnum) -> Unit,
        onDismiss: () -> Unit
    ) -> Unit,
    
    selectionHeader: @Composable RowScope.(
        selectedCount: Int,
        allSelected: Boolean,
        onSelectAll: () -> Unit,
        onCancel: () -> Unit
    ) -> Unit,
    
    viewTypeToggle: @Composable (
        viewType: ViewTypeEnum,
        onClick: () -> Unit
    ) -> Unit,
    
    // Configuration
    albumCreationDescription: String,
    isLargeGrid: (ViewTypeEnum) -> Boolean,
    getColumnCount: (ViewTypeEnum) -> Int,
    getSpacing: (ViewTypeEnum) -> Dp,
    isCustomOrder: (SortOptionEnum) -> Boolean,
    sortItems: (List<MixedItem>, SortOptionEnum, Boolean) -> List<MixedItem>,
    
    // ... rest of parameters
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState = rememberLazyGridState()
) {
    // All the shared logic from GroupDetailScreen
}
```

**Step 2: Create wrapper in video-library** (1 hour)
```kotlin
package com.videolibrary.ui.screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDetailScreen(
    groupName: String,
    folders: List<FolderItem>,
    subGroups: List<GroupItem>,
    viewType: ViewType,
    // ... all existing parameters
) {
    SharedGroupDetailScreen(
        groupName = groupName,
        folders = folders,
        subGroups = subGroups,
        viewType = viewType,
        sortOption = sortOption,
        // ... rest of parameters
        
        colors = LocalVideoColors.current,
        
        folderGridItem = { folder, isSelected, isSelectionMode, vt, onClick, onLongClick, isDragging, mod ->
            FolderGridItem(
                folder = folder,
                isSelected = isSelected,
                isSelectionMode = isSelectionMode,
                viewType = vt,
                onClick = onClick,
                onLongClick = onLongClick,
                isDragging = isDragging,
                modifier = mod
            )
        },
        
        groupGridItem = { group, isSelected, isSelectionMode, vt, onClick, onLongClick, isDragging, mod ->
            GroupGridItem(
                group = group,
                isSelected = isSelected,
                isSelectionMode = isSelectionMode,
                viewType = vt,
                onClick = onClick,
                onLongClick = onLongClick,
                isDragging = isDragging,
                modifier = mod
            )
        },
        
        sortDialog = { current, onSelected, onDismiss ->
            FolderSortDialog(
                currentSortOption = current,
                onSortOptionSelected = onSelected,
                onDismiss = onDismiss
            )
        },
        
        selectionHeader = { count, allSel, onAll, onCancel ->
            SelectionModeHeader(
                selectedCount = count,
                totalCount = mixedItems.size,
                onSelectAll = onAll,
                onCancel = onCancel
            )
        },
        
        viewTypeToggle = { vt, onClick ->
            ViewTypeToggleButton(viewType = vt, onClick = onClick)
        },
        
        albumCreationDescription = "Create a new album and add videos manually.",
        isLargeGrid = { it == ViewType.GRID_LARGE },
        getColumnCount = { if (it == ViewType.GRID_LARGE) 2 else 3 },
        getSpacing = { if (it == ViewType.GRID_LARGE) 18.dp else 12.dp },
        isCustomOrder = { it == FolderSortOption.CUSTOM_ORDER },
        sortItems = { items, sort, groupsTop -> sortMixedItems(items, sort, groupsTop) },
        
        lazyGridState = lazyGridState
    )
}
```

**Step 3: Create wrapper in image-library** (1 hour)
```kotlin
// Similar pattern to video-library, with image-specific components
```

**Step 4: Test both libraries** (30 minutes)
- Verify drag-to-reorder works
- Verify sort options work
- Verify selection mode works
- Verify all menu actions work

---

### 2. FoldersTab.kt ⭐⭐⭐⭐

**Estimated Effort:** 4-6 hours  
**Impact:** ~800 lines consolidated  
**Priority:** HIGH (core folder/group display)

#### Implementation Plan:

Similar pattern to GroupDetailScreen:
1. Create SharedFoldersTab in common
2. Inject grid/list item composables as parameters
3. Handle LIST view via optional parameter
4. Create thin wrappers in both libraries

**Key Consideration:** Video-library supports LIST view, image-library doesn't. Solution: Make `listContent` parameter nullable.

---

### 3. FolderDetailScreen.kt ⭐⭐⭐

**Estimated Effort:** 3-4 hours  
**Impact:** ~320 lines consolidated  
**Priority:** MEDIUM (simpler than group screens)

#### Implementation Plan:

1. Create SharedFolderDetailScreen in common
2. Accept media item type as generic parameter
3. Inject grid item composable
4. Handle TabContentScaffold difference (video-library only)

---

## 📋 Phase 3: Minor Improvements

### SelectionHeader.kt Unification
- **Effort:** 30 minutes
- **Impact:** Standardize import patterns
- **Action:** Image-library should remove SelectionHeader.kt and use ScreenChrome.kt pattern

### MixedFolderItem.kt Standardization  
- **Effort:** 15 minutes
- **Impact:** Consistent patterns across libraries
- **Action:** Add same typealias wrapper to image-library's FoldersTab.kt

---

## 🎯 Success Metrics

### Target Code Reuse Percentage
- **Current:** ~60%
- **After Phase 2:** ~80%
- **Ultimate Goal:** 85-90% (some media-specific code must remain)

### Target Duplicate Lines
- **Current:** ~2,000 duplicate lines
- **After Phase 2:** <500 duplicate lines
- **Ultimate Goal:** <200 duplicate lines

### Quality Metrics
- ✅ Zero behavioral differences between libraries
- ✅ All common operations work identically
- ✅ Single source of truth for all shared logic
- ✅ Easy to maintain and extend

---

## 🔧 Implementation Guidelines

### Before Starting Each Screen:

1. **Read both versions completely**
2. **List all differences** (colors, types, components)
3. **Identify abstraction points** (what needs to be injected)
4. **Design the generic signature** (type parameters, slots)
5. **Write common version first**
6. **Create wrappers second**
7. **Test thoroughly in both apps**

### Coding Standards:

- **Use generic type parameters** for library-specific enums
- **Inject colors** via LibraryColors parameter
- **Use component slots** for library-specific UI (FolderGridItem, etc.)
- **Prefer configuration over conditionals** (pass lambda/value, don't branch on library)
- **Document thoroughly** (what each parameter does, why it's needed)
- **Test exhaustively** (every feature, every mode, every edge case)

### Anti-Patterns to Avoid:

❌ **Don't use conditionals based on library**
```kotlin
// BAD
if (isVideoLibrary) { ... } else { ... }
```

✅ **Do inject behavior as parameters**
```kotlin
// GOOD
gridItemRenderer: @Composable (item) -> Unit
```

❌ **Don't hardcode library-specific values**
```kotlin
// BAD
val spacing = 4.dp  // assumes video-library
```

✅ **Do pass configuration**
```kotlin
// GOOD
spacing: Dp
```

---

## 📊 Estimated Timeline

### Conservative Estimate:
- **Phase 2 (3 screens):** 11-16 hours total
  - GroupDetailScreen: 4-6 hours
  - FoldersTab: 4-6 hours
  - FolderDetailScreen: 3-4 hours

### Realistic Timeline:
- **Week 1:** GroupDetailScreen
- **Week 2:** FoldersTab
- **Week 3:** FolderDetailScreen + testing + documentation

### Aggressive Timeline:
- **3-4 focused days** (if working full-time on this)

---

## 🏆 Expected Outcomes

### After Phase 2 Completion:

#### Quantitative Benefits:
- **~2,020 fewer duplicate lines**
- **~80% code reuse**
- **3 critical screens unified**

#### Qualitative Benefits:
- **Guaranteed consistency:** Impossible for behaviors to diverge
- **Faster development:** New features auto-apply to both libraries
- **Easier maintenance:** Fix once, works everywhere
- **Better testing:** Test shared logic once
- **Cleaner codebase:** Clear separation of concerns

#### Risk Mitigation:
- **Lower bug density:** Less code = fewer bugs
- **Easier onboarding:** New developers see shared pattern
- **Future-proof:** New libraries can reuse same common code

---

## 📝 Next Actions

### Immediate (Today):
1. ✅ Review this roadmap
2. ✅ Understand the consolidation pattern
3. ✅ Prepare to start GroupDetailScreen

### This Week:
1. Implement SharedGroupDetailScreen
2. Create wrappers in both libraries
3. Test thoroughly
4. Document any gotchas

### This Month:
1. Complete all Phase 2 screens
2. Update documentation
3. Verify 80%+ code reuse achieved
4. Plan Phase 3 improvements

---

**Document Status:** Living document - update as work progresses  
**Last Updated:** April 7, 2026  
**Next Review:** After GroupDetailScreen consolidation

