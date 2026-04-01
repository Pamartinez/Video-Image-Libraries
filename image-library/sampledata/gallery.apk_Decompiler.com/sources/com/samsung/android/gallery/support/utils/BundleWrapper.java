package com.samsung.android.gallery.support.utils;

import A.a;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BundleWrapper {
    private final Bundle mBundle;

    public BundleWrapper(Bundle bundle) {
        this.mBundle = bundle;
    }

    public static boolean getBoolean(Bundle bundle, String str) {
        return getBoolean(bundle, str, false);
    }

    public static double getDouble(Bundle bundle, String str) {
        return getDouble(bundle, str, MapUtil.INVALID_LOCATION);
    }

    public static int getInt(Bundle bundle, String str) {
        return getInt(bundle, str, 0);
    }

    public static String getKey(Bundle bundle) {
        return bundle.getString("_SUBSCRIBE_KEY");
    }

    public static long getLong(Bundle bundle, String str, long j2) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return j2;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            a.u("getInt wrong value=", string, "BundleWrapper");
            return j2;
        }
    }

    public static String getString(Bundle bundle, String str, String str2) {
        String string = bundle.getString(str);
        return TextUtils.isEmpty(string) ? str2 : string;
    }

    public static boolean getBoolean(Bundle bundle, String str, boolean z) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return z;
        }
        return string.equalsIgnoreCase(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public static double getDouble(Bundle bundle, String str, double d) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return d;
        }
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException unused) {
            a.u("getDouble wrong value=", string, "BundleWrapper");
            return d;
        }
    }

    public static int getInt(Bundle bundle, String str, int i2) {
        String string = bundle.getString(str);
        if (TextUtils.isEmpty(string)) {
            return i2;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            a.u("getInt wrong value=", string, "BundleWrapper");
            return i2;
        }
    }

    public String getString(String str, String str2) {
        return getString(this.mBundle, str, str2);
    }

    public boolean getBoolean(String str) {
        return getBoolean(this.mBundle, str, false);
    }

    public long getLong(String str, long j2) {
        return getLong(this.mBundle, str, j2);
    }

    public int getInt(String str) {
        return getInt(this.mBundle, str, -1);
    }

    public long getLong(String str) {
        return getLong(this.mBundle, str, -1);
    }
}
