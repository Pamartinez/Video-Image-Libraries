package Te;

import Qe.C;
import Qe.C0822l;
import Qe.C0824n;
import Qe.H;
import Qe.Q;
import Re.g;
import kotlin.jvm.internal.j;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class B extends C0853n implements H {

    /* renamed from: i  reason: collision with root package name */
    public final C1236c f3743i;

    /* renamed from: j  reason: collision with root package name */
    public final String f3744j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public B(C c5, C1236c cVar) {
        super(c5, g.f3692a, cVar.g(), Q.f3662a);
        j.e(c5, "module");
        j.e(cVar, "fqName");
        this.f3743i = cVar;
        this.f3744j = "package " + cVar + " of " + c5;
    }

    /* renamed from: E0 */
    public final C g() {
        C0822l g = super.g();
        j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ModuleDescriptor");
        return (C) g;
    }

    public Q getSource() {
        return Q.f3662a;
    }

    public String toString() {
        return this.f3744j;
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.w(this, obj);
    }
}
