package F2;

import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends E {
    public static E g(int i2) {
        if (i2 < 0) {
            return E.b;
        }
        if (i2 > 0) {
            return E.f240c;
        }
        return E.f239a;
    }

    public final E a(int i2, int i7) {
        return g(Integer.compare(i2, i7));
    }

    public final E b(long j2, long j3) {
        return g(Long.compare(j2, j3));
    }

    public final E c(Object obj, Object obj2, Comparator comparator) {
        return g(comparator.compare(obj, obj2));
    }

    public final E d(boolean z, boolean z3) {
        return g(Boolean.compare(z, z3));
    }

    public final E e(boolean z, boolean z3) {
        return g(Boolean.compare(z3, z));
    }

    public final int f() {
        return 0;
    }
}
