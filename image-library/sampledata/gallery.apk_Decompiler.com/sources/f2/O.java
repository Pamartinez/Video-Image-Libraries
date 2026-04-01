package F2;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class O extends AbstractCollection implements Serializable {
    public static final Object[] d = new Object[0];

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(Object obj);

    public U p() {
        if (isEmpty()) {
            G g = U.e;
            return y0.f278h;
        }
        Object[] array = toArray(d);
        G g3 = U.e;
        return U.w(array.length, array);
    }

    public int q(int i2, Object[] objArr) {
        O0 v = iterator();
        while (v.hasNext()) {
            objArr[i2] = v.next();
            i2++;
        }
        return i2;
    }

    public Object[] r() {
        return null;
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

    public int s() {
        throw new UnsupportedOperationException();
    }

    public final Spliterator spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    public int t() {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(d);
    }

    public abstract boolean u();

    /* renamed from: v */
    public abstract O0 iterator();

    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int size = size();
        if (objArr.length < size) {
            Object[] r = r();
            if (r != null) {
                return Arrays.copyOfRange(r, t(), s(), objArr.getClass());
            }
            if (objArr.length != 0) {
                objArr = Arrays.copyOf(objArr, 0);
            }
            objArr = Arrays.copyOf(objArr, size);
        } else if (objArr.length > size) {
            objArr[size] = null;
        }
        q(0, objArr);
        return objArr;
    }
}
