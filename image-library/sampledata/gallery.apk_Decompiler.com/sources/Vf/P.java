package Vf;

import Ae.b;
import L2.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P extends i0 {

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3844h;

    /* renamed from: i  reason: collision with root package name */
    public final Object f3845i;

    public /* synthetic */ P(int i2, Object obj) {
        this.f3844h = i2;
        this.f3845i = obj;
    }

    public final boolean j() {
        switch (this.f3844h) {
            case 0:
                return false;
            case 1:
                return false;
            default:
                return false;
        }
    }

    public final void k(Throwable th) {
        switch (this.f3844h) {
            case 0:
                ((O) this.f3845i).a();
                return;
            case 1:
                ((b) this.f3845i).invoke(th);
                return;
            default:
                j0 j0Var = (j0) this.f3845i;
                Object obj = n0.d.get(i());
                if (obj instanceof C0883u) {
                    j0Var.resumeWith(a.l(((C0883u) obj).f3874a));
                    return;
                } else {
                    j0Var.resumeWith(D.u(obj));
                    return;
                }
        }
    }
}
