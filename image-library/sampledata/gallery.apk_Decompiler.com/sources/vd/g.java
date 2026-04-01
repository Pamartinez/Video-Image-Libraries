package vd;

import Ae.c;
import L2.a;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends i implements c {
    public /* synthetic */ Object d;

    /* JADX WARNING: type inference failed for: r1v1, types: [vd.g, se.i, qe.c] */
    public final C1227c create(Object obj, C1227c cVar) {
        ? iVar = new i(2, cVar);
        iVar.d = obj;
        return iVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        x xVar = x.f4917a;
        ((g) create((VexFwkHelperConfiguration.BufferStream) obj, (C1227c) obj2)).invokeSuspend(xVar);
        return xVar;
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        a.A(obj);
        VexFwkHelperConfiguration.BufferStream bufferStream = (VexFwkHelperConfiguration.BufferStream) this.d;
        bufferStream.setId(new Integer(1));
        bufferStream.setType(VexFwkStreamType.BUFFER);
        bufferStream.setUsage(VexFwkStreamUsage.IMAGE);
        return x.f4917a;
    }
}
