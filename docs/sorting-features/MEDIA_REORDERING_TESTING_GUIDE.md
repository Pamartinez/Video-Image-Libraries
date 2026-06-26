# Media Drag-to-Reorder Testing Guide

**Feature:** Drag-and-drop reordering for images/videos within albums  
**Branch:** `feature/media-drag-reorder`  
**Date:** June 26, 2026  
**Status:** Ready for Testing ✅

---

## Quick Start

### Prerequisites
- Both apps installed on device: `./gradlew :image-library:installDebug :video-library:installDebug`
- Device has media files (images and/or videos) in multiple albums
- Familiarity with Samsung Gallery's drag-to-reorder UX

### Enable the Feature
1. Open either app (image-library or video-library)
2. Navigate to **Settings** (3-dot menu → Settings)
3. Scroll to **Interface** section
4. Toggle **"Drag to reorder media"** to ON
5. (Optional) Tap the ℹ️ icon to see feature explanation dialog

---

## Test Scenarios

### ✅ Basic Drag-and-Drop (Album View)

**Setup:**
1. Open an album with at least 10 items
2. Change sort to **"Custom order"** (3-dot menu → Sort)
3. Ensure **Drag to reorder media** toggle is ON in settings

**Test Steps:**
1. **Long-press** an image/video for approximately 1 second
2. Item should "lift" with slight elevation/shadow
3. **Drag** the item to a new position
4. Other items should smoothly shift to make space
5. **Release** to drop the item
6. Order should persist immediately

**Expected:**
- Smooth animations during drag
- Scroll disabled while dragging
- Item stays in new position after release
- No visual glitches or jumps

**Test in Both Apps:**
- [ ] image-library (images in album)
- [ ] video-library (videos in folder)

---

### ✅ Order Persistence

**Test Steps:**
1. Reorder several items in an album
2. **Navigate away** (back to main screen)
3. **Reopen the album**
4. Verify custom order is preserved

**Expected:**
- Order exactly matches what you set
- No items out of place
- No default sort applied

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Multiple Albums Independence

**Test Steps:**
1. Open Album A, reorder items
2. Navigate back to main screen
3. Open Album B, reorder items differently
4. Navigate back
5. Reopen Album A - verify order unchanged
6. Reopen Album B - verify its unique order

**Expected:**
- Each album maintains its own independent custom order
- No cross-contamination between albums

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ New Items Appear at Top

**Test Steps:**
1. Reorder items in an album (set custom order)
2. Add a new image/video to that album (using another app or camera)
3. Refresh the album (may require app restart or pull-to-refresh if available)
4. Verify new item appears at **position 0** (top of the list)

**Expected:**
- New items always prepend at the very top
- Existing custom order shifts down but remains intact

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Drag Only in Custom Sort

**Test Steps:**
1. Open an album
2. Ensure **Drag to reorder media** toggle is ON
3. Set sort to **"Date taken (newest first)"**
4. Try to drag an item
5. Change sort to **"Custom order"**
6. Try to drag again

**Expected:**
- Drag does NOT work in Date/Name/Size sorts
- Long-press in non-custom sorts enters selection mode
- Drag ONLY works in Custom sort mode

**Test in Both Apps:**
- [ ] image-library (test with Date, Name, Size sorts)
- [ ] video-library (test with Date, Name, Size, Duration sorts)

---

### ✅ Toggle Disables Drag

**Test Steps:**
1. Open an album with Custom sort
2. With toggle ON, verify drag works
3. Go to Settings
4. Toggle **"Drag to reorder media"** OFF
5. Return to album (still in Custom sort)
6. Try to drag an item

**Expected:**
- Drag does NOT work when toggle is OFF
- Long-press enters selection mode instead

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Selection Mode Disables Drag

**Test Steps:**
1. Open an album with Custom sort and toggle ON
2. Enter selection mode (long-press without dragging, or tap "Select" button)
3. Try to drag an item

**Expected:**
- Drag does NOT work in selection mode
- Tapping items toggles their selection instead

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Floating Top Bar Compatibility

**Test Steps:**
1. Enable **"Floating top bar"** in Settings
2. Open an album with many items
3. Scroll down so floating controls appear
4. Try to drag items (with Custom sort + drag toggle ON)
5. Verify header row doesn't interfere

**Expected:**
- Drag works correctly with floating top bar
- Index calculations handle header row offset properly
- No items get stuck or dragged incorrectly

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ App Restart Persistence

**Test Steps:**
1. Reorder items in multiple albums
2. **Force close** the app (swipe away from recent apps)
3. **Reopen** the app
4. Check each album

**Expected:**
- All custom orders preserved across app restarts
- No data loss
- SharedPreferences persisted correctly

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Backup & Restore

**Test Steps:**
1. Reorder items in 2-3 albums
2. Go to Settings → Backup → **Create Backup**
3. Save backup to file
4. Reorder items differently (or change to default sort)
5. Go to Settings → Backup → **Restore Backup**
6. Load the backup file
7. Check albums

**Expected:**
- Custom orders restored exactly as they were in backup
- Both the toggle state AND the orders are restored
- No corruption or data loss

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Auto-Backup Integration

