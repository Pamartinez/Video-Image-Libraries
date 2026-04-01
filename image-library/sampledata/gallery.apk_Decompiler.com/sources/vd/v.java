package vd;

import Ae.b;
import Ae.c;
import L2.a;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.vexfwk.imagetranslation.LanguagePackInstaller;
import com.samsung.android.vexfwk.imagetranslation.VexFwkLanguagePackInfo;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.ArrayList;
import java.util.List;
import me.k;
import me.x;
import ne.C1194l;
import ne.C1196n;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ List e;
    public final /* synthetic */ Context f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v(List list, Context context, C1227c cVar) {
        super(2, cVar);
        this.e = list;
        this.f = context;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        v vVar = new v(this.e, this.f, cVar);
        vVar.d = obj;
        return vVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        k kVar = new k(((k) obj).d);
        x xVar = x.f4917a;
        ((v) create(kVar, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        Object obj2 = ((k) this.d).d;
        Throwable a7 = k.a(obj2);
        if (a7 == null) {
            String str = (String) ((VexFwkSessionTotalResult) obj2).getResultMetadata().get(VexFwkMetadataKey.STRING_RESOURCE.INSTANCE);
            if (str == null) {
                str = "translate";
            }
            Iterable<VexFwkLanguagePackInfo> iterable = this.e;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (VexFwkLanguagePackInfo languagePackCode : iterable) {
                arrayList.add(languagePackCode.getLanguagePackCode());
            }
            List H02 = C1194l.H0(arrayList);
            int size = H02.size();
            Context context = this.f;
            if (size > 2) {
                LanguagePackInstaller languagePackInstaller = new LanguagePackInstaller(context);
                ArrayList arrayList2 = new ArrayList();
                C1194l.i1(H02, arrayList2);
                languagePackInstaller.goToLangPackDownload(arrayList2);
            } else {
                new LanguagePackInstaller(context).requestLangPackDownload(C1194l.R0(H02, GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (b) null, 62), str);
            }
            return x.f4917a;
        }
        C0086a.x("Failed to process request : ", VexFwkImageTranslatorV2.TAG, a7);
        throw a7;
    }
}
