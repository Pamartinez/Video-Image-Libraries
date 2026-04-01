package ef;

import Hf.B;
import Hf.Y;
import java.util.Set;
import kotlin.jvm.internal.j;

/* renamed from: ef.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0993a {

    /* renamed from: a  reason: collision with root package name */
    public final Y f4317a;
    public final C0994b b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f4318c;
    public final boolean d;
    public final Set e;
    public final B f;

    public C0993a(Y y, C0994b bVar, boolean z, boolean z3, Set set, B b5) {
        j.e(y, "howThisTypeIsUsed");
        j.e(bVar, "flexibility");
        this.f4317a = y;
        this.b = bVar;
        this.f4318c = z;
        this.d = z3;
        this.e = set;
        this.f = b5;
    }

    public static C0993a a(C0993a aVar, C0994b bVar, boolean z, Set set, B b5, int i2) {
        Y y = aVar.f4317a;
        if ((i2 & 2) != 0) {
            bVar = aVar.b;
        }
        C0994b bVar2 = bVar;
        if ((i2 & 4) != 0) {
            z = aVar.f4318c;
        }
        boolean z3 = z;
        boolean z7 = aVar.d;
        if ((i2 & 16) != 0) {
            set = aVar.e;
        }
        Set set2 = set;
        if ((i2 & 32) != 0) {
            b5 = aVar.f;
        }
        aVar.getClass();
        j.e(y, "howThisTypeIsUsed");
        j.e(bVar2, "flexibility");
        return new C0993a(y, bVar2, z3, z7, set2, b5);
    }

    public final C0993a b(C0994b bVar) {
        j.e(bVar, "flexibility");
        return a(this, bVar, false, (Set) null, (B) null, 61);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0993a)) {
            return false;
        }
        C0993a aVar = (C0993a) obj;
        if (j.a(aVar.f, this.f) && aVar.f4317a == this.f4317a && aVar.b == this.b && aVar.f4318c == this.f4318c && aVar.d == this.d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        B b5 = this.f;
        if (b5 != null) {
            i2 = b5.hashCode();
        } else {
            i2 = 0;
        }
        int hashCode = this.f4317a.hashCode() + (i2 * 31) + i2;
        int hashCode2 = this.b.hashCode() + (hashCode * 31) + hashCode;
        int i7 = (hashCode2 * 31) + (this.f4318c ? 1 : 0) + hashCode2;
        return (i7 * 31) + (this.d ? 1 : 0) + i7;
    }

    public final String toString() {
        return "JavaTypeAttributes(howThisTypeIsUsed=" + this.f4317a + ", flexibility=" + this.b + ", isRaw=" + this.f4318c + ", isForAnnotationParameter=" + this.d + ", visitedTypeParameters=" + this.e + ", defaultType=" + this.f + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0993a(Y y, boolean z, boolean z3, Set set, int i2) {
        this(y, C0994b.INFLEXIBLE, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? null : set, (B) null);
    }
}
