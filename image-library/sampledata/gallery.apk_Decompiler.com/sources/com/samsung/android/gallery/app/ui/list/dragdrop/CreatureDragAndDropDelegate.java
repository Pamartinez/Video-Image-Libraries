package com.samsung.android.gallery.app.ui.list.dragdrop;

import A.a;
import Ad.C0720a;
import T3.e;
import T4.b;
import android.content.ClipData;
import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.clipboard.ClipDataUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dragdrop.AlbumsDropTargetFinder;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureDragAndDropDelegate extends ListDragAndDropDelegate {
    private DragAndDropListScroller mDragAndDropListScroller;
    private final AlbumsDropTargetFinder mTargetFinder = new AlbumsDropTargetFinder();
    private Runnable mUpdateMenu;

    public CreatureDragAndDropDelegate(IBaseListView iBaseListView) {
        super(iBaseListView);
        this.mMode = new Lazy<>(new C0720a(7));
    }

    private DragAndDropListScroller getAlbumListScroller() {
        if (this.mDragAndDropListScroller == null) {
            this.mDragAndDropListScroller = new DragAndDropListScroller(this.mView);
        }
        return this.mDragAndDropListScroller;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragInner$1() {
        Optional.ofNullable(this.mView).ifPresent(new e(1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startDragInner$2() {
        ThreadUtil.postOnUiThreadDelayed(new b(this, 1), 100);
    }

    public boolean handleDragLocation(View view, DragEvent dragEvent) {
        float x9 = dragEvent.getX();
        int y = (int) dragEvent.getY();
        getAlbumListScroller().processAutoScroll(y, this.mView.getListView());
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        this.mTargetFinder.findDropView(((int) x9) + iArr[0], y + iArr[1], this.mView.getListView());
        return true;
    }

    public boolean handleDragStarted(DragEvent dragEvent) {
        Runnable runnable;
        GalleryListView listView = this.mView.getListView();
        if (listView == null) {
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

    public boolean handleDrop(View view, DragEvent dragEvent) {
        super.handleDrop(view, dragEvent);
        DragAndDrop handler = getHandler();
        IBaseListView iBaseListView = this.mView;
        boolean handleDrop = handler.handleDrop(iBaseListView, dragEvent, this.mTargetFinder.getTargetItem(iBaseListView.getListView()));
        this.mTargetFinder.resetDrag();
        a.v("handleDrag {DROP,", "}", this.TAG, handleDrop);
        return handleDrop;
    }

    public boolean isAutoDragPossible() {
        if (!this.mView.isSelectionMode() || this.mView.getListView().isOngoingPinchAnimation() || !PickerUtil.isNormalLaunchMode(this.mView.getBlackboard())) {
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
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            ClipDataUtils.putBooleanExtra(clipData, "use_circle_thumb", true);
        }
        ClipDataUtils.putBooleanExtra(clipData, "use_uncropped_bitmap", true);
        if (clipData == null) {
            Log.d(this.TAG, "start drag failed. invalid ClipData");
            return;
        }
        getHandler().start(this.mView.getListView(), view, clipData, listViewHolder, mediaItemArr.length);
        this.mUpdateMenu = new b(this, 0);
    }

    public boolean supportPopupMenu(Context context) {
        return false;
    }
}
