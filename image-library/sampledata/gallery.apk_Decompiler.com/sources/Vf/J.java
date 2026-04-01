package Vf;

import L1.d;
import cg.a;
import cg.r;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J extends r {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f3842h = AtomicIntegerFieldUpdater.newUpdater(J.class, "_decision$volatile");
    private volatile /* synthetic */ int _decision$volatile;

    public final void k(Object obj) {
        l(obj);
    }

    public final void l(Object obj) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        do {
            atomicIntegerFieldUpdater = f3842h;
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 != 0) {
                if (i2 == 1) {
                    a.h(D.p(obj), d.m(this.g));
                    return;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, 0, 2));
    }
}
