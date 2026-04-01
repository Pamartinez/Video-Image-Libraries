package Ke;

import L2.a;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;

/* renamed from: Ke.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0789g extends a {
    public final List d;

    public C0789g(Class cls) {
        j.e(cls, "jClass");
        Object[] declaredMethods = cls.getDeclaredMethods();
        j.d(declaredMethods, "getDeclaredMethods(...)");
        C0787f fVar = new C0787f(0);
        if (declaredMethods.length != 0) {
            declaredMethods = Arrays.copyOf(declaredMethods, declaredMethods.length);
            j.d(declaredMethods, "copyOf(...)");
            if (declaredMethods.length > 1) {
                Arrays.sort(declaredMethods, fVar);
            }
        }
        this.d = C1192j.a0(declaredMethods);
    }

    public final String d() {
        return C1194l.R0(this.d, "", "<init>(", ")V", C0779b.g, 24);
    }
}
