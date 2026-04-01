package cg;

import Vf.s0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s extends b implements s0 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(s.class, "cleanedAndPointers$volatile");

    /* renamed from: c  reason: collision with root package name */
    public final long f4032c;
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    public s(long j2, s sVar, int i2) {
        super(sVar);
        this.f4032c = j2;
        this.cleanedAndPointers$volatile = i2 << 16;
    }

    public final boolean d() {
        if (d.get(this) != g() || c() == null) {
            return false;
        }
        return true;
    }

    public final boolean f() {
        if (d.addAndGet(this, -65536) != g() || c() == null) {
            return false;
        }
        return true;
    }

    public abstract int g();

    public abstract void h(int i2, C1232h hVar);

    public final void i() {
        if (d.incrementAndGet(this) == g()) {
            e();
        }
    }

    public final boolean j() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = d;
            i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 == g() && c() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
