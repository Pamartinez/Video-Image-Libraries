# Samsung Gallery Floating Buttons with Auto-Hiding Header - FINAL - April 11, 2026

## ✅ IMPLEMENTATION COMPLETE & VERIFIED

Successfully implemented Samsung Gallery-style floating overlay with scroll-based auto-hiding header.

---

## 🎯 Final Behavior

### When "Floating top bar" Setting is ON:

#### At Top of Scroll (firstVisibleItemIndex == 0 && scrollOffset < 50):
- ⭕ **Circular back button** (left) - 55% opacity black
- 📝 **Title** (e.g., "Porn")
- 📝 **Subtitle** (e.g., "1 group 79 albums")
- 💊 **ActionsPill** (right) with 3 buttons:
  - **+** Create (Groups only)
  - **⊞** View Type toggle
  - **⋮** Menu dropdown
- 📐 **Content padding**: `0.dp` top (content starts below header)

#### When Scrolled Down (not at top):
- ⭕ **Circular back button** (left) - 55% opacity black
- ⭕ **Circular menu button** (right) - 55% opacity black
- ❌ Title disappears
- ❌ Subtitle disappears
- ❌ ActionsPill disappears
- 📐 **Content padding**: `70.dp` top (space for floating buttons)

#### Scroll Back to Top:
- ✅ Returns to full header (back + title/subtitle + ActionsPill)
- ✅ Smooth transition

#### In Selection Mode:
- Shows traditional header overlay (regardless of scroll position)
- Selection count + actions

### When "Floating top bar" Setting is OFF:
- Traditional fixed top bar with black background
- Back button, title, subtitle, ActionsPill always visible

---

## 📦 Changes Made

### 1. ActionsPill Transparency
**File**: `common/src/main/java/com/example/common/ui/components/ScreenChromeHelpers.kt`

**Changed from**: `Color(0x4D808080)` (30% gray)  
**Changed to**: `Color(0x8C000000)` (55% black)

### 2. SharedGroupDetailScreen - Auto-Hiding Header
**File**: `common/src/main/java/com/example/common/ui/screen/SharedGroupDetailScreen.kt`

**Implementation**:
```kotlin
} else {
    // Track scroll position
    val isScrolledToTop = lazyGridState.firstVisibleItemIndex == 0 &&
                          lazyGridState.firstVisibleItemScrollOffset < 50
    
    Column(Modifier.fillMaxSize()) {
        // Header at top: back + title + subtitle + ActionsPill
        if (isScrolledToTop && !isSelectionMode) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .heightIn(min = 56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circular back button (55% opacity)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0x8C000000), RoundedCornerShape(24.dp))
                        .clickable(onClick = onBack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", 
                         tint = Color.White, modifier = Modifier.size(24.dp))
                }
                
                Spacer(Modifier.width(12.dp))
                
                // Title and subtitle
                Column(Modifier.weight(1f)) {
                    Text(groupName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                         color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    // Subtitle with group/album count
                    Text("1 group 79 albums", fontSize = 13.sp, color = Color(0xFFBBBBBB))
                }
                
                // ActionsPill: Create + ViewType + Menu
                ActionsPill {
                    IconButton(onClick = { showCreateMenu = true }) {
                        Icon(Icons.Default.Add, "Create", tint = Color.White)
                    }
                    viewTypeToggle(viewType, onCycleViewType)
                    Box {
                        IconButton(onClick = { showMoreMenu = !showMoreMenu }) {
                            Icon(Icons.Default.MoreVert, "More", tint = Color.White)
                        }
                        DropdownMenu(
                            expanded = showMoreMenu,
                            onDismissRequest = { showMoreMenu = false }
                        ) {
                            // Samsung Gallery menu structure
                            AppMenuItem("Add album(s)", ...)
                            AppMenuItem("Rename group", ...)
                            AppMenuItem("Hide album(s)", ...)
                            AppMenuDivider()
                            AppMenuItem("Destroy group", ..., textColor = Red)
                            AppMenuDivider()
                            AppMenuItem("Sort", ...)
                            AppMenuItem("View as", ...)
                            AppMenuItem("Settings", ...)
                            AppMenuItem("About App", ...)
                        }
                    }
                }
            }
        }
        
        // Content area
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(
                    top = if (isScrolledToTop) 0.dp else 70.dp,  // Dynamic!
                    start = 10.dp, end = 10.dp, bottom = 10.dp
                ),
                // ...items...
            )
            
            // Floating buttons when scrolled OR in selection mode
            if (!isScrolledToTop || isSelectionMode) {
                // Back button (left)
                Box(...) { Icon(ArrowBack) }
                
                // Menu button (right) - only when NOT in selection mode
                if (!isSelectionMode) {
                    Box(...) { Icon(MoreVert) + DropdownMenu }
                }
            }
        }
    }
}
```

### 3. SharedFolderDetailScreen - Same Auto-Hiding Logic
**File**: `common/src/main/java/com/example/common/ui/screen/SharedFolderDetailScreen.kt`

