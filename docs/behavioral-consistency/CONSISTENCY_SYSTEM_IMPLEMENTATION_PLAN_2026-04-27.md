# Consistency System Implementation Plan
**Date**: April 27, 2026

## Problem Statement
The same user operations (creating albums, creating groups, showing dialogs) have different implementations depending on:
- Root view vs. Group view context
- Image-library vs. Video-library

This violates the BEHAVIORAL CONSISTENCY RULE and creates confusing user experiences.

## Root Causes Identified
1. **ViewModel method inconsistencies** - Same operations implemented differently between libraries
2. **Duplicate dialog rendering** - Dialogs rendered in multiple places with conditional logic based on context
3. **No automated verification** - No checks to catch inconsistencies during development
4. **Insufficient guidelines** - Copilot Instructions don't enforce strict enough consistency checks

## Implementation Status

### ✅ Phase 1: ViewModel Parity Fixes (COMPLETED)

#### Fixed: VideoListViewModel.showCreateAlbumDialog()
**Before:**
```kotlin
fun showCreateAlbumDialog() = _uiState.update { it.copy(showCreateAlbumDialog = true) }
```

**After:**
```kotlin
fun showCreateAlbumDialog() {
    viewModelScope.launch {
        val dcimNames = repository.getExistingDcimFolderNames()
        _uiState.update { it.copy(showCreateAlbumDialog = true, dcimFolderNames = dcimNames) }
    }
}
```

**Impact**: Both libraries now validate album names against DCIM folders identically, in all contexts (root and group views).

#### Fixed: ImageListViewModel.showGroupNameForCreation()
**Before:**
```kotlin
fun showGroupNameForCreation() {
    _uiState.update { it.copy(showGroupNameDialog = true, groupNameDialogForCreation = true) }
}
```

**After:**
```kotlin
fun showGroupNameForCreation() {
    viewModelScope.launch {
        val allNames = groupRepository.getAllGroups().map { it.name }.toSet()
        val suggested = generateUniqueGroupName(allNames)
        _uiState.update {
            it.copy(
                showGroupNameDialog = true,
                groupNameDialogForCreation = true,
                existingGroupNames = allNames,
                suggestedGroupName = suggested
            )
        }
    }
}
```

**Impact**: Both libraries now load group names asynchronously and pre-generate unique suggested names identically.

#### Added: ImageListUiState properties
```kotlin
val existingGroupNames: Set<String> = emptySet(),
val suggestedGroupName: String = "",
```

**Impact**: State parity between ImageListUiState and VideoListUiState for group creation flow.

---

### ✅ Phase 2: Dialog Consolidation (COMPLETED)

#### Changes Made
**ImageListScreen.kt:**
- Removed duplicate CreateAlbumDialog at line 344 (inside FoldersTab)
- Removed duplicate CreateAlbumDialog at lines 590-596 (inside CreateAlbumPickerScreen)
- Fixed CreateAlbumDialog at line 882-884 to remove context-based conditional `&& state.currentFolderBucketId == null`
- **Result**: 3 locations → 1 location (at bottom, unconditional)

**VideoListScreen.kt:**
- Removed duplicate CreateAlbumDialog at lines 522-528 (inside FoldersTab)
- Fixed CreateAlbumDialog at lines 1162-1168 to remove context-based conditional `&& state.currentFolderBucketId == null`
- **Result**: 2 locations → 1 location (at bottom, unconditional)

#### Impact
- CreateAlbumDialog now renders identically in ALL contexts (root view, group view, folders tab, all images/videos tab)
- No more conditional rendering based on `currentGroupId` or `currentFolderBucketId`
- Both libraries follow the same pattern: ONE dialog rendering location at the bottom of the composable

---

### ✅ Phase 3: Automated Verification Script (COMPLETED)

