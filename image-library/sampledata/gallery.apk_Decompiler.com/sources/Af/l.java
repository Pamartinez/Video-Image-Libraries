package Af;

import Ae.b;
import Gf.h;
import Gf.i;
import Gf.m;
import Gf.p;
import Qe.C0812b;
import Qe.C0819i;
import Qe.C0822l;
import Ye.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1240g;
import tf.C1312p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements p {
    public final /* synthetic */ int b = 1;

    /* renamed from: c  reason: collision with root package name */
    public final Object f3316c;

    public l(p pVar) {
        this.f3316c = pVar;
    }

    public Collection a(C1240g gVar, a aVar) {
        switch (this.b) {
            case 1:
                j.e(gVar, "name");
                j.e(aVar, "location");
                return C1312p.o(j(gVar, aVar), m.f);
            default:
                return j(gVar, aVar);
        }
    }

    public final Set b() {
        return l().b();
    }

    public final C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return l().c(gVar, aVar);
    }

    public Collection d(f fVar, b bVar) {
        switch (this.b) {
            case 1:
                j.e(fVar, "kindFilter");
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Object next : i(fVar, bVar)) {
                    if (((C0822l) next) instanceof C0812b) {
                        arrayList.add(next);
                    } else {
                        arrayList2.add(next);
                    }
                }
                return C1194l.X0(arrayList2, C1312p.o(arrayList, m.f3317h));
            default:
                return i(fVar, bVar);
        }
    }

    public final Set e() {
        return l().e();
    }

    public Collection f(C1240g gVar, a aVar) {
        switch (this.b) {
            case 1:
                j.e(gVar, "name");
                j.e(aVar, "location");
                return C1312p.o(k(gVar, aVar), m.g);
            default:
                return k(gVar, aVar);
        }
    }

    public final Set g() {
        return l().g();
    }

    public final p h() {
        if (!(l() instanceof l)) {
            return l();
        }
        p l = l();
        j.c(l, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.AbstractScopeAdapter");
        return ((l) l).h();
    }

    public final Collection i(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        return l().d(fVar, bVar);
    }

    public final Collection j(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return l().a(gVar, aVar);
    }

    public final Collection k(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return l().f(gVar, aVar);
    }

    public final p l() {
        switch (this.b) {
            case 0:
                return (p) ((i) this.f3316c).invoke();
            default:
                return (p) this.f3316c;
        }
    }

    public l(p pVar, Ae.a aVar) {
        j.e(pVar, "storageManager");
        this.f3316c = new h((m) pVar, new k(aVar, 0));
    }
}
