package X2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends r {
    public final String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final String[] f942c;
    public final String d;
    public final String e;

    public v(String str, String str2) {
        super(s.SMS);
        this.b = new String[]{str};
        this.f942c = new String[]{null};
        this.d = null;
        this.e = str2;
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(100);
        r.c(sb2, this.b);
        r.b(sb2, this.d);
        r.b(sb2, this.e);
        return sb2.toString();
    }

    public v(String[] strArr, String str, String[] strArr2, String str2) {
        super(s.SMS);
        this.b = strArr;
        this.f942c = strArr2;
        this.d = str;
        this.e = str2;
    }
}
