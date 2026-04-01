package com.samsung.android.photoremaster.sdk.enhancer;

import android.content.Context;
import android.graphics.Rect;
import com.samsung.android.photoremaster.sdk.ip.BgrBuffer;
import com.samsung.android.photoremaster.sdk.ip.IRequestCancelledChecker;
import com.samsung.android.photoremaster.sdk.ip.PrSize;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IEnhancer {
    EnhanceResult enhance(Context context, BgrBuffer bgrBuffer, BgrBuffer bgrBuffer2, double d, List<Rect> list, String str, IRequestCancelledChecker iRequestCancelledChecker);

    List<String> getEstimators(Context context);

    PrSize getOutputSizeFrom(Context context, PrSize prSize);

    void releaseResources();

    void stop();
}
