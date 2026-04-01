package X2;

import c0.C0086a;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    public String f934a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f935c;
    public String d;
    public String e;
    public String f;
    public String g;

    /* renamed from: h  reason: collision with root package name */
    public String f936h;

    public final String toString() {
        String str = this.f934a;
        String str2 = this.b;
        String str3 = this.f935c;
        String str4 = this.d;
        String str5 = this.e;
        String str6 = this.f;
        String str7 = this.g;
        String str8 = this.f936h;
        StringBuilder q = C0086a.q("NameStruct{familyName='", str, "', givenName='", str2, "', additionalName='");
        C0086a.z(q, str3, "', honorificPrefix='", str4, "', honorificSuffix='");
        C0086a.z(q, str5, "', phoneticFamilyName='", str6, "', phoneticGivenName='");
        return C0212a.q(q, str7, "', phoneticAdditionalName='", str8, "'}");
    }
}
