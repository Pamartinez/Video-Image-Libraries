package F2;

import He.F;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

/* renamed from: F2.f0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0016f0 extends Y implements NavigableMap {
    public static final C0016f0 k;

    /* renamed from: h  reason: collision with root package name */
    public final transient F0 f260h;

    /* renamed from: i  reason: collision with root package name */
    public final transient U f261i;

    /* renamed from: j  reason: collision with root package name */
    public final transient C0016f0 f262j;

    static {
        F0 D = C0018g0.D(v0.e);
        G g = U.e;
        k = new C0016f0(D, y0.f278h, (C0016f0) null);
    }

    public C0016f0(F0 f02, U u, C0016f0 f0Var) {
        this.f260h = f02;
        this.f261i = u;
        this.f262j = f0Var;
    }

    public static C0016f0 j(Comparator comparator) {
        if (v0.e.equals(comparator)) {
            return k;
        }
        return new C0016f0(C0018g0.D(comparator), y0.f278h, (C0016f0) null);
    }

    public final C0010c0 b() {
        if (!isEmpty()) {
            return new C0014e0(this);
        }
        int i2 = C0010c0.f;
        return E0.m;
    }

    public final C0010c0 c() {
        throw new AssertionError("should never be called");
    }

    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    public final Object ceilingKey(Object obj) {
        Map.Entry ceilingEntry = ceilingEntry(obj);
        if (ceilingEntry == null) {
            return null;
        }
        return ceilingEntry.getKey();
    }

    public final Comparator comparator() {
        return this.f260h.g;
    }

    public final O d() {
        throw new AssertionError("should never be called");
    }

    public final NavigableSet descendingKeySet() {
        return this.f260h.descendingSet();
    }

    public final NavigableMap descendingMap() {
        w0 w0Var;
        C0016f0 f0Var = this.f262j;
        if (f0Var != null) {
            return f0Var;
        }
        boolean isEmpty = isEmpty();
        F0 f02 = this.f260h;
        if (!isEmpty) {
            return new C0016f0((F0) f02.descendingSet(), this.f261i.F(), this);
        }
        Comparator comparator = f02.g;
        if (comparator instanceof w0) {
            w0Var = (w0) comparator;
        } else {
            w0Var = new B(comparator);
        }
        return j(w0Var.a());
    }

    public final Set entrySet() {
        return super.entrySet();
    }

    public final boolean f() {
        if (this.f260h.f246i.u() || this.f261i.u()) {
            return true;
        }
        return false;
    }

    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) super.entrySet().p().get(0);
    }

    public final Object firstKey() {
        return this.f260h.first();
    }

    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    public final Object floorKey(Object obj) {
        Map.Entry floorEntry = floorEntry(obj);
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getKey();
    }

    public final C0010c0 g() {
        return this.f260h;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r4 >= 0) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(java.lang.Object r4) {
        /*
            r3 = this;
            F2.F0 r0 = r3.f260h
            r0.getClass()
            r1 = -1
            if (r4 != 0) goto L_0x000a
        L_0x0008:
            r4 = r1
            goto L_0x0014
        L_0x000a:
            F2.U r2 = r0.f246i     // Catch:{ ClassCastException -> 0x0008 }
            java.util.Comparator r0 = r0.g     // Catch:{ ClassCastException -> 0x0008 }
            int r4 = java.util.Collections.binarySearch(r2, r4, r0)     // Catch:{ ClassCastException -> 0x0008 }
            if (r4 < 0) goto L_0x0008
        L_0x0014:
            if (r4 != r1) goto L_0x0018
            r3 = 0
            goto L_0x001e
        L_0x0018:
            F2.U r3 = r3.f261i
            java.lang.Object r3 = r3.get(r4)
        L_0x001e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: F2.C0016f0.get(java.lang.Object):java.lang.Object");
    }

    public final O h() {
        return this.f261i;
    }

    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    public final Object higherKey(Object obj) {
        Map.Entry higherEntry = higherEntry(obj);
        if (higherEntry == null) {
            return null;
        }
        return higherEntry.getKey();
    }

    public final C0016f0 k(int i2, int i7) {
        U u = this.f261i;
        if (i2 == 0 && i7 == u.size()) {
            return this;
        }
        F0 f02 = this.f260h;
        if (i2 == i7) {
            return j(f02.g);
        }
        return new C0016f0(f02.F(i2, i7), u.subList(i2, i7), (C0016f0) null);
    }

    public final Set keySet() {
        return this.f260h;
    }

    /* renamed from: l */
    public final C0016f0 headMap(Object obj, boolean z) {
        obj.getClass();
        return k(0, this.f260h.G(obj, z));
    }

    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) super.entrySet().p().get(this.f261i.size() - 1);
    }

    public final Object lastKey() {
        return this.f260h.last();
    }

    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    public final Object lowerKey(Object obj) {
        Map.Entry lowerEntry = lowerEntry(obj);
        if (lowerEntry == null) {
            return null;
        }
        return lowerEntry.getKey();
    }

    /* renamed from: m */
    public final C0016f0 subMap(Object obj, boolean z, Object obj2, boolean z3) {
        boolean z7;
        obj.getClass();
        obj2.getClass();
        if (this.f260h.g.compare(obj, obj2) <= 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        F.l(z7, "expected fromKey <= toKey but %s > %s", obj, obj2);
        return headMap(obj2, z3).tailMap(obj, z);
    }

    /* renamed from: n */
    public final C0016f0 tailMap(Object obj, boolean z) {
        obj.getClass();
        return k(this.f260h.H(obj, z), this.f261i.size());
    }

    public final NavigableSet navigableKeySet() {
        return this.f260h;
    }

    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.f261i.size();
    }

    public final Collection values() {
        return this.f261i;
    }

    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public final SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}
