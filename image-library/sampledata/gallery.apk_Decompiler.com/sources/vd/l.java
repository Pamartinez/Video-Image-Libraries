package vd;

import Ae.c;
import L2.a;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.LinkedList;
import me.k;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends i implements c {
    public /* synthetic */ Object d;

    /* JADX WARNING: type inference failed for: r1v1, types: [se.i, vd.l, qe.c] */
    public final C1227c create(Object obj, C1227c cVar) {
        ? iVar = new i(2, cVar);
        iVar.d = obj;
        return iVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((l) create(new k(((k) obj).d), (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        Object obj2 = ((k) this.d).d;
        Throwable a7 = k.a(obj2);
        if (a7 == null) {
            VexFwkLanguageCapabilities vexFwkLanguageCapabilities = (VexFwkLanguageCapabilities) ((VexFwkSessionTotalResult) obj2).getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_CAPABILITIES.INSTANCE);
            if (vexFwkLanguageCapabilities == null) {
                vexFwkLanguageCapabilities = new VexFwkLanguageCapabilities(new LinkedList());
            }
            Log.i(VexFwkImageTranslatorV2.TAG, "get available languages X");
            return vexFwkLanguageCapabilities;
        }
        C0086a.x("Failed to process request : ", VexFwkImageTranslatorV2.TAG, a7);
        throw a7;
    }
}
