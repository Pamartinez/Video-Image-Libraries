package df;

import Ae.b;
import Af.f;
import Af.m;
import B0.a;
import Df.C0736b;
import Df.p;
import If.l;
import Ne.q;
import Qe.C0813c;
import Qe.C0819i;
import Qe.C0822l;
import Qe.O;
import Qf.k;
import We.o;
import Ye.c;
import cf.C0922a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1200r;
import ne.v;
import o1.C0246a;
import qf.C1240g;
import tf.C1311o;
import tf.C1312p;
import yf.C1357a;

/* renamed from: df.D  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0935D extends C0936E {

    /* renamed from: p  reason: collision with root package name */
    public static final /* synthetic */ int f4236p = 0;
    public final o n;

    /* renamed from: o  reason: collision with root package name */
    public final C0946i f4237o;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0935D(a aVar, o oVar, C0946i iVar) {
        super(aVar, (C0951n) null);
        j.e(oVar, "jClass");
        this.n = oVar;
        this.f4237o = iVar;
    }

    public static O v(O o2) {
        C0813c b = o2.b();
        b.getClass();
        if (b != C0813c.FAKE_OVERRIDE) {
            return o2;
        }
        Collection h5 = o2.h();
        j.d(h5, "getOverriddenDescriptors(...)");
        Iterable<O> iterable = h5;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (O o3 : iterable) {
            j.b(o3);
            arrayList.add(v(o3));
        }
        return (O) C1194l.b1(C1194l.H0(arrayList));
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return null;
    }

    public final Set h(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        return v.d;
    }

    public final Set i(f fVar, m mVar) {
        Collection collection;
        j.e(fVar, "kindFilter");
        Set o12 = C1194l.o1(((C0940c) this.e.invoke()).a());
        C0946i iVar = this.f4237o;
        C0935D x9 = B1.a.x(iVar);
        if (x9 != null) {
            collection = x9.b();
        } else {
            collection = null;
        }
        if (collection == null) {
            collection = v.d;
        }
        o12.addAll(collection);
        if (this.n.f3891a.isEnum()) {
            o12.addAll(C1195m.q0(q.f3575c, q.f3574a));
        }
        a aVar = this.b;
        o12.addAll(((C1357a) ((C0922a) aVar.d).f4013x).g(iVar, aVar));
        return o12;
    }

    public final void j(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
        a aVar = this.b;
        ((C1357a) ((C0922a) aVar.d).f4013x).d(this.f4237o, gVar, arrayList, aVar);
    }

    public final C0940c k() {
        return new C0938a(this.n, C0949l.g);
    }

    public final void m(LinkedHashSet linkedHashSet, C1240g gVar) {
        Collection p12;
        j.e(gVar, "name");
        C0946i iVar = this.f4237o;
        C0935D x9 = B1.a.x(iVar);
        if (x9 == null) {
            p12 = v.d;
        } else {
            p12 = C1194l.p1(x9.a(gVar, c.WHEN_GET_SUPER_MEMBERS));
        }
        Collection collection = p12;
        C0922a aVar = (C0922a) this.b.d;
        LinkedHashSet linkedHashSet2 = linkedHashSet;
        C1240g gVar2 = gVar;
        linkedHashSet2.addAll(com.samsung.context.sdk.samsunganalytics.internal.sender.c.P(gVar2, collection, linkedHashSet2, this.f4237o, aVar.f, ((l) aVar.u).d));
        if (!this.n.f3891a.isEnum()) {
            return;
        }
        if (gVar2.equals(q.f3575c)) {
            linkedHashSet2.add(C1312p.i(iVar));
        } else if (gVar2.equals(q.f3574a)) {
            linkedHashSet2.add(C1312p.j(iVar));
        }
    }

    public final void n(ArrayList arrayList, C1240g gVar) {
        j.e(gVar, "name");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        C0736b bVar = new C0736b(20, gVar);
        C0946i iVar = this.f4237o;
        k.f(C0246a.e0(iVar), C0933B.d, new C0934C(iVar, linkedHashSet, bVar));
        boolean isEmpty = arrayList.isEmpty();
        a aVar = this.b;
        if (!isEmpty) {
            C0922a aVar2 = (C0922a) aVar.d;
            p pVar = aVar2.f;
            C1311o oVar = ((l) aVar2.u).d;
            C1240g gVar2 = gVar;
            arrayList.addAll(com.samsung.context.sdk.samsunganalytics.internal.sender.c.P(gVar2, linkedHashSet, arrayList, this.f4237o, pVar, oVar));
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object next : linkedHashSet) {
                O v = v((O) next);
                Object obj = linkedHashMap.get(v);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(v, obj);
                }
                ((List) obj).add(next);
            }
            ArrayList arrayList2 = new ArrayList();
            for (Map.Entry value : linkedHashMap.entrySet()) {
                C0922a aVar3 = (C0922a) aVar.d;
                p pVar2 = aVar3.f;
                C1311o oVar2 = ((l) aVar3.u).d;
                C1240g gVar3 = gVar;
                C1200r.A0(com.samsung.context.sdk.samsunganalytics.internal.sender.c.P(gVar3, (Collection) value.getValue(), arrayList, this.f4237o, pVar2, oVar2), arrayList2);
            }
            arrayList.addAll(arrayList2);
        }
        if (this.n.f3891a.isEnum() && gVar.equals(q.b)) {
            k.a(arrayList, C1312p.h(iVar));
        }
    }

    public final Set o(f fVar) {
        j.e(fVar, "kindFilter");
        Set o12 = C1194l.o1(((C0940c) this.e.invoke()).c());
        C0949l lVar = C0949l.f4251h;
        C0946i iVar = this.f4237o;
        k.f(C0246a.e0(iVar), C0933B.d, new C0934C(iVar, o12, lVar));
        if (this.n.f3891a.isEnum()) {
            o12.add(q.b);
        }
        return o12;
    }

    public final C0822l q() {
        return this.f4237o;
    }
}
