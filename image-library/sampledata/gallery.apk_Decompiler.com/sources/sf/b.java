package Sf;

import Be.a;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import ne.C1195m;
import ne.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Iterator, a {
    public final /* synthetic */ int d = 0;
    public final Iterator e;
    public int f;

    public b(Iterator it) {
        j.e(it, "iterator");
        this.e = it;
    }

    public final boolean hasNext() {
        Iterator it;
        switch (this.d) {
            case 0:
                break;
            default:
                return this.e.hasNext();
        }
        while (true) {
            int i2 = this.f;
            it = this.e;
            if (i2 > 0 && it.hasNext()) {
                it.next();
                this.f--;
            }
        }
        return it.hasNext();
    }

    public final Object next() {
        Iterator it;
        switch (this.d) {
            case 0:
                break;
            default:
                int i2 = this.f;
                this.f = i2 + 1;
                if (i2 >= 0) {
                    return new x(i2, this.e.next());
                }
                C1195m.v0();
                throw null;
        }
        while (true) {
            int i7 = this.f;
            it = this.e;
            if (i7 > 0 && it.hasNext()) {
                it.next();
                this.f--;
            }
        }
        return it.next();
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public b(c cVar) {
        this.e = cVar.f3729a.iterator();
        this.f = cVar.b;
    }
}
