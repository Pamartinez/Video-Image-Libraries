package Qe;

import kotlin.jvm.internal.j;
import oe.C1217f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class j0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f3673a;
    public final boolean b;

    public j0(String str, boolean z) {
        this.f3673a = str;
        this.b = z;
    }

    public Integer a(j0 j0Var) {
        j.e(j0Var, "visibility");
        C1217f fVar = i0.f3672a;
        if (this == j0Var) {
            return 0;
        }
        C1217f fVar2 = i0.f3672a;
        Integer num = (Integer) fVar2.get(this);
        Integer num2 = (Integer) fVar2.get(j0Var);
        if (num == null || num2 == null || num.equals(num2)) {
            return null;
        }
        return Integer.valueOf(num.intValue() - num2.intValue());
    }

    public String b() {
        return this.f3673a;
    }

    public final String toString() {
        return b();
    }

    public j0 c() {
        return this;
    }
}
