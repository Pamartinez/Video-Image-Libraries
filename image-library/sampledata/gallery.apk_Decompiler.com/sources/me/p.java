package me;

import Be.a;
import ig.i;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements Collection, a {
    public final byte[] d;

    public /* synthetic */ p(byte[] bArr) {
        this.d = bArr;
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
        if (!(obj instanceof o)) {
            return false;
        }
        byte b = ((o) obj).d;
        byte[] bArr = this.d;
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if (b == bArr[i2]) {
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
                if (next instanceof o) {
                    byte b = ((o) next).d;
                    byte[] bArr = this.d;
                    int length = bArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            i2 = -1;
                            break;
                        } else if (b == bArr[i2]) {
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
        if (!(obj instanceof p)) {
            return false;
        }
        if (!j.a(this.d, ((p) obj).d)) {
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
        return new i(3, this.d);
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
        return "UByteArray(storage=" + Arrays.toString(this.d) + ')';
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return kotlin.jvm.internal.i.b(this, objArr);
    }
}
