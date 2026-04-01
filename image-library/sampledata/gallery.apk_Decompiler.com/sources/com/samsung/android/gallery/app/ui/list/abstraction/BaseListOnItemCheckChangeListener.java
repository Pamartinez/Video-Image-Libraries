package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.C;
import A4.D;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DragAndDropDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.selection.OnItemCheckChangeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseListOnItemCheckChangeListener implements OnItemCheckChangeListener {
    private final BaseListBottomHandler mBottomHandler;
    private final DragAndDropDelegate mDragAndDropDelegate;
    private final IBaseListView mView;

    public BaseListOnItemCheckChangeListener(IBaseListView iBaseListView, DragAndDropDelegate dragAndDropDelegate, BaseListBottomHandler baseListBottomHandler) {
        this.mView = iBaseListView;
        this.mDragAndDropDelegate = dragAndDropDelegate;
        this.mBottomHandler = baseListBottomHandler;
    }

    private void alignScrollToBottom(Integer num) {
        if (this.mView.isTouchOngoing()) {
            this.mView.getListView().addOnTouchUpListener(new C(this, num));
        } else {
            this.mBottomHandler.moveToScrollOnBottomLine(num.intValue());
        }
    }

    private int getMinCheckCount(BaseListViewAdapter<?> baseListViewAdapter) {
        if (baseListViewAdapter.useClipBoardForNormalSelectionMode() || !PickerUtil.isNormalLaunchMode(this.mView.getBlackboard())) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$alignScrollToBottom$0(Integer num) {
        this.mBottomHandler.moveToScrollOnBottomLine(num.intValue());
    }

    private void updateClipData(MediaItem mediaItem, boolean z, boolean z3) {
        if (z3) {
            ClipDataManager.getInstance().addRemoveItemAndUpdateLater(this.mView.getListView(), mediaItem, z);
        } else {
            ClipDataManager.getInstance().addRemoveItemAndUpdate(this.mView.getListView(), mediaItem, z);
        }
    }

    public void onItemCheckChanged(Integer num, boolean z) {
        boolean z3;
        BaseListViewAdapter adapter = this.mView.getAdapter();
        if (adapter != null && ((Fragment) this.mView).isResumed()) {
            if (adapter.isDragSelectActive() || adapter.isGroupCheckBoxClicked()) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (this.mDragAndDropDelegate.isDraggingOnGallery()) {
                updateClipData(adapter.getMediaItemSync(num.intValue()), z, z3);
            }
            if (!z3 && z && this.mView.getSelectedItemCount() > getMinCheckCount(adapter)) {
                alignScrollToBottom(num);
            }
        }
    }

    public void onSelectAll() {
        BaseListViewAdapter adapter = this.mView.getAdapter();
        if (this.mDragAndDropDelegate.isDraggingOnGallery() && adapter != null) {
            ClipDataManager.getInstance().onSelectAll(this.mView.getListView(), new D(0, adapter), adapter.getItemCount());
        }
    }

    public void onUnSelectAll() {
        BaseListViewAdapter adapter = this.mView.getAdapter();
        if (this.mDragAndDropDelegate.isDraggingOnGallery() && adapter != null) {
            ClipDataManager.getInstance().onUnselectAll(this.mView.getListView(), new D(0, adapter), adapter.getItemCount());
        }
    }

    public void onItemCheckChangedAll(boolean z) {
    }
}
