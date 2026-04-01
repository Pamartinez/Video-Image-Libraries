package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;

/* renamed from: kg.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1130k extends U {

    /* renamed from: a  reason: collision with root package name */
    public char[] f4702a;
    public int b;

    public final Object a() {
        char[] copyOf = Arrays.copyOf(this.f4702a, this.b);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final void b(int i2) {
        char[] cArr = this.f4702a;
        if (cArr.length < i2) {
            int length = cArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            char[] copyOf = Arrays.copyOf(cArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4702a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
