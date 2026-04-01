package com.samsung.android.gallery.app.ui.map.google;

import K1.e;
import M1.c;
import M1.d;
import android.graphics.Bitmap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.samsung.android.gallery.module.map.model.MapLatLng;
import com.samsung.android.gallery.module.map.model.MapLatLngBounds;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class GoogleModelConverter {
    private static c createMarkerOptions(double[] dArr, Bitmap bitmap) {
        c cVar = new c();
        cVar.d = new LatLng(dArr[0], dArr[1]);
        cVar.g = C0246a.U(bitmap);
        cVar.f418h = 0.5f;
        cVar.f419i = 0.85f;
        return cVar;
    }

    public static LatLng getLatLng(MapLatLng mapLatLng) {
        return new LatLng(mapLatLng.latitude, mapLatLng.longitude);
    }

    public static LatLngBounds getLatLngBounds(MapLatLngBounds mapLatLngBounds) {
        return new LatLngBounds(getLatLng(mapLatLngBounds.southwest), getLatLng(mapLatLngBounds.northeast));
    }

    public static MapLatLng getMapLatLng(LatLng latLng) {
        return new MapLatLng(latLng.d, latLng.e);
    }

    public static MapLatLngBounds getMapLatLngBounds(d dVar) {
        return new MapLatLngBounds(getMapLatLng(dVar.f423h.d), getMapLatLng(dVar.f423h.e));
    }

    public static GoogleMapMarker getMarker(e eVar, double[] dArr, Bitmap bitmap) {
        return new GoogleMapMarker(eVar.a(createMarkerOptions(dArr, bitmap)));
    }
}
