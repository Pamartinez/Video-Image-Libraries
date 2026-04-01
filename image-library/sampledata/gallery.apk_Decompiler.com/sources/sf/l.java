package Sf;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends m implements Iterator, C1227c, a {
    public int d;
    public Object e;
    public Iterator f;
    public C1227c g;

    public final RuntimeException a() {
        int i2 = this.d;
        if (i2 == 4) {
            return new NoSuchElementException();
        }
        if (i2 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.d);
    }

    public final C1232h getContext() {
        return C1233i.d;
    }

    public final boolean hasNext() {
        while (true) {
            int i2 = this.d;
            if (i2 != 0) {
                if (i2 == 1) {
                    Iterator it = this.f;
                    j.b(it);
                    if (it.hasNext()) {
                        this.d = 2;
                        return true;
                    }
                    this.f = null;
                } else if (i2 == 2 || i2 == 3) {
                    return true;
                } else {
                    if (i2 == 4) {
                        return false;
                    }
                    throw a();
                }
            }
            this.d = 5;
            C1227c cVar = this.g;
            j.b(cVar);
            this.g = null;
            cVar.resumeWith(x.f4917a);
        }
    }

    public final Object next() {
        int i2 = this.d;
        if (i2 == 0 || i2 == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        } else if (i2 == 2) {
            this.d = 1;
            Iterator it = this.f;
            j.b(it);
            return it.next();
        } else if (i2 == 3) {
            this.d = 0;
            Object obj = this.e;
            this.e = null;
            return obj;
        } else {
            throw a();
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void resumeWith(Object obj) {
        L2.a.A(obj);
        this.d = 4;
    }
}
