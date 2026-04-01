package Ze;

import c0.C0086a;
import hf.C1085g;
import hf.C1086h;
import java.util.Collection;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final C1086h f3947a;
    public final Collection b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f3948c;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public m(C1086h hVar, Collection collection) {
        this(hVar, collection, hVar.f4588a == C1085g.NOT_NULL);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (j.a(this.f3947a, mVar.f3947a) && j.a(this.b, mVar.b) && this.f3948c == mVar.f3948c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        return Boolean.hashCode(this.f3948c) + ((hashCode + (this.f3947a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JavaDefaultQualifiers(nullabilityQualifier=");
        sb2.append(this.f3947a);
        sb2.append(", qualifierApplicabilityTypes=");
        sb2.append(this.b);
        sb2.append(", definitelyNotNull=");
        return C0086a.n(sb2, this.f3948c, ')');
    }

    public m(C1086h hVar, Collection collection, boolean z) {
        j.e(collection, "qualifierApplicabilityTypes");
        this.f3947a = hVar;
        this.b = collection;
        this.f3948c = z;
    }
}
