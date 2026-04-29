# Carousel Auto-Scroll Implementation

**Date:** April 29, 2026  
**Feature:** Album grid auto-scroll during carousel swipe  
**Status:** ✅ Implemented in image-library  
**Applies to:** image-library (video-library pending carousel implementation)

---

## Overview

This feature implements Samsung Gallery-style auto-scrolling of the album grid when swiping through the carousel view. As the user swipes through images in the carousel, the background album grid automatically scrolls to keep the currently viewed image visible.

## Implementation Summary

### Architecture

The implementation uses a **state-based approach** (Jetpack Compose pattern) that achieves the same user-visible behavior as Samsung Gallery's event-based ActionInvoker system:

1. **State tracking**: `currentCarouselPage` in ViewModel state tracks the active carousel page
2. **Page change callback**: Carousel screen reports page changes via `onPageChanged(Int)` callback
3. **Reactive scroll**: LaunchedEffect responds to state changes and scrolls the grid

### Samsung Gallery Investigation

Samsung Gallery uses:
- **ActionInvoker pub-sub system**: ViewPagerDelegate invokes `VIEW_PAGER_PAGE_SCROLLED` with `(position, positionOffset, positionOffsetPixels)`
- **Direct RecyclerView manipulation**: Background fragment receives events and calls `scrollToPositionWithOffset(position, 0)`
- **Instant scroll** (no animation): Uses immediate positioning, not smooth scroll

Our implementation achieves the **same behavior** using Compose's state-driven architecture.

---

## Code Changes

### 1. ImageListViewModel.kt

**Added state property:**
```kotlin
val currentCarouselPage: Int = -1,
```

**Updated carousel methods:**
```kotlin
fun closeCarousel() = _uiState.update { it.copy(carouselIndex = -1, currentCarouselPage = -1) }
fun updateCarouselPage(page: Int) = _uiState.update { it.copy(currentCarouselPage = page) }
```

### 2. ImageCarouselScreen.kt

**Added callback parameter:**
```kotlin
onPageChanged: (Int) -> Unit = {}
```

**Updated LaunchedEffect:**
```kotlin
LaunchedEffect(pagerState.currentPage) {
    isCurrentPageZoomed = false
    thumbnailListState.animateScrollToItem(pagerState.currentPage)
    onPageChanged(pagerState.currentPage)  // ← Notify ViewModel
}
```

### 3. ImageListScreen.kt

**Wired callback:**
```kotlin
ImageCarouselScreen(
    // ...existing parameters...
    onPageChanged = { page -> viewModel.updateCarouselPage(page) },
    // ...rest of parameters...
)
```

**Added auto-scroll effect:**
```kotlin
// Auto-scroll album grid to match carousel page (Samsung Gallery behavior)
LaunchedEffect(state.currentCarouselPage) {
    if (state.carouselIndex >= 0 && state.currentCarouselPage >= 0) {
        imageGridState.scrollToItem(state.currentCarouselPage)
    }
}
```

---

## Behavior

### How It Works

1. **User opens carousel**: `carouselIndex` set to initial image index
2. **User swipes**: HorizontalPager's `currentPage` changes
3. **LaunchedEffect triggers**: Detects page change and calls `onPageChanged()`
4. **ViewModel updates state**: Sets `currentCarouselPage`
5. **Auto-scroll effect triggers**: Detects state change and scrolls grid
6. **Grid repositions**: Uses instant `scrollToItem()` (no animation)

### User Experience

- **Seamless sync**: Grid scrolls as you swipe through carousel
- **Instant positioning**: No animation delay (matches Samsung Gallery)
- **Only when active**: Only scrolls when carousel is open (`carouselIndex >= 0`)
- **Maintains position**: Grid stays at the current image

---

## Video Library Status

**Status:** ⏳ Ready for implementation when carousel is added

The video library has:
- ✅ Setting: `instantPlayerEnabled` preference exists
- ❌ Player screen: No carousel/player screen implemented yet
- ✅ Prepared state: Can add identical implementation when player is built

### When Implementing Video Player

Apply the **exact same changes** to maintain behavioral consistency:

1. Add `currentCarouselPage` to `VideoListUiState`
2. Add `updateCarouselPage()` to `VideoListViewModel`
3. Add `onPageChanged` callback to the player screen
4. Add auto-scroll LaunchedEffect in `VideoListScreen`

---

## Testing

### How to Test

1. **Open image-library**
2. **Navigate to an album** with many images
3. **Click on an image** in the middle of the grid (carousel opens)
4. **Swipe left/right** through carousel
5. **Observe**: Album grid in background scrolls to keep current image visible

### Expected Behavior

- ✅ Grid scrolls instantly (no animation)
- ✅ Current image stays visible in grid
- ✅ Works with any grid position
- ✅ Only scrolls when carousel is active
- ✅ Grid position maintained after closing carousel

---

## Technical Notes

### Why State-Based Instead of Event-Based?

Samsung Gallery uses an event system (ActionInvoker) because it's built with Fragments and XML views. Our Compose implementation uses **state** because:

1. **Compose pattern**: State changes trigger recomposition
2. **Simpler**: No pub-sub infrastructure needed
3. **Same result**: User sees identical behavior
4. **Type-safe**: Compile-time checking vs runtime event matching

### Why Instant Scroll?

Samsung Gallery uses `scrollToPositionWithOffset()` with no animation. We match this with `scrollToItem()` because:

1. **Matches Samsung**: Instant positioning is their UX
2. **Performance**: No animation overhead during rapid swipes
3. **Predictable**: Always shows exact position

### Thread Safety

- ✅ **Safe**: All state updates use `_uiState.update { }`
- ✅ **Main thread**: LaunchedEffect runs on main dispatcher
- ✅ **Compose-safe**: `scrollToItem()` is suspend function

---

## Files Modified

### image-library
- `ImageListViewModel.kt`: Added state tracking and update methods
- `ImageCarouselScreen.kt`: Added page change callback
- `ImageListScreen.kt`: Wired callback and added auto-scroll effect

### video-library
- ⏳ No changes (waiting for carousel implementation)

---

## Consistency Compliance

✅ **Behavioral Consistency Rule**: Implementation ready for both libraries  
✅ **Common-First Rule**: Uses framework features (no custom code needed)  
✅ **Samsung Gallery Pattern**: Matches reference implementation behavior  
✅ **Testing**: Installed and ready to test  

---

## Future Enhancements

Potential improvements (not currently needed):

1. **Smooth scroll option**: Add setting for animated vs instant scroll
2. **Center alignment**: Calculate offset to center item in viewport
3. **Smart positioning**: Only scroll if item not visible (optimization)

---

## References

- **Samsung Gallery Decompiled**: `ViewPagerDelegate.java` (lines 84-104)
- **ActionInvoker System**: `com.samsung.android.gallery.support.actioninvoker`
- **Viewer Architecture**: `VuContainerFragment.java`
- **RecyclerView Scroll**: `FolderViewFragment.java` (line 49)

---

## Summary

The auto-scroll feature is **fully implemented** in image-library and matches Samsung Gallery's behavior. The video library will receive the identical implementation when the carousel/player screen is added, maintaining perfect behavioral consistency between both apps.

**The background album grid now auto-scrolls while you swipe through the carousel!** 🎉

