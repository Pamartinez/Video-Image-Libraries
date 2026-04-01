package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.util.LineWidthRule;
import java.util.function.ToDoubleFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ToDoubleFunction {
    public final double applyAsDouble(Object obj) {
        return LineWidthRule.LineOutlierDetector.lambda$getStdDeviation$1((LttOcrResult.LineInfo) obj);
    }
}
