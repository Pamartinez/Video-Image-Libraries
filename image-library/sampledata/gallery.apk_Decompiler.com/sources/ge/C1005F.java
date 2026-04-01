package ge;

import G0.c;
import ee.C0979l;
import ee.a0;
import io.grpc.a;
import java.util.ArrayList;

/* renamed from: ge.F  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1005F extends L {
    public final C1014c1 m;
    public final C0979l n = C0979l.b();

    /* renamed from: o  reason: collision with root package name */
    public final a[] f4396o;

    /* renamed from: p  reason: collision with root package name */
    public final /* synthetic */ G f4397p;

    public C1005F(G g, C1014c1 c1Var, a[] aVarArr) {
        this.f4397p = g;
        this.m = c1Var;
        this.f4396o = aVarArr;
    }

    public final void e(c cVar) {
        if (Boolean.TRUE.equals(this.m.f4498a.g)) {
            ((ArrayList) cVar.e).add("wait_for_ready");
        }
        super.e(cVar);
    }

    public final void i() {
        for (a aVar : this.f4396o) {
            aVar.getClass();
        }
    }

    public final void v(a0 a0Var) {
        super.v(a0Var);
        synchronized (this.f4397p.b) {
            try {
                G g = this.f4397p;
                if (g.g != null) {
                    boolean remove = g.f4430i.remove(this);
                    if (!this.f4397p.g() && remove) {
                        G g3 = this.f4397p;
                        g3.d.b(g3.f);
                        G g10 = this.f4397p;
                        if (g10.f4431j != null) {
                            g10.d.b(g10.g);
                            this.f4397p.g = null;
                        }
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.f4397p.d.a();
    }
}
