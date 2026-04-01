package F2;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v0 extends w0 implements Serializable {
    public static final v0 e = new v0(0);
    public static final v0 f = new v0(1);
    public final /* synthetic */ int d;

    public /* synthetic */ v0(int i2) {
        this.d = i2;
    }

    public final w0 a() {
        switch (this.d) {
            case 0:
                return f;
            default:
                return e;
        }
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                comparable.getClass();
                comparable2.getClass();
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                comparable3.getClass();
                if (comparable3 == comparable4) {
                    return 0;
                }
                return comparable4.compareTo(comparable3);
        }
    }

    public final String toString() {
        switch (this.d) {
            case 0:
                return "Ordering.natural()";
            default:
                return "Ordering.natural().reverse()";
        }
    }
}
