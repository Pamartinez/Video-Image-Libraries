package Zf;

import qe.C1227c;
import qe.C1232h;
import se.C1272d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements C1227c, C1272d {
    public final C1227c d;
    public final C1232h e;

    public p(C1227c cVar, C1232h hVar) {
        this.d = cVar;
        this.e = hVar;
    }

    public final C1272d getCallerFrame() {
        C1227c cVar = this.d;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public final C1232h getContext() {
        return this.e;
    }

    public final void resumeWith(Object obj) {
        this.d.resumeWith(obj);
    }
}
