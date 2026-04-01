package ne;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* renamed from: ne.B  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1181B extends C1188f {
    public final ArrayList d;

    public C1181B(ArrayList arrayList) {
        this.d = arrayList;
    }

    public final void add(int i2, Object obj) {
        this.d.add(C1194l.E0(i2, this), obj);
    }

    public final void clear() {
        this.d.clear();
    }

    public final Object get(int i2) {
        return this.d.get(C1194l.D0(i2, this));
    }

    public final Iterator iterator() {
        return new C1180A(this, 0);
    }

    public final ListIterator listIterator() {
        return new C1180A(this, 0);
    }

    public final int p() {
        return this.d.size();
    }

    public final Object q(int i2) {
        return this.d.remove(C1194l.D0(i2, this));
    }

    public final Object set(int i2, Object obj) {
        return this.d.set(C1194l.D0(i2, this), obj);
    }

    public final ListIterator listIterator(int i2) {
        return new C1180A(this, i2);
    }
}
