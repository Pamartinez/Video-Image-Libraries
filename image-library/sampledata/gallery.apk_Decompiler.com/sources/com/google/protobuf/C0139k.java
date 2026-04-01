package com.google.protobuf;

import i.C0212a;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.google.protobuf.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0139k extends CodedInputStream {
    public final InputStream d;
    public final byte[] e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f1613h;

    /* renamed from: i  reason: collision with root package name */
    public int f1614i;

    /* renamed from: j  reason: collision with root package name */
    public int f1615j;
    public int k = Integer.MAX_VALUE;

    public C0139k(InputStream inputStream) {
        D.a(inputStream, "input");
        this.d = inputStream;
        this.e = new byte[4096];
        this.f = 0;
        this.f1613h = 0;
        this.f1615j = 0;
    }

    public final int A() {
        return H();
    }

    public final long B() {
        return I();
    }

    public final byte[] C(int i2) {
        byte[] D = D(i2);
        if (D != null) {
            return D;
        }
        int i7 = this.f1613h;
        int i8 = this.f;
        int i10 = i8 - i7;
        this.f1615j += i8;
        this.f1613h = 0;
        this.f = 0;
        ArrayList E = E(i2 - i10);
        byte[] bArr = new byte[i2];
        System.arraycopy(this.e, i7, bArr, 0, i10);
        Iterator it = E.iterator();
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            System.arraycopy(bArr2, 0, bArr, i10, bArr2.length);
            i10 += bArr2.length;
        }
        return bArr;
    }

    public final byte[] D(int i2) {
        if (i2 == 0) {
            return D.b;
        }
        if (i2 >= 0) {
            int i7 = this.f1615j;
            int i8 = this.f1613h;
            int i10 = i7 + i8 + i2;
            if (i10 - Integer.MAX_VALUE <= 0) {
                int i11 = this.k;
                if (i10 <= i11) {
                    int i12 = this.f - i8;
                    int i13 = i2 - i12;
                    InputStream inputStream = this.d;
                    if (i13 >= 4096) {
                        try {
                            if (i13 > inputStream.available()) {
                                return null;
                            }
                        } catch (F e7) {
                            e7.d = true;
                            throw e7;
                        }
                    }
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.e, this.f1613h, bArr, 0, i12);
                    this.f1615j += this.f;
                    this.f1613h = 0;
                    this.f = 0;
                    while (i12 < i2) {
                        try {
                            int read = inputStream.read(bArr, i12, i2 - i12);
                            if (read != -1) {
                                this.f1615j += read;
                                i12 += read;
                            } else {
                                throw F.g();
                            }
                        } catch (F e8) {
                            e8.d = true;
                            throw e8;
                        }
                    }
                    return bArr;
                }
                M((i11 - i7) - i8);
                throw F.g();
            }
            throw new IOException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        throw F.e();
    }

    public final ArrayList E(int i2) {
        ArrayList arrayList = new ArrayList();
        while (i2 > 0) {
            int min = Math.min(i2, 4096);
            byte[] bArr = new byte[min];
            int i7 = 0;
            while (i7 < min) {
                int read = this.d.read(bArr, i7, min - i7);
                if (read != -1) {
                    this.f1615j += read;
                    i7 += read;
                } else {
                    throw F.g();
                }
            }
            i2 -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public final int F() {
        int i2 = this.f1613h;
        if (this.f - i2 < 4) {
            L(4);
            i2 = this.f1613h;
        }
        this.f1613h = i2 + 4;
        byte[] bArr = this.e;
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public final long G() {
        int i2 = this.f1613h;
        if (this.f - i2 < 8) {
            L(8);
            i2 = this.f1613h;
        }
        this.f1613h = i2 + 8;
        byte[] bArr = this.e;
        return ((((long) bArr[i2 + 1]) & 255) << 8) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
    }

    public final int H() {
        byte b;
        byte b5;
        int i2 = this.f1613h;
        int i7 = this.f;
        if (i7 != i2) {
            int i8 = i2 + 1;
            byte[] bArr = this.e;
            byte b8 = bArr[i2];
            if (b8 >= 0) {
                this.f1613h = i8;
                return b8;
            } else if (i7 - i8 >= 9) {
                int i10 = i2 + 2;
                byte b10 = (bArr[i8] << 7) ^ b8;
                if (b10 < 0) {
                    b = b10 ^ Byte.MIN_VALUE;
                } else {
                    int i11 = i2 + 3;
                    byte b11 = (bArr[i10] << 14) ^ b10;
                    if (b11 >= 0) {
                        b5 = b11 ^ 16256;
                    } else {
                        int i12 = i2 + 4;
                        byte b12 = b11 ^ (bArr[i11] << 21);
                        if (b12 < 0) {
                            b = -2080896 ^ b12;
                        } else {
                            i11 = i2 + 5;
                            byte b13 = bArr[i12];
                            byte b14 = (b12 ^ (b13 << 28)) ^ 266354560;
                            if (b13 < 0) {
                                i12 = i2 + 6;
                                if (bArr[i11] < 0) {
                                    i11 = i2 + 7;
                                    if (bArr[i12] < 0) {
                                        i12 = i2 + 8;
                                        if (bArr[i11] < 0) {
                                            i11 = i2 + 9;
                                            if (bArr[i12] < 0) {
                                                int i13 = i2 + 10;
                                                if (bArr[i11] >= 0) {
                                                    byte b15 = b14;
                                                    i10 = i13;
                                                    b = b15;
                                                }
                                            }
                                        }
                                    }
                                }
                                b = b14;
                            }
                            b5 = b14;
                        }
                        i10 = i12;
                    }
                    i10 = i11;
                }
                this.f1613h = i10;
                return b;
            }
        }
        return (int) J();
    }

    public final long I() {
        long j2;
        long j3;
        long j8;
        long j10;
        int i2 = this.f1613h;
        int i7 = this.f;
        if (i7 != i2) {
            int i8 = i2 + 1;
            byte[] bArr = this.e;
            byte b = bArr[i2];
            if (b >= 0) {
                this.f1613h = i8;
                return (long) b;
            } else if (i7 - i8 >= 9) {
                int i10 = i2 + 2;
                byte b5 = (bArr[i8] << 7) ^ b;
                if (b5 < 0) {
                    j2 = (long) (b5 ^ Byte.MIN_VALUE);
                } else {
                    int i11 = i2 + 3;
                    byte b8 = (bArr[i10] << 14) ^ b5;
                    if (b8 >= 0) {
                        j2 = (long) (b8 ^ 16256);
                        i10 = i11;
                    } else {
                        int i12 = i2 + 4;
                        byte b10 = b8 ^ (bArr[i11] << 21);
                        if (b10 < 0) {
                            j10 = (long) (-2080896 ^ b10);
                        } else {
                            long j11 = (long) b10;
                            int i13 = i2 + 5;
                            long j12 = j11 ^ (((long) bArr[i12]) << 28);
                            if (j12 >= 0) {
                                j8 = 266354560;
                            } else {
                                i12 = i2 + 6;
                                long j13 = j12 ^ (((long) bArr[i13]) << 35);
                                if (j13 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i13 = i2 + 7;
                                    j12 = j13 ^ (((long) bArr[i12]) << 42);
                                    if (j12 >= 0) {
                                        j8 = 4363953127296L;
                                    } else {
                                        i12 = i2 + 8;
                                        j13 = j12 ^ (((long) bArr[i13]) << 49);
                                        if (j13 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i10 = i2 + 9;
                                            long j14 = (j13 ^ (((long) bArr[i12]) << 56)) ^ 71499008037633920L;
                                            if (j14 < 0) {
                                                int i14 = i2 + 10;
                                                if (((long) bArr[i10]) >= 0) {
                                                    i10 = i14;
                                                }
                                            }
                                            j2 = j14;
                                        }
                                    }
                                }
                                j10 = j3 ^ j13;
                            }
                            j2 = j8 ^ j12;
                        }
                        i10 = i12;
                        j2 = j10;
                    }
                }
                this.f1613h = i10;
                return j2;
            }
        }
        return J();
    }

    public final long J() {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            if (this.f1613h == this.f) {
                L(1);
            }
            int i7 = this.f1613h;
            this.f1613h = i7 + 1;
            byte b = this.e[i7];
            j2 |= ((long) (b & Byte.MAX_VALUE)) << i2;
            if ((b & 128) == 0) {
                return j2;
            }
        }
        throw F.d();
    }

    public final void K() {
        int i2 = this.f + this.g;
        this.f = i2;
        int i7 = this.f1615j + i2;
        int i8 = this.k;
        if (i7 > i8) {
            int i10 = i7 - i8;
            this.g = i10;
            this.f = i2 - i10;
            return;
        }
        this.g = 0;
    }

    public final void L(int i2) {
        if (N(i2)) {
            return;
        }
        if (i2 > (Integer.MAX_VALUE - this.f1615j) - this.f1613h) {
            throw new IOException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        throw F.g();
    }

    public final void M(int i2) {
        int i7 = this.f;
        int i8 = this.f1613h;
        if (i2 > i7 - i8 || i2 < 0) {
            InputStream inputStream = this.d;
            if (i2 >= 0) {
                int i10 = this.f1615j;
                int i11 = i10 + i8;
                int i12 = i11 + i2;
                int i13 = this.k;
                if (i12 <= i13) {
                    this.f1615j = i11;
                    int i14 = i7 - i8;
                    this.f = 0;
                    this.f1613h = 0;
                    while (i14 < i2) {
                        long j2 = (long) (i2 - i14);
                        try {
                            long skip = inputStream.skip(j2);
                            int i15 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
                            if (i15 < 0 || skip > j2) {
                                throw new IllegalStateException(inputStream.getClass() + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                            } else if (i15 == 0) {
                                break;
                            } else {
                                i14 += (int) skip;
                            }
                        } catch (F e7) {
                            e7.d = true;
                            throw e7;
                        } catch (Throwable th) {
                            this.f1615j += i14;
                            K();
                            throw th;
                        }
                    }
                    this.f1615j += i14;
                    K();
                    if (i14 < i2) {
                        int i16 = this.f;
                        int i17 = i16 - this.f1613h;
                        this.f1613h = i16;
                        L(1);
                        while (true) {
                            int i18 = i2 - i17;
                            int i19 = this.f;
                            if (i18 > i19) {
                                i17 += i19;
                                this.f1613h = i19;
                                L(1);
                            } else {
                                this.f1613h = i18;
                                return;
                            }
                        }
                    }
                } else {
                    M((i13 - i10) - i8);
                    throw F.g();
                }
            } else {
                throw F.e();
            }
        } else {
            this.f1613h = i8 + i2;
        }
    }

    public final boolean N(int i2) {
        InputStream inputStream = this.d;
        int i7 = this.f1613h;
        int i8 = i7 + i2;
        int i10 = this.f;
        if (i8 > i10) {
            int i11 = this.f1615j;
            if (i2 <= (Integer.MAX_VALUE - i11) - i7 && i11 + i7 + i2 <= this.k) {
                byte[] bArr = this.e;
                if (i7 > 0) {
                    if (i10 > i7) {
                        System.arraycopy(bArr, i7, bArr, 0, i10 - i7);
                    }
                    this.f1615j += i7;
                    this.f -= i7;
                    this.f1613h = 0;
                }
                int i12 = this.f;
                try {
                    int read = inputStream.read(bArr, i12, Math.min(bArr.length - i12, (Integer.MAX_VALUE - this.f1615j) - i12));
                    if (read == 0 || read < -1 || read > bArr.length) {
                        throw new IllegalStateException(inputStream.getClass() + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                    } else if (read > 0) {
                        this.f += read;
                        K();
                        if (this.f >= i2) {
                            return true;
                        }
                        return N(i2);
                    }
                } catch (F e7) {
                    e7.d = true;
                    throw e7;
                }
            }
            return false;
        }
        throw new IllegalStateException(C0212a.j(i2, "refillBuffer() called when ", " bytes were already available in buffer"));
    }

    public final void a(int i2) {
        if (this.f1614i != i2) {
            throw new IOException("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int d() {
        return this.f1615j + this.f1613h;
    }

    public final boolean e() {
        if (this.f1613h != this.f || N(1)) {
            return false;
        }
        return true;
    }

    public final void h(int i2) {
        this.k = i2;
        K();
    }

    public final int i(int i2) {
        if (i2 >= 0) {
            int i7 = this.f1615j + this.f1613h + i2;
            int i8 = this.k;
            if (i7 <= i8) {
                this.k = i7;
                K();
                return i8;
            }
            throw F.g();
        }
        throw F.e();
    }

    public final boolean j() {
        if (I() != 0) {
            return true;
        }
        return false;
    }

    public final C0137i k() {
        int H5 = H();
        int i2 = this.f;
        int i7 = this.f1613h;
        int i8 = i2 - i7;
        byte[] bArr = this.e;
        if (H5 <= i8 && H5 > 0) {
            C0137i r = ByteString.r(i7, H5, bArr);
            this.f1613h += H5;
            return r;
        } else if (H5 == 0) {
            return ByteString.e;
        } else {
            byte[] D = D(H5);
            if (D != null) {
                return ByteString.r(0, D.length, D);
            }
            int i10 = this.f1613h;
            int i11 = this.f;
            int i12 = i11 - i10;
            this.f1615j += i11;
            this.f1613h = 0;
            this.f = 0;
            ArrayList E = E(H5 - i12);
            byte[] bArr2 = new byte[H5];
            System.arraycopy(bArr, i10, bArr2, 0, i12);
            Iterator it = E.iterator();
            while (it.hasNext()) {
                byte[] bArr3 = (byte[]) it.next();
                System.arraycopy(bArr3, 0, bArr2, i12, bArr3.length);
                i12 += bArr3.length;
            }
            C0137i iVar = ByteString.e;
            return new C0137i(bArr2);
        }
    }

    public final double l() {
        return Double.longBitsToDouble(G());
    }

    public final int m() {
        return H();
    }

    public final int n() {
        return F();
    }

    public final long o() {
        return G();
    }

    public final float p() {
        return Float.intBitsToFloat(F());
    }

    public final int q() {
        return H();
    }

    public final long r() {
        return I();
    }

    public final int t() {
        return F();
    }

    public final long u() {
        return G();
    }

    public final int v() {
        return CodedInputStream.b(H());
    }

    public final long w() {
        return CodedInputStream.c(I());
    }

    public final String x() {
        int H5 = H();
        byte[] bArr = this.e;
        if (H5 > 0) {
            int i2 = this.f;
            int i7 = this.f1613h;
            if (H5 <= i2 - i7) {
                String str = new String(bArr, i7, H5, D.f1578a);
                this.f1613h += H5;
                return str;
            }
        }
        if (H5 == 0) {
            return "";
        }
        if (H5 > this.f) {
            return new String(C(H5), D.f1578a);
        }
        L(H5);
        String str2 = new String(bArr, this.f1613h, H5, D.f1578a);
        this.f1613h += H5;
        return str2;
    }

    public final String y() {
        int H5 = H();
        int i2 = this.f1613h;
        int i7 = this.f;
        int i8 = i7 - i2;
        byte[] bArr = this.e;
        if (H5 <= i8 && H5 > 0) {
            this.f1613h = i2 + H5;
        } else if (H5 == 0) {
            return "";
        } else {
            i2 = 0;
            if (H5 <= i7) {
                L(H5);
                this.f1613h = H5;
            } else {
                bArr = C(H5);
            }
        }
        return s0.f1629a.o(i2, H5, bArr);
    }

    public final int z() {
        if (e()) {
            this.f1614i = 0;
            return 0;
        }
        int H5 = H();
        this.f1614i = H5;
        if ((H5 >>> 3) != 0) {
            return H5;
        }
        throw F.a();
    }
}
