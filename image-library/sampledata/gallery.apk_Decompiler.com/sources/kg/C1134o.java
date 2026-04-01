package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;

/* renamed from: kg.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1134o extends U {

    /* renamed from: a  reason: collision with root package name */
    public double[] f4710a;
    public int b;

    public final Object a() {
        double[] copyOf = Arrays.copyOf(this.f4710a, this.b);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final void b(int i2) {
        double[] dArr = this.f4710a;
        if (dArr.length < i2) {
            int length = dArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            double[] copyOf = Arrays.copyOf(dArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4710a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
