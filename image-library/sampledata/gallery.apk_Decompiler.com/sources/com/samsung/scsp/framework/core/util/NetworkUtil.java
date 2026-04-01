package com.samsung.scsp.framework.core.util;

import A4.Y;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NetworkUtil {
    private static boolean isConnected(Context context, int i2) {
        return ((Boolean) FaultBarrier.get(new Y((Object) context, i2, 5), Boolean.FALSE).obj).booleanValue();
    }

    public static boolean isEthernetConnected(Context context) {
        return isConnected(context, 3);
    }

    public static boolean isMobileConnected(Context context) {
        return isConnected(context, 0);
    }

    private static boolean isWiFiConnected(Context context) {
        return isConnected(context, 1);
    }

    public static boolean isWifiOrEthernetConnected(Context context) {
        if (isWiFiConnected(context) || isEthernetConnected(context)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isConnected$0(Context context, int i2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            return Boolean.valueOf(networkCapabilities.hasTransport(i2));
        }
        return Boolean.FALSE;
    }
}
