package ge;

import B1.a;
import D1.f;
import E2.k;
import F2.C0010c0;
import java.util.Arrays;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f4572a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final long f4573c;
    public final double d;
    public final Long e;
    public final C0010c0 f;

    public x1(int i2, long j2, long j3, double d2, Long l, Set set) {
        this.f4572a = i2;
        this.b = j2;
        this.f4573c = j3;
        this.d = d2;
        this.e = l;
        this.f = C0010c0.y(set);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof x1)) {
            return false;
        }
        x1 x1Var = (x1) obj;
        if (this.f4572a == x1Var.f4572a && this.b == x1Var.b && this.f4573c == x1Var.f4573c && Double.compare(this.d, x1Var.d) == 0 && f.p(this.e, x1Var.e) && f.p(this.f, x1Var.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f4572a), Long.valueOf(this.b), Long.valueOf(this.f4573c), Double.valueOf(this.d), this.e, this.f});
    }

    public final String toString() {
        k V = a.V(this);
        V.d("maxAttempts", String.valueOf(this.f4572a));
        V.b("initialBackoffNanos", this.b);
        V.b("maxBackoffNanos", this.f4573c);
        V.d("backoffMultiplier", String.valueOf(this.d));
        V.a(this.e, "perAttemptRecvTimeoutNanos");
        V.a(this.f, "retryableStatusCodes");
        return V.toString();
    }
}
