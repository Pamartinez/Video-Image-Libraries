package com.samsung.android.gallery.module.remote;

import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SConnectUtil {
    public static String getDeviceId(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra("DEVICE");
        }
        return null;
    }

    public static ArrayList<Uri> getUris(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
    }

    public static boolean isSConnectIntent(Intent intent) {
        return intent != null && "com.samsung.android.sconnect.action.IMAGE_DMR".equals(intent.getAction());
    }

    public static boolean isSConnectIntent(String str) {
        return "com.samsung.android.sconnect.action.IMAGE_DMR".equals(str);
    }
}
