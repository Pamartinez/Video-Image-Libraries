package vd;

import Ae.c;
import L2.a;
import android.util.Log;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends i implements c {
    public final /* synthetic */ int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ String f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(String str, C1227c cVar, int i2) {
        super(2, cVar);
        this.d = i2;
        this.f = str;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                p pVar = new p(this.f, cVar, 0);
                pVar.e = obj;
                return pVar;
            default:
                p pVar2 = new p(this.f, cVar, 1);
                pVar2.e = obj;
                return pVar2;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                x xVar = x.f4917a;
                ((p) create(vexFwkHelperProcess, cVar)).invokeSuspend(xVar);
                return xVar;
            default:
                x xVar2 = x.f4917a;
                ((p) create(vexFwkHelperProcess, cVar)).invokeSuspend(xVar2);
                return xVar2;
        }
    }

    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        x xVar = x.f4917a;
        String str = this.f;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) this.e;
                String access$getTAG$cp = VexFwkImageTranslatorV2.TAG;
                Log.i(access$getTAG$cp, "get available languages to " + str + " E");
                vexFwkHelperProcess.setRequest(new n(str, (C1227c) null));
                vexFwkHelperProcess.setFinally(new o(str, (C1227c) null));
                return xVar;
            default:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess2 = (VexFwkHelperProcess) this.e;
                String access$getTAG$cp2 = VexFwkImageTranslatorV2.TAG;
                Log.i(access$getTAG$cp2, "get available languages from " + str + " E");
                vexFwkHelperProcess2.setRequest(new r(str, (C1227c) null));
                vexFwkHelperProcess2.setFinally(new s(str, (C1227c) null));
                return xVar;
        }
    }
}
