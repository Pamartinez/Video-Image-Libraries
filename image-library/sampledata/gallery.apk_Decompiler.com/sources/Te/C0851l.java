package Te;

import Ae.b;
import Qe.C0833x;
import Qe.K;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1236c;

/* renamed from: Te.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0851l implements K {

    /* renamed from: a  reason: collision with root package name */
    public final List f3784a;
    public final String b;

    public C0851l(List list, String str) {
        j.e(str, "debugName");
        this.f3784a = list;
        this.b = str;
        list.size();
        C1194l.p1(list).size();
    }

    public final void a(C1236c cVar, ArrayList arrayList) {
        j.e(cVar, "fqName");
        for (K b5 : this.f3784a) {
            C0833x.b(b5, cVar, arrayList);
        }
    }

    public final boolean b(C1236c cVar) {
        j.e(cVar, "fqName");
        Iterable<K> iterable = this.f3784a;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        for (K h5 : iterable) {
            if (!C0833x.h(h5, cVar)) {
                return false;
            }
        }
        return true;
    }

    public final Collection m(C1236c cVar, b bVar) {
        j.e(cVar, "fqName");
        HashSet hashSet = new HashSet();
        for (K m : this.f3784a) {
            hashSet.addAll(m.m(cVar, bVar));
        }
        return hashSet;
    }

    public final String toString() {
        return this.b;
    }
}
