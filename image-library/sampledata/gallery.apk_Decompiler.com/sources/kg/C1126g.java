package kg;

import java.util.Arrays;
import kotlin.jvm.internal.j;

/* renamed from: kg.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1126g extends U {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f4695a;
    public int b;

    public final Object a() {
        byte[] copyOf = Arrays.copyOf(this.f4695a, this.b);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final void b(int i2) {
        byte[] bArr = this.f4695a;
        if (bArr.length < i2) {
            int length = bArr.length * 2;
            if (i2 < length) {
                i2 = length;
            }
            byte[] copyOf = Arrays.copyOf(bArr, i2);
            j.d(copyOf, "copyOf(...)");
            this.f4695a = copyOf;
        }
    }

    public final int d() {
        return this.b;
    }
}
