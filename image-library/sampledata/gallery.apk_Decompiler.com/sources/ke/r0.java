package Ke;

import Ae.a;
import Df.E;
import He.C0749e;
import He.t;
import He.u;
import Hf.C0774x;
import Hf.P;
import Hf.a0;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.U;
import Qe.V;
import We.C0892d;
import a.C0068a;
import java.lang.reflect.Array;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import o1.C0246a;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r0 implements u {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ t[] f3509h;
    public final C0774x d;
    public final x0 e;
    public final x0 f;
    public final x0 g;

    static {
        w wVar = v.f4727a;
        Class<r0> cls = r0.class;
        f3509h = new t[]{wVar.f(new p(wVar.b(cls), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), wVar.f(new p(wVar.b(cls), "arguments", "getArguments()Ljava/util/List;"))};
    }

    public r0(C0774x xVar, a aVar) {
        x0 x0Var;
        j.e(xVar, "type");
        this.d = xVar;
        if (aVar instanceof x0) {
            x0Var = (x0) aVar;
        } else {
            x0Var = null;
        }
        if (x0Var == null) {
            if (aVar != null) {
                x0Var = C0246a.d0((C0814d) null, aVar);
            } else {
                x0Var = null;
            }
        }
        this.e = x0Var;
        this.f = C0246a.d0((C0814d) null, new o0(this, 0));
        this.g = C0246a.d0((C0814d) null, new E(5, (Object) this, (Object) aVar));
    }

    public final C0749e a() {
        t tVar = f3509h[0];
        return (C0749e) this.f.invoke();
    }

    public final C0749e b(C0774x xVar) {
        C0774x b;
        C0819i g3 = xVar.s0().g();
        if (g3 instanceof C0816f) {
            Class k = E0.k((C0816f) g3);
            if (k != null) {
                if (k.isArray()) {
                    P p6 = (P) C1194l.d1(xVar.e0());
                    if (p6 == null || (b = p6.b()) == null) {
                        return new B(k);
                    }
                    C0749e b5 = b(b);
                    if (b5 != null) {
                        return new B(Array.newInstance(C0068a.A(Gd.a.A(b5)), 0).getClass());
                    }
                    throw new v0("Cannot determine classifier for array element type: " + this, 0);
                } else if (a0.e(xVar)) {
                    return new B(k);
                } else {
                    Class cls = (Class) C0892d.b.get(k);
                    if (cls != null) {
                        k = cls;
                    }
                    return new B(k);
                }
            }
        } else if (g3 instanceof V) {
            return new t0((u0) null, (V) g3);
        } else {
            if (g3 instanceof U) {
                throw new Error("An operation is not implemented: Type alias classifiers are not yet supported");
            }
        }
        return null;
    }

    public final List c() {
        t tVar = f3509h[1];
        Object invoke = this.g.invoke();
        j.d(invoke, "getValue(...)");
        return (List) invoke;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r0)) {
            return false;
        }
        r0 r0Var = (r0) obj;
        if (!j.a(this.d, r0Var.d) || !j.a(a(), r0Var.a()) || !c().equals(r0Var.c())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i2;
        int hashCode = this.d.hashCode() * 31;
        C0749e a7 = a();
        if (a7 != null) {
            i2 = a7.hashCode();
        } else {
            i2 = 0;
        }
        return c().hashCode() + ((hashCode + i2) * 31);
    }

    public final String toString() {
        C1283j jVar = B0.f3485a;
        return B0.d(this.d);
    }
}
