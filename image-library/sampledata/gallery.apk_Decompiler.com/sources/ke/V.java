package Ke;

import Ae.a;
import Ge.c;
import Ge.e;
import Le.B;
import Le.C;
import Le.g;
import Qe.C0813c;
import Qe.C0816f;
import Qe.C0822l;
import Qe.M;
import Te.u;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;

public final class V implements a {
    public final /* synthetic */ int d;
    public final X e;

    public /* synthetic */ V(X x9, int i2) {
        this.d = i2;
        this.e = x9;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [Ge.c, Ge.e] */
    public final Object invoke() {
        W w;
        List list;
        switch (this.d) {
            case 0:
                return E0.d(this.e.b());
            default:
                X x9 = this.e;
                M b = x9.b();
                int i2 = x9.e;
                C0800s sVar = x9.d;
                if (!(b instanceof u) || !j.a(E0.g(sVar.j()), b) || sVar.j().b() != C0813c.FAKE_OVERRIDE) {
                    g g = sVar.g();
                    if (g instanceof C) {
                        if (sVar.q()) {
                            C c5 = (C) g;
                            e c6 = c5.c(i2 + 1);
                            int i7 = c5.c(0).e + 1;
                            list = C1194l.e1(c5.b.a(), new c(c6.d - i7, c6.e - i7, 1));
                        } else {
                            C c8 = (C) g;
                            list = C1194l.e1(c8.b.a(), c8.c(i2));
                        }
                        Type[] typeArr = (Type[]) list.toArray(new Type[0]);
                        Type[] typeArr2 = (Type[]) Arrays.copyOf(typeArr, typeArr.length);
                        int length = typeArr2.length;
                        if (length == 0) {
                            throw new Error("Expected at least 1 type for compound type");
                        } else if (length == 1) {
                            return (Type) C1192j.v0(typeArr2);
                        } else {
                            w = new W(typeArr2);
                        }
                    } else if (!(g instanceof B)) {
                        return (Type) g.a().get(i2);
                    } else {
                        Class[] clsArr = (Class[]) ((Collection) ((B) g).d.get(i2)).toArray(new Class[0]);
                        Type[] typeArr3 = (Type[]) Arrays.copyOf(clsArr, clsArr.length);
                        int length2 = typeArr3.length;
                        if (length2 == 0) {
                            throw new Error("Expected at least 1 type for compound type");
                        } else if (length2 == 1) {
                            return (Type) C1192j.v0(typeArr3);
                        } else {
                            w = new W(typeArr3);
                        }
                    }
                    return w;
                }
                C0822l g3 = sVar.j().g();
                j.c(g3, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                Class k = E0.k((C0816f) g3);
                if (k != null) {
                    return k;
                }
                throw new v0("Cannot determine receiver Java type of inherited declaration: " + b, 0);
        }
    }
}
