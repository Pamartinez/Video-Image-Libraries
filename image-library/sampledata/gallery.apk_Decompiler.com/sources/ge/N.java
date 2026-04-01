package ge;

import E2.q;
import P1.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class N implements Runnable {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ e e;

    public N(e eVar, boolean z) {
        this.e = eVar;
        this.d = z;
    }

    public final void run() {
        P p6 = (P) this.e.f;
        if (this.d) {
            p6.f4469o = true;
            if (p6.l > 0) {
                q qVar = p6.n;
                qVar.f175a = false;
                qVar.a();
            }
        }
        p6.t = false;
    }
}
