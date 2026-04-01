package X2;

import java.util.HashMap;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends r {
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final String f927c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f928h;

    /* renamed from: i  reason: collision with root package name */
    public final String f929i;

    /* renamed from: j  reason: collision with root package name */
    public final String f930j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;

    /* renamed from: o  reason: collision with root package name */
    public final HashMap f931o;

    public m(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, HashMap hashMap) {
        super(s.PRODUCT);
        this.b = str;
        this.f927c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.f928h = str7;
        this.f929i = str8;
        this.f930j = str9;
        this.k = str10;
        this.l = str11;
        this.m = str12;
        this.n = str13;
        this.f931o = hashMap;
    }

    public final String a() {
        return String.valueOf(this.b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (!Objects.equals(this.f927c, mVar.f927c) || !Objects.equals(this.d, mVar.d) || !Objects.equals(this.e, mVar.e) || !Objects.equals(this.f, mVar.f) || !Objects.equals(this.g, mVar.g) || !Objects.equals(this.f928h, mVar.f928h) || !Objects.equals(this.f929i, mVar.f929i) || !Objects.equals(this.f930j, mVar.f930j) || !Objects.equals(this.k, mVar.k) || !Objects.equals(this.l, mVar.l) || !Objects.equals(this.m, mVar.m) || !Objects.equals(this.n, mVar.n) || !this.f931o.equals(mVar.f931o)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f931o.hashCode() ^ (((((((((((Objects.hashCode(this.f927c) ^ Objects.hashCode(this.d)) ^ Objects.hashCode(this.e)) ^ Objects.hashCode(this.f)) ^ Objects.hashCode(this.g)) ^ Objects.hashCode(this.f928h)) ^ Objects.hashCode(this.f929i)) ^ Objects.hashCode(this.f930j)) ^ Objects.hashCode(this.k)) ^ Objects.hashCode(this.l)) ^ Objects.hashCode(this.m)) ^ Objects.hashCode(this.n));
    }
}
