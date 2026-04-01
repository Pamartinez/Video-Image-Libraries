package Vf;

import Ae.c;
import kotlin.jvm.internal.j;
import o1.C0246a;
import qe.C1230f;
import qe.C1231g;
import qe.C1232h;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D0 implements C1230f, C1231g {
    public static final D0 d = new Object();

    public final Object fold(Object obj, c cVar) {
        return cVar.invoke(obj, this);
    }

    public final C1230f get(C1231g gVar) {
        j.e(gVar, "key");
        if (j.a(this, gVar)) {
            return this;
        }
        return null;
    }

    public final C1232h minusKey(C1231g gVar) {
        return C0246a.h0(this, gVar);
    }

    public final C1232h plus(C1232h hVar) {
        j.e(hVar, "context");
        if (hVar == C1233i.d) {
            return this;
        }
        return (C1232h) hVar.fold(this, new C0884v(9));
    }

    public final C1231g getKey() {
        return this;
    }
}
