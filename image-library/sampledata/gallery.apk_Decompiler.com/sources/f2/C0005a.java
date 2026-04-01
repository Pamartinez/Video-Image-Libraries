package F2;

import He.F;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: F2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0005a extends O0 implements ListIterator {
    public final int d;
    public int e;

    public C0005a(int i2, int i7) {
        F.o(i7, i2);
        this.d = i2;
        this.e = i7;
    }

    public abstract Object a(int i2);

    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        if (this.e < this.d) {
            return true;
        }
        return false;
    }

    public final boolean hasPrevious() {
        if (this.e > 0) {
            return true;
        }
        return false;
    }

    public final Object next() {
        if (hasNext()) {
            int i2 = this.e;
            this.e = i2 + 1;
            return a(i2);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.e;
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i2 = this.e - 1;
            this.e = i2;
            return a(i2);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.e - 1;
    }

    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
