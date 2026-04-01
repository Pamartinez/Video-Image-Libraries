package ge;

import He.F;
import java.util.Random;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S {

    /* renamed from: a  reason: collision with root package name */
    public Random f4473a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public double f4474c;
    public double d;
    public long e;

    public final long a() {
        boolean z;
        long j2 = this.e;
        double d2 = (double) j2;
        this.e = Math.min((long) (this.f4474c * d2), this.b);
        double d3 = this.d;
        double d5 = (-d3) * d2;
        double d6 = d3 * d2;
        if (d6 >= d5) {
            z = true;
        } else {
            z = false;
        }
        F.j(z);
        return j2 + ((long) ((this.f4473a.nextDouble() * (d6 - d5)) + d5));
    }
}
