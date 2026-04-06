# Copilot Instructions

## Project Scope
Before making any change, clearly state which project the change applies to:
- `image-library`
- `video-library`
- `both`

If the user has already specified the project in their request, use that — do **not** ask again.
Only ask when the target project is **not clear** from the request.

## Helper Code
- Always look for existing helper/utility code before writing new logic.
- If similar code is used in **more than 2 places**, extract it into a shared helper (in the `common` module or a shared utility file).
- Prefer reuse over duplication.

## Common-First Rule — Mandatory Before Every Code Change
**Before writing or placing ANY new code**, ask and answer these questions in order:

1. **Can this logic run in both libraries without modification?**
   → If yes, it **must** go in `common`. No exceptions.

2. **Can it be made shared with a small abstraction** (parameter, callback, interface, or generic type)?
   → If yes, implement the abstraction in `common` and call it from both libraries.

3. **Does it only differ because of a library-specific type** (e.g. `ImageItem` vs `VideoItem`, `AppPreferences` image vs video)?
   → Extract everything that is type-independent into `common`; only the type-specific binding stays in the library file.

4. **Only if the logic is truly, fundamentally different** between the two libraries (different UI, different business rules) may it live exclusively in a library-specific file.

**Double-check rule:** After writing code in a library-specific file, re-read it once more and ask:
> *"Is there even one line here that is identical or near-identical in the other library?"*
If the answer is yes, move that line (or block) to `common` before finishing.

This rule applies to **all layers**: ViewModels, Repositories, Screens, Components, utilities, BackupManager, AppPreferences helpers, and any future additions.

## Shared Base Architecture
- `image-library` and `video-library` share the same base structure and logic.
- Always write code that works for **both** projects.
- The only difference between the two libraries is **what they display** (images vs. video).
- Any feature, fix, or refactor should be designed to be compatible with both libraries unless explicitly scoped to one.

## Shared Components & Screens
- Before creating any new screen or component, **analyze if it can be shared** between `image-library` and `video-library`.
- If the screen or component logic is the same (or nearly the same) between both projects, place it in the `common` module.
- Only create a project-specific screen or component if the logic or UI **fundamentally differs** and cannot be abstracted.
- When in doubt, prefer a shared base with customizable parameters (e.g., content type, display config) over duplicating code.

## Sort Order Integrity — Mandatory Rule
**Albums and Groups must ALWAYS respect their own sort order**, regardless of context or operation.

This applies to **ALL scenarios** without exception:
- Displaying items in the main view
- Copy operations (FolderPickerScreen, CreateAlbumPickerScreen)
- Move operations (FolderPickerScreen, CreateAlbumPickerScreen)
- Create Album flow
- Any UI component that displays items from an album or group

