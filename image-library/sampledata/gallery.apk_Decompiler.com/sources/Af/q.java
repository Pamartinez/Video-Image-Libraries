package Af;

import Qe.C0819i;
import Qf.b;
import Te.K;
import Ye.a;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q implements p {
    public Collection a(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return C1202t.d;
    }

    public Set b() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object next : d(f.f3312p, b.d)) {
            if (next instanceof K) {
                C1240g name = ((K) next).getName();
                j.d(name, "getName(...)");
                linkedHashSet.add(name);
            }
        }
        return linkedHashSet;
    }

    public C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return null;
    }

    public Collection d(f fVar, Ae.b bVar) {
        j.e(fVar, "kindFilter");
        return C1202t.d;
    }

    public Set e() {
        return null;
    }

    public Collection f(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return C1202t.d;
    }

    public Set g() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object next : d(f.q, b.d)) {
            if (next instanceof K) {
                C1240g name = ((K) next).getName();
                j.d(name, "getName(...)");
                linkedHashSet.add(name);
            }
        }
        return linkedHashSet;
    }
}
