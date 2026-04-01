package com.samsung.android.gallery.app.ui.map.staticmarker;

import a6.g;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SnapShotMapFragment extends GalleryMapFragment {
    private double mLatitude;
    private double mLongitude;
    private MapContainer.OnSnapshotReadyListener mSnapshotReady;

    public Bitmap chinaSnapshot() {
        View view;
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || (view = getView()) == null) {
            return null;
        }
        Bitmap bitmapFromView = ViewUtils.getBitmapFromView(view);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "ChSnapshot bmp = " + bitmapFromView);
        return bitmapFromView;
    }

    public double[] getLocation() {
        return new double[]{this.mLatitude, this.mLongitude};
    }

    public void moveCamera() {
        MapContainer mapContainer = this.mMapContainer;
        if (mapContainer != null) {
            mapContainer.moveCamera(this.mLatitude, this.mLongitude);
        }
    }

    public void onMapReady(Object obj) {
        super.onMapReady(obj);
        this.mMapContainer.setSnapShotCallback(new g(15, this));
        this.mMapContainer.setAllGesturesEnabled(false);
        this.mMapContainer.setZoomControlsEnabled(false);
        moveCamera();
    }

    public void pauseContainer() {
        MapContainer mapContainer;
        if (!isDestroyed() && (mapContainer = this.mMapContainer) != null) {
            mapContainer.onPause();
        }
    }

    public void setLocation(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
        moveCamera();
    }

    public void setMapSnapshotReadyListener(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        this.mSnapshotReady = onSnapshotReadyListener;
    }

    public void snapshotReady(Bitmap bitmap) {
        MapContainer.OnSnapshotReadyListener onSnapshotReadyListener = this.mSnapshotReady;
        if (onSnapshotReadyListener != null) {
            onSnapshotReadyListener.onSnapshotReady(bitmap);
        }
    }

    public boolean supportToolbar() {
        return false;
    }

    public void onPrepareSharedTransitionV2() {
    }

    public void setScreenMode() {
    }
}
