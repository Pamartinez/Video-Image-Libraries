package com.samsung.android.photoremaster.engine.enhancer;

import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import t1.C0280e;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWFaceRestorationEnhancer {
    private static final String TAG = "Remaster-VSWFaceRestorationEnhancer";

    public static boolean enhance(byte[] bArr, byte[] bArr2, int i2, int i7, int i8) {
        String str = TAG;
        Log.d(str, "enhance - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runFaceRestorationEnhancer = VSWEngineNativeInterface.runFaceRestorationEnhancer(bArr, bArr2, i2, i7, i8);
        C0280e.g("result : ", str, str, "enhance - X", runFaceRestorationEnhancer);
        return runFaceRestorationEnhancer;
    }

    public static void stop() {
        VSWEngineNativeInterface.stopEnhancer();
    }
}
