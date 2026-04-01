package F2;

import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class N0 implements Iterator {
    public final Iterator d;

    public N0(Iterator it) {
        it.getClass();
        this.d = it;
    }

    public abstract Object a(Object obj);

    public final boolean hasNext() {
        return this.d.hasNext();
    }

    public final Object next() {
        return a(this.d.next());
    }

    public final void remove() {
        this.d.remove();
    }
}
