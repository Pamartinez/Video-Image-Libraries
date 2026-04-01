package P2;

import S2.p;
import S2.q;
import T2.a;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends s {
    public final void a(C0056f fVar) {
        fVar.f.p(this.e);
        C c5 = fVar.e;
        p pVar = this.f;
        c5.o(pVar.e.d);
        C c6 = fVar.g;
        a aVar = ((q) pVar).f;
        TreeMap treeMap = (TreeMap) c6.g;
        if (aVar != null) {
            c6.g();
            if (((B) treeMap.get(aVar)) == null) {
                treeMap.put(aVar, new B(aVar));
                return;
            }
            return;
        }
        throw new NullPointerException("prototype == null");
    }

    public final q b() {
        return q.TYPE_METHOD_ID_ITEM;
    }

    public final int h(C0056f fVar) {
        C c5 = fVar.g;
        a aVar = ((q) this.f).f;
        if (aVar != null) {
            c5.f();
            B b = (B) ((TreeMap) c5.g).get(aVar);
            if (b != null) {
                return b.e();
            }
            throw new IllegalArgumentException("not found");
        }
        c5.getClass();
        throw new NullPointerException("prototype == null");
    }

    public final String i() {
        return "proto_idx";
    }
}
