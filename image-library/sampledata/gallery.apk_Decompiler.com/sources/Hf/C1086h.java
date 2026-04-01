package hf;

import c0.C0086a;
import kotlin.jvm.internal.j;

/* renamed from: hf.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1086h {

    /* renamed from: a  reason: collision with root package name */
    public final C1085g f4588a;
    public final boolean b;

    public C1086h(C1085g gVar, boolean z) {
        j.e(gVar, "qualifier");
        this.f4588a = gVar;
        this.b = z;
    }

    public static C1086h a(C1086h hVar, C1085g gVar, boolean z, int i2) {
        if ((i2 & 1) != 0) {
            gVar = hVar.f4588a;
        }
        if ((i2 & 2) != 0) {
            z = hVar.b;
        }
        hVar.getClass();
        j.e(gVar, "qualifier");
        return new C1086h(gVar, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1086h)) {
            return false;
        }
        C1086h hVar = (C1086h) obj;
        if (this.f4588a == hVar.f4588a && this.b == hVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (this.f4588a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NullabilityQualifierWithMigrationStatus(qualifier=");
        sb2.append(this.f4588a);
        sb2.append(", isForWarningOnly=");
        return C0086a.n(sb2, this.b, ')');
    }
}
