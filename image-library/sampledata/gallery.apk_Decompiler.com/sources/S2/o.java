package S2;

import L2.a;
import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends m {
    public static final o e = new m(0);

    public final String a() {
        return Long.toString(this.d);
    }

    public final String e() {
        return "long";
    }

    public final c getType() {
        return c.f831p;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("long{0x");
        long j2 = this.d;
        sb2.append(a.F(j2));
        sb2.append(" / ");
        sb2.append(j2);
        sb2.append('}');
        return sb2.toString();
    }
}
