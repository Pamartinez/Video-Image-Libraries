package com.samsung.android.app.sdk.deepsky.textextraction.languagepack;

import Ae.c;
import Vf.A;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Ljava/lang/Object;"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager$initTranslateSupportLangList$1", f = "LangPackManager.kt", l = {57}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LangPackManager$initTranslateSupportLangList$1 extends i implements c {
    final /* synthetic */ u $configuration;
    final /* synthetic */ Context $context;
    final /* synthetic */ u $supportLangList;
    int label;
    final /* synthetic */ LangPackManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LangPackManager$initTranslateSupportLangList$1(u uVar, Context context, u uVar2, LangPackManager langPackManager, C1227c cVar) {
        super(2, cVar);
        this.$configuration = uVar;
        this.$context = context;
        this.$supportLangList = uVar2;
        this.this$0 = langPackManager;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new LangPackManager$initTranslateSupportLangList$1(this.$configuration, this.$context, this.$supportLangList, this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((LangPackManager$initTranslateSupportLangList$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        if (r10 == null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        r10.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0070, code lost:
        if (r10 == null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0073, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r10.label
            java.lang.String r2 = "LangPackManager"
            r3 = 1
            if (r1 == 0) goto L_0x0020
            if (r1 != r3) goto L_0x0018
            L2.a.A(r11)     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            goto L_0x003c
        L_0x000f:
            r0 = move-exception
            r11 = r0
            goto L_0x0074
        L_0x0012:
            r0 = move-exception
            r11 = r0
            goto L_0x0048
        L_0x0015:
            r0 = move-exception
            r11 = r0
            goto L_0x005f
        L_0x0018:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0020:
            L2.a.A(r11)
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager$initTranslateSupportLangList$1$1 r4 = new com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager$initTranslateSupportLangList$1$1     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            kotlin.jvm.internal.u r5 = r10.$configuration     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            android.content.Context r6 = r10.$context     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            kotlin.jvm.internal.u r7 = r10.$supportLangList     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager r8 = r10.this$0     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            r9 = 0
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            r10.label = r3     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            r5 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r11 = Vf.D.x(r5, r4, r10)     // Catch:{ z0 -> 0x0015, Exception -> 0x0012 }
            if (r11 != r0) goto L_0x003c
            return r0
        L_0x003c:
            kotlin.jvm.internal.u r10 = r10.$configuration
            java.lang.Object r10 = r10.d
            com.samsung.android.sdk.scs.ai.language.Configuration r10 = (com.samsung.android.sdk.scs.ai.language.Configuration) r10
            if (r10 == 0) goto L_0x0047
            r10.release()
        L_0x0047:
            return r11
        L_0x0048:
            java.lang.String r0 = "getTranslateSupportLanguage failed"
            int r11 = com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.e(r2, r0, r11)     // Catch:{ all -> 0x000f }
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ all -> 0x000f }
            r0.<init>(r11)     // Catch:{ all -> 0x000f }
            kotlin.jvm.internal.u r10 = r10.$configuration
            java.lang.Object r10 = r10.d
            com.samsung.android.sdk.scs.ai.language.Configuration r10 = (com.samsung.android.sdk.scs.ai.language.Configuration) r10
            if (r10 == 0) goto L_0x0073
        L_0x005b:
            r10.release()
            goto L_0x0073
        L_0x005f:
            java.lang.String r0 = "getTranslateSupportLanguage timeout"
            int r11 = com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.e(r2, r0, r11)     // Catch:{ all -> 0x000f }
            java.lang.Integer r0 = new java.lang.Integer     // Catch:{ all -> 0x000f }
            r0.<init>(r11)     // Catch:{ all -> 0x000f }
            kotlin.jvm.internal.u r10 = r10.$configuration
            java.lang.Object r10 = r10.d
            com.samsung.android.sdk.scs.ai.language.Configuration r10 = (com.samsung.android.sdk.scs.ai.language.Configuration) r10
            if (r10 == 0) goto L_0x0073
            goto L_0x005b
        L_0x0073:
            return r0
        L_0x0074:
            kotlin.jvm.internal.u r10 = r10.$configuration
            java.lang.Object r10 = r10.d
            com.samsung.android.sdk.scs.ai.language.Configuration r10 = (com.samsung.android.sdk.scs.ai.language.Configuration) r10
            if (r10 == 0) goto L_0x007f
            r10.release()
        L_0x007f:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.languagepack.LangPackManager$initTranslateSupportLangList$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
