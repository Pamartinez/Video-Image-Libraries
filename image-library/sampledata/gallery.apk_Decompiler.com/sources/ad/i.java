package Ad;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import me.x;
import qe.C1227c;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends se.i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ VexFwkOrientation e;
    public final /* synthetic */ Object f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(VexFwkOrientation vexFwkOrientation, Object obj, C1227c cVar) {
        super(2, cVar);
        this.e = vexFwkOrientation;
        this.f = obj;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        i iVar = new i(this.e, this.f, cVar);
        iVar.d = obj;
        return iVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((i) create((VexFwkHelperProcess.Request) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperProcess.Request request = (VexFwkHelperProcess.Request) this.d;
        request.setUsecase(VexFwkUsecase.UNIFIED_DETECTOR);
        request.setOnRequestSetup(new h(this.e, this.f, (C1227c) null));
        return x.f4917a;
    }
}
