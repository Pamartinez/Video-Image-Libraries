package Af;

import Ae.b;
import He.F;
import Qe.C0819i;
import Qe.C0820j;
import Qe.C0834y;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1200r;
import ne.C1202t;
import ne.v;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements p {
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final p[] f3303c;

    public a(String str, p[] pVarArr) {
        this.b = str;
        this.f3303c = pVarArr;
    }

    public final Collection a(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        p[] pVarArr = this.f3303c;
        int length = pVarArr.length;
        if (length == 0) {
            return C1202t.d;
        }
        if (length == 1) {
            return pVarArr[0].a(gVar, aVar);
        }
        Collection collection = null;
        for (p a7 : pVarArr) {
            collection = F.w(collection, a7.a(gVar, aVar));
        }
        if (collection == null) {
            return v.d;
        }
        return collection;
    }

    public final Set b() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p b5 : this.f3303c) {
            C1200r.A0(b5.b(), linkedHashSet);
        }
        return linkedHashSet;
    }

    public final C0819i c(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        C0819i iVar = null;
        for (p c5 : this.f3303c) {
            C0819i c6 = c5.c(gVar, aVar);
            if (c6 != null) {
                if (!(c6 instanceof C0820j) || !((C0834y) c6).b0()) {
                    return c6;
                }
                if (iVar == null) {
                    iVar = c6;
                }
            }
        }
        return iVar;
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        p[] pVarArr = this.f3303c;
        int length = pVarArr.length;
        if (length == 0) {
            return C1202t.d;
        }
        if (length == 1) {
            return pVarArr[0].d(fVar, bVar);
        }
        Collection collection = null;
        for (p d : pVarArr) {
            collection = F.w(collection, d.d(fVar, bVar));
        }
        if (collection == null) {
            return v.d;
        }
        return collection;
    }

    public final Set e() {
        return Gd.a.t(C1192j.Z(this.f3303c));
    }

    public final Collection f(C1240g gVar, Ye.a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        p[] pVarArr = this.f3303c;
        int length = pVarArr.length;
        if (length == 0) {
            return C1202t.d;
        }
        if (length == 1) {
            return pVarArr[0].f(gVar, aVar);
        }
        Collection collection = null;
        for (p f : pVarArr) {
            collection = F.w(collection, f.f(gVar, aVar));
        }
        if (collection == null) {
            return v.d;
        }
        return collection;
    }

    public final Set g() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (p g : this.f3303c) {
            C1200r.A0(g.g(), linkedHashSet);
        }
        return linkedHashSet;
    }

    public final String toString() {
        return this.b;
    }
}
