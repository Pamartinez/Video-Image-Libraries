package F2;

import E2.l;
import He.F;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class I0 extends AbstractCollection implements Set {
    public final Set d;
    public final l e;

    public I0(Set set, l lVar) {
        this.d = set;
        this.e = lVar;
    }

    public final boolean add(Object obj) {
        F.j(this.e.apply(obj));
        return this.d.add(obj);
    }

    public final boolean addAll(Collection collection) {
        for (Object apply : collection) {
            F.j(this.e.apply(apply));
        }
        return this.d.addAll(collection);
    }

    public final void clear() {
        Set set = this.d;
        boolean z = set instanceof RandomAccess;
        l lVar = this.e;
        if (!z || !(set instanceof List)) {
            Iterator it = set.iterator();
            lVar.getClass();
            while (it.hasNext()) {
                if (lVar.apply(it.next())) {
                    it.remove();
                }
            }
            return;
        }
        List list = (List) set;
        lVar.getClass();
        int i2 = 0;
        for (int i7 = 0; i7 < list.size(); i7++) {
            Object obj = list.get(i7);
            if (!lVar.apply(obj)) {
                if (i7 > i2) {
                    try {
                        list.set(i2, obj);
                    } catch (UnsupportedOperationException unused) {
                        C0040v.o(list, lVar, i2, i7);
                        return;
                    } catch (IllegalArgumentException unused2) {
                        C0040v.o(list, lVar, i2, i7);
                        return;
                    }
                }
                i2++;
            }
        }
        list.subList(i2, list.size()).clear();
    }

    public final boolean contains(Object obj) {
        boolean z;
        Set set = this.d;
        set.getClass();
        try {
            z = set.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            z = false;
        }
        if (z) {
            return this.e.apply(obj);
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        return C0040v.f(this, obj);
    }

    public final int hashCode() {
        return C0040v.j(this);
    }

    public final boolean isEmpty() {
        Iterator it = this.d.iterator();
        l lVar = this.e;
        F.n(lVar, "predicate");
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (lVar.apply(it.next())) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            z = true;
        }
        return true ^ z;
    }

    public final Iterator iterator() {
        Iterator it = this.d.iterator();
        it.getClass();
        l lVar = this.e;
        lVar.getClass();
        return new C0022i0(it, lVar);
    }

    public final boolean remove(Object obj) {
        if (!contains(obj) || !this.d.remove(obj)) {
            return false;
        }
        return true;
    }

    public final boolean removeAll(Collection collection) {
        Iterator it = this.d.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.e.apply(next) && collection.contains(next)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public final boolean retainAll(Collection collection) {
        Iterator it = this.d.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            if (this.e.apply(next) && !collection.contains(next)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public final int size() {
        int i2 = 0;
        for (Object apply : this.d) {
            if (this.e.apply(apply)) {
                i2++;
            }
        }
        return i2;
    }

    public final Object[] toArray() {
        return C0040v.m(iterator()).toArray();
    }

    public final Object[] toArray(Object[] objArr) {
        return C0040v.m(iterator()).toArray(objArr);
    }
}
