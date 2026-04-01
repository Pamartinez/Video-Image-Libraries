package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ double f3228a;

    public /* synthetic */ i(double d) {
        this.f3228a = d;
    }

    public final boolean test(Object obj) {
        return LineWidthRule.lambda$getScore$3(this.f3228a, (LttOcrResult.LineInfo) obj);
    }
}
