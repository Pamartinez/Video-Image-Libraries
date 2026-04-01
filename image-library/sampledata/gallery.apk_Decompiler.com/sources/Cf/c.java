package cf;

import B0.a;
import Bd.C0725a;
import Df.C0736b;
import Gf.j;
import Gf.m;
import Ne.p;
import Re.b;
import Re.h;
import Sf.f;
import Sf.g;
import Sf.k;
import Sf.n;
import Sf.u;
import We.C0893e;
import a.C0068a;
import af.C0905c;
import gf.C1071b;
import java.util.Iterator;
import ne.C1192j;
import ne.C1194l;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements h {
    public final a d;
    public final C1071b e;
    public final boolean f;
    public final j g;

    public c(a aVar, C1071b bVar, boolean z) {
        kotlin.jvm.internal.j.e(aVar, "c");
        kotlin.jvm.internal.j.e(bVar, "annotationOwner");
        this.d = aVar;
        this.e = bVar;
        this.f = z;
        this.g = ((m) ((C0922a) aVar.d).f4006a).c(new C0736b(16, this));
    }

    public final boolean g(C1236c cVar) {
        return C0068a.I(this, cVar);
    }

    public final boolean isEmpty() {
        if (this.e.getAnnotations().isEmpty()) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        C1071b bVar = this.e;
        u t02 = n.t0(C1194l.F0(bVar.getAnnotations()), this.g);
        C1240g gVar = C0905c.f3992a;
        return new f(new g(n.r0(C1192j.b0(new k[]{t02, C1192j.b0(new Object[]{C0905c.a(p.m, bVar, this.d)})})), false, new C0725a(9)));
    }

    public final b m(C1236c cVar) {
        b bVar;
        kotlin.jvm.internal.j.e(cVar, "fqName");
        C1071b bVar2 = this.e;
        C0893e a7 = bVar2.a(cVar);
        if (a7 != null && (bVar = (b) this.g.invoke(a7)) != null) {
            return bVar;
        }
        C1240g gVar = C0905c.f3992a;
        return C0905c.a(cVar, bVar2, this.d);
    }
}
