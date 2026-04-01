package ge;

import B1.a;
import D1.f;
import E2.k;
import ee.C0967D;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H1 {

    /* renamed from: a  reason: collision with root package name */
    public final C0967D f4435a;
    public final Object b;

    public H1(C0967D d, Object obj) {
        this.f4435a = d;
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && H1.class == obj.getClass()) {
            H1 h12 = (H1) obj;
            if (!f.p(this.f4435a, h12.f4435a) || !f.p(this.b, h12.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4435a, this.b});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4435a, "provider");
        V.a(this.b, "config");
        return V.toString();
    }
}
