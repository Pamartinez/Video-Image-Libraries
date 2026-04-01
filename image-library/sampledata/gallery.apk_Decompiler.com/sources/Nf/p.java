package Nf;

import Ge.a;
import Hf.C0759h;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends a {
    public final C0759h d;
    public final int e;

    public p(int i2, C0759h hVar) {
        this.d = hVar;
        this.e = i2;
    }

    public final Object get(int i2) {
        if (i2 == this.e) {
            return this.d;
        }
        return null;
    }

    public final int i() {
        return 1;
    }

    public final Iterator iterator() {
        return new o(0, this);
    }

    public final void p(int i2, C0759h hVar) {
        throw new IllegalStateException();
    }
}
