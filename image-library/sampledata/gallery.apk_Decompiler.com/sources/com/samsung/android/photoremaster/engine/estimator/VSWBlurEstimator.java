package com.samsung.android.photoremaster.engine.estimator;

import android.content.Context;
import android.util.Log;
import com.samsung.android.photoremaster.engine.nativeInterface.VSWEngineNativeInterface;
import com.samsung.android.photoremaster.engine.result.EstimatedResult;
import com.samsung.android.photoremaster.sdk.estimator.EstimatorResult;
import com.samsung.android.photoremaster.sdk.estimator.IEstimator;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWBlurEstimator implements IEstimator {
    public static final float CRETERIA_BLUR_HIGH = 0.51f;
    public static final float CRETERIA_BLUR_LOW = 0.26f;
    public static final float CRETERIA_BLUR_MID = 0.51f;
    public static final float CRETERIA_BLUR_NONE = 0.14f;
    public static final float CRETERIA_BLUR_VERYHIGH = 0.76f;
    private static final String TAG = "Remaster-VSWBlurEstimator";

    @Deprecated
    public static boolean estimate(byte[] bArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runBlurEstimator = VSWEngineNativeInterface.runBlurEstimator(bArr, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runBlurEstimator);
        return runBlurEstimator;
    }

    public boolean allowDownscaledInput(Context context) {
        return false;
    }

    public PrSize getRecommendedInputSize(Context context) {
        return new PrSize(TextToSpeechConst.MAX_SPEECH_INPUT, 3000);
    }

    public static boolean estimate(byte[] bArr, EstimatedResult estimatedResult, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        float[] fArr = new float[2];
        VSWEngineNativeInterface.runMQABlurEstimator(bArr, fArr, i2, i7);
        boolean z = fArr[0] >= 0.26f;
        Log.d(str, "bResult : " + z);
        if (estimatedResult != null) {
            estimatedResult.type = EstimatedResult.EstimationType.Blur;
            float f = fArr[0];
            estimatedResult.defectProbability = f;
            if (f < 0.14f) {
                estimatedResult.hasDefect = false;
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.None;
            } else if (f < 0.26f) {
                estimatedResult.hasDefect = true;
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.Low;
            } else if (f < 0.51f) {
                estimatedResult.hasDefect = true;
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.Mid;
            } else {
                estimatedResult.hasDefect = true;
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.High;
            }
        }
        Log.d(str, "estimate - X");
        return z;
    }

    public void releaseResources() {
    }

    public void stop() {
    }

    public EstimatorResult estimate(Context context, BgrBuffer bgrBuffer, int i2, String str) {
        byte[] data = bgrBuffer.getData();
        int width = bgrBuffer.getSize().getWidth();
        int height = bgrBuffer.getSize().getHeight();
        EstimatedResult estimatedResult = new EstimatedResult();
        return new EstimatorResult(estimate(data, estimatedResult, width, height), 1.0d, (double) estimatedResult.defectProbability);
    }
}
