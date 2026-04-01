package Ke;

import A0.l;
import Ae.a;
import Af.o;
import Af.p;
import He.F;
import He.t;
import Ve.b;
import Ve.e;
import We.C0892d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import jf.C1107g;
import kf.C1116b;
import kotlin.jvm.internal.j;
import lf.C;
import me.i;
import me.n;
import ne.C1192j;
import ne.C1194l;
import ne.C1202t;
import o1.C0246a;
import pf.f;
import pf.g;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import yf.C1358b;

public final class P implements a {
    public final /* synthetic */ int d;
    public final S e;

    public /* synthetic */ P(S s, int i2) {
        this.d = i2;
        this.e = s;
    }

    public final Object invoke() {
        List<b> list;
        switch (this.d) {
            case 0:
                S s = this.e;
                x0 x0Var = s.f3494c;
                t tVar = S.g[0];
                b bVar = (b) x0Var.invoke();
                if (bVar == null) {
                    return o.b;
                }
                x0 x0Var2 = s.f3486a;
                t tVar2 = C.b[0];
                Object invoke = x0Var2.invoke();
                j.d(invoke, "getValue(...)");
                l lVar = ((e) invoke).b;
                C1107g gVar = (C1107g) lVar.e;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) lVar.g;
                Class cls = bVar.f3829a;
                C1235b a7 = C0892d.a(cls);
                Object obj = concurrentHashMap.get(a7);
                if (obj == null) {
                    C1236c cVar = C0892d.a(cls).f5033a;
                    ee.P p6 = bVar.b;
                    C1116b bVar2 = (C1116b) p6.f4277c;
                    C1116b bVar3 = C1116b.MULTIFILE_CLASS;
                    if (bVar2 == bVar3) {
                        String[] strArr = (String[]) p6.e;
                        List<String> list2 = null;
                        if (bVar2 != bVar3) {
                            strArr = null;
                        }
                        if (strArr != null) {
                            list2 = C1192j.a0(strArr);
                        }
                        if (list2 == null) {
                            list2 = C1202t.d;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (String c5 : list2) {
                            C1236c cVar2 = new C1236c(C1358b.c(c5).f5168a.replace('/', '.'));
                            C1236c e7 = cVar2.e();
                            C1240g f = cVar2.f();
                            j.d(f, "shortName(...)");
                            C1235b bVar4 = new C1235b(e7, f);
                            j.e(gVar.c().f3350c, "<this>");
                            b E = F.E((B1.b) lVar.f, bVar4, f.g);
                            if (E != null) {
                                arrayList.add(E);
                            }
                        }
                        list = arrayList;
                    } else {
                        list = C0246a.e0(bVar);
                    }
                    Pe.o oVar = new Pe.o(gVar.c().b, cVar, 1);
                    ArrayList arrayList2 = new ArrayList();
                    for (b a10 : list) {
                        Ff.t a11 = gVar.a(oVar, a10);
                        if (a11 != null) {
                            arrayList2.add(a11);
                        }
                    }
                    p m = D1.f.m("package " + cVar + " (" + bVar + ')', C1194l.k1(arrayList2));
                    Object putIfAbsent = concurrentHashMap.putIfAbsent(a7, m);
                    if (putIfAbsent == null) {
                        obj = m;
                    } else {
                        obj = putIfAbsent;
                    }
                }
                j.d(obj, "getOrPut(...)");
                return (p) obj;
            default:
                x0 x0Var3 = this.e.f3494c;
                t tVar3 = S.g[0];
                b bVar5 = (b) x0Var3.invoke();
                if (bVar5 != null) {
                    ee.P p8 = bVar5.b;
                    String[] strArr2 = (String[]) p8.e;
                    String[] strArr3 = (String[]) p8.g;
                    if (!(strArr2 == null || strArr3 == null)) {
                        i h5 = pf.i.h(strArr2, strArr3);
                        return new n((g) h5.d, (C) h5.e, (f) p8.d);
                    }
                }
                return null;
        }
    }
}
