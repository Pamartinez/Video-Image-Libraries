package com.samsung.android.photoremaster.engine.estimator;

import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import t1.C0280e;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWLowLightEstimator {
    private static final String TAG = "Remaster-VSWLowLightEstimator";

    public static boolean estimate(byte[] bArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runLowLightEstimator = VSWEngineNativeInterface.runLowLightEstimator(bArr, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runLowLightEstimator);
        return runLowLightEstimator;
    }

    public static void stop() {
    }
}
