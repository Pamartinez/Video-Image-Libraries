package P2;

import S2.t;
import S2.u;
import T2.c;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends I {
    public final /* synthetic */ int f;
    public final Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C(C0056f fVar, int i2) {
        super("proto_ids", fVar, 4);
        this.f = i2;
        switch (i2) {
            case 1:
                super("string_ids", fVar, 4);
                this.g = new TreeMap();
                return;
            case 2:
                super("type_ids", fVar, 4);
                this.g = new TreeMap();
                return;
            case 3:
                super((String) null, fVar, 4);
                o oVar = new o();
                oVar.g(0);
                this.g = Collections.singletonList(oVar);
                return;
            default:
                this.g = new TreeMap();
                return;
        }
    }

    public final Collection c() {
        switch (this.f) {
            case 0:
                return ((TreeMap) this.g).values();
            case 1:
                return ((TreeMap) this.g).values();
            case 2:
                return ((TreeMap) this.g).values();
            default:
                return (List) this.g;
        }
    }

    public final void k() {
        switch (this.f) {
            case 0:
                int i2 = 0;
                for (B g3 : ((TreeMap) this.g).values()) {
                    g3.g(i2);
                    i2++;
                }
                return;
            case 1:
                int i7 = 0;
                for (F g10 : ((TreeMap) this.g).values()) {
                    g10.g(i7);
                    i7++;
                }
                return;
            case 2:
                int i8 = 0;
                for (G g11 : ((TreeMap) this.g).values()) {
                    g11.g(i8);
                    i8++;
                }
                return;
            default:
                return;
        }
    }

    public int l(t tVar) {
        if (tVar != null) {
            f();
            F f5 = (F) ((TreeMap) this.g).get(tVar);
            if (f5 != null) {
                return f5.e();
            }
            throw new IllegalArgumentException("not found");
        }
        throw new NullPointerException("string == null");
    }

    public int m(u uVar) {
        if (uVar != null) {
            return n(uVar.d);
        }
        throw new NullPointerException("type == null");
    }

    public int n(c cVar) {
        if (cVar != null) {
            f();
            G g3 = (G) ((TreeMap) this.g).get(cVar);
            if (g3 != null) {
                return g3.e();
            }
            throw new IllegalArgumentException("not found: " + cVar);
        }
        throw new NullPointerException("type == null");
    }

    public void o(t tVar) {
        F f5 = new F(tVar);
        TreeMap treeMap = (TreeMap) this.g;
        g();
        if (((F) treeMap.get(tVar)) == null) {
            treeMap.put(tVar, f5);
        }
    }

    public void p(u uVar) {
        TreeMap treeMap = (TreeMap) this.g;
        if (uVar != null) {
            g();
            c cVar = uVar.d;
            if (((G) treeMap.get(cVar)) == null) {
                treeMap.put(cVar, new n(uVar));
                return;
            }
            return;
        }
        throw new NullPointerException("type == null");
    }

    public void q(c cVar) {
        TreeMap treeMap = (TreeMap) this.g;
        if (cVar != null) {
            g();
            if (((G) treeMap.get(cVar)) == null) {
                treeMap.put(cVar, new n(new u(cVar)));
                return;
            }
            return;
        }
        throw new NullPointerException("type == null");
    }

    private final void r() {
    }
}
