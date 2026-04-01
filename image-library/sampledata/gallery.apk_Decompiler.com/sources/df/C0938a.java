package df;

import Ae.b;
import Df.C0736b;
import Sf.f;
import Sf.g;
import Sf.n;
import We.A;
import We.o;
import We.u;
import We.x;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import qf.C1240g;

/* renamed from: df.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0938a implements C0940c {

    /* renamed from: a  reason: collision with root package name */
    public final o f4240a;
    public final b b;

    /* renamed from: c  reason: collision with root package name */
    public final C0736b f4241c;
    public final LinkedHashMap d;
    public final LinkedHashMap e;
    public final LinkedHashMap f;

    public C0938a(o oVar, b bVar) {
        j.e(oVar, "jClass");
        this.f4240a = oVar;
        this.b = bVar;
        C0736b bVar2 = new C0736b(18, this);
        this.f4241c = bVar2;
        g p02 = n.p0(C1194l.F0(oVar.d()), bVar2);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        f fVar = new f(p02);
        while (fVar.hasNext()) {
            Object next = fVar.next();
            C1240g c5 = ((x) next).c();
            Object obj = linkedHashMap.get(c5);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(c5, obj);
            }
            ((List) obj).add(next);
        }
        this.d = linkedHashMap;
        g p03 = n.p0(C1194l.F0(this.f4240a.b()), this.b);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        f fVar2 = new f(p03);
        while (fVar2.hasNext()) {
            Object next2 = fVar2.next();
            linkedHashMap2.put(((u) next2).c(), next2);
        }
        this.e = linkedHashMap2;
        ArrayList f5 = this.f4240a.f();
        b bVar3 = this.b;
        ArrayList arrayList = new ArrayList();
        Iterator it = f5.iterator();
        while (it.hasNext()) {
            Object next3 = it.next();
            if (((Boolean) bVar3.invoke(next3)).booleanValue()) {
                arrayList.add(next3);
            }
        }
        int Z = z.Z(C1196n.w0(arrayList, 10));
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(Z < 16 ? 16 : Z);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Object next4 = it2.next();
            linkedHashMap3.put(((A) next4).c(), next4);
        }
        this.f = linkedHashMap3;
    }

    public final Set a() {
        g p02 = n.p0(C1194l.F0(this.f4240a.d()), this.f4241c);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        f fVar = new f(p02);
        while (fVar.hasNext()) {
            linkedHashSet.add(((x) fVar.next()).c());
        }
        return linkedHashSet;
    }

    public final Set b() {
        return this.f.keySet();
    }

    public final Set c() {
        g p02 = n.p0(C1194l.F0(this.f4240a.b()), this.b);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        f fVar = new f(p02);
        while (fVar.hasNext()) {
            linkedHashSet.add(((u) fVar.next()).c());
        }
        return linkedHashSet;
    }

    public final u d(C1240g gVar) {
        j.e(gVar, "name");
        return (u) this.e.get(gVar);
    }

    public final Collection e(C1240g gVar) {
        j.e(gVar, "name");
        List list = (List) this.d.get(gVar);
        if (list != null) {
            return list;
        }
        return C1202t.d;
    }

    public final A f(C1240g gVar) {
        j.e(gVar, "name");
        return (A) this.f.get(gVar);
    }
}
