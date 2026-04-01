package ne;

import Ad.f;
import Be.a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Collection;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.j;

/* renamed from: ne.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1183a implements Collection, a {
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Object obj) {
        if (isEmpty()) {
            return false;
        }
        for (Object a7 : this) {
            if (j.a(a7, obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        j.e(collection, "elements");
        Iterable<Object> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Object contains : iterable) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public abstract int p();

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final /* bridge */ int size() {
        return p();
    }

    public Object[] toArray() {
        return i.a(this);
    }

    public String toString() {
        return C1194l.R0(this, ArcCommonLog.TAG_COMMA, "[", "]", new f(17, (Object) this), 24);
    }

    public <T> T[] toArray(T[] tArr) {
        j.e(tArr, "array");
        return i.b(this, tArr);
    }
}
