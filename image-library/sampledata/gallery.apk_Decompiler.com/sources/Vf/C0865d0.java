package Vf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* renamed from: Vf.d0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0865d0 extends i0 {

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f3856i = AtomicIntegerFieldUpdater.newUpdater(C0865d0.class, "_invoked$volatile");
    private volatile /* synthetic */ int _invoked$volatile = 0;

    /* renamed from: h  reason: collision with root package name */
    public final h0 f3857h;

    public C0865d0(h0 h0Var) {
        this.f3857h = h0Var;
    }

    public final boolean j() {
        return true;
    }

    public final void k(Throwable th) {
        if (f3856i.compareAndSet(this, 0, 1)) {
            this.f3857h.invoke(th);
        }
    }
}
