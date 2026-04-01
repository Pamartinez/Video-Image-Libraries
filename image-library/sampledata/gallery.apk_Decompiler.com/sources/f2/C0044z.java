package F2;

import D1.f;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: F2.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0044z extends AbstractCollection {
    public final /* synthetic */ int d = 0;
    public final AbstractMap e;

    public C0044z(A a7) {
        this.e = a7;
    }

    public final void clear() {
        switch (this.d) {
            case 0:
                ((A) this.e).clear();
                return;
            default:
                ((C0017g) this.e).clear();
                return;
        }
    }

    public boolean contains(Object obj) {
        switch (this.d) {
            case 1:
                return ((C0017g) this.e).containsValue(obj);
            default:
                return super.contains(obj);
        }
    }

    public boolean isEmpty() {
        switch (this.d) {
            case 1:
                return ((C0017g) this.e).isEmpty();
            default:
                return super.isEmpty();
        }
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                A a7 = (A) this.e;
                Map c5 = a7.c();
                if (c5 != null) {
                    return c5.values().iterator();
                }
                return new C0041w(a7, 2);
            default:
                return new N0(((C0017g) this.e).entrySet().iterator());
        }
    }

    public boolean remove(Object obj) {
        switch (this.d) {
            case 1:
                C0017g gVar = (C0017g) this.e;
                try {
                    return super.remove(obj);
                } catch (UnsupportedOperationException unused) {
                    for (Map.Entry entry : gVar.entrySet()) {
                        if (f.p(obj, entry.getValue())) {
                            gVar.remove(entry.getKey());
                            return true;
                        }
                    }
                    return false;
                }
            default:
                return super.remove(obj);
        }
    }

    public boolean removeAll(Collection collection) {
        switch (this.d) {
            case 1:
                C0017g gVar = (C0017g) this.e;
                try {
                    collection.getClass();
                    return super.removeAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    for (Map.Entry entry : gVar.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return gVar.keySet().removeAll(hashSet);
                }
            default:
                return super.removeAll(collection);
        }
    }

    public boolean retainAll(Collection collection) {
        switch (this.d) {
            case 1:
                C0017g gVar = (C0017g) this.e;
                try {
                    collection.getClass();
                    return super.retainAll(collection);
                } catch (UnsupportedOperationException unused) {
                    HashSet hashSet = new HashSet();
                    for (Map.Entry entry : gVar.entrySet()) {
                        if (collection.contains(entry.getValue())) {
                            hashSet.add(entry.getKey());
                        }
                    }
                    return gVar.keySet().retainAll(hashSet);
                }
            default:
                return super.retainAll(collection);
        }
    }

    public final int size() {
        switch (this.d) {
            case 0:
                return ((A) this.e).size();
            default:
                return ((C0017g) this.e).f.size();
        }
    }

    public C0044z(C0017g gVar) {
        this.e = gVar;
    }
}
