package de;

import N2.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements Cloneable {
    public long[] d;
    public int e;

    public final void a(long j2) {
        int i2;
        int i7 = this.e;
        int i8 = i7 + 1;
        long[] jArr = this.d;
        if (i8 >= jArr.length) {
            if (i7 < 6) {
                i2 = 12;
            } else {
                i2 = i7 >> 1;
            }
            int i10 = i2 + i7;
            if (i10 > i8) {
                i8 = i10;
            }
            long[] jArr2 = new long[i8];
            System.arraycopy(jArr, 0, jArr2, 0, i7);
            this.d = jArr2;
        }
        int i11 = this.e;
        int i12 = i11 - i7;
        int i13 = i11 + 1;
        this.e = i13;
        if (i7 < 0 || i13 <= i7) {
            throw new ArrayIndexOutOfBoundsException(j.b(i13, i7, "length=", "; index="));
        }
        if (i12 != 0) {
            long[] jArr3 = this.d;
            System.arraycopy(jArr3, i7, jArr3, i7 + 1, i12);
        }
        this.d[i7] = j2;
    }

    public final Object clone() {
        try {
            d dVar = (d) super.clone();
            try {
                dVar.d = (long[]) this.d.clone();
                return dVar;
            } catch (CloneNotSupportedException unused) {
                return dVar;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }
}
