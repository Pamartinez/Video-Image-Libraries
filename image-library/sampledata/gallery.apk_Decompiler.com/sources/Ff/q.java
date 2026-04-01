package Ff;

import Ae.a;
import Df.l;
import Gf.p;
import Hf.C0774x;
import Ke.B;
import Ke.C0805x;
import Ke.E0;
import Ke.v0;
import Qe.C0816f;
import Qe.C0819i;
import Qe.S;
import Te.C0846g;
import Te.C0847h;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import kotlin.jvm.internal.j;
import ne.C1192j;
import rf.C1253c;
import rf.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements a {
    public final /* synthetic */ int d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ q(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return ((C1253c) ((z) this.e)).b((ByteArrayInputStream) this.f, ((l) ((s) this.g).b.f3358a).f3355p);
            case 1:
                C0805x xVar = (C0805x) this.f;
                B b = (B) this.g;
                C0819i g3 = ((C0774x) this.e).s0().g();
                if (g3 instanceof C0816f) {
                    Class k = E0.k((C0816f) g3);
                    if (k != null) {
                        Class cls = b.e;
                        if (j.a(cls.getSuperclass(), k)) {
                            Type genericSuperclass = cls.getGenericSuperclass();
                            j.b(genericSuperclass);
                            return genericSuperclass;
                        }
                        Class[] interfaces = cls.getInterfaces();
                        j.d(interfaces, "getInterfaces(...)");
                        int q0 = C1192j.q0(interfaces, k);
                        if (q0 >= 0) {
                            Type type = cls.getGenericInterfaces()[q0];
                            j.b(type);
                            return type;
                        }
                        throw new v0("No superclass of " + xVar + " in Java reflection for " + g3, 0);
                    }
                    throw new v0("Unsupported superclass of " + xVar + ": " + g3, 0);
                }
                throw new v0("Supertype not a class: " + g3, 0);
            default:
                return new C0846g((C0847h) this.g, (p) this.e, (S) this.f);
        }
    }

    public q(C0847h hVar, p pVar, S s) {
        this.d = 2;
        this.g = hVar;
        this.e = pVar;
        this.f = s;
    }
}
