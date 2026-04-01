package com.samsung.android.sdk.scs.ai.visual.c2pa;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paUtils {
    private static final String TAG = "C2paUtils";

    public static String getFileExtension(String str) {
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            String str2 = TAG;
            Log.e(str2, "getFileExtension: File does not exist or cannot be read/written:" + str);
            return null;
        }
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf >= 0 && lastIndexOf < name.length() - 1) {
            return name.substring(lastIndexOf + 1);
        }
        Log.e(TAG, "getFileExtension: Unable to find extension".concat(name));
        return null;
    }

    public static ParcelFileDescriptor getParcelFileDescriptor(String str) {
        try {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                if (file.canWrite()) {
                    return ParcelFileDescriptor.open(file, 805306368);
                }
            }
            String str2 = TAG;
            Log.e(str2, "File does not exist or cannot be read/written: " + str);
            return null;
        } catch (SecurityException unused) {
            String str3 = TAG;
            Log.e(str3, "SecurityException Occurred in getParcelFileDescriptor: " + str);
            return null;
        } catch (IOException unused2) {
            String str4 = TAG;
            Log.e(str4, "IOException Occurred in getParcelFileDescriptor: " + str);
            return null;
        }
    }
}
