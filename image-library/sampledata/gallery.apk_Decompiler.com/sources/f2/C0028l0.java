package F2;

import He.F;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: F2.l0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0028l0 implements Iterator {
    ;

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        F.t(false, "no calls to next() since the last call to remove()");
    }
}
