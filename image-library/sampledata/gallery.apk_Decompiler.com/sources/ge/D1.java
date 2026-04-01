package ge;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D1 extends I1 {

    /* renamed from: c  reason: collision with root package name */
    public final AtomicIntegerFieldUpdater f4390c;

    public D1(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        this.f4390c = atomicIntegerFieldUpdater;
    }

    public final boolean c(F1 f12) {
        return this.f4390c.compareAndSet(f12, 0, -1);
    }

    public final void d(F1 f12) {
        this.f4390c.set(f12, 0);
    }
}
