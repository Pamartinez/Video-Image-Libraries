package ge;

import B2.A;
import P1.e;
import ee.M;
import ee.a0;
import fe.i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K implements C1056t {
    public final C1056t d;
    public volatile boolean e;
    public List f = new ArrayList();

    public K(C1056t tVar) {
        this.d = tVar;
    }

    public final void a(Runnable runnable) {
        synchronized (this) {
            try {
                if (!this.e) {
                    this.f.add(runnable);
                } else {
                    runnable.run();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final void b0() {
        if (this.e) {
            this.d.b0();
        } else {
            a(new A(5, (Object) this));
        }
    }

    public final void h0(i iVar) {
        if (this.e) {
            this.d.h0(iVar);
        } else {
            a(new e(19, this, iVar));
        }
    }

    public final void u0(a0 a0Var, C1054s sVar, M m) {
        a(new J(this, a0Var, sVar, m, 0));
    }

    public final void z(M m) {
        a(new e(20, this, m));
    }
}
