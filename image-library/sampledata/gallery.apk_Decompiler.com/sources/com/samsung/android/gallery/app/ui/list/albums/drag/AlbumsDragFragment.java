package com.samsung.android.gallery.app.ui.list.albums.drag;

import A4.C0385u;
import E9.a;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragPresenter;
import com.samsung.android.gallery.app.ui.list.albums.drag.IAlbumsDragView;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumsDragFragment<V extends IAlbumsDragView, P extends AlbumsDragPresenter> extends AlbumsBaseFragment<V, P> implements IAlbumsDragView {
    private final RecyclerView.SimpleOnItemTouchListener mItemTouchListener = createItemTouchListener();
    /* access modifiers changed from: private */
    public ReorderDragManager mReorderDragManager;

    private RecyclerView.SimpleOnItemTouchListener createItemTouchListener() {
        return new RecyclerView.SimpleOnItemTouchListener() {
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                if (AlbumsDragFragment.this.mReorderDragManager == null) {
                    return false;
                }
                AlbumsDragFragment.this.mReorderDragManager.onRecyclerViewTouchEvent(AlbumsDragFragment.this.getListView(), motionEvent, AlbumsDragFragment.this.isSelectionMode());
                return false;
            }
        };
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$1(GalleryListView galleryListView) {
        galleryListView.removeOnItemTouchListener(this.mItemTouchListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onViewCreated$0() {
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager == null || !reorderDragManager.isDragStarted()) {
            return false;
        }
        return true;
    }

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new AlbumsDragAndDropDelegate(this);
        getListView().setTag("DRAG_LISTVIEW");
    }

    public abstract AlbumsDragAdapter createListViewAdapter(GalleryListView galleryListView);

    public ReorderDragManager getReorderDragManager() {
        return new ReorderDragManager(this.mBlackboard, this);
    }

    public IReorderHandler getReorderHandler() {
        return (IReorderHandler) super.getAdapter();
    }

    public int getViewPositionOffset(AlbumsDragAdapter<?> albumsDragAdapter) {
        return albumsDragAdapter.getHeaderCount();
    }

    public void initializeAdapter() {
        super.initializeAdapter();
        if (getAdapter() != null && !getAdapter().isDragSelectSupported()) {
            ReorderDragManager reorderDragManager = getReorderDragManager();
            this.mReorderDragManager = reorderDragManager;
            reorderDragManager.setDragListener(true);
            getListView().addOnItemTouchListener(this.mItemTouchListener);
        }
    }

    public boolean isAllowAdvancedMouseEvent() {
        return !isMoveMode();
    }

    public void onDestroy() {
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager != null) {
            reorderDragManager.destroy();
            this.mReorderDragManager = null;
        }
        Optional.ofNullable(getListView()).ifPresent(new a(1, this));
        super.onDestroy();
    }

    public void onEmptySpaceSecondaryClick(PointF pointF) {
        if (!isMoveMode()) {
            super.onEmptySpaceSecondaryClick(pointF);
        }
    }

    public boolean onFolderCreatedFromDrag(int i2, String str) {
        boolean z;
        AlbumsDragAdapter adapter;
        int i7;
        String str2;
        boolean z3 = true;
        if (this.mReorderDragManager == null || (adapter = getAdapter()) == null) {
            StringCompat stringCompat = this.TAG;
            if (this.mReorderDragManager != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (getAdapter() == null) {
                z3 = false;
            }
            Log.e((CharSequence) stringCompat, "onFolderCreatedFromDrag failed", valueOf, Boolean.valueOf(z3));
            return false;
        }
        int folderIndex = this.mReorderDragManager.getFolderIndex();
        int updateFolderInfo = adapter.updateFolderInfo(folderIndex, i2, str);
        if (updateFolderInfo != -1) {
            i7 = getViewPositionOffset(adapter) + updateFolderInfo;
        } else {
            i7 = -1;
        }
        if (updateFolderInfo == -1 || SortByType.isSortByCustom()) {
            z3 = false;
        }
        StringCompat stringCompat2 = this.TAG;
        if (z3) {
            str2 = "start";
        } else {
            str2 = "skip";
        }
        Log.d(stringCompat2, "onFolderCreatedFromDrag animation", str2, Integer.valueOf(i2), Logger.getEncodedString(str), Integer.valueOf(folderIndex), Integer.valueOf(updateFolderInfo), Integer.valueOf(i7));
        if (z3) {
            this.mReorderDragManager.startFolderCreateAnimation(getListView(), folderIndex, i7);
        }
        return z3;
    }

    public void onFolderCreationFailed(boolean z) {
        Log.e(this.TAG, "onFolderCreationFailed");
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager != null) {
            reorderDragManager.endDrag(false);
        }
        if (!z) {
            getListView().getRecycledViewPool().clear();
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.mDragAndDropDelegate.onKeyDown(getListView(), i2, keyEvent) || super.onKeyDown(i2, keyEvent)) {
            return true;
        }
        return false;
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager == null || !reorderDragManager.isDragging()) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        if (!isAutoDragPossible()) {
            return super.onListItemLongClick(listViewHolder, i2, mediaItem);
        }
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager != null) {
            reorderDragManager.onListItemLongClick(listViewHolder, getListView(), isSelectionMode());
        }
        stopAutoDrag();
        if (this.mDragAndDropDelegate.startAutoDrag(i2) || super.onListItemLongClick(listViewHolder, i2, mediaItem)) {
            return true;
        }
        return false;
    }

    public void onListItemSecondaryClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, PointF pointF) {
        if (!isMoveMode()) {
            super.onListItemSecondaryClick(listViewHolder, i2, mediaItem, pointF);
        }
    }

    public void onPause() {
        super.onPause();
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager != null) {
            if (reorderDragManager.isDragging()) {
                this.mReorderDragManager.stopAlbumDrag();
            }
            this.mReorderDragManager.setDragListener(false);
        }
    }

    public void onResume() {
        super.onResume();
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager != null) {
            reorderDragManager.setDragListener(true);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getListView().useDelayedCancelEvent(new C0385u(2, this));
    }

    public boolean startAutoDrag(int i2) {
        ReorderDragManager reorderDragManager = this.mReorderDragManager;
        if (reorderDragManager == null || !reorderDragManager.isAvailableReorderDrag(getListView(), getActivity())) {
            return super.startAutoDrag(i2);
        }
        Log.d(this.TAG, "reorder case");
        return false;
    }

    public AlbumsDragPresenter createPresenter(IAlbumsDragView iAlbumsDragView) {
        return new AlbumsDragPresenter(this.mBlackboard, iAlbumsDragView);
    }

    public AlbumsDragAdapter getAdapter() {
        return (AlbumsDragAdapter) super.getAdapter();
    }
}
