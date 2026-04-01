package Nf;

import Be.a;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c implements Iterable, a {
    public Ge.a d;

    public final boolean isEmpty() {
        if (this.d.i() == 0) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return this.d.iterator();
    }
}
