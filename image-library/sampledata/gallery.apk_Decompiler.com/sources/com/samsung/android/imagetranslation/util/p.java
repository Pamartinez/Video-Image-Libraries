package com.samsung.android.imagetranslation.util;

import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3231a;
    public final /* synthetic */ OcrDataToSceneTextConverter b;

    public /* synthetic */ p(OcrDataToSceneTextConverter ocrDataToSceneTextConverter, int i2) {
        this.f3231a = i2;
        this.b = ocrDataToSceneTextConverter;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3231a;
        OcrDataToSceneTextConverter ocrDataToSceneTextConverter = this.b;
        switch (i2) {
            case 0:
                return ocrDataToSceneTextConverter.lambda$convertStChars$7((LttOcrResult.CharInfo) obj);
            default:
                return ocrDataToSceneTextConverter.lambda$convertStWords$5((LttOcrResult.WordInfo) obj);
        }
    }
}
