package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.telephony.TelephonyManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class NetworkUtils {
    private static final NetworkStateApi instance = new NetworkStateApiQ();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NetworkStateApi {
        public final ConnectivityManager getConnectivityManager(Context context) {
            return (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        }

        public abstract boolean isConnected(ConnectivityManager connectivityManager);

        public boolean isMobileHotspotConnected(Context context) {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            if (connectivityManager == null || !isWifiConnected(connectivityManager) || !connectivityManager.isActiveNetworkMetered()) {
                return false;
            }
            return true;
        }

        public boolean isNetworkConnected(Context context) {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            if (connectivityManager == null || !isConnected(connectivityManager)) {
                return false;
            }
            return true;
        }

        public boolean isWifiConnected(Context context) {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            return connectivityManager != null && isWifiConnected(connectivityManager);
        }

        public abstract boolean isWifiConnected(ConnectivityManager connectivityManager);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NetworkStateApiQ extends NetworkStateApi {
        public boolean isConnected(ConnectivityManager connectivityManager) {
            if (connectivityManager.getActiveNetwork() != null) {
                return true;
            }
            return false;
        }

        public boolean isWifiConnected(ConnectivityManager connectivityManager) {
            NetworkCapabilities networkCapabilities;
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            } else {
                networkCapabilities = null;
            }
            if (networkCapabilities == null || !networkCapabilities.hasTransport(1)) {
                return false;
            }
            return true;
        }
    }

    public static boolean isMobileHotspotConnected(Context context) {
        if (context == null || !instance.isMobileHotspotConnected(context)) {
            return false;
        }
        return true;
    }

    public static boolean isNetworkAvailable() {
        if (isNetworkConnected(AppResources.getAppContext()) || isWifiConnected(AppResources.getAppContext())) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context == null || !instance.isNetworkConnected(context)) {
            return false;
        }
        return true;
    }

    public static boolean isNetworkRoaming(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || (telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone")) == null || !telephonyManager.isNetworkRoaming()) {
            return false;
        }
        return true;
    }

    public static boolean isWifiConnected(Context context) {
        if (context == null || !instance.isWifiConnected(context)) {
            return false;
        }
        return true;
    }
}
