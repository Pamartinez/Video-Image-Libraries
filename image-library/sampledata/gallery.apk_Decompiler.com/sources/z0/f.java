package z0;

import A0.a;
import A0.e;
import A0.j;
import D0.c;
import E0.y;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements n, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2157a = new Path();
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final w f2158c;
    public final j d;
    public final e e;
    public final E0.a f;
    public final c g = new c();

    /* renamed from: h  reason: collision with root package name */
    public boolean f2159h;

    public f(w wVar, F0.c cVar, E0.a aVar) {
        this.b = aVar.f124a;
        this.f2158c = wVar;
        e p02 = aVar.f125c.p0();
        this.d = (j) p02;
        e p03 = aVar.b.p0();
        this.e = p03;
        this.f = aVar;
        cVar.f(p02);
        cVar.f(p03);
        p02.a(this);
        p03.a(this);
    }

    public final void a() {
        this.f2159h = false;
        this.f2158c.invalidateSelf();
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
                        this.g.d.add(vVar);
                        vVar.c(this);
                    }
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void c(C0.f fVar, int i2, ArrayList arrayList, C0.f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(D0.e eVar, Object obj) {
        if (obj == C0319A.f) {
            this.d.k(eVar);
        } else if (obj == C0319A.f2037i) {
            this.e.k(eVar);
        }
    }

    public final String getName() {
        return this.b;
    }

    public final Path getPath() {
        boolean z = this.f2159h;
        Path path = this.f2157a;
        if (z) {
            return path;
        }
        path.reset();
        E0.a aVar = this.f;
        if (aVar.e) {
            this.f2159h = true;
            return path;
        }
        PointF pointF = (PointF) this.d.f();
        float f5 = pointF.x / 2.0f;
        float f8 = pointF.y / 2.0f;
        float f10 = f5 * 0.55228f;
        float f11 = f8 * 0.55228f;
        path.reset();
        if (aVar.d) {
            float f12 = -f8;
            path.moveTo(0.0f, f12);
            float f13 = 0.0f - f10;
            float f14 = -f5;
            float f15 = 0.0f - f11;
            path.cubicTo(f13, f12, f14, f15, f14, 0.0f);
            float f16 = f12;
            float f17 = f15;
            float f18 = f11 + 0.0f;
            float f19 = f14;
            float f20 = f13;
            float f21 = f19;
            float f22 = f8;
            path.cubicTo(f21, f18, f20, f22, 0.0f, f8);
            float f23 = f10 + 0.0f;
            float f24 = f5;
            path.cubicTo(f23, f22, f24, f18, f5, 0.0f);
            float f25 = f17;
            path.cubicTo(f24, f25, f23, f16, 0.0f, f16);
        } else {
            float f26 = f5;
            float f27 = f8;
            float f28 = -f27;
            path.moveTo(0.0f, f28);
            float f29 = 0.0f - f11;
            float f30 = f26;
            float f31 = f26;
            float f32 = f10 + 0.0f;
            path.cubicTo(f32, f28, f31, f29, f30, 0.0f);
            float f33 = f31;
            float f34 = f32;
            float f35 = f33;
            float f36 = f28;
            float f37 = f29;
            float f38 = f11 + 0.0f;
            float f39 = f27;
            path.cubicTo(f35, f38, f34, f39, 0.0f, f27);
            float f40 = f39;
            float f41 = f38;
            float f42 = f40;
            float f43 = -f35;
            float f44 = f43;
            float f45 = f43;
            float f46 = 0.0f - f10;
            path.cubicTo(f46, f42, f45, f41, f44, 0.0f);
            float f47 = f45;
            float f48 = f46;
            float f49 = f37;
            path.cubicTo(f47, f49, f48, f36, 0.0f, f36);
        }
        PointF pointF2 = (PointF) this.e.f();
        path.offset(pointF2.x, pointF2.y);
        path.close();
        this.g.c(path);
        this.f2159h = true;
        return path;
    }
}