#### Script Created
- **Location**: `scripts/verify-consistency.ps1`
- **Exit Codes**: 0 = all checks passed, 1 = inconsistencies found
- **Checks Implemented**:
  1. ViewModel method signature matching for common operations
  2. Duplicate dialog rendering detection (finds multiple rendering locations)
  3. State property parity between UiState classes

#### Test Results
Script successfully detected:
- ✅ ViewModel method parity (showCreateAlbumDialog, showGroupNameForCreation, etc.)
- ✅ Dialog consolidation success (CreateAlbumDialog now rendered once per screen)
- ✅ State property parity (dcimFolderNames exists in both)

---

### 🔄 Phase 4: Copilot Instructions Update (PENDING)

#### Current Problem
**ImageListScreen.kt** renders `CreateAlbumDialog` in **3 locations:**
- Line 344: Inside FoldersTab when showCreateAlbumDialog is true
- Line 592: Inside CreateAlbumPickerScreen
- Line 883: At bottom of main composable (root view and group view)

**VideoListScreen.kt** renders `CreateAlbumDialog` in **2 locations:**
- Line 524: Inside FoldersTab  
- Line 1164: At bottom of main composable

#### Proposed Fix
**Consolidate to ONE location per screen** - at the bottom of the main composable, rendered unconditionally when `state.showCreateAlbumDialog == true`, regardless of:
- Current context (root vs. group)
- Current tab (Folders vs. All Videos/Images)
- Current picker state

**Same pattern applies to:**
- GroupNameDialog
- CreateFolderDialog
- All other shared dialogs

#### Files to Modify
1. `image-library/src/main/java/com/imagelibrary/ui/screen/ImageListScreen.kt`
2. `video-library/src/main/java/com/videolibrary/ui/screen/VideoListScreen.kt`

---

### 🔄 Phase 3: Automated Verification Script (PENDING)

#### Script Purpose
Catch consistency violations automatically before code reaches production.

#### Checks to Implement
1. **ViewModel Method Signature Matching**
   - Compare method signatures between ImageListViewModel and VideoListViewModel
   - Flag differences in:
     - Method names for common operations
     - Parameter types and names
     - Return types
     - Async behavior (viewModelScope.launch presence)

2. **Dialog Rendering Duplication Detection**
   - Search for duplicate dialog component calls within same file
   - Examples: Multiple `CreateAlbumDialog(`, `GroupNameDialog(`, etc.

3. **Conditional Dialog Rendering Detection**
   - Search for patterns like:
     ```kotlin
     if (state.currentGroupId != null) { CreateAlbumDialog(...) }
     if (state.currentFolderBucketId == null) { GroupNameDialog(...) }
     ```
   - Dialogs should NOT be conditionally rendered based on context

4. **State Property Parity**
   - Verify ImageListUiState and VideoListUiState have matching properties for common operations
   - Examples: dcimFolderNames, existingGroupNames, suggestedGroupName

#### Script Location
`scripts/verify-consistency.ps1`

#### Exit Codes
- `0`: All checks passed
- `1`: Inconsistencies found (prints violations to stderr)

#### CI/CD Integration
Can be added to Gradle build as a pre-compilation task:
```kotlin
tasks.register<Exec>("verifyConsistency") {
    commandLine("powershell", "-File", "scripts/verify-consistency.ps1")
}
tasks.named("preBuild") { dependsOn("verifyConsistency") }
```

---

### 🔄 Phase 4: Copilot Instructions Update (PENDING)

#### New Rules to Add

##### MANDATORY CONSISTENCY VERIFICATION CHECKLIST
Before completing ANY task:
- [ ] Have I tested this in BOTH image-library and video-library?
- [ ] Have I tested this in BOTH root view and group view contexts?
- [ ] Are method signatures identical between ImageListViewModel and VideoListViewModel?
- [ ] Are dialogs rendered exactly ONCE per screen (not duplicated)?
- [ ] Are dialogs shown unconditionally when their state flag is true (no context-based conditionals)?
- [ ] Have I run `./scripts/verify-consistency.ps1` to verify no regressions?

