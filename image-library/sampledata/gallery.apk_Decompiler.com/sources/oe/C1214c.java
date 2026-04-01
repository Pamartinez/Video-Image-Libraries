package oe;

import L2.a;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.j;
import ne.C1184b;
import ne.C1187e;
import ne.C1188f;
import ne.C1192j;

/* renamed from: oe.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1214c extends C1188f implements RandomAccess, Serializable {
    public static final C1214c g;
    public Object[] d;
    public int e;
    public boolean f;

    static {
        C1214c cVar = new C1214c(0);
        cVar.f = true;
        g = cVar;
    }

    public C1214c(int i2) {
        if (i2 >= 0) {
            this.d = new Object[i2];
            return;
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }

    public final boolean add(Object obj) {
        u();
        int i2 = this.e;
        this.modCount++;
        v(i2, 1);
        this.d[i2] = obj;
        return true;
    }

    public final boolean addAll(Collection collection) {
        j.e(collection, "elements");
        u();
        int size = collection.size();
        s(this.e, collection, size);
        return size > 0;
    }

    public final void clear() {
        u();
        x(0, this.e);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.d;
            int i2 = this.e;
            if (i2 == list.size()) {
                int i7 = 0;
                while (i7 < i2) {
                    if (j.a(objArr[i7], list.get(i7))) {
                        i7++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final Object get(int i2) {
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.a(i2, i7);
        return this.d[i2];
    }

    public final int hashCode() {
        int i2;
        Object[] objArr = this.d;
        int i7 = this.e;
        int i8 = 1;
        for (int i10 = 0; i10 < i7; i10++) {
            Object obj = objArr[i10];
            int i11 = i8 * 31;
            if (obj != null) {
                i2 = obj.hashCode();
            } else {
                i2 = 0;
            }
            i8 = i11 + i2;
        }
        return i8;
    }

    public final int indexOf(Object obj) {
        for (int i2 = 0; i2 < this.e; i2++) {
            if (j.a(this.d[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        if (this.e == 0) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(Object obj) {
        for (int i2 = this.e - 1; i2 >= 0; i2--) {
            if (j.a(this.d[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public final int p() {
        return this.e;
    }

    public final Object q(int i2) {
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.a(i2, i7);
        return w(i2);
    }

    public final boolean remove(Object obj) {
        u();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            q(indexOf);
        }
        if (indexOf >= 0) {
            return true;
        }
        return false;
    }

    public final boolean removeAll(Collection collection) {
        j.e(collection, "elements");
        u();
        if (y(0, this.e, collection, false) > 0) {
            return true;
        }
        return false;
    }

    public final boolean retainAll(Collection collection) {
        j.e(collection, "elements");
        u();
        if (y(0, this.e, collection, true) > 0) {
            return true;
        }
        return false;
    }

    public final void s(int i2, Collection collection, int i7) {
        this.modCount++;
        v(i2, i7);
        Iterator it = collection.iterator();
        for (int i8 = 0; i8 < i7; i8++) {
            this.d[i2 + i8] = it.next();
        }
    }

    public final Object set(int i2, Object obj) {
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.a(i2, i7);
        Object[] objArr = this.d;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final List subList(int i2, int i7) {
        C1184b bVar = C1187e.Companion;
        int i8 = this.e;
        bVar.getClass();
        C1184b.c(i2, i7, i8);
        return new C1213b(this.d, i2, i7 - i2, (C1213b) null, this);
    }

    public final void t(int i2, Object obj) {
        this.modCount++;
        v(i2, 1);
        this.d[i2] = obj;
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        int length = objArr.length;
        int i2 = this.e;
        if (length < i2) {
            Object[] copyOfRange = Arrays.copyOfRange(this.d, 0, i2, objArr.getClass());
            j.d(copyOfRange, "copyOfRange(...)");
            return copyOfRange;
        }
        C1192j.g0(0, 0, i2, this.d, objArr);
        int i7 = this.e;
        if (i7 < objArr.length) {
            objArr[i7] = null;
        }
        return objArr;
    }

    public final String toString() {
        return a.b(this.d, 0, this.e, this);
    }

    public final void u() {
        if (this.f) {
            throw new UnsupportedOperationException();
        }
    }

    public final void v(int i2, int i7) {
        int i8 = this.e + i7;
        if (i8 >= 0) {
            Object[] objArr = this.d;
            if (i8 > objArr.length) {
                C1184b bVar = C1187e.Companion;
                int length = objArr.length;
                bVar.getClass();
                int d2 = C1184b.d(length, i8);
                Object[] objArr2 = this.d;
                j.e(objArr2, "<this>");
                Object[] copyOf = Arrays.copyOf(objArr2, d2);
                j.d(copyOf, "copyOf(...)");
                this.d = copyOf;
            }
            Object[] objArr3 = this.d;
            C1192j.g0(i2 + i7, i2, this.e, objArr3, objArr3);
            this.e += i7;
            return;
        }
        throw new OutOfMemoryError();
    }

    public final Object w(int i2) {
        this.modCount++;
        Object[] objArr = this.d;
        Object obj = objArr[i2];
        C1192j.g0(i2, i2 + 1, this.e, objArr, objArr);
        Object[] objArr2 = this.d;
        j.e(objArr2, "<this>");
        objArr2[this.e - 1] = null;
        this.e--;
        return obj;
    }

    public final void x(int i2, int i7) {
        if (i7 > 0) {
            this.modCount++;
        }
        Object[] objArr = this.d;
        C1192j.g0(i2, i2 + i7, this.e, objArr, objArr);
        Object[] objArr2 = this.d;
        int i8 = this.e;
        a.v(objArr2, i8 - i7, i8);
        this.e -= i7;
    }

    public final int y(int i2, int i7, Collection collection, boolean z) {
        int i8 = 0;
        int i10 = 0;
        while (i8 < i7) {
            int i11 = i2 + i8;
            if (collection.contains(this.d[i11]) == z) {
                Object[] objArr = this.d;
                i8++;
                objArr[i10 + i2] = objArr[i11];
                i10++;
            } else {
                i8++;
            }
        }
        int i12 = i7 - i10;
        Object[] objArr2 = this.d;
        C1192j.g0(i2 + i10, i7 + i2, this.e, objArr2, objArr2);
        Object[] objArr3 = this.d;
        int i13 = this.e;
        a.v(objArr3, i13 - i12, i13);
        if (i12 > 0) {
            this.modCount++;
        }
        this.e -= i12;
        return i12;
    }

    public final ListIterator listIterator(int i2) {
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.b(i2, i7);
        return new C1212a(this, i2);
    }

    public final boolean addAll(int i2, Collection collection) {
        j.e(collection, "elements");
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.b(i2, i7);
        int size = collection.size();
        s(i2, collection, size);
        return size > 0;
    }

    public final void add(int i2, Object obj) {
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.e;
        bVar.getClass();
        C1184b.b(i2, i7);
        this.modCount++;
        v(i2, 1);
        this.d[i2] = obj;
    }

    public final Object[] toArray() {
        return C1192j.i0(this.d, 0, this.e);
    }
}
