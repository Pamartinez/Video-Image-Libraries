package Ad;

import Ae.b;
import Ae.d;
import L2.a;
import com.samsung.android.vexfwk.param.VexFwkOrientation;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends i implements d {
    public /* synthetic */ VexFwkSessionRequest d;
    public final /* synthetic */ VexFwkOrientation e;
    public final /* synthetic */ Object f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(VexFwkOrientation vexFwkOrientation, Object obj, C1227c cVar) {
        super(3, cVar);
        this.e = vexFwkOrientation;
        this.f = obj;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        VexFwkSessionRequest vexFwkSessionRequest = (VexFwkSessionRequest) obj;
        h hVar = new h(this.e, this.f, (C1227c) obj3);
        hVar.d = (VexFwkSessionRequest) obj2;
        x xVar = x.f4917a;
        hVar.invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkSessionRequest vexFwkSessionRequest = this.d;
        vexFwkSessionRequest.setSettingMetadata((b) new f(0, (Object) this.e));
        vexFwkSessionRequest.setInputBuffer(new g(0, this.f));
        return x.f4917a;
    }
}
