package df;

import Ae.b;
import Af.f;
import B0.a;
import Df.E;
import Gf.h;
import Gf.j;
import Gf.m;
import Gf.p;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import We.o;
import We.y;
import cf.C0922a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import ne.C1202t;
import ne.v;
import qf.C1240g;
import qf.C1242i;

/* renamed from: df.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0959v extends C0936E {
    public final y n;

    /* renamed from: o  reason: collision with root package name */
    public final C0954q f4262o;

    /* renamed from: p  reason: collision with root package name */
    public final h f4263p;
    public final j q;

    public C0959v(a aVar, y yVar, C0954q qVar) {
        super(aVar, (C0951n) null);
        this.n = yVar;
        this.f4262o = qVar;
        p pVar = ((C0922a) aVar.d).f4006a;
        E e = new E(18, (Object) aVar, (Object) this);
        m mVar = (m) pVar;
        mVar.getClass();
        this.f4263p = new h(mVar, e);
        this.q = ((m) pVar).c(new Ff.j(4, this, aVar));
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        return v(gVar, (o) null);
    }

    public final Collection d(f fVar, b bVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        if (!fVar.a(f.l | f.e)) {
            return C1202t.d;
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : (Iterable) this.d.invoke()) {
            C0822l lVar = (C0822l) next;
            if (lVar instanceof C0816f) {
                C1240g name = ((C0816f) lVar).getName();
                kotlin.jvm.internal.j.d(name, "getName(...)");
                if (((Boolean) bVar.invoke(name)).booleanValue()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public final Collection f(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        return C1202t.d;
    }

    public final Set h(f fVar, b bVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        if (!fVar.a(f.e)) {
            return v.d;
        }
        Set<String> set = (Set) this.f4263p.invoke();
        if (set != null) {
            HashSet hashSet = new HashSet();
            for (String e : set) {
                hashSet.add(C1240g.e(e));
            }
            return hashSet;
        }
        this.n.getClass();
        return new LinkedHashSet();
    }

    public final Set i(f fVar, Af.m mVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        return v.d;
    }

    public final C0940c k() {
        return C0939b.f4242a;
    }

    public final void m(LinkedHashSet linkedHashSet, C1240g gVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
    }

    public final Set o(f fVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        return v.d;
    }

    public final C0822l q() {
        return this.f4262o;
    }

    public final C0816f v(C1240g gVar, o oVar) {
        C1240g gVar2 = C1242i.f5041a;
        kotlin.jvm.internal.j.e(gVar, "name");
        String b = gVar.b();
        kotlin.jvm.internal.j.d(b, "asString(...)");
        if (b.length() <= 0 || gVar.e) {
            return null;
        }
        Set set = (Set) this.f4263p.invoke();
        if (oVar == null && set != null && !set.contains(gVar.b())) {
            return null;
        }
        return (C0816f) this.q.invoke(new C0955r(gVar, oVar));
    }

    public final pf.f w() {
        kotlin.jvm.internal.j.e(((C0922a) this.b.d).d.c().f3350c, "<this>");
        return pf.f.g;
    }
}
