package z0;

import A0.j;
import A0.s;
import D0.e;
import E0.f;
import F0.c;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import x0.C0319A;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends b {

    /* renamed from: A  reason: collision with root package name */
    public final j f2172A;
    public s B;
    public final String r;
    public final boolean s;
    public final LongSparseArray t;
    public final LongSparseArray u;
    public final RectF v;
    public final f w;

    /* renamed from: x  reason: collision with root package name */
    public final int f2173x;
    public final j y;
    public final j z;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public i(x0.w r11, F0.c r12, E0.e r13) {
        /*
            r10 = this;
            E0.v r0 = r13.f132h
            r0.getClass()
            int[] r1 = E0.u.f155a
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 2
            r2 = 1
            if (r0 == r2) goto L_0x001a
            if (r0 == r1) goto L_0x0017
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.SQUARE
        L_0x0015:
            r3 = r0
            goto L_0x001d
        L_0x0017:
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.ROUND
            goto L_0x0015
        L_0x001a:
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.BUTT
            goto L_0x0015
        L_0x001d:
            E0.w r0 = r13.f133i
            r0.getClass()
            int[] r4 = E0.u.b
            int r0 = r0.ordinal()
            r0 = r4[r0]
            if (r0 == r2) goto L_0x003a
            if (r0 == r1) goto L_0x0037
            r1 = 3
            if (r0 == r1) goto L_0x0034
            r0 = 0
        L_0x0032:
            r4 = r0
            goto L_0x003d
        L_0x0034:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.ROUND
            goto L_0x0032
        L_0x0037:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.MITER
            goto L_0x0032
        L_0x003a:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.BEVEL
            goto L_0x0032
        L_0x003d:
            float r5 = r13.f134j
            D0.a r6 = r13.d
            D0.b r7 = r13.g
            java.util.ArrayList r8 = r13.k
            D0.b r9 = r13.l
            r0 = r10
            r1 = r11
            r2 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            androidx.collection.LongSparseArray r1 = new androidx.collection.LongSparseArray
            r1.<init>()
            r10.t = r1
            androidx.collection.LongSparseArray r1 = new androidx.collection.LongSparseArray
            r1.<init>()
            r10.u = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r10.v = r1
            java.lang.String r1 = r13.f130a
            r10.r = r1
            E0.f r1 = r13.b
            r10.w = r1
            boolean r1 = r13.m
            r10.s = r1
            x0.j r1 = r11.d
            float r1 = r1.b()
            r3 = 1107296256(0x42000000, float:32.0)
            float r1 = r1 / r3
            int r1 = (int) r1
            r10.f2173x = r1
            D0.a r1 = r13.f131c
            A0.e r1 = r1.p0()
            r3 = r1
            A0.j r3 = (A0.j) r3
            r10.y = r3
            r1.a(r10)
            r12.f(r1)
            D0.a r1 = r13.e
            A0.e r1 = r1.p0()
            r3 = r1
            A0.j r3 = (A0.j) r3
            r10.z = r3
            r1.a(r10)
            r12.f(r1)
            D0.a r1 = r13.f
            A0.e r1 = r1.p0()
            r3 = r1
            A0.j r3 = (A0.j) r3
            r10.f2172A = r3
            r1.a(r10)
            r12.f(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.i.<init>(x0.w, F0.c, E0.e):void");
    }

    public final void d(e eVar, Object obj) {
        super.d(eVar, obj);
        if (obj == C0319A.f2029G) {
            s sVar = this.B;
            c cVar = this.f;
            if (sVar != null) {
                cVar.n(sVar);
            }
            s sVar2 = new s(eVar, (Object) null);
            this.B = sVar2;
            sVar2.a(this);
            cVar.f(this.B);
        }
    }

    public final int[] f(int[] iArr) {
        s sVar = this.B;
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

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        Shader shader;
        Shader radialGradient;
        Matrix matrix2 = matrix;
        if (!this.s) {
            e(this.v, matrix2, false);
            f fVar = this.w;
            f fVar2 = f.LINEAR;
            j jVar = this.y;
            j jVar2 = this.f2172A;
            j jVar3 = this.z;
            if (fVar == fVar2) {
                long h5 = (long) h();
                LongSparseArray longSparseArray = this.t;
                shader = (LinearGradient) longSparseArray.get(h5);
                if (shader == null) {
                    PointF pointF = (PointF) jVar3.f();
                    PointF pointF2 = (PointF) jVar2.f();
                    E0.c cVar = (E0.c) jVar.f();
                    radialGradient = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, f(cVar.b), cVar.f126a, Shader.TileMode.CLAMP);
                    longSparseArray.put(h5, radialGradient);
                }
                shader.setLocalMatrix(matrix2);
                this.f2148i.setShader(shader);
                super.g(canvas, matrix, i2);
            }
            long h6 = (long) h();
            LongSparseArray longSparseArray2 = this.u;
            shader = (RadialGradient) longSparseArray2.get(h6);
            if (shader == null) {
                PointF pointF3 = (PointF) jVar3.f();
                PointF pointF4 = (PointF) jVar2.f();
                E0.c cVar2 = (E0.c) jVar.f();
                int[] f = f(cVar2.b);
                float[] fArr = cVar2.f126a;
                float f5 = pointF3.x;
                float f8 = pointF3.y;
                radialGradient = new RadialGradient(f5, f8, (float) Math.hypot((double) (pointF4.x - f5), (double) (pointF4.y - f8)), f, fArr, Shader.TileMode.CLAMP);
                longSparseArray2.put(h6, radialGradient);
            }
            shader.setLocalMatrix(matrix2);
            this.f2148i.setShader(shader);
            super.g(canvas, matrix, i2);
            shader = radialGradient;
            shader.setLocalMatrix(matrix2);
            this.f2148i.setShader(shader);
            super.g(canvas, matrix, i2);
        }
    }

    public final String getName() {
        return this.r;
    }

    public final int h() {
        int i2;
        float f = this.z.d;
        float f5 = (float) this.f2173x;
        int round = Math.round(f * f5);
        int round2 = Math.round(this.f2172A.d * f5);
        int round3 = Math.round(this.y.d * f5);
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
