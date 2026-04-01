package com.samsung.android.gallery.app.ui.list.sharings.pinch;

import com.samsung.android.gallery.app.ui.list.sharings.ISharingsView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pinch.SharingPinchPresenter;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPinchFragment<V extends ISharingsView, P extends SharingPinchPresenter> extends SharingsFragment<V, P> implements ISharingsView {
    public int getLayoutId() {
        return R.layout.fragment_sharing_v3_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new SharingPinchAnimationManager((PinchLayoutManager) getLayoutManager());
    }

    public int getPreferenceDefault() {
        return 1;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUMS_GRID_SIZE;
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        if (getAdapter() != null) {
            getAdapter().initDimens();
        }
    }

    public boolean isAlbum() {
        return true;
    }

    public boolean isSharing() {
        return true;
    }

    public int[] loadPinchColumnResource() {
        return GridHelper.getInstance(getLocationKey()).getGridArray(getContext());
    }

    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
        }
        if (i7 != -1 && i2 != i7 && isResumed()) {
            String zoomLevel = AnalyticsDetail.getZoomLevel(i2);
            postAnalyticsLog(AnalyticsEventId.EVENT_PINCH_ZOOM, zoomLevel);
            Log.majorEvent("PinchZoom : " + zoomLevel);
        }
    }

    public void savePinchDepth(String str, int i2) {
        super.savePinchDepth(str, i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            Blackboard.publishGlobal("command://AlbumsViewChanged", Integer.valueOf(((SharingPinchPresenter) p6).getCurrentViewDepth()));
        }
    }

    public SharingPinchViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SharingPinchViewAdapter(this, getLocationKey());
    }

    public SharingPinchViewAdapter getAdapter() {
        return (SharingPinchViewAdapter) super.getAdapter();
    }

    public SharingPinchLayoutManager createLayoutManager() {
        return new SharingPinchLayoutManager(getContext(), getListView().getColumnCount()) {
            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                SharingPinchFragment.this.onGridChanged(i2, spanCount);
            }
        };
    }

    public SharingPinchPresenter createPresenter(ISharingsView iSharingsView) {
        return new SharingPinchPresenter(this.mBlackboard, iSharingsView);
    }
}
