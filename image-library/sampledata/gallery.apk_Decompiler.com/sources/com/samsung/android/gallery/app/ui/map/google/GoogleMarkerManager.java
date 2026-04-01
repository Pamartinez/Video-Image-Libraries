package com.samsung.android.gallery.app.ui.map.google;

import K1.e;
import android.content.Context;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.map.abstraction.IMap;
import com.samsung.android.gallery.module.map.abstraction.IMapMarker;
import com.samsung.android.gallery.module.map.abstraction.MarkerManager;
import com.samsung.android.gallery.module.map.model.MapPositionHelper;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleMarkerManager<MAP extends IMap<e>> extends MarkerManager<MAP> {
    public GoogleMarkerManager(Context context, MAP map) {
        super(context, map);
    }

    public void animateMarker(MarkerWithPosition markerWithPosition, float f, double[] dArr, double[] dArr2, float f5) {
        IMapMarker marker = markerWithPosition.getMarker();
        marker.setPosition(MapPositionHelper.getTargetLatLng(f, dArr, dArr2));
        marker.setAlpha(f5);
    }

    public String tag() {
        return "GoogleMarkerManager";
    }

    public GoogleMapMarker addMarker(double[] dArr, Bitmap bitmap) {
        MAP map = this.mMap;
        if (map == null) {
            Log.e(this.TAG, "Map is null; fail to add marker");
            return null;
        }
        e eVar = (e) map.getMapInstance();
        if (eVar == null) {
            return null;
        }
        return GoogleModelConverter.getMarker(eVar, dArr, bitmap);
    }
}
