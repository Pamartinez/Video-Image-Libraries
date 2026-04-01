package com.samsung.android.gallery.module.map.abstraction;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapPositionHelper;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MapContainer<MAP> implements IMap<MAP> {
    protected final String TAG = getClass().getSimpleName();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCameraIdleListener {
        void onListen();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnClickListener {
        void onClick();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnGalleryMapReadyListener<MAP> {
        void onMapReady(MAP map);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMarkerClickListener {
        void onClick(Object obj);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSnapshotReadyListener {
        void onSnapshotReady(Bitmap bitmap);
    }

    public ArrayList<double[]> getAllVisibleLocations(ArrayList<double[]> arrayList) {
        MapVisibleRegion visibleRegion = getVisibleRegion();
        if (visibleRegion == null) {
            Log.e(this.TAG, "getAllVisibleLocations fail");
            return null;
        }
        ArrayList<double[]> arrayList2 = new ArrayList<>();
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            if (visibleRegion.latLngBounds.contains(getGalleryMapLatLng(next[0], next[1]))) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public abstract MapLatLng getGalleryMapLatLng(double d, double d2);

    public abstract IMapMarker getGalleryMarker(Object obj);

    public abstract void getMapAsync(OnGalleryMapReadyListener<MAP> onGalleryMapReadyListener);

    public abstract View getView();

    public boolean isAllMarkerVisible(ArrayList<double[]> arrayList) {
        MapVisibleRegion visibleRegion = getVisibleRegion();
        if (visibleRegion == null) {
            Log.e(this.TAG, "isAllMarkerVisible fail");
            return false;
        }
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            if (!visibleRegion.latLngBounds.contains(getGalleryMapLatLng(next[0], next[1]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isThereAtLeastOneVisibleMarker(ArrayList<double[]> arrayList) {
        MapVisibleRegion visibleRegion = getVisibleRegion();
        if (visibleRegion == null) {
            Log.e(this.TAG, "isThereAtLeastOneVisibleMarker fail");
            return false;
        }
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            if (visibleRegion.latLngBounds.contains(getGalleryMapLatLng(next[0], next[1]))) {
                return true;
            }
        }
        return false;
    }

    public void moveCamera(double d, double d2) {
        moveCamera(d, d2, 13.0f);
    }

    public abstract void moveCamera(double d, double d2, float f);

    public abstract void moveCamera(MapLatLngBounds mapLatLngBounds, int i2);

    public void moveCameraToCenter(ArrayList<double[]> arrayList) {
        MapLatLngBounds.Builder builder = new MapLatLngBounds.Builder();
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            builder.include(getGalleryMapLatLng(next[0], next[1]));
        }
        moveCamera(MapPositionHelper.getExpandedBounds(builder.build()), 0);
    }

    public void onClusteredMapReady() {
        setZoomControlsEnabled(true);
    }

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onLowMemory();

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onStart();

    public abstract void onStop();

    public abstract void setAllGesturesEnabled(boolean z);

    public abstract void setMapOnClickListener(OnClickListener onClickListener);

    public abstract void setMarkerOnClickListener(OnMarkerClickListener onMarkerClickListener);

    public abstract void setOnCameraIdleListener(OnCameraIdleListener onCameraIdleListener);

    public abstract void setSnapShotCallback(OnSnapshotReadyListener onSnapshotReadyListener);

    public abstract void setZoomControlsEnabled(boolean z);
}
