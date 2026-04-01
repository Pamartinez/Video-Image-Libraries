package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import android.text.TextUtils;
import i.C0212a;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CameraUsbUri {
    public static String getDisplayName(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return C0212a.l("/DCIM/Camera/", str2);
        }
        if (str == null || !str.startsWith("content://com.sec.android.app.camera.FileProvider/external")) {
            return "/DCIM/Camera/";
        }
        try {
            return URLDecoder.decode(str.replace("content://com.sec.android.app.camera.FileProvider/external/", ""), StandardCharsets.UTF_8.toString());
        } catch (Exception unused) {
            return str;
        }
    }

    public static boolean match(String str) {
        if (str != null) {
            return str.startsWith("content://com.samsung.android.providers.otg/files") || str.startsWith("content://com.sec.android.app.camera.FileProvider/external");
        }
        return false;
    }

    public static boolean match(Uri uri) {
        return uri != null && match(uri.toString());
    }
}
