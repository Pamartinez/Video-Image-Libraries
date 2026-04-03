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
