package S2;

import L2.a;
import T2.c;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends m {
    public static final g e = new m(Double.doubleToLongBits(MapUtil.INVALID_LOCATION));

    /* JADX WARNING: type inference failed for: r0v0, types: [S2.m, S2.g] */
    static {
        Double.doubleToLongBits(1.0d);
    }

    public final String a() {
        return Double.toString(Double.longBitsToDouble(this.d));
    }

    public final String e() {
        return "double";
    }

    public final c getType() {
        return c.m;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("double{0x");
        long j2 = this.d;
        sb2.append(a.F(j2));
        sb2.append(" / ");
        sb2.append(Double.longBitsToDouble(j2));
        sb2.append('}');
        return sb2.toString();
    }
}
