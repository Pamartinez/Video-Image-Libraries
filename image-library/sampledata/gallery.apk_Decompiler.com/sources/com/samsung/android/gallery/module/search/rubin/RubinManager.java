package com.samsung.android.gallery.module.search.rubin;

import N2.j;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.window.embedding.c;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RubinManager {
    private static final Uri RUBIN_CLIENT_AUTHORITY_URI = Uri.parse("content://com.samsung.android.rubin.state");

    public static String getRubinState(Context context) {
        String str;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle call = context.getContentResolver().call(RUBIN_CLIENT_AUTHORITY_URI, "getRubinState", "com.samsung.storyservice", (Bundle) null);
            String str2 = "null";
            if (call != null) {
                str2 = call.getString("currentRubinState", "");
                str = getSummaryText(context, str2, call.getBoolean("isEnabledInSupportedApps", false));
            } else {
                str = "";
            }
            Log.d("RubinManager", "getRubinState {" + str2 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            return str;
        } catch (IllegalArgumentException | NullPointerException e) {
            Log.e("RubinManager", "getRubinState failed. e=" + e.getMessage());
            return "";
        }
    }

    private static String getSummaryText(Context context, String str, boolean z) {
        int i2;
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2019634887:
                if (str.equals("USER_NOT_ENABLE_RUBIN_IN_DEVICE")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1298383029:
                if (str.equals("CRITICAL_UPDATE_NEEDED")) {
                    c5 = 1;
                    break;
                }
                break;
            case 2524:
                if (str.equals("OK")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return context.getString(R$string.customization_service_status_off);
            case 1:
            case 2:
                if (z) {
                    i2 = R$string.customization_service_status_on;
                } else {
                    i2 = R$string.customization_service_status_off;
                }
                return context.getString(i2);
            default:
                return context.getString(R$string.not_in_use);
        }
    }

    public static boolean isRubinEnabled(Context context) {
        boolean z;
        ThreadUtil.assertBgThread("retrieve rubin state only on worker thread");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Bundle call = context.getContentResolver().call(RUBIN_CLIENT_AUTHORITY_URI, "getRubinState", "gallery", (Bundle) null);
            if (call == null || !"OK".equals(call.getString("currentRubinState", ""))) {
                z = false;
            } else {
                z = true;
            }
            Log.d("RubinManager", "isRubinEnabled {" + z + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            return z;
        } catch (IllegalArgumentException | NullPointerException | SecurityException e) {
            Log.e("RubinManager", "isRubinEnabled failed e=" + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadRubinState$0(Consumer consumer, String str) {
        try {
            consumer.accept(str);
        } catch (Exception unused) {
        }
    }

    public static void loadRubinState(Context context, Consumer<String> consumer) {
        ThreadUtil.postOnBgThread(new c(14, context, consumer));
    }

    public static boolean start(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.rubin.CS_SETTINGS");
            intent.putExtra("targetPage", 2);
            intent.putExtra("uiPackageName", "com.sec.android.gallery3d");
            intent.putExtra(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, "com.samsung.storyservice");
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            j.D(e, new StringBuilder("start failed e="), "RubinManager");
            return false;
        }
    }
}
