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
public class VSWNoiseEstimator implements IEstimator {
    public static final float CRETERIA_NOISE = 0.52f;
    public static final float CRETERIA_NOISE_MID = 0.75f;
    private static final String TAG = "Remaster-VSWNoiseEstimator";

    @Deprecated
    public static boolean estimate(byte[] bArr, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        boolean runNoiseEstimator = VSWEngineNativeInterface.runNoiseEstimator(bArr, i2, i7);
        C0280e.g("result : ", str, str, "estimate - X", runNoiseEstimator);
        return runNoiseEstimator;
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
        VSWEngineNativeInterface.runMQANoiseEstimator(bArr, fArr, i2, i7);
        boolean z = false;
        float f = fArr[0];
        float f5 = fArr[1];
        Log.d(str, "Clean : " + f5);
        Log.d(str, "Noisy : " + f);
        if (f > f5 && f > 0.52f) {
            z = true;
        }
        Log.d(str, "bResult : " + z);
        if (estimatedResult != null) {
            estimatedResult.type = EstimatedResult.EstimationType.Noise;
            estimatedResult.defectProbability = f;
            estimatedResult.hasDefect = z;
            if (f < 0.52f) {
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.None;
            } else if (f < 0.75f) {
                estimatedResult.defectLevel = EstimatedResult.DefactLevel.Mid;
            } else {
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
        estimate(data, estimatedResult, width, height);
        float f = estimatedResult.defectProbability;
        return new EstimatorResult(f > 0.5f, 1.0d, (double) f);
    }
}
