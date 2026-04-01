package com.google.protobuf;

import I0.a;
import com.samsung.android.sdk.cover.ScoverState;

/* renamed from: com.google.protobuf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0142n extends CodedOutputStream {

    /* renamed from: j  reason: collision with root package name */
    public final byte[] f1621j;
    public final int k;
    public int l;

    public C0142n(byte[] bArr, int i2, int i7) {
        if (bArr != null) {
            int i8 = i2 + i7;
            if ((i2 | i7 | (bArr.length - i8)) >= 0) {
                this.f1621j = bArr;
                this.l = i2;
                this.k = i8;
                return;
            }
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i7)}));
        }
        throw new NullPointerException("buffer");
    }

    public final void X(int i2, int i7, byte[] bArr) {
        try {
            System.arraycopy(bArr, i2, this.f1621j, this.l, i7);
            this.l += i7;
        } catch (IndexOutOfBoundsException e) {
            throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), Integer.valueOf(i7)}), e);
        }
    }

    public final void e0(byte b) {
        try {
            byte[] bArr = this.f1621j;
            int i2 = this.l;
            this.l = i2 + 1;
            bArr[i2] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), 1}), e);
        }
    }

    public final void f0(int i2, boolean z) {
        p0(i2, 0);
        e0(z ? (byte) 1 : 0);
    }

    public final void g0(int i2, ByteString byteString) {
        p0(i2, 2);
        r0(byteString.size());
        byteString.A(this);
    }

    public final void h0(int i2, int i7) {
        p0(i2, 5);
        i0(i7);
    }

    public final void i0(int i2) {
        try {
            byte[] bArr = this.f1621j;
            int i7 = this.l;
            int i8 = i7 + 1;
            this.l = i8;
            bArr[i7] = (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
            int i10 = i7 + 2;
            this.l = i10;
            bArr[i8] = (byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER);
            int i11 = i7 + 3;
            this.l = i11;
            bArr[i10] = (byte) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER);
            this.l = i7 + 4;
            bArr[i11] = (byte) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER);
        } catch (IndexOutOfBoundsException e) {
            throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), 1}), e);
        }
    }

    public final void j0(int i2, long j2) {
        p0(i2, 1);
        k0(j2);
    }

    public final void k0(long j2) {
        try {
            byte[] bArr = this.f1621j;
            int i2 = this.l;
            int i7 = i2 + 1;
            this.l = i7;
            bArr[i2] = (byte) (((int) j2) & ScoverState.TYPE_NFC_SMART_COVER);
            int i8 = i2 + 2;
            this.l = i8;
            bArr[i7] = (byte) (((int) (j2 >> 8)) & ScoverState.TYPE_NFC_SMART_COVER);
            int i10 = i2 + 3;
            this.l = i10;
            bArr[i8] = (byte) (((int) (j2 >> 16)) & ScoverState.TYPE_NFC_SMART_COVER);
            int i11 = i2 + 4;
            this.l = i11;
            bArr[i10] = (byte) (((int) (j2 >> 24)) & ScoverState.TYPE_NFC_SMART_COVER);
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
        } catch (IndexOutOfBoundsException e) {
            throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), 1}), e);
        }
    }

    public final void l0(int i2, int i7) {
        p0(i2, 0);
        m0(i7);
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
        p0(i2, 2);
        int i7 = this.l;
        try {
            int b0 = CodedOutputStream.b0(str.length() * 3);
            int b02 = CodedOutputStream.b0(str.length());
            byte[] bArr = this.f1621j;
            if (b02 == b0) {
                int i8 = i7 + b02;
                this.l = i8;
                int r = s0.f1629a.r(str, bArr, i8, u0());
                this.l = i7;
                r0((r - i7) - b02);
                this.l = r;
                return;
            }
            r0(s0.b(str));
            this.l = s0.f1629a.r(str, bArr, this.l, u0());
        } catch (r0 e) {
            this.l = i7;
            d0(str, e);
        } catch (IndexOutOfBoundsException e7) {
            throw new a(e7);
        }
    }

    public final void p0(int i2, int i7) {
        r0((i2 << 3) | i7);
    }

    public final void q0(int i2, int i7) {
        p0(i2, 0);
        r0(i7);
    }

    public final void r0(int i2) {
        while (true) {
            int i7 = i2 & -128;
            byte[] bArr = this.f1621j;
            if (i7 == 0) {
                try {
                    int i8 = this.l;
                    this.l = i8 + 1;
                    bArr[i8] = (byte) i2;
                    return;
                } catch (IndexOutOfBoundsException e) {
                    throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), 1}), e);
                }
            } else {
                int i10 = this.l;
                this.l = i10 + 1;
                bArr[i10] = (byte) ((i2 & 127) | 128);
                i2 >>>= 7;
            }
        }
    }

    public final void s0(int i2, long j2) {
        p0(i2, 0);
        t0(j2);
    }

    public final void t0(long j2) {
        boolean z = CodedOutputStream.f1577i;
        byte[] bArr = this.f1621j;
        if (!z || u0() < 10) {
            while ((j2 & -128) != 0) {
                int i2 = this.l;
                this.l = i2 + 1;
                bArr[i2] = (byte) ((((int) j2) & 127) | 128);
                j2 >>>= 7;
            }
            try {
                int i7 = this.l;
                this.l = i7 + 1;
                bArr[i7] = (byte) ((int) j2);
            } catch (IndexOutOfBoundsException e) {
                throw new a(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.l), Integer.valueOf(this.k), 1}), e);
            }
        } else {
            while ((j2 & -128) != 0) {
                int i8 = this.l;
                this.l = i8 + 1;
                p0.k(bArr, (long) i8, (byte) ((((int) j2) & 127) | 128));
                j2 >>>= 7;
            }
            int i10 = this.l;
            this.l = i10 + 1;
            p0.k(bArr, (long) i10, (byte) ((int) j2));
        }
    }

    public final int u0() {
        return this.k - this.l;
    }
}
