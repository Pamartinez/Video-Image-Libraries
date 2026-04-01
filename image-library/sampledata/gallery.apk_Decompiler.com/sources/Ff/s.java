package Ff;

import Ae.a;
import Ae.b;
import Af.g;
import Af.k;
import Af.q;
import D1.f;
import Df.l;
import Df.n;
import Gf.h;
import Gf.i;
import Gf.m;
import He.t;
import Qe.C0819i;
import Qe.U;
import Ye.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1199q;
import qf.C1235b;
import qf.C1240g;
import tf.C1306j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s extends q {
    public static final /* synthetic */ t[] f;
    public final n b;

    /* renamed from: c  reason: collision with root package name */
    public final r f3397c;
    public final i d;
    public final h e;

    static {
        w wVar = v.f4727a;
        Class<s> cls = s.class;
        f = new t[]{wVar.f(new p(wVar.b(cls), "classNames", "getClassNames$deserialization()Ljava/util/Set;")), wVar.f(new p(wVar.b(cls), "classifierNamesLazy", "getClassifierNamesLazy()Ljava/util/Set;"))};
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [Gf.h, Gf.i] */
    public s(n nVar, List list, List list2, List list3, a aVar) {
        j.e(nVar, "c");
        j.e(list, "functionList");
        j.e(list2, "propertyList");
        j.e(list3, "typeAliasList");
        this.b = nVar;
        l lVar = (l) nVar.f3358a;
        lVar.f3350c.getClass();
        this.f3397c = new r(this, list, list2, list3);
        m mVar = lVar.f3349a;
        k kVar = new k(aVar, 1);
        mVar.getClass();
        this.d = new h(mVar, kVar);
        g gVar = new g(5, this);
        mVar.getClass();
        this.e = new h(mVar, gVar);
    }

    public Collection a(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return this.f3397c.a(gVar, aVar);
    }

    public final Set b() {
        return (Set) f.y(this.f3397c.g, r.f3392j[0]);
    }

    public C0819i c(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        if (q(gVar)) {
            return ((l) this.b.f3358a).b(l(gVar));
        }
        r rVar = this.f3397c;
        if (!rVar.f3394c.keySet().contains(gVar)) {
            return null;
        }
        rVar.getClass();
        return (U) rVar.f.invoke(gVar);
    }

    public final Set e() {
        t tVar = f[1];
        h hVar = this.e;
        j.e(hVar, "<this>");
        j.e(tVar, "p");
        return (Set) hVar.invoke();
    }

    public Collection f(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return this.f3397c.b(gVar, aVar);
    }

    public final Set g() {
        return (Set) f.y(this.f3397c.f3395h, r.f3392j[1]);
    }

    public abstract void h(ArrayList arrayList, b bVar);

    public final Collection i(Af.f fVar, b bVar, c cVar) {
        j.e(fVar, "kindFilter");
        j.e(cVar, "location");
        ArrayList arrayList = new ArrayList(0);
        if (fVar.a(Af.f.f)) {
            h(arrayList, bVar);
        }
        r rVar = this.f3397c;
        rVar.getClass();
        boolean a7 = fVar.a(Af.f.f3310j);
        C1306j jVar = C1306j.e;
        if (a7) {
            ArrayList arrayList2 = new ArrayList();
            for (C1240g gVar : (Set) f.y(rVar.f3395h, r.f3392j[1])) {
                if (((Boolean) bVar.invoke(gVar)).booleanValue()) {
                    arrayList2.addAll(rVar.b(gVar, cVar));
                }
            }
            C1199q.z0(arrayList2, jVar);
            arrayList.addAll(arrayList2);
        }
        if (fVar.a(Af.f.f3309i)) {
            ArrayList arrayList3 = new ArrayList();
            for (C1240g gVar2 : (Set) f.y(rVar.g, r.f3392j[0])) {
                if (((Boolean) bVar.invoke(gVar2)).booleanValue()) {
                    arrayList3.addAll(rVar.a(gVar2, cVar));
                }
            }
            C1199q.z0(arrayList3, jVar);
            arrayList.addAll(arrayList3);
        }
        if (fVar.a(Af.f.l)) {
            for (C1240g gVar3 : m()) {
                if (((Boolean) bVar.invoke(gVar3)).booleanValue()) {
                    Qf.k.a(arrayList, ((l) this.b.f3358a).b(l(gVar3)));
                }
            }
        }
        if (fVar.a(Af.f.g)) {
            for (C1240g gVar4 : rVar.f3394c.keySet()) {
                if (((Boolean) bVar.invoke(gVar4)).booleanValue()) {
                    rVar.getClass();
                    j.e(gVar4, "name");
                    Qf.k.a(arrayList, (U) rVar.f.invoke(gVar4));
                }
            }
        }
        return Qf.k.d(arrayList);
    }

    public void j(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
    }

    public void k(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
    }

    public abstract C1235b l(C1240g gVar);

    public final Set m() {
        return (Set) f.y(this.d, f[0]);
    }

    public abstract Set n();

    public abstract Set o();

    public abstract Set p();

    public boolean q(C1240g gVar) {
        j.e(gVar, "name");
        return m().contains(gVar);
    }

    public boolean r(v vVar) {
        return true;
    }
}
