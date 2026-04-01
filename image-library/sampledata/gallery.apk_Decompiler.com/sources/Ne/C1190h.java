package ne;

import Be.a;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.j;

/* renamed from: ne.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1190h implements Collection, a {
    public final Object[] d;
    public final boolean e;

    public C1190h(Object[] objArr, boolean z) {
        j.e(objArr, StateHandler.VALUES);
        this.d = objArr;
        this.e = z;
    }

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean contains(Object obj) {
        return C1192j.d0(this.d, obj);
    }

    public final boolean containsAll(Collection collection) {
        j.e(collection, "elements");
        Iterable<Object> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Object d0 : iterable) {
            if (!C1192j.d0(this.d, d0)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isEmpty() {
        if (this.d.length == 0) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return j.g(this.d);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final int size() {
        return this.d.length;
    }

    public final Object[] toArray() {
        Object[] objArr = this.d;
        j.e(objArr, "<this>");
        Class<Object[]> cls = Object[].class;
        if (this.e && objArr.getClass().equals(cls)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, cls);
        j.d(copyOf, "copyOf(...)");
        return copyOf;
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return i.b(this, objArr);
    }
}
