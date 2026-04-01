package com.samsung.android.livetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.util.ParagraphRenderLineBreaker;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3238a;

    public /* synthetic */ f(int i2) {
        this.f3238a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f3238a) {
            case 0:
                return LineWidthRule.lambda$getScore$0((LttOcrResult.BlockInfo) obj);
            case 1:
                return ParagraphRenderLineBreaker.BulletParagraphDetector.SPECIAL_CHAR.contains(Character.toString(((LttOcrResult.LineInfo) obj).getString().trim().charAt(0)));
            case 2:
                return OcrDataToSceneTextConverter.lambda$convertStLines$2((LttOcrResult.LineInfo) obj);
            case 3:
                return OcrDataToSceneTextConverter.lambda$convert$0((LttOcrResult.BlockInfo) obj);
            default:
                return OcrDataToSceneTextConverter.lambda$convertStWords$4((LttOcrResult.WordInfo) obj);
        }
    }
}
