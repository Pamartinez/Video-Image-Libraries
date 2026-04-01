package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import ge.E0;
import java.util.Arrays;

/* renamed from: ee.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0964A {
    public static final C0964A d = new C0964A((E0) null, a0.e, false);

    /* renamed from: a  reason: collision with root package name */
    public final E0 f4266a;
    public final a0 b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f4267c;

    public C0964A(E0 e02, a0 a0Var, boolean z) {
        this.f4266a = e02;
        F.n(a0Var, "status");
        this.b = a0Var;
        this.f4267c = z;
    }

    public static C0964A a(a0 a0Var) {
        F.i("error status shouldn't be OK", !a0Var.e());
        return new C0964A((E0) null, a0Var, false);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0964A)) {
            return false;
        }
        C0964A a7 = (C0964A) obj;
        if (!f.p(this.f4266a, a7.f4266a) || !f.p(this.b, a7.b) || !f.p((Object) null, (Object) null) || this.f4267c != a7.f4267c) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4266a, this.b, null, Boolean.valueOf(this.f4267c)});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4266a, "subchannel");
        V.a((Object) null, "streamTracerFactory");
        V.a(this.b, "status");
        V.c("drop", this.f4267c);
        return V.toString();
    }
}
