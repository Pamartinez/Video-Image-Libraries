package lg;

import Ae.b;
import Be.a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.j;
import ne.C1194l;

/* renamed from: lg.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1176d extends l implements List<l>, a {
    public static final C1175c Companion = new Object();
    public final List d;

    public C1176d(List list) {
        j.e(list, "content");
        this.d = list;
    }

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
        if (!(obj instanceof l)) {
            return false;
        }
        return this.d.contains((l) obj);
    }

    public final boolean containsAll(Collection collection) {
        j.e(collection, "elements");
        return this.d.containsAll(collection);
    }

    public final boolean equals(Object obj) {
        return j.a(this.d, obj);
    }

    public final Object get(int i2) {
        return (l) this.d.get(i2);
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof l)) {
            return -1;
        }
        return this.d.indexOf((l) obj);
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        return this.d.iterator();
    }

    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof l)) {
            return -1;
        }
        return this.d.lastIndexOf((l) obj);
    }

    public final ListIterator listIterator() {
        return this.d.listIterator();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final int size() {
        return this.d.size();
    }

    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final List subList(int i2, int i7) {
        return this.d.subList(i2, i7);
    }

    public final Object[] toArray() {
        return i.a(this);
    }

    public final String toString() {
        return C1194l.R0(this.d, GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]", (b) null, 56);
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final ListIterator listIterator(int i2) {
        return this.d.listIterator(i2);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return i.b(this, objArr);
    }
}
