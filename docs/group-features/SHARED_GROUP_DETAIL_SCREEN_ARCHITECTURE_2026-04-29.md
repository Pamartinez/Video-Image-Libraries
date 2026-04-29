# SharedGroupDetailScreen Architecture & Features

**Date**: April 29, 2026  
**Component**: `common/ui/screen/SharedGroupDetailScreen.kt`  
**Purpose**: Shared composable screen for displaying group details in both image-library and video-library

---

## 📋 Overview

`SharedGroupDetailScreen` is a highly reusable, generic composable that displays the contents of a single Group, including:
- Sub-groups (nested groups)
- Member folders (albums)
- Support for all common operations (selection, reordering, sorting, etc.)

This component enforces **behavioral consistency** between image-library and video-library by providing a single implementation for group detail screens.

---

## 🎯 Key Features

### 1. **Floating Top Bar Support**
The screen implements a sophisticated dual-header system:

#### **Inline Header** (scrolls with content)
- Rendered as the **first item** in the LazyVerticalGrid
- Visible when `firstVisibleItemIndex == 0` and scroll offset < 10
- Contains:
  - Circular back button with dark background
  - Group title and subtitle (album/group counts)
  - ActionsPill with Create (+), View Type toggle, and More menu (⋮)

#### **Floating Overlay Buttons** (always visible when scrolled)
- Appears when user scrolls down
- Rendered as separate overlays with `zIndex(20f)`
- Contains:
  - Back button (top-left, aligned with inline position)
  - More menu button (top-right, aligned with inline position)
- **Positioning details**:
  - Back button: `padding(start = 16.dp, top = 26.dp)`
  - More menu: `padding(end = 24.dp, top = 26.dp)` (accounts for ActionsPill padding)

#### **Selection Mode Header** (non-floating)
- Uses `ScreenTopBar` component
- Shows selection count and "Select All" button
- **Never floats** - always visible at top when in selection mode

### 2. **Drag-to-Reorder**
Allows users to manually reorder items when in custom sort order:

- **Enabled when**: `isCustomOrder(sortOption) == true`
- **Visual feedback**: Dragged item scales to 1.08x with blue border and shadow
- **Dimming**: Non-dragged items fade to 65% opacity during drag
- **Touch handling**: 
  - Long-press initiates drag
  - If not dragging, long-press triggers selection mode
  - Click events are consumed during drag (prevents accidental opens)
- **Index management**: 
  - Accounts for optional header row in grid (when floating mode is ON)
  - Converts layout indices to data indices before callbacks
  - Sets `minDragIndex = 1` when header is present (prevents dragging header)

### 3. **Selection Mode**
Multi-select functionality for bulk operations:

- **Triggers**: Long-press on any item (when not in custom sort)
- **Visual**: Checkboxes appear on all items
- **Bottom Action Bar**: Shows available actions (Move, Delete, Share, Group, Ungroup, etc.)
- **Select All**: Selects all items (both folders and groups)
- **Count Display**: Shows "X of Y selected"

### 4. **Group Creation Mode**
Special mode for selecting albums to include in a new group:

- **Activated by**: `isGroupCreationMode = true`
- **Differences from Selection Mode**:
  - Only folders (albums) show checkboxes
  - Groups are NOT selectable (no checkboxes)
  - Uses separate selection set: `groupCreationSelectedFolderIds`
  - Prevents selecting nested groups during group creation

### 5. **Create Menu Bottom Sheet**
Modal sheet for choosing what to create within the group:

- **Album Option**:
  - Icon: Collections
  - Description: Configurable via `albumCreationDescription` parameter
  - Action: Triggers `onCreateAlbum()`
  
- **Group Option**:
  - Icon: Folder
  - Description: "Create a group of related albums."
  - Action: Triggers `onGroup()` (creates sub-group)

- **Styling**: Samsung Gallery style with rounded corners (28.dp)

### 6. **Empty State**
Friendly empty state when group has no items:

- **Message**: "No items in this group"
- **Action Button**: "Add albums" with + icon
- **Centered**: Uses `Box` with `Alignment.Center`

---

