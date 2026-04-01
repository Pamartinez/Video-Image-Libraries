package vd;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends i implements c {
    public /* synthetic */ Object d;

    /* JADX WARNING: type inference failed for: r1v1, types: [se.i, qe.c, vd.h] */
    public final C1227c create(Object obj, C1227c cVar) {
        ? iVar = new i(2, cVar);
        iVar.d = obj;
        return iVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((h) create((VexFwkHelperConfiguration.Session) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r3v3, types: [Ae.c, se.i] */
    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperConfiguration.Session session = (VexFwkHelperConfiguration.Session) this.d;
        session.setUsecase(VexFwkUsecase.IMAGE_TRANSLATION);
        session.setInputBufferStream(new i(2, (C1227c) null));
        session.setOutputBufferStream(new i(2, (C1227c) null));
        return x.f4917a;
    }
}
