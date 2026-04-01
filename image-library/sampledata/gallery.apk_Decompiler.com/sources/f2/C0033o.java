package F2;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: F2.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0033o extends AbstractCollection implements List {
    public final Object d;
    public Collection e;
    public final C0033o f;
    public final Collection g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C0009c f269h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ C0009c f270i;

    public C0033o(C0009c cVar, Object obj, List list, C0033o oVar) {
        Collection collection;
        this.f270i = cVar;
        this.f269h = cVar;
        this.d = obj;
        this.e = list;
        this.f = oVar;
        if (oVar == null) {
            collection = null;
        } else {
            collection = oVar.e;
        }
        this.g = collection;
    }

    public final boolean add(Object obj) {
        p();
        boolean isEmpty = this.e.isEmpty();
        boolean add = this.e.add(obj);
        if (add) {
            this.f269h.f257i++;
            if (isEmpty) {
                i();
            }
        }
        return add;
    }

    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.e.addAll(collection);
        if (addAll) {
            this.f269h.f257i += this.e.size() - size;
            if (size == 0) {
                i();
            }
        }
        return addAll;
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.e.clear();
            this.f269h.f257i -= size;
            q();
        }
    }

    public final boolean contains(Object obj) {
        p();
        return this.e.contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        p();
        return this.e.containsAll(collection);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        p();
        return this.e.equals(obj);
    }

    public final Object get(int i2) {
        p();
        return ((List) this.e).get(i2);
    }

    public final int hashCode() {
        p();
        return this.e.hashCode();
    }

    public final void i() {
        C0033o oVar = this.f;
        if (oVar != null) {
            oVar.i();
        } else {
            this.f269h.f256h.put(this.d, this.e);
        }
    }

    public final int indexOf(Object obj) {
        p();
        return ((List) this.e).indexOf(obj);
    }

    public final Iterator iterator() {
        p();
        return new C0015f(this);
    }

    public final int lastIndexOf(Object obj) {
        p();
        return ((List) this.e).lastIndexOf(obj);
    }

    public final ListIterator listIterator() {
        p();
        return new C0031n(this);
    }

    public final void p() {
        Collection collection;
        C0033o oVar = this.f;
        if (oVar != null) {
            oVar.p();
            if (oVar.e != this.g) {
                throw new ConcurrentModificationException();
            }
        } else if (this.e.isEmpty() && (collection = (Collection) this.f269h.f256h.get(this.d)) != null) {
            this.e = collection;
        }
    }

    public final void q() {
        C0033o oVar = this.f;
        if (oVar != null) {
            oVar.q();
        } else if (this.e.isEmpty()) {
            this.f269h.f256h.remove(this.d);
        }
    }

    public final boolean remove(Object obj) {
        p();
        boolean remove = this.e.remove(obj);
        if (remove) {
            C0009c cVar = this.f269h;
            cVar.f257i--;
            q();
        }
        return remove;
    }

    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.e.removeAll(collection);
        if (removeAll) {
            this.f269h.f257i += this.e.size() - size;
            q();
        }
        return removeAll;
    }

    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean retainAll = this.e.retainAll(collection);
        if (retainAll) {
            this.f269h.f257i += this.e.size() - size;
            q();
        }
        return retainAll;
    }

    public final Object set(int i2, Object obj) {
        p();
        return ((List) this.e).set(i2, obj);
    }

    public final int size() {
        p();
        return this.e.size();
    }

    public final List subList(int i2, int i7) {
        p();
        List subList = ((List) this.e).subList(i2, i7);
        C0033o oVar = this.f;
        if (oVar == null) {
            oVar = this;
        }
        boolean z = subList instanceof RandomAccess;
        C0009c cVar = this.f270i;
        Object obj = this.d;
        if (z) {
            return new C0033o(cVar, obj, subList, oVar);
        }
        return new C0033o(cVar, obj, subList, oVar);
    }

    public final String toString() {
        p();
        return this.e.toString();
    }

    public final ListIterator listIterator(int i2) {
        p();
        return new C0031n(this, i2);
    }

    public final Object remove(int i2) {
        p();
        Object remove = ((List) this.e).remove(i2);
        C0009c cVar = this.f270i;
        cVar.f257i--;
        q();
        return remove;
    }

    public final void add(int i2, Object obj) {
        p();
        boolean isEmpty = this.e.isEmpty();
        ((List) this.e).add(i2, obj);
        this.f270i.f257i++;
        if (isEmpty) {
            i();
        }
    }

    public final boolean addAll(int i2, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.e).addAll(i2, collection);
        if (addAll) {
            this.f270i.f257i += this.e.size() - size;
            if (size == 0) {
                i();
            }
        }
        return addAll;
    }
}
