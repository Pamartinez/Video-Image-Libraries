package S2;

import L2.a;
import T2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends l {
    public static final i e = new l(Float.floatToIntBits(0.0f));

    /* JADX WARNING: type inference failed for: r1v0, types: [S2.l, S2.i] */
    static {
        Float.floatToIntBits(1.0f);
        Float.floatToIntBits(2.0f);
    }

    public final String a() {
        return Float.toString(Float.intBitsToFloat(this.d));
    }

    public final String e() {
        return "float";
    }

    public final c getType() {
        return c.n;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("float{0x");
        int i2 = this.d;
        sb2.append(a.E(i2));
        sb2.append(" / ");
        sb2.append(Float.intBitsToFloat(i2));
        sb2.append('}');
        return sb2.toString();
    }
}
