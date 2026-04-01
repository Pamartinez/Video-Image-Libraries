package com.samsung.android.photoremaster.sdk.estimator;

import android.content.Context;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.PrSize;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IEstimator {
    boolean allowDownscaledInput(Context context);

    EstimatorResult estimate(Context context, BgrBuffer bgrBuffer, int i2, String str);

    PrSize getRecommendedInputSize(Context context);

    void releaseResources();

    void stop();
}
