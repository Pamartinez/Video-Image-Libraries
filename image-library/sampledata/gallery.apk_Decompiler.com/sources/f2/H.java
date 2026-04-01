package F2;

import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H extends I {
    public final /* synthetic */ Iterable[] d;

    public H(Iterable[] iterableArr) {
        this.d = iterableArr;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.Iterator, F2.k0, java.lang.Object] */
    public final Iterator iterator() {
        G g = new G(this, this.d.length);
        ? obj = new Object();
        obj.e = C0024j0.g;
        obj.f = g;
        return obj;
    }
}
