package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ParagraphRenderLineBreaker f3235a;
    public final /* synthetic */ LineRenderLineBreaker b;

    public /* synthetic */ c(ParagraphRenderLineBreaker paragraphRenderLineBreaker, LineRenderLineBreaker lineRenderLineBreaker) {
        this.f3235a = paragraphRenderLineBreaker;
        this.b = lineRenderLineBreaker;
    }

    public final Object apply(Object obj) {
        return LineBreakUtil.lambda$initResultWithSourceText$0(this.f3235a, this.b, (LttOcrResult.BlockInfo) obj);
    }
}
