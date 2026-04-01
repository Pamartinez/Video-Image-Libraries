package ge;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: ge.z0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1069z0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ B0 e;

    public /* synthetic */ C1069z0(B0 b0, int i2) {
        this.d = i2;
        this.e = b0;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                B0 b0 = this.e;
                F0 f02 = b0.d;
                AtomicReference atomicReference = b0.f4382a;
                if (f02.B == null) {
                    if (atomicReference.get() == F0.f4402i0) {
                        atomicReference.set((Object) null);
                    }
                    b0.d.f4406F.P(F0.f4400f0);
                    return;
                }
                return;
            default:
                this.e.d.k();
                return;
        }
    }
}
