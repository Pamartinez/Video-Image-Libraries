package F2;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: F2.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0027l extends C0017g implements SortedMap {

    /* renamed from: h  reason: collision with root package name */
    public SortedSet f266h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ t0 f267i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0027l(t0 t0Var, SortedMap sortedMap) {
        super(t0Var, sortedMap);
        this.f267i = t0Var;
    }

    public SortedSet b() {
        return new C0029m(this.f267i, d());
    }

    /* renamed from: c */
    public SortedSet keySet() {
        SortedSet sortedSet = this.f266h;
        if (sortedSet != null) {
            return sortedSet;
        }
        SortedSet b = b();
        this.f266h = b;
        return b;
    }

    public final Comparator comparator() {
        return d().comparator();
    }

    public SortedMap d() {
        return (SortedMap) this.f;
    }

    public final Object firstKey() {
        return d().firstKey();
    }

    public SortedMap headMap(Object obj) {
        return new C0027l(this.f267i, d().headMap(obj));
    }

    public final Object lastKey() {
        return d().lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new C0027l(this.f267i, d().subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new C0027l(this.f267i, d().tailMap(obj));
    }
}
