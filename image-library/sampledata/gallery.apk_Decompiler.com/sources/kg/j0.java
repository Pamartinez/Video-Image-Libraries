package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j0 extends U {

    /* renamed from: a  reason: collision with root package name */
    public int[] f4701a;
    public int b;

    public final Object a() {
        int[] copyOf = Arrays.copyOf(this.f4701a, this.b);
        j.d(copyOf, "copyOf(...)");
        return new r(copyOf);
    }

    public final void b(int i2) {
        int[] iArr = this.f4701a;
        if (iArr.length < i2) {
            int length = iArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            int[] copyOf = Arrays.copyOf(iArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4701a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
