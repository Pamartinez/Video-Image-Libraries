package me;

import Be.a;
import ig.i;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w implements Collection, a {
    public final short[] d;

    public /* synthetic */ w(short[] sArr) {
        this.d = sArr;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof v)) {
            return false;
        }
        short s = ((v) obj).d;
        short[] sArr = this.d;
        int length = sArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (s == sArr[i2]) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        boolean z;
        j.e(collection, "elements");
        Iterable iterable = collection;
        if (!((Collection) iterable).isEmpty()) {
            for (Object next : iterable) {
                if (next instanceof v) {
                    short s = ((v) next).d;
                    short[] sArr = this.d;
                    int length = sArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            i2 = -1;
                            break;
                        } else if (s == sArr[i2]) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i2 >= 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof w)) {
            return false;
        }
        if (!j.a(this.d, ((w) obj).d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.d);
    }

    public final boolean isEmpty() {
        if (this.d.length == 0) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return new i(6, this.d);
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
        return kotlin.jvm.internal.i.a(this);
    }

    public final String toString() {
        return "UShortArray(storage=" + Arrays.toString(this.d) + ')';
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return kotlin.jvm.internal.i.b(this, objArr);
    }
}
