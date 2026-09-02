# Samsung Gallery vs. Our Libraries — Feature Gap Analysis

Comparison source: decompiled **Samsung Gallery** APK at
`image-library/sampledata/gallery.apk_Decompiler.com` (feature list derived from
`resources/res/values/strings.xml`, `arrays.xml`, `public.xml`).

This document lists features Samsung Gallery has that **our apps
(`image-library` + `video-library`) currently do NOT have**. Nothing here is
implemented — this is a comparison report only.

Legend for **Fit** (how appropriate it is for our offline, local folder/album
organizer):

- 🟢 **Good fit** — local, no cloud/AI/OEM dependency; realistic to build.
- 🟡 **Partial fit** — buildable but heavier, or only a simplified version is realistic.
- 🔴 **Out of scope** — needs Samsung cloud, on-device AI models, OEM system
  hooks, or a camera; not realistic for us.

---

## What we already have (baseline — for reference)

Albums/folders, nested **Groups**, Copy / Move (with filename-conflict dialog +
progress), Share, Delete, Rename, Details, Open location, Hide folders, Sort &
view-type options (with independent per-album/group sort & view), "Groups always
on top", Search, Backup / Restore + Auto-backup, Dropbox upload, carousel image
viewer / instant video player, drag-to-reorder, refresh previews, clear
thumbnails. Deletes route through the system trash via `MediaTrashHelper`.

---

## 1. Item organization & metadata

| Feature | What it does | Fit |
|---|---|---|
| **Favorites** | Star/heart items; a dedicated "Favorites" smart album. We have no favorite concept at all. | 🟢 |
| **Recycle bin / Trash UI** | A user-facing Trash album to review and **restore** recently deleted items (with retention period). We delete to system trash but expose no restore UI. | 🟢 |
| **Tags** | Add custom text tags to items and filter/search by them (`add_tag`, `tags`). | 🟢 |
| **Edit date / time** | Change an item's captured date (`edit_date`). | 🟢 |
| **Edit / Add location** | Set or edit GPS location metadata on an item (`edit_location`, `add_location`). | 🟢 |
| **Set as album cover** | Manually pick an album's cover image (`set_as_cover_image`). We only auto-refresh previews. | 🟢 |
| **Pin** | Pin items/stories to the top (`pin`, `story_pinned_to_top`). | 🟢 |

## 2. Cleanup & storage

| Feature | What it does | Fit |
|---|---|---|
| **Duplicate finder** | Detect duplicate photos/videos and bulk-delete to save space (`duplicate`, `no_duplicates_description`). | 🟢 |
| **Optimize / cleanup storage** | Suggestions for large/blurry/screenshot cleanup. | 🟡 |
| **Convert / compress** | Convert or compress media to save space (`entity_capsule_title_convert`). | 🟡 |

## 3. Viewer / playback

| Feature | What it does | Fit |
|---|---|---|
| **Slideshow** | Auto-advancing full-screen slideshow with settings (auto-repeat, selected items) (`slideshow_*`, `labs_title_slideshow_*`). | 🟢 |
| **Rotate** | Rotate an image left/right and save (`sa_rotate`). | 🟢 |
| **Set as wallpaper / lock screen** | Apply an image as home/lock wallpaper (`set_as_wallpaper`, `lock_screen`). | 🟢 |
| **Print** | Print one or multiple pictures (`printer`, `labs_title_print_multiple_pictures`). | 🟡 (uses Android print framework) |
| **Zoom-in / high-res pan** | Deep zoom into large images. We have `ZoomableImage`; Samsung adds finer high-res tiling. | 🟡 |

## 4. Photo/video editing (built-in editor)

| Feature | What it does | Fit |
|---|---|---|
| **Crop** | Crop / straighten a picture (`crop_label`). | 🟢 |
| **Basic editor** | Brightness/contrast/filters/stickers/drawing/add-text. | 🟡 (large feature) |
| **Video trim / merge / split / reverse** | Basic video editing (`trim`, `reverse_order`, `extract`). | 🟡 |
| **GIF maker** | Create GIFs from photos/video (`create_gif_menu`, `gif_saved`). | 🟡 |
| **Collage** | Combine multiple photos into a collage (`sa_collage`, `collage_saved_in`). | 🟡 |
| **Highlight reel / Create movie** | Auto-generate a montage video (`highlight_reel`, `shot_mode_highlight_video`). | 🟡 |
| **Object eraser** | AI removal of objects/people (`object_eraser`). | 🔴 (on-device AI) |
| **Photo Remaster** | AI one-tap enhancement (`remaster`, `remaster_picture`). | 🔴 (on-device AI) |
| **Portrait / Slow-motion effects** | Camera-mode-specific effects (`shot_mode_portrait`, `slow_motion`). | 🔴 (camera/OEM) |

## 5. Discovery & smart categorization

| Feature | What it does | Fit |
|---|---|---|
| **Map / Places view** | Browse media on a map by GPS location (`map`, `map_view`, `recap_places`). | 🟡 (needs maps SDK; only geotagged media) |
| **Suggestions tab** | Suggested actions/albums (`bottom_tab_drawer_suggestions`, `suggest_word`). | 🟡 |
| **Stories / Memories / Highlights** | Auto-generated event albums & recaps (`stories`, `highlights`, `memories`). | 🔴 (AI curation) |
| **People / Faces** | Face grouping into people albums (`people`, `story_category_people`). | 🔴 (on-device face AI) |
| **Things / Scene tagging** | Auto content categorization (`things`). | 🔴 (on-device AI) |

## 6. Sharing & collaboration

| Feature | What it does | Fit |
|---|---|---|
| **Shared albums** | Create a cloud album others can view/contribute to (`share_album`, `shared_album`). | 🔴 (Samsung cloud) |
| **Add to Story** | Add items to a shared/event story (`add_to_event`). | 🔴 (tied to Stories) |

## 7. Security / privacy

| Feature | What it does | Fit |
|---|---|---|
| **Secure Folder** | Move items into Samsung Knox Secure Folder (`move_to_secure_folder`). | 🔴 (Samsung Knox/OEM) |
| **Locked/hidden with password** | Password/biometric-locked private album. (We have plain Hide, no lock.) | 🟡 (biometric-lock buildable; not Knox) |

---

## Recommended "good fit" shortlist (if we ever implement)

These are the 🟢 items that match our offline organizer and would apply to
**both** `image-library` and `video-library`:

1. **Favorites** (star + Favorites smart album + sort/filter by favorite)
2. **Recycle bin / Trash restore UI**
3. **Slideshow** (viewer auto-play with settings)
4. **Duplicate finder** (cleanup)
5. **Set as album cover** (manual cover selection)
6. **Rotate** (image; images only)
7. **Set as wallpaper** (image; images only)
8. **Crop** (image; images only)
9. **Tags** + tag search
10. **Edit date / Edit location** metadata

> Note: per the Behavioral Consistency Rule, any common feature must ship in
> **both** libraries; only truly media-specific ones (Rotate, Crop, Set as
> wallpaper = image-only) stay isolated.
