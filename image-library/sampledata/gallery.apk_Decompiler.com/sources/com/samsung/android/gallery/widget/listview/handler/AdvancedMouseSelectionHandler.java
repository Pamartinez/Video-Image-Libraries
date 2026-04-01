package com.samsung.android.gallery.widget.listview.handler;

import A4.A;
import D3.i;
import F6.f;
import Fa.F;
import Gb.a;
import Gb.b;
import Gb.c;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AdvancedMouseSelectionHandler extends PenSelectionHandler implements RecyclerView.SeslLongPressMultiSelectionListener {
    private boolean mIsActive;
    private Consumer<Void> mOnUpdateListener;
    private boolean mSecondaryButtonClicked;
    private BooleanSupplier mUseDnd = new f(4);

    public AdvancedMouseSelectionHandler(GalleryListView galleryListView) {
        super(galleryListView);
        this.mTargetList.seslSetLongPressMultiSelectionListener(this);
    }

    private AdvancedMouseFocusManager getAdvancedMouseFocusManager() {
        return (AdvancedMouseFocusManager) Optional.ofNullable(this.mTargetList.getAdapter()).map(new i(29)).orElse((Object) null);
    }

    private void handleFocusOnCtrlKeyCombination(ArrayList<Integer> arrayList) {
        AdvancedMouseFocusManager advancedMouseFocusManager = getAdvancedMouseFocusManager();
        if (advancedMouseFocusManager != null) {
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                boolean containsViewPosition = advancedMouseFocusManager.containsViewPosition(intValue);
                if (containsViewPosition) {
                    advancedMouseFocusManager.removeViewPosition(intValue);
                } else {
                    advancedMouseFocusManager.addViewPosition(intValue);
                }
                ListViewHolder listViewHolder = (ListViewHolder) this.mTargetList.findViewHolderForLayoutPosition(intValue);
                if (listViewHolder != null) {
                    listViewHolder.setFocusedFilterOnAdvancedMouseEvent(!containsViewPosition);
                } else {
                    String str = this.TAG;
                    Log.w(str, "viewHolder is null : " + intValue);
                }
            }
        }
    }

    private void handleFocusOnShiftKeyCombination(ArrayList<Integer> arrayList) {
        AdvancedMouseFocusManager advancedMouseFocusManager = getAdvancedMouseFocusManager();
        if (advancedMouseFocusManager != null) {
            if (arrayList.size() == 1) {
                handleSingleItemSelectionOnShift(arrayList, advancedMouseFocusManager);
            } else {
                handleMultipleItemSelection(arrayList, advancedMouseFocusManager);
            }
        }
    }

    private void handleMultipleItemSelection(ArrayList<Integer> arrayList, AdvancedMouseFocusManager advancedMouseFocusManager) {
        arrayList.forEach(new A(15, (Object) this, (Object) advancedMouseFocusManager));
    }

    private void handleSingleItemSelectionOnNormal(ArrayList<Integer> arrayList, AdvancedMouseFocusManager advancedMouseFocusManager) {
        int intValue = arrayList.get(0).intValue();
        ListViewHolder listViewHolder = (ListViewHolder) this.mTargetList.findViewHolderForLayoutPosition(intValue);
        boolean containsViewPosition = advancedMouseFocusManager.containsViewPosition(intValue);
        int totalCount = advancedMouseFocusManager.getTotalCount();
        if (!containsViewPosition || (!this.mSecondaryButtonClicked && totalCount != 1)) {
            resetListViewHolderTint();
            advancedMouseFocusManager.addViewPosition(intValue);
            if (listViewHolder != null) {
                listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
            }
        }
    }

    private void handleSingleItemSelectionOnShift(ArrayList<Integer> arrayList, AdvancedMouseFocusManager advancedMouseFocusManager) {
        int lastSelectedViewPosition = advancedMouseFocusManager.getLastSelectedViewPosition();
        if (lastSelectedViewPosition == -1 || advancedMouseFocusManager.containsViewPosition(lastSelectedViewPosition)) {
            int intValue = arrayList.get(0).intValue();
            int min = Math.min(intValue, lastSelectedViewPosition);
            int max = Math.max(intValue, lastSelectedViewPosition);
            advancedMouseFocusManager.setLastSelectedViewPosition(intValue);
            String str = this.TAG;
            Log.d(str, min + " -> " + max);
            IntStream.range(min, max + 1).filter(new b(0, this)).forEach(new c(0, this, advancedMouseFocusManager));
            return;
        }
        handleFocusOnCtrlKeyCombination(arrayList);
    }

    private boolean isSecondaryButtonClicked(MotionEvent motionEvent) {
        if (motionEvent == null || !motionEvent.isButtonPressed(2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMultipleItemSelection$3(AdvancedMouseFocusManager advancedMouseFocusManager, Integer num) {
        advancedMouseFocusManager.addViewPosition(num.intValue(), true);
        ListViewHolder listViewHolder = (ListViewHolder) this.mTargetList.findViewHolderForLayoutPosition(num.intValue());
        if (listViewHolder != null) {
            listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
            return;
        }
        String str = this.TAG;
        Log.w(str, "viewHolder is null : " + num);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handleSingleItemSelectionOnShift$1(int i2) {
        return this.mTargetList.isData(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSingleItemSelectionOnShift$2(AdvancedMouseFocusManager advancedMouseFocusManager, int i2) {
        advancedMouseFocusManager.addViewPosition(i2, true);
        ListViewHolder listViewHolder = (ListViewHolder) this.mTargetList.findViewHolderForLayoutPosition(i2);
        if (listViewHolder != null) {
            listViewHolder.setFocusedFilterOnAdvancedMouseEvent(true);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0() {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetListViewHolderTint$4(Integer num) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mTargetList.findViewHolderForLayoutPosition(num.intValue());
        if (listViewHolder != null) {
            listViewHolder.setFocusedFilterOnAdvancedMouseEvent(false);
        }
    }

    public final void clearLastSelectedViewPosition() {
        AdvancedMouseFocusManager advancedMouseFocusManager = getAdvancedMouseFocusManager();
        if (advancedMouseFocusManager != null) {
            advancedMouseFocusManager.clearLastSelectedViewPosition();
        }
    }

    public DisHandler createChildDisHandler(GalleryListView galleryListView) {
        return new AdvancedMouseSelectionHandler(galleryListView, getChildTag());
    }

    public void focusItemWithSync(ArrayList<Integer> arrayList) {
        if (arrayList.isEmpty()) {
            resetListViewHolderTint();
            clearLastSelectedViewPosition();
            return;
        }
        if (isOnCtrlKeyCombination() && !isOnVirtualCtrlKeyCombination()) {
            handleFocusOnCtrlKeyCombination(arrayList);
        } else if (isOnShiftKeyCombination()) {
            handleFocusOnShiftKeyCombination(arrayList);
        } else {
            handleFocusOnNormal(arrayList);
        }
        Optional.ofNullable(this.mTargetList.getAdapter()).ifPresent(new F(18));
    }

    public void handleDown(MotionEvent motionEvent, int i2) {
        reset();
        handleDown((int) motionEvent.getX(), (int) motionEvent.getY(), i2);
        this.mSecondaryButtonClicked = isSecondaryButtonClicked(motionEvent);
        this.mIsActive = true;
    }

    public final void handleFocusOnNormal(ArrayList<Integer> arrayList) {
        AdvancedMouseFocusManager advancedMouseFocusManager = getAdvancedMouseFocusManager();
        if (advancedMouseFocusManager != null) {
            if (arrayList.size() == 1) {
                handleSingleItemSelectionOnNormal(arrayList, advancedMouseFocusManager);
                return;
            }
            resetListViewHolderTint();
            handleMultipleItemSelection(arrayList, advancedMouseFocusManager);
        }
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public final boolean isOnCtrlKeyCombination() {
        return ((Boolean) Optional.ofNullable(this.mTargetList.getAdapter()).map(new a(2)).orElse(Boolean.FALSE)).booleanValue();
    }

    public final boolean isOnShiftKeyCombination() {
        return ((Boolean) Optional.ofNullable(this.mTargetList.getAdapter()).map(new a(0)).orElse(Boolean.FALSE)).booleanValue();
    }

    public final boolean isOnVirtualCtrlKeyCombination() {
        return ((Boolean) Optional.ofNullable(this.mTargetList.getAdapter()).map(new a(1)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isActive() && this.mUseDnd.getAsBoolean()) {
            super.onBindViewHolder(listViewHolder, i2);
        }
    }

    public void reset() {
        super.reset();
        this.mIsActive = false;
        this.mSecondaryButtonClicked = false;
    }

    public final void resetListViewHolderTint() {
        AdvancedMouseFocusManager advancedMouseFocusManager = getAdvancedMouseFocusManager();
        if (advancedMouseFocusManager != null) {
            advancedMouseFocusManager.getSelectedList().forEach(new E9.a(17, this));
            advancedMouseFocusManager.clearViewPosition();
        }
    }

    public void resetListener() {
        this.mTargetList.seslSetLongPressMultiSelectionListener((RecyclerView.SeslLongPressMultiSelectionListener) null);
        this.mOnUpdateListener = null;
        this.mUseDnd = null;
    }

    public void setOnUpdateSelectionListener(Consumer<Void> consumer) {
        this.mOnUpdateListener = consumer;
    }

    public void updateSelectionInternal(boolean z) {
        if (this.mTargetList.getAdapter() != null) {
            if (!this.mTargetList.isSelectionMode()) {
                ArrayList arrayList = new ArrayList();
                Iterator<PenSelectionHandler.Item> it = this.mSelectedItems.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(it.next().mViewPosition));
                }
                focusItemWithSync(arrayList);
                Consumer<Void> consumer = this.mOnUpdateListener;
                if (consumer != null) {
                    consumer.accept((Object) null);
                }
            } else if (this.mSelectedItems.size() > 1) {
                updateSelection();
            }
        }
    }

    public void useAdvMouseDnd(BooleanSupplier booleanSupplier) {
        this.mUseDnd = booleanSupplier;
    }

    public AdvancedMouseSelectionHandler(GalleryListView galleryListView, String str) {
        super(galleryListView, str);
        this.mTargetList.seslSetLongPressMultiSelectionListener(this);
    }

    public void onLongPressMultiSelectionEnded(int i2, int i7) {
    }

    public void onLongPressMultiSelectionStarted(int i2, int i7) {
    }

    public void onItemSelected(RecyclerView recyclerView, View view, int i2, long j2) {
    }
}
