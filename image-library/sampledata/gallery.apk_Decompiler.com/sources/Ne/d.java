package Ne;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final LinkedHashSet f3545a;

    static {
        Set<l> set = l.NUMBER_TYPES;
        ArrayList arrayList = new ArrayList(C1196n.w0(set, 10));
        for (l lVar : set) {
            j.e(lVar, "primitiveType");
            arrayList.add(q.l.c(lVar.f()));
        }
        ArrayList Y02 = C1194l.Y0(C1194l.Y0(C1194l.Y0(arrayList, p.f.g()), p.f3568h.g()), p.f3570j.g());
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = Y02.iterator();
        while (it.hasNext()) {
            C1236c cVar = (C1236c) it.next();
            j.e(cVar, "topLevelFqName");
            C1236c e = cVar.e();
            C1240g f = cVar.f();
            j.d(f, "shortName(...)");
            linkedHashSet.add(new C1235b(e, f));
        }
        f3545a = linkedHashSet;
    }
}
