package com.google.android.appfunctions.schema.internal.dependencies;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a0 extends AbstractList implements C0105o {
    public boolean d;

    public final boolean addAll(int i2, Collection collection) {
        i();
        return super.addAll(i2, collection);
    }

    public final void clear() {
        i();
        super.clear();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (!get(i2).equals(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i7 = 0; i7 < size; i7++) {
            i2 = (i2 * 31) + get(i7).hashCode();
        }
        return i2;
    }

    public final void i() {
        if (!this.d) {
            throw new UnsupportedOperationException();
        }
    }

    public abstract Object remove(int i2);

    public final boolean remove(Object obj) {
        i();
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public final boolean removeAll(Collection collection) {
        i();
        return super.removeAll(collection);
    }

    public final boolean retainAll(Collection collection) {
        i();
        return super.retainAll(collection);
    }

    public final boolean addAll(Collection collection) {
        i();
        return super.addAll(collection);
    }
}
