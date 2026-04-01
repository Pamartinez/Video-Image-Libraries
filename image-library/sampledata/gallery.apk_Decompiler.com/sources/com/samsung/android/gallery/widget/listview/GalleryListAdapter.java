package com.samsung.android.gallery.widget.listview;

import A.a;
import A4.I;
import D7.g;
import Fa.F;
import Fb.b;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.gallery.widget.listview.DragSelectTouchListener;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchAdapter;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryListAdapter<VH extends ListViewHolder> extends GalleryRecyclerAdapter<VH> implements IPinchAdapter {
    private AdvancedMouseFocusManager mAdvancedMouseFocusManager;
    /* access modifiers changed from: private */
    public ListAutoDragHandler mAutoDragHandler;
    /* access modifiers changed from: protected */
    public Blackboard mBlackBoard;
    protected final Clipboard mClipBoard;
    private final ListViewHolder.OnItemBindListener mClipboardSynchronizer = new b(0, this);
    /* access modifiers changed from: private */
    public final DragSelectTouchListener mDragSelectTouchListener;
    Runnable mDragUpdater;
    protected boolean mHideDecoIcons;
    private boolean mIsGroupCheckBoxClicked;
    /* access modifiers changed from: private */
    public boolean mOnDragSelecting;
    private final DragSelectTouchListener.OnDragSelectListener mSelectListener;
    protected SelectableChecker<MediaItem> mSelectableChecker;
    protected final HashMap<Long, MediaItem> mSelectionBuffer = new HashMap<>();
    private boolean mSelectionByLongPress;
    protected SelectionManager mSelectionManager;
    private final boolean mUseClipboardView;
    private final ListViewHolder.OnItemTouchListener onDragListenerOnAdvancedMouseEvent;
    private final ListViewHolder.OnItemTouchListener onHorizontalDragListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SelectableType {
        SUPPORT,
        UNSUPPORTED,
        REACHED_MAX_COUNT
    }

    public GalleryListAdapter(Blackboard blackboard) {
        AnonymousClass1 r0 = new DragSelectTouchListener.OnAdvancedDragSelectListener() {
            private boolean bReachedMaxCount;
            boolean mIsSelected;

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSelectionFinished$0() {
                GalleryListAdapter galleryListAdapter = GalleryListAdapter.this;
                if (galleryListAdapter.mBlackBoard != null && !galleryListAdapter.mOnDragSelecting) {
                    GalleryListAdapter.this.setDragSelection(false);
                }
            }

            public void onSelectChange(int i2, int i7, boolean z, boolean z3) {
                int i8;
                int i10;
                String str = GalleryListAdapter.this.TAG;
                StringBuilder h5 = a.h(i2, i7, "onSelectChange ", "~", "/");
                h5.append(z);
                h5.append(" ");
                h5.append(z3);
                Log.d(str, h5.toString());
                if (z3) {
                    i8 = i7;
                } else {
                    i8 = i2;
                }
                int i11 = i2;
                while (true) {
                    if (i11 > i7) {
                        break;
                    }
                    if (z3) {
                        i10 = i11;
                    } else {
                        i10 = (i2 + i7) - i11;
                    }
                    if (GalleryListAdapter.this.isDragSelectable(i10)) {
                        boolean z7 = false;
                        if (this.bReachedMaxCount && GalleryListAdapter.this.mSelectionManager.isSelected(Integer.valueOf(i10)) && z != this.mIsSelected) {
                            this.bReachedMaxCount = false;
                        }
                        GalleryListAdapter galleryListAdapter = GalleryListAdapter.this;
                        if (z == this.mIsSelected) {
                            z7 = true;
                        }
                        SelectableType selectItem = galleryListAdapter.selectItem(i10, z7, true);
                        if (selectItem == SelectableType.SUPPORT) {
                            if (GalleryListAdapter.this.mAutoDragHandler != null && i10 == i8) {
                                GalleryListAdapter.this.mAutoDragHandler.startTimer(i8, z);
                            }
                            if (!GalleryListAdapter.this.updateDividerCheckBox(i10) && i10 == i8) {
                                GalleryListAdapter.this.lambda$updateCheckboxOnBindMediaItem$0(i10);
                            }
                        } else if (selectItem == SelectableType.REACHED_MAX_COUNT) {
                            if (!this.bReachedMaxCount) {
                                this.bReachedMaxCount = true;
                            }
                            GalleryListAdapter.this.showToastForSelectionError(selectItem);
                        }
                    }
                    i11++;
                }
                GalleryListAdapter.this.notifySelectedItemChanged(i2, i7);
            }

            public void onSelectionFinished(int i2) {
                a.k(i2, "onSelectionFinished ", GalleryListAdapter.this.TAG);
                GalleryListAdapter.this.mOnDragSelecting = false;
                this.bReachedMaxCount = false;
                if (GalleryListAdapter.this.mAutoDragHandler != null) {
                    GalleryListAdapter.this.mAutoDragHandler.finish();
                }
                ThreadUtil.postOnBgThreadDelayed(new a(this), 200);
            }

            public void onSelectionStarted(int i2) {
                boolean z;
                a.k(i2, "onSelectionStarted ", GalleryListAdapter.this.TAG);
                GalleryListAdapter.this.mOnDragSelecting = true;
                GalleryListAdapter.this.setDragSelection(true);
                if (GalleryListAdapter.this.mDragSelectTouchListener.isLongPressedAfterSelectionMode() || !GalleryListAdapter.this.mSelectionManager.isSelected(Integer.valueOf(i2))) {
                    z = true;
                } else {
                    z = false;
                }
                this.mIsSelected = z;
                if (GalleryListAdapter.this.isDragSelectable(i2)) {
                    GalleryListAdapter.this.selectItemWithSelectableCheck(i2, this.mIsSelected);
                    if (GalleryListAdapter.this.mAutoDragHandler != null) {
                        GalleryListAdapter.this.mAutoDragHandler.startTimer(i2, true);
                    }
                }
            }
        };
        this.mSelectListener = r0;
        this.onHorizontalDragListener = new b(1, this);
        this.onDragListenerOnAdvancedMouseEvent = new b(2, this);
        this.mBlackBoard = blackboard;
        this.mDragSelectTouchListener = new DragSelectTouchListener().withSelectListener(r0);
        this.mUseClipboardView = PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard);
        this.mClipBoard = Clipboard.getInstance(blackboard);
    }

    private void deleteRemovedClipboardItem(long j2) {
        if (this.mUseClipboardView && this.mClipBoard.containsRemovedClipboardItemId(Long.valueOf(j2))) {
            this.mClipBoard.deleteRemovedClipboardItemId(Long.valueOf(j2));
        }
    }

    private boolean isAllClusterDataChecked(int i2) {
        int itemCount = getItemCount();
        for (int i7 = i2 + 1; i7 < itemCount; i7++) {
            if (!this.mSelectionManager.isSelectedData(Integer.valueOf(i7))) {
                return isDivider(i7);
            }
        }
        return true;
    }

    private boolean isCheckboxClicked(int i2, int i7) {
        if (i7 == 1 || (i7 != 2 && isSelectionMode() && !isSingleSelectionMode() && isData(i2))) {
            return true;
        }
        return false;
    }

    private boolean isSelectSpaceRemained() {
        int selectableMaxCount = getSelectableMaxCount();
        if (selectableMaxCount == -1 || getSelectedItemCount() < selectableMaxCount) {
            return true;
        }
        return false;
    }

    private boolean isValidPosition(int i2) {
        if (i2 != -1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Log.d(this.TAG, a.d(i2, i7, "onItemTouch(onHrzDrag) {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        if (!supportDragSelection() || !isValidPosition(i2) || !isCheckBoxEnabled(listViewHolder) || this.mOnDragSelecting) {
            return false;
        }
        startDragSelection(i2);
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$2(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Log.d(this.TAG, a.d(i2, i7, "onItemTouch(onAdvMouse) {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        if (!isValidPosition(i2) || !isEnabledDragOnAdvancedMouseEvent()) {
            return false;
        }
        startDragAndDropOnAdvancedMouseAction(i2, listViewHolder);
        return false;
    }

    private void notifySelectionModeChange() {
        notifyItemRangeChanged(0, getItemCount(), "checkBox");
    }

    private void onGroupCheckBoxClickFinished() {
        Runnable runnable = this.mDragUpdater;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void setCheckboxAnimation(VH vh) {
        Optional.ofNullable(vh.getCheckbox()).ifPresent(new F(8));
    }

    private void setIsGroupCheckBoxClicked(boolean z) {
        this.mIsGroupCheckBoxClicked = z;
        if (!z) {
            onGroupCheckBoxClickFinished();
        }
    }

    private void syncSelectionWithClipboard(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null && isData(i2)) {
            boolean contains = this.mClipBoard.contains(Long.valueOf(getDataId(mediaItem)));
            if (isNeedToSyncClusterDivider(listViewHolder, mediaItem, contains)) {
                this.mSelectionManager.select(Integer.valueOf(i2), contains, false);
                deleteRemovedClipboardItem(getDataId(mediaItem));
                listViewHolder.getRootView().post(new Fb.a(i2, 0, this));
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCheckboxOnBindMediaItem(ListViewHolder listViewHolder, MediaItem mediaItem) {
        if (mediaItem != null) {
            int viewPosition = listViewHolder.getViewPosition();
            boolean contains = this.mClipBoard.contains(Long.valueOf(getDataId(mediaItem)));
            if (isNeedToSyncClusterDivider(listViewHolder, mediaItem, contains)) {
                listViewHolder.setChecked(contains);
                this.mSelectionManager.select(Integer.valueOf(viewPosition), contains, false);
                deleteRemovedClipboardItem(getDataId(mediaItem));
                listViewHolder.getRootView().post(new Fb.a(viewPosition, 1, this));
            }
        }
    }

    private void updateDecoViews(VH vh, int i2) {
        if (isSelectionMode()) {
            enableCheckbox(vh, !isSingleSelectionMode(), false);
            if (isMultipleSelectionMode()) {
                vh.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
                vh.setOnBindMediaItem(this.mClipboardSynchronizer);
                return;
            }
            return;
        }
        vh.setSupportDecoItemType(getDecoItemViewType());
    }

    /* access modifiers changed from: private */
    public boolean updateDividerCheckBox(int i2) {
        if (i2 != getItemCount() - 1 && !isDivider(i2 + 1) && (i2 <= 0 || !isDivider(i2 - 1))) {
            return false;
        }
        lambda$updateCheckboxOnBindMediaItem$0(i2);
        return true;
    }

    public boolean checkConsumeTouchEventOnAdvancedMouseAction(MotionEvent motionEvent) {
        AdvancedMouseFocusManager advancedMouseFocusManager = this.mAdvancedMouseFocusManager;
        if (advancedMouseFocusManager == null || !advancedMouseFocusManager.checkConsumeTouchEventOnAdvancedMouseAction(motionEvent, new A5.a(9, this), new g(18, this))) {
            return false;
        }
        return true;
    }

    public int dataOnlyListCount(ArrayList<Integer> arrayList) {
        return (int) arrayList.stream().filter(new I(5, this)).count();
    }

    public void destroy() {
        this.mBlackBoard = null;
        this.mSelectionManager = null;
        this.mDragSelectTouchListener.release();
        this.mSelectableChecker = null;
        this.mAdvancedMouseFocusManager = null;
        this.mAutoDragHandler = null;
    }

    public void enableCheckbox(VH vh, boolean z, boolean z3) {
        vh.setCheckboxEnabled(getDecoItemViewType(), z);
        if (z3) {
            resetCheckBoxState(z, vh);
        }
    }

    public final void exitSelect(boolean z) {
        setSelectionModeByLongPress(false);
        setSelectableChecker((SelectableChecker<MediaItem>) null);
        this.mSelectionManager.stopSelect();
        this.mDragSelectTouchListener.exitDragSelect();
        notifySelectionModeChange();
        if (PickerUtil.isNormalLaunchMode(this.mBlackBoard)) {
            this.mClipBoard.clear();
            if (useClipBoardForNormalSelectionMode()) {
                this.mSelectionBuffer.clear();
            }
        }
        onExitSelectMode(z);
    }

    public AdvancedMouseFocusManager getAdvancedMouseFocusManager() {
        return this.mAdvancedMouseFocusManager;
    }

    public ArrayList<Integer> getCluster(int i2) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i7 = i2 + 1;
        while (isData(i7)) {
            arrayList.add(Integer.valueOf(i7));
            i7++;
            if (i7 >= getItemCount()) {
                break;
            }
        }
        return arrayList;
    }

    public Context getContext() {
        return BlackboardUtils.readActivity(this.mBlackBoard);
    }

    public long getDataId(MediaItem mediaItem) {
        return mediaItem.getFileId();
    }

    public List<Long> getDataIds() {
        return null;
    }

    public int getDecoItemViewType() {
        if (this.mHideDecoIcons) {
            return 0;
        }
        if (!isSelectionMode()) {
            return 76;
        }
        if (isSingleSelectionMode()) {
            return 94;
        }
        return 95;
    }

    public int getDividerIndex(int i2) {
        int i7 = i2 - 1;
        while (i7 >= 0 && !isDivider(i7)) {
            i7--;
        }
        if (isDivider(i7)) {
            return i7;
        }
        return -1;
    }

    public DragSelectTouchListener getDragSelectTouchListener() {
        return this.mDragSelectTouchListener;
    }

    public MediaItem getMediaItemSync(int i2) {
        return null;
    }

    public SelectableChecker<MediaItem> getSelectableChecker() {
        return this.mSelectableChecker;
    }

    public List<GalleryListView> getSelectableHeaderRecyclerListView() {
        return null;
    }

    public int getSelectableMaxCount() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            return selectionManager.getMaxCount();
        }
        return -1;
    }

    public int getSelectedCountOnCurrentView() {
        ArrayList<Integer> arrayList;
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null) {
            arrayList = null;
        } else {
            arrayList = selectionManager.getSelectedList();
        }
        if (arrayList == null) {
            return 0;
        }
        return getSelectedDataCount(arrayList);
    }

    public int getSelectedDataCount(ArrayList<Integer> arrayList) {
        return arrayList.size();
    }

    public int getSelectedItemCount() {
        if (useClipBoardForNormalSelectionMode()) {
            return this.mClipBoard.getTotalCount();
        }
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager != null) {
            return selectionManager.getSelectedItemCount();
        }
        Log.e(this.TAG, "getSelectedItemCount failed");
        return 0;
    }

    public int getSelectedItemCountForView() {
        if (useClipBoardForNormalSelectionMode()) {
            return this.mClipBoard.getTotalCount();
        }
        return getSelectedCountOnCurrentView();
    }

    public MediaItem[] getSelectedItemsFromBuffer() {
        int i2 = 0;
        if (!useClipBoardForNormalSelectionMode()) {
            return new MediaItem[0];
        }
        LinkedHashSet<Long> cloneSet = this.mClipBoard.cloneSet();
        int size = cloneSet.size();
        if (size == 0) {
            return new MediaItem[0];
        }
        MediaItem[] mediaItemArr = new MediaItem[size];
        Iterator<Long> it = cloneSet.iterator();
        while (it.hasNext()) {
            Long next = it.next();
            MediaItem mediaItem = this.mSelectionBuffer.get(next);
            if (mediaItem != null) {
                mediaItemArr[i2] = mediaItem;
                i2++;
            } else {
                String str = this.TAG;
                Log.e(str, "fail to get item : " + next);
            }
        }
        return mediaItemArr;
    }

    public boolean getSelectionModeByLongPress() {
        return this.mSelectionByLongPress;
    }

    public int getStartDepth() {
        return 0;
    }

    public int getViewHolderMargin(int i2) {
        return 0;
    }

    public boolean handleDataUpdateAnimation() {
        return false;
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!isCheckboxClicked(i2, i7)) {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        } else if (onCheckBoxClicked(listViewHolder, i2)) {
            int viewType = listViewHolder.getViewType();
            if (ViewHolderValue.isDivider(viewType)) {
                setIsGroupCheckBoxClicked(true);
                if (!syncClusterData(i2)) {
                    listViewHolder.setChecked(false);
                }
                setIsGroupCheckBoxClicked(false);
            } else if (viewType == 0 && i7 != 2) {
                lambda$updateCheckboxOnBindMediaItem$0(i2);
            }
        }
    }

    public boolean isCheckBoxEnabled(ListViewHolder listViewHolder) {
        return listViewHolder.isCheckBoxEnabled();
    }

    public boolean isCheckingTargetCluster() {
        return false;
    }

    public boolean isDragSelectActive() {
        return this.mDragSelectTouchListener.isActive();
    }

    public boolean isDragSelectSupported() {
        return true;
    }

    public boolean isDragSelectable(int i2) {
        return isData(i2);
    }

    public boolean isEnabledDragOnAdvancedMouseEvent() {
        return DeviceInfo.isAdvancedMouseCompat(getContext());
    }

    public boolean isGroupCheckBoxClicked() {
        return this.mIsGroupCheckBoxClicked;
    }

    public boolean isInvisibleViewHolder(ListViewHolder listViewHolder) {
        return false;
    }

    public SelectableType isItemSelectable(int i2) {
        return isItemSelectable(i2, getMediaItemSync(i2));
    }

    public boolean isMonthForViewing() {
        return false;
    }

    public boolean isMultiPick() {
        return PickerUtil.isMultiplePickLaunchMode(this.mBlackBoard);
    }

    public boolean isMultipleSelectionMode() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null || !selectionManager.isSelectMode() || this.mSelectionManager.isSingleSelectMode()) {
            return false;
        }
        return true;
    }

    public boolean isNeedToSyncClusterDivider(ListViewHolder listViewHolder, MediaItem mediaItem, boolean z) {
        int viewPosition = listViewHolder.getViewPosition();
        if (z && !this.mSelectionManager.isSelected(Integer.valueOf(viewPosition))) {
            return true;
        }
        if (!this.mUseClipboardView || z || !this.mSelectionManager.isSelected(Integer.valueOf(viewPosition))) {
            return false;
        }
        if (this.mClipBoard.containsRemovedClipboardItemId(Long.valueOf(getDataId(mediaItem))) || (isData(viewPosition) && listViewHolder.getCheckbox() != null && listViewHolder.getCheckbox().isChecked())) {
            return true;
        }
        return false;
    }

    public boolean isNeedToUpdateDecoView(int i2) {
        return !isFooter(i2);
    }

    public boolean isOnCtrlKeyCombination() {
        if (this.mBlackBoard == null) {
            return false;
        }
        if (isOnVirtualCtrlKeyCombination()) {
            return true;
        }
        Integer num = (Integer) this.mBlackBoard.read("data://key_combination_ctrl");
        if (num == null || (num.intValue() != 113 && num.intValue() != 114)) {
            return false;
        }
        return true;
    }

    public boolean isOnKeyCombination() {
        if (isOnCtrlKeyCombination() || isOnShiftKeyCombination()) {
            return true;
        }
        return false;
    }

    public boolean isOnShiftKeyCombination() {
        Integer num;
        Blackboard blackboard = this.mBlackBoard;
        if (blackboard == null || (num = (Integer) blackboard.read("data://key_combination_shift")) == null || (num.intValue() != 59 && num.intValue() != 60)) {
            return false;
        }
        return true;
    }

    public boolean isOnVirtualCtrlKeyCombination() {
        Blackboard blackboard = this.mBlackBoard;
        if (blackboard == null) {
            return false;
        }
        return ((Boolean) blackboard.read("data://virtual_ctrl_pressed_on_dex_live", Boolean.FALSE)).booleanValue();
    }

    public boolean isPinchSupported() {
        return !isDragSelectActive();
    }

    public boolean isRealRatio() {
        return false;
    }

    public boolean isSelectionMode() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null || !selectionManager.isSelectMode()) {
            return false;
        }
        return true;
    }

    public boolean isSingleSelectionMode() {
        SelectionManager selectionManager = this.mSelectionManager;
        if (selectionManager == null || !selectionManager.isSingleSelectMode()) {
            return false;
        }
        return true;
    }

    public boolean isYear() {
        return false;
    }

    public void notifyAdvancedMouseFocusedItemChanged() {
        notifyItemRangeChanged(0, getItemCount(), "advancedMouseFocusItem");
    }

    public final boolean notifyDataChanged(Runnable runnable) {
        removePendingJobs();
        onDataChanged();
        if (isSelectionMode()) {
            restoreSelectionOnDataChanged(runnable, true);
        }
        if (!handleDataUpdateAnimation()) {
            notifyDataSetChanged();
        }
        return true;
    }

    public final boolean notifyDataRangeInserted(int i2, int i7) {
        onDataRangeInserted(i2, i7);
        int viewPosition = getViewPosition(i2);
        notifyItemRangeInserted(viewPosition, getViewPosition(i2 + i7) - viewPosition);
        return true;
    }

    public void notifyItemRangeChanged() {
        notifyItemRangeChanged(0, getItemCount());
    }

    public void notifySelectedItemChanged(int i2, int i7) {
        notifyItemRangeChanged(i2, (i7 - i2) + 1, "checkBoxItem");
    }

    public boolean onCheckBoxClicked(ListViewHolder listViewHolder, int i2) {
        if (isCheckBoxEnabled(listViewHolder)) {
            boolean z = !this.mSelectionManager.isSelected(Integer.valueOf(i2));
            this.mSelectionManager.select(Integer.valueOf(i2), z, true);
            if (isData(i2)) {
                onSelected(i2, z, true);
            } else {
                onDividerSelected();
            }
            listViewHolder.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
        }
        return true;
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        try {
            onPrepareLongPressSelectMode();
            if (startSelect(0)) {
                setSelectionModeByLongPress(true);
                this.mClipBoard.clear();
                startDragSelection(i2);
                return true;
            } else if (this.mSelectionManager.isSelectMode()) {
                this.mDragSelectTouchListener.setLongPressedAfterSelectionMode(true);
                if (!this.mSelectionManager.isSelected(Integer.valueOf(i2))) {
                    selectItemWithSelectableCheck(i2, true);
                    onPostLongPressSelectMode();
                    return true;
                }
                onPostLongPressSelectMode();
                return false;
            } else {
                onPostLongPressSelectMode();
                return false;
            }
        } finally {
            onPostLongPressSelectMode();
        }
    }

    public void onSelected(int i2, boolean z, boolean z3) {
        char c5;
        if (this.mBlackBoard == null) {
            Log.e(this.TAG, "onSelected failed. null bb");
        } else if (isData(i2)) {
            MediaItem mediaItemSync = getMediaItemSync(i2);
            if (mediaItemSync != null) {
                long dataId = getDataId(mediaItemSync);
                if (z) {
                    this.mClipBoard.add(Long.valueOf(dataId));
                    deleteRemovedClipboardItem(dataId);
                    if (useClipBoardForNormalSelectionMode()) {
                        this.mSelectionBuffer.put(Long.valueOf(dataId), mediaItemSync);
                    }
                } else {
                    this.mClipBoard.remove(Long.valueOf(dataId));
                    if (useClipBoardForNormalSelectionMode()) {
                        this.mSelectionBuffer.remove(Long.valueOf(dataId));
                    }
                }
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("onSelected {");
                if (z) {
                    c5 = 's';
                } else {
                    c5 = 'u';
                }
                sb2.append(c5);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(i2);
                sb2.append("} #");
                sb2.append(this.mClipBoard.getTotalCount());
                Log.d(str, sb2.toString());
            } else {
                a.B(i2, "onSelected failed. null item ", this.TAG);
            }
            if (z3) {
                sendItemSelectedSyncEvent(z, i2);
            }
        }
    }

    public void removeRemainedDataFromClipboard(LinkedHashSet<Long> linkedHashSet) {
        Iterator<Long> it = linkedHashSet.iterator();
        while (it.hasNext()) {
            this.mClipBoard.remove(it.next());
        }
    }

    public void replaceSelection(int i2, List<Integer> list) {
        this.mSelectionManager.replaceSelection(i2, list);
    }

    public void resetCheckBoxState(boolean z, ListViewHolder listViewHolder) {
        if (z && listViewHolder.getCheckbox() != null) {
            listViewHolder.setChecked(false);
            listViewHolder.getCheckbox().jumpDrawablesToCurrentState();
        }
    }

    public void restoreClipboard(Runnable runnable, TimeTickLog timeTickLog, LinkedHashSet<Integer> linkedHashSet, LinkedHashSet<Long> linkedHashSet2) {
        ArrayList<Long> arrayList;
        if (getDataIds() != null) {
            arrayList = new ArrayList<>(getDataIds());
        } else {
            arrayList = new ArrayList<>();
        }
        timeTickLog.tick();
        int i2 = 0;
        for (Long l : arrayList) {
            if (this.mClipBoard.contains(l)) {
                linkedHashSet.add(Integer.valueOf(getViewPosition(i2)));
                linkedHashSet2.remove(l);
            }
            i2++;
        }
        timeTickLog.tick();
        removeRemainedDataFromClipboard(linkedHashSet2);
        this.mSelectionManager.replaceSelection(getItemCount(), new ArrayList(linkedHashSet));
        timeTickLog.tick();
        this.mSelectionManager.syncAllDivider(supportHeader());
        timeTickLog.tick();
        String str = this.TAG;
        Log.d(str, "restoreClipboard" + Logger.vt(timeTickLog));
        if (runnable != null) {
            runnable.run();
        }
    }

    public void restoreSelectionOnDataChanged(Runnable runnable, boolean z) {
        TimeTickLog timeTickLog = new TimeTickLog("restoreSelect");
        this.mSelectionManager.clearAll(z);
        restoreClipboard(runnable, timeTickLog, new LinkedHashSet(), new LinkedHashSet(this.mClipBoard.cloneSet()));
    }

    public boolean selectAll() {
        if (this.mSelectionManager == null) {
            return false;
        }
        TimeTickLog timeTickLog = new TimeTickLog("select all");
        Log.d(this.TAG, "select all  start");
        this.mSelectionManager.selectAll(supportHeader(), hasFooter());
        String str = this.TAG;
        Log.d(str, "select all  = " + timeTickLog.getElapsedTime());
        if (!useClipBoardForNormalSelectionMode()) {
            this.mClipBoard.clear();
        }
        String str2 = this.TAG;
        Log.d(str2, "clear  = " + timeTickLog.getElapsedTime());
        return selectAllClipboard(timeTickLog);
    }

    public boolean selectAllClipboard(TimeTickLog timeTickLog) {
        MediaItem mediaItemSync;
        if (useClipBoardForNormalSelectionMode()) {
            int itemCount = getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (isData(i2) && (mediaItemSync = getMediaItemSync(i2)) != null) {
                    this.mClipBoard.add(Long.valueOf(mediaItemSync.getFileId()));
                    this.mSelectionBuffer.put(Long.valueOf(mediaItemSync.getFileId()), mediaItemSync);
                }
            }
        } else {
            this.mClipBoard.addAll(getDataIds());
        }
        String str = this.TAG;
        Log.d(str, "selectAllClipboard +" + timeTickLog.getElapsedTime());
        notifySelectedItemChanged();
        return true;
    }

    public void selectClusterDivider(int i2) {
        if (this.mSelectionManager != null) {
            this.mSelectionManager.select(Integer.valueOf(i2), isAllClusterDataChecked(i2), false);
        }
    }

    public SelectableType selectItem(int i2, boolean z, boolean z3) {
        SelectableType isItemSelectable = isItemSelectable(i2);
        if (isItemSelectable == SelectableType.SUPPORT) {
            this.mSelectionManager.select(Integer.valueOf(i2), z, z3);
            if (isData(i2)) {
                onSelected(i2, z, z3);
            }
        }
        return isItemSelectable;
    }

    public void selectItemWithSelectableCheck(int i2, boolean z) {
        SelectableType selectItem = selectItem(i2, z, true);
        if (selectItem == SelectableType.SUPPORT) {
            lambda$updateCheckboxOnBindMediaItem$0(i2);
            notifyItemChanged(i2, "checkBoxItem");
            return;
        }
        showToastForSelectionError(selectItem);
    }

    public void selectItemWithSync(int i2, boolean z, boolean z3) {
        selectItem(i2, z, z3);
        notifySelectedItemChanged(i2, i2);
        lambda$updateCheckboxOnBindMediaItem$0(i2);
    }

    public void setAdvancedMouseFocusManager() {
        if (DeviceInfo.isAdvancedMouseCompat(getContext()) && this.mAdvancedMouseFocusManager == null) {
            this.mAdvancedMouseFocusManager = new AdvancedMouseFocusManager();
        }
    }

    public void setDragSelection(boolean z) {
        this.mBlackBoard.publish("data://dragging_selection", Boolean.valueOf(z));
    }

    public void setDragUpdater(Runnable runnable) {
        this.mDragUpdater = runnable;
        this.mDragSelectTouchListener.setDragUpdater(runnable);
    }

    public void setGridSize(int i2, int i7) {
        boolean z;
        if (i2 >= i7) {
            z = true;
        } else {
            z = false;
        }
        this.mHideDecoIcons = z;
    }

    public void setLastSelectedViewPositionOnMotionEventDown(int i2) {
        AdvancedMouseFocusManager advancedMouseFocusManager = this.mAdvancedMouseFocusManager;
        if (advancedMouseFocusManager != null) {
            advancedMouseFocusManager.setLastSelectedViewPositionOnMotionEventDown(i2);
        }
    }

    public void setListeners(VH vh) {
        super.setListeners(vh);
        if (isMultipleSelectionMode()) {
            vh.setOnHorizontalDragListener(this.onHorizontalDragListener);
        }
        if (this.mAdvancedMouseFocusManager != null) {
            vh.setOnAdvancedMouseDragListener(this.onDragListenerOnAdvancedMouseEvent);
        }
    }

    public void setOnAutoDragHandler(ListAutoDragHandler listAutoDragHandler) {
        this.mAutoDragHandler = listAutoDragHandler;
    }

    public void setSelectableChecker(SelectableChecker<MediaItem> selectableChecker) {
        this.mSelectableChecker = selectableChecker;
    }

    public void setSelectionManager(SelectionManager selectionManager) {
        this.mSelectionManager = selectionManager;
    }

    public void setSelectionModeByLongPress(boolean z) {
        String str = this.TAG;
        Log.v(str, "setSelectionModeByLongPress v=" + z);
        this.mSelectionByLongPress = z;
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
    }

    public void showToastForSelectionError(SelectableType selectableType) {
        if (selectableType == SelectableType.REACHED_MAX_COUNT) {
            SelectableChecker<MediaItem> selectableChecker = this.mSelectableChecker;
            if (selectableChecker != null) {
                selectableChecker.showExceedMaxCountToast(getContext());
            } else if (this.mSelectionManager != null) {
                Utils.showToast(getContext(), getContext().getString(R$string.max_size_reached, new Object[]{Integer.valueOf(this.mSelectionManager.getMaxCount())}));
            }
        }
    }

    public void startDragAndDropOnAdvancedMouseAction(int i2, ListViewHolder listViewHolder) {
        this.mBlackBoard.postEvent(EventMessage.obtain(1068, Integer.valueOf(i2)));
    }

    public void startDragSelection(int i2) {
        this.mDragSelectTouchListener.startDragSelection(i2);
    }

    public boolean startSelect(int i2) {
        if (this.mSelectionManager.isSelectMode() || !this.mSelectionManager.startSelect(getItemCount(), i2)) {
            return false;
        }
        if (i2 != 1) {
            notifySelectionModeChange();
        }
        onStartSelectMode();
        return true;
    }

    public boolean supportDragSelection() {
        return isMultipleSelectionMode();
    }

    public final boolean supportPinch(boolean z) {
        if (!isDragSelectActive()) {
            return supportPinchInternal(z);
        }
        Log.e(this.TAG, "fail pinch in drag select");
        return false;
    }

    public boolean supportPinchInternal(boolean z) {
        return true;
    }

    public boolean supportSpannable() {
        return false;
    }

    public boolean syncClusterData(int i2) {
        int i7 = i2 + 1;
        boolean isSelected = this.mSelectionManager.isSelected(Integer.valueOf(i2));
        SelectableType selectableType = SelectableType.SUPPORT;
        int i8 = i7;
        boolean z = true;
        while (true) {
            if (isData(i8)) {
                if (isSelected && !isSelectSpaceRemained()) {
                    selectableType = SelectableType.REACHED_MAX_COUNT;
                    z = false;
                    break;
                }
                selectableType = selectItem(i8, isSelected, true);
                if (selectableType != SelectableType.SUPPORT) {
                    z = false;
                }
                i8++;
                if (i8 >= getItemCount()) {
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            this.mSelectionManager.select(Integer.valueOf(i2), false, false);
            showToastForSelectionError(selectableType);
        }
        notifySelectedItemChanged(i7, i8);
        return z;
    }

    /* renamed from: syncClusterDivider */
    public void lambda$updateCheckboxOnBindMediaItem$0(int i2) {
        boolean z;
        if (this.mSelectionManager == null) {
            Log.e(this.TAG, "syncClusterDivider : mSelectionManager==null");
        } else if (i2 < 0) {
            Log.e(this.TAG, "syncClusterDivider : invalid view position {" + i2 + "}");
        } else {
            int dividerIndex = getDividerIndex(i2);
            if (dividerIndex >= 0) {
                if (!this.mSelectionManager.isSelected(Integer.valueOf(i2)) || !isAllClusterDataChecked(dividerIndex)) {
                    z = false;
                } else {
                    z = true;
                }
                this.mSelectionManager.select(Integer.valueOf(dividerIndex), z, false);
                notifySelectedItemChanged(dividerIndex, dividerIndex);
            }
        }
    }

    public void syncClusterDividerOnLongClick(int i2, int i7) {
        if (isSelectionMode() && i7 == 0) {
            lambda$updateCheckboxOnBindMediaItem$0(i2);
        }
    }

    public void unSelectAll() {
        if (this.mSelectionManager != null) {
            this.mClipBoard.clear();
            this.mSelectionManager.unSelectAll();
            notifySelectedItemChanged();
        }
    }

    public boolean useClipBoardForNormalSelectionMode() {
        return false;
    }

    private SelectableType isItemSelectable(int i2, MediaItem mediaItem) {
        if (this.mSelectionManager.isSelected(Integer.valueOf(i2)) || this.mSelectableChecker == null) {
            return SelectableType.SUPPORT;
        }
        if (!isSelectSpaceRemained()) {
            return SelectableType.REACHED_MAX_COUNT;
        }
        if (!isData(i2) || mediaItem == null || this.mSelectableChecker.isSupported(mediaItem)) {
            return SelectableType.SUPPORT;
        }
        return SelectableType.UNSUPPORTED;
    }

    public void notifyItemRangeChanged(String str) {
        notifyItemRangeChanged(0, getItemCount(), str);
    }

    public final void notifySelectedItemChanged() {
        notifyItemRangeChanged("checkBoxItem");
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2, int i7) {
    }

    public void onBindViewHolder(VH vh, int i2, List<Object> list) {
        if (list.contains("checkBox") || list.contains("checkBoxItem") || list.contains("updateDecoView")) {
            boolean isSelectionMode = isSelectionMode();
            if (list.contains("updateDecoView")) {
                vh.setSupportDecoItemType(getDecoItemViewType());
                vh.updateDecoItemViews();
            }
            if (list.contains("checkBox")) {
                if (this.mAdvancedMouseFocusManager != null) {
                    vh.setFocusedFilterOnAdvancedMouseEvent(false);
                }
                enableCheckbox(vh, isSelectionMode, true);
                if (isSelectionMode && isData(i2)) {
                    setCheckboxAnimation(vh);
                }
                setListeners(vh);
            }
            if (isSelectionMode && list.contains("checkBoxItem")) {
                syncSelectionWithClipboard(vh, i2);
                vh.setChecked(this.mSelectionManager.isSelected(Integer.valueOf(i2)));
                DragSelectTouchListener dragSelectTouchListener = this.mDragSelectTouchListener;
                if ((dragSelectTouchListener != null && dragSelectTouchListener.isAutoScrolling()) || isInvisibleViewHolder(vh)) {
                    vh.skipCheckboxAnimation();
                }
            }
            if (this.mAdvancedMouseFocusManager != null && list.contains("advancedMouseFocusItem")) {
                vh.setFocusedFilterOnAdvancedMouseEvent(this.mAdvancedMouseFocusManager.containsViewPosition(i2));
            }
        } else if (this.mAdvancedMouseFocusManager == null || !list.contains("advancedMouseFocusItem")) {
            super.onBindViewHolder(vh, i2, list);
        } else {
            vh.setFocusedFilterOnAdvancedMouseEvent(this.mAdvancedMouseFocusManager.containsViewPosition(i2));
        }
    }

    public void onDataChanged() {
    }

    public void onDividerSelected() {
    }

    public void onPostLongPressSelectMode() {
    }

    public void onPrepareLongPressSelectMode() {
    }

    public void onStartSelectMode() {
    }

    public void removePendingJobs() {
    }

    public void resetBlockStartDrag() {
    }

    public void resume() {
    }

    public void updateExtraViewHolderMargin() {
    }

    public void checkPreviewCandidate(long j2) {
    }

    public int getViewPosition(int i2) {
        return i2;
    }

    public void onExitSelectMode(boolean z) {
    }

    public void stopPreview(boolean z) {
    }

    public void onBindViewHolder(VH vh, int i2) {
        super.onBindViewHolder(vh, i2);
        if (isNeedToUpdateDecoView(i2)) {
            updateDecoViews(vh, i2);
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
    }

    public void sendItemSelectedSyncEvent(boolean z, int i2) {
    }

    public void setVisibleArea(int i2, int i7) {
    }

    public void onLayoutChanged(View view, int i2, int i7, int i8, int i10) {
    }
}
