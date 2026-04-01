package ge;

import D0.f;
import ee.a0;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Future;

/* renamed from: ge.i1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1032i1 implements Runnable {
    public final /* synthetic */ Collection d;
    public final /* synthetic */ v1 e;
    public final /* synthetic */ Future f;
    public final /* synthetic */ Future g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C1057t0 f4524h;

    public C1032i1(C1057t0 t0Var, Collection collection, v1 v1Var, Future future, Future future2) {
        this.f4524h = t0Var;
        this.d = collection;
        this.e = v1Var;
        this.f = future;
        this.g = future2;
    }

    public final void run() {
        a0 a0Var;
        for (v1 v1Var : this.d) {
            if (v1Var != this.e) {
                v1Var.f4567a.v(C1057t0.f4549J);
            }
        }
        Future future = this.f;
        if (future != null) {
            future.cancel(false);
        }
        Future future2 = this.g;
        if (future2 != null) {
            future2.cancel(false);
        }
        C1057t0 t0Var = this.f4524h;
        f fVar = t0Var.f4554G.b.f4406F;
        synchronized (fVar.e) {
            try {
                ((HashSet) fVar.f).remove(t0Var);
                if (((HashSet) fVar.f).isEmpty()) {
                    a0Var = (a0) fVar.g;
                    fVar.f = new HashSet();
                } else {
                    a0Var = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (a0Var != null) {
            ((F0) fVar.f106h).E.d(a0Var);
        }
    }
}
