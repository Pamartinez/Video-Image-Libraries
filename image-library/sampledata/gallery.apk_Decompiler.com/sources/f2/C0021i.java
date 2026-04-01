package F2;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: F2.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0021i extends C0027l implements NavigableMap {

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ t0 f264j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0021i(t0 t0Var, NavigableMap navigableMap) {
        super(t0Var, navigableMap);
        this.f264j = t0Var;
    }

    public final SortedSet b() {
        return new C0023j(this.f264j, d());
    }

    public final SortedSet c() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry ceilingEntry(Object obj) {
        Map.Entry ceilingEntry = d().ceilingEntry(obj);
        if (ceilingEntry == null) {
            return null;
        }
        return a(ceilingEntry);
    }

    public final Object ceilingKey(Object obj) {
        return d().ceilingKey(obj);
    }

    public final NavigableSet descendingKeySet() {
        return (NavigableSet) super.keySet();
    }

    public final NavigableMap descendingMap() {
        return new C0021i(this.f264j, d().descendingMap());
    }

    public final P e(Iterator it) {
        if (!it.hasNext()) {
            return null;
        }
        Map.Entry entry = (Map.Entry) it.next();
        Collection f = this.f264j.f();
        f.addAll((Collection) entry.getValue());
        it.remove();
        return new P(entry.getKey(), Collections.unmodifiableList((List) f));
    }

    /* renamed from: f */
    public final NavigableMap d() {
        return (NavigableMap) ((SortedMap) this.f);
    }

    public final Map.Entry firstEntry() {
        Map.Entry firstEntry = d().firstEntry();
        if (firstEntry == null) {
            return null;
        }
        return a(firstEntry);
    }

    public final Map.Entry floorEntry(Object obj) {
        Map.Entry floorEntry = d().floorEntry(obj);
        if (floorEntry == null) {
            return null;
        }
        return a(floorEntry);
    }

    public final Object floorKey(Object obj) {
        return d().floorKey(obj);
    }

    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public final Map.Entry higherEntry(Object obj) {
        Map.Entry higherEntry = d().higherEntry(obj);
        if (higherEntry == null) {
            return null;
        }
        return a(higherEntry);
    }

    public final Object higherKey(Object obj) {
        return d().higherKey(obj);
    }

    public final Set keySet() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry lastEntry() {
        Map.Entry lastEntry = d().lastEntry();
        if (lastEntry == null) {
            return null;
        }
        return a(lastEntry);
    }

    public final Map.Entry lowerEntry(Object obj) {
        Map.Entry lowerEntry = d().lowerEntry(obj);
        if (lowerEntry == null) {
            return null;
        }
        return a(lowerEntry);
    }

    public final Object lowerKey(Object obj) {
        return d().lowerKey(obj);
    }

    public final NavigableSet navigableKeySet() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry pollFirstEntry() {
        return e(((C0013e) entrySet()).iterator());
    }

    public final Map.Entry pollLastEntry() {
        return e(((C0013e) ((C0017g) descendingMap()).entrySet()).iterator());
    }

    public final SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    public final NavigableMap headMap(Object obj, boolean z) {
        return new C0021i(this.f264j, d().headMap(obj, z));
    }

    public final NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z3) {
        return new C0021i(this.f264j, d().subMap(obj, z, obj2, z3));
    }

    public final NavigableMap tailMap(Object obj, boolean z) {
        return new C0021i(this.f264j, d().tailMap(obj, z));
    }
}
