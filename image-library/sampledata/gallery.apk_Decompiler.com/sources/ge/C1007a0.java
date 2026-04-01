package ge;

import B1.a;
import D1.f;
import E2.k;
import F2.C0010c0;
import java.util.Arrays;
import java.util.Set;

/* renamed from: ge.a0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1007a0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f4495a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final C0010c0 f4496c;

    public C1007a0(int i2, long j2, Set set) {
        this.f4495a = i2;
        this.b = j2;
        this.f4496c = C0010c0.y(set);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1007a0.class != obj.getClass()) {
            return false;
        }
        C1007a0 a0Var = (C1007a0) obj;
        if (this.f4495a == a0Var.f4495a && this.b == a0Var.b && f.p(this.f4496c, a0Var.f4496c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4495a), Long.valueOf(this.b), this.f4496c});
    }

    public final String toString() {
        k V = a.V(this);
        V.d("maxAttempts", String.valueOf(this.f4495a));
        V.b("hedgingDelayNanos", this.b);
        V.a(this.f4496c, "nonFatalStatusCodes");
        return V.toString();
    }
}
