package He;

import Ke.r0;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x {

    /* renamed from: c  reason: collision with root package name */
    public static final x f3421c = new x((y) null, (r0) null);

    /* renamed from: a  reason: collision with root package name */
    public final y f3422a;
    public final u b;

    public x(y yVar, r0 r0Var) {
        boolean z;
        String str;
        this.f3422a = yVar;
        this.b = r0Var;
        boolean z3 = false;
        if (yVar == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (r0Var == null ? true : z3)) {
            if (yVar == null) {
                str = "Star projection must have no type specified.";
            } else {
                str = "The projection variance " + yVar + " requires type to be specified.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        if (this.f3422a == xVar.f3422a && j.a(this.b, xVar.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i2;
        int i7 = 0;
        y yVar = this.f3422a;
        if (yVar == null) {
            i2 = 0;
        } else {
            i2 = yVar.hashCode();
        }
        int i8 = i2 * 31;
        u uVar = this.b;
        if (uVar != null) {
            i7 = uVar.hashCode();
        }
        return i8 + i7;
    }

    public final String toString() {
        int i2;
        y yVar = this.f3422a;
        if (yVar == null) {
            i2 = -1;
        } else {
            i2 = w.f3420a[yVar.ordinal()];
        }
        if (i2 == -1) {
            return "*";
        }
        u uVar = this.b;
        if (i2 == 1) {
            return String.valueOf(uVar);
        }
        if (i2 == 2) {
            return "in " + uVar;
        } else if (i2 == 3) {
            return "out " + uVar;
        } else {
            throw new RuntimeException();
        }
    }
}
