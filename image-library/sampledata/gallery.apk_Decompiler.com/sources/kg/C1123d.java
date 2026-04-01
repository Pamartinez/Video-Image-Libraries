package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;

/* renamed from: kg.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1123d extends U {

    /* renamed from: a  reason: collision with root package name */
    public boolean[] f4690a;
    public int b;

    public final Object a() {
        boolean[] copyOf = Arrays.copyOf(this.f4690a, this.b);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final void b(int i2) {
        boolean[] zArr = this.f4690a;
        if (zArr.length < i2) {
            int length = zArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            boolean[] copyOf = Arrays.copyOf(zArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4690a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
