package X2;

/* renamed from: X2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0064d extends r {
    public final String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final q[] f911c;
    public final String[] d;
    public final String[] e;
    public final String f;
    public final String[] g;

    /* renamed from: h  reason: collision with root package name */
    public final String[] f912h;

    /* renamed from: i  reason: collision with root package name */
    public final String[] f913i;

    /* renamed from: j  reason: collision with root package name */
    public final String[] f914j;
    public final String k;
    public final String l;
    public final String[] m;
    public final String[] n;

    /* renamed from: o  reason: collision with root package name */
    public final String f915o;

    /* renamed from: p  reason: collision with root package name */
    public final String f916p;
    public final C0066f q;
    public final String r;
    public final String[] s;
    public final String[] t;
    public final C0065e[] u;
    public final l[] v;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0064d(String[] strArr, q[] qVarArr, String[] strArr2, String[] strArr3, String str, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7, String str2, String str3, String[] strArr8, String[] strArr9, String str4, String str5, C0066f fVar, String str6, String[] strArr10, String[] strArr11, C0065e[] eVarArr, l[] lVarArr) {
        super(s.ADDRESSBOOK);
        String[] strArr12 = strArr7;
        String[] strArr13 = strArr8;
        String[] strArr14 = strArr9;
        if (strArr4 != null && strArr5 != null && strArr4.length != strArr5.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        } else if (strArr6 != null && strArr12 != null && strArr6.length != strArr12.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        } else if (strArr13 == null || strArr14 == null || strArr13.length == strArr14.length) {
            this.b = strArr;
            this.f911c = qVarArr;
            this.d = strArr2;
            this.e = strArr3;
            this.f = str;
            this.g = strArr4;
            this.f912h = strArr5;
            this.f913i = strArr6;
            this.f914j = strArr12;
            this.k = str2;
            this.l = str3;
            this.m = strArr13;
            this.n = strArr14;
            this.f915o = str4;
            this.f916p = str5;
            this.q = fVar;
            this.r = str6;
            this.s = strArr10;
            this.t = strArr11;
            this.u = eVarArr;
            this.v = lVarArr;
        } else {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(100);
        r.c(sb2, this.b);
        r.c(sb2, this.e);
        r.b(sb2, this.f);
        r.b(sb2, this.r);
        r.b(sb2, this.f915o);
        r.c(sb2, this.m);
        r.c(sb2, this.g);
        r.c(sb2, this.f913i);
        r.b(sb2, this.k);
        r.c(sb2, this.s);
        r.b(sb2, this.f916p);
        r.c(sb2, this.t);
        r.b(sb2, this.l);
        return sb2.toString();
    }
}
