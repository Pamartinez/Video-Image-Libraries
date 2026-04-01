package Vf;

import cg.a;
import cg.f;
import eg.j;
import java.util.concurrent.CancellationException;
import qe.C1227c;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class K extends j {
    public int f;

    public K(int i2) {
        super(0, false);
        this.f = i2;
    }

    public abstract C1227c e();

    public Throwable f(Object obj) {
        C0883u uVar;
        if (obj instanceof C0883u) {
            uVar = (C0883u) obj;
        } else {
            uVar = null;
        }
        if (uVar != null) {
            return uVar.f3874a;
        }
        return null;
    }

    public final void h(Throwable th) {
        D.l(new Error("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th), e().getContext());
    }

    public abstract Object i();

    public final void run() {
        C1232h context;
        Object l;
        C0 c02;
        try {
            C1227c e = e();
            kotlin.jvm.internal.j.c(e, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            f fVar = (f) e;
            C1227c cVar = fVar.f4020h;
            Object obj = fVar.f4022j;
            context = cVar.getContext();
            l = a.l(context, obj);
            C0867e0 e0Var = null;
            if (l != a.d) {
                c02 = D.v(cVar, context, l);
            } else {
                c02 = null;
            }
            C1232h context2 = cVar.getContext();
            Object i2 = i();
            Throwable f5 = f(i2);
            if (f5 == null) {
                int i7 = this.f;
                boolean z = true;
                if (i7 != 1) {
                    if (i7 != 2) {
                        z = false;
                    }
                }
                if (z) {
                    e0Var = (C0867e0) context2.get(C0887y.e);
                }
            }
            if (e0Var != null && !e0Var.isActive()) {
                CancellationException v = ((n0) e0Var).v();
                c(v);
                cVar.resumeWith(L2.a.l(v));
            } else if (f5 != null) {
                cVar.resumeWith(L2.a.l(f5));
            } else {
                cVar.resumeWith(g(i2));
            }
            if (c02 != null) {
                if (!c02.Y()) {
                    return;
                }
            }
            a.g(context, l);
        } catch (Throwable th) {
            h(th);
        }
    }

    public void c(CancellationException cancellationException) {
    }

    public Object g(Object obj) {
        return obj;
    }
}
