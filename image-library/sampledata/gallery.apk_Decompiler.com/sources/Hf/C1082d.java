package hf;

import c0.C0086a;
import i.C0212a;

/* renamed from: hf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1082d {
    public static final C1082d e = new C1082d((C1085g) null, false);

    /* renamed from: a  reason: collision with root package name */
    public final C1085g f4586a;
    public final C1083e b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f4587c;
    public final boolean d;

    public C1082d(C1085g gVar, C1083e eVar, boolean z, boolean z3) {
        this.f4586a = gVar;
        this.b = eVar;
        this.f4587c = z;
        this.d = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1082d)) {
            return false;
        }
        C1082d dVar = (C1082d) obj;
        if (this.f4586a == dVar.f4586a && this.b == dVar.b && this.f4587c == dVar.f4587c && this.d == dVar.d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        C1085g gVar = this.f4586a;
        if (gVar == null) {
            i2 = 0;
        } else {
            i2 = gVar.hashCode();
        }
        int i8 = i2 * 31;
        C1083e eVar = this.b;
        if (eVar != null) {
            i7 = eVar.hashCode();
        }
        return Boolean.hashCode(this.d) + C0212a.e((i8 + i7) * 31, 31, this.f4587c);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JavaTypeQualifiers(nullability=");
        sb2.append(this.f4586a);
        sb2.append(", mutability=");
        sb2.append(this.b);
        sb2.append(", definitelyNotNull=");
        sb2.append(this.f4587c);
        sb2.append(", isNullabilityQualifierForWarning=");
        return C0086a.n(sb2, this.d, ')');
    }

    public /* synthetic */ C1082d(C1085g gVar, boolean z) {
        this(gVar, (C1083e) null, z, false);
    }
}
