package F2;

import E2.h;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: F2.o0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0034o0 extends AbstractList implements RandomAccess, Serializable {
    public final List d;
    public final h e;

    public C0034o0(List list, h hVar) {
        list.getClass();
        this.d = list;
        this.e = hVar;
    }

    public final Object get(int i2) {
        return this.e.apply(this.d.get(i2));
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        return listIterator();
    }

    public final ListIterator listIterator(int i2) {
        return new C0032n0(this, this.d.listIterator(i2), 0);
    }

    public final Object remove(int i2) {
        return this.e.apply(this.d.remove(i2));
    }

    public final void removeRange(int i2, int i7) {
        this.d.subList(i2, i7).clear();
    }

    public final int size() {
        return this.d.size();
    }
}
