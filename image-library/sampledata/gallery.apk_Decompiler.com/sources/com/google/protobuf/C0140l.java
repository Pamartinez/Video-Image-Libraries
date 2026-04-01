package com.google.protobuf;

import Gd.a;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: com.google.protobuf.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0140l extends CodedInputStream {
    public final ByteBuffer d;
    public final long e;
    public long f;
    public long g;

    /* renamed from: h  reason: collision with root package name */
    public final long f1616h;

    /* renamed from: i  reason: collision with root package name */
    public int f1617i;

    /* renamed from: j  reason: collision with root package name */
    public int f1618j;
    public int k = Integer.MAX_VALUE;

    public C0140l(ByteBuffer byteBuffer, boolean z) {
        this.d = byteBuffer;
        long j2 = p0.f1625c.j(byteBuffer, p0.g);
        this.e = j2;
        this.f = ((long) byteBuffer.limit()) + j2;
        long position = j2 + ((long) byteBuffer.position());
        this.g = position;
        this.f1616h = position;
    }

    public final int A() {
        return E();
    }

    public final long B() {
        return F();
    }

    public final int C() {
        long j2 = this.g;
        if (this.f - j2 >= 4) {
            this.g = 4 + j2;
            o0 o0Var = p0.f1625c;
            return ((o0Var.e(j2 + 3) & 255) << 24) | (o0Var.e(j2) & 255) | ((o0Var.e(1 + j2) & 255) << 8) | ((o0Var.e(2 + j2) & 255) << 16);
        }
        throw F.g();
    }

    public final long D() {
        long j2 = this.g;
        if (this.f - j2 >= 8) {
            this.g = 8 + j2;
            o0 o0Var = p0.f1625c;
            return ((((long) o0Var.e(j2 + 7)) & 255) << 56) | (((long) o0Var.e(j2)) & 255) | ((((long) o0Var.e(1 + j2)) & 255) << 8) | ((((long) o0Var.e(2 + j2)) & 255) << 16) | ((((long) o0Var.e(3 + j2)) & 255) << 24) | ((((long) o0Var.e(4 + j2)) & 255) << 32) | ((((long) o0Var.e(5 + j2)) & 255) << 40) | ((((long) o0Var.e(6 + j2)) & 255) << 48);
        }
        throw F.g();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0091, code lost:
        if (r4.e(r8) < 0) goto L_0x0093;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int E() {
        /*
            r12 = this;
            long r0 = r12.g
            long r2 = r12.f
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x000a
            goto L_0x0093
        L_0x000a:
            r2 = 1
            long r2 = r2 + r0
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            byte r5 = r4.e(r0)
            if (r5 < 0) goto L_0x0018
            r12.g = r2
            return r5
        L_0x0018:
            long r6 = r12.f
            long r6 = r6 - r2
            r8 = 9
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x0023
            goto L_0x0093
        L_0x0023:
            r6 = 2
            long r6 = r6 + r0
            byte r2 = r4.e(r2)
            int r2 = r2 << 7
            r2 = r2 ^ r5
            if (r2 >= 0) goto L_0x0033
            r0 = r2 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x00a0
        L_0x0033:
            r10 = 3
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            int r3 = r3 << 14
            r2 = r2 ^ r3
            if (r2 < 0) goto L_0x0043
            r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
        L_0x0041:
            r6 = r10
            goto L_0x00a0
        L_0x0043:
            r5 = 4
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            int r3 = r3 << 21
            r2 = r2 ^ r3
            if (r2 >= 0) goto L_0x0055
            r0 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x00a0
        L_0x0055:
            r10 = 5
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            int r5 = r3 << 28
            r2 = r2 ^ r5
            r5 = 266354560(0xfe03f80, float:2.2112565E-29)
            r2 = r2 ^ r5
            if (r3 >= 0) goto L_0x009e
            r5 = 6
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            if (r3 >= 0) goto L_0x0099
            r10 = 7
            long r10 = r10 + r0
            byte r3 = r4.e(r6)
            if (r3 >= 0) goto L_0x009e
            r5 = 8
            long r6 = r0 + r5
            byte r3 = r4.e(r10)
            if (r3 >= 0) goto L_0x0099
            long r8 = r8 + r0
            byte r3 = r4.e(r6)
            if (r3 >= 0) goto L_0x009b
            r5 = 10
            long r6 = r0 + r5
            byte r0 = r4.e(r8)
            if (r0 >= 0) goto L_0x0099
        L_0x0093:
            long r0 = r12.G()
            int r12 = (int) r0
            return r12
        L_0x0099:
            r0 = r2
            goto L_0x00a0
        L_0x009b:
            r0 = r2
            r6 = r8
            goto L_0x00a0
        L_0x009e:
            r0 = r2
            goto L_0x0041
        L_0x00a0:
            r12.g = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.C0140l.E():int");
    }

    public final long F() {
        long j2;
        long j3;
        long j8;
        byte b;
        long j10 = this.g;
        if (this.f != j10) {
            long j11 = 1 + j10;
            o0 o0Var = p0.f1625c;
            byte e7 = o0Var.e(j10);
            if (e7 >= 0) {
                this.g = j11;
                return (long) e7;
            } else if (this.f - j11 >= 9) {
                long j12 = 2 + j10;
                byte e8 = (o0Var.e(j11) << 7) ^ e7;
                if (e8 < 0) {
                    b = e8 ^ Byte.MIN_VALUE;
                } else {
                    long j13 = 3 + j10;
                    byte e9 = e8 ^ (o0Var.e(j12) << 14);
                    if (e9 >= 0) {
                        j2 = (long) (e9 ^ 16256);
                    } else {
                        j12 = j10 + 4;
                        byte e10 = e9 ^ (o0Var.e(j13) << 21);
                        if (e10 < 0) {
                            b = -2080896 ^ e10;
                        } else {
                            j13 = 5 + j10;
                            long e11 = ((long) e10) ^ (((long) o0Var.e(j12)) << 28);
                            if (e11 >= 0) {
                                j8 = 266354560;
                            } else {
                                long j14 = 6 + j10;
                                long e12 = e11 ^ (((long) o0Var.e(j13)) << 35);
                                if (e12 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    j13 = 7 + j10;
                                    e11 = e12 ^ (((long) o0Var.e(j14)) << 42);
                                    if (e11 >= 0) {
                                        j8 = 4363953127296L;
                                    } else {
                                        j14 = 8 + j10;
                                        e12 = e11 ^ (((long) o0Var.e(j13)) << 49);
                                        if (e12 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            long j15 = j10 + 9;
                                            long e13 = (e12 ^ (((long) o0Var.e(j14)) << 56)) ^ 71499008037633920L;
                                            if (e13 < 0) {
                                                long j16 = j10 + 10;
                                                if (((long) o0Var.e(j15)) >= 0) {
                                                    j12 = j16;
                                                    j2 = e13;
                                                }
                                            } else {
                                                j2 = e13;
                                                j12 = j15;
                                            }
                                            this.g = j12;
                                            return j2;
                                        }
                                    }
                                }
                                j2 = j3 ^ e12;
                                j12 = j14;
                                this.g = j12;
                                return j2;
                            }
                            j2 = j8 ^ e11;
                        }
                    }
                    j12 = j13;
                    this.g = j12;
                    return j2;
                }
                j2 = (long) b;
                this.g = j12;
                return j2;
            }
        }
        return G();
    }

    public final long G() {
        long j2 = 0;
        int i2 = 0;
        while (i2 < 64) {
            long j3 = this.g;
            if (j3 != this.f) {
                this.g = 1 + j3;
                byte e7 = p0.f1625c.e(j3);
                j2 |= ((long) (e7 & Byte.MAX_VALUE)) << i2;
                if ((e7 & 128) == 0) {
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
        long j2 = this.f + ((long) this.f1617i);
        this.f = j2;
        int i2 = (int) (j2 - this.f1616h);
        int i7 = this.k;
        if (i2 > i7) {
            int i8 = i2 - i7;
            this.f1617i = i8;
            this.f = j2 - ((long) i8);
            return;
        }
        this.f1617i = 0;
    }

    public final void a(int i2) {
        if (this.f1618j != i2) {
            throw new IOException("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int d() {
        return (int) (this.g - this.f1616h);
    }

    public final boolean e() {
        if (this.g == this.f) {
            return true;
        }
        return false;
    }

    public final void h(int i2) {
        this.k = i2;
        H();
    }

    public final int i(int i2) {
        if (i2 >= 0) {
            int d2 = d() + i2;
            int i7 = this.k;
            if (d2 <= i7) {
                this.k = d2;
                H();
                return i7;
            }
            throw F.g();
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
        int E = E();
        if (E > 0) {
            long j2 = this.f;
            long j3 = this.g;
            if (E <= ((int) (j2 - j3))) {
                byte[] bArr = new byte[E];
                long j8 = (long) E;
                p0.f1625c.c(j3, j8, bArr);
                this.g += j8;
                C0137i iVar = ByteString.e;
                return new C0137i(bArr);
            }
        }
        if (E == 0) {
            return ByteString.e;
        }
        if (E < 0) {
            throw F.e();
        }
        throw F.g();
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
            long j2 = this.f;
            long j3 = this.g;
            if (E <= ((int) (j2 - j3))) {
                byte[] bArr = new byte[E];
                long j8 = (long) E;
                p0.f1625c.c(j3, j8, bArr);
                String str = new String(bArr, D.f1578a);
                this.g += j8;
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
        String str;
        int E = E();
        if (E > 0) {
            long j2 = this.f;
            long j3 = this.g;
            if (E <= ((int) (j2 - j3))) {
                int i2 = (int) (j3 - this.e);
                a aVar = s0.f1629a;
                aVar.getClass();
                ByteBuffer byteBuffer = this.d;
                if (byteBuffer.hasArray()) {
                    str = aVar.o(byteBuffer.arrayOffset() + i2, E, byteBuffer.array());
                } else if (byteBuffer.isDirect()) {
                    str = aVar.q(byteBuffer, i2, E);
                } else {
                    str = a.p(byteBuffer, i2, E);
                }
                this.g += (long) E;
                return str;
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
            this.f1618j = 0;
            return 0;
        }
        int E = E();
        this.f1618j = E;
        if ((E >>> 3) != 0) {
            return E;
        }
        throw F.a();
    }
}
