package com.samsung.android.imagetranslation.util;

import android.content.Context;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.LineWidthRule;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3227a;
    public final /* synthetic */ Object b;

    public /* synthetic */ h(int i2, Object obj) {
        this.f3227a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3227a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return LineWidthRule.lambda$getScore$1((LineWidthRule.LineOutlierDetector) obj2, (LttOcrResult.LineInfo) obj);
            case 1:
                return NLPAnalyzer.lambda$new$0((Context) obj2, (String) obj);
            default:
                return ((OcrDataToSceneTextConverter) obj2).lambda$convertStChars$6((LttOcrResult.CharInfo) obj);
        }
    }
}
