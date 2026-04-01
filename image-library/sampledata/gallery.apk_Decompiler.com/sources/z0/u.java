package z0;

import A0.f;
import A0.s;
import D0.e;
import F0.c;
import F0.l;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import x0.C0319A;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends b {
    public final c r;
    public final String s;
    public final boolean t;
    public final f u;
    public s v;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public u(x0.w r11, F0.c r12, E0.x r13) {
        /*
            r10 = this;
            E0.v r0 = r13.g
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
            E0.w r0 = r13.f158h
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
            float r5 = r13.f159i
            D0.a r6 = r13.e
            D0.b r7 = r13.f
            java.util.ArrayList r8 = r13.f157c
            D0.b r9 = r13.b
            r0 = r10
            r1 = r11
            r2 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r10.r = r12
            java.lang.String r1 = r13.f156a
            r10.s = r1
            boolean r1 = r13.f160j
            r10.t = r1
            D0.a r1 = r13.d
            A0.e r1 = r1.p0()
            r3 = r1
            A0.f r3 = (A0.f) r3
            r10.u = r3
            r1.a(r10)
            r12.f(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.u.<init>(x0.w, F0.c, E0.x):void");
    }

    public final void d(e eVar, Object obj) {
        super.d(eVar, obj);
        PointF pointF = C0319A.f2034a;
        f fVar = this.u;
        if (obj == 2) {
            fVar.k(eVar);
        } else if (obj == C0319A.f2028F) {
            s sVar = this.v;
            c cVar = this.r;
            if (sVar != null) {
                cVar.n(sVar);
            }
            s sVar2 = new s(eVar, (Object) null);
            this.v = sVar2;
            sVar2.a(this);
            cVar.f(fVar);
        }
    }

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        if (!this.t) {
            f fVar = this.u;
            int l = fVar.l(fVar.b(), fVar.d());
            l lVar = this.f2148i;
            lVar.setColor(l);
            s sVar = this.v;
            if (sVar != null) {
                lVar.setColorFilter((ColorFilter) sVar.f());
            }
            super.g(canvas, matrix, i2);
        }
    }

    public final String getName() {
        return this.s;
    }
}
