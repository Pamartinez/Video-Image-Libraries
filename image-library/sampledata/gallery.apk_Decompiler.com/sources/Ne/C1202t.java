package ne;

import Be.a;
import c0.C0086a;
import com.adobe.internal.xmp.XMPConst;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.j;

/* renamed from: ne.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1202t implements List, Serializable, RandomAccess, a {
    public static final C1202t d = new Object();

    public final /* bridge */ /* synthetic */ void add(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean contains(Object obj) {
        return false;
    }

    public final boolean containsAll(Collection collection) {
        j.e(collection, "elements");
        return collection.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof List) || !((List) obj).isEmpty()) {
            return false;
        }
        return true;
    }

    public final Object get(int i2) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + i2 + '.');
    }

    public final int hashCode() {
        return 1;
    }

    public final int indexOf(Object obj) {
        return -1;
    }

    public final boolean isEmpty() {
        return true;
    }

    public final Iterator iterator() {
        return C1201s.d;
    }

    public final int lastIndexOf(Object obj) {
        return -1;
    }

    public final ListIterator listIterator() {
        return C1201s.d;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ int size() {
        return 0;
    }

    public final List subList(int i2, int i7) {
        if (i2 == 0 && i7 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException(N2.j.b(i2, i7, "fromIndex: ", ", toIndex: "));
    }

    public final Object[] toArray() {
        return i.a(this);
    }

    public final String toString() {
        return XMPConst.ARRAY_ITEM_NAME;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final ListIterator listIterator(int i2) {
        if (i2 == 0) {
            return C1201s.d;
        }
        throw new IndexOutOfBoundsException(C0086a.i(i2, "Index: "));
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return i.b(this, objArr);
    }
}
