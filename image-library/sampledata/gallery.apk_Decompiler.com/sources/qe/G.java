package Qe;

import Af.o;
import Af.p;
import B1.a;
import Ge.d;
import Ge.e;
import Gf.m;
import Hf.C0761j;
import Hf.M;
import Hf.d0;
import If.f;
import Re.g;
import Re.h;
import Te.C0848i;
import Te.C0849j;
import Te.O;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;
import ne.v;
import ne.y;
import qf.C1240g;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends C0849j {

    /* renamed from: j  reason: collision with root package name */
    public final boolean f3658j;
    public final ArrayList k;
    public final C0761j l;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public G(m mVar, C0818h hVar, C1240g gVar, boolean z, int i2) {
        super(mVar, hVar, gVar, Q.f3662a);
        j.e(hVar, "container");
        this.f3658j = z;
        e Z = a.Z(0, i2);
        ArrayList arrayList = new ArrayList(C1196n.w0(Z, 10));
        Iterator it = Z.iterator();
        while (((d) it).f) {
            int nextInt = ((y) it).nextInt();
            d0 d0Var = d0.INVARIANT;
            arrayList.add(O.H0(this, d0Var, C1240g.e("T" + nextInt), nextInt, mVar));
        }
        this.k = arrayList;
        this.l = new C0761j(this, C0833x.c(this), a.S(C1353d.j(this).f().e()), mVar);
    }

    public final p K(f fVar) {
        return o.b;
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
        return C0817g.CLASS;
    }

    public final boolean b0() {
        return false;
    }

    public final /* bridge */ /* synthetic */ p c0() {
        return o.b;
    }

    public final Collection d() {
        return v.d;
    }

    public final Collection e() {
        return C1202t.d;
    }

    public final h getAnnotations() {
        return g.f3692a;
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
        return this.k;
    }

    public final A k() {
        return A.FINAL;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        return this.l;
    }

    public final boolean s() {
        return this.f3658j;
    }

    public final boolean t0() {
        return false;
    }

    public final String toString() {
        return "class " + getName() + " (not found)";
    }

    public final C0848i y() {
        return null;
    }
}
