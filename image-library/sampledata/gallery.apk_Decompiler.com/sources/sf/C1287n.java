package sf;

import De.b;
import He.t;
import kotlin.jvm.internal.j;

/* renamed from: sf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1287n implements b {
    public Object d;
    public final /* synthetic */ C1288o e;

    public C1287n(Object obj, C1288o oVar) {
        this.e = oVar;
        this.d = obj;
    }

    public final void e(Object obj, t tVar) {
        j.e(tVar, "property");
        if (!this.e.f5099a) {
            this.d = obj;
            return;
        }
        throw new IllegalStateException("Cannot modify readonly DescriptorRendererOptions");
    }

    public final Object f(Object obj, t tVar) {
        j.e(tVar, "property");
        return this.d;
    }

    public final String toString() {
        return "ObservableProperty(value=" + this.d + ')';
    }
}
