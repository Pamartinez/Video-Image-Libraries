package Qe;

import java.util.ArrayList;
import java.util.Map;
import ne.z;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends W {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f3656a;
    public final Map b;

    public D(ArrayList arrayList) {
        this.f3656a = arrayList;
        Map e02 = z.e0(arrayList);
        if (e02.size() == arrayList.size()) {
            this.b = e02;
            return;
        }
        throw new IllegalArgumentException("Some properties have the same names");
    }

    public final boolean a(C1240g gVar) {
        return this.b.containsKey(gVar);
    }

    public final String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + this.f3656a + ')';
    }
}
