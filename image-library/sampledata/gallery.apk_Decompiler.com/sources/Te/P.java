package Te;

import Ae.a;
import Hf.C0774x;
import L1.d;
import Oe.g;
import Qe.C0812b;
import Qe.Q;
import Re.h;
import kotlin.jvm.internal.j;
import me.m;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P extends Q {

    /* renamed from: p  reason: collision with root package name */
    public final m f3769p;

    public P(C0812b bVar, Q q, int i2, h hVar, C1240g gVar, C0774x xVar, boolean z, boolean z3, boolean z7, C0774x xVar2, Q q10, a aVar) {
        super(bVar, q, i2, hVar, gVar, xVar, z, z3, z7, xVar2, q10);
        this.f3769p = d.q(aVar);
    }

    public final Q E0(g gVar, C1240g gVar2, int i2) {
        h annotations = getAnnotations();
        j.d(annotations, "<get-annotations>(...)");
        C0774x type = getType();
        j.d(type, "getType(...)");
        return new P(gVar, (Q) null, i2, annotations, gVar2, type, F0(), this.l, this.m, this.n, Q.f3662a, new Af.g(24, this));
    }
}
