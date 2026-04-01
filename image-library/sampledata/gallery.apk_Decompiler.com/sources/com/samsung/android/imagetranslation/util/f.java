package com.samsung.android.imagetranslation.util;

import android.content.Context;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ParagraphRenderLineBreaker f3225a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ LineRenderLineBreaker f3226c;

    public /* synthetic */ f(ParagraphRenderLineBreaker paragraphRenderLineBreaker, Context context, LineRenderLineBreaker lineRenderLineBreaker) {
        this.f3225a = paragraphRenderLineBreaker;
        this.b = context;
        this.f3226c = lineRenderLineBreaker;
    }

    public final Object apply(Object obj) {
        return LineBreakUtil.lambda$initResultWithSourceText$1(this.f3225a, this.b, this.f3226c, (LttOcrResult.BlockInfo) obj);
    }
}
