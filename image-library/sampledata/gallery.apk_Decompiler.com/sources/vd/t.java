package vd;

import Ae.b;
import Ae.d;
import L2.a;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends i implements d {
    public /* synthetic */ VexFwkSessionRequest d;

    /* JADX WARNING: type inference failed for: r0v1, types: [vd.t, se.i] */
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        VexFwkSessionRequest vexFwkSessionRequest = (VexFwkSessionRequest) obj;
        ? iVar = new i(3, (C1227c) obj3);
        iVar.d = (VexFwkSessionRequest) obj2;
        x xVar = x.f4917a;
        iVar.invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        this.d.setSettingMetadata((b) new d(3));
        return x.f4917a;
    }
}
