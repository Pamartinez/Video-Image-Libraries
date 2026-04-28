# Consistency Quick Reference Card
**For Developers Working on Video-Image-Libraries**

---

## 🚨 GOLDEN RULES - Never Break These

### 1. Test in BOTH Libraries + BOTH Contexts
✅ **ALWAYS** test changes in:
- Image Library (root view)
- Image Library (group view)
- Video Library (root view)
- Video Library (group view)

### 2. One Dialog = One Location
✅ Render each dialog **exactly ONCE** at the **bottom** of the composable  
❌ **NEVER** render the same dialog in multiple places  
❌ **NEVER** use context conditionals: `if (state.currentGroupId != null)`

### 3. ViewModel Methods Must Match
✅ Identical signatures in ImageListViewModel and VideoListViewModel  
✅ Identical async patterns (both use `viewModelScope.launch` or neither do)  
✅ Identical state property names

---

## ✅ Pre-Completion Checklist

Before marking ANY task complete:
- [ ] Tested in BOTH libraries?
- [ ] Tested in BOTH contexts (root + group)?
- [ ] ViewModel method signatures identical?
- [ ] Dialogs rendered exactly once?
- [ ] No context-based conditionals?
- [ ] Ran `./scripts/verify-consistency.ps1`?
- [ ] Installed both apps?

---

## 🔧 Common Tasks

### Adding a New Dialog

1. **Create dialog component** in `common/ui/components/`
2. **Add state flag** to BOTH UiState classes:
   ```kotlin
   val showXxxDialog: Boolean = false
   ```
3. **Add ViewModel methods** to BOTH ViewModels:
   ```kotlin
   fun showXxxDialog() { /* identical implementation */ }
   fun dismissXxxDialog() { /* identical implementation */ }
   ```
4. **Render ONCE** at bottom of BOTH screens:
   ```kotlin
   if (state.showXxxDialog) {
       XxxDialog(
           onConfirm = { viewModel.handleXxx(it) },
           onDismiss = { viewModel.dismissXxxDialog() }
       )
   }
   ```
5. **Run verification**: `./scripts/verify-consistency.ps1`
6. **Test in all contexts**: Root + Group in BOTH libraries

### Adding a Common Operation

1. **Implement in BOTH ViewModels** with identical signatures
2. **Add state properties to BOTH UiState classes**
3. **Use identical async patterns**
4. **Update BOTH screen files** if UI changes needed
5. **Run verification**: `./scripts/verify-consistency.ps1`
6. **Test in BOTH libraries and contexts**

### Fixing a Bug

1. **Check if bug exists in OTHER library**
2. **Fix in BOTH libraries** if applicable
3. **Test in BOTH contexts** (root + group)
4. **Run verification**: `./scripts/verify-consistency.ps1`
5. **Document the fix**

---

## 🛠️ Quick Commands

### Verify Consistency
```powershell
./scripts/verify-consistency.ps1
```
**Expected**: Exit code 0 (all checks pass)

### Build and Install Both Apps
```powershell
./gradlew :image-library:installDebug :video-library:installDebug
```
**Expected**: "Installed on 1 device" for both

### Check for Errors
Open files in IDE and check error panel

---

## ❌ Common Mistakes to Avoid

### Mistake 1: Multiple Dialog Locations
```kotlin
// ❌ WRONG
if (state.showXxxDialog) { XxxDialog(...) } // Location 1
// ...100 lines later...
if (state.showXxxDialog) { XxxDialog(...) } // Location 2
```
**Fix**: Keep only ONE at the bottom

### Mistake 2: Context-Based Conditionals
```kotlin
// ❌ WRONG
if (state.showXxxDialog && state.currentGroupId == null) {
    XxxDialog(...)
}
```
**Fix**: Remove the `&& state.currentGroupId == null` part

### Mistake 3: Inconsistent ViewModel Methods
```kotlin
// ❌ WRONG - ImageListViewModel
fun showXxxDialog() {
    viewModelScope.launch {
        val data = repository.loadData()
        _uiState.update { it.copy(showXxxDialog = true, data = data) }
    }
}

// ❌ WRONG - VideoListViewModel (different!)
fun showXxxDialog() = _uiState.update { it.copy(showXxxDialog = true) }
```
**Fix**: Make them IDENTICAL

### Mistake 4: Different State Properties
```kotlin
// ❌ WRONG
data class ImageListUiState(val xxxData: List<String>)
data class VideoListUiState(val xxxItems: List<String>) // Different name!
```
**Fix**: Use the same property name in BOTH

---

## 📚 Documentation

**Full Details**: `docs/behavioral-consistency/DIALOG_CONSOLIDATION_FIXES_2026-04-27.md`  
**Implementation Plan**: `docs/behavioral-consistency/CONSISTENCY_SYSTEM_IMPLEMENTATION_PLAN_2026-04-27.md`  
**Summary**: `docs/behavioral-consistency/IMPLEMENTATION_FINAL_SUMMARY_2026-04-27.md`  
**Copilot Instructions**: `.github/copilot-instructions.md`

---

## 🆘 When In Doubt

1. **Check existing patterns** in the codebase
2. **Run verification script**: `./scripts/verify-consistency.ps1`
3. **Test in ALL contexts** before completing
4. **Ask questions** rather than making assumptions
5. **Document your changes** if they establish new patterns

---

## ✨ Remember

> "Same operation = Same behavior everywhere"

**No exceptions. No compromises. Always consistent.**

---

**Last Updated**: April 27, 2026  
**Verification Script**: `scripts/verify-consistency.ps1`  
**Always test in**: Root + Group + Both Libraries = 4 scenarios minimum

