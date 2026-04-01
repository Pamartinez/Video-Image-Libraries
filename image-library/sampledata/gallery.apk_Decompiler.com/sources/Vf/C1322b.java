package vf;

import Ae.b;
import Hf.C0774x;
import Ne.i;
import Ne.p;
import Qe.C;
import Qe.C0819i;
import java.util.List;
import kotlin.jvm.internal.j;

/* renamed from: vf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C1322b extends C1327g {
    public final b b;

    public C1322b(List list, b bVar) {
        super(list);
        this.b = bVar;
    }

    public final C0774x a(C c5) {
        C0819i g;
        j.e(c5, "module");
        C0774x xVar = (C0774x) this.b.invoke(c5);
        if (!i.y(xVar) && (((g = xVar.s0().g()) == null || i.r(g) == null) && !i.B(xVar, p.f3563W.i()) && !i.B(xVar, p.f3564X.i()) && !i.B(xVar, p.Y.i()))) {
            i.B(xVar, p.Z.i());
        }
        return xVar;
    }
}
