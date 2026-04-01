package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapUtil {
    private static final String A_MAP_CACHE_VERSION = " AM:6.0.0";
    public static final float DEFAULT_ZOOM_LEVEL = 13.0f;
    public static final float DEFAULT_ZOOM_LEVEL_FOR_SEARCH = 14.0f;
    private static final String GOOGLE_MAP_CACHE_VERSION = " GM:19.0.0";
    public static final double INVALID_LOCATION = 0.0d;
    public static final float MAX_ZOOM_LEVEL = 21.0f;
    public static final float MIN_ZOOM_LEVEL = 2.0f;
    private static final String TAG = "MapUtil";
    static final boolean USE_AMAP = Features.isEnabled(Features.IS_CHINESE_DEVICE);
    public static final double ZOOM_VALUE_NOT_SPECIFIED = -1.0d;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnLocationChanged {
        void onLocationChanged(Location location);
    }

    private static String attachVersion(String str) {
        String str2;
        StringBuilder s = C0212a.s(str);
        if (USE_AMAP) {
            str2 = A_MAP_CACHE_VERSION;
        } else {
            str2 = GOOGLE_MAP_CACHE_VERSION;
        }
        s.append(str2);
        return s.toString();
    }

    public static double[] getCurrentLocation(Context context) {
        Location location;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager != null && locationManager.isProviderEnabled("network")) {
            location = locationManager.getLastKnownLocation("network");
        } else if (locationManager == null || !locationManager.isProviderEnabled("gps")) {
            Log.e(TAG, "getCurrentLocation failed. provider not available");
            location = null;
        } else {
            location = locationManager.getLastKnownLocation("gps");
        }
        if (location == null) {
            return new double[]{INVALID_LOCATION, INVALID_LOCATION};
        }
        return new double[]{location.getLatitude(), location.getLongitude()};
    }

    public static String getDiskCacheKey(double d, double d2, int i2, int i7) {
        Locale locale = Locale.ENGLISH;
        return attachVersion(String.format(locale, "MAP: %.4f %.4f %d %d " + Locale.getDefault(), new Object[]{Double.valueOf(d), Double.valueOf(d2), Integer.valueOf(i2), Integer.valueOf(i7)}));
    }

    public static String getSearchDiskCacheKey(double d, double d2, int i2, int i7) {
        Locale locale = Locale.ENGLISH;
        return attachVersion(String.format(locale, "MAP@SEARCH %.4f %.4f %d %d " + Locale.getDefault(), new Object[]{Double.valueOf(d), Double.valueOf(d2), Integer.valueOf(i2), Integer.valueOf(i7)}));
    }

    public static boolean isValidLocation(double d, double d2) {
        if (!Double.isNaN(INVALID_LOCATION / d) || !Double.isNaN(INVALID_LOCATION / d2)) {
            return true;
        }
        return false;
    }

    public static void requestCurrentLocation(Context context, final OnLocationChanged onLocationChanged) {
        AnonymousClass1 r0 = new LocationListener() {
            public void onLocationChanged(Location location) {
                OnLocationChanged onLocationChanged = OnLocationChanged.this;
                if (onLocationChanged != null) {
                    onLocationChanged.onLocationChanged(location);
                }
            }

            public void onProviderDisabled(String str) {
            }

            public void onProviderEnabled(String str) {
            }

            public void onStatusChanged(String str, int i2, Bundle bundle) {
            }
        };
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager != null && locationManager.isProviderEnabled("network")) {
            locationManager.requestSingleUpdate("network", r0, (Looper) null);
        } else if (locationManager == null || !locationManager.isProviderEnabled("gps")) {
            Log.e(TAG, "requestCurrentLocation failed. provider not available");
        } else {
            locationManager.requestSingleUpdate("gps", r0, (Looper) null);
        }
    }
}
