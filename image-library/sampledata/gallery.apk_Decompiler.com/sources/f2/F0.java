package F2;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F0 extends C0018g0 {

    /* renamed from: j  reason: collision with root package name */
    public static final F0 f245j = new F0(y0.f278h, v0.e);

    /* renamed from: i  reason: collision with root package name */
    public final transient U f246i;

    static {
        G g = U.e;
    }

    public F0(U u, Comparator comparator) {
        super(comparator);
        this.f246i = u;
    }

    public final F0 F(int i2, int i7) {
        U u = this.f246i;
        if (i2 == 0 && i7 == u.size()) {
            return this;
        }
        Comparator comparator = this.g;
        if (i2 < i7) {
            return new F0(u.subList(i2, i7), comparator);
        }
        return C0018g0.D(comparator);
    }

    public final int G(Object obj, boolean z) {
        obj.getClass();
        int binarySearch = Collections.binarySearch(this.f246i, obj, this.g);
        if (binarySearch < 0) {
            return ~binarySearch;
        }
        if (z) {
            return binarySearch + 1;
        }
        return binarySearch;
    }

    public final int H(Object obj, boolean z) {
        obj.getClass();
        int binarySearch = Collections.binarySearch(this.f246i, obj, this.g);
        if (binarySearch < 0) {
            return ~binarySearch;
        }
        if (z) {
            return binarySearch;
        }
        return binarySearch + 1;
    }

    public final Object ceiling(Object obj) {
        int H5 = H(obj, true);
        U u = this.f246i;
        if (H5 == u.size()) {
            return null;
        }
        return u.get(H5);
    }

    public final boolean contains(Object obj) {
        if (obj != null) {
            try {
                if (Collections.binarySearch(this.f246i, obj, this.g) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        if (collection instanceof u0) {
            collection = ((u0) collection).elementSet();
        }
        Comparator comparator = this.g;
        if (!C0040v.i(comparator, collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        O0 v = v();
        Iterator it = collection.iterator();
        C0005a aVar = (C0005a) v;
        if (!aVar.hasNext()) {
            return false;
        }
        Object next = it.next();
        Object next2 = aVar.next();
        while (true) {
            try {
                int compare = comparator.compare(next2, next);
                if (compare < 0) {
                    if (!aVar.hasNext()) {
                        return false;
                    }
                    next2 = aVar.next();
                } else if (compare == 0) {
                    if (!it.hasNext()) {
                        return true;
                    }
                    next = it.next();
                } else if (compare > 0) {
                    return false;
                }
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
    }

    public final Iterator descendingIterator() {
        return this.f246i.F().listIterator(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037 A[Catch:{ ClassCastException | NoSuchElementException -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            if (r4 != r3) goto L_0x0003
            goto L_0x0048
        L_0x0003:
            boolean r0 = r4 instanceof java.util.Set
            if (r0 != 0) goto L_0x0008
            goto L_0x004a
        L_0x0008:
            java.util.Set r4 = (java.util.Set) r4
            F2.U r0 = r3.f246i
            int r0 = r0.size()
            int r1 = r4.size()
            if (r0 == r1) goto L_0x0017
            goto L_0x004a
        L_0x0017:
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x001e
            goto L_0x0048
        L_0x001e:
            java.util.Comparator r0 = r3.g
            boolean r1 = F2.C0040v.i(r0, r4)
            if (r1 == 0) goto L_0x004c
            java.util.Iterator r4 = r4.iterator()
            F2.O0 r3 = r3.v()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
        L_0x002e:
            r1 = r3
            F2.a r1 = (F2.C0005a) r1     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
            boolean r2 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
            if (r2 == 0) goto L_0x0048
            java.lang.Object r1 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
            java.lang.Object r2 = r4.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
            if (r2 == 0) goto L_0x004a
            int r1 = r0.compare(r1, r2)     // Catch:{ ClassCastException | NoSuchElementException -> 0x004a }
            if (r1 == 0) goto L_0x002e
            goto L_0x004a
        L_0x0048:
            r3 = 1
            return r3
        L_0x004a:
            r3 = 0
            return r3
        L_0x004c:
            boolean r3 = r3.containsAll(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: F2.F0.equals(java.lang.Object):boolean");
    }

    public final Object first() {
        if (!isEmpty()) {
            return this.f246i.get(0);
        }
        throw new NoSuchElementException();
    }

    public final Object floor(Object obj) {
        int G5 = G(obj, true) - 1;
        if (G5 == -1) {
            return null;
        }
        return this.f246i.get(G5);
    }

    public final Object higher(Object obj) {
        int H5 = H(obj, false);
        U u = this.f246i;
        if (H5 == u.size()) {
            return null;
        }
        return u.get(H5);
    }

    public final Object last() {
        if (!isEmpty()) {
            U u = this.f246i;
            return u.get(u.size() - 1);
        }
        throw new NoSuchElementException();
    }

    public final Object lower(Object obj) {
        int G5 = G(obj, false) - 1;
        if (G5 == -1) {
            return null;
        }
        return this.f246i.get(G5);
    }

    public final U p() {
        return this.f246i;
    }

    public final int q(int i2, Object[] objArr) {
        return this.f246i.q(i2, objArr);
    }

    public final Object[] r() {
        return this.f246i.r();
    }

    public final int s() {
        return this.f246i.s();
    }

    public final int size() {
        return this.f246i.size();
    }

    public final int t() {
        return this.f246i.t();
    }

    public final boolean u() {
        return this.f246i.u();
    }

    public final O0 v() {
        return this.f246i.listIterator(0);
    }
}
