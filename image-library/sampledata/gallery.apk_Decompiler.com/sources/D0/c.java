package D0;

import A0.e;
import A0.j;
import A0.n;
import D1.g;
import K0.a;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import z0.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements h {
    public final ArrayList d;

    public c() {
        this.d = new ArrayList();
    }

    public void a(Object obj) {
        this.d.add(obj);
    }

    public void b(Object obj) {
        if (obj != null) {
            boolean z = obj instanceof Object[];
            ArrayList arrayList = this.d;
            if (z) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    arrayList.ensureCapacity(arrayList.size() + objArr.length);
                    Collections.addAll(arrayList, objArr);
                }
            } else if (obj instanceof Collection) {
                arrayList.addAll((Collection) obj);
            } else if (obj instanceof Iterable) {
                for (Object add : (Iterable) obj) {
                    arrayList.add(add);
                }
            } else if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
            } else {
                throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
            }
        }
    }

    public void c(Path path) {
        ArrayList arrayList = this.d;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            v vVar = (v) arrayList.get(size);
            g gVar = J0.g.f363a;
            if (vVar != null && !vVar.f2199a) {
                J0.g.a(vVar.d.l() / 100.0f, vVar.e.l() / 100.0f, vVar.f.l() / 360.0f, path);
            }
        }
    }

    public e p0() {
        ArrayList arrayList = this.d;
        if (((a) arrayList.get(0)).c()) {
            return new j(1, arrayList);
        }
        return new n(arrayList);
    }

    public List s0() {
        return this.d;
    }

    public boolean u0() {
        ArrayList arrayList = this.d;
        if (arrayList.size() != 1 || !((a) arrayList.get(0)).c()) {
            return false;
        }
        return true;
    }

    public c(ArrayList arrayList) {
        this.d = arrayList;
    }

    public c(int i2) {
        this.d = new ArrayList(i2);
    }
}
