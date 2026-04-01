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

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWImageQualityEstimator implements IEstimator {
    public static final float CRETERIA_IQA_GD = 0.75f;
    public static final float CRETERIA_IQA_NI = 0.51f;
    public static final float CRETERIA_IQA_VG = 1.0f;
    private static final String TAG = "Remaster-VSWImageQualityEstimator";

    public static boolean estimate(byte[] bArr, EstimatedResult estimatedResult, int i2, int i7) {
        String str = TAG;
        Log.d(str, "estimate - E");
        VSWEngineNativeInterface.printLibraryVersion();
        float[] fArr = new float[4];
        boolean runMQAQualityEstimator = VSWEngineNativeInterface.runMQAQualityEstimator(bArr, fArr, i2, i7);
        if (estimatedResult != null) {
            estimatedResult.type = EstimatedResult.EstimationType.IQA;
            float f = fArr[0];
            estimatedResult.qualityScore = f;
            if (f < 0.51f) {
                estimatedResult.qualityLevel = EstimatedResult.QualityLevel.NeedImprovement;
            } else if (f <= 0.75f) {
                estimatedResult.qualityLevel = EstimatedResult.QualityLevel.Good;
            } else {
                estimatedResult.qualityLevel = EstimatedResult.QualityLevel.VeryGood;
            }
        }
        Log.d(str, "estimate - X");
        return runMQAQualityEstimator;
    }

    public boolean allowDownscaledInput(Context context) {
        return false;
    }

    public PrSize getRecommendedInputSize(Context context) {
        return new PrSize(TextToSpeechConst.MAX_SPEECH_INPUT, 3000);
    }

    public EstimatorResult estimate(Context context, BgrBuffer bgrBuffer, int i2, String str) {
        byte[] data = bgrBuffer.getData();
        int width = bgrBuffer.getSize().getWidth();
        int height = bgrBuffer.getSize().getHeight();
        EstimatedResult estimatedResult = new EstimatedResult();
        return new EstimatorResult(estimate(data, estimatedResult, width, height), 1.0d, (double) estimatedResult.qualityScore);
    }

    public void releaseResources() {
    }

    public void stop() {
    }
}
