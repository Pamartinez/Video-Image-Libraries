package R1;

import android.animation.TimeInterpolator;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public long f643a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public TimeInterpolator f644c;
    public int d;
    public int e;

    public final TimeInterpolator a() {
        TimeInterpolator timeInterpolator = this.f644c;
        if (timeInterpolator != null) {
            return timeInterpolator;
        }
        return a.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f643a == cVar.f643a && this.b == cVar.b && this.d == cVar.d && this.e == cVar.e) {
            return a().getClass().equals(cVar.a().getClass());
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f643a;
        long j3 = this.b;
        return ((((a().getClass().hashCode() + (((((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31)) * 31) + this.d) * 31) + this.e;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("\n");
        sb2.append(c.class.getName());
        sb2.append('{');
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" delay: ");
        sb2.append(this.f643a);
        sb2.append(" duration: ");
        sb2.append(this.b);
        sb2.append(" interpolator: ");
        sb2.append(a().getClass());
        sb2.append(" repeatCount: ");
        sb2.append(this.d);
        sb2.append(" repeatMode: ");
        return C0086a.l(sb2, this.e, "}\n");
    }
}
