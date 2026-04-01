package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Ae.c;
import He.F;
import L2.a;
import Vf.A;
import android.graphics.Bitmap;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslation$1", f = "VexImageTranslationEngine.kt", l = {76}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexImageTranslationEngine$doImageTranslation$1 extends i implements c {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ ImageTranslateListener $listener;
    final /* synthetic */ OcrData $ocrData;
    Object L$0;
    int label;
    final /* synthetic */ VexImageTranslationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexImageTranslationEngine$doImageTranslation$1(VexImageTranslationEngine vexImageTranslationEngine, Bitmap bitmap, OcrData ocrData, ImageTranslateListener imageTranslateListener, C1227c cVar) {
        super(2, cVar);
        this.this$0 = vexImageTranslationEngine;
        this.$bitmap = bitmap;
        this.$ocrData = ocrData;
        this.$listener = imageTranslateListener;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new VexImageTranslationEngine$doImageTranslation$1(this.this$0, this.$bitmap, this.$ocrData, this.$listener, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((VexImageTranslationEngine$doImageTranslation$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th;
        AutoCloseable autoCloseable;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            VexFwkImageTranslator vexFwkImageTranslator = new VexFwkImageTranslator();
            VexImageTranslationEngine vexImageTranslationEngine = this.this$0;
            Bitmap bitmap = this.$bitmap;
            OcrData ocrData = this.$ocrData;
            ImageTranslateListener imageTranslateListener = this.$listener;
            try {
                this.L$0 = vexFwkImageTranslator;
                this.label = 1;
                if (vexImageTranslationEngine.doImageTranslationInternal(vexFwkImageTranslator, bitmap, ocrData, imageTranslateListener, this) == aVar) {
                    return aVar;
                }
                autoCloseable = vexFwkImageTranslator;
            } catch (Throwable th2) {
                th = th2;
                autoCloseable = vexFwkImageTranslator;
                try {
                    throw th;
                } catch (Throwable th3) {
                    F.u(autoCloseable, th);
                    throw th3;
                }
            }
        } else if (i2 == 1) {
            autoCloseable = (AutoCloseable) this.L$0;
            try {
                a.A(obj);
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        F.u(autoCloseable, (Throwable) null);
        return x.f4917a;
    }
}
