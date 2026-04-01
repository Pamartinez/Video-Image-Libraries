package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import L2.a;
import Vf.A;
import com.samsung.android.vexfwk.coroutines.VexFwkBaseScope;
import java.util.concurrent.CancellationException;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends i implements c {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(Object obj, C1227c cVar, int i2) {
        super(2, cVar);
        this.d = i2;
        this.e = obj;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                return new f((VexFwkHelperBaseFuture) this.e, cVar, 0);
            default:
                return new f((VexFwkBaseScope) this.e, cVar, 1);
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        A a7 = (A) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                x xVar = x.f4917a;
                ((f) create(a7, cVar)).invokeSuspend(xVar);
                return xVar;
            default:
                x xVar2 = x.f4917a;
                ((f) create(a7, cVar)).invokeSuspend(xVar2);
                return xVar2;
        }
    }

    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        x xVar = x.f4917a;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                ((VexFwkHelperBaseFuture) obj2).job.a((CancellationException) null);
                return xVar;
            default:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                a.A(obj);
                ((VexFwkBaseScope) obj2).rootJob.a((CancellationException) null);
                return xVar;
        }
    }
}
