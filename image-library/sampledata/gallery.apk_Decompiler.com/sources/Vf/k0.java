package Vf;

import cg.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k0 extends i0 {

    /* renamed from: h  reason: collision with root package name */
    public final n0 f3861h;

    /* renamed from: i  reason: collision with root package name */
    public final l0 f3862i;

    /* renamed from: j  reason: collision with root package name */
    public final C0879p f3863j;
    public final Object k;

    public k0(n0 n0Var, l0 l0Var, C0879p pVar, Object obj) {
        this.f3861h = n0Var;
        this.f3862i = l0Var;
        this.f3863j = pVar;
        this.k = obj;
    }

    public final boolean j() {
        return false;
    }

    public final void k(Throwable th) {
        C0879p pVar = this.f3863j;
        C0879p K6 = n0.K(pVar);
        n0 n0Var = this.f3861h;
        l0 l0Var = this.f3862i;
        Object obj = this.k;
        if (K6 == null || !n0Var.U(l0Var, K6, obj)) {
            l0Var.d.d(new h(2), 2);
            C0879p K10 = n0.K(pVar);
            if (K10 == null || !n0Var.U(l0Var, K10, obj)) {
                n0Var.k(n0Var.u(l0Var, obj));
            }
        }
    }
}
