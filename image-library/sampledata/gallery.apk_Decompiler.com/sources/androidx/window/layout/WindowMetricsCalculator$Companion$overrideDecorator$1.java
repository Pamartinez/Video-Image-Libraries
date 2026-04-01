package androidx.window.layout;

import Ae.b;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class WindowMetricsCalculator$Companion$overrideDecorator$1 extends h implements b {
    public WindowMetricsCalculator$Companion$overrideDecorator$1(Object obj) {
        super(1, obj, WindowMetricsCalculatorDecorator.class, "decorate", "decorate(Landroidx/window/layout/WindowMetricsCalculator;)Landroidx/window/layout/WindowMetricsCalculator;", 0);
    }

    public final WindowMetricsCalculator invoke(WindowMetricsCalculator windowMetricsCalculator) {
        j.e(windowMetricsCalculator, "p0");
        return ((WindowMetricsCalculatorDecorator) this.receiver).decorate(windowMetricsCalculator);
    }
}
