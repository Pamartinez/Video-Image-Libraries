package com.samsung.android.gallery.app.ui.map.staticmarker;

import android.view.View;
import c7.C0446b;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaticMarkerSearchMapFragment extends StaticMarkerMapFragment {
    /* access modifiers changed from: private */
    public void onClick() {
        try {
            ((View) getParentView().getParent()).callOnClick();
        } catch (ClassCastException | NullPointerException e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "fail to start map fragment : " + e.getMessage());
        }
    }

    public boolean isActivityToolbarSupported() {
        return true;
    }

    public void onMapReady(Object obj) {
        super.onMapReady(obj);
        this.mMapContainer.setSnapShotCallback(new C0446b(this));
        this.mMapContainer.setMapOnClickListener(new C0446b(this));
    }

    public void pauseContainer() {
        MapContainer mapContainer;
        if (!isDestroyed() && (mapContainer = this.mMapContainer) != null) {
            mapContainer.onPause();
        }
    }
}
