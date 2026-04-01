package rf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L extends AbstractList implements RandomAccess, w {
    public final v d;

    public L(v vVar) {
        this.d = vVar;
    }

    public final List b() {
        return Collections.unmodifiableList(this.d.d);
    }

    public final Object get(int i2) {
        return (String) this.d.get(i2);
    }

    public final C1255e h(int i2) {
        return this.d.h(i2);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.K, java.util.Iterator, java.lang.Object] */
    public final Iterator iterator() {
        ? obj = new Object();
        obj.d = this.d.iterator();
        return obj;
    }

    public final void l(x xVar) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.J, java.util.ListIterator, java.lang.Object] */
    public final ListIterator listIterator(int i2) {
        ? obj = new Object();
        obj.d = this.d.listIterator(i2);
        return obj;
    }

    public final int size() {
        return this.d.size();
    }

    public final L c() {
        return this;
    }
}
