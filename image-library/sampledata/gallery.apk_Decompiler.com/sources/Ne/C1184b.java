package ne;

import A.a;
import N2.j;

/* renamed from: ne.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1184b {
    public static void a(int i2, int i7) {
        if (i2 < 0 || i2 >= i7) {
            throw new IndexOutOfBoundsException(j.b(i2, i7, "index: ", ", size: "));
        }
    }

    public static void b(int i2, int i7) {
        if (i2 < 0 || i2 > i7) {
            throw new IndexOutOfBoundsException(j.b(i2, i7, "index: ", ", size: "));
        }
    }

    public static void c(int i2, int i7, int i8) {
        if (i2 < 0 || i7 > i8) {
            StringBuilder h5 = a.h(i2, i7, "fromIndex: ", ", toIndex: ", ", size: ");
            h5.append(i8);
            throw new IndexOutOfBoundsException(h5.toString());
        } else if (i2 > i7) {
            throw new IllegalArgumentException(j.b(i2, i7, "fromIndex: ", " > toIndex: "));
        }
    }

    public static int d(int i2, int i7) {
        int i8 = i2 + (i2 >> 1);
        if (i8 - i7 < 0) {
            i8 = i7;
        }
        if (i8 - 2147483639 <= 0) {
            return i8;
        }
        if (i7 > 2147483639) {
            return Integer.MAX_VALUE;
        }
        return 2147483639;
    }
}
