package qe;

import Ae.c;
import Vf.C0884v;
import c0.C0086a;
import java.io.Serializable;
import kotlin.jvm.internal.j;

/* renamed from: qe.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1226b implements C1232h, Serializable {
    public final C1232h d;
    public final C1230f e;

    public C1226b(C1232h hVar, C1230f fVar) {
        j.e(hVar, "left");
        j.e(fVar, "element");
        this.d = hVar;
        this.e = fVar;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1226b) {
            C1226b bVar = (C1226b) obj;
            int i2 = 2;
            C1226b bVar2 = bVar;
            int i7 = 2;
            while (true) {
                C1232h hVar = bVar2.d;
                if (hVar instanceof C1226b) {
                    bVar2 = (C1226b) hVar;
                } else {
                    bVar2 = null;
                }
                if (bVar2 == null) {
                    break;
                }
                i7++;
            }
            C1226b bVar3 = this;
            while (true) {
                C1232h hVar2 = bVar3.d;
                if (hVar2 instanceof C1226b) {
                    bVar3 = (C1226b) hVar2;
                } else {
                    bVar3 = null;
                }
                if (bVar3 == null) {
                    break;
                }
                i2++;
            }
            if (i7 == i2) {
                while (true) {
                    C1230f fVar = this.e;
                    if (j.a(bVar.get(fVar.getKey()), fVar)) {
                        C1232h hVar3 = this.d;
                        if (!(hVar3 instanceof C1226b)) {
                            j.c(hVar3, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                            C1230f fVar2 = (C1230f) hVar3;
                            z = j.a(bVar.get(fVar2.getKey()), fVar2);
                            break;
                        }
                        this = (C1226b) hVar3;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Object fold(Object obj, c cVar) {
        return cVar.invoke(this.d.fold(obj, cVar), this.e);
    }

    public final C1230f get(C1231g gVar) {
        j.e(gVar, "key");
        while (true) {
            C1230f fVar = this.e.get(gVar);
            if (fVar != null) {
                return fVar;
            }
            C1232h hVar = this.d;
            if (!(hVar instanceof C1226b)) {
                return hVar.get(gVar);
            }
            this = (C1226b) hVar;
        }
    }

    public final int hashCode() {
        return this.e.hashCode() + this.d.hashCode();
    }

    public final C1232h minusKey(C1231g gVar) {
        j.e(gVar, "key");
        C1230f fVar = this.e;
        C1230f fVar2 = fVar.get(gVar);
        C1232h hVar = this.d;
        if (fVar2 != null) {
            return hVar;
        }
        C1232h minusKey = hVar.minusKey(gVar);
        if (minusKey == hVar) {
            return this;
        }
        if (minusKey == C1233i.d) {
            return fVar;
        }
        return new C1226b(minusKey, fVar);
    }

    public final C1232h plus(C1232h hVar) {
        j.e(hVar, "context");
        if (hVar == C1233i.d) {
            return this;
        }
        return (C1232h) hVar.fold(this, new C0884v(9));
    }

    public final String toString() {
        return C0086a.m(new StringBuilder("["), (String) fold("", new C0884v(8)), ']');
    }
}
