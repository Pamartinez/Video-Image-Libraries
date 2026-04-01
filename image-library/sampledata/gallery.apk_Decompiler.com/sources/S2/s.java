package S2;

import L2.a;
import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends l {
    public static final s e = new l(0);

    public final String a() {
        return Integer.toString(this.d);
    }

    public final String e() {
        return "short";
    }

    public final c getType() {
        return c.q;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("short{0x");
        int i2 = this.d;
        sb2.append(a.D(i2));
        sb2.append(" / ");
        sb2.append(i2);
        sb2.append('}');
        return sb2.toString();
    }
}
