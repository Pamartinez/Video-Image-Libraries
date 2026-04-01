package F2;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: F2.k0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0026k0 implements Iterator {
    public Iterator d;
    public Iterator e;
    public Iterator f;
    public ArrayDeque g;

    public final boolean hasNext() {
        Iterator it;
        while (true) {
            Iterator it2 = this.e;
            it2.getClass();
            if (it2.hasNext()) {
                return true;
            }
            while (true) {
                Iterator it3 = this.f;
                if (it3 != null && it3.hasNext()) {
                    it = this.f;
                    break;
                }
                ArrayDeque arrayDeque = this.g;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    it = null;
                } else {
                    this.f = (Iterator) this.g.removeFirst();
                }
            }
            it = null;
            this.f = it;
            if (it == null) {
                return false;
            }
            Iterator it4 = (Iterator) it.next();
            this.e = it4;
            if (it4 instanceof C0026k0) {
                C0026k0 k0Var = (C0026k0) it4;
                this.e = k0Var.e;
                if (this.g == null) {
                    this.g = new ArrayDeque();
                }
                this.g.addFirst(this.f);
                if (k0Var.g != null) {
                    while (!k0Var.g.isEmpty()) {
                        this.g.addFirst((Iterator) k0Var.g.removeLast());
                    }
                }
                this.f = k0Var.f;
            }
        }
    }

    public final Object next() {
        if (hasNext()) {
            Iterator it = this.e;
            this.d = it;
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        Iterator it = this.d;
        if (it != null) {
            it.remove();
            this.d = null;
            return;
        }
        throw new IllegalStateException("no calls to next() since the last call to remove()");
    }
}
