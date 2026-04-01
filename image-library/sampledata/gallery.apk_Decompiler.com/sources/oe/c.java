package Oe;

import Af.i;
import Af.o;
import Af.p;
import Ef.d;
import Gf.m;
import Hf.M;
import Hf.d0;
import If.f;
import Ne.q;
import Qe.A;
import Qe.C0817g;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0827q;
import Qe.H;
import Qe.Q;
import Qe.W;
import Re.g;
import Re.h;
import Te.C0841b;
import Te.C0848i;
import Te.O;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.y;
import qf.C1235b;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C0841b {

    /* renamed from: o  reason: collision with root package name */
    public static final C1235b f3613o = new C1235b(q.l, C1240g.e("Function"));

    /* renamed from: p  reason: collision with root package name */
    public static final C1235b f3614p = new C1235b(q.f3577i, C1240g.e("KFunction"));

    /* renamed from: h  reason: collision with root package name */
    public final m f3615h;

    /* renamed from: i  reason: collision with root package name */
    public final H f3616i;

    /* renamed from: j  reason: collision with root package name */
    public final l f3617j;
    public final int k;
    public final b l = new b(this);
    public final f m;
    public final List n;

    /* JADX WARNING: type inference failed for: r5v2, types: [Oe.f, Af.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(m mVar, d dVar, l lVar, int i2) {
        super(mVar, lVar.a(i2));
        j.e(dVar, "containingDeclaration");
        this.f3615h = mVar;
        this.f3616i = dVar;
        this.f3617j = lVar;
        this.k = i2;
        this.m = new i(mVar, this);
        ArrayList arrayList = new ArrayList();
        Ge.c cVar = new Ge.c(1, i2, 1);
        ArrayList arrayList2 = new ArrayList(C1196n.w0(cVar, 10));
        Iterator it = cVar.iterator();
        while (((Ge.d) it).f) {
            int nextInt = ((y) it).nextInt();
            d0 d0Var = d0.IN_VARIANCE;
            arrayList.add(O.H0(this, d0Var, C1240g.e("P" + nextInt), arrayList.size(), this.f3615h));
            arrayList2.add(x.f4917a);
        }
        arrayList.add(O.H0(this, d0.OUT_VARIANCE, C1240g.e("R"), arrayList.size(), this.f3615h));
        this.n = C1194l.k1(arrayList);
        d dVar2 = e.Companion;
        l lVar2 = this.f3617j;
        dVar2.getClass();
        j.e(lVar2, "functionTypeKind");
        if (!lVar2.equals(h.f3618c) && !lVar2.equals(k.f3621c) && !lVar2.equals(i.f3619c)) {
            lVar2.equals(j.f3620c);
        }
    }

    public final p K(f fVar) {
        return this.m;
    }

    public final W N() {
        return null;
    }

    public final boolean Q() {
        return false;
    }

    public final boolean T() {
        return false;
    }

    public final boolean X() {
        return false;
    }

    public final C0817g b() {
        return C0817g.INTERFACE;
    }

    public final boolean b0() {
        return false;
    }

    public final /* bridge */ /* synthetic */ p c0() {
        return o.b;
    }

    public final /* bridge */ /* synthetic */ Collection d() {
        return C1202t.d;
    }

    public final /* bridge */ /* synthetic */ Collection e() {
        return C1202t.d;
    }

    public final C0822l g() {
        return this.f3616i;
    }

    public final h getAnnotations() {
        return g.f3692a;
    }

    public final Q getSource() {
        return Q.f3662a;
    }

    public final C0826p getVisibility() {
        C0826p pVar = C0827q.e;
        j.d(pVar, "PUBLIC");
        return pVar;
    }

    public final boolean isExternal() {
        return false;
    }

    public final boolean isInline() {
        return false;
    }

    public final List j() {
        return this.n;
    }

    public final A k() {
        return A.ABSTRACT;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        return this.l;
    }

    public final boolean s() {
        return false;
    }

    public final boolean t0() {
        return false;
    }

    public final String toString() {
        String b = getName().b();
        j.d(b, "asString(...)");
        return b;
    }

    public final /* bridge */ /* synthetic */ C0848i y() {
        return null;
    }
}
