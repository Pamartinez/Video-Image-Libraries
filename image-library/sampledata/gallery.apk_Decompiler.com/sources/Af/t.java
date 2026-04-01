package Af;

import Ae.b;
import Ff.k;
import Gf.h;
import Gf.i;
import Gf.m;
import Qe.C0817g;
import Qe.C0819i;
import Qe.O;
import Qf.f;
import Te.K;
import Ye.a;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends q {
    public static final /* synthetic */ He.t[] f;
    public final k b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f3320c;
    public final i d;
    public final i e;

    static {
        w wVar = v.f4727a;
        Class<t> cls = t.class;
        f = new He.t[]{wVar.f(new p(wVar.b(cls), "functions", "getFunctions()Ljava/util/List;")), wVar.f(new p(wVar.b(cls), "properties", "getProperties()Ljava/util/List;"))};
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r4v4, types: [Gf.h, Gf.i] */
    public t(m mVar, k kVar, boolean z) {
        j.e(mVar, "storageManager");
        this.b = kVar;
        this.f3320c = z;
        C0817g gVar = C0817g.CLASS;
        this.d = new h(mVar, new s(this, 0));
        this.e = new h(mVar, new s(this, 1));
    }

    public final Collection a(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        He.t tVar = f[0];
        f fVar = new f();
        for (Object next : (List) D1.f.y(this.d, tVar)) {
            if (j.a(((K) next).getName(), gVar)) {
                fVar.add(next);
            }
        }
        return fVar;
    }

    public final C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return null;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        He.t[] tVarArr = f;
        He.t tVar = tVarArr[0];
        return C1194l.X0((List) D1.f.y(this.e, tVarArr[1]), (List) D1.f.y(this.d, tVar));
    }

    public final Collection f(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        He.t tVar = f[1];
        f fVar = new f();
        for (Object next : (List) D1.f.y(this.e, tVar)) {
            if (j.a(((O) next).getName(), gVar)) {
                fVar.add(next);
            }
        }
        return fVar;
    }
}
