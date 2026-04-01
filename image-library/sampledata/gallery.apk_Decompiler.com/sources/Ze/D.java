package Ze;

import c0.C0086a;
import i.C0212a;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D {

    /* renamed from: a  reason: collision with root package name */
    public final String f3930a;
    public final C1240g b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3931c;
    public final String d;
    public final String e;

    public D(String str, C1240g gVar, String str2, String str3) {
        j.e(str, "classInternalName");
        this.f3930a = str;
        this.b = gVar;
        this.f3931c = str2;
        this.d = str3;
        String str4 = gVar + '(' + str2 + ')' + str3;
        j.e(str4, "jvmDescriptor");
        this.e = str + '.' + str4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof D)) {
            return false;
        }
        D d2 = (D) obj;
        if (j.a(this.f3930a, d2.f3930a) && j.a(this.b, d2.b) && j.a(this.f3931c, d2.f3931c) && j.a(this.d, d2.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + C0212a.d((this.b.hashCode() + (this.f3930a.hashCode() * 31)) * 31, 31, this.f3931c);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NameAndSignature(classInternalName=");
        sb2.append(this.f3930a);
        sb2.append(", name=");
        sb2.append(this.b);
        sb2.append(", parameters=");
        sb2.append(this.f3931c);
        sb2.append(", returnType=");
        return C0086a.m(sb2, this.d, ')');
    }
}
