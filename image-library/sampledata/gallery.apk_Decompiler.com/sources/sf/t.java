package Sf;

import Be.a;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t implements Iterator, a {
    public final Iterator d;
    public final /* synthetic */ u e;

    public t(u uVar) {
        this.e = uVar;
        this.d = uVar.f3739a.iterator();
    }

    public final boolean hasNext() {
        return this.d.hasNext();
    }

    public final Object next() {
        return this.e.b.invoke(this.d.next());
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
