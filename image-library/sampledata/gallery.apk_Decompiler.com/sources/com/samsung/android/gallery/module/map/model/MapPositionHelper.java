package com.samsung.android.gallery.module.map.model;

import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MapPositionHelper {
    public static MapLatLngBounds getExpandedBounds(MapLatLngBounds mapLatLngBounds) {
        boolean z;
        double d;
        if (mapLatLngBounds == null) {
            return null;
        }
        MapLatLng mapLatLng = mapLatLngBounds.northeast;
        double d2 = mapLatLng.longitude;
        MapLatLng mapLatLng2 = mapLatLngBounds.southwest;
        double d3 = mapLatLng2.longitude;
        if (d2 < d3) {
            z = true;
        } else {
            z = false;
        }
        double d5 = mapLatLng.latitude;
        double d6 = d5 - mapLatLng2.latitude;
        if (!z) {
            d = d2 - d3;
        } else {
            d = (180.0d - d3) + d2 + 180.0d;
        }
        double d7 = d6 * 0.15d;
        double d9 = d * 0.15d;
        MapLatLng mapLatLng3 = new MapLatLng(d5 + d7, d2 + d9);
        MapLatLng mapLatLng4 = mapLatLngBounds.southwest;
        return new MapLatLngBounds(new MapLatLng(mapLatLng4.latitude - d7, mapLatLng4.longitude - d9), mapLatLng3);
    }

    public static MapLatLngBounds getLargeVisibleRegion(MapVisibleRegion mapVisibleRegion) {
        double d;
        MapLatLngBounds mapLatLngBounds = mapVisibleRegion.latLngBounds;
        double abs = Math.abs(mapLatLngBounds.northeast.latitude - mapLatLngBounds.southwest.latitude);
        MapLatLngBounds mapLatLngBounds2 = mapVisibleRegion.latLngBounds;
        double abs2 = Math.abs(mapLatLngBounds2.northeast.longitude - mapLatLngBounds2.southwest.longitude);
        double d2 = (2.0d * abs) - abs2;
        double d3 = 0.1d;
        if (d2 < MapUtil.INVALID_LOCATION) {
            d = 0.15d;
        } else {
            d = 0.1d;
        }
        double d5 = abs * d;
        if (d2 >= MapUtil.INVALID_LOCATION) {
            d3 = 0.15d;
        }
        double d6 = abs2 * d3;
        MapLatLng mapLatLng = mapVisibleRegion.latLngBounds.southwest;
        MapLatLng latLng = getLatLng(mapLatLng.latitude - d5, mapLatLng.longitude - d6);
        MapLatLng mapLatLng2 = mapVisibleRegion.latLngBounds.northeast;
        return new MapLatLngBounds(latLng, getLatLng(mapLatLng2.latitude + d5, mapLatLng2.longitude + d6));
    }

    private static MapLatLng getLatLng(double d, double d2) {
        if (-180.0d > d2 || d2 >= 180.0d) {
            d2 = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        }
        return new MapLatLng(Math.max(-90.0d, Math.min(90.0d, d)), d2);
    }

    public static MapLatLng getTargetLatLng(float f, double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr2[0];
        double d3 = (double) f;
        double d5 = ((d - d2) * d3) + d2;
        double d6 = dArr[1] - dArr2[1];
        if (Math.abs(d6) > 180.0d) {
            d6 -= Math.signum(d6) * 360.0d;
        }
        return new MapLatLng(d5, (d6 * d3) + dArr2[1]);
    }

    public static MapLatLng toLatLng(double[] dArr) {
        return new MapLatLng(dArr[0], dArr[1]);
    }
}
