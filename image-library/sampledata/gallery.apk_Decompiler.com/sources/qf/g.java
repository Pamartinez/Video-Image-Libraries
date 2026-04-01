package Qf;

import Be.a;
import ig.i;
import java.util.Iterator;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements Iterator, a {
    public final i d;

    public g(Object[] objArr) {
        j.e(objArr, "array");
        this.d = j.g(objArr);
    }

    public final boolean hasNext() {
        return this.d.hasNext();
    }

    public final Object next() {
        return this.d.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
