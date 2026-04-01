package ud;

import Ae.c;
import L2.a;
import android.util.Log;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends i implements c {
    public final /* synthetic */ int d;
    public /* synthetic */ Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i2, C1227c cVar, int i7) {
        super(i2, cVar);
        this.d = i7;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                d dVar = new d(2, cVar, 0);
                dVar.e = obj;
                return dVar;
            case 1:
                d dVar2 = new d(2, cVar, 1);
                dVar2.e = obj;
                return dVar2;
            default:
                d dVar3 = new d(2, cVar, 2);
                dVar3.e = obj;
                return dVar3;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                x xVar = x.f4917a;
                ((d) create((VexFwkHelperConfiguration) obj, (C1227c) obj2)).invokeSuspend(xVar);
                return xVar;
            case 1:
                x xVar2 = x.f4917a;
                ((d) create((VexFwkHelperConfiguration) obj, (C1227c) obj2)).invokeSuspend(xVar2);
                return xVar2;
            default:
                x xVar3 = x.f4917a;
                ((d) create((VexFwkHelperProcess) obj, (C1227c) obj2)).invokeSuspend(xVar3);
                return xVar3;
        }
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r5v2, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r5v3, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r5v5, types: [Ae.c, se.i] */
    /* JADX WARNING: type inference failed for: r5v6, types: [Ae.c, se.i] */
    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        x xVar = x.f4917a;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                ((VexFwkHelperConfiguration) this.e).setSession(new i(2, (C1227c) null));
                return xVar;
            case 1:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperConfiguration vexFwkHelperConfiguration = (VexFwkHelperConfiguration) this.e;
                vexFwkHelperConfiguration.setSession(new i(2, (C1227c) null));
                vexFwkHelperConfiguration.setSession(new i(2, (C1227c) null));
                return xVar;
            default:
                C1245a aVar3 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) this.e;
                Log.i(VexFwkImageTranslatorV2.TAG, "get available languages E");
                vexFwkHelperProcess.setRequest(new i(2, (C1227c) null));
                vexFwkHelperProcess.setFinally(new i(2, (C1227c) null));
                return xVar;
        }
    }
}
