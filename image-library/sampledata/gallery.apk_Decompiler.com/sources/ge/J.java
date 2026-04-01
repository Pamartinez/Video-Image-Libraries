package ge;

import ee.M;
import ee.a0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f4448h;

    public /* synthetic */ J(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.f4448h = obj;
        this.e = obj2;
        this.f = obj3;
        this.g = obj4;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((K) this.f4448h).d.u0((a0) this.e, (C1054s) this.f, (M) this.g);
                return;
            case 1:
                C1057t0 t0Var = (C1057t0) this.f4448h;
                t0Var.f4552C = true;
                t0Var.f4560x.u0((a0) this.e, (C1054s) this.f, (M) this.g);
                return;
            default:
                synchronized (((L1) this.f4448h)) {
                    try {
                        if (((J1) this.e).b == 0) {
                            ((K1) this.f).d(this.g);
                            ((L1) this.f4448h).f4459a.remove((K1) this.f);
                            if (((L1) this.f4448h).f4459a.isEmpty()) {
                                ((L1) this.f4448h).f4460c.shutdown();
                                ((L1) this.f4448h).f4460c = null;
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
        }
    }
}
