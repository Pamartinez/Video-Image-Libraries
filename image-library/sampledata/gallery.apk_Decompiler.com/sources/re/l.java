package Re;

import Hf.C0771u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements h {
    public final h d;
    public final C0771u e;

    public l(h hVar, C0771u uVar) {
        this.d = hVar;
        this.e = uVar;
    }

    public final boolean g(C1236c cVar) {
        j.e(cVar, "fqName");
        if (((Boolean) this.e.invoke(cVar)).booleanValue()) {
            return this.d.g(cVar);
        }
        return false;
    }

    public final boolean isEmpty() {
        h<b> hVar = this.d;
        if ((hVar instanceof Collection) && ((Collection) hVar).isEmpty()) {
            return false;
        }
        for (b b : hVar) {
            C1236c b5 = b.b();
            if (b5 != null && ((Boolean) this.e.invoke(b5)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final Iterator iterator() {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.d) {
            C1236c b = ((b) next).b();
            if (b != null && ((Boolean) this.e.invoke(b)).booleanValue()) {
                arrayList.add(next);
            }
        }
        return arrayList.iterator();
    }

    public final b m(C1236c cVar) {
        j.e(cVar, "fqName");
        if (((Boolean) this.e.invoke(cVar)).booleanValue()) {
            return this.d.m(cVar);
        }
        return null;
    }
}
