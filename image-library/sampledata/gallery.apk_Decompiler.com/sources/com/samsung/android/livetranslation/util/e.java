package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ double f3237a;

    public /* synthetic */ e(double d) {
        this.f3237a = d;
    }

    public final boolean test(Object obj) {
        return LineWidthRule.lambda$getScore$3(this.f3237a, (LttOcrResult.LineInfo) obj);
    }
}
