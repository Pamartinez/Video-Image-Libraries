package oe;

import Be.a;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* renamed from: oe.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1219h extends AbstractCollection implements Collection, a {
    public final C1217f d;

    public C1219h(C1217f fVar) {
        this.d = fVar;
    }

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        j.e(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.d.clear();
    }

    public final boolean contains(Object obj) {
        return this.d.containsValue(obj);
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        C1217f fVar = this.d;
        fVar.getClass();
        return new C1215d(fVar, 2);
    }

    public final boolean remove(Object obj) {
        C1217f fVar = this.d;
        fVar.c();
        int k = fVar.k(obj);
        if (k < 0) {
            return false;
        }
        fVar.o(k);
        return true;
    }

    public final boolean removeAll(Collection collection) {
        j.e(collection, "elements");
        this.d.c();
        return super.removeAll(collection);
    }

    public final boolean retainAll(Collection collection) {
        j.e(collection, "elements");
        this.d.c();
        return super.retainAll(collection);
    }

    public final int size() {
        return this.d.l;
    }
}
