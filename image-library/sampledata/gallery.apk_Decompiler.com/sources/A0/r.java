package A0;

import D0.a;
import D0.b;
import D0.c;
import D0.e;
import D0.g;
import D0.h;
import android.graphics.Matrix;
import android.graphics.PointF;
import java.util.Collections;
import x0.C0319A;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    public final Matrix f21a = new Matrix();
    public final Matrix b;

    /* renamed from: c  reason: collision with root package name */
    public final Matrix f22c;
    public final Matrix d;
    public final float[] e;
    public e f;
    public e g;

    /* renamed from: h  reason: collision with root package name */
    public e f23h;

    /* renamed from: i  reason: collision with root package name */
    public e f24i;

    /* renamed from: j  reason: collision with root package name */
    public e f25j;
    public i k;
    public i l;
    public e m;
    public e n;

    /* renamed from: o  reason: collision with root package name */
    public final boolean f26o;

    public r(g gVar) {
        e eVar;
        e eVar2;
        e eVar3;
        i iVar;
        i iVar2;
        i iVar3;
        c cVar = gVar.f107a;
        if (cVar == null) {
            eVar = null;
        } else {
            eVar = cVar.p0();
        }
        this.f = eVar;
        h hVar = gVar.b;
        if (hVar == null) {
            eVar2 = null;
        } else {
            eVar2 = hVar.p0();
        }
        this.g = eVar2;
        a aVar = gVar.f108c;
        if (aVar == null) {
            eVar3 = null;
        } else {
            eVar3 = aVar.p0();
        }
        this.f23h = eVar3;
        b bVar = gVar.d;
        if (bVar == null) {
            iVar = null;
        } else {
            iVar = bVar.p0();
        }
        this.f24i = iVar;
        b bVar2 = gVar.f;
        if (bVar2 == null) {
            iVar2 = null;
        } else {
            iVar2 = bVar2.p0();
        }
        this.k = iVar2;
        this.f26o = gVar.f111j;
        if (iVar2 != null) {
            this.b = new Matrix();
            this.f22c = new Matrix();
            this.d = new Matrix();
            this.e = new float[9];
        } else {
            this.b = null;
            this.f22c = null;
            this.d = null;
            this.e = null;
        }
        b bVar3 = gVar.g;
        if (bVar3 == null) {
            iVar3 = null;
        } else {
            iVar3 = bVar3.p0();
        }
        this.l = iVar3;
        a aVar2 = gVar.e;
        if (aVar2 != null) {
            this.f25j = aVar2.p0();
        }
        b bVar4 = gVar.f109h;
        if (bVar4 != null) {
            this.m = bVar4.p0();
        } else {
            this.m = null;
        }
        b bVar5 = gVar.f110i;
        if (bVar5 != null) {
            this.n = bVar5.p0();
        } else {
            this.n = null;
        }
    }

    public final void a(F0.c cVar) {
        cVar.f(this.f25j);
        cVar.f(this.m);
        cVar.f(this.n);
        cVar.f(this.f);
        cVar.f(this.g);
        cVar.f(this.f23h);
        cVar.f(this.f24i);
        cVar.f(this.k);
        cVar.f(this.l);
    }

    public final void b(a aVar) {
        e eVar = this.f25j;
        if (eVar != null) {
            eVar.a(aVar);
        }
        e eVar2 = this.m;
        if (eVar2 != null) {
            eVar2.a(aVar);
        }
        e eVar3 = this.n;
        if (eVar3 != null) {
            eVar3.a(aVar);
        }
        e eVar4 = this.f;
        if (eVar4 != null) {
            eVar4.a(aVar);
        }
        e eVar5 = this.g;
        if (eVar5 != null) {
            eVar5.a(aVar);
        }
        e eVar6 = this.f23h;
        if (eVar6 != null) {
            eVar6.a(aVar);
        }
        e eVar7 = this.f24i;
        if (eVar7 != null) {
            eVar7.a(aVar);
        }
        i iVar = this.k;
        if (iVar != null) {
            iVar.a(aVar);
        }
        i iVar2 = this.l;
        if (iVar2 != null) {
            iVar2.a(aVar);
        }
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [A0.i, A0.e] */
    /* JADX WARNING: type inference failed for: r6v4, types: [A0.i, A0.e] */
    public final boolean c(e eVar, Object obj) {
        Float valueOf = Float.valueOf(100.0f);
        Float valueOf2 = Float.valueOf(0.0f);
        if (obj == C0319A.f2034a) {
            e eVar2 = this.f;
            if (eVar2 == null) {
                this.f = new s(eVar, new PointF());
                return true;
            }
            eVar2.k(eVar);
            return true;
        } else if (obj == C0319A.b) {
            e eVar3 = this.g;
            if (eVar3 == null) {
                this.g = new s(eVar, new PointF());
                return true;
            }
            eVar3.k(eVar);
            return true;
        } else {
            if (obj == C0319A.f2035c) {
                e eVar4 = this.g;
                if (eVar4 instanceof p) {
                    ((p) eVar4).m = eVar;
                    return true;
                }
            }
            if (obj == C0319A.d) {
                e eVar5 = this.g;
                if (eVar5 instanceof p) {
                    ((p) eVar5).n = eVar;
                    return true;
                }
            }
            if (obj == C0319A.f2038j) {
                e eVar6 = this.f23h;
                if (eVar6 == null) {
                    this.f23h = new s(eVar, new K0.c());
                    return true;
                }
                eVar6.k(eVar);
                return true;
            } else if (obj == C0319A.k) {
                e eVar7 = this.f24i;
                if (eVar7 == null) {
                    this.f24i = new s(eVar, valueOf2);
                    return true;
                }
                eVar7.k(eVar);
                return true;
            } else if (obj == 3) {
                e eVar8 = this.f25j;
                if (eVar8 == null) {
                    this.f25j = new s(eVar, 100);
                    return true;
                }
                eVar8.k(eVar);
                return true;
            } else if (obj == C0319A.f2041x) {
                e eVar9 = this.m;
                if (eVar9 == null) {
                    this.m = new s(eVar, valueOf);
                    return true;
                }
                eVar9.k(eVar);
                return true;
            } else if (obj == C0319A.y) {
                e eVar10 = this.n;
                if (eVar10 == null) {
                    this.n = new s(eVar, valueOf);
                    return true;
                }
                eVar10.k(eVar);
                return true;
            } else if (obj == C0319A.l) {
                if (this.k == null) {
                    this.k = new e(Collections.singletonList(new K0.a(valueOf2)));
                }
                this.k.k(eVar);
                return true;
            } else if (obj != C0319A.m) {
                return false;
            } else {
                if (this.l == null) {
                    this.l = new e(Collections.singletonList(new K0.a(valueOf2)));
                }
                this.l.k(eVar);
                return true;
            }
        }
    }

    public final void d() {
        for (int i2 = 0; i2 < 9; i2++) {
            this.e[i2] = 0.0f;
        }
    }

    public final Matrix e() {
        PointF pointF;
        K0.c cVar;
        float f5;
        float f8;
        float f10;
        PointF pointF2;
        Matrix matrix = this.f21a;
        matrix.reset();
        e eVar = this.g;
        if (!(eVar == null || (pointF2 = (PointF) eVar.f()) == null)) {
            float f11 = pointF2.x;
            if (!(f11 == 0.0f && pointF2.y == 0.0f)) {
                matrix.preTranslate(f11, pointF2.y);
            }
        }
        if (!this.f26o) {
            e eVar2 = this.f24i;
            if (eVar2 != null) {
                if (eVar2 instanceof s) {
                    f10 = ((Float) eVar2.f()).floatValue();
                } else {
                    f10 = ((i) eVar2).l();
                }
                if (f10 != 0.0f) {
                    matrix.preRotate(f10);
                }
            }
        } else if (eVar != null) {
            float f12 = eVar.d;
            PointF pointF3 = (PointF) eVar.f();
            float f13 = pointF3.x;
            float f14 = pointF3.y;
            eVar.j(1.0E-4f + f12);
            PointF pointF4 = (PointF) eVar.f();
            eVar.j(f12);
            matrix.preRotate((float) Math.toDegrees(Math.atan2((double) (pointF4.y - f14), (double) (pointF4.x - f13))));
        }
        i iVar = this.k;
        if (iVar != null) {
            i iVar2 = this.l;
            if (iVar2 == null) {
                f5 = 0.0f;
            } else {
                f5 = (float) Math.cos(Math.toRadians((double) ((-iVar2.l()) + 90.0f)));
            }
            i iVar3 = this.l;
            if (iVar3 == null) {
                f8 = 1.0f;
            } else {
                f8 = (float) Math.sin(Math.toRadians((double) ((-iVar3.l()) + 90.0f)));
            }
            float tan = (float) Math.tan(Math.toRadians((double) iVar.l()));
            d();
            float[] fArr = this.e;
            fArr[0] = f5;
            fArr[1] = f8;
            float f15 = -f8;
            fArr[3] = f15;
            fArr[4] = f5;
            fArr[8] = 1.0f;
            Matrix matrix2 = this.b;
            matrix2.setValues(fArr);
            d();
            fArr[0] = 1.0f;
            fArr[3] = tan;
            fArr[4] = 1.0f;
            fArr[8] = 1.0f;
            Matrix matrix3 = this.f22c;
            matrix3.setValues(fArr);
            d();
            fArr[0] = f5;
            fArr[1] = f15;
            fArr[3] = f8;
            fArr[4] = f5;
            fArr[8] = 1.0f;
            Matrix matrix4 = this.d;
            matrix4.setValues(fArr);
            matrix3.preConcat(matrix2);
            matrix4.preConcat(matrix3);
            matrix.preConcat(matrix4);
        }
        e eVar3 = this.f23h;
        if (!(eVar3 == null || (cVar = (K0.c) eVar3.f()) == null)) {
            float f16 = cVar.f378a;
            if (!(f16 == 1.0f && cVar.b == 1.0f)) {
                matrix.preScale(f16, cVar.b);
            }
        }
        e eVar4 = this.f;
        if (!(eVar4 == null || (pointF = (PointF) eVar4.f()) == null)) {
            float f17 = pointF.x;
            if (!(f17 == 0.0f && pointF.y == 0.0f)) {
                matrix.preTranslate(-f17, -pointF.y);
            }
        }
        return matrix;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.graphics.PointF} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Matrix f(float r9) {
        /*
            r8 = this;
            A0.e r0 = r8.g
            r1 = 0
            if (r0 != 0) goto L_0x0007
            r0 = r1
            goto L_0x000d
        L_0x0007:
            java.lang.Object r0 = r0.f()
            android.graphics.PointF r0 = (android.graphics.PointF) r0
        L_0x000d:
            A0.e r2 = r8.f23h
            if (r2 != 0) goto L_0x0013
            r2 = r1
            goto L_0x0019
        L_0x0013:
            java.lang.Object r2 = r2.f()
            K0.c r2 = (K0.c) r2
        L_0x0019:
            android.graphics.Matrix r3 = r8.f21a
            r3.reset()
            if (r0 == 0) goto L_0x0029
            float r4 = r0.x
            float r4 = r4 * r9
            float r0 = r0.y
            float r0 = r0 * r9
            r3.preTranslate(r4, r0)
        L_0x0029:
            if (r2 == 0) goto L_0x003f
            float r0 = r2.f378a
            double r4 = (double) r0
            double r6 = (double) r9
            double r4 = java.lang.Math.pow(r4, r6)
            float r0 = (float) r4
            float r2 = r2.b
            double r4 = (double) r2
            double r4 = java.lang.Math.pow(r4, r6)
            float r2 = (float) r4
            r3.preScale(r0, r2)
        L_0x003f:
            A0.e r0 = r8.f24i
            if (r0 == 0) goto L_0x0069
            java.lang.Object r0 = r0.f()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            A0.e r8 = r8.f
            if (r8 != 0) goto L_0x0052
            goto L_0x0059
        L_0x0052:
            java.lang.Object r8 = r8.f()
            r1 = r8
            android.graphics.PointF r1 = (android.graphics.PointF) r1
        L_0x0059:
            float r0 = r0 * r9
            r8 = 0
            if (r1 != 0) goto L_0x005f
            r9 = r8
            goto L_0x0061
        L_0x005f:
            float r9 = r1.x
        L_0x0061:
            if (r1 != 0) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            float r8 = r1.y
        L_0x0066:
            r3.preRotate(r0, r9, r8)
        L_0x0069:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.r.f(float):android.graphics.Matrix");
    }
}
