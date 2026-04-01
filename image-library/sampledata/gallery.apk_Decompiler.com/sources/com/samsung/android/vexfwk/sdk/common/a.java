package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Vf.A;
import android.util.Log;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession;
import com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTaggerV2;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import me.k;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;
import ud.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends i implements c {
    public final /* synthetic */ int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Object obj, C1227c cVar, int i2) {
        super(2, cVar);
        this.d = i2;
        this.f = obj;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                a aVar = new a((A) this.f, cVar, 0);
                aVar.e = obj;
                return aVar;
            default:
                a aVar2 = new a(this.f, cVar, 1);
                aVar2.e = obj;
                return aVar2;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                x xVar = x.f4917a;
                ((a) create((VexFwkSession) obj, (C1227c) obj2)).invokeSuspend(xVar);
                return xVar;
            default:
                x xVar2 = x.f4917a;
                ((a) create((VexFwkHelperProcess) obj, (C1227c) obj2)).invokeSuspend(xVar2);
                return xVar2;
        }
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [Ae.c, se.i] */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        int i2 = this.d;
        Object obj3 = x.f4917a;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                L2.a.A(obj);
                VexFwkSession vexFwkSession = (VexFwkSession) this.e;
                try {
                    String access$getTAG$cp = VexFwkHelperBase.TAG;
                    VexFwkUsecase usecase = vexFwkSession.getUsecase();
                    Log.i(access$getTAG$cp, "closing session : usecase(" + usecase + ")");
                    vexFwkSession.flush();
                    vexFwkSession.close();
                    obj2 = obj3;
                } catch (Throwable th) {
                    obj2 = L2.a.l(th);
                }
                Throwable a7 = k.a(obj2);
                if (a7 != null) {
                    String access$getTAG$cp2 = VexFwkHelperBase.TAG;
                    VexFwkUsecase usecase2 = vexFwkSession.getUsecase();
                    Log.e(access$getTAG$cp2, "failed to close session : usecase(" + usecase2 + ")", a7);
                }
                return obj3;
            default:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                L2.a.A(obj);
                VexFwkHelperProcess vexFwkHelperProcess = (VexFwkHelperProcess) this.e;
                Log.d(VexFwkImageTaggerV2.TAG, "detectImageTagsImpl E");
                vexFwkHelperProcess.setRequest(new f(this.f, (C1227c) null));
                vexFwkHelperProcess.setFinally(new i(2, (C1227c) null));
                Log.d(VexFwkImageTaggerV2.TAG, "detectImageTagsImpl X");
                return obj3;
        }
    }
}
