package F2;

import java.util.AbstractList;
import java.util.ListIterator;

/* renamed from: F2.n0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0032n0 extends N0 implements ListIterator {
    public final /* synthetic */ int e;
    public final /* synthetic */ AbstractList f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0032n0(AbstractList abstractList, ListIterator listIterator, int i2) {
        super(listIterator);
        this.e = i2;
        this.f = abstractList;
    }

    public final Object a(Object obj) {
        switch (this.e) {
            case 0:
                return ((C0034o0) this.f).e.apply(obj);
            default:
                return ((p0) this.f).e.apply(obj);
        }
    }

    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasPrevious() {
        return ((ListIterator) this.d).hasPrevious();
    }

    public final int nextIndex() {
        return ((ListIterator) this.d).nextIndex();
    }

    public final Object previous() {
        return a(((ListIterator) this.d).previous());
    }

    public final int previousIndex() {
        return ((ListIterator) this.d).previousIndex();
    }

    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
