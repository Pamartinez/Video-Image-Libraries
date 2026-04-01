package z0;

import A0.a;
import A0.e;
import A0.i;
import C0.f;
import D0.c;
import D0.h;
import E0.o;
import E0.y;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements a, k, n {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2185a = new Path();
    public final RectF b = new RectF();

    /* renamed from: c  reason: collision with root package name */
    public final String f2186c;
    public final boolean d;
    public final w e;
    public final e f;
    public final e g;

    /* renamed from: h  reason: collision with root package name */
    public final i f2187h;

    /* renamed from: i  reason: collision with root package name */
    public final c f2188i = new c();

    /* renamed from: j  reason: collision with root package name */
    public e f2189j = null;
    public boolean k;

    public q(w wVar, F0.c cVar, o oVar) {
        this.f2186c = (String) oVar.b;
        this.d = oVar.d;
        this.e = wVar;
        e p02 = oVar.e.p0();
        this.f = p02;
        e p03 = ((h) oVar.f).p0();
        this.g = p03;
        i C02 = oVar.f145c.p0();
        this.f2187h = C02;
        cVar.f(p02);
        cVar.f(p03);
        cVar.f(C02);
        p02.a(this);
        p03.a(this);
        C02.a(this);
    }

    public final void a() {
        this.k = false;
        this.e.invalidateSelf();
    }

    public final void b(List list, List list2) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i2 < arrayList.size()) {
                c cVar = (c) arrayList.get(i2);
                if (cVar instanceof v) {
                    v vVar = (v) cVar;
                    if (vVar.f2200c == y.SIMULTANEOUSLY) {
                        this.f2188i.d.add(vVar);
                        vVar.c(this);
                        i2++;
                    }
                }
                if (cVar instanceof s) {
                    this.f2189j = ((s) cVar).b;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(D0.e eVar, Object obj) {
        if (obj == C0319A.g) {
            this.g.k(eVar);
        } else if (obj == C0319A.f2037i) {
            this.f.k(eVar);
        } else if (obj == C0319A.f2036h) {
            this.f2187h.k(eVar);
        }
    }

    public final String getName() {
        return this.f2186c;
    }

    public final Path getPath() {
        float f5;
        float f8;
        e eVar;
        boolean z = this.k;
        Path path = this.f2185a;
        if (z) {
            return path;
        }
        path.reset();
        if (this.d) {
            this.k = true;
            return path;
        }
        PointF pointF = (PointF) this.g.f();
        float f10 = pointF.x / 2.0f;
        float f11 = pointF.y / 2.0f;
        i iVar = this.f2187h;
        if (iVar == null) {
            f5 = 0.0f;
        } else {
            f5 = iVar.l();
        }
        if (f5 == 0.0f && (eVar = this.f2189j) != null) {
            f5 = Math.min(((Float) eVar.f()).floatValue(), Math.min(f10, f11));
        }
        float min = Math.min(f10, f11);
        if (f5 > min) {
            f5 = min;
        }
        PointF pointF2 = (PointF) this.f.f();
        path.moveTo(pointF2.x + f10, (pointF2.y - f11) + f5);
        path.lineTo(pointF2.x + f10, (pointF2.y + f11) - f5);
        int i2 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
        RectF rectF = this.b;
        if (i2 > 0) {
            float f12 = pointF2.x + f10;
            float f13 = f5 * 2.0f;
            f8 = 2.0f;
            float f14 = pointF2.y + f11;
            rectF.set(f12 - f13, f14 - f13, f12, f14);
            path.arcTo(rectF, 0.0f, 90.0f, false);
        } else {
            f8 = 2.0f;
        }
        path.lineTo((pointF2.x - f10) + f5, pointF2.y + f11);
        if (i2 > 0) {
            float f15 = pointF2.x - f10;
            float f16 = pointF2.y + f11;
            float f17 = f5 * f8;
            rectF.set(f15, f16 - f17, f17 + f15, f16);
            path.arcTo(rectF, 90.0f, 90.0f, false);
        }
        path.lineTo(pointF2.x - f10, (pointF2.y - f11) + f5);
        if (i2 > 0) {
            float f18 = pointF2.x - f10;
            float f19 = pointF2.y - f11;
            float f20 = f5 * f8;
            rectF.set(f18, f19, f18 + f20, f20 + f19);
            path.arcTo(rectF, 180.0f, 90.0f, false);
        }
        path.lineTo((pointF2.x + f10) - f5, pointF2.y - f11);
        if (i2 > 0) {
            float f21 = pointF2.x + f10;
            float f22 = f5 * f8;
            float f23 = pointF2.y - f11;
            rectF.set(f21 - f22, f23, f21, f22 + f23);
            path.arcTo(rectF, 270.0f, 90.0f, false);
        }
        path.close();
        this.f2188i.c(path);
        this.k = true;
        return path;
    }
}
