package P2;

import S2.q;
import java.util.Collection;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends t {
    public final TreeMap f = new TreeMap();

    public v(C0056f fVar) {
        super("method_ids", fVar, 4);
    }

    public final Collection c() {
        return this.f.values();
    }

    public final int l(q qVar) {
        if (qVar != null) {
            f();
            u uVar = (u) this.f.get(qVar);
            if (uVar != null) {
                return uVar.e();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("ref == null");
    }

    public final void m(q qVar) {
        if (qVar != null) {
            g();
            TreeMap treeMap = this.f;
            if (((u) treeMap.get(qVar)) == null) {
                treeMap.put(qVar, new s(qVar));
                return;
            }
            return;
        }
        throw new NullPointerException("method == null");
    }
}
