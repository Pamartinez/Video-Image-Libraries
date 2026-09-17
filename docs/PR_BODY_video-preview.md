## Description

Opening an image from a folder in `image-library` showed a brief flash and then the image visibly re-fitted itself — worst on EXIF-rotated photos, which is most of a real camera roll. Two root causes, both introduced with the Samsung-style zoom-open transition:

- The zoom overlay derived its target rect from raw `MediaStore.Images.Media.WIDTH/HEIGHT`, which on this device are the pre-rotation sensor dimensions. It ignored `ORIENTATION`, so a portrait-displayed photo stored as `4000x3000 / orientation=90` grew into a landscape-shaped rect and cover-cropped, then popped to the correct fit once Telephoto (which honors EXIF via Coil) took over.
- The carousel's Telephoto `ZoomableAsyncImage` used `crossfade(true)` and no `placeholderMemoryCacheKey`, so at hand-off it started from an empty image and faded in while recomputing its fit transform — the flash + adjust.

Separately, every `gradlew installDebug` kept re-creating a clone of the apps in Samsung's Dual App profile (user 95), because `adb install` targets all users by default. There is no app-side way to stop the OS from cloning, but pinning installs to the primary user prevents our own installs from re-creating it.

### What changed

- `ImageItem`: added `orientation` (from `MediaStore.Images.Media.ORIENTATION`) and a `displayAspectRatio` that swaps width/height for 90/270 rotations; the zoom transition now uses it.
- `ImageCarouselScreen`: added `placeholderMemoryCacheKey` and disabled crossfade so the viewer reuses the warm grid thumbnail and sizes correctly up front.
- All three modules: `installation { installOptions "--user" "0" }` so installs no longer clone into the Dual App profile.
- Version bumped to `1.4.0` (reset BUILD, VERSION 1.3 -> 1.4) across `AppVersion` and all three `build.gradle.kts`.
- Includes an unrelated in-progress `VideoThumbnailExtractor` frame-selection refactor already on the branch.

## Testing

- [x] Build passes with no errors (`./gradlew assembleDebug`)
- [x] Unit tests pass (`./gradlew test`)
- [x] `image-library` installed and verified on device (Samsung SM-S948U1)
- [x] Confirmed via `content query` that MediaStore WIDTH/HEIGHT are pre-rotation on the target device (e.g. `4000x3000 / orientation=90`), so the aspect swap is in the correct direction
- [x] Verified install lands only in user 0 — the Dual App profile (user 95) stays empty and the clone does not return
- [ ] Visually confirm rotated portrait/landscape/square photos open without flash or re-fit

## Notes for Reviewer

- The aspect-ratio swap assumes MediaStore WIDTH/HEIGHT are pre-rotation. That is confirmed on the target Samsung device but is device/version-dependent; if a future device reports post-rotation dims with a non-zero orientation, this would need to switch to deriving aspect from the decoded bitmap instead.
- The branch commits the built `.apk` files under `apk/`; GitHub flagged them as large files. Consider dropping them from tracking and adding `apk/` to `.gitignore`.
