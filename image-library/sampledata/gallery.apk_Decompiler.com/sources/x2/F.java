package X2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class F extends r {
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f910c;
    public final String d;
    public final boolean e;
    public final G f;
    public final String g;

    public F(String str, String str2, String str3, boolean z, String str4, G g3) {
        super(s.WIFI);
        this.b = str2;
        this.f910c = str;
        this.d = str3;
        this.e = z;
        this.g = str4;
        this.f = g3;
    }

    public String a() {
        StringBuilder sb2 = new StringBuilder(80);
        r.b(sb2, this.b);
        r.b(sb2, this.f910c);
        r.b(sb2, this.d);
        r.b(sb2, Boolean.toString(this.e));
        return sb2.toString();
    }
}
