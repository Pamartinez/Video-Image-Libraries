package com.samsung.android.gallery.app.ui.list.mtp.pictures;

import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.mtp.pictures.MtpPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpPicturesFragment<V extends IPicturesView, P extends MtpPicturesPresenter<V>> extends PicturesFragment<V, P> implements IPicturesView {
    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public int getLayoutId() {
        return R.layout.fragment_mtp_pictures_layout;
    }

    public String getScreenId() {
        P p6 = this.mPresenter;
        if (p6 == null) {
            return null;
        }
        if (((MtpPicturesPresenter) p6).isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_MTP_PICTURES_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_MTP_PICTURES_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return 2;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        StringCompat stringCompat = this.TAG;
        Log.mt(stringCompat, "onDataChangedOnUi [" + getItemCount() + "]");
    }

    public void onExpandItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        super.onExpandItemClick(listViewHolder, i2, mediaItem);
        this.mBlackboard.publish("data://user/fromMtpViewer", Boolean.TRUE);
    }

    public boolean supportRealRatio() {
        return false;
    }

    public MtpPicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MtpPicturesViewAdapter(this, getLocationKey());
    }

    public MtpPicturesPresenter createPresenter(IPicturesView iPicturesView) {
        return new MtpPicturesPresenter(this.mBlackboard, iPicturesView);
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
    }
}
