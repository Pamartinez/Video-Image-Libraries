package R2;

import T2.c;
import T2.e;
import U2.d;
import java.util.BitSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends d implements e {
    public static final l f = new d(0);

    /* JADX WARNING: type inference failed for: r0v0, types: [U2.d, R2.l] */
    public static l h(k kVar) {
        ? dVar = new d(1);
        dVar.e(0, kVar);
        return dVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [U2.d, R2.l] */
    public static l i(k kVar, k kVar2) {
        ? dVar = new d(2);
        dVar.e(0, kVar);
        dVar.e(1, kVar2);
        return dVar;
    }

    public final int g() {
        int length = this.e.length;
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            i2 += getType(i7).e();
        }
        return i2;
    }

    public final c getType(int i2) {
        c type = ((k) d(i2)).e.getType();
        type.getClass();
        return type;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [U2.d, Nf.f, R2.l] */
    public final l j(boolean z, BitSet bitSet) {
        k kVar;
        int length = this.e.length;
        if (length == 0) {
            return this;
        }
        ? dVar = new d(length);
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            k kVar2 = (k) d(i7);
            if (bitSet != null && bitSet.get(i7)) {
                dVar.e(i7, kVar2);
            } else {
                if (kVar2.d == i2) {
                    kVar = kVar2;
                } else {
                    kVar = k.d(i2, kVar2.e);
                }
                dVar.e(i7, kVar);
                if (!z) {
                    i2 += kVar2.c();
                }
            }
            if (z) {
                z = false;
            }
        }
        if (!this.d) {
            dVar.d = false;
        }
        return dVar;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [U2.d, Nf.f, R2.l] */
    public final l k(int i2) {
        int i7;
        int i8;
        int length = this.e.length;
        if (length == 0) {
            return this;
        }
        ? dVar = new d(length);
        for (int i10 = 0; i10 < length; i10++) {
            k kVar = (k) d(i10);
            if (!(i2 == 0 || (i7 = kVar.d) == (i8 = i7 + i2))) {
                kVar = k.d(i8, kVar.e);
            }
            dVar.e(i10, kVar);
        }
        if (!this.d) {
            dVar.d = false;
        }
        return dVar;
    }
}
