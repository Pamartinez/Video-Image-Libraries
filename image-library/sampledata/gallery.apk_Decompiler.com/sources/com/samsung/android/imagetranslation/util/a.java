package com.samsung.android.imagetranslation.util;

import android.graphics.Point;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3223a;

    public /* synthetic */ a(int i2) {
        this.f3223a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3223a) {
            case 0:
                return Integer.valueOf(((LttOcrResult.LineInfo) obj).getWordInfo().size());
            case 1:
                return Integer.valueOf(((LttOcrResult.LineInfo) obj).getRect().width());
            case 2:
                return ((LttOcrResult.LineInfo) obj).getString();
            default:
                return OCRResultUtils.lambda$calculateRotatedRect$0((Point) obj);
        }
    }
}
