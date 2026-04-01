package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S {

    /* renamed from: a  reason: collision with root package name */
    public final List f4280a;
    public final C0969b b;

    /* renamed from: c  reason: collision with root package name */
    public final Q f4281c;

    public S(List list, C0969b bVar, Q q) {
        this.f4280a = Collections.unmodifiableList(new ArrayList(list));
        F.n(bVar, "attributes");
        this.b = bVar;
        this.f4281c = q;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof S)) {
            return false;
        }
        S s = (S) obj;
        if (!f.p(this.f4280a, s.f4280a) || !f.p(this.b, s.b) || !f.p(this.f4281c, s.f4281c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4280a, this.b, this.f4281c});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4280a, "addresses");
        V.a(this.b, "attributes");
        V.a(this.f4281c, "serviceConfig");
        return V.toString();
    }
}
