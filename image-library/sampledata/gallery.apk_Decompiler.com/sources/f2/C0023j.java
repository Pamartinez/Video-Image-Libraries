package F2;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: F2.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0023j extends C0029m implements NavigableSet {
    public final /* synthetic */ t0 g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0023j(t0 t0Var, NavigableMap navigableMap) {
        super(t0Var, navigableMap);
        this.g = t0Var;
    }

    public final Object ceiling(Object obj) {
        return i().ceilingKey(obj);
    }

    public final Iterator descendingIterator() {
        return ((C0019h) descendingSet()).iterator();
    }

    public final NavigableSet descendingSet() {
        return new C0023j(this.g, i().descendingMap());
    }

    public final Object floor(Object obj) {
        return i().floorKey(obj);
    }

    public final SortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    public final Object higher(Object obj) {
        return i().higherKey(obj);
    }

    public final Object lower(Object obj) {
        return i().lowerKey(obj);
    }

    /* renamed from: p */
    public final NavigableMap i() {
        return (NavigableMap) ((SortedMap) this.d);
    }

    public final Object pollFirst() {
        C0015f fVar = (C0015f) iterator();
        if (!fVar.hasNext()) {
            return null;
        }
        Object next = fVar.next();
        fVar.remove();
        return next;
    }

    public final Object pollLast() {
        Iterator descendingIterator = descendingIterator();
        if (!descendingIterator.hasNext()) {
            return null;
        }
        Object next = descendingIterator.next();
        descendingIterator.remove();
        return next;
    }

    public final SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public final SortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }

    public final NavigableSet headSet(Object obj, boolean z) {
        return new C0023j(this.g, i().headMap(obj, z));
    }

    public final NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z3) {
        return new C0023j(this.g, i().subMap(obj, z, obj2, z3));
    }

    public final NavigableSet tailSet(Object obj, boolean z) {
        return new C0023j(this.g, i().tailMap(obj, z));
    }
}
