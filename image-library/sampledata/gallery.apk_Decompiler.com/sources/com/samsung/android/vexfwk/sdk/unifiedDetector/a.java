package com.samsung.android.vexfwk.sdk.unifiedDetector;

import Ad.C0724e;
import Ae.c;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.sdk.unifiedDetector.VexFwkUnifiedDetector;
import java.util.List;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ VexFwkUnifiedDetectorMode e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(VexFwkUnifiedDetectorMode vexFwkUnifiedDetectorMode, C1227c cVar) {
        super(2, cVar);
        this.e = vexFwkUnifiedDetectorMode;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        a aVar = new a(this.e, cVar);
        aVar.d = obj;
        return aVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((a) create((VexFwkHelperConfiguration) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        L2.a.A(obj);
        VexFwkHelperConfiguration vexFwkHelperConfiguration = (VexFwkHelperConfiguration) this.d;
        VexFwkUnifiedDetector.Companion companion = VexFwkUnifiedDetector.Companion;
        List<VexFwkUnifiedDetectorMode> supportedModes = companion.getSupportedModes();
        VexFwkUnifiedDetectorMode vexFwkUnifiedDetectorMode = this.e;
        if (supportedModes.contains(vexFwkUnifiedDetectorMode)) {
            vexFwkHelperConfiguration.setSession(new C0724e(vexFwkUnifiedDetectorMode, (C1227c) null));
            return x.f4917a;
        }
        List<VexFwkUnifiedDetectorMode> supportedModes2 = companion.getSupportedModes();
        throw new IllegalStateException(("Unsupported mode " + vexFwkUnifiedDetectorMode + ", supported types are " + supportedModes2).toString());
    }
}
