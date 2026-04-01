package com.samsung.android.photoremaster.engine.enhancer;

import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import t1.C0280e;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWUnifiedEnhancer {
    private static final String TAG = "Remaster-VSWUnifiedEnhancer";

    public static boolean enhance(byte[] bArr, byte[] bArr2, int i2, int i7, int i8) {
        String str = TAG;
        Log.d(str, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runUnifiedEnhancer = VSWEngineNativeInterface.runUnifiedEnhancer(bArr, bArr2, i2, i7, i8);
        C0280e.g("result : ", str, str, "enhance - X", runUnifiedEnhancer);
        return runUnifiedEnhancer;
    }

    public static boolean enhanceDenoiseDeblur(byte[] bArr, byte[] bArr2, int i2, int i7) {
        return enhance(bArr, bArr2, i2, i7, 1);
    }

    public static boolean enhanceUpscaleDeblur(byte[] bArr, byte[] bArr2, int i2, int i7, int i8) {
        if (bArr2.length == i7 * i8 * i2 * i8 * 3) {
            return enhance(bArr, bArr2, i2, i7, i8);
        }
        throw new NegativeArraySizeException();
    }

    public static void stop() {
        VSWEngineNativeInterface.stopEnhancer();
    }
}
