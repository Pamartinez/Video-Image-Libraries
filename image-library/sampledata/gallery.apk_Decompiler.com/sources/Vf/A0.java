package Vf;

import cg.r;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A0 extends r implements Runnable {

    /* renamed from: h  reason: collision with root package name */
    public final long f3833h;

    public A0(long j2, i iVar) {
        super(iVar, iVar.getContext());
        this.f3833h = j2;
    }

    public final String J() {
        return super.J() + "(timeMillis=" + this.f3833h + ')';
    }

    public final void run() {
        D.i(this.f);
        n(new z0("Timed out waiting for " + this.f3833h + " ms", this));
    }
}
