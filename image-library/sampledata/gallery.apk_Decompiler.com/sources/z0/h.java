package z0;

import A0.a;
import A0.e;
import A0.i;
import A0.j;
import A0.s;
import D0.b;
import E0.d;
import E0.f;
import F0.c;
import F0.l;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.C0332j;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements e, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final String f2165a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final c f2166c;
    public final LongSparseArray d = new LongSparseArray();
    public final LongSparseArray e = new LongSparseArray();
    public final Path f;
    public final l g;

    /* renamed from: h  reason: collision with root package name */
    public final RectF f2167h;

    /* renamed from: i  reason: collision with root package name */
    public final ArrayList f2168i;

    /* renamed from: j  reason: collision with root package name */
    public final f f2169j;
    public final j k;
    public final A0.f l;
    public final j m;
    public final j n;

    /* renamed from: o  reason: collision with root package name */
    public s f2170o;

    /* renamed from: p  reason: collision with root package name */
    public s f2171p;
    public final w q;
    public final int r;
    public e s;
    public float t;
    public final A0.h u;

    public h(w wVar, C0332j jVar, c cVar, d dVar) {
        Path path = new Path();
        this.f = path;
        this.g = new l(1, 2);
        this.f2167h = new RectF();
        this.f2168i = new ArrayList();
        this.t = 0.0f;
        this.f2166c = cVar;
        this.f2165a = dVar.g;
        this.b = dVar.f129h;
        this.q = wVar;
        this.f2169j = dVar.f127a;
        path.setFillType(dVar.b);
        this.r = (int) (jVar.b() / 32.0f);
        e p02 = dVar.f128c.p0();
        this.k = (j) p02;
        p02.a(this);
        cVar.f(p02);
        e p03 = dVar.d.p0();
        this.l = (A0.f) p03;
        p03.a(this);
        cVar.f(p03);
        e p04 = dVar.e.p0();
        this.m = (j) p04;
        p04.a(this);
        cVar.f(p04);
        e p05 = dVar.f.p0();
        this.n = (j) p05;
        p05.a(this);
        cVar.f(p05);
        if (cVar.j() != null) {
            i C02 = ((b) cVar.j().d).p0();
            this.s = C02;
            C02.a(this);
            cVar.f(this.s);
        }
        if (cVar.k() != null) {
            this.u = new A0.h(this, cVar, cVar.k());
        }
    }

    public final void a() {
        this.q.invalidateSelf();
    }

    public final void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            c cVar = (c) list2.get(i2);
            if (cVar instanceof n) {
                this.f2168i.add((n) cVar);
            }
        }
    }

    public final void c(C0.f fVar, int i2, ArrayList arrayList, C0.f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(D0.e eVar, Object obj) {
        PointF pointF = C0319A.f2034a;
        if (obj == 4) {
            this.l.k(eVar);
            return;
        }
        ColorFilter colorFilter = C0319A.f2028F;
        c cVar = this.f2166c;
        if (obj == colorFilter) {
            s sVar = this.f2170o;
            if (sVar != null) {
                cVar.n(sVar);
            }
            s sVar2 = new s(eVar, (Object) null);
            this.f2170o = sVar2;
            sVar2.a(this);
            cVar.f(this.f2170o);
        } else if (obj == C0319A.f2029G) {
            s sVar3 = this.f2171p;
            if (sVar3 != null) {
                cVar.n(sVar3);
            }
            this.d.clear();
            this.e.clear();
            s sVar4 = new s(eVar, (Object) null);
            this.f2171p = sVar4;
            sVar4.a(this);
            cVar.f(this.f2171p);
        } else if (obj == C0319A.e) {
            e eVar2 = this.s;
            if (eVar2 != null) {
                eVar2.k(eVar);
                return;
            }
            s sVar5 = new s(eVar, (Object) null);
            this.s = sVar5;
            sVar5.a(this);
            cVar.f(this.s);
        } else {
            A0.h hVar = this.u;
            if (obj == 5 && hVar != null) {
                hVar.f6c.k(eVar);
            } else if (obj == C0319A.B && hVar != null) {
                hVar.c(eVar);
            } else if (obj == C0319A.f2027C && hVar != null) {
                hVar.e.k(eVar);
            } else if (obj == C0319A.D && hVar != null) {
                hVar.f.k(eVar);
            } else if (obj == C0319A.E && hVar != null) {
                hVar.g.k(eVar);
            }
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        Path path = this.f;
        path.reset();
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f2168i;
            if (i2 < arrayList.size()) {
                path.addPath(((n) arrayList.get(i2)).getPath(), matrix);
                i2++;
            } else {
                path.computeBounds(rectF, false);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                return;
            }
        }
    }

    public final int[] f(int[] iArr) {
        s sVar = this.f2171p;
        if (sVar != null) {
            Integer[] numArr = (Integer[]) sVar.f();
            int i2 = 0;
            if (iArr.length == numArr.length) {
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i2 < numArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            }
        }
        return iArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g(android.graphics.Canvas r21, android.graphics.Matrix r22, int r23) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r0.b
            if (r2 == 0) goto L_0x0009
            return
        L_0x0009:
            x0.a r2 = x0.C0326d.f2049a
            android.graphics.Path r2 = r0.f
            r2.reset()
            r3 = 0
            r4 = r3
        L_0x0012:
            java.util.ArrayList r5 = r0.f2168i
            int r6 = r5.size()
            if (r4 >= r6) goto L_0x002a
            java.lang.Object r5 = r5.get(r4)
            z0.n r5 = (z0.n) r5
            android.graphics.Path r5 = r5.getPath()
            r2.addPath(r5, r1)
            int r4 = r4 + 1
            goto L_0x0012
        L_0x002a:
            android.graphics.RectF r4 = r0.f2167h
            r2.computeBounds(r4, r3)
            E0.f r4 = r0.f2169j
            E0.f r5 = E0.f.LINEAR
            r6 = 0
            A0.j r7 = r0.k
            A0.j r8 = r0.n
            A0.j r9 = r0.m
            if (r4 != r5) goto L_0x007f
            int r4 = r0.h()
            long r4 = (long) r4
            androidx.collection.LongSparseArray r10 = r0.d
            java.lang.Object r11 = r10.get(r4)
            android.graphics.LinearGradient r11 = (android.graphics.LinearGradient) r11
            if (r11 == 0) goto L_0x004d
            goto L_0x00cf
        L_0x004d:
            java.lang.Object r9 = r9.f()
            android.graphics.PointF r9 = (android.graphics.PointF) r9
            java.lang.Object r8 = r8.f()
            android.graphics.PointF r8 = (android.graphics.PointF) r8
            java.lang.Object r7 = r7.f()
            E0.c r7 = (E0.c) r7
            int[] r11 = r7.b
            int[] r17 = r0.f(r11)
            float[] r7 = r7.f126a
            android.graphics.LinearGradient r12 = new android.graphics.LinearGradient
            float r13 = r9.x
            float r14 = r9.y
            float r15 = r8.x
            float r8 = r8.y
            android.graphics.Shader$TileMode r19 = android.graphics.Shader.TileMode.CLAMP
            r18 = r7
            r16 = r8
            r12.<init>(r13, r14, r15, r16, r17, r18, r19)
            r10.put(r4, r12)
        L_0x007d:
            r11 = r12
            goto L_0x00cf
        L_0x007f:
            int r4 = r0.h()
            long r4 = (long) r4
            androidx.collection.LongSparseArray r10 = r0.e
            java.lang.Object r11 = r10.get(r4)
            android.graphics.RadialGradient r11 = (android.graphics.RadialGradient) r11
            if (r11 == 0) goto L_0x008f
            goto L_0x00cf
        L_0x008f:
            java.lang.Object r9 = r9.f()
            android.graphics.PointF r9 = (android.graphics.PointF) r9
            java.lang.Object r8 = r8.f()
            android.graphics.PointF r8 = (android.graphics.PointF) r8
            java.lang.Object r7 = r7.f()
            E0.c r7 = (E0.c) r7
            int[] r11 = r7.b
            int[] r16 = r0.f(r11)
            float[] r7 = r7.f126a
            float r13 = r9.x
            float r14 = r9.y
            float r9 = r8.x
            float r8 = r8.y
            float r9 = r9 - r13
            double r11 = (double) r9
            float r8 = r8 - r14
            double r8 = (double) r8
            double r8 = java.lang.Math.hypot(r11, r8)
            float r8 = (float) r8
            int r9 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r9 > 0) goto L_0x00c1
            r8 = 981668463(0x3a83126f, float:0.001)
        L_0x00c1:
            r15 = r8
            android.graphics.RadialGradient r12 = new android.graphics.RadialGradient
            android.graphics.Shader$TileMode r18 = android.graphics.Shader.TileMode.CLAMP
            r17 = r7
            r12.<init>(r13, r14, r15, r16, r17, r18)
            r10.put(r4, r12)
            goto L_0x007d
        L_0x00cf:
            r11.setLocalMatrix(r1)
            F0.l r4 = r0.g
            r4.setShader(r11)
            A0.s r5 = r0.f2170o
            if (r5 == 0) goto L_0x00e4
            java.lang.Object r5 = r5.f()
            android.graphics.ColorFilter r5 = (android.graphics.ColorFilter) r5
            r4.setColorFilter(r5)
        L_0x00e4:
            A0.e r5 = r0.s
            if (r5 == 0) goto L_0x010d
            java.lang.Object r5 = r5.f()
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x00fb
            r6 = 0
            r4.setMaskFilter(r6)
            goto L_0x010b
        L_0x00fb:
            float r6 = r0.t
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x010b
            android.graphics.BlurMaskFilter r6 = new android.graphics.BlurMaskFilter
            android.graphics.BlurMaskFilter$Blur r7 = android.graphics.BlurMaskFilter.Blur.NORMAL
            r6.<init>(r5, r7)
            r4.setMaskFilter(r6)
        L_0x010b:
            r0.t = r5
        L_0x010d:
            r5 = r23
            float r5 = (float) r5
            r6 = 1132396544(0x437f0000, float:255.0)
            float r5 = r5 / r6
            A0.f r7 = r0.l
            java.lang.Object r7 = r7.f()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            float r7 = (float) r7
            float r7 = r7 * r5
            r8 = 1120403456(0x42c80000, float:100.0)
            float r7 = r7 / r8
            float r7 = r7 * r6
            int r7 = (int) r7
            android.graphics.PointF r8 = J0.f.f362a
            r8 = 255(0xff, float:3.57E-43)
            int r8 = java.lang.Math.min(r8, r7)
            int r3 = java.lang.Math.max(r3, r8)
            r4.setAlpha(r3)
            A0.h r0 = r0.u
            if (r0 == 0) goto L_0x0143
            D1.g r3 = J0.g.f363a
            float r3 = (float) r7
            float r5 = r5 * r3
            float r5 = r5 / r6
            float r5 = r5 * r6
            int r3 = (int) r5
            r0.b(r4, r1, r3)
        L_0x0143:
            r0 = r21
            r0.drawPath(r2, r4)
            x0.a r0 = x0.C0326d.f2049a
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.h.g(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }

    public final String getName() {
        return this.f2165a;
    }

    public final int h() {
        int i2;
        float f5 = this.m.d;
        float f8 = (float) this.r;
        int round = Math.round(f5 * f8);
        int round2 = Math.round(this.n.d * f8);
        int round3 = Math.round(this.k.d * f8);
        if (round != 0) {
            i2 = 527 * round;
        } else {
            i2 = 17;
        }
        if (round2 != 0) {
            i2 = i2 * 31 * round2;
        }
        if (round3 != 0) {
            return i2 * 31 * round3;
        }
        return i2;
    }
}
