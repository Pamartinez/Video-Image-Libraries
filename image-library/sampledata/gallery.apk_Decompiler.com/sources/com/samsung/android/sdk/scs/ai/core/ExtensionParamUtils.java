package com.samsung.android.sdk.scs.ai.core;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ExtensionParamUtils {
    private static final String TAG = "ExtensionParamUtils";

    private static Bitmap asSharedCompat(Bitmap bitmap) {
        if (!bitmap.isMutable()) {
            return bitmap;
        }
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return bitmap.copy(config, false);
    }

    public static String getGenericInferenceMethod(Bundle bundle) {
        return bundle.getString("genericInferenceMethod");
    }

    public static void setBundleFeature(Bundle bundle, String str) {
        bundle.putString("feature", str);
    }

    public static void setBundleInputArg(Bundle bundle, String str) {
        bundle.putString("inputArg", str);
    }

    public static void setBundleTaskId(Bundle bundle, String str) {
        bundle.putString("taskId", str);
    }

    public static void transformBitmapsInBundle(Bundle bundle) {
        Bitmap bitmap;
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            if (obj instanceof Bitmap) {
                Bitmap bitmap2 = (Bitmap) obj;
                try {
                    if (Build.VERSION.SDK_INT >= 31) {
                        bitmap = bitmap2.asShared();
                    } else {
                        bitmap = asSharedCompat(bitmap2);
                    }
                    bundle.putParcelable(next, bitmap);
                } catch (Exception e) {
                    Log.e(TAG, "Failed to create shared Bitmap", e);
                }
            }
        }
    }
}
