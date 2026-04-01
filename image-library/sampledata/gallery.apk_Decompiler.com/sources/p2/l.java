package P2;

import S2.h;
import java.util.Collection;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends t {
    public final TreeMap f = new TreeMap();

    public l(C0056f fVar) {
        super("field_ids", fVar, 4);
    }

    public final Collection c() {
        return this.f.values();
    }

    public final int l(h hVar) {
        if (hVar != null) {
            f();
            k kVar = (k) this.f.get(hVar);
            if (kVar != null) {
                return kVar.e();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("ref == null");
    }

    public final void m(h hVar) {
        if (hVar != null) {
            g();
            TreeMap treeMap = this.f;
            if (((k) treeMap.get(hVar)) == null) {
                treeMap.put(hVar, new s(hVar));
                return;
            }
            return;
        }
        throw new NullPointerException("field == null");
    }
}
