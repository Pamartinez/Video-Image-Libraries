package ge;

import D0.e;
import ee.C0975h;
import ee.a0;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: ge.d0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1016d0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ a0 e;
    public final /* synthetic */ C1031i0 f;

    public /* synthetic */ C1016d0(C1031i0 i0Var, a0 a0Var, int i2) {
        this.d = i2;
        this.f = i0Var;
        this.e = a0Var;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                C0975h hVar = this.f.w.f4298a;
                C0975h hVar2 = C0975h.SHUTDOWN;
                if (hVar != hVar2) {
                    C1031i0 i0Var = this.f;
                    i0Var.f4523x = this.e;
                    C1019e0 e0Var = i0Var.v;
                    C1031i0 i0Var2 = this.f;
                    C1019e0 e0Var2 = i0Var2.u;
                    i0Var2.v = null;
                    C1031i0 i0Var3 = this.f;
                    i0Var3.u = null;
                    C1031i0.f(i0Var3, hVar2);
                    this.f.l.d();
                    if (this.f.s.isEmpty()) {
                        C1031i0 i0Var4 = this.f;
                        i0Var4.k.execute(new C1013c0(i0Var4, 2));
                    }
                    C1031i0 i0Var5 = this.f;
                    i0Var5.k.d();
                    e eVar = i0Var5.f4522p;
                    if (eVar != null) {
                        eVar.B();
                        i0Var5.f4522p = null;
                        i0Var5.n = null;
                    }
                    e eVar2 = this.f.q;
                    if (eVar2 != null) {
                        eVar2.B();
                        this.f.r.d(this.e);
                        C1031i0 i0Var6 = this.f;
                        i0Var6.q = null;
                        i0Var6.r = null;
                    }
                    if (e0Var != null) {
                        e0Var.d(this.e);
                    }
                    if (e0Var2 != null) {
                        e0Var2.d(this.e);
                        return;
                    }
                    return;
                }
                return;
            default:
                Iterator it = new ArrayList(this.f.s).iterator();
                while (it.hasNext()) {
                    ((P0) it.next()).b(this.e);
                }
                return;
        }
    }
}
