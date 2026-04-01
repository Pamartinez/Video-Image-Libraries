package qe;

import Ae.c;
import Vf.C0884v;
import kotlin.jvm.internal.j;
import o1.C0246a;

/* renamed from: qe.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1225a implements C1230f {
    private final C1231g key;

    public C1225a(C1231g gVar) {
        this.key = gVar;
    }

    public <R> R fold(R r, c cVar) {
        j.e(cVar, "operation");
        return cVar.invoke(r, this);
    }

    public C1230f get(C1231g gVar) {
        j.e(gVar, "key");
        if (j.a(getKey(), gVar)) {
            return this;
        }
        return null;
    }

    public C1231g getKey() {
        return this.key;
    }

    public C1232h minusKey(C1231g gVar) {
        return C0246a.h0(this, gVar);
    }

    public C1232h plus(C1232h hVar) {
        j.e(hVar, "context");
        if (hVar == C1233i.d) {
            return this;
        }
        return (C1232h) hVar.fold(this, new C0884v(9));
    }
}
