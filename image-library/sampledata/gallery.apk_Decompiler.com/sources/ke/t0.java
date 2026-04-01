package Ke;

import Af.g;
import Ff.m;
import Ff.n;
import G0.c;
import He.C0748d;
import He.t;
import He.v;
import He.y;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0822l;
import Qe.V;
import Ve.b;
import a.C0068a;
import jf.C1109i;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.w;
import kotlin.jvm.internal.z;
import me.x;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t0 implements v {
    public static final /* synthetic */ t[] g;
    public final V d;
    public final x0 e = C0246a.d0((C0814d) null, new g(15, this));
    public final u0 f;

    static {
        w wVar = kotlin.jvm.internal.v.f4727a;
        g = new t[]{wVar.f(new p(wVar.b(t0.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};
    }

    public t0(u0 u0Var, V v) {
        Object obj;
        B b;
        n nVar;
        C1109i iVar;
        b bVar;
        Class cls;
        this.d = v;
        b bVar2 = null;
        if (u0Var == null) {
            C0822l g3 = v.g();
            j.d(g3, "getContainingDeclaration(...)");
            if (g3 instanceof C0816f) {
                obj = b((C0816f) g3);
            } else if (g3 instanceof C0814d) {
                C0822l g10 = ((C0814d) g3).g();
                j.d(g10, "getContainingDeclaration(...)");
                if (g10 instanceof C0816f) {
                    b = b((C0816f) g10);
                } else {
                    if (g3 instanceof n) {
                        nVar = (n) g3;
                    } else {
                        nVar = null;
                    }
                    if (nVar != null) {
                        m D = nVar.D();
                        if (D instanceof C1109i) {
                            iVar = (C1109i) D;
                        } else {
                            iVar = null;
                        }
                        if (iVar != null) {
                            bVar = iVar.f;
                        } else {
                            bVar = null;
                        }
                        bVar2 = bVar instanceof b ? bVar : bVar2;
                        if (bVar2 == null || (cls = bVar2.f3829a) == null) {
                            throw new v0("Container of deserialized member is not resolved: " + nVar, 0);
                        }
                        b = (B) C0068a.D(cls);
                    } else {
                        throw new v0("Non-class callable descriptor must be deserialized: " + g3, 0);
                    }
                }
                obj = g3.v(new c(6, (Object) b), x.f4917a);
            } else {
                throw new v0("Unknown type parameter container: " + g3, 0);
            }
            u0Var = (u0) obj;
        }
        this.f = u0Var;
    }

    public static B b(C0816f fVar) {
        C0748d dVar;
        Class k = E0.k(fVar);
        if (k != null) {
            dVar = C0068a.D(k);
        } else {
            dVar = null;
        }
        B b = (B) dVar;
        if (b != null) {
            return b;
        }
        throw new v0("Type parameter container is not resolved: " + fVar.g(), 0);
    }

    public final String a() {
        String b = this.d.getName().b();
        j.d(b, "asString(...)");
        return b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t0)) {
            return false;
        }
        t0 t0Var = (t0) obj;
        if (!j.a(this.f, t0Var.f) || !a().equals(t0Var.a())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return a().hashCode() + (this.f.hashCode() * 31);
    }

    public final String toString() {
        y yVar;
        StringBuilder sb2 = new StringBuilder();
        int i2 = s0.f3512a[this.d.t().ordinal()];
        if (i2 == 1) {
            yVar = y.INVARIANT;
        } else if (i2 == 2) {
            yVar = y.IN;
        } else if (i2 == 3) {
            yVar = y.OUT;
        } else {
            throw new RuntimeException();
        }
        int i7 = z.f4728a[yVar.ordinal()];
        if (i7 != 1) {
            if (i7 == 2) {
                sb2.append("in ");
            } else if (i7 == 3) {
                sb2.append("out ");
            } else {
                throw new RuntimeException();
            }
        }
        sb2.append(a());
        return sb2.toString();
    }
}
