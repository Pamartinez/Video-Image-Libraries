package com.samsung.android.sdk.pen.ocr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.os.SemSystemProperties;
import android.text.TextUtils;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenRecogConfig {
    public static final String OCR_RECOGNIZER_CONFIGURATION_KEY_ALLOW_MEMORY_OPTIMIZATION = "allowMemoryOptimization";
    public static final String OCR_RECOGNIZER_CONFIGURATION_KEY_ALLOW_PREFETCH_MODEL_OPTIMIZATION = "allowPrefetchModelOptimization";
    public static final String OCR_RECOGNIZER_CONFIGURATION_KEY_DEMOIRE = "deMoire";
    public static final String OCR_RECOGNIZER_CONFIGURATION_KEY_SYSTEM_LANGUAGE = "systemLanguage";
    public static final String OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE = "false";
    public static final String OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE = "true";
    private static final String TAG = "SpenRecogConfig";
    private String mLanguage;
    private SpenOrientation mOrientation;

    public SpenRecogConfig() {
        this.mOrientation = SpenOrientation.Rotation_0;
        this.mLanguage = SpenOcrLanguage.ENGLISH.toLanguageCode();
    }

    public static int getOneUIVersion() {
        try {
            String str = SemSystemProperties.get("ro.build.version.oneui");
            if (!TextUtils.isEmpty(str)) {
                Log.i(TAG, "OneUI = " + str);
                if (str.matches("[0-9]+")) {
                    return Integer.parseInt(str);
                }
                return 0;
            }
            Log.w(TAG, "Cannot get One UI version!");
            return 0;
        } catch (NumberFormatException e) {
            Log.e(TAG, "Cannot get OneUI : " + e.getMessage());
            return 0;
        }
    }

    public static int getProcessState(Context context) {
        int myPid = Process.myPid();
        Log.i(TAG, "getProcessState: myPid = " + myPid);
        for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (next.pid == myPid) {
                int i2 = next.importance;
                if (i2 == 400) {
                    Log.i(TAG, "getProcessState: Current process is in CACHED state.");
                } else if (i2 == 300) {
                    Log.i(TAG, "getProcessState: Current process is in SERVICE state.");
                } else if (i2 == 200) {
                    Log.i(TAG, "getProcessState: Current process is in VISIBLE state.");
                } else if (i2 == 100) {
                    Log.i(TAG, "getProcessState: Current process is in FOREGROUND state.");
                } else {
                    Log.i(TAG, "getProcessState: Unknown process state.");
                }
                return next.importance;
            }
        }
        return 0;
    }

    public static boolean isCachedStateProcess(Context context) {
        int processState = getProcessState(context);
        Log.i(TAG, "isCachedStateProcess: procState = " + processState);
        if (processState == 400) {
            return true;
        }
        return false;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public SpenOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public void setOrientation(SpenOrientation spenOrientation) {
        this.mOrientation = spenOrientation;
    }

    public SpenRecogConfig(String str) {
        this.mOrientation = SpenOrientation.Rotation_0;
        this.mLanguage = str;
    }
}
