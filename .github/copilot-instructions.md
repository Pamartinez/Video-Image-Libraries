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