## 🏗️ Architecture

### Generic Type Parameters

```kotlin
<ViewTypeEnum, SortOptionEnum>
```

Allows the component to work with different view type and sort option enums from each library.

### Component Slots (Dependency Injection)

The screen uses **composable slots** for maximum flexibility:

```kotlin
folderGridItem: @Composable (
    folder: FolderItem,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    viewType: ViewTypeEnum,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)?,
    isDragging: Boolean,
    modifier: Modifier
) -> Unit
```

This pattern allows:
- Image-library to render `ImageFolderGridItem`
- Video-library to render `VideoFolderGridItem`
- Both using the exact same screen logic

**Other slots**:
- `groupGridItem` - Renders group items
- `sortDialog` - Sort options dialog
- `selectionHeader` - Selection mode header
- `viewTypeToggle` - View type toggle button

### Configuration Parameters

Pure functions injected to abstract library-specific logic:

- `isLargeGrid(ViewTypeEnum) -> Boolean`
- `getColumnCount(ViewTypeEnum) -> Int`
- `getSpacing(ViewTypeEnum) -> Dp`
- `isCustomOrder(SortOptionEnum) -> Boolean`
- `sortMixedItems(List<MixedItem>, SortOptionEnum, Boolean) -> List<MixedItem>`

### Color Theming

Uses `LibraryColors` interface for consistent theming:
- `screenBackground`
- `iconColor`
- `listFirstText`, `listSecondText`
- `menuBg`
- `dividerColor`

---

## 📊 Data Flow

### Input Data
1. **Folders**: `List<FolderItem>` - Albums in this group
2. **SubGroups**: `List<GroupItem>` - Nested groups
3. **Ordered Items**: `List<Any>` - Pre-ordered list (if custom order enabled)

### Display List Construction

```kotlin
// 1. Build raw mixed list from ordered items or folders/groups
val rawMixed: List<MixedItem> = remember(orderedMixedItems, folders, subGroups) {
    if (orderedMixedItems.isNotEmpty()) {
        orderedMixedItems.mapNotNull { item ->
            when (item) {
                is GroupItem  -> MixedItem.Group(item)
                is FolderItem -> MixedItem.Folder(item)
                else          -> null
            }
        }
    } else {
        buildList {
            subGroups.forEach { add(MixedItem.Group(it)) }
            folders.forEach { add(MixedItem.Folder(it)) }
        }
    }
}

// 2. Sort the mixed list
val mixedItems: List<MixedItem> = remember(rawMixed, sortOption, groupsAlwaysOnTop) {
    sortMixedItems(rawMixed, sortOption, groupsAlwaysOnTop)
}
```

**Optimization**: Both lists are memoized to prevent expensive recomputations on every recomposition.

---

## 🎨 UI Structure

```
Box (screen container)
└── Column (main layout)
    ├── ScreenTopBar (when floating OFF or selection mode)
    │   ├── Back button
    │   ├── Title + Subtitle
    │   └── ActionsPill (Create, View Type, More)
    │
    └── Box (content area)
        ├── LazyVerticalGrid
        │   ├── Item 0: Header Row (when floating ON, not selection mode)
        │   │   └── Inline header (visible only when not scrolled)
        │   │       ├── Circular back button
        │   │       ├── Title + Subtitle
        │   │       └── ActionsPill
        │   │
        │   ├── Items 1..N: Folders and Groups
        │   │   ├── FolderGridItem
        │   │   └── GroupGridItem
        │   │
        │   └── (or) Empty State
        │       └── "No items" + "Add albums" button
        │
        ├── Drag Overlay (when dragging)
        │   └── Scaled copy of dragged item
        │
        └── Floating Overlay Buttons (when scrolled, floating ON)
            ├── Back button (top-left)
            └── More menu (top-right)
    
    └── BottomActionBar (when selection mode)
        └── Actions (Copy, Move, Delete, Group, Ungroup, etc.)

ModalBottomSheet (Create menu)
└── "Choose what to create"
    ├── Album option
    └── Group option
```

---

## 🔄 State Management

