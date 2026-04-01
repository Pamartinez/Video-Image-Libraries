package R2;

import L2.a;
import U2.d;
import U2.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends d {
    public final f f;
    public int g = -1;

    public b(int i2) {
        super(i2);
        this.f = new f(i2);
    }

    public final int g() {
        f fVar = this.f;
        int i2 = fVar.f - 1;
        while (i2 >= 0 && fVar.e(i2) < 0) {
            i2--;
        }
        int i7 = i2 + 1;
        if (i7 < 0) {
            fVar.getClass();
            throw new IllegalArgumentException("newSize < 0");
        } else if (i7 <= fVar.f) {
            fVar.c();
            fVar.f = i7;
            return i7;
        } else {
            throw new IllegalArgumentException("newSize > size");
        }
    }

    public final a h(int i2) {
        int i7;
        f fVar = this.f;
        if (i2 >= fVar.f) {
            i7 = -1;
        } else {
            i7 = fVar.e(i2);
        }
        if (i7 >= 0) {
            return (a) d(i7);
        }
        throw new IllegalArgumentException("no such label: ".concat(a.D(i2)));
    }

    public final void i(int i2, a aVar) {
        a aVar2 = (a) this.e[i2];
        e(i2, aVar);
        f fVar = this.f;
        if (aVar2 != null) {
            fVar.f(aVar2.f645a, -1);
        }
        int i7 = aVar.f645a;
        int i8 = fVar.f;
        for (int i10 = 0; i10 <= i7 - i8; i10++) {
            fVar.d(-1);
        }
        fVar.f(i7, i2);
    }
}
