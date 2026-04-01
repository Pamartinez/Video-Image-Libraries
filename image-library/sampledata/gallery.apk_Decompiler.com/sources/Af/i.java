package Af;

import Ae.b;
import D1.f;
import Gf.h;
import Gf.m;
import He.t;
import Qe.O;
import Te.C0841b;
import Te.K;
import Ye.a;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1202t;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i extends q {
    public static final /* synthetic */ t[] d;
    public final C0841b b;

    /* renamed from: c  reason: collision with root package name */
    public final Gf.i f3315c;

    static {
        w wVar = v.f4727a;
        d = new t[]{wVar.f(new p(wVar.b(i.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [Gf.h, Gf.i] */
    public i(m mVar, C0841b bVar) {
        j.e(mVar, "storageManager");
        this.b = bVar;
        this.f3315c = new h(mVar, new g(0, this));
    }

    public final Collection a(C1240g gVar, a aVar) {
        Collection collection;
        j.e(gVar, "name");
        j.e(aVar, "location");
        Collection collection2 = (List) f.y(this.f3315c, d[0]);
        if (collection2.isEmpty()) {
            collection = C1202t.d;
        } else {
            Qf.f fVar = new Qf.f();
            for (Object next : collection2) {
                if ((next instanceof K) && j.a(((K) next).getName(), gVar)) {
                    fVar.add(next);
                }
            }
            collection = fVar;
        }
        return collection;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        if (!fVar.a(f.n.b)) {
            return C1202t.d;
        }
        return (List) f.y(this.f3315c, d[0]);
    }

    public final Collection f(C1240g gVar, a aVar) {
        Collection collection;
        j.e(gVar, "name");
        j.e(aVar, "location");
        Collection collection2 = (List) f.y(this.f3315c, d[0]);
        if (collection2.isEmpty()) {
            collection = C1202t.d;
        } else {
            Qf.f fVar = new Qf.f();
            for (Object next : collection2) {
                if ((next instanceof O) && j.a(((O) next).getName(), gVar)) {
                    fVar.add(next);
                }
            }
            collection = fVar;
        }
        return collection;
    }

    public abstract List h();
}
