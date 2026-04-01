package vd;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ String e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(String str, C1227c cVar) {
        super(2, cVar);
        this.e = str;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        n nVar = new n(this.e, cVar);
        nVar.d = obj;
        return nVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((n) create((VexFwkHelperProcess.Request) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperProcess.Request request = (VexFwkHelperProcess.Request) this.d;
        request.setUsecase(VexFwkUsecase.TRANSLATION_UTIL);
        request.setOnRequestSetup(new m(this.e, (C1227c) null));
        return x.f4917a;
    }
}
