package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import kotlin.Metadata;
import me.k;
import qe.C1227c;
import re.C1245a;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine", f = "VexImageTranslationEngine.kt", l = {376}, m = "translateImage-BWLJW6A")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexImageTranslationEngine$translateImage$1 extends C1271c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VexImageTranslationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexImageTranslationEngine$translateImage$1(VexImageTranslationEngine vexImageTranslationEngine, C1227c cVar) {
        super(cVar);
        this.this$0 = vexImageTranslationEngine;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r1 = this.this$0.m37translateImageBWLJW6A((VexFwkImageTranslator) null, (Bitmap) null, (OcrData) null, this);
        if (r1 == C1245a.COROUTINE_SUSPENDED) {
            return r1;
        }
        return new k(r1);
    }
}
