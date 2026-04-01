package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p0 extends U {

    /* renamed from: a  reason: collision with root package name */
    public short[] f4713a;
    public int b;

    public final Object a() {
        short[] copyOf = Arrays.copyOf(this.f4713a, this.b);
        j.d(copyOf, "copyOf(...)");
        return new w(copyOf);
    }

    public final void b(int i2) {
        short[] sArr = this.f4713a;
        if (sArr.length < i2) {
            int length = sArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            short[] copyOf = Arrays.copyOf(sArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4713a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
