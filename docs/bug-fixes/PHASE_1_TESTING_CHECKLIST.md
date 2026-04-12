# Phase 1: Testing Checklist
**Date:** April 8, 2026  
**Before proceeding to Phase 2, verify all changes work correctly in BOTH apps**

---

## 🔧 Build & Compilation

### Common Module:
- [ ] `./gradlew :common:build` succeeds
- [ ] No compilation errors in:
  - [ ] CopyMoveProgress.kt
  - [ ] FileConflict.kt
  - [ ] MixedItemSorter.kt
  - [ ] FilePathUtils.kt

### Image Library:
- [ ] `./gradlew :image-library:assembleDebug` succeeds
- [ ] No import errors in ImageListViewModel
- [ ] All references to CopyMoveProgress resolve
- [ ] All references to FileConflict resolve
- [ ] All calls to MixedItemSorter methods work

### Video Library:
- [ ] `./gradlew :video-library:assembleDebug` succeeds
- [ ] No import errors in VideoListViewModel
- [ ] All references to CopyMoveProgress resolve
- [ ] All references to FileConflict resolve
- [ ] All calls to MixedItemSorter methods work

---

## 📱 Image Library Testing

### Sorting Functionality:
- [ ] **Folders Tab → Custom Order:**
  - [ ] Drag-and-drop to reorder folders works
  - [ ] Drag-and-drop to reorder groups works
  - [ ] New folders appear at the beginning
  - [ ] New groups appear at the beginning
  - [ ] Order persists after app restart

- [ ] **Folders Tab → Name A-Z:**
  - [ ] Folders sorted alphabetically (ascending)
  - [ ] Groups sorted alphabetically (ascending)
  - [ ] When "Groups always on top" enabled, groups appear first

- [ ] **Folders Tab → Name Z-A:**
  - [ ] Folders sorted alphabetically (descending)
  - [ ] Groups sorted alphabetically (descending)

- [ ] **Folders Tab → Items (most first):**
  - [ ] Folders sorted by item count (descending)
  - [ ] Groups sorted by total item count (descending)

- [ ] **Folders Tab → Items (fewest first):**
  - [ ] Folders sorted by item count (ascending)
  - [ ] Groups sorted by total item count (ascending)

### Group Functionality:
- [ ] Open a group → displays folders in correct order
- [ ] Group sort independent of root sort
- [ ] Can change sort inside group
- [ ] Drag-and-drop works inside group (custom order)

### Hide Folders Screen:
- [ ] Open Hide Folders → shows correct sort order
- [ ] Open group inside Hide Folders → shows correct sort
- [ ] Custom order matches Folders tab order (read-only)
- [ ] Toggle folder visibility works
- [ ] Toggle group visibility (all folders) works
- [ ] Ghost folders appear if previously hidden

### Copy/Move Operations:
- [ ] **Copy single image:**
  - [ ] Progress dialog shows "Copying items to..."
  - [ ] Progress bar updates (0/1 → 1/1)
  - [ ] No conflict → completes successfully
  - [ ] Conflict → shows FileConflictDialog
    - [ ] Shows correct filename
    - [ ] "Apply to all" checkbox visible
    - [ ] Skip button works
    - [ ] Replace button works
    - [ ] Rename button works

- [ ] **Copy multiple images:**
  - [ ] Progress shows correct count (e.g., 0/5 → 5/5)
  - [ ] First conflict → dialog appears
  - [ ] Check "Apply to all" → applies to remaining
  - [ ] Can choose different action for each conflict

- [ ] **Move operations:**
  - [ ] Same as copy tests
  - [ ] Files removed from source after move

---

## 📱 Video Library Testing

### Sorting Functionality:
- [ ] **Folders Tab → Custom Order:**
  - [ ] Drag-and-drop to reorder folders works
  - [ ] Drag-and-drop to reorder groups works
  - [ ] New folders appear at the beginning
  - [ ] New groups appear at the beginning
  - [ ] Order persists after app restart

- [ ] **Folders Tab → Name A-Z:**
  - [ ] Folders sorted alphabetically (ascending)
  - [ ] Groups sorted alphabetically (ascending)
  - [ ] When "Groups always on top" enabled, groups appear first