### Local State
- `showMoreMenu` - More menu visibility
- `showSortDialog` - Sort dialog visibility
- `showCreateMenu` - Create menu bottom sheet visibility

### Derived State
```kotlin
val firstVisibleItemIndex by remember {
    derivedStateOf { lazyGridState.firstVisibleItemIndex }
}
val firstVisibleItemScrollOffset by remember {
    derivedStateOf { lazyGridState.firstVisibleItemScrollOffset }
}
```

These derived states control the inline vs. floating header visibility:
- `showInline = firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset < 10`
- `showFloating = !showInline`

### Selection State
- `selectedFolderIds: Set<Int>` - Selected album bucket IDs
- `selectedGroupIds: Set<Long>` - Selected group IDs
- `groupCreationSelectedFolderIds: Set<Int>` - Albums selected during group creation

---

## 🎭 Behavioral Rules

### 1. **Dialog Rendering Rule**
All dialogs (sort, more menu, create menu) are rendered **at the bottom of the composable**, controlled by state flags only (no context conditionals).

### 2. **Header Presence Detection**
The grid must know if a header row is present to correctly handle drag indices:

```kotlin
val hasHeaderRow = floatingTopBarEnabled && !isSelectionMode
```

This affects:
- Drag-to-reorder index calculations
- `minDragIndex` setting (prevents dragging header)

### 3. **Selection Mode Priority**
Selection mode **always** uses `ScreenTopBar` (never floats), even when `floatingTopBarEnabled = true`.

### 4. **Empty State Handling**
When `mixedItems.isEmpty()`, show empty state instead of grid.

---

## 🧪 Testing Checklist

When modifying this component, verify:

- ✅ **Both Libraries**: Test in image-library AND video-library
- ✅ **Floating Mode**: Test with floating ON and OFF
- ✅ **Selection Mode**: Verify header switches to non-floating
- ✅ **Drag-to-Reorder**: Test dragging in custom sort order
- ✅ **Group Creation**: Verify only folders are selectable
- ✅ **Empty State**: Test with empty group
- ✅ **Nested Navigation**: Test opening sub-groups
- ✅ **Scroll Behavior**: Verify header visibility transitions smoothly
- ✅ **Index Calculations**: Verify drag indices are correct with/without header
- ✅ **Create Menu**: Test both album and group creation flows
- ✅ **More Menu**: Test all menu actions
- ✅ **Bottom Actions**: Test all selection mode actions

---

## 📦 Usage Example

### Image Library

```kotlin
SharedGroupDetailScreen(
    groupName = state.currentGroupName ?: "Group",
    folders = state.currentGroupFolders,
    subGroups = state.currentGroupSubGroups,
    viewType = state.folderViewType,
    sortOption = state.currentGroupSortOption,
    isSelectionMode = state.isSelectionMode,
    selectedFolderIds = state.selectedFolderIds,
    selectedGroupIds = state.selectedGroupIds,
    isGroupCreationMode = state.isGroupCreationMode,
    groupCreationSelectedFolderIds = state.groupCreationSelectedFolderIds,
    onBack = { viewModel.exitGroup() },
    onFolderClick = { viewModel.onFolderClick(it) },
    onFolderLongClick = { viewModel.enterSelectionMode(it) },
    onGroupClick = { viewModel.openGroup(it.groupId) },
    onGroupLongClick = { viewModel.enterSelectionModeWithGroup(it) },
    // ... other callbacks ...
    colors = ImageColors,
    albumCreationDescription = "Copy or move images to a new album.",
    isLargeGrid = { it == FolderViewType.LARGE_GRID },
    getColumnCount = { if (it == FolderViewType.LARGE_GRID) 2 else 3 },
    getSpacing = { if (it == FolderViewType.LARGE_GRID) 8.dp else 6.dp },
    isCustomOrder = { it == FolderSortOption.CUSTOM },
    sortMixedItems = { items, sort, groupsOnTop -> 
        sortMixedItemsList(items, sort, groupsOnTop) 
    },
    folderGridItem = { folder, isSelected, isSelectionMode, viewType, onClick, onLongClick, isDragging, modifier ->
        ImageFolderGridItem(
            folder = folder,
            isSelected = isSelected,
            isSelectionMode = isSelectionMode,
            // ... other props ...
        )
    },
    groupGridItem = { group, isSelected, isSelectionMode, viewType, onClick, onLongClick, isDragging, modifier ->
        GroupGridItem(
            group = group,
            isSelected = isSelected,
            isSelectionMode = isSelectionMode,
            // ... other props ...
        )
    },
    sortDialog = { currentSort, onSortSelected, onDismiss ->
        FolderSortDialog(
            currentSortOption = currentSort,
            onSortOptionSelected = onSortSelected,
            onDismiss = onDismiss
        )
    },
    selectionHeader = { selectedCount, totalCount, allSelected, onSelectAll, onCancel ->
        ImageSelectionHeader(
            selectedCount = selectedCount,
            totalCount = totalCount,
            allSelected = allSelected,
            onSelectAll = onSelectAll,
            onCancel = onCancel
        )
    },
    viewTypeToggle = { viewType, onClick ->
        FolderViewTypeToggle(viewType = viewType, onClick = onClick)
    },
    lazyGridState = rememberLazyGridState()
)
```

