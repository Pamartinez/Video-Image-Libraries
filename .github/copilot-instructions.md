# Copilot Instructions

## 🎯 QUALITY FIRST RULE — FOUNDATIONAL PRINCIPLE
**Go slow. Double-check. Triple-check. Get it right the first time.**

### Working Methodology:
1. **Thoroughness Over Speed**
   - Take time to fully understand the problem before implementing
   - Double-check and triple-check work before completing
   - Verify changes compile and function correctly
   - Test in BOTH libraries when making shared changes
   - Better to spend extra time upfront than go back and forth with corrections

2. **Clarity Over Assumptions**
   - **Ask clarifying questions when ANYTHING is not 100% clear**
   - Don't guess or make assumptions about requirements
   - If implementation details are ambiguous, ask before proceeding
   - If multiple approaches are possible, present options and ask for preference
   - More questions upfront = better results and fewer iterations

3. **Quality Checklist Before Completing**
   - ✅ Does this solve the ENTIRE problem (not just part of it)?
   - ✅ Does this work correctly in BOTH libraries?
   - ✅ Have I verified there are no compilation errors?
   - ✅ Does this follow all architectural rules (consistency, common-first, etc.)?
   - ✅ Is the implementation clean, maintainable, and well-documented?
   - ✅ Have I tested edge cases and error scenarios?

**Remember: It's better to ask 10 questions and get it perfect than to implement incorrectly and waste time fixing it.**

---

## ⚠️ BEHAVIORAL CONSISTENCY RULE — CRITICAL
**Both `image-library` and `video-library` MUST behave identically for ALL common operations.**

This is a **non-negotiable architectural principle** that supersedes all other considerations.

### Common Operations That MUST Have Identical Behavior:
- **Creating groups** (flow, dialogs, validation, naming)
- **Creating albums** (picker flow, selection, copy/move dialog)
- **Copy operations** (folder picker navigation, progress display, conflict handling)
- **Move operations** (folder picker navigation, progress display, conflict handling)
- **Deleting items** (confirmation dialogs, batch operations)
- **Group navigation** (opening, closing, browsing nested groups)
- **Sort order preservation** (groups/albums maintaining their sort in pickers)
- **Hiding/unhiding folders** (screen flow, toggle behavior)
- **Settings behavior** (all shared settings must work identically)
- **Backup/restore** (shared settings backup and restore identically)
- **Selection mode** (enter/exit, select all, multi-select)
- **Drag-to-reorder** (reorder UX, persistence)
- **Search functionality** (search UI, filtering, results display)
- **Details screens** (information displayed, format, layout)
- **Rename operations** (dialog, validation, error handling)
- **File conflict resolution** (rename/replace/skip options, "Keep Both" vs "Rename" labels)

### Enforcement Rules:
1. **When implementing ANY new feature in one library, ALWAYS implement it in the other** — even if not explicitly requested.
2. **When fixing ANY bug in one library, ALWAYS check if the same bug exists in the other** and fix it there too.
3. **When refactoring code in one library, apply the same refactoring to the other.**
4. **Test both libraries** after any change to common operations to ensure they behave the same way.
5. **If behavior diverges unintentionally**, treat it as a **critical bug** and fix immediately.
6. **Before declaring a task complete**, verify that both libraries exhibit identical behavior for the feature/fix.

### New Feature Development Rule:
**When adding ANY new functionality:**
1. First determine if it's media-specific (image-only or video-only) or common (applies to both)
2. If common (99% of cases):
   - Implement in `common` module when possible
   - If library-specific wrappers are needed, implement in **BOTH** libraries simultaneously
   - Ensure identical UX, dialogs, flows, error messages, and behavior
   - Use the same parameter names, function signatures, and state management patterns
3. If media-specific:
   - Clearly document WHY it's specific to one library
   - Keep it isolated and minimal

### The Only Acceptable Differences:
- **Media type displayed** (images vs. videos)
- **Media-specific settings** (e.g., `carouselShowBarsOnOpen` in image-library, `instantPlayerEnabled` in video-library)
- **Thumbnail rendering** (AsyncImage vs. VideoFrameDecoder)
- **Item labels** ("image" vs. "video" in UI text)
- **Player functionality** (instant video player vs. carousel image viewer)

**Everything else must be identical.**

### Examples of Required Consistency:
✅ **DO:** If you add a "Mark as Favorite" feature to image-library, add it to video-library too  
✅ **DO:** If you fix a group deletion bug in video-library, check and fix it in image-library  
✅ **DO:** If you improve the copy progress dialog in one app, apply it to both  
✅ **DO:** If you add drag-to-reorder in albums, ensure both apps support it identically  
❌ **DON'T:** Implement a feature in only one library unless it's truly media-specific  
❌ **DON'T:** Let UX flows diverge between the apps (e.g., different dialog flows)  
❌ **DON'T:** Use different parameter names or state management patterns for the same operation  

