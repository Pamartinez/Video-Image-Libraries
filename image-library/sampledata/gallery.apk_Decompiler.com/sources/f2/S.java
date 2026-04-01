package F2;

import He.F;
import java.util.Iterator;
import java.util.ListIterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S extends U {
    public final transient U f;

    public S(U u) {
        this.f = u;
    }

    public final U F() {
        return this.f;
    }

    /* renamed from: G */
    public final U subList(int i2, int i7) {
        U u = this.f;
        F.p(i2, i7, u.size());
        return u.subList(u.size() - i7, u.size() - i2).F();
    }

    public final boolean contains(Object obj) {
        return this.f.contains(obj);
    }

    public final Object get(int i2) {
        U u = this.f;
        F.m(i2, u.size());
        return u.get((u.size() - 1) - i2);
    }

    public final int indexOf(Object obj) {
        U u = this.f;
        int lastIndexOf = u.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return (u.size() - 1) - lastIndexOf;
        }
        return -1;
    }

    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(Object obj) {
        U u = this.f;
        int indexOf = u.indexOf(obj);
        if (indexOf >= 0) {
            return (u.size() - 1) - indexOf;
        }
        return -1;
    }

    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public final int size() {
        return this.f.size();
    }

    public final boolean u() {
        return this.f.u();
    }

    public final /* bridge */ /* synthetic */ ListIterator listIterator(int i2) {
        return listIterator(i2);
    }
}
