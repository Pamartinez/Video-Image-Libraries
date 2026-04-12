# Group Scroll Position Fix - April 8, 2026

## Problem
In the video-library, when navigating to a group, scrolling down, then opening an album and going back, the app would scroll all the way to the top instead of preserving the scroll position.

This issue was already fixed in image-library but hadn't been applied to video-library yet.

## Root Cause
The video-library was creating a new `LazyGridState` instance every time `GroupDetailScreen` was recomposed by calling `rememberLazyGridState()` directly in the `SharedGroupDetailScreen` call. This caused the scroll position to be lost on every recomposition (such as when navigating to and from an album).

The image-library had already solved this by:
1. Hoisting the `LazyGridState` to the top level of the screen composable
2. Passing it as a parameter to `GroupDetailScreen`
3. Using `LaunchedEffect` to scroll to top only when the group changes

## Solution Applied

### 1. VideoListScreen.kt
**Added dedicated groupGridState** (line ~68):
```kotlin
// Hoisted so GroupDetailScreen scroll survives album-detail navigations.
// Scrolls to top when navigating to a different group; stays put when returning
// from a folder (album) inside the same group.
val groupGridState = rememberLazyGridState()
```

**Updated LaunchedEffect** (lines ~81-84):
Changed from:
```kotlin
LaunchedEffect(state.currentGroupId, state.currentGroupSortOption) {
    folderGridState.scrollToItem(0)
    folderListState.scrollToItem(0)
}
```

To:
```kotlin
LaunchedEffect(state.currentGroupId, state.currentGroupSortOption) {
    groupGridState.scrollToItem(0)
}
```

This ensures the group grid only scrolls to top when:
- The group ID changes (navigating to a different group)
- The group's sort option changes

But NOT when:
- Navigating into an album within the same group
- Navigating back from an album to the group

**Passed lazyGridState to GroupDetailScreen** (line ~325):
```kotlin
GroupDetailScreen(
    // ... other parameters ...
    lazyGridState = groupGridState
)
```

### 2. GroupDetailScreen.kt
**Added lazyGridState parameter** (line 62):
```kotlin
fun GroupDetailScreen(
    // ... other parameters ...
    lazyGridState: LazyGridState = rememberLazyGridState(),
    modifier: Modifier = Modifier
)
```

**Passed to SharedGroupDetailScreen** (line 100):
Changed from:
```kotlin
lazyGridState = rememberLazyGridState(),
```

To:
```kotlin
lazyGridState = lazyGridState,
```

## Result
The scroll position is now preserved when:
1. User navigates to a group
2. User scrolls down within the group
3. User taps an album to view its contents
4. User presses back to return to the group

The group view will maintain its scroll position at the same location where the user left it.

The scroll position resets to top (as expected) when:
- Navigating to a different group
- Changing the sort option within the group

## Files Modified
- `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`
- `video-library/src/main/java/com/videolibrary/ui/screen/GroupDetailScreen.kt`

## Consistency Note
This fix brings video-library into alignment with image-library, maintaining behavioral consistency as required by the Copilot Instructions. Both libraries now handle group scroll position identically.

