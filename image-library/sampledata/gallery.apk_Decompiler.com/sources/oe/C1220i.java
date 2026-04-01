package oe;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import ne.C1189g;

/* renamed from: oe.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1220i extends C1189g implements Serializable {
    public static final C1220i e = new C1220i(C1217f.q);
    public final C1217f d;

    static {
        C1217f fVar = C1217f.q;
    }

    public C1220i(C1217f fVar) {
        j.e(fVar, "backing");
        this.d = fVar;
    }

    public final boolean add(Object obj) {
        if (this.d.a(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean addAll(Collection collection) {
        j.e(collection, "elements");
        this.d.c();
        return super.addAll(collection);
    }

    public final void clear() {
        this.d.clear();
    }

    public final boolean contains(Object obj) {
        return this.d.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    public final Iterator iterator() {
        C1217f fVar = this.d;
        fVar.getClass();
        return new C1215d(fVar, 1);
    }

    public final int p() {
        return this.d.l;
    }

    public final boolean remove(Object obj) {
        C1217f fVar = this.d;
        fVar.c();
        int j2 = fVar.j(obj);
        if (j2 < 0) {
            return false;
        }
        fVar.o(j2);
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

    public C1220i() {
        this(new C1217f());
    }
}
