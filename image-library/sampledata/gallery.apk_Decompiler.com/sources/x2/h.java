package X2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends r {
    public final String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final String[] f925c;
    public final String[] d;
    public final String e;
    public final String f;

    public h(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        super(s.EMAIL_ADDRESS);
        this.b = strArr;
        this.f925c = strArr2;
        this.d = strArr3;
        this.e = str;
        this.f = str2;
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(30);
        r.c(sb2, this.b);
        r.c(sb2, this.f925c);
        r.c(sb2, this.d);
        r.b(sb2, this.e);
        r.b(sb2, this.f);
        return sb2.toString();
    }
}
