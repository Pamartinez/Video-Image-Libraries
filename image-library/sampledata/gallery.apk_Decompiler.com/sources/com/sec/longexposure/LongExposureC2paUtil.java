package com.sec.longexposure;

import N2.j;
import android.content.Context;
import android.os.DeadObjectException;
import android.util.Log;
import ce.a;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClient;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifest;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paResult;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LongExposureC2paUtil {
    private static boolean hasC2pa = false;

    public static void copyC2paManifest(Context context, String str, String str2) {
        if (!isC2paAvailable()) {
            return;
        }
        if (!hasC2pa) {
            Log.d("LongExposureC2paUtil", "Do not copy C2pa, hasC2pa:" + hasC2pa);
            return;
        }
        Log.i("LongExposureC2paUtil", "Copy C2pa Manifest to dstPath: " + str);
        hasC2pa = false;
        manifestToFile(str, str2, AiServices.getC2PAClient(context.getApplicationContext()));
        Log.d("LongExposureC2paUtil", "copyC2paManifest done");
    }

    private static String getBrandName() {
        String string = SemFloatingFeature.getInstance().getString("SEC_FLOATING_FEATURE_SETTINGS_CONFIG_BRAND_NAME");
        j.w("getBrandName : ", string, "LongExposureC2paUtil");
        if (string == null || string.isEmpty()) {
            return "empty";
        }
        return string;
    }

    private static void handleException(Exception exc) {
        String str;
        if (exc != null) {
            if (exc instanceof DeadObjectException) {
                str = "service died";
            } else if (exc.getMessage() == null) {
                str = exc.toString();
            } else {
                str = exc.getMessage();
            }
            j.w("Failed to generate: ", str, "LongExposureC2paUtil");
        }
    }

    public static void hasC2paManifest(Context context, String str) {
        if (isC2paAvailable()) {
            C2paClient c2PAClient = AiServices.getC2PAClient(context.getApplicationContext());
            c2PAClient.isC2paExist(str).addOnCompleteListener(new a(c2PAClient, 0));
            Log.d("LongExposureC2paUtil", "hasC2paManifest done");
        }
    }

    public static boolean isC2paAvailable() {
        return isFromOneUIVersion70();
    }

    public static boolean isFromOneUIVersion70() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$hasC2paManifest$0(C2paClient c2paClient, Task task) {
        hasC2pa = false;
        if (task.isSuccessful()) {
            Boolean bool = (Boolean) task.getResult();
            if (bool == null || !bool.booleanValue()) {
                Log.d("LongExposureC2paUtil", "C2PA not exist");
            } else {
                Log.d("LongExposureC2paUtil", "C2PA exist");
                hasC2pa = true;
            }
        } else {
            handleException(task.getException());
        }
        releaseC2paClient(c2paClient);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$manifestToFile$1(C2paClient c2paClient, Task task) {
        if (task.isSuccessful()) {
            C2paResult c2paResult = (C2paResult) task.getResult();
            if (c2paResult == null) {
                Log.d("LongExposureC2paUtil", "C2PA result is null");
            } else if (c2paResult.isSuccess()) {
                Log.d("LongExposureC2paUtil", "OnSuccess");
            } else {
                Log.d("LongExposureC2paUtil", "OnError: " + c2paResult.getError());
            }
        } else {
            handleException(task.getException());
        }
        releaseC2paClient(c2paClient);
    }

    private static void manifestToFile(String str, String str2, C2paClient c2paClient) {
        c2paClient.embedManifestToFile(new C2paManifest.Builder().claimGenerator(getBrandName()).build(), str, str2, (List<String>) null).addOnCompleteListener(new a(c2paClient, 1));
    }

    private static void releaseC2paClient(C2paClient c2paClient) {
        c2paClient.release();
    }
}
