package Te;

import Ae.b;
import Df.C0736b;
import G0.e;
import Gf.m;
import L1.d;
import Ne.i;
import Qe.B;
import Qe.C;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0833x;
import Qe.K;
import Qe.L;
import Re.g;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;
import ne.C1203u;
import ne.v;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z extends C0852m implements C {
    public final m g;

    /* renamed from: h  reason: collision with root package name */
    public final i f3811h;

    /* renamed from: i  reason: collision with root package name */
    public final Map f3812i;

    /* renamed from: j  reason: collision with root package name */
    public final E f3813j;
    public e k;
    public K l;
    public final boolean m;
    public final Gf.e n;

    /* renamed from: o  reason: collision with root package name */
    public final me.m f3814o;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public z(C1240g gVar, m mVar, i iVar, int i2) {
        super(g.f3692a, gVar);
        j.e(gVar, "moduleName");
        this.g = mVar;
        this.f3811h = iVar;
        if (gVar.e) {
            this.f3812i = C1203u.d;
            E.f3746a.getClass();
            E e = (E) x(C.b);
            this.f3813j = e == null ? D.b : e;
            this.m = true;
            this.n = mVar.b(new C0736b(9, this));
            this.f3814o = d.q(new Ne.m(this, 2));
            return;
        }
        throw new IllegalArgumentException("Module name must be special: " + gVar);
    }

    public final void D0() {
        if (!this.m) {
            if (x(C0833x.f3684a) == null) {
                String str = "Accessing invalid module descriptor " + this;
                j.e(str, "message");
                throw new IllegalStateException(str);
            }
            throw new ClassCastException();
        }
    }

    public final i f() {
        return this.f3811h;
    }

    public final boolean f0(C c5) {
        j.e(c5, "targetModule");
        if (equals(c5)) {
            return true;
        }
        j.b(this.k);
        if (C1194l.G0(v.d, c5)) {
            return true;
        }
        k0();
        if (c5.k0().contains(this)) {
            return true;
        }
        return false;
    }

    public final C0822l g() {
        return null;
    }

    public final List k0() {
        if (this.k != null) {
            return C1202t.d;
        }
        StringBuilder sb2 = new StringBuilder("Dependencies of module ");
        String str = getName().d;
        j.d(str, "toString(...)");
        sb2.append(str);
        sb2.append(" were not set");
        throw new AssertionError(sb2.toString());
    }

    public final Collection m(C1236c cVar, b bVar) {
        j.e(cVar, "fqName");
        D0();
        D0();
        return ((C0851l) this.f3814o.getValue()).m(cVar, bVar);
    }

    public final L n0(C1236c cVar) {
        j.e(cVar, "fqName");
        D0();
        return (L) this.n.invoke(cVar);
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(C0852m.C0(this));
        if (!this.m) {
            sb2.append(" !isValid");
        }
        sb2.append(" packageFragmentProvider: ");
        K k10 = this.l;
        if (k10 != null) {
            str = k10.getClass().getSimpleName();
        } else {
            str = null;
        }
        sb2.append(str);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.d(this, obj);
    }

    public final Object x(B b) {
        j.e(b, "capability");
        Object obj = this.f3812i.get(b);
        if (obj == null) {
            return null;
        }
        return obj;
    }
}
