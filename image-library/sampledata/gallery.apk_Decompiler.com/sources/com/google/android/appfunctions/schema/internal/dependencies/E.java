package com.google.android.appfunctions.schema.internal.dependencies;

import c0.C0086a;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E implements M {
    public static final int[] g = new int[0];

    /* renamed from: h  reason: collision with root package name */
    public static final Unsafe f1200h = W.p();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f1201a;
    public final Object[] b;

    /* renamed from: c  reason: collision with root package name */
    public final A f1202c;
    public final int[] d;
    public final int e;
    public final P f;

    public E(int[] iArr, Object[] objArr, A a7, int[] iArr2, int i2, P p6, C0094d dVar) {
        this.f1201a = iArr;
        this.b = objArr;
        this.d = iArr2;
        this.e = i2;
        this.f = p6;
        this.f1202c = a7;
    }

    public static int j(int i2) {
        return (i2 >>> 20) & ScoverState.TYPE_NFC_SMART_COVER;
    }

    public static boolean k(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof C0102l) {
            return ((C0102l) obj).e();
        }
        return true;
    }

    public static int l(Object obj, long j2) {
        return ((Integer) W.n(obj, j2)).intValue();
    }

    public static long m(Object obj, long j2) {
        return ((Long) W.n(obj, j2)).longValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0379  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.appfunctions.schema.internal.dependencies.E s(com.google.android.appfunctions.schema.internal.dependencies.L r30, com.google.android.appfunctions.schema.internal.dependencies.P r31, com.google.android.appfunctions.schema.internal.dependencies.C0094d r32) {
        /*
            r0 = r30
            boolean r1 = r0 instanceof com.google.android.appfunctions.schema.internal.dependencies.L
            if (r1 == 0) goto L_0x03df
            java.lang.String r1 = r0.b
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0021
            r4 = 1
        L_0x0017:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0022
            r4 = r7
            goto L_0x0017
        L_0x0021:
            r7 = 1
        L_0x0022:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0041
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x002e:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x003e
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x002e
        L_0x003e:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x0041:
            if (r7 != 0) goto L_0x004d
            int[] r7 = g
            r9 = r3
            r10 = r9
            r11 = r10
            r14 = r11
            r13 = r7
            r7 = r14
            goto L_0x013a
        L_0x004d:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x006c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0059:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0069
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
            if (r7 < r5) goto L_0x008b
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0078:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x0088
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
            if (r9 < r5) goto L_0x009e
        L_0x0093:
            int r9 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x009d
            r10 = r9
            goto L_0x0093
        L_0x009d:
            r10 = r9
        L_0x009e:
            int r9 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00b1
        L_0x00a6:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00b0
            r9 = r10
            goto L_0x00a6
        L_0x00b0:
            r9 = r10
        L_0x00b1:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00d0
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00bd:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00cd
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00bd
        L_0x00cd:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00d0:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00ef
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00dc:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00ec
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00dc
        L_0x00ec:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00ef:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x010e
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00fb:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x010b
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00fb
        L_0x010b:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x010e:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x012d
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x011a:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x012a
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x011a
        L_0x012a:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x012d:
            int r14 = r12 + r10
            int r14 = r14 + r11
            int r11 = r4 + r4
            int r11 = r11 + r7
            int[] r7 = new int[r14]
            r14 = r7
            r7 = r4
            r4 = r13
            r13 = r14
            r14 = r12
        L_0x013a:
            sun.misc.Unsafe r12 = f1200h
            java.lang.Object[] r15 = r0.f1210c
            com.google.android.appfunctions.schema.internal.dependencies.A r3 = r0.f1209a
            java.lang.Class r3 = r3.getClass()
            int r10 = r10 + r14
            int r8 = r9 + r9
            int r9 = r9 * 3
            int[] r9 = new int[r9]
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r19 = r14
            r17 = 0
            r18 = 0
        L_0x0153:
            if (r4 >= r2) goto L_0x03cd
            int r20 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x017b
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r6 = r20
            r20 = 13
        L_0x0163:
            int r22 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r5) goto L_0x0175
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r20
            r4 = r4 | r6
            int r20 = r20 + 13
            r6 = r22
            goto L_0x0163
        L_0x0175:
            int r6 = r6 << r20
            r4 = r4 | r6
            r6 = r22
            goto L_0x017d
        L_0x017b:
            r6 = r20
        L_0x017d:
            int r20 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r5) goto L_0x01aa
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r5 = r20
            r20 = 13
        L_0x018b:
            int r23 = r5 + 1
            char r5 = r1.charAt(r5)
            r24 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r2) goto L_0x01a4
            r2 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r20
            r6 = r6 | r2
            int r20 = r20 + 13
            r5 = r23
            r2 = r24
            goto L_0x018b
        L_0x01a4:
            int r2 = r5 << r20
            r6 = r6 | r2
            r2 = r23
            goto L_0x01ae
        L_0x01aa:
            r24 = r2
            r2 = r20
        L_0x01ae:
            r5 = r6 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L_0x01b8
            int r5 = r17 + 1
            r13[r17] = r18
            r17 = r5
        L_0x01b8:
            r5 = r6 & 255(0xff, float:3.57E-43)
            r20 = r4
            r4 = r6 & 2048(0x800, float:2.87E-42)
            r23 = r4
            r4 = 51
            if (r5 < r4) goto L_0x0284
            int r4 = r2 + 1
            char r2 = r1.charAt(r2)
            r25 = r4
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r4) goto L_0x01f6
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r4 = r25
            r25 = 13
        L_0x01d7:
            int r28 = r4 + 1
            char r4 = r1.charAt(r4)
            r29 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x01ef
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r25
            r2 = r29 | r2
            int r25 = r25 + 13
            r4 = r28
            goto L_0x01d7
        L_0x01ef:
            int r2 = r4 << r25
            r2 = r29 | r2
            r4 = r28
            goto L_0x01f8
        L_0x01f6:
            r4 = r25
        L_0x01f8:
            r25 = r2
            int r2 = r5 + -51
            r28 = r4
            r4 = 9
            if (r2 == r4) goto L_0x0206
            r4 = 17
            if (r2 != r4) goto L_0x0208
        L_0x0206:
            r4 = 1
            goto L_0x0228
        L_0x0208:
            r4 = 12
            if (r2 != r4) goto L_0x0225
            int r2 = r0.a()
            r4 = 1
            if (r2 == r4) goto L_0x0218
            if (r23 == 0) goto L_0x0216
            goto L_0x0218
        L_0x0216:
            r4 = 0
            goto L_0x0235
        L_0x0218:
            int r2 = r11 + 1
            int r21 = r18 / 3
            int r21 = r21 + r21
            int r21 = r21 + 1
            r11 = r15[r11]
            r8[r21] = r11
        L_0x0224:
            r11 = r2
        L_0x0225:
            r4 = r23
            goto L_0x0235
        L_0x0228:
            int r2 = r11 + 1
            int r21 = r18 / 3
            int r21 = r21 + r21
            int r26 = r21 + 1
            r4 = r15[r11]
            r8[r26] = r4
            goto L_0x0224
        L_0x0235:
            int r2 = r25 + r25
            r23 = r2
            r2 = r15[r23]
            r25 = r4
            boolean r4 = r2 instanceof java.lang.reflect.Field
            if (r4 == 0) goto L_0x0247
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
        L_0x0243:
            r4 = r7
            r29 = r8
            goto L_0x0250
        L_0x0247:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = t(r3, r2)
            r15[r23] = r2
            goto L_0x0243
        L_0x0250:
            long r7 = r12.objectFieldOffset(r2)
            int r2 = (int) r7
            int r7 = r23 + 1
            r8 = r15[r7]
            r23 = r2
            boolean r2 = r8 instanceof java.lang.reflect.Field
            if (r2 == 0) goto L_0x0262
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x026a
        L_0x0262:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = t(r3, r8)
            r15[r7] = r8
        L_0x026a:
            long r7 = r12.objectFieldOffset(r8)
            int r2 = (int) r7
            r22 = r25
            r25 = r4
            r4 = r22
            r22 = r1
            r26 = r11
            r1 = 0
            r27 = 55296(0xd800, float:7.7486E-41)
            r11 = r2
            r2 = r23
            r23 = r28
            goto L_0x0390
        L_0x0284:
            r4 = r7
            r29 = r8
            int r7 = r11 + 1
            r8 = r15[r11]
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = t(r3, r8)
            r25 = r4
            r4 = 9
            if (r5 == r4) goto L_0x029b
            r4 = 17
            if (r5 != r4) goto L_0x02a0
        L_0x029b:
            r26 = r7
            r7 = 1
            goto L_0x0310
        L_0x02a0:
            r4 = 27
            if (r5 == r4) goto L_0x0302
            r4 = 49
            if (r5 != r4) goto L_0x02ae
            int r11 = r11 + 2
            r26 = r7
            r7 = 1
            goto L_0x0307
        L_0x02ae:
            r4 = 12
            if (r5 == r4) goto L_0x02e8
            r4 = 30
            if (r5 == r4) goto L_0x02e8
            r4 = 44
            if (r5 != r4) goto L_0x02bb
            goto L_0x02e8
        L_0x02bb:
            r4 = 50
            if (r5 != r4) goto L_0x02e4
            int r4 = r11 + 2
            int r26 = r19 + 1
            r13[r19] = r18
            int r19 = r18 / 3
            r7 = r15[r7]
            int r19 = r19 + r19
            r29[r19] = r7
            if (r23 == 0) goto L_0x02de
            int r19 = r19 + 1
            int r7 = r11 + 3
            r4 = r15[r4]
            r29[r19] = r4
            r4 = r23
            r19 = r26
            r26 = r7
            goto L_0x031b
        L_0x02de:
            r19 = r26
            r26 = r4
        L_0x02e2:
            r4 = 0
            goto L_0x031b
        L_0x02e4:
            r26 = r7
            r7 = 1
            goto L_0x02ff
        L_0x02e8:
            int r4 = r0.a()
            r26 = r7
            r7 = 1
            if (r4 == r7) goto L_0x02f3
            if (r23 == 0) goto L_0x02e2
        L_0x02f3:
            int r11 = r11 + 2
            int r4 = r18 / 3
            int r4 = r4 + r4
            int r4 = r4 + r7
            r21 = r15[r26]
            r29[r4] = r21
        L_0x02fd:
            r26 = r11
        L_0x02ff:
            r4 = r23
            goto L_0x031b
        L_0x0302:
            r26 = r7
            r7 = 1
            int r11 = r11 + 2
        L_0x0307:
            int r4 = r18 / 3
            int r4 = r4 + r4
            int r4 = r4 + r7
            r21 = r15[r26]
            r29[r4] = r21
            goto L_0x02fd
        L_0x0310:
            int r4 = r18 / 3
            int r4 = r4 + r4
            int r4 = r4 + r7
            java.lang.Class r11 = r8.getType()
            r29[r4] = r11
            goto L_0x02ff
        L_0x031b:
            long r7 = r12.objectFieldOffset(r8)
            int r7 = (int) r7
            r8 = r6 & 4096(0x1000, float:5.74E-42)
            r11 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 == 0) goto L_0x0379
            r8 = 17
            if (r5 > r8) goto L_0x0379
            int r8 = r2 + 1
            char r2 = r1.charAt(r2)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r11) goto L_0x0350
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r22 = 13
        L_0x033a:
            int r23 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r11) goto L_0x034c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r22
            r2 = r2 | r8
            int r22 = r22 + 13
            r8 = r23
            goto L_0x033a
        L_0x034c:
            int r8 = r8 << r22
            r2 = r2 | r8
            goto L_0x0352
        L_0x0350:
            r23 = r8
        L_0x0352:
            int r8 = r25 + r25
            int r22 = r2 / 32
            int r22 = r22 + r8
            r8 = r15[r22]
            boolean r11 = r8 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x0364
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
        L_0x0360:
            r22 = r1
            r11 = r2
            goto L_0x036d
        L_0x0364:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = t(r3, r8)
            r15[r22] = r8
            goto L_0x0360
        L_0x036d:
            long r1 = r12.objectFieldOffset(r8)
            int r1 = (int) r1
            int r2 = r11 % 32
            r11 = r1
            r27 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0381
        L_0x0379:
            r22 = r1
            r27 = 55296(0xd800, float:7.7486E-41)
            r23 = r2
            r2 = 0
        L_0x0381:
            r1 = 18
            if (r5 < r1) goto L_0x038e
            r1 = 49
            if (r5 > r1) goto L_0x038e
            int r1 = r10 + 1
            r13[r10] = r7
            r10 = r1
        L_0x038e:
            r1 = r2
            r2 = r7
        L_0x0390:
            int r7 = r18 + 1
            r9[r18] = r20
            int r8 = r18 + 2
            r20 = r1
            r1 = r6 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x039f
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03a0
        L_0x039f:
            r1 = 0
        L_0x03a0:
            r6 = r6 & 256(0x100, float:3.59E-43)
            if (r6 == 0) goto L_0x03a7
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03a8
        L_0x03a7:
            r6 = 0
        L_0x03a8:
            if (r4 == 0) goto L_0x03ad
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03ae
        L_0x03ad:
            r4 = 0
        L_0x03ae:
            int r5 = r5 << 20
            r1 = r1 | r6
            r1 = r1 | r4
            r1 = r1 | r5
            r1 = r1 | r2
            r9[r7] = r1
            int r18 = r18 + 3
            int r1 = r20 << 20
            r1 = r1 | r11
            r9[r8] = r1
            r1 = r22
            r4 = r23
            r2 = r24
            r7 = r25
            r11 = r26
            r5 = r27
            r8 = r29
            goto L_0x0153
        L_0x03cd:
            r29 = r8
            com.google.android.appfunctions.schema.internal.dependencies.E r1 = new com.google.android.appfunctions.schema.internal.dependencies.E
            com.google.android.appfunctions.schema.internal.dependencies.A r12 = r0.f1209a
            r15 = r31
            r16 = r32
            r10 = r9
            r11 = r29
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            return r9
        L_0x03df:
            r0.getClass()
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.s(com.google.android.appfunctions.schema.internal.dependencies.L, com.google.android.appfunctions.schema.internal.dependencies.P, com.google.android.appfunctions.schema.internal.dependencies.d):com.google.android.appfunctions.schema.internal.dependencies.E");
    }

    public static Field t(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e7) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + name.length() + 29 + String.valueOf(arrays).length());
            C0086a.z(sb2, "Field ", str, " for ", name);
            throw new RuntimeException(C0212a.p(sb2, " not found. Known fields are ", arrays), e7);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0466, code lost:
        r0 = r19;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.Object r20, com.google.android.appfunctions.schema.internal.dependencies.C0114y r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r6 = r21
            java.lang.Object r2 = r6.f1229a
            r7 = r2
            com.google.android.appfunctions.schema.internal.dependencies.h0 r7 = (com.google.android.appfunctions.schema.internal.dependencies.h0) r7
            sun.misc.Unsafe r8 = f1200h
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r10
            r2 = 0
            r4 = 0
        L_0x0013:
            int[] r5 = r0.f1201a
            int r11 = r5.length
            if (r2 >= r11) goto L_0x05c5
            int r11 = r0.i(r2)
            int r12 = j(r11)
            r13 = r5[r2]
            r14 = 17
            r15 = 1
            if (r12 > r14) goto L_0x0044
            int r14 = r2 + 2
            r14 = r5[r14]
            r9 = r14 & r10
            if (r9 == r3) goto L_0x003a
            if (r9 != r10) goto L_0x0033
            r4 = 0
            goto L_0x0039
        L_0x0033:
            long r3 = (long) r9
            int r3 = r8.getInt(r1, r3)
            r4 = r3
        L_0x0039:
            r3 = r9
        L_0x003a:
            int r9 = r14 >>> 20
            int r9 = r15 << r9
            r18 = r9
            r9 = r5
            r5 = r18
            goto L_0x0046
        L_0x0044:
            r9 = r5
            r5 = 0
        L_0x0046:
            r11 = r11 & r10
            long r10 = (long) r11
            r17 = 63
            r14 = 4
            r15 = 3
            switch(r12) {
                case 0: goto L_0x05aa;
                case 1: goto L_0x0594;
                case 2: goto L_0x0584;
                case 3: goto L_0x0574;
                case 4: goto L_0x0557;
                case 5: goto L_0x0547;
                case 6: goto L_0x0537;
                case 7: goto L_0x0508;
                case 8: goto L_0x04eb;
                case 9: goto L_0x04d7;
                case 10: goto L_0x04c6;
                case 11: goto L_0x04b7;
                case 12: goto L_0x049c;
                case 13: goto L_0x048d;
                case 14: goto L_0x047e;
                case 15: goto L_0x046a;
                case 16: goto L_0x0453;
                case 17: goto L_0x0437;
                case 18: goto L_0x0429;
                case 19: goto L_0x041b;
                case 20: goto L_0x040d;
                case 21: goto L_0x03ff;
                case 22: goto L_0x03f1;
                case 23: goto L_0x03e3;
                case 24: goto L_0x03d5;
                case 25: goto L_0x03c7;
                case 26: goto L_0x037e;
                case 27: goto L_0x0357;
                case 28: goto L_0x0332;
                case 29: goto L_0x0324;
                case 30: goto L_0x0316;
                case 31: goto L_0x0308;
                case 32: goto L_0x02fa;
                case 33: goto L_0x02ec;
                case 34: goto L_0x02de;
                case 35: goto L_0x02d0;
                case 36: goto L_0x02c2;
                case 37: goto L_0x02b4;
                case 38: goto L_0x02a6;
                case 39: goto L_0x0298;
                case 40: goto L_0x028a;
                case 41: goto L_0x027c;
                case 42: goto L_0x026e;
                case 43: goto L_0x0260;
                case 44: goto L_0x0252;
                case 45: goto L_0x0244;
                case 46: goto L_0x0236;
                case 47: goto L_0x0228;
                case 48: goto L_0x021a;
                case 49: goto L_0x01eb;
                case 50: goto L_0x01d4;
                case 51: goto L_0x01bb;
                case 52: goto L_0x01a2;
                case 53: goto L_0x0193;
                case 54: goto L_0x0184;
                case 55: goto L_0x0168;
                case 56: goto L_0x0159;
                case 57: goto L_0x014a;
                case 58: goto L_0x0117;
                case 59: goto L_0x00fb;
                case 60: goto L_0x00e8;
                case 61: goto L_0x00d7;
                case 62: goto L_0x00c8;
                case 63: goto L_0x00ae;
                case 64: goto L_0x00a0;
                case 65: goto L_0x0092;
                case 66: goto L_0x007f;
                case 67: goto L_0x006c;
                case 68: goto L_0x0052;
                default: goto L_0x004f;
            }
        L_0x004f:
            r12 = 0
            goto L_0x05be
        L_0x0052:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.M r9 = r0.w(r2)
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            r7.Z(r13, r15)
            r9.a(r5, r6)
            r7.Z(r13, r14)
            goto L_0x004f
        L_0x006c:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            long r9 = m(r1, r10)
            long r11 = r9 + r9
            long r9 = r9 >> r17
            long r9 = r9 ^ r11
            r7.c0(r13, r9)
            goto L_0x004f
        L_0x007f:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            int r9 = r5 + r5
            int r5 = r5 >> 31
            r5 = r5 ^ r9
            r7.a0(r13, r5)
            goto L_0x004f
        L_0x0092:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            long r9 = m(r1, r10)
            r7.e0(r13, r9)
            goto L_0x004f
        L_0x00a0:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            r7.b0(r13, r5)
            goto L_0x004f
        L_0x00ae:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            int r9 = r13 << 3
            r7.h0(r9)
            if (r5 < 0) goto L_0x00c3
            r7.h0(r5)
            goto L_0x004f
        L_0x00c3:
            long r9 = (long) r5
            r7.j0(r9)
            goto L_0x004f
        L_0x00c8:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            r7.a0(r13, r5)
            goto L_0x004f
        L_0x00d7:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r5 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r5
            r7.g0(r13, r5)
            goto L_0x004f
        L_0x00e8:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.M r9 = r0.w(r2)
            r6.a(r13, r5, r9)
            goto L_0x004f
        L_0x00fb:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = r8.getObject(r1, r10)
            boolean r9 = r5 instanceof java.lang.String
            if (r9 == 0) goto L_0x0110
            java.lang.String r5 = (java.lang.String) r5
            r7.f0(r13, r5)
            goto L_0x004f
        L_0x0110:
            com.google.android.appfunctions.schema.internal.dependencies.f0 r5 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r5
            r7.g0(r13, r5)
            goto L_0x004f
        L_0x0117:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r1, r10)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            int r9 = r13 << 3
            r7.h0(r9)
            int r9 = r7.g
            byte[] r10 = r7.e     // Catch:{ IndexOutOfBoundsException -> 0x013d }
            int r11 = r9 + 1
            r10[r9] = r5     // Catch:{ IndexOutOfBoundsException -> 0x0138 }
            r7.g = r11
            goto L_0x004f
        L_0x0138:
            r0 = move-exception
            r9 = r11
        L_0x013a:
            r16 = r0
            goto L_0x013f
        L_0x013d:
            r0 = move-exception
            goto L_0x013a
        L_0x013f:
            int r0 = r7.f
            I0.a r10 = new I0.a
            long r11 = (long) r9
            long r13 = (long) r0
            r15 = 1
            r10.<init>(r11, r13, r15, r16)
            throw r10
        L_0x014a:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            r7.b0(r13, r5)
            goto L_0x004f
        L_0x0159:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            long r9 = m(r1, r10)
            r7.e0(r13, r9)
            goto L_0x004f
        L_0x0168:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            int r5 = l(r1, r10)
            int r9 = r13 << 3
            r7.h0(r9)
            if (r5 < 0) goto L_0x017e
            r7.h0(r5)
            goto L_0x004f
        L_0x017e:
            long r9 = (long) r5
            r7.j0(r9)
            goto L_0x004f
        L_0x0184:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            long r9 = m(r1, r10)
            r7.c0(r13, r9)
            goto L_0x004f
        L_0x0193:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            long r9 = m(r1, r10)
            r7.c0(r13, r9)
            goto L_0x004f
        L_0x01a2:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r1, r10)
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            int r5 = java.lang.Float.floatToRawIntBits(r5)
            r7.b0(r13, r5)
            goto L_0x004f
        L_0x01bb:
            boolean r5 = r0.r(r13, r2, r1)
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r1, r10)
            java.lang.Double r5 = (java.lang.Double) r5
            double r9 = r5.doubleValue()
            long r9 = java.lang.Double.doubleToRawLongBits(r9)
            r7.e0(r13, r9)
            goto L_0x004f
        L_0x01d4:
            java.lang.Object r5 = r8.getObject(r1, r10)
            if (r5 != 0) goto L_0x01dc
            goto L_0x004f
        L_0x01dc:
            int r2 = r2 / r15
            java.lang.Object[] r0 = r0.b
            int r2 = r2 + r2
            r0 = r0[r2]
            r0.getClass()
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            r0.<init>()
            throw r0
        L_0x01eb:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.M r10 = r0.w(r2)
            java.lang.Class r11 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            if (r9 == 0) goto L_0x004f
            boolean r11 = r9.isEmpty()
            if (r11 != 0) goto L_0x004f
            r11 = 0
        L_0x0202:
            int r12 = r9.size()
            if (r11 >= r12) goto L_0x004f
            java.lang.Object r12 = r9.get(r11)
            com.google.android.appfunctions.schema.internal.dependencies.A r12 = (com.google.android.appfunctions.schema.internal.dependencies.A) r12
            r7.Z(r5, r15)
            r10.a(r12, r6)
            r7.Z(r5, r14)
            int r11 = r11 + 1
            goto L_0x0202
        L_0x021a:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            r12 = 1
            com.google.android.appfunctions.schema.internal.dependencies.N.i(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0228:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.n(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0236:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.k(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0244:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.p(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0252:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.q(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0260:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.m(r5, r9, r6, r12)
            goto L_0x004f
        L_0x026e:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.r(r5, r9, r6, r12)
            goto L_0x004f
        L_0x027c:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.o(r5, r9, r6, r12)
            goto L_0x004f
        L_0x028a:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.j(r5, r9, r6, r12)
            goto L_0x004f
        L_0x0298:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.l(r5, r9, r6, r12)
            goto L_0x004f
        L_0x02a6:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.h(r5, r9, r6, r12)
            goto L_0x004f
        L_0x02b4:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.g(r5, r9, r6, r12)
            goto L_0x004f
        L_0x02c2:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.f(r5, r9, r6, r12)
            goto L_0x004f
        L_0x02d0:
            r12 = 1
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.e(r5, r9, r6, r12)
            goto L_0x004f
        L_0x02de:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.appfunctions.schema.internal.dependencies.N.i(r5, r9, r6, r12)
            goto L_0x05be
        L_0x02ec:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.n(r5, r9, r6, r12)
            goto L_0x05be
        L_0x02fa:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.k(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0308:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.p(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0316:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.q(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0324:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.m(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0332:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            java.lang.Class r10 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            if (r9 == 0) goto L_0x004f
            boolean r10 = r9.isEmpty()
            if (r10 != 0) goto L_0x004f
            r12 = 0
        L_0x0345:
            int r10 = r9.size()
            if (r12 >= r10) goto L_0x004f
            java.lang.Object r10 = r9.get(r12)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r10 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r10
            r7.g0(r5, r10)
            int r12 = r12 + 1
            goto L_0x0345
        L_0x0357:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.M r10 = r0.w(r2)
            java.lang.Class r11 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            if (r9 == 0) goto L_0x004f
            boolean r11 = r9.isEmpty()
            if (r11 != 0) goto L_0x004f
            r12 = 0
        L_0x036e:
            int r11 = r9.size()
            if (r12 >= r11) goto L_0x004f
            java.lang.Object r11 = r9.get(r12)
            r6.a(r5, r11, r10)
            int r12 = r12 + 1
            goto L_0x036e
        L_0x037e:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            java.lang.Class r10 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            if (r9 == 0) goto L_0x004f
            boolean r10 = r9.isEmpty()
            if (r10 != 0) goto L_0x004f
            boolean r10 = r9 instanceof com.google.android.appfunctions.schema.internal.dependencies.C0109t
            if (r10 == 0) goto L_0x03b4
            r10 = r9
            com.google.android.appfunctions.schema.internal.dependencies.t r10 = (com.google.android.appfunctions.schema.internal.dependencies.C0109t) r10
            r12 = 0
        L_0x0398:
            int r11 = r9.size()
            if (r12 >= r11) goto L_0x004f
            java.lang.Object r11 = r10.o()
            boolean r13 = r11 instanceof java.lang.String
            if (r13 == 0) goto L_0x03ac
            java.lang.String r11 = (java.lang.String) r11
            r7.f0(r5, r11)
            goto L_0x03b1
        L_0x03ac:
            com.google.android.appfunctions.schema.internal.dependencies.f0 r11 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r11
            r7.g0(r5, r11)
        L_0x03b1:
            int r12 = r12 + 1
            goto L_0x0398
        L_0x03b4:
            r12 = 0
        L_0x03b5:
            int r10 = r9.size()
            if (r12 >= r10) goto L_0x004f
            java.lang.Object r10 = r9.get(r12)
            java.lang.String r10 = (java.lang.String) r10
            r7.f0(r5, r10)
            int r12 = r12 + 1
            goto L_0x03b5
        L_0x03c7:
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            r12 = 0
            com.google.android.appfunctions.schema.internal.dependencies.N.r(r5, r9, r6, r12)
            goto L_0x05be
        L_0x03d5:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.o(r5, r9, r6, r12)
            goto L_0x05be
        L_0x03e3:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.j(r5, r9, r6, r12)
            goto L_0x05be
        L_0x03f1:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.l(r5, r9, r6, r12)
            goto L_0x05be
        L_0x03ff:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.h(r5, r9, r6, r12)
            goto L_0x05be
        L_0x040d:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.g(r5, r9, r6, r12)
            goto L_0x05be
        L_0x041b:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.f(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0429:
            r12 = 0
            r5 = r9[r2]
            java.lang.Object r9 = r8.getObject(r1, r10)
            java.util.List r9 = (java.util.List) r9
            com.google.android.appfunctions.schema.internal.dependencies.N.e(r5, r9, r6, r12)
            goto L_0x05be
        L_0x0437:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05be
            java.lang.Object r5 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.M r9 = r0.w(r2)
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            r7.Z(r13, r15)
            r9.a(r5, r6)
            r7.Z(r13, r14)
            goto L_0x05be
        L_0x0453:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            long r9 = r8.getLong(r1, r10)
            long r14 = r9 + r9
            long r9 = r9 >> r17
            long r9 = r9 ^ r14
            r7.c0(r13, r9)
        L_0x0466:
            r0 = r19
            goto L_0x05be
        L_0x046a:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            int r5 = r0 + r0
            int r0 = r0 >> 31
            r0 = r0 ^ r5
            r7.a0(r13, r0)
            goto L_0x0466
        L_0x047e:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            long r9 = r8.getLong(r1, r10)
            r7.e0(r13, r9)
            goto L_0x0466
        L_0x048d:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            r7.b0(r13, r0)
            goto L_0x0466
        L_0x049c:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            int r5 = r13 << 3
            r7.h0(r5)
            if (r0 < 0) goto L_0x04b2
            r7.h0(r0)
            goto L_0x0466
        L_0x04b2:
            long r9 = (long) r0
            r7.j0(r9)
            goto L_0x0466
        L_0x04b7:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            r7.a0(r13, r0)
            goto L_0x0466
        L_0x04c6:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            java.lang.Object r0 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r0 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r0
            r7.g0(r13, r0)
            goto L_0x0466
        L_0x04d7:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05be
            java.lang.Object r5 = r8.getObject(r1, r10)
            com.google.android.appfunctions.schema.internal.dependencies.M r9 = r0.w(r2)
            r6.a(r13, r5, r9)
            goto L_0x05be
        L_0x04eb:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            java.lang.Object r0 = r8.getObject(r1, r10)
            boolean r5 = r0 instanceof java.lang.String
            if (r5 == 0) goto L_0x0501
            java.lang.String r0 = (java.lang.String) r0
            r7.f0(r13, r0)
            goto L_0x0466
        L_0x0501:
            com.google.android.appfunctions.schema.internal.dependencies.f0 r0 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r0
            r7.g0(r13, r0)
            goto L_0x0466
        L_0x0508:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            com.google.android.appfunctions.schema.internal.dependencies.V r0 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            boolean r0 = r0.b(r1, r10)
            int r5 = r13 << 3
            r7.h0(r5)
            int r5 = r7.g
            byte[] r9 = r7.e     // Catch:{ IndexOutOfBoundsException -> 0x052a }
            int r10 = r5 + 1
            r9[r5] = r0     // Catch:{ IndexOutOfBoundsException -> 0x0526 }
            r7.g = r10
            goto L_0x0466
        L_0x0526:
            r0 = move-exception
            r5 = r10
        L_0x0528:
            r14 = r0
            goto L_0x052c
        L_0x052a:
            r0 = move-exception
            goto L_0x0528
        L_0x052c:
            int r0 = r7.f
            I0.a r8 = new I0.a
            long r9 = (long) r5
            long r11 = (long) r0
            r13 = 1
            r8.<init>(r9, r11, r13, r14)
            throw r8
        L_0x0537:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            r7.b0(r13, r0)
            goto L_0x0466
        L_0x0547:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            long r9 = r8.getLong(r1, r10)
            r7.e0(r13, r9)
            goto L_0x0466
        L_0x0557:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            int r0 = r8.getInt(r1, r10)
            int r5 = r13 << 3
            r7.h0(r5)
            if (r0 < 0) goto L_0x056e
            r7.h0(r0)
            goto L_0x0466
        L_0x056e:
            long r9 = (long) r0
            r7.j0(r9)
            goto L_0x0466
        L_0x0574:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            long r9 = r8.getLong(r1, r10)
            r7.c0(r13, r9)
            goto L_0x0466
        L_0x0584:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            long r9 = r8.getLong(r1, r10)
            r7.c0(r13, r9)
            goto L_0x0466
        L_0x0594:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x0466
            com.google.android.appfunctions.schema.internal.dependencies.V r0 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            float r0 = r0.d(r1, r10)
            int r0 = java.lang.Float.floatToRawIntBits(r0)
            r7.b0(r13, r0)
            goto L_0x0466
        L_0x05aa:
            r12 = 0
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05be
            com.google.android.appfunctions.schema.internal.dependencies.V r5 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            double r9 = r5.f(r1, r10)
            long r9 = java.lang.Double.doubleToRawLongBits(r9)
            r7.e0(r13, r9)
        L_0x05be:
            int r2 = r2 + 3
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0013
        L_0x05c5:
            com.google.android.appfunctions.schema.internal.dependencies.P r0 = r0.f
            r0.getClass()
            r0 = r1
            com.google.android.appfunctions.schema.internal.dependencies.l r0 = (com.google.android.appfunctions.schema.internal.dependencies.C0102l) r0
            com.google.android.appfunctions.schema.internal.dependencies.O r0 = r0.unknownFields
            r0.a(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.a(java.lang.Object, com.google.android.appfunctions.schema.internal.dependencies.y):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x03b5, code lost:
        r8 = (r8 * r7) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x03b7, code lost:
        r9 = r9 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x04f2, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x04fc, code lost:
        r9 = r9 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x05be, code lost:
        r9 = r9 + (r5 + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x05c0, code lost:
        r0 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x05ea, code lost:
        r0 = r16;
        r1 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        r9 = r9 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        r9 = r9 + (r7 + r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int b(com.google.android.appfunctions.schema.internal.dependencies.C0102l r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            sun.misc.Unsafe r6 = f1200h
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r8
            r2 = 0
            r4 = 0
            r9 = 0
        L_0x000d:
            int[] r5 = r0.f1201a
            int r10 = r5.length
            if (r2 >= r10) goto L_0x0724
            int r10 = r0.i(r2)
            int r11 = j(r10)
            r12 = r5[r2]
            int r13 = r2 + 2
            r5 = r5[r13]
            r13 = r5 & r8
            r14 = 17
            r15 = 1
            if (r11 > r14) goto L_0x0039
            if (r13 == r3) goto L_0x0034
            if (r13 != r8) goto L_0x002d
            r4 = 0
            goto L_0x0033
        L_0x002d:
            long r3 = (long) r13
            int r3 = r6.getInt(r1, r3)
            r4 = r3
        L_0x0033:
            r3 = r13
        L_0x0034:
            int r5 = r5 >>> 20
            int r5 = r15 << r5
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            r10 = r10 & r8
            com.google.android.appfunctions.schema.internal.dependencies.f r13 = com.google.android.appfunctions.schema.internal.dependencies.C0096f.DOUBLE_LIST_PACKED
            int r13 = r13.a()
            if (r11 < r13) goto L_0x0048
            com.google.android.appfunctions.schema.internal.dependencies.f r13 = com.google.android.appfunctions.schema.internal.dependencies.C0096f.SINT64_LIST_PACKED
            r13.getClass()
        L_0x0048:
            long r13 = (long) r10
            r10 = 63
            r7 = 4
            r8 = 8
            switch(r11) {
                case 0: goto L_0x0711;
                case 1: goto L_0x0703;
                case 2: goto L_0x06ed;
                case 3: goto L_0x06d7;
                case 4: goto L_0x06c0;
                case 5: goto L_0x06b2;
                case 6: goto L_0x06a4;
                case 7: goto L_0x0696;
                case 8: goto L_0x0666;
                case 9: goto L_0x0644;
                case 10: goto L_0x0628;
                case 11: goto L_0x0613;
                case 12: goto L_0x05fd;
                case 13: goto L_0x05f0;
                case 14: goto L_0x05de;
                case 15: goto L_0x05c4;
                case 16: goto L_0x05a6;
                case 17: goto L_0x0587;
                case 18: goto L_0x057b;
                case 19: goto L_0x056f;
                case 20: goto L_0x054f;
                case 21: goto L_0x0533;
                case 22: goto L_0x0517;
                case 23: goto L_0x050b;
                case 24: goto L_0x04ff;
                case 25: goto L_0x04e4;
                case 26: goto L_0x0481;
                case 27: goto L_0x044f;
                case 28: goto L_0x0420;
                case 29: goto L_0x0406;
                case 30: goto L_0x03ec;
                case 31: goto L_0x03e0;
                case 32: goto L_0x03d4;
                case 33: goto L_0x03ba;
                case 34: goto L_0x039b;
                case 35: goto L_0x0380;
                case 36: goto L_0x0365;
                case 37: goto L_0x034d;
                case 38: goto L_0x0335;
                case 39: goto L_0x031d;
                case 40: goto L_0x0302;
                case 41: goto L_0x02e7;
                case 42: goto L_0x02cd;
                case 43: goto L_0x02b5;
                case 44: goto L_0x029d;
                case 45: goto L_0x0282;
                case 46: goto L_0x0267;
                case 47: goto L_0x024f;
                case 48: goto L_0x0237;
                case 49: goto L_0x0206;
                case 50: goto L_0x01ea;
                case 51: goto L_0x01dc;
                case 52: goto L_0x01ce;
                case 53: goto L_0x01b8;
                case 54: goto L_0x01a2;
                case 55: goto L_0x018b;
                case 56: goto L_0x017d;
                case 57: goto L_0x016f;
                case 58: goto L_0x0161;
                case 59: goto L_0x0131;
                case 60: goto L_0x010f;
                case 61: goto L_0x00f3;
                case 62: goto L_0x00dd;
                case 63: goto L_0x00c7;
                case 64: goto L_0x00b9;
                case 65: goto L_0x00ab;
                case 66: goto L_0x0090;
                case 67: goto L_0x0074;
                case 68: goto L_0x0053;
                default: goto L_0x0051;
            }
        L_0x0051:
            goto L_0x071d
        L_0x0053:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r12 << 3
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r8 = r8 + r8
            int r5 = r5.a(r7)
        L_0x0070:
            int r5 = r5 + r8
        L_0x0071:
            int r9 = r9 + r5
            goto L_0x071d
        L_0x0074:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            long r7 = m(r1, r13)
            long r11 = r7 + r7
            long r7 = r7 >> r10
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            long r7 = r7 ^ r11
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
        L_0x008c:
            int r7 = r7 + r5
            int r9 = r9 + r7
            goto L_0x071d
        L_0x0090:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r7 = l(r1, r13)
            int r8 = r7 + r7
            int r7 = r7 >> 31
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            r7 = r7 ^ r8
            int r9 = c0.C0086a.a(r7, r5, r9)
            goto L_0x071d
        L_0x00ab:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r8, r9)
            goto L_0x071d
        L_0x00b9:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r7, r9)
            goto L_0x071d
        L_0x00c7:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r7 = l(r1, r13)
            long r7 = (long) r7
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x008c
        L_0x00dd:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r7 = l(r1, r13)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r9 = c0.C0086a.a(r7, r5, r9)
            goto L_0x071d
        L_0x00f3:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            java.lang.Object r7 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r7 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r7
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = r7.r()
            int r9 = c0.C0086a.b(r7, r7, r5, r9)
            goto L_0x071d
        L_0x010f:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r12 << 3
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r5 = r5.a(r7)
            int r9 = c0.C0086a.b(r5, r5, r8, r9)
            goto L_0x071d
        L_0x0131:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            java.lang.Object r7 = r6.getObject(r1, r13)
            boolean r8 = r7 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r8 == 0) goto L_0x0151
            com.google.android.appfunctions.schema.internal.dependencies.f0 r7 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r7
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = r7.r()
            int r9 = c0.C0086a.b(r7, r7, r5, r9)
            goto L_0x071d
        L_0x0151:
            java.lang.String r7 = (java.lang.String) r7
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.Z.a(r7)
            int r9 = c0.C0086a.b(r7, r7, r5, r9)
            goto L_0x071d
        L_0x0161:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r15, r9)
            goto L_0x071d
        L_0x016f:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r7, r9)
            goto L_0x071d
        L_0x017d:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r8, r9)
            goto L_0x071d
        L_0x018b:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r7 = l(r1, r13)
            long r7 = (long) r7
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x008c
        L_0x01a2:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            long r7 = m(r1, r13)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x008c
        L_0x01b8:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            long r7 = m(r1, r13)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r5)
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x008c
        L_0x01ce:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r7, r9)
            goto L_0x071d
        L_0x01dc:
            boolean r5 = r0.r(r12, r2, r1)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r8, r9)
            goto L_0x071d
        L_0x01ea:
            java.lang.Object r1 = r6.getObject(r1, r13)
            int r2 = r2 / 3
            java.lang.Object[] r0 = r0.b
            int r2 = r2 + r2
            r0 = r0[r2]
            if (r1 != 0) goto L_0x0200
            r0.getClass()
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            r0.<init>()
            throw r0
        L_0x0200:
            java.lang.ClassCastException r0 = new java.lang.ClassCastException
            r0.<init>()
            throw r0
        L_0x0206:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r5.size()
            if (r8 != 0) goto L_0x021a
            r11 = 0
            goto L_0x0234
        L_0x021a:
            r10 = 0
            r11 = 0
        L_0x021c:
            if (r10 >= r8) goto L_0x0234
            java.lang.Object r13 = r5.get(r10)
            com.google.android.appfunctions.schema.internal.dependencies.A r13 = (com.google.android.appfunctions.schema.internal.dependencies.A) r13
            int r14 = r12 << 3
            int r14 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r14)
            int r14 = r14 + r14
            int r13 = r13.a(r7)
            int r13 = r13 + r14
            int r11 = r11 + r13
            int r10 = r10 + 1
            goto L_0x021c
        L_0x0234:
            int r9 = r9 + r11
            goto L_0x071d
        L_0x0237:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.u(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x024f:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.y(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0267:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r8
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0282:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r7
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x029d:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.v(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x02b5:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.x(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x02cd:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x02e7:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r7
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0302:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r8
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x031d:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.w(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0335:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.t(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x034d:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.s(r5)
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0365:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r7
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x0380:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            int r5 = r5 * r8
            if (r5 <= 0) goto L_0x071d
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r9 = c0.C0086a.b(r5, r7, r5, r9)
            goto L_0x071d
        L_0x039b:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x03ab
        L_0x03a9:
            r8 = 0
            goto L_0x03b7
        L_0x03ab:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.u(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
        L_0x03b5:
            int r8 = r8 * r7
            int r8 = r8 + r5
        L_0x03b7:
            int r9 = r9 + r8
            goto L_0x071d
        L_0x03ba:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x03c9
            goto L_0x03a9
        L_0x03c9:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.y(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            goto L_0x03b5
        L_0x03d4:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.b(r12, r5)
            goto L_0x0071
        L_0x03e0:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.a(r12, r5)
            goto L_0x0071
        L_0x03ec:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x03fb
            goto L_0x03a9
        L_0x03fb:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.v(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            goto L_0x03b5
        L_0x0406:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0415
            goto L_0x03a9
        L_0x0415:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.x(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            goto L_0x03b5
        L_0x0420:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0430
            goto L_0x03a9
        L_0x0430:
            int r8 = r12 << 3
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r8 = r8 * r7
            r7 = 0
        L_0x0438:
            int r10 = r5.size()
            if (r7 >= r10) goto L_0x03b7
            java.lang.Object r10 = r5.get(r7)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r10 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r10
            int r10 = r10.r()
            int r8 = c0.C0086a.a(r10, r10, r8)
            int r7 = r7 + 1
            goto L_0x0438
        L_0x044f:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r5.size()
            if (r8 != 0) goto L_0x0463
            r10 = 0
            goto L_0x047e
        L_0x0463:
            int r10 = r12 << 3
            int r10 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r10)
            int r10 = r10 * r8
            r11 = 0
        L_0x046b:
            if (r11 >= r8) goto L_0x047e
            java.lang.Object r12 = r5.get(r11)
            com.google.android.appfunctions.schema.internal.dependencies.A r12 = (com.google.android.appfunctions.schema.internal.dependencies.A) r12
            int r12 = r12.a(r7)
            int r10 = c0.C0086a.a(r12, r12, r10)
            int r11 = r11 + 1
            goto L_0x046b
        L_0x047e:
            int r9 = r9 + r10
            goto L_0x071d
        L_0x0481:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0491
            goto L_0x03a9
        L_0x0491:
            int r8 = r12 << 3
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r8 = r8 * r7
            boolean r10 = r5 instanceof com.google.android.appfunctions.schema.internal.dependencies.C0109t
            if (r10 == 0) goto L_0x04c1
            com.google.android.appfunctions.schema.internal.dependencies.t r5 = (com.google.android.appfunctions.schema.internal.dependencies.C0109t) r5
            r10 = 0
        L_0x049f:
            if (r10 >= r7) goto L_0x03b7
            java.lang.Object r11 = r5.o()
            boolean r12 = r11 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r12 == 0) goto L_0x04b4
            com.google.android.appfunctions.schema.internal.dependencies.f0 r11 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r11
            int r11 = r11.r()
            int r8 = c0.C0086a.a(r11, r11, r8)
            goto L_0x04be
        L_0x04b4:
            java.lang.String r11 = (java.lang.String) r11
            int r11 = com.google.android.appfunctions.schema.internal.dependencies.Z.a(r11)
            int r8 = c0.C0086a.a(r11, r11, r8)
        L_0x04be:
            int r10 = r10 + 1
            goto L_0x049f
        L_0x04c1:
            r10 = 0
        L_0x04c2:
            if (r10 >= r7) goto L_0x03b7
            java.lang.Object r11 = r5.get(r10)
            boolean r12 = r11 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r12 == 0) goto L_0x04d7
            com.google.android.appfunctions.schema.internal.dependencies.f0 r11 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r11
            int r11 = r11.r()
            int r8 = c0.C0086a.a(r11, r11, r8)
            goto L_0x04e1
        L_0x04d7:
            java.lang.String r11 = (java.lang.String) r11
            int r11 = com.google.android.appfunctions.schema.internal.dependencies.Z.a(r11)
            int r8 = c0.C0086a.a(r11, r11, r8)
        L_0x04e1:
            int r10 = r10 + 1
            goto L_0x04c2
        L_0x04e4:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r5 = r5.size()
            if (r5 != 0) goto L_0x04f4
        L_0x04f2:
            r7 = 0
            goto L_0x04fc
        L_0x04f4:
            int r7 = r12 << 3
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r7 = r7 + r15
            int r7 = r7 * r5
        L_0x04fc:
            int r9 = r9 + r7
            goto L_0x071d
        L_0x04ff:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.a(r12, r5)
            goto L_0x0071
        L_0x050b:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.b(r12, r5)
            goto L_0x0071
        L_0x0517:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0527
            goto L_0x03a9
        L_0x0527:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.w(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            goto L_0x03b5
        L_0x0533:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x0543
            goto L_0x03a9
        L_0x0543:
            int r8 = r12 << 3
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.t(r5)
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            goto L_0x03b5
        L_0x054f:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            java.lang.Class r7 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r7 = r5.size()
            if (r7 != 0) goto L_0x055e
            goto L_0x04f2
        L_0x055e:
            int r7 = r12 << 3
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.N.s(r5)
            int r5 = r5.size()
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r7)
            int r7 = r7 * r5
            int r7 = r7 + r8
            goto L_0x04fc
        L_0x056f:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.a(r12, r5)
            goto L_0x0071
        L_0x057b:
            java.lang.Object r5 = r6.getObject(r1, r13)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.N.b(r12, r5)
            goto L_0x0071
        L_0x0587:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x071d
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r12 << 3
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r8 = r8 + r8
            int r5 = r5.a(r7)
            goto L_0x0070
        L_0x05a6:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            long r7 = r6.getLong(r1, r13)
            long r11 = r7 + r7
            long r7 = r7 >> r10
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            long r7 = r7 ^ r11
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
        L_0x05be:
            int r5 = r5 + r0
            int r9 = r9 + r5
        L_0x05c0:
            r0 = r16
            goto L_0x071d
        L_0x05c4:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            int r5 = r6.getInt(r1, r13)
            int r7 = r5 + r5
            int r5 = r5 >> 31
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            r5 = r5 ^ r7
            int r9 = c0.C0086a.a(r5, r0, r9)
            goto L_0x05c0
        L_0x05de:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r8, r9)
        L_0x05ea:
            r0 = r16
            r1 = r17
            goto L_0x071d
        L_0x05f0:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r7, r9)
            goto L_0x05ea
        L_0x05fd:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            int r5 = r6.getInt(r1, r13)
            long r7 = (long) r5
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x05be
        L_0x0613:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            int r5 = r6.getInt(r1, r13)
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r9 = c0.C0086a.a(r5, r0, r9)
            goto L_0x05c0
        L_0x0628:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.f0 r5 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r5
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = r5.r()
            int r9 = c0.C0086a.b(r5, r5, r0, r9)
            goto L_0x05c0
        L_0x0644:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x071d
            java.lang.Object r5 = r6.getObject(r1, r13)
            com.google.android.appfunctions.schema.internal.dependencies.M r7 = r0.w(r2)
            java.lang.Class r8 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            int r8 = r12 << 3
            com.google.android.appfunctions.schema.internal.dependencies.A r5 = (com.google.android.appfunctions.schema.internal.dependencies.A) r5
            int r8 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r8)
            int r5 = r5.a(r7)
            int r9 = c0.C0086a.b(r5, r5, r8, r9)
            goto L_0x071d
        L_0x0666:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            java.lang.Object r5 = r6.getObject(r1, r13)
            boolean r7 = r5 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r7 == 0) goto L_0x0686
            com.google.android.appfunctions.schema.internal.dependencies.f0 r5 = (com.google.android.appfunctions.schema.internal.dependencies.f0) r5
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = r5.r()
            int r9 = c0.C0086a.b(r5, r5, r0, r9)
            goto L_0x05c0
        L_0x0686:
            java.lang.String r5 = (java.lang.String) r5
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.Z.a(r5)
            int r9 = c0.C0086a.b(r5, r5, r0, r9)
            goto L_0x05c0
        L_0x0696:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r15, r9)
            goto L_0x05ea
        L_0x06a4:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r7, r9)
            goto L_0x05ea
        L_0x06b2:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r8, r9)
            goto L_0x05ea
        L_0x06c0:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            int r5 = r6.getInt(r1, r13)
            long r7 = (long) r5
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x05be
        L_0x06d7:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            long r7 = r6.getLong(r1, r13)
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x05be
        L_0x06ed:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05c0
            int r0 = r12 << 3
            long r7 = r6.getLong(r1, r13)
            int r0 = com.google.android.appfunctions.schema.internal.dependencies.h0.l0(r0)
            int r5 = com.google.android.appfunctions.schema.internal.dependencies.h0.m0(r7)
            goto L_0x05be
        L_0x0703:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x05ea
            int r0 = r12 << 3
            int r9 = c0.C0086a.a(r0, r7, r9)
            goto L_0x05ea
        L_0x0711:
            boolean r5 = r0.o(r1, r2, r3, r4, r5)
            if (r5 == 0) goto L_0x071d
            int r5 = r12 << 3
            int r9 = c0.C0086a.a(r5, r8, r9)
        L_0x071d:
            int r2 = r2 + 3
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000d
        L_0x0724:
            com.google.android.appfunctions.schema.internal.dependencies.P r0 = r0.f
            r0.getClass()
            com.google.android.appfunctions.schema.internal.dependencies.O r0 = r1.unknownFields
            int r0 = r0.b()
            int r0 = r0 + r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.b(com.google.android.appfunctions.schema.internal.dependencies.l):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r1 = r1 + ((int) (r2 ^ (r2 >>> 32)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d9, code lost:
        if (r2 != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00db, code lost:
        r6 = 1231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00dc, code lost:
        r1 = r6 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0182, code lost:
        r1 = r1 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ea, code lost:
        if (r2 != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        r1 = r2 + r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int c(com.google.android.appfunctions.schema.internal.dependencies.C0102l r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int[] r2 = r10.f1201a
            int r3 = r2.length
            if (r0 >= r3) goto L_0x023e
            int r3 = r10.i(r0)
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r3
            int r3 = j(r3)
            r2 = r2[r0]
            long r4 = (long) r4
            r6 = 1237(0x4d5, float:1.733E-42)
            r7 = 1231(0x4cf, float:1.725E-42)
            r8 = 37
            r9 = 32
            switch(r3) {
                case 0: goto L_0x022a;
                case 1: goto L_0x021c;
                case 2: goto L_0x0212;
                case 3: goto L_0x0208;
                case 4: goto L_0x0200;
                case 5: goto L_0x01f6;
                case 6: goto L_0x01ee;
                case 7: goto L_0x01e0;
                case 8: goto L_0x01d2;
                case 9: goto L_0x01c5;
                case 10: goto L_0x01b9;
                case 11: goto L_0x01b1;
                case 12: goto L_0x01a9;
                case 13: goto L_0x01a1;
                case 14: goto L_0x0197;
                case 15: goto L_0x018f;
                case 16: goto L_0x0185;
                case 17: goto L_0x0176;
                case 18: goto L_0x016a;
                case 19: goto L_0x016a;
                case 20: goto L_0x016a;
                case 21: goto L_0x016a;
                case 22: goto L_0x016a;
                case 23: goto L_0x016a;
                case 24: goto L_0x016a;
                case 25: goto L_0x016a;
                case 26: goto L_0x016a;
                case 27: goto L_0x016a;
                case 28: goto L_0x016a;
                case 29: goto L_0x016a;
                case 30: goto L_0x016a;
                case 31: goto L_0x016a;
                case 32: goto L_0x016a;
                case 33: goto L_0x016a;
                case 34: goto L_0x016a;
                case 35: goto L_0x016a;
                case 36: goto L_0x016a;
                case 37: goto L_0x016a;
                case 38: goto L_0x016a;
                case 39: goto L_0x016a;
                case 40: goto L_0x016a;
                case 41: goto L_0x016a;
                case 42: goto L_0x016a;
                case 43: goto L_0x016a;
                case 44: goto L_0x016a;
                case 45: goto L_0x016a;
                case 46: goto L_0x016a;
                case 47: goto L_0x016a;
                case 48: goto L_0x016a;
                case 49: goto L_0x016a;
                case 50: goto L_0x015e;
                case 51: goto L_0x0144;
                case 52: goto L_0x012c;
                case 53: goto L_0x011c;
                case 54: goto L_0x010c;
                case 55: goto L_0x00fe;
                case 56: goto L_0x00ee;
                case 57: goto L_0x00e0;
                case 58: goto L_0x00c5;
                case 59: goto L_0x00b1;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008f;
                case 62: goto L_0x0082;
                case 63: goto L_0x0075;
                case 64: goto L_0x0068;
                case 65: goto L_0x0059;
                case 66: goto L_0x004c;
                case 67: goto L_0x0037;
                case 68: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x023a
        L_0x0023:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
        L_0x0033:
            int r2 = r2 + r1
            r1 = r2
            goto L_0x023a
        L_0x0037:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            long r2 = m(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
        L_0x0045:
            long r4 = r2 >>> r9
            long r2 = r2 ^ r4
            int r2 = (int) r2
            int r1 = r1 + r2
            goto L_0x023a
        L_0x004c:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x0059:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            long r2 = m(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x0068:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x0075:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x0082:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x008f:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x00a0:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x00b1:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            java.lang.String r2 = (java.lang.String) r2
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x00c5:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            java.nio.charset.Charset r3 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            if (r2 == 0) goto L_0x00dc
        L_0x00db:
            r6 = r7
        L_0x00dc:
            int r6 = r6 + r1
            r1 = r6
            goto L_0x023a
        L_0x00e0:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x00ee:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            long r2 = m(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x00fe:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            int r2 = l(r11, r4)
            goto L_0x0033
        L_0x010c:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            long r2 = m(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x011c:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            long r2 = m(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x012c:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            int r2 = java.lang.Float.floatToIntBits(r2)
            goto L_0x0033
        L_0x0144:
            boolean r2 = r10.r(r2, r0, r11)
            if (r2 == 0) goto L_0x023a
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            java.lang.Double r2 = (java.lang.Double) r2
            double r2 = r2.doubleValue()
            long r2 = java.lang.Double.doubleToLongBits(r2)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x015e:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x016a:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x0176:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            if (r2 == 0) goto L_0x0182
            int r8 = r2.hashCode()
        L_0x0182:
            int r1 = r1 + r8
            goto L_0x023a
        L_0x0185:
            int r1 = r1 * 53
            long r2 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x018f:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x0197:
            int r1 = r1 * 53
            long r2 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x01a1:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x01a9:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x01b1:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x01b9:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x01c5:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            if (r2 == 0) goto L_0x0182
            int r8 = r2.hashCode()
            goto L_0x0182
        L_0x01d2:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r11, r4)
            java.lang.String r2 = (java.lang.String) r2
            int r2 = r2.hashCode()
            goto L_0x0033
        L_0x01e0:
            int r1 = r1 * 53
            com.google.android.appfunctions.schema.internal.dependencies.V r2 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            boolean r2 = r2.b(r11, r4)
            java.nio.charset.Charset r3 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            if (r2 == 0) goto L_0x00dc
            goto L_0x00db
        L_0x01ee:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x01f6:
            int r1 = r1 * 53
            long r2 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x0200:
            int r1 = r1 * 53
            int r2 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r11, r4)
            goto L_0x0033
        L_0x0208:
            int r1 = r1 * 53
            long r2 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x0212:
            int r1 = r1 * 53
            long r2 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r11, r4)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x021c:
            int r1 = r1 * 53
            com.google.android.appfunctions.schema.internal.dependencies.V r2 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            float r2 = r2.d(r11, r4)
            int r2 = java.lang.Float.floatToIntBits(r2)
            goto L_0x0033
        L_0x022a:
            int r1 = r1 * 53
            com.google.android.appfunctions.schema.internal.dependencies.V r2 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            double r2 = r2.f(r11, r4)
            long r2 = java.lang.Double.doubleToLongBits(r2)
            java.nio.charset.Charset r4 = com.google.android.appfunctions.schema.internal.dependencies.C0106p.f1226a
            goto L_0x0045
        L_0x023a:
            int r0 = r0 + 3
            goto L_0x0002
        L_0x023e:
            int r1 = r1 * 53
            com.google.android.appfunctions.schema.internal.dependencies.P r10 = r10.f
            r10.getClass()
            com.google.android.appfunctions.schema.internal.dependencies.O r10 = r11.unknownFields
            int r10 = r10.hashCode()
            int r10 = r10 + r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.c(com.google.android.appfunctions.schema.internal.dependencies.l):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(java.lang.Object r13, java.lang.Object r14) {
        /*
            r12 = this;
            boolean r0 = k(r13)
            if (r0 == 0) goto L_0x01e3
            r14.getClass()
            r0 = 0
        L_0x000a:
            int[] r1 = r12.f1201a
            int r2 = r1.length
            if (r0 >= r2) goto L_0x01dc
            int r2 = r12.i(r0)
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r2 & r3
            int r2 = j(r2)
            r5 = r1[r0]
            long r8 = (long) r4
            switch(r2) {
                case 0: goto L_0x01c4;
                case 1: goto L_0x01b0;
                case 2: goto L_0x019e;
                case 3: goto L_0x018c;
                case 4: goto L_0x017a;
                case 5: goto L_0x0168;
                case 6: goto L_0x0156;
                case 7: goto L_0x0142;
                case 8: goto L_0x0130;
                case 9: goto L_0x012b;
                case 10: goto L_0x0119;
                case 11: goto L_0x0107;
                case 12: goto L_0x00f5;
                case 13: goto L_0x00e3;
                case 14: goto L_0x00d1;
                case 15: goto L_0x00bf;
                case 16: goto L_0x00ad;
                case 17: goto L_0x00a8;
                case 18: goto L_0x0076;
                case 19: goto L_0x0076;
                case 20: goto L_0x0076;
                case 21: goto L_0x0076;
                case 22: goto L_0x0076;
                case 23: goto L_0x0076;
                case 24: goto L_0x0076;
                case 25: goto L_0x0076;
                case 26: goto L_0x0076;
                case 27: goto L_0x0076;
                case 28: goto L_0x0076;
                case 29: goto L_0x0076;
                case 30: goto L_0x0076;
                case 31: goto L_0x0076;
                case 32: goto L_0x0076;
                case 33: goto L_0x0076;
                case 34: goto L_0x0076;
                case 35: goto L_0x0076;
                case 36: goto L_0x0076;
                case 37: goto L_0x0076;
                case 38: goto L_0x0076;
                case 39: goto L_0x0076;
                case 40: goto L_0x0076;
                case 41: goto L_0x0076;
                case 42: goto L_0x0076;
                case 43: goto L_0x0076;
                case 44: goto L_0x0076;
                case 45: goto L_0x0076;
                case 46: goto L_0x0076;
                case 47: goto L_0x0076;
                case 48: goto L_0x0076;
                case 49: goto L_0x0076;
                case 50: goto L_0x005b;
                case 51: goto L_0x0044;
                case 52: goto L_0x0044;
                case 53: goto L_0x0044;
                case 54: goto L_0x0044;
                case 55: goto L_0x0044;
                case 56: goto L_0x0044;
                case 57: goto L_0x0044;
                case 58: goto L_0x0044;
                case 59: goto L_0x0044;
                case 60: goto L_0x0040;
                case 61: goto L_0x0029;
                case 62: goto L_0x0029;
                case 63: goto L_0x0029;
                case 64: goto L_0x0029;
                case 65: goto L_0x0029;
                case 66: goto L_0x0029;
                case 67: goto L_0x0029;
                case 68: goto L_0x0025;
                default: goto L_0x0022;
            }
        L_0x0022:
            r7 = r13
            goto L_0x01d7
        L_0x0025:
            r12.v(r0, r13, r14)
            goto L_0x0022
        L_0x0029:
            boolean r2 = r12.r(r5, r0, r14)
            if (r2 == 0) goto L_0x0022
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.o(r8, r13, r2)
            int r2 = r0 + 2
            r1 = r1[r2]
            r1 = r1 & r3
            long r1 = (long) r1
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r5, r1, r13)
            goto L_0x0022
        L_0x0040:
            r12.v(r0, r13, r14)
            goto L_0x0022
        L_0x0044:
            boolean r2 = r12.r(r5, r0, r14)
            if (r2 == 0) goto L_0x0022
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.o(r8, r13, r2)
            int r2 = r0 + 2
            r1 = r1[r2]
            r1 = r1 & r3
            long r1 = (long) r1
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r5, r1, r13)
            goto L_0x0022
        L_0x005b:
            java.lang.Class r12 = com.google.android.appfunctions.schema.internal.dependencies.N.f1211a
            java.lang.Object r12 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r13, r8)
            java.lang.Object r13 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            if (r12 != 0) goto L_0x0070
            r13.getClass()
            java.lang.ClassCastException r12 = new java.lang.ClassCastException
            r12.<init>()
            throw r12
        L_0x0070:
            java.lang.ClassCastException r12 = new java.lang.ClassCastException
            r12.<init>()
            throw r12
        L_0x0076:
            java.lang.Object r1 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r13, r8)
            com.google.android.appfunctions.schema.internal.dependencies.o r1 = (com.google.android.appfunctions.schema.internal.dependencies.C0105o) r1
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.o r2 = (com.google.android.appfunctions.schema.internal.dependencies.C0105o) r2
            int r3 = r1.size()
            int r4 = r2.size()
            if (r3 <= 0) goto L_0x009f
            if (r4 <= 0) goto L_0x009f
            r5 = r1
            com.google.android.appfunctions.schema.internal.dependencies.a0 r5 = (com.google.android.appfunctions.schema.internal.dependencies.a0) r5
            boolean r5 = r5.d
            if (r5 != 0) goto L_0x009c
            int r4 = r4 + r3
            com.google.android.appfunctions.schema.internal.dependencies.K r1 = (com.google.android.appfunctions.schema.internal.dependencies.K) r1
            com.google.android.appfunctions.schema.internal.dependencies.K r1 = r1.q(r4)
        L_0x009c:
            r1.addAll(r2)
        L_0x009f:
            if (r3 > 0) goto L_0x00a2
            goto L_0x00a3
        L_0x00a2:
            r2 = r1
        L_0x00a3:
            com.google.android.appfunctions.schema.internal.dependencies.W.o(r8, r13, r2)
            goto L_0x0022
        L_0x00a8:
            r12.u(r0, r13, r14)
            goto L_0x0022
        L_0x00ad:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            long r1 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.m(r13, r8, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x00bf:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x00d1:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            long r1 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.m(r13, r8, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x00e3:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x00f5:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x0107:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x0119:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.o(r8, r13, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x012b:
            r12.u(r0, r13, r14)
            goto L_0x0022
        L_0x0130:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.o(r8, r13, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x0142:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            com.google.android.appfunctions.schema.internal.dependencies.V r1 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            boolean r2 = r1.b(r14, r8)
            r1.c(r13, r8, r2)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x0156:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x0168:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            long r1 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.m(r13, r8, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x017a:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            int r1 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.k(r1, r8, r13)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x018c:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            long r1 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.m(r13, r8, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x019e:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            long r1 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r14, r8)
            com.google.android.appfunctions.schema.internal.dependencies.W.m(r13, r8, r1)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x01b0:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            com.google.android.appfunctions.schema.internal.dependencies.V r1 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            float r2 = r1.d(r14, r8)
            r1.e(r13, r8, r2)
            r12.q(r0, r13)
            goto L_0x0022
        L_0x01c4:
            boolean r1 = r12.p(r0, r14)
            if (r1 == 0) goto L_0x0022
            com.google.android.appfunctions.schema.internal.dependencies.V r6 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            double r10 = r6.f(r14, r8)
            r7 = r13
            r6.g(r7, r8, r10)
            r12.q(r0, r7)
        L_0x01d7:
            int r0 = r0 + 3
            r13 = r7
            goto L_0x000a
        L_0x01dc:
            r7 = r13
            com.google.android.appfunctions.schema.internal.dependencies.P r12 = r12.f
            com.google.android.appfunctions.schema.internal.dependencies.N.d(r12, r7, r14)
            return
        L_0x01e3:
            r7 = r13
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = java.lang.String.valueOf(r7)
            java.lang.String r14 = "Mutating immutable message: "
            java.lang.String r13 = r14.concat(r13)
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.d(java.lang.Object, java.lang.Object):void");
    }

    public final boolean e(Object obj) {
        int i2;
        int i7;
        int i8;
        int i10 = 0;
        int i11 = 0;
        int i12 = 1048575;
        while (i11 < this.e) {
            int i13 = this.d[i11];
            int[] iArr = this.f1201a;
            int i14 = iArr[i13];
            int i15 = i(i13);
            int i16 = iArr[i13 + 2];
            int i17 = i16 & 1048575;
            int i18 = 1 << (i16 >>> 20);
            if (i17 != i12) {
                if (i17 != 1048575) {
                    i10 = f1200h.getInt(obj, (long) i17);
                }
                i8 = i13;
                i2 = i10;
                i7 = i17;
            } else {
                int i19 = i10;
                i7 = i12;
                i8 = i13;
                i2 = i19;
            }
            if ((268435456 & i15) == 0 || o(obj, i8, i7, i2, i18)) {
                int j2 = j(i15);
                if (j2 != 9 && j2 != 17) {
                    if (j2 != 27) {
                        if (j2 == 60 || j2 == 68) {
                            if (r(i14, i8, obj) && !w(i8).e(W.n(obj, (long) (i15 & 1048575)))) {
                            }
                        } else if (j2 != 49) {
                            if (j2 == 50) {
                                W.n(obj, (long) (i15 & 1048575)).getClass();
                                throw new ClassCastException();
                            }
                        }
                    }
                    List list = (List) W.n(obj, (long) (i15 & 1048575));
                    if (!list.isEmpty()) {
                        M w = w(i8);
                        int i20 = 0;
                        while (i20 < list.size()) {
                            if (w.e(list.get(i20))) {
                                i20++;
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                } else if (o(obj, i8, i7, i2, i18) && !w(i8).e(W.n(obj, (long) (i15 & 1048575)))) {
                }
                i11++;
                i12 = i7;
                i10 = i2;
            }
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0085, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = k(r8)
            if (r0 != 0) goto L_0x0008
            goto L_0x0097
        L_0x0008:
            boolean r0 = r8 instanceof com.google.android.appfunctions.schema.internal.dependencies.C0102l
            r1 = 0
            if (r0 == 0) goto L_0x001b
            r0 = r8
            com.google.android.appfunctions.schema.internal.dependencies.l r0 = (com.google.android.appfunctions.schema.internal.dependencies.C0102l) r0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r0.h(r2)
            r0.memoizedHashCode = r1
            r0.f()
        L_0x001b:
            r0 = r1
        L_0x001c:
            int[] r2 = r7.f1201a
            int r3 = r2.length
            if (r0 >= r3) goto L_0x0088
            int r3 = r7.i(r0)
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r3
            int r3 = j(r3)
            long r4 = (long) r4
            r6 = 9
            if (r3 == r6) goto L_0x0072
            r6 = 60
            if (r3 == r6) goto L_0x005c
            r6 = 68
            if (r3 == r6) goto L_0x005c
            switch(r3) {
                case 17: goto L_0x0072;
                case 18: goto L_0x004d;
                case 19: goto L_0x004d;
                case 20: goto L_0x004d;
                case 21: goto L_0x004d;
                case 22: goto L_0x004d;
                case 23: goto L_0x004d;
                case 24: goto L_0x004d;
                case 25: goto L_0x004d;
                case 26: goto L_0x004d;
                case 27: goto L_0x004d;
                case 28: goto L_0x004d;
                case 29: goto L_0x004d;
                case 30: goto L_0x004d;
                case 31: goto L_0x004d;
                case 32: goto L_0x004d;
                case 33: goto L_0x004d;
                case 34: goto L_0x004d;
                case 35: goto L_0x004d;
                case 36: goto L_0x004d;
                case 37: goto L_0x004d;
                case 38: goto L_0x004d;
                case 39: goto L_0x004d;
                case 40: goto L_0x004d;
                case 41: goto L_0x004d;
                case 42: goto L_0x004d;
                case 43: goto L_0x004d;
                case 44: goto L_0x004d;
                case 45: goto L_0x004d;
                case 46: goto L_0x004d;
                case 47: goto L_0x004d;
                case 48: goto L_0x004d;
                case 49: goto L_0x004d;
                case 50: goto L_0x003e;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x0085
        L_0x003e:
            sun.misc.Unsafe r2 = f1200h
            java.lang.Object r2 = r2.getObject(r8, r4)
            if (r2 != 0) goto L_0x0047
            goto L_0x0085
        L_0x0047:
            java.lang.ClassCastException r7 = new java.lang.ClassCastException
            r7.<init>()
            throw r7
        L_0x004d:
            java.lang.Object r2 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r8, r4)
            com.google.android.appfunctions.schema.internal.dependencies.o r2 = (com.google.android.appfunctions.schema.internal.dependencies.C0105o) r2
            com.google.android.appfunctions.schema.internal.dependencies.a0 r2 = (com.google.android.appfunctions.schema.internal.dependencies.a0) r2
            boolean r3 = r2.d
            if (r3 == 0) goto L_0x0085
            r2.d = r1
            goto L_0x0085
        L_0x005c:
            r2 = r2[r0]
            boolean r2 = r7.r(r2, r0, r8)
            if (r2 == 0) goto L_0x0085
            com.google.android.appfunctions.schema.internal.dependencies.M r2 = r7.w(r0)
            sun.misc.Unsafe r3 = f1200h
            java.lang.Object r3 = r3.getObject(r8, r4)
            r2.f(r3)
            goto L_0x0085
        L_0x0072:
            boolean r2 = r7.p(r0, r8)
            if (r2 == 0) goto L_0x0085
            com.google.android.appfunctions.schema.internal.dependencies.M r2 = r7.w(r0)
            sun.misc.Unsafe r3 = f1200h
            java.lang.Object r3 = r3.getObject(r8, r4)
            r2.f(r3)
        L_0x0085:
            int r0 = r0 + 3
            goto L_0x001c
        L_0x0088:
            com.google.android.appfunctions.schema.internal.dependencies.P r7 = r7.f
            r7.getClass()
            com.google.android.appfunctions.schema.internal.dependencies.l r8 = (com.google.android.appfunctions.schema.internal.dependencies.C0102l) r8
            com.google.android.appfunctions.schema.internal.dependencies.O r7 = r8.unknownFields
            boolean r8 = r7.e
            if (r8 == 0) goto L_0x0097
            r7.e = r1
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.f(java.lang.Object):void");
    }

    public final boolean g(C0102l lVar, C0102l lVar2) {
        boolean z;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f1201a;
            if (i2 < iArr.length) {
                int i7 = i(i2);
                long j2 = (long) (i7 & 1048575);
                switch (j(i7)) {
                    case 0:
                        if (!n(lVar, lVar2, i2)) {
                            break;
                        } else {
                            V v = W.f1217c;
                            if (Double.doubleToLongBits(v.f(lVar, j2)) != Double.doubleToLongBits(v.f(lVar2, j2))) {
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 1:
                        if (!n(lVar, lVar2, i2)) {
                            break;
                        } else {
                            V v6 = W.f1217c;
                            if (Float.floatToIntBits(v6.d(lVar, j2)) != Float.floatToIntBits(v6.d(lVar2, j2))) {
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 2:
                        if (n(lVar, lVar2, i2) && W.l(lVar, j2) == W.l(lVar2, j2)) {
                            continue;
                        }
                    case 3:
                        if (n(lVar, lVar2, i2) && W.l(lVar, j2) == W.l(lVar2, j2)) {
                            continue;
                        }
                    case 4:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 5:
                        if (n(lVar, lVar2, i2) && W.l(lVar, j2) == W.l(lVar2, j2)) {
                            continue;
                        }
                    case 6:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 7:
                        if (!n(lVar, lVar2, i2)) {
                            break;
                        } else {
                            V v8 = W.f1217c;
                            if (v8.b(lVar, j2) != v8.b(lVar2, j2)) {
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 8:
                        if (n(lVar, lVar2, i2) && N.c(W.n(lVar, j2), W.n(lVar2, j2))) {
                            continue;
                        }
                    case 9:
                        if (n(lVar, lVar2, i2) && N.c(W.n(lVar, j2), W.n(lVar2, j2))) {
                            continue;
                        }
                    case 10:
                        if (n(lVar, lVar2, i2) && N.c(W.n(lVar, j2), W.n(lVar2, j2))) {
                            continue;
                        }
                    case 11:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 12:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 13:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 14:
                        if (n(lVar, lVar2, i2) && W.l(lVar, j2) == W.l(lVar2, j2)) {
                            continue;
                        }
                    case 15:
                        if (n(lVar, lVar2, i2) && W.j(lVar, j2) == W.j(lVar2, j2)) {
                            continue;
                        }
                    case 16:
                        if (n(lVar, lVar2, i2) && W.l(lVar, j2) == W.l(lVar2, j2)) {
                            continue;
                        }
                    case 17:
                        if (n(lVar, lVar2, i2) && N.c(W.n(lVar, j2), W.n(lVar2, j2))) {
                            continue;
                        }
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
                        z = N.c(W.n(lVar, j2), W.n(lVar2, j2));
                        break;
                    case 50:
                        z = N.c(W.n(lVar, j2), W.n(lVar2, j2));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long j3 = (long) (iArr[i2 + 2] & 1048575);
                        if (W.j(lVar, j3) == W.j(lVar2, j3) && N.c(W.n(lVar, j2), W.n(lVar2, j2))) {
                            continue;
                        }
                }
                if (z) {
                    i2 += 3;
                }
            } else {
                this.f.getClass();
                if (!lVar.unknownFields.equals(lVar2.unknownFields)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final C0102l h() {
        return (C0102l) ((C0102l) this.f1202c).b(C0101k.zzd);
    }

    public final int i(int i2) {
        return this.f1201a[i2 + 1];
    }

    public final boolean n(C0102l lVar, C0102l lVar2, int i2) {
        if (p(i2, lVar) == p(i2, lVar2)) {
            return true;
        }
        return false;
    }

    public final boolean o(Object obj, int i2, int i7, int i8, int i10) {
        if (i7 == 1048575) {
            return p(i2, obj);
        }
        if ((i8 & i10) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f8 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean p(int r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r7 + 2
            int[] r1 = r6.f1201a
            r0 = r1[r0]
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r0 & r1
            long r2 = (long) r2
            r4 = 1048575(0xfffff, double:5.18065E-318)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r5 = 1
            if (r4 != 0) goto L_0x00ed
            int r6 = r6.i(r7)
            r7 = r6 & r1
            int r6 = j(r6)
            long r0 = (long) r7
            r2 = 0
            switch(r6) {
                case 0: goto L_0x00de;
                case 1: goto L_0x00d1;
                case 2: goto L_0x00c8;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00b8;
                case 5: goto L_0x00af;
                case 6: goto L_0x00a8;
                case 7: goto L_0x00a1;
                case 8: goto L_0x007c;
                case 9: goto L_0x0074;
                case 10: goto L_0x0066;
                case 11: goto L_0x005e;
                case 12: goto L_0x0056;
                case 13: goto L_0x004e;
                case 14: goto L_0x0044;
                case 15: goto L_0x003c;
                case 16: goto L_0x0032;
                case 17: goto L_0x002a;
                default: goto L_0x0024;
            }
        L_0x0024:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L_0x002a:
            java.lang.Object r6 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x0032:
            long r6 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x003c:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x0044:
            long r6 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x004e:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x0056:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x005e:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x0066:
            com.google.android.appfunctions.schema.internal.dependencies.g0 r6 = com.google.android.appfunctions.schema.internal.dependencies.f0.e
            java.lang.Object r7 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r8, r0)
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00f9
            goto L_0x00f8
        L_0x0074:
            java.lang.Object r6 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x007c:
            java.lang.Object r6 = com.google.android.appfunctions.schema.internal.dependencies.W.n(r8, r0)
            boolean r7 = r6 instanceof java.lang.String
            if (r7 == 0) goto L_0x008e
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x00f9
            goto L_0x00f8
        L_0x008e:
            boolean r7 = r6 instanceof com.google.android.appfunctions.schema.internal.dependencies.f0
            if (r7 == 0) goto L_0x009b
            com.google.android.appfunctions.schema.internal.dependencies.g0 r7 = com.google.android.appfunctions.schema.internal.dependencies.f0.e
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L_0x00f9
            goto L_0x00f8
        L_0x009b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L_0x00a1:
            com.google.android.appfunctions.schema.internal.dependencies.V r6 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            boolean r6 = r6.b(r8, r0)
            return r6
        L_0x00a8:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00af:
            long r6 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00b8:
            int r6 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r0)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00bf:
            long r6 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00c8:
            long r6 = com.google.android.appfunctions.schema.internal.dependencies.W.l(r8, r0)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00d1:
            com.google.android.appfunctions.schema.internal.dependencies.V r6 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            float r6 = r6.d(r8, r0)
            int r6 = java.lang.Float.floatToRawIntBits(r6)
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00de:
            com.google.android.appfunctions.schema.internal.dependencies.V r6 = com.google.android.appfunctions.schema.internal.dependencies.W.f1217c
            double r6 = r6.f(r8, r0)
            long r6 = java.lang.Double.doubleToRawLongBits(r6)
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x00f9
            goto L_0x00f8
        L_0x00ed:
            int r6 = r0 >>> 20
            int r6 = r5 << r6
            int r7 = com.google.android.appfunctions.schema.internal.dependencies.W.j(r8, r2)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x00f9
        L_0x00f8:
            return r5
        L_0x00f9:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.appfunctions.schema.internal.dependencies.E.p(int, java.lang.Object):boolean");
    }

    public final void q(int i2, Object obj) {
        int i7 = this.f1201a[i2 + 2];
        long j2 = (long) (1048575 & i7);
        if (j2 != 1048575) {
            W.k((1 << (i7 >>> 20)) | W.j(obj, j2), j2, obj);
        }
    }

    public final boolean r(int i2, int i7, Object obj) {
        if (W.j(obj, (long) (this.f1201a[i7 + 2] & 1048575)) == i2) {
            return true;
        }
        return false;
    }

    public final void u(int i2, Object obj, Object obj2) {
        if (p(i2, obj2)) {
            Unsafe unsafe = f1200h;
            long i7 = (long) (i(i2) & 1048575);
            Object object = unsafe.getObject(obj2, i7);
            if (object != null) {
                M w = w(i2);
                if (!p(i2, obj)) {
                    if (!k(object)) {
                        unsafe.putObject(obj, i7, object);
                    } else {
                        C0102l h5 = w.h();
                        w.d(h5, object);
                        unsafe.putObject(obj, i7, h5);
                    }
                    q(i2, obj);
                    return;
                }
                Object object2 = unsafe.getObject(obj, i7);
                if (!k(object2)) {
                    C0102l h6 = w.h();
                    w.d(h6, object2);
                    unsafe.putObject(obj, i7, h6);
                    object2 = h6;
                }
                w.d(object2, object);
                return;
            }
            int i8 = this.f1201a[i2];
            String obj3 = obj2.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(i8).length() + 38 + obj3.length());
            sb2.append("Source subfield ");
            sb2.append(i8);
            sb2.append(" is present but null: ");
            sb2.append(obj3);
            throw new IllegalStateException(sb2.toString());
        }
    }

    public final void v(int i2, Object obj, Object obj2) {
        int[] iArr = this.f1201a;
        int i7 = iArr[i2];
        if (r(i7, i2, obj2)) {
            Unsafe unsafe = f1200h;
            long i8 = (long) (i(i2) & 1048575);
            Object object = unsafe.getObject(obj2, i8);
            if (object != null) {
                M w = w(i2);
                if (!r(i7, i2, obj)) {
                    if (!k(object)) {
                        unsafe.putObject(obj, i8, object);
                    } else {
                        C0102l h5 = w.h();
                        w.d(h5, object);
                        unsafe.putObject(obj, i8, h5);
                    }
                    W.k(i7, (long) (iArr[i2 + 2] & 1048575), obj);
                    return;
                }
                Object object2 = unsafe.getObject(obj, i8);
                if (!k(object2)) {
                    C0102l h6 = w.h();
                    w.d(h6, object2);
                    unsafe.putObject(obj, i8, h6);
                    object2 = h6;
                }
                w.d(object2, object);
                return;
            }
            int i10 = iArr[i2];
            String obj3 = obj2.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 38 + obj3.length());
            sb2.append("Source subfield ");
            sb2.append(i10);
            sb2.append(" is present but null: ");
            sb2.append(obj3);
            throw new IllegalStateException(sb2.toString());
        }
    }

    public final M w(int i2) {
        int i7 = i2 / 3;
        int i8 = i7 + i7;
        Object[] objArr = this.b;
        M m = (M) objArr[i8];
        if (m != null) {
            return m;
        }
        M a7 = J.f1206c.a((Class) objArr[i8 + 1]);
        objArr[i8] = a7;
        return a7;
    }
}
