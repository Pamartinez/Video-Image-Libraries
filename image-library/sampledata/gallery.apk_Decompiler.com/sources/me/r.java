package me;

import Be.a;
import ig.i;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements Collection, a {
    public final int[] d;

    public /* synthetic */ r(int[] iArr) {
        this.d = iArr;
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
        if (!(obj instanceof q)) {
            return false;
        }
        return C1192j.c0(((q) obj).d, this.d);
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean containsAll(java.util.Collection r3) {
        /*
            r2 = this;
            java.lang.String r0 = "elements"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r0 = r3
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            java.util.Iterator r3 = r3.iterator()
        L_0x0015:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r3.next()
            boolean r1 = r0 instanceof me.q
            if (r1 == 0) goto L_0x0030
            me.q r0 = (me.q) r0
            int r0 = r0.d
            int[] r1 = r2.d
            boolean r0 = ne.C1192j.c0(r0, r1)
            if (r0 == 0) goto L_0x0030
            goto L_0x0015
        L_0x0030:
            r2 = 0
            return r2
        L_0x0032:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: me.r.containsAll(java.util.Collection):boolean");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        if (!j.a(this.d, ((r) obj).d)) {
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
        return new i(4, this.d);
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
        return "UIntArray(storage=" + Arrays.toString(this.d) + ')';
    }

    public final Object[] toArray(Object[] objArr) {
        j.e(objArr, "array");
        return kotlin.jvm.internal.i.b(this, objArr);
    }
}
