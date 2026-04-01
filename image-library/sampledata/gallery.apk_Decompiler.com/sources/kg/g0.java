package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g0 extends U {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f4696a;
    public int b;

    public final Object a() {
        byte[] copyOf = Arrays.copyOf(this.f4696a, this.b);
        j.d(copyOf, "copyOf(...)");
        return new p(copyOf);
    }

    public final void b(int i2) {
        byte[] bArr = this.f4696a;
        if (bArr.length < i2) {
            int length = bArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            byte[] copyOf = Arrays.copyOf(bArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4696a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
