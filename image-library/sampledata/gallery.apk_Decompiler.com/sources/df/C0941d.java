package df;

import Ae.b;
import Af.f;
import Af.g;
import Af.p;
import B0.a;
import Gf.h;
import Gf.i;
import Gf.m;
import He.F;
import He.t;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0820j;
import Qe.C0834y;
import We.o;
import We.y;
import cf.C0922a;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1192j;
import ne.C1200r;
import ne.C1202t;
import qf.C1240g;

/* renamed from: df.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0941d implements p {
    public static final /* synthetic */ t[] f;
    public final a b;

    /* renamed from: c  reason: collision with root package name */
    public final C0954q f4243c;
    public final C0959v d;
    public final i e;

    static {
        w wVar = v.f4727a;
        f = new t[]{wVar.f(new kotlin.jvm.internal.p(wVar.b(C0941d.class), "kotlinScopes", "getKotlinScopes()[Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [Gf.h, Gf.i] */
    public C0941d(a aVar, y yVar, C0954q qVar) {
        this.b = aVar;
        this.f4243c = qVar;
        this.d = new C0959v(aVar, yVar, qVar);
        Gf.p pVar = ((C0922a) aVar.d).f4006a;
        g gVar = new g(28, this);
        m mVar = (m) pVar;
        mVar.getClass();
        this.e = new h(mVar, gVar);
    }

    public final Collection a(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        i(gVar, aVar);
        p[] h5 = h();
        Collection a7 = this.d.a(gVar, aVar);
        for (p a10 : h5) {
            a7 = F.w(a7, a10.a(gVar, aVar));
        }
        if (a7 == null) {
            return ne.v.d;
        }
        return a7;
    }

    public final Set b() {
        p[] h5 = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p b5 : h5) {
            C1200r.A0(b5.b(), linkedHashSet);
        }
        linkedHashSet.addAll(this.d.b());
        return linkedHashSet;
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        i(gVar, aVar);
        C0959v vVar = this.d;
        vVar.getClass();
        C0819i iVar = null;
        C0816f v = vVar.v(gVar, (o) null);
        if (v != null) {
            return v;
        }
        for (p c5 : h()) {
            C0819i c6 = c5.c(gVar, aVar);
            if (c6 != null) {
                if (!(c6 instanceof C0820j) || !((C0834y) c6).b0()) {
                    return c6;
                }
                if (iVar == null) {
                    iVar = c6;
                }
            }
        }
        return iVar;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        p[] h5 = h();
        Collection d2 = this.d.d(fVar, bVar);
        for (p d3 : h5) {
            d2 = F.w(d2, d3.d(fVar, bVar));
        }
        if (d2 == null) {
            return ne.v.d;
        }
        return d2;
    }

    public final Set e() {
        HashSet t = Gd.a.t(C1192j.Z(h()));
        if (t == null) {
            return null;
        }
        t.addAll(this.d.e());
        return t;
    }

    public final Collection f(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        i(gVar, aVar);
        p[] h5 = h();
        this.d.f(gVar, aVar);
        Collection collection = C1202t.d;
        for (p f5 : h5) {
            collection = F.w(collection, f5.f(gVar, aVar));
        }
        if (collection == null) {
            return ne.v.d;
        }
        return collection;
    }

    public final Set g() {
        p[] h5 = h();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p g : h5) {
            C1200r.A0(g.g(), linkedHashSet);
        }
        linkedHashSet.addAll(this.d.g());
        return linkedHashSet;
    }

    public final p[] h() {
        return (p[]) D1.f.y(this.e, f[0]);
    }

    public final void i(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        Gd.a.d0(((C0922a) this.b.d).n, aVar, this.f4243c, gVar);
    }

    public final String toString() {
        return "scope for " + this.f4243c;
    }
}