##### DIALOG RENDERING RULE
**Each dialog component MUST be rendered exactly ONCE per screen composable, at the bottom, with NO conditional rendering based on context (root vs group).**

❌ **WRONG:**
```kotlin
// Inside FoldersTab
if (state.showCreateAlbumDialog) { CreateAlbumDialog(...) }
// Inside group detail
if (state.showCreateAlbumDialog && state.currentGroupId != null) { CreateAlbumDialog(...) }
```

✅ **CORRECT:**
```kotlin
// At bottom of main screen composable, ONCE
if (state.showCreateAlbumDialog) { CreateAlbumDialog(...) }
```

##### VIEWMODEL PARITY RULE
**For ANY common operation, the method signature, parameters, state updates, and async behavior MUST be identical between ImageListViewModel and VideoListViewModel.**

When adding a new common operation:
1. Implement the method with IDENTICAL signature in BOTH ViewModels
2. Use IDENTICAL state property names
3. Use IDENTICAL async patterns (both use viewModelScope.launch, or neither do)
4. Update BOTH UiState data classes with matching properties

---

### 🔄 Phase 5: Documentation (PENDING)

#### Documents to Create

1. **CONSISTENCY_VERIFICATION_SYSTEM_2026-04-27.md**
   - How the verification script works
   - What checks it performs
   - How to add new checks
   - CI/CD integration guide

2. **DIALOG_CONSOLIDATION_FIXES_2026-04-27.md**
   - Before/after comparison of dialog rendering
   - Why consolidation prevents bugs
   - Pattern to follow for future dialogs

3. **CONSISTENCY_TESTING_CHECKLIST.md**
   - Manual test cases for all common operations
   - Test in root view + group view + both libraries
   - Verification steps after ANY code change

---

## Testing Plan

### Manual Testing Required
After all phases complete:

1. **Create Album Flow**
   - Root view + button → Album (both apps)
   - Group view + button → Album (both apps)
   - Verify: Same dialog, same validation, same pre-filled name

2. **Create Group Flow**
   - Root view + button → Group (both apps)
   - Group view + button → Group (both apps)
   - Verify: Same dialog, same validation, same suggested name

3. **Copy/Move Operations**
   - Root selection → Copy (both apps)
   - Group selection → Copy (both apps)
   - Verify: Same picker, same conflict handling, same progress dialog

4. **All Other Common Operations**
   - Follow CONSISTENCY_TESTING_CHECKLIST.md

### Automated Testing
- Run `./scripts/verify-consistency.ps1`
- Should exit with code 0 (all checks pass)

---

## Next Steps

1. **Get User Approval** on this plan
2. **Implement Phase 2** (Dialog Consolidation)
3. **Implement Phase 3** (Verification Script)
4. **Implement Phase 4** (Copilot Instructions Update)
5. **Implement Phase 5** (Documentation)
6. **Build and Install Apps** for manual testing
7. **Run Verification Script** to confirm no regressions
8. **Document Final Results** in DIALOG_CONSOLIDATION_FIXES_2026-04-27.md

---

## Estimated Time
- Phase 2: ~30 minutes (careful surgery on dialog rendering)
- Phase 3: ~45 minutes (PowerShell script with multiple checks)
- Phase 4: ~15 minutes (Copilot Instructions updates)
- Phase 5: ~30 minutes (3 comprehensive documents)
- Testing: ~20 minutes (build, install, manual verification)

**Total**: ~2.5 hours for complete implementation and verification

---

## Success Criteria
- ✅ No duplicate dialog rendering in any screen file
- ✅ All common operations behave identically in both libraries and both contexts
- ✅ Verification script catches inconsistencies automatically
- ✅ Copilot Instructions enforce consistency through mandatory checklists
- ✅ Manual testing confirms identical user experience everywhere
- ✅ Documentation provides clear patterns for future development


