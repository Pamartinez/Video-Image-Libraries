package cg;

import Vf.A0;
import Vf.C0875l;
import Vf.C0886x;
import Vf.F;
import Vf.I;
import Vf.O;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends C0886x implements I {
    public final /* synthetic */ I d;
    public final C0886x e;
    public final String f;

    public o(C0886x xVar, String str) {
        I i2;
        if (xVar instanceof I) {
            i2 = (I) xVar;
        } else {
            i2 = null;
        }
        this.d = i2 == null ? F.f3841a : i2;
        this.e = xVar;
        this.f = str;
    }

    public final void dispatchYield(C1232h hVar, Runnable runnable) {
        this.e.dispatchYield(hVar, runnable);
    }

    public final O f(long j2, A0 a0, C1232h hVar) {
        return this.d.f(j2, a0, hVar);
    }

    public final void h(long j2, C0875l lVar) {
        this.d.h(j2, lVar);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        this.e.i(hVar, runnable);
    }

    public final boolean j(C1232h hVar) {
        return this.e.j(hVar);
    }

    public final String toString() {
        return this.f;
    }
}
