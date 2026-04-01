package F2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: F2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0013e extends K0 {
    public final /* synthetic */ C0017g d;

    public C0013e(C0017g gVar) {
        this.d = gVar;
    }

    public final void clear() {
        this.d.clear();
    }

    public final boolean contains(Object obj) {
        Set entrySet = this.d.f.entrySet();
        entrySet.getClass();
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        return new C0015f(this.d);
    }

    public final boolean remove(Object obj) {
        Object obj2;
        if (!contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Objects.requireNonNull(entry);
        C0009c cVar = this.d.g;
        Object key = entry.getKey();
        Map map = cVar.f256h;
        map.getClass();
        try {
            obj2 = map.remove(key);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection == null) {
            return true;
        }
        int size = collection.size();
        collection.clear();
        cVar.f257i -= size;
        return true;
    }

    public final boolean removeAll(Collection collection) {
        try {
            collection.getClass();
            return super.removeAll(collection);
        } catch (UnsupportedOperationException unused) {
            boolean z = false;
            for (Object remove : collection) {
                z |= remove(remove);
            }
            return z;
        }
    }

    public final boolean retainAll(Collection collection) {
        int i2;
        try {
            collection.getClass();
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            int size = collection.size();
            if (size < 3) {
                C0040v.c(size, "expectedSize");
                i2 = size + 1;
            } else if (size < 1073741824) {
                i2 = (int) Math.ceil(((double) size) / 0.75d);
            } else {
                i2 = Integer.MAX_VALUE;
            }
            HashSet hashSet = new HashSet(i2);
            for (Object next : collection) {
                if (contains(next) && (next instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) next).getKey());
                }
            }
            return this.d.keySet().retainAll(hashSet);
        }
    }

    public final int size() {
        return this.d.f.size();
    }
}
