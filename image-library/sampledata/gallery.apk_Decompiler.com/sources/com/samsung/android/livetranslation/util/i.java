package com.samsung.android.livetranslation.util;

import com.samsung.android.livetranslation.util.LineWidthRule;
import java.util.function.DoubleBinaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements DoubleBinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ double f3239a;

    public /* synthetic */ i(double d) {
        this.f3239a = d;
    }

    public final double applyAsDouble(double d, double d2) {
        return LineWidthRule.LineOutlierDetector.lambda$getStdDeviation$2(this.f3239a, d, d2);
    }
}
