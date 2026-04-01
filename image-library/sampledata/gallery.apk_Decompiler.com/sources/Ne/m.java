package Ne;

import Ae.a;
import G0.e;
import Pe.i;
import Qe.K;
import Te.C0851l;
import Te.w;
import Te.z;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1196n;

public final class m implements a {
    public final /* synthetic */ int d;
    public final z e;

    public /* synthetic */ m(z zVar, int i2) {
        this.d = i2;
        this.e = zVar;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return ((w) this.e.n0(q.f3577i)).k;
            case 1:
                return new i(this.e);
            default:
                z zVar = this.e;
                e eVar = zVar.k;
                if (eVar != null) {
                    List list = (List) eVar.d;
                    zVar.D0();
                    list.contains(zVar);
                    Iterable<z> iterable = list;
                    for (z zVar2 : iterable) {
                        zVar2.getClass();
                    }
                    ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                    for (z zVar3 : iterable) {
                        K k = zVar3.l;
                        j.b(k);
                        arrayList.add(k);
                    }
                    return new C0851l(arrayList, "CompositeProvider@ModuleDescriptor for " + zVar.getName());
                }
                StringBuilder sb2 = new StringBuilder("Dependencies of module ");
                String str = zVar.getName().d;
                j.d(str, "toString(...)");
                sb2.append(str);
                sb2.append(" were not set before querying module content");
                throw new AssertionError(sb2.toString());
        }
    }
}
