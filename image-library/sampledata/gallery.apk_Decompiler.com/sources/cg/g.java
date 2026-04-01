package cg;

import N2.j;
import P1.e;
import Vf.A0;
import Vf.C0875l;
import Vf.C0886x;
import Vf.F;
import Vf.I;
import Vf.O;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends C0886x implements I {

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f4023j = AtomicIntegerFieldUpdater.newUpdater(g.class, "runningWorkers$volatile");
    public final /* synthetic */ I d;
    public final C0886x e;
    public final int f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final k f4024h;

    /* renamed from: i  reason: collision with root package name */
    public final Object f4025i;
    private volatile /* synthetic */ int runningWorkers$volatile;

    public g(C0886x xVar, int i2, String str) {
        I i7;
        if (xVar instanceof I) {
            i7 = (I) xVar;
        } else {
            i7 = null;
        }
        this.d = i7 == null ? F.f3841a : i7;
        this.e = xVar;
        this.f = i2;
        this.g = str;
        this.f4024h = new k();
        this.f4025i = new Object();
    }

    public final void dispatchYield(C1232h hVar, Runnable runnable) {
        Runnable l;
        this.f4024h.a(runnable);
        if (f4023j.get(this) < this.f && m() && (l = l()) != null) {
            this.e.dispatchYield(this, new e(6, this, l));
        }
    }

    public final O f(long j2, A0 a0, C1232h hVar) {
        return this.d.f(j2, a0, hVar);
    }

    public final void h(long j2, C0875l lVar) {
        this.d.h(j2, lVar);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        Runnable l;
        this.f4024h.a(runnable);
        if (f4023j.get(this) < this.f && m() && (l = l()) != null) {
            this.e.i(this, new e(6, this, l));
        }
    }

    public final Runnable l() {
        while (true) {
            Runnable runnable = (Runnable) this.f4024h.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f4025i) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4023j;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f4024h.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        if (i2 < this.f) {
            return super.limitedParallelism(i2, str);
        }
        if (str != null) {
            return new o(this, str);
        }
        return this;
    }

    public final boolean m() {
        synchronized (this.f4025i) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4023j;
            if (atomicIntegerFieldUpdater.get(this) >= this.f) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    public final String toString() {
        String str = this.g;
        if (str != null) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.e);
        sb2.append(".limitedParallelism(");
        return j.e(sb2, this.f, ')');
    }
}
