package rf;

import com.google.android.appfunctions.schema.internal.dependencies.d0;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B implements Iterator {
    public final C1250A d;
    public d0 e;
    public int f;

    public B(C c5) {
        C1250A a7 = new C1250A(c5);
        this.d = a7;
        this.e = new d0(a7.next());
        this.f = c5.e;
    }

    public final boolean hasNext() {
        if (this.f > 0) {
            return true;
        }
        return false;
    }

    public final Object next() {
        if (!this.e.hasNext()) {
            this.e = new d0(this.d.next());
        }
        this.f--;
        return Byte.valueOf(this.e.a());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
