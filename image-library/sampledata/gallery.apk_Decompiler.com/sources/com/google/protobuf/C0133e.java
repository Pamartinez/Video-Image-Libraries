package com.google.protobuf;

import Od.b;

/* renamed from: com.google.protobuf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0133e {
    public static int a(byte[] bArr, int i2, b bVar) {
        int h5 = h(bArr, i2, bVar);
        int i7 = bVar.f3609a;
        if (i7 < 0) {
            throw F.e();
        } else if (i7 > bArr.length - h5) {
            throw F.g();
        } else if (i7 == 0) {
            bVar.f3610c = ByteString.e;
            return h5;
        } else {
            bVar.f3610c = ByteString.r(h5, i7, bArr);
            return h5 + i7;
        }
    }

    public static int b(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public static long c(byte[] bArr, int i2) {
        return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
    }

    public static int d(Schema schema, byte[] bArr, int i2, int i7, int i8, b bVar) {
        Object f = schema.f();
        Schema schema2 = schema;
        b bVar2 = bVar;
        int k = k(f, schema2, bArr, i2, i7, i8, bVar2);
        schema2.c(f);
        bVar2.f3610c = f;
        return k;
    }

    public static int e(Schema schema, int i2, byte[] bArr, int i7, int i8, Internal$ProtobufList internal$ProtobufList, b bVar) {
        Object f = schema.f();
        Schema schema2 = schema;
        byte[] bArr2 = bArr;
        int i10 = i8;
        b bVar2 = bVar;
        int l = l(f, schema2, bArr2, i7, i10, bVar2);
        schema2.c(f);
        bVar2.f3610c = f;
        internal$ProtobufList.add(f);
        while (l < i10) {
            b bVar3 = bVar2;
            int i11 = i10;
            int h5 = h(bArr2, l, bVar3);
            if (i2 != bVar3.f3609a) {
                break;
            }
            byte[] bArr3 = bArr2;
            Schema schema3 = schema2;
            Object f5 = schema3.f();
            l = l(f5, schema3, bArr3, h5, i11, bVar3);
            Object obj = f5;
            schema2 = schema3;
            bArr2 = bArr3;
            i10 = i11;
            bVar2 = bVar3;
            schema2.c(obj);
            bVar2.f3610c = obj;
            internal$ProtobufList.add(obj);
        }
        return l;
    }

    public static int f(int i2, byte[] bArr, int i7, int i8, UnknownFieldSetLite unknownFieldSetLite, b bVar) {
        if ((i2 >>> 3) != 0) {
            int i10 = i2 & 7;
            if (i10 == 0) {
                b bVar2 = bVar;
                int j2 = j(bArr, i7, bVar2);
                unknownFieldSetLite.f(i2, Long.valueOf(bVar2.b));
                return j2;
            } else if (i10 == 1) {
                unknownFieldSetLite.f(i2, Long.valueOf(c(bArr, i7)));
                return i7 + 8;
            } else if (i10 == 2) {
                byte[] bArr2 = bArr;
                b bVar3 = bVar;
                int h5 = h(bArr2, i7, bVar3);
                int i11 = bVar3.f3609a;
                if (i11 < 0) {
                    throw F.e();
                } else if (i11 <= bArr2.length - h5) {
                    if (i11 == 0) {
                        unknownFieldSetLite.f(i2, ByteString.e);
                    } else {
                        unknownFieldSetLite.f(i2, ByteString.r(h5, i11, bArr2));
                    }
                    return h5 + i11;
                } else {
                    throw F.g();
                }
            } else if (i10 == 3) {
                UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
                int i12 = (i2 & -8) | 4;
                int i13 = 0;
                while (true) {
                    if (i7 >= i8) {
                        break;
                    }
                    int h6 = h(bArr, i7, bVar);
                    i13 = bVar.f3609a;
                    if (i13 == i12) {
                        i7 = h6;
                        break;
                    }
                    i7 = f(i13, bArr, h6, i8, unknownFieldSetLite2, bVar);
                }
                if (i7 > i8 || i13 != i12) {
                    throw F.f();
                }
                unknownFieldSetLite.f(i2, unknownFieldSetLite2);
                return i7;
            } else if (i10 == 5) {
                unknownFieldSetLite.f(i2, Integer.valueOf(b(bArr, i7)));
                return i7 + 4;
            } else {
                throw F.a();
            }
        } else {
            throw F.a();
        }
    }

    public static int g(int i2, byte[] bArr, int i7, b bVar) {
        int i8 = i2 & 127;
        int i10 = i7 + 1;
        byte b = bArr[i7];
        if (b >= 0) {
            bVar.f3609a = i8 | (b << 7);
            return i10;
        }
        int i11 = i8 | ((b & Byte.MAX_VALUE) << 7);
        int i12 = i7 + 2;
        byte b5 = bArr[i10];
        if (b5 >= 0) {
            bVar.f3609a = i11 | (b5 << 14);
            return i12;
        }
        int i13 = i11 | ((b5 & Byte.MAX_VALUE) << 14);
        int i14 = i7 + 3;
        byte b8 = bArr[i12];
        if (b8 >= 0) {
            bVar.f3609a = i13 | (b8 << 21);
            return i14;
        }
        int i15 = i13 | ((b8 & Byte.MAX_VALUE) << 21);
        int i16 = i7 + 4;
        byte b10 = bArr[i14];
        if (b10 >= 0) {
            bVar.f3609a = i15 | (b10 << 28);
            return i16;
        }
        int i17 = i15 | ((b10 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i18 = i16 + 1;
            if (bArr[i16] < 0) {
                i16 = i18;
            } else {
                bVar.f3609a = i17;
                return i18;
            }
        }
    }

    public static int h(byte[] bArr, int i2, b bVar) {
        int i7 = i2 + 1;
        byte b = bArr[i2];
        if (b < 0) {
            return g(b, bArr, i7, bVar);
        }
        bVar.f3609a = b;
        return i7;
    }

    public static int i(int i2, byte[] bArr, int i7, int i8, Internal$ProtobufList internal$ProtobufList, b bVar) {
        B b = (B) internal$ProtobufList;
        int h5 = h(bArr, i7, bVar);
        b.k(bVar.f3609a);
        while (h5 < i8) {
            int h6 = h(bArr, h5, bVar);
            if (i2 != bVar.f3609a) {
                break;
            }
            h5 = h(bArr, h6, bVar);
            b.k(bVar.f3609a);
        }
        return h5;
    }

    public static int j(byte[] bArr, int i2, b bVar) {
        int i7 = i2 + 1;
        long j2 = (long) bArr[i2];
        if (j2 >= 0) {
            bVar.b = j2;
            return i7;
        }
        int i8 = i2 + 2;
        byte b = bArr[i7];
        long j3 = (j2 & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i10 = 7;
        while (b < 0) {
            int i11 = i8 + 1;
            byte b5 = bArr[i8];
            i10 += 7;
            j3 |= ((long) (b5 & Byte.MAX_VALUE)) << i10;
            int i12 = i11;
            b = b5;
            i8 = i12;
        }
        bVar.b = j3;
        return i8;
    }

    public static int k(Object obj, Schema schema, byte[] bArr, int i2, int i7, int i8, b bVar) {
        X x9 = (X) schema;
        Object obj2 = obj;
        int G5 = x9.G(obj2, bArr, i2, i7, i8, bVar);
        bVar.f3610c = obj2;
        return G5;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int l(java.lang.Object r6, com.google.protobuf.Schema r7, byte[] r8, int r9, int r10, Od.b r11) {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = g(r9, r8, r0, r11)
            int r9 = r11.f3609a
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r4 = r3 + r9
            r1 = r6
            r0 = r7
            r2 = r8
            r5 = r11
            r0.j(r1, r2, r3, r4, r5)
            r5.f3610c = r1
            return r4
        L_0x001e:
            com.google.protobuf.F r6 = com.google.protobuf.F.g()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.C0133e.l(java.lang.Object, com.google.protobuf.Schema, byte[], int, int, Od.b):int");
    }
}
