package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m0 extends U {

    /* renamed from: a  reason: collision with root package name */
    public long[] f4707a;
    public int b;

    public final Object a() {
        long[] copyOf = Arrays.copyOf(this.f4707a, this.b);
        j.d(copyOf, "copyOf(...)");
        return new t(copyOf);
    }

    public final void b(int i2) {
        long[] jArr = this.f4707a;
        if (jArr.length < i2) {
            int length = jArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            long[] copyOf = Arrays.copyOf(jArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4707a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
