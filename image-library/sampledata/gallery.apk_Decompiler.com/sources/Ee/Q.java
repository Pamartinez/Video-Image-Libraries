package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Q {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f4279a;
    public final Object b;

    public Q(Object obj) {
        this.b = obj;
        this.f4279a = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Q.class == obj.getClass()) {
            Q q = (Q) obj;
            if (!f.p(this.f4279a, q.f4279a) || !f.p(this.b, q.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4279a, this.b});
    }

    public final String toString() {
        Object obj = this.b;
        if (obj != null) {
            k V = a.V(this);
            V.a(obj, "config");
            return V.toString();
        }
        k V5 = a.V(this);
        V5.a(this.f4279a, "error");
        return V5.toString();
    }

    public Q(a0 a0Var) {
        this.b = null;
        F.n(a0Var, "status");
        this.f4279a = a0Var;
        F.h("cannot use OK status: %s", a0Var, !a0Var.e());
    }
}
