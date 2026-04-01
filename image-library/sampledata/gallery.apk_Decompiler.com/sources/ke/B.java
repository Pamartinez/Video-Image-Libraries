package Ke;

import Af.i;
import Af.p;
import B1.b;
import D1.f;
import Df.l;
import Df.n;
import Ff.k;
import Gf.m;
import He.C0748d;
import He.t;
import L1.d;
import Ne.q;
import Pe.o;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.O;
import Te.C0848i;
import Te.C0850k;
import Ve.e;
import We.C0892d;
import Ye.c;
import a.C0068a;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.C1157j;
import lf.G;
import me.h;
import ne.C1194l;
import ne.C1202t;
import ne.v;
import nf.C1209f;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import rf.C1266p;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends F implements C0748d, u0 {
    public static final /* synthetic */ int g = 0;
    public final Class e;
    public final Object f = d.p(h.PUBLICATION, new C0801t(this, 0));

    public B(Class cls) {
        j.e(cls, "jClass");
        this.e = cls;
    }

    public static C0850k x(C1235b bVar, e eVar) {
        l lVar = eVar.f3832a;
        m mVar = lVar.f3349a;
        C0850k kVar = new C0850k(new o(lVar.b, bVar.f5033a, 1), bVar.f(), A.FINAL, C0817g.CLASS, C0246a.e0(lVar.b.f().j("Any").i()), mVar);
        kVar.e0(new i(mVar, kVar), v.d, (C0848i) null);
        return kVar;
    }

    public final Class b() {
        return this.e;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final Collection d() {
        x0 x0Var = ((C0805x) this.f.getValue()).f;
        t tVar = C0805x.f3514o[4];
        Object invoke = x0Var.invoke();
        j.d(invoke, "getValue(...)");
        return (Collection) invoke;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final List e() {
        x0 x0Var = ((C0805x) this.f.getValue()).f3516h;
        t tVar = C0805x.f3514o[8];
        Object invoke = x0Var.invoke();
        j.d(invoke, "getValue(...)");
        return (List) invoke;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof B) || !C0068a.B(this).equals(C0068a.B((C0748d) obj))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return C0068a.B(this).hashCode();
    }

    public final Collection i() {
        C0816f z = z();
        if (z.b() == C0817g.INTERFACE || z.b() == C0817g.OBJECT) {
            return C1202t.d;
        }
        Collection d = z.d();
        j.d(d, "getConstructors(...)");
        return d;
    }

    public final Collection j(C1240g gVar) {
        p A10 = z().i().A();
        c cVar = c.FROM_REFLECTION;
        Collection a7 = A10.a(gVar, cVar);
        p c02 = z().c0();
        j.d(c02, "getStaticScope(...)");
        return C1194l.X0(c02.a(gVar, cVar), a7);
    }

    public final O k(int i2) {
        k kVar;
        Class<?> declaringClass;
        Class cls = this.e;
        if (cls.getSimpleName().equals("DefaultImpls") && (declaringClass = cls.getDeclaringClass()) != null && declaringClass.isInterface()) {
            return ((B) C0068a.D(declaringClass)).k(i2);
        }
        C0816f z = z();
        if (z instanceof k) {
            kVar = (k) z;
        } else {
            kVar = null;
        }
        if (kVar != null) {
            C1157j jVar = kVar.f3386h;
            C1266p pVar = of.k.f5012j;
            j.d(pVar, "classLocalVariable");
            G g3 = (G) f.r(jVar, pVar, i2);
            if (g3 != null) {
                n nVar = kVar.f3389o;
                return (O) E0.f(this.e, g3, (C1209f) nVar.b, (b) nVar.d, kVar.f3387i, A.d);
            }
        }
        return null;
    }

    public final boolean l() {
        return z().l();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v4, types: [me.f, java.lang.Object] */
    public final Object m() {
        return ((C0805x) this.f.getValue()).g.getValue();
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final String n() {
        x0 x0Var = ((C0805x) this.f.getValue()).e;
        t tVar = C0805x.f3514o[3];
        return (String) x0Var.invoke();
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [me.f, java.lang.Object] */
    public final String o() {
        x0 x0Var = ((C0805x) this.f.getValue()).d;
        t tVar = C0805x.f3514o[2];
        return (String) x0Var.invoke();
    }

    public final Collection r(C1240g gVar) {
        p A10 = z().i().A();
        c cVar = c.FROM_REFLECTION;
        Collection f5 = A10.f(gVar, cVar);
        p c02 = z().c0();
        j.d(c02, "getStaticScope(...)");
        return C1194l.X0(c02.f(gVar, cVar), f5);
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("class ");
        C1235b y = y();
        C1236c cVar = y.f5033a;
        if (cVar.d()) {
            str = "";
        } else {
            str = cVar.b().concat(".");
        }
        String r0 = Tf.v.r0(y.b.b(), '.', '$');
        sb2.append(str + r0);
        return sb2.toString();
    }

    public final C1235b y() {
        C1235b bVar = C0.f3487a;
        Class cls = this.e;
        j.e(cls, "klass");
        Ne.l lVar = null;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            j.d(componentType, "getComponentType(...)");
            if (componentType.isPrimitive()) {
                lVar = C1359c.c(componentType.getSimpleName()).f();
            }
            if (lVar != null) {
                return new C1235b(q.l, lVar.d());
            }
            C1236c g3 = Ne.p.g.g();
            C1236c e7 = g3.e();
            C1240g f5 = g3.f();
            j.d(f5, "shortName(...)");
            return new C1235b(e7, f5);
        } else if (cls.equals(Void.TYPE)) {
            return C0.f3487a;
        } else {
            if (cls.isPrimitive()) {
                lVar = C1359c.c(cls.getSimpleName()).f();
            }
            if (lVar != null) {
                return new C1235b(q.l, lVar.f());
            }
            C1235b a7 = C0892d.a(cls);
            if (!a7.f5034c) {
                String str = Pe.d.f3633a;
                C1236c a10 = a7.a();
                j.e(a10, "fqName");
                C1235b bVar2 = (C1235b) Pe.d.f3635h.get(a10.i());
                if (bVar2 != null) {
                    return bVar2;
                }
            }
            return a7;
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final C0816f z() {
        return ((C0805x) this.f.getValue()).a();
    }
}
