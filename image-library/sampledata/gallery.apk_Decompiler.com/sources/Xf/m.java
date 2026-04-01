package Xf;

import Qe.B;
import Vf.E0;
import cg.s;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.jvm.internal.j;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends s {
    public final e e;
    public final /* synthetic */ AtomicReferenceArray f = new AtomicReferenceArray(g.b * 2);

    public m(long j2, m mVar, e eVar, int i2) {
        super(j2, mVar, i2);
        this.e = eVar;
    }

    public final int g() {
        return g.b;
    }

    public final void h(int i2, C1232h hVar) {
        boolean z;
        e eVar;
        B b;
        int i7 = g.b;
        if (i2 >= i7) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            i2 -= i7;
        }
        this.f.get(i2 * 2);
        while (true) {
            Object l = l(i2);
            boolean z3 = l instanceof E0;
            eVar = this.e;
            if (z3 || (l instanceof v)) {
                if (z) {
                    b = g.f3906j;
                } else {
                    b = g.k;
                }
                if (k(i2, l, b)) {
                    n(i2, (Object) null);
                    m(i2, !z);
                    if (z) {
                        j.b(eVar);
                        return;
                    }
                    return;
                }
            } else if (l == g.f3906j || l == g.k) {
                n(i2, (Object) null);
            } else if (!(l == g.g || l == g.f)) {
                if (l != g.f3905i && l != g.d && l != g.l) {
                    throw new IllegalStateException(("unexpected state: " + l).toString());
                }
                return;
            }
        }
        n(i2, (Object) null);
        if (z) {
            j.b(eVar);
        }
    }

    public final boolean k(int i2, Object obj, Object obj2) {
        AtomicReferenceArray atomicReferenceArray;
        int i7 = (i2 * 2) + 1;
        do {
            atomicReferenceArray = this.f;
            if (atomicReferenceArray.compareAndSet(i7, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceArray.get(i7) == obj);
        return false;
    }

    public final Object l(int i2) {
        return this.f.get((i2 * 2) + 1);
    }

    public final void m(int i2, boolean z) {
        if (z) {
            e eVar = this.e;
            j.b(eVar);
            eVar.G((this.f4032c * ((long) g.b)) + ((long) i2));
        }
        i();
    }

    public final void n(int i2, Object obj) {
        this.f.set(i2 * 2, obj);
    }

    public final void o(int i2, Object obj) {
        this.f.set((i2 * 2) + 1, obj);
    }
}
