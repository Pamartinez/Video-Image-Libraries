package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F extends U {

    /* renamed from: a  reason: collision with root package name */
    public int[] f4667a;
    public int b;

    public final Object a() {
        int[] copyOf = Arrays.copyOf(this.f4667a, this.b);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final void b(int i2) {
        int[] iArr = this.f4667a;
        if (iArr.length < i2) {
            int length = iArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            int[] copyOf = Arrays.copyOf(iArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4667a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
