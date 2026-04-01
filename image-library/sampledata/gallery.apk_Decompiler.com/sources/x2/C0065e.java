package X2;

import c0.C0086a;
import i.C0212a;

/* renamed from: X2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0065e {

    /* renamed from: a  reason: collision with root package name */
    public String f917a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f918c;
    public String d;
    public String e;
    public String f;
    public String g;

    /* renamed from: h  reason: collision with root package name */
    public String f919h;

    public final String toString() {
        String str = this.f917a;
        String str2 = this.b;
        String str3 = this.f918c;
        String str4 = this.d;
        String str5 = this.e;
        String str6 = this.f;
        String str7 = this.g;
        String str8 = this.f919h;
        StringBuilder q = C0086a.q("AddressStruct{type='", str, "', postOfficeBox='", str2, "', extended='");
        C0086a.z(q, str3, "', street='", str4, "', locality='");
        C0086a.z(q, str5, "', region='", str6, "', postalCode='");
        return C0212a.q(q, str7, "', country='", str8, "'}");
    }
}
