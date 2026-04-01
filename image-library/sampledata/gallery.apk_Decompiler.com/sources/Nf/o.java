package Nf;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements Iterator, a {
    public final /* synthetic */ int d;
    public boolean e = true;
    public final Object f;

    public /* synthetic */ o(int i2, Object obj) {
        this.d = i2;
        this.f = obj;
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                return this.e;
            default:
                return this.e;
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                if (this.e) {
                    this.e = false;
                    return ((p) this.f).d;
                }
                throw new NoSuchElementException();
            default:
                if (this.e) {
                    this.e = false;
                    return this.f;
                }
                throw new NoSuchElementException();
        }
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException();
        }
    }
}
