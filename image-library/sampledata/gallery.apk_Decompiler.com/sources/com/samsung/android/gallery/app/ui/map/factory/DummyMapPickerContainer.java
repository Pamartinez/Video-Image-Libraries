package com.samsung.android.gallery.app.ui.map.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DummyMapPickerContainer<T> extends MapPickerContainer<T> {
    public DummyMapPickerContainer(Context context) {
        super(context);
    }

    public ISimpleMarker addMarker(Object obj, double[] dArr, Bitmap bitmap, String str) {
        return null;
    }

    public T getMap() {
        return null;
    }

    public View getView() {
        return null;
    }

    public void moveCamera() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void setInfoWindowAdapter(Object obj) {
    }

    public void setMap(Object obj) {
    }

    public void setMapListeners(Object obj) {
    }

    public void requestCurrentLocationUpdate(Context context, Runnable runnable) {
    }

    public void setCompassEnabled(Object obj, boolean z) {
    }

    public void setRotateGesturesEnabled(Object obj, boolean z) {
    }

    public void setZoomControlsEnabled(Object obj, boolean z) {
    }
}
