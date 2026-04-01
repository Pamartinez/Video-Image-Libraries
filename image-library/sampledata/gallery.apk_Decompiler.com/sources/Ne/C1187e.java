package ne;

import ig.i;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.j;

/* renamed from: ne.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1187e extends C1183a implements List {
    public static final C1184b Companion = new Object();

    public void add(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i2, Collection<Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        Companion.getClass();
        if (size() != collection.size()) {
            return false;
        }
        Iterator it = collection.iterator();
        for (Object a7 : this) {
            if (!j.a(a7, it.next())) {
            }
        }
        return true;
        return false;
    }

    public int hashCode() {
        int i2;
        Companion.getClass();
        int i7 = 1;
        for (Object next : this) {
            int i8 = i7 * 31;
            if (next != null) {
                i2 = next.hashCode();
            } else {
                i2 = 0;
            }
            i7 = i8 + i2;
        }
        return i7;
    }

    public int indexOf(Object obj) {
        int i2 = 0;
        for (Object a7 : this) {
            if (j.a(a7, obj)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public Iterator<Object> iterator() {
        return new i(7, this);
    }

    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (j.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public ListIterator<Object> listIterator() {
        return new C1185c(this, 0);
    }

    public Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public List<Object> subList(int i2, int i7) {
        return new C1186d(this, i2, i7);
    }

    public ListIterator<Object> listIterator(int i2) {
        return new C1185c(this, i2);
    }
}
