package Hf;

import Qe.V;
import ef.C0993a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O {

    /* renamed from: a  reason: collision with root package name */
    public final V f3434a;
    public final C0993a b;

    public O(V v, C0993a aVar) {
        j.e(v, "typeParameter");
        j.e(aVar, "typeAttr");
        this.f3434a = v;
        this.b = aVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof O)) {
            return false;
        }
        O o2 = (O) obj;
        if (!j.a(o2.f3434a, this.f3434a) || !j.a(o2.b, this.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int hashCode = this.f3434a.hashCode();
        return this.b.hashCode() + (hashCode * 31) + hashCode;
    }

    public final String toString() {
        return "DataToEraseUpperBound(typeParameter=" + this.f3434a + ", typeAttr=" + this.b + ')';
    }
}
