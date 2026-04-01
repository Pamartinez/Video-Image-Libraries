package H0;

import D0.e;
import D1.g;
import J0.f;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import x0.C0323a;
import x0.C0326d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p {

    /* renamed from: a  reason: collision with root package name */
    public static final LinearInterpolator f322a = new LinearInterpolator();
    public static final e b = e.S("t", "s", "e", "o", "i", "h", "to", "ti");

    /* renamed from: c  reason: collision with root package name */
    public static final e f323c = e.S("x", "y");

    public static Interpolator a(PointF pointF, PointF pointF2) {
        Interpolator interpolator;
        pointF.x = f.b(pointF.x, -1.0f, 1.0f);
        pointF.y = f.b(pointF.y, -100.0f, 100.0f);
        pointF2.x = f.b(pointF2.x, -1.0f, 1.0f);
        float b5 = f.b(pointF2.y, -100.0f, 100.0f);
        pointF2.y = b5;
        g gVar = J0.g.f363a;
        C0323a aVar = C0326d.f2049a;
        try {
            interpolator = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, b5);
        } catch (IllegalArgumentException e) {
            if ("The Path cannot loop back on itself.".equals(e.getMessage())) {
                interpolator = PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y);
            } else {
                interpolator = new LinearInterpolator();
            }
        }
        C0323a aVar2 = C0326d.f2049a;
        return interpolator;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static K0.a b(I0.c r24, x0.C0332j r25, float r26, H0.F r27, boolean r28, boolean r29) {
        /*
            r0 = r24
            r1 = r26
            r2 = r27
            D0.e r3 = b
            android.view.animation.LinearInterpolator r8 = f322a
            if (r28 == 0) goto L_0x023d
            if (r29 == 0) goto L_0x023d
            r0.c()
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r19 = 0
            r20 = 0
        L_0x001f:
            boolean r21 = r0.h()
            if (r21 == 0) goto L_0x01de
            int r21 = r0.q(r3)
            D0.e r7 = f323c
            switch(r21) {
                case 0: goto L_0x01cb;
                case 1: goto L_0x01bd;
                case 2: goto L_0x01ad;
                case 3: goto L_0x0113;
                case 4: goto L_0x0047;
                case 5: goto L_0x003c;
                case 6: goto L_0x0037;
                case 7: goto L_0x0032;
                default: goto L_0x002e;
            }
        L_0x002e:
            r0.s()
            goto L_0x001f
        L_0x0032:
            android.graphics.PointF r6 = H0.o.b(r0, r1)
            goto L_0x001f
        L_0x0037:
            android.graphics.PointF r5 = H0.o.b(r0, r1)
            goto L_0x001f
        L_0x003c:
            int r7 = r0.l()
            r9 = 1
            if (r7 != r9) goto L_0x0045
            r9 = 1
            goto L_0x001f
        L_0x0045:
            r9 = 0
            goto L_0x001f
        L_0x0047:
            r21 = r8
            I0.b r8 = r0.n()
            r28 = r9
            I0.b r9 = I0.b.BEGIN_OBJECT
            if (r8 != r9) goto L_0x0103
            r0.c()
            r4 = 0
            r8 = 0
            r9 = 0
            r15 = 0
        L_0x005a:
            boolean r22 = r0.h()
            if (r22 == 0) goto L_0x00e7
            r29 = r13
            int r13 = r0.q(r7)
            if (r13 == 0) goto L_0x00b0
            r22 = r3
            r3 = 1
            if (r13 == r3) goto L_0x0075
            r0.s()
            r13 = r29
            r3 = r22
            goto L_0x005a
        L_0x0075:
            I0.b r3 = r0.n()
            I0.b r8 = I0.b.NUMBER
            if (r3 != r8) goto L_0x008e
            r23 = r5
            r3 = r6
            double r5 = r0.j()
            float r15 = (float) r5
            r13 = r29
            r6 = r3
            r8 = r15
        L_0x0089:
            r3 = r22
            r5 = r23
            goto L_0x005a
        L_0x008e:
            r23 = r5
            r3 = r6
            r0.a()
            double r5 = r0.j()
            float r5 = (float) r5
            I0.b r6 = r0.n()
            if (r6 != r8) goto L_0x00a7
            r8 = r5
            double r5 = r0.j()
            float r5 = (float) r5
            r15 = r5
            goto L_0x00a9
        L_0x00a7:
            r8 = r5
            r15 = r8
        L_0x00a9:
            r0.f()
            r13 = r29
            r6 = r3
            goto L_0x0089
        L_0x00b0:
            r22 = r3
            r23 = r5
            r3 = r6
            I0.b r4 = r0.n()
            I0.b r5 = I0.b.NUMBER
            if (r4 != r5) goto L_0x00c7
            double r4 = r0.j()
            float r9 = (float) r4
            r13 = r29
            r6 = r3
            r4 = r9
            goto L_0x0089
        L_0x00c7:
            r0.a()
            r6 = r3
            double r3 = r0.j()
            float r4 = (float) r3
            I0.b r3 = r0.n()
            if (r3 != r5) goto L_0x00de
            r5 = r4
            double r3 = r0.j()
            float r3 = (float) r3
            r9 = r3
            goto L_0x00e0
        L_0x00de:
            r5 = r4
            r9 = r5
        L_0x00e0:
            r0.f()
            r13 = r29
            r4 = r5
            goto L_0x0089
        L_0x00e7:
            r22 = r3
            r23 = r5
            r29 = r13
            android.graphics.PointF r3 = new android.graphics.PointF
            r3.<init>(r4, r8)
            android.graphics.PointF r4 = new android.graphics.PointF
            r4.<init>(r9, r15)
            r0.g()
            r9 = r28
            r15 = r3
        L_0x00fd:
            r8 = r21
            r3 = r22
            goto L_0x001f
        L_0x0103:
            r22 = r3
            r23 = r5
            r29 = r13
            android.graphics.PointF r11 = H0.o.b(r0, r1)
            r9 = r28
            r8 = r21
            goto L_0x001f
        L_0x0113:
            r22 = r3
            r23 = r5
            r21 = r8
            r28 = r9
            r29 = r13
            I0.b r3 = r0.n()
            I0.b r5 = I0.b.BEGIN_OBJECT
            if (r3 != r5) goto L_0x01a8
            r0.c()
            r3 = 0
            r5 = 0
            r8 = 0
            r9 = 0
        L_0x012c:
            boolean r12 = r0.h()
            if (r12 == 0) goto L_0x018f
            int r12 = r0.q(r7)
            if (r12 == 0) goto L_0x0167
            r13 = 1
            if (r12 == r13) goto L_0x013f
            r0.s()
            goto L_0x012c
        L_0x013f:
            I0.b r5 = r0.n()
            I0.b r9 = I0.b.NUMBER
            if (r5 != r9) goto L_0x014e
            double r12 = r0.j()
            float r9 = (float) r12
            r5 = r9
            goto L_0x012c
        L_0x014e:
            r0.a()
            double r12 = r0.j()
            float r5 = (float) r12
            I0.b r12 = r0.n()
            if (r12 != r9) goto L_0x0162
            double r12 = r0.j()
            float r9 = (float) r12
            goto L_0x0163
        L_0x0162:
            r9 = r5
        L_0x0163:
            r0.f()
            goto L_0x012c
        L_0x0167:
            I0.b r3 = r0.n()
            I0.b r8 = I0.b.NUMBER
            if (r3 != r8) goto L_0x0176
            double r12 = r0.j()
            float r8 = (float) r12
            r3 = r8
            goto L_0x012c
        L_0x0176:
            r0.a()
            double r12 = r0.j()
            float r3 = (float) r12
            I0.b r12 = r0.n()
            if (r12 != r8) goto L_0x018a
            double r12 = r0.j()
            float r8 = (float) r12
            goto L_0x018b
        L_0x018a:
            r8 = r3
        L_0x018b:
            r0.f()
            goto L_0x012c
        L_0x018f:
            android.graphics.PointF r12 = new android.graphics.PointF
            r12.<init>(r3, r5)
            android.graphics.PointF r14 = new android.graphics.PointF
            r14.<init>(r8, r9)
            r0.g()
        L_0x019c:
            r9 = r28
            r13 = r29
            r8 = r21
            r3 = r22
            r5 = r23
            goto L_0x001f
        L_0x01a8:
            android.graphics.PointF r10 = H0.o.b(r0, r1)
            goto L_0x019c
        L_0x01ad:
            r22 = r3
            r23 = r5
            r21 = r8
            r28 = r9
            r29 = r13
            java.lang.Object r20 = r2.g(r0, r1)
            goto L_0x001f
        L_0x01bd:
            r22 = r3
            r23 = r5
            r21 = r8
            r28 = r9
            java.lang.Object r13 = r2.g(r0, r1)
            goto L_0x001f
        L_0x01cb:
            r22 = r3
            r23 = r5
            r21 = r8
            r28 = r9
            r29 = r13
            double r7 = r0.j()
            float r3 = (float) r7
            r19 = r3
            goto L_0x00fd
        L_0x01de:
            r23 = r5
            r21 = r8
            r28 = r9
            r29 = r13
            r0.g()
            if (r28 == 0) goto L_0x01f1
            r14 = r29
        L_0x01ed:
            r15 = 0
            r16 = 0
            goto L_0x0215
        L_0x01f1:
            if (r10 == 0) goto L_0x01fe
            if (r11 == 0) goto L_0x01fe
            android.view.animation.Interpolator r0 = a(r10, r11)
            r21 = r0
        L_0x01fb:
            r14 = r20
            goto L_0x01ed
        L_0x01fe:
            if (r12 == 0) goto L_0x01fb
            if (r14 == 0) goto L_0x01fb
            if (r15 == 0) goto L_0x01fb
            if (r4 == 0) goto L_0x01fb
            android.view.animation.Interpolator r0 = a(r12, r15)
            android.view.animation.Interpolator r1 = a(r14, r4)
            r15 = r0
            r16 = r1
            r14 = r20
            r21 = 0
        L_0x0215:
            if (r15 == 0) goto L_0x0227
            if (r16 == 0) goto L_0x0227
            K0.a r11 = new K0.a
            r12 = r25
            r13 = r29
            r17 = r19
            r11.<init>((x0.C0332j) r12, (java.lang.Object) r13, (java.lang.Object) r14, (android.view.animation.Interpolator) r15, (android.view.animation.Interpolator) r16, (float) r17)
        L_0x0224:
            r5 = r23
            goto L_0x0237
        L_0x0227:
            r13 = r29
            r16 = r19
            K0.a r11 = new K0.a
            r17 = 0
            r12 = r25
            r15 = r21
            r11.<init>((x0.C0332j) r12, (java.lang.Object) r13, (java.lang.Object) r14, (android.view.animation.Interpolator) r15, (float) r16, (java.lang.Float) r17)
            goto L_0x0224
        L_0x0237:
            r11.f374o = r5
            r3 = r6
            r11.f375p = r3
            return r11
        L_0x023d:
            r22 = r3
            r21 = r8
            if (r28 == 0) goto L_0x02c6
            r0.c()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r14 = 0
            r17 = 0
            r18 = 0
        L_0x0250:
            boolean r8 = r0.h()
            if (r8 == 0) goto L_0x029f
            r8 = r22
            int r9 = r0.q(r8)
            r10 = 1065353216(0x3f800000, float:1.0)
            switch(r9) {
                case 0: goto L_0x0294;
                case 1: goto L_0x028e;
                case 2: goto L_0x0288;
                case 3: goto L_0x0282;
                case 4: goto L_0x027c;
                case 5: goto L_0x0271;
                case 6: goto L_0x026c;
                case 7: goto L_0x0267;
                default: goto L_0x0261;
            }
        L_0x0261:
            r0.s()
        L_0x0264:
            r22 = r8
            goto L_0x0250
        L_0x0267:
            android.graphics.PointF r5 = H0.o.b(r0, r1)
            goto L_0x0264
        L_0x026c:
            android.graphics.PointF r4 = H0.o.b(r0, r1)
            goto L_0x0264
        L_0x0271:
            int r7 = r0.l()
            r13 = 1
            if (r7 != r13) goto L_0x027a
            r7 = r13
            goto L_0x0264
        L_0x027a:
            r7 = 0
            goto L_0x0264
        L_0x027c:
            r13 = 1
            android.graphics.PointF r3 = H0.o.b(r0, r10)
            goto L_0x0264
        L_0x0282:
            r13 = 1
            android.graphics.PointF r6 = H0.o.b(r0, r10)
            goto L_0x0264
        L_0x0288:
            r13 = 1
            java.lang.Object r18 = r2.g(r0, r1)
            goto L_0x0264
        L_0x028e:
            r13 = 1
            java.lang.Object r14 = r2.g(r0, r1)
            goto L_0x0264
        L_0x0294:
            r13 = 1
            double r9 = r0.j()
            float r9 = (float) r9
            r22 = r8
            r17 = r9
            goto L_0x0250
        L_0x029f:
            r0.g()
            if (r7 == 0) goto L_0x02a8
            r15 = r14
        L_0x02a5:
            r16 = r21
            goto L_0x02b8
        L_0x02a8:
            if (r6 == 0) goto L_0x02b5
            if (r3 == 0) goto L_0x02b5
            android.view.animation.Interpolator r8 = a(r6, r3)
            r16 = r8
            r15 = r18
            goto L_0x02b8
        L_0x02b5:
            r15 = r18
            goto L_0x02a5
        L_0x02b8:
            K0.a r12 = new K0.a
            r18 = 0
            r13 = r25
            r12.<init>((x0.C0332j) r13, (java.lang.Object) r14, (java.lang.Object) r15, (android.view.animation.Interpolator) r16, (float) r17, (java.lang.Float) r18)
            r12.f374o = r4
            r12.f375p = r5
            return r12
        L_0x02c6:
            java.lang.Object r0 = r2.g(r0, r1)
            K0.a r1 = new K0.a
            r1.<init>(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.p.b(I0.c, x0.j, float, H0.F, boolean, boolean):K0.a");
    }
}
