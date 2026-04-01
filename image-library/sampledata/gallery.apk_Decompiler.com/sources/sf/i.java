package Sf;

import Ae.b;
import Be.a;
import Qe.C0812b;
import Qe.C0822l;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements Iterator, a {
    public final /* synthetic */ int d = 0;
    public int e;
    public Object f;
    public final Object g;

    public i(o oVar) {
        this.g = ((k) oVar.b).iterator();
        this.e = -1;
    }

    public void a() {
        Object obj;
        int i2;
        j jVar = (j) this.g;
        if (this.e == -2) {
            obj = ((Ae.a) jVar.b).invoke();
        } else {
            Object obj2 = this.f;
            j.b(obj2);
            obj = ((b) jVar.f3737c).invoke(obj2);
        }
        this.f = obj;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        this.e = i2;
    }

    public void b() {
        Iterator it = (Iterator) this.g;
        if (it.hasNext()) {
            Object next = it.next();
            C0822l lVar = (C0822l) next;
            j.e(lVar, "it");
            if (lVar instanceof C0812b) {
                this.e = 1;
                this.f = next;
                return;
            }
        }
        this.e = 0;
    }

    public final boolean hasNext() {
        switch (this.d) {
            case 0:
                if (this.e < 0) {
                    a();
                }
                if (this.e == 1) {
                    return true;
                }
                return false;
            default:
                if (this.e == -1) {
                    b();
                }
                if (this.e == 1) {
                    return true;
                }
                return false;
        }
    }

    public final Object next() {
        switch (this.d) {
            case 0:
                if (this.e < 0) {
                    a();
                }
                if (this.e != 0) {
                    Object obj = this.f;
                    j.c(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
                    this.e = -1;
                    return obj;
                }
                throw new NoSuchElementException();
            default:
                if (this.e == -1) {
                    b();
                }
                if (this.e != 0) {
                    Object obj2 = this.f;
                    this.f = null;
                    this.e = -1;
                    return obj2;
                }
                throw new NoSuchElementException();
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

    public i(j jVar) {
        this.g = jVar;
        this.e = -2;
    }
}
