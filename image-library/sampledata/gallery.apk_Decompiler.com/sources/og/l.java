package og;

import java.nio.charset.Charset;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends c {

    /* renamed from: i  reason: collision with root package name */
    public final transient byte[][] f5018i;

    /* renamed from: j  reason: collision with root package name */
    public final transient int[] f5019j;

    public l(a aVar, int i2) {
        super((byte[]) null);
        n.a(aVar.e, 0, (long) i2);
        j jVar = aVar.d;
        int i7 = 0;
        int i8 = 0;
        int i10 = 0;
        while (i8 < i2) {
            int i11 = jVar.f5017c;
            int i12 = jVar.b;
            if (i11 != i12) {
                i8 += i11 - i12;
                i10++;
                jVar = jVar.f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.f5018i = new byte[i10][];
        this.f5019j = new int[(i10 * 2)];
        j jVar2 = aVar.d;
        int i13 = 0;
        while (i7 < i2) {
            byte[][] bArr = this.f5018i;
            bArr[i13] = jVar2.f5016a;
            int i14 = jVar2.f5017c;
            int i15 = jVar2.b;
            int i16 = (i14 - i15) + i7;
            if (i16 > i2) {
                i7 = i2;
            } else {
                i7 = i16;
            }
            int[] iArr = this.f5019j;
            iArr[i13] = i7;
            iArr[bArr.length + i13] = i15;
            jVar2.d = true;
            i13++;
            jVar2 = jVar2.f;
        }
    }

    public final byte b(int i2) {
        int i7;
        byte[][] bArr = this.f5018i;
        int[] iArr = this.f5019j;
        n.a((long) iArr[bArr.length - 1], (long) i2, 1);
        int k = k(i2);
        if (k == 0) {
            i7 = 0;
        } else {
            i7 = iArr[k - 1];
        }
        return bArr[k][(i2 - i7) + iArr[bArr.length + k]];
    }

    public final String c() {
        return new c(l()).c();
    }

    public final byte[] d() {
        return l();
    }

    public final boolean e(int i2, int i7, int i8, byte[] bArr) {
        int i10;
        if (i2 >= 0 && i2 <= g() - i8 && i7 >= 0 && i7 <= bArr.length - i8) {
            int k = k(i2);
            while (i8 > 0) {
                int[] iArr = this.f5019j;
                if (k == 0) {
                    i10 = 0;
                } else {
                    i10 = iArr[k - 1];
                }
                int min = Math.min(i8, ((iArr[k] - i10) + i10) - i2);
                byte[][] bArr2 = this.f5018i;
                int i11 = (i2 - i10) + iArr[bArr2.length + k];
                byte[] bArr3 = bArr2[k];
                Charset charset = n.f5020a;
                int i12 = 0;
                while (i12 < min) {
                    if (bArr3[i12 + i11] == bArr[i12 + i7]) {
                        i12++;
                    }
                }
                i2 += min;
                i7 += min;
                i8 -= min;
                k++;
            }
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (cVar.g() != g() || !f(cVar, g())) {
            return false;
        }
        return true;
    }

    public final boolean f(c cVar, int i2) {
        int i7;
        if (g() - i2 >= 0) {
            int k = k(0);
            int i8 = 0;
            int i10 = 0;
            while (i2 > 0) {
                int[] iArr = this.f5019j;
                if (k == 0) {
                    i7 = 0;
                } else {
                    i7 = iArr[k - 1];
                }
                int min = Math.min(i2, ((iArr[k] - i7) + i7) - i8);
                byte[][] bArr = this.f5018i;
                if (cVar.e(i10, (i8 - i7) + iArr[bArr.length + k], min, bArr[k])) {
                    i8 += min;
                    i10 += min;
                    i2 -= min;
                    k++;
                }
            }
            return true;
        }
        return false;
    }

    public final int g() {
        return this.f5019j[this.f5018i.length - 1];
    }

    public final c h() {
        return new c(l()).h();
    }

    public final int hashCode() {
        int i2 = this.e;
        if (i2 != 0) {
            return i2;
        }
        byte[][] bArr = this.f5018i;
        int length = bArr.length;
        int i7 = 0;
        int i8 = 1;
        int i10 = 0;
        while (i7 < length) {
            byte[] bArr2 = bArr[i7];
            int[] iArr = this.f5019j;
            int i11 = iArr[length + i7];
            int i12 = iArr[i7];
            int i13 = (i12 - i10) + i11;
            while (i11 < i13) {
                i8 = (i8 * 31) + bArr2[i11];
                i11++;
            }
            i7++;
            i10 = i12;
        }
        this.e = i8;
        return i8;
    }

    public final String j() {
        return new c(l()).j();
    }

    public final int k(int i2) {
        int binarySearch = Arrays.binarySearch(this.f5019j, 0, this.f5018i.length, i2 + 1);
        if (binarySearch >= 0) {
            return binarySearch;
        }
        return ~binarySearch;
    }

    public final byte[] l() {
        byte[][] bArr = this.f5018i;
        int[] iArr = this.f5019j;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i2 = 0;
        int i7 = 0;
        while (i2 < length) {
            int i8 = iArr[length + i2];
            int i10 = iArr[i2];
            System.arraycopy(bArr[i2], i8, bArr2, i7, i10 - i7);
            i2++;
            i7 = i10;
        }
        return bArr2;
    }

    public final String toString() {
        return new c(l()).toString();
    }
}
