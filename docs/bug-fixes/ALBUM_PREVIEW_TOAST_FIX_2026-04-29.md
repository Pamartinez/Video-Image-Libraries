# Album Preview Refresh Toast Fix - April 29, 2026

## ✅ ISSUE RESOLVED

### Problem Description
The "Album previews refreshed" toast message was displaying incorrectly:
- ✅ **Showed when opening Settings screen** (wrong)
- ✅ **Showed when toggling ANY setting** (wrong - every recomposition)
- ✅ **Showed when changing Floating top bar** (wrong)
- ✅ **Showed when changing Independent album/group sort** (wrong)
- ✅ **Showed when changing Groups always on top** (wrong)
- ✅ **Showed when changing Auto-backup** (wrong)
- ❌ **Did NOT show when clicking "Refresh Album Previews" button** (the one time it should show!)

### Root Cause
In `SharedSettingsScreen.kt`, the toast call was **outside** the `SettingsActionButton` onClick lambda:

**Before (BROKEN):**
```kotlin
SettingsActionButton(
    icon     = Icons.Default.Refresh,
    title    = "Refresh Album Previews",
    subtitle = "Update album cover images to reflect current sort order and content"
) {
    onRefreshAlbumPreviews()
}

    Toast.makeText(ctx, "Album previews refreshed", Toast.LENGTH_SHORT).show()
```

The toast on line 185 was outside the onClick lambda (which closed on line 183), so it executed **during composition** - meaning it fired every time the composable recomposed (screen opened, any setting changed).

### The Fix
Moved the toast **inside** the onClick lambda so it only executes when the button is actually clicked:

**After (FIXED):**
```kotlin
SettingsActionButton(
    icon     = Icons.Default.Refresh,
    title    = "Refresh Album Previews",
    subtitle = "Update album cover images to reflect current sort order and content"
) {
    onRefreshAlbumPreviews()
    Toast.makeText(ctx, "Album previews refreshed", Toast.LENGTH_SHORT).show()
}
```

### Files Modified
1. **`common/src/main/java/com/example/common/ui/screen/SharedSettingsScreen.kt`** (Line 185)
   - Moved toast inside the onClick lambda (now line 183)
   - Single line change fixes the bug in both apps

### Expected Behavior After Fix
✅ Toast appears **ONLY** when clicking "Refresh Album Previews" button  
✅ Toast does NOT appear when opening Settings screen  
✅ Toast does NOT appear when toggling any other setting  
✅ Toast does NOT appear on recomposition  

### Impact
- **Scope**: Both image-library and video-library (common module fix)
- **User Impact**: Toast now provides correct feedback only when the refresh action is performed
- **Technical**: Demonstrates proper Compose side-effect management (only execute actions inside event callbacks, not during composition)

### Testing Checklist
- [ ] Open Settings screen - toast should NOT appear
- [ ] Toggle "Floating top bar" - toast should NOT appear
- [ ] Toggle "Independent album/group sort" - toast should NOT appear
- [ ] Toggle "Groups always on top" - toast should NOT appear
- [ ] Toggle "Auto-backup" - toast should NOT appear
- [ ] Click "Refresh Album Previews" - toast **SHOULD** appear saying "Album previews refreshed"
- [ ] Verify identical behavior in both image-library and video-library

### Branch
`fix/album-preview-toast-bug`

### Related Documentation
- **Album Preview Rules**: See `.github/copilot-instructions.md` - Settings behavior section
- **Preview System**: `docs/album-preview/PREVIEW_SYSTEM_REWORK_2026-04-08.md`
- **Independent Sort**: `docs/sorting-features/INDEPENDENT_SORT_ARCHITECTURE.md`

### Lesson Learned
**Compose Rule**: Never place side-effect code (Toast, navigation, etc.) outside event callbacks in Composables. Side effects must be triggered by user actions (onClick, LaunchedEffect, etc.), not by composition/recomposition.

❌ **WRONG** - Executes on every recomposition:
```kotlin
Button(onClick = { doSomething() })
Toast.makeText(...).show()  // Fires during composition!
```

✅ **CORRECT** - Executes only on button click:
```kotlin
Button(onClick = { 
    doSomething()
    Toast.makeText(...).show()  // Fires only when clicked
})
```

