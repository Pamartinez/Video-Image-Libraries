# Group Sort Order Architecture

**Created:** April 6, 2026  
**Issue Fixed:** Group Custom sort not preserved in picker screens

---

## 🎯 Core Principle

**Groups have independent sort preferences that MUST be respected in ALL contexts where the group's contents are displayed.**

This includes:
- Group detail screens
- Folder picker screens (move/copy operations)
- Create album picker screens
- Any future screen that displays group contents

---

## ⚠️ The Problem Pattern

### What Went Wrong

Both `FolderPickerScreen` and `CreateAlbumPickerScreen` were **re-calculating** group contents from raw data:

```kotlin
// ❌ WRONG: Re-calculating group contents
val memberFolders = memberBucketIds.mapNotNull { bid -> 
    allFolders.find { it.bucketId == bid } 
}
// This returns folders in whatever order the 'folders' list is in (root sort),
// NOT the group's configured sort order!
```

**Why this is wrong:**
- The `folders` list is sorted according to ROOT sort preferences
- When you filter it by `memberBucketIds`, you get folders in root order
- The group's own sort preference (Custom, A-Z, Z-A) is completely ignored

### Symptoms

User navigates: Root → Group (Custom sort: "Ninas, Album 100, Olivia") → Album → Move/Copy  
**Expected:** Picker shows "Ninas, Album 100, Olivia"  
**Actual:** Picker shows "Ninas, Download, Album, Album 100" (alphabetical or root order)

---

## ✅ The Solution Pattern

### Architecture

**Use pre-calculated, correctly-ordered items from the ViewModel instead of re-calculating.**

```kotlin
// ✅ CORRECT: Use pre-calculated ordered items
val preCalculated = groupOrderedItems[currentBrowseGroupId]
if (preCalculated != null) {
    // Use exact items from GroupDetailScreen - already in correct order!
    preCalculated.mapNotNull { item ->
        when (item) {
            is FolderItem -> MixedItem.Folder(item)
            is GroupItem -> MixedItem.Group(item)
            else -> null
        }
    }
} else {
    // Fallback: re-calculate (for groups without pre-calculated data)
    val browsedGroup = allGroups.find { it.groupId == currentBrowseGroupId }
    val memberBucketIds = browsedGroup?.memberBucketIds ?: emptyList()
    // ... existing re-calculation logic ...
}
```

### Implementation Checklist

When creating ANY screen that displays group contents:

1. **Add `groupOrderedItems` parameter:**
   ```kotlin
   groupOrderedItems: Map<Long, List<Any>> = emptyMap()
   ```

2. **Check for pre-calculated items FIRST:**
   ```kotlin
   val preCalculated = groupOrderedItems[currentGroupId]
   if (preCalculated != null) {
       // Use pre-calculated
   } else {
       // Fallback to re-calculation
   }
   ```

3. **Pass ordered items at call sites:**
   ```kotlin
   val groupOrderedItemsMap = if (state.currentGroupId != null) {
       mapOf(state.currentGroupId!! to state.currentGroupOrderedMixedItems)
   } else {
       emptyMap()
   }
   
   SomePickerScreen(
       // ... other params ...
       groupOrderedItems = groupOrderedItemsMap
   )
   ```

4. **Test with Custom-sorted groups:**
   - Create a group with Custom sort
   - Reorder items manually
   - Navigate through the flow that uses your new screen
   - Verify the order matches exactly

---

## 📋 Screens That Must Follow This Pattern

### ✅ Already Fixed

- `FolderPickerScreen` (common + wrappers)
- `CreateAlbumPickerScreen` (common + wrappers)

### 🔍 Check These If Created

- Any new picker screen that navigates through groups
- Any dialog that displays group contents for selection
- Any screen that allows browsing folder/album hierarchies

---

## 🧪 Testing Requirements

When modifying or creating screens that display group contents:

### Manual Test Flow

1. **Create test group:**
   - Create a new group
   - Add 3-4 folders/albums to it
   - Change sort to "Custom"
   - Manually reorder items (e.g., "C, A, B" instead of "A, B, C")

2. **Test the flow:**
   - Navigate into the group
   - Trigger the operation that uses your screen (move/copy/create album/etc.)
   - Navigate back to the group in the picker/dialog
   - **Verify:** Order is EXACTLY "C, A, B" (same as group detail screen)

3. **Test all sort options:**
   - Repeat with A-Z sort
   - Repeat with Z-A sort
   - Repeat with other sort options
   - Verify each sort preference is respected

### Automated Test (Future)

```kotlin
@Test
fun `group custom sort order is preserved in picker`() {
    // Create group with custom order
    val group = createGroupWithCustomOrder(listOf("C", "A", "B"))
    
    // Get ordered items from ViewModel
    val orderedItems = viewModel.state.value.currentGroupOrderedMixedItems
    
    // Pass to picker screen
    val pickerItems = buildPickerDisplayItems(
        groupOrderedItems = mapOf(group.id to orderedItems)
    )
    
    // Verify order matches
    assertEquals(listOf("C", "A", "B"), pickerItems.map { it.name })
}
```

---

## 🚨 Red Flags - When to Review

If you see these patterns, check if group sort order is properly handled:

1. **Filtering folders by memberBucketIds:**
   ```kotlin
   // ⚠️ RED FLAG
   val memberFolders = bucketIds.mapNotNull { id -> 
       folders.find { it.bucketId == id } 
   }
   ```

2. **Alphabetical sorting of group contents:**
   ```kotlin
   // ⚠️ RED FLAG
   val sortedFolders = folders.sortedBy { it.name.lowercase() }
   ```

3. **Building display items without checking pre-calculated data:**
   ```kotlin
   // ⚠️ RED FLAG - where is groupOrderedItems?
   val displayItems = remember(allFolders, currentGroupId) {
       // ... building items from raw data ...
   }
   ```

---

## 📚 Related Documentation

- **Sort Order Integrity Rule** in `.github/copilot-instructions.md`
- **Behavioral Consistency Rule** in `.github/copilot-instructions.md`
- **Common-First Rule** in `.github/copilot-instructions.md`

---

## 💡 Key Takeaway

**Never re-calculate what the ViewModel has already calculated correctly.**

Groups maintain their own sort preferences and ordered item lists. Trust the ViewModel's pre-calculated data instead of trying to rebuild it from raw data sources.

