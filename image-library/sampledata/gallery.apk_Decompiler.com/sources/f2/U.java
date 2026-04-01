package F2;

import D1.f;
import He.F;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class U extends O implements List, RandomAccess {
    public static final G e = new G(0, (U) y0.f278h);

    public static y0 B(Object obj) {
        Object[] objArr = {obj};
        C0040v.a(1, objArr);
        return w(1, objArr);
    }

    public static y0 D(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        C0040v.a(2, objArr);
        return w(2, objArr);
    }

    public static y0 E(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5};
        C0040v.a(5, objArr);
        return w(5, objArr);
    }

    public static y0 w(int i2, Object[] objArr) {
        if (i2 == 0) {
            return y0.f278h;
        }
        return new y0(i2, objArr);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [F2.N, F2.Q] */
    public static Q x() {
        return new N(4);
    }

    public static U y(Collection collection) {
        if (collection instanceof O) {
            U p6 = ((O) collection).p();
            if (!p6.u()) {
                return p6;
            }
            Object[] array = p6.toArray(O.d);
            return w(array.length, array);
        }
        Object[] array2 = collection.toArray();
        C0040v.a(array2.length, array2);
        return w(array2.length, array2);
    }

    public static y0 z(Object[] objArr) {
        if (objArr.length == 0) {
            return y0.f278h;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        C0040v.a(objArr2.length, objArr2);
        return w(objArr2.length, objArr2);
    }

    /* renamed from: A */
    public final G listIterator(int i2) {
        F.o(i2, size());
        if (isEmpty()) {
            return e;
        }
        return new G(i2, this);
    }

    public U F() {
        if (size() <= 1) {
            return this;
        }
        return new S(this);
    }

    /* renamed from: G */
    public U subList(int i2, int i7) {
        F.p(i2, i7, size());
        int i8 = i7 - i2;
        if (i8 == size()) {
            return this;
        }
        if (i8 == 0) {
            return y0.f278h;
        }
        return new T(this, i2, i8);
    }

    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof List) {
                List list = (List) obj;
                int size = size();
                if (size == list.size()) {
                    if (list instanceof RandomAccess) {
                        int i2 = 0;
                        while (i2 < size) {
                            if (f.p(get(i2), list.get(i2))) {
                                i2++;
                            }
                        }
                    } else {
                        Iterator it = list.iterator();
                        for (Object p6 : this) {
                            if (it.hasNext()) {
                                if (!f.p(p6, it.next())) {
                                }
                            }
                        }
                        return !it.hasNext();
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i7 = 0; i7 < size; i7++) {
            i2 = ~(~(get(i7).hashCode() + (i2 * 31)));
        }
        return i2;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public Iterator iterator() {
        return listIterator(0);
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public int q(int i2, Object[] objArr) {
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            objArr[i2 + i7] = get(i7);
        }
        return i2 + size;
    }

    public final Object remove(int i2) {
        throw new UnsupportedOperationException();
    }

    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final O0 v() {
        return listIterator(0);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public final U p() {
        return this;
    }
}
