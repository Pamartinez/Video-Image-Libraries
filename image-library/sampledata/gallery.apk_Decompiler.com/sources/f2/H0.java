package F2;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H0 extends AbstractSet {
    public final /* synthetic */ Set d;
    public final /* synthetic */ Set e;

    public H0(Set set, Set set2) {
        this.d = set;
        this.e = set2;
    }

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(Object obj) {
        if (!this.d.contains(obj) || !this.e.contains(obj)) {
            return false;
        }
        return true;
    }

    public final boolean containsAll(Collection collection) {
        if (!this.d.containsAll(collection) || !this.e.containsAll(collection)) {
            return false;
        }
        return true;
    }

    public final boolean isEmpty() {
        return Collections.disjoint(this.e, this.d);
    }

    public final Iterator iterator() {
        return new C0022i0(this);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        int i2 = 0;
        for (Object contains : this.d) {
            if (this.e.contains(contains)) {
                i2++;
            }
        }
        return i2;
    }
}
