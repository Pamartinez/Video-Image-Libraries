package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.pen.ocr.SpenIOcrRecognizer;
import com.samsung.android.sdk.pen.ocr.SpenOcrEngine;
import com.samsung.android.sdk.pen.ocr.SpenOcrLanguage;
import com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerUtils {
    private static final String SOCR_PROVIDER_PACKAGE_NAME = "com.samsung.android.sdk.ocr";
    private static final String TAG = "RecognizerUtils";

    private static int getVersionNumberFrom(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        String[] split = str.split("\\.");
        if (split.length > 1) {
            return Integer.parseInt(split[1]) + (Integer.parseInt(split[0]) * 100);
        }
        Log.e(TAG, "Unable to split version name : ".concat(str));
        return i2;
    }

    public static int getVersionOfNativeLibInDevice() {
        SpenOcrEngine spenOcrEngine = new SpenOcrEngine();
        String engineVersion = spenOcrEngine.getEngineVersion();
        Log.i(TAG, "native so version : " + engineVersion);
        int versionNumberFrom = getVersionNumberFrom(engineVersion, 101);
        spenOcrEngine.close();
        Log.i(TAG, "version number = " + versionNumberFrom);
        return versionNumberFrom;
    }

    public static int getVersionOfNativeLibInOneUI41Device(Context context) {
        SpenOcrEngine spenOcrEngine = new SpenOcrEngine(context, SpenOcrModelLoaderFactory.MODEL_LOADER.ONEUI41);
        SpenOcrLanguage spenOcrLanguage = SpenOcrLanguage.ENGLISH;
        int i2 = 100;
        if (spenOcrEngine.isSupportedLanguage(spenOcrLanguage)) {
            SpenIOcrRecognizer createRecognizer = spenOcrEngine.createRecognizer();
            if (createRecognizer == null) {
                Log.e(TAG, "Fails to create recognizer");
            } else {
                i2 = getVersionNumberFrom(createRecognizer.getVersion(), 100);
                createRecognizer.close();
            }
        } else {
            String name = spenOcrLanguage.name();
            Log.w(TAG, "Does not support language(" + name + ")");
        }
        spenOcrEngine.close();
        Log.i(TAG, "version number = " + i2);
        return i2;
    }

    public static int getVersionOfNativeLibInServiceProvider(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.samsung.android.sdk.ocr", 128);
            Log.i(TAG, "OCRDataProvider Version = " + packageInfo.versionName + ", Version Code = " + packageInfo.versionCode);
            return getVersionNumberFrom(packageInfo.versionName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "OCRDataProvider is not found");
            return 0;
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
            return 0;
        }
    }
}
