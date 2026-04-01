package com.samsung.android.gallery.app.ui.list.albums.drag;

import A.a;
import A4.C0368c;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.ReorderAlbumCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.drag.IAlbumsDragView;
import com.samsung.android.gallery.app.ui.list.albums.viewholder.AlbumTitleCountViewHolder;
import com.samsung.android.gallery.app.ui.list.reorder.DragUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumsDragAdapter<V extends IAlbumsDragView> extends AlbumsBaseViewAdapter<V> implements IReorderHandler {
    protected final boolean SUPPORT_REORDER = Features.isEnabled(Features.SUPPORT_REORDER);
    private Drawable[] mBorderDrawable;
    private int mDraggedIndex = -1;
    protected boolean mDragging = false;
    private int mInvisibleFolderIndex = -1;
    private Drawable[] mSelectedBorder;

    public AlbumsDragAdapter(V v, String str) {
        super(v, str);
    }

    private void bindDragEvent(ListViewHolder listViewHolder, int i2) {
        boolean z;
        float f;
        if (listViewHolder instanceof AlbumTitleCountViewHolder) {
            AlbumTitleCountViewHolder albumTitleCountViewHolder = (AlbumTitleCountViewHolder) listViewHolder;
            if (this.mDraggedIndex == i2) {
                z = true;
            } else {
                z = false;
            }
            albumTitleCountViewHolder.enableBorderView(z, this.mBorderDrawable[this.mGalleryListView.getDepth()]);
            View rootView = albumTitleCountViewHolder.getRootView();
            if (this.mInvisibleFolderIndex == i2) {
                f = 0.0f;
            } else {
                f = 1.0f;
            }
            rootView.setAlpha(f);
            if (this.mInvisibleFolderIndex == i2) {
                PrintStream printStream = System.out;
                printStream.println("mInvisibleIndex = " + this.mInvisibleFolderIndex);
            }
        }
    }

    private boolean handleDragEvent(final ListViewHolder listViewHolder, List<Object> list) {
        if (listViewHolder instanceof AlbumTitleCountViewHolder) {
            AlbumTitleCountViewHolder albumTitleCountViewHolder = (AlbumTitleCountViewHolder) listViewHolder;
            if (list.contains("add_drag_index")) {
                albumTitleCountViewHolder.enableBorderView(true, this.mBorderDrawable[this.mGalleryListView.getDepth()]);
                return true;
            } else if (list.contains("remove_drag_index")) {
                albumTitleCountViewHolder.enableBorderView(false, this.mBorderDrawable[this.mGalleryListView.getDepth()]);
                return true;
            } else if (list.contains("add_highlight_index")) {
                listViewHolder.addThumbnailBorder(this.mSelectedBorder[this.mGalleryListView.getDepth()]);
                return true;
            } else if (list.contains("reset_view_transparency")) {
                PrintStream printStream = System.out;
                printStream.println("RESET_VIEW_TRANSPARENCY = " + this.mInvisibleFolderIndex);
                DragUtil.animateView(listViewHolder.getRootView(), R.anim.fade_in_folder, new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        listViewHolder.getRootView().setAlpha(1.0f);
                    }
                });
                this.mInvisibleFolderIndex = -1;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveFolderToPosition$0(int i2) {
        notifyItemChanged(i2, "reset_view_transparency");
    }

    private void notifyAlbumOrderUpdated() {
        Blackboard blackboard = this.mBlackBoard;
        Boolean bool = Boolean.FALSE;
        if (((Boolean) blackboard.pop("data://useralbum_order_is_updated", bool)).booleanValue()) {
            this.mBlackBoard.publish("album_data_swapped", bool);
        }
    }

    private void updateDrawableCornerRadius() {
        if (PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR) {
            Resources resources = getContext().getResources();
            ViewUtils.setDrawableCornerRadius(this.mBorderDrawable[0], resources.getDimension(R.dimen.album_view_corner_radius_list_blur));
            ViewUtils.setDrawableCornerRadius(this.mBorderDrawable[1], resources.getDimension(R.dimen.album_view_corner_radius_grid_blur));
            ViewUtils.setDrawableCornerRadius(this.mBorderDrawable[2], resources.getDimension(R.dimen.album_view_corner_radius_grid_max_blur));
            ViewUtils.setDrawableCornerRadius(this.mSelectedBorder[1], resources.getDimension(R.dimen.album_view_corner_radius_grid_blur));
            ViewUtils.setDrawableCornerRadius(this.mSelectedBorder[2], resources.getDimension(R.dimen.album_view_corner_radius_grid_max_blur));
        }
    }

    public void folderCreatedAt(int i2, MediaItem mediaItem, int i7, String str) {
        if (this.mDragging) {
            a.k(i2, "folderDataCreatedAt : folderViewIndex = ", this.TAG);
            this.mBlackBoard.publish("dragged_item_album_order", Long.valueOf(this.mMediaData.createFolderAt(getMediaItemPosition(i2), mediaItem, i7, str)));
            updateCluster();
            notifyItemChanged(i2);
        }
    }

    public ArrayList<MediaItem> getAllAlbumData() {
        return this.mMediaData.getAllData();
    }

    public MediaItem getSyncedItem(int i2) {
        return getMediaItemSync(i2);
    }

    public void initResourceValues() {
        super.initResourceValues();
        this.mBorderDrawable = new Drawable[]{getContext().getDrawable(R.drawable.albums_drag_border_list), getContext().getDrawable(R.drawable.albums_drag_border_grid), getContext().getDrawable(R.drawable.albums_drag_border_grid_max)};
        this.mSelectedBorder = new Drawable[]{null, getContext().getDrawable(R.drawable.albums_highlighted_border_grid), getContext().getDrawable(R.drawable.albums_highlighted_border_grid_max)};
        updateDrawableCornerRadius();
    }

    public boolean isDragSelectSupported() {
        if (this.mLocationKey.equals("location://albums") || this.mLocationKey.equals("location://albums/all")) {
            return false;
        }
        return true;
    }

    public boolean isReorderSupported() {
        if (!this.SUPPORT_REORDER) {
            return false;
        }
        if (this.mLocationKey.equals("location://albums") || this.mLocationKey.equals("location://albums/all")) {
            return true;
        }
        return false;
    }

    public void moveFolderToPosition(int i2, int i7) {
        this.mInvisibleFolderIndex = i7;
        int mediaItemPosition = getMediaItemPosition(i2);
        int mediaItemPosition2 = getMediaItemPosition(i7);
        Log.d(this.TAG, "moveFolderToPosition", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(mediaItemPosition), Integer.valueOf(mediaItemPosition2));
        this.mMediaData.reorderData(mediaItemPosition, mediaItemPosition2);
        updateCluster();
        notifyItemMoved(i2, i7);
        if (i7 % this.mGalleryListView.getColumnCount() == 0) {
            this.mGalleryListView.scrollToPosition(i7);
        }
        ThreadUtil.postOnUiThreadDelayed(new C0368c(this, i7, 5), 200);
    }

    public void moveItemTo(int i2, int i7) {
        if (this.mDragging && isReorderSupported() && getMediaItemPosition(i2) != -1 && getMediaItemPosition(i7) != -1) {
            String str = this.TAG;
            Log.d(str, "moveItemTo : fromIdx = " + i2 + ", toIdx = " + i7);
            this.mMediaData.reorderData(getMediaItemPosition(i2), getMediaItemPosition(i7));
            notifyItemMoved(i2, i7);
            if (i2 != i7) {
                this.mBlackBoard.publish("data://useralbum_order_is_updated", Boolean.TRUE);
            }
            if (isSelectionMode()) {
                restoreSelectionOnDataChanged((Runnable) null, false);
            }
            if (i2 == 0 || i7 == 0) {
                this.mGalleryListView.scrollToPosition(0);
            }
        }
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!PickerUtil.isNormalLaunchMode(this.mBlackBoard) || !this.mSelectionManager.isSelectMode()) {
            return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
        }
        ((IAlbumsDragView) this.mView).onListItemLongClick(listViewHolder, i2, mediaItem);
        ((IAlbumsDragView) this.mView).postAnalyticsLog(AnalyticsEventId.EVENT_LONG_PRESS_ALBUM);
        return true;
    }

    public void orderChanged(int i2, Object obj) {
        notifyItemChanged(i2, obj);
    }

    public void removeItemAt(int i2) {
        if (this.mDragging) {
            a.k(i2, "removeItemAt : index = ", this.TAG);
            this.mMediaData.removeItemAt(getMediaItemPosition(i2));
            updateCluster();
            notifyItemRemoved(i2);
            if (isSelectionMode()) {
                restoreSelectionOnDataChanged((Runnable) null, false);
            }
        }
    }

    public void resetDrag() {
        this.mDraggedIndex = -1;
        this.mInvisibleFolderIndex = -1;
        this.mDragging = false;
        notifyAlbumOrderUpdated();
    }

    public void setDraggedIndex(int i2) {
        this.mDraggedIndex = i2;
    }

    public void setDragging(boolean z) {
        this.mDragging = z;
    }

    public int updateFolderInfo(int i2, int i7, String str) {
        if (!this.mDragging) {
            return -1;
        }
        a.k(i2, "updateFolderInfo : folderIndex = ", this.TAG);
        MediaItem read = this.mMediaData.read(getMediaItemPosition(i2));
        if (read == null || !read.isFolder() || read.getFolderName().equals(str)) {
            return -1;
        }
        return this.mMediaData.updateFolderAt(getMediaItemPosition(i2), i7, str);
    }

    public void updateOrder(EventContext eventContext) {
        if (this.mDragging && isReorderSupported() && getMediaItemPosition(this.mDraggedIndex) != -1) {
            String str = this.TAG;
            Log.d(str, "updateAlbumOrder : mDragIndex = " + getMediaItemPosition(this.mDraggedIndex));
            new ReorderAlbumCmd().execute(eventContext, getAllAlbumData(), Integer.valueOf(getMediaItemPosition(this.mDraggedIndex)), Boolean.valueOf(LocationKey.isAlbums(this.mLocationKey)));
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!handleDragEvent(listViewHolder, list) || list.contains("checkBox")) {
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (!((IAlbumsDragView) this.mView).isMoveMode()) {
            bindDragEvent(listViewHolder, i2);
        }
    }
}
