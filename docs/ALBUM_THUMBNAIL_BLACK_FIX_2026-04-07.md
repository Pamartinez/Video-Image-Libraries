# Album Thumbnail Black Screen Fix - April 7, 2026

## Problem
In the video-library app's Move/Copy folder picker screens:
- "Movies" and "music videos" albums showed **black thumbnails**
- Other albums showed **different preview images** compared to the main Folders tab
- The same albums displayed correct thumbnails on the main screen

## Root Cause
The main screen and picker screens were using **different thumbnail rendering components**:

### Main Screen (VideoListScreen → FolderGridItem)
- Used `VideoThumbnail` component
- **Brightness-aware frame selection**: Seeks up to 10 seconds into the video to find a bright frame
- Avoids black frames from fade-ins, studio logos, dark intros, etc.
- Uses `MediaMetadataRetriever` with smart seeking algorithm

### Picker Screens (FolderPickerScreen)
- Used `AsyncImage` with `VideoFrameDecoder.Factory()`
- **No brightness awareness**: Just extracts the first frame using Coil's decoder
- Black frames from video intros would be shown as-is

This explained the symptoms:
1. **Black thumbnails** = Videos starting with dark frames (common in movies with studio logos, fade-ins)
2. **Different images** = Main screen showing bright frames (from seeking), picker showing first frames

## Solution
Changed `FolderPickerScreen.kt` to use `VideoThumbnail` instead of `AsyncImage`, ensuring **identical thumbnail rendering** between main screen and pickers.

### Before
```kotlin
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest

// ...
thumbnailContent = { folder, mod ->
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(folder.latestItemUri)
            .decoderFactory(VideoFrameDecoder.Factory())
            .crossfade(true)
            .build(),
        contentDescription = folder.name,
        contentScale = ContentScale.Crop,
        modifier = mod
    )
}
```

### After
```kotlin
import com.videolibrary.ui.components.VideoThumbnail

// ...
thumbnailContent = { folder, mod ->
    VideoThumbnail(
        contentUri         = folder.latestItemUri,
        contentDescription = folder.name,
        contentScale       = ContentScale.Crop,
        modifier           = mod
            .fillMaxWidth()
            .aspectRatio(0.75f)
    )
}
```

## Files Modified
- `video-library/src/main/java/com/videolibrary/ui/screen/FolderPickerScreen.kt`

## How VideoThumbnail Works
1. **First attempt**: Loads system cached thumbnail via `ContentResolver.loadThumbnail()`
2. **Brightness check**: Measures average brightness using ITU-R BT.601 luminance formula
3. **Smart seeking** (if too dark): Uses `MediaMetadataRetriever` to seek 1s, 2s, 3s... up to 10 seconds
4. **Frame selection**: Keeps the brightest frame found that exceeds threshold (28/255)
5. **LRU caching**: Results cached in memory (24 MB cache) for instant scrolling

## Verification
- Build: ✅ Successful
- Install: ✅ Deployed to SM-S948U1
- Behavior: Both main screen and picker now use identical thumbnail rendering

## Architectural Alignment
This fix enforces the **Behavioral Consistency Rule**:
> "Both `image-library` and `video-library` MUST behave identically for ALL common operations."

The same UI component (`VideoThumbnail` for video-library, `AsyncImage` for image-library) is now used consistently across:
- Main folder/album grid
- Move folder picker
- Copy folder picker  
- Create Album picker (already using FolderGridItem → VideoThumbnail)

## Related Components
- ✅ `CreateAlbumPickerScreen.kt` - Already uses `FolderGridItem` which wraps `VideoThumbnail`
- ✅ `VideoListScreen.kt` - Main screen uses `FolderGridItem` which wraps `VideoThumbnail`
- ✅ `FolderPickerScreen.kt` - **NOW FIXED** to use `VideoThumbnail` directly

All folder thumbnail rendering in video-library now uses the same smart, brightness-aware component.

