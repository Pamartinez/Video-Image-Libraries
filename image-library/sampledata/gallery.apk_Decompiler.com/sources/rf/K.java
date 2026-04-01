package rf;

import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K implements Iterator {
    public Iterator d;

    public final boolean hasNext() {
        return this.d.hasNext();
    }

    public final Object next() {
        return (String) this.d.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
