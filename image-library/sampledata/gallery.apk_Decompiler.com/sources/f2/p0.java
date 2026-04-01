package F2;

import E2.h;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p0 extends AbstractSequentialList implements Serializable {
    public final List d;
    public final h e;

    public p0(List list, h hVar) {
        list.getClass();
        this.d = list;
        this.e = hVar;
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final ListIterator listIterator(int i2) {
        return new C0032n0(this, this.d.listIterator(i2), 1);
    }

    public final void removeRange(int i2, int i7) {
        this.d.subList(i2, i7).clear();
    }

    public final int size() {
        return this.d.size();
    }
}
