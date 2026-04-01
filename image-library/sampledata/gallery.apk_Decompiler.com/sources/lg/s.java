package lg;

import kotlin.jvm.internal.j;
import mg.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends C {
    public final boolean d;
    public final String e;

    public s(String str, boolean z) {
        j.e(str, "body");
        this.d = z;
        this.e = str.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s.class != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        if (this.d == sVar.d && j.a(this.e, sVar.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() + (Boolean.hashCode(this.d) * 31);
    }

    public final String i() {
        return this.e;
    }

    public final String toString() {
        boolean z = this.d;
        String str = this.e;
        if (!z) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        t.a(sb2, str);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }
}
