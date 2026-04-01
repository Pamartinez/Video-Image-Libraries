package com.samsung.android.gallery.support.utils;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppResources {
    private static volatile Context sAppContext;

    public static Context getAppContext() {
        return sAppContext;
    }

    public static boolean getBoolean(int i2) {
        if (sAppContext == null || !sAppContext.getResources().getBoolean(i2)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r5v7, types: [java.lang.Object, java.util.function.Function] */
    public static String getCallingPackageCompat(Activity activity, Supplier<Intent> supplier) {
        Intent intent;
        String callingPackage = activity.getCallingPackage();
        if (callingPackage == null) {
            try {
                if (Build.VERSION.SDK_INT >= 35) {
                    return (String) Optional.ofNullable(activity.getCaller()).map(new Object()).orElse((Object) null);
                }
                if (supplier != null) {
                    intent = supplier.get();
                } else {
                    intent = null;
                }
                if (intent == null || (intent.getParcelableExtra("android.intent.extra.REFERRER") == null && intent.getStringExtra("android.intent.extra.REFERRER_NAME") == null)) {
                    return (String) Optional.ofNullable(activity.getReferrer()).map(new C0670h(8)).orElse((Object) null);
                }
            } catch (Error | Exception unused) {
            }
        }
        return callingPackage;
    }

    public static int getColor(int i2) {
        if (sAppContext != null) {
            return sAppContext.getColor(i2);
        }
        return -16777216;
    }

    public static String getConfigPath() {
        try {
            return sAppContext.getExternalFilesDir(".config").getAbsolutePath();
        } catch (Exception unused) {
            Log.w("AppResources", "getConfigPath failed");
            return "/storage/emulated/0/Android/data/com.sec.android.gallery3d/files/.config";
        }
    }

    public static int getInteger(int i2) {
        if (sAppContext != null) {
            return sAppContext.getResources().getInteger(i2);
        }
        return 0;
    }

    public static String getLanguage() {
        try {
            return sAppContext.getResources().getConfiguration().getLocales().get(0).getLanguage();
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLanguage failed e="), "AppResources");
            return "en";
        }
    }

    public static String getLanguageCountry() {
        try {
            Locale locale = sAppContext.getResources().getConfiguration().getLocales().get(0);
            return locale.getLanguage() + "_" + locale.getCountry();
        } catch (Exception e) {
            a.s(e, new StringBuilder("getLanguage failed e="), "AppResources");
            return "en";
        }
    }

    public static String getQuantityString(int i2, int i7, Object... objArr) {
        try {
            if (sAppContext != null) {
                return sAppContext.getResources().getQuantityString(i2, i7, objArr);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String getSecTheme() {
        try {
            return Settings.System.getString(sAppContext.getContentResolver(), "current_sec_active_themepackage");
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getString(int i2) {
        try {
            if (sAppContext != null) {
                return sAppContext.getString(i2);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static void setAppContext(Context context) {
        if (sAppContext == null) {
            sAppContext = context;
        }
    }

    public static String getString(int i2, Object... objArr) {
        try {
            if (sAppContext != null) {
                return sAppContext.getResources().getString(i2, objArr);
            }
        } catch (Exception unused) {
        }
        return "";
    }
}
