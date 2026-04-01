package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: ee.B  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0965B {

    /* renamed from: a  reason: collision with root package name */
    public final List f4268a;
    public final C0969b b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f4269c;

    public C0965B(List list, C0969b bVar, Object obj) {
        F.n(list, "addresses");
        this.f4268a = Collections.unmodifiableList(new ArrayList(list));
        F.n(bVar, "attributes");
        this.b = bVar;
        this.f4269c = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0965B)) {
            return false;
        }
        C0965B b5 = (C0965B) obj;
        if (!f.p(this.f4268a, b5.f4268a) || !f.p(this.b, b5.b) || !f.p(this.f4269c, b5.f4269c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4268a, this.b, this.f4269c});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4268a, "addresses");
        V.a(this.b, "attributes");
        V.a(this.f4269c, "loadBalancingPolicyConfig");
        return V.toString();
    }
}