**When in doubt: implement it in BOTH apps.**

## ⚠️ UI COMPONENT CONSISTENCY RULE — MANDATORY
**ALL UI components, dialogs, and interactive elements MUST be identical across both libraries.**

This rule enforces visual and behavioral consistency for every user-facing component.

### Dialogs & Modals:
1. **File Conflict Dialog** (`FileConflictDialog.kt`)
   - Samsung Gallery style: "Rename item or replace existing one?" title
   - Message format: "There's already an item named {fileName} in the selected album."
   - "Apply to all items" checkbox
   - Three buttons: Skip | Replace | Rename (in this order)
   - "Rename" label (default parameter)
   - Corner radius: 28.dp

2. **Copy/Move Progress Dialog** (`CopyMoveProgressDialog.kt`)
   - Same title format: "Copying..." / "Moving..."
   - Same progress bar style and colors
   - Same "Cancel" button behavior
   - Same overlay background dimming

3. **Standard Dialog Styling** (for all other dialogs):
   - Corner radius: 28.dp (or 16.dp for smaller dialogs)
   - Container color: `Color(0xFF2C2C2C)` (dark theme)
   - Title: 20.sp, SemiBold, White
   - Body text: 15.sp, `Color(0xFFBBBBBB)`
   - Button colors: Blue (#2979FF) for primary, Gray for secondary

### Menus:
1. **3-Dot Overflow Menus** (`AppMoreMenuButton`)
   - ALL overflow menus MUST use `AppMoreMenuButton` component
   - Standard items: Sort | View as | Settings | ─── | About App
   - Same order, same styling, same behavior

2. **Bottom Action Bar** (`BottomActionBar.kt`)
   - Selection mode actions: Copy, Move, Delete, Share, etc.
   - Same icons, same layout, same action order
   - Overflow menu (⋮) for additional actions
   - Same elevation, colors, and spacing

3. **Context Menus**
   - Same menu items, same order
   - Same styling (16.dp corners, same colors)
   - Same dismiss behavior

### Implementation Rules:
1. **Use Shared Components First**
   - If a component exists in `common/ui/components/`, USE IT
   - Do NOT duplicate components between image-library and video-library
   - Do NOT create library-specific variants unless absolutely necessary

2. **When Creating New Components**
   - Place in `common` module if it applies to both libraries
   - Follow Samsung Gallery design patterns when applicable
   - Document the component's purpose in a header comment

3. **Testing Requirement**
   - After creating or modifying ANY UI component, test it in BOTH apps
   - Verify identical appearance and behavior
   - Check animations, timing, colors, spacing

### Enforcement:
❌ **NEVER** create different dialog styling or animation timing  
❌ **NEVER** use different menu layouts or icon orders between apps  
✅ **ALWAYS** use shared components from `common` for all UI elements  
✅ **ALWAYS** apply the same visual styling to both apps  
✅ **ALWAYS** test UI changes in BOTH apps before completing a task  

**Visual consistency is NON-NEGOTIABLE. Users should not notice ANY difference in UI behavior between apps.**

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

### Implementation Rules:
1. **Use Shared Components**
   - `FileConflictDialog` from `common/ui/components/` for ALL conflict resolution
   - `CopyMoveProgressDialog` from `common/ui/components/` for ALL progress display
   - Shared conflict resolution logic in ViewModels (bulkResolution tracking)

2. **Overlay Host Placement**
   - Every early-return picker screen (`FolderPickerScreen`, `CreateAlbumPickerScreen`) must be wrapped in a `Box` that also renders `CopyMoveAndConflictOverlayHost` on top, so the overlay is present even while the picker is visible.
   - When a Copy or Move is initiated from a context that has a higher-priority early-return (e.g., the Carousel), **close that context first** (e.g., call `closeCarousel()`) before opening the picker — so the picker's overlay host is always reachable in the composition tree.
   - `CopyMoveAndConflictOverlayHost` must be present in **every branch** of the UI where a copy/move can be in progress, not only in the main-screen Box.

3. **Testing & Verification**
   - After ANY change to copy/move logic in one app, test it in BOTH apps
   - Verify identical progress dialog appearance
   - Verify identical conflict dialog behavior
   - Verify identical error handling
   - Test with single-file and multi-file operations
   - Test conflict scenarios (skip, replace, keep both, apply to all)

### Enforcement:
❌ **NEVER** implement copy/move behavior differently between the two apps  
❌ **NEVER** use different dialog styling for copy/move operations  
❌ **NEVER** use different error messages or completion messages  
✅ **ALWAYS** use the same shared components from `common` module  
✅ **ALWAYS** test copy/move operations in BOTH apps after changes  
✅ **ALWAYS** keep progress tracking and conflict resolution logic identical

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

