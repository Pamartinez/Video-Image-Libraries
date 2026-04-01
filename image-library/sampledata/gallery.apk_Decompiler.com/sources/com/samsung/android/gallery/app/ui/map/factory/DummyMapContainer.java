package com.samsung.android.gallery.app.ui.map.factory;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import com.samsung.android.gallery.module.map.model.MapVisibleRegion;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DummyMapContainer<T> extends MapContainer<T> {
    public MapLatLng getGalleryMapLatLng(double d, double d2) {
        return new MapLatLng(MapUtil.INVALID_LOCATION, MapUtil.INVALID_LOCATION);
    }

    public IMapMarker getGalleryMarker(Object obj) {
        return EmptyMapFactory.I_MAP_MARKER;
    }

    public T getMapInstance() {
        return null;
    }

    public double getMapZoom() {
        return MapUtil.INVALID_LOCATION;
    }

    public View getView() {
        return null;
    }

    public MapVisibleRegion getVisibleRegion() {
        return null;
    }

    public boolean hasMap() {
        return false;
    }

    public void moveCamera(double d, double d2, float f) {
    }

    public void moveCamera(MapLatLngBounds mapLatLngBounds, int i2) {
    }

    public void onDestroy() {
    }

    public void onLowMemory() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void getMapAsync(MapContainer.OnGalleryMapReadyListener<T> onGalleryMapReadyListener) {
    }

    public void handleDensityChanged(Context context) {
    }

    public void onCreate(Bundle bundle) {
    }

    public void setAllGesturesEnabled(boolean z) {
    }

    public void setMapOnClickListener(MapContainer.OnClickListener onClickListener) {
    }

    public void setMarkerOnClickListener(MapContainer.OnMarkerClickListener onMarkerClickListener) {
    }

    public void setOnCameraIdleListener(MapContainer.OnCameraIdleListener onCameraIdleListener) {
    }

    public void setSnapShotCallback(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
    }

    public void setZoomControlsEnabled(boolean z) {
    }

    public void setZoomLevel(float f) {
    }
}
