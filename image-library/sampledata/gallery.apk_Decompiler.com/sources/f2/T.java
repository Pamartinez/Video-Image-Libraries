package F2;

import He.F;
import java.util.Iterator;
import java.util.ListIterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T extends U {
    public final transient int f;
    public final transient int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ U f249h;

    public T(U u, int i2, int i7) {
        this.f249h = u;
        this.f = i2;
        this.g = i7;
    }

    /* renamed from: G */
    public final U subList(int i2, int i7) {
        F.p(i2, i7, this.g);
        int i8 = this.f;
        return this.f249h.subList(i2 + i8, i7 + i8);
    }

    public final Object get(int i2) {
        F.m(i2, this.g);
        return this.f249h.get(i2 + this.f);
    }

    public final Iterator iterator() {
        return listIterator(0);
    }

    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public final Object[] r() {
        return this.f249h.r();
    }

    public final int s() {
        return this.f249h.t() + this.f + this.g;
    }

    public final int size() {
        return this.g;
    }

    public final int t() {
        return this.f249h.t() + this.f;
    }

    public final boolean u() {
        return true;
    }

    public final /* bridge */ /* synthetic */ ListIterator listIterator(int i2) {
        return listIterator(i2);
    }
}
