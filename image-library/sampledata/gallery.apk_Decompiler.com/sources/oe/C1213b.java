package oe;

import L2.a;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.j;
import ne.C1184b;
import ne.C1187e;
import ne.C1188f;
import ne.C1192j;

/* renamed from: oe.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1213b extends C1188f implements RandomAccess, Serializable {
    public Object[] d;
    public final int e;
    public int f;
    public final C1213b g;

    /* renamed from: h  reason: collision with root package name */
    public final C1214c f4977h;

    public C1213b(Object[] objArr, int i2, int i7, C1213b bVar, C1214c cVar) {
        j.e(objArr, "backing");
        j.e(cVar, "root");
        this.d = objArr;
        this.e = i2;
        this.f = i7;
        this.g = bVar;
        this.f4977h = cVar;
        this.modCount = cVar.modCount;
    }

    public final boolean add(Object obj) {
        v();
        u();
        t(this.e + this.f, obj);
        return true;
    }

    public final boolean addAll(Collection collection) {
        j.e(collection, "elements");
        v();
        u();
        int size = collection.size();
        s(this.e + this.f, collection, size);
        return size > 0;
    }

    public final void clear() {
        v();
        u();
        x(this.e, this.f);
    }

    public final boolean equals(Object obj) {
        u();
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.d;
            int i2 = this.f;
            if (i2 == list.size()) {
                int i7 = 0;
                while (i7 < i2) {
                    if (j.a(objArr[this.e + i7], list.get(i7))) {
                        i7++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final Object get(int i2) {
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        return this.d[this.e + i2];
    }

    public final int hashCode() {
        int i2;
        u();
        Object[] objArr = this.d;
        int i7 = this.f;
        int i8 = 1;
        for (int i10 = 0; i10 < i7; i10++) {
            Object obj = objArr[this.e + i10];
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
        u();
        for (int i2 = 0; i2 < this.f; i2++) {
            if (j.a(this.d[this.e + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        u();
        if (this.f == 0) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(Object obj) {
        u();
        for (int i2 = this.f - 1; i2 >= 0; i2--) {
            if (j.a(this.d[this.e + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public final int p() {
        u();
        return this.f;
    }

    public final Object q(int i2) {
        v();
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        return w(this.e + i2);
    }

    public final boolean remove(Object obj) {
        v();
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
        v();
        u();
        if (y(this.e, this.f, collection, false) > 0) {
            return true;
        }
        return false;
    }

    public final boolean retainAll(Collection collection) {
        j.e(collection, "elements");
        v();
        u();
        if (y(this.e, this.f, collection, true) > 0) {
            return true;
        }
        return false;
    }

    public final void s(int i2, Collection collection, int i7) {
        this.modCount++;
        C1214c cVar = this.f4977h;
        C1213b bVar = this.g;
        if (bVar != null) {
            bVar.s(i2, collection, i7);
        } else {
            C1214c cVar2 = C1214c.g;
            cVar.s(i2, collection, i7);
        }
        this.d = cVar.d;
        this.f += i7;
    }

    public final Object set(int i2, Object obj) {
        v();
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.a(i2, i7);
        Object[] objArr = this.d;
        int i8 = this.e + i2;
        Object obj2 = objArr[i8];
        objArr[i8] = obj;
        return obj2;
    }

    public final List subList(int i2, int i7) {
        C1184b bVar = C1187e.Companion;
        int i8 = this.f;
        bVar.getClass();
        C1184b.c(i2, i7, i8);
        return new C1213b(this.d, this.e + i2, i7 - i2, this, this.f4977h);
    }

    public final void t(int i2, Object obj) {
        this.modCount++;
        C1214c cVar = this.f4977h;
        C1213b bVar = this.g;
        if (bVar != null) {
            bVar.t(i2, obj);
        } else {
            C1214c cVar2 = C1214c.g;
            cVar.t(i2, obj);
        }
        this.d = cVar.d;
        this.f++;
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        u();
        int length = objArr.length;
        int i2 = this.f;
        int i7 = this.e;
        if (length < i2) {
            Object[] copyOfRange = Arrays.copyOfRange(this.d, i7, i2 + i7, objArr.getClass());
            j.d(copyOfRange, "copyOfRange(...)");
            return copyOfRange;
        }
        C1192j.g0(0, i7, i2 + i7, this.d, objArr);
        int i8 = this.f;
        if (i8 < objArr.length) {
            objArr[i8] = null;
        }
        return objArr;
    }

    public final String toString() {
        u();
        return a.b(this.d, this.e, this.f, this);
    }

    public final void u() {
        if (this.f4977h.modCount != this.modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void v() {
        if (this.f4977h.f) {
            throw new UnsupportedOperationException();
        }
    }

    public final Object w(int i2) {
        Object obj;
        this.modCount++;
        C1213b bVar = this.g;
        if (bVar != null) {
            obj = bVar.w(i2);
        } else {
            C1214c cVar = C1214c.g;
            obj = this.f4977h.w(i2);
        }
        this.f--;
        return obj;
    }

    public final void x(int i2, int i7) {
        if (i7 > 0) {
            this.modCount++;
        }
        C1213b bVar = this.g;
        if (bVar != null) {
            bVar.x(i2, i7);
        } else {
            C1214c cVar = C1214c.g;
            this.f4977h.x(i2, i7);
        }
        this.f -= i7;
    }

    public final int y(int i2, int i7, Collection collection, boolean z) {
        int i8;
        C1213b bVar = this.g;
        if (bVar != null) {
            i8 = bVar.y(i2, i7, collection, z);
        } else {
            C1214c cVar = C1214c.g;
            i8 = this.f4977h.y(i2, i7, collection, z);
        }
        if (i8 > 0) {
            this.modCount++;
        }
        this.f -= i8;
        return i8;
    }

    public final ListIterator listIterator(int i2) {
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.b(i2, i7);
        return new C1212a(this, i2);
    }

    public final void add(int i2, Object obj) {
        v();
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.b(i2, i7);
        t(this.e + i2, obj);
    }

    public final boolean addAll(int i2, Collection collection) {
        j.e(collection, "elements");
        v();
        u();
        C1184b bVar = C1187e.Companion;
        int i7 = this.f;
        bVar.getClass();
        C1184b.b(i2, i7);
        int size = collection.size();
        s(this.e + i2, collection, size);
        return size > 0;
    }

    public final Object[] toArray() {
        u();
        Object[] objArr = this.d;
        int i2 = this.f;
        int i7 = this.e;
        return C1192j.i0(objArr, i7, i2 + i7);
    }
}
