package cg;

import L1.d;
import Vf.C0858a;
import Vf.D;
import qe.C1227c;
import qe.C1232h;
import se.C1272d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class r extends C0858a implements C1272d {
    public final C1227c g;

    public r(C1227c cVar, C1232h hVar) {
        super(hVar, true);
        this.g = cVar;
    }

    public final boolean F() {
        return true;
    }

    public final C1272d getCallerFrame() {
        C1227c cVar = this.g;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public void k(Object obj) {
        a.h(D.p(obj), d.m(this.g));
    }

    public void l(Object obj) {
        this.g.resumeWith(D.p(obj));
    }
}
