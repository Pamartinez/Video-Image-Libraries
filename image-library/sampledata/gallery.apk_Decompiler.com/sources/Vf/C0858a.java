package Vf;

import Ae.c;
import Dd.C0730a;
import L1.d;
import cg.a;
import kotlin.jvm.internal.y;
import me.k;
import me.x;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;
import se.C1269a;

/* renamed from: Vf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0858a extends n0 implements C1227c, A {
    public final C1232h f;

    public C0858a(C1232h hVar, boolean z) {
        super(z);
        C((C0867e0) hVar.get(C0887y.e));
        this.f = hVar.plus(this);
    }

    public final void B(C0730a aVar) {
        D.l(aVar, this.f);
    }

    public final void M(Object obj) {
        boolean z;
        if (obj instanceof C0883u) {
            C0883u uVar = (C0883u) obj;
            Throwable th = uVar.f3874a;
            if (C0883u.b.get(uVar) != 0) {
                z = true;
            } else {
                z = false;
            }
            V(th, z);
            return;
        }
        W(obj);
    }

    public final void X(C c5, C0858a aVar, c cVar) {
        C1232h hVar;
        Object l;
        Object obj;
        c5.getClass();
        int i2 = B.f3834a[c5.ordinal()];
        x xVar = x.f4917a;
        if (i2 == 1) {
            try {
                a.h(xVar, d.m(d.f(cVar, aVar, this)));
            } catch (Throwable th) {
                resumeWith(L2.a.l(th));
                throw th;
            }
        } else if (i2 == 2) {
            d.m(d.f(cVar, aVar, this)).resumeWith(xVar);
        } else if (i2 == 3) {
            try {
                hVar = this.f;
                l = a.l(hVar, (Object) null);
                if (!(cVar instanceof C1269a)) {
                    obj = d.x(aVar, cVar, this);
                } else {
                    y.c(2, cVar);
                    obj = cVar.invoke(aVar, this);
                }
                a.g(hVar, l);
                if (obj != C1245a.COROUTINE_SUSPENDED) {
                    resumeWith(obj);
                }
            } catch (Throwable th2) {
                resumeWith(L2.a.l(th2));
            }
        } else if (i2 != 4) {
            throw new RuntimeException();
        }
    }

    public final C1232h getContext() {
        return this.f;
    }

    public final C1232h getCoroutineContext() {
        return this.f;
    }

    public final String q() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    public final void resumeWith(Object obj) {
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            obj = new C0883u(a7, false);
        }
        Object I6 = I(obj);
        if (I6 != D.e) {
            l(I6);
        }
    }

    public void W(Object obj) {
    }

    public void V(Throwable th, boolean z) {
    }
}
