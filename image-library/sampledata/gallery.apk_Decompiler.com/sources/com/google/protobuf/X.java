package com.google.protobuf;

import A.a;
import N2.j;
import Od.b;
import com.adobe.internal.xmp.options.PropertyOptions;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X implements Schema {
    public static final int[] n = new int[0];

    /* renamed from: o  reason: collision with root package name */
    public static final Unsafe f1591o = p0.j();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f1592a;
    public final Object[] b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1593c;
    public final int d;
    public final MessageLite e;
    public final boolean f;
    public final int[] g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1594h;

    /* renamed from: i  reason: collision with root package name */
    public final int f1595i;

    /* renamed from: j  reason: collision with root package name */
    public final Z f1596j;
    public final L k;
    public final h0 l;
    public final S m;

    public X(int[] iArr, Object[] objArr, int i2, int i7, MessageLite messageLite, int[] iArr2, int i8, int i10, Z z, L l8, h0 h0Var, r rVar, S s) {
        this.f1592a = iArr;
        this.b = objArr;
        this.f1593c = i2;
        this.d = i7;
        this.f = messageLite instanceof GeneratedMessageLite;
        this.g = iArr2;
        this.f1594h = i8;
        this.f1595i = i10;
        this.f1596j = z;
        this.k = l8;
        this.l = h0Var;
        this.e = messageLite;
        this.m = s;
    }

    public static X A(f0 f0Var, Z z, L l8, h0 h0Var, r rVar, S s) {
        if (f0Var instanceof f0) {
            return B(f0Var, z, l8, h0Var, rVar, s);
        }
        f0Var.getClass();
        throw new ClassCastException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x033a  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0384  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.protobuf.X B(com.google.protobuf.f0 r33, com.google.protobuf.Z r34, com.google.protobuf.L r35, com.google.protobuf.h0 r36, com.google.protobuf.r r37, com.google.protobuf.S r38) {
        /*
            r0 = r33
            java.lang.String r1 = r0.b
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r6) goto L_0x001d
            r4 = 1
        L_0x0013:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r6) goto L_0x001e
            r4 = r7
            goto L_0x0013
        L_0x001d:
            r7 = 1
        L_0x001e:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x003d
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x002a:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r6) goto L_0x003a
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x002a
        L_0x003a:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x003d:
            if (r7 != 0) goto L_0x004d
            int[] r7 = n
            r9 = r3
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r16 = r13
            r15 = r7
            r7 = r16
            goto L_0x0160
        L_0x004d:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r6) goto L_0x006c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0059:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0069
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r4 = r4 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x0059
        L_0x0069:
            int r7 = r7 << r9
            r4 = r4 | r7
            r7 = r10
        L_0x006c:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x008b
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0078:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r6) goto L_0x0088
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0078
        L_0x0088:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r11
        L_0x008b:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r6) goto L_0x00aa
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x0097:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r6) goto L_0x00a7
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x0097
        L_0x00a7:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00aa:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r6) goto L_0x00c9
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00b6:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r6) goto L_0x00c6
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00b6
        L_0x00c6:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00c9:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r6) goto L_0x00e8
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00d5:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r6) goto L_0x00e5
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00d5
        L_0x00e5:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00e8:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r6) goto L_0x0107
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00f4:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r6) goto L_0x0104
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00f4
        L_0x0104:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0107:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r6) goto L_0x0128
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0113:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r6) goto L_0x0124
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x0113
        L_0x0124:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0128:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r6) goto L_0x014b
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0134:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0146
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0134
        L_0x0146:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x014b:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int[] r13 = new int[r13]
            int r16 = r4 * 2
            int r16 = r16 + r7
            r7 = r12
            r12 = r9
            r9 = r7
            r7 = r4
            r4 = r15
            r15 = r13
            r13 = r10
            r10 = r16
            r16 = r14
        L_0x0160:
            sun.misc.Unsafe r14 = f1591o
            java.lang.Object[] r3 = r0.f1604c
            r18 = 1
            com.google.protobuf.MessageLite r5 = r0.f1603a
            java.lang.Class r5 = r5.getClass()
            int r8 = r11 * 3
            int[] r8 = new int[r8]
            int r11 = r11 * 2
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r9 = r16 + r9
            r23 = r9
            r22 = r16
            r20 = 0
            r21 = 0
        L_0x017e:
            if (r4 >= r2) goto L_0x03e3
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r6) goto L_0x01ad
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r6 = r24
            r24 = 13
        L_0x018e:
            int r26 = r6 + 1
            char r6 = r1.charAt(r6)
            r27 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r2) goto L_0x01a7
            r2 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r24
            r4 = r4 | r2
            int r24 = r24 + 13
            r6 = r26
            r2 = r27
            goto L_0x018e
        L_0x01a7:
            int r2 = r6 << r24
            r4 = r4 | r2
            r2 = r26
            goto L_0x01b1
        L_0x01ad:
            r27 = r2
            r2 = r24
        L_0x01b1:
            int r6 = r2 + 1
            char r2 = r1.charAt(r2)
            r24 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r3) goto L_0x01dc
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x01c2:
            int r28 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r3) goto L_0x01d7
            r3 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r26
            r2 = r2 | r3
            int r26 = r26 + 13
            r6 = r28
            r3 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01c2
        L_0x01d7:
            int r3 = r6 << r26
            r2 = r2 | r3
            r6 = r28
        L_0x01dc:
            r3 = r2 & 255(0xff, float:3.57E-43)
            r26 = r4
            r4 = r2 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto L_0x01ea
            int r4 = r20 + 1
            r15[r20] = r21
            r20 = r4
        L_0x01ea:
            r4 = 51
            r30 = r7
            if (r3 < r4) goto L_0x0297
            int r4 = r6 + 1
            char r6 = r1.charAt(r6)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r7) goto L_0x0219
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r31 = 13
        L_0x01ff:
            int r32 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r7) goto L_0x0214
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r31
            r6 = r6 | r4
            int r31 = r31 + 13
            r4 = r32
            r7 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01ff
        L_0x0214:
            int r4 = r4 << r31
            r6 = r6 | r4
            r4 = r32
        L_0x0219:
            int r7 = r3 + -51
            r31 = r4
            r4 = 9
            if (r7 == r4) goto L_0x0248
            r4 = 17
            if (r7 != r4) goto L_0x0226
            goto L_0x0248
        L_0x0226:
            r4 = 12
            if (r7 != r4) goto L_0x0255
            com.google.protobuf.c0 r4 = r0.a()
            com.google.protobuf.c0 r7 = com.google.protobuf.c0.PROTO2
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x023a
            r4 = r2 & 2048(0x800, float:2.87E-42)
            if (r4 == 0) goto L_0x0255
        L_0x023a:
            int r4 = r21 / 3
            int r4 = r4 * 2
            int r4 = r4 + 1
            int r7 = r10 + 1
            r10 = r24[r10]
            r11[r4] = r10
        L_0x0246:
            r10 = r7
            goto L_0x0255
        L_0x0248:
            int r4 = r21 / 3
            int r4 = r4 * 2
            int r4 = r4 + 1
            int r7 = r10 + 1
            r10 = r24[r10]
            r11[r4] = r10
            goto L_0x0246
        L_0x0255:
            int r6 = r6 * 2
            r4 = r24[r6]
            boolean r7 = r4 instanceof java.lang.reflect.Field
            if (r7 == 0) goto L_0x0262
            java.lang.reflect.Field r4 = (java.lang.reflect.Field) r4
        L_0x025f:
            r28 = r6
            goto L_0x026b
        L_0x0262:
            java.lang.String r4 = (java.lang.String) r4
            java.lang.reflect.Field r4 = M(r5, r4)
            r24[r6] = r4
            goto L_0x025f
        L_0x026b:
            long r6 = r14.objectFieldOffset(r4)
            int r4 = (int) r6
            int r6 = r28 + 1
            r7 = r24[r6]
            r28 = r4
            boolean r4 = r7 instanceof java.lang.reflect.Field
            if (r4 == 0) goto L_0x027d
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            goto L_0x0285
        L_0x027d:
            java.lang.String r7 = (java.lang.String) r7
            java.lang.reflect.Field r7 = M(r5, r7)
            r24[r6] = r7
        L_0x0285:
            long r6 = r14.objectFieldOffset(r7)
            int r4 = (int) r6
            r25 = r10
            r29 = r31
            r6 = 0
            r10 = r5
            r5 = r4
            r4 = r28
            r28 = r8
            goto L_0x039d
        L_0x0297:
            int r4 = r10 + 1
            r7 = r24[r10]
            java.lang.String r7 = (java.lang.String) r7
            java.lang.reflect.Field r7 = M(r5, r7)
            r31 = r4
            r4 = 9
            if (r3 == r4) goto L_0x02ab
            r4 = 17
            if (r3 != r4) goto L_0x02af
        L_0x02ab:
            r28 = r8
            goto L_0x031f
        L_0x02af:
            r4 = 27
            if (r3 == r4) goto L_0x02b7
            r4 = 49
            if (r3 != r4) goto L_0x02ba
        L_0x02b7:
            r28 = r8
            goto L_0x0312
        L_0x02ba:
            r4 = 12
            if (r3 == r4) goto L_0x02f6
            r4 = 30
            if (r3 == r4) goto L_0x02f6
            r4 = 44
            if (r3 != r4) goto L_0x02c7
            goto L_0x02f6
        L_0x02c7:
            r4 = 50
            if (r3 != r4) goto L_0x02f3
            int r4 = r22 + 1
            r15[r22] = r21
            int r22 = r21 / 3
            int r22 = r22 * 2
            int r28 = r10 + 2
            r29 = r24[r31]
            r11[r22] = r29
            r29 = r4
            r4 = r2 & 2048(0x800, float:2.87E-42)
            if (r4 == 0) goto L_0x02ec
            int r22 = r22 + 1
            int r4 = r10 + 3
            r10 = r24[r28]
            r11[r22] = r10
            r28 = r8
            r22 = r29
            goto L_0x032d
        L_0x02ec:
            r4 = r28
            r22 = r29
            r28 = r8
            goto L_0x032d
        L_0x02f3:
            r28 = r8
            goto L_0x032b
        L_0x02f6:
            com.google.protobuf.c0 r4 = r0.a()
            r28 = r8
            com.google.protobuf.c0 r8 = com.google.protobuf.c0.PROTO2
            if (r4 == r8) goto L_0x0304
            r4 = r2 & 2048(0x800, float:2.87E-42)
            if (r4 == 0) goto L_0x032b
        L_0x0304:
            int r4 = r21 / 3
            int r4 = r4 * 2
            int r4 = r4 + 1
            int r10 = r10 + 2
            r8 = r24[r31]
            r11[r4] = r8
        L_0x0310:
            r4 = r10
            goto L_0x032d
        L_0x0312:
            int r4 = r21 / 3
            int r4 = r4 * 2
            int r4 = r4 + 1
            int r10 = r10 + 2
            r8 = r24[r31]
            r11[r4] = r8
            goto L_0x0310
        L_0x031f:
            int r4 = r21 / 3
            int r4 = r4 * 2
            int r4 = r4 + 1
            java.lang.Class r8 = r7.getType()
            r11[r4] = r8
        L_0x032b:
            r4 = r31
        L_0x032d:
            long r7 = r14.objectFieldOffset(r7)
            int r7 = (int) r7
            r8 = r2 & 4096(0x1000, float:5.74E-42)
            if (r8 == 0) goto L_0x0384
            r8 = 17
            if (r3 > r8) goto L_0x0384
            int r8 = r6 + 1
            char r6 = r1.charAt(r6)
            r10 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r10) goto L_0x035f
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r25 = 13
        L_0x0349:
            int r29 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r10) goto L_0x035b
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r25
            r6 = r6 | r8
            int r25 = r25 + 13
            r8 = r29
            goto L_0x0349
        L_0x035b:
            int r8 = r8 << r25
            r6 = r6 | r8
            goto L_0x0361
        L_0x035f:
            r29 = r8
        L_0x0361:
            int r8 = r30 * 2
            int r25 = r6 / 32
            int r25 = r25 + r8
            r8 = r24[r25]
            boolean r10 = r8 instanceof java.lang.reflect.Field
            if (r10 == 0) goto L_0x0373
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
        L_0x036f:
            r25 = r4
            r10 = r5
            goto L_0x037c
        L_0x0373:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = M(r5, r8)
            r24[r25] = r8
            goto L_0x036f
        L_0x037c:
            long r4 = r14.objectFieldOffset(r8)
            int r4 = (int) r4
            int r6 = r6 % 32
            goto L_0x038d
        L_0x0384:
            r25 = r4
            r10 = r5
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r29 = r6
            r6 = 0
        L_0x038d:
            r5 = 18
            if (r3 < r5) goto L_0x039b
            r5 = 49
            if (r3 > r5) goto L_0x039b
            int r5 = r23 + 1
            r15[r23] = r7
            r23 = r5
        L_0x039b:
            r5 = r4
            r4 = r7
        L_0x039d:
            int r7 = r21 + 1
            r28[r21] = r26
            int r8 = r21 + 2
            r26 = r1
            r1 = r2 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x03ac
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03ad
        L_0x03ac:
            r1 = 0
        L_0x03ad:
            r31 = r1
            r1 = r2 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x03b6
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03b7
        L_0x03b6:
            r1 = 0
        L_0x03b7:
            r1 = r31 | r1
            r2 = r2 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x03c0
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03c1
        L_0x03c0:
            r2 = 0
        L_0x03c1:
            r1 = r1 | r2
            int r2 = r3 << 20
            r1 = r1 | r2
            r1 = r1 | r4
            r28[r7] = r1
            int r21 = r21 + 3
            int r1 = r6 << 20
            r1 = r1 | r5
            r28[r8] = r1
            r5 = r10
            r3 = r24
            r10 = r25
            r1 = r26
            r2 = r27
            r8 = r28
            r4 = r29
            r7 = r30
            r6 = 55296(0xd800, float:7.7486E-41)
            goto L_0x017e
        L_0x03e3:
            r28 = r8
            com.google.protobuf.X r1 = new com.google.protobuf.X
            com.google.protobuf.MessageLite r14 = r0.f1603a
            r0.a()
            r18 = r34
            r19 = r35
            r20 = r36
            r21 = r37
            r22 = r38
            r17 = r9
            r10 = r28
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.B(com.google.protobuf.f0, com.google.protobuf.Z, com.google.protobuf.L, com.google.protobuf.h0, com.google.protobuf.r, com.google.protobuf.S):com.google.protobuf.X");
    }

    public static long C(int i2) {
        return (long) (i2 & 1048575);
    }

    public static int D(Object obj, long j2) {
        return ((Integer) p0.f1625c.k(obj, j2)).intValue();
    }

    public static long E(Object obj, long j2) {
        return ((Long) p0.f1625c.k(obj, j2)).longValue();
    }

    public static Field M(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder k10 = j.k("Field ", str, " for ");
            k10.append(cls.getName());
            k10.append(" not found. Known fields are ");
            k10.append(Arrays.toString(declaredFields));
            throw new RuntimeException(k10.toString());
        }
    }

    public static int S(int i2) {
        return (i2 & 267386880) >>> 20;
    }

    public static void V(int i2, Object obj, P p6) {
        if (obj instanceof String) {
            ((CodedOutputStream) p6.f1586a).o0(i2, (String) obj);
            return;
        }
        p6.a(i2, (ByteString) obj);
    }

    public static void l(Object obj) {
        if (!t(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + obj);
        }
    }

    public static UnknownFieldSetLite q(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.f) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
        generatedMessageLite.unknownFields = unknownFieldSetLite2;
        return unknownFieldSetLite2;
    }

    public static boolean t(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    public final void F(int i2, long j2, Object obj) {
        Unsafe unsafe = f1591o;
        Object o2 = o(i2);
        Object object = unsafe.getObject(obj, j2);
        this.m.getClass();
        if (!((Q) object).d) {
            Q c5 = Q.e.c();
            S.a(c5, object);
            unsafe.putObject(obj, j2, c5);
        }
        a.t(o2);
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v69, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v70, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v73, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v74, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02aa, code lost:
        r10 = r3;
        r11 = r9;
        r9 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0305, code lost:
        r4 = r33;
        r7 = r12;
        r8 = r13;
        r6 = r17;
        r16 = r24;
        r13 = r25 | r23;
        r3 = r9;
        r9 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x031b, code lost:
        r11 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x031c, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x035a, code lost:
        r3 = r9;
        r9 = r10;
        r7 = r12;
        r8 = r13;
        r6 = r17;
        r16 = r24;
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0363, code lost:
        r1 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x03c3, code lost:
        r10 = r34;
        r9 = r1;
        r5 = r8;
        r26 = r11;
        r7 = r12;
        r16 = r13;
        r2 = r14;
        r6 = r17;
        r15 = r24;
        r13 = r25;
        r32 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x001b, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x001b, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x001b, code lost:
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00be, code lost:
        r10 = r9;
        r9 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f1, code lost:
        r4 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x012f, code lost:
        r16 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0138, code lost:
        r11 = r2;
        r17 = r10;
        r10 = r9;
        r9 = r8;
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x015e, code lost:
        r4 = r25 | r23;
        r5 = r2;
        r2 = r1;
        r1 = r5;
        r5 = r3;
        r3 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0165, code lost:
        r6 = r10;
        r7 = r12;
        r8 = r13;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0168, code lost:
        r16 = r24;
        r13 = r4;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01fe, code lost:
        r4 = r25 | r23;
        r5 = r3;
        r3 = r1;
        r1 = r9;
        r9 = r5;
        r5 = r2;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0213, code lost:
        r11 = r9;
        r17 = r10;
        r9 = r1;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0218, code lost:
        r1 = r8;
        r8 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int G(java.lang.Object r30, byte[] r31, int r32, int r33, int r34, Od.b r35) {
        /*
            r29 = this;
            r0 = r29
            r2 = r30
            r3 = r31
            r4 = r33
            r9 = r35
            l(r2)
            sun.misc.Unsafe r1 = f1591o
            r5 = r32
            r6 = -1
            r7 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r13 = 0
            r14 = 0
            r16 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001b:
            if (r5 >= r4) goto L_0x0506
            int r14 = r5 + 1
            byte r5 = r3[r5]
            if (r5 >= 0) goto L_0x0029
            int r14 = com.google.protobuf.C0133e.g(r5, r3, r14, r9)
            int r5 = r9.f3609a
        L_0x0029:
            r27 = r14
            r14 = r5
            r5 = r27
            int r10 = r14 >>> 3
            r17 = r7
            r7 = r14 & 7
            int r12 = r0.d
            int r11 = r0.f1593c
            r3 = 3
            if (r10 <= r6) goto L_0x004b
            int r6 = r17 / 3
            if (r10 < r11) goto L_0x0046
            if (r10 > r12) goto L_0x0046
            int r6 = r0.P(r10, r6)
            goto L_0x0047
        L_0x0046:
            r6 = -1
        L_0x0047:
            r11 = 0
        L_0x0048:
            r12 = r6
            r6 = -1
            goto L_0x0058
        L_0x004b:
            if (r10 < r11) goto L_0x0055
            if (r10 > r12) goto L_0x0055
            r11 = 0
            int r6 = r0.P(r10, r11)
            goto L_0x0048
        L_0x0055:
            r11 = 0
            r6 = -1
            goto L_0x0048
        L_0x0058:
            if (r12 != r6) goto L_0x006f
            r26 = r1
            r9 = r2
            r18 = r6
            r6 = r10
            r7 = r11
            r19 = r7
            r2 = r14
            r15 = r16
            r32 = 0
            r10 = r34
            r16 = r8
        L_0x006c:
            r8 = r0
            goto L_0x04dc
        L_0x006f:
            int r17 = r12 + 1
            int[] r6 = r0.f1592a
            r11 = r6[r17]
            int r3 = S(r11)
            r4 = r11 & r16
            r20 = r5
            long r4 = (long) r4
            r21 = r4
            r4 = 17
            if (r3 > r4) goto L_0x03d9
            int r4 = r12 + 2
            r4 = r6[r4]
            int r6 = r4 >>> 20
            r5 = 1
            int r23 = r5 << r6
            r4 = r4 & r16
            if (r4 == r8) goto L_0x00ab
            r6 = r16
            if (r8 == r6) goto L_0x009c
            long r5 = (long) r8
            r1.putInt(r2, r5, r13)
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x009c:
            if (r4 != r6) goto L_0x00a1
            r5 = r7
            r6 = 0
            goto L_0x00a7
        L_0x00a1:
            r5 = r7
            long r6 = (long) r4
            int r6 = r1.getInt(r2, r6)
        L_0x00a7:
            r13 = r4
            r25 = r6
            goto L_0x00af
        L_0x00ab:
            r5 = r7
            r25 = r13
            r13 = r8
        L_0x00af:
            r4 = 5
            switch(r3) {
                case 0: goto L_0x038c;
                case 1: goto L_0x0366;
                case 2: goto L_0x033a;
                case 3: goto L_0x033a;
                case 4: goto L_0x031f;
                case 5: goto L_0x02dc;
                case 6: goto L_0x02af;
                case 7: goto L_0x0273;
                case 8: goto L_0x021c;
                case 9: goto L_0x01d2;
                case 10: goto L_0x01b6;
                case 11: goto L_0x031f;
                case 12: goto L_0x016c;
                case 13: goto L_0x02af;
                case 14: goto L_0x02dc;
                case 15: goto L_0x0140;
                case 16: goto L_0x0101;
                case 17: goto L_0x00c3;
                default: goto L_0x00b3;
            }
        L_0x00b3:
            r11 = r1
            r1 = r2
            r17 = r10
            r8 = r20
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
        L_0x00be:
            r10 = r9
            r9 = r31
            goto L_0x03c3
        L_0x00c3:
            r7 = r5
            r3 = 3
            if (r7 != r3) goto L_0x00f5
            java.lang.Object r3 = r0.y(r12, r2)
            int r4 = r10 << 3
            r8 = r4 | 4
            com.google.protobuf.Schema r4 = r0.p(r12)
            r5 = r31
            r7 = r33
            r6 = r20
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            int r4 = com.google.protobuf.C0133e.k(r3, r4, r5, r6, r7, r8, r9)
            r8 = r5
            r0.Q(r12, r2, r3)
            r3 = r25 | r23
            r5 = r13
            r13 = r3
            r3 = r8
            r8 = r5
            r5 = r4
            r6 = r10
            r7 = r12
            r16 = r24
        L_0x00f1:
            r4 = r33
            goto L_0x001b
        L_0x00f5:
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r11 = r1
            r1 = r2
            r17 = r10
            r8 = r20
            goto L_0x00be
        L_0x0101:
            r8 = r31
            r7 = r5
            r3 = r20
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0133
            int r7 = com.google.protobuf.C0133e.j(r8, r3, r9)
            long r3 = r9.b
            long r5 = com.google.protobuf.CodedInputStream.c(r3)
            r3 = r21
            r1.putLong(r2, r3, r5)
            r27 = r2
            r2 = r1
            r1 = r27
            r3 = r25 | r23
            r4 = r2
            r2 = r1
            r1 = r4
            r4 = r13
            r13 = r3
            r3 = r8
            r8 = r4
            r4 = r33
            r5 = r7
            r6 = r10
            r7 = r12
        L_0x012f:
            r16 = r24
            goto L_0x001b
        L_0x0133:
            r27 = r2
            r2 = r1
            r1 = r27
        L_0x0138:
            r11 = r2
            r17 = r10
            r10 = r9
            r9 = r8
            r8 = r3
            goto L_0x03c3
        L_0x0140:
            r3 = r2
            r2 = r1
            r1 = r3
            r8 = r31
            r7 = r5
            r3 = r20
            r5 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0138
            int r3 = com.google.protobuf.C0133e.h(r8, r3, r9)
            int r4 = r9.f3609a
            int r4 = com.google.protobuf.CodedInputStream.b(r4)
            r2.putInt(r1, r5, r4)
        L_0x015e:
            r4 = r25 | r23
            r5 = r2
            r2 = r1
            r1 = r5
            r5 = r3
            r3 = r8
        L_0x0165:
            r6 = r10
            r7 = r12
            r8 = r13
        L_0x0168:
            r16 = r24
            r13 = r4
            goto L_0x00f1
        L_0x016c:
            r3 = r2
            r2 = r1
            r1 = r3
            r8 = r31
            r7 = r5
            r3 = r20
            r5 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != 0) goto L_0x0138
            int r3 = com.google.protobuf.C0133e.h(r8, r3, r9)
            int r4 = r9.f3609a
            com.google.protobuf.Internal$EnumVerifier r7 = r0.n(r12)
            r16 = -2147483648(0xffffffff80000000, float:-0.0)
            r11 = r11 & r16
            if (r11 == 0) goto L_0x01b2
            if (r7 == 0) goto L_0x01b2
            boolean r7 = r7.isInRange(r4)
            if (r7 == 0) goto L_0x0196
            goto L_0x01b2
        L_0x0196:
            com.google.protobuf.UnknownFieldSetLite r5 = q(r1)
            long r6 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            r5.f(r14, r4)
            r4 = r2
            r2 = r1
            r1 = r4
            r4 = r33
            r5 = r3
            r3 = r8
            r6 = r10
            r7 = r12
            r8 = r13
            r16 = r24
            r13 = r25
            goto L_0x001b
        L_0x01b2:
            r2.putInt(r1, r5, r4)
            goto L_0x015e
        L_0x01b6:
            r3 = r2
            r2 = r1
            r1 = r3
            r8 = r31
            r7 = r5
            r3 = r20
            r5 = r21
            r4 = 2
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r4) goto L_0x0138
            int r3 = com.google.protobuf.C0133e.a(r8, r3, r9)
            java.lang.Object r4 = r9.f3610c
            r2.putObject(r1, r5, r4)
            goto L_0x015e
        L_0x01d2:
            r3 = r2
            r2 = r1
            r1 = r3
            r8 = r31
            r7 = r5
            r3 = r20
            r4 = 2
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 != r4) goto L_0x0208
            r4 = r1
            java.lang.Object r1 = r0.y(r12, r4)
            r5 = r2
            com.google.protobuf.Schema r2 = r0.p(r12)
            r6 = r4
            r4 = r3
            r3 = r8
            r8 = r6
            r6 = r9
            r9 = r5
            r5 = r33
            int r2 = com.google.protobuf.C0133e.l(r1, r2, r3, r4, r5, r6)
            r4 = r1
            r1 = r3
            r3 = r6
            r0.Q(r12, r8, r4)
        L_0x01fe:
            r4 = r25 | r23
            r5 = r3
            r3 = r1
            r1 = r9
            r9 = r5
            r5 = r2
            r2 = r8
            goto L_0x0165
        L_0x0208:
            r27 = r8
            r8 = r1
            r1 = r27
            r27 = r9
            r9 = r2
            r2 = r3
            r3 = r27
        L_0x0213:
            r11 = r9
            r17 = r10
            r9 = r1
            r10 = r3
        L_0x0218:
            r1 = r8
            r8 = r2
            goto L_0x03c3
        L_0x021c:
            r8 = r2
            r7 = r5
            r3 = r9
            r2 = r20
            r5 = r21
            r4 = 2
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r1
            r1 = r31
            if (r7 != r4) goto L_0x0213
            r4 = 536870912(0x20000000, float:1.0842022E-19)
            r4 = r4 & r11
            java.lang.String r7 = ""
            if (r4 == 0) goto L_0x0251
            int r2 = com.google.protobuf.C0133e.h(r1, r2, r3)
            int r4 = r3.f3609a
            if (r4 < 0) goto L_0x024c
            if (r4 != 0) goto L_0x0242
            r3.f3610c = r7
            goto L_0x0268
        L_0x0242:
            Gd.a r7 = com.google.protobuf.s0.f1629a
            java.lang.String r7 = r7.o(r2, r4, r1)
            r3.f3610c = r7
        L_0x024a:
            int r2 = r2 + r4
            goto L_0x0268
        L_0x024c:
            com.google.protobuf.F r0 = com.google.protobuf.F.e()
            throw r0
        L_0x0251:
            int r2 = com.google.protobuf.C0133e.h(r1, r2, r3)
            int r4 = r3.f3609a
            if (r4 < 0) goto L_0x026e
            if (r4 != 0) goto L_0x025e
            r3.f3610c = r7
            goto L_0x0268
        L_0x025e:
            java.lang.String r7 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.protobuf.D.f1578a
            r7.<init>(r1, r2, r4, r11)
            r3.f3610c = r7
            goto L_0x024a
        L_0x0268:
            java.lang.Object r4 = r3.f3610c
            r9.putObject(r8, r5, r4)
            goto L_0x01fe
        L_0x026e:
            com.google.protobuf.F r0 = com.google.protobuf.F.e()
            throw r0
        L_0x0273:
            r8 = r2
            r7 = r5
            r3 = r9
            r2 = r20
            r5 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r1
            r1 = r31
            if (r7 != 0) goto L_0x02a8
            int r2 = com.google.protobuf.C0133e.j(r1, r2, r3)
            r17 = r10
            long r10 = r3.b
            r20 = 0
            int r4 = (r10 > r20 ? 1 : (r10 == r20 ? 0 : -1))
            if (r4 == 0) goto L_0x0294
            r4 = 1
            goto L_0x0295
        L_0x0294:
            r4 = 0
        L_0x0295:
            com.google.protobuf.o0 r7 = com.google.protobuf.p0.f1625c
            r7.m(r8, r5, r4)
            r4 = r25 | r23
            r5 = r3
            r3 = r1
            r1 = r9
            r9 = r5
            r5 = r2
            r2 = r8
            r7 = r12
            r8 = r13
            r6 = r17
            goto L_0x0168
        L_0x02a8:
            r17 = r10
        L_0x02aa:
            r10 = r3
            r11 = r9
            r9 = r1
            goto L_0x0218
        L_0x02af:
            r8 = r2
            r7 = r5
            r3 = r9
            r17 = r10
            r2 = r20
            r5 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r1
            r1 = r31
            if (r7 != r4) goto L_0x02aa
            int r4 = com.google.protobuf.C0133e.b(r1, r2)
            r9.putInt(r8, r5, r4)
            int r5 = r2 + 4
            r2 = r25 | r23
            r4 = r3
            r3 = r1
            r1 = r9
            r9 = r4
            r4 = r13
            r13 = r2
            r2 = r8
            r8 = r4
            r4 = r33
            r7 = r12
            r6 = r17
            goto L_0x012f
        L_0x02dc:
            r8 = r2
            r7 = r5
            r3 = r9
            r17 = r10
            r2 = r20
            r5 = r21
            r4 = 1
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r1
            r1 = r31
            if (r7 != r4) goto L_0x0314
            r10 = r5
            long r5 = com.google.protobuf.C0133e.c(r1, r2)
            r4 = r9
            r9 = r1
            r1 = r4
            r4 = r8
            r8 = r2
            r2 = r4
            r27 = r10
            r10 = r3
            r3 = r27
            r1.putLong(r2, r3, r5)
            int r5 = r8 + 8
        L_0x0305:
            r3 = r25 | r23
            r4 = r33
            r7 = r12
            r8 = r13
            r6 = r17
            r16 = r24
            r13 = r3
            r3 = r9
            r9 = r10
            goto L_0x001b
        L_0x0314:
            r10 = r9
            r9 = r1
            r1 = r10
            r10 = r8
            r8 = r2
            r2 = r10
            r10 = r3
        L_0x031b:
            r11 = r1
        L_0x031c:
            r1 = r2
            goto L_0x03c3
        L_0x031f:
            r7 = r5
            r17 = r10
            r8 = r20
            r3 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r9
            r9 = r31
            if (r7 != 0) goto L_0x031b
            int r5 = com.google.protobuf.C0133e.h(r9, r8, r10)
            int r6 = r10.f3609a
            r1.putInt(r2, r3, r6)
            goto L_0x0305
        L_0x033a:
            r7 = r5
            r17 = r10
            r8 = r20
            r3 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r9
            r9 = r31
            if (r7 != 0) goto L_0x031b
            int r7 = com.google.protobuf.C0133e.j(r9, r8, r10)
            long r5 = r10.b
            r1.putLong(r2, r3, r5)
            r11 = r1
            r1 = r25 | r23
            r4 = r33
            r5 = r7
        L_0x035a:
            r3 = r9
            r9 = r10
            r7 = r12
            r8 = r13
            r6 = r17
            r16 = r24
            r13 = r1
        L_0x0363:
            r1 = r11
            goto L_0x001b
        L_0x0366:
            r11 = r1
            r7 = r5
            r17 = r10
            r8 = r20
            r5 = r21
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r9
            r9 = r31
            if (r7 != r4) goto L_0x031c
            int r1 = com.google.protobuf.C0133e.b(r9, r8)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            com.google.protobuf.o0 r3 = com.google.protobuf.p0.f1625c
            r3.p(r2, r5, r1)
            int r5 = r8 + 4
            r1 = r25 | r23
            r4 = r33
            goto L_0x035a
        L_0x038c:
            r11 = r1
            r7 = r5
            r17 = r10
            r8 = r20
            r5 = r21
            r4 = 1
            r18 = -1
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r9
            r9 = r31
            if (r7 != r4) goto L_0x031c
            long r3 = com.google.protobuf.C0133e.c(r9, r8)
            double r3 = java.lang.Double.longBitsToDouble(r3)
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            r27 = r5
            r5 = r3
            r3 = r27
            r1.o(r2, r3, r5)
            r1 = r2
            int r5 = r8 + 8
            r2 = r25 | r23
            r4 = r33
            r3 = r9
            r9 = r10
            r7 = r12
            r8 = r13
            r6 = r17
            r16 = r24
            r13 = r2
            r2 = r1
            goto L_0x0363
        L_0x03c3:
            r10 = r34
            r9 = r1
            r5 = r8
            r26 = r11
            r7 = r12
            r16 = r13
            r2 = r14
            r6 = r17
            r15 = r24
            r13 = r25
            r32 = 0
            r19 = 0
            goto L_0x006c
        L_0x03d9:
            r5 = r2
            r2 = r1
            r1 = r5
            r17 = r10
            r24 = r16
            r5 = r21
            r18 = -1
            r10 = r9
            r9 = r31
            r4 = 27
            if (r3 != r4) goto L_0x0444
            r4 = 2
            if (r7 != r4) goto L_0x042e
            java.lang.Object r3 = r2.getObject(r1, r5)
            com.google.protobuf.Internal$ProtobufList r3 = (com.google.protobuf.Internal$ProtobufList) r3
            boolean r4 = r3.j()
            if (r4 != 0) goto L_0x040c
            int r4 = r3.size()
            if (r4 != 0) goto L_0x0403
            r4 = 10
            goto L_0x0405
        L_0x0403:
            int r4 = r4 * 2
        L_0x0405:
            com.google.protobuf.Internal$ProtobufList r3 = r3.a(r4)
            r2.putObject(r1, r5, r3)
        L_0x040c:
            r6 = r3
            com.google.protobuf.Schema r1 = r0.p(r12)
            r5 = r33
            r3 = r9
            r7 = r10
            r4 = r20
            r9 = r2
            r2 = r14
            int r1 = com.google.protobuf.C0133e.e(r1, r2, r3, r4, r5, r6, r7)
            r3 = r31
            r4 = r33
            r5 = r1
            r1 = r9
            r7 = r12
            r6 = r17
            r16 = r24
            r2 = r30
        L_0x042a:
            r9 = r35
            goto L_0x001b
        L_0x042e:
            r9 = r2
            r1 = r30
            r16 = r8
            r26 = r9
            r25 = r13
            r2 = r14
            r6 = r17
            r3 = r20
            r15 = r24
            r32 = 0
            r19 = 0
            goto L_0x04aa
        L_0x0444:
            r9 = r2
            r2 = r14
            r1 = 49
            if (r3 > r1) goto L_0x048e
            r1 = r9
            long r9 = (long) r11
            r4 = r33
            r14 = r35
            r26 = r1
            r11 = r3
            r16 = r8
            r8 = r12
            r25 = r13
            r3 = r20
            r15 = r24
            r32 = 0
            r19 = 0
            r1 = r30
            r12 = r5
            r6 = r17
            r5 = r2
            r2 = r31
            int r7 = r0.I(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            r2 = r5
            r12 = r8
            if (r7 == r3) goto L_0x0484
            r3 = r31
            r4 = r33
            r9 = r35
            r14 = r2
            r5 = r7
            r7 = r12
            r8 = r16
            r13 = r25
            r2 = r1
            r16 = r15
            r1 = r26
            goto L_0x001b
        L_0x0484:
            r10 = r34
            r8 = r0
            r9 = r1
        L_0x0488:
            r5 = r7
        L_0x0489:
            r7 = r12
            r13 = r25
            goto L_0x04dc
        L_0x048e:
            r1 = r30
            r16 = r8
            r26 = r9
            r8 = r11
            r25 = r13
            r15 = r24
            r32 = 0
            r19 = 0
            r9 = r3
            r10 = r5
            r6 = r17
            r3 = r20
            r4 = 50
            if (r9 != r4) goto L_0x04b4
            r4 = 2
            if (r7 == r4) goto L_0x04b0
        L_0x04aa:
            r10 = r34
            r8 = r0
            r9 = r1
            r5 = r3
            goto L_0x0489
        L_0x04b0:
            r0.F(r12, r10, r1)
            throw r32
        L_0x04b4:
            r4 = r33
            r13 = r35
            r5 = r2
            r2 = r31
            int r7 = r0.H(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            r8 = r0
            r9 = r1
            r2 = r5
            if (r7 == r3) goto L_0x04d9
            r3 = r31
            r4 = r33
            r14 = r2
            r5 = r7
            r0 = r8
            r2 = r9
            r7 = r12
            r8 = r16
            r13 = r25
            r1 = r26
            r9 = r35
            r16 = r15
            goto L_0x001b
        L_0x04d9:
            r10 = r34
            goto L_0x0488
        L_0x04dc:
            if (r2 != r10) goto L_0x04e6
            if (r10 == 0) goto L_0x04e6
            r4 = r33
            r14 = r2
        L_0x04e3:
            r0 = r16
            goto L_0x0515
        L_0x04e6:
            com.google.protobuf.UnknownFieldSetLite r4 = q(r9)
            r1 = r31
            r3 = r33
            r0 = r2
            r2 = r5
            r5 = r35
            int r2 = com.google.protobuf.C0133e.f(r0, r1, r2, r3, r4, r5)
            r5 = r0
            r4 = r3
            r14 = r5
            r0 = r8
            r8 = r16
            r1 = r26
            r3 = r31
            r5 = r2
            r2 = r9
            r16 = r15
            goto L_0x042a
        L_0x0506:
            r10 = r34
            r26 = r1
            r9 = r2
            r25 = r13
            r15 = r16
            r32 = 0
            r16 = r8
            r8 = r0
            goto L_0x04e3
        L_0x0515:
            if (r0 == r15) goto L_0x051d
            long r0 = (long) r0
            r2 = r26
            r2.putInt(r9, r0, r13)
        L_0x051d:
            int r0 = r8.f1594h
        L_0x051f:
            int r1 = r8.f1595i
            if (r0 >= r1) goto L_0x052f
            int[] r1 = r8.g
            r1 = r1[r0]
            r2 = r32
            r8.m(r1, r9, r2)
            int r0 = r0 + 1
            goto L_0x051f
        L_0x052f:
            if (r10 != 0) goto L_0x0539
            if (r5 != r4) goto L_0x0534
            goto L_0x053d
        L_0x0534:
            com.google.protobuf.F r0 = com.google.protobuf.F.f()
            throw r0
        L_0x0539:
            if (r5 > r4) goto L_0x053e
            if (r14 != r10) goto L_0x053e
        L_0x053d:
            return r5
        L_0x053e:
            com.google.protobuf.F r0 = com.google.protobuf.F.f()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.G(java.lang.Object, byte[], int, int, int, Od.b):int");
    }

    public final int H(Object obj, byte[] bArr, int i2, int i7, int i8, int i10, int i11, int i12, int i13, long j2, int i14, b bVar) {
        int i15 = i10;
        int i16 = i11;
        long j3 = j2;
        int i17 = i14;
        Unsafe unsafe = f1591o;
        long j8 = (long) (this.f1592a[i17 + 2] & 1048575);
        boolean z = true;
        switch (i13) {
            case 51:
                byte[] bArr2 = bArr;
                int i18 = i2;
                if (i16 != 1) {
                    return i18;
                }
                unsafe.putObject(obj, j3, Double.valueOf(Double.longBitsToDouble(C0133e.c(bArr, i2))));
                int i19 = i18 + 8;
                unsafe.putInt(obj, j8, i15);
                return i19;
            case 52:
                byte[] bArr3 = bArr;
                int i20 = i2;
                if (i16 != 5) {
                    return i20;
                }
                unsafe.putObject(obj, j3, Float.valueOf(Float.intBitsToFloat(C0133e.b(bArr, i2))));
                int i21 = i20 + 4;
                unsafe.putInt(obj, j8, i15);
                return i21;
            case 53:
            case 54:
                byte[] bArr4 = bArr;
                int i22 = i2;
                b bVar2 = bVar;
                if (i16 != 0) {
                    return i22;
                }
                int j10 = C0133e.j(bArr4, i22, bVar2);
                unsafe.putObject(obj, j3, Long.valueOf(bVar2.b));
                unsafe.putInt(obj, j8, i15);
                return j10;
            case 55:
            case 62:
                byte[] bArr5 = bArr;
                int i23 = i2;
                b bVar3 = bVar;
                if (i16 != 0) {
                    return i23;
                }
                int h5 = C0133e.h(bArr5, i23, bVar3);
                unsafe.putObject(obj, j3, Integer.valueOf(bVar3.f3609a));
                unsafe.putInt(obj, j8, i15);
                return h5;
            case 56:
            case 65:
                byte[] bArr6 = bArr;
                int i24 = i2;
                if (i16 != 1) {
                    return i24;
                }
                unsafe.putObject(obj, j3, Long.valueOf(C0133e.c(bArr, i2)));
                int i25 = i24 + 8;
                unsafe.putInt(obj, j8, i15);
                return i25;
            case 57:
            case 64:
                byte[] bArr7 = bArr;
                int i26 = i2;
                if (i16 != 5) {
                    return i26;
                }
                unsafe.putObject(obj, j3, Integer.valueOf(C0133e.b(bArr, i2)));
                int i27 = i26 + 4;
                unsafe.putInt(obj, j8, i15);
                return i27;
            case 58:
                byte[] bArr8 = bArr;
                int i28 = i2;
                b bVar4 = bVar;
                if (i16 != 0) {
                    return i28;
                }
                int j11 = C0133e.j(bArr8, i28, bVar4);
                if (bVar4.b == 0) {
                    z = false;
                }
                unsafe.putObject(obj, j3, Boolean.valueOf(z));
                unsafe.putInt(obj, j8, i15);
                return j11;
            case 59:
                byte[] bArr9 = bArr;
                int i29 = i2;
                b bVar5 = bVar;
                if (i16 != 2) {
                    return i29;
                }
                int h6 = C0133e.h(bArr9, i29, bVar5);
                int i30 = bVar5.f3609a;
                if (i30 == 0) {
                    unsafe.putObject(obj, j3, "");
                } else {
                    if ((i12 & PropertyOptions.DELETE_EXISTING) != 0) {
                        if (!s0.f1629a.Q(h6, h6 + i30, bArr9)) {
                            throw F.b();
                        }
                    }
                    unsafe.putObject(obj, j3, new String(bArr9, h6, i30, D.f1578a));
                    h6 += i30;
                }
                unsafe.putInt(obj, j8, i15);
                return h6;
            case 60:
                byte[] bArr10 = bArr;
                int i31 = i2;
                b bVar6 = bVar;
                if (i16 != 2) {
                    return i31;
                }
                Object z3 = z(i15, i17, obj);
                int l8 = C0133e.l(z3, p(i17), bArr10, i2, i7, bVar6);
                R(obj, i15, z3, i17);
                return l8;
            case 61:
                byte[] bArr11 = bArr;
                int i32 = i2;
                b bVar7 = bVar;
                if (i16 != 2) {
                    return i32;
                }
                int a7 = C0133e.a(bArr11, i32, bVar7);
                unsafe.putObject(obj, j3, bVar7.f3610c);
                unsafe.putInt(obj, j8, i15);
                return a7;
            case 63:
                byte[] bArr12 = bArr;
                int i33 = i2;
                int i34 = i8;
                b bVar8 = bVar;
                if (i16 != 0) {
                    return i33;
                }
                int h8 = C0133e.h(bArr12, i33, bVar8);
                int i35 = bVar8.f3609a;
                Internal$EnumVerifier n3 = n(i17);
                if (n3 == null || n3.isInRange(i35)) {
                    unsafe.putObject(obj, j3, Integer.valueOf(i35));
                    unsafe.putInt(obj, j8, i15);
                    return h8;
                }
                q(obj).f(i34, Long.valueOf((long) i35));
                return h8;
            case 66:
                byte[] bArr13 = bArr;
                int i36 = i2;
                b bVar9 = bVar;
                if (i16 != 0) {
                    return i36;
                }
                int h10 = C0133e.h(bArr13, i36, bVar9);
                unsafe.putObject(obj, j3, Integer.valueOf(CodedInputStream.b(bVar9.f3609a)));
                unsafe.putInt(obj, j8, i15);
                return h10;
            case 67:
                byte[] bArr14 = bArr;
                int i37 = i2;
                b bVar10 = bVar;
                if (i16 != 0) {
                    return i37;
                }
                int j12 = C0133e.j(bArr14, i37, bVar10);
                unsafe.putObject(obj, j3, Long.valueOf(CodedInputStream.c(bVar10.b)));
                unsafe.putInt(obj, j8, i15);
                return j12;
            case 68:
                if (i16 == 3) {
                    Object z7 = z(i15, i17, obj);
                    int k10 = C0133e.k(z7, p(i17), bArr, i2, i7, (i8 & -8) | 4, bVar);
                    R(obj, i15, z7, i17);
                    return k10;
                }
                break;
        }
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0259  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x01b5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x022e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x028f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0186  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int I(java.lang.Object r12, byte[] r13, int r14, int r15, int r16, int r17, int r18, int r19, long r20, int r22, long r23, Od.b r25) {
        /*
            r11 = this;
            r0 = r16
            r1 = r18
            r6 = r19
            r2 = r23
            sun.misc.Unsafe r4 = f1591o
            java.lang.Object r5 = r4.getObject(r12, r2)
            com.google.protobuf.Internal$ProtobufList r5 = (com.google.protobuf.Internal$ProtobufList) r5
            boolean r7 = r5.j()
            r8 = 2
            if (r7 != 0) goto L_0x0028
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0020
            r7 = 10
            goto L_0x0021
        L_0x0020:
            int r7 = r7 * r8
        L_0x0021:
            com.google.protobuf.Internal$ProtobufList r5 = r5.a(r7)
            r4.putObject(r12, r2, r5)
        L_0x0028:
            r4 = r5
            r2 = 5
            r9 = 0
            r3 = 1
            switch(r22) {
                case 18: goto L_0x044a;
                case 19: goto L_0x03f9;
                case 20: goto L_0x03b4;
                case 21: goto L_0x03b4;
                case 22: goto L_0x037f;
                case 23: goto L_0x033a;
                case 24: goto L_0x02f5;
                case 25: goto L_0x029a;
                case 26: goto L_0x01dc;
                case 27: goto L_0x01c0;
                case 28: goto L_0x0163;
                case 29: goto L_0x037f;
                case 30: goto L_0x0121;
                case 31: goto L_0x02f5;
                case 32: goto L_0x033a;
                case 33: goto L_0x00d0;
                case 34: goto L_0x007f;
                case 35: goto L_0x044a;
                case 36: goto L_0x03f9;
                case 37: goto L_0x03b4;
                case 38: goto L_0x03b4;
                case 39: goto L_0x037f;
                case 40: goto L_0x033a;
                case 41: goto L_0x02f5;
                case 42: goto L_0x029a;
                case 43: goto L_0x037f;
                case 44: goto L_0x0121;
                case 45: goto L_0x02f5;
                case 46: goto L_0x033a;
                case 47: goto L_0x00d0;
                case 48: goto L_0x007f;
                case 49: goto L_0x0032;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x049b
        L_0x0032:
            r12 = 3
            if (r1 != r12) goto L_0x049b
            com.google.protobuf.Schema r11 = r11.p(r6)
            r12 = r0 & -8
            r12 = r12 | 4
            r17 = r11
            r21 = r12
            r18 = r13
            r19 = r14
            r20 = r15
            r22 = r25
            int r11 = com.google.protobuf.C0133e.d(r17, r18, r19, r20, r21, r22)
            r12 = r17
            r3 = r20
            r2 = r21
            r5 = r22
            java.lang.Object r6 = r5.f3610c
            r4.add(r6)
        L_0x005a:
            if (r11 >= r3) goto L_0x007e
            int r6 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r7 = r5.f3609a
            if (r0 == r7) goto L_0x0065
            goto L_0x007e
        L_0x0065:
            r17 = r12
            r18 = r13
            r21 = r2
            r20 = r3
            r22 = r5
            r19 = r6
            int r11 = com.google.protobuf.C0133e.d(r17, r18, r19, r20, r21, r22)
            r1 = r21
            java.lang.Object r6 = r5.f3610c
            r4.add(r6)
            r2 = r1
            goto L_0x005a
        L_0x007e:
            return r11
        L_0x007f:
            r3 = r15
            r5 = r25
            if (r1 != r8) goto L_0x00a5
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r12 = r5.f3609a
            int r12 = r12 + r11
        L_0x008d:
            if (r11 >= r12) goto L_0x009d
            int r11 = com.google.protobuf.C0133e.j(r13, r11, r5)
            long r0 = r5.b
            long r0 = com.google.protobuf.CodedInputStream.c(r0)
            r4.p(r0)
            goto L_0x008d
        L_0x009d:
            if (r11 != r12) goto L_0x00a0
            return r11
        L_0x00a0:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x00a5:
            if (r1 != 0) goto L_0x049b
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            int r11 = com.google.protobuf.C0133e.j(r13, r14, r5)
            long r6 = r5.b
            long r6 = com.google.protobuf.CodedInputStream.c(r6)
            r4.p(r6)
        L_0x00b6:
            if (r11 >= r3) goto L_0x00cf
            int r12 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r1 = r5.f3609a
            if (r0 == r1) goto L_0x00c1
            goto L_0x00cf
        L_0x00c1:
            int r11 = com.google.protobuf.C0133e.j(r13, r12, r5)
            long r6 = r5.b
            long r6 = com.google.protobuf.CodedInputStream.c(r6)
            r4.p(r6)
            goto L_0x00b6
        L_0x00cf:
            return r11
        L_0x00d0:
            r3 = r15
            r5 = r25
            if (r1 != r8) goto L_0x00f6
            com.google.protobuf.B r4 = (com.google.protobuf.B) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r12 = r5.f3609a
            int r12 = r12 + r11
        L_0x00de:
            if (r11 >= r12) goto L_0x00ee
            int r11 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r0 = r5.f3609a
            int r0 = com.google.protobuf.CodedInputStream.b(r0)
            r4.k(r0)
            goto L_0x00de
        L_0x00ee:
            if (r11 != r12) goto L_0x00f1
            return r11
        L_0x00f1:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x00f6:
            if (r1 != 0) goto L_0x049b
            com.google.protobuf.B r4 = (com.google.protobuf.B) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r12 = r5.f3609a
            int r12 = com.google.protobuf.CodedInputStream.b(r12)
            r4.k(r12)
        L_0x0107:
            if (r11 >= r3) goto L_0x0120
            int r12 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r1 = r5.f3609a
            if (r0 == r1) goto L_0x0112
            goto L_0x0120
        L_0x0112:
            int r11 = com.google.protobuf.C0133e.h(r13, r12, r5)
            int r12 = r5.f3609a
            int r12 = com.google.protobuf.CodedInputStream.b(r12)
            r4.k(r12)
            goto L_0x0107
        L_0x0120:
            return r11
        L_0x0121:
            r3 = r15
            r5 = r25
            if (r1 != r8) goto L_0x0144
            r0 = r4
            com.google.protobuf.B r0 = (com.google.protobuf.B) r0
            int r1 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r3 = r5.f3609a
            int r3 = r3 + r1
        L_0x0130:
            if (r1 >= r3) goto L_0x013c
            int r1 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r7 = r5.f3609a
            r0.k(r7)
            goto L_0x0130
        L_0x013c:
            if (r1 != r3) goto L_0x013f
            goto L_0x014c
        L_0x013f:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x0144:
            if (r1 != 0) goto L_0x049b
            r1 = r13
            r2 = r14
            int r1 = com.google.protobuf.C0133e.i(r0, r1, r2, r3, r4, r5)
        L_0x014c:
            com.google.protobuf.Internal$EnumVerifier r0 = r11.n(r6)
            r2 = 0
            com.google.protobuf.h0 r11 = r11.l
            r23 = r11
            r18 = r12
            r19 = r17
            r21 = r0
            r22 = r2
            r20 = r4
            com.google.protobuf.g0.j(r18, r19, r20, r21, r22, r23)
            return r1
        L_0x0163:
            r3 = r15
            r5 = r25
            if (r1 != r8) goto L_0x049b
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r1 = r5.f3609a
            if (r1 < 0) goto L_0x01bb
            int r2 = r13.length
            int r2 = r2 - r11
            if (r1 > r2) goto L_0x01b6
            if (r1 != 0) goto L_0x017c
            com.google.protobuf.i r1 = com.google.protobuf.ByteString.e
            r4.add(r1)
            goto L_0x0184
        L_0x017c:
            com.google.protobuf.i r2 = com.google.protobuf.ByteString.r(r11, r1, r13)
            r4.add(r2)
        L_0x0183:
            int r11 = r11 + r1
        L_0x0184:
            if (r11 >= r3) goto L_0x01b5
            int r1 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r2 = r5.f3609a
            if (r0 == r2) goto L_0x018f
            goto L_0x01b5
        L_0x018f:
            int r11 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r1 = r5.f3609a
            if (r1 < 0) goto L_0x01b0
            int r2 = r13.length
            int r2 = r2 - r11
            if (r1 > r2) goto L_0x01ab
            if (r1 != 0) goto L_0x01a3
            com.google.protobuf.i r1 = com.google.protobuf.ByteString.e
            r4.add(r1)
            goto L_0x0184
        L_0x01a3:
            com.google.protobuf.i r2 = com.google.protobuf.ByteString.r(r11, r1, r13)
            r4.add(r2)
            goto L_0x0183
        L_0x01ab:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x01b0:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x01b5:
            return r11
        L_0x01b6:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x01bb:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x01c0:
            r3 = r15
            r5 = r25
            if (r1 != r8) goto L_0x049b
            com.google.protobuf.Schema r11 = r11.p(r6)
            r17 = r11
            r19 = r13
            r20 = r14
            r18 = r0
            r21 = r3
            r22 = r4
            r23 = r5
            int r11 = com.google.protobuf.C0133e.e(r17, r18, r19, r20, r21, r22, r23)
            return r11
        L_0x01dc:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x049b
            r1 = 536870912(0x20000000, double:2.652494739E-315)
            long r1 = r20 & r1
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x0234
            int r1 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r3 = r5.f3609a
            if (r3 < 0) goto L_0x022f
            if (r3 != 0) goto L_0x01fa
            r4.add(r2)
            goto L_0x0205
        L_0x01fa:
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r7 = com.google.protobuf.D.f1578a
            r6.<init>(r13, r1, r3, r7)
            r4.add(r6)
        L_0x0204:
            int r1 = r1 + r3
        L_0x0205:
            if (r1 >= r11) goto L_0x022e
            int r3 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r6 = r5.f3609a
            if (r0 == r6) goto L_0x0210
            goto L_0x022e
        L_0x0210:
            int r1 = com.google.protobuf.C0133e.h(r13, r3, r5)
            int r3 = r5.f3609a
            if (r3 < 0) goto L_0x0229
            if (r3 != 0) goto L_0x021e
            r4.add(r2)
            goto L_0x0205
        L_0x021e:
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r7 = com.google.protobuf.D.f1578a
            r6.<init>(r13, r1, r3, r7)
            r4.add(r6)
            goto L_0x0204
        L_0x0229:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x022e:
            return r1
        L_0x022f:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x0234:
            int r1 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r3 = r5.f3609a
            if (r3 < 0) goto L_0x0295
            if (r3 != 0) goto L_0x0242
            r4.add(r2)
            goto L_0x0257
        L_0x0242:
            int r6 = r1 + r3
            Gd.a r7 = com.google.protobuf.s0.f1629a
            boolean r7 = r7.Q(r1, r6, r13)
            if (r7 == 0) goto L_0x0290
            java.lang.String r7 = new java.lang.String
            java.nio.charset.Charset r8 = com.google.protobuf.D.f1578a
            r7.<init>(r13, r1, r3, r8)
            r4.add(r7)
        L_0x0256:
            r1 = r6
        L_0x0257:
            if (r1 >= r11) goto L_0x028f
            int r3 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r6 = r5.f3609a
            if (r0 == r6) goto L_0x0262
            goto L_0x028f
        L_0x0262:
            int r1 = com.google.protobuf.C0133e.h(r13, r3, r5)
            int r3 = r5.f3609a
            if (r3 < 0) goto L_0x028a
            if (r3 != 0) goto L_0x0270
            r4.add(r2)
            goto L_0x0257
        L_0x0270:
            int r6 = r1 + r3
            Gd.a r7 = com.google.protobuf.s0.f1629a
            boolean r7 = r7.Q(r1, r6, r13)
            if (r7 == 0) goto L_0x0285
            java.lang.String r7 = new java.lang.String
            java.nio.charset.Charset r8 = com.google.protobuf.D.f1578a
            r7.<init>(r13, r1, r3, r8)
            r4.add(r7)
            goto L_0x0256
        L_0x0285:
            com.google.protobuf.F r11 = com.google.protobuf.F.b()
            throw r11
        L_0x028a:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x028f:
            return r1
        L_0x0290:
            com.google.protobuf.F r11 = com.google.protobuf.F.b()
            throw r11
        L_0x0295:
            com.google.protobuf.F r11 = com.google.protobuf.F.e()
            throw r11
        L_0x029a:
            r11 = r15
            r5 = r25
            r2 = 0
            if (r1 != r8) goto L_0x02c4
            com.google.protobuf.f r4 = (com.google.protobuf.C0134f) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x02a9:
            if (r11 >= r0) goto L_0x02bc
            int r11 = com.google.protobuf.C0133e.j(r13, r11, r5)
            long r6 = r5.b
            int r1 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r1 == 0) goto L_0x02b7
            r1 = r3
            goto L_0x02b8
        L_0x02b7:
            r1 = r2
        L_0x02b8:
            r4.p(r1)
            goto L_0x02a9
        L_0x02bc:
            if (r11 != r0) goto L_0x02bf
            return r11
        L_0x02bf:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x02c4:
            if (r1 != 0) goto L_0x049b
            com.google.protobuf.f r4 = (com.google.protobuf.C0134f) r4
            int r1 = com.google.protobuf.C0133e.j(r13, r14, r5)
            long r6 = r5.b
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x02d4
            r6 = r3
            goto L_0x02d5
        L_0x02d4:
            r6 = r2
        L_0x02d5:
            r4.p(r6)
        L_0x02d8:
            if (r1 >= r11) goto L_0x02f4
            int r6 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r7 = r5.f3609a
            if (r0 == r7) goto L_0x02e3
            goto L_0x02f4
        L_0x02e3:
            int r1 = com.google.protobuf.C0133e.j(r13, r6, r5)
            long r6 = r5.b
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x02ef
            r6 = r3
            goto L_0x02f0
        L_0x02ef:
            r6 = r2
        L_0x02f0:
            r4.p(r6)
            goto L_0x02d8
        L_0x02f4:
            return r1
        L_0x02f5:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x0317
            com.google.protobuf.B r4 = (com.google.protobuf.B) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x0303:
            if (r11 >= r0) goto L_0x030f
            int r1 = com.google.protobuf.C0133e.b(r13, r11)
            r4.k(r1)
            int r11 = r11 + 4
            goto L_0x0303
        L_0x030f:
            if (r11 != r0) goto L_0x0312
            return r11
        L_0x0312:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x0317:
            if (r1 != r2) goto L_0x049b
            com.google.protobuf.B r4 = (com.google.protobuf.B) r4
            int r1 = com.google.protobuf.C0133e.b(r13, r14)
            r4.k(r1)
            int r1 = r14 + 4
        L_0x0324:
            if (r1 >= r11) goto L_0x0339
            int r2 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r3 = r5.f3609a
            if (r0 == r3) goto L_0x032f
            goto L_0x0339
        L_0x032f:
            int r1 = com.google.protobuf.C0133e.b(r13, r2)
            r4.k(r1)
            int r1 = r2 + 4
            goto L_0x0324
        L_0x0339:
            return r1
        L_0x033a:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x035c
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x0348:
            if (r11 >= r0) goto L_0x0354
            long r1 = com.google.protobuf.C0133e.c(r13, r11)
            r4.p(r1)
            int r11 = r11 + 8
            goto L_0x0348
        L_0x0354:
            if (r11 != r0) goto L_0x0357
            return r11
        L_0x0357:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x035c:
            if (r1 != r3) goto L_0x049b
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            long r1 = com.google.protobuf.C0133e.c(r13, r14)
            r4.p(r1)
            int r1 = r14 + 8
        L_0x0369:
            if (r1 >= r11) goto L_0x037e
            int r2 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r3 = r5.f3609a
            if (r0 == r3) goto L_0x0374
            goto L_0x037e
        L_0x0374:
            long r6 = com.google.protobuf.C0133e.c(r13, r2)
            r4.p(r6)
            int r1 = r2 + 8
            goto L_0x0369
        L_0x037e:
            return r1
        L_0x037f:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x03a1
            com.google.protobuf.B r4 = (com.google.protobuf.B) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x038d:
            if (r11 >= r0) goto L_0x0399
            int r11 = com.google.protobuf.C0133e.h(r13, r11, r5)
            int r1 = r5.f3609a
            r4.k(r1)
            goto L_0x038d
        L_0x0399:
            if (r11 != r0) goto L_0x039c
            return r11
        L_0x039c:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x03a1:
            if (r1 != 0) goto L_0x049b
            r20 = r11
            r18 = r13
            r19 = r14
            r17 = r0
            r21 = r4
            r22 = r5
            int r11 = com.google.protobuf.C0133e.i(r17, r18, r19, r20, r21, r22)
            return r11
        L_0x03b4:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x03d6
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x03c2:
            if (r11 >= r0) goto L_0x03ce
            int r11 = com.google.protobuf.C0133e.j(r13, r11, r5)
            long r1 = r5.b
            r4.p(r1)
            goto L_0x03c2
        L_0x03ce:
            if (r11 != r0) goto L_0x03d1
            return r11
        L_0x03d1:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x03d6:
            if (r1 != 0) goto L_0x049b
            com.google.protobuf.M r4 = (com.google.protobuf.M) r4
            int r1 = com.google.protobuf.C0133e.j(r13, r14, r5)
            long r2 = r5.b
            r4.p(r2)
        L_0x03e3:
            if (r1 >= r11) goto L_0x03f8
            int r2 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r3 = r5.f3609a
            if (r0 == r3) goto L_0x03ee
            goto L_0x03f8
        L_0x03ee:
            int r1 = com.google.protobuf.C0133e.j(r13, r2, r5)
            long r2 = r5.b
            r4.p(r2)
            goto L_0x03e3
        L_0x03f8:
            return r1
        L_0x03f9:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x041f
            com.google.protobuf.x r4 = (com.google.protobuf.C0151x) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x0407:
            if (r11 >= r0) goto L_0x0417
            int r1 = com.google.protobuf.C0133e.b(r13, r11)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r4.p(r1)
            int r11 = r11 + 4
            goto L_0x0407
        L_0x0417:
            if (r11 != r0) goto L_0x041a
            return r11
        L_0x041a:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x041f:
            if (r1 != r2) goto L_0x049b
            com.google.protobuf.x r4 = (com.google.protobuf.C0151x) r4
            int r1 = com.google.protobuf.C0133e.b(r13, r14)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r4.p(r1)
            int r1 = r14 + 4
        L_0x0430:
            if (r1 >= r11) goto L_0x0449
            int r2 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r3 = r5.f3609a
            if (r0 == r3) goto L_0x043b
            goto L_0x0449
        L_0x043b:
            int r1 = com.google.protobuf.C0133e.b(r13, r2)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r4.p(r1)
            int r1 = r2 + 4
            goto L_0x0430
        L_0x0449:
            return r1
        L_0x044a:
            r11 = r15
            r5 = r25
            if (r1 != r8) goto L_0x0470
            com.google.protobuf.p r4 = (com.google.protobuf.C0144p) r4
            int r11 = com.google.protobuf.C0133e.h(r13, r14, r5)
            int r0 = r5.f3609a
            int r0 = r0 + r11
        L_0x0458:
            if (r11 >= r0) goto L_0x0468
            long r1 = com.google.protobuf.C0133e.c(r13, r11)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            r4.p(r1)
            int r11 = r11 + 8
            goto L_0x0458
        L_0x0468:
            if (r11 != r0) goto L_0x046b
            return r11
        L_0x046b:
            com.google.protobuf.F r11 = com.google.protobuf.F.g()
            throw r11
        L_0x0470:
            if (r1 != r3) goto L_0x049b
            com.google.protobuf.p r4 = (com.google.protobuf.C0144p) r4
            long r1 = com.google.protobuf.C0133e.c(r13, r14)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            r4.p(r1)
            int r1 = r14 + 8
        L_0x0481:
            if (r1 >= r11) goto L_0x049a
            int r2 = com.google.protobuf.C0133e.h(r13, r1, r5)
            int r3 = r5.f3609a
            if (r0 == r3) goto L_0x048c
            goto L_0x049a
        L_0x048c:
            long r6 = com.google.protobuf.C0133e.c(r13, r2)
            double r6 = java.lang.Double.longBitsToDouble(r6)
            r4.p(r6)
            int r1 = r2 + 8
            goto L_0x0481
        L_0x049a:
            return r1
        L_0x049b:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.I(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, Od.b):int");
    }

    public final void J(Object obj, long j2, C0141m mVar, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int z;
        List c5 = this.k.c(obj, j2);
        CodedInputStream codedInputStream = mVar.f1619a;
        int i2 = mVar.b;
        if ((i2 & 7) == 3) {
            do {
                Object f5 = schema.f();
                mVar.b(f5, schema, extensionRegistryLite);
                schema.c(f5);
                c5.add(f5);
                if (!codedInputStream.e() && mVar.d == 0) {
                    z = codedInputStream.z();
                } else {
                    return;
                }
            } while (z == i2);
            mVar.d = z;
            return;
        }
        throw F.c();
    }

    public final void K(Object obj, int i2, C0141m mVar, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int z;
        List c5 = this.k.c(obj, (long) (i2 & 1048575));
        CodedInputStream codedInputStream = mVar.f1619a;
        int i7 = mVar.b;
        if ((i7 & 7) == 2) {
            do {
                Object f5 = schema.f();
                mVar.c(f5, schema, extensionRegistryLite);
                schema.c(f5);
                c5.add(f5);
                if (!codedInputStream.e() && mVar.d == 0) {
                    z = codedInputStream.z();
                } else {
                    return;
                }
            } while (z == i7);
            mVar.d = z;
            return;
        }
        throw F.c();
    }

    public final void L(Object obj, int i2, C0141m mVar) {
        if ((536870912 & i2) != 0) {
            mVar.v(2);
            p0.p((long) (i2 & 1048575), obj, mVar.f1619a.y());
        } else if (this.f) {
            mVar.v(2);
            p0.p((long) (i2 & 1048575), obj, mVar.f1619a.x());
        } else {
            p0.p((long) (i2 & 1048575), obj, mVar.e());
        }
    }

    public final void N(int i2, Object obj) {
        int i7 = this.f1592a[i2 + 2];
        long j2 = (long) (1048575 & i7);
        if (j2 != 1048575) {
            p0.n((1 << (i7 >>> 20)) | p0.f1625c.i(obj, j2), j2, obj);
        }
    }

    public final void O(int i2, int i7, Object obj) {
        p0.n(i2, (long) (this.f1592a[i7 + 2] & 1048575), obj);
    }

    public final int P(int i2, int i7) {
        int[] iArr = this.f1592a;
        int length = (iArr.length / 3) - 1;
        while (i7 <= length) {
            int i8 = (length + i7) >>> 1;
            int i10 = i8 * 3;
            int i11 = iArr[i10];
            if (i2 == i11) {
                return i10;
            }
            if (i2 < i11) {
                length = i8 - 1;
            } else {
                i7 = i8 + 1;
            }
        }
        return -1;
    }

    public final void Q(int i2, Object obj, Object obj2) {
        f1591o.putObject(obj, (long) (T(i2) & 1048575), obj2);
        N(i2, obj);
    }

    public final void R(Object obj, int i2, Object obj2, int i7) {
        f1591o.putObject(obj, (long) (T(i7) & 1048575), obj2);
        O(i2, i7, obj);
    }

    public final int T(int i2) {
        return this.f1592a[i2 + 1];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:112:0x03a9, code lost:
        r0 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x04e8, code lost:
        r2 = r2 + 3;
        r10 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x029f, code lost:
        r13 = r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void U(java.lang.Object r22, com.google.protobuf.P r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r6 = r23
            int[] r7 = r0.f1592a
            int r8 = r7.length
            sun.misc.Unsafe r9 = f1591o
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r10
            r2 = 0
            r4 = 0
        L_0x0011:
            if (r2 >= r8) goto L_0x04ef
            int r5 = r0.T(r2)
            r12 = r7[r2]
            int r13 = S(r5)
            r14 = 17
            r15 = 1
            if (r13 > r14) goto L_0x003f
            int r14 = r2 + 2
            r14 = r7[r14]
            r11 = r14 & r10
            if (r11 == r3) goto L_0x0035
            if (r11 != r10) goto L_0x002e
            r4 = 0
            goto L_0x0034
        L_0x002e:
            long r3 = (long) r11
            int r3 = r9.getInt(r1, r3)
            r4 = r3
        L_0x0034:
            r3 = r11
        L_0x0035:
            int r11 = r14 >>> 20
            int r11 = r15 << r11
            r20 = r11
            r11 = r5
            r5 = r20
            goto L_0x0041
        L_0x003f:
            r11 = r5
            r5 = 0
        L_0x0041:
            r11 = r11 & r10
            long r10 = (long) r11
            r17 = 63
            switch(r13) {
                case 0: goto L_0x04cd;
                case 1: goto L_0x04b0;
                case 2: goto L_0x04a0;
                case 3: goto L_0x048c;
                case 4: goto L_0x047c;
                case 5: goto L_0x046c;
                case 6: goto L_0x045c;
                case 7: goto L_0x0446;
                case 8: goto L_0x0436;
                case 9: goto L_0x0422;
                case 10: goto L_0x0411;
                case 11: goto L_0x03fe;
                case 12: goto L_0x03eb;
                case 13: goto L_0x03d8;
                case 14: goto L_0x03c5;
                case 15: goto L_0x03ad;
                case 16: goto L_0x0392;
                case 17: goto L_0x037e;
                case 18: goto L_0x0370;
                case 19: goto L_0x0362;
                case 20: goto L_0x0354;
                case 21: goto L_0x0346;
                case 22: goto L_0x0338;
                case 23: goto L_0x032a;
                case 24: goto L_0x031c;
                case 25: goto L_0x030e;
                case 26: goto L_0x0301;
                case 27: goto L_0x02f0;
                case 28: goto L_0x02e3;
                case 29: goto L_0x02d6;
                case 30: goto L_0x02c9;
                case 31: goto L_0x02bc;
                case 32: goto L_0x02af;
                case 33: goto L_0x02a2;
                case 34: goto L_0x0293;
                case 35: goto L_0x0286;
                case 36: goto L_0x0279;
                case 37: goto L_0x026c;
                case 38: goto L_0x025f;
                case 39: goto L_0x0252;
                case 40: goto L_0x0245;
                case 41: goto L_0x0238;
                case 42: goto L_0x022b;
                case 43: goto L_0x021e;
                case 44: goto L_0x0211;
                case 45: goto L_0x0204;
                case 46: goto L_0x01f7;
                case 47: goto L_0x01ea;
                case 48: goto L_0x01dd;
                case 49: goto L_0x01cc;
                case 50: goto L_0x01b6;
                case 51: goto L_0x0194;
                case 52: goto L_0x0172;
                case 53: goto L_0x0163;
                case 54: goto L_0x0150;
                case 55: goto L_0x0141;
                case 56: goto L_0x0132;
                case 57: goto L_0x0123;
                case 58: goto L_0x0108;
                case 59: goto L_0x00f9;
                case 60: goto L_0x00e6;
                case 61: goto L_0x00d5;
                case 62: goto L_0x00c2;
                case 63: goto L_0x00b0;
                case 64: goto L_0x009e;
                case 65: goto L_0x008c;
                case 66: goto L_0x0075;
                case 67: goto L_0x005d;
                case 68: goto L_0x004b;
                default: goto L_0x0048;
            }
        L_0x0048:
            r13 = 0
            goto L_0x04e8
        L_0x004b:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            java.lang.Object r5 = r9.getObject(r1, r10)
            com.google.protobuf.Schema r10 = r0.p(r2)
            r6.d(r12, r5, r10)
            goto L_0x0048
        L_0x005d:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            long r10 = E(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            long r18 = r10 << r15
            long r10 = r10 >> r17
            long r10 = r18 ^ r10
            r5.s0(r12, r10)
            goto L_0x0048
        L_0x0075:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            int r11 = r5 << 1
            int r5 = r5 >> 31
            r5 = r5 ^ r11
            r10.q0(r12, r5)
            goto L_0x0048
        L_0x008c:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            long r10 = E(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.j0(r12, r10)
            goto L_0x0048
        L_0x009e:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            r10.h0(r12, r5)
            goto L_0x0048
        L_0x00b0:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            r10.l0(r12, r5)
            goto L_0x0048
        L_0x00c2:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            r10.q0(r12, r5)
            goto L_0x0048
        L_0x00d5:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            java.lang.Object r5 = r9.getObject(r1, r10)
            com.google.protobuf.ByteString r5 = (com.google.protobuf.ByteString) r5
            r6.a(r12, r5)
            goto L_0x0048
        L_0x00e6:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            java.lang.Object r5 = r9.getObject(r1, r10)
            com.google.protobuf.Schema r10 = r0.p(r2)
            r6.g(r12, r5, r10)
            goto L_0x0048
        L_0x00f9:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            java.lang.Object r5 = r9.getObject(r1, r10)
            V(r12, r5, r6)
            goto L_0x0048
        L_0x0108:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r5 = r5.k(r1, r10)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            r10.f0(r12, r5)
            goto L_0x0048
        L_0x0123:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            r6.b(r12, r5)
            goto L_0x0048
        L_0x0132:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            long r10 = E(r1, r10)
            r6.c(r12, r10)
            goto L_0x0048
        L_0x0141:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            int r5 = D(r1, r10)
            r6.e(r12, r5)
            goto L_0x0048
        L_0x0150:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            long r10 = E(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.s0(r12, r10)
            goto L_0x0048
        L_0x0163:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            long r10 = E(r1, r10)
            r6.f(r12, r10)
            goto L_0x0048
        L_0x0172:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r5 = r5.k(r1, r10)
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            java.lang.Object r10 = r6.f1586a
            com.google.protobuf.CodedOutputStream r10 = (com.google.protobuf.CodedOutputStream) r10
            r10.getClass()
            int r5 = java.lang.Float.floatToRawIntBits(r5)
            r10.h0(r12, r5)
            goto L_0x0048
        L_0x0194:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x0048
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r5 = r5.k(r1, r10)
            java.lang.Double r5 = (java.lang.Double) r5
            double r10 = r5.doubleValue()
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.getClass()
            long r10 = java.lang.Double.doubleToRawLongBits(r10)
            r5.j0(r12, r10)
            goto L_0x0048
        L_0x01b6:
            java.lang.Object r5 = r9.getObject(r1, r10)
            if (r5 != 0) goto L_0x01be
            goto L_0x0048
        L_0x01be:
            java.lang.Object r1 = r0.o(r2)
            com.google.protobuf.S r0 = r0.m
            r0.getClass()
            A.a.t(r1)
            r0 = 0
            throw r0
        L_0x01cc:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.Schema r11 = r0.p(r2)
            com.google.protobuf.g0.u(r5, r10, r6, r11)
            goto L_0x0048
        L_0x01dd:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.B(r5, r10, r6, r15)
            goto L_0x0048
        L_0x01ea:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.A(r5, r10, r6, r15)
            goto L_0x0048
        L_0x01f7:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.z(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0204:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.y(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0211:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.q(r5, r10, r6, r15)
            goto L_0x0048
        L_0x021e:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.D(r5, r10, r6, r15)
            goto L_0x0048
        L_0x022b:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.n(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0238:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.r(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0245:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.s(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0252:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.v(r5, r10, r6, r15)
            goto L_0x0048
        L_0x025f:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.E(r5, r10, r6, r15)
            goto L_0x0048
        L_0x026c:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.w(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0279:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.t(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0286:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.p(r5, r10, r6, r15)
            goto L_0x0048
        L_0x0293:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            r12 = 0
            com.google.protobuf.g0.B(r5, r10, r6, r12)
        L_0x029f:
            r13 = r12
            goto L_0x04e8
        L_0x02a2:
            r12 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.A(r5, r10, r6, r12)
            goto L_0x029f
        L_0x02af:
            r12 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.z(r5, r10, r6, r12)
            goto L_0x029f
        L_0x02bc:
            r12 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.y(r5, r10, r6, r12)
            goto L_0x029f
        L_0x02c9:
            r12 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.q(r5, r10, r6, r12)
            goto L_0x029f
        L_0x02d6:
            r12 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.D(r5, r10, r6, r12)
            goto L_0x029f
        L_0x02e3:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.o(r5, r10, r6)
            goto L_0x0048
        L_0x02f0:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.Schema r11 = r0.p(r2)
            com.google.protobuf.g0.x(r5, r10, r6, r11)
            goto L_0x0048
        L_0x0301:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.C(r5, r10, r6)
            goto L_0x0048
        L_0x030e:
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            r13 = 0
            com.google.protobuf.g0.n(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x031c:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.r(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x032a:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.s(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x0338:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.v(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x0346:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.E(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x0354:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.w(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x0362:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.t(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x0370:
            r13 = 0
            r5 = r7[r2]
            java.lang.Object r10 = r9.getObject(r1, r10)
            java.util.List r10 = (java.util.List) r10
            com.google.protobuf.g0.p(r5, r10, r6, r13)
            goto L_0x04e8
        L_0x037e:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x04e8
            java.lang.Object r5 = r9.getObject(r1, r10)
            com.google.protobuf.Schema r10 = r0.p(r2)
            r6.d(r12, r5, r10)
            goto L_0x04e8
        L_0x0392:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            long r10 = r9.getLong(r1, r10)
            java.lang.Object r0 = r6.f1586a
            com.google.protobuf.CodedOutputStream r0 = (com.google.protobuf.CodedOutputStream) r0
            long r15 = r10 << r15
            long r10 = r10 >> r17
            long r10 = r10 ^ r15
            r0.s0(r12, r10)
        L_0x03a9:
            r0 = r21
            goto L_0x04e8
        L_0x03ad:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            int r10 = r0 << 1
            int r0 = r0 >> 31
            r0 = r0 ^ r10
            r5.q0(r12, r0)
            goto L_0x03a9
        L_0x03c5:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            long r10 = r9.getLong(r1, r10)
            java.lang.Object r0 = r6.f1586a
            com.google.protobuf.CodedOutputStream r0 = (com.google.protobuf.CodedOutputStream) r0
            r0.j0(r12, r10)
            goto L_0x03a9
        L_0x03d8:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.h0(r12, r0)
            goto L_0x03a9
        L_0x03eb:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.l0(r12, r0)
            goto L_0x03a9
        L_0x03fe:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.q0(r12, r0)
            goto L_0x03a9
        L_0x0411:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            java.lang.Object r0 = r9.getObject(r1, r10)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            r6.a(r12, r0)
            goto L_0x03a9
        L_0x0422:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x04e8
            java.lang.Object r5 = r9.getObject(r1, r10)
            com.google.protobuf.Schema r10 = r0.p(r2)
            r6.g(r12, r5, r10)
            goto L_0x04e8
        L_0x0436:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            java.lang.Object r0 = r9.getObject(r1, r10)
            V(r12, r0, r6)
            goto L_0x03a9
        L_0x0446:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            com.google.protobuf.o0 r0 = com.google.protobuf.p0.f1625c
            boolean r0 = r0.d(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.f0(r12, r0)
            goto L_0x03a9
        L_0x045c:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            r6.b(r12, r0)
            goto L_0x03a9
        L_0x046c:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            long r10 = r9.getLong(r1, r10)
            r6.c(r12, r10)
            goto L_0x03a9
        L_0x047c:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            int r0 = r9.getInt(r1, r10)
            r6.e(r12, r0)
            goto L_0x03a9
        L_0x048c:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            long r10 = r9.getLong(r1, r10)
            java.lang.Object r0 = r6.f1586a
            com.google.protobuf.CodedOutputStream r0 = (com.google.protobuf.CodedOutputStream) r0
            r0.s0(r12, r10)
            goto L_0x03a9
        L_0x04a0:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            long r10 = r9.getLong(r1, r10)
            r6.f(r12, r10)
            goto L_0x03a9
        L_0x04b0:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x03a9
            com.google.protobuf.o0 r0 = com.google.protobuf.p0.f1625c
            float r0 = r0.h(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.getClass()
            int r0 = java.lang.Float.floatToRawIntBits(r0)
            r5.h0(r12, r0)
            goto L_0x03a9
        L_0x04cd:
            r13 = 0
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x04e8
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            double r10 = r5.g(r1, r10)
            java.lang.Object r5 = r6.f1586a
            com.google.protobuf.CodedOutputStream r5 = (com.google.protobuf.CodedOutputStream) r5
            r5.getClass()
            long r10 = java.lang.Double.doubleToRawLongBits(r10)
            r5.j0(r12, r10)
        L_0x04e8:
            int r2 = r2 + 3
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0011
        L_0x04ef:
            com.google.protobuf.h0 r0 = r0.l
            r0.getClass()
            r0 = r1
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            com.google.protobuf.UnknownFieldSetLite r0 = r0.unknownFields
            r0.g(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.U(java.lang.Object, com.google.protobuf.P):void");
    }

    public final void a(Object obj, P p6) {
        p6.getClass();
        CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
        if (y0.ASCENDING == y0.DESCENDING) {
            this.l.getClass();
            ((GeneratedMessageLite) obj).unknownFields.g(p6);
            int[] iArr = this.f1592a;
            for (int length = iArr.length - 3; length >= 0; length -= 3) {
                int T = T(length);
                int i2 = iArr[length];
                switch (S(T)) {
                    case 0:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            double g3 = p0.f1625c.g(obj, (long) (T & 1048575));
                            codedOutputStream.getClass();
                            codedOutputStream.j0(i2, Double.doubleToRawLongBits(g3));
                            break;
                        }
                    case 1:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            float h5 = p0.f1625c.h(obj, (long) (T & 1048575));
                            codedOutputStream.getClass();
                            codedOutputStream.h0(i2, Float.floatToRawIntBits(h5));
                            break;
                        }
                    case 2:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.f(i2, p0.f1625c.j(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 3:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.s0(i2, p0.f1625c.j(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 4:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.e(i2, p0.f1625c.i(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 5:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.c(i2, p0.f1625c.j(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 6:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.b(i2, p0.f1625c.i(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 7:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.f0(i2, p0.f1625c.d(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 8:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            V(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p6);
                            break;
                        }
                    case 9:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.g(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p(length));
                            break;
                        }
                    case 10:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.a(i2, (ByteString) p0.f1625c.k(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 11:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.q0(i2, p0.f1625c.i(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 12:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.l0(i2, p0.f1625c.i(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 13:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.h0(i2, p0.f1625c.i(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 14:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            codedOutputStream.j0(i2, p0.f1625c.j(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 15:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            int i7 = p0.f1625c.i(obj, (long) (T & 1048575));
                            codedOutputStream.q0(i2, (i7 >> 31) ^ (i7 << 1));
                            break;
                        }
                    case 16:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            long j2 = p0.f1625c.j(obj, (long) (T & 1048575));
                            codedOutputStream.s0(i2, (j2 >> 63) ^ (j2 << 1));
                            break;
                        }
                    case 17:
                        if (!r(length, obj)) {
                            break;
                        } else {
                            p6.d(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p(length));
                            break;
                        }
                    case 18:
                        g0.p(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 19:
                        g0.t(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 20:
                        g0.w(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 21:
                        g0.E(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 22:
                        g0.v(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 23:
                        g0.s(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 24:
                        g0.r(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 25:
                        g0.n(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 26:
                        g0.C(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6);
                        break;
                    case 27:
                        g0.x(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, p(length));
                        break;
                    case 28:
                        g0.o(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6);
                        break;
                    case 29:
                        g0.D(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 30:
                        g0.q(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 31:
                        g0.y(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 32:
                        g0.z(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 33:
                        g0.A(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 34:
                        g0.B(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, false);
                        break;
                    case 35:
                        g0.p(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 36:
                        g0.t(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 37:
                        g0.w(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 38:
                        g0.E(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 39:
                        g0.v(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 40:
                        g0.s(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 41:
                        g0.r(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 42:
                        g0.n(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 43:
                        g0.D(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 44:
                        g0.q(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 45:
                        g0.y(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 46:
                        g0.z(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 47:
                        g0.A(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 48:
                        g0.B(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, true);
                        break;
                    case 49:
                        g0.u(iArr[length], (List) p0.f1625c.k(obj, (long) (T & 1048575)), p6, p(length));
                        break;
                    case 50:
                        if (p0.f1625c.k(obj, (long) (T & 1048575)) == null) {
                            break;
                        } else {
                            Object o2 = o(length);
                            this.m.getClass();
                            a.t(o2);
                            throw null;
                        }
                    case 51:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            double doubleValue = ((Double) p0.f1625c.k(obj, (long) (T & 1048575))).doubleValue();
                            codedOutputStream.getClass();
                            codedOutputStream.j0(i2, Double.doubleToRawLongBits(doubleValue));
                            break;
                        }
                    case 52:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            float floatValue = ((Float) p0.f1625c.k(obj, (long) (T & 1048575))).floatValue();
                            codedOutputStream.getClass();
                            codedOutputStream.h0(i2, Float.floatToRawIntBits(floatValue));
                            break;
                        }
                    case 53:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.f(i2, E(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 54:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.s0(i2, E(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 55:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.e(i2, D(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 56:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.c(i2, E(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 57:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.b(i2, D(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 58:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.f0(i2, ((Boolean) p0.f1625c.k(obj, (long) (T & 1048575))).booleanValue());
                            break;
                        }
                    case 59:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            V(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p6);
                            break;
                        }
                    case 60:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.g(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p(length));
                            break;
                        }
                    case 61:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.a(i2, (ByteString) p0.f1625c.k(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 62:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.q0(i2, D(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 63:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.l0(i2, D(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 64:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.h0(i2, D(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 65:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            codedOutputStream.j0(i2, E(obj, (long) (T & 1048575)));
                            break;
                        }
                    case 66:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            int D = D(obj, (long) (T & 1048575));
                            codedOutputStream.q0(i2, (D >> 31) ^ (D << 1));
                            break;
                        }
                    case 67:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            long E = E(obj, (long) (T & 1048575));
                            codedOutputStream.s0(i2, (E >> 63) ^ (E << 1));
                            break;
                        }
                    case 68:
                        if (!u(i2, length, obj)) {
                            break;
                        } else {
                            p6.d(i2, p0.f1625c.k(obj, (long) (T & 1048575)), p(length));
                            break;
                        }
                }
            }
            return;
        }
        U(obj, p6);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(java.lang.Object r11, java.lang.Object r12) {
        /*
            r10 = this;
            l(r11)
            r12.getClass()
            r0 = 0
        L_0x0007:
            int[] r1 = r10.f1592a
            int r2 = r1.length
            if (r0 >= r2) goto L_0x01b9
            int r2 = r10.T(r0)
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r2
            long r6 = (long) r3
            r1 = r1[r0]
            int r2 = S(r2)
            switch(r2) {
                case 0: goto L_0x01a1;
                case 1: goto L_0x018d;
                case 2: goto L_0x0179;
                case 3: goto L_0x0165;
                case 4: goto L_0x0151;
                case 5: goto L_0x013d;
                case 6: goto L_0x0129;
                case 7: goto L_0x0115;
                case 8: goto L_0x0101;
                case 9: goto L_0x00fc;
                case 10: goto L_0x00e8;
                case 11: goto L_0x00d4;
                case 12: goto L_0x00c0;
                case 13: goto L_0x00ac;
                case 14: goto L_0x0098;
                case 15: goto L_0x0085;
                case 16: goto L_0x0072;
                case 17: goto L_0x006e;
                case 18: goto L_0x0068;
                case 19: goto L_0x0068;
                case 20: goto L_0x0068;
                case 21: goto L_0x0068;
                case 22: goto L_0x0068;
                case 23: goto L_0x0068;
                case 24: goto L_0x0068;
                case 25: goto L_0x0068;
                case 26: goto L_0x0068;
                case 27: goto L_0x0068;
                case 28: goto L_0x0068;
                case 29: goto L_0x0068;
                case 30: goto L_0x0068;
                case 31: goto L_0x0068;
                case 32: goto L_0x0068;
                case 33: goto L_0x0068;
                case 34: goto L_0x0068;
                case 35: goto L_0x0068;
                case 36: goto L_0x0068;
                case 37: goto L_0x0068;
                case 38: goto L_0x0068;
                case 39: goto L_0x0068;
                case 40: goto L_0x0068;
                case 41: goto L_0x0068;
                case 42: goto L_0x0068;
                case 43: goto L_0x0068;
                case 44: goto L_0x0068;
                case 45: goto L_0x0068;
                case 46: goto L_0x0068;
                case 47: goto L_0x0068;
                case 48: goto L_0x0068;
                case 49: goto L_0x0068;
                case 50: goto L_0x004f;
                case 51: goto L_0x003c;
                case 52: goto L_0x003c;
                case 53: goto L_0x003c;
                case 54: goto L_0x003c;
                case 55: goto L_0x003c;
                case 56: goto L_0x003c;
                case 57: goto L_0x003c;
                case 58: goto L_0x003c;
                case 59: goto L_0x003c;
                case 60: goto L_0x0038;
                case 61: goto L_0x0025;
                case 62: goto L_0x0025;
                case 63: goto L_0x0025;
                case 64: goto L_0x0025;
                case 65: goto L_0x0025;
                case 66: goto L_0x0025;
                case 67: goto L_0x0025;
                case 68: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0022
        L_0x001f:
            r10.x(r0, r11, r12)
        L_0x0022:
            r5 = r11
            goto L_0x01b4
        L_0x0025:
            boolean r2 = r10.u(r1, r0, r12)
            if (r2 == 0) goto L_0x0022
            com.google.protobuf.o0 r2 = com.google.protobuf.p0.f1625c
            java.lang.Object r2 = r2.k(r12, r6)
            com.google.protobuf.p0.p(r6, r11, r2)
            r10.O(r1, r0, r11)
            goto L_0x0022
        L_0x0038:
            r10.x(r0, r11, r12)
            goto L_0x0022
        L_0x003c:
            boolean r2 = r10.u(r1, r0, r12)
            if (r2 == 0) goto L_0x0022
            com.google.protobuf.o0 r2 = com.google.protobuf.p0.f1625c
            java.lang.Object r2 = r2.k(r12, r6)
            com.google.protobuf.p0.p(r6, r11, r2)
            r10.O(r1, r0, r11)
            goto L_0x0022
        L_0x004f:
            java.lang.Class r1 = com.google.protobuf.g0.f1606a
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            java.lang.Object r2 = r1.k(r11, r6)
            java.lang.Object r1 = r1.k(r12, r6)
            com.google.protobuf.S r3 = r10.m
            r3.getClass()
            com.google.protobuf.Q r1 = com.google.protobuf.S.a(r2, r1)
            com.google.protobuf.p0.p(r6, r11, r1)
            goto L_0x0022
        L_0x0068:
            com.google.protobuf.L r1 = r10.k
            r1.b(r6, r11, r12)
            goto L_0x0022
        L_0x006e:
            r10.w(r0, r11, r12)
            goto L_0x0022
        L_0x0072:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            long r1 = r1.j(r12, r6)
            com.google.protobuf.p0.o(r11, r6, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0085:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0098:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            long r1 = r1.j(r12, r6)
            com.google.protobuf.p0.o(r11, r6, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x00ac:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x00c0:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x00d4:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x00e8:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            java.lang.Object r1 = r1.k(r12, r6)
            com.google.protobuf.p0.p(r6, r11, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x00fc:
            r10.w(r0, r11, r12)
            goto L_0x0022
        L_0x0101:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            java.lang.Object r1 = r1.k(r12, r6)
            com.google.protobuf.p0.p(r6, r11, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0115:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            boolean r2 = r1.d(r12, r6)
            r1.m(r11, r6, r2)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0129:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x013d:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            long r1 = r1.j(r12, r6)
            com.google.protobuf.p0.o(r11, r6, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0151:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            int r1 = r1.i(r12, r6)
            com.google.protobuf.p0.n(r1, r6, r11)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0165:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            long r1 = r1.j(r12, r6)
            com.google.protobuf.p0.o(r11, r6, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x0179:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            long r1 = r1.j(r12, r6)
            com.google.protobuf.p0.o(r11, r6, r1)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x018d:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r1 = com.google.protobuf.p0.f1625c
            float r2 = r1.h(r12, r6)
            r1.p(r11, r6, r2)
            r10.N(r0, r11)
            goto L_0x0022
        L_0x01a1:
            boolean r1 = r10.r(r0, r12)
            if (r1 == 0) goto L_0x0022
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            double r8 = r4.g(r12, r6)
            r5 = r11
            r4.o(r5, r6, r8)
            r10.N(r0, r5)
        L_0x01b4:
            int r0 = r0 + 3
            r11 = r5
            goto L_0x0007
        L_0x01b9:
            r5 = r11
            com.google.protobuf.h0 r10 = r10.l
            com.google.protobuf.g0.k(r10, r5, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.b(java.lang.Object, java.lang.Object):void");
    }

    public final void c(Object obj) {
        if (t(obj)) {
            if (obj instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int[] iArr = this.f1592a;
            int length = iArr.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int T = T(i2);
                long j2 = (long) (1048575 & T);
                int S = S(T);
                if (S != 9) {
                    if (S == 60 || S == 68) {
                        if (u(iArr[i2], i2, obj)) {
                            p(i2).c(f1591o.getObject(obj, j2));
                        }
                    } else {
                        switch (S) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.k.a(obj, j2);
                                continue;
                            case 50:
                                Unsafe unsafe = f1591o;
                                Object object = unsafe.getObject(obj, j2);
                                if (object != null) {
                                    this.m.getClass();
                                    ((Q) object).d = false;
                                    unsafe.putObject(obj, j2, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (r(i2, obj)) {
                    p(i2).c(f1591o.getObject(obj, j2));
                }
            }
            this.l.getClass();
            UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) obj).unknownFields;
            if (unknownFieldSetLite.e) {
                unknownFieldSetLite.e = false;
            }
        }
    }

    public final boolean d(Object obj) {
        int i2;
        int i7;
        int i8;
        Object obj2 = obj;
        int i10 = 1048575;
        int i11 = 0;
        int i12 = 0;
        while (i12 < this.f1594h) {
            int i13 = this.g[i12];
            int[] iArr = this.f1592a;
            int i14 = iArr[i13];
            int T = T(i13);
            int i15 = iArr[i13 + 2];
            int i16 = i15 & 1048575;
            int i17 = 1 << (i15 >>> 20);
            if (i16 != i10) {
                if (i16 != 1048575) {
                    i11 = f1591o.getInt(obj2, (long) i16);
                }
                i8 = i13;
                i2 = i11;
                i7 = i16;
            } else {
                int i18 = i11;
                i7 = i10;
                i8 = i13;
                i2 = i18;
            }
            if ((268435456 & T) == 0 || s(obj2, i8, i7, i2, i17)) {
                int S = S(T);
                if (S != 9 && S != 17) {
                    if (S != 27) {
                        if (S == 60 || S == 68) {
                            if (u(i14, i8, obj2)) {
                                if (!p(i8).d(p0.f1625c.k(obj2, (long) (T & 1048575)))) {
                                }
                            } else {
                                continue;
                            }
                        } else if (S != 49) {
                            if (S != 50) {
                                continue;
                            } else {
                                Object k10 = p0.f1625c.k(obj2, (long) (T & 1048575));
                                this.m.getClass();
                                if (!((Q) k10).isEmpty()) {
                                    a.t(o(i8));
                                    throw null;
                                }
                            }
                        }
                    }
                    List list = (List) p0.f1625c.k(obj2, (long) (T & 1048575));
                    if (list.isEmpty()) {
                        continue;
                    } else {
                        Schema p6 = p(i8);
                        int i19 = 0;
                        while (i19 < list.size()) {
                            if (p6.d(list.get(i19))) {
                                i19++;
                            }
                        }
                        continue;
                    }
                } else if (s(obj2, i8, i7, i2, i17)) {
                    if (!p(i8).d(p0.f1625c.k(obj2, (long) (T & 1048575)))) {
                    }
                } else {
                    continue;
                }
                i12++;
                i10 = i7;
                i11 = i2;
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x039d, code lost:
        r11 = (r11 * r10) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x039f, code lost:
        r9 = r9 + r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r5 = r5 + r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0599, code lost:
        r9 = r9 + (r5 + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x059b, code lost:
        r0 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x05b6, code lost:
        r0 = r0 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x05b7, code lost:
        r9 = r9 + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
        r9 = r9 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05c3, code lost:
        r0 = r0 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x05d0, code lost:
        r0 = r0 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0089, code lost:
        r9 = r9 + (r10 + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a4, code lost:
        r5 = r5 + r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b0, code lost:
        r5 = r5 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bd, code lost:
        r5 = r5 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int e(com.google.protobuf.AbstractMessageLite r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            sun.misc.Unsafe r6 = f1591o
            r7 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r7
            r4 = r2
            r9 = r4
            r3 = r8
        L_0x000e:
            int[] r5 = r0.f1592a
            int r10 = r5.length
            if (r2 >= r10) goto L_0x06e0
            int r10 = r0.T(r2)
            int r11 = S(r10)
            r12 = r5[r2]
            int r13 = r2 + 2
            r5 = r5[r13]
            r13 = r5 & r8
            r14 = 17
            r15 = 1
            if (r11 > r14) goto L_0x003a
            if (r13 == r3) goto L_0x0035
            if (r13 != r8) goto L_0x002e
            r4 = r7
            goto L_0x0034
        L_0x002e:
            long r3 = (long) r13
            int r3 = r6.getInt(r1, r3)
            r4 = r3
        L_0x0034:
            r3 = r13
        L_0x0035:
            int r5 = r5 >>> 20
            int r5 = r15 << r5
            goto L_0x003b
        L_0x003a:
            r5 = r7
        L_0x003b:
            r10 = r10 & r8
            long r13 = (long) r10
            com.google.protobuf.w r10 = com.google.protobuf.C0150w.DOUBLE_LIST_PACKED
            int r10 = r10.a()
            if (r11 < r10) goto L_0x004b
            com.google.protobuf.w r10 = com.google.protobuf.C0150w.SINT64_LIST_PACKED
            int r10 = r10.a()
        L_0x004b:
            r10 = 63
            switch(r11) {
                case 0: goto L_0x06cd;
                case 1: goto L_0x06c1;
                case 2: goto L_0x06ad;
                case 3: goto L_0x0699;
                case 4: goto L_0x0685;
                case 5: goto L_0x0679;
                case 6: goto L_0x066d;
                case 7: goto L_0x0660;
                case 8: goto L_0x0634;
                case 9: goto L_0x0612;
                case 10: goto L_0x05f9;
                case 11: goto L_0x05e6;
                case 12: goto L_0x05d3;
                case 13: goto L_0x05c6;
                case 14: goto L_0x05b9;
                case 15: goto L_0x059f;
                case 16: goto L_0x0582;
                case 17: goto L_0x0564;
                case 18: goto L_0x0558;
                case 19: goto L_0x054c;
                case 20: goto L_0x052c;
                case 21: goto L_0x0512;
                case 22: goto L_0x04f8;
                case 23: goto L_0x04ec;
                case 24: goto L_0x04e0;
                case 25: goto L_0x04c7;
                case 26: goto L_0x0464;
                case 27: goto L_0x0430;
                case 28: goto L_0x0402;
                case 29: goto L_0x03ea;
                case 30: goto L_0x03d2;
                case 31: goto L_0x03c6;
                case 32: goto L_0x03ba;
                case 33: goto L_0x03a2;
                case 34: goto L_0x0385;
                case 35: goto L_0x036b;
                case 36: goto L_0x0351;
                case 37: goto L_0x033b;
                case 38: goto L_0x0325;
                case 39: goto L_0x030f;
                case 40: goto L_0x02f5;
                case 41: goto L_0x02db;
                case 42: goto L_0x02c3;
                case 43: goto L_0x02ad;
                case 44: goto L_0x0297;
                case 45: goto L_0x027d;
                case 46: goto L_0x0263;
                case 47: goto L_0x024d;
                case 48: goto L_0x0237;
                case 49: goto L_0x0204;
                case 50: goto L_0x01c7;
                case 51: goto L_0x01bb;
                case 52: goto L_0x01af;
                case 53: goto L_0x019b;
                case 54: goto L_0x0187;
                case 55: goto L_0x0173;
                case 56: goto L_0x0167;
                case 57: goto L_0x015b;
                case 58: goto L_0x014e;
                case 59: goto L_0x0122;
                case 60: goto L_0x0100;
                case 61: goto L_0x00e6;
                case 62: goto L_0x00d3;
                case 63: goto L_0x00c0;
                case 64: goto L_0x00b3;
                case 65: goto L_0x00a6;
                case 66: goto L_0x008d;
                case 67: goto L_0x0072;
                case 68: goto L_0x0052;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x06da
        L_0x0052:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
            com.google.protobuf.Schema r10 = r0.p(r2)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r11 * 2
            com.google.protobuf.AbstractMessageLite r5 = (com.google.protobuf.AbstractMessageLite) r5
            int r5 = r5.getSerializedSize(r10)
        L_0x006e:
            int r5 = r5 + r11
        L_0x006f:
            int r9 = r9 + r5
            goto L_0x06da
        L_0x0072:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            long r13 = E(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            long r11 = r13 << r15
            long r13 = r13 >> r10
            long r10 = r11 ^ r13
            int r10 = com.google.protobuf.CodedOutputStream.c0(r10)
        L_0x0089:
            int r10 = r10 + r5
            int r9 = r9 + r10
            goto L_0x06da
        L_0x008d:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = D(r1, r13)
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r5 << 1
            int r5 = r5 >> 31
            r5 = r5 ^ r11
            int r5 = com.google.protobuf.CodedOutputStream.b0(r5)
        L_0x00a4:
            int r5 = r5 + r10
            goto L_0x006f
        L_0x00a6:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
        L_0x00b0:
            int r5 = r5 + 8
            goto L_0x006f
        L_0x00b3:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
        L_0x00bd:
            int r5 = r5 + 4
            goto L_0x006f
        L_0x00c0:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = D(r1, r13)
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.Y(r5)
            goto L_0x00a4
        L_0x00d3:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = D(r1, r13)
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.b0(r5)
            goto L_0x00a4
        L_0x00e6:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.protobuf.ByteString r5 = (com.google.protobuf.ByteString) r5
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = r5.size()
            int r9 = c0.C0086a.B(r5, r5, r10, r9)
            goto L_0x06da
        L_0x0100:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.protobuf.Schema r10 = r0.p(r2)
            java.lang.Class r11 = com.google.protobuf.g0.f1606a
            com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            com.google.protobuf.AbstractMessageLite r5 = (com.google.protobuf.AbstractMessageLite) r5
            int r5 = r5.getSerializedSize(r10)
            int r9 = c0.C0086a.B(r5, r5, r11, r9)
            goto L_0x06da
        L_0x0122:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            boolean r10 = r5 instanceof com.google.protobuf.ByteString
            if (r10 == 0) goto L_0x0141
            com.google.protobuf.ByteString r5 = (com.google.protobuf.ByteString) r5
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = r5.size()
            int r5 = c0.C0086a.B(r5, r5, r10, r9)
        L_0x013e:
            r9 = r5
            goto L_0x06da
        L_0x0141:
            java.lang.String r5 = (java.lang.String) r5
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.Z(r5)
            int r5 = r5 + r10
            int r5 = r5 + r9
            goto L_0x013e
        L_0x014e:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = r5 + r15
            goto L_0x006f
        L_0x015b:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x00bd
        L_0x0167:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x00b0
        L_0x0173:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = D(r1, r13)
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.Y(r5)
            goto L_0x00a4
        L_0x0187:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            long r10 = E(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r10 = com.google.protobuf.CodedOutputStream.c0(r10)
            goto L_0x0089
        L_0x019b:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            long r10 = E(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r10 = com.google.protobuf.CodedOutputStream.c0(r10)
            goto L_0x0089
        L_0x01af:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x00bd
        L_0x01bb:
            boolean r5 = r0.u(r12, r2, r1)
            if (r5 == 0) goto L_0x06da
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x00b0
        L_0x01c7:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.lang.Object r10 = r0.o(r2)
            com.google.protobuf.S r11 = r0.m
            r11.getClass()
            com.google.protobuf.Q r5 = (com.google.protobuf.Q) r5
            if (r10 != 0) goto L_0x01fe
            boolean r10 = r5.isEmpty()
            if (r10 == 0) goto L_0x01e0
            goto L_0x06da
        L_0x01e0:
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
            boolean r10 = r5.hasNext()
            if (r10 != 0) goto L_0x01f0
            goto L_0x06da
        L_0x01f0:
            java.lang.Object r0 = r5.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r0.getKey()
            r0.getValue()
            r0 = 0
            throw r0
        L_0x01fe:
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            r0.<init>()
            throw r0
        L_0x0204:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            com.google.protobuf.Schema r10 = r0.p(r2)
            java.lang.Class r11 = com.google.protobuf.g0.f1606a
            int r11 = r5.size()
            if (r11 != 0) goto L_0x0218
            r14 = r7
            goto L_0x0234
        L_0x0218:
            r13 = r7
            r14 = r13
        L_0x021a:
            if (r13 >= r11) goto L_0x0234
            java.lang.Object r15 = r5.get(r13)
            com.google.protobuf.MessageLite r15 = (com.google.protobuf.MessageLite) r15
            int r16 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r16 = r16 * 2
            com.google.protobuf.AbstractMessageLite r15 = (com.google.protobuf.AbstractMessageLite) r15
            int r15 = r15.getSerializedSize(r10)
            int r15 = r15 + r16
            int r14 = r14 + r15
            int r13 = r13 + 1
            goto L_0x021a
        L_0x0234:
            int r9 = r9 + r14
            goto L_0x06da
        L_0x0237:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.g(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x024d:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.f(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x0263:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 8
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x027d:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 4
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x0297:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.a(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x02ad:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.h(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x02c3:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x02db:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 4
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x02f5:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 8
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x030f:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.d(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x0325:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.i(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x033b:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.e(r5)
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x0351:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 4
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x036b:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            int r5 = r5 * 8
            if (r5 <= 0) goto L_0x06da
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r9 = c0.C0086a.B(r5, r10, r5, r9)
            goto L_0x06da
        L_0x0385:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x0395
        L_0x0393:
            r11 = r7
            goto L_0x039f
        L_0x0395:
            int r5 = com.google.protobuf.g0.g(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
        L_0x039d:
            int r11 = r11 * r10
            int r11 = r11 + r5
        L_0x039f:
            int r9 = r9 + r11
            goto L_0x06da
        L_0x03a2:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x03b1
            goto L_0x0393
        L_0x03b1:
            int r5 = com.google.protobuf.g0.f(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x039d
        L_0x03ba:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.c(r12, r5)
            goto L_0x006f
        L_0x03c6:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.b(r12, r5)
            goto L_0x006f
        L_0x03d2:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x03e1
            goto L_0x0393
        L_0x03e1:
            int r5 = com.google.protobuf.g0.a(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x039d
        L_0x03ea:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x03f9
            goto L_0x0393
        L_0x03f9:
            int r5 = com.google.protobuf.g0.h(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x039d
        L_0x0402:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x0411
            goto L_0x0393
        L_0x0411:
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r11 * r10
            r10 = r7
        L_0x0417:
            int r12 = r5.size()
            if (r10 >= r12) goto L_0x039f
            java.lang.Object r12 = r5.get(r10)
            com.google.protobuf.ByteString r12 = (com.google.protobuf.ByteString) r12
            int r12 = r12.size()
            int r13 = com.google.protobuf.CodedOutputStream.b0(r12)
            int r13 = r13 + r12
            int r11 = r11 + r13
            int r10 = r10 + 1
            goto L_0x0417
        L_0x0430:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            com.google.protobuf.Schema r10 = r0.p(r2)
            java.lang.Class r11 = com.google.protobuf.g0.f1606a
            int r11 = r5.size()
            if (r11 != 0) goto L_0x0444
            r12 = r7
            goto L_0x0461
        L_0x0444:
            int r12 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r12 = r12 * r11
            r13 = r7
        L_0x044a:
            if (r13 >= r11) goto L_0x0461
            java.lang.Object r14 = r5.get(r13)
            com.google.protobuf.MessageLite r14 = (com.google.protobuf.MessageLite) r14
            com.google.protobuf.AbstractMessageLite r14 = (com.google.protobuf.AbstractMessageLite) r14
            int r14 = r14.getSerializedSize(r10)
            int r15 = com.google.protobuf.CodedOutputStream.b0(r14)
            int r15 = r15 + r14
            int r12 = r12 + r15
            int r13 = r13 + 1
            goto L_0x044a
        L_0x0461:
            int r9 = r9 + r12
            goto L_0x06da
        L_0x0464:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x0474
            goto L_0x0393
        L_0x0474:
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r11 * r10
            boolean r12 = r5 instanceof com.google.protobuf.I
            if (r12 == 0) goto L_0x04a3
            com.google.protobuf.I r5 = (com.google.protobuf.I) r5
            r12 = r7
        L_0x0480:
            if (r12 >= r10) goto L_0x039f
            java.lang.Object r13 = r5.n(r12)
            boolean r14 = r13 instanceof com.google.protobuf.ByteString
            if (r14 == 0) goto L_0x0498
            com.google.protobuf.ByteString r13 = (com.google.protobuf.ByteString) r13
            int r13 = r13.size()
            int r14 = com.google.protobuf.CodedOutputStream.b0(r13)
            int r14 = r14 + r13
            int r14 = r14 + r11
            r11 = r14
            goto L_0x04a0
        L_0x0498:
            java.lang.String r13 = (java.lang.String) r13
            int r13 = com.google.protobuf.CodedOutputStream.Z(r13)
            int r13 = r13 + r11
            r11 = r13
        L_0x04a0:
            int r12 = r12 + 1
            goto L_0x0480
        L_0x04a3:
            r12 = r7
        L_0x04a4:
            if (r12 >= r10) goto L_0x039f
            java.lang.Object r13 = r5.get(r12)
            boolean r14 = r13 instanceof com.google.protobuf.ByteString
            if (r14 == 0) goto L_0x04bc
            com.google.protobuf.ByteString r13 = (com.google.protobuf.ByteString) r13
            int r13 = r13.size()
            int r14 = com.google.protobuf.CodedOutputStream.b0(r13)
            int r14 = r14 + r13
            int r14 = r14 + r11
            r11 = r14
            goto L_0x04c4
        L_0x04bc:
            java.lang.String r13 = (java.lang.String) r13
            int r13 = com.google.protobuf.CodedOutputStream.Z(r13)
            int r13 = r13 + r11
            r11 = r13
        L_0x04c4:
            int r12 = r12 + 1
            goto L_0x04a4
        L_0x04c7:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r5 = r5.size()
            if (r5 != 0) goto L_0x04d7
            r10 = r7
            goto L_0x04dd
        L_0x04d7:
            int r10 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r10 = r10 + r15
            int r10 = r10 * r5
        L_0x04dd:
            int r9 = r9 + r10
            goto L_0x06da
        L_0x04e0:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.b(r12, r5)
            goto L_0x006f
        L_0x04ec:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.c(r12, r5)
            goto L_0x006f
        L_0x04f8:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x0508
            goto L_0x0393
        L_0x0508:
            int r5 = com.google.protobuf.g0.d(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x039d
        L_0x0512:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x0522
            goto L_0x0393
        L_0x0522:
            int r5 = com.google.protobuf.g0.i(r5)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x039d
        L_0x052c:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r10 = com.google.protobuf.g0.f1606a
            int r10 = r5.size()
            if (r10 != 0) goto L_0x053c
            goto L_0x0393
        L_0x053c:
            int r10 = com.google.protobuf.g0.e(r5)
            int r5 = r5.size()
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r11 * r5
            int r11 = r11 + r10
            goto L_0x039f
        L_0x054c:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.b(r12, r5)
            goto L_0x006f
        L_0x0558:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.protobuf.g0.c(r12, r5)
            goto L_0x006f
        L_0x0564:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
            com.google.protobuf.Schema r10 = r0.p(r2)
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r11 = r11 * 2
            com.google.protobuf.AbstractMessageLite r5 = (com.google.protobuf.AbstractMessageLite) r5
            int r5 = r5.getSerializedSize(r10)
            goto L_0x006e
        L_0x0582:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            long r13 = r6.getLong(r1, r13)
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            long r11 = r13 << r15
            long r13 = r13 >> r10
            long r10 = r11 ^ r13
            int r5 = com.google.protobuf.CodedOutputStream.c0(r10)
        L_0x0599:
            int r5 = r5 + r0
            int r9 = r9 + r5
        L_0x059b:
            r0 = r17
            goto L_0x06da
        L_0x059f:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = r6.getInt(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r10 = r0 << 1
            int r0 = r0 >> 31
            r0 = r0 ^ r10
            int r0 = com.google.protobuf.CodedOutputStream.b0(r0)
        L_0x05b6:
            int r0 = r0 + r5
        L_0x05b7:
            int r9 = r9 + r0
            goto L_0x059b
        L_0x05b9:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
        L_0x05c3:
            int r0 = r0 + 8
            goto L_0x05b7
        L_0x05c6:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
        L_0x05d0:
            int r0 = r0 + 4
            goto L_0x05b7
        L_0x05d3:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = r6.getInt(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = com.google.protobuf.CodedOutputStream.Y(r0)
            goto L_0x05b6
        L_0x05e6:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = r6.getInt(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = com.google.protobuf.CodedOutputStream.b0(r0)
            goto L_0x05b6
        L_0x05f9:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            java.lang.Object r0 = r6.getObject(r1, r13)
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = r0.size()
            int r9 = c0.C0086a.B(r0, r0, r5, r9)
            goto L_0x059b
        L_0x0612:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x06da
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.protobuf.Schema r10 = r0.p(r2)
            java.lang.Class r11 = com.google.protobuf.g0.f1606a
            com.google.protobuf.MessageLite r5 = (com.google.protobuf.MessageLite) r5
            int r11 = com.google.protobuf.CodedOutputStream.a0(r12)
            com.google.protobuf.AbstractMessageLite r5 = (com.google.protobuf.AbstractMessageLite) r5
            int r5 = r5.getSerializedSize(r10)
            int r9 = c0.C0086a.B(r5, r5, r11, r9)
            goto L_0x06da
        L_0x0634:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            java.lang.Object r0 = r6.getObject(r1, r13)
            boolean r5 = r0 instanceof com.google.protobuf.ByteString
            if (r5 == 0) goto L_0x0653
            com.google.protobuf.ByteString r0 = (com.google.protobuf.ByteString) r0
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = r0.size()
            int r0 = c0.C0086a.B(r0, r0, r5, r9)
        L_0x0650:
            r9 = r0
            goto L_0x059b
        L_0x0653:
            java.lang.String r0 = (java.lang.String) r0
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = com.google.protobuf.CodedOutputStream.Z(r0)
            int r0 = r0 + r5
            int r0 = r0 + r9
            goto L_0x0650
        L_0x0660:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = r0 + r15
            goto L_0x05b7
        L_0x066d:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x05d0
        L_0x0679:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x05c3
        L_0x0685:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = r6.getInt(r1, r13)
            int r5 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r0 = com.google.protobuf.CodedOutputStream.Y(r0)
            goto L_0x05b6
        L_0x0699:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            long r10 = r6.getLong(r1, r13)
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.c0(r10)
            goto L_0x0599
        L_0x06ad:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            long r10 = r6.getLong(r1, r13)
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r5 = com.google.protobuf.CodedOutputStream.c0(r10)
            goto L_0x0599
        L_0x06c1:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x059b
            int r0 = com.google.protobuf.CodedOutputStream.a0(r12)
            goto L_0x05d0
        L_0x06cd:
            boolean r5 = r0.s(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x06da
            int r1 = com.google.protobuf.CodedOutputStream.a0(r12)
            int r1 = r1 + 8
            int r9 = r9 + r1
        L_0x06da:
            int r2 = r2 + 3
            r1 = r18
            goto L_0x000e
        L_0x06e0:
            com.google.protobuf.h0 r0 = r0.l
            r0.getClass()
            r0 = r18
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            com.google.protobuf.UnknownFieldSetLite r0 = r0.unknownFields
            int r0 = r0.c()
            int r0 = r0 + r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.e(com.google.protobuf.AbstractMessageLite):int");
    }

    public final Object f() {
        this.f1596j.getClass();
        return ((GeneratedMessageLite) this.e).newMutableInstance();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v56, resolved type: com.google.protobuf.ExtensionRegistryLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v57, resolved type: com.google.protobuf.ExtensionRegistryLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v66, resolved type: com.google.protobuf.ExtensionRegistryLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: com.google.protobuf.ExtensionRegistryLite} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v70, resolved type: com.google.protobuf.h0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v71, resolved type: com.google.protobuf.h0} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:138|137|183|184|(0)|187|(0)|193|207|200) */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02e6, code lost:
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02e7, code lost:
        r8 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0401, code lost:
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0427, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x068b, code lost:
        r13 = com.google.protobuf.h0.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0696, code lost:
        if (r11 < r10) goto L_0x0698;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0698, code lost:
        r6.m(r9[r11], r1, r13);
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x06a0, code lost:
        if (r13 != null) goto L_0x06a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x06a2, code lost:
        r13 = (com.google.protobuf.UnknownFieldSetLite) r13;
        r0 = (com.google.protobuf.GeneratedMessageLite) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x06b6, code lost:
        r6.m(r9[r11], r1, r13);
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x06c0, code lost:
        r8.getClass();
        ((com.google.protobuf.GeneratedMessageLite) r1).unknownFields = (com.google.protobuf.UnknownFieldSetLite) r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b7, code lost:
        r6 = r1;
        r1 = r2;
        r14 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0266, code lost:
        r6 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0282, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:183:0x0686 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x068b A[Catch:{ all -> 0x0427 }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x06b6 A[LOOP:5: B:195:0x06b4->B:196:0x06b6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x06c0  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0696 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0282 A[Catch:{ E -> 0x0284, all -> 0x0282 }, ExcHandler: all (th java.lang.Throwable), PHI: r1 r12 
      PHI: (r1v65 java.lang.Object) = (r1v37 java.lang.Object), (r1v42 java.lang.Object), (r1v66 java.lang.Object) binds: [B:131:0x03eb, B:127:0x03d0, B:86:0x027a] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v27 com.google.protobuf.X) = (r12v2 com.google.protobuf.X), (r12v4 com.google.protobuf.X), (r12v28 com.google.protobuf.X) binds: [B:131:0x03eb, B:127:0x03d0, B:86:0x027a] A[DONT_GENERATE, DONT_INLINE], Splitter:B:86:0x027a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g(java.lang.Object r20, com.google.protobuf.C0141m r21, com.google.protobuf.ExtensionRegistryLite r22) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r4 = r21
            r6 = r22
            r6.getClass()
            l(r2)
            com.google.protobuf.h0 r8 = r1.l
            int[] r9 = r1.g
            int r10 = r1.f1595i
            int r11 = r1.f1594h
            r13 = 0
        L_0x0017:
            int r0 = r4.a()     // Catch:{ all -> 0x06b1 }
            int r3 = r1.f1593c     // Catch:{ all -> 0x06b1 }
            r5 = 0
            if (r0 < r3) goto L_0x002e
            int r3 = r1.d     // Catch:{ all -> 0x06b1 }
            if (r0 > r3) goto L_0x002e
            int r3 = r1.P(r0, r5)     // Catch:{ all -> 0x06b1 }
        L_0x0028:
            r7 = r3
            goto L_0x0030
        L_0x002a:
            r6 = r1
            r1 = r2
            goto L_0x06b4
        L_0x002e:
            r3 = -1
            goto L_0x0028
        L_0x0030:
            if (r7 >= 0) goto L_0x006c
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r0 != r3) goto L_0x004f
        L_0x0037:
            if (r11 >= r10) goto L_0x0041
            r0 = r9[r11]
            r1.m(r0, r2, r13)
            int r11 = r11 + 1
            goto L_0x0037
        L_0x0041:
            if (r13 == 0) goto L_0x06a9
            r8.getClass()
        L_0x0046:
            com.google.protobuf.UnknownFieldSetLite r13 = (com.google.protobuf.UnknownFieldSetLite) r13
            r0 = r2
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
        L_0x004b:
            r0.unknownFields = r13
            goto L_0x06a9
        L_0x004f:
            r8.getClass()     // Catch:{ all -> 0x06b1 }
            if (r13 != 0) goto L_0x0058
            com.google.protobuf.UnknownFieldSetLite r13 = com.google.protobuf.h0.a(r2)     // Catch:{ all -> 0x06b1 }
        L_0x0058:
            boolean r0 = com.google.protobuf.h0.b(r13, r4)     // Catch:{ all -> 0x06b1 }
            if (r0 == 0) goto L_0x005f
            goto L_0x0017
        L_0x005f:
            if (r11 >= r10) goto L_0x0069
            r0 = r9[r11]
            r1.m(r0, r2, r13)
            int r11 = r11 + 1
            goto L_0x005f
        L_0x0069:
            if (r13 == 0) goto L_0x06a9
        L_0x006b:
            goto L_0x0046
        L_0x006c:
            int r3 = r1.T(r7)     // Catch:{ all -> 0x06b1 }
            int r14 = S(r3)     // Catch:{ E -> 0x0681 }
            r16 = 0
            r12 = 3
            r18 = 1048575(0xfffff, float:1.469367E-39)
            com.google.protobuf.L r15 = r1.k
            switch(r14) {
                case 0: goto L_0x0666;
                case 1: goto L_0x064b;
                case 2: goto L_0x0632;
                case 3: goto L_0x0619;
                case 4: goto L_0x0600;
                case 5: goto L_0x05e6;
                case 6: goto L_0x05cc;
                case 7: goto L_0x05b1;
                case 8: goto L_0x05a5;
                case 9: goto L_0x058b;
                case 10: goto L_0x0577;
                case 11: goto L_0x055e;
                case 12: goto L_0x0530;
                case 13: goto L_0x0516;
                case 14: goto L_0x04fc;
                case 15: goto L_0x04e3;
                case 16: goto L_0x04ca;
                case 17: goto L_0x04b1;
                case 18: goto L_0x04a0;
                case 19: goto L_0x048f;
                case 20: goto L_0x047e;
                case 21: goto L_0x046d;
                case 22: goto L_0x045c;
                case 23: goto L_0x044b;
                case 24: goto L_0x043a;
                case 25: goto L_0x042a;
                case 26: goto L_0x0405;
                case 27: goto L_0x03e8;
                case 28: goto L_0x03d9;
                case 29: goto L_0x03ca;
                case 30: goto L_0x03af;
                case 31: goto L_0x039e;
                case 32: goto L_0x038d;
                case 33: goto L_0x037c;
                case 34: goto L_0x036b;
                case 35: goto L_0x035a;
                case 36: goto L_0x034a;
                case 37: goto L_0x033a;
                case 38: goto L_0x032a;
                case 39: goto L_0x031a;
                case 40: goto L_0x030a;
                case 41: goto L_0x02fa;
                case 42: goto L_0x02ea;
                case 43: goto L_0x02d7;
                case 44: goto L_0x02b1;
                case 45: goto L_0x02a3;
                case 46: goto L_0x0295;
                case 47: goto L_0x0287;
                case 48: goto L_0x0274;
                case 49: goto L_0x0255;
                case 50: goto L_0x024d;
                case 51: goto L_0x0234;
                case 52: goto L_0x021b;
                case 53: goto L_0x0203;
                case 54: goto L_0x01eb;
                case 55: goto L_0x01d3;
                case 56: goto L_0x01ba;
                case 57: goto L_0x01a1;
                case 58: goto L_0x0189;
                case 59: goto L_0x0181;
                case 60: goto L_0x016b;
                case 61: goto L_0x015c;
                case 62: goto L_0x0144;
                case 63: goto L_0x011a;
                case 64: goto L_0x0102;
                case 65: goto L_0x00ea;
                case 66: goto L_0x00d3;
                case 67: goto L_0x00bc;
                case 68: goto L_0x00a4;
                default: goto L_0x007f;
            }
        L_0x007f:
            if (r13 != 0) goto L_0x008e
            r8.getClass()     // Catch:{ E -> 0x0089 }
            com.google.protobuf.UnknownFieldSetLite r13 = com.google.protobuf.h0.a(r2)     // Catch:{ E -> 0x0089 }
            goto L_0x008e
        L_0x0089:
            r6 = r1
            r1 = r2
            r14 = r4
            goto L_0x0686
        L_0x008e:
            r8.getClass()     // Catch:{ E -> 0x0089 }
            boolean r0 = com.google.protobuf.h0.b(r13, r4)     // Catch:{ E -> 0x0089 }
            if (r0 != 0) goto L_0x00b7
        L_0x0097:
            if (r11 >= r10) goto L_0x00a1
            r0 = r9[r11]
            r1.m(r0, r2, r13)
            int r11 = r11 + 1
            goto L_0x0097
        L_0x00a1:
            if (r13 == 0) goto L_0x06a9
            goto L_0x006b
        L_0x00a4:
            java.lang.Object r3 = r1.z(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3     // Catch:{ E -> 0x0089 }
            com.google.protobuf.Schema r5 = r1.p(r7)     // Catch:{ E -> 0x0089 }
            r4.v(r12)     // Catch:{ E -> 0x0089 }
            r4.b(r3, r5, r6)     // Catch:{ E -> 0x0089 }
            r1.R(r2, r0, r3, r7)     // Catch:{ E -> 0x0089 }
        L_0x00b7:
            r6 = r1
            r1 = r2
            r14 = r4
            goto L_0x06aa
        L_0x00bc:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            long r17 = r3.w()     // Catch:{ E -> 0x0089 }
            java.lang.Long r3 = java.lang.Long.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x00d3:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r3 = r3.v()     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x00ea:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 1
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            long r17 = r3.u()     // Catch:{ E -> 0x0089 }
            java.lang.Long r3 = java.lang.Long.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0102:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 5
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r3 = r3.t()     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x011a:
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r5 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r5 = r5.m()     // Catch:{ E -> 0x0089 }
            com.google.protobuf.Internal$EnumVerifier r12 = r1.n(r7)     // Catch:{ E -> 0x0089 }
            if (r12 == 0) goto L_0x0135
            boolean r12 = r12.isInRange(r5)     // Catch:{ E -> 0x0089 }
            if (r12 == 0) goto L_0x0130
            goto L_0x0135
        L_0x0130:
            java.lang.Object r13 = com.google.protobuf.g0.m(r2, r0, r5, r13, r8)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0135:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0144:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r3 = r3.A()     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x015c:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            com.google.protobuf.ByteString r3 = r4.e()     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x016b:
            java.lang.Object r3 = r1.z(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.MessageLite r3 = (com.google.protobuf.MessageLite) r3     // Catch:{ E -> 0x0089 }
            com.google.protobuf.Schema r5 = r1.p(r7)     // Catch:{ E -> 0x0089 }
            r12 = 2
            r4.v(r12)     // Catch:{ E -> 0x0089 }
            r4.c(r3, r5, r6)     // Catch:{ E -> 0x0089 }
            r1.R(r2, r0, r3, r7)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0181:
            r1.L(r2, r3, r4)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0189:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            boolean r3 = r3.j()     // Catch:{ E -> 0x0089 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x01a1:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 5
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r3 = r3.n()     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x01ba:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 1
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            long r17 = r3.o()     // Catch:{ E -> 0x0089 }
            java.lang.Long r3 = java.lang.Long.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x01d3:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            int r3 = r3.q()     // Catch:{ E -> 0x0089 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x01eb:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            long r17 = r3.B()     // Catch:{ E -> 0x0089 }
            java.lang.Long r3 = java.lang.Long.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0203:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r4.v(r5)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            long r17 = r3.r()     // Catch:{ E -> 0x0089 }
            java.lang.Long r3 = java.lang.Long.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x021b:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 5
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            float r3 = r3.p()     // Catch:{ E -> 0x0089 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x0234:
            r3 = r3 & r18
            long r14 = (long) r3     // Catch:{ E -> 0x0089 }
            r3 = 1
            r4.v(r3)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.CodedInputStream r3 = r4.f1619a     // Catch:{ E -> 0x0089 }
            double r17 = r3.l()     // Catch:{ E -> 0x0089 }
            java.lang.Double r3 = java.lang.Double.valueOf(r17)     // Catch:{ E -> 0x0089 }
            com.google.protobuf.p0.p(r14, r2, r3)     // Catch:{ E -> 0x0089 }
            r1.O(r0, r7, r2)     // Catch:{ E -> 0x0089 }
            goto L_0x00b7
        L_0x024d:
            java.lang.Object r0 = r1.o(r7)     // Catch:{ E -> 0x0089 }
            r1.v(r7, r2, r0)     // Catch:{ E -> 0x0089 }
            throw r16     // Catch:{ E -> 0x0089 }
        L_0x0255:
            r0 = r3 & r18
            long r14 = (long) r0
            com.google.protobuf.Schema r6 = r1.p(r7)     // Catch:{ all -> 0x0269 }
            r7 = r22
            r5 = r4
            r3 = r14
            r1.J(r2, r3, r5, r6, r7)     // Catch:{ E -> 0x026f }
            r12 = r1
            r1 = r2
            r14 = r5
        L_0x0266:
            r6 = r12
            goto L_0x06aa
        L_0x0269:
            r0 = move-exception
            r12 = r1
            r1 = r2
        L_0x026c:
            r6 = r12
            goto L_0x06b4
        L_0x026f:
            r6 = r1
            r1 = r2
            r14 = r5
            goto L_0x0686
        L_0x0274:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.q(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x0282:
            r0 = move-exception
            goto L_0x026c
        L_0x0284:
            r6 = r12
            goto L_0x0686
        L_0x0287:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x0284, all -> 0x0282 }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.p(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x0295:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x0284, all -> 0x0282 }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.o(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x02a3:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x0284, all -> 0x0282 }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.n(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x02b1:
            r12 = r1
            r1 = r2
            r14 = r4
            r2 = r3 & r18
            long r2 = (long) r2
            java.util.List r3 = r15.c(r1, r2)     // Catch:{ E -> 0x02d5, all -> 0x02d1 }
            r14.h(r3)     // Catch:{ E -> 0x02d5, all -> 0x02d1 }
            com.google.protobuf.Internal$EnumVerifier r4 = r12.n(r7)     // Catch:{ E -> 0x02d5, all -> 0x02d1 }
            r2 = r0
            r6 = r8
            r5 = r13
            java.lang.Object r13 = com.google.protobuf.g0.j(r1, r2, r3, r4, r5, r6)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e7
        L_0x02ca:
            r0 = move-exception
            r13 = r5
            r8 = r6
            goto L_0x026c
        L_0x02ce:
            r13 = r5
            r8 = r6
            goto L_0x0284
        L_0x02d1:
            r0 = move-exception
            r6 = r8
            r5 = r13
            goto L_0x026c
        L_0x02d5:
            r5 = r13
            goto L_0x0284
        L_0x02d7:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.s(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
        L_0x02e6:
            r13 = r5
        L_0x02e7:
            r8 = r6
            goto L_0x0266
        L_0x02ea:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.d(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x02fa:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.i(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x030a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.j(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x031a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.l(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x032a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.t(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x033a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.m(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x034a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.k(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x035a:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.g(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x036b:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.q(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x037c:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.p(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x038d:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.o(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x039e:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.n(r0)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            goto L_0x02e6
        L_0x03af:
            r12 = r1
            r1 = r2
            r14 = r4
            r6 = r8
            r5 = r13
            r2 = r0
            r0 = r3 & r18
            long r3 = (long) r0     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.util.List r3 = r15.c(r1, r3)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r14.h(r3)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            com.google.protobuf.Internal$EnumVerifier r4 = r12.n(r7)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            java.lang.Object r13 = com.google.protobuf.g0.j(r1, r2, r3, r4, r5, r6)     // Catch:{ E -> 0x02ce, all -> 0x02ca }
            r8 = r6
            goto L_0x0266
        L_0x03ca:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.s(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x03d9:
            r12 = r1
            r1 = r2
            r14 = r4
            r0 = r3 & r18
            long r2 = (long) r0     // Catch:{ E -> 0x0284, all -> 0x0282 }
            java.util.List r0 = r15.c(r1, r2)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            r14.f(r0)     // Catch:{ E -> 0x0284, all -> 0x0282 }
            goto L_0x0266
        L_0x03e8:
            r12 = r1
            r1 = r2
            r14 = r4
            com.google.protobuf.Schema r5 = r12.p(r7)     // Catch:{ E -> 0x0401, all -> 0x0282 }
            r6 = r22
            r1 = r12
            r1.K(r2, r3, r4, r5, r6)     // Catch:{ E -> 0x03fb }
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            goto L_0x06aa
        L_0x03fb:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            goto L_0x0686
        L_0x0401:
            r0 = r22
            goto L_0x0284
        L_0x0405:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            r2 = 536870912(0x20000000, float:1.0842022E-19)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x041b
            r2 = r3 & r18
            long r2 = (long) r2
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r3 = 1
            r14.r(r2, r3)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x041b:
            r2 = r3 & r18
            long r2 = (long) r2     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.r(r2, r5)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0427:
            r0 = move-exception
            goto L_0x06b4
        L_0x042a:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            r2 = r3 & r18
            long r2 = (long) r2     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.d(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x043a:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.i(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x044b:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.j(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x045c:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.l(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x046d:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.t(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x047e:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.m(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x048f:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.k(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x04a0:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            java.util.List r2 = r15.c(r1, r2)     // Catch:{ E -> 0x0686 }
            r14.g(r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x04b1:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            java.lang.Object r2 = r6.y(r7, r1)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.MessageLite r2 = (com.google.protobuf.MessageLite) r2     // Catch:{ E -> 0x0686 }
            com.google.protobuf.Schema r3 = r6.p(r7)     // Catch:{ E -> 0x0686 }
            r14.v(r12)     // Catch:{ E -> 0x0686 }
            r14.b(r2, r3, r0)     // Catch:{ E -> 0x0686 }
            r6.Q(r7, r1, r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x04ca:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            long r4 = r4.w()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x04e3:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.v()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x04fc:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 1
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            long r4 = r4.u()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0516:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 5
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.t()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0530:
            r14 = r2
            r2 = r0
            r0 = r6
            r6 = r1
            r1 = r14
            r14 = r4
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.m()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.Internal$EnumVerifier r5 = r6.n(r7)     // Catch:{ E -> 0x0686 }
            if (r5 == 0) goto L_0x0552
            boolean r5 = r5.isInRange(r4)     // Catch:{ E -> 0x0686 }
            if (r5 == 0) goto L_0x054c
            goto L_0x0552
        L_0x054c:
            java.lang.Object r13 = com.google.protobuf.g0.m(r1, r2, r4, r13, r8)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0552:
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x055e:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.A()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0577:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.ByteString r4 = r14.e()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.p(r2, r1, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x058b:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            java.lang.Object r2 = r6.y(r7, r1)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.MessageLite r2 = (com.google.protobuf.MessageLite) r2     // Catch:{ E -> 0x0686 }
            com.google.protobuf.Schema r3 = r6.p(r7)     // Catch:{ E -> 0x0686 }
            r12 = 2
            r14.v(r12)     // Catch:{ E -> 0x0686 }
            r14.c(r2, r3, r0)     // Catch:{ E -> 0x0686 }
            r6.Q(r7, r1, r2)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x05a5:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            r6.L(r1, r3, r14)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x05b1:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            boolean r4 = r4.j()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c     // Catch:{ E -> 0x0686 }
            r5.m(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x05cc:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 5
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.n()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x05e6:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 1
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            long r4 = r4.o()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0600:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            int r4 = r4.q()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.n(r4, r2, r1)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0619:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            long r4 = r4.B()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0632:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r14.v(r5)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            long r4 = r4.r()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.p0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x064b:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 5
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            float r4 = r4.p()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c     // Catch:{ E -> 0x0686 }
            r5.p(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0666:
            r14 = r4
            r0 = r6
            r6 = r1
            r1 = r2
            long r2 = C(r3)     // Catch:{ E -> 0x0686 }
            r4 = 1
            r14.v(r4)     // Catch:{ E -> 0x0686 }
            com.google.protobuf.CodedInputStream r4 = r14.f1619a     // Catch:{ E -> 0x0686 }
            double r4 = r4.l()     // Catch:{ E -> 0x0686 }
            com.google.protobuf.o0 r0 = com.google.protobuf.p0.f1625c     // Catch:{ E -> 0x0686 }
            r0.o(r1, r2, r4)     // Catch:{ E -> 0x0686 }
            r6.N(r7, r1)     // Catch:{ E -> 0x0686 }
            goto L_0x06aa
        L_0x0681:
            r6 = r1
            r1 = r2
            r14 = r4
            r16 = 0
        L_0x0686:
            r8.getClass()     // Catch:{ all -> 0x0427 }
            if (r13 != 0) goto L_0x0690
            com.google.protobuf.UnknownFieldSetLite r0 = com.google.protobuf.h0.a(r1)     // Catch:{ all -> 0x0427 }
            r13 = r0
        L_0x0690:
            boolean r0 = com.google.protobuf.h0.b(r13, r14)     // Catch:{ all -> 0x0427 }
            if (r0 != 0) goto L_0x06aa
        L_0x0696:
            if (r11 >= r10) goto L_0x06a0
            r0 = r9[r11]
            r6.m(r0, r1, r13)
            int r11 = r11 + 1
            goto L_0x0696
        L_0x06a0:
            if (r13 == 0) goto L_0x06a9
            com.google.protobuf.UnknownFieldSetLite r13 = (com.google.protobuf.UnknownFieldSetLite) r13
            r0 = r1
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            goto L_0x004b
        L_0x06a9:
            return
        L_0x06aa:
            r2 = r1
            r1 = r6
            r4 = r14
            r6 = r22
            goto L_0x0017
        L_0x06b1:
            r0 = move-exception
            goto L_0x002a
        L_0x06b4:
            if (r11 >= r10) goto L_0x06be
            r2 = r9[r11]
            r6.m(r2, r1, r13)
            int r11 = r11 + 1
            goto L_0x06b4
        L_0x06be:
            if (r13 == 0) goto L_0x06c9
            r8.getClass()
            com.google.protobuf.UnknownFieldSetLite r13 = (com.google.protobuf.UnknownFieldSetLite) r13
            com.google.protobuf.GeneratedMessageLite r1 = (com.google.protobuf.GeneratedMessageLite) r1
            r1.unknownFields = r13
        L_0x06c9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.g(java.lang.Object, com.google.protobuf.m, com.google.protobuf.ExtensionRegistryLite):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00df, code lost:
        if (r4 != false) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e1, code lost:
        r8 = 1231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e2, code lost:
        r3 = r8 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0198, code lost:
        r3 = (r3 * 53) + r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        r3 = r4 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0216, code lost:
        if (r4 != false) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0278, code lost:
        r2 = r2 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int h(com.google.protobuf.GeneratedMessageLite r12) {
        /*
            r11 = this;
            int[] r0 = r11.f1592a
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x0005:
            if (r2 >= r1) goto L_0x027c
            int r4 = r11.T(r2)
            r5 = r0[r2]
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r6 & r4
            long r6 = (long) r6
            int r4 = S(r4)
            r8 = 1237(0x4d5, float:1.733E-42)
            r9 = 1231(0x4cf, float:1.725E-42)
            r10 = 37
            switch(r4) {
                case 0: goto L_0x0266;
                case 1: goto L_0x0258;
                case 2: goto L_0x024a;
                case 3: goto L_0x023c;
                case 4: goto L_0x0232;
                case 5: goto L_0x0224;
                case 6: goto L_0x021a;
                case 7: goto L_0x020c;
                case 8: goto L_0x01fc;
                case 9: goto L_0x01ef;
                case 10: goto L_0x01e1;
                case 11: goto L_0x01d7;
                case 12: goto L_0x01cd;
                case 13: goto L_0x01c3;
                case 14: goto L_0x01b5;
                case 15: goto L_0x01ab;
                case 16: goto L_0x019d;
                case 17: goto L_0x018c;
                case 18: goto L_0x017e;
                case 19: goto L_0x017e;
                case 20: goto L_0x017e;
                case 21: goto L_0x017e;
                case 22: goto L_0x017e;
                case 23: goto L_0x017e;
                case 24: goto L_0x017e;
                case 25: goto L_0x017e;
                case 26: goto L_0x017e;
                case 27: goto L_0x017e;
                case 28: goto L_0x017e;
                case 29: goto L_0x017e;
                case 30: goto L_0x017e;
                case 31: goto L_0x017e;
                case 32: goto L_0x017e;
                case 33: goto L_0x017e;
                case 34: goto L_0x017e;
                case 35: goto L_0x017e;
                case 36: goto L_0x017e;
                case 37: goto L_0x017e;
                case 38: goto L_0x017e;
                case 39: goto L_0x017e;
                case 40: goto L_0x017e;
                case 41: goto L_0x017e;
                case 42: goto L_0x017e;
                case 43: goto L_0x017e;
                case 44: goto L_0x017e;
                case 45: goto L_0x017e;
                case 46: goto L_0x017e;
                case 47: goto L_0x017e;
                case 48: goto L_0x017e;
                case 49: goto L_0x017e;
                case 50: goto L_0x0170;
                case 51: goto L_0x0152;
                case 52: goto L_0x0138;
                case 53: goto L_0x0126;
                case 54: goto L_0x0114;
                case 55: goto L_0x0106;
                case 56: goto L_0x00f4;
                case 57: goto L_0x00e6;
                case 58: goto L_0x00c9;
                case 59: goto L_0x00b3;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008d;
                case 62: goto L_0x0080;
                case 63: goto L_0x0073;
                case 64: goto L_0x0066;
                case 65: goto L_0x0055;
                case 66: goto L_0x0048;
                case 67: goto L_0x0037;
                case 68: goto L_0x0021;
                default: goto L_0x001f;
            }
        L_0x001f:
            goto L_0x0278
        L_0x0021:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r3 = r3 * 53
            int r4 = r4.hashCode()
        L_0x0033:
            int r4 = r4 + r3
            r3 = r4
            goto L_0x0278
        L_0x0037:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            long r4 = E(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0048:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x0055:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            long r4 = E(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0066:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x0073:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x0080:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x008d:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x00a0:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r3 = r3 * 53
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x00b3:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            java.lang.String r4 = (java.lang.String) r4
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x00c9:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            java.nio.charset.Charset r5 = com.google.protobuf.D.f1578a
            if (r4 == 0) goto L_0x00e2
        L_0x00e1:
            r8 = r9
        L_0x00e2:
            int r8 = r8 + r3
            r3 = r8
            goto L_0x0278
        L_0x00e6:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x00f4:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            long r4 = E(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0106:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            int r4 = D(r12, r6)
            goto L_0x0033
        L_0x0114:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            long r4 = E(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0126:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            long r4 = E(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0138:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            java.lang.Float r4 = (java.lang.Float) r4
            float r4 = r4.floatValue()
            int r4 = java.lang.Float.floatToIntBits(r4)
            goto L_0x0033
        L_0x0152:
            boolean r4 = r11.u(r5, r2, r12)
            if (r4 == 0) goto L_0x0278
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            java.lang.Double r4 = (java.lang.Double) r4
            double r4 = r4.doubleValue()
            long r4 = java.lang.Double.doubleToLongBits(r4)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0170:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x017e:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x018c:
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            if (r4 == 0) goto L_0x0198
            int r10 = r4.hashCode()
        L_0x0198:
            int r3 = r3 * 53
            int r3 = r3 + r10
            goto L_0x0278
        L_0x019d:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            long r4 = r4.j(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x01ab:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x01b5:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            long r4 = r4.j(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x01c3:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x01cd:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x01d7:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x01e1:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x01ef:
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            if (r4 == 0) goto L_0x0198
            int r10 = r4.hashCode()
            goto L_0x0198
        L_0x01fc:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r4 = r4.k(r12, r6)
            java.lang.String r4 = (java.lang.String) r4
            int r4 = r4.hashCode()
            goto L_0x0033
        L_0x020c:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            boolean r4 = r4.d(r12, r6)
            java.nio.charset.Charset r5 = com.google.protobuf.D.f1578a
            if (r4 == 0) goto L_0x00e2
            goto L_0x00e1
        L_0x021a:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x0224:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            long r4 = r4.j(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0232:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            int r4 = r4.i(r12, r6)
            goto L_0x0033
        L_0x023c:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            long r4 = r4.j(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x024a:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            long r4 = r4.j(r12, r6)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0258:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            float r4 = r4.h(r12, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            goto L_0x0033
        L_0x0266:
            int r3 = r3 * 53
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            double r4 = r4.g(r12, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            int r4 = com.google.protobuf.D.b(r4)
            goto L_0x0033
        L_0x0278:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x027c:
            int r3 = r3 * 53
            com.google.protobuf.h0 r11 = r11.l
            r11.getClass()
            com.google.protobuf.UnknownFieldSetLite r11 = r12.unknownFields
            int r11 = r11.hashCode()
            int r11 = r11 + r3
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.h(com.google.protobuf.GeneratedMessageLite):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0074, code lost:
        if (com.google.protobuf.g0.l(r5.k(r12, r7), r5.k(r13, r7)) != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008a, code lost:
        if (r5.j(r12, r7) == r5.j(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009e, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b4, code lost:
        if (r5.j(r12, r7) == r5.j(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c8, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00dc, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f0, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0108, code lost:
        if (com.google.protobuf.g0.l(r5.k(r12, r7), r5.k(r13, r7)) != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0120, code lost:
        if (com.google.protobuf.g0.l(r5.k(r12, r7), r5.k(r13, r7)) != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0138, code lost:
        if (com.google.protobuf.g0.l(r5.k(r12, r7), r5.k(r13, r7)) != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x014c, code lost:
        if (r5.d(r12, r7) == r5.d(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0160, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0176, code lost:
        if (r5.j(r12, r7) == r5.j(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018a, code lost:
        if (r5.i(r12, r7) == r5.i(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x019f, code lost:
        if (r5.j(r12, r7) == r5.j(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01b4, code lost:
        if (r5.j(r12, r7) == r5.j(r13, r7)) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01cf, code lost:
        if (java.lang.Float.floatToIntBits(r5.h(r12, r7)) == java.lang.Float.floatToIntBits(r5.h(r13, r7))) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01ec, code lost:
        if (java.lang.Double.doubleToLongBits(r5.g(r12, r7)) == java.lang.Double.doubleToLongBits(r5.g(r13, r7))) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        if (com.google.protobuf.g0.l(r9.k(r12, r7), r9.k(r13, r7)) != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003d, code lost:
        r4 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean i(com.google.protobuf.GeneratedMessageLite r12, com.google.protobuf.GeneratedMessageLite r13) {
        /*
            r11 = this;
            int[] r0 = r11.f1592a
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x0005:
            r4 = 1
            if (r3 >= r1) goto L_0x01f5
            int r5 = r11.T(r3)
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r5 & r6
            long r7 = (long) r7
            int r5 = S(r5)
            switch(r5) {
                case 0: goto L_0x01d2;
                case 1: goto L_0x01b7;
                case 2: goto L_0x01a2;
                case 3: goto L_0x018d;
                case 4: goto L_0x017a;
                case 5: goto L_0x0164;
                case 6: goto L_0x0150;
                case 7: goto L_0x013c;
                case 8: goto L_0x0124;
                case 9: goto L_0x010c;
                case 10: goto L_0x00f4;
                case 11: goto L_0x00e0;
                case 12: goto L_0x00cc;
                case 13: goto L_0x00b8;
                case 14: goto L_0x00a2;
                case 15: goto L_0x008e;
                case 16: goto L_0x0078;
                case 17: goto L_0x0060;
                case 18: goto L_0x0050;
                case 19: goto L_0x0050;
                case 20: goto L_0x0050;
                case 21: goto L_0x0050;
                case 22: goto L_0x0050;
                case 23: goto L_0x0050;
                case 24: goto L_0x0050;
                case 25: goto L_0x0050;
                case 26: goto L_0x0050;
                case 27: goto L_0x0050;
                case 28: goto L_0x0050;
                case 29: goto L_0x0050;
                case 30: goto L_0x0050;
                case 31: goto L_0x0050;
                case 32: goto L_0x0050;
                case 33: goto L_0x0050;
                case 34: goto L_0x0050;
                case 35: goto L_0x0050;
                case 36: goto L_0x0050;
                case 37: goto L_0x0050;
                case 38: goto L_0x0050;
                case 39: goto L_0x0050;
                case 40: goto L_0x0050;
                case 41: goto L_0x0050;
                case 42: goto L_0x0050;
                case 43: goto L_0x0050;
                case 44: goto L_0x0050;
                case 45: goto L_0x0050;
                case 46: goto L_0x0050;
                case 47: goto L_0x0050;
                case 48: goto L_0x0050;
                case 49: goto L_0x0050;
                case 50: goto L_0x0040;
                case 51: goto L_0x001b;
                case 52: goto L_0x001b;
                case 53: goto L_0x001b;
                case 54: goto L_0x001b;
                case 55: goto L_0x001b;
                case 56: goto L_0x001b;
                case 57: goto L_0x001b;
                case 58: goto L_0x001b;
                case 59: goto L_0x001b;
                case 60: goto L_0x001b;
                case 61: goto L_0x001b;
                case 62: goto L_0x001b;
                case 63: goto L_0x001b;
                case 64: goto L_0x001b;
                case 65: goto L_0x001b;
                case 66: goto L_0x001b;
                case 67: goto L_0x001b;
                case 68: goto L_0x001b;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x01ee
        L_0x001b:
            int r5 = r3 + 2
            r5 = r0[r5]
            r5 = r5 & r6
            long r5 = (long) r5
            com.google.protobuf.o0 r9 = com.google.protobuf.p0.f1625c
            int r10 = r9.i(r12, r5)
            int r5 = r9.i(r13, r5)
            if (r10 != r5) goto L_0x003d
            java.lang.Object r5 = r9.k(r12, r7)
            java.lang.Object r6 = r9.k(r13, r7)
            boolean r5 = com.google.protobuf.g0.l(r5, r6)
            if (r5 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x003d:
            r4 = r2
            goto L_0x01ee
        L_0x0040:
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r5 = r4.k(r12, r7)
            java.lang.Object r4 = r4.k(r13, r7)
            boolean r4 = com.google.protobuf.g0.l(r5, r4)
            goto L_0x01ee
        L_0x0050:
            com.google.protobuf.o0 r4 = com.google.protobuf.p0.f1625c
            java.lang.Object r5 = r4.k(r12, r7)
            java.lang.Object r4 = r4.k(r13, r7)
            boolean r4 = com.google.protobuf.g0.l(r5, r4)
            goto L_0x01ee
        L_0x0060:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r5.k(r12, r7)
            java.lang.Object r5 = r5.k(r13, r7)
            boolean r5 = com.google.protobuf.g0.l(r6, r5)
            if (r5 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x0078:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            long r9 = r5.j(r12, r7)
            long r5 = r5.j(r13, r7)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
            goto L_0x01ee
        L_0x008e:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x00a2:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            long r9 = r5.j(r12, r7)
            long r5 = r5.j(r13, r7)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
            goto L_0x01ee
        L_0x00b8:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x00cc:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x00e0:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x00f4:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r5.k(r12, r7)
            java.lang.Object r5 = r5.k(r13, r7)
            boolean r5 = com.google.protobuf.g0.l(r6, r5)
            if (r5 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x010c:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r5.k(r12, r7)
            java.lang.Object r5 = r5.k(r13, r7)
            boolean r5 = com.google.protobuf.g0.l(r6, r5)
            if (r5 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x0124:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r5.k(r12, r7)
            java.lang.Object r5 = r5.k(r13, r7)
            boolean r5 = com.google.protobuf.g0.l(r6, r5)
            if (r5 == 0) goto L_0x003d
            goto L_0x01ee
        L_0x013c:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            boolean r6 = r5.d(r12, r7)
            boolean r5 = r5.d(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x0150:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x0164:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            long r9 = r5.j(r12, r7)
            long r5 = r5.j(r13, r7)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
            goto L_0x01ee
        L_0x017a:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            int r6 = r5.i(r12, r7)
            int r5 = r5.i(r13, r7)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x018d:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            long r9 = r5.j(r12, r7)
            long r5 = r5.j(r13, r7)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
            goto L_0x01ee
        L_0x01a2:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            long r9 = r5.j(r12, r7)
            long r5 = r5.j(r13, r7)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
            goto L_0x01ee
        L_0x01b7:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            float r6 = r5.h(r12, r7)
            int r6 = java.lang.Float.floatToIntBits(r6)
            float r5 = r5.h(r13, r7)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r6 != r5) goto L_0x003d
            goto L_0x01ee
        L_0x01d2:
            boolean r5 = r11.k(r12, r13, r3)
            if (r5 == 0) goto L_0x003d
            com.google.protobuf.o0 r5 = com.google.protobuf.p0.f1625c
            double r9 = r5.g(r12, r7)
            long r9 = java.lang.Double.doubleToLongBits(r9)
            double r5 = r5.g(r13, r7)
            long r5 = java.lang.Double.doubleToLongBits(r5)
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x003d
        L_0x01ee:
            if (r4 != 0) goto L_0x01f1
            goto L_0x0204
        L_0x01f1:
            int r3 = r3 + 3
            goto L_0x0005
        L_0x01f5:
            com.google.protobuf.h0 r11 = r11.l
            r11.getClass()
            com.google.protobuf.UnknownFieldSetLite r11 = r12.unknownFields
            com.google.protobuf.UnknownFieldSetLite r12 = r13.unknownFields
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x0205
        L_0x0204:
            return r2
        L_0x0205:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.i(com.google.protobuf.GeneratedMessageLite, com.google.protobuf.GeneratedMessageLite):boolean");
    }

    public final void j(Object obj, byte[] bArr, int i2, int i7, b bVar) {
        G(obj, bArr, i2, i7, 0, bVar);
    }

    public final boolean k(GeneratedMessageLite generatedMessageLite, GeneratedMessageLite generatedMessageLite2, int i2) {
        if (r(i2, generatedMessageLite) == r(i2, generatedMessageLite2)) {
            return true;
        }
        return false;
    }

    public final void m(int i2, Object obj, Object obj2) {
        int i7 = this.f1592a[i2];
        Object k10 = p0.f1625c.k(obj, (long) (T(i2) & 1048575));
        if (k10 != null && n(i2) != null) {
            this.m.getClass();
            Q q = (Q) k10;
            a.t(o(i2));
            throw null;
        }
    }

    public final Internal$EnumVerifier n(int i2) {
        return (Internal$EnumVerifier) this.b[((i2 / 3) * 2) + 1];
    }

    public final Object o(int i2) {
        return this.b[(i2 / 3) * 2];
    }

    public final Schema p(int i2) {
        int i7 = (i2 / 3) * 2;
        Object[] objArr = this.b;
        Schema schema = (Schema) objArr[i7];
        if (schema != null) {
            return schema;
        }
        Schema a7 = d0.f1601c.a((Class) objArr[i7 + 1]);
        objArr[i7] = a7;
        return a7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0113 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean r(int r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r7 + 2
            int[] r1 = r6.f1592a
            r0 = r1[r0]
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r0 & r1
            long r2 = (long) r2
            r4 = 1048575(0xfffff, double:5.18065E-318)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r5 = 1
            if (r4 != 0) goto L_0x0106
            int r6 = r6.T(r7)
            r7 = r6 & r1
            long r0 = (long) r7
            int r6 = S(r6)
            r2 = 0
            switch(r6) {
                case 0: goto L_0x00f7;
                case 1: goto L_0x00ea;
                case 2: goto L_0x00df;
                case 3: goto L_0x00d4;
                case 4: goto L_0x00cb;
                case 5: goto L_0x00c0;
                case 6: goto L_0x00b7;
                case 7: goto L_0x00b0;
                case 8: goto L_0x008c;
                case 9: goto L_0x0082;
                case 10: goto L_0x0074;
                case 11: goto L_0x006a;
                case 12: goto L_0x0060;
                case 13: goto L_0x0056;
                case 14: goto L_0x004a;
                case 15: goto L_0x0040;
                case 16: goto L_0x0034;
                case 17: goto L_0x002a;
                default: goto L_0x0024;
            }
        L_0x0024:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L_0x002a:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r6.k(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0034:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            long r6 = r6.j(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0040:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x004a:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            long r6 = r6.j(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0056:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0060:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x006a:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0074:
            com.google.protobuf.i r6 = com.google.protobuf.ByteString.e
            com.google.protobuf.o0 r7 = com.google.protobuf.p0.f1625c
            java.lang.Object r7 = r7.k(r8, r0)
            boolean r6 = r6.equals(r7)
            r6 = r6 ^ r5
            return r6
        L_0x0082:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r6.k(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x008c:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            java.lang.Object r6 = r6.k(r8, r0)
            boolean r7 = r6 instanceof java.lang.String
            if (r7 == 0) goto L_0x009e
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r5
            return r6
        L_0x009e:
            boolean r7 = r6 instanceof com.google.protobuf.ByteString
            if (r7 == 0) goto L_0x00aa
            com.google.protobuf.i r7 = com.google.protobuf.ByteString.e
            boolean r6 = r7.equals(r6)
            r6 = r6 ^ r5
            return r6
        L_0x00aa:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L_0x00b0:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            boolean r6 = r6.d(r8, r0)
            return r6
        L_0x00b7:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00c0:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            long r6 = r6.j(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00cb:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            int r6 = r6.i(r8, r0)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00d4:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            long r6 = r6.j(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00df:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            long r6 = r6.j(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00ea:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            float r6 = r6.h(r8, r0)
            int r6 = java.lang.Float.floatToRawIntBits(r6)
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x00f7:
            com.google.protobuf.o0 r6 = com.google.protobuf.p0.f1625c
            double r6 = r6.g(r8, r0)
            long r6 = java.lang.Double.doubleToRawLongBits(r6)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0114
            goto L_0x0113
        L_0x0106:
            int r6 = r0 >>> 20
            int r6 = r5 << r6
            com.google.protobuf.o0 r7 = com.google.protobuf.p0.f1625c
            int r7 = r7.i(r8, r2)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x0114
        L_0x0113:
            return r5
        L_0x0114:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.X.r(int, java.lang.Object):boolean");
    }

    public final boolean s(Object obj, int i2, int i7, int i8, int i10) {
        if (i7 == 1048575) {
            return r(i2, obj);
        }
        if ((i8 & i10) != 0) {
            return true;
        }
        return false;
    }

    public final boolean u(int i2, int i7, Object obj) {
        if (p0.f1625c.i(obj, (long) (this.f1592a[i7 + 2] & 1048575)) == i2) {
            return true;
        }
        return false;
    }

    public final void v(int i2, Object obj, Object obj2) {
        long T = (long) (T(i2) & 1048575);
        Object k10 = p0.f1625c.k(obj, T);
        S s = this.m;
        if (k10 != null) {
            s.getClass();
            if (!((Q) k10).d) {
                Object c5 = Q.e.c();
                S.a(c5, k10);
                p0.p(T, obj, c5);
                k10 = c5;
            }
        } else {
            s.getClass();
            k10 = Q.e.c();
            p0.p(T, obj, k10);
        }
        s.getClass();
        Q q = (Q) k10;
        a.t(obj2);
        throw null;
    }

    public final void w(int i2, Object obj, Object obj2) {
        if (r(i2, obj2)) {
            long T = (long) (T(i2) & 1048575);
            Unsafe unsafe = f1591o;
            Object object = unsafe.getObject(obj2, T);
            if (object != null) {
                Schema p6 = p(i2);
                if (!r(i2, obj)) {
                    if (!t(object)) {
                        unsafe.putObject(obj, T, object);
                    } else {
                        Object f5 = p6.f();
                        p6.b(f5, object);
                        unsafe.putObject(obj, T, f5);
                    }
                    N(i2, obj);
                    return;
                }
                Object object2 = unsafe.getObject(obj, T);
                if (!t(object2)) {
                    Object f8 = p6.f();
                    p6.b(f8, object2);
                    unsafe.putObject(obj, T, f8);
                    object2 = f8;
                }
                p6.b(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + this.f1592a[i2] + " is present but null: " + obj2);
        }
    }

    public final void x(int i2, Object obj, Object obj2) {
        int[] iArr = this.f1592a;
        int i7 = iArr[i2];
        if (u(i7, i2, obj2)) {
            long T = (long) (T(i2) & 1048575);
            Unsafe unsafe = f1591o;
            Object object = unsafe.getObject(obj2, T);
            if (object != null) {
                Schema p6 = p(i2);
                if (!u(i7, i2, obj)) {
                    if (!t(object)) {
                        unsafe.putObject(obj, T, object);
                    } else {
                        Object f5 = p6.f();
                        p6.b(f5, object);
                        unsafe.putObject(obj, T, f5);
                    }
                    O(i7, i2, obj);
                    return;
                }
                Object object2 = unsafe.getObject(obj, T);
                if (!t(object2)) {
                    Object f8 = p6.f();
                    p6.b(f8, object2);
                    unsafe.putObject(obj, T, f8);
                    object2 = f8;
                }
                p6.b(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + iArr[i2] + " is present but null: " + obj2);
        }
    }

    public final Object y(int i2, Object obj) {
        Schema p6 = p(i2);
        long T = (long) (T(i2) & 1048575);
        if (!r(i2, obj)) {
            return p6.f();
        }
        Object object = f1591o.getObject(obj, T);
        if (t(object)) {
            return object;
        }
        Object f5 = p6.f();
        if (object != null) {
            p6.b(f5, object);
        }
        return f5;
    }

    public final Object z(int i2, int i7, Object obj) {
        Schema p6 = p(i7);
        if (!u(i2, i7, obj)) {
            return p6.f();
        }
        Object object = f1591o.getObject(obj, (long) (T(i7) & 1048575));
        if (t(object)) {
            return object;
        }
        Object f5 = p6.f();
        if (object != null) {
            p6.b(f5, object);
        }
        return f5;
    }
}
