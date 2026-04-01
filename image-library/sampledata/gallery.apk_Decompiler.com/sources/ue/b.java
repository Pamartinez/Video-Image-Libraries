package Ue;

import Qe.a0;
import Qe.d0;
import Qe.e0;
import Qe.f0;
import Qe.i0;
import Qe.j0;
import kotlin.jvm.internal.j;
import oe.C1217f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends j0 {

    /* renamed from: c  reason: collision with root package name */
    public static final b f3826c = new j0("protected_and_package", true);

    public final Integer a(j0 j0Var) {
        j.e(j0Var, "visibility");
        if (equals(j0Var)) {
            return 0;
        }
        if (j0Var == a0.f3664c) {
            return null;
        }
        C1217f fVar = i0.f3672a;
        if (j0Var == d0.f3667c || j0Var == e0.f3668c) {
            return 1;
        }
        return -1;
    }

    public final String b() {
        return "protected/*protected and package*/";
    }

    public final j0 c() {
        return f0.f3669c;
    }
}
