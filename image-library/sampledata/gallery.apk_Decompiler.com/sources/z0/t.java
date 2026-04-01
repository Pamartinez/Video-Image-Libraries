package z0;

import A0.a;
import A0.o;
import C0.f;
import D0.c;
import D0.e;
import E0.y;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t implements n, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2197a = new Path();
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f2198c;
    public final w d;
    public final o e;
    public boolean f;
    public final c g = new c();

    public t(w wVar, F0.c cVar, E0.t tVar) {
        this.b = tVar.f153a;
        this.f2198c = tVar.d;
        this.d = wVar;
        o oVar = new o((List) tVar.f154c.e);
        this.e = oVar;
        cVar.f(oVar);
        oVar.a(this);
    }

    public final void a() {
        this.f = false;
        this.d.invalidateSelf();
    }

    public final void b(List list, List list2) {
        ArrayList arrayList = null;
        int i2 = 0;
        while (true) {
            ArrayList arrayList2 = (ArrayList) list;
            if (i2 < arrayList2.size()) {
                c cVar = (c) arrayList2.get(i2);
                if (cVar instanceof v) {
                    v vVar = (v) cVar;
                    if (vVar.f2200c == y.SIMULTANEOUSLY) {
                        this.g.d.add(vVar);
                        vVar.c(this);
                        i2++;
                    }
                }
                if (cVar instanceof s) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((s) cVar);
                }
                i2++;
            } else {
                this.e.m = arrayList;
                return;
            }
        }
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(e eVar, Object obj) {
        if (obj == C0319A.f2033K) {
            this.e.k(eVar);
        }
    }

    public final String getName() {
        return this.b;
    }

    public final Path getPath() {
        boolean z = this.f;
        o oVar = this.e;
        Path path = this.f2197a;
        if (z && oVar.e == null) {
            return path;
        }
        path.reset();
        if (this.f2198c) {
            this.f = true;
            return path;
        }
        Path path2 = (Path) oVar.f();
        if (path2 == null) {
            return path;
        }
        path.set(path2);
        path.setFillType(Path.FillType.EVEN_ODD);
        this.g.c(path);
        this.f = true;
        return path;
    }
}
