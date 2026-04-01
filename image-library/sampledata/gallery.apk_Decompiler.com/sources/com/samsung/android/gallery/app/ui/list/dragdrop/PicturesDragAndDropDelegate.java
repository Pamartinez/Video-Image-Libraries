package com.samsung.android.gallery.app.ui.list.dragdrop;

import T3.e;
import T4.d;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesDragAndDropDelegate extends ListDragAndDropDelegate {
    private Runnable mUpdateMenu;

    public PicturesDragAndDropDelegate(IBaseListView iBaseListView) {
        super(iBaseListView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragInner$0() {
        Optional.ofNullable(this.mView).ifPresent(new e(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragInner$1() {
        ThreadUtil.postOnUiThreadDelayed(new d(this, 1), 100);
    }

    public boolean handleDragStarted(DragEvent dragEvent) {
        Runnable runnable;
        GalleryListView listView = this.mView.getListView();
        if (listView == null || getMode().isNormal()) {
            String str = this.TAG;
            Log.d(str, "handleDrag not support : " + getMode().toString() + "/" + listView);
            return false;
        }
        if (getHandler().isDragStartedFromGallery(dragEvent) && (runnable = this.mUpdateMenu) != null) {
            runnable.run();
            this.mUpdateMenu = null;
        }
        return super.handleDragStarted(dragEvent);
    }

    public boolean isAutoDragPossible() {
        if (getMode().isNormal() || !this.mView.isSelectionMode() || this.mView.getListView().isOngoingPinchAnimation() || !PickerUtil.isNormalLaunchMode(this.mView.getBlackboard()) || this.mView.getAdapter() == null || this.mView.getAdapter().getSelectableChecker() != null) {
            return false;
        }
        return true;
    }

    public boolean startAutoDrag(int i2) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mView.getListView().findViewHolderForAdapterPosition(i2);
        if (listViewHolder == null) {
            Log.e(this.TAG, "startAutoDrag skipped, viewHolder is null");
            return false;
        }
        MediaItem[] selectedItems = this.mView.getSelectedItems();
        if (selectedItems == null || selectedItems.length == 0) {
            Log.e(this.TAG, "startAutoDrag skipped, nothing selected");
            return false;
        }
        startDrag(selectedItems, listViewHolder);
        return true;
    }

    public void startDragInner(View view, MediaItem[] mediaItemArr, ListViewHolder listViewHolder) {
        ClipData clipData = ClipDataManager.getInstance().getClipData(this.mView.getContext(), ClipDataCreatorFactory.create(getMode(), this.mView.getLocationKey(), mediaItemArr));
        if (clipData == null) {
            Log.d(this.TAG, "start drag failed. invalid ClipData");
            return;
        }
        ClipDataUtils.putStringExtra(clipData, "started_location", this.mView.getLocationKey());
        getHandler().start(this.mView.getListView(), view, clipData, listViewHolder, mediaItemArr.length);
        this.mUpdateMenu = new d(this, 0);
    }
}
