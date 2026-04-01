package com.google.protobuf;

import I0.a;
import com.samsung.android.sdk.cover.ScoverState;
import java.io.OutputStream;

/* renamed from: com.google.protobuf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0143o extends CodedOutputStream {

    /* renamed from: j  reason: collision with root package name */
    public final byte[] f1622j;
    public final int k;
    public int l;
    public final OutputStream m;

    public C0143o(OutputStream outputStream, int i2) {
        if (i2 >= 0) {
            int max = Math.max(i2, 20);
            this.f1622j = new byte[max];
            this.k = max;
            if (outputStream != null) {
                this.m = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }
        throw new IllegalArgumentException("bufferSize must be >= 0");
    }

    public final void A0(int i2) {
        if (this.k - this.l < i2) {
            z0();
        }
    }

    public final void B0(byte[] bArr, int i2, int i7) {
        int i8 = this.l;
        int i10 = this.k;
        int i11 = i10 - i8;
        byte[] bArr2 = this.f1622j;
        if (i11 >= i7) {
            System.arraycopy(bArr, i2, bArr2, i8, i7);
            this.l += i7;
            return;
        }
        System.arraycopy(bArr, i2, bArr2, i8, i11);
        int i12 = i2 + i11;
        int i13 = i7 - i11;
        this.l = i10;
        z0();
        if (i13 <= i10) {
            System.arraycopy(bArr, i12, bArr2, 0, i13);
            this.l = i13;
            return;
        }
        this.m.write(bArr, i12, i13);
    }

    public final void X(int i2, int i7, byte[] bArr) {
        B0(bArr, i2, i7);
    }

    public final void e0(byte b) {
        if (this.l == this.k) {
            z0();
        }
        int i2 = this.l;
        this.l = i2 + 1;
        this.f1622j[i2] = b;
    }

    public final void f0(int i2, boolean z) {
        A0(11);
        w0(i2, 0);
        byte b = z ? (byte) 1 : 0;
        int i7 = this.l;
        this.l = i7 + 1;
        this.f1622j[i7] = b;
    }

    public final void g0(int i2, ByteString byteString) {
        p0(i2, 2);
        r0(byteString.size());
        byteString.A(this);
    }

    public final void h0(int i2, int i7) {
        A0(14);
        w0(i2, 5);
        u0(i7);
    }

    public final void i0(int i2) {
        A0(4);
        u0(i2);
    }

    public final void j0(int i2, long j2) {
        A0(18);
        w0(i2, 1);
        v0(j2);
    }

    public final void k0(long j2) {
        A0(8);
        v0(j2);
    }

    public final void l0(int i2, int i7) {
        A0(20);
        w0(i2, 0);
        if (i7 >= 0) {
            x0(i7);
        } else {
            y0((long) i7);
        }
    }

    public final void m0(int i2) {
        if (i2 >= 0) {
            r0(i2);
        } else {
            t0((long) i2);
        }
    }

    public final void n0(int i2, MessageLite messageLite, Schema schema) {
        p0(i2, 2);
        r0(((AbstractMessageLite) messageLite).getSerializedSize(schema));
        schema.a(messageLite, this.g);
    }

    public final void o0(int i2, String str) {
        int i7;
        p0(i2, 2);
        try {
            int length = str.length() * 3;
            int b0 = CodedOutputStream.b0(length);
            int i8 = b0 + length;
            int i10 = this.k;
            if (i8 > i10) {
                byte[] bArr = new byte[length];
                int r = s0.f1629a.r(str, bArr, 0, length);
                r0(r);
                B0(bArr, 0, r);
                return;
            }
            if (i8 > i10 - this.l) {
                z0();
            }
            int b02 = CodedOutputStream.b0(str.length());
            i7 = this.l;
            byte[] bArr2 = this.f1622j;
            if (b02 == b0) {
                int i11 = i7 + b02;
                this.l = i11;
                int r5 = s0.f1629a.r(str, bArr2, i11, i10 - i11);
                this.l = i7;
                x0((r5 - i7) - b02);
                this.l = r5;
                return;
            }
            int b = s0.b(str);
            x0(b);
            this.l = s0.f1629a.r(str, bArr2, this.l, b);
        } catch (r0 e) {
            this.l = i7;
            throw e;
        } catch (ArrayIndexOutOfBoundsException e7) {
            throw new a(e7);
        } catch (r0 e8) {
            d0(str, e8);
        }
    }

    public final void p0(int i2, int i7) {
        r0((i2 << 3) | i7);
    }

    public final void q0(int i2, int i7) {
        A0(20);
        w0(i2, 0);
        x0(i7);
    }

    public final void r0(int i2) {
        A0(5);
        x0(i2);
    }

    public final void s0(int i2, long j2) {
        A0(20);
        w0(i2, 0);
        y0(j2);
    }

    public final void t0(long j2) {
        A0(10);
        y0(j2);
    }

    public final void u0(int i2) {
        int i7 = this.l;
        int i8 = i7 + 1;
        this.l = i8;
        byte[] bArr = this.f1622j;
        bArr[i7] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
        int i10 = i7 + 2;
        this.l = i10;
        bArr[i8] = (byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
        int i11 = i7 + 3;
        this.l = i11;
        bArr[i10] = (byte) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
        this.l = i7 + 4;
        bArr[i11] = (byte) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public final void v0(long j2) {
        int i2 = this.l;
        int i7 = i2 + 1;
        this.l = i7;
        byte[] bArr = this.f1622j;
        bArr[i2] = (byte) ((int) (j2 & 255));
        int i8 = i2 + 2;
        this.l = i8;
        bArr[i7] = (byte) ((int) ((j2 >> 8) & 255));
        int i10 = i2 + 3;
        this.l = i10;
        bArr[i8] = (byte) ((int) ((j2 >> 16) & 255));
        int i11 = i2 + 4;
        this.l = i11;
        bArr[i10] = (byte) ((int) (255 & (j2 >> 24)));
        int i12 = i2 + 5;
        this.l = i12;
        bArr[i11] = (byte) (((int) (j2 >> 32)) & ScoverState.TYPE_NFC_SMART_COVER);
        int i13 = i2 + 6;
        this.l = i13;
        bArr[i12] = (byte) (((int) (j2 >> 40)) & ScoverState.TYPE_NFC_SMART_COVER);
        int i14 = i2 + 7;
        this.l = i14;
        bArr[i13] = (byte) (((int) (j2 >> 48)) & ScoverState.TYPE_NFC_SMART_COVER);
        this.l = i2 + 8;
        bArr[i14] = (byte) (((int) (j2 >> 56)) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public final void w0(int i2, int i7) {
        x0((i2 << 3) | i7);
    }

    public final void x0(int i2) {
        boolean z = CodedOutputStream.f1577i;
        byte[] bArr = this.f1622j;
        if (z) {
            while ((i2 & -128) != 0) {
                int i7 = this.l;
                this.l = i7 + 1;
                p0.k(bArr, (long) i7, (byte) ((i2 & 127) | 128));
                i2 >>>= 7;
            }
            int i8 = this.l;
            this.l = i8 + 1;
            p0.k(bArr, (long) i8, (byte) i2);
            return;
        }
        while ((i2 & -128) != 0) {
            int i10 = this.l;
            this.l = i10 + 1;
            bArr[i10] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
        }
        int i11 = this.l;
        this.l = i11 + 1;
        bArr[i11] = (byte) i2;
    }

    public final void y0(long j2) {
        boolean z = CodedOutputStream.f1577i;
        byte[] bArr = this.f1622j;
        if (z) {
            while ((j2 & -128) != 0) {
                int i2 = this.l;
                this.l = i2 + 1;
                p0.k(bArr, (long) i2, (byte) ((((int) j2) & 127) | 128));
                j2 >>>= 7;
            }
            int i7 = this.l;
            this.l = i7 + 1;
            p0.k(bArr, (long) i7, (byte) ((int) j2));
            return;
        }
        while ((j2 & -128) != 0) {
            int i8 = this.l;
            this.l = i8 + 1;
            bArr[i8] = (byte) ((((int) j2) & 127) | 128);
            j2 >>>= 7;
        }
        int i10 = this.l;
        this.l = i10 + 1;
        bArr[i10] = (byte) ((int) j2);
    }

    public final void z0() {
        this.m.write(this.f1622j, 0, this.l);
        this.l = 0;
    }
}
