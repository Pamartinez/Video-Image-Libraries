package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.ParagraphRenderLineBreaker;
import com.samsung.android.sdk.scs.ai.text.bnlp.Token;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3229a;

    public /* synthetic */ j(int i2) {
        this.f3229a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3229a) {
            case 0:
                return LineWidthRule.lambda$getScore$0((LttOcrResult.BlockInfo) obj);
            case 1:
                return NLPAnalyzer.lambda$isSentenceBlock$1((Token) obj);
            case 2:
                return ParagraphRenderLineBreaker.BulletParagraphDetector.lambda$new$0((LttOcrResult.LineInfo) obj);
            case 3:
                return OcrDataToSceneTextConverter.lambda$convert$0((LttOcrResult.BlockInfo) obj);
            case 4:
                return OcrDataToSceneTextConverter.lambda$convertStLines$2((LttOcrResult.LineInfo) obj);
            default:
                return OcrDataToSceneTextConverter.lambda$convertStWords$4((LttOcrResult.WordInfo) obj);
        }
    }
}
