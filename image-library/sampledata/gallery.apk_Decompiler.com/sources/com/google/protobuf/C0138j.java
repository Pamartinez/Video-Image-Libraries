package com.google.protobuf;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.google.protobuf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0138j extends CodedInputStream {
    public final byte[] d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1610h;

    /* renamed from: i  reason: collision with root package name */
    public int f1611i;

    /* renamed from: j  reason: collision with root package name */
    public int f1612j = Integer.MAX_VALUE;

    public C0138j(byte[] bArr, int i2, int i7, boolean z) {
        this.d = bArr;
        this.e = i7 + i2;
        this.g = i2;
        this.f1610h = i2;
    }

    public final int A() {
        return E();
    }

    public final long B() {
        return F();
    }

    public final int C() {
        int i2 = this.g;
        if (this.e - i2 >= 4) {
            this.g = i2 + 4;
            byte[] bArr = this.d;
            return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        }
        throw F.g();
    }

    public final long D() {
        int i2 = this.g;
        if (this.e - i2 >= 8) {
            this.g = i2 + 8;
            byte[] bArr = this.d;
            return ((((long) bArr[i2 + 1]) & 255) << 8) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
        }
        throw F.g();
    }

    public final int E() {
        byte b;
        byte b5;
        int i2 = this.g;
        int i7 = this.e;
        if (i7 != i2) {
            int i8 = i2 + 1;
            byte[] bArr = this.d;
            byte b8 = bArr[i2];
            if (b8 >= 0) {
                this.g = i8;
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
                this.g = i10;
                return b;
            }
        }
        return (int) G();
    }

    public final long F() {
        long j2;
        long j3;
        long j8;
        long j10;
        int i2 = this.g;
        int i7 = this.e;
        if (i7 != i2) {
            int i8 = i2 + 1;
            byte[] bArr = this.d;
            byte b = bArr[i2];
            if (b >= 0) {
                this.g = i8;
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
                this.g = i10;
                return j2;
            }
        }
        return G();
    }

    public final long G() {
        long j2 = 0;
        int i2 = 0;
        while (i2 < 64) {
            int i7 = this.g;
            if (i7 != this.e) {
                this.g = i7 + 1;
                byte b = this.d[i7];
                j2 |= ((long) (b & Byte.MAX_VALUE)) << i2;
                if ((b & 128) == 0) {
                    return j2;
                }
                i2 += 7;
            } else {
                throw F.g();
            }
        }
        throw F.d();
    }

    public final void H() {
        int i2 = this.e + this.f;
        this.e = i2;
        int i7 = i2 - this.f1610h;
        int i8 = this.f1612j;
        if (i7 > i8) {
            int i10 = i7 - i8;
            this.f = i10;
            this.e = i2 - i10;
            return;
        }
        this.f = 0;
    }

    public final void a(int i2) {
        if (this.f1611i != i2) {
            throw new IOException("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int d() {
        return this.g - this.f1610h;
    }

    public final boolean e() {
        if (this.g == this.e) {
            return true;
        }
        return false;
    }

    public final void h(int i2) {
        this.f1612j = i2;
        H();
    }

    public final int i(int i2) {
        if (i2 >= 0) {
            int d2 = d() + i2;
            if (d2 >= 0) {
                int i7 = this.f1612j;
                if (d2 <= i7) {
                    this.f1612j = d2;
                    H();
                    return i7;
                }
                throw F.g();
            }
            throw F.f();
        }
        throw F.e();
    }

    public final boolean j() {
        if (F() != 0) {
            return true;
        }
        return false;
    }

    public final C0137i k() {
        byte[] bArr;
        int E = E();
        byte[] bArr2 = this.d;
        if (E > 0) {
            int i2 = this.e;
            int i7 = this.g;
            if (E <= i2 - i7) {
                C0137i r = ByteString.r(i7, E, bArr2);
                this.g += E;
                return r;
            }
        }
        if (E == 0) {
            return ByteString.e;
        }
        if (E > 0) {
            int i8 = this.e;
            int i10 = this.g;
            if (E <= i8 - i10) {
                int i11 = E + i10;
                this.g = i11;
                bArr = Arrays.copyOfRange(bArr2, i10, i11);
                C0137i iVar = ByteString.e;
                return new C0137i(bArr);
            }
        }
        if (E > 0) {
            throw F.g();
        } else if (E == 0) {
            bArr = D.b;
            C0137i iVar2 = ByteString.e;
            return new C0137i(bArr);
        } else {
            throw F.e();
        }
    }

    public final double l() {
        return Double.longBitsToDouble(D());
    }

    public final int m() {
        return E();
    }

    public final int n() {
        return C();
    }

    public final long o() {
        return D();
    }

    public final float p() {
        return Float.intBitsToFloat(C());
    }

    public final int q() {
        return E();
    }

    public final long r() {
        return F();
    }

    public final int t() {
        return C();
    }

    public final long u() {
        return D();
    }

    public final int v() {
        return CodedInputStream.b(E());
    }

    public final long w() {
        return CodedInputStream.c(F());
    }

    public final String x() {
        int E = E();
        if (E > 0) {
            int i2 = this.e;
            int i7 = this.g;
            if (E <= i2 - i7) {
                String str = new String(this.d, i7, E, D.f1578a);
                this.g += E;
                return str;
            }
        }
        if (E == 0) {
            return "";
        }
        if (E < 0) {
            throw F.e();
        }
        throw F.g();
    }

    public final String y() {
        int E = E();
        if (E > 0) {
            int i2 = this.e;
            int i7 = this.g;
            if (E <= i2 - i7) {
                String o2 = s0.f1629a.o(i7, E, this.d);
                this.g += E;
                return o2;
            }
        }
        if (E == 0) {
            return "";
        }
        if (E <= 0) {
            throw F.e();
        }
        throw F.g();
    }

    public final int z() {
        if (e()) {
            this.f1611i = 0;
            return 0;
        }
        int E = E();
        this.f1611i = E;
        if ((E >>> 3) != 0) {
            return E;
        }
        throw F.a();
    }
}
