package Qf;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements Iterator {
    public boolean d;
    public final int e;
    public final /* synthetic */ f f;

    public e(f fVar) {
        this.f = fVar;
        this.e = fVar.modCount;
    }

    public final void a() {
        f fVar = this.f;
        int q = fVar.modCount;
        int i2 = this.e;
        if (q != i2) {
            throw new ConcurrentModificationException("ModCount: " + fVar.modCount + "; expected: " + i2);
        }
    }

    public final boolean hasNext() {
        return !this.d;
    }

    public final Object next() {
        if (!this.d) {
            this.d = true;
            a();
            return this.f.e;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        a();
        this.f.clear();
    }
}
