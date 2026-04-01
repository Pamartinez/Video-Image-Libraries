package rf;

import B2.o;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: rf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1256f {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f5058a = new byte[4096];
    public int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public int f5059c;
    public int d = 0;
    public final InputStream e;
    public int f;
    public int g = 0;

    /* renamed from: h  reason: collision with root package name */
    public int f5060h = Integer.MAX_VALUE;

    /* renamed from: i  reason: collision with root package name */
    public int f5061i;

    public C1256f(InputStream inputStream) {
        this.e = inputStream;
    }

    public final void a(int i2) {
        if (this.f != i2) {
            throw new u("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int b() {
        int i2 = this.f5060h;
        if (i2 == Integer.MAX_VALUE) {
            return -1;
        }
        return i2 - (this.g + this.d);
    }

    public final void c(int i2) {
        this.f5060h = i2;
        o();
    }

    public final int d(int i2) {
        if (i2 >= 0) {
            int i7 = this.g + this.d + i2;
            int i8 = this.f5060h;
            if (i7 <= i8) {
                this.f5060h = i7;
                o();
                return i8;
            }
            throw u.a();
        }
        throw new u("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public final x e() {
        int k = k();
        int i2 = this.b;
        int i7 = this.d;
        if (k <= i2 - i7 && k > 0) {
            byte[] bArr = new byte[k];
            System.arraycopy(this.f5058a, i7, bArr, 0, k);
            x xVar = new x(bArr);
            this.d += k;
            return xVar;
        } else if (k == 0) {
            return C1255e.d;
        } else {
            return new x(h(k));
        }
    }

    public final int f() {
        return k();
    }

    public final C1252b g(z zVar, C1258h hVar) {
        int k = k();
        if (this.f5061i < 64) {
            int d2 = d(k);
            this.f5061i++;
            C1252b bVar = (C1252b) zVar.a(this, hVar);
            a(0);
            this.f5061i--;
            c(d2);
            return bVar;
        }
        throw new u("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final byte[] h(int i2) {
        if (i2 > 0) {
            int i7 = this.g;
            int i8 = this.d;
            int i10 = i7 + i8 + i2;
            int i11 = this.f5060h;
            if (i10 <= i11) {
                byte[] bArr = this.f5058a;
                if (i2 < 4096) {
                    byte[] bArr2 = new byte[i2];
                    int i12 = this.b - i8;
                    System.arraycopy(bArr, i8, bArr2, 0, i12);
                    this.d = this.b;
                    int i13 = i2 - i12;
                    if (i13 > 0) {
                        p(i13);
                    }
                    System.arraycopy(bArr, 0, bArr2, i12, i13);
                    this.d = i13;
                    return bArr2;
                }
                int i14 = this.b;
                this.g = i7 + i14;
                this.d = 0;
                this.b = 0;
                int i15 = i14 - i8;
                int i16 = i2 - i15;
                ArrayList arrayList = new ArrayList();
                while (i16 > 0) {
                    int min = Math.min(i16, 4096);
                    byte[] bArr3 = new byte[min];
                    int i17 = 0;
                    while (i17 < min) {
                        int read = this.e.read(bArr3, i17, min - i17);
                        if (read != -1) {
                            this.g += read;
                            i17 += read;
                        } else {
                            throw u.a();
                        }
                    }
                    i16 -= min;
                    arrayList.add(bArr3);
                }
                byte[] bArr4 = new byte[i2];
                System.arraycopy(bArr, i8, bArr4, 0, i15);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    byte[] bArr5 = (byte[]) it.next();
                    System.arraycopy(bArr5, 0, bArr4, i15, bArr5.length);
                    i15 += bArr5.length;
                }
                return bArr4;
            }
            r((i11 - i7) - i8);
            throw u.a();
        } else if (i2 == 0) {
            return t.f5070a;
        } else {
            throw new u("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
    }

    public final int i() {
        int i2 = this.d;
        if (this.b - i2 < 4) {
            p(4);
            i2 = this.d;
        }
        this.d = i2 + 4;
        byte[] bArr = this.f5058a;
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public final long j() {
        int i2 = this.d;
        if (this.b - i2 < 8) {
            p(8);
            i2 = this.d;
        }
        this.d = i2 + 8;
        byte[] bArr = this.f5058a;
        return ((((long) bArr[i2 + 1]) & 255) << 8) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
    }

    public final int k() {
        int i2;
        int i7;
        int i8 = this.d;
        int i10 = this.b;
        if (i10 != i8) {
            int i11 = i8 + 1;
            byte[] bArr = this.f5058a;
            byte b5 = bArr[i8];
            if (b5 >= 0) {
                this.d = i11;
                return b5;
            } else if (i10 - i11 >= 9) {
                int i12 = i8 + 2;
                byte b8 = (bArr[i11] << 7) ^ b5;
                long j2 = (long) b8;
                if (j2 < 0) {
                    i2 = (int) (-128 ^ j2);
                } else {
                    int i13 = i8 + 3;
                    byte b10 = (bArr[i12] << 14) ^ b8;
                    long j3 = (long) b10;
                    if (j3 >= 0) {
                        i7 = (int) (16256 ^ j3);
                    } else {
                        int i14 = i8 + 4;
                        byte b11 = b10 ^ (bArr[i13] << 21);
                        long j8 = (long) b11;
                        if (j8 < 0) {
                            i2 = (int) (-2080896 ^ j8);
                        } else {
                            i13 = i8 + 5;
                            byte b12 = bArr[i14];
                            int i15 = (int) (((long) (b11 ^ (b12 << 28))) ^ 266354560);
                            if (b12 < 0) {
                                i14 = i8 + 6;
                                if (bArr[i13] < 0) {
                                    i13 = i8 + 7;
                                    if (bArr[i14] < 0) {
                                        i14 = i8 + 8;
                                        if (bArr[i13] < 0) {
                                            i13 = i8 + 9;
                                            if (bArr[i14] < 0) {
                                                int i16 = i8 + 10;
                                                if (bArr[i13] >= 0) {
                                                    int i17 = i15;
                                                    i12 = i16;
                                                    i2 = i17;
                                                }
                                            }
                                        }
                                    }
                                }
                                i2 = i15;
                            }
                            i7 = i15;
                        }
                        i12 = i14;
                    }
                    i12 = i13;
                }
                this.d = i12;
                return i2;
            }
        }
        return (int) m();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b6, code lost:
        if (((long) r3[r2]) < 0) goto L_0x00b8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long l() {
        /*
            r12 = this;
            int r0 = r12.d
            int r1 = r12.b
            if (r1 != r0) goto L_0x0008
            goto L_0x00b8
        L_0x0008:
            int r2 = r0 + 1
            byte[] r3 = r12.f5058a
            byte r4 = r3[r0]
            if (r4 < 0) goto L_0x0014
            r12.d = r2
            long r0 = (long) r4
            return r0
        L_0x0014:
            int r1 = r1 - r2
            r5 = 9
            if (r1 >= r5) goto L_0x001b
            goto L_0x00b8
        L_0x001b:
            int r1 = r0 + 2
            byte r2 = r3[r2]
            int r2 = r2 << 7
            r2 = r2 ^ r4
            long r4 = (long) r2
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x002e
            r2 = -128(0xffffffffffffff80, double:NaN)
        L_0x002b:
            long r2 = r2 ^ r4
            goto L_0x00c1
        L_0x002e:
            int r2 = r0 + 3
            byte r1 = r3[r1]
            int r1 = r1 << 14
            long r8 = (long) r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0042
            r0 = 16256(0x3f80, double:8.0315E-320)
        L_0x003c:
            long r0 = r0 ^ r4
            r10 = r0
            r1 = r2
            r2 = r10
            goto L_0x00c1
        L_0x0042:
            int r1 = r0 + 4
            byte r2 = r3[r2]
            int r2 = r2 << 21
            long r8 = (long) r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0052
            r2 = -2080896(0xffffffffffe03f80, double:NaN)
            goto L_0x002b
        L_0x0052:
            int r2 = r0 + 5
            byte r1 = r3[r1]
            long r8 = (long) r1
            r1 = 28
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0063
            r0 = 266354560(0xfe03f80, double:1.315966377E-315)
            goto L_0x003c
        L_0x0063:
            int r1 = r0 + 6
            byte r2 = r3[r2]
            long r8 = (long) r2
            r2 = 35
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0076
            r2 = -34093383808(0xfffffff80fe03f80, double:NaN)
            goto L_0x002b
        L_0x0076:
            int r2 = r0 + 7
            byte r1 = r3[r1]
            long r8 = (long) r1
            r1 = 42
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0089
            r0 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L_0x003c
        L_0x0089:
            int r1 = r0 + 8
            byte r2 = r3[r2]
            long r8 = (long) r2
            r2 = 49
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x009c
            r2 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L_0x002b
        L_0x009c:
            int r2 = r0 + 9
            byte r1 = r3[r1]
            long r8 = (long) r1
            r1 = 56
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            r8 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x00bf
            int r1 = r0 + 10
            byte r0 = r3[r2]
            long r2 = (long) r0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00bd
        L_0x00b8:
            long r0 = r12.m()
            return r0
        L_0x00bd:
            r2 = r4
            goto L_0x00c1
        L_0x00bf:
            r1 = r2
            goto L_0x00bd
        L_0x00c1:
            r12.d = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C1256f.l():long");
    }

    public final long m() {
        long j2 = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            if (this.d == this.b) {
                p(1);
            }
            int i7 = this.d;
            this.d = i7 + 1;
            byte b5 = this.f5058a[i7];
            j2 |= ((long) (b5 & Byte.MAX_VALUE)) << i2;
            if ((b5 & 128) == 0) {
                return j2;
            }
        }
        throw new u("CodedInputStream encountered a malformed varint.");
    }

    public final int n() {
        if (this.d != this.b || s(1)) {
            int k = k();
            this.f = k;
            if ((k >>> 3) != 0) {
                return k;
            }
            throw new u("Protocol message contained an invalid tag (zero).");
        }
        this.f = 0;
        return 0;
    }

    public final void o() {
        int i2 = this.b + this.f5059c;
        this.b = i2;
        int i7 = this.g + i2;
        int i8 = this.f5060h;
        if (i7 > i8) {
            int i10 = i7 - i8;
            this.f5059c = i10;
            this.b = i2 - i10;
            return;
        }
        this.f5059c = 0;
    }

    public final void p(int i2) {
        if (!s(i2)) {
            throw u.a();
        }
    }

    public final boolean q(int i2, o oVar) {
        int n;
        int i7 = i2 & 7;
        if (i7 == 0) {
            long l = l();
            oVar.v(i2);
            oVar.w(l);
            return true;
        } else if (i7 == 1) {
            long j2 = j();
            oVar.v(i2);
            oVar.u(j2);
            return true;
        } else if (i7 == 2) {
            x e7 = e();
            oVar.v(i2);
            oVar.v(e7.size());
            oVar.r(e7);
            return true;
        } else if (i7 == 3) {
            oVar.v(i2);
            do {
                n = n();
                if (n == 0 || !q(n, oVar)) {
                    int i8 = ((i2 >>> 3) << 3) | 4;
                    a(i8);
                    oVar.v(i8);
                }
                n = n();
                break;
            } while (!q(n, oVar));
            int i82 = ((i2 >>> 3) << 3) | 4;
            a(i82);
            oVar.v(i82);
            return true;
        } else if (i7 == 4) {
            return false;
        } else {
            if (i7 == 5) {
                int i10 = i();
                oVar.v(i2);
                oVar.t(i10);
                return true;
            }
            throw new u("Protocol message tag had invalid wire type.");
        }
    }

    public final void r(int i2) {
        int i7 = this.b;
        int i8 = this.d;
        int i10 = i7 - i8;
        if (i2 <= i10 && i2 >= 0) {
            this.d = i8 + i2;
        } else if (i2 >= 0) {
            int i11 = this.g;
            int i12 = i11 + i8 + i2;
            int i13 = this.f5060h;
            if (i12 <= i13) {
                this.d = i7;
                p(1);
                while (true) {
                    int i14 = i2 - i10;
                    int i15 = this.b;
                    if (i14 > i15) {
                        i10 += i15;
                        this.d = i15;
                        p(1);
                    } else {
                        this.d = i14;
                        return;
                    }
                }
            } else {
                r((i13 - i11) - i8);
                throw u.a();
            }
        } else {
            throw new u("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
    }

    public final boolean s(int i2) {
        InputStream inputStream;
        int i7 = this.d;
        int i8 = i7 + i2;
        int i10 = this.b;
        if (i8 > i10) {
            if (this.g + i7 + i2 <= this.f5060h && (inputStream = this.e) != null) {
                byte[] bArr = this.f5058a;
                if (i7 > 0) {
                    if (i10 > i7) {
                        System.arraycopy(bArr, i7, bArr, 0, i10 - i7);
                    }
                    this.g += i7;
                    this.b -= i7;
                    this.d = 0;
                }
                int i11 = this.b;
                int read = inputStream.read(bArr, i11, bArr.length - i11);
                if (read == 0 || read < -1 || read > bArr.length) {
                    StringBuilder sb2 = new StringBuilder(102);
                    sb2.append("InputStream#read(byte[]) returned invalid result: ");
                    sb2.append(read);
                    sb2.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(sb2.toString());
                } else if (read > 0) {
                    this.b += read;
                    if ((this.g + i2) - 67108864 <= 0) {
                        o();
                        if (this.b >= i2) {
                            return true;
                        }
                        return s(i2);
                    }
                    throw new u("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
            }
            return false;
        }
        StringBuilder sb3 = new StringBuilder(77);
        sb3.append("refillBuffer() called when ");
        sb3.append(i2);
        sb3.append(" bytes were already available in buffer");
        throw new IllegalStateException(sb3.toString());
    }
}
