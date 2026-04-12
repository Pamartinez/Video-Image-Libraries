# Album Sort Fix - April 8, 2026

## Problem
In video-library, when changing the sort option inside an album, the UI would not update correctly until you left the album and came back. The sort option would change but the videos would remain in the old order.

## Root Cause
The `setFolderSortOption()` function was updating the UI state twice in separate recomposition frames:
1. First update: Changed `currentFolderSortOption` but kept old `folderVideos` list
2. Second update (async): Updated `folderVideos` with newly sorted data

This caused the UI to briefly show the old video order with the new sort indicator, creating a visible flicker and incorrect display until the async data arrived.

## Solution Applied
Applied the same pattern that image-library uses in its `setImageSortOption()` function:

### 1. Added `sortVideosInMemory()` Function
Created a new private function that sorts videos in-memory immediately based on the sort option:

```kotlin
private fun sortVideosInMemory(videos: List<VideoItem>, option: VideoSortOption): List<VideoItem> {
    return when (option) {
        VideoSortOption.CUSTOM_ORDER -> videos.sortedWith(compareByDescending<VideoItem> { it.dateModified }.thenBy { it.id })
        VideoSortOption.NAME_A_TO_Z -> videos.sortedBy { it.displayName.lowercase() }
        VideoSortOption.NAME_Z_TO_A -> videos.sortedByDescending { it.displayName.lowercase() }
        VideoSortOption.DURATION_ASC -> videos.sortedBy { it.duration }
        VideoSortOption.DURATION_DESC -> videos.sortedByDescending { it.duration }
        VideoSortOption.DATE_CREATED_ASC -> videos.sortedBy { it.id }
        VideoSortOption.DATE_CREATED_DESC -> videos.sortedByDescending { it.id }
        VideoSortOption.DATE_MODIFIED_ASC -> videos.sortedBy { it.dateModified }
        VideoSortOption.DATE_MODIFIED_DESC -> videos.sortedByDescending { it.dateModified }
    }
}
```

### 2. Updated `setFolderSortOption()`
Changed the function to:
1. Sort the existing videos in-memory immediately
2. Update the state with both the sort option AND the sorted list in a single atomic update
3. Call `refreshFolderVideos()` to refresh data in the background

**Before:**
```kotlin
fun setFolderSortOption(s: VideoSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId ?: return
    if (preferences.independentSortEnabled) {
        preferences.saveFolderVideoSortOption(bucketId, s)
    } else {
        preferences.videoSortOption = s
    }
    _uiState.update {
        it.copy(
            currentFolderSortOption = s,
            folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
        )
    }
    viewModelScope.launch {
        val videos = repository.getVideos(videoSortOption = s, bucketId = bucketId)
        _uiState.update { it.copy(folderVideos = videos) }
    }
    scheduleAutoBackup()
}
```

**After:**
```kotlin
fun setFolderSortOption(s: VideoSortOption) {
    val bucketId = _uiState.value.currentFolderBucketId ?: return
    if (preferences.independentSortEnabled) {
        preferences.saveFolderVideoSortOption(bucketId, s)
    } else {
        preferences.videoSortOption = s
    }
    // Sort existing folder videos in-memory immediately so that both
    // currentFolderSortOption and folderVideos change in the same recomposition frame.
    // This prevents LazyVerticalGrid's stable keys from re-scrolling when
    // the async data arrives later.
    val sorted = sortVideosInMemory(_uiState.value.folderVideos, s)
    _uiState.update {
        it.copy(
            currentFolderSortOption = s,
            folderVideos = sorted,
            folderDetailScrollToTopTrigger = it.folderDetailScrollToTopTrigger + 1
        )
    }
    refreshFolderVideos()
    scheduleAutoBackup()
}
```

## Benefits
1. **Immediate visual feedback**: Videos are sorted instantly when the user changes the sort option
2. **No flicker**: Both the sort indicator and video list update in the same frame
3. **Smooth scrolling**: LazyGrid doesn't re-scroll because items maintain stable keys
4. **Consistent with image-library**: Uses the exact same pattern for identical behavior

## Testing
To verify the fix:
1. Open an album with multiple videos
2. Change the sort option (e.g., from Custom Order to Name A-Z)
3. Videos should immediately re-sort without any flicker or delay
4. The sort should be correct immediately, no need to exit and re-enter

## Files Modified
- `video-library/src/main/java/com/videolibrary/ui/viewmodel/VideoListViewModel.kt`
  - Added `sortVideosInMemory()` function (lines ~1270-1282)
  - Updated `setFolderSortOption()` function (lines ~1247-1268)

## Consistency Note
This fix brings video-library into alignment with image-library, maintaining behavioral consistency as required by the Copilot Instructions. Both libraries now handle album sorting identically with immediate in-memory sorting followed by background refresh.

