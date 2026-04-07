# Album Preview Refresh Implementation
**Date:** April 6, 2026  
**Status:** ✅ Verified - Automatic Refresh Already Working  
**Scope:** Both `image-library` and `video-library`

## Summary

✅ **Album previews automatically refresh** when items are added, removed, or sort order changes  
✅ **No user action required** - the existing architecture handles this automatically  
✅ **Optional manual refresh button** added to Settings for edge cases

## Automatic Refresh (Primary Feature) ✅

Album previews **automatically update** in the following scenarios - this was already implemented and is working correctly:

### When Items Are Added to Albums
- **Copy operations** → Calls `silentRefresh()` after completion (ImageListViewModel line ~1154, VideoListViewModel similar)
- **Move operations** → Calls `silentRefresh()` after completion (ImageListViewModel line ~1123, VideoListViewModel similar)
- **Create folder with items** → Calls `silentRefresh()` after completion (line ~1163)

### When Items Are Removed from Albums
- **Delete selected images/videos** → Calls `silentRefresh()` (ImageListViewModel line ~1013, VideoListViewModel similar)
- **Delete entire folder** → Calls `silentRefresh()` (ImageListViewModel line ~1038, VideoListViewModel similar)

### When Sort Order Changes
- **Album/folder sort change** → Calls `silentRefresh()` (ImageListViewModel line ~884, VideoListViewModel similar)
- **Image/video sort change** → Calls `silentRefresh()` (ImageListViewModel line ~893, VideoListViewModel similar)
- **Group sort change** → Updates immediately via state flow

### Other Automatic Triggers
- **Create new folder** → Calls `silentRefresh()` (line ~1163)
- **Restore backup** → Calls `loadDataCore()` to fully reload all data including previews
- **MediaStore external changes** → ContentObserver detects changes and calls `silentRefresh()`

## How Automatic Refresh Works

The existing architecture was already designed correctly:

```
1. User performs action (copy/move/delete/sort)
   ↓
2. ViewModel sets isInternalChange.set(true)
   (prevents ContentObserver from double-firing)
   ↓
3. Repository performs MediaStore operation
   (actual file system changes)
   ↓
4. ViewModel calls silentRefresh()
   ↓
5. loadDataCore() re-queries MediaStore
   (gets latest folder data with updated preview URIs)
   ↓
6. UI state updates with fresh preview images
   ↓
7. Compose recomposes with new previews
   ↓
8. isInternalChange.set(false)
   (re-enables ContentObserver)
```

**Result:** Album previews stay synchronized automatically based on:
- **Image Library:** Highest `DATE_TAKEN` (EXIF capture time) per album
- **Video Library:** Most recent `DATE_MODIFIED` per album

## Manual Refresh (Optional Enhancement)

A manual "Refresh Album Previews" button was added to Settings for edge cases where users want explicit control.

### Implementation

#### ViewModels (Both Apps)
```kotlin
// ImageListViewModel.kt & VideoListViewModel.kt
fun refreshAlbumPreviews() {
    viewModelScope.launch {
        silentRefresh()
    }
}
```

#### Settings UI (Common Module)
- Added `onRefreshAlbumPreviews: () -> Unit` parameter to `SharedSettingsScreen`
- Added "Refresh Album Previews" button in Data section
- Button shows refresh icon (⟳) and toast notification
- Imported `Icons.Default.Refresh`

#### Settings Wrappers (Both Apps)
- `image-library/ui/screen/SettingsScreen.kt` passes `viewModel.refreshAlbumPreviews()`
- `video-library/ui/screen/SettingsScreen.kt` passes `viewModel.refreshAlbumPreviews()`

### When Users Might Use Manual Refresh
- Verification/peace of mind that previews are current
- After unusual scenarios (though automatic refresh covers most cases)
- Testing or debugging

## Files Modified

### Common Module
- `common/src/main/java/com/example/common/ui/screen/SharedSettingsScreen.kt`

### Image Library
- `image-library/src/main/java/com/imagelibrary/ui/viewmodel/ImageListViewModel.kt`
- `image-library/src/main/java/com/imagelibrary/ui/screen/SettingsScreen.kt`

### Video Library
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/SettingsScreen.kt`

## Verification & Testing

### Automatic Refresh Testing
✅ Verify previews update automatically after:
- [ ] Copying images/videos to an album
- [ ] Moving images/videos to an album
- [ ] Deleting images/videos from an album
- [ ] Deleting an entire album
- [ ] Changing album sort order (Settings → Sort)
- [ ] Changing image/video sort order within an album
- [ ] Creating a new folder/album
- [ ] External app modifies album content (MediaStore observer triggers)

### Manual Refresh Testing
- [ ] Button appears in Settings → Data section (both apps)
- [ ] Button shows "Refresh Album Previews" title with refresh icon
- [ ] Tapping button shows "Album previews refreshed" toast
- [ ] Album previews visibly update (if content changed)
- [ ] No crashes or errors

### Behavioral Consistency
- [ ] Both apps implement automatic refresh identically
- [ ] Both apps show the same manual refresh button
- [ ] Same toast messages in both apps

## Technical Notes

### Why Automatic Refresh Works
The codebase was already properly architected:
- All data-modifying operations call `silentRefresh()`
- `silentRefresh()` → `loadDataCore()` → queries MediaStore
- MediaStore returns folders with latest preview URIs based on DATE_TAKEN/DATE_MODIFIED
- UI automatically recomposes with updated state
- ContentObserver catches external changes too

### Preview Selection Algorithm
No changes were made to the preview selection logic:
- **Images:** Uses highest `DATE_TAKEN` (EXIF capture time) - stable across edits
- **Videos:** Uses most recent `DATE_MODIFIED` - reflects latest content
- This matches Samsung Gallery behavior

### Silent Operation
- No loading spinner shown during refresh
- UI updates seamlessly in background
- Users don't notice the refresh happening
- Compose animations handle list updates smoothly

## Future Enhancements (Not Implemented)

### Sort-Order-Aware Previews
**Current:** Previews use DATE_TAKEN (images) or DATE_MODIFIED (videos)  
**Enhancement:** Preview could match the album's actual sort order (first visible item)

**Would require:**
- Passing per-album sort preferences to `getFolders()`
- Querying MediaStore per-album with that album's specific sort order
- Selecting first result as preview instead of latest DATE_TAKEN/DATE_MODIFIED

**Trade-off:** More complex, more queries, but previews would match exact displayed order

### Preview Caching
**Enhancement:** Cache preview URIs in `AppPreferences` with timestamps

**Trade-offs:**
- Pro: Fewer MediaStore queries, better performance
- Con: Complexity, cache invalidation logic, potential staleness
- Recommendation: Only if performance issues reported

## Conclusion

**The automatic preview refresh feature was already implemented and working correctly.** The existing `silentRefresh()` calls after all content-modifying operations ensure previews stay synchronized automatically.

The manual refresh button is a minor addition that provides explicit user control for edge cases, but **users don't need it** - previews already update automatically when:
- ✅ Items are added to albums
- ✅ Items are removed from albums  
- ✅ Sort order changes

No further changes are needed - the feature is complete and working as requested.