**Test Steps:**
1. Enable **"Auto-backup"** in Settings
2. Reorder items in an album
3. Wait a few seconds (auto-backup debounce)
4. Check that a backup was created automatically
   - May need to manually verify backup timestamp or contents

**Expected:**
- Auto-backup triggers after reorder completion
- No performance issues or delays
- Background operation doesn't block UI

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Large Albums (Performance Test)

**Test Steps:**
1. Find or create an album with **100+ items**
2. Enable drag-to-reorder
3. Set sort to Custom
4. Drag items from top to bottom
5. Drag items from bottom to top
6. Observe frame rate and responsiveness

**Expected:**
- No lag or stuttering
- Smooth 60fps animations
- Drag gesture responds immediately
- Scroll disabled during drag (no accidental scrolling)

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

### ✅ Edge Cases

#### Edge Case 1: Rapid Reordering
1. Quickly drag multiple items in succession
2. Verify no crashes or incorrect ordering

#### Edge Case 2: Drag to Same Position
1. Drag an item and drop it in the same spot
2. Verify order unchanged, no crashes

#### Edge Case 3: Delete Item After Reordering
1. Reorder items
2. Delete one of the reordered items (using another app or system file manager)
3. Refresh the album
4. Verify remaining items maintain their custom order

#### Edge Case 4: Maximum Albums (Storage Limit)
1. Create/reorder items in 50+ albums
2. Verify oldest albums eventually get pruned (max 50 stored)
3. Verify no crashes or data corruption

**Test in Both Apps:**
- [ ] image-library
- [ ] video-library

---

## Consistency Verification

### ✅ Both Apps Behave Identically

**Compare Side-by-Side:**
1. Open both apps simultaneously (or test sequentially)
2. Perform the same actions in both apps
3. Verify identical behavior:
   - Drag activation time (1 second long-press)
   - Animation style and speed
   - Selection mode entry behavior
   - Settings UI identical
   - Info dialog text identical
   - Error messages identical

**Checklist:**
- [ ] Drag UX feels identical
- [ ] Settings toggle in same location with same text
- [ ] Info dialog text matches
- [ ] Long-press duration the same
- [ ] Animations use same spring physics
- [ ] No unexpected differences

---

## Known Issues / Limitations

Document any issues found during testing:

### Issues Found:
- [ ] None yet

### Expected Limitations (by design):
- [x] Root view reordering not implemented (Phase 7 deferred)
- [x] Maximum 50 albums stored (SharedPreferences limit)
- [x] Custom order not visible in Samsung Gallery (app-only feature)
- [x] Requires Custom sort mode (intentional)
- [x] Requires toggle enabled (intentional)

---

## Regression Testing

Run the consistency verification script to ensure no regressions:

```powershell
./scripts/verify-consistency.ps1
```

Expected: All checks pass ✅

---

## Sign-Off

### Image-Library Testing
- [ ] Basic drag-and-drop works
- [ ] Order persists across restarts
- [ ] Multiple albums independent
- [ ] New items appear at top
- [ ] Drag only in Custom sort
- [ ] Toggle disables drag
- [ ] Selection mode disables drag
- [ ] Floating top bar compatible
- [ ] Backup/restore works
- [ ] Performance acceptable
- [ ] No crashes or bugs found

**Tester Name:** _________________  
**Date:** _________________  
**Build Version:** _________________

---

### Video-Library Testing
- [ ] Basic drag-and-drop works
- [ ] Order persists across restarts
- [ ] Multiple folders independent
- [ ] New items appear at top
- [ ] Drag only in Custom sort
- [ ] Toggle disables drag
- [ ] Selection mode disables drag
- [ ] Floating top bar compatible
- [ ] Backup/restore works
- [ ] Performance acceptable
- [ ] No crashes or bugs found

**Tester Name:** _________________  
**Date:** _________________  
**Build Version:** _________________

---

### Consistency Testing
- [ ] Both apps behave identically
- [ ] Settings UI matches
- [ ] Drag UX consistent
- [ ] No unexpected differences

**Tester Name:** _________________  
**Date:** _________________

---

## Next Steps After Testing

1. **If all tests pass:**
   - Mark Phase 8 as complete
   - Proceed to Phase 9 (Documentation)
   - Update CHANGELOG.md
   - Create PR with `/pr-description` skill

2. **If issues found:**
   - Document in "Issues Found" section
   - Create tickets/issues for each bug
   - Fix critical bugs before proceeding
   - Re-test after fixes

3. **Performance issues:**
   - Profile with Android Studio
   - Optimize hot paths if needed
   - Consider increasing storage limits if needed

---

## Quick Commands

```bash
# Install both apps
./gradlew :image-library:installDebug :video-library:installDebug

# Build with warnings as errors
./gradlew :image-library:build -warnaserror
./gradlew :video-library:build -warnaserror

# Run consistency check
./scripts/verify-consistency.ps1

# Clear app data (for fresh testing)
adb shell pm clear com.imagelibrary.debug
adb shell pm clear com.videolibrary.debug
```

---

**Happy Testing! 🧪✨**

