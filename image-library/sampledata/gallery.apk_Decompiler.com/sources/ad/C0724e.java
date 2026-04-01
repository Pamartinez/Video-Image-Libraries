package Ad;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* renamed from: Ad.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0724e extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ VexFwkUnifiedDetectorMode e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0724e(VexFwkUnifiedDetectorMode vexFwkUnifiedDetectorMode, C1227c cVar) {
        super(2, cVar);
        this.e = vexFwkUnifiedDetectorMode;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        C0724e eVar = new C0724e(this.e, cVar);
        eVar.d = obj;
        return eVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((C0724e) create((VexFwkHelperConfiguration.Session) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [Ae.c, se.i] */
    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperConfiguration.Session session = (VexFwkHelperConfiguration.Session) this.d;
        session.setUsecase(VexFwkUsecase.UNIFIED_DETECTOR);
        session.setConfigMetadata(new C0722c(this.e, (C1227c) null));
        session.setInputBufferStream(new i(2, (C1227c) null));
        return x.f4917a;
    }
}
