package F2;

import E2.l;
import java.util.Iterator;

/* renamed from: F2.h0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0020h0 extends I {
    public final /* synthetic */ Iterable d;
    public final /* synthetic */ l e;

    public C0020h0(Iterable iterable, l lVar) {
        this.d = iterable;
        this.e = lVar;
    }

    public final Iterator iterator() {
        Iterator it = this.d.iterator();
        it.getClass();
        return new C0022i0(it, this.e);
    }
}
