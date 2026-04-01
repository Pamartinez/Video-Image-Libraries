package F2;

import java.util.AbstractMap;

/* renamed from: F2.d0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0012d0 extends U {
    public final /* synthetic */ C0014e0 f;

    public C0012d0(C0014e0 e0Var) {
        this.f = e0Var;
    }

    public final Object get(int i2) {
        C0014e0 e0Var = this.f;
        return new AbstractMap.SimpleImmutableEntry(e0Var.g.f260h.f246i.get(i2), e0Var.g.f261i.get(i2));
    }

    public final int size() {
        return this.f.g.f261i.size();
    }

    public final boolean u() {
        return true;
    }
}
