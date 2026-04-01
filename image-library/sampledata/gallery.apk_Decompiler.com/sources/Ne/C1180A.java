package ne;

import Be.a;
import Tf.i;
import java.util.List;
import java.util.ListIterator;

/* renamed from: ne.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1180A implements ListIterator, a {
    public final /* synthetic */ int d = 1;
    public final ListIterator e;
    public final /* synthetic */ Object f;

    public C1180A(i iVar, int i2) {
        this.f = iVar;
        this.e = ((List) iVar.e).listIterator(C1194l.E0(i2, iVar));
    }

    public final void add(Object obj) {
        switch (this.d) {
            case 0:
                ListIterator listIterator = this.e;
                listIterator.add(obj);
                listIterator.previous();
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                return this.e.hasPrevious();
            default:
                return this.e.hasPrevious();
        }
    }

    public final boolean hasPrevious() {
        switch (this.d) {
            case 0:
                return this.e.hasNext();
            default:
                return this.e.hasNext();
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                return this.e.previous();
            default:
                return this.e.previous();
        }
    }

    public final int nextIndex() {
        int previousIndex;
        int p02;
        switch (this.d) {
            case 0:
                previousIndex = this.e.previousIndex();
                p02 = C1195m.p0((C1181B) this.f);
                break;
            default:
                previousIndex = this.e.previousIndex();
                p02 = C1195m.p0((i) this.f);
                break;
        }
        return p02 - previousIndex;
    }

    public final Object previous() {
        switch (this.d) {
            case 0:
                return this.e.next();
            default:
                return this.e.next();
        }
    }

    public final int previousIndex() {
        int nextIndex;
        int p02;
        switch (this.d) {
            case 0:
                nextIndex = this.e.nextIndex();
                p02 = C1195m.p0((C1181B) this.f);
                break;
            default:
                nextIndex = this.e.nextIndex();
                p02 = C1195m.p0((i) this.f);
                break;
        }
        return p02 - nextIndex;
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                this.e.remove();
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public final void set(Object obj) {
        switch (this.d) {
            case 0:
                this.e.set(obj);
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C1180A(C1181B b, int i2) {
        this.f = b;
        this.e = b.d.listIterator(C1194l.E0(i2, b));
    }
}