- [ ] **Folders Tab → Name Z-A:**
  - [ ] Folders sorted alphabetically (descending)
  - [ ] Groups sorted alphabetically (descending)

- [ ] **Folders Tab → Items (most first):**
  - [ ] Folders sorted by item count (descending)
  - [ ] Groups sorted by total item count (descending)

- [ ] **Folders Tab → Items (fewest first):**
  - [ ] Folders sorted by item count (ascending)
  - [ ] Groups sorted by total item count (ascending)

### Group Functionality:
- [ ] Open a group → displays folders in correct order
- [ ] Group sort independent of root sort
- [ ] Can change sort inside group
- [ ] Drag-and-drop works inside group (custom order)
- [ ] Generate unique group name works (Group, Group (2), Group (3), etc.)

### Hide Folders Screen:
- [ ] Open Hide Folders → shows correct sort order
- [ ] Open group inside Hide Folders → shows correct sort
- [ ] Custom order matches Folders tab order (read-only)
- [ ] Toggle folder visibility works
- [ ] Toggle group visibility (all folders) works
- [ ] Ghost folders appear if previously hidden

### Copy/Move Operations:
- [ ] **Copy single video:**
  - [ ] Progress dialog shows "Copying items to..."
  - [ ] Progress bar updates (0/1 → 1/1)
  - [ ] No conflict → completes successfully
  - [ ] Conflict → shows FileConflictDialog
    - [ ] Shows correct filename
    - [ ] "Apply to all" checkbox visible
    - [ ] Skip button works
    - [ ] Replace button works
    - [ ] Rename button works

- [ ] **Copy multiple videos:**
  - [ ] Progress shows correct count (e.g., 0/5 → 5/5)
  - [ ] First conflict → dialog appears
  - [ ] Check "Apply to all" → applies to remaining
  - [ ] Can choose different action for each conflict

- [ ] **Move operations:**
  - [ ] Same as copy tests
  - [ ] Files removed from source after move

---

## 🔄 Behavioral Consistency (Most Important!)

**Compare both apps side-by-side:**

### Sorting:
- [ ] **Custom order behavior is identical**
  - New items prepended at beginning in both apps
  - Drag-and-drop order persists identically
  
- [ ] **All sort options produce same relative order**
  - Name A-Z sorts identically
  - Name Z-A sorts identically
  - Item counts sort identically

### Groups:
- [ ] **Groups always on top works identically**
- [ ] **Group creation flow identical**
- [ ] **Group naming identical**
- [ ] **Group sorting identical**

### Hide Folders:
- [ ] **Screen behavior identical**
- [ ] **Toggle visibility identical**
- [ ] **Sort order identical**

### Copy/Move:
- [ ] **Progress dialog appearance identical**
- [ ] **Conflict resolution dialog identical**
- [ ] **Button labels identical** ("Skip", "Replace", "Rename")
- [ ] **"Apply to all" behavior identical**

---

## ⚠️ Known Issues to Watch For

### Potential Issues:
1. **Import errors** - If common module classes not found
2. **Type mismatches** - If SortOption vs FolderSortOption causes issues
3. **Null pointer exceptions** - If preferences return null unexpectedly
4. **Order corruption** - If custom order gets corrupted during migration

### If Issues Found:
1. Check imports in both ViewModels
2. Verify common module builds successfully
3. Check preferences are being read/written correctly
4. Compare behavior with previous version (before changes)

---

## ✅ Sign-Off

After completing all tests above:

- [ ] All build checks pass
- [ ] All Image Library tests pass
- [ ] All Video Library tests pass
- [ ] Behavioral consistency verified
- [ ] No regressions detected
- [ ] Ready to proceed to Phase 2

**Tested by:** _______________  
**Date:** _______________  
**Approved to proceed to Phase 2:** [ ] YES  [ ] NO

**If NO, list issues found:**
_______________________________________
_______________________________________
_______________________________________

---

## 📋 Next: Phase 2

Once all tests pass, proceed to:
- Read: `docs/CONSOLIDATION_QUICK_WINS.md` (Phase 2 section)
- Create: `BaseMediaListViewModel<MediaItem, MediaSortOption>`
- Extract: ~1,800 lines of identical ViewModel logic
- Goal: 47% reduction in ViewModel code

