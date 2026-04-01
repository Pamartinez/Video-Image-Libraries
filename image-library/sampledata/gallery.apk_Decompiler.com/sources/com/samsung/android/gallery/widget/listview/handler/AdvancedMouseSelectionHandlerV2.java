package com.samsung.android.gallery.widget.listview.handler;

import A4.M;
import E9.a;
import Fa.F;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AdvancedMouseSelectionHandlerV2 extends AdvancedMouseSelectionHandler {
    private boolean mIsDragging;
    private int mShiftStartPosition = -1;

    public AdvancedMouseSelectionHandlerV2(GalleryListView galleryListView) {
        super(galleryListView);
    }

    private void cleanSelection() {
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter != null) {
            if (this.mTargetList.getSelectedItemList() != null) {
                Iterator<Integer> it = this.mTargetList.getSelectedItemList().iterator();
                while (it.hasNext()) {
                    adapter.selectItem(it.next().intValue(), false, isNeedSelectNotify());
                }
            }
            focusItemWithSync(new ArrayList());
            this.mDividerPosition.forEach(new a(18, adapter));
            this.mTargetList.notifySelectedItemChanged();
        }
    }

    private int getShiftStartPosition() {
        if (!this.mIsDragging && isOnShiftKeyCombination()) {
            if (this.mTargetList.getSelectedItemCount() + this.mSelectedItems.size() < 2) {
                return ((PenSelectionHandler.Item) C0212a.i(this.mSelectedItems, 1)).mViewPosition;
            }
        }
        return this.mShiftStartPosition;
    }

    private int getStartPadding() {
        RecyclerView.LayoutManager layoutManager = this.mTargetList.getLayoutManager();
        if (layoutManager != null) {
            return layoutManager.getPaddingStart();
        }
        return 0;
    }

    private boolean isNeedSelectNotify() {
        if (isChildType() || this.mTargetList.isMultiPick()) {
            return true;
        }
        return false;
    }

    private boolean isOnCombinationKeys() {
        if (isOnShiftKeyCombination()) {
            return true;
        }
        if (!isOnCtrlKeyCombination() || isOnVirtualCtrlKeyCombination()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateShiftKeySelection$0(int i2, int i7, GalleryListAdapter galleryListAdapter) {
        HashSet hashSet = new HashSet();
        int max = Math.max(i2, i7);
        for (int min = Math.min(i2, i7); min <= max; min++) {
            hashSet.add(Integer.valueOf(min));
            galleryListAdapter.selectItem(min, true, isNeedSelectNotify());
        }
        if (this.mTargetList.getSelectedItemList() != null) {
            Iterator<Integer> it = this.mTargetList.getSelectedItemList().iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if (!hashSet.contains(next)) {
                    galleryListAdapter.selectItem(next.intValue(), false, isNeedSelectNotify());
                }
            }
        }
        focusItemWithSync(new ArrayList());
        ArrayList<Integer> arrayList = this.mDividerPosition;
        Objects.requireNonNull(galleryListAdapter);
        arrayList.forEach(new a(18, galleryListAdapter));
        this.mTargetList.notifySelectedItemChanged();
    }

    private void updateFocus() {
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter != null) {
            HashSet hashSet = new HashSet();
            Iterator<PenSelectionHandler.Item> it = this.mSelectedItems.iterator();
            while (it.hasNext()) {
                PenSelectionHandler.Item next = it.next();
                hashSet.add(Integer.valueOf(next.mViewPosition));
                if (this.mTargetList.isSelectionMode()) {
                    adapter.selectItem(next.mViewPosition, true, isNeedSelectNotify());
                }
            }
            if (this.mTargetList.isSelectionMode() && this.mTargetList.getSelectedItemList() != null && !isOnCombinationKeys()) {
                Iterator<Integer> it2 = this.mTargetList.getSelectedItemList().iterator();
                while (it2.hasNext()) {
                    Integer next2 = it2.next();
                    if (!hashSet.contains(next2)) {
                        adapter.selectItem(next2.intValue(), false, isNeedSelectNotify());
                    }
                }
                this.mDividerPosition.forEach(new a(18, adapter));
                this.mTargetList.notifySelectedItemChanged();
            }
            focusItemWithSync(new ArrayList(hashSet));
        }
    }

    private void updateShiftKeySelection() {
        int i2;
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter != null && this.mTargetList.getSelectedItemList() != null && (i2 = this.mShiftStartPosition) >= 0) {
            ThreadUtil.postOnUiThreadDelayed(new M((Object) this, i2, this.mSelectedItems.get(0).mViewPosition, (Object) adapter, 3), 50);
        }
    }

    private boolean validArea(int i2) {
        if (getStartPadding() <= i2) {
            return true;
        }
        return false;
    }

    public DisHandler createChildDisHandler(GalleryListView galleryListView) {
        return new AdvancedMouseSelectionHandlerV2(galleryListView, getChildTag());
    }

    public void focusItemWithSync(ArrayList<Integer> arrayList) {
        if (arrayList.isEmpty()) {
            resetListViewHolderTint();
            clearLastSelectedViewPosition();
            return;
        }
        handleFocusOnNormal(arrayList);
        Optional.ofNullable(this.mTargetList.getAdapter()).ifPresent(new F(18));
    }

    public void handleDown(MotionEvent motionEvent, int i2) {
        if (validArea((int) motionEvent.getX())) {
            super.handleDown(motionEvent, i2);
        }
    }

    public void handleMove(int i2, int i7) {
        if (validArea(i2) && isActive()) {
            this.mIsDragging = true;
            this.mShiftStartPosition = -1;
            super.handleMove(i2, i7);
        }
    }

    public void handleUp(int i2, int i7) {
        if (validArea(i2)) {
            super.handleUp(i2, i7);
        } else {
            this.mShiftStartPosition = -1;
        }
        this.mIsDragging = false;
    }

    public void updateSelectionInternal(boolean z) {
        GalleryListAdapter adapter = this.mTargetList.getAdapter();
        if (adapter != null) {
            if (z) {
                updateFocus();
            } else if (this.mSelectedItems.isEmpty()) {
                cleanSelection();
            } else {
                this.mShiftStartPosition = getShiftStartPosition();
                if (!this.mTargetList.isSelectionMode()) {
                    this.mTargetList.enterSelectionMode(0);
                    Iterator<PenSelectionHandler.Item> it = this.mSelectedItems.iterator();
                    while (it.hasNext()) {
                        adapter.selectItem(it.next().mViewPosition, true, isNeedSelectNotify());
                    }
                } else if (!this.mIsDragging && isOnShiftKeyCombination()) {
                    updateShiftKeySelection();
                }
                focusItemWithSync(new ArrayList());
                this.mDividerPosition.forEach(new a(18, adapter));
                this.mTargetList.notifySelectedItemChanged();
            }
        }
    }

    public AdvancedMouseSelectionHandlerV2(GalleryListView galleryListView, String str) {
        super(galleryListView, str);
    }
}
