package com.google.android.appfunctions.schema.internal.dependencies;

import I0.a;
import N2.j;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.io.IOException;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h0 extends c {

    /* renamed from: h  reason: collision with root package name */
    public static final boolean f1225h = W.e;
    public C0114y d;
    public final byte[] e;
    public final int f;
    public int g;

    public h0(byte[] bArr, int i2) {
        super(12);
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.e = bArr;
            this.g = 0;
            this.f = i2;
            return;
        }
        Locale locale = Locale.US;
        throw new IllegalArgumentException(j.b(length, i2, "Array range is invalid. Buffer.length=", ", offset=0, length="));
    }

    public static int l0(int i2) {
        return (352 - (Integer.numberOfLeadingZeros(i2) * 9)) >>> 6;
    }

    public static int m0(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    public final void Z(int i2, int i7) {
        h0((i2 << 3) | i7);
    }

    public final void a0(int i2, int i7) {
        h0(i2 << 3);
        h0(i7);
    }

    public final void b0(int i2, int i7) {
        h0((i2 << 3) | 5);
        i0(i7);
    }

    public final void c0(int i2, long j2) {
        h0(i2 << 3);
        j0(j2);
    }

    public final void d0(int i2, int i7, byte[] bArr) {
        try {
            System.arraycopy(bArr, i2, this.e, this.g, i7);
            this.g += i7;
        } catch (IndexOutOfBoundsException e7) {
            throw new a((long) this.g, (long) this.f, i7, e7);
        }
    }

    public final void e0(int i2, long j2) {
        h0((i2 << 3) | 1);
        k0(j2);
    }

    public final void f0(int i2, String str) {
        h0((i2 << 3) | 2);
        int i7 = this.g;
        try {
            int l0 = l0(str.length() * 3);
            int l02 = l0(str.length());
            int i8 = this.f;
            byte[] bArr = this.e;
            if (l02 == l0) {
                int i10 = i7 + l02;
                this.g = i10;
                int K6 = Z.f1218a.K(str, bArr, i10, i8 - i10);
                this.g = i7;
                h0((K6 - i7) - l02);
                this.g = K6;
                return;
            }
            h0(Z.a(str));
            int i11 = this.g;
            this.g = Z.f1218a.K(str, bArr, i11, i8 - i11);
        } catch (IndexOutOfBoundsException e7) {
            throw new IOException("CodedOutputStream was writing to a flat byte array and ran out of space.", e7);
        }
    }

    public final void g0(int i2, f0 f0Var) {
        h0((i2 << 3) | 2);
        h0(f0Var.r());
        f0Var.t(this);
    }

    public final void h0(int i2) {
        int i7;
        int i8 = this.g;
        while (true) {
            int i10 = i2 & -128;
            byte[] bArr = this.e;
            if (i10 == 0) {
                i7 = i8 + 1;
                try {
                    bArr[i8] = (byte) i2;
                    this.g = i7;
                    return;
                } catch (IndexOutOfBoundsException e7) {
                    throw new a((long) i7, (long) this.f, 1, e7);
                }
            } else {
                i7 = i8 + 1;
                bArr[i8] = (byte) (i2 | 128);
                i2 >>>= 7;
                i8 = i7;
            }
        }
    }

    public final void i0(int i2) {
        int i7 = this.g;
        try {
            byte[] bArr = this.e;
            bArr[i7] = (byte) i2;
            bArr[i7 + 1] = (byte) (i2 >> 8);
            bArr[i7 + 2] = (byte) (i2 >> 16);
            bArr[i7 + 3] = (byte) (i2 >> 24);
            this.g = i7 + 4;
        } catch (IndexOutOfBoundsException e7) {
            throw new a((long) i7, (long) this.f, 4, e7);
        }
    }

    public final void j0(long j2) {
        int i2;
        int i7 = this.g;
        int i8 = this.f;
        byte[] bArr = this.e;
        if (!f1225h || i8 - i7 < 10) {
            long j3 = j2;
            while ((j3 & -128) != 0) {
                int i10 = i7 + 1;
                try {
                    bArr[i7] = (byte) (((int) j3) | 128);
                    j3 >>>= 7;
                    i7 = i10;
                } catch (IndexOutOfBoundsException e7) {
                    e = e7;
                    i2 = i10;
                    throw new a((long) i2, (long) i8, 1, e);
                }
            }
            i2 = i7 + 1;
            try {
                bArr[i7] = (byte) ((int) j3);
            } catch (IndexOutOfBoundsException e8) {
                e = e8;
                throw new a((long) i2, (long) i8, 1, e);
            }
        } else {
            long j8 = j2;
            while ((j8 & -128) != 0) {
                W.f1217c.a(bArr, W.f + ((long) i7), (byte) (((int) j8) | 128));
                j8 >>>= 7;
                i7++;
            }
            i2 = i7 + 1;
            W.f1217c.a(bArr, W.f + ((long) i7), (byte) ((int) j8));
        }
        this.g = i2;
    }

    public final void k0(long j2) {
        int i2 = this.g;
        try {
            byte[] bArr = this.e;
            bArr[i2] = (byte) ((int) j2);
            bArr[i2 + 1] = (byte) ((int) (j2 >> 8));
            bArr[i2 + 2] = (byte) ((int) (j2 >> 16));
            bArr[i2 + 3] = (byte) ((int) (j2 >> 24));
            bArr[i2 + 4] = (byte) ((int) (j2 >> 32));
            bArr[i2 + 5] = (byte) ((int) (j2 >> 40));
            bArr[i2 + 6] = (byte) ((int) (j2 >> 48));
            bArr[i2 + 7] = (byte) ((int) (j2 >> 56));
            this.g = i2 + 8;
        } catch (IndexOutOfBoundsException e7) {
            throw new a((long) i2, (long) this.f, 8, e7);
        }
    }
}
