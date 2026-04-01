package mg;

import Ae.d;
import Q2.a;
import lg.l;
import me.b;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends h implements d {
    public int d;
    public /* synthetic */ b e;
    public final /* synthetic */ a f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(a aVar, C1227c cVar) {
        super(3, cVar);
        this.f = aVar;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        x xVar = (x) obj2;
        o oVar = new o(this.f, (C1227c) obj3);
        oVar.e = (b) obj;
        return oVar.invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        a aVar = this.f;
        Ed.b bVar = (Ed.b) aVar.b;
        C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.d;
        if (i2 == 0) {
            L2.a.A(obj);
            b bVar2 = this.e;
            byte w = bVar.w();
            if (w == 1) {
                return aVar.d(true);
            }
            if (w == 0) {
                return aVar.d(false);
            }
            if (w == 6) {
                this.d = 1;
                obj = a.a(aVar, bVar2, this);
                if (obj == aVar2) {
                    return aVar2;
                }
            } else if (w == 8) {
                return aVar.c();
            } else {
                Ed.b.p(bVar, "Can't begin reading element, unexpected token", 0, (String) null, 6);
                throw null;
            }
        } else if (i2 == 1) {
            L2.a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (l) obj;
    }
}
