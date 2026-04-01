package Q0;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements Comparable, Serializable {
    public final int d;
    public final int e;
    public final int f;
    public final String g;

    /* renamed from: h  reason: collision with root package name */
    public final String f612h;

    /* renamed from: i  reason: collision with root package name */
    public final String f613i;

    public k(int i2, int i7, int i8, String str, String str2, String str3) {
        this.d = i2;
        this.e = i7;
        this.f = i8;
        this.f613i = str;
        this.g = str2 == null ? "" : str2;
        this.f612h = str3 == null ? "" : str3;
    }

    public final int compareTo(Object obj) {
        k kVar = (k) obj;
        if (kVar == this) {
            return 0;
        }
        int compareTo = this.g.compareTo(kVar.g);
        if (compareTo == 0 && (compareTo = this.f612h.compareTo(kVar.f612h)) == 0 && (compareTo = this.d - kVar.d) == 0 && (compareTo = this.e - kVar.e) == 0) {
            return this.f - kVar.f;
        }
        return compareTo;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != k.class) {
            return false;
        }
        k kVar = (k) obj;
        if (kVar.d == this.d && kVar.e == this.e && kVar.f == this.f && kVar.f612h.equals(this.f612h) && kVar.g.equals(this.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f612h.hashCode() ^ (((this.g.hashCode() + this.d) - this.e) + this.f);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.d);
        sb2.append('.');
        sb2.append(this.e);
        sb2.append('.');
        sb2.append(this.f);
        String str = this.f613i;
        if (str != null && str.length() > 0) {
            sb2.append('-');
            sb2.append(str);
        }
        return sb2.toString();
    }
}
