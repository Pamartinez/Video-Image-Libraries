package com.samsung.android.gallery.module.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.PersistableBundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClipDataUtils {
    public static boolean getBooleanExtra(ClipData clipData, String str) {
        PersistableBundle bundle = getBundle(clipData);
        if (bundle == null || !bundle.getBoolean(str)) {
            return false;
        }
        return true;
    }

    private static PersistableBundle getBundle(ClipData clipData) {
        if (clipData == null || clipData.getDescription() == null || clipData.getDescription().getExtras() == null) {
            return null;
        }
        return clipData.getDescription().getExtras();
    }

    public static int getIntExtra(ClipData clipData, String str) {
        PersistableBundle bundle = getBundle(clipData);
        if (bundle != null) {
            return bundle.getInt(str);
        }
        return -1;
    }

    public static CharSequence getLabel(ClipDescription clipDescription) {
        if (clipDescription != null) {
            return clipDescription.getLabel();
        }
        return null;
    }

    public static Object getObjectExtra(ClipData clipData, String str) {
        PersistableBundle bundle = getBundle(clipData);
        if (bundle != null) {
            return bundle.get(str);
        }
        return null;
    }

    private static PersistableBundle getOrCreateBundle(ClipData clipData) {
        if (clipData == null || clipData.getDescription() == null) {
            return null;
        }
        PersistableBundle extras = clipData.getDescription().getExtras();
        if (extras != null) {
            return extras;
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        clipData.getDescription().setExtras(persistableBundle);
        return persistableBundle;
    }

    public static String getStringExtra(ClipData clipData, String str) {
        PersistableBundle bundle = getBundle(clipData);
        if (bundle != null) {
            return bundle.getString(str);
        }
        return null;
    }

    public static void putBooleanExtra(ClipData clipData, String str, boolean z) {
        PersistableBundle orCreateBundle = getOrCreateBundle(clipData);
        if (orCreateBundle != null) {
            orCreateBundle.putBoolean(str, z);
        }
    }

    public static void putStringExtra(ClipData clipData, String str, String str2) {
        PersistableBundle orCreateBundle = getOrCreateBundle(clipData);
        if (orCreateBundle != null) {
            orCreateBundle.putString(str, str2);
        }
    }
}
