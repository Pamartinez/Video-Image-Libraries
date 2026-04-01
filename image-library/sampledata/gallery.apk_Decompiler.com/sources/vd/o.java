package vd;

import Ae.c;
import L2.a;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.ArrayList;
import java.util.LinkedList;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import ne.C1194l;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ String e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(String str, C1227c cVar) {
        super(2, cVar);
        this.e = str;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        o oVar = new o(this.e, cVar);
        oVar.d = obj;
        return oVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((o) create(new k(((k) obj).d), (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        VexFwkLanguageCapabilities vexFwkLanguageCapabilities;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        Object obj2 = ((k) this.d).d;
        Throwable a7 = k.a(obj2);
        if (a7 == null) {
            VexFwkLanguageCapabilities vexFwkLanguageCapabilities2 = (VexFwkLanguageCapabilities) ((VexFwkSessionTotalResult) obj2).getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_CAPABILITIES.INSTANCE);
            String str = this.e;
            if (vexFwkLanguageCapabilities2 == null) {
                vexFwkLanguageCapabilities = new VexFwkLanguageCapabilities(new LinkedList());
            } else {
                ArrayList arrayList = new ArrayList();
                for (Object next : C1194l.H0(vexFwkLanguageCapabilities2)) {
                    if (!j.a(((VexFwkAvailableLanguage) next).getLang(), str)) {
                        arrayList.add(next);
                    }
                }
                vexFwkLanguageCapabilities = new VexFwkLanguageCapabilities(new LinkedList(arrayList));
            }
            String access$getTAG$cp = VexFwkImageTranslatorV2.TAG;
            Log.i(access$getTAG$cp, "get available languages to " + str + " X");
            return vexFwkLanguageCapabilities;
        }
        C0086a.x("Failed to process request : ", VexFwkImageTranslatorV2.TAG, a7);
        throw a7;
    }
}
