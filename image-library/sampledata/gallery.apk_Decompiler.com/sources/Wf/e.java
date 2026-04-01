package Wf;

import Vf.A0;
import Vf.C0867e0;
import Vf.C0875l;
import Vf.C0887y;
import Vf.M;
import Vf.O;
import Vf.r0;
import android.os.Handler;
import android.os.Looper;
import cg.n;
import eg.f;
import i.C0212a;
import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.j;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends f {
    public final Handler d;
    public final boolean e;
    public final e f;

    public e(boolean z, Handler handler) {
        e eVar;
        this.d = handler;
        this.e = z;
        if (z) {
            eVar = this;
        } else {
            eVar = new e(true, handler);
        }
        this.f = eVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (eVar.d == this.d && eVar.e == this.e) {
            return true;
        }
        return false;
    }

    public final O f(long j2, A0 a0, C1232h hVar) {
        if (j2 > 4611686018427387903L) {
            j2 = 4611686018427387903L;
        }
        if (this.d.postDelayed(a0, j2)) {
            return new d(this, a0);
        }
        l(hVar, a0);
        return r0.d;
    }

    public final void h(long j2, C0875l lVar) {
        P1.e eVar = new P1.e(lVar, this, false, 4);
        if (j2 > 4611686018427387903L) {
            j2 = 4611686018427387903L;
        }
        if (this.d.postDelayed(eVar, j2)) {
            lVar.t(new c(0, this, eVar));
        } else {
            l(lVar.f3866h, eVar);
        }
    }

    public final int hashCode() {
        int i2;
        int identityHashCode = System.identityHashCode(this.d);
        if (this.e) {
            i2 = 1231;
        } else {
            i2 = 1237;
        }
        return i2 ^ identityHashCode;
    }

    public final void i(C1232h hVar, Runnable runnable) {
        if (!this.d.post(runnable)) {
            l(hVar, runnable);
        }
    }

    public final boolean j(C1232h hVar) {
        if (!this.e || !j.a(Looper.myLooper(), this.d.getLooper())) {
            return true;
        }
        return false;
    }

    public final void l(C1232h hVar, Runnable runnable) {
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        C0867e0 e0Var = (C0867e0) hVar.get(C0887y.e);
        if (e0Var != null) {
            e0Var.a(cancellationException);
        }
        f fVar = M.f3843a;
        eg.e.d.i(hVar, runnable);
    }

    public final String toString() {
        String str;
        e eVar;
        f fVar = M.f3843a;
        e eVar2 = n.f4030a;
        if (this == eVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                eVar = eVar2.f;
            } catch (UnsupportedOperationException unused) {
                eVar = null;
            }
            if (this == eVar) {
                str = "Dispatchers.Main.immediate";
            } else {
                str = null;
            }
        }
        if (str != null) {
            return str;
        }
        String handler = this.d.toString();
        if (this.e) {
            return C0212a.A(handler, ".immediate");
        }
        return handler;
    }
}
