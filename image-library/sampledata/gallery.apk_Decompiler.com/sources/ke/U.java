package Ke;

import Af.p;
import B1.b;
import He.t;
import L1.d;
import Qe.O;
import We.C0892d;
import Ye.c;
import java.util.Collection;
import kotlin.jvm.internal.j;
import lf.C;
import lf.G;
import lf.X;
import me.h;
import me.n;
import ne.C1202t;
import of.k;
import pf.f;
import pf.g;
import qf.C1240g;
import rf.C1266p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class U extends F {
    public final Class e;
    public final Object f = d.p(h.PUBLICATION, new O(this, 0));

    public U(Class cls) {
        j.e(cls, "jClass");
        this.e = cls;
    }

    public final Class b() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof U)) {
            return false;
        }
        if (j.a(this.e, ((U) obj).e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode();
    }

    public final Collection i() {
        return C1202t.d;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final Collection j(C1240g gVar) {
        x0 x0Var = ((S) this.f.getValue()).d;
        t tVar = S.g[1];
        Object invoke = x0Var.invoke();
        j.d(invoke, "getValue(...)");
        return ((p) invoke).a(gVar, c.FROM_REFLECTION);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v3, types: [me.f, java.lang.Object] */
    public final O k(int i2) {
        n nVar = (n) ((S) this.f.getValue()).f.getValue();
        if (nVar == null) {
            return null;
        }
        g gVar = (g) nVar.d;
        C c5 = (C) nVar.e;
        f fVar = (f) nVar.f;
        C1266p pVar = k.n;
        j.d(pVar, "packageLocalVariable");
        G g = (G) D1.f.r(c5, pVar, i2);
        if (g == null) {
            return null;
        }
        X x9 = c5.f4735j;
        j.d(x9, "getTypeTable(...)");
        return (O) E0.f(this.e, g, gVar, new b(x9), fVar, T.d);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v3, types: [me.f, java.lang.Object] */
    public final Class q() {
        Class cls = (Class) ((S) this.f.getValue()).e.getValue();
        if (cls == null) {
            return this.e;
        }
        return cls;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final Collection r(C1240g gVar) {
        x0 x0Var = ((S) this.f.getValue()).d;
        t tVar = S.g[1];
        Object invoke = x0Var.invoke();
        j.d(invoke, "getValue(...)");
        return ((p) invoke).f(gVar, c.FROM_REFLECTION);
    }

    public final String toString() {
        return "file class " + C0892d.a(this.e).a();
    }
}
