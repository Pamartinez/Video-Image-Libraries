package com.google.protobuf;

import D1.f;
import Gd.a;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q0 extends a {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1628a;

    public /* synthetic */ q0(int i2) {
        this.f1628a = i2;
    }

    public static int l0(int i2, int i7, long j2, byte[] bArr) {
        if (i7 == 0) {
            a aVar = s0.f1629a;
            if (i2 > -12) {
                return -1;
            }
            return i2;
        } else if (i7 == 1) {
            return s0.c(i2, p0.g(bArr, j2));
        } else {
            if (i7 == 2) {
                return s0.d(i2, p0.g(bArr, j2), p0.g(bArr, j2 + 1));
            }
            throw new AssertionError();
        }
    }

    public final int Z(int i2, int i7, byte[] bArr) {
        int i8;
        switch (this.f1628a) {
            case 0:
                break;
            default:
                if ((i2 | i7 | (bArr.length - i7)) >= 0) {
                    long j2 = (long) i2;
                    int i10 = (int) (((long) i7) - j2);
                    if (i10 < 16) {
                        i8 = 0;
                    } else {
                        int i11 = 8 - (((int) j2) & 7);
                        long j3 = j2;
                        i8 = 0;
                        while (true) {
                            if (i8 < i11) {
                                long j8 = j3 + 1;
                                if (p0.g(bArr, j3) >= 0) {
                                    i8++;
                                    j3 = j8;
                                }
                            } else {
                                while (true) {
                                    int i12 = i8 + 8;
                                    if (i12 <= i10) {
                                        if ((p0.f1625c.j(bArr, p0.f + j3) & -9187201950435737472L) == 0) {
                                            j3 += 8;
                                            i8 = i12;
                                        }
                                    }
                                }
                                while (true) {
                                    if (i8 < i10) {
                                        long j10 = j3 + 1;
                                        if (p0.g(bArr, j3) >= 0) {
                                            i8++;
                                            j3 = j10;
                                        }
                                    } else {
                                        i8 = i10;
                                    }
                                }
                            }
                        }
                    }
                    int i13 = i10 - i8;
                    long j11 = j2 + ((long) i8);
                    while (true) {
                        byte b = 0;
                        while (true) {
                            if (i13 > 0) {
                                long j12 = j11 + 1;
                                b = p0.g(bArr, j11);
                                if (b >= 0) {
                                    i13--;
                                    j11 = j12;
                                } else {
                                    j11 = j12;
                                }
                            }
                        }
                        if (i13 == 0) {
                            return 0;
                        }
                        int i14 = i13 - 1;
                        if (b < -32) {
                            if (i14 == 0) {
                                return b;
                            }
                            i13 -= 2;
                            if (b >= -62) {
                                long j13 = j11 + 1;
                                if (p0.g(bArr, j11) <= -65) {
                                    j11 = j13;
                                }
                            }
                        } else if (b < -16) {
                            if (i14 < 2) {
                                return l0(b, i14, j11, bArr);
                            }
                            i13 -= 3;
                            long j14 = j11 + 1;
                            byte g = p0.g(bArr, j11);
                            if (g <= -65 && ((b != -32 || g >= -96) && (b != -19 || g < -96))) {
                                j11 += 2;
                                if (p0.g(bArr, j14) > -65) {
                                }
                            }
                        } else if (i14 < 3) {
                            return l0(b, i14, j11, bArr);
                        } else {
                            i13 -= 4;
                            long j15 = j11 + 1;
                            byte g3 = p0.g(bArr, j11);
                            if (g3 <= -65) {
                                if ((((g3 + 112) + (b << 28)) >> 30) == 0) {
                                    long j16 = 2 + j11;
                                    if (p0.g(bArr, j15) <= -65) {
                                        j11 += 3;
                                        if (p0.g(bArr, j16) > -65) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return -1;
                }
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i7)}));
        }
        while (r13 < i7 && bArr[r13] >= 0) {
            i2 = r13 + 1;
        }
        if (r13 < i7) {
            while (r13 < i7) {
                int i15 = r13 + 1;
                byte b5 = bArr[r13];
                if (b5 < 0) {
                    if (b5 < -32) {
                        if (i15 >= i7) {
                            return b5;
                        }
                        if (b5 >= -62) {
                            r13 += 2;
                            if (bArr[i15] > -65) {
                            }
                        }
                    } else if (b5 < -16) {
                        if (i15 >= i7 - 1) {
                            return s0.a(i15, i7, bArr);
                        }
                        int i16 = r13 + 2;
                        byte b8 = bArr[i15];
                        if (b8 <= -65 && ((b5 != -32 || b8 >= -96) && (b5 != -19 || b8 < -96))) {
                            r13 += 3;
                            if (bArr[i16] > -65) {
                            }
                        }
                    } else if (i15 >= i7 - 2) {
                        return s0.a(i15, i7, bArr);
                    } else {
                        int i17 = r13 + 2;
                        byte b10 = bArr[i15];
                        if (b10 <= -65) {
                            if ((((b10 + 112) + (b5 << 28)) >> 30) == 0) {
                                int i18 = r13 + 3;
                                if (bArr[i17] <= -65) {
                                    r13 += 4;
                                    if (bArr[i18] > -65) {
                                    }
                                }
                            }
                        }
                    }
                    return -1;
                }
                r13 = i15;
            }
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String o(int r7, int r8, byte[] r9) {
        /*
            r6 = this;
            int r6 = r6.f1628a
            switch(r6) {
                case 0: goto L_0x002b;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r0 = com.google.protobuf.D.f1578a
            r6.<init>(r9, r7, r8, r0)
            java.lang.String r1 = "�"
            boolean r1 = r6.contains(r1)
            if (r1 != 0) goto L_0x0016
            goto L_0x0025
        L_0x0016:
            byte[] r0 = r6.getBytes(r0)
            int r8 = r8 + r7
            byte[] r7 = java.util.Arrays.copyOfRange(r9, r7, r8)
            boolean r7 = java.util.Arrays.equals(r0, r7)
            if (r7 == 0) goto L_0x0026
        L_0x0025:
            return r6
        L_0x0026:
            com.google.protobuf.F r6 = com.google.protobuf.F.b()
            throw r6
        L_0x002b:
            r6 = r7 | r8
            int r0 = r9.length
            int r0 = r0 - r7
            int r0 = r0 - r8
            r6 = r6 | r0
            if (r6 < 0) goto L_0x00bd
            int r6 = r7 + r8
            char[] r4 = new char[r8]
            r8 = 0
            r0 = r8
        L_0x0039:
            if (r7 >= r6) goto L_0x0048
            byte r1 = r9[r7]
            if (r1 < 0) goto L_0x0048
            int r7 = r7 + 1
            int r2 = r0 + 1
            char r1 = (char) r1
            r4[r0] = r1
            r0 = r2
            goto L_0x0039
        L_0x0048:
            r5 = r0
        L_0x0049:
            if (r7 >= r6) goto L_0x00b7
            int r0 = r7 + 1
            r1 = r0
            byte r0 = r9[r7]
            if (r0 < 0) goto L_0x006a
            int r7 = r5 + 1
            char r0 = (char) r0
            r4[r5] = r0
            r0 = r1
        L_0x0058:
            if (r0 >= r6) goto L_0x0067
            byte r1 = r9[r0]
            if (r1 < 0) goto L_0x0067
            int r0 = r0 + 1
            int r2 = r7 + 1
            char r1 = (char) r1
            r4[r7] = r1
            r7 = r2
            goto L_0x0058
        L_0x0067:
            r5 = r7
            r7 = r0
            goto L_0x0049
        L_0x006a:
            r2 = -32
            if (r0 >= r2) goto L_0x0080
            if (r1 >= r6) goto L_0x007b
            int r7 = r7 + 2
            byte r1 = r9[r1]
            int r2 = r5 + 1
            D1.f.b(r0, r1, r4, r5)
            r5 = r2
            goto L_0x0049
        L_0x007b:
            com.google.protobuf.F r6 = com.google.protobuf.F.b()
            throw r6
        L_0x0080:
            r2 = -16
            if (r0 >= r2) goto L_0x009c
            int r2 = r6 + -1
            if (r1 >= r2) goto L_0x0097
            int r2 = r7 + 2
            byte r1 = r9[r1]
            int r7 = r7 + 3
            byte r2 = r9[r2]
            int r3 = r5 + 1
            D1.f.c(r0, r1, r2, r4, r5)
            r5 = r3
            goto L_0x0049
        L_0x0097:
            com.google.protobuf.F r6 = com.google.protobuf.F.b()
            throw r6
        L_0x009c:
            int r2 = r6 + -2
            if (r1 >= r2) goto L_0x00b2
            int r2 = r7 + 2
            byte r1 = r9[r1]
            int r3 = r7 + 3
            byte r2 = r9[r2]
            int r7 = r7 + 4
            byte r3 = r9[r3]
            D1.f.a(r0, r1, r2, r3, r4, r5)
            int r5 = r5 + 2
            goto L_0x0049
        L_0x00b2:
            com.google.protobuf.F r6 = com.google.protobuf.F.b()
            throw r6
        L_0x00b7:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r4, r8, r5)
            return r6
        L_0x00bd:
            java.lang.ArrayIndexOutOfBoundsException r6 = new java.lang.ArrayIndexOutOfBoundsException
            int r9 = r9.length
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r7 = new java.lang.Object[]{r9, r7, r8}
            java.lang.String r8 = "buffer length=%d, index=%d, size=%d"
            java.lang.String r7 = java.lang.String.format(r8, r7)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.q0.o(int, int, byte[]):java.lang.String");
    }

    public final String q(ByteBuffer byteBuffer, int i2, int i7) {
        long j2;
        long j3;
        byte e;
        byte e7;
        int i8 = i2;
        int i10 = i7;
        switch (this.f1628a) {
            case 0:
                ByteBuffer byteBuffer2 = byteBuffer;
                return a.p(byteBuffer, i2, i7);
            default:
                if ((i8 | i10 | ((byteBuffer.limit() - i8) - i10)) >= 0) {
                    long j8 = p0.f1625c.j(byteBuffer, p0.g) + ((long) i8);
                    long j10 = ((long) i10) + j8;
                    char[] cArr = new char[i10];
                    int i11 = 0;
                    while (true) {
                        j3 = 1;
                        if (j2 >= j10 || (e7 = p0.f1625c.e(j2)) < 0) {
                            int i12 = i11;
                        } else {
                            j8 = j2 + 1;
                            cArr[i11] = (char) e7;
                            i11++;
                        }
                    }
                    int i122 = i11;
                    while (j2 < j10) {
                        long j11 = j2 + j3;
                        o0 o0Var = p0.f1625c;
                        byte e8 = o0Var.e(j2);
                        if (e8 >= 0) {
                            int i13 = i122 + 1;
                            cArr[i122] = (char) e8;
                            while (j11 < j10 && (e = p0.f1625c.e(j11)) >= 0) {
                                j11 += j3;
                                cArr[i13] = (char) e;
                                i13++;
                            }
                            i122 = i13;
                            j2 = j11;
                        } else if (e8 < -32) {
                            if (j11 < j10) {
                                j2 += 2;
                                f.b(e8, o0Var.e(j11), cArr, i122);
                                i122++;
                            } else {
                                throw F.b();
                            }
                        } else if (e8 < -16) {
                            if (j11 < j10 - j3) {
                                long j12 = 2 + j2;
                                j2 += 3;
                                f.c(e8, o0Var.e(j11), o0Var.e(j12), cArr, i122);
                                i122++;
                            } else {
                                throw F.b();
                            }
                        } else if (j11 < j10 - 2) {
                            byte e9 = o0Var.e(j11);
                            long j13 = j2 + 3;
                            byte e10 = o0Var.e(2 + j2);
                            j2 += 4;
                            f.a(e8, e9, e10, o0Var.e(j13), cArr, i122);
                            i122 += 2;
                        } else {
                            throw F.b();
                        }
                        j3 = 1;
                    }
                    return new String(cArr, 0, i122);
                }
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i8), Integer.valueOf(i10)}));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b A[LOOP:1: B:14:0x003b->B:39:0x00f7, LOOP_START, PHI: r2 r5 r7 r11 
      PHI: (r2v10 int) = (r2v9 int), (r2v12 int) binds: [B:12:0x0036, B:39:0x00f7] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r5v18 long) = (r5v17 long), (r5v19 long) binds: [B:12:0x0036, B:39:0x00f7] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r7v13 long) = (r7v12 long), (r7v14 long) binds: [B:12:0x0036, B:39:0x00f7] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v4 long) binds: [B:12:0x0036, B:39:0x00f7] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int r(java.lang.String r24, byte[] r25, int r26, int r27) {
        /*
            r23 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r23
            r4 = r27
            int r3 = r3.f1628a
            switch(r3) {
                case 0: goto L_0x0159;
                default: goto L_0x000f;
            }
        L_0x000f:
            long r5 = (long) r2
            long r7 = (long) r4
            long r7 = r7 + r5
            int r3 = r0.length()
            java.lang.String r9 = " at index "
            java.lang.String r10 = "Failed writing "
            if (r3 > r4) goto L_0x0139
            int r11 = r1.length
            int r11 = r11 - r4
            if (r11 < r2) goto L_0x0139
            r2 = 0
        L_0x0021:
            r11 = 1
            r4 = 128(0x80, float:1.794E-43)
            if (r2 >= r3) goto L_0x0036
            char r13 = r0.charAt(r2)
            if (r13 >= r4) goto L_0x0036
            long r11 = r11 + r5
            byte r4 = (byte) r13
            com.google.protobuf.p0.k(r1, r5, r4)
            int r2 = r2 + 1
            r5 = r11
            goto L_0x0021
        L_0x0036:
            if (r2 != r3) goto L_0x003b
        L_0x0038:
            int r0 = (int) r5
            goto L_0x0138
        L_0x003b:
            if (r2 >= r3) goto L_0x0038
            char r13 = r0.charAt(r2)
            if (r13 >= r4) goto L_0x0054
            int r14 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r14 >= 0) goto L_0x0054
            long r14 = r5 + r11
            byte r13 = (byte) r13
            com.google.protobuf.p0.k(r1, r5, r13)
            r19 = r7
            r26 = r11
            r5 = r14
            goto L_0x00f7
        L_0x0054:
            r14 = 2048(0x800, float:2.87E-42)
            r15 = 2
            if (r13 >= r14) goto L_0x0078
            long r17 = r7 - r15
            int r14 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
            if (r14 > 0) goto L_0x0078
            r26 = r11
            long r11 = r5 + r26
            int r14 = r13 >>> 6
            r14 = r14 | 960(0x3c0, float:1.345E-42)
            byte r14 = (byte) r14
            com.google.protobuf.p0.k(r1, r5, r14)
            long r5 = r5 + r15
            r13 = r13 & 63
            r13 = r13 | r4
            byte r13 = (byte) r13
            com.google.protobuf.p0.k(r1, r11, r13)
            r19 = r7
            goto L_0x00f7
        L_0x0078:
            r26 = r11
            r11 = 57343(0xdfff, float:8.0355E-41)
            r12 = 55296(0xd800, float:7.7486E-41)
            r17 = 3
            if (r13 < r12) goto L_0x008a
            if (r11 >= r13) goto L_0x0087
            goto L_0x008a
        L_0x0087:
            r19 = r7
            goto L_0x00b1
        L_0x008a:
            long r19 = r7 - r17
            int r14 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r14 > 0) goto L_0x0087
            long r11 = r5 + r26
            int r14 = r13 >>> 12
            r14 = r14 | 480(0x1e0, float:6.73E-43)
            byte r14 = (byte) r14
            com.google.protobuf.p0.k(r1, r5, r14)
            long r14 = r5 + r15
            int r16 = r13 >>> 6
            r19 = r7
            r7 = r16 & 63
            r7 = r7 | r4
            byte r7 = (byte) r7
            com.google.protobuf.p0.k(r1, r11, r7)
            long r5 = r5 + r17
            r7 = r13 & 63
            r7 = r7 | r4
            byte r7 = (byte) r7
            com.google.protobuf.p0.k(r1, r14, r7)
            goto L_0x00f7
        L_0x00b1:
            r7 = 4
            long r21 = r19 - r7
            int r14 = (r5 > r21 ? 1 : (r5 == r21 ? 0 : -1))
            if (r14 > 0) goto L_0x0108
            int r11 = r2 + 1
            if (r11 == r3) goto L_0x0100
            char r2 = r0.charAt(r11)
            boolean r12 = java.lang.Character.isSurrogatePair(r13, r2)
            if (r12 == 0) goto L_0x00ff
            int r2 = java.lang.Character.toCodePoint(r13, r2)
            long r12 = r5 + r26
            int r14 = r2 >>> 18
            r14 = r14 | 240(0xf0, float:3.36E-43)
            byte r14 = (byte) r14
            com.google.protobuf.p0.k(r1, r5, r14)
            long r14 = r5 + r15
            int r16 = r2 >>> 12
            r21 = r7
            r7 = r16 & 63
            r7 = r7 | r4
            byte r7 = (byte) r7
            com.google.protobuf.p0.k(r1, r12, r7)
            long r7 = r5 + r17
            int r12 = r2 >>> 6
            r12 = r12 & 63
            r12 = r12 | r4
            byte r12 = (byte) r12
            com.google.protobuf.p0.k(r1, r14, r12)
            long r5 = r5 + r21
            r2 = r2 & 63
            r2 = r2 | r4
            byte r2 = (byte) r2
            com.google.protobuf.p0.k(r1, r7, r2)
            r2 = r11
        L_0x00f7:
            int r2 = r2 + 1
            r11 = r26
            r7 = r19
            goto L_0x003b
        L_0x00ff:
            r2 = r11
        L_0x0100:
            com.google.protobuf.r0 r0 = new com.google.protobuf.r0
            int r2 = r2 + -1
            r0.<init>(r2, r3)
            throw r0
        L_0x0108:
            if (r12 > r13) goto L_0x0120
            if (r13 > r11) goto L_0x0120
            int r1 = r2 + 1
            if (r1 == r3) goto L_0x011a
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
            if (r0 != 0) goto L_0x0120
        L_0x011a:
            com.google.protobuf.r0 r0 = new com.google.protobuf.r0
            r0.<init>(r2, r3)
            throw r0
        L_0x0120:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r10)
            r1.append(r13)
            r1.append(r9)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0138:
            return r0
        L_0x0139:
            java.lang.ArrayIndexOutOfBoundsException r1 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r10)
            int r3 = r3 + -1
            char r0 = r0.charAt(r3)
            r5.append(r0)
            r5.append(r9)
            int r0 = r2 + r4
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r1.<init>(r0)
            throw r1
        L_0x0159:
            int r3 = r0.length()
            int r4 = r4 + r2
            r5 = 0
        L_0x015f:
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r3) goto L_0x0173
            int r7 = r5 + r2
            if (r7 >= r4) goto L_0x0173
            char r8 = r0.charAt(r5)
            if (r8 >= r6) goto L_0x0173
            byte r6 = (byte) r8
            r1[r7] = r6
            int r5 = r5 + 1
            goto L_0x015f
        L_0x0173:
            if (r5 != r3) goto L_0x0179
            int r0 = r2 + r3
            goto L_0x0256
        L_0x0179:
            int r2 = r2 + r5
        L_0x017a:
            if (r5 >= r3) goto L_0x0255
            char r7 = r0.charAt(r5)
            if (r7 >= r6) goto L_0x018c
            if (r2 >= r4) goto L_0x018c
            int r8 = r2 + 1
            byte r7 = (byte) r7
            r1[r2] = r7
            r2 = r8
            goto L_0x0210
        L_0x018c:
            r8 = 2048(0x800, float:2.87E-42)
            if (r7 >= r8) goto L_0x01a6
            int r8 = r4 + -2
            if (r2 > r8) goto L_0x01a6
            int r8 = r2 + 1
            int r9 = r7 >>> 6
            r9 = r9 | 960(0x3c0, float:1.345E-42)
            byte r9 = (byte) r9
            r1[r2] = r9
            int r2 = r2 + 2
            r7 = r7 & 63
            r7 = r7 | r6
            byte r7 = (byte) r7
            r1[r8] = r7
            goto L_0x0210
        L_0x01a6:
            r8 = 57343(0xdfff, float:8.0355E-41)
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r7 < r9) goto L_0x01b0
            if (r8 >= r7) goto L_0x01d0
        L_0x01b0:
            int r10 = r4 + -3
            if (r2 > r10) goto L_0x01d0
            int r8 = r2 + 1
            int r9 = r7 >>> 12
            r9 = r9 | 480(0x1e0, float:6.73E-43)
            byte r9 = (byte) r9
            r1[r2] = r9
            int r9 = r2 + 2
            int r10 = r7 >>> 6
            r10 = r10 & 63
            r10 = r10 | r6
            byte r10 = (byte) r10
            r1[r8] = r10
            int r2 = r2 + 3
            r7 = r7 & 63
            r7 = r7 | r6
            byte r7 = (byte) r7
            r1[r9] = r7
            goto L_0x0210
        L_0x01d0:
            int r10 = r4 + -4
            if (r2 > r10) goto L_0x021d
            int r8 = r5 + 1
            int r9 = r0.length()
            if (r8 == r9) goto L_0x0215
            char r5 = r0.charAt(r8)
            boolean r9 = java.lang.Character.isSurrogatePair(r7, r5)
            if (r9 == 0) goto L_0x0214
            int r5 = java.lang.Character.toCodePoint(r7, r5)
            int r7 = r2 + 1
            int r9 = r5 >>> 18
            r9 = r9 | 240(0xf0, float:3.36E-43)
            byte r9 = (byte) r9
            r1[r2] = r9
            int r9 = r2 + 2
            int r10 = r5 >>> 12
            r10 = r10 & 63
            r10 = r10 | r6
            byte r10 = (byte) r10
            r1[r7] = r10
            int r7 = r2 + 3
            int r10 = r5 >>> 6
            r10 = r10 & 63
            r10 = r10 | r6
            byte r10 = (byte) r10
            r1[r9] = r10
            int r2 = r2 + 4
            r5 = r5 & 63
            r5 = r5 | r6
            byte r5 = (byte) r5
            r1[r7] = r5
            r5 = r8
        L_0x0210:
            int r5 = r5 + 1
            goto L_0x017a
        L_0x0214:
            r5 = r8
        L_0x0215:
            com.google.protobuf.r0 r0 = new com.google.protobuf.r0
            int r5 = r5 + -1
            r0.<init>(r5, r3)
            throw r0
        L_0x021d:
            if (r9 > r7) goto L_0x0239
            if (r7 > r8) goto L_0x0239
            int r1 = r5 + 1
            int r4 = r0.length()
            if (r1 == r4) goto L_0x0233
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r7, r0)
            if (r0 != 0) goto L_0x0239
        L_0x0233:
            com.google.protobuf.r0 r0 = new com.google.protobuf.r0
            r0.<init>(r5, r3)
            throw r0
        L_0x0239:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed writing "
            r1.<init>(r3)
            r1.append(r7)
            java.lang.String r3 = " at index "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0255:
            r0 = r2
        L_0x0256:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.q0.r(java.lang.String, byte[], int, int):int");
    }
}
