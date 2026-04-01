package Af;

import Ae.b;
import Hf.T;
import Hf.X;
import L1.d;
import L2.a;
import Qe.C0819i;
import Qe.C0822l;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.m;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements p {
    public final p b;

    /* renamed from: c  reason: collision with root package name */
    public final X f3321c;
    public HashMap d;
    public final m e = d.q(new g(2, this));

    public u(p pVar, X x9) {
        j.e(pVar, "workerScope");
        j.e(x9, "givenSubstitutor");
        this.b = pVar;
        d.q(new g(1, x9));
        T f = x9.f();
        j.d(f, "getSubstitution(...)");
        this.f3321c = new X(a.I(f));
    }

    public final Collection a(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return i(this.b.a(gVar, aVar));
    }

    public final Set b() {
        return this.b.b();
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        C0819i c5 = this.b.c(gVar, aVar);
        if (c5 != null) {
            return (C0819i) h(c5);
        }
        return null;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        return (Collection) this.e.getValue();
    }

    public final Set e() {
        return this.b.e();
    }

    public final Collection f(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return i(this.b.f(gVar, aVar));
    }

    public final Set g() {
        return this.b.g();
    }

    public final C0822l h(C0822l lVar) {
        X x9 = this.f3321c;
        if (x9.f3438a.e()) {
            return lVar;
        }
        if (this.d == null) {
            this.d = new HashMap();
        }
        HashMap hashMap = this.d;
        j.b(hashMap);
        Object obj = hashMap.get(lVar);
        if (obj == null) {
            if (lVar instanceof Qe.T) {
                obj = ((Qe.T) lVar).c(x9);
                if (obj != null) {
                    hashMap.put(lVar, obj);
                } else {
                    throw new AssertionError("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, but " + lVar + " substitution fails");
                }
            } else {
                throw new IllegalStateException(("Unknown descriptor in scope: " + lVar).toString());
            }
        }
        return (C0822l) obj;
    }

    public final Collection i(Collection collection) {
        if (this.f3321c.f3438a.e() || collection.isEmpty()) {
            return collection;
        }
        int size = collection.size();
        int i2 = 3;
        if (size >= 3) {
            i2 = (size / 3) + size + 1;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(i2);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(h((C0822l) it.next()));
        }
        return linkedHashSet;
    }
}
