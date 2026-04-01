package Af;

import Ae.b;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0820j;
import Qe.U;
import Ye.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import ne.C1202t;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends q {
    public final p b;

    public j(p pVar) {
        kotlin.jvm.internal.j.e(pVar, "workerScope");
        this.b = pVar;
    }

    public final Set b() {
        return this.b.b();
    }

    public final C0819i c(C1240g gVar, a aVar) {
        C0816f fVar;
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        C0819i c5 = this.b.c(gVar, aVar);
        if (c5 != null) {
            if (c5 instanceof C0816f) {
                fVar = (C0816f) c5;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                return fVar;
            }
            if (c5 instanceof U) {
                return (U) c5;
            }
        }
        return null;
    }

    public final Collection d(f fVar, b bVar) {
        f fVar2;
        Collection collection;
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        int i2 = f.l & fVar.b;
        if (i2 == 0) {
            fVar2 = null;
        } else {
            fVar2 = new f(i2, fVar.f3313a);
        }
        if (fVar2 == null) {
            collection = C1202t.d;
        } else {
            ArrayList arrayList = new ArrayList();
            for (Object next : this.b.d(fVar2, bVar)) {
                if (next instanceof C0820j) {
                    arrayList.add(next);
                }
            }
            collection = arrayList;
        }
        return collection;
    }

    public final Set e() {
        return this.b.e();
    }

    public final Set g() {
        return this.b.g();
    }

    public final String toString() {
        return "Classes from " + this.b;
    }
}
