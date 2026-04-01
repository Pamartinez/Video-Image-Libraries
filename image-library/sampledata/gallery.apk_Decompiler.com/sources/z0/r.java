package z0;

import A0.a;
import A0.i;
import C0.f;
import D0.b;
import D0.e;
import D0.g;
import E0.o;
import F0.c;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements e, n, j, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final Matrix f2190a = new Matrix();
    public final Path b = new Path();

    /* renamed from: c  reason: collision with root package name */
    public final w f2191c;
    public final c d;
    public final String e;
    public final boolean f;
    public final i g;

    /* renamed from: h  reason: collision with root package name */
    public final i f2192h;

    /* renamed from: i  reason: collision with root package name */
    public final A0.r f2193i;

    /* renamed from: j  reason: collision with root package name */
    public d f2194j;

    public r(w wVar, c cVar, o oVar) {
        this.f2191c = wVar;
        this.d = cVar;
        this.e = (String) oVar.b;
        this.f = oVar.d;
        i C02 = oVar.f145c.p0();
        this.g = C02;
        cVar.f(C02);
        C02.a(this);
        i C03 = ((b) oVar.e).p0();
        this.f2192h = C03;
        cVar.f(C03);
        C03.a(this);
        g gVar = (g) oVar.f;
        gVar.getClass();
        A0.r rVar = new A0.r(gVar);
        this.f2193i = rVar;
        rVar.a(cVar);
        rVar.b(this);
    }

    public final void a() {
        this.f2191c.invalidateSelf();
    }

    public final void b(List list, List list2) {
        this.f2194j.b(list, list2);
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
        for (int i7 = 0; i7 < this.f2194j.f2154h.size(); i7++) {
            c cVar = (c) this.f2194j.f2154h.get(i7);
            if (cVar instanceof k) {
                J0.f.f(fVar, i2, arrayList, fVar2, (k) cVar);
            }
        }
    }

    public final void d(e eVar, Object obj) {
        if (!this.f2193i.c(eVar, obj)) {
            if (obj == C0319A.f2040p) {
                this.g.k(eVar);
            } else if (obj == C0319A.q) {
                this.f2192h.k(eVar);
            }
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        this.f2194j.e(rectF, matrix, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0005 A[LOOP:0: B:3:0x0005->B:6:0x000f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(java.util.ListIterator r9) {
        /*
            r8 = this;
            z0.d r0 = r8.f2194j
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r9.hasPrevious()
            if (r0 == 0) goto L_0x0012
            java.lang.Object r0 = r9.previous()
            if (r0 == r8) goto L_0x0012
            goto L_0x0005
        L_0x0012:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0017:
            boolean r0 = r9.hasPrevious()
            if (r0 == 0) goto L_0x002a
            java.lang.Object r0 = r9.previous()
            z0.c r0 = (z0.c) r0
            r6.add(r0)
            r9.remove()
            goto L_0x0017
        L_0x002a:
            java.util.Collections.reverse(r6)
            z0.d r1 = new z0.d
            boolean r5 = r8.f
            r7 = 0
            x0.w r2 = r8.f2191c
            F0.c r3 = r8.d
            java.lang.String r4 = "Repeater"
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.f2194j = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.r.f(java.util.ListIterator):void");
    }

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        float floatValue = ((Float) this.g.f()).floatValue();
        float floatValue2 = ((Float) this.f2192h.f()).floatValue();
        A0.r rVar = this.f2193i;
        float floatValue3 = ((Float) rVar.m.f()).floatValue() / 100.0f;
        float floatValue4 = ((Float) rVar.n.f()).floatValue() / 100.0f;
        for (int i7 = ((int) floatValue) - 1; i7 >= 0; i7--) {
            Matrix matrix2 = this.f2190a;
            matrix2.set(matrix);
            float f5 = (float) i7;
            matrix2.preConcat(rVar.f(f5 + floatValue2));
            this.f2194j.g(canvas, matrix2, (int) (J0.f.e(floatValue3, floatValue4, f5 / floatValue) * ((float) i2)));
        }
    }

    public final String getName() {
        return this.e;
    }

    public final Path getPath() {
        Path path = this.f2194j.getPath();
        Path path2 = this.b;
        path2.reset();
        float floatValue = ((Float) this.g.f()).floatValue();
        float floatValue2 = ((Float) this.f2192h.f()).floatValue();
        for (int i2 = ((int) floatValue) - 1; i2 >= 0; i2--) {
            Matrix f5 = this.f2193i.f(((float) i2) + floatValue2);
            Matrix matrix = this.f2190a;
            matrix.set(f5);
            path2.addPath(path, matrix);
        }
        return path2;
    }
}
