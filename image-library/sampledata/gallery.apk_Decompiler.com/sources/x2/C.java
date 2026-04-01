package X2;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends r {
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f905c;
    public final String d;
    public final String e;
    public final int f;
    public final char g;

    /* renamed from: h  reason: collision with root package name */
    public final String f906h;

    public C(String str, String str2, String str3, String str4, int i2, char c5, String str5) {
        super(s.VIN);
        this.b = str;
        this.f905c = str2;
        this.d = str3;
        this.e = str4;
        this.f = i2;
        this.g = c5;
        this.f906h = str5;
    }

    public final String a() {
        StringBuilder sb2 = new StringBuilder(50);
        sb2.append(this.b);
        sb2.append(' ');
        sb2.append(this.f905c);
        sb2.append(' ');
        sb2.append(this.d);
        sb2.append(10);
        String str = this.e;
        if (str != null) {
            sb2.append(str);
            sb2.append(' ');
        }
        sb2.append(this.f);
        sb2.append(' ');
        sb2.append(this.g);
        sb2.append(' ');
        return C0086a.m(sb2, this.f906h, 10);
    }
}
