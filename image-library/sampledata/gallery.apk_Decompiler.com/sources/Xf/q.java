package Xf;

import Df.C0736b;
import Qe.B;
import Vf.C0858a;
import Vf.C0869f0;
import Vf.D;
import Zf.g;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import me.x;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends C0858a implements r, i {
    public final e g;

    public q(C1232h hVar, e eVar) {
        super(hVar, true);
        this.g = eVar;
    }

    public final void V(Throwable th, boolean z) {
        if (!this.g.k(th, false) && !z) {
            D.l(th, this.f);
        }
    }

    public final void W(Object obj) {
        x xVar = (x) obj;
        this.g.j((Throwable) null);
    }

    public final void Y(C0736b bVar) {
        e eVar = this.g;
        eVar.getClass();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e.m;
        while (!atomicReferenceFieldUpdater.compareAndSet(eVar, (Object) null, bVar)) {
            if (atomicReferenceFieldUpdater.get(eVar) != null) {
                while (true) {
                    Object obj = atomicReferenceFieldUpdater.get(eVar);
                    B b = g.q;
                    if (obj == b) {
                        B b5 = g.r;
                        while (true) {
                            if (atomicReferenceFieldUpdater.compareAndSet(eVar, b, b5)) {
                                bVar.invoke(eVar.p());
                                return;
                            } else if (atomicReferenceFieldUpdater.get(eVar) != b) {
                            }
                        }
                    } else if (obj == g.r) {
                        throw new IllegalStateException("Another handler was already registered and successfully invoked");
                    } else {
                        throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
                    }
                }
            }
        }
    }

    public final void a(CancellationException cancellationException) {
        if (!E()) {
            if (cancellationException == null) {
                cancellationException = new C0869f0(q(), (Throwable) null, this);
            }
            o(cancellationException);
        }
    }

    public final Object e(Object obj) {
        return this.g.e(obj);
    }

    public final Object f(g gVar) {
        e eVar = this.g;
        eVar.getClass();
        Object z = e.z(eVar, gVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return z;
    }

    public final Object h() {
        return this.g.h();
    }

    public final Object i(Object obj, C1227c cVar) {
        return this.g.i(obj, cVar);
    }

    public final b iterator() {
        e eVar = this.g;
        eVar.getClass();
        return new b(eVar);
    }

    public final void o(CancellationException cancellationException) {
        this.g.k(cancellationException, true);
        n(cancellationException);
    }
}
