package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import java.util.Map;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "LVf/e0;", "<anonymous>", "(LVf/A;)LVf/e0;"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.ImageTranslationEngine$init$1", f = "ImageTranslationEngine.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageTranslationEngine$init$1 extends i implements c {
    final /* synthetic */ OcrData $ocrData;
    int label;
    final /* synthetic */ ImageTranslationEngine this$0;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
    @C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.ImageTranslationEngine$init$1$1", f = "ImageTranslationEngine.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.samsung.android.app.sdk.deepsky.textextraction.translate.engine.ImageTranslationEngine$init$1$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass1 extends i implements c {
        int label;

        public final C1227c create(Object obj, C1227c cVar) {
            return new AnonymousClass1(imageTranslationEngine, ocrData, cVar);
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass1) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                a.A(obj);
                ImageTranslationEngine imageTranslationEngine = imageTranslationEngine;
                imageTranslationEngine.detectedLangCountMap = imageTranslationEngine.getTextTranslator().getDetectedLanguageAndCount(OcrData.toBlockStringList$deepsky_sdk_textextraction_8_5_30_release$default(ocrData, (String) null, (String) null, (String) null, 7, (Object) null));
                Map access$getDetectedLangCountMap$p = imageTranslationEngine.detectedLangCountMap;
                LibLogger.i("ImageTranslationEngine", "detectedLangCountMap: " + access$getDetectedLangCountMap$p);
                return x.f4917a;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageTranslationEngine$init$1(ImageTranslationEngine imageTranslationEngine, OcrData ocrData, C1227c cVar) {
        super(2, cVar);
        this.this$0 = imageTranslationEngine;
        this.$ocrData = ocrData;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new ImageTranslationEngine$init$1(this.this$0, this.$ocrData, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((ImageTranslationEngine$init$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            cg.c a7 = D.a(M.f3843a);
            final ImageTranslationEngine imageTranslationEngine = this.this$0;
            final OcrData ocrData = this.$ocrData;
            return D.n(a7, (C0886x) null, (C) null, new AnonymousClass1((C1227c) null), 3);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
