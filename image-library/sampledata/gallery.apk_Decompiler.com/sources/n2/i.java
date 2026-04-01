package N2;

import B1.a;
import L1.d;
import Q2.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final int f428a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final int f429c;
    public final a d;
    public final boolean e;

    public i(int i2, int i7, int i8, a aVar, boolean z) {
        if (!d.o(i2)) {
            throw new IllegalArgumentException("bogus opcode");
        } else if (!d.o(i7)) {
            throw new IllegalArgumentException("bogus family");
        } else if (!d.o(i8)) {
            throw new IllegalArgumentException("bogus nextOpcode");
        } else if (aVar != null) {
            this.f428a = i2;
            this.b = i7;
            this.f429c = i8;
            this.d = aVar;
            this.e = z;
        } else {
            throw new NullPointerException("format == null");
        }
    }

    public final String a() {
        String str;
        int i2 = this.f428a;
        try {
            Q2.a aVar = b.f635a[i2 + 1];
            if (aVar != null) {
                return (String) aVar.b;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        if (i2 == ((char) i2)) {
            str = L2.a.D(i2);
        } else {
            str = L2.a.E(i2);
        }
        throw new IllegalArgumentException("bogus opcode: ".concat(str));
    }

    public final String toString() {
        return a();
    }
}
