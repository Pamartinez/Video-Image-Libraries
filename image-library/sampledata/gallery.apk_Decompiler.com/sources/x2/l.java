package X2;

import c0.C0086a;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends w {

    /* renamed from: c  reason: collision with root package name */
    public String f926c;
    public String d;
    public k e;

    public final String toString() {
        String str = this.f926c;
        String str2 = this.d;
        k kVar = this.e;
        StringBuilder q = C0086a.q("EventStruct{date='", str, "', title='", str2, "', type=");
        q.append(kVar);
        q.append(", solarType='");
        q.append((String) this.f943a);
        q.append("', solarDate='");
        return C0212a.p(q, (String) this.b, "'}");
    }
}
