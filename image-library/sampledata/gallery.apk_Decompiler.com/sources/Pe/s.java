package Pe;

import B1.b;
import Df.C0736b;
import Df.l;
import Ef.a;
import Ef.d;
import Ef.e;
import Gf.j;
import Gf.m;
import Ne.q;
import Qe.H;
import Qe.K;
import Qf.k;
import Te.z;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import ne.v;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s implements K {

    /* renamed from: a  reason: collision with root package name */
    public final m f3650a;
    public final z b;

    /* renamed from: c  reason: collision with root package name */
    public l f3651c;
    public final j d;

    public s(m mVar, b bVar, z zVar) {
        this.f3650a = mVar;
        this.b = zVar;
        this.d = mVar.c(new C0736b(0, this));
    }

    public final void a(C1236c cVar, ArrayList arrayList) {
        kotlin.jvm.internal.j.e(cVar, "fqName");
        k.a(arrayList, this.d.invoke(cVar));
    }

    public final boolean b(C1236c cVar) {
        Object obj;
        kotlin.jvm.internal.j.e(cVar, "fqName");
        j jVar = this.d;
        Object obj2 = jVar.e.get(cVar);
        if (obj2 == null || obj2 == Gf.k.COMPUTING) {
            obj = c(cVar);
        } else {
            obj = (H) jVar.invoke(cVar);
        }
        if (obj == null) {
            return true;
        }
        return false;
    }

    public final d c(C1236c cVar) {
        InputStream inputStream;
        kotlin.jvm.internal.j.e(cVar, "fqName");
        if (!cVar.h(q.k)) {
            inputStream = null;
        } else {
            a.m.getClass();
            inputStream = e.a(a.a(cVar));
        }
        if (inputStream != null) {
            return c.o(cVar, this.f3650a, this.b, inputStream);
        }
        return null;
    }

    public final Collection m(C1236c cVar, Ae.b bVar) {
        kotlin.jvm.internal.j.e(cVar, "fqName");
        return v.d;
    }
}
