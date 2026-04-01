package Df;

import B1.a;
import Ne.p;
import Qe.C0816f;
import java.util.Set;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j {

    /* renamed from: c  reason: collision with root package name */
    public static final Set f3346c;

    /* renamed from: a  reason: collision with root package name */
    public final l f3347a;
    public final Gf.j b;

    static {
        C1236c g = p.f3566c.g();
        C1236c e = g.e();
        C1240g f = g.f();
        kotlin.jvm.internal.j.d(f, "shortName(...)");
        f3346c = a.S(new C1235b(e, f));
    }

    public j(l lVar) {
        this.f3347a = lVar;
        this.b = lVar.f3349a.c(new C0736b(1, this));
    }

    public final C0816f a(C1235b bVar, C0741g gVar) {
        kotlin.jvm.internal.j.e(bVar, "classId");
        return (C0816f) this.b.invoke(new C0743i(bVar, gVar));
    }
}
