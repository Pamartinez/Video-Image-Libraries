package Yf;

import Ae.c;
import L2.a;
import Vf.A;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends i implements c {
    public int d;
    public final /* synthetic */ n e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(n nVar, C1227c cVar) {
        super(2, cVar);
        this.e = nVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new j(this.e, cVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.d;
        x xVar = x.f4917a;
        if (i2 == 0) {
            a.A(obj);
            this.d = 1;
            Object collect = this.e.collect(Zf.j.d, this);
            if (collect != aVar) {
                collect = xVar;
            }
            if (collect == aVar) {
                return aVar;
            }
            return xVar;
        } else if (i2 == 1) {
            a.A(obj);
            return xVar;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
