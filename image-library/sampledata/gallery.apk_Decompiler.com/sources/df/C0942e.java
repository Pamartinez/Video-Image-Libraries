package df;

import Ae.a;
import B1.b;
import G0.c;
import Jf.k;
import Jf.l;
import Pe.e;
import Qe.C;
import Qe.C0816f;
import Qe.C0833x;
import We.C0892d;
import We.C0893e;
import We.f;
import We.o;
import Ze.x;
import a.C0068a;
import cf.C0922a;
import gf.C1070a;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import vf.C1327g;

/* renamed from: df.e  reason: case insensitive filesystem */
public final class C0942e implements a {
    public final /* synthetic */ int d;
    public final C0943f e;

    public /* synthetic */ C0942e(C0943f fVar, int i2) {
        this.d = i2;
        this.e = fVar;
    }

    public final Object invoke() {
        i iVar;
        switch (this.d) {
            case 0:
                return C0892d.a(C0068a.A(C0068a.w(this.e.b.f3887a))).a();
            case 1:
                C0943f fVar = this.e;
                C1236c b = fVar.b();
                C0893e eVar = fVar.b;
                B0.a aVar = fVar.f4245a;
                if (b == null) {
                    return l.c(k.NOT_FOUND_FQNAME_FOR_JAVA_ANNOTATION, eVar.toString());
                }
                C0922a aVar2 = (C0922a) aVar.d;
                C c5 = aVar2.f4011o;
                C0816f c6 = e.c(b, c5.f());
                if (c6 == null) {
                    o oVar = new o(C0068a.A(C0068a.w(eVar.f3887a)));
                    b bVar = aVar2.k;
                    bVar.getClass();
                    c cVar = (c) bVar.e;
                    if (cVar != null) {
                        c6 = cVar.A(oVar);
                        if (c6 == null) {
                            C1236c e7 = b.e();
                            C1240g f = b.f();
                            j.d(f, "shortName(...)");
                            c6 = C0833x.f(c5, new C1235b(e7, f), aVar2.d.c().l);
                        }
                    } else {
                        j.k("resolver");
                        throw null;
                    }
                }
                return c6.i();
            default:
                C0943f fVar2 = this.e;
                ArrayList b5 = fVar2.b.b();
                ArrayList arrayList = new ArrayList();
                Iterator it = b5.iterator();
                while (it.hasNext()) {
                    C1070a aVar3 = (C1070a) it.next();
                    C1240g gVar = ((f) aVar3).f3888a;
                    if (gVar == null) {
                        gVar = x.b;
                    }
                    C1327g c8 = fVar2.c(aVar3);
                    if (c8 != null) {
                        iVar = new i(gVar, c8);
                    } else {
                        iVar = null;
                    }
                    if (iVar != null) {
                        arrayList.add(iVar);
                    }
                }
                return z.e0(arrayList);
        }
    }
}
