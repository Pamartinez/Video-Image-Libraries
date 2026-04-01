package ne;

import ig.i;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: ne.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1185c extends i implements ListIterator {
    public final /* synthetic */ C1187e g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1185c(C1187e eVar, int i2) {
        super(7, eVar);
        this.g = eVar;
        C1184b bVar = C1187e.Companion;
        int size = eVar.size();
        bVar.getClass();
        C1184b.b(i2, size);
        this.e = i2;
    }

    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean hasPrevious() {
        if (this.e > 0) {
            return true;
        }
        return false;
    }

    public final int nextIndex() {
        return this.e;
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i2 = this.e - 1;
            this.e = i2;
            return this.g.get(i2);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.e - 1;
    }

    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
