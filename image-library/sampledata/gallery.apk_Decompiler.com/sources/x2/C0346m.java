package x2;

import K0.b;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* renamed from: x2.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0346m {

    /* renamed from: a  reason: collision with root package name */
    public final b[] f2128a = new b[4];
    public final Matrix[] b = new Matrix[4];

    /* renamed from: c  reason: collision with root package name */
    public final Matrix[] f2129c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final b g = new b();

    /* renamed from: h  reason: collision with root package name */
    public final float[] f2130h = new float[2];

    /* renamed from: i  reason: collision with root package name */
    public final float[] f2131i = new float[2];

    /* renamed from: j  reason: collision with root package name */
    public final Path f2132j = new Path();
    public final Path k = new Path();
    public final boolean l = true;

    public C0346m() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f2128a[i2] = new b();
            this.b[i2] = new Matrix();
            this.f2129c[i2] = new Matrix();
        }
    }

    /* JADX WARNING: type inference failed for: r16v0 */
    /* JADX WARNING: type inference failed for: r16v1 */
    /* JADX WARNING: type inference failed for: r16v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(x2.C0344k r22, float r23, android.graphics.RectF r24, G0.e r25, android.graphics.Path r26) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r24
            r3 = r25
            r4 = r26
            r4.rewind()
            android.graphics.Path r5 = r0.e
            r5.rewind()
            android.graphics.Path r6 = r0.f
            r6.rewind()
            android.graphics.Path$Direction r7 = android.graphics.Path.Direction.CW
            r6.addRect(r2, r7)
            r8 = 0
        L_0x001d:
            android.graphics.Matrix[] r9 = r0.f2129c
            r10 = 2
            r11 = 3
            float[] r12 = r0.f2130h
            r13 = 4
            K0.b[] r14 = r0.f2128a
            android.graphics.Matrix[] r15 = r0.b
            r16 = 0
            r7 = 1
            if (r8 >= r13) goto L_0x00d0
            if (r8 == r7) goto L_0x003c
            if (r8 == r10) goto L_0x0039
            if (r8 == r11) goto L_0x0036
            x2.c r13 = r1.f
            goto L_0x003e
        L_0x0036:
            x2.c r13 = r1.e
            goto L_0x003e
        L_0x0039:
            x2.c r13 = r1.f2124h
            goto L_0x003e
        L_0x003c:
            x2.c r13 = r1.g
        L_0x003e:
            if (r8 == r7) goto L_0x004d
            if (r8 == r10) goto L_0x004a
            if (r8 == r11) goto L_0x0047
            o1.a r11 = r1.b
            goto L_0x004f
        L_0x0047:
            o1.a r11 = r1.f2122a
            goto L_0x004f
        L_0x004a:
            o1.a r11 = r1.d
            goto L_0x004f
        L_0x004d:
            o1.a r11 = r1.f2123c
        L_0x004f:
            r10 = r14[r8]
            r11.getClass()
            float r13 = r13.a(r2)
            r7 = r23
            r11.X(r10, r7, r13)
            int r10 = r8 + 1
            int r11 = r10 % 4
            int r11 = r11 * 90
            float r11 = (float) r11
            r13 = r15[r8]
            r13.reset()
            android.graphics.PointF r13 = r0.d
            r7 = 1
            if (r8 == r7) goto L_0x0092
            r7 = 2
            if (r8 == r7) goto L_0x0088
            r7 = 3
            if (r8 == r7) goto L_0x007e
            float r7 = r2.right
            r17 = r8
            float r8 = r2.top
            r13.set(r7, r8)
            goto L_0x009b
        L_0x007e:
            r17 = r8
            float r7 = r2.left
            float r8 = r2.top
            r13.set(r7, r8)
            goto L_0x009b
        L_0x0088:
            r17 = r8
            float r7 = r2.left
            float r8 = r2.bottom
            r13.set(r7, r8)
            goto L_0x009b
        L_0x0092:
            r17 = r8
            float r7 = r2.right
            float r8 = r2.bottom
            r13.set(r7, r8)
        L_0x009b:
            r7 = r15[r17]
            float r8 = r13.x
            float r13 = r13.y
            r7.setTranslate(r8, r13)
            r7 = r15[r17]
            r7.preRotate(r11)
            r7 = r14[r17]
            float r8 = r7.b
            r12[r16] = r8
            float r7 = r7.f377c
            r18 = 1
            r12[r18] = r7
            r7 = r15[r17]
            r7.mapPoints(r12)
            r7 = r9[r17]
            r7.reset()
            r7 = r9[r17]
            r8 = r12[r16]
            r12 = r12[r18]
            r7.setTranslate(r8, r12)
            r7 = r9[r17]
            r7.preRotate(r11)
            r8 = r10
            goto L_0x001d
        L_0x00d0:
            r7 = r16
        L_0x00d2:
            if (r7 >= r13) goto L_0x0251
            r8 = r14[r7]
            r8.getClass()
            r10 = 0
            r12[r16] = r10
            float r8 = r8.f376a
            r18 = 1
            r12[r18] = r8
            r8 = r15[r7]
            r8.mapPoints(r12)
            if (r7 != 0) goto L_0x00f1
            r8 = r12[r16]
            r11 = r12[r18]
            r4.moveTo(r8, r11)
            goto L_0x00f8
        L_0x00f1:
            r8 = r12[r16]
            r11 = r12[r18]
            r4.lineTo(r8, r11)
        L_0x00f8:
            r8 = r14[r7]
            r11 = r15[r7]
            r8.b(r11, r4)
            if (r3 == 0) goto L_0x0132
            r8 = r14[r7]
            r11 = r15[r7]
            java.lang.Object r13 = r3.d
            x2.g r13 = (x2.C0340g) r13
            r23 = r10
            java.util.BitSet r10 = r13.g
            r8.getClass()
            r2 = r16
            r10.set(r7, r2)
            x2.s[] r2 = r13.e
            float r10 = r8.e
            r8.a(r10)
            android.graphics.Matrix r10 = new android.graphics.Matrix
            r10.<init>(r11)
            java.util.ArrayList r11 = new java.util.ArrayList
            java.lang.Object r8 = r8.g
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            r11.<init>(r8)
            x2.n r8 = new x2.n
            r8.<init>(r11, r10)
            r2[r7] = r8
            goto L_0x0134
        L_0x0132:
            r23 = r10
        L_0x0134:
            int r2 = r7 + 1
            int r8 = r2 % 4
            r10 = r14[r7]
            float r11 = r10.b
            r16 = 0
            r12[r16] = r11
            float r10 = r10.f377c
            r18 = 1
            r12[r18] = r10
            r10 = r15[r7]
            r10.mapPoints(r12)
            r10 = r14[r8]
            r10.getClass()
            float[] r11 = r0.f2131i
            r11[r16] = r23
            float r10 = r10.f376a
            r11[r18] = r10
            r10 = r15[r8]
            r10.mapPoints(r11)
            r10 = r12[r16]
            r13 = r11[r16]
            float r10 = r10 - r13
            r13 = r9
            double r9 = (double) r10
            r19 = r12[r18]
            r11 = r11[r18]
            float r11 = r19 - r11
            r20 = r13
            r19 = r14
            double r13 = (double) r11
            double r9 = java.lang.Math.hypot(r9, r13)
            float r9 = (float) r9
            r10 = 981668463(0x3a83126f, float:0.001)
            float r9 = r9 - r10
            r10 = r23
            float r9 = java.lang.Math.max(r9, r10)
            r10 = r19[r7]
            float r11 = r10.b
            r16 = 0
            r12[r16] = r11
            float r10 = r10.f377c
            r11 = 1
            r12[r11] = r10
            r10 = r15[r7]
            r10.mapPoints(r12)
            if (r7 == r11) goto L_0x01a0
            r10 = 3
            if (r7 == r10) goto L_0x01a0
            float r10 = r24.centerY()
            r13 = r12[r11]
            float r10 = r10 - r13
            java.lang.Math.abs(r10)
            goto L_0x01ac
        L_0x01a0:
            float r10 = r24.centerX()
            r16 = 0
            r11 = r12[r16]
            float r10 = r10 - r11
            java.lang.Math.abs(r10)
        L_0x01ac:
            r10 = 1132920832(0x43870000, float:270.0)
            K0.b r11 = r0.g
            r13 = 0
            r11.d(r13, r10, r13)
            r10 = 1
            if (r7 == r10) goto L_0x01c7
            r10 = 2
            if (r7 == r10) goto L_0x01c3
            r13 = 3
            if (r7 == r13) goto L_0x01c0
            x2.e r14 = r1.f2126j
            goto L_0x01cb
        L_0x01c0:
            x2.e r14 = r1.f2125i
            goto L_0x01cb
        L_0x01c3:
            r13 = 3
            x2.e r14 = r1.l
            goto L_0x01cb
        L_0x01c7:
            r10 = 2
            r13 = 3
            x2.e r14 = r1.k
        L_0x01cb:
            r14.getClass()
            r14 = 0
            r11.c(r9, r14)
            android.graphics.Path r9 = r0.f2132j
            r9.reset()
            r14 = r20[r7]
            r11.b(r14, r9)
            boolean r14 = r0.l
            if (r14 == 0) goto L_0x01ed
            boolean r14 = r0.b(r9, r7)
            if (r14 != 0) goto L_0x01f0
            boolean r8 = r0.b(r9, r8)
            if (r8 == 0) goto L_0x01ed
            goto L_0x01f0
        L_0x01ed:
            r18 = 1
            goto L_0x0212
        L_0x01f0:
            android.graphics.Path$Op r8 = android.graphics.Path.Op.DIFFERENCE
            r9.op(r9, r6, r8)
            r14 = 0
            r16 = 0
            r12[r16] = r14
            float r8 = r11.f376a
            r18 = 1
            r12[r18] = r8
            r8 = r20[r7]
            r8.mapPoints(r12)
            r8 = r12[r16]
            r9 = r12[r18]
            r5.moveTo(r8, r9)
            r8 = r20[r7]
            r11.b(r8, r5)
            goto L_0x0217
        L_0x0212:
            r8 = r20[r7]
            r11.b(r8, r4)
        L_0x0217:
            if (r3 == 0) goto L_0x0244
            r8 = r20[r7]
            java.lang.Object r9 = r3.d
            x2.g r9 = (x2.C0340g) r9
            java.util.BitSet r14 = r9.g
            int r10 = r7 + 4
            r13 = 0
            r14.set(r10, r13)
            x2.s[] r9 = r9.f
            float r10 = r11.e
            r11.a(r10)
            android.graphics.Matrix r10 = new android.graphics.Matrix
            r10.<init>(r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            java.lang.Object r11 = r11.g
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            r8.<init>(r11)
            x2.n r11 = new x2.n
            r11.<init>(r8, r10)
            r9[r7] = r11
            goto L_0x0245
        L_0x0244:
            r13 = 0
        L_0x0245:
            r7 = r2
            r16 = r13
            r14 = r19
            r9 = r20
            r13 = 4
            r2 = r24
            goto L_0x00d2
        L_0x0251:
            r4.close()
            r5.close()
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L_0x0262
            android.graphics.Path$Op r0 = android.graphics.Path.Op.UNION
            r4.op(r5, r0)
        L_0x0262:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x2.C0346m.a(x2.k, float, android.graphics.RectF, G0.e, android.graphics.Path):void");
    }

    public final boolean b(Path path, int i2) {
        Path path2 = this.k;
        path2.reset();
        this.f2128a[i2].b(this.b[i2], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (!rectF.isEmpty() || (rectF.width() > 1.0f && rectF.height() > 1.0f)) {
            return true;
        }
        return false;
    }
}
