package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine", f = "VexImageTranslationEngine.kt", l = {325, 327}, m = "doImageTranslationInternal")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexImageTranslationEngine$doImageTranslationInternal$1 extends C1271c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VexImageTranslationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexImageTranslationEngine$doImageTranslationInternal$1(VexImageTranslationEngine vexImageTranslationEngine, C1227c cVar) {
        super(cVar);
        this.this$0 = vexImageTranslationEngine;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doImageTranslationInternal((VexFwkImageTranslator) null, (Bitmap) null, (OcrData) null, (ImageTranslateListener) null, this);
    }
}
