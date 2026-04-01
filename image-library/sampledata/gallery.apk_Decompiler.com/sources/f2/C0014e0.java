package F2;

import java.util.Map;

/* renamed from: F2.e0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0014e0 extends C0010c0 {
    public final /* synthetic */ C0016f0 g;

    public C0014e0(C0016f0 f0Var) {
        this.g = f0Var;
    }

    public final boolean A() {
        this.g.getClass();
        return false;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.g.get(entry.getKey());
            if (obj2 == null || !obj2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.g.hashCode();
    }

    public final int size() {
        return this.g.f261i.size();
    }

    public final boolean u() {
        return this.g.f();
    }

    public final O0 v() {
        return p().listIterator(0);
    }

    public final U z() {
        return new C0012d0(this);
    }
}