### Video Library

Nearly identical usage, but with:
- `VideoColors` instead of `ImageColors`
- `"Copy or move videos to a new album."` description
- `VideoFolderGridItem` instead of `ImageFolderGridItem`
- Video-specific view types and sort options

---

## 🎯 Consistency Guarantees

This shared component ensures:

1. ✅ **Identical UX**: Both libraries have the exact same group detail screen behavior
2. ✅ **Identical Layout**: Same header structure, spacing, colors
3. ✅ **Identical Actions**: Same menu items, same bottom bar actions
4. ✅ **Identical State Management**: Same selection logic, same drag behavior
5. ✅ **Identical Animations**: Same transitions, same scroll behavior

**Any change to group detail screen behavior automatically applies to BOTH libraries.**

---

## 🔧 Maintenance Guidelines

### When Adding a New Feature

1. **Add to this shared component** - Don't duplicate in library-specific screens
2. **Use slots if library-specific** - Inject behavior via parameters/slots
3. **Test in both libraries** - Always verify identical behavior
4. **Update this document** - Document the new feature here

### When Fixing a Bug

1. **Fix in shared component** - Single fix applies to both libraries
2. **Verify fix in both apps** - Test image-library AND video-library
3. **Update tests** - Ensure regression doesn't happen

### When Refactoring

1. **Maintain slot signatures** - Don't break library-specific implementations
2. **Preserve behavior** - Don't change UX without intentional decision
3. **Verify both libraries compile** - Ensure no breaking changes

---

## 📚 Related Documentation

- [GROUP_CREATION_CONSISTENCY_2026-04-28.md](GROUP_CREATION_CONSISTENCY_2026-04-28.md) - Group creation workflow consistency
- [GROUP_CREATION_INSIDE_GROUP_FIX_2026-04-28.md](GROUP_CREATION_INSIDE_GROUP_FIX_2026-04-28.md) - Fixed group creation inside groups
- [BEHAVIORAL_CONSISTENCY_QUICK_REFERENCE.md](../behavioral-consistency/CONSISTENCY_QUICK_REFERENCE.md) - Consistency rules
- [FLOATING_TOP_BAR_IMPLEMENTATION_SUMMARY.md](../floating-top-bar/FLOATING_TOP_BAR_IMPLEMENTATION_SUMMARY.md) - Floating header system

---

## 🏆 Success Criteria

This component successfully achieves:

✅ **Single Source of Truth** - One implementation for both libraries  
✅ **Maximum Reusability** - Generic types and slots enable library-specific customization  
✅ **Behavioral Consistency** - Identical UX across both apps  
✅ **Performance** - Memoized computations prevent unnecessary work  
✅ **Maintainability** - Clear architecture and well-documented behavior  
✅ **Testability** - Easy to verify behavior in both libraries  

---

**Last Updated**: April 29, 2026  
**Component Version**: 1.0  
**Status**: ✅ Production Ready

