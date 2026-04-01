package F2;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: F2.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0029m extends C0019h implements SortedSet {
    public final /* synthetic */ t0 f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0029m(t0 t0Var, SortedMap sortedMap) {
        super(t0Var, sortedMap);
        this.f = t0Var;
    }

    public final Comparator comparator() {
        return i().comparator();
    }

    public final Object first() {
        return i().firstKey();
    }

    public SortedSet headSet(Object obj) {
        return new C0029m(this.f, i().headMap(obj));
    }

    public SortedMap i() {
        return (SortedMap) this.d;
    }

    public final Object last() {
        return i().lastKey();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return new C0029m(this.f, i().subMap(obj, obj2));
    }

    public SortedSet tailSet(Object obj) {
        return new C0029m(this.f, i().tailMap(obj));
    }
}