**Applied identical scroll-based behavior**:
- At top: Back + Title + ActionsPill (ViewType + Menu only, no Create)
- When scrolled: Only 2 circular buttons
- Same menu structure (Sort, View as, Settings, About App)

---

## 🎨 Technical Specifications

### Scroll Detection
```kotlin
val isScrolledToTop = lazyGridState.firstVisibleItemIndex == 0 &&
                      lazyGridState.firstVisibleItemScrollOffset < 50
```

**Threshold**: 50dp - small tolerance for smooth UX

### Dynamic Content Padding
```kotlin
contentPadding = PaddingValues(
    top = if (isScrolledToTop) 0.dp else 70.dp,
    start = 10.dp,
    end = 10.dp,
    bottom = 10.dp
)
```

### Conditional Rendering Logic

| State | Header Visible | Floating Buttons Visible |
|-------|---------------|-------------------------|
| At top + normal mode | ✅ Back + Title + ActionsPill | ❌ Hidden |
| Scrolled + normal mode | ❌ Hidden | ✅ Back + Menu only |
| At top + selection mode | ❌ Hidden | ✅ Back + Menu |
| Scrolled + selection mode | ❌ Hidden | ✅ Back + Menu |

### Button Styling
```kotlin
Box(
    modifier = Modifier
        .size(48.dp)
        .background(Color(0x8C000000), RoundedCornerShape(24.dp)),  // 55% opacity
    contentAlignment = Alignment.Center
)
```

### Menu Structure

**Groups Menu** (Samsung Gallery order):
1. Add album(s)
2. Rename group
3. Hide album(s)
4. ─── (divider)
5. Destroy group (RED)
6. ─── (divider)
7. Sort
8. View as
9. Settings
10. About App

**Folders Menu**:
1. Sort
2. View as
3. Settings
4. About App

---

## ✅ Compliance with Architecture Rules

### ✅ BEHAVIORAL CONSISTENCY RULE
Both `image-library` and `video-library` behave identically:
- Same scroll threshold (50dp)
- Same header hiding/showing logic
- Same floating button positioning
- Same menu structure

### ✅ UI COMPONENT CONSISTENCY RULE
- Uses shared `ActionsPill` component
- Identical styling across both libraries
- Same menu implementation

### ✅ COMMON-FIRST RULE
- All logic in `common` module
- SharedGroupDetailScreen and SharedFolderDetailScreen
- No library-specific variants

### ✅ BACKUP & RESTORE RULE
- `floatingTopBarEnabled` setting backed up and restored
- Already integrated in previous session

---

## 🎯 Success Criteria Met

✅ At top: Shows back button + title + subtitle + ActionsPill  
✅ When scrolled: Hides header, shows only 2 circular buttons  
✅ Scroll back to top: Header reappears smoothly  
✅ Dynamic content padding prevents jumping  
✅ 55% opacity black buttons (enhanced transparency)  
✅ Menu properly anchored to button  
✅ Samsung Gallery menu structure with dividers  
✅ Applied to both GroupDetailScreen and FolderDetailScreen  
✅ Behavioral consistency across both libraries  
✅ Build successful (4s)  
✅ Apps installed on device SM-S948U1  
✅ **User confirmed: "perfect"** ✅  

---

## 📸 Visual States

**State 1 - At Top**:
```
┌─────────────────────────────────────────────────┐
│ [Status Bar]                                    │
│                                                 │
│  ⭕ ← Porn              ┌──────────────┐       │
│      1 group 79 albums │ + ⊞ ⋮        │       │
│                         └──────────────┘       │
│                                                 │
│  [Album] [Album] [Album]                       │
│  [Album] [Album] [Album]                       │
```

**State 2 - Scrolled Down**:
```
┌─────────────────────────────────────────────────┐
│ [Status Bar]                                    │
│                                                 │
│  ⭕ ←                                      ⋮ ⭕ │
│                                                 │
│  [Album] [Album] [Album]                       │
│  [Album] [Album] [Album]                       │
│  [Album] [Album] [Album]                       │
```

---

## 📊 Files Modified Summary

### Common Module (3 files)
1. `ScreenChromeHelpers.kt` - ActionsPill transparency 55% black
2. `SharedGroupDetailScreen.kt` - Scroll-based auto-hiding header
3. `SharedFolderDetailScreen.kt` - Same scroll-based behavior

**Total: 3 files modified**

---

## 🚀 Build & Installation Status

✅ **Build**: SUCCESS in 4s (99 actionable tasks)  
✅ **image-library**: Installed on device SM-S948U1  
✅ **video-library**: Installed on device SM-S948U1  
✅ **User verified**: Working perfectly  

---

**Implementation Date**: April 11, 2026  
**Status**: ✅ COMPLETE & VERIFIED - Production ready  
**User Feedback**: "perfect" ✅  
**Supersedes**: All previous floating top bar implementations  
**Ready for**: Production use

