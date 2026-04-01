package com.samsung.android.photoremaster.engine.estimator;

import android.content.Context;
import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.sdk.estimator.EstimatorResult;
import com.samsung.android.photoremaster.sdk.estimator.IEstimator;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWMoireEstimator implements IEstimator {
    private static final String TAG = "Remaster-VSWMoireEstimator";

    @Deprecated
    public static boolean estimate(byte[] bArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runMoireEstimator = VSWEngineNativeInterface.runMoireEstimator(bArr, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runMoireEstimator);
        return runMoireEstimator;
    }

    public boolean allowDownscaledInput(Context context) {
        return false;
    }

    public PrSize getRecommendedInputSize(Context context) {
        return new PrSize(TextToSpeechConst.MAX_SPEECH_INPUT, 3000);
    }

    public static boolean estimate(byte[] bArr, float[] fArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runMoireEstimatorWithScore = VSWEngineNativeInterface.runMoireEstimatorWithScore(bArr, fArr, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runMoireEstimatorWithScore);
        return runMoireEstimatorWithScore;
    }

    public void releaseResources() {
    }

    public void stop() {
    }

    public EstimatorResult estimate(Context context, BgrBuffer bgrBuffer, int i2, String str) {
        float[] fArr = new float[2];
        return new EstimatorResult(estimate(bgrBuffer.getData(), fArr, bgrBuffer.getSize().getWidth(), bgrBuffer.getSize().getHeight()), 1.0d, (double) fArr[1]);
    }
}
