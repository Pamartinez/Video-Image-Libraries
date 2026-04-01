package F2;

import E2.l;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J0 extends I0 implements SortedSet {
    public final Comparator comparator() {
        return ((SortedSet) this.d).comparator();
    }

    public final Object first() {
        Iterator it = this.d.iterator();
        it.getClass();
        l lVar = this.e;
        lVar.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            if (lVar.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.SortedSet, F2.I0] */
    public final SortedSet headSet(Object obj) {
        return new I0(((SortedSet) this.d).headSet(obj), this.e);
    }

    public final Object last() {
        SortedSet sortedSet = (SortedSet) this.d;
        while (true) {
            Object last = sortedSet.last();
            if (this.e.apply(last)) {
                return last;
            }
            sortedSet = sortedSet.headSet(last);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.SortedSet, F2.I0] */
    public final SortedSet subSet(Object obj, Object obj2) {
        return new I0(((SortedSet) this.d).subSet(obj, obj2), this.e);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.SortedSet, F2.I0] */
    public final SortedSet tailSet(Object obj) {
        return new I0(((SortedSet) this.d).tailSet(obj), this.e);
    }
}
