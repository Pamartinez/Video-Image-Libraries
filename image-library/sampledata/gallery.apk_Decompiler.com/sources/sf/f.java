package Sf;

import Be.a;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements Iterator, a {
    public final /* synthetic */ int d = 0;
    public final Iterator e;
    public int f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ k f3731h;

    public f(g gVar) {
        this.f3731h = gVar;
        this.e = gVar.f3732a.iterator();
        this.f = -1;
    }

    public void a() {
        Object next;
        g gVar = (g) this.f3731h;
        do {
            Iterator it = this.e;
            if (it.hasNext()) {
                next = it.next();
            } else {
                this.f = 0;
                return;
            }
        } while (((Boolean) gVar.f3733c.invoke(next)).booleanValue() != gVar.b);
        this.g = next;
        this.f = 1;
    }

    public boolean b() {
        Iterator it;
        Iterator it2 = (Iterator) this.g;
        if (it2 == null || !it2.hasNext()) {
            do {
                Iterator it3 = this.e;
                if (it3.hasNext()) {
                    Object next = it3.next();
                    h hVar = (h) this.f3731h;
                    it = (Iterator) hVar.f3735c.invoke(hVar.b.invoke(next));
                } else {
                    this.f = 2;
                    this.g = null;
                    return false;
                }
            } while (!it.hasNext());
            this.g = it;
            this.f = 1;
            return true;
        }
        this.f = 1;
        return true;
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                if (this.f == -1) {
                    a();
                }
                if (this.f == 1) {
                    return true;
                }
                return false;
            default:
                int i2 = this.f;
                if (i2 == 1) {
                    return true;
                }
                if (i2 == 2) {
                    return false;
                }
                return b();
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                if (this.f == -1) {
                    a();
                }
                if (this.f != 0) {
                    Object obj = this.g;
                    this.g = null;
                    this.f = -1;
                    return obj;
                }
                throw new NoSuchElementException();
            default:
                int i2 = this.f;
                if (i2 == 2) {
                    throw new NoSuchElementException();
                } else if (i2 != 0 || b()) {
                    this.f = 0;
                    Iterator it = (Iterator) this.g;
                    j.b(it);
                    return it.next();
                } else {
                    throw new NoSuchElementException();
                }
        }
    }

    public final void remove() {
        switch (this.d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public f(h hVar) {
        this.f3731h = hVar;
        this.e = hVar.f3734a.iterator();
    }
}
