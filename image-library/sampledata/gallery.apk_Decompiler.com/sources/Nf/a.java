package Nf;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Iterator, Be.a {
    public int d;
    public Object e;
    public int f = -1;
    public final /* synthetic */ b g;

    public a(b bVar) {
        this.g = bVar;
    }

    public final boolean a() {
        int i2;
        Object[] objArr;
        this.d = 3;
        do {
            i2 = this.f + 1;
            this.f = i2;
            objArr = this.g.d;
            if (i2 >= objArr.length || objArr[i2] != null) {
            }
            i2 = this.f + 1;
            this.f = i2;
            objArr = this.g.d;
            break;
        } while (objArr[i2] != null);
        if (i2 >= objArr.length) {
            this.d = 2;
        } else {
            Object obj = objArr[i2];
            j.c(obj, "null cannot be cast to non-null type T of org.jetbrains.kotlin.util.ArrayMapImpl");
            this.e = obj;
            this.d = 1;
        }
        if (this.d == 1) {
            return true;
        }
        return false;
    }

    public final boolean hasNext() {
        int i2 = this.d;
        if (i2 == 0) {
            return a();
        }
        if (i2 == 1) {
            return true;
        }
        if (i2 == 2) {
            return false;
        }
        throw new IllegalArgumentException("hasNext called when the iterator is in the FAILED state.");
    }

    public final Object next() {
        int i2 = this.d;
        if (i2 == 1) {
            this.d = 0;
            return this.e;
        } else if (i2 == 2 || !a()) {
            throw new NoSuchElementException();
        } else {
            this.d = 0;
            return this.e;
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