**Implementation rules:**
- **Root view**: Always use `orderedMixedItems` (respects root sort preferences)
- **Group view** (`currentGroupId != null`): Always use `currentGroupOrderedMixedItems` (respects that specific group's sort preferences)
- **Album view**: Always use the album's own sort order
- **Never** fall back to root sort when inside a group or album — each container has independent sort settings that must be respected
- When passing items to any screen or component, verify you're passing the correctly sorted list based on the current context (root, group, or album)

## Copy / Move Operations — Mandatory UX Rules
**Every** Copy or Move operation — regardless of where it is triggered — must:
1. **Show the progress popup** (`CopyMoveProgressDialog` via `CopyMoveAndConflictOverlayHost`) while the operation is running.
2. **Check for filename conflicts** and show the conflict dialog (`FileConflictDialog`) when a name clash is detected, letting the user choose **Rename**, **Replace**, or **Skip**.

This applies to ALL entry points without exception:
- BottomActionBar (Copy / Move buttons in selection mode)
- Carousel Copy / Move actions
- Create Album flow (Copy / Move confirmation step)
- Any future entry point that triggers a file-system copy or move

**Implementation rules:**
- Every early-return picker screen (`FolderPickerScreen`, `CreateAlbumPickerScreen`) must be wrapped in a `Box` that also renders `CopyMoveAndConflictOverlayHost` on top, so the overlay is present even while the picker is visible.
- When a Copy or Move is initiated from a context that has a higher-priority early-return (e.g., the Carousel), **close that context first** (e.g., call `closeCarousel()`) before opening the picker — so the picker's overlay host is always reachable in the composition tree.
- `CopyMoveAndConflictOverlayHost` must be present in **every branch** of the UI where a copy/move can be in progress, not only in the main-screen Box.

## Backup & Restore — Mandatory Rules
**All user-configurable settings must be included in the backup system**, both existing settings and any new settings added in the future.

### Settings Coverage
- **Every setting visible in the Settings screen** must be backed up and restored.
- When adding a new feature with user-configurable options, **always add backup support immediately** — do not defer it.
- Settings that are backed up include (but are not limited to):
  - View preferences (viewType, folderViewType)
  - Sort options (all sort-related settings)
  - UI behavior toggles (carouselShowBarsOnOpen, carouselAlwaysHideOverlay, instantPlayerEnabled, etc.)
  - Feature flags (independentSortEnabled, groupsAlwaysOnTop, autoBackupEnabled)
  - Custom ordering data (customGroupOrder, customMixedOrder, customAlbumOrder, customFolderOrder)
  - Hidden folder configuration
  - Tab selection state

### BackupManager Architecture
Follow the **Common-First Rule** for backup code:

1. **Common BackupManager** (`common/src/main/java/com/example/common/data/util/BackupManager.kt`):
   - Contains all shared backup logic (file I/O, JSON serialization, group data persistence)
   - Defines `SharedSettings` data class for settings that exist in both libraries
   - Provides `writeSharedSettings()` and `readSharedSettings()` helper methods
   - **Rule**: If a setting exists in both libraries with the same name and type, it goes in `SharedSettings`

2. **Library-specific BackupManager** (image-library and video-library):
   - Extends the common `BackupManager`
   - Implements only library-specific settings (e.g., `carouselShowBarsOnOpen` for image-library, `instantPlayerEnabled` for video-library)
   - Calls `writeSharedSettings()` and `readSharedSettings()` for shared settings
   - **Keep these files minimal** — only true library-specific logic belongs here

### Auto-Backup Triggers
When `autoBackupEnabled` is `true`, backups are automatically saved in the following scenarios:

1. **After any data-modifying operation**:
   - Creating, renaming, or destroying a group
   - Adding/removing folders or groups to/from a group
   - Reordering items (groups, folders, albums, images)
   - Hiding or unhiding folders/groups
   - Changing any sort option

2. **After settings changes**:
   - Toggling any setting in the Settings screen
   - Changing view type or sort order

3. **App lifecycle events**:
   - When the app goes to background (`onAppBackground()` / `MainActivity.onStop`)
   - When the ViewModel is cleared (`onCleared()`)

4. **Debouncing**:
   - Operations are debounced using `scheduleAutoBackup()` which waits `AUTO_BACKUP_DEBOUNCE_MS` before saving
   - Background and lifecycle events use immediate backup without debouncing

### Implementation Checklist (When Adding a New Setting)
When you add a new user-configurable setting:
- [ ] Add the property to `AppPreferences` in the appropriate library
- [ ] If it's shared between both libraries, add it to `SharedSettings` in common `BackupManager`
- [ ] If it's shared, add it as a parameter to `writeSharedSettings()` and write it to JSON
- [ ] If it's shared, read it in `readSharedSettings()` and return it in `SharedSettings`
- [ ] If it's library-specific, add it to the library's `writeSettings()` method (write to JSON)
- [ ] Add restore logic in the library's `readSettings()` method (read from JSON)
- [ ] Update the JSON schema documentation in common `BackupManager` header comment
- [ ] If the setting affects data or display, call `scheduleAutoBackup()` after changes (when auto-backup is enabled)
- [ ] Update the UI state in ViewModel after restore (in `restoreBackupFromFile()` and `refreshStateAfterRestore()`)

