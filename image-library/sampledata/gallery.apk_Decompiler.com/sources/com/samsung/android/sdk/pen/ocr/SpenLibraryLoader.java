package com.samsung.android.sdk.pen.ocr;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import c0.C0086a;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenLibraryLoader {
    private static final String LIB_EXT_SO = ".so";
    private static final String LIB_PREFIX = "lib";
    public static final String SOCR_PROVIDER_PACKAGE_NAME = "com.samsung.android.sdk.ocr";
    private static final String TAG = "SpenLibraryLoader";
    private static final Map<LibType, List<String>> libMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LibType {
        OCR,
        Moire
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(LibType.OCR, Arrays.asList(new String[]{"SDKonnxruntime.spenocr.samsung", "SDKRecognitionOCR.spenocr.samsung"}));
        hashMap.put(LibType.Moire, Arrays.asList(new String[]{"tensorflowlite_c.spenocr.samsung", "SDKMoireDetector.spenocr.samsung"}));
        libMap = Collections.unmodifiableMap(hashMap);
    }

    public static String getMainLibName(LibType libType) {
        List list = libMap.get(libType);
        if (list == null || list.size() <= 0) {
            return "";
        }
        return (String) C0086a.f(1, list);
    }

    private static boolean loadLibraries(String str, List<String> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (String str2 : list) {
                        String str3 = LIB_PREFIX + str2 + LIB_EXT_SO;
                        String str4 = str + File.separator + str3;
                        Log.d(TAG, "loadLibraries : " + str4);
                        if (new File(str4).exists()) {
                            System.load(str4);
                        } else {
                            Log.i(TAG, "loadLibraries : " + str3 + " does not exist");
                            return false;
                        }
                    }
                    return true;
                }
            } catch (UnsatisfiedLinkError e) {
                Log.e(TAG, "loadLibraries : Cannot load remote libs : " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    public static boolean loadRemoteLibrary(Context context, LibType libType) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.samsung.android.sdk.ocr", 0);
            Log.d(TAG, "loadRemoteLibrary : " + applicationInfo.nativeLibraryDir);
            return loadLibraries(applicationInfo.nativeLibraryDir, libMap.get(libType));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "loadRemoteLibrary : Cannot find remote lib : " + e.getLocalizedMessage());
            return false;
        }
    }
}
