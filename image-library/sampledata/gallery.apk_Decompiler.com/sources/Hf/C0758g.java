package Hf;

import Af.g;
import Df.C0736b;
import Gf.d;
import Gf.m;
import Gf.p;
import Jf.l;
import Qe.C0819i;
import Qe.S;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import tf.C1301e;

/* renamed from: Hf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0758g implements M {

    /* renamed from: a  reason: collision with root package name */
    public int f3444a;
    public final d b;

    public C0758g(p pVar) {
        j.e(pVar, "storageManager");
        this.b = new d((m) pVar, new g(7, this), new C0736b(2, this));
    }

    public abstract Collection b();

    public abstract C0774x c();

    public abstract S d();

    /* renamed from: e */
    public final List h() {
        return ((C0757f) this.b.invoke()).b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof M) && obj.hashCode() == hashCode()) {
            M m = (M) obj;
            if (m.getParameters().size() == getParameters().size()) {
                C0819i g = g();
                C0819i g3 = m.g();
                if (g3 != null && !l.f(g) && !C1301e.o(g) && !l.f(g3) && !C1301e.o(g3)) {
                    return j(g3);
                }
                return false;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7 = this.f3444a;
        if (i7 != 0) {
            return i7;
        }
        C0819i g = g();
        if (l.f(g) || C1301e.o(g)) {
            i2 = System.identityHashCode(this);
        } else {
            i2 = C1301e.g(g).f5037a.hashCode();
        }
        this.f3444a = i2;
        return i2;
    }

    public abstract boolean j(C0819i iVar);

    public List k(List list) {
        return list;
    }
}
