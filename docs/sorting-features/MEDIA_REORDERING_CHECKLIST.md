# Media Drag-to-Reorder Implementation Checklist

**Branch:** `feature/media-drag-reorder`  
**Status:** Phase 1 Complete ✅  
**Started:** June 26, 2026

---

## Phase 1: Storage Layer ✅ COMPLETE

- [x] Add `allowMediaReordering: Boolean` to `SharedAppPreferences`
- [x] Add `customRootMediaOrder: List<Long>` to `SharedAppPreferences`
- [x] Add `getFolderMediaCustomOrder(bucketId): List<Long>` to image-library `AppPreferences`
- [x] Add `saveFolderMediaCustomOrder(bucketId, order)` to image-library `AppPreferences`
- [x] Add `getAllFolderMediaCustomOrders(): Map<Int, List<Long>>` for backup
- [x] Add `restoreAllFolderMediaCustomOrders(orders)` for restore
- [x] Mirror all methods in video-library `AppPreferences`
- [x] Storage format: "bucketId:id1;id2;id3,..." with max 50 albums

**Commit:** Phase 1 storage layer complete

---

## Phase 2: Repository Layer ⏳ TODO

- [ ] Add `allowMediaReordering` parameter to `ImageRepository.getImages()`
- [ ] Implement `applyCustomMediaOrder()` in `ImageRepository`
- [ ] Handle new items (prepend at position 0)
- [ ] Mirror implementation in `VideoRepository.getVideos()`
- [ ] Test: Custom order applied correctly after MediaStore load
- [ ] Test: New items appear at top when custom order exists

---

## Phase 3: ViewModel Layer ⏳ TODO

- [ ] Add `allowMediaReordering: Boolean` to `ImageListUiState`
- [ ] Add `updateAllowMediaReordering(value: Boolean)` method
- [ ] Add `reorderFolderMedia(fromIndex, toIndex)` method
- [ ] Add `onFolderMediaReorderDone()` method (persist order)
- [ ] Add `reorderRootMedia(fromIndex, toIndex)` method
- [ ] Add `onRootMediaReorderDone()` method
- [ ] Mirror all changes in `VideoListViewModel`
- [ ] Test: Reordering updates UI state correctly
- [ ] Test: Order persists after reorder done

---

## Phase 4: Backup Integration ⏳ TODO

- [ ] Add `allowMediaReordering` to `BackupManager.SharedSettings`
- [ ] Add `customRootMediaOrder` to shared settings
- [ ] Add per-album custom orders to library-specific backup
- [ ] Implement write logic in `writeSharedSettings()`
- [ ] Implement read logic in `readSharedSettings()`
- [ ] Test: Backup exports custom media orders
- [ ] Test: Restore imports custom media orders correctly

---

## Phase 5: Settings UI ⏳ TODO

- [ ] Add toggle parameter to `SharedSettingsScreen`
- [ ] Create info dialog composable for feature explanation
- [ ] Add "Drag to reorder media" row in Interface section
- [ ] Pass through image-library `SettingsScreen`
- [ ] Pass through video-library `SettingsScreen`
- [ ] Test: Toggle updates preference
- [ ] Test: Info dialog displays correctly

---

## Phase 6: Drag-and-Drop UI (Folders) ⏳ TODO

- [ ] Add `allowMediaReordering: Boolean` param to `SharedFolderDetailScreen`
- [ ] Add `onReorderItem: (Int, Int) -> Unit` callback
- [ ] Add `onReorderDone: () -> Unit` callback
- [ ] Create `DragDropGridState` with conditional enable logic
- [ ] Apply `.dragToReorderGrid()` modifier conditionally
- [ ] Disable scroll during drag (`userScrollEnabled`)
- [ ] Wire up in image-library `FolderDetailScreen`
- [ ] Wire up in video-library `FolderDetailScreen`
- [ ] Test: Long-press activates drag in Custom sort
- [ ] Test: Drag only works when toggle enabled
- [ ] Test: Drag disabled in selection mode

---

## Phase 7: Drag-and-Drop UI (Root Views) ⏳ TODO

- [ ] Add drag support to Images tab (image-library)
- [ ] Add drag support to Videos tab (video-library)
- [ ] Same conditional logic as folders
- [ ] Wire up reorder callbacks
- [ ] Test: Root view reordering works
- [ ] Test: Order persists after app restart

---

## Phase 8: Testing & Polish ⏳ TODO

### Image-Library Testing
- [ ] Test: Reorder images in album
- [ ] Test: Reorder images in root view
- [ ] Test: New images appear at top
- [ ] Test: Reorder persists after app restart
- [ ] Test: Reorder in Custom sort only
- [ ] Test: Backup/restore preserves order
- [ ] Test: Disable toggle restores date sort
- [ ] Test: Multiple albums have independent orders

### Video-Library Testing
- [ ] Test: Reorder videos in folder
- [ ] Test: Reorder videos in root view
- [ ] Test: New videos appear at top
- [ ] Test: Reorder persists after app restart
- [ ] Test: Reorder in Custom sort only
- [ ] Test: Backup/restore preserves order
- [ ] Test: Disable toggle restores date sort
- [ ] Test: Multiple folders have independent orders

### Consistency Testing
- [ ] Test: Both apps behave identically
- [ ] Test: Settings UI consistent
- [ ] Test: Drag UX consistent
- [ ] Test: Performance acceptable (no lag)

---

## Phase 9: Documentation ⏳ TODO

- [ ] Update user-facing help/about screen
- [ ] Create implementation summary doc
- [ ] Document any edge cases discovered
- [ ] Update CHANGELOG.md

---

## Phase 10: Code Review & Merge ⏳ TODO

- [ ] Run `./gradlew :image-library:build -warnaserror`
- [ ] Run `./gradlew :video-library:build -warnaserror`
- [ ] Run `./scripts/verify-consistency.ps1`
- [ ] Install both apps: `./gradlew :image-library:installDebug :video-library:installDebug`
- [ ] Final manual testing on device
- [ ] Create PR with `/pr-description` skill
- [ ] Address code review feedback
- [ ] Merge to main

---

## Progress Summary

**Completed:** 1/10 phases (10%)  
**Estimated Remaining:** 7-10 hours  
**Current Phase:** Phase 1 Complete ✅  
**Next Phase:** Phase 2 - Repository Layer

---

## Notes

- Storage layer uses semicolon-separated IDs within buckets to support large albums
- Max 50 albums stored to prevent SharedPreferences bloat
- New items always prepend at position 0 (consistent with folder/group reordering)
- Feature disabled by default (requires settings toggle)
- Only works in Custom sort mode

