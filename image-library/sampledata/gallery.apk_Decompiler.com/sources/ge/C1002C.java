package ge;

import B2.A;
import P1.e;
import S1.j;
import ee.C0971d;
import ee.M;
import ee.a0;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ge.C  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1002C extends C0971d {

    /* renamed from: c  reason: collision with root package name */
    public final C0971d f4386c;
    public volatile boolean d;
    public List e = new ArrayList();

    public C1002C(C0971d dVar) {
        this.f4386c = dVar;
    }

    public final void d(a0 a0Var, M m) {
        k(new j(this, a0Var, m, 3));
    }

    public final void f(M m) {
        if (this.d) {
            this.f4386c.f(m);
        } else {
            k(new e(11, this, m));
        }
    }

    public final void g(Object obj) {
        if (this.d) {
            this.f4386c.g(obj);
        } else {
            k(new e(12, this, obj));
        }
    }

    public final void h() {
        if (this.d) {
            this.f4386c.h();
        } else {
            k(new A(4, (Object) this));
        }
    }

    public final void k(Runnable runnable) {
        synchronized (this) {
            try {
                if (!this.d) {
                    this.e.add(runnable);
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
}
