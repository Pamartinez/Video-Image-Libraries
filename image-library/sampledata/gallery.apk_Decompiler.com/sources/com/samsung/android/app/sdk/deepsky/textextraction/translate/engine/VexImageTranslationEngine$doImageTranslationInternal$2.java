package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Ae.c;
import L2.a;
import Vf.A;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.j;
import me.k;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"LVf/A;", "Lme/k;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "kotlin.jvm.PlatformType", "<anonymous>", "(LVf/A;)Lme/k;"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.VexImageTranslationEngine$doImageTranslationInternal$2", f = "VexImageTranslationEngine.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexImageTranslationEngine$doImageTranslationInternal$2 extends i implements c {
    final /* synthetic */ ImageTranslateListener $listener;
    final /* synthetic */ Object $translationResult;
    final /* synthetic */ VexFwkImageTranslator $translator;
    int label;
    final /* synthetic */ VexImageTranslationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexImageTranslationEngine$doImageTranslationInternal$2(Object obj, VexImageTranslationEngine vexImageTranslationEngine, VexFwkImageTranslator vexFwkImageTranslator, ImageTranslateListener imageTranslateListener, C1227c cVar) {
        super(2, cVar);
        this.$translationResult = obj;
        this.this$0 = vexImageTranslationEngine;
        this.$translator = vexFwkImageTranslator;
        this.$listener = imageTranslateListener;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new VexImageTranslationEngine$doImageTranslationInternal$2(this.$translationResult, this.this$0, this.$translator, this.$listener, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((VexImageTranslationEngine$doImageTranslationInternal$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            Object obj2 = this.$translationResult;
            VexImageTranslationEngine vexImageTranslationEngine = this.this$0;
            VexFwkImageTranslator vexFwkImageTranslator = this.$translator;
            ImageTranslateListener imageTranslateListener = this.$listener;
            if (!(obj2 instanceof j)) {
                VexFwkImageTranslator.TranslationResult translationResult = (VexFwkImageTranslator.TranslationResult) obj2;
                LibLogger.i("VexImageTranslationEngine", "imageTranslateTask done successfully");
                kotlin.jvm.internal.j.b(translationResult);
                vexImageTranslationEngine.onImageTranslationComplete(vexFwkImageTranslator, translationResult, imageTranslateListener);
            }
            ImageTranslateListener imageTranslateListener2 = this.$listener;
            Throwable a7 = k.a(obj2);
            if (a7 != null) {
                LibLogger.e("VexImageTranslationEngine", "image translation failed", a7);
                imageTranslateListener2.onImageTranslateFail(new ImageTranslationResult.Error.RendererFail(0, 1, (e) null));
            }
            return new k(obj2);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
