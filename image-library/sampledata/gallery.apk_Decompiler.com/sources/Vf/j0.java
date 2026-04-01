package Vf;

import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j0 extends C0875l {
    public final n0 l;

    public j0(n0 n0Var, C1227c cVar) {
        super(1, cVar);
        this.l = n0Var;
    }

    public final Throwable p(n0 n0Var) {
        Throwable b;
        n0 n0Var2 = this.l;
        n0Var2.getClass();
        Object obj = n0.d.get(n0Var2);
        if ((obj instanceof l0) && (b = ((l0) obj).b()) != null) {
            return b;
        }
        if (obj instanceof C0883u) {
            return ((C0883u) obj).f3874a;
        }
        return n0Var.v();
    }

    public final String x() {
        return "AwaitContinuation";
    }
}
