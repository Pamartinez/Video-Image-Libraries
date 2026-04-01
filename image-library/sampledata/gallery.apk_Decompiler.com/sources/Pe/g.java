package Pe;

import Ae.b;
import B1.a;
import D1.f;
import Df.E;
import Gf.h;
import Gf.i;
import Gf.m;
import He.t;
import Ne.q;
import Qe.C0816f;
import Se.c;
import Te.C0850k;
import Te.z;
import java.util.Collection;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements c {
    public static final e d = new Object();
    public static final /* synthetic */ t[] e;
    public static final C1236c f = q.l;
    public static final C1240g g;

    /* renamed from: h  reason: collision with root package name */
    public static final C1235b f3638h;

    /* renamed from: a  reason: collision with root package name */
    public final z f3639a;
    public final b b = f.e;

    /* renamed from: c  reason: collision with root package name */
    public final i f3640c;

    /* JADX WARNING: type inference failed for: r0v2, types: [Pe.e, java.lang.Object] */
    static {
        w wVar = v.f4727a;
        e = new t[]{wVar.f(new p(wVar.b(g.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
        C1238e eVar = Ne.p.f3566c;
        C1240g f5 = eVar.f();
        j.d(f5, "shortName(...)");
        g = f5;
        C1236c g3 = eVar.g();
        C1236c e7 = g3.e();
        C1240g f8 = g3.f();
        j.d(f8, "shortName(...)");
        f3638h = new C1235b(e7, f8);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [Gf.h, Gf.i] */
    public g(m mVar, z zVar) {
        this.f3639a = zVar;
        this.f3640c = new h(mVar, new E(7, (Object) this, (Object) mVar));
    }

    public final C0816f a(C1235b bVar) {
        j.e(bVar, "classId");
        if (!bVar.equals(f3638h)) {
            return null;
        }
        return (C0850k) f.y(this.f3640c, e[0]);
    }

    public final boolean b(C1236c cVar, C1240g gVar) {
        j.e(cVar, "packageFqName");
        j.e(gVar, "name");
        if (!gVar.equals(g) || !cVar.equals(f)) {
            return false;
        }
        return true;
    }

    public final Collection c(C1236c cVar) {
        j.e(cVar, "packageFqName");
        if (!cVar.equals(f)) {
            return ne.v.d;
        }
        return a.S((C0850k) f.y(this.f3640c, e[0]));
    }
}
