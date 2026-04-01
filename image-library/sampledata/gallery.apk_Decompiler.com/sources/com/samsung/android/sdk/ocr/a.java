package com.samsung.android.sdk.ocr;

import android.graphics.Point;
import com.samsung.android.gallery.module.story.transcode.transcoder.ITranscoder;
import java.util.function.ToDoubleFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ToDoubleFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1635a;

    public /* synthetic */ a(int i2) {
        this.f1635a = i2;
    }

    public final double applyAsDouble(Object obj) {
        switch (this.f1635a) {
            case 0:
                return OCRResultUtils.lambda$getCenterPoint$0((Point) obj);
            case 1:
                return OCRResultUtils.lambda$getCenterPoint$1((Point) obj);
            default:
                return (double) ((ITranscoder) obj).getProgress();
        }
    }
}
