package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0888z;
import Vf.D;
import Vf.G;
import Vf.M;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationData;
import com.samsung.android.sdk.scs.ai.translation.NeuralTranslator;
import com.samsung.android.sdk.scs.base.tasks.Tasks;
import eg.e;
import eg.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator$translateAll$1", f = "TextTranslator.kt", l = {247}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextTranslator$translateAll$1 extends i implements c {
    final /* synthetic */ List<ImageTranslationData> $dataList;
    final /* synthetic */ boolean $isConcurrentMode;
    final /* synthetic */ int $requestId;
    Object L$0;
    int label;
    final /* synthetic */ TextTranslator this$0;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
    @C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator$translateAll$1$1", f = "TextTranslator.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator$translateAll$1$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass1 extends i implements c {
        int label;

        public final C1227c create(Object obj, C1227c cVar) {
            return new AnonymousClass1(next, textTranslator, i10, list, cVar);
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass1) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                a.A(obj);
                try {
                    ImageTranslationData imageTranslationData = next;
                    imageTranslationData.setTargetText(textTranslator.translate(i10, list.indexOf(imageTranslationData), next.getSourceText(), next.getSourceLang(), next.getTargetLang()));
                } catch (ExecutionException e) {
                    ImageTranslationData imageTranslationData2 = next;
                    imageTranslationData2.setTargetText(imageTranslationData2.getSourceText());
                    LibLogger.e("TextTranslator", "Exception thrown during translation", e);
                }
                return x.f4917a;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextTranslator$translateAll$1(TextTranslator textTranslator, int i2, List<ImageTranslationData> list, boolean z, C1227c cVar) {
        super(2, cVar);
        this.this$0 = textTranslator;
        this.$requestId = i2;
        this.$dataList = list;
        this.$isConcurrentMode = z;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new TextTranslator$translateAll$1(this.this$0, this.$requestId, this.$dataList, this.$isConcurrentMode, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((TextTranslator$translateAll$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        Iterator it;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            Tasks.await(this.this$0.translator.refresh());
            LibLogger.i("TextTranslator", "translateAll: refresh success");
            for (int i7 = 0; i7 < 3; i7++) {
                NeuralTranslator access$getTranslator$p = this.this$0.translator;
                int i8 = this.$requestId;
                Tasks.await(access$getTranslator$p.clear(i8 + "DeepSky" + (i7 % 3)));
            }
            ArrayList arrayList = new ArrayList();
            for (final ImageTranslationData next : this.$dataList) {
                f fVar = M.f3843a;
                cg.c a7 = D.a(e.d);
                C0888z zVar = new C0888z();
                final TextTranslator textTranslator = this.this$0;
                final int i10 = this.$requestId;
                final List<ImageTranslationData> list = this.$dataList;
                arrayList.add(D.b(a7, zVar, (C) null, new AnonymousClass1((C1227c) null), 2));
            }
            it = arrayList.iterator();
        } else if (i2 == 1) {
            it = (Iterator) this.L$0;
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            this.L$0 = it;
            this.label = 1;
            if (((G) it.next()).g(this) == aVar) {
                return aVar;
            }
        }
        if (!this.$isConcurrentMode) {
            this.this$0.close();
        }
        return x.f4917a;
    }
}
