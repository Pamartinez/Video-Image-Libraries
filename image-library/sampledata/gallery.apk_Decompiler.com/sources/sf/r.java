package Sf;

import Be.a;
import ig.i;
import java.util.Iterator;
import kg.C1139u;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements Iterable, a {
    public final /* synthetic */ int d;
    public final Object e;

    public /* synthetic */ r(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Iterator iterator() {
        switch (this.d) {
            case 0:
                return ((k) this.e).iterator();
            case 1:
                return new i((C1139u) this.e);
            case 2:
                return j.g((Object[]) this.e);
            default:
                return new b((Iterator) ((Ae.a) this.e).invoke());
        }
    }
}
