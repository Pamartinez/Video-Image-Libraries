package We;

import a.C0068a;
import gf.C1073d;
import java.lang.reflect.Type;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class B implements C1073d {
    public C0893e a(C1236c cVar) {
        Object obj;
        j.e(cVar, "fqName");
        Iterator it = getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (j.a(C0892d.a(C0068a.A(C0068a.w(((C0893e) obj).f3887a))).a(), cVar)) {
                break;
            }
        }
        return (C0893e) obj;
    }

    public abstract Type b();

    public final boolean equals(Object obj) {
        if (!(obj instanceof B) || !j.a(b(), ((B) obj).b())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return b().hashCode();
    }

    public final String toString() {
        return getClass().getName() + ": " + b();
    }
}
