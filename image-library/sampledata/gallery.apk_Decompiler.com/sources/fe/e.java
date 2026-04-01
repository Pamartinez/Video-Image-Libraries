package fe;

import ee.C0992z;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends InputStream implements C0992z {
    public byte[][] d = null;
    public byte[] e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4344h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4345i;

    public e(byte[] bArr) {
        byte[] bArr2 = null;
        this.e = bArr.length > 0 ? bArr : bArr2;
        this.f4344h = bArr.length;
    }

    public final void a() {
        int i2 = this.f + 1;
        this.f = i2;
        this.g = 0;
        byte[][] bArr = this.d;
        if (bArr == null || i2 >= bArr.length) {
            this.e = null;
        } else {
            this.e = bArr[i2];
        }
    }

    public final int available() {
        return this.f4344h;
    }

    public final void close() {
        if (!this.f4345i) {
            this.f4345i = true;
            byte[][] bArr = this.d;
            if (bArr != null) {
                for (byte[] b : bArr) {
                    f.b(b);
                }
            } else {
                byte[] bArr2 = this.e;
                if (bArr2 != null) {
                    f.b(bArr2);
                }
            }
            this.e = null;
            this.d = null;
        }
    }

    public final int read() {
        byte[] bArr = this.e;
        if (bArr == null) {
            return -1;
        }
        int i2 = this.g;
        int i7 = i2 + 1;
        this.g = i7;
        byte b = bArr[i2];
        this.f4344h--;
        if (i7 == bArr.length) {
            a();
        }
        return b;
    }

    public final int read(byte[] bArr, int i2, int i7) {
        int i8 = i7;
        while (true) {
            byte[] bArr2 = this.e;
            if (bArr2 == null) {
                break;
            }
            int[] iArr = {i8, bArr2.length - this.g, this.f4344h};
            int i10 = iArr[0];
            for (int i11 = 1; i11 < 3; i11++) {
                int i12 = iArr[i11];
                if (i12 < i10) {
                    i10 = i12;
                }
            }
            System.arraycopy(this.e, this.g, bArr, i2, i10);
            i2 += i10;
            i8 -= i10;
            this.f4344h -= i10;
            if (i8 == 0) {
                int i13 = this.g + i10;
                this.g = i13;
                if (i13 == this.e.length) {
                    a();
                }
            } else {
                a();
            }
        }
        int i14 = i7 - i8;
        if (i14 > 0 || this.f4344h > 0) {
            return i14;
        }
        return -1;
    }
}
