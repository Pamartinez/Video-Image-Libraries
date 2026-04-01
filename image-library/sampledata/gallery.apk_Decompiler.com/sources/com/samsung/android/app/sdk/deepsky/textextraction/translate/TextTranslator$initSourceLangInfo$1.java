package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import Ae.c;
import L2.a;
import Vf.A;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationData;
import com.samsung.android.sdk.scs.base.tasks.Tasks;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1195m;
import ne.C1196n;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator$initSourceLangInfo$1", f = "TextTranslator.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextTranslator$initSourceLangInfo$1 extends i implements c {
    final /* synthetic */ List<ImageTranslationData> $resultList;
    int label;
    final /* synthetic */ TextTranslator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextTranslator$initSourceLangInfo$1(TextTranslator textTranslator, List<ImageTranslationData> list, C1227c cVar) {
        super(2, cVar);
        this.this$0 = textTranslator;
        this.$resultList = list;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new TextTranslator$initSourceLangInfo$1(this.this$0, this.$resultList, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((TextTranslator$initSourceLangInfo$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            Tasks.await(this.this$0.translator.refresh());
            LibLogger.i("TextTranslator", "initSourceLangInfo: refresh success");
            boolean a7 = j.a(this.this$0.getSourceLanguage(), "Auto");
            x xVar = x.f4917a;
            if (a7) {
                Iterable<ImageTranslationData> iterable = this.$resultList;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (ImageTranslationData sourceText : iterable) {
                    arrayList.add(sourceText.getSourceText());
                }
                List access$detectLanguage = this.this$0.detectLanguage(arrayList);
                Iterable iterable2 = this.$resultList;
                TextTranslator textTranslator = this.this$0;
                Iterator it = iterable2.iterator();
                Iterable iterable3 = access$detectLanguage;
                Iterator it2 = iterable3.iterator();
                ArrayList arrayList2 = new ArrayList(Math.min(C1196n.w0(iterable2, 10), C1196n.w0(iterable3, 10)));
                while (it.hasNext() && it2.hasNext()) {
                    ImageTranslationData imageTranslationData = (ImageTranslationData) it.next();
                    imageTranslationData.setSourceLang((String) it2.next());
                    imageTranslationData.setTargetLang(textTranslator.getTargetLanguage());
                    arrayList2.add(xVar);
                }
                me.i access$getMostDetectedLanguageAndCount = this.this$0.getMostDetectedLanguageAndCount(arrayList, access$detectLanguage);
                if (access$getMostDetectedLanguageAndCount == null || (str = (String) access$getMostDetectedLanguageAndCount.d) == null) {
                    str = "";
                }
                TextTranslator textTranslator2 = this.this$0;
                textTranslator2.languagePackRequestLanguageList = C1195m.q0(str, textTranslator2.getTargetLanguage());
            } else {
                for (ImageTranslationData next : this.$resultList) {
                    next.setSourceLang(this.this$0.getSourceLanguage());
                    next.setTargetLang(this.this$0.getTargetLanguage());
                }
                TextTranslator textTranslator3 = this.this$0;
                textTranslator3.languagePackRequestLanguageList = C1195m.q0(textTranslator3.getSourceLanguage(), this.this$0.getTargetLanguage());
            }
            return xVar;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
