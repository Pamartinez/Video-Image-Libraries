package H0;

import A0.m;
import D0.c;
import D0.e;
import D0.h;
import I0.b;
import I0.d;
import J0.g;
import K0.a;
import java.util.ArrayList;
import x0.C0332j;

/* renamed from: H0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0045a {

    /* renamed from: a  reason: collision with root package name */
    public static final e f303a = e.S("k", "x", "y");

    public static c a(d dVar, C0332j jVar) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        if (dVar.n() == b.BEGIN_ARRAY) {
            dVar.a();
            while (dVar.h()) {
                if (dVar.n() == b.BEGIN_OBJECT) {
                    z = true;
                } else {
                    z = false;
                }
                d dVar2 = dVar;
                C0332j jVar2 = jVar;
                arrayList.add(new m(jVar2, p.b(dVar2, jVar2, g.c(), C0050f.f309h, z, false)));
                dVar = dVar2;
                jVar = jVar2;
            }
            dVar.f();
            q.b(arrayList);
        } else {
            arrayList.add(new a(o.b(dVar, g.c())));
        }
        return new c(arrayList);
    }

    public static h b(d dVar, C0332j jVar) {
        dVar.c();
        c cVar = null;
        D0.b bVar = null;
        boolean z = false;
        D0.b bVar2 = null;
        while (dVar.n() != b.END_OBJECT) {
            int q = dVar.q(f303a);
            if (q != 0) {
                if (q != 1) {
                    if (q != 2) {
                        dVar.r();
                        dVar.s();
                    } else if (dVar.n() == b.STRING) {
                        dVar.s();
                    } else {
                        bVar = Gd.a.V(dVar, jVar, true);
                    }
                } else if (dVar.n() == b.STRING) {
                    dVar.s();
                } else {
                    bVar2 = Gd.a.V(dVar, jVar, true);
                }
                z = true;
            } else {
                cVar = a(dVar, jVar);
            }
        }
        dVar.g();
        if (z) {
            jVar.a("Lottie doesn't support expressions.");
        }
        if (cVar != null) {
            return cVar;
        }
        return new D0.d(bVar2, bVar);
    }
}
